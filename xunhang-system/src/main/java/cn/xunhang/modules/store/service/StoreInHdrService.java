package cn.xunhang.modules.store.service;


import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.StoreInHdr;
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
public interface StoreInHdrService extends InfoBaseService<StoreInHdr,StoreInHdrDao> {

    List<Map> queryStoreInWaitListByCondition(String storeName, String name, String code, String storeInName, String status, String kind, int current, int offset);

    List<Map> queryStoreInConfirmListByCondition(String storeName, String storeInNo, String storeInName, String status, String kind, int current, int offset);

    void submitByIds(List<String> formNo);

//    void confirmSave(List<String> storeInId, List<Map> dlt);

}
