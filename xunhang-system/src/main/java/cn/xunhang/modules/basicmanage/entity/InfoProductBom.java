package cn.xunhang.modules.basicmanage.entity;


import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyj 
 * @date 2018-10-22 14:23
 */
@TableName("InfoProductBom")
public class InfoProductBom extends InfoBaseDO<InfoProductBom> {

    //所属单号(订单号/产品号)
    private String orderNum;
    //父id(父自己为0)
    private String parentId;
    //等级
	private String lev;
	//文件/产品/零件编码
	private String ref;
	//文件/产品/零件名称
	private String des;
	//产品型号
	private String prdPropMod;
	//标准类型
	private String sta;
	//物料编码
	private String partMatRef;
	//物料名称
	private String partMat;
	//毛料长度
	private String partOL;
	//毛料宽度
	private String partOW;
	//毛料厚度
	private String partOT;
	//长度/宽度
	private String partL;
	//宽度/深度
	private String partW;
	//厚度/高度
	private String partT;
	//数量
	private String qty;
	//T1_榫头名称
	private String t1Nam;
	//T1_榫头序号
	private String t1Ord;
	//T1_加工机种
	private String t1Mac;
	//T1_关键工艺
	private String t1Pro;
	//T1_榫头组别
	private String t1Num;
	//T2_榫头名称
	private String t2Nam;
	//T2_榫头序号
	private String t2Ord;
    //T2_加工机种
	private String t2Mac;
	//T2_关键工艺
	private String t2Pro;
	//T2_榫头组别
	private String t2Num;
	//零件类型
	private String typ;
	//备注
//	private String partCom;


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrdPropMod() {
        return prdPropMod;
    }

    public void setPrdPropMod(String prdPropMod) {
        this.prdPropMod = prdPropMod;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public String getPartMatRef() {
        return partMatRef;
    }

    public void setPartMatRef(String partMatRef) {
        this.partMatRef = partMatRef;
    }

    public String getPartMat() {
        return partMat;
    }

    public void setPartMat(String partMat) {
        this.partMat = partMat;
    }

    public String getPartOL() {
        return partOL;
    }

    public void setPartOL(String partOL) {
        this.partOL = partOL;
    }

    public String getPartOW() {
        return partOW;
    }

    public void setPartOW(String partOW) {
        this.partOW = partOW;
    }

    public String getPartOT() {
        return partOT;
    }

    public void setPartOT(String partOT) {
        this.partOT = partOT;
    }

    public String getPartL() {
        return partL;
    }

    public void setPartL(String partL) {
        this.partL = partL;
    }

    public String getPartW() {
        return partW;
    }

    public void setPartW(String partW) {
        this.partW = partW;
    }

    public String getPartT() {
        return partT;
    }

    public void setPartT(String partT) {
        this.partT = partT;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getT1Nam() {
        return t1Nam;
    }

    public void setT1Nam(String t1Nam) {
        this.t1Nam = t1Nam;
    }

    public String getT1Ord() {
        return t1Ord;
    }

    public void setT1Ord(String t1Ord) {
        this.t1Ord = t1Ord;
    }

    public String getT1Mac() {
        return t1Mac;
    }

    public void setT1Mac(String t1Mac) {
        this.t1Mac = t1Mac;
    }

    public String getT1Pro() {
        return t1Pro;
    }

    public void setT1Pro(String t1Pro) {
        this.t1Pro = t1Pro;
    }

    public String getT1Num() {
        return t1Num;
    }

    public void setT1Num(String t1Num) {
        this.t1Num = t1Num;
    }

    public String getT2Nam() {
        return t2Nam;
    }

    public void setT2Nam(String t2Nam) {
        this.t2Nam = t2Nam;
    }

    public String getT2Ord() {
        return t2Ord;
    }

    public void setT2Ord(String t2Ord) {
        this.t2Ord = t2Ord;
    }

    public String getT2Mac() {
        return t2Mac;
    }

    public void setT2Mac(String t2Mac) {
        this.t2Mac = t2Mac;
    }

    public String getT2Pro() {
        return t2Pro;
    }

    public void setT2Pro(String t2Pro) {
        this.t2Pro = t2Pro;
    }

    public String getT2Num() {
        return t2Num;
    }

    public void setT2Num(String t2Num) {
        this.t2Num = t2Num;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
