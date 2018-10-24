package cn.xunhang.modules.basicmanage.service;


import cn.xunhang.common.base.Tree;
import cn.xunhang.modules.basicmanage.dao.InfoProductBomDao;
import cn.xunhang.modules.basicmanage.entity.InfoProductBom;
import cn.xunhang.modules.basicmanage.service.infoBase.InfoBaseService;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyj 
 * @date 2018-10-22 15:05
 */
public interface InfoProductBomService extends InfoBaseService<InfoProductBom,InfoProductBomDao> {

    /**
     * 导入bom
     * @param file
     */
    void importBom(File file,String orderNum);

    /**
     * 查询bom列表
     */
    List<Tree<InfoProductBom>> queryList(Map<String, Object> params);


}
