package cn.xunhang.modules.store.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.ReviewStatusEnum;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.modules.store.dao.PickMaterialDltDao;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.PickMaterialDlt;
import cn.xunhang.modules.store.entity.PickMaterialHdr;
import cn.xunhang.modules.store.dao.PickMaterialHdrDao;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreInHdr;
import cn.xunhang.modules.store.service.PickMaterialHdrService;
import cn.xunhang.modules.store.service.infoBase.impl.InfoBaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-10-21
 */
@Service
public class PickMaterialHdrServiceImpl extends InfoBaseServiceImpl<PickMaterialHdr, PickMaterialHdrDao> implements PickMaterialHdrService {

    @Autowired
    private PickMaterialHdrDao pickMaterialHdrDao;
    @Autowired
    private PickMaterialDltDao pickMaterialDltDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<PickMaterialHdr> queryStoreInWaitListByCondition(String formNo, String name, String code, String kind, int current, int offset) {


        List<PickMaterialHdr> pickMaterialHdrList;
        try {
            PageHelper.startPage(current, offset);
            pickMaterialHdrList = pickMaterialHdrDao.queryListByCondition(formNo, name, code, kind);
        } finally {
            PageHelper.remove();
        }

        if (pickMaterialHdrList.size() == 0) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "没有数据");
        }
        return pickMaterialHdrList;

    }

    @Override
    @Transactional
    public void submitByIds(List<String> id) {

        for (String formNo : id){
            PickMaterialHdr pickMaterialHdr = pickMaterialHdrDao.selectById(formNo);
            if (pickMaterialHdr == null) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据出错");
            }
            if (!StoreReviewStatusEnum.NewType.getValue().equals(pickMaterialHdr.getStatus())) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "此记录审核状态不对");
            }
            pickMaterialHdr.setStatus(StoreReviewStatusEnum.WaitConfirmType.getValue());
            int result = pickMaterialHdrDao.updateById(pickMaterialHdr);
            if (result == 0) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "提交失败");
            }

            //对明细操作
            List<PickMaterialDlt> pickMaterialDltList = pickMaterialDltDao.selectList(new EntityWrapper<PickMaterialDlt>().eq("formNo", pickMaterialHdr.getFormNo()));
            for (PickMaterialDlt pickMaterialDlt : pickMaterialDltList) {
                pickMaterialDlt.setLeave(pickMaterialDlt.getQty());
//                pickMaterialDlt.setQty(0L);
                pickMaterialDltDao.updateById(pickMaterialDlt);

                //运算器
            }
        }


    }

    @Override
    public List<PickMaterialHdr> queryConfirmListByCondition(String formNo, String code, String name, String kind, int current, int offset) {

        List<Map> mapList = new ArrayList<>();
        List<PickMaterialHdr> pickMaterialHdrList;
        try {
            PageHelper.startPage(current, offset);
            pickMaterialHdrList = pickMaterialHdrDao.queryConfirmListByCondition(formNo, code, name, kind);
        } finally {
            PageHelper.remove();
        }

        if (pickMaterialHdrList.size() == 0) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "没有数据");
        }

        return pickMaterialHdrList;
    }

    @Override
    @Transactional
    public void confirmSave(List<String> formNo) {

        List<PickMaterialHdr>pickMaterialHdrList = pickMaterialHdrDao.selectList(new EntityWrapper<PickMaterialHdr>().in("formNo",formNo));

        if(pickMaterialHdrList.size() == 0){
             throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据不存在");
        }
        for (PickMaterialHdr pickMaterialHdr : pickMaterialHdrList){
            pickMaterialHdr.setStatus(StoreReviewStatusEnum.ConfirmType.getValue());
            pickMaterialHdrDao.updateById(pickMaterialHdr);
        }
    }
}
