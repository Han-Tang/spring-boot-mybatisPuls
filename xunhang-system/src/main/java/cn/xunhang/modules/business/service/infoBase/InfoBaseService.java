package cn.xunhang.modules.business.service.infoBase;


import cn.xunhang.common.base.BaseDO;
import cn.xunhang.common.base.BaseService;
import cn.xunhang.modules.business.baseMapper.SuperMapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tyj
 * @date 2018-09-18 17:56
 */
public interface InfoBaseService<T1 extends BaseDO<T1>, T2 extends SuperMapper<T1>> extends BaseService<T1,T2> {


}