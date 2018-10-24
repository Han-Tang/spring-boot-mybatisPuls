package cn.xunhang.modules.store.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.modules.store.dao.StoreOutDltDao;
import cn.xunhang.modules.store.entity.PickMaterialDlt;
import cn.xunhang.modules.store.entity.StoreOutDlt;
import cn.xunhang.modules.store.entity.StoreOutHdr;
import cn.xunhang.modules.store.dao.StoreOutHdrDao;
import cn.xunhang.modules.store.service.StoreOutHdrService;
import cn.xunhang.modules.store.service.infoBase.impl.InfoBaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-09-20
 */
@Service
public class StoreOutHdrServiceImpl extends InfoBaseServiceImpl<StoreOutHdr, StoreOutHdrDao> implements StoreOutHdrService {


    @Autowired
    private StoreOutHdrDao storeOutHdrDao;
    @Autowired
    private StoreOutDltDao storeOutDltDao;

    @Override
    @Transactional
    public void submitByIds(List<String> formNo) {

        for (String fn : formNo) {
            StoreOutHdr storeOutHdr = storeOutHdrDao.selectById(fn);
            if (storeOutHdr == null) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据出错");
            }
            if(!StoreReviewStatusEnum.NewType.getValue().equals(storeOutHdr.getStatus())){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "审核状态不对");
            }
            storeOutHdr.setStatus(StoreReviewStatusEnum.ConfirmType.getValue());
            int result = storeOutHdrDao.updateById(storeOutHdr);
            if (result == 0) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "提交失败");
            }

            //对明细操作
            List<StoreOutDlt> storeOutDltList = storeOutDltDao.selectList(new EntityWrapper<StoreOutDlt>().eq("formNo", storeOutHdr.getFormNo()));
            for (StoreOutDlt storeOutDlt : storeOutDltList) {
                storeOutDlt.setLeave(storeOutDlt.getQty());
                storeOutDltDao.updateById(storeOutDlt);

                //运算器
            }

        }
    }
}
