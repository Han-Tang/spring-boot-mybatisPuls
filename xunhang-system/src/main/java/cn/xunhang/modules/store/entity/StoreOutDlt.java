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
 * @since 2018-10-22
 */
@TableName("StoreOutDlt")
public class StoreOutDlt extends InfoBaseEntityDlt<StoreOutDlt> {

	private String fFormNo;
	private String sFormNo;

    /**
     * 产品名称
     */
	private String name;

    /**
     * 主材质
     */
	private String meterial1;
    /**
     * 主花色
     */
	private String color1;


	private String storeId;

	public String getfFormNo() {
		return fFormNo;
	}

	public void setfFormNo(String fFormNo) {
		this.fFormNo = fFormNo;
	}

	public String getsFormNo() {
		return sFormNo;
	}

	public void setsFormNo(String sFormNo) {
		this.sFormNo = sFormNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
