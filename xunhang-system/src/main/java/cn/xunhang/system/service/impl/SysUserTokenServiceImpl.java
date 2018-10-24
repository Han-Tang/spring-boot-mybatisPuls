package cn.xunhang.system.service.impl;

import cn.xunhang.common.oauth2.TokenGenerator;
import cn.xunhang.common.utils.R;
import cn.xunhang.system.dao.SysUserTokenDao;
import cn.xunhang.system.entity.SysUser;
import cn.xunhang.system.entity.SysUserToken;
import cn.xunhang.system.service.SysUserTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserToken> implements SysUserTokenService {

	@Value("${spring.EXPIRE}")
	private int EXPIRE;
	@Autowired
	private SysUserTokenDao sysUserTokenDao;

	@Override
	public SysUserToken queryByToken(String token) {
		return sysUserTokenDao.queryByToken(token);
	}
	
	@Override
	@Transactional
	public R createToken(SysUser user) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserToken tokenEntity = sysUserTokenDao.selectById(user.getId());
		if(tokenEntity == null){
			tokenEntity = new SysUserToken();
			tokenEntity.setUserId(user.getId());
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			sysUserTokenDao.insert(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			sysUserTokenDao.updateById(tokenEntity);
		}

		R r = R.ok("登录成功").put("token", token).put("expire", EXPIRE).put("name", user.getName());

		return r;
	}
	
}
