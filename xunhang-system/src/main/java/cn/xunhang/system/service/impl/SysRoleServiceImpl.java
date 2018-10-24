package cn.xunhang.system.service.impl;

import cn.xunhang.system.dao.SysRoleDao;
import cn.xunhang.system.dao.SysUserRoleDao;
import cn.xunhang.system.entity.SysRole;
import cn.xunhang.system.service.SysRoleService;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysRoleServiceImpl extends InfoBaseServiceImpl<SysRole,SysRoleDao> implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;


	@Override
	public List<SysRole> getRoles() {
		List<SysRole> roles = sysRoleDao.selectList(new EntityWrapper<SysRole>()
				.eq("deleted",0));
		return roles;
	}

	@Override
	public List<SysRole> getUserRoles(String userId) {
		List<String> roleIds = sysUserRoleDao.queryRoleIdList(userId);
		List<SysRole> roles = sysRoleDao.selectList(new EntityWrapper<SysRole>()
				.eq("deleted",0)
				.in("id",roleIds));
		return roles;
	}

	
}
