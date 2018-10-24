package cn.xunhang.modules.store.controller;


import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreInHdr;
import cn.xunhang.modules.store.entity.StoreOutHdr;
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.StoreInHdrService;
import cn.xunhang.modules.store.service.StoreOutDltService;
import cn.xunhang.modules.store.service.StoreOutHdrService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
@RestController
@RequestMapping("/storeOutConfirm")
public class StoreOutConfirmController extends InfoBaseController<StoreInHdr, StoreInHdrDao, StoreInDlt, StoreInDltDao> {

    @Autowired
    private StoreOutHdrService storeOutHdrService;

    @Autowired
    private StoreOutDltService storeOutDltService;

    /*
     *  fun:出仓确认获取列表
     *  generator:zzc
     *
     */
//    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
//    public Response queryList(@RequestBody Map<String, Object> params) {
//
//        Page<StoreOutHdr> page = new Page<StoreOutHdr>((int) params.get("current"), (int) params.get("offset"));
//
//        Map<String, Object> map = (Map<String, Object>) params.get("condition");
//        EntityWrapper<StoreOutHdr> entityWrapper = new EntityWrapper<>();
//        entityWrapper.eq("1",1);
//        entityWrapper.eq(StringUtils.isNotBlank((String) map.get("storeOutNo")), "storeOutNo", map.get("storeOutNo"))
//                .and(StringUtils.isNotBlank((String) map.get("deliveryNo")), "deliveryNo={0}", map.get("deliveryNo"))
//                .and(StringUtils.isNotBlank((String) map.get("saleNo")), "saleNo={0}", map.get("saleNo"))
//        ;
//
//        entityWrapper.andNew();
//        entityWrapper.eq(StringUtils.isNotBlank((String) map.get("status")),"status", map.get("status"));
//        entityWrapper.eq(StringUtils.isBlank((String) map.get("status")),"status","1");
//        entityWrapper.or(StringUtils.isBlank((String) map.get("status")),"status={0}","2");
//
//        entityWrapper.eq("deleted", 0).orderBy("createDate", false);
//        Page<StoreOutHdr> pageInfo = storeOutHdrService.queryList(page, entityWrapper);
//
//        return new ObjectResponse<Page>(pageInfo);
//    }
//
//    /*
//     *  fun:出仓确认
//     *  generator:zzc
//     *
//     */
//    @Override
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public Response save(@RequestBody Map<String, Object> params) {
//
//        List<String> ids = (List<String>) params.get("id");
//        for (String id : ids){
//            StoreOutHdr storeOutHdr = storeOutHdrService.selectById(id);
//
//            if(!StoreReviewStatusEnum.WaitConfirmType.getValue().equals(storeOutHdr.getStatus())){
//                 throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"审核状态不对");
//            }
//            storeOutHdr.setStatus(StoreReviewStatusEnum.ConfirmType.getValue());
//            storeOutHdrService.updateInfo(storeOutHdr);
//        }
//        return new Response("确认成功");
//
//    }


}
