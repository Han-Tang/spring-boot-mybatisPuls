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
 * @since 2018-10-21
 */

@TableName("PickMaterialHdr")
public class PickMaterialHdr extends SuperEntity<PickMaterialHdr> {


    /**
     * 进仓单号
     */
    @TableId
	private String formNo;
    /**
     * 进仓日期
     */
	private Date pickTime;

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
	private Date confirmTime;
    /**
     * 产品类型
     */
	private String kind;
	private String comfirmBy;
	private String pickBy;


	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public Date getPickTime() {
		return pickTime;
	}

	public void setPickTime(Date pickTime) {
		this.pickTime = pickTime;
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

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getComfirmBy() {
		return comfirmBy;
	}

	public void setComfirmBy(String comfirmBy) {
		this.comfirmBy = comfirmBy;
	}

	public String getPickBy() {
		return pickBy;
	}

	public void setPickBy(String pickBy) {
		this.pickBy = pickBy;
	}
}
