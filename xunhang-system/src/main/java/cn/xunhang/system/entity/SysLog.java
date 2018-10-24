package cn.xunhang.system.entity;

import cn.xunhang.system.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author theodo
 * @since 2017-10-28
 */
@TableName("sys_log")
public class SysLog extends InfoBaseDO<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
	private String username;
    /**
     * 用户操作
     */
	private String operation;
    /**
     * 请求方法
     */
	private String method;
    /**
     * 请求参数
     */
	private String params;
    /**
     * 执行时长(毫秒)
     */
	private Long time;
    /**
     * IP地址
     */
	private String ip;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "SysLog{" +
			", id=" + id +
			", username=" + username +
			", operation=" + operation +
			", method=" + method +
			", params=" + params +
			", time=" + time +
			", ip=" + ip +
			", createDate=" + createDate +
			"}";
	}
}
