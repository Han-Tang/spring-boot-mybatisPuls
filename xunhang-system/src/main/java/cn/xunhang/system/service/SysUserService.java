package cn.xunhang.system.service;

import cn.xunhang.system.dao.SysUserDao;
import cn.xunhang.system.entity.SysUser;
import cn.xunhang.system.service.infoBase.InfoBaseService;
import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysUserService extends InfoBaseService<SysUser,SysUserDao> {

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(String userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);

}
