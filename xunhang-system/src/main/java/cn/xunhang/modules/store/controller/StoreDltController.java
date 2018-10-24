package cn.xunhang.modules.store.controller;

import cn.xunhang.common.base.BaseController;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.store.entity.StoreDlt;
import cn.xunhang.modules.store.service.StoreDltService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/storeDlt")
public class StoreDltController extends BaseController {

    @Autowired
    StoreDltService storeDltBaseService;

    @RequestMapping("/save")
    public Response save(@RequestBody Map<String, Object> params) {

        StoreDlt t1 = JSON.parseObject(JSON.toJSONString(params), StoreDlt.class);
        storeDltBaseService.insertInfo(t1);
        return new Response("提交成功");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Map<String, Object> params) {
        storeDltBaseService.deleteInfo(params);
        return new Response("删除成功");
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {
        Page<StoreDlt> page = new Page<StoreDlt>((int) params.get("current"), (int) params.get("offset"));

        Map<String, Object> map = (Map<String, Object>) params.get("condition");
        EntityWrapper<StoreDlt> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(StringUtils.isNotBlank((String) map.get("storeName")), "storeName", map.get("storeName"))
                    .and(StringUtils.isNotBlank((String) map.get("col")), "col={0}", map.get("col"))
                .and(StringUtils.isNotBlank((String) map.get("locationNo")), "locationNo={0}", map.get("locationNo"))
                .and(StringUtils.isNotBlank((String) map.get("row")), "row={0}", map.get("row"))
                .and(StringUtils.isNotBlank((String) map.get("layer")), "layer={0}", map.get("layer"))
        ;
        entityWrapper.eq("deleted", 0).orderBy("createDate", false);
        Page<StoreDlt> pageInfo = storeDltBaseService.queryList(page, entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public Response queryDetail(@RequestBody Map<String, Object> params) {
        StoreDlt t1 = storeDltBaseService.queryDetail(params);
        return new ObjectResponse<StoreDlt>(t1);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody Map<String, Object> params) {
        StoreDlt t1 = JSON.parseObject(JSON.toJSONString(params), StoreDlt.class);
        storeDltBaseService.updateInfo(t1);
        return new Response("修改成功");
    }


}
