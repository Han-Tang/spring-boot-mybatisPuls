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
 * @since 2018-10-19
 */

@TableName("StoreInHdr")
public class StoreInHdr extends SuperEntity<StoreInHdr> {


    /**
     * 进仓单号
     */
    @TableId
	private String formNo;

	/**
	 * 产品类型
	 */
	private String kind;

    /**
     * 进仓日期
     */
	private Date storeInTime;

	/**
	 * 进仓人
	 */
	private String storeInBy;

    /**
     * 进仓确认人
     */
	private String storeInConfirmBy;

    /**
     * 仓库id
     */
	private String storeId;
    /**
     * 审核状态
     */
	private String status;
    /**
     * 进仓确认时间
     */
	private Date storeInConfirmTime;


	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Date getStoreInTime() {
		return storeInTime;
	}

	public void setStoreInTime(Date storeInTime) {
		this.storeInTime = storeInTime;
	}

	public String getStoreInBy() {
		return storeInBy;
	}

	public void setStoreInBy(String storeInBy) {
		this.storeInBy = storeInBy;
	}

	public String getStoreInConfirmBy() {
		return storeInConfirmBy;
	}

	public void setStoreInConfirmBy(String storeInConfirmBy) {
		this.storeInConfirmBy = storeInConfirmBy;
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

	public Date getStoreInConfirmTime() {
		return storeInConfirmTime;
	}

	public void setStoreInConfirmTime(Date storeInConfirmTime) {
		this.storeInConfirmTime = storeInConfirmTime;
	}
}
