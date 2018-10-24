package cn.xunhang.modules.store.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.OrderHeaderEnum;
import cn.xunhang.common.utils.SequenceCodeUtils;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.PickMaterialDltDao;
import cn.xunhang.modules.store.dao.PickMaterialHdrDao;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.*;
import cn.xunhang.modules.store.service.PickMaterialDltService;
import cn.xunhang.modules.store.service.PickMaterialHdrService;
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.StoreInHdrService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pickMaterial")
public class PickMaterialController  extends InfoBaseController<PickMaterialHdr,PickMaterialHdrDao,PickMaterialDlt,PickMaterialDltDao> {

    @Autowired
    private PickMaterialHdrService pickMaterialHdrService;

    @Autowired
    private PickMaterialDltService pickMaterialDltService;

    /*
     *  主表
     *
     *
     */

    @RequestMapping(value = "/hdr/queryList", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {

        Map<String, Object> map = (Map<String, Object>) params.get("condition");
        List<PickMaterialHdr>mapList = pickMaterialHdrService.queryStoreInWaitListByCondition((String)map.get("formNo")
                ,(String)map.get("name")
                ,(String)map.get("code")
                ,(String)map.get("kind")
                ,(int) map.get("current")
                ,(int) map.get("offset"));
        Page<PickMaterialHdr> pageInfo = new Page<>();
        pageInfo.setRecords(mapList);
        return new ObjectResponse<Page>(pageInfo);
    }

    @Override
    @RequestMapping(value = "/hdr/queryDetail", method = RequestMethod.POST)
    public Response queryDetail(@RequestBody Map<String, Object> params) {

        String formNo = (String)params.get("formNo");
        Page<PickMaterialDlt> page = new Page<PickMaterialDlt>(1,Integer.MAX_VALUE);
        EntityWrapper<PickMaterialDlt> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("formNo",formNo);
        entityWrapper.eq("deleted",0).orderBy("createDate",false);
        Page<PickMaterialDlt> pageInfo = pickMaterialDltService.queryList(page,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }

    //提交
    @RequestMapping("/hdr/submit")
    public Response submit(@RequestBody Map<String,Object> params) {

         List<String> formNo  = (List<String>) params.get("formNo");
        pickMaterialHdrService.submitByIds(formNo);

        return new Response("提交成功");
    }

    /*
     *  从表
     *  generator:
     *
     */
    @Override
    @RequestMapping(value = "/dlt/save", method = RequestMethod.POST)
    public Response save2(@RequestBody Map<String, Object> params) {

        String kind = (String)params.get("kind");
        String description = (String)params.get("description");
        List<Map> mapList = (List<Map>)params.get("dlt");
        pickMaterialDltService.save(description,mapList,kind);
        return new Response("提交成功");
    }

    @Override
    @RequestMapping(value = "/dlt/edit", method = RequestMethod.POST)
    public Response edit2(@RequestBody Map<String, Object> params) {

        String formNo = (String)params.get("formNo");
        String date = (String)params.get("description");
        List<Map>mapList = (List<Map>)params.get("dlt");

        pickMaterialDltService.edit(formNo,date,mapList);
        return new Response("修改成功");
    }

}
