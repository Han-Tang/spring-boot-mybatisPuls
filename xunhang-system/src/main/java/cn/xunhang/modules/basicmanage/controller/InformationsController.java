package cn.xunhang.modules.basicmanage.controller;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.InforEnum;
import cn.xunhang.common.excel.ExcelLogs;
import cn.xunhang.common.excel.ExcelUtil;
import cn.xunhang.modules.basicmanage.entity.Informations;
import cn.xunhang.modules.basicmanage.service.InformationsService;
import cn.xunhang.modules.business.controller.infoBase.InfoBaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 基础信息
 * @author tyj
 * @date 2018-08-06 10:25
 */
@RestController
@RequestMapping("/info")
public class InformationsController extends InfoBaseController {

    @Autowired
    private InformationsService informationsService;


    /**
     * 查询配置资料分页
     * @param params
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("basic:info:list")
    public Response list(@RequestBody Map<String, Object> params) {
        //查询条件
        Page<Informations> pg = new Page<Informations>((int) params.get("current"),(int) params.get("offset"));
        EntityWrapper<Informations> entityWrapper = new EntityWrapper<Informations>();
        entityWrapper.eq("deleted",0);
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("code")),"code",(String)params.get("code"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("name")),"name",(String)params.get("name"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("type")),"type",(String)params.get("type"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("remark")),"remark",(String)params.get("remark"));
        entityWrapper.orderBy("code",true);

        Page<Informations> pageInfo = informationsService.queryPage(pg,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }

    /**
     * 新增配置资料
     * @param document
     * @return
     */
    @RequiresPermissions("basic:info:save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody Informations document) {
        if(StringUtils.isBlank(document.getCode())||StringUtils.isBlank(document.getName())|| StringUtils.isBlank(document.getType()))
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"编码、名称和分组不能为空");

//        List<Informations> list = informationsService.selectList(new EntityWrapper<Informations>()
//                .or("(code = '"+document.getCode()+"'").or("name = '"+document.getName()+"')")
//                .eq("deleted",0).eq("type",document.getType()));
//        if(list.size()>0)
//            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"该分组下已存在编码或名称已存在");

        document.setDescription(InforEnum.getValueByCode(document.getType()));
        informationsService.insertInfo(document);
        return new Response("新增配置资料成功");
    }

    /**
     * 更新配置资料
     * @param document
     * @return
     */
    @RequiresPermissions("basic:info:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody Informations document) {
        informationsService.updateInfo(document);
        return new Response("更新配置资料成功");
    }


    /**
     * 配置资料删除
     * @param ids
     * @return
     */
    @RequiresPermissions("basic:info:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody String[] ids) {
        informationsService.deleteBatchIds(Arrays.asList(ids));
        return new Response("配置资料删除成功");
    }

    /**
     * 根据类型查询配置资料
     * @param params
     * @return
     */
    @PostMapping("/queryInfo")
    public Response queryInfor(@RequestBody Map params) {
        //查询条件
        EntityWrapper<Informations> entityWrapper = new EntityWrapper<Informations>();
        entityWrapper.eq("deleted",0);
        entityWrapper.eq("type",params.get("type"));

        List<Informations> items = informationsService.queryList(entityWrapper);
        List list = new ArrayList();
        for(Informations en : items){
            Map<String,String> map = new HashMap<>();
            map.put("value",en.getName());
            map.put("label",en.getName());
            list.add(map);
        }
        return new ObjectResponse<List>(list);
    }

    @PostMapping("/infoEnum")
    public Response inforEnum() {
        List list = new ArrayList();
        for(InforEnum en : InforEnum.values()){
            Map<String,String> map = new HashMap<>();
            map.put("value",en.code);
            map.put("label",en.value);
            list.add(map);
        }
        return new ObjectResponse<List>(list);
    }

    /**
     * 导出配置资料
     * @author tyj 
     * @date 2018-10-15 11:39
     */
    @PostMapping("/exportInfo")
    public void exportInfo(HttpServletResponse response, @RequestBody Map<String, Object> params){

        //查询条件
        EntityWrapper<Informations> entityWrapper = new EntityWrapper<Informations>();
        entityWrapper.eq("deleted",0);
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("code")),"code",(String)params.get("code"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("name")),"name",(String)params.get("name"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("type")),"type",(String)params.get("type"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("remark")),"remark",(String)params.get("remark"));
        entityWrapper.orderBy("type",true);

        List<Informations> list = informationsService.queryList(entityWrapper);

        String filename = "导出配置资料.Excel";
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(filename, "UTF-8") + ".xls" + "\"");
            OutputStream out = response.getOutputStream();

            ExcelUtil.getInstance().exportExcel(out,list, Informations.class);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入配置资料
     * @author tyj 
     * @date 2018-10-15 16:15
     */
    @PostMapping("/importInfo")
    public Response importInfo(@RequestParam(value="file" ,required=true) MultipartFile multipartFile){
        try {
            InputStream inputStream= multipartFile.getInputStream();

            ExcelLogs logs =new ExcelLogs();
            List<Informations> importExcel =(List<Informations>) ExcelUtil.getInstance().importExcel(Informations.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
            informationsService.insertBatch(importExcel);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Response("配置资料删除成功");
    }

}
