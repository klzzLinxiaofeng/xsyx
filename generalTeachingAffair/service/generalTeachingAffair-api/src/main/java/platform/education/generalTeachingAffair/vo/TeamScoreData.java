package platform.education.generalTeachingAffair.vo;

import java.util.List;

public class TeamScoreData {
	//班级id
	private Integer teamId;
	//项目id
	private Integer itemId;
	//项目名称
	private String itemName;
	//得分
	private Float score;
	
	private String remark;
	
	private List<String> fileUUIDs;
	
	private Integer teacherId;
	
	private String teacherName;
	
	private String judgeDate;
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<String> getFileUUIDs() {
		return fileUUIDs;
	}
	public void setFileUUIDs(List<String> fileUUIDs) {
		this.fileUUIDs = fileUUIDs;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getJudgeDate() {
		return judgeDate;
	}
	public void setJudgeDate(String judgeDate) {
		this.judgeDate = judgeDate;
	}
	
}
