package platform.szxyzxx.web.schoolaffair.vo;

import java.util.Date;
import java.util.List;

public class AptTaskImportVo {
	//考核人的ID（teacherId）
	private Integer teacherId;
	//受考核教师
	private String teacherName;
	//考核项目
	private List<AptTaskItemImportVo> taskItem;
	//考核时间
	private Date getTime;
	//评考人ID
	private String judgeTeacherId;
	//评考人姓名
	private String judgeTeacher;
	//数据格式等错误信息
	private String errorMessage;
	
	public String getJudgeTeacherId() {
		return judgeTeacherId;
	}
	public void setJudgeTeacherId(String judgeTeacherId) {
		this.judgeTeacherId = judgeTeacherId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	public List<AptTaskItemImportVo> getTaskItem() {
		return taskItem;
	}
	public void setTaskItem(List<AptTaskItemImportVo> taskItem) {
		this.taskItem = taskItem;
	}
	public Date getGetTime() {
		return getTime;
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}
	public String getJudgeTeacher() {
		return judgeTeacher;
	}
	public void setJudgeTeacher(String judgeTeacher) {
		this.judgeTeacher = judgeTeacher;
	}
	
}
