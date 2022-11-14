package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Complain;

import java.util.List;

/**
 * Complain
 * @author AutoCreate
 *
 */
public class ComplainVo extends Complain {
	private static final long serialVersionUID = 1L;

	//投诉次数
	private Integer count;
	//平均评价星级
	private Float averageLevel;
	//图片uuid, 用","连接
	private String fileUUIDs;
	//图片uuid,用于页面输出
	private List<String> fileUUIDList;
	//投诉人所在部门
	private String departName;
	//投诉人姓名
	private String complainName;
	//评价人姓名
	private String disposeName;

	private String percent;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Float getAverageLevel() {
		return averageLevel;
	}

	public void setAverageLevel(Float averageLevel) {
		this.averageLevel = averageLevel;
	}

	public String getFileUUIDs() {
		return fileUUIDs;
	}

	public void setFileUUIDs(String fileUUIDs) {
		this.fileUUIDs = fileUUIDs;
	}

	public List<String> getFileUUIDList() {
		return fileUUIDList;
	}

	public void setFileUUIDList(List<String> fileUUIDList) {
		this.fileUUIDList = fileUUIDList;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getComplainName() {
		return complainName;
	}

	public void setComplainName(String complainName) {
		this.complainName = complainName;
	}

	public String getDisposeName() {
		return disposeName;
	}

	public void setDisposeName(String disposeName) {
		this.disposeName = disposeName;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
}
