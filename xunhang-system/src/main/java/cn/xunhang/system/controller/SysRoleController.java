package cn.xunhang.system.controller;

import cn.xunhang.common.annotation.Log;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.SysRole;
import cn.xunhang.system.service.SysRoleMenuService;
import cn.xunhang.system.service.SysRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/role")
public class SysRoleController extends InfoBaseController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 角色列表分页
	 */
	@RequiresPermissions("sys:role:list")
	@RequestMapping("/list")
	public Response list(@RequestBody Map<String, Object> params){
		//分页条件
		Page<SysRole> pg = new Page<SysRole>((int) params.get("current"),(int) params.get("offset"));
		EntityWrapper<SysRole> entityWrapper = new EntityWrapper<SysRole>();
		entityWrapper.eq("deleted",0).orderBy("createDate",false);

		Page<SysRole> pageInfo = sysRoleService.queryPage(pg,entityWrapper);
		return new ObjectResponse<Page>(pageInfo);
	}
	
	/**
	 * 角色信息
	 */
	@PostMapping("/info")
	public Response info(@RequestBody Map params){
		String roleId = (String)params.get("id");
		SysRole role = sysRoleService.selectById(roleId);

		//查询角色对应的菜单
		List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		//去除父节点
		menuIdList = sysRoleMenuService.removeParentId(menuIdList);
		role.setMenuIdList(menuIdList);

		//查询角色对应的部门
//		List<String> deptIdList = sysRoleDeptService.queryDeptIdList(roleId);
//		role.setDeptIdList(deptIdList);

		return new ObjectResponse<SysRole>(role);
	}
	
	/**
	 * 新增保存角色
	 */
	@RequiresPermissions("sys:role:save")
	@Log("保存角色")
	@RequestMapping("/save")
	public Response save(@RequestBody SysRole role){
//		ValidatorUtils.validateEntity(role);
		sysRoleService.insertInfo(role);
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());
		return new Response("新增保存角色成功");
	}
	
	/**
	 * 编辑修改角色
	 */
	@RequiresPermissions("sys:role:update")
	@Log("修改角色")
	@RequestMapping("/update")
	public Response update(@RequestBody SysRole role){
//		ValidatorUtils.validateEntity(role);
		sysRoleService.updateInfo(role);
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());
		return new Response("编辑修改角色成功");
	}
	
	/**
	 * 删除角色
	 */
	@RequiresPermissions("sys:role:delete")
	@Log("删除角色")
	@RequestMapping("/delete")
	public Response delete(@RequestBody String[] roleIds){
		sysRoleService.deleteBatchIds(Arrays.asList(roleIds));
		return new Response("删除角色成功");
	}

	/**
	 * 获取角色列表
	 */
	@ResponseBody
	@PostMapping("/getRoles")
	public Response getRoles(){
		List<SysRole> roles = sysRoleService.getRoles();
		return new ObjectResponse<List<SysRole>>(roles);
	}


}
