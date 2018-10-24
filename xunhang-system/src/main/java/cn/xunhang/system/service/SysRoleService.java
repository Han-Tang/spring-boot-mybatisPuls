package cn.xunhang.system.service;

import cn.xunhang.system.dao.SysRoleDao;
import cn.xunhang.system.entity.SysRole;
import cn.xunhang.system.service.infoBase.InfoBaseService;
import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysRoleService extends InfoBaseService<SysRole,SysRoleDao> {

	/**
	 * 获取所有角色
	 */
	List<SysRole> getRoles();

	/**
	 * 获取当前用户角色
	 * @param userId
	 * @return
	 */
	List<SysRole> getUserRoles(String userId);
	
}
