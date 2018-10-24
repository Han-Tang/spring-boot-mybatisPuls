package cn.xunhang.modules.business.service;

import cn.xunhang.modules.business.dao.OrderHistoryDao;
import cn.xunhang.modules.business.entity.OrderHistory;
import cn.xunhang.modules.business.service.infoBase.InfoBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public interface OrderHistoryService extends InfoBaseService<OrderHistory,OrderHistoryDao> {

    /**
     * 添加历史记录
     */
    void createHistory(String orderType,String formno,String oldStatus,String newStatus,String remark);
}
