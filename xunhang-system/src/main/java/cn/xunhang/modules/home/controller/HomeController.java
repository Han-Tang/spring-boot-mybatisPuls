package cn.xunhang.modules.home.controller;

import cn.hutool.core.date.DateUtil;
import cn.xunhang.common.base.BaseController;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.modules.business.entity.OrderHdr;
import cn.xunhang.modules.business.service.OrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 首页查询
     * @return
     * 待处理订单
     * 交期提醒
     * 逾期订单提醒
     * 变更订单
     */
    @PostMapping("/index")
    public Response index(@RequestParam Map params){
        Map data = new HashMap();

        Map map1 = new HashMap();
        map1.put("status","计划排产");
        Page statistics1 = order(map1).getData();

        Map map2 = new HashMap();
        map2.put("statistics2","true");
        Page statistics2 = order(map2).getData();

        Map map3 = new HashMap();
        map3.put("statistics3","true");
        Page statistics3 = order(map3).getData();

        data.put("statistics1",statistics1);
        data.put("statistics2",statistics2);
        data.put("statistics3",statistics3);
        return new ObjectResponse<Map>(data);
    }

    /**
     * 看板
     * @param params
     * @return
     */
    @PostMapping("/board")
    public Response board(@RequestParam Map params){
        Map data = new HashMap();

        return new ObjectResponse<Map>(data);
    }


    private ObjectResponse<Page> order(Map params){
        Page<OrderHdr> pg = new Page<OrderHdr>(1 ,5);
        EntityWrapper<OrderHdr> entityWrapper = new EntityWrapper<OrderHdr>();
        entityWrapper.eq("deleted",0);
        entityWrapper.eq(StringUtils.isNotBlank((String)params.get("status")),"status",params.get("status"));
        entityWrapper.orderBy("createDate",false);
        if(params.get("statistics2")!=null && params.get("statistics2").toString()=="true"){
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, 7);

            entityWrapper.ge("deliveryDate",DateUtil.format(new Date(),"yyyy-MM-dd"));
            entityWrapper.le("deliveryDate",DateUtil.format(c.getTime(),"yyyy-MM-dd"));
        }
        if(params.get("statistics3")!=null && params.get("statistics3").toString()=="true"){
            entityWrapper.lt("deliveryDate",DateUtil.format(new Date(),"yyyy-MM-dd"));
        }

        Page<OrderHdr> pageInfo = orderService.queryPage(pg,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }



}
