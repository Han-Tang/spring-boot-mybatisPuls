package cn.xunhang.modules.store.service;

import cn.xunhang.modules.store.dao.PickMaterialHdrDao;
import cn.xunhang.modules.store.entity.PickMaterialHdr;
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
 * @since 2018-10-21
 */
public interface PickMaterialHdrService extends InfoBaseService<PickMaterialHdr,PickMaterialHdrDao> {

    List<PickMaterialHdr> queryStoreInWaitListByCondition(String formNo, String name, String code, String kind, int current, int offset);

    void submitByIds(List<String> ids);

    List<PickMaterialHdr> queryConfirmListByCondition(String formNo, String code, String name, String kind, int current, int offset);

    void confirmSave(List<String> formNo);

}
