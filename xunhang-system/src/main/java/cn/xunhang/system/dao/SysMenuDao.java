package cn.xunhang.system.dao;

import cn.xunhang.system.baseMapper.SuperMapper;
import cn.xunhang.system.entity.SysMenu;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 菜单管理 Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysMenuDao extends SuperMapper<SysMenu> {

	/**
	 * 查询菜单列表
	 * @param map
	 * @return
	 */
	List<SysMenu> queryList(Map<String, Object> map);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(String parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenu> queryUserList(String userId);

	/**
	 * 删除菜单和角色关联的数据
	 */
	int deleteBatch(String[] id);
	
}