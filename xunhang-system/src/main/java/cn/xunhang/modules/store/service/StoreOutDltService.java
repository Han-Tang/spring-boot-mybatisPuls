package cn.xunhang.modules.store.service;

import cn.xunhang.modules.store.dao.StoreOutDltDao;
import cn.xunhang.modules.store.entity.StoreOutDlt;
import cn.xunhang.modules.store.service.infoBase.InfoBaseService;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzc
 * @since 2018-09-20
 */
public interface StoreOutDltService extends InfoBaseService<StoreOutDlt,StoreOutDltDao> {

    Page<StoreOutDlt> queryListByCondition(String deliveryNo, String saleNo, int current, int offset);

    void save(String date, String description, List<Map> mapList, String fFormNo, String sFormNo);


    void edit(String storeOutId, String date, String description, List<Map> mapList);
}
