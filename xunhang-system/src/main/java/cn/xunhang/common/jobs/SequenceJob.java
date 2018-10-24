package cn.xunhang.common.jobs;

import cn.xunhang.common.enums.OrderHeaderEnum;
import cn.xunhang.modules.basicmanage.service.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 序列号重置定时任务
 */
@Component
@EnableScheduling
public class SequenceJob {

    private static final Logger logger = LoggerFactory.getLogger(SequenceJob.class);

    @Autowired
    SequenceService sequenceService;
//	@Value("${spring.jobs.sequence.cron}")
//	private static final String TEST_CORE = "0/10 * * * * ? ";

    /*每天0点启动定时任务*/
    @Scheduled(cron = "0 0 0 * * ?")
    public void testTask() {
        logger.info(String.format("每日序列号重置工作开始于: %s", new Date()));
        sequenceService.resetSequence("QO");//报价单序列号
        sequenceService.resetSequence("DO");//订单序列号
        sequenceService.resetSequence(OrderHeaderEnum.StoreOutHeaderType.getValue());//出仓单序列号
        sequenceService.resetSequence(OrderHeaderEnum.DeliveryHeaderType.getValue());//发货单序列号
        logger.info(String.format("每日序列号重置作业结束于: %s", new Date()));
    }

//	/*每100秒执行一次*/
//    @Scheduled(cron = TEST_CORE)
//    public void timerRate() {
//    	System.out.println("》》》》》》》定时每十秒启动一次。。。2");
//    }
//
//    /*第一次10秒后执行，当执行完后2秒再执行*/
//    @Scheduled(initialDelay = 100000, fixedDelay = 20000)
//    public void timerInit() {
//    	System.out.println("》》》》》》》定时每十秒启动一次。。。3");
//    }


}
