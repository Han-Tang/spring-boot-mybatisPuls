package cn.xunhang.system.dao;

import cn.xunhang.system.baseMapper.SuperMapper;
import cn.xunhang.system.entity.SysLog;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 系统日志 Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysLogDao extends SuperMapper<SysLog> {

	/**
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * </p>
     *
     * @param page
     *            翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @param map
     *            状态
     * @return
     */
    List<SysLog> selectPageList(Pagination page, Map<String, Object> map);
    
}