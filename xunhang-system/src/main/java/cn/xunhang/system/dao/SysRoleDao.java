package cn.xunhang.system.dao;

import cn.xunhang.system.baseMapper.SuperMapper;
import cn.xunhang.system.entity.SysRole;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 角色 Mapper 接口
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysRoleDao extends SuperMapper<SysRole> {

	List<SysRole> queryPageList(Page<SysRole> page, Map<String, Object> map);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int deleteBatch(Object[] id);

}