package cn.xunhang.common.base;


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

/**
 * 演示实体父类
 */
public class SuperEntity<T extends Model> extends Model<T> {


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
        if(StringUtils.isBlank(id)){
            id = UUID.randomUUID().toString();
        }
        return id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


//    public String getCreateBy() {
//        if(StringUtils.isBlank(createBy)){
//            createBy = ShiroUtils.getUserId();
//        }
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    public String getUpdateBy() {
//        if(StringUtils.isBlank(updateBy)){
//            updateBy = ShiroUtils.getUserId();
//        }
//        return updateBy;
//    }
//
//    public void setUpdateBy(String updateBy) {
//        this.updateBy = updateBy;
//    }
//
//    public Date getCreateDate() {
//        if(createDate==null){
//            createDate = new Date();
//        }
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getUpdateDate() {
//        if(updateDate==null){
//            updateDate = new Date();
//        }
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }

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
