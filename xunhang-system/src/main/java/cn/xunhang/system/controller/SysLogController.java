package cn.xunhang.system.controller;

import cn.xunhang.common.base.ObjectResponse;
import cn.xunhang.common.base.Response;
import cn.xunhang.system.controller.infoBase.InfoBaseController;
import cn.xunhang.system.entity.SysLog;
import cn.xunhang.system.service.SysLogService;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 系统日志
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017-03-08 10:40:56
 */
@RestController
@RequestMapping("/log")
public class SysLogController extends InfoBaseController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public Response list(@RequestBody Map<String, Object> params){
		Page<SysLog> pageInfo = sysLogService.queryPageList(params);
		return new ObjectResponse<>(pageInfo);
	}
	
}
