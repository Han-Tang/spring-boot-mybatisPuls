package cn.xunhang.system.service.infoBase.impl;


import cn.xunhang.common.base.BaseDO;
import cn.xunhang.common.base.impl.BaseServiceImpl;
import cn.xunhang.system.baseMapper.SuperMapper;
import cn.xunhang.system.service.infoBase.InfoBaseService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tyj
 * @date 2018-09-18 17:58
 */

public class InfoBaseServiceImpl<T1 extends BaseDO<T1>,  T2 extends SuperMapper<T1>> extends BaseServiceImpl<T1,T2> implements InfoBaseService<T1, T2> {


}
