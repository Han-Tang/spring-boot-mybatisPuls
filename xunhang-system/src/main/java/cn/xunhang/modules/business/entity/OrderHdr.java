package cn.xunhang.modules.business.entity;

import cn.xunhang.modules.business.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public class OrderHdr extends InfoBaseDO<OrderHdr> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号 primary unique
     */
	@TableId
	private String formno;
    /**
     * 报价单编号
     */
	private String pFormno;
    /**
     * 订单类型
     */
	private String orderType;
    /**
     * 客户编码
     */
	private String customerCode;
    /**
     * 客户名称
     */
	private String customerName;
    /**
     * 区域
     */
	private String region;
    /**
     * 业务员
     */
	private String salesman;
    /**
     * 是否加急
     */
	private Boolean urgent;
	/**
	 * 订货日期
	 */
	private Date orderedDate;
    /**
     * 交货日期
     */
	private Date deliveryDate;
    /**
     * 新建订单、订单提交、产品设计、产品报价、业务收款、计划排产、产品生产、产品入仓、产品出仓、订单完成
     */
	private String status;

	@TableField(exist = false)
	private String oldStatus;
    /**
     * 状态时间
     */
	private Date statusDate;
    /**
     * 订单金额
     */
	private Float amount;
    /**
     * 折扣说明
     */
	private String discountSuggest;
	/**
	 * 付款方式
	 */
	private String payWay;
	/**
	 * 提货方式
	 */
	private String deliveryWay;

	/**
	 * 已收款金额
	 */
	private Float receivedAmount;
	//收货地址
	@TableField(exist = false)
	private String shippingAddress;
	//联系方式
	@TableField(exist = false)
	private String phone;


	public String getFormno() {
		return formno;
	}

	public void setFormno(String formno) {
		this.formno = formno;
	}

	public String getpFormno() {
		return pFormno;
	}

	public void setpFormno(String pFormno) {
		this.pFormno = pFormno;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public Boolean getUrgent() {
		return urgent;
	}

	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDiscountSuggest() {
		return discountSuggest;
	}

	public void setDiscountSuggest(String discountSuggest) {
		this.discountSuggest = discountSuggest;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getDeliveryWay() {
		return deliveryWay;
	}

	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}

	public Float getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(Float receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "OrderHdr{" +
			", id=" + id +
			", formno=" + formno +
			", pFormno=" + pFormno +
			", orderType=" + orderType +
			", customerCode=" + customerCode +
			", customerName=" + customerName +
			", region=" + region +
			", salesman=" + salesman +
			", urgent=" + urgent +
			", deliveryDate=" + deliveryDate +
			", status=" + status +
			", statusDate=" + statusDate +
			", amount=" + amount +
			", discountSuggest=" + discountSuggest +
			", description=" + description +
			", active=" + active +
			", deleted=" + deleted +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", payWay=" + payWay +
			", deliveryWay=" + deliveryWay +
			", ts=" + ts +
			"}";
	}
}
