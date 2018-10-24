package cn.xunhang.modules.basicmanage.entity;

import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;

/**
 * @author tyj
 * @date 2018-07-30 15:11
 * 客户联系人
 */
public class CustomerContact extends InfoBaseDO<CustomerContact> {
    /**
     * 客户编码
     */
    private String customerCode;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系方式1
     */
    private String phone;
    /**
     * 联系方式2
     */
    private String phone2;
    /**
     * 联系方式3
     */
    private String phone3;
    /**
     * 微信
     */
    private String weChat;
    /**
     * QQ
     */
    private String qq;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 职务
     */
    private String position;


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
