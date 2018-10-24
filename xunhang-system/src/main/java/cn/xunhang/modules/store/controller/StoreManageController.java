package cn.xunhang.modules.store.controller;


import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.RepertoryDlt;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreInHdr;
import cn.xunhang.modules.store.service.RepertoryDltService;
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.StoreInHdrService;
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
 *  前端控制器
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
@RestController
@RequestMapping("/storeManage")
public class StoreManageController extends InfoBaseController<StoreInHdr,StoreInHdrDao,StoreInDlt,StoreInDltDao> {

	@Autowired
	private RepertoryDltService repertoryDltService;



	/*
	 *  从表
	 *  generator:
	 *
	 */

	@RequestMapping(value = "/dlt/queryList", method = RequestMethod.POST)
	public Response queryList2(@RequestBody Map<String, Object> params) {
		Map<String, Object> map = (Map<String, Object>) params.get("condition");

		Page<RepertoryDlt> page = new Page<RepertoryDlt>((int) map.get("current"), (int) map.get("offset"));
		EntityWrapper<RepertoryDlt> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq(StringUtils.isNotBlank((String) map.get("code")), "code", map.get("code"))
				.eq(StringUtils.isNotBlank((String) map.get("name")), "name", map.get("name"))
				.eq(StringUtils.isNotBlank((String) map.get("serial")), "serial", map.get("serial"))

				.eq(StringUtils.isNotBlank((String) map.get("model")), "model", map.get("model"))
				.eq(StringUtils.isNotBlank((String) map.get("storeName")), "storeName", map.get("storeName"))
				.eq(StringUtils.isNotBlank((String) map.get("type")), "type", map.get("type"))
				.eq("kind", map.get("kind"))
		;

		entityWrapper.eq("deleted",0).orderBy("createDate",false);
		Page<RepertoryDlt> pageInfo = repertoryDltService.queryList(page,entityWrapper);
		return new ObjectResponse<Page>(pageInfo);
	}


}
