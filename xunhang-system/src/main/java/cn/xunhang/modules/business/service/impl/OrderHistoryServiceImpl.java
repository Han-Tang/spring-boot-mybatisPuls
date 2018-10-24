package cn.xunhang.modules.business.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.modules.business.dao.OrderHistoryDao;
import cn.xunhang.modules.business.entity.OrderHistory;
import cn.xunhang.modules.business.service.OrderHistoryService;
import cn.xunhang.modules.business.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@Service
public class OrderHistoryServiceImpl extends InfoBaseServiceImpl<OrderHistory,OrderHistoryDao> implements OrderHistoryService {

    @Autowired
    private OrderHistoryDao orderHistoryDao;

    @Override
    public void createHistory(String orderType,String formno,String oldStatus,String newStatus,String remark){
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setFormno(formno);
        orderHistory.setOrderType(orderType);
        orderHistory.setStatus(oldStatus);
        orderHistory.setNewStatus(newStatus);
        orderHistory.setDescription(remark);
        int i = orderHistoryDao.insert(orderHistory);
        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,orderType+":历史记录新增失败");
    }
}
