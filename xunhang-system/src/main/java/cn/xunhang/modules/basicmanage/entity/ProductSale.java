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
@TableName("ProductSale")
public class ProductSale extends InfoBaseDO<ProductSale> {

    private static final long serialVersionUID = 1L;

    //产品编码
	private String infoProductId;
	//产品名称
	private String name;
	//计量单位
	private String unit;
	//产品等级
	private String grade;
	//计价方式
	private String priceMethod;
	//默认销售价
	private Double price;


	public String getInfoProductId() {
		return infoProductId;
	}

	public void setInfoProductId(String infoProductId) {
		this.infoProductId = infoProductId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPriceMethod() {
		return priceMethod;
	}

	public void setPriceMethod(String priceMethod) {
		this.priceMethod = priceMethod;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


}
