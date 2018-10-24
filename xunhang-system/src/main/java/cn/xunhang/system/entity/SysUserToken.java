package cn.xunhang.system.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;

/**
 * <p>
 * 系统用户Token
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_user_token")
public class SysUserToken {

    private static final long serialVersionUID = 1L;

    @TableId
	private String userId;
    /**
     * token
     */
	private String token;
    /**
     * 过期时间
     */
	private Date expireTime;
    /**
     * 更新时间
     */
	private Date updateTime;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SysUserToken{" +
			", userId=" + userId +
			", token=" + token +
			", expireTime=" + expireTime +
			", updateTime=" + updateTime +
			"}";
	}
}
