package cn.xunhang.modules.business.entity;

import cn.xunhang.modules.business.entity.infoBase.InfoBaseDO;
import java.util.Date;

/**
 * 订单收款
 */
public class OrderReceipts extends InfoBaseDO<OrderReceipts> {

    /**
     * 订单号
     */
    private String formno;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 收款账单编号
     */
    private String receiptNumber;
    /**
     * 收款账号名称
     */
    private String receiptAccount;
    /**
     * 收款金额
     */
    private Float amount;
    /**
     * 确认收款人
     */
    private String receiptor;
    /**
     * 收款时间
     */
    private Date receiptDate;
    /**
     * 收款类型
     */
    private String receiptType;
    /**
     * 收款凭证(关联文件id)
     */
    private String receiptVoucher;
    /**
     * 支付银行
     */
    private String payBank;
    /**
     * 支付账号
     */
    private String payAccount;

    public String getFormno() {
        return formno;
    }

    public void setFormno(String formno) {
        this.formno = formno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptAccount() {
        return receiptAccount;
    }

    public void setReceiptAccount(String receiptAccount) {
        this.receiptAccount = receiptAccount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getReceiptor() {
        return receiptor;
    }

    public void setReceiptor(String receiptor) {
        this.receiptor = receiptor;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptVoucher() {
        return receiptVoucher;
    }

    public void setReceiptVoucher(String receiptVoucher) {
        this.receiptVoucher = receiptVoucher;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }
}
