package cn.xunhang.modules.store.entity;

import cn.xunhang.common.base.InfoBaseEntityDlt;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-10-19
 */
@TableName("OtherSourceDlt")
public class OtherSourceDlt extends InfoBaseEntityDlt<OtherSourceDlt> {


    /**
     * 产品名称
     */
	private String name;

	private String gCode;

	private String storeId;

	private String type;
	private String format;
	private String meterial1;
	private String color1;
	private String unit;
    /**
     * 种类：如成品，半成品、原材料
     */
	private String kind;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getMeterial1() {
		return meterial1;
	}

	public void setMeterial1(String meterial1) {
		this.meterial1 = meterial1;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
