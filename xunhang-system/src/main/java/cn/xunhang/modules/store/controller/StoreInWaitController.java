package cn.xunhang.modules.store.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.modules.store.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.OtherSourceDlt;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreInHdr;
import cn.xunhang.modules.store.service.OtherSourceDltService;
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.StoreInHdrService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
@RestController
@RequestMapping("/storeIn")
public class StoreInWaitController extends InfoBaseController<StoreInHdr,StoreInHdrDao,StoreInDlt,StoreInDltDao> {

	@Autowired
	private StoreInHdrService storeInHdrService;

	@Autowired
	private StoreInDltService storeInDltService;

    @Autowired
    private OtherSourceDltService otherSourceDltService;



	/*
	 *  主表
	 *
	 *
	 */

	@RequestMapping(value = "/hdr/queryList", method = RequestMethod.POST)
	public Response queryList(@RequestBody Map<String, Object> params) {

		Map<String, Object> map = (Map<String, Object>) params.get("condition");
		List<Map>mapList = storeInHdrService.queryStoreInWaitListByCondition((String)map.get("storeName")
				,(String)map.get("name")
				,(String)map.get("code")
				,(String)map.get("storeInName")
				,(String) map.get("status")
				,(String) map.get("kind")
				,(int) params.get("current")
				,(int) params.get("offset"));
		Page<Map> pageInfo = new Page<>();
		pageInfo.setRecords(mapList);
		return new ObjectResponse<Page>(pageInfo);
	}


	@Override
	@RequestMapping(value = "/hdr/queryDetail", method = RequestMethod.POST)
	public Response queryDetail(@RequestBody Map<String, Object> params) {

		String storeInId = (String)params.get("formNo");
		Page<StoreInDlt> page = new Page<StoreInDlt>(1,Integer.MAX_VALUE);
		EntityWrapper<StoreInDlt> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("formNo",storeInId);
		entityWrapper.eq("deleted",0).orderBy("createDate",false);
		Page<StoreInDlt> pageInfo = storeInDltService.queryList(page,entityWrapper);
		return new ObjectResponse<Page>(pageInfo);
	}

	//待进仓提交
	@RequestMapping("/hdr/submit")
	public Response submit(@RequestBody Map<String,Object> params) {

		List<String> formNo  = (List<String>) params.get("formNo");
		storeInHdrService.submitByIds(formNo);

		return new Response("提交成功");
	}

	/*
	 *  从表
	 *  generator:
	 *
	 */
	@RequestMapping(value = "/dlt/queryList", method = RequestMethod.POST)
	public Response queryList2(@RequestBody Map<String, Object> params) {

		Page<OtherSourceDlt> page = new Page<OtherSourceDlt>((int) params.get("current"), (int) params.get("offset"));

		Map<String, Object> map = (Map<String, Object>) params.get("condition");
		EntityWrapper<OtherSourceDlt> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq(StringUtils.isNotBlank((String) map.get("kind")), "kind", map.get("kind"))
				.eq(StringUtils.isNotBlank((String) map.get("name")), "name", map.get("name"))
				.eq(StringUtils.isNotBlank((String) map.get("code")), "code", map.get("code"))
		;

		entityWrapper.eq("deleted",0).orderBy("createDate",false);
		Page<OtherSourceDlt> pageInfo = otherSourceDltService.queryList(page,entityWrapper);
		return new ObjectResponse<Page>(pageInfo);
	}

    @Override
    @RequestMapping(value = "/dlt/save", method = RequestMethod.POST)
    public Response save2(@RequestBody Map<String, Object> params) {

        String date = (String)params.get("date");
        String kind = (String)params.get("kind");
        List<Map>mapList = (List<Map>)params.get("data");
        storeInDltService.save(date,mapList,kind);
        return new Response("提交成功");
    }

	@Override
	@RequestMapping(value = "/dlt/edit", method = RequestMethod.POST)
	public Response edit2(@RequestBody Map<String, Object> params) {

		String formNo = (String)params.get("formNo");
		String date = (String)params.get("date");
		List<Map>mapList = (List<Map>)params.get("dlt");

		storeInDltService.edit(formNo,date,mapList);
		return new Response("修改成功");
	}








}
