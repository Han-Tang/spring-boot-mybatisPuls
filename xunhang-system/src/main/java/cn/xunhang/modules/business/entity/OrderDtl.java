package cn.xunhang.modules.business.entity;

import cn.xunhang.modules.business.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public class OrderDtl extends InfoBaseDO<OrderDtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
	private String formno;
    /**
     * 明细序号
     */
	private String sn;
    /**
     * 产品名称
     */
	private String productName;
    /**
     * 产品编码
     */
	private String productCode;
    /**
     * 主花色
     */
	private String mainColor;
    /**
     * 副花色
     */
	private String viceColor;
    /**
     * 主材质
     */
	private String mainMaterial;
    /**
     * 副材质
     */
	private String viceMaterial;
    /**
     * 数量
     */
	private Float qty;
    /**
     * 单价
     */
	private Float price;
    /**
     * 计价方式
     */
	private String priceMethod;
    /**
     * 金额
     */
	private Float amount;
	//系列名称
	@TableField(exist = false)
	private String serial;
	//漆类
	@TableField(exist = false)
	private String paint;

	public String getFormno() {
		return formno;
	}

	public void setFormno(String formno) {
		this.formno = formno;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMainColor() {
		return mainColor;
	}

	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}

	public String getViceColor() {
		return viceColor;
	}

	public void setViceColor(String viceColor) {
		this.viceColor = viceColor;
	}

	public String getMainMaterial() {
		return mainMaterial;
	}

	public void setMainMaterial(String mainMaterial) {
		this.mainMaterial = mainMaterial;
	}

	public String getViceMaterial() {
		return viceMaterial;
	}

	public void setViceMaterial(String viceMaterial) {
		this.viceMaterial = viceMaterial;
	}

	public Float getQty() {
		return qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPriceMethod() {
		return priceMethod;
	}

	public void setPriceMethod(String priceMethod) {
		this.priceMethod = priceMethod;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getPaint() {
		return paint;
	}

	public void setPaint(String paint) {
		this.paint = paint;
	}

	@Override
	public String toString() {
		return "OrderDtl{" +
			", id=" + id +
			", formno=" + formno +
			", sn=" + sn +
			", productName=" + productName +
			", productCode=" + productCode +
			", mainColor=" + mainColor +
			", viceColor=" + viceColor +
			", mainMaterial=" + mainMaterial +
			", viceMaterial=" + viceMaterial +
			", qty=" + qty +
			", price=" + price +
			", priceMethod=" + priceMethod +
			", amount=" + amount +
			", description=" + description +
			", active=" + active +
			", deleted=" + deleted +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", ts=" + ts +
			"}";
	}
}
