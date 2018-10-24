package cn.xunhang.modules.store.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.OrderHeaderEnum;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.common.utils.SequenceCodeUtils;
import cn.xunhang.modules.basicmanage.service.SequenceService;
import cn.xunhang.modules.store.dao.StoreOutHdrDao;
import cn.xunhang.modules.store.entity.DeliveryDlt;
import cn.xunhang.modules.store.entity.StoreOutDlt;
import cn.xunhang.modules.store.dao.StoreOutDltDao;
import cn.xunhang.modules.store.entity.StoreOutHdr;
import cn.xunhang.modules.store.service.StoreOutDltService;
import cn.xunhang.modules.store.service.infoBase.impl.InfoBaseServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer;
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
 * 服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-09-20
 */
@Service
public class StoreOutDltServiceImpl extends InfoBaseServiceImpl<StoreOutDlt, StoreOutDltDao> implements StoreOutDltService {

    @Autowired
    private StoreOutHdrDao storeOutHdrDao;
    @Autowired
    private StoreOutDltDao storeOutDltDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Page<StoreOutDlt> queryListByCondition(String deliveryNo, String saleNo, int current, int offset) {
        Page<StoreOutDlt> page = new Page<>();
        try {
            PageHelper.startPage(current, offset);
            List<StoreOutDlt> storeOutDltList = storeOutDltDao.queryListByCondition(deliveryNo, saleNo);
            page.setRecords(storeOutDltList);
        }finally {
            PageHelper.remove();
        }
        return page;
    }

    @Override
    @Transactional
    public void save(String date, String description, List<Map> mapList,String fFormNo,String sFormNo) {

        StoreOutHdr storeOutHdr =  new StoreOutHdr();
        storeOutHdr.setDescription(description);
        String formNo = SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.StoreOutHeaderType.getValue(),1);
        storeOutHdr.setStoreOutDate(DateUtil.parse(date, "yyyy-MM-dd"));
        storeOutHdr.setStatus(StoreReviewStatusEnum.NewType.getValue());
        storeOutHdr.setFormNo(formNo);
        storeOutHdr.setsFormNo(sFormNo);
        storeOutHdr.setfFormNo(fFormNo);
        Integer result = storeOutHdrDao.insert(storeOutHdr);
        if (result == 0) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "操作不成功，请重新刷新页面");
        }

        for (Map map : mapList) {

            DeliveryDlt deliveryDlt = JSON.parseObject(JSON.toJSONString(map), DeliveryDlt.class);
            if (deliveryDlt.getQty() > deliveryDlt.getLeave()) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "本次发货数量不能多于剩余数量");
            }

            StoreOutDlt storeOutDlt = new StoreOutDlt();
            storeOutDlt.setName(deliveryDlt.getName());
            storeOutDlt.setCode(deliveryDlt.getCode());
            storeOutDlt.setMeterial1(deliveryDlt.getMeterial1());
            storeOutDlt.setColor1(deliveryDlt.getColor1());
            storeOutDlt.setStoreId(deliveryDlt.getStoreId());
            storeOutDlt.setFormNo(formNo);
            storeOutDlt.setSn(SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.SnHeaderType.getValue(),1));
            storeOutDlt.setCode(deliveryDlt.getCode());
            storeOutDlt.setQty(deliveryDlt.getQty());
            storeOutDlt.setLeave(0L);
            storeOutDlt.setSum(0L);
            this.insertInfo(storeOutDlt);
        }
    }

    @Override
    @Transactional
    public void edit(String storeOutId, String date, String description, List<Map> mapList) {

        StoreOutHdr storeOutHdr = storeOutHdrDao.selectById(storeOutId);
        if(storeOutHdr == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据不存在");
        }

        storeOutHdr.setStoreOutDate(DateUtil.parse(date,"yyyy-MM-dd"));
        storeOutHdr.setUpdateDate(new Date());
        storeOutHdr.setDescription(description);
        Integer result = storeOutHdrDao.updateById(storeOutHdr);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }

        for (Map map : mapList){

            StoreOutDlt entity = JSON.parseObject(JSON.toJSONString(map), StoreOutDlt.class);
//            if(entity.getQty()>entity.getNotDeliveryNum()){
////                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"本次发货数量不能多于剩余发货数量");
////            }
            this.updateInfo(entity);
        }

    }
}
