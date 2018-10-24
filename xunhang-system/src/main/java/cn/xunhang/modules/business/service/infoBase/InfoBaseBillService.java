package cn.xunhang.modules.business.service.infoBase;


import cn.xunhang.common.base.BaseBillService;
import cn.xunhang.common.base.BaseDO;
import cn.xunhang.modules.business.baseMapper.SuperMapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tyj
 * @date 2018-09-18 17:56
 */
public interface InfoBaseBillService<T1 extends BaseDO<T1>, M1 extends SuperMapper<T1>,T2 extends BaseDO<T2>, M2 extends SuperMapper<T2>> extends BaseBillService<T1,M1,T2,M2> {


}