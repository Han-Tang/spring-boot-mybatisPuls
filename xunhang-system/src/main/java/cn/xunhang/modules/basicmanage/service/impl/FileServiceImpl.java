package cn.xunhang.modules.basicmanage.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.base.PropertiesConifig;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.utils.FileUploadUtils;
import cn.xunhang.modules.basicmanage.dao.InfoFileDao;
import cn.xunhang.modules.basicmanage.entity.InfoFile;
import cn.xunhang.modules.basicmanage.service.FileService;
import cn.xunhang.common.enums.FilePurpose;
import cn.xunhang.common.enums.FileTypeAndPath;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-07-31
 */
@Service
public class FileServiceImpl extends InfoBaseServiceImpl<InfoFile,InfoFileDao> implements FileService {

    @Autowired
    private InfoFileDao fileDao;

    @Override
    @Transactional
    public List<String> saveFile(MultipartFile[] multipartFile, String fileType, String filePurpose, String fkPurposeId) throws IOException {
        //获取文件类型对应路径
        String fileTypePath = FileTypeAndPath.getPathByType(fileType);
        //如果找不到文档类型对应路径配置
        if (StringUtils.isEmpty(fileTypePath)) {
            throw new BusinessException(CommonErrorResult.SECRET_FAIL, "文档类型错误");
        }
        //如果用途不存在
        FilePurpose filePurposeObj = FilePurpose.getByCode(filePurpose);
        if (filePurposeObj == null) {
            throw new BusinessException(CommonErrorResult.SECRET_FAIL, "指定文件用途不存在");
        }
        //如果用途的绑定对象不存在
        Object belongToObj = fkPurposeId;
        if (belongToObj == null) {
            throw new BusinessException(CommonErrorResult.SECRET_FAIL, "指定绑定对象不存在");
        }
        List<String> list = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            //获取上传文件类型的扩展名
            String extensionName = FileUploadUtils.getExtensionName(file);
            //生成唯一文件标识，作为文件名
            String uuIdFileName = file.getOriginalFilename();//UUID.randomUUID().toString();
            //拼接文件完整存储URL
            String fileLocation = PropertiesConifig.getFileRoot() + fileTypePath + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            //拼接逻辑访问路径给前端请求
            String fileLogicLocation = PropertiesConifig.getFileRoot() + fileTypePath + "/" + filePurpose + "/" + uuIdFileName + "." + extensionName;
            //创建文件
            File repositoryfile = new File(fileLocation);
            //如果目录不存在，则创建
            if (!repositoryfile.getParentFile().exists()) {
                repositoryfile.getParentFile().mkdirs();
            }
            //保存文件
            file.transferTo(repositoryfile);

            //保存文件信息到数据库
            insertFileInfoToDB(fileType, filePurpose, fileLogicLocation, fkPurposeId,uuIdFileName);
            list.add(fileLogicLocation);
        }
        //返回数据
        return list;
    }

    @Transactional
    protected void insertFileInfoToDB(String fileType, String filePurpose, String fileLocation, String fkPurposeId, String fileName) {
        InfoFile fileUpload = new InfoFile();
        fileUpload.setFileType(fileType);
        fileUpload.setPurpose(filePurpose);
        fileUpload.setLocation(fileLocation);
        fileUpload.setCode(fkPurposeId);
        fileUpload.setName(fileName);
        fileDao.insert(fileUpload);
    }

    @Override
    public List<InfoFile> queryList(Map<String, Object> params) {
        List<InfoFile> list = fileDao.selectList(new EntityWrapper<InfoFile>()
                .eq("purpose",params.get("purpose"))
                .eq("code",params.get("code"))
                .eq("deleted",0)
                .orderBy("createDate",false));
        return list;
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) {
        List<String> ids = (List<String>) params.get("ids");
        for (String id : ids){
            InfoFile infoFile = fileDao.selectById(id);
            infoFile.setDeleted(true);
            int i = fileDao.updateById(infoFile);
            if(i == 0){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"删除附件失败");
            }
        }
    }

}
