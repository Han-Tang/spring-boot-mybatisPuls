package cn.xunhang.system.service.impl;

import cn.xunhang.common.base.Tree;
import cn.xunhang.common.utils.BuildTree;
import cn.xunhang.system.dao.SysMenuDao;
import cn.xunhang.system.entity.SysMenu;
import cn.xunhang.system.service.SysMenuService;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysMenuServiceImpl extends InfoBaseServiceImpl<SysMenu,SysMenuDao> implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<Tree<SysMenu>> queryList(Map<String, Object> map) {
		List<SysMenu> menuDOs = sysMenuDao.queryList(map);
		List<Tree<SysMenu>> trees = getAllMenuTree(menuDOs);
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
		return list;
	}

	@Override
	public List<Tree<SysMenu>> listMenuTree(String id) {
		List<SysMenu> menuDOs = sysMenuDao.queryUserList(id);
		List<Tree<SysMenu>> trees = getAllMenuTree(menuDOs);
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
		return list;
	}

	@Override
	public Tree<SysMenu> getTree(String userId) {
		List<SysMenu> menuDOs = sysMenuDao.queryList(new HashMap<>());
		List<Tree<SysMenu>> trees = getAllMenuTree(menuDOs);
//		List<Tree<SysMenu>> trees = listMenuTree(userId);
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<SysMenu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<SysMenu> queryListParentId(String parentId) {
		return sysMenuDao.queryListParentId(parentId);
	}

	/**
	 * 获取所有菜单列表树
	 */
	private List<Tree<SysMenu>> getAllMenuTree(List<SysMenu> menuDOs){
		List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
		for (SysMenu sysMenuDO : menuDOs) {
			Tree<SysMenu> tree = new Tree<SysMenu>();
			tree.setId(sysMenuDO.getId());
			tree.setParentId(sysMenuDO.getParentId());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			attributes.put("type", sysMenuDO.getType());
			attributes.put("perms", sysMenuDO.getPerms());
			attributes.put("ts", sysMenuDO.getTs());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		return trees;
	}

	
}
