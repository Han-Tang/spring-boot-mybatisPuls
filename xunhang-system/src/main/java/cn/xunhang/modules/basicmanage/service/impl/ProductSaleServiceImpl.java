package cn.xunhang.modules.basicmanage.service.impl;


import cn.xunhang.modules.basicmanage.dao.ProductSaleDao;
import cn.xunhang.modules.basicmanage.entity.ProductSale;
import cn.xunhang.modules.basicmanage.service.ProductSaleService;
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
public class ProductSaleServiceImpl extends InfoBaseServiceImpl<ProductSale,ProductSaleDao> implements ProductSaleService {

//    @Autowired
//    private ProductSaleDao productSaleDao;

//    @Override
//    @Transactional
//    public  void insertInfo(Map<String,Object> params, String cod){
//        ProductSale productSale = JSON.parseObject(JSON.toJSONString(params), ProductSale.class);
//        productSale.setInfoProductId(cod);
//        productSale.setId(UUID.randomUUID().toString());
//        productSale.setCreateDate(new Date());
//        productSaleDao.insert(productSale);
//    }

//    @Override
//    @Transactional
//    public  void updateInfo(Map<String,Object> params){
//        ProductSale productSale = JSON.parseObject(JSON.toJSONString(params), ProductSale.class);
//        productSale.setUpdateDate(new Date());
//        productSaleDao.updateById(productSale);
//    }
}
