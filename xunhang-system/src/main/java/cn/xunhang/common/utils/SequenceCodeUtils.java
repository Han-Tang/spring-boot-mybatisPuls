package cn.xunhang.common.utils;

import cn.hutool.core.date.DateUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SequenceCodeUtils {

    public static final String SequenceCode = "Sequence";

    public static String genSeqCode(RedisTemplate redisTemplate,String prefix,int expireDays){

        String orderNumber = null;
        try {
            Long lnc =  redisTemplate.opsForValue().increment(SequenceCode+prefix,1);  //举例 如: SequenceFS
            if(lnc == 1){
                redisTemplate.expire(SequenceCode, expireDays, TimeUnit.DAYS);
            }
            String strForm = String.format("%5d", lnc).replace(" ", "0");
            orderNumber = String.format("%s%s%s", prefix, DateUtil.format(new Date(),"yyyyMMdd"),strForm);
        }
        catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return  orderNumber;
    }





}
