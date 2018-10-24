package cn.xunhang.common.base;

import cn.xunhang.common.exception.RRException;
import cn.xunhang.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dly on 2016/8/12.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getId();
    }

    protected String getDeptId() {
        return getUser().getDeptId();
    }

    @ExceptionHandler
    public @ResponseBody Response runtimeExceptionHandler(Exception ex) throws Exception{
        // 只过滤定义异常
        if(ex instanceof BusinessException) {
            logger.error("BaseController BusinessException异常>>>>>>>>>>>>>>>>>>>"+((BusinessException)ex).getMsg());
            return new Response((BusinessException)ex);
        }
        if(ex instanceof RRException) {
            logger.error("BaseController RRException>>>>>>>>>>>>>>>>>>>"+((RRException)ex).getMsg());
            return new Response((RRException)ex);
        }
        //过滤权限异常
        if(ex instanceof UnauthorizedException) {
            logger.error("没有权限>>>>>"+ex.getMessage());
            return new Response(100,"没有权限");
        }
        ex.printStackTrace();
        return new Response(500,ex.getClass().toString() +" : "+ex.getMessage());
    }


}
