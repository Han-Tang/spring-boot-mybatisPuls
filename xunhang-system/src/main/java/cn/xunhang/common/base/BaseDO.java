package cn.xunhang.common.base;

import cn.hutool.db.ds.pooled.DbConfig;
import cn.xunhang.common.utils.ShiroUtils;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public abstract class BaseDO<T extends Model> extends Model<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2574597014795888358L;

    /**
     * id
     */
    protected String id;

    /**
     * 备注
     */
    protected String description;

    /**
     * 可用
     */
    protected boolean active = true;

    /**
     * 删除 0-没有删除 1--删除
     */
    protected boolean deleted = false;

    /**
     * 创建人
     */
    @CreatedBy
    protected String createBy;
    /**
     * 创建人姓名
     */
    protected String createName;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    protected Date createDate;

    /**
     * 更新人
     */
    @LastModifiedBy
    protected String updateBy;
    /**
     * 更新人姓名
     */
    protected String updateName;
    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    protected Date updateDate;

    /**
     * 版本
     */
    @Version
    protected int ts;

    public String getId() {

        if (DebugConfig.ENTITY_DEBUG) {
            return id;
        }
        else {
            if (StringUtils.isBlank(id)) {
                id = UUID.randomUUID().toString();
            }
            return id;
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreateBy() {

        if(DebugConfig.ENTITY_DEBUG){
            return createBy;
        }
        else {
            if (StringUtils.isBlank(createBy)) {
                createBy = ShiroUtils.getUserId();
            }
            return createBy;
        }

    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    public String getCreateName() {

        if (DebugConfig.ENTITY_DEBUG) {
            return createName;
        } else {
            if (StringUtils.isBlank(createName)) {
                createName = ShiroUtils.getUser().getName();
            }
            return createName;
        }

    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {

        if(DebugConfig.ENTITY_DEBUG){
            return updateName;
        }
        else {
            if (StringUtils.isBlank(updateName)) {
                updateName = ShiroUtils.getUser().getName();
            }
            return updateName;
        }
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateBy() {

        if(DebugConfig.ENTITY_DEBUG){
            return updateBy;
        }
        else {
            if (StringUtils.isBlank(updateBy)) {
                updateBy = ShiroUtils.getUserId();
            }
            return updateBy;
        }

    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        if (createDate == null) {
            createDate = new Date();
        }
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        if (updateDate == null) {
            updateDate = new Date();
        }
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
