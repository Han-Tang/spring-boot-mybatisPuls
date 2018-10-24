package cn.xunhang.system.service;

import cn.xunhang.common.base.Tree;
import cn.xunhang.system.dao.SysMenuDao;
import cn.xunhang.system.entity.SysMenu;
import cn.xunhang.system.service.infoBase.InfoBaseService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysMenuService extends InfoBaseService<SysMenu,SysMenuDao> {

	/**
	 * 查询菜单列表
	 */
	List<Tree<SysMenu>> queryList(Map<String, Object> map);

	/**
	 * 获取用户菜单树
	 */
	List<Tree<SysMenu>> listMenuTree(String id);

	/**
	 * 获取菜单树 添加顶级菜单
	 */
	Tree<SysMenu> getTree(String userId);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(String parentId);

}
