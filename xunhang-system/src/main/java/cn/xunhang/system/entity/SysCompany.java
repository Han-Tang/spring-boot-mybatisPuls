package cn.xunhang.system.entity;

import cn.xunhang.system.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_company")
public class SysCompany extends InfoBaseDO<SysCompany> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6267172643186769918L;

	// 上级部门ID，一级部门为0
	private String parentId;
	// 公司名称
	private String name;
	// 公司类型
	private String type;
	// 系统分配的编码
	private String code;
	// 公司简称
	private String simpleName;
	// 公司所属行业
	private String industry;
	// 公司法人
	private String manager;
	// 公司联系人
	private String contact;
	// 公司联系电话
	private String phone;
	// 传真
	private String fax;
	// 邮件地址
	private String email;
	// 公司详细地址
	private String address;
	//区域
	private String region;
	//收货地址
	private String recevieAddress;
	//业务员
	private String salesman;
	// 备注
	private String remark;
	// 排序
	private Integer orderNum;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRecevieAddress() {
		return recevieAddress;
	}

	public void setRecevieAddress(String recevieAddress) {
		this.recevieAddress = recevieAddress;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "SysCompany{" +
				"id=" + id +
				", parentId=" + parentId +
				", name='" + name +
				", type=" + type +
				", code=" + code +
				", simpleName=" + simpleName +
				", industry=" + industry +
				", manager=" + manager +
				", contact=" + contact +
				", phone=" + phone +
				", fax=" + fax +
				", email=" + email +
				", address=" + address +
				", remark=" + remark +
				", orderNum=" + orderNum +
				'}';
	}
}
