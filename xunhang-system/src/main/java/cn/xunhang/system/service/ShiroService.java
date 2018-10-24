package cn.xunhang.system.service;

import cn.xunhang.system.entity.SysUser;
import cn.xunhang.system.entity.SysUserToken;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;

import java.util.Set;

/**
 * shiro相关接口
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017-06-06 8:49
 */
public interface ShiroService  {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);

    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(String userId);
}
