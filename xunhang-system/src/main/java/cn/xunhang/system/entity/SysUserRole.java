package cn.xunhang.system.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_user_role")
public class SysUserRole {

    private static final long serialVersionUID = 1L;

	@TableId
	private String id;
    /**
     * 用户ID
     */
	private String userId;
    /**
     * 角色ID
     */
	private String roleId;


	public String getId() {
		if(StringUtils.isBlank(userId)){
			userId = UUID.randomUUID().toString();
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SysUserRole{" +
			", id=" + id +
			", userId=" + userId +
			", roleId=" + roleId +
			"}";
	}
}
