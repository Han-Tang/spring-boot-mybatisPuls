package cn.xunhang.system.service.impl;

import cn.xunhang.system.dao.SysUserRoleDao;
import cn.xunhang.system.entity.SysUserRole;
import cn.xunhang.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	@Transactional
	public void saveOrUpdate(String userId, List<String> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleDao.deleteByUserId(userId);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		sysUserRoleDao.save(map);
	}

	@Override
	public List<String> queryRoleIdList(String userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	@Transactional
	public void deleteByUserId(String userId) {
		sysUserRoleDao.deleteByUserId(userId);
	}
}
