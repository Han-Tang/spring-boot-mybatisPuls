package cn.xunhang.common.base;

import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.exception.RRException;

import java.io.Serializable;

/**
 * dly
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public Response() {
        this.code = 0;
        this.msg = "操作成功";
    }

    public Response(String exMsg) {
        this.code = 0;
        this.msg = exMsg;
    }

    public Response(int code ,String exMsg) {
        this.code = code;
        this.msg = exMsg;
    }

    public Response(CommonErrorResult resultMessage, String exMessage) {
        this.code = resultMessage.getCode();
        this.msg = exMessage;
    }

    public Response(BusinessException businessException){
        this.code = businessException.getCode();
        this.msg = businessException.getMsg();
    }

    public Response(RRException ex){
        this.code = ex.getCode();
        this.msg = ex.getMsg();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
