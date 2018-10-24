package cn.xunhang.common.enums;

public enum OrderHeaderEnum {

    SnHeaderType("SnHeaderType","sn"), //进仓单号头部

    StoreInHeaderType("StoreInHeaderType","JS"), //进仓单号头部
    StoreOutHeaderType("StoreOutHeaderType","CS"), //出仓单号头部
    DeliveryHeaderType("DeliveryHeaderType","FS"), //发货单号头部
    PurchHeaderType("PurchHeaderType","PS"), //采购单号头部
    PickMaterialHeaderType("PickMaterialHeaderType","PM") //采购单号头部

//    BillHeaderType("BillHeaderType","QO"), //报价单序列号，每日重置
//    OrderHeaderType("OrderHeaderType","DO") //订单序列号，每日重置
    ;

    public String code;
    public String value;

    private OrderHeaderEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
