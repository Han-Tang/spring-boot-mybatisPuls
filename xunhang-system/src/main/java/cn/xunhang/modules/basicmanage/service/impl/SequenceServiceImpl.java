package cn.xunhang.modules.basicmanage.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.xunhang.modules.basicmanage.entity.Sequence;
import cn.xunhang.modules.basicmanage.dao.SequenceDao;
import cn.xunhang.modules.basicmanage.service.SequenceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@Service
public class SequenceServiceImpl extends ServiceImpl<SequenceDao, Sequence> implements SequenceService {

    private final static int DEFAULT_STARTING_OFFSET = 0;//默认的起始偏移
    private Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);
    @Autowired
    SequenceDao sequenceMapper;

    /**
     * 获取序列号
     */
    @Override
    @Transactional
    public synchronized String getNextNumber(String identifier) {
        Sequence sequence = sequenceMapper.selectById(identifier);
        int current = sequence.getCurrentNumber() + 1;
        sequence.setCurrentNumber(current);
        sequence.setLastReset(sequence.getLastReset());
        sequence.setLastUpdated(new Date());
        sequenceMapper.updateById(sequence);
        String number = getSequenceNumberString(sequence);
        logger.info(">>>>>>>>>>>>>>获取序列号字符串:" + number);
        return number;
    }

    /**
     * 定时任务中重置序列号
     */
    @Override
    @Transactional
    public synchronized void resetSequence(String identifier) {
        logger.info(">>>>>>>>>>>>>>重置序列号: " + identifier);
        Sequence sequence = sequenceMapper.selectById(identifier);
        sequence.setCurrentNumber(DEFAULT_STARTING_OFFSET);
        sequence.setLastReset(new Date());
        sequenceMapper.updateById(sequence);
    }

    /**
     * 根据序号标识生成单号  例：DO201808010001
     */
    @Override
    public String getOrderNumber(String identifier) {
        String orderNumber = String.format("%s%s%s", identifier, DateUtil.format(new Date(),"yyyyMMdd"), getNextNumber(identifier));
        return orderNumber;
    }

    /**
     * 检索当前序列号的字符串。
     */
    private String getSequenceNumberString(Sequence sequence) {
        Integer num = sequence.getCurrentNumber();
        if (sequence.getLength() != null || sequence.getLength() != 0) {
            return String.format("%0" + sequence.getLength().toString() + "d", num);
        } else {
            return num.toString();
        }
    }

}
