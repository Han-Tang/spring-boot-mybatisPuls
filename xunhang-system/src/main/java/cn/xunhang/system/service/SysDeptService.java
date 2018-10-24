package cn.xunhang.system.service;

import cn.xunhang.common.base.Tree;
import cn.xunhang.system.dao.SysDeptDao;
import cn.xunhang.system.entity.SysDept;
import cn.xunhang.system.service.infoBase.InfoBaseService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
public interface SysDeptService extends InfoBaseService<SysDept,SysDeptDao> {

	/**
	 * 查询部门列表
	 */
	List<Tree<SysDept>> queryList(Map<String, Object> map);

	/**
	 * 获取部门树
	 */
	Tree<SysDept> getDeptTree();
	
	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<String> queryDetpIdList(String parentId);


}
