package cn.xunhang.modules.store.entity;

import cn.xunhang.modules.store.entity.infoBase.InfoBaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-08-30
 */
public class StoreHdr extends InfoBaseEntity<StoreHdr> {

    /**
     * 仓库名称
     */
	private String storeName;
    /**
     * 仓库编码
     */
	private String storeNo;
    /**
     * 仓库位置
     */
	private String storeLocation;



	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}


}
