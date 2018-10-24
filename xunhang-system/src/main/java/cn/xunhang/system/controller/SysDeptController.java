package cn.xunhang.system.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.base.Tree;
import cn.xunhang.common.utils.ShiroUtils;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.SysDept;
import cn.xunhang.system.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 部门管理
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017-06-20 15:23:47
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends InfoBaseController {
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 查询部门列表
	 */
	@RequiresPermissions("sys:dept:list")
	@RequestMapping("/list")
	public Response list(@RequestBody Map<String, Object> params){
		List<Tree<SysDept>> menuList = sysDeptService.queryList(params);
		return new ObjectResponse<List>(menuList);
	}

	/**
	 * 新增保存部门
	 */
	@RequiresPermissions("sys:dept:save")
	@RequestMapping("/save")
	public Response save(@RequestBody SysDept document){
		if(document.getParentId().equals("-1")){
			document.setParentId("0");
		}
		sysDeptService.insertInfo(document);
		return new Response("新增保存部门成功");
	}
	
	/**
	 * 更新修改部门
	 */
	@RequiresPermissions("sys:dept:update")
	@RequestMapping("/update")
	public Response update(@RequestBody SysDept document){
		if(document.getParentId().equals("-1")){
			document.setParentId("0");
		}
		sysDeptService.updateInfo(document);
		return new Response("更新修改部门成功");
	}
	
	/**
	 * 删除部门
	 */
	@RequiresPermissions("sys:dept:delete")
	@RequestMapping("/delete")
	public Response delete(@RequestBody String[] ids){
		for (String id : ids){
			//判断是否有子部门
			List<String> deptList = sysDeptService.queryDetpIdList(id);
			if(deptList.size() > 0){
				return new Response(1,"请先删除子部门");
			}
		}
		sysDeptService.deleteBatchIds(Arrays.asList(ids));
		return new Response("删除部门成功");
	}

	@PostMapping("/tree")
	public Response tree() {
		Tree<SysDept> tree = sysDeptService.getDeptTree();
		return new ObjectResponse<Tree<SysDept>>(tree);
	}

	/**
	 * 获取部门信息
	 */
	@PostMapping("/info")
	public Response info(@RequestBody Map params){
		SysDept document = sysDeptService.selectById((String)params.get("id"));
		if(!document.getParentId().equals("0")){
			SysDept parentDept = sysDeptService.selectById(document.getParentId());
			document.setParentName(parentDept.getName());
		}
		return new ObjectResponse<SysDept>(document);
	}
	
}
