package cn.xunhang.modules.business.service.impl;

import cn.xunhang.common.enums.OrderStatus;
import cn.xunhang.common.enums.ReviewStatusEnum;
import cn.xunhang.modules.basicmanage.entity.InfoFile;
import cn.xunhang.modules.basicmanage.service.FileService;
import cn.xunhang.modules.basicmanage.service.SequenceService;
import cn.xunhang.modules.business.dao.OrderDtlDao;
import cn.xunhang.modules.business.dao.OrderHdrDao;
import cn.xunhang.modules.business.entity.*;
import cn.xunhang.modules.business.service.OrderService;
import cn.xunhang.modules.business.service.infoBase.impl.InfoBaseBillServiceImpl;
import cn.xunhang.vo.Param;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@Service
public class OrderServiceImpl extends InfoBaseBillServiceImpl<OrderHdr,OrderHdrDao,OrderDtl,OrderDtlDao> implements OrderService {
    @Autowired
    private OrderHdrDao orderHdrDao;
    @Autowired
    private OrderDtlDao orderDtlDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private SequenceService sequenceService;

    @Override
    @Transactional
    public void deleteInfo(Map<String, Object> params) {
        String formno = (String)params.get("formno");
        OrderHdr document = orderHdrDao.selectById(formno);
        document.setDeleted(true);
        orderHdrDao.updateById(document);

        List<OrderDtl> dtls = orderDtlDao.selectList(new EntityWrapper<OrderDtl>().eq("formno",formno));
        for(OrderDtl dtl : dtls){
            dtl.setDeleted(true);
            orderDtlDao.updateById(dtl);
        }
    }

    @Override
    @Transactional
    public void createOrder(Param<QuotationHdr, QuotationDtl> param) {
        System.out.println(">>>>>>>>>>>>>>>>由报价单生成订单");
        String formno = sequenceService.getOrderNumber("DO");
        Param<OrderHdr,OrderDtl> createOrder = new Param<OrderHdr,OrderDtl>();
        OrderHdr order = new OrderHdr();
        order.setFormno(formno);
        order.setpFormno(param.order.getFormno());
        order.setOrderType(param.order.getOrderType());
        order.setCustomerCode(param.order.getCustomerCode());
        order.setCustomerName(param.order.getCustomerName());
        order.setRegion(param.order.getRegion());
        order.setSalesman(param.order.getSalesman());
        order.setUrgent(param.order.getUrgent());
        order.setOrderedDate(param.order.getOrderedDate());
        order.setDeliveryDate(param.order.getDeliveryDate());
        order.setStatus(OrderStatus.计划排产.toString());
        order.setStatusDate(new Date());
        order.setAmount(param.order.getAmount());
        order.setDiscountSuggest(param.order.getDiscountSuggest());

        List<OrderDtl> list = new ArrayList<OrderDtl>();
        for (QuotationDtl dtl : param.list){
            OrderDtl orderDtl = new OrderDtl();
            orderDtl.setFormno(formno);
            orderDtl.setProductName(dtl.getProductName());
            orderDtl.setProductCode(dtl.getProductCode());
            orderDtl.setMainColor(dtl.getMainColor());
            orderDtl.setViceColor(dtl.getViceColor());
            orderDtl.setMainMaterial(dtl.getMainMaterial());
            orderDtl.setViceMaterial(dtl.getViceMaterial());
            orderDtl.setQty(dtl.getQty());
            orderDtl.setPrice(dtl.getPrice());
            orderDtl.setPriceMethod(dtl.getPriceMethod());
            orderDtl.setAmount(dtl.getAmount());
            list.add(orderDtl);
        }
        createOrder.order = order;
        createOrder.list = list;
        insertInfo(createOrder);


        Map<String,Object> params = new HashMap<>();
        params.put("code",order.getpFormno());
        params.put("purpose","fileInfoQuotation");
        List<InfoFile> files = fileService.queryList(params);
        for(InfoFile fi : files){
            InfoFile file = new InfoFile();
            file.setPurpose("fileInfoOrder");
            file.setCode(formno);
            file.setFileType(fi.getFileType());
            file.setName(fi.getName());
            file.setLocation(fi.getLocation());
            fileService.insertInfo(file);
        }
    }

    @Override
    @Transactional
    public OrderHdr processReturn(Map<String, Object> params) {
        OrderHdr orderHdr = orderHdrDao.selectById((String)params.get("formno"));
        String status = orderHdr.getStatus();
        orderHdr.setOldStatus(orderHdr.getStatus());
        orderHdr.setStatus((String) params.get("newStatus"));
        orderHdr.setDescription((String) params.get("remark"));
        orderHdrDao.updateById(orderHdr);
        return orderHdr;
    }

    @Override
    public Param<OrderHdr, OrderDtl> printing(OrderHdr document) {
        Param<OrderHdr, OrderDtl> param = new Param<OrderHdr, OrderDtl>();
        OrderHdr orderHdr = orderHdrDao.selectById(document);
        List<OrderDtl> list = orderDtlDao.selectList(new EntityWrapper<OrderDtl>()
                .eq("deleted","0")
                .eq("formno",orderHdr.getFormno()));
        param.order = orderHdr;
        param.list = list;
        return param;
    }

}
