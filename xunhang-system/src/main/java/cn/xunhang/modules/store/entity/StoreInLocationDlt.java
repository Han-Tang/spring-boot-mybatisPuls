package cn.xunhang.modules.store.entity;

import cn.xunhang.common.base.SuperEntity;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */

public class StoreInLocationDlt extends SuperEntity<StoreInLocationDlt> {


	/**
	 * 待进仓从表id
	 */
	private String storeInDltId;
	/**
	 * 仓位id
	 */
	private String storeLocationId;

	/**
	 * 存储数量
	 */
	private int num;

	public String getStoreInDltId() {
		return storeInDltId;
	}

	public void setStoreInDltId(String storeInDltId) {
		this.storeInDltId = storeInDltId;
	}

	public String getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(String storeLocationId) {
		this.storeLocationId = storeLocationId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
