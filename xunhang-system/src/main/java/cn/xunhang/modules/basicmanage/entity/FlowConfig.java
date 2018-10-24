package cn.xunhang.modules.basicmanage.entity;


import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 流程
 * </p>
 *
 * @author tyj
 * @since 2018-09-30
 */
@TableName("FlowConfig")
public class FlowConfig extends InfoBaseDO<FlowConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
	private String billName;
    /**
     * 状态
     */
	private String status;


	@Override
	public String toString() {
		return "FlowConfig{" +
				", billName=" + billName +
				", status=" + status +
				", id=" + id +
				", active=" + active +
				", deleted=" + deleted +
				", createDate=" + createDate +
				", createBy=" + createBy +
				", updateDate=" + updateDate +
				", updateBy=" + updateBy +
				", ts=" + ts +
				"}";
	}
}
