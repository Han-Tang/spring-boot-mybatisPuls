package cn.xunhang.modules.business.service.impl;

import cn.xunhang.modules.business.dao.OrderHdrDao;
import cn.xunhang.modules.business.entity.*;
import cn.xunhang.modules.business.service.OrderHdrService;
import cn.xunhang.modules.business.service.infoBase.impl.InfoBaseServiceImpl;
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
public class OrderHdrServiceImpl extends InfoBaseServiceImpl<OrderHdr,OrderHdrDao> implements OrderHdrService {

//    @Autowired
//    private OrderHdrDao orderHdrDao;
//    @Autowired
//    private OrderDtlDao orderDtlDao;
//    @Autowired
//    private SequenceService sequenceService;
//    @Autowired
//    private OrderHistoryDao orderHistoryDao;
//    @Autowired
//    private FileService fileService;
//
//    @Override
//    @Transactional
//    public void insertInfo(Param<OrderHdr, OrderDtl> param) {
//        String formno = sequenceService.getOrderNumber("DO");
//        param.order.setFormno(formno);
//        for(OrderDtl orderDtl : param.list){
//            orderDtl.setFormno(formno);
//            orderDtlDao.insert(orderDtl);
//        }
//        int i = orderHdrDao.insert(param.order);
//        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增订单失败，请重新操作");
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("code",param.order.getpFormno());
//        params.put("purpose","fileInfoQuotation");
//        List<InfoFile> files = fileService.queryList(params);
//        for(InfoFile fi : files){
//            InfoFile file = new InfoFile();
//            file.setPurpose("fileInfoOrder");
//            file.setCode(formno);
//            file.setFileType(fi.getFileType());
//            file.setName(fi.getName());
//            file.setLocation(fi.getLocation());
//            fileService.insertInfo(file);
//        }
//        createHistory(param.order.getFormno(),param.order.getStatus(),param.order.getStatus(),param.order.getStatus());
//    }
//
//    @Override
//    @Transactional
//    public void updateInfo(Param<OrderHdr, OrderDtl> param) {
//
//    }
//
//    @Override
//    @Transactional
//    public void deleteInfo(Map<String, Object> params) {
//        String formno = (String)params.get("formno");
//        OrderHdr document = orderHdrDao.selectById(formno);
//        document.setDeleted(true);
//        orderHdrDao.updateById(document);
//
//        List<OrderDtl> dtls = orderDtlDao.selectList(new EntityWrapper<OrderDtl>().eq("formno",formno));
//        for(OrderDtl dtl : dtls){
//            dtl.setDeleted(true);
//            orderDtlDao.updateById(dtl);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void createOrder(Param<QuotationHdr, QuotationDtl> param) {
//        System.out.println(">>>>>>>>>>>>>>>>由报价单生成订单");
//        Param<OrderHdr,OrderDtl> createOrder = new Param<OrderHdr,OrderDtl>();
//        OrderHdr order = new OrderHdr();
//        order.setpFormno(param.order.getFormno());
//        order.setOrderType(param.order.getOrderType());
//        order.setCustomerCode(param.order.getCustomerCode());
//        order.setCustomerName(param.order.getCustomerName());
//        order.setRegion(param.order.getRegion());
//        order.setSalesman(param.order.getSalesman());
//        order.setUrgent(param.order.getUrgent());
//        order.setOrderedDate(param.order.getOrderedDate());
//        order.setDeliveryDate(param.order.getDeliveryDate());
//        order.setStatus(OrderStatus.计划排产.toString());
//        order.setStatusDate(new Date());
//        order.setAmount(param.order.getAmount());
//        order.setDiscountSuggest(param.order.getDiscountSuggest());
//
//        List<OrderDtl> list = new ArrayList<OrderDtl>();
//        for (QuotationDtl dtl : param.list){
//            OrderDtl orderDtl = new OrderDtl();
//            orderDtl.setProductName(dtl.getProductName());
//            orderDtl.setProductCode(dtl.getProductCode());
//            orderDtl.setMainColor(dtl.getMainColor());
//            orderDtl.setViceColor(dtl.getViceColor());
//            orderDtl.setMainMaterial(dtl.getMainMaterial());
//            orderDtl.setViceMaterial(dtl.getViceMaterial());
//            orderDtl.setQty(dtl.getQty());
//            orderDtl.setPrice(dtl.getPrice());
//            orderDtl.setPriceMethod(dtl.getPriceMethod());
//            orderDtl.setAmount(dtl.getAmount());
//            list.add(orderDtl);
//        }
//        createOrder.order = order;
//        createOrder.list = list;
//        insertInfo(createOrder);
//    }
//
//    @Override
//    @Transactional
//    public void processReturn(Map<String, Object> params) {
//        OrderHdr orderHdr = orderHdrDao.selectById((String)params.get("formno"));
//        String status = orderHdr.getStatus();
//        orderHdr.setStatus((String) params.get("newStatus"));
//        orderHdr.setDescription((String) params.get("remark"));
//        orderHdrDao.updateById(orderHdr);
//        createHistory(orderHdr.getFormno(),status,orderHdr.getStatus(),"订单退回");
//    }
//
//    @Override
//    public Param<OrderHdr, OrderDtl> printing(OrderHdr document) {
//        Param<OrderHdr, OrderDtl> param = new Param<OrderHdr, OrderDtl>();
//        OrderHdr orderHdr = orderHdrDao.selectById(document);
//        List<OrderDtl> list = orderDtlDao.selectList(new EntityWrapper<OrderDtl>()
//                .eq("delete","0")
//                .eq("formno",orderHdr.getFormno()));
//        param.order = orderHdr;
//        param.list = list;
//        return param;
//    }
//
//    private void createHistory(String formno,String status,String newStatus,String remark){
//        OrderHistory orderHistory = new OrderHistory();
//        orderHistory.setFormno(formno);
//        orderHistory.setOrderType("order");
//        orderHistory.setStatus(status);
//        orderHistory.setNewStatus(newStatus);
//        orderHistory.setDescription(remark);
//        int j = orderHistoryDao.insert(orderHistory);
//        if(j==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"订单历史记录新增失败");
//    }
}
