package cn.xunhang.modules.business.controller;



import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.OrderStatus;
import cn.xunhang.common.enums.ReviewStatusEnum;
import cn.xunhang.modules.basicmanage.entity.Customer;
import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.basicmanage.service.CustomerService;
import cn.xunhang.modules.basicmanage.service.InfoProductService;
import cn.xunhang.modules.basicmanage.service.SequenceService;
import cn.xunhang.modules.business.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.business.entity.OrderHistory;
import cn.xunhang.modules.business.entity.QuotationDtl;
import cn.xunhang.modules.business.entity.QuotationHdr;
import cn.xunhang.modules.business.service.OrderHistoryService;
import cn.xunhang.modules.business.service.QuotationDtlService;
import cn.xunhang.modules.business.service.QuotationHdrService;
import cn.xunhang.modules.business.service.QuotationService;
import cn.xunhang.vo.Param;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@RestController
@RequestMapping("/quotation")
public class QuotationController extends InfoBaseController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuotationHdrService quotationHdrService;
    @Autowired
    private QuotationDtlService quotationDtlService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private InfoProductService productService;

    /**
     * 销售报价单查询
     * @author tyj 
     * @date 2018-08-13 11:01
     */
    @RequiresPermissions("business:quotation:list")
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {
        //查询条件
        Page<QuotationHdr> pg = new Page<QuotationHdr>((int) params.get("current"),(int) params.get("offset"));
        EntityWrapper<QuotationHdr> entityWrapper = new EntityWrapper<QuotationHdr>();
        entityWrapper.ne("deleted",1);
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("formno")),"formno",(String)params.get("formno"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("customerName")),"customerName",(String)params.get("customerName"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("region")),"region",(String)params.get("region"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("salesman")),"salesman",(String)params.get("salesman"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("status")),"status",(String)params.get("status"));
        entityWrapper.ge(StringUtils.isNotBlank((String)((List)params.get("deliveryDate")).get(0)),"deliveryDate",((List)params.get("deliveryDate")).get(0));
        entityWrapper.le(StringUtils.isNotBlank((String)((List)params.get("deliveryDate")).get(1)),"deliveryDate",((List)params.get("deliveryDate")).get(1));
        entityWrapper.ge(StringUtils.isNotBlank((String)((List)params.get("createDate")).get(0)),"createDate",((List)params.get("createDate")).get(0));
        entityWrapper.le(StringUtils.isNotBlank((String)((List)params.get("createDate")).get(1)),"createDate",((List)params.get("createDate")).get(1));
        entityWrapper.orderBy("createDate",false);

        Page<QuotationHdr> pageInfo = quotationService.queryPage(pg,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }


    /**
     * 销售报价单明细列表
     * @param params
     * @return
     */
    @RequiresPermissions("business:quotation:dtlList")
    @RequestMapping(value = "/dtlList", method = RequestMethod.POST)
    public Response list(@RequestBody Map<String, Object> params) {
        //查询条件
        EntityWrapper<QuotationDtl> entityWrapper = new EntityWrapper<QuotationDtl>();
        entityWrapper.ne("deleted",1).eq("formno",params.get("formno"));

        List<QuotationDtl> details = quotationService.queryDtlList(entityWrapper);
        return new ObjectResponse<List>(details);
    }

    /**
     * 新增销售报价单
     * @param param
     * @return
     */
    @RequiresPermissions("business:quotation:save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody Param<QuotationHdr,QuotationDtl> param) {
        quotationService.verifyForm(param);
        String formno = sequenceService.getOrderNumber("QO");
        Customer customer = customerService.selectById(param.order.getCustomerCode());
        param.order.setStatusDate(new Date());
        param.order.setFormno(formno);
        param.order.setRegion(customer.getRegion());
        param.order.setStatus(OrderStatus.新建订单.toString());
        Float amount = 0f;
        for(QuotationDtl orderDtl : param.list){
            if(orderDtl.getPrice()!=null && orderDtl.getQty()!=null)
                orderDtl.setAmount(orderDtl.getPrice()*orderDtl.getQty());
            if(orderDtl.getAmount()!=null) amount += orderDtl.getAmount();
            orderDtl.setFormno(formno);
        }
        param.order.setAmount(amount);
        quotationService.insertInfo(param);
        orderHistoryService.createHistory("quotation",param.order.getFormno(),param.order.getStatus(),param.order.getStatus(),param.order.getStatus());
        return new Response("新增销售报价单成功");
    }

    /**
     * 更新销售报价单
     * @param param
     * @return
     */
    @RequiresPermissions("business:quotation:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody Param<QuotationHdr,QuotationDtl> param) {
        quotationService.verifyForm(param);
        quotationService.deleteDtl(new EntityWrapper<QuotationDtl>().eq("formno",param.order.getFormno()));
        Customer customer = customerService.selectById(param.order.getCustomerCode());
        param.order.setRegion(customer.getRegion());
        Float amount = 0f;
        for(QuotationDtl orderDtl : param.list){
            if(orderDtl.getPrice()!=null && orderDtl.getQty()!=null)
                orderDtl.setAmount(orderDtl.getPrice()*orderDtl.getQty());
            if(orderDtl.getAmount()!=null) amount += orderDtl.getAmount();
            orderDtl.setFormno(param.order.getFormno());
        }
        param.order.setAmount(amount);
        quotationService.updateInfo(param);
        return new Response("更新销售报价单成功");
    }

    /**
     * 销售报价单删除
     * @param params
     * @return
     */
    @RequiresPermissions("business:quotation:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Map<String, Object> params) {
        quotationService.deleteInfo(params);
        return new Response("销售报价单删除成功");
    }


    /**
     * 报价单审核
     */
    @RequiresPermissions("business:quotation:examine")
    @RequestMapping(value = "/examine", method = RequestMethod.POST)
    public Response examine(@RequestBody QuotationHdr document) {
        QuotationHdr quotationHdr = quotationService.examine(document);
        orderHistoryService.createHistory(
                "quotation",
                quotationHdr.getFormno(),
                quotationHdr.getOldStatus(),
                quotationHdr.getStatus(),
                quotationHdr.getStatus());
        return new Response("报价单审核成功");
    }

    /**
     * 查询历史状态记录
     */
//    @RequiresPermissions("business:quotation:history")
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public Response history(@RequestBody QuotationHdr document){
        EntityWrapper<OrderHistory> entityWrapper = new EntityWrapper<OrderHistory>();
        entityWrapper.eq("formno",document.getFormno());
        entityWrapper.eq("deleted","0");
        entityWrapper.orderBy("createDate",true);
        List<OrderHistory> list = orderHistoryService.queryList(entityWrapper);
        return new ObjectResponse<List>(list);
    }

    /**
     * 流程退回
     */
    @RequestMapping(value = "/processReturn", method = RequestMethod.POST)
    public Response processReturn(@RequestBody Map<String, Object> params) {
        QuotationHdr quotationHdr = quotationService.processReturn(params);
        orderHistoryService.createHistory(
                "quotation",
                quotationHdr.getFormno(),
                quotationHdr.getOldStatus(),
                quotationHdr.getStatus(),
                "报价单退回");
        return new Response("流程退回成功");
    }


    /**
     * 获取订单状态
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public Response status(@RequestBody Map<String, Object> params){
        List list = new ArrayList();
        for(OrderStatus en : OrderStatus.values()){
            Map<String,String> map = new HashMap<>();
            map.put("value",en.name());
            map.put("label",en.name());
            list.add(map);
        }
        return new ObjectResponse<List>(list);
    }

    /**
     * 打印
     */
    @RequestMapping(value = "/printing", method = RequestMethod.POST)
    public Response printing(@RequestBody QuotationHdr document){
        Param<QuotationHdr,QuotationDtl> param = quotationService.printing(document);
        Customer customer = customerService.selectById(param.order.getCustomerCode());
        param.order.setShippingAddress(customer.getShippingAddress());
        param.order.setPhone(customer.getPhone());
        for(QuotationDtl dtl : param.list){
            Map map = new HashMap();
            map.put("code",dtl.getProductCode());
            List<InfoProduct> products = productService.selectByMap(map);
            dtl.setPaint(products.get(0).getPaint());
            dtl.setSerial(products.get(0).getSerial());
        }
        return new ObjectResponse<Param<QuotationHdr,QuotationDtl>>(param);
    }

    /**
     * 合同明细
     */
    @RequestMapping(value = "/contractDetails",method = RequestMethod.POST)
    public Response contractDetails(@RequestBody Map<String,Object> params){
        Map<String,Object> data = new HashMap<>();
        QuotationHdr quotationHdr = quotationService.selectByParams(new EntityWrapper<QuotationHdr>().eq("formno",params.get("formno")));
        List<QuotationDtl> dtls = quotationService.selectAllDtlByParams(new EntityWrapper<QuotationDtl>().eq("formno",params.get("formno")));
        data.put("quotationHdr",quotationHdr);
        data.put("quotationDtl",dtls);
        return new ObjectResponse<Map>(data);
    }

}
