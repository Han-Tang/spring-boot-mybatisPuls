package cn.xunhang.modules.basicmanage.entity;


import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-08-07
 */
@TableName("InfoProduct")
public class InfoProduct extends InfoBaseDO<InfoProduct> {

    /**
     * 包含产品材质、花色等属性的产品编号，若无属性，则与主产品编号一致 unique
     */
	private String code;
    /**
     * 不包含任何属性的产品编号
     */
	//主产品编码
	private String gCode;
	//产品名称
	private String name;
	//产品系列
	private String serial;
	//产品型号
	private String model;
	//产品规格
	private String spec;
	//主花色编号
	private String colorCode1;
	//副花色编号
	private String colorCode2;
	//产品主花色
	private String color1;
	//产品副花色
	private String color2;
	//主材质编号
	private String meterialCode1;
	//副材质编号
	private String meterialCode2;
	//产品主材质
	private String meterial1;
	//产品副材质
	private String meterial2;
	//产品漆类
	private String paint;
	//产品类型
	private String kind;
	//是否外购
	private Boolean Buy;
	//造型
	private String shape;
	//计量单位
	private String unit;
	//软包类型
	private String softKind;
	//产品等级
	private String grade;
    /**
     * 计量单位：毫米，精确到0.1
     */
    //产品长
	private Double length;
	//产品宽
	private Double width;
	//产品高
	private Double thick;
	//销售属性
	private Boolean saleAttr;
	//采购属性
	private Boolean purchAttr;
	//生产属性
	private Boolean produceAttr;
	//仓储属性
	private Boolean storeAttr;



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getColorCode1() {
		return colorCode1;
	}

	public void setColorCode1(String colorCode1) {
		this.colorCode1 = colorCode1;
	}

	public String getColorCode2() {
		return colorCode2;
	}

	public void setColorCode2(String colorCode2) {
		this.colorCode2 = colorCode2;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getMeterialCode1() {
		return meterialCode1;
	}

	public void setMeterialCode1(String meterialCode1) {
		this.meterialCode1 = meterialCode1;
	}

	public String getMeterialCode2() {
		return meterialCode2;
	}

	public void setMeterialCode2(String meterialCode2) {
		this.meterialCode2 = meterialCode2;
	}

	public String getMeterial1() {
		return meterial1;
	}

	public void setMeterial1(String meterial1) {
		this.meterial1 = meterial1;
	}

	public String getMeterial2() {
		return meterial2;
	}

	public void setMeterial2(String meterial2) {
		this.meterial2 = meterial2;
	}

	public String getPaint() {
		return paint;
	}

	public void setPaint(String paint) {
		this.paint = paint;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Boolean getBuy() {
		return Buy;
	}

	public void setBuy(Boolean buy) {
		Buy = buy;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSoftKind() {
		return softKind;
	}

	public void setSoftKind(String softKind) {
		this.softKind = softKind;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getThick() {
		return thick;
	}

	public void setThick(Double thick) {
		this.thick = thick;
	}

	public Boolean getSaleAttr() {
		return saleAttr;
	}

	public void setSaleAttr(Boolean saleAttr) {
		this.saleAttr = saleAttr;
	}

	public Boolean getPurchAttr() {
		return purchAttr;
	}

	public void setPurchAttr(Boolean purchAttr) {
		this.purchAttr = purchAttr;
	}

	public Boolean getProduceAttr() {
		return produceAttr;
	}

	public void setProduceAttr(Boolean produceAttr) {
		this.produceAttr = produceAttr;
	}

	public Boolean getStoreAttr() {
		return storeAttr;
	}

	public void setStoreAttr(Boolean storeAttr) {
		this.storeAttr = storeAttr;
	}




}
