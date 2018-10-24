package cn.xunhang.system.service.impl;

import cn.xunhang.system.dao.SysUserDao;
import cn.xunhang.system.entity.SysUser;
import cn.xunhang.system.service.SysUserService;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysUserServiceImpl extends InfoBaseServiceImpl<SysUser,SysUserDao> implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;

	
	@Override
	public List<String> queryAllPerms(String userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<String> queryAllMenuId(String userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUser queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}

	
}
