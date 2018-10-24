package cn.xunhang.system.service;

import cn.xunhang.system.dao.SysLogDao;
import cn.xunhang.system.entity.SysLog;
import cn.xunhang.system.service.infoBase.InfoBaseService;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.Map;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysLogService extends InfoBaseService<SysLog,SysLogDao> {

	/**
	 * 日志分页列表
	 * @param params
	 * @return
	 */
	Page<SysLog> queryPageList(Map<String, Object> params);
}
