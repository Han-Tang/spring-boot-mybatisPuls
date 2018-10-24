package cn.xunhang.modules.store.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.StoreOutDltDao;
import cn.xunhang.modules.store.dao.StoreOutHdrDao;

import cn.xunhang.modules.store.entity.DeliveryHdr;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreOutDlt;
import cn.xunhang.modules.store.entity.StoreOutHdr;
import cn.xunhang.modules.store.service.DeliveryHdrService;
import cn.xunhang.modules.store.service.StoreOutDltService;
import cn.xunhang.modules.store.service.StoreOutHdrService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author zzc
 * @since 2018-09-20
 */
@RestController
@RequestMapping("/storeOut")
public class StoreOutController extends InfoBaseController<StoreOutHdr, StoreOutHdrDao, StoreOutDlt, StoreOutDltDao> {


    @Autowired
    private StoreOutHdrService hdrService;

    @Autowired
    private StoreOutDltService dltService;

    @Autowired
    private DeliveryHdrService deliveryHdrService;

    /*
     *  主表
     *
     *
     */

    @Override
    @RequestMapping(value = "/hdr/save", method = RequestMethod.POST)
    public Response save(@RequestBody Map<String, Object> params) {

        String date = (String) params.get("date");
        String description = (String) params.get("description");
        List<Map> mapList = (List<Map>) params.get("data");
        String fFormNo = (String) params.get("fFormNo");
        String sFormNo = (String) params.get("sFormNo");

        dltService.save(date, description, mapList,fFormNo,sFormNo);
        return new Response("提交成功");
    }

    @RequestMapping(value = "/hdr/queryList", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {

        Map<String, Object> map = (Map<String, Object>) params.get("condition");
        Page<StoreOutHdr> page = new Page<StoreOutHdr>((int) map.get("current"), (int) map.get("offset"));

        EntityWrapper<StoreOutHdr> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("1",1);

        entityWrapper.eq(StringUtils.isNotBlank((String) map.get("cformNo")), "formNo", map.get("cformNo"))
                .eq(StringUtils.isNotBlank((String) map.get("fFormNo")), "fFormNo", map.get("fFormNo"))
                .eq(StringUtils.isNotBlank((String) map.get("sFormNo")), "sFormNo", map.get("sFormNo"))
        ;
        entityWrapper.andNew();
        entityWrapper.eq(StringUtils.isNotBlank((String) map.get("status")),"status", map.get("status"));
        entityWrapper.eq(StringUtils.isBlank((String) map.get("status")),"status","1");
        entityWrapper.or(StringUtils.isBlank((String) map.get("status")),"status={0}","3");

        entityWrapper.andNew();
        entityWrapper.eq("deleted", 0).orderBy("createDate", false);

        Page<StoreOutHdr> pageInfo = hdrService.queryList(page, entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }


    @Override
    @RequestMapping(value = "/hdr/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody Map<String, Object> params) {

        String formNo = (String)params.get("formNo");
        String date = (String)params.get("date");
        String description = (String)params.get("description");
        List<Map>mapList = (List<Map>)params.get("dlt");

        dltService.edit(formNo,date,description,mapList);
        return new Response("修改成功");
    }

    @RequestMapping("/hdr/submit")
    public Response submit(@RequestBody Map<String,Object> params) {

        List<String> formNo  = (List<String>) params.get("formNo");
        hdrService.submitByIds(formNo);
        return new Response("提交成功");
    }


    //从表
    @RequestMapping(value = "/dlt/queryList", method = RequestMethod.POST)
    public Response queryList2(@RequestBody Map<String, Object> params) {

        String formNo = (String) params.get("formNo");

        EntityWrapper<StoreOutDlt> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("formNo",formNo);
        entityWrapper.eq("deleted", 0).orderBy("createDate", false);

        Page<StoreOutDlt> pa = new Page<>(1,Integer.MAX_VALUE);
        Page<StoreOutDlt> page = dltService.queryList(pa,entityWrapper);
        return new ObjectResponse<Page>(page);
    }



}

