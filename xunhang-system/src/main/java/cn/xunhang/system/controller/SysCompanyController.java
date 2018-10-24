package cn.xunhang.system.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.utils.ShiroUtils;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.BankAccount;
import cn.xunhang.system.entity.SysCompany;
import cn.xunhang.system.service.BankAccountService;
import cn.xunhang.system.service.SysCompanyService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 公司管理
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017-06-20 15:23:47
 */
@RestController
@RequestMapping("/company")
public class SysCompanyController extends InfoBaseController {
	@Autowired
	private SysCompanyService sysCompanyService;
	@Autowired
	private BankAccountService bankAccountService;
	
	/**
	 * 查询公司列表
	 */
	@RequiresPermissions("sys:company:list")
	@RequestMapping("/list")
	public Response list(@RequestBody Map<String, Object> params){
		//查询条件
		Page<SysCompany> pg = new Page<SysCompany>((int) params.get("current"),(int) params.get("offset"));
		EntityWrapper<SysCompany> entityWrapper = new EntityWrapper<SysCompany>();
		entityWrapper.eq("deleted",0).orderBy("createDate",false);

		Page<SysCompany> pageInfo = sysCompanyService.queryPage(pg,entityWrapper);
		return new ObjectResponse<Page>(pageInfo);
	}

	/**
	 * 新增保存公司
	 */
	@RequiresPermissions("sys:company:save")
	@PostMapping("/save")
	public Response save(@RequestBody SysCompany document) {
		sysCompanyService.insertInfo(document);
		return new Response("新增保存公司成功");
	}

	/**
	 * 更新修改公司
	 */
	@RequiresPermissions("sys:company:update")
	@RequestMapping("/update")
	public Response update(@RequestBody SysCompany document) {
		sysCompanyService.updateInfo(document);
		return new Response("更新修改公司成功");
	}

	/**
	 * 删除公司
	 */
	@RequiresPermissions("sys:company:remove")
	@PostMapping("/remove")
	public Response remove(@RequestBody String[] ids) {
		EntityWrapper wrapper = new EntityWrapper<SysCompany>();
		wrapper.in("id",Arrays.asList(ids));
		List<SysCompany> list = sysCompanyService.queryList(wrapper);

		List<String> codes = new ArrayList<>();
		for (SysCompany company : list) codes.add(company.getCode());
		EntityWrapper wrapper1 = new EntityWrapper<BankAccount>();
		wrapper1.in("code",codes);
		bankAccountService.delete(wrapper1);

		sysCompanyService.deleteBatchIds(Arrays.asList(ids));
		return new Response("删除公司成功");
	}

	/**
	 * 获取公司信息
	 */
	@PostMapping("/info")
	public Response info(@RequestBody Map params){
		SysCompany document = sysCompanyService.selectById((String)params.get("id"));
		return new ObjectResponse<SysCompany>(document);
	}
	
}
