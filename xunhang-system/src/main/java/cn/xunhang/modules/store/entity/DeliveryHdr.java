package cn.xunhang.modules.store.entity;

import cn.xunhang.common.base.SuperEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-10-22
 */
@TableName("DeliveryHdr")
public class DeliveryHdr extends SuperEntity<DeliveryHdr> {


	@TableId
	private String formNo;

	private String sFormNo;
	private Date deliveryTime;
	private String status;
	private String reviewBy;
	private Date storeOutConfirmTime;

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getsFormNo() {
		return sFormNo;
	}

	public void setsFormNo(String sFormNo) {
		this.sFormNo = sFormNo;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReviewBy() {
		return reviewBy;
	}

	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}

	public Date getStoreOutConfirmTime() {
		return storeOutConfirmTime;
	}

	public void setStoreOutConfirmTime(Date storeOutConfirmTime) {
		this.storeOutConfirmTime = storeOutConfirmTime;
	}
}
