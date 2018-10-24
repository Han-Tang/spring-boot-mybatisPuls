package cn.xunhang.system.entity;

//import cn.xunhang.common.excel.ExcelResources;
import cn.xunhang.common.validator.group.AddGroup;
import cn.xunhang.common.validator.group.UpdateGroup;
import cn.xunhang.system.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_user")
public class SysUser extends InfoBaseDO<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;
    /**
     * 密码
     */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String password;
    /**
     * sha256加密
     */
	private String salt;
	/**
	 * 用户姓名
	 */
	private String name;
    /**
     * 邮箱
     */
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
	private Integer status;
    /**
     * 部门ID
     */
	@NotNull(message="部门不能为空 deptId", groups = {AddGroup.class, UpdateGroup.class})
	private String deptId;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<String> roleIdList;

	
//	@ExcelResources(title="用户名",order=1)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@ExcelResources(title="邮箱",order=2)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	@ExcelResources(title="手机号",order=3)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
//	@ExcelResources(title="部门名称",order=4)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	@Override
	public String toString() {
		return "SysUser{" +
			", id=" + id +
			", username=" + username +
			", password=" + password +
			", salt=" + salt +
			", email=" + email +
			", mobile=" + mobile +
			", status=" + status +
			", deptId=" + deptId +
			", createDate=" + createDate +
			"}";
	}
}
