package cn.xunhang.modules.store.dao;

import cn.xunhang.modules.store.baseMapper.SuperMapper;
import cn.xunhang.modules.store.entity.StoreInHdr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
public interface StoreInHdrDao extends SuperMapper<StoreInHdr> {

    List<StoreInHdr> queryListByCondition(@Param(value = "storeName") String storeName
            , @Param(value = "name") String name
            , @Param(value = "code") String code
            , @Param(value = "storeInName") String storeInName
            , @Param(value = "status") String status
            , @Param(value = "kind") String kind);

    List<StoreInHdr> queryListConfirmByCondition(@Param(value = "storeName") String storeName
            , @Param(value = "storeInNo") String storeInNo
            , @Param(value = "storeInName") String storeInName
            , @Param(value = "status") String status
            , @Param(value = "kind") String kind
    );
}