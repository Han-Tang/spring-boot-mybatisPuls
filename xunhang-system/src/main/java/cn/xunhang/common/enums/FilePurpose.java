package cn.xunhang.common.enums;


import cn.xunhang.modules.basicmanage.entity.Customer;
import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.business.entity.OrderHdr;
import cn.xunhang.modules.business.entity.QuotationHdr;

/**
 * Created by dly on 2016/8/5.
 */
public enum FilePurpose {

    FIEL_INFO_PRODUCT("fileInfoProduct",InfoProduct.class),//产品资料附件
    FIEL_INFO_ORDER("fileInfoOrder",OrderHdr.class),//订单附件
    FIEL_INFO_QUOTATION("fileInfoQuotation",QuotationHdr.class),//报价单附件
    FIEL_INFO_CUSTOMER("fileInfoCustomer",Customer.class),//客户附件
    FIEL_INFO_COMPANY("fileInfoCompany",Customer.class);//公司附件



    FilePurpose(String code,Class belongToClass){
        this.code = code;
        this.belongToClass = belongToClass;
    }

    String code;
    Class belongToClass;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Class getBelongToClass() {
        return belongToClass;
    }

    public void setBelongToClass(Class belongToClass) {
        this.belongToClass = belongToClass;
    }

    public static FilePurpose getByCode(String code){
        for(FilePurpose filePurpose: FilePurpose.values()){
            if (filePurpose.getCode().equals(code)){
                return filePurpose;
            }
        }
        return null;
    }
}
