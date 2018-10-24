package cn.xunhang.common.enums;

public enum StoreReviewStatusEnum {

    InitType("InitType","0"), //初始状态
    NewType("NewType","1"), //新建状态
    WaitConfirmType("WaitConfirmType","2"),  //待确认状态
    ConfirmType("ConfirmType","3")  //已确认状态
    ;

    public String code;
    public String value;

    private StoreReviewStatusEnum(String code, String value) {
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
