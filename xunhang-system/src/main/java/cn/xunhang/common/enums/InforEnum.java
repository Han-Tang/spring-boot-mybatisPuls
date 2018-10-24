package cn.xunhang.common.enums;

public enum InforEnum {

    CustomerType("CustomerType","客户类型"),
    CustomerClass("CustomerClass","客户分类"),
    OrderType("OrderType","订单类型"),
    REGION("Region","区域"),
    PayType("PayType","付款方式"),
    ReceiveType("ReceiveType","收款类型"),
    DeliveryType("DeliveryType","提货方式"),
    ProductNameType("ProductNameType","系列名称"),
    ProductColorType("ProductColorType","产品花色"),
    ProductColor2Type("ProductColor2Type","产品副花色"),
    ProductMaterialType("ProductMaterialType","产品材质"),
    ProductMaterial2Type("ProductMaterial2Type","产品副材质"),
    ProductModelType("ProductModelType","产品型号"),
    PaintType("PaintType","漆类"),
    ModelMakeType("ModelMakeType","造型"),
    UnitType("UnitType","单位"),
    SoftBagType("SoftBagType","软包类型"),
    ProductGradeType("ProductGradeType","产品等级"),
    PricingMethodType("PricingMethodType","计价方式"),
    ProductType("ProductType","产品类型"),
    MaterielType("MaterielType","物料类型");

    public String code;
    public String value;

    private InforEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getCodeByValue(String value) {
        String code = null;
        for(InforEnum inforTypeEnum : InforEnum.values()) {
            if(inforTypeEnum.value.equals(value)) {
                code = inforTypeEnum.code;
                break;
            }
        }
        return code;
    }

    public static String getValueByCode(String code) {
        String value = null;
        for(InforEnum inforTypeEnum : InforEnum.values()) {
            if(inforTypeEnum.code.equals(code)) {
                value = inforTypeEnum.value;
                break;
            }
        }
        return value;
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
