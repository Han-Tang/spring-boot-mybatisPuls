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
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.StoreInHdrService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
@RestController
@RequestMapping("/storeInConfirm")
public class StoreInConfirmController extends InfoBaseController<StoreInHdr,StoreInHdrDao,StoreInDlt,StoreInDltDao> {

	@Autowired
	private StoreInHdrService storeInHdrService;

	@Autowired
	private StoreInDltService storeInDltService;

	/*
	 *  fun:进仓确人获取列表
	 *  generator:zzc
	 *
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public Response queryList(@RequestBody Map<String, Object> params) {

		Map<String, Object> map = (Map<String, Object>) params.get("condition");
		List<Map>mapList = storeInHdrService.queryStoreInConfirmListByCondition((String)map.get("storeName")
																		,(String)map.get("storeInNo")
																		,(String)map.get("storeInName")
																		,(String)map.get("status")
				                                                        ,(String)map.get("kind")
				,(int) params.get("current")
																		,(int) params.get("offset"));
	    Page<Map> pageInfo = new Page<>();
	    pageInfo.setRecords(mapList);
	    return new ObjectResponse<Page>(pageInfo);
	}

	/*
	 *  fun:进仓确认
	 *  generator:zzc
	 *
	 */
//	@Override
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public Response save(@RequestBody Map<String, Object> params) {
//
//		List<String> storeInId = (List<String>)params.get("storeInId");
//		List<Map> dlt = (List<Map>)params.get("dlt");
//		storeInHdrService.confirmSave(storeInId,dlt);
//		return new Response("确认成功");
//
//	}



}
