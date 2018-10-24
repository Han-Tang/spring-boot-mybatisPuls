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
@TableName("ProductProduce")
public class ProductProduce extends InfoBaseDO<ProductProduce> {

    private static final long serialVersionUID = 1L;

    //产品编码
	private String infoProductId;
	//产品主花色
	private String colorCode1;
	//产品主材质
	private String meterialCode1;
	//生产周期
	private Integer cycle;
	//图纸号
	private String drawingNo;
	//产品规格
	private String spec;


	public String getInfoProductId() {
		return infoProductId;
	}

	public void setInfoProductId(String infoProductId) {
		this.infoProductId = infoProductId;
	}

	public String getColorCode1() {
		return colorCode1;
	}

	public void setColorCode1(String colorCode1) {
		this.colorCode1 = colorCode1;
	}

	public String getMeterialCode1() {
		return meterialCode1;
	}

	public void setMeterialCode1(String meterialCode1) {
		this.meterialCode1 = meterialCode1;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}




}
