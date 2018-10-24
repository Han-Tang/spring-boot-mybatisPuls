package cn.xunhang.modules.basicmanage.entity;


import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 流程记录
 * </p>
 *
 * @author tyj
 * @since 2018-09-30
 */
@TableName("FlowLog")
public class FlowLog extends InfoBaseDO<FlowLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
	private String billName;
    /**
     * 上个状态
     */
	private String fromStatus;
    /**
     * 下个状态
     */
	private String toStatus;


	@Override
	public String toString() {
		return "FlowLog{" +
				", billName=" + billName +
				", fromStatus=" + fromStatus +
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
