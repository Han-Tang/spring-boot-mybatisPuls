package cn.xunhang.modules.store.controller;

import cn.xunhang.common.base.BaseController;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.store.entity.StoreHdr;
import cn.xunhang.modules.store.service.StoreHdrService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
@RestController
@RequestMapping("/storeHdr")
public class StoreHdrController extends BaseController {

    @Autowired
    private StoreHdrService hdrBaseService;

    @RequestMapping("/save")
    public Response save(@RequestBody Map<String,Object> params) {

        StoreHdr t1  = JSON.parseObject(JSON.toJSONString(params), StoreHdr.class);
        hdrBaseService.insertInfo(t1);
        return new Response("提交成功");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Map<String, Object> params) {
        hdrBaseService.deleteInfo(params);
        return new Response("删除成功");
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {
        Page<StoreHdr> page = new Page<StoreHdr>(1, Integer.MAX_VALUE);
        EntityWrapper<StoreHdr> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("deleted",0).orderBy("createDate",false);
        Page<StoreHdr> pageInfo = hdrBaseService.queryList(page,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public Response queryDetail(@RequestBody Map<String, Object> params) {
        StoreHdr t1 = hdrBaseService.queryDetail(params);
        return new ObjectResponse<StoreHdr>(t1);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody Map<String, Object> params) {
        StoreHdr t1  = JSON.parseObject(JSON.toJSONString(params), StoreHdr.class);
        hdrBaseService.updateInfo(t1);
        return new Response("修改成功");
    }


}
