package platform.education.oa.vo;
import platform.education.oa.model.Paper;
/**
 * Paper
 * @author AutoCreate
 *
 */
public class PaperVo extends Paper {
	private static final long serialVersionUID = 1L;
	
	//部门名称
	private String departmentName;
	
	//上传文件的名称
	private String realFileName;
	
	//已阅读人数
	private Integer readNumber;
	
	//当前登录是否为发布者或接收者
	private Boolean isPublishOrReceiver;

	private Integer readStatus;

	
	public Boolean getIsPublishOrReceiver() {
		return isPublishOrReceiver;
	}

	public void setIsPublishOrReceiver(Boolean isPublishOrReceiver) {
		this.isPublishOrReceiver = isPublishOrReceiver;
	}

	public Integer getReadNumber() {
		return readNumber;
	}
	
	public void setReadNumber(Integer readNumber) {
		this.readNumber = readNumber;
	}
	
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}
}