package cn.xunhang.modules.store.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.OrderHeaderEnum;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.common.utils.SequenceCodeUtils;
import cn.xunhang.modules.store.dao.PickMaterialHdrDao;
import cn.xunhang.modules.store.entity.*;
import cn.xunhang.modules.store.dao.PickMaterialDltDao;
import cn.xunhang.modules.store.service.PickMaterialDltService;
import cn.xunhang.modules.store.service.infoBase.impl.InfoBaseServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-10-21
 */
@Service
public class PickMaterialDltServiceImpl extends InfoBaseServiceImpl<PickMaterialDlt,PickMaterialDltDao> implements  PickMaterialDltService {

    @Autowired
    private PickMaterialHdrDao pickMaterialHdrDao;

    @Autowired
    private PickMaterialDltDao pickMaterialDltDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void save(String description, List<Map> mapList, String kind) {

        PickMaterialHdr pickMaterialHdr = new PickMaterialHdr();

        pickMaterialHdr.setDescription(description);
        pickMaterialHdr.setStatus(StoreReviewStatusEnum.NewType.getValue());
        pickMaterialHdr.setKind(kind);

        String formNo = SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.PickMaterialHeaderType.getValue(),1);
        pickMaterialHdr.setFormNo(formNo);
        Integer result = pickMaterialHdrDao.insert(pickMaterialHdr);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }

        for (Map map : mapList){

            RepertoryDlt repertoryDlt =  JSON.parseObject(JSON.toJSONString(map), RepertoryDlt.class);
            if(repertoryDlt.getQty()>repertoryDlt.getLeave()){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"领料数量不能多于剩余数量");
            }

            PickMaterialDlt pickMaterialDlt = new PickMaterialDlt();

            pickMaterialDlt.setName(repertoryDlt.getName());
            pickMaterialDlt.setFormNo(formNo);

            String sn = SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.SnHeaderType.getValue(),1);
            pickMaterialDlt.setSn(sn);
            pickMaterialDlt.setCode(repertoryDlt.getCode());
            pickMaterialDlt.setgCode(repertoryDlt.getgCode());
            pickMaterialDlt.setQty(repertoryDlt.getQty());
            pickMaterialDlt.setType(repertoryDlt.getType());
            pickMaterialDlt.setFormat(repertoryDlt.getFormat());
            pickMaterialDlt.setMeterial1(repertoryDlt.getMeterial1());
            pickMaterialDlt.setColor1(repertoryDlt.getColor1());
            pickMaterialDlt.setUnit(repertoryDlt.getUnit());
            pickMaterialDlt.setKind(kind);
            pickMaterialDlt.setStoreId(repertoryDlt.getStoreId());

            this.insertInfo(pickMaterialDlt);
        }

    }

    @Override
    @Transactional
    public void edit(String formNo, String description, List<Map> mapList) {

        PickMaterialHdr pickMaterialHdr = pickMaterialHdrDao.selectById(formNo);
        if(pickMaterialHdr == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据不存在");
        }
        pickMaterialHdr.setDescription(description);
        pickMaterialHdr.setUpdateDate(new Date());
        Integer result = pickMaterialHdrDao.updateById(pickMaterialHdr);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }

        for (Map map : mapList){

            PickMaterialDlt entity = (PickMaterialDlt)JSON.parseObject(JSON.toJSONString(map), PickMaterialDlt.class);
//            if(entity.getQty() <= 0){
//                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"领取数量不能少于0");
//            }
            this.updateInfo(entity);
        }

    }
}
