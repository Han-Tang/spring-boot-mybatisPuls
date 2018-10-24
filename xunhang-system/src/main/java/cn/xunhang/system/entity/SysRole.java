package cn.xunhang.system.entity;

import cn.xunhang.system.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_role")
public class SysRole extends InfoBaseDO<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
	@NotBlank(message="角色名称不能为空")
	private String roleName;
    /**
     * 备注
     */
	private String remark;

	@TableField(exist=false)
	private List<String> menuIdList;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<String> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}

	@Override
	public String toString() {
		return "SysRole{" +
			", id=" + id +
			", roleName=" + roleName +
			", remark=" + remark +
			", createDate=" + createDate +
			"}";
	}
}
