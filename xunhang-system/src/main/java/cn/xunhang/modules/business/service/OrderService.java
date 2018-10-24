package cn.xunhang.modules.business.service;

import cn.xunhang.modules.business.dao.OrderDtlDao;
import cn.xunhang.modules.business.dao.OrderHdrDao;
import cn.xunhang.modules.business.entity.OrderDtl;
import cn.xunhang.modules.business.entity.OrderHdr;
import cn.xunhang.modules.business.entity.QuotationDtl;
import cn.xunhang.modules.business.entity.QuotationHdr;
import cn.xunhang.modules.business.service.infoBase.InfoBaseBillService;
import cn.xunhang.vo.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
public interface OrderService extends InfoBaseBillService<OrderHdr,OrderHdrDao,OrderDtl,OrderDtlDao> {

    void deleteInfo(Map<String, Object> params);

    /**
     * 根据报价单创建订单
     */
    void createOrder(Param<QuotationHdr, QuotationDtl> param);

    /**
     * 流程退回
     */
    OrderHdr processReturn(Map<String, Object> params);

    /**
     * 打印
     */
    Param<OrderHdr,OrderDtl> printing(OrderHdr document);

}
