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

@TableName("DeliveryDlt")
public class DeliveryDlt extends InfoBaseEntityDlt<DeliveryDlt> {


    /**
     * 产品名称
     */
	private String name;
    /**
     * 产品编码
     */
	private String code;
    /**
     * 主材质
     */
	private String meterial1;
    /**
     * 主花色
     */
	private String color1;

	private String storeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
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
