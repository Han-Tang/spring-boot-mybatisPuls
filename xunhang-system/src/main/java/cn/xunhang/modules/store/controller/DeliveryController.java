package cn.xunhang.modules.store.controller;


import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.DeliveryDltDao;
import cn.xunhang.modules.store.dao.DeliveryHdrDao;
import cn.xunhang.modules.store.dao.StoreOutDltDao;
import cn.xunhang.modules.store.dao.StoreOutHdrDao;
import cn.xunhang.modules.store.entity.DeliveryDlt;
import cn.xunhang.modules.store.entity.DeliveryHdr;
import cn.xunhang.modules.store.entity.StoreOutDlt;
import cn.xunhang.modules.store.entity.StoreOutHdr;
import cn.xunhang.modules.store.service.DeliveryDltService;
import cn.xunhang.modules.store.service.DeliveryHdrService;
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
 * @author zzc
 * @since 2018-09-20
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController extends InfoBaseController<DeliveryHdr, DeliveryHdrDao, DeliveryDlt, DeliveryDltDao> {


    @Autowired
    private DeliveryHdrService deliveryHdrService;

    @Autowired
    private DeliveryDltService deliveryDltService;

    //主表

    //获取发货单主表
    @RequestMapping(value = "/hdr/queryList", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {

        Map<String, Object> map = (Map<String, Object>) params.get("condition");
        Page<DeliveryHdr> page = new Page<DeliveryHdr>((int) map.get("current"), (int) map.get("offset"));
        EntityWrapper<DeliveryHdr> entityWrapper = new EntityWrapper<DeliveryHdr>();

        entityWrapper.eq(StringUtils.isNotBlank((String) map.get("fFormNo")), "formNo", map.get("fFormNo"))
                .eq(StringUtils.isNotBlank((String) map.get("sFormNo")), "sFormNo", map.get("sFormNo"))
                .eq("status",StoreReviewStatusEnum.ConfirmType.getValue())
        ;
        entityWrapper.eq("deleted", 0).orderBy("createDate", false);
        Page<DeliveryHdr> pageInfo = deliveryHdrService.queryList(page, entityWrapper);

        return new ObjectResponse<Page>(pageInfo);
    }


    //从表

    //获取发货单明细
    @RequestMapping(value = "/dlt/queryList", method = RequestMethod.POST)
    public Response queryList2(@RequestBody Map<String, Object> params) {

        String formNo = (String) params.get("formNo");
        Page<DeliveryDlt> page = new Page<DeliveryDlt>(1, Integer.MAX_VALUE);
        EntityWrapper<DeliveryDlt> entityWrapper = new EntityWrapper<DeliveryDlt>();
        entityWrapper.eq("formNo",formNo);
        entityWrapper.eq("deleted", 0).orderBy("createDate", false);
        Page<DeliveryDlt> pageInfo = deliveryDltService.queryList(page, entityWrapper);

        return new ObjectResponse<Page>(pageInfo);
    }

}

