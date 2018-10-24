package cn.xunhang.system.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

/**
 * <p>
 * 角色与部门对应关系
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_role_dept")
public class SysRoleDept {

    private static final long serialVersionUID = 1L;

	@TableId
	private String id;
    /**
     * 角色ID
     */
	private String roleId;
    /**
     * 部门ID
     */
	private String deptId;


	public String getId() {
		if(StringUtils.isBlank(id)){
			id = UUID.randomUUID().toString();
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "SysRoleDept{" +
			", id=" + id +
			", roleId=" + roleId +
			", deptId=" + deptId +
			"}";
	}
}
