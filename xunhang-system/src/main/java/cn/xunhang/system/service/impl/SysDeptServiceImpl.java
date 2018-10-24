package cn.xunhang.system.service.impl;

import cn.xunhang.common.base.Tree;
import cn.xunhang.common.utils.BuildTree;
import cn.xunhang.system.dao.SysDeptDao;
import cn.xunhang.system.entity.SysDept;
import cn.xunhang.system.service.SysDeptService;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@Service
public class SysDeptServiceImpl extends InfoBaseServiceImpl<SysDept,SysDeptDao> implements SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;


	@Override
	public List<Tree<SysDept>> queryList(Map<String, Object> params) {
		List<SysDept> SysDepts = sysDeptDao.queryList(params);
		List<Tree<SysDept>> trees = getAllTree(SysDepts);
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<SysDept>> list = BuildTree.buildList(trees, "0");
		return list;
	}

	@Override
	public Tree<SysDept> getDeptTree() {
		List<SysDept> SysDepts = sysDeptDao.queryList(new HashMap<>());
		List<Tree<SysDept>> trees = getAllTree(SysDepts);
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<SysDept> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<String> queryDetpIdList(String parentId) {
		return sysDeptDao.queryDetpIdList(parentId);
	}

	/**
	 * 获取所有部门列表树
	 */
	private List<Tree<SysDept>> getAllTree(List<SysDept> list){
		List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
		for (SysDept sysOrgTree : list) {
			Tree<SysDept> tree = new Tree<SysDept>();
			tree.setId(sysOrgTree.getId());
			tree.setParentId(sysOrgTree.getParentId());
			tree.setText(sysOrgTree.getName());
			Map<String, Object> state = new HashMap<>();
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		return trees;
	}

	
}
