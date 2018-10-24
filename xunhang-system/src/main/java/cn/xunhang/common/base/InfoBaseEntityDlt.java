package cn.xunhang.common.base;

import com.baomidou.mybatisplus.activerecord.Model;

public class InfoBaseEntityDlt<T extends Model> extends SuperEntity<T> {


    /**
     * 单号
     */
    protected String formNo;
    /**
     * 序列号
     */
    protected String sn;
    /**
     * 编码
     */
    protected String code;
    protected Long qty;
    protected Long leave;
    protected Long sum;
    /**
     * 冲减标志
     */
    protected Boolean tag;

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getLeave() {
        return leave;
    }

    public void setLeave(Long leave) {
        this.leave = leave;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }


}
