package cn.xunhang.modules.store.controller;


import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.PickMaterialDltDao;
import cn.xunhang.modules.store.dao.PickMaterialHdrDao;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.PickMaterialDlt;
import cn.xunhang.modules.store.entity.PickMaterialHdr;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreInHdr;
import cn.xunhang.modules.store.service.PickMaterialDltService;
import cn.xunhang.modules.store.service.PickMaterialHdrService;
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.StoreInHdrService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
@RequestMapping("/pickMaterialConfirm")
public class PickMaterialConfirmController extends InfoBaseController<PickMaterialHdr,PickMaterialHdrDao
																	,PickMaterialDlt,PickMaterialDltDao> {

	@Autowired
	private PickMaterialHdrService pickMaterialHdrService;

	@Autowired
	private PickMaterialDltService pickMaterialDltService;

	/*
	 *  fun:确认获取列表
	 *  generator:zzc
	 *
	 */
	@RequestMapping(value = "/hdr/queryList", method = RequestMethod.POST)
	public Response queryList(@RequestBody Map<String, Object> params) {

		Map<String, Object> map = (Map<String, Object>) params.get("condition");
		String formNo = (String)map.get("formNo");
		String code = (String)map.get("code");
		String name = (String)map.get("name");
		String kind = (String)map.get("kind");
		List<PickMaterialHdr>mapList = pickMaterialHdrService.queryConfirmListByCondition(formNo
																		,code
																		,name
				                                                        ,kind
																		,(int) map.get("current")
																		,(int) map.get("offset"));
	    Page<PickMaterialHdr> pageInfo = new Page<>();
	    pageInfo.setRecords(mapList);
	    return new ObjectResponse<Page>(pageInfo);
	}


	/*
	 *  fun:进仓确认
	 *  generator:zzc
	 *
	 */
	@Override
	@RequestMapping(value = "/hdr/submit", method = RequestMethod.POST)
	public Response save(@RequestBody Map<String, Object> params) {

		List<String> formNo = (List<String>)params.get("formNo");
		pickMaterialHdrService.confirmSave(formNo);
		return new Response("确认成功");
	}



}
