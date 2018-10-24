package cn.xunhang.modules.store.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.OrderHeaderEnum;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.common.utils.SequenceCodeUtils;
import cn.xunhang.modules.basicmanage.service.SequenceService;
import cn.xunhang.modules.store.dao.StoreInDltDao;
import cn.xunhang.modules.store.dao.StoreInHdrDao;
import cn.xunhang.modules.store.entity.OtherSourceDlt;
import cn.xunhang.modules.store.entity.StoreInDlt;
import cn.xunhang.modules.store.entity.StoreInHdr;
import cn.xunhang.modules.store.service.StoreInDltService;
import cn.xunhang.modules.store.service.infoBase.impl.InfoBaseServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
@Service
public class StoreInDltServiceImpl extends InfoBaseServiceImpl<StoreInDlt,StoreInDltDao> implements StoreInDltService {

    @Autowired
    private StoreInHdrDao storeInHdrDao;
    @Autowired
    private StoreInDltDao storeInDltDao;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void save(String date, List<Map> mapList,String kind) {

        StoreInHdr storeInHdr = new StoreInHdr();

        storeInHdr.setStoreInTime(DateUtil.parse(date,"yyyy-MM-dd"));
        storeInHdr.setStatus(StoreReviewStatusEnum.NewType.getValue());
        storeInHdr.setKind(kind);

        String formNo = SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.StoreInHeaderType.getValue(),1);
        storeInHdr.setFormNo(formNo);
        Integer result = storeInHdrDao.insert(storeInHdr);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }

        for (Map map : mapList){

            OtherSourceDlt otherSourceDlt = JSON.parseObject(JSON.toJSONString(map), OtherSourceDlt.class);
            if(otherSourceDlt.getQty()>otherSourceDlt.getLeave()){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"入库数量不能多于剩余数量");
            }

            StoreInDlt storeInDlt = new StoreInDlt();
            storeInDlt.setName(otherSourceDlt.getName());
            storeInDlt.setFormNo(formNo);

            String sn = SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.SnHeaderType.getValue(),1);
            storeInDlt.setSn(sn);
            storeInDlt.setCode(otherSourceDlt.getCode());
            storeInDlt.setgCode(otherSourceDlt.getgCode());
            storeInDlt.setQty(otherSourceDlt.getQty());
            storeInDlt.setType(otherSourceDlt.getType());
            storeInDlt.setFormat(otherSourceDlt.getFormat());
            storeInDlt.setMeterial1(otherSourceDlt.getMeterial1());
            storeInDlt.setColor1(otherSourceDlt.getColor1());
            storeInDlt.setUnit(otherSourceDlt.getUnit());
            storeInDlt.setKind(kind);
            storeInDlt.setStoreId(otherSourceDlt.getStoreId());

            this.insertInfo(storeInDlt);

        }

    }

    @Override
    public void edit(String storeInId, String date, List<Map> mapList) {

        StoreInHdr store = storeInHdrDao.selectById(storeInId);
        if(store == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据不存在");
        }

        store.setStoreInTime(new Date(date));
        store.setUpdateDate(new Date());
        Integer result = storeInHdrDao.updateById(store);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }

        for (Map map : mapList){

            StoreInDlt entity = (StoreInDlt)JSON.parseObject(JSON.toJSONString(map), StoreInDlt.class);
//            if(entity.getQty()>entity.getLeave()){
//                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"入库数量不能多于剩余数量");
//            }
            this.updateInfo(entity);
        }

    }
}
