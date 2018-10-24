package cn.xunhang.modules.basicmanage.service.impl;


import cn.xunhang.modules.basicmanage.dao.ProductPurchDao;
import cn.xunhang.modules.basicmanage.entity.ProductPurch;
import cn.xunhang.modules.basicmanage.service.ProductPurchService;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class ProductPurchServiceImpl extends InfoBaseServiceImpl<ProductPurch,ProductPurchDao> implements ProductPurchService {

//    @Autowired
//    private ProductPurchDao productPurchDao;

//    @Override
//    @Transactional
//    public  void insertInfo(ProductPurch productPurch,String cod){
////        ProductPurch productPurch = JSON.parseObject(JSON.toJSONString(params), ProductPurch.class);
//        productPurch.setInfoProductId(cod);
////        productPurch.setId(UUID.randomUUID().toString());
//        productPurch.setCreateDate(new Date());
//        productPurch.setUpdateDate(new Date());
//        productPurchDao.insert(productPurch);
//    }

//    @Override
//    @Transactional
//    public  void updateInfo(Map<String,Object> params){
//        ProductPurch productPurch =  JSON.parseObject(JSON.toJSONString(params), ProductPurch.class);
//        productPurch.setUpdateDate(new Date());
//        productPurchDao.updateById(productPurch);
//    }

}
