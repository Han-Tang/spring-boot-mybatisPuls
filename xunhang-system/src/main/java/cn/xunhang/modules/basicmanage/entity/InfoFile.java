package cn.xunhang.modules.basicmanage.entity;

import cn.xunhang.modules.basicmanage.entity.infoBase.InfoBaseDO;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzc
 * @since 2018-08-07
 */
@TableName("InfoFile")
public class InfoFile extends InfoBaseDO<InfoFile> {

    private static final long serialVersionUID = 1L;
	/**
	 * 关联表主键
	 */
	private String code;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件类型  FileTypeAndPath
	 */
	private String fileType;
	/**
	 * 文件保存地址
	 */
	private String location;
	/**
	 * 文件关联类型  FilePurpose
	 */
	private String purpose;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		return "InfoFile{" +
			", id=" + id +
			", code=" + code +
			", active=" + active +
			", deleted=" + deleted +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", ts=" + ts +
			", fileType=" + fileType +
			", name=" + name +
			", location=" + location +
			", purpose=" + purpose +
			"}";
	}
}
