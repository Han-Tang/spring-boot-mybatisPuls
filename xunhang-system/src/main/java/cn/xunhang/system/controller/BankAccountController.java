package cn.xunhang.system.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.BankAccount;
import cn.xunhang.system.entity.SysCompany;
import cn.xunhang.system.service.BankAccountService;
import cn.xunhang.system.service.SysCompanyService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 针对具体编号生成的树结构 前端控制器
 * </p>
 *
 * @author tyj
 * @since 2018-09-13
 */
@RestController
@RequestMapping("/bankAccount")
public class BankAccountController extends InfoBaseController {
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private SysCompanyService companyService;

    /**
     * 查询银行账号列表
     */
//    @RequiresPermissions("sys:bankAccount:list")
    @RequestMapping("/list")
    public Response list(@RequestBody Map<String, Object> params){
        //列表查询
        EntityWrapper<BankAccount> entityWrapper = new EntityWrapper<BankAccount>();
        entityWrapper.eq("deleted",0).orderBy("createDate",false);
        entityWrapper.eq(StringUtils.isNotBlank((String)params.get("code")),"code",params.get("code"));

        List<BankAccount> list = bankAccountService.queryList(entityWrapper);
        return new ObjectResponse<List>(list);
    }

    /**
     * 新增保存银行账号
     */
//    @RequiresPermissions("sys:bankAccount:save")
    @PostMapping("/save")
    public Response save(@RequestBody BankAccount document) {
        SysCompany company = companyService.selectOne(new EntityWrapper<SysCompany>().eq("code",document.getCode()));
        document.setName(company.getName());
        bankAccountService.insertInfo(document);
        return new Response("新增保存银行账号成功");
    }

    /**
     * 更新修改银行账号
     */
//    @RequiresPermissions("sys:bankAccount:update")
    @RequestMapping("/update")
    public Response update(@RequestBody BankAccount document) {
        bankAccountService.updateInfo(document);
        return new Response("更新修改银行账号成功");
    }

    /**
     * 删除银行账号
     */
//    @RequiresPermissions("sys:bankAccount:delete")
    @PostMapping("/delete")
    public Response delete(@RequestBody BankAccount document) {
        bankAccountService.deleteById(document);
        return new Response("删除银行账号成功");
    }
}
