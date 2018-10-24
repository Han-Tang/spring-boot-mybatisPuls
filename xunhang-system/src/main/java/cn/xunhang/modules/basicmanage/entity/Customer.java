package cn.xunhang.modules.basicmanage.entity;


import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@TableName("Customer")
public class Customer extends InfoBaseDO<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户编码 primary unique
     */
	@TableId
	private String code;
    /**
     * 客户名称 unique
     */
	private String name;
    /**
     * 客户类型
     */
	private String type;
	/**
	 * 客户分类
	 */
	private String category;
	/**
	 * 法人
	 */
	private String manager;
    /**
     * 电话
     */
	private String phone;
    /**
     * 传真
     */
	private String fax;
    /**
     * 地址
     */
	private String address;
    /**
     * 收货地址
     */
	private String shippingAddress;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 身份证
     */
	private String idNumber;
	/**
	 * 支付方式
	 */
	private String payment;
	/**
	 * 提货方式
	 */
	private String delivery;
    /**
     * 备注
     */
	private String remark;
    /**
     * 业务员
     */
	private String salesman;
    /**
     * 区域
     */
	private String region;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	@Override
	public String toString() {
		return "Customer{" +
				", code=" + code +
				", name=" + name +
				", type=" + type +
				", phone=" + phone +
				", fax=" + fax +
				", address=" + address +
				", shippingAddress=" + shippingAddress +
				", email=" + email +
				", idNumber=" + idNumber +
				", payment=" + payment +
				", delivery=" + delivery +
				", id=" + id +
				", active=" + active +
				", deleted=" + deleted +
				", createDate=" + createDate +
				", createBy=" + createBy +
				", updateDate=" + updateDate +
				", updateBy=" + updateBy +
				", remark=" + remark +
				", salesman=" + salesman +
				", region=" + region +
				", ts=" + ts +
				"}";
	}
}
