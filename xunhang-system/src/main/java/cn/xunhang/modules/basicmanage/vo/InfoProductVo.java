package cn.xunhang.modules.basicmanage.vo;

import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.basicmanage.entity.ProductProduce;
import cn.xunhang.modules.basicmanage.entity.ProductPurch;
import cn.xunhang.modules.basicmanage.entity.ProductSale;

public class InfoProductVo {

    private InfoProduct infoProduct;

    private ProductProduce productProduce;

    private ProductPurch productPurch;

    private ProductSale productSale;

    public InfoProduct getInfoProduct() {
        return infoProduct;
    }

    public void setInfoProduct(InfoProduct infoProduct) {
        this.infoProduct = infoProduct;
    }

    public ProductProduce getProductProduce() {
        return productProduce;
    }

    public void setProductProduce(ProductProduce productProduce) {
        this.productProduce = productProduce;
    }

    public ProductPurch getProductPurch() {
        return productPurch;
    }

    public void setProductPurch(ProductPurch productPurch) {
        this.productPurch = productPurch;
    }

    public ProductSale getProductSale() {
        return productSale;
    }

    public void setProductSale(ProductSale productSale) {
        this.productSale = productSale;
    }
}
