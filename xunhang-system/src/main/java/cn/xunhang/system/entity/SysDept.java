package cn.xunhang.system.entity;

import cn.xunhang.system.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.List;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_dept")
public class SysDept extends InfoBaseDO<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 上级部门ID，一级部门为0
     */
	private String parentId;
    /**
     * 部门名称
     */
	private String name;
    /**
     * 排序
     */
	private Integer orderNum;
	/**
	 * 上级部门名称
	 */
	@TableField(exist=false)
	private String parentName;
	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;
	
	@TableField(exist=false)
	private List<?> list;


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "SysDept{" +
			", id=" + id +
			", parentId=" + parentId +
			", name=" + name +
			", orderNum=" + orderNum +
			"}";
	}
}
