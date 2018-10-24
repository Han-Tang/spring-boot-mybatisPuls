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
@TableName("StoreOutHdr")
public class StoreOutHdr extends SuperEntity<StoreOutHdr> {


    /**
     * 出仓单号
     */
    @TableId
	private String formNo;

	/**
	 * 发货单号
	 */
	private String fFormNo;
    /**
     * 销售单号
     */
	private String sFormNo;
    /**
     * 出仓人
     */
	private String storeOutBy;
    /**
     * 出仓日期
     */
	private Date storeOutDate;
    /**
     * 仓库id
     */
	private String storeId;
    /**
     * 审核状态
     */
	private String status;
    /**
     * 出仓确认人
     */
	private String storeOutConfirmBy;
    /**
     * 出仓确认时间
     */
	private Date storeOutConfirmTime;

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

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

	public String getStoreOutBy() {
		return storeOutBy;
	}

	public void setStoreOutBy(String storeOutBy) {
		this.storeOutBy = storeOutBy;
	}

	public Date getStoreOutDate() {
		return storeOutDate;
	}

	public void setStoreOutDate(Date storeOutDate) {
		this.storeOutDate = storeOutDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoreOutConfirmBy() {
		return storeOutConfirmBy;
	}

	public void setStoreOutConfirmBy(String storeOutConfirmBy) {
		this.storeOutConfirmBy = storeOutConfirmBy;
	}

	public Date getStoreOutConfirmTime() {
		return storeOutConfirmTime;
	}

	public void setStoreOutConfirmTime(Date storeOutConfirmTime) {
		this.storeOutConfirmTime = storeOutConfirmTime;
	}
}
