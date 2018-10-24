package cn.xunhang.system.dao;

import cn.xunhang.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);
	
	void save(Map<String, Object> map);

	void deleteByRoleId(String roleId);
	
}