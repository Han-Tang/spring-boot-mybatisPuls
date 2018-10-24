package cn.xunhang.modules.basicmanage.service;

import cn.xunhang.modules.basicmanage.entity.Sequence;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public interface SequenceService extends IService<Sequence> {

    String getNextNumber(final String identifier);

    void resetSequence(final String identifier);

    /**
     * 根据序号标识生成单号
     */
    String getOrderNumber(final String identifier);
}
