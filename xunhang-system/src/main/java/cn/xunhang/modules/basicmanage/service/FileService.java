package cn.xunhang.modules.basicmanage.service;

import cn.xunhang.modules.basicmanage.dao.InfoFileDao;
import cn.xunhang.modules.basicmanage.entity.InfoFile;
import cn.xunhang.modules.basicmanage.service.infoBase.InfoBaseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzc
 * @since 2018-07-31
 */
public interface FileService extends InfoBaseService<InfoFile,InfoFileDao> {
    /**
     * 存储文件到文件系统
     * @param multipartFile
     * @param fileType
     * @param filePurpose
     * @param fkPurposeId
     * @return
     * @throws IOException
     */
    List<String> saveFile(MultipartFile[] multipartFile, String fileType, String filePurpose, String fkPurposeId) throws IOException;

    /**
     * 读取附件列表
     * @param params
     * @return
     */
    List<InfoFile> queryList(Map<String, Object> params);

    /**
     * 读取附件列表
     * @param params
     * @return
     */
    void delete(Map<String, Object> params);

}
