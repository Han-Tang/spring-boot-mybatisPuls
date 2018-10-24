package cn.xunhang.common.base;


import cn.xunhang.common.enums.CommonErrorResult;

public class BusinessException extends RuntimeException{
    private Integer code;
    private String msg;

    public BusinessException(CommonErrorResult errorResult, String msg){
        this.code = errorResult.getCode();
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
