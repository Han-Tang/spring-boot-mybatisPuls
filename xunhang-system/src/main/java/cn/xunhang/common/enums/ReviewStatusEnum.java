package cn.xunhang.common.enums;

public enum ReviewStatusEnum {

    WAIT_STATUS("WaitStatus","0"), //待审核
    PASS_STATUS("PassStatus","1")//已审核
    ;

    public String code;
    public String value;

    private ReviewStatusEnum(String code, String value) {
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
