package cn.xunhang.modules.basicmanage.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.basicmanage.entity.InfoFile;
import cn.xunhang.modules.basicmanage.service.FileService;
import cn.xunhang.modules.business.controller.infoBase.InfoBaseController;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tyj
 * @since 2018-07-31
 */
@RestController
@RequestMapping("/file")
public class FileController extends InfoBaseController {

    @Autowired
    FileService fileService;

    /**
     * 上传文件
     * @param multipartFile 文件
     * @param fileType      文件类型(FileTypeAndPath.class  获取文件类型对应文件存储位置 image/file/..)
     * @param filePurpose   文件关联类型（FilePurpose.class）
     * @param fkPurposeId   文件关联id(code)
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/uploadFile",method=RequestMethod.POST)
    @ResponseBody
    public Response uploadFile(
            @RequestParam(value="file" ,required=true) MultipartFile[] multipartFile,
            @RequestParam(value="fileType",required=true) String fileType,
            @RequestParam(value="filePurpose",required=true) String filePurpose,
            @RequestParam(value="fkPurposeId",required=true) String fkPurposeId)throws IOException
    {
        return new ObjectResponse<>(fileService.saveFile(multipartFile, fileType, filePurpose, fkPurposeId));
    }

    /**
     * 附件列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Response list(@RequestBody Map<String, Object> params){
        List<InfoFile> list = fileService.queryList(params);
        return new ObjectResponse<List>(list);
    }

    /**
     * 删除附件成功
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(@RequestBody Map<String, Object> params){
        fileService.delete(params);
        return new Response("删除附件成功");
    }

    /**
     * 下载文件
     * @param response
     * @param params
     */
    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public void downloadFile(HttpServletResponse response, @RequestBody Map<String,Object> params) throws Exception {
        String id = (String) params.get("id");
        InfoFile infoFile = fileService.selectById(id);
        String path = infoFile.getLocation();
        String fileName = infoFile.getName();

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");

        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        IOUtils.copy(fis, response.getOutputStream());
        response.flushBuffer();
    }


}
