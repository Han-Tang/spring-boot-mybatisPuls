package cn.xunhang.system.service.impl;

import cn.xunhang.system.dao.SysMenuDao;
import cn.xunhang.system.dao.SysRoleMenuDao;
import cn.xunhang.system.entity.SysMenu;
import cn.xunhang.system.entity.SysRoleMenu;
import cn.xunhang.system.service.SysRoleMenuService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	@Transactional
	public void saveOrUpdate(String roleId, List<String> menuIdList) {
		//先删除角色与菜单关系
		sysRoleMenuDao.deleteByRoleId(roleId);

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		map.put("menuIdList", menuIdList);
		sysRoleMenuDao.save(map);
	}

	@Override
	public List<String> queryMenuIdList(String roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}

	@Override
	public List<String> removeParentId(List<String> menuIdList) {
		List<String> ids = new ArrayList<String>();
		for (String id : menuIdList){
			List<SysMenu> menus = sysMenuDao.selectList(new EntityWrapper<SysMenu>().eq("parentId",id));
			if(menus.size()==0 && !id.equals("-1")){
				ids.add(id);
			}
		}
		return ids;
	}
}
