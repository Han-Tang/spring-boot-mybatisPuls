package cn.xunhang.modules.basicmanage.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.UUID;

/**
 * <p>
 * 序列号
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public class Sequence extends Model<Sequence> {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
    /**
     * 序列号标识符 primary unique
     */
	@TableId
	private String identifier;
    /**
     * 序列号长度
     */
	private Integer length;
    /**
     * 序列号
     */
	private int currentNumber;
    /**
     * 重置时间
     */
	@DateTimeFormat
	private Date lastReset;
    /**
     * 更新时间
     */
	@DateTimeFormat
	private Date lastUpdated;
	/**
	 * 版本
	 */
	@Version
	private int ts;

	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		if(StringUtils.isBlank(id)){
			id = UUID.randomUUID().toString();
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	public Date getLastReset() {
		return lastReset;
	}

	public void setLastReset(Date lastReset) {
		this.lastReset = lastReset;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getTs() {
		return ts;
	}

	public void setTs(int ts) {
		this.ts = ts;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sequence{" +
			", id=" + id +
			", identifier=" + identifier +
			", length=" + length +
			", currentNumber=" + currentNumber +
			", lastReset=" + lastReset +
			", lastUpdated=" + lastUpdated +
			", ts=" + ts +
			"}";
	}

}
