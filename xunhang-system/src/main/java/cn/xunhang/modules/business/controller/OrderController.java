package cn.xunhang.modules.business.controller;



import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.OrderStatus;
import cn.xunhang.modules.basicmanage.entity.Customer;
import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.basicmanage.service.CustomerService;
import cn.xunhang.modules.basicmanage.service.InfoProductService;
import cn.xunhang.modules.business.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.business.entity.*;
import cn.xunhang.modules.business.service.*;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@RestController
@RequestMapping("/order")
public class OrderController extends InfoBaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderReceiptService orderReceiptService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InfoProductService productService;


    /**
     * 销售报价单查询
     * @author tyj
     * @date 2018-08-13 11:01
     */
    @RequiresPermissions("business:order:list")
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    public Response queryList(@RequestBody Map<String, Object> params) {
        //查询条件
        Page<OrderHdr> pg = new Page<OrderHdr>((int) params.get("current"),(int) params.get("offset"));
        EntityWrapper<OrderHdr> entityWrapper = new EntityWrapper<OrderHdr>();
        entityWrapper.eq("deleted",0);
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("formno")),"formno",(String)params.get("formno"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("pFormno")),"pFormno",(String)params.get("pFormno"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("customerName")),"customerName",(String)params.get("customerName"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("region")),"region",(String)params.get("region"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("salesman")),"salesman",(String)params.get("salesman"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("status")),"status",(String)params.get("status"));
        entityWrapper.ge(StringUtils.isNotBlank((String)((List)params.get("deliveryDate")).get(0)),"deliveryDate",((List)params.get("deliveryDate")).get(0));
        entityWrapper.le(StringUtils.isNotBlank((String)((List)params.get("deliveryDate")).get(1)),"deliveryDate",((List)params.get("deliveryDate")).get(1));
        entityWrapper.ge(StringUtils.isNotBlank((String)((List)params.get("createDate")).get(0)),"createDate",((List)params.get("createDate")).get(0));
        entityWrapper.le(StringUtils.isNotBlank((String)((List)params.get("createDate")).get(1)),"createDate",((List)params.get("createDate")).get(1));
        entityWrapper.orderBy("createDate",false);

        Page<OrderHdr> pageInfo = orderService.queryPage(pg,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }

    /**
     * 销售订单详情列表
     * @param params
     * @return
     */
	@RequiresPermissions("business:order:dtlList")
    @RequestMapping(value = "/dtlList", method = RequestMethod.POST)
    public Response list(@RequestBody Map<String, Object> params) {
	    //查询条件
        EntityWrapper<OrderDtl> entityWrapper = new EntityWrapper<OrderDtl>();
        entityWrapper.eq("deleted",0);
        entityWrapper.eq("formno",params.get("formno"));

        List<OrderDtl> details = orderService.queryDtlList(entityWrapper);
        return new ObjectResponse<List>(details);
    }


    /**
     * 销售订单删除
     * @param params
     * @return
     */
    @RequiresPermissions("business:order:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Map<String, Object> params) {
        orderService.deleteInfo(params);
        return new Response("销售订单删除成功");
    }

    /**
     * 查询订单收款
     */
//    @RequiresPermissions("business:order:receipts")
    @RequestMapping(value = "/receipts", method = RequestMethod.POST)
    public Response receipts(@RequestBody Map<String, Object> params) {
        EntityWrapper<OrderReceipts> entityWrapper = new EntityWrapper<OrderReceipts>();
        entityWrapper.eq("deleted","0");
        entityWrapper.eq("formno",params.get("formno"));
        entityWrapper.orderBy("createDate",true);
        List<OrderReceipts> list = orderReceiptService.queryList(entityWrapper);
        return new ObjectResponse<List>(list);
    }
    /**
     * 新增订单收款
     */
//    @RequiresPermissions("business:order:receiptSave")
    @RequestMapping(value = "/receiptSave", method = RequestMethod.POST)
    public Response receiptSave(@RequestBody OrderReceipts document) {
        orderReceiptService.insertInfo(document);
        return new Response("新增订单收款成功");
    }
    /**
     * 编辑订单收款
     */
//    @RequiresPermissions("business:order:receiptSave")
    @RequestMapping(value = "/receiptUpdate", method = RequestMethod.POST)
    public Response receiptUpdate(@RequestBody OrderReceipts document) {
        orderReceiptService.updateInfo(document);
        return new Response("编辑订单收款成功");
    }
    /**
     * 删除订单收款
     */
//    @RequiresPermissions("business:order:receiptSave")
    @RequestMapping(value = "/receiptDelete", method = RequestMethod.POST)
    public Response receiptDelete(@RequestBody OrderReceipts document) {
        orderReceiptService.deleteById(document);
        return new Response("删除订单收款成功");
    }

    /**
     * 流程退回
     */
    @RequestMapping(value = "/processReturn", method = RequestMethod.POST)
    public Response processReturn(@RequestBody Map<String, Object> params){
        OrderHdr orderHdr = orderService.processReturn(params);
        orderHistoryService.createHistory("order",orderHdr.getFormno(),orderHdr.getOldStatus(),orderHdr.getStatus(),"订单退回");
        return new Response("流程退回成功");
    }

    /**
     * 查询历史记录
     * @param document
     * @return
     */
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public Response history(@RequestBody OrderHdr document){
        EntityWrapper<OrderHistory> entityWrapper = new EntityWrapper<OrderHistory>();
        entityWrapper.eq("deleted","0");
        entityWrapper.eq("formno",document.getFormno());
        entityWrapper.orderBy("createDate",true);
        List<OrderHistory> list = orderHistoryService.queryList(entityWrapper);
        return new ObjectResponse<List>(list);
    }

    /**
     * 打印
     */
    @RequestMapping(value = "/printing", method = RequestMethod.POST)
    public Response printing(@RequestBody OrderHdr document){
        Param<OrderHdr,OrderDtl> param = orderService.printing(document);

        Customer customer = customerService.selectById(param.order.getCustomerCode());
        param.order.setShippingAddress(customer.getShippingAddress());
        param.order.setPhone(customer.getPhone());
        for(OrderDtl dtl : param.list){
            Map map = new HashMap();
            map.put("code",dtl.getProductCode());
            List<InfoProduct> products = productService.selectByMap(map);
            if(products.size()>0){
                dtl.setSerial(products.get(0).getSerial());
                dtl.setPaint(products.get(0).getPaint());
            }
        }
        return new ObjectResponse<Param<OrderHdr,OrderDtl>>(param);
    }

    /**
     * 获取订单状态
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public Response status(@RequestBody Map<String, Object> params){
        List list = new ArrayList();
        for(OrderStatus en : OrderStatus.values()){
            Map<String,String> map = new HashMap<>();
            map.put("label",en.name());
            map.put("value",en.name());
            list.add(map);
        }
        return new ObjectResponse<List>(list);
    }

    /**
     * 合同明细
     */
    @RequestMapping(value = "/contractDetails",method = RequestMethod.POST)
    public Response contractDetails(@RequestBody Map<String,Object> params){
        Map<String,Object> data = new HashMap<>();
        OrderHdr quotationHdr = orderService.selectByParams(new EntityWrapper<OrderHdr>().eq("formno",params.get("formno")));
        List<OrderDtl> dtls = orderService.selectAllDtlByParams(new EntityWrapper<OrderDtl>().eq("formno",params.get("formno")));
        data.put("quotationHdr",quotationHdr);
        data.put("quotationDtl",dtls);
        return new ObjectResponse<Map>(data);
    }


}
