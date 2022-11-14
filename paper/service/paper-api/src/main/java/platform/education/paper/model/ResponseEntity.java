package platform.education.paper.model;
import java.io.Serializable;

/**
 * 
 * 服务端响应实体类
 * @author pantq
 *
 */
public class ResponseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//文件MD5
	private String fileMd5;
	
	//文件uuid
	private String fileUuid;
	
	//文件url
	private String fileUrl;
	
	//是否存在
	private Boolean isExist;
	
	//源文件字段，移动端要用
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}
	
	
}
