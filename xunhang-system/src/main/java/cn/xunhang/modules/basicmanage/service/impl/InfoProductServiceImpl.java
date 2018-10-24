package cn.xunhang.modules.basicmanage.service.impl;

import cn.xunhang.common.base.BusinessException;

import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.modules.basicmanage.dao.InfoProductDao;
import cn.xunhang.modules.basicmanage.dao.ProductProduceDao;
import cn.xunhang.modules.basicmanage.dao.ProductPurchDao;
import cn.xunhang.modules.basicmanage.dao.ProductSaleDao;
import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.basicmanage.entity.ProductProduce;
import cn.xunhang.modules.basicmanage.entity.ProductPurch;
import cn.xunhang.modules.basicmanage.entity.ProductSale;
import cn.xunhang.modules.basicmanage.service.InfoProductService;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import cn.xunhang.modules.basicmanage.vo.InfoProductVo;
import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-07-30
 */
@Service
public class InfoProductServiceImpl extends InfoBaseServiceImpl<InfoProduct,InfoProductDao> implements InfoProductService {

    @Autowired
    private InfoProductDao infoProductDao;

    @Autowired
    private ProductPurchDao productPurchDao;
    @Autowired
    private ProductSaleDao productSaleDao;
    @Autowired
    private ProductProduceDao productProduceDao;


//    @Override
//    @Transactional
//    public String insertInfo(Map<String, Object> params) {
//        InfoProduct infoProduct = JSON.parseObject(JSON.toJSONString(params), InfoProduct.class);
//        if(StringUtils.isBlank(infoProduct.getCode())){
//            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"产品编码必填。");
//        }
//        InfoProduct info = infoProduct.selectOne(new EntityWrapper().eq("code",infoProduct.getCode()));
//        if(info!=null){
//            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"产品编码已存在。");
//        }
//        infoProduct.setId(UUID.randomUUID().toString());
//        infoProduct.setCreateDate(new Date());
//        infoProduct.setUpdateDate(new Date());
//        infoProductDao.insert(infoProduct);
//        return infoProduct.getId();
//    }

//    @Override
//    @Transactional
//    public  void updateInfo(Map<String,Object> params){
//        InfoProduct infoProduct = JSON.parseObject(JSON.toJSONString(params), InfoProduct.class);
//        infoProduct.setUpdateDate(new Date());
//        infoProductDao.updateById(infoProduct);
//    }

    @Override
    public Page<InfoProductVo> queryList(Map<String, Object> params) {

        Map condition = (Map)params.get("condition");
        Page<InfoProduct> pg = new Page<InfoProduct>((int) condition.get("current"),(int) condition.get("offset"));
        List<InfoProduct> infoProductList = infoProductDao.selectPage(pg, new EntityWrapper<InfoProduct>()
                .eq("deleted",0)
                .like(StringUtils.isNotBlank((String)condition.get("code")),"code",(String)condition.get("code"))
                .like(StringUtils.isNotBlank((String)condition.get("name")),"name",(String)condition.get("name"))
                .like(StringUtils.isNotBlank((String)condition.get("serial")),"serial",(String)condition.get("serial"))
                .like(StringUtils.isNotBlank((String)condition.get("color1")),"color1",(String)condition.get("color1"))
                .orderBy("code",true)
//              .orderBy("createDate",false)

        );

        List<InfoProductVo> list = new ArrayList<>();
        for (InfoProduct infoProduct:infoProductList){

            InfoProductVo infoProductVo = new InfoProductVo();

            ProductProduce productProduce = new ProductProduce();
            productProduce = productProduce.selectOne(new EntityWrapper().eq("infoProductId",infoProduct.getId()));

            ProductSale productSale = new ProductSale();
            productSale = productSale.selectOne(new EntityWrapper().eq("infoProductId",infoProduct.getId()));

            ProductPurch productPurch = new ProductPurch();
            productPurch = productPurch.selectOne(new EntityWrapper().eq("infoProductId",infoProduct.getId()));

            infoProductVo.setInfoProduct(infoProduct);
            infoProductVo.setProductProduce(productProduce);
            infoProductVo.setProductPurch(productPurch);
            infoProductVo.setProductSale(productSale);
            list.add(infoProductVo);
        }

        Page<InfoProductVo> listPage = new Page<>();
        listPage.setTotal(pg.getTotal());
        listPage.setRecords(list);
        return  listPage;

    }


    @Override
    public Map<String, Object> queryDetails(Map<String, Object> params) {

        String id = (String) params.get("id");
        InfoProduct infoProduct = infoProductDao.selectById(id);

        if(infoProduct == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"没有数据");
        }

        List<ProductSale> productSale = productSaleDao.selectList(
                new EntityWrapper<ProductSale>().eq("infoProductId",id));

        List<ProductProduce> productProduce = productProduceDao.selectList(
                new EntityWrapper<ProductProduce>().eq("infoProductId",id));

        List<ProductPurch> productPurch = productPurchDao.selectList(
                new EntityWrapper<ProductPurch>().eq("infoProductId",id));

        Map map = new HashMap();
        map.put("infoProduct", infoProduct);
        map.put("productSale", productSale.get(0));
        map.put("productProduce", productProduce.get(0));
        map.put("productPurch", productPurch.get(0));

        return map;
    }

    @Override
    @Transactional
    public void deleteInfo(List<String> ids){
        List<InfoProduct> list = infoProductDao.selectList(new EntityWrapper<InfoProduct>().in("id",ids));
        for (InfoProduct infoProduct : list){
//            infoProduct.setDeleted(true);
//            infoProductDao.updateById(infoProduct);
            String id = infoProduct.getId();
            infoProductDao.deleteById(infoProduct);
            productSaleDao.delete(new EntityWrapper<ProductSale>().eq("infoProductId",id));
            productProduceDao.delete(new EntityWrapper<ProductProduce>().eq("infoProductId",id));
            productPurchDao.delete(new EntityWrapper<ProductPurch>().eq("infoProductId",id));
        }
    }

}
