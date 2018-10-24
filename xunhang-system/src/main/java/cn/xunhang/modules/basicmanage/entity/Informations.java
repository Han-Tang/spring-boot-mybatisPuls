package cn.xunhang.modules.basicmanage.entity;

//import cn.xunhang.common.excel.ExcelResources;
import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("Informations")
public class Informations extends InfoBaseDO<Informations> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码 unique
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 分组/类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;

//    @ExcelResources(title="编码",order=1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    @ExcelResources(title="名称",order=2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @ExcelResources(title="类型",order=3)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    @ExcelResources(title="备注",order=4)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
