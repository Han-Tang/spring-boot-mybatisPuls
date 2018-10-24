package cn.xunhang.modules.store.entity;

import cn.xunhang.common.base.InfoBaseEntityDlt;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-10-19
 */

@TableName("RepertoryDlt")
public class RepertoryDlt extends InfoBaseEntityDlt<RepertoryDlt> {

    /**
     * 产品名称
     */
	private String name;

	private String gCode;

	/**
	 * 类型
	 */
	private String type;
	/**
	 * 规格
	 */
	private String format;
	/**
	 * 主材质
	 */
	private String meterial1;
	/**
	 * 主花色
	 */
	private String color1;
	/**
	 *  入库包装数量
	 */
	private Long packQty;
	/**
	 *  入库包装剩余数量
	 */
	private Long packLeave;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 种类：如 成品 半成品 原材料
	 */
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

	/**
	 * 占用库存
	 */
	private Long using;

	/**
	 * 仓位
	 */
	@TableField(exist = false)
	private List<Map> storeLocation;


	public List<Map> getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(List<Map> storeLocation) {
		this.storeLocation = storeLocation;
	}

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

	public Long getUsing() {
		return using;
	}

	public void setUsing(Long using) {
		this.using = using;
	}
}
