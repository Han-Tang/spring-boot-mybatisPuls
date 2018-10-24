package cn.xunhang.system.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.base.Tree;
import cn.xunhang.common.exception.RRException;
import cn.xunhang.common.utils.Constant.MenuType;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.SysMenu;
import cn.xunhang.system.service.ShiroService;
import cn.xunhang.system.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends InfoBaseController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroService shiroService;

	/**
	 * 导航菜单
	 */
	@PostMapping("/nav")
	public Response nav(){
		List<Tree<SysMenu>> menus = sysMenuService.listMenuTree(getUserId());
		return new ObjectResponse<List>(menus);
	}

	
	/**
	 * 所有菜单列表
	 */
	@RequiresPermissions("sys:menu:list")
	@RequestMapping("/list")
	public Response list(@RequestBody Map<String,Object> params){
		List<Tree<SysMenu>> menuList = sysMenuService.queryList(params);
		return new ObjectResponse<List>(menuList);
	}

	/**
	 * 菜单信息
	 */
	@RequestMapping("/info")
	public Response info(@RequestBody Map params){
		SysMenu menu = sysMenuService.selectById((String)params.get("id"));
		return new ObjectResponse<SysMenu>(menu);
	}
	
	/**
	 * 新增保存菜单
	 */
	@RequiresPermissions("sys:menu:save")
	@RequestMapping("/save")
	public Response save(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
		sysMenuService.insertInfo(menu);
		return new Response("新增保存菜单成功");
	}
	
	/**
	 * 编辑修改菜单
	 */
	@RequiresPermissions("sys:menu:update")
	@RequestMapping("/update")
	public Response update(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
		sysMenuService.updateInfo(menu);
		return new Response("编辑修改菜单成功");
	}
	
	/**
	 * 删除菜单
	 */
	@RequiresPermissions("sys:menu:delete")
	@RequestMapping("/delete")
	public Response delete(@RequestBody String[] ids){
		for (String id : ids){
			//判断是否有子菜单或按钮
			List<SysMenu> menuList = sysMenuService.queryListParentId(id);
			if(menuList.size() > 0){
				return new Response(100,"请先删除子菜单或按钮");
			}
		}
		sysMenuService.deleteBatchIds(Arrays.asList(ids));
		return new Response("删除菜单成功");
	}

	/**
	 * 获取菜单树
	 */
	@PostMapping("/tree")
	public Response tree() {
		Tree<SysMenu> tree = sysMenuService.getTree(getUserId());
		return new ObjectResponse<Tree<SysMenu>>(tree);
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId().contains("-1")) menu.setParentId("0");
		if(menu.getParentId() != "0"){
			SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()){
			if(parentType != MenuType.CATALOG.getValue()){
				throw new RRException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == MenuType.BUTTON.getValue()){
			if(parentType != MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
