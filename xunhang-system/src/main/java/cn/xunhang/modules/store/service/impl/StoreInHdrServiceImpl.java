package cn.xunhang.modules.store.service.impl;


import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.enums.OrderHeaderEnum;
import cn.xunhang.common.enums.ReviewStatusEnum;
import cn.xunhang.common.enums.StoreReviewStatusEnum;
import cn.xunhang.common.utils.SequenceCodeUtils;
import cn.xunhang.modules.store.dao.*;
import cn.xunhang.modules.store.entity.*;
import cn.xunhang.modules.store.service.StoreInHdrService;
import cn.xunhang.modules.store.service.infoBase.impl.InfoBaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-09-06
 */
@Service
public class StoreInHdrServiceImpl extends InfoBaseServiceImpl<StoreInHdr, StoreInHdrDao> implements StoreInHdrService {

    @Autowired
    private StoreInHdrDao storeInHdrDao;
    @Autowired
    private StoreInDltDao storeInDltDao;
    @Autowired
    private StoreInLocationDltDao storeInLocationDltDao;
    @Autowired
    private StoreDltDao storeDltDao;

    @Autowired
    private RepertoryDltDao repertoryDltDao;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<Map> queryStoreInWaitListByCondition(String storeName, String name, String code, String storeInName, String status, String kind, int current, int offset) {

        List<Map> mapList = new ArrayList<>();
        List<StoreInHdr> storeInHdrList;
        try {
            PageHelper.startPage(current, offset);
            storeInHdrList = storeInHdrDao.queryListByCondition(storeName, name, code, storeInName, status, kind);
        } finally {
            PageHelper.remove();
        }

        if (storeInHdrList.size() == 0) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "没有数据");
        }

        for (StoreInHdr storeInHdr : storeInHdrList) {
            Map<String, Object> map = new HashMap<>();
            List<StoreInDlt> storeInDltList = storeInDltDao.selectList(
                    new EntityWrapper<StoreInDlt>().eq("formNo", storeInHdr.getFormNo())
                            .orderBy("createDate", false));
            if (storeInDltList.size() == 0) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "没有数据");
            }
            map.put("hdr", storeInHdr);
            map.put("dlt", storeInDltList);
            mapList.add(map);
        }

        return mapList;
    }

    @Override
    public List<Map> queryStoreInConfirmListByCondition(String storeName, String storeInNo, String storeInName, String status, String kind, int current, int offset) {

        List<Map> mapList = new ArrayList<>();
        List<StoreInHdr> storeInHdrList;
        try {
            PageHelper.startPage(current, offset);
            storeInHdrList = storeInHdrDao.queryListConfirmByCondition(storeName, storeInNo, storeInName, status, kind);
        } finally {
            PageHelper.remove();
        }

        if (storeInHdrList.size() == 0) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "没有数据");
        }

        for (StoreInHdr storeInHdr : storeInHdrList) {
            Map<String, Object> map = new HashMap<>();
            List<StoreInDlt> storeInDltList = storeInDltDao.selectList(
                    new EntityWrapper<StoreInDlt>().eq("storeInId", storeInHdr.getId())
                            .orderBy("createDate", false));

            if (storeInDltList.size() == 0) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "没有数据");
            }
            for (StoreInDlt storeInDlt : storeInDltList) {

                List<StoreInLocationDlt> storeInLocationDltList = storeInLocationDltDao.selectList(new EntityWrapper<StoreInLocationDlt>().eq("storeInDltId", storeInDlt.getId()));

                List<Map> mapList1 = new ArrayList<>();
                for (StoreInLocationDlt storeInLocationDlt : storeInLocationDltList) {

                    StoreDlt storeDlt = storeDltDao.selectById(storeInLocationDlt.getStoreLocationId());

                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("locationNo", storeDlt.getLocationNo());
                    map1.put("num", storeInLocationDlt.getNum());
                    mapList1.add(map1);
                }
                storeInDlt.setStoreLocation(mapList1);
            }

            map.put("hdr", storeInHdr);
            map.put("dlt", storeInDltList);
            mapList.add(map);
        }

        return mapList;
    }

    @Override
    @Transactional
    public void submitByIds(List<String> formNo) {

        for (String fn : formNo) {
            StoreInHdr storeInHdr = this.selectOne(new EntityWrapper<StoreInHdr>().eq("id",fn));
            if (storeInHdr == null) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据出错");
            }
            if (!StoreReviewStatusEnum.NewType.getValue().equals(storeInHdr.getStatus())) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "此记录审核状态不对");
            }
            storeInHdr.setStatus(StoreReviewStatusEnum.ConfirmType.getValue());
            int result = storeInHdrDao.updateById(storeInHdr);
            if (result == 0) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "提交失败");
            }

            //对明细操作
            List<StoreInDlt> storeInDltList = storeInDltDao.selectList(new EntityWrapper<StoreInDlt>().eq("formNo",storeInHdr.getFormNo()));
            for(StoreInDlt storeInDlt : storeInDltList){
                storeInDlt.setLeave(storeInDlt.getQty());
                storeInDltDao.updateById(storeInDlt);

                //运算器

                //入库存
                List<RepertoryDlt> repertoryDltList = repertoryDltDao.selectList(
                        new EntityWrapper<RepertoryDlt>()
                                .eq("storeId",storeInDlt.getStoreId())
                                .eq("code",storeInDlt.getCode()));
                if(repertoryDltList.size() != 0){
                    RepertoryDlt repertoryDlt = repertoryDltList.get(0);
                    repertoryDlt.setLeave(repertoryDlt.getLeave()+storeInDlt.getLeave());
                    repertoryDlt.setSum(repertoryDlt.getSum()+storeInDlt.getLeave());
                    repertoryDltDao.updateById(repertoryDlt);
                }
                else {

                    RepertoryDlt repertoryDlt =  new RepertoryDlt();
                    repertoryDlt.setName(storeInDlt.getName());
                    repertoryDlt.setgCode(storeInDlt.getgCode());
                    repertoryDlt.setType(storeInDlt.getType());
                    repertoryDlt.setFormat(storeInDlt.getFormat());
                    repertoryDlt.setMeterial1(storeInDlt.getMeterial1());
                    repertoryDlt.setColor1(storeInDlt.getColor1());
                    repertoryDlt.setPackQty(storeInDlt.getPackQty());
                    repertoryDlt.setPackLeave(storeInDlt.getPackLeave());
                    repertoryDlt.setUnit(storeInDlt.getUnit());
                    repertoryDlt.setKind(storeInDlt.getKind());
                    repertoryDlt.setStoreId(storeInDlt.getStoreId());
                    repertoryDlt.setSerial(storeInDlt.getSerial());
                    repertoryDlt.setModel(storeInDlt.getModel());
                    repertoryDlt.setUsing(0L);
                    repertoryDlt.setSum(storeInDlt.getLeave());

                    repertoryDlt.setQty(0L);
                    repertoryDlt.setLeave(storeInDlt.getLeave());
                    repertoryDlt.setCode(storeInDlt.getCode());
                    repertoryDlt.setSn(SequenceCodeUtils.genSeqCode(redisTemplate,OrderHeaderEnum.SnHeaderType.getValue(),1));

                    repertoryDltDao.insert(repertoryDlt);

                }

            }

        }

    }

//    @Override
//    @Transactional
//    public void confirmSave(List<String> storeInId, List<Map> dlt) {
//
//        for (String sid : storeInId) {
//            StoreInHdr storeInHdr = storeInHdrDao.selectById(sid);
//            if (storeInHdr == null) {
//                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据出错");
//            }
//            if (!StoreReviewStatusEnum.WaitConfirmType.getValue().equals(storeInHdr.getStatus())) {
//                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据审核状态不对");
//            }
//            storeInHdr.setStatus(StoreReviewStatusEnum.ConfirmType.getValue());
//            int result = storeInHdrDao.updateById(storeInHdr);
//            if (result == 0) {
//                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "确认失败");
//            }
//        }
//
//        for (Map map : dlt) {
//
//            String id = (String) map.get("id");
//            String storeLocationId = (String) map.get("storeLocationId");
//            String num = (String) map.get("num");
//
//            StoreInLocationDlt storeInLocationDlt = new StoreInLocationDlt();
//            storeInLocationDlt.setId(UUID.randomUUID().toString());
//            storeInLocationDlt.setNum(Integer.parseInt(num));
//            storeInLocationDlt.setStoreInDltId(id);
//            storeInLocationDlt.setStoreLocationId(storeLocationId);
//            int result2 = storeInLocationDltDao.insert(storeInLocationDlt);
//            if (result2 == 0) {
//                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "确认失败");
//            }
//        }


}

