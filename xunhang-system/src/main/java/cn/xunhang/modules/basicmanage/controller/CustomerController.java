package cn.xunhang.modules.basicmanage.controller;


import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.modules.basicmanage.controller.infoBase.InfoBaseController;
import cn.xunhang.modules.basicmanage.entity.Customer;
import cn.xunhang.modules.basicmanage.entity.CustomerContact;
import cn.xunhang.modules.basicmanage.service.CustomerContactService;
import cn.xunhang.modules.basicmanage.service.CustomerService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/customer")
public class CustomerController extends InfoBaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerContactService customerContactService;


    /**
     * 客户列表查询分页
     * @author tyj
     * @date 2018-07-30 09:26
     * @param params
     * @return
     */
    @RequiresPermissions("basic:customer:list")
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    public Response queryByPage(@RequestBody Map<String, Object> params) {
        //查询条件
        Page<Customer> pg = new Page<Customer>((int) params.get("current"),(int) params.get("offset"));
        EntityWrapper<Customer> entityWrapper = new EntityWrapper<Customer>();
        entityWrapper.eq("deleted",0);
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("code")),"code",(String)params.get("code"));
        entityWrapper.like(StringUtils.isNotBlank((String)params.get("name")),"name",(String)params.get("name"));
        entityWrapper.eq(StringUtils.isNotBlank((String)params.get("type")),"type",params.get("type"));
        entityWrapper.orderBy("createDate",false);

        Page<Customer> pageInfo = customerService.queryPage(pg,entityWrapper);
        return new ObjectResponse<Page>(pageInfo);
    }

    /**
     * 新增客户保存
     * @param document
     * @return
     */
    @RequiresPermissions("basic:customer:save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody Customer document) {
        if(StringUtils.isBlank(document.getCode()) || StringUtils.isBlank(document.getName()))
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"客户编码和名称不能为空");

        customerService.insertInfo(document);
        return new Response("新增客户成功");
    }

    /**
     * 更新客户保存
     * @param document
     * @return
     */
    @RequiresPermissions("basic:customer:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody Customer document) {
        if(StringUtils.isBlank(document.getCode()) || StringUtils.isBlank(document.getName()))
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"客户编码和名称不能为空");

        customerService.updateInfo(document);
        return new Response("更新客户成功");
    }

    /**
     * 客户删除
     * @param document
     * @return
     */
    @RequiresPermissions("basic:customer:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody Customer document) {
        if(document.getCode() == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"要删除的客户不能为空");
        }else {
            Customer customer = customerService.selectById(document.getCode());
            List<CustomerContact> list = customerContactService.selectList(new EntityWrapper<CustomerContact>()
                    .eq("customerCode",customer.getCode()));
            for (CustomerContact contact : list){
                customerContactService.deleteByT(contact);
            }
            customerService.deleteById(document);
        }
        return new Response("客户删除成功");
    }

    /**
     * 查询客户联系人
     */
    @RequestMapping(value = "/dtlContact", method = RequestMethod.POST)
    public Response dtlContact(@RequestBody Map<String, Object> params) {
        //查询条件
        EntityWrapper<CustomerContact> entityWrapper = new EntityWrapper<CustomerContact>();
        entityWrapper.eq("deleted",0);
        entityWrapper.eq("customerCode",params.get("code"));
        entityWrapper.orderBy("createDate",false);

        List<CustomerContact> contacts = customerContactService.queryList(entityWrapper);
        return new ObjectResponse<List>(contacts);
    }

    /**
     * 新增客户联系人
     */
    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public Response saveContact(@RequestBody CustomerContact document) {
        customerContactService.insertInfo(document);
        return new Response("新增客户联系人成功");
    }

    /**
     * 更新客户联系人
     */
    @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
    @ResponseBody
    public Response updateContact(@RequestBody CustomerContact document) {
        customerContactService.updateInfo(document);
        return new Response("更新客户联系人成功");
    }

    /**
     * 删除客户联系人
     */
    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public Response deleteContact(@RequestBody CustomerContact document) {
        customerContactService.deleteById(document);
        return new Response("删除客户联系人成功");
    }

}
