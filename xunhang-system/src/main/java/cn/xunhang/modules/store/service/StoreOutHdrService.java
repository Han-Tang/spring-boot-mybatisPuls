package cn.xunhang.modules.store.service;

import cn.xunhang.modules.store.dao.StoreOutHdrDao;
import cn.xunhang.modules.store.entity.StoreOutHdr;
import cn.xunhang.modules.store.service.infoBase.InfoBaseService;
import com.baomidou.mybatisplus.service.IService;

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
public interface StoreOutHdrService extends InfoBaseService<StoreOutHdr,StoreOutHdrDao> {

     void submitByIds(List<String> formNo);
}
