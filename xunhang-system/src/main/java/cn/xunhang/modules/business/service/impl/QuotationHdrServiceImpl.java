package cn.xunhang.modules.business.service.impl;

import cn.xunhang.modules.business.dao.QuotationHdrDao;
import cn.xunhang.modules.business.entity.QuotationHdr;
import cn.xunhang.modules.business.service.QuotationHdrService;
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
public class QuotationHdrServiceImpl extends InfoBaseServiceImpl<QuotationHdr,QuotationHdrDao> implements QuotationHdrService {
//    @Autowired
//    private QuotationHdrDao quotationHdrDao;
//    @Autowired
//    private QuotationDtlDao quotationDtlDao;
//    @Autowired
//    private SequenceService sequenceService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private OrderHistoryDao orderHistoryDao;
//
//    @Override
//    @Transactional
//    public void insertInfo(Param<QuotationHdr,QuotationDtl> param) {
//        Customer customer = customerService.selectById(param.order.getCustomerCode());
//        String formno = sequenceService.getOrderNumber("QO");
//        param.order.setFormno(formno);
//        param.order.setRegion(customer.getRegion());
//        param.order.setStatus(OrderStatus.新建订单.toString());
//        Float amount = 0f;
//        for(QuotationDtl orderDtl : param.list){
//            if(orderDtl.getAmount()!=null) amount += orderDtl.getAmount();
//            orderDtl.setFormno(formno);
//            int i = quotationDtlDao.insert(orderDtl);
//            if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增报价单明细失败，请重新操作");
//        }
//        param.order.setAmount(amount);
//        int i = quotationHdrDao.insert(param.order);
//        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增报价单失败，请重新操作");
//        createHistory(param.order.getFormno(),param.order.getStatus(),param.order.getStatus(),param.order.getStatus());
//    }
//
//    @Override
//    @Transactional
//    public void updateInfo(Param<QuotationHdr,QuotationDtl> param) {
//        Customer customer = customerService.selectById(param.order.getCustomerCode());
//        param.order.setRegion(customer.getRegion());
//        param.order.setUpdateBy(ShiroUtils.getUserId());
//        param.order.setUpdateName(ShiroUtils.getUser().getName());
//        param.order.setUpdateDate(new Date());
//
//        quotationDtlDao.delete(new EntityWrapper<QuotationDtl>().eq("formno",param.order.getFormno()));
//        Float amount = 0f;
//        for(QuotationDtl orderDtl : param.list){
//            if(orderDtl.getAmount()!=null) amount += orderDtl.getAmount();
//            orderDtl.setFormno(param.order.getFormno());
//            int i = quotationDtlDao.insert(orderDtl);
//            if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"编辑报价单明细失败，请重新操作");
//        }
//        param.order.setAmount(amount);
//        int i = quotationHdrDao.updateById(param.order);
//        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"编辑报价单失败，请重新操作");
//    }
//
//    @Override
//    @Transactional
//    public void deleteInfo(Map<String, Object> params) {
//        String formno = (String)params.get("formno");
//        QuotationHdr document = quotationHdrDao.selectById(formno);
//        document.setDeleted(true);
//        int i = quotationHdrDao.updateById(document);
//        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"删除报价单失败，请重新操作");
//        List<QuotationDtl> dtls = quotationDtlDao.selectList(new EntityWrapper<QuotationDtl>().eq("formno",formno));
//        for(QuotationDtl dtl : dtls){
//            dtl.setDeleted(true);
//            int j = quotationDtlDao.updateById(dtl);
//            if(j==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"删除报价单明细失败，请重新操作");
//        }
//    }
//
//    @Override
//    @Transactional
//    public void examine(QuotationHdr document){
//        String formno = document.getFormno();
//        QuotationHdr quotationHdr = quotationHdrDao.selectById(formno);
//        String status = quotationHdr.getStatus();
//        if(!quotationHdr.getStatus().equals("计划排产")){
//            if(quotationHdr.getStatus().equals("业务收款")){
//                List<QuotationDtl> dtls = quotationDtlDao.selectList(new EntityWrapper<QuotationDtl>()
//                        .eq("formno",formno).eq("deleted",0));
//
//                Param<QuotationHdr,QuotationDtl> param = new Param<QuotationHdr,QuotationDtl>();
//                param.order = quotationHdr;
//                param.list = dtls;
//                orderService.createOrder(param);
//            }
//            if(quotationHdr.getOrderType().equals("库存订单")){
//                switch (quotationHdr.getStatus()){
//                    case "新建订单":
//                        quotationHdr.setStatus(OrderStatus.订单提交.toString());
//                        break;
//                    case "订单提交":
//                        quotationHdr.setStatus(OrderStatus.业务收款.toString());
//                        break;
//                    case "业务收款":
//                        quotationHdr.setStatus(OrderStatus.计划排产.toString());
//                        break;
//                }
//            }else {
//                switch (quotationHdr.getStatus()){
//                    case "新建订单":
//                        quotationHdr.setStatus(OrderStatus.订单提交.toString());
//                        break;
//                    case "订单提交":
//                        quotationHdr.setStatus(OrderStatus.产品设计.toString());
//                        break;
//                    case "产品设计":
//                        quotationHdr.setStatus(OrderStatus.产品报价.toString());
//                        break;
//                    case "产品报价":
//                        quotationHdr.setStatus(OrderStatus.业务收款.toString());
//                        break;
//                    case "业务收款":
//                        quotationHdr.setStatus(OrderStatus.计划排产.toString());
//                        break;
//                }
//            }
//            createHistory(quotationHdr.getFormno(),status,quotationHdr.getStatus(),quotationHdr.getStatus());
//            quotationHdr.setStatusDate(new Date());
//            int i = quotationHdrDao.updateById(quotationHdr);
//            if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"报价单提交失败，请重新操作");
//        }
//
//    }
//
//    @Override
//    @Transactional
//    public void processReturn(Map<String, Object> params) {
//        QuotationHdr quotationHdr = quotationHdrDao.selectById((String)params.get("formno"));
//        String status = quotationHdr.getStatus();
//        quotationHdr.setStatus((String) params.get("newStatus"));
//        quotationHdr.setDescription((String) params.get("remark"));
//        quotationHdrDao.updateById(quotationHdr);
//        createHistory(quotationHdr.getFormno(),status,quotationHdr.getStatus(),"报价单退回");
//    }
//
//    @Override
//    public Param<QuotationHdr, QuotationDtl> printing(QuotationHdr document) {
//        Param<QuotationHdr, QuotationDtl> param = new Param<QuotationHdr, QuotationDtl>();
//        QuotationHdr quotationHdr = quotationHdrDao.selectById(document);
//        List<QuotationDtl> list = quotationDtlDao.selectList(new EntityWrapper<QuotationDtl>()
//                .eq("delete","0")
//                .eq("formno",quotationHdr.getFormno()));
//        param.order = quotationHdr;
//        param.list = list;
//        return param;
//    }
//
//    @Override
//    public void verifyForm(Param<QuotationHdr, QuotationDtl> param) {
//        Set set = new HashSet();
//        for(QuotationDtl dtl : param.list){
//            set.add(dtl.getProductCode());
//        }
//        if(set.size() != param.list.size()){
//            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"产品重复，请重新录入");
//        }
//    }
//
//    private void createHistory(String formno,String status,String newStatus,String remark){
//        OrderHistory orderHistory = new OrderHistory();
//        orderHistory.setFormno(formno);
//        orderHistory.setOrderType("quotation");
//        orderHistory.setStatus(status);
//        orderHistory.setNewStatus(newStatus);
//        orderHistory.setDescription(remark);
//        int i = orderHistoryDao.insert(orderHistory);
//        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"报价单历史记录新增失败");
//    }

}
