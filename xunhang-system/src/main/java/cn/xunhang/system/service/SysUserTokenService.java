package cn.xunhang.system.service;

import cn.xunhang.common.utils.R;
import cn.xunhang.system.entity.SysUser;
import cn.xunhang.system.entity.SysUserToken;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysUserTokenService extends IService<SysUserToken> {

	SysUserToken queryByToken(String token);
	
	/**
	 * 生成token
	 * @param user  用户
	 */
	R createToken(SysUser user);
}
