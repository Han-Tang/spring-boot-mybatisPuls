package cn.xunhang.modules.business.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.OrderStatus;
import cn.xunhang.modules.business.dao.*;
import cn.xunhang.modules.business.entity.*;
import cn.xunhang.modules.business.service.OrderService;
import cn.xunhang.modules.business.service.QuotationService;
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
public class QuotationServiceImpl extends InfoBaseBillServiceImpl<QuotationHdr,QuotationHdrDao,QuotationDtl,QuotationDtlDao> implements QuotationService {


    @Autowired
    private QuotationHdrDao quotationHdrDao;
    @Autowired
    private QuotationDtlDao quotationDtlDao;
    @Autowired
    private OrderService orderService;


    @Override
    @Transactional
    public void deleteInfo(Map<String, Object> params) {
        String formno = (String)params.get("formno");
        QuotationHdr document = quotationHdrDao.selectById(formno);
        document.setDeleted(true);
        int i = quotationHdrDao.updateById(document);
        if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"删除报价单失败，请重新操作");
        List<QuotationDtl> dtls = quotationDtlDao.selectList(new EntityWrapper<QuotationDtl>().eq("formno",formno));
        for(QuotationDtl dtl : dtls){
            dtl.setDeleted(true);
            int j = quotationDtlDao.updateById(dtl);
            if(j==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"删除报价单明细失败，请重新操作");
        }
    }

    @Override
    @Transactional
    public QuotationHdr examine(QuotationHdr document){
        String formno = document.getFormno();
        QuotationHdr quotationHdr = quotationHdrDao.selectById(formno);
        quotationHdr.setOldStatus(quotationHdr.getStatus());
        if(!quotationHdr.getStatus().equals("计划排产")){
            if(quotationHdr.getStatus().equals("业务收款")){
                List<QuotationDtl> dtls = quotationDtlDao.selectList(new EntityWrapper<QuotationDtl>()
                        .eq("formno",formno).eq("deleted",0));

                Param<QuotationHdr,QuotationDtl> param = new Param<QuotationHdr,QuotationDtl>();
                param.order = quotationHdr;
                param.list = dtls;
                orderService.createOrder(param);
            }
            if(quotationHdr.getOrderType().equals("库存订单")){
                switch (quotationHdr.getStatus()){
                    case "新建订单":
                        quotationHdr.setStatus(OrderStatus.订单提交.toString());
                        break;
                    case "订单提交":
                        quotationHdr.setStatus(OrderStatus.业务收款.toString());
                        break;
                    case "业务收款":
                        quotationHdr.setStatus(OrderStatus.计划排产.toString());
                        break;
                }
            }else {
                switch (quotationHdr.getStatus()){
                    case "新建订单":
                        quotationHdr.setStatus(OrderStatus.订单提交.toString());
                        break;
                    case "订单提交":
                        quotationHdr.setStatus(OrderStatus.产品设计.toString());
                        break;
                    case "产品设计":
                        quotationHdr.setStatus(OrderStatus.产品报价.toString());
                        break;
                    case "产品报价":
                        quotationHdr.setStatus(OrderStatus.业务收款.toString());
                        break;
                    case "业务收款":
                        quotationHdr.setStatus(OrderStatus.计划排产.toString());
                        break;
                }
            }
            quotationHdr.setStatusDate(new Date());
            int i = quotationHdrDao.updateById(quotationHdr);
            if(i==0) throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"报价单提交失败，请重新操作");
        }
        return quotationHdr;
    }

    @Override
    @Transactional
    public QuotationHdr processReturn(Map<String, Object> params) {
        QuotationHdr quotationHdr = quotationHdrDao.selectById((String)params.get("formno"));
        quotationHdr.setOldStatus(quotationHdr.getStatus());
        quotationHdr.setStatus((String) params.get("newStatus"));
        quotationHdr.setDescription((String) params.get("remark"));
        quotationHdrDao.updateById(quotationHdr);
        return quotationHdr;
    }

    @Override
    public Param<QuotationHdr, QuotationDtl> printing(QuotationHdr document) {
        Param<QuotationHdr, QuotationDtl> param = new Param<QuotationHdr, QuotationDtl>();
        QuotationHdr quotationHdr = quotationHdrDao.selectById(document);
        List<QuotationDtl> list = quotationDtlDao.selectList(new EntityWrapper<QuotationDtl>()
                .eq("deleted","0")
                .eq("formno",quotationHdr.getFormno()));
        param.order = quotationHdr;
        param.list = list;
        return param;
    }

    @Override
    public void verifyForm(Param<QuotationHdr, QuotationDtl> param) {
        Set set = new HashSet();
        for(QuotationDtl dtl : param.list){
            set.add(dtl.getProductCode());
        }
        if(set.size() != param.list.size()){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"产品重复，请重新录入");
        }
        if(param.order.getCustomerCode()==null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"客户编码不能为空");
        }
    }

}
