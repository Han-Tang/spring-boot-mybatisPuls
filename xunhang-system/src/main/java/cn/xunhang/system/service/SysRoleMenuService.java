package cn.xunhang.system.service;

import cn.xunhang.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

	void saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 去除父节点id
	 */
	List<String> removeParentId(List<String> menuIdList);
}
