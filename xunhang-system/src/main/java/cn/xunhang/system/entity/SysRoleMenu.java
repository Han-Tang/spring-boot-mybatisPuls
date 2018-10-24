package cn.xunhang.system.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_role_menu")
public class SysRoleMenu {

    private static final long serialVersionUID = 1L;

	@TableId
	private String id;
    /**
     * 角色ID
     */
	private String roleId;
    /**
     * 菜单ID
     */
	private String menuId;


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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	@Override
	public String toString() {
		return "SysRoleMenu{" +
			", id=" + id +
			", roleId=" + roleId +
			", menuId=" + menuId +
			"}";
	}
}
