package cn.xunhang.modules.store.service.infoBase;


import cn.xunhang.common.base.SuperEntity;
import cn.xunhang.modules.store.baseMapper.SuperMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzc
 * @since 2018-09-03
 */
public interface InfoBaseService<T1 extends SuperEntity<T1>, T2 extends SuperMapper<T1>>  extends IService<T1> {

    String insertInfo(T1 t1);

    void updateInfo(T1 t1);

    Page<T1> queryList(Page<T1> page, EntityWrapper<T1> entityWrapper);

    T1 queryDetail(Map<String, Object> params);

    void deleteInfo(Map<String, Object> params);


}
