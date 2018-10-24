package cn.xunhang.system.service;

import cn.xunhang.system.entity.SysRoleDept;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与部门对应关系 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {
	
	void saveOrUpdate(String roleId, List<String> deptIdList);
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<String> queryDeptIdList(String roleId);
	
}
