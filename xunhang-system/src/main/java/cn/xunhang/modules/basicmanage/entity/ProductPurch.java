package cn.xunhang.modules.basicmanage.entity;

import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-08-07
 */
@TableName("ProductPurch")
public class ProductPurch extends InfoBaseDO<ProductPurch> {

    private static final long serialVersionUID = 1L;

	//
	private String infoProductId;
	//产品等级
	private String grade;
	//采购价
	private Double price;
	//供应商
	private String supplierCode;
	//包装贴牌
	private String logo;


	public String getInfoProductId() {
		return infoProductId;
	}

	public void setInfoProductId(String infoProductId) {
		this.infoProductId = infoProductId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}



}
