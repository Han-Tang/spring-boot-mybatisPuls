package cn.xunhang.modules.basicmanage.service.impl;


import cn.xunhang.modules.basicmanage.dao.ProductProduceDao;
import cn.xunhang.modules.basicmanage.entity.ProductProduce;
import cn.xunhang.modules.basicmanage.service.ProductProduceService;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-07-30
 */
@Service
public class ProductProduceServiceImpl extends InfoBaseServiceImpl<ProductProduce,ProductProduceDao> implements ProductProduceService {

//    @Autowired
//    private ProductProduceDao productProduceDao;

//    @Override
//    @Transactional
//    public void insertInfo(ProductProduce productProduce,String code){
////        ProductProduce productProduce = JSON.parseObject(JSON.toJSONString(params), ProductProduce.class);
//        productProduce.setInfoProductId(code);
////        productProduce.setId(UUID.randomUUID().toString());
//        productProduce.setCreateDate(new Date());
//        productProduce.setUpdateDate(new Date());
//        productProduceDao.insert(productProduce);
//    }

//    @Override
//    @Transactional
//    public  void updateInfo(Map<String,Object> params){
//        ProductProduce productProduce = JSON.parseObject(JSON.toJSONString(params), ProductProduce.class);
//        productProduce.setUpdateDate(new Date());
//        productProduceDao.updateById(productProduce);
//    }
}
