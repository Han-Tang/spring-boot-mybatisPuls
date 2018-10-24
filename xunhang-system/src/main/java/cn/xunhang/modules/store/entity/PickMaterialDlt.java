package cn.xunhang.modules.store.entity;

import cn.xunhang.common.base.InfoBaseEntityDlt;
import cn.xunhang.common.base.SuperEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-10-21
 */

@TableName("PickMaterialDlt")
public class PickMaterialDlt extends InfoBaseEntityDlt<PickMaterialDlt> {

    /**
     * 产品名称
     */
	private String name;
	private String gCode;
	private String type;
	private String format;
	private String meterial1;
	private String color1;
	private Long packQty;
	private Long packLeave;
	private String unit;
	private String kind;
	private String storeId;
    /**
     * 系列
     */
	private String serial;
    /**
     * 型号
     */
	private String model;

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

	public Long getPackQty() {
		return packQty;
	}

	public void setPackQty(Long packQty) {
		this.packQty = packQty;
	}

	public Long getPackLeave() {
		return packLeave;
	}

	public void setPackLeave(Long packLeave) {
		this.packLeave = packLeave;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
}
