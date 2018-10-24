package cn.xunhang.modules.store.service;

import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.service.infoBase.InfoBaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
public interface StoreInDltService extends InfoBaseService<StoreInDlt,StoreInDltDao> {

    void save(String date, List<Map> mapList, String kind);

    void edit(String storeInId, String date, List<Map> mapList);

}
