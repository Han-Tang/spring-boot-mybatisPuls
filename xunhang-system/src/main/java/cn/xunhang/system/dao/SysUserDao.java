package cn.xunhang.system.dao;

import cn.xunhang.system.baseMapper.SuperMapper;
import cn.xunhang.system.entity.SysUser;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 系统用户 Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysUserDao extends SuperMapper<SysUser> {
	
	List<SysUser> queryPageList(Page<SysUser> page, Map<String, Object> map);

	List<SysUser> queryList(Map<String, Object> map);
	
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
	/**
	 * 根据id查询用户
	 */
	SysUser queryById(String id);

	/**
	 * 删除用户关联表数据
	 * @param id
	 * @return
	 */
	int deleteBatch(Object[] id);	
	
}