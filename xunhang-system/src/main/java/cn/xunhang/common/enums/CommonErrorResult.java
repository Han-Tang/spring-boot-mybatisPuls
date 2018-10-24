package cn.xunhang.common.enums;

/**
 * Created by dly on 2016/7/29.
 */
public enum CommonErrorResult {
    BUSINESS_ERROR(100),//前端拿到可以直接弹出
    SECRET_FAIL(500);   //前端拿到不应该直接弹出
//    REEA(200),
//    WECHAT_ERROR(400),   //当error为400时，去完善个人信息
//    VERIFY_FAIL(600);  //验证身份失败


    CommonErrorResult(Integer code){
        this.code=code;
    }
    
    Integer code;

    public Integer getCode() {
        return code;
    }


}
