package cn.xunhang.modules.store.service;

import cn.xunhang.modules.store.dao.PickMaterialDltDao;
import cn.xunhang.modules.store.entity.PickMaterialDlt;
import cn.xunhang.modules.store.service.infoBase.InfoBaseService;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzc
 * @since 2018-10-21
 */
public interface PickMaterialDltService extends InfoBaseService<PickMaterialDlt,PickMaterialDltDao> {

    void save(String description, List<Map> mapList, String kind);

    void edit(String formNo, String description, List<Map> mapList);


}
