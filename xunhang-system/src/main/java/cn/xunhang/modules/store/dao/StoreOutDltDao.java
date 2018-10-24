package cn.xunhang.modules.store.dao;

import cn.xunhang.modules.store.baseMapper.SuperMapper;
import cn.xunhang.modules.store.entity.StoreOutDlt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zzc
 * @since 2018-09-20
 */
public interface StoreOutDltDao extends SuperMapper<StoreOutDlt> {

    List<StoreOutDlt> queryListByCondition(@Param(value = "deliveryNo") String deliveryNo,
                                           @Param(value = "saleNo") String saleNo);


}