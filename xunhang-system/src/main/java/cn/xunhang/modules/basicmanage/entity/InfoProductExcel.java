package cn.xunhang.modules.basicmanage.entity;


import cn.xunhang.common.excel.ExcelCell;
import cn.xunhang.modules.basicmanage.vo.InfoProductVo;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyj
 * @date 2018-10-18 09:34
 */

public class InfoProductExcel {


	//产品名称
    @ExcelCell(title="产品名称",index=1)
	private String name;
	//产品编码
    @ExcelCell(title="产品编号",index=2)
    private String code;
	//产品系列
    @ExcelCell(title="系列名称",index=3)
	private String serial;
    //产品长
    @ExcelCell(title="长",index=4)
    private Double length;
    //产品宽
    @ExcelCell(title="宽",index=5)
    private Double width;
    //产品高
    @ExcelCell(title="高",index=6)
    private Double thick;
    //产品主花色
    @ExcelCell(title="产品主花色",index=7)
    private String color1;
    //产品副花色
    @ExcelCell(title="产品副花色",index=8)
    private String color2;
    //产品主材质
    @ExcelCell(title="产品主材质",index=9)
    private String meterial1;
    //产品副材质
    @ExcelCell(title="产品副材质",index=10)
    private String meterial2;
	//产品漆类
    @ExcelCell(title="漆类",index=11)
	private String paint;
    //造型
    @ExcelCell(title="造型",index=12)
    private String shape;
    //软包类型
    @ExcelCell(title="软包类型",index=13)
    private String softKind;
    //是否外购
    @ExcelCell(title="是否外购",index=14)
    private Boolean buy;
    //默认销售价
    @ExcelCell(title="默认销售价",index=15)
    private Double price;
	//计量单位
    @ExcelCell(title="单位",index=16)
	private String unit;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getThick() {
        return thick;
    }

    public void setThick(Double thick) {
        this.thick = thick;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getMeterial1() {
        return meterial1;
    }

    public void setMeterial1(String meterial1) {
        this.meterial1 = meterial1;
    }

    public String getMeterial2() {
        return meterial2;
    }

    public void setMeterial2(String meterial2) {
        this.meterial2 = meterial2;
    }

    public String getPaint() {
        return paint;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getSoftKind() {
        return softKind;
    }

    public void setSoftKind(String softKind) {
        this.softKind = softKind;
    }

    public Boolean getBuy() {
        return buy;
    }

    public void setBuy(Boolean buy) {
        this.buy = buy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



    public static InfoProductExcel infoProductExcel(InfoProductVo vo){
        InfoProductExcel info = new InfoProductExcel();
        info.name = vo.getInfoProduct().getName();
        info.code = vo.getInfoProduct().getCode();
        info.serial = vo.getInfoProduct().getSerial();
        info.length = vo.getInfoProduct().getLength();
        info.width = vo.getInfoProduct().getWidth();
        info.thick = vo.getInfoProduct().getThick();
        info.color1 = vo.getInfoProduct().getColor1();
        info.color2 = vo.getInfoProduct().getColor2();
        info.meterial1 = vo.getInfoProduct().getMeterial1();
        info.meterial2 = vo.getInfoProduct().getMeterial2();
        info.paint = vo.getInfoProduct().getPaint();
        info.shape = vo.getInfoProduct().getShape();
        info.softKind = vo.getInfoProduct().getSoftKind();
        info.buy = vo.getInfoProduct().getBuy();
        info.price = vo.getProductSale().getPrice();
        info.unit = vo.getInfoProduct().getUnit();

        return info;
    }

    public static InfoProductVo infoProductVo(InfoProductExcel infoProductExcel){

        InfoProductVo infoProductVo = new InfoProductVo();

        InfoProduct infoProduct = new InfoProduct();
        infoProduct.setName(infoProductExcel.name);
        infoProduct.setCode(infoProductExcel.code);
        infoProduct.setSerial(infoProductExcel.serial);
        infoProduct.setLength(infoProductExcel.length);
        infoProduct.setWidth(infoProductExcel.width);
        infoProduct.setThick(infoProductExcel.thick);
        infoProduct.setColor1(infoProductExcel.color1);
        infoProduct.setColor2(infoProductExcel.color2);
        infoProduct.setMeterial1(infoProductExcel.meterial1);
        infoProduct.setMeterial2(infoProductExcel.meterial2);
        infoProduct.setPaint(infoProductExcel.paint);
        infoProduct.setShape(infoProductExcel.shape);
        infoProduct.setSoftKind(infoProductExcel.softKind);
        infoProduct.setBuy(infoProductExcel.buy);
        infoProduct.setUnit(infoProductExcel.unit);

        ProductSale sale = new ProductSale();
        sale.setPrice(infoProductExcel.price);

        ProductProduce produce = new ProductProduce();
        ProductPurch purch = new ProductPurch();

        infoProductVo.setInfoProduct(infoProduct);
        infoProductVo.setProductSale(sale);
        infoProductVo.setProductProduce(produce);
        infoProductVo.setProductPurch(purch);

        return infoProductVo;
    }
}
