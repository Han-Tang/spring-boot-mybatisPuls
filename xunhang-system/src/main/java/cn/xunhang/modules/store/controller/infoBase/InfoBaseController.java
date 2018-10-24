package cn.xunhang.modules.store.controller.infoBase;

import cn.xunhang.common.base.*;
import cn.xunhang.modules.store.baseMapper.SuperMapper;
import cn.xunhang.modules.store.service.infoBase.InfoBaseService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
public class InfoBaseController<T1 extends SuperEntity<T1>,T2 extends SuperMapper<T1>,T3 extends SuperEntity<T3>,T4 extends SuperMapper<T3>> extends BaseController {


    @Autowired
    private InfoBaseService<T1,T2> hdrService;

    @Autowired
    private InfoBaseService<T3,T4> dltService;

    /*
     *  fun:主表
     *  generator:
     *
     */

    @RequestMapping("/hdr/save")
    public Response save(@RequestBody Map<String,Object> params) {

        T1 entity  = JSON.parseObject(JSON.toJSONString(params), getT1Class());
        hdrService.insertInfo(entity);
        return new Response(ConstantResponseTips.getSubmitSuccess());
    }

    @RequestMapping(value = "/hdr/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Map<String, Object> params) {
        hdrService.deleteInfo(params);
        return new Response(ConstantResponseTips.getDeleteSuccess());
    }


    @RequestMapping(value = "/hdr/queryDetail", method = RequestMethod.POST)
    public Response queryDetail(@RequestBody Map<String, Object> params) {
        T1 entity = hdrService.queryDetail(params);
        return new ObjectResponse<T1>(entity);
    }

    @RequestMapping(value = "/hdr/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody Map<String, Object> params) {
        T1 entity  = JSON.parseObject(JSON.toJSONString(params), getT1Class());
        hdrService.updateInfo(entity);
        return new Response(ConstantResponseTips.getModifySuccess());
    }

    /*
     *  从表
     *  generator:
     *
     */

    @RequestMapping("/dlt/save")
    public Response save2(@RequestBody Map<String, Object> params) {

        T3 entity = JSON.parseObject(JSON.toJSONString(params),getT3Class());
        dltService.insertInfo(entity);
        return new Response(ConstantResponseTips.getSubmitSuccess());
    }

    @RequestMapping(value = "/dlt/delete", method = RequestMethod.POST)
    public Response delete2(@RequestBody Map<String, Object> params) {
        dltService.deleteInfo(params);
        return new Response(ConstantResponseTips.getDeleteSuccess());
    }



    @RequestMapping(value = "/dlt/queryDetail", method = RequestMethod.POST)
    public Response queryDetail2(@RequestBody Map<String, Object> params) {
        T3 entity = dltService.queryDetail(params);
        return new ObjectResponse<T3>(entity);
    }

    @RequestMapping(value = "/dlt/edit", method = RequestMethod.POST)
    public Response edit2(@RequestBody Map<String, Object> params) {
        T3 entity = JSON.parseObject(JSON.toJSONString(params), getT3Class());
        dltService.updateInfo(entity);
        return new Response(ConstantResponseTips.getModifySuccess());
    }

    public Class<T1> getT1Class() {
        Class<T1> tClass = (Class<T1>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    public Class<T3> getT3Class() {
        Class<T3> tClass = (Class<T3>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        return tClass;
    }




}
