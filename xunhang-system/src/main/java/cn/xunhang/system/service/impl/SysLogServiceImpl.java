package cn.xunhang.system.service.impl;

import cn.xunhang.system.dao.SysLogDao;
import cn.xunhang.system.entity.SysLog;
import cn.xunhang.system.service.SysLogService;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysLogServiceImpl extends InfoBaseServiceImpl<SysLog,SysLogDao> implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public Page<SysLog> queryPageList(Map<String, Object> params) {
		Map page =(Map) params.get("page");
		Page<SysLog> pg = new Page<SysLog>((int) page.get("page"),(int) page.get("rows"));
		List<SysLog> list = sysLogDao.selectPage(pg, new EntityWrapper<SysLog>()
						.eq("deleted",0)
						.orderBy("createDate",false));
		pg.setRecords(list);
		return  pg;
	}
	
}
