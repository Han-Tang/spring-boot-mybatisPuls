package cn.xunhang.modules.business.entity;

import cn.xunhang.modules.business.entity.infoBase.InfoBaseDO;

/**
 * 历史记录
 */
public class OrderHistory extends InfoBaseDO<OrderHistory> {

    /**
     * 单号
     */
    private String formno;
    /**
     * 单型 order-订单，quotation-报价单
     */
    private String orderType;
    /**
     * 改变前状态
     */
    private String status;
    /**
     * 状态编码（前）
     */
    private String statusCode;

    /**
     * 改变后状态
     */
    private String newStatus;
    /**
     * 状态编码（后）
     */
    private String newStatusCode;

    public String getFormno() {
        return formno;
    }

    public void setFormno(String formno) {
        this.formno = formno;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getNewStatusCode() {
        return newStatusCode;
    }

    public void setNewStatusCode(String newStatusCode) {
        this.newStatusCode = newStatusCode;
    }
}
