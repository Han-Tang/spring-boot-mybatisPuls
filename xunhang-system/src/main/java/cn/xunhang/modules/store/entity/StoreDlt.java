package cn.xunhang.modules.store.entity;

import cn.xunhang.modules.store.entity.infoBase.InfoBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-08-30
 */

public class StoreDlt extends InfoBaseEntity<StoreDlt> {

	private String storeId;
	//仓库名称
	private String storeName;
	//行
	private String row;
	//列
	private String col;
	//层
	private String layer;
	//状态
	private String status;

	//仓位区域
	private String area;
	//编码
	private String locationNo;
	//简码
	private String locationShortNo;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	public String getLocationShortNo() {
		return locationShortNo;
	}

	public void setLocationShortNo(String locationShortNo) {
		this.locationShortNo = locationShortNo;
	}
}
