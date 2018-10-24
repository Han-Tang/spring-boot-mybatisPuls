package cn.xunhang.modules.store.dao;

import cn.xunhang.modules.store.baseMapper.SuperMapper;
import cn.xunhang.modules.store.entity.PickMaterialHdr;
import cn.xunhang.modules.store.entity.StoreInHdr;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zzc
 * @since 2018-10-21
 */
public interface PickMaterialHdrDao extends SuperMapper<PickMaterialHdr> {

    List<PickMaterialHdr> queryListByCondition(@Param(value = "formNo") String formNo
            , @Param(value = "name") String name
            , @Param(value = "code") String code
            , @Param(value = "kind") String kind
    );

    List<PickMaterialHdr> queryConfirmListByCondition(@Param(value = "formNo") String formNo
            , @Param(value = "name") String name
            , @Param(value = "code") String code
            , @Param(value = "kind") String kind
    );


}