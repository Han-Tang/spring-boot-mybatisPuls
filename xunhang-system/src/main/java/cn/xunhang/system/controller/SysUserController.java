package cn.xunhang.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.xunhang.common.annotation.Log;
import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.common.excel.ExcelTemplate;
import cn.xunhang.common.excel.ExcelUtil;
import cn.xunhang.common.validator.Assert;
import cn.xunhang.common.validator.ValidatorUtils;
import cn.xunhang.common.validator.group.AddGroup;
import cn.xunhang.common.validator.group.UpdateGroup;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.SysUser;
import cn.xunhang.system.service.SysDeptService;
import cn.xunhang.system.service.SysUserRoleService;
import cn.xunhang.system.service.SysUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 系统用户
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends InfoBaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 用户列表分页
	 */
	@RequiresPermissions("sys:user:list")
	@RequestMapping("/list")
	public Response list(@RequestBody Map<String, Object> params){
		//查询条件
		boolean deptId = false;
		List<String> deptIds = new ArrayList<String>();
		if(StringUtils.isNotBlank((String)params.get("deptId"))&&!params.get("deptId").equals("-1")){
			deptId = true;
			deptIds = sysDeptService.queryDetpIdList((String)params.get("deptId"));
			deptIds.add((String)params.get("deptId"));
		}
		Page<SysUser> pg = new Page<SysUser>((int) params.get("current"),(int) params.get("offset"));
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<SysUser>();
		entityWrapper.eq("deleted",0).in(deptId,"deptId",deptIds).orderBy("createDate",false);

		Page<SysUser> pageInfo = sysUserService.queryPage(pg,entityWrapper);
		return new ObjectResponse<Page>(pageInfo);
	}

	/**
	 * 用户列表
	 */
	@RequestMapping("/users")
	public Response users(@RequestBody Map<String, Object> params){
		//查询条件
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<SysUser>();
		entityWrapper.eq("deleted",0).orderBy("createDate",false);

		List<SysUser> list = sysUserService.queryList(entityWrapper);
		return new ObjectResponse<List>(list);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@PostMapping("/info")
	public Response info(@RequestBody Map params){
		String id = (String)params.get("id");
		if(StringUtils.isBlank(id)){
			id = getUserId();
		}
		SysUser user = sysUserService.selectById(id);

		//查询用户对应的角色
		List<String> roleIdList = sysUserRoleService.queryRoleIdList(id);
		user.setRoleIdList(roleIdList);

		return new ObjectResponse<SysUser>(user);
	}
	
	/**
	 * 修改登录用户密码
	 */
	@Log("修改密码")
	@RequestMapping("/password")
	public Response password(@RequestBody Map params){
		String password = (String) params.get("password");
		String newPassword = (String) params.get("newPassword");

		Assert.isBlank(newPassword, "新密码不为能空");

		//sha256加密
		password = new Sha256Hash(password, getUser().getSalt()).toHex();
		//sha256加密
		newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();
		
		SysUser user = new SysUser();
		user.setId(getUserId());
		user.setPassword(newPassword);
		//更新密码
		boolean bFlag = sysUserService.updateById(user);
		if(!bFlag){
			return new Response(1,"原密码不正确");
		}
		
		return new Response("修改密码成功");
	}
	
	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("sys:user:save")
	@Log("保存用户")
	@RequestMapping("/save")
	public Response save(@RequestBody SysUser user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);

		sysUserService.insertInfo(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
		return new Response("新增保存用户成功");
	}
	
	/**
	 * 编辑修改用户
	 */
	@RequiresPermissions("sys:user:update")
	@Log("修改用户")
	@RequestMapping("/update")
	public Response update(@RequestBody SysUser user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.updateInfo(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
		return new Response("编辑修改用户成功");
	}
	
	/**
	 * 批量删除用户
	 */
	@RequiresPermissions("sys:user:delete")
	@Log("删除用户")
	@RequestMapping("/batchRemove")
	public Response delete(@RequestBody String[] userIds){
		if(ArrayUtils.contains(userIds, "1")){
			return new Response(1,"系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, getUserId())){
			return new Response(1,"当前用户不能删除");
		}
		sysUserService.deleteBatchIds(Arrays.asList(userIds));
		return new Response("删除用户成功");
	}
	
	/**
	 * 导出用户
	 * @throws IOException 
	 */
	@Log("导出用户")
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response) throws IOException{

		//查询条件
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<SysUser>();
		entityWrapper.eq("deleted",0).orderBy("createDate",false);

		List<SysUser> userList = sysUserService.queryList(entityWrapper);

		OutputStream os = response.getOutputStream();
        
		Map<String, String> map = new HashMap<String, String>();
        map.put("title", "用户信息表");
        map.put("total", userList.size()+" 条");
        map.put("date", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm"));
        
        //响应信息，弹出文件下载窗口
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",  "attachment; filename="+ URLEncoder.encode("用户信息表.xls", "UTF-8"));  

        ExcelTemplate et = ExcelUtil.getInstance().handlerExcel("web-info-template.xls", userList, SysUser.class, true);
        et.replaceFinalData(map);
        et.wirteToStream(os);
        os.flush();
        os.close();
	}
}
