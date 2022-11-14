package platform.szxyzxx.web.schoolaffair.vo;

import platform.education.school.affair.model.AptTaskScore;
import platform.service.storage.model.EntityFile;

public class TaskScoreVo extends AptTaskScore{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 附件文件
	 */
	private EntityFile entityFile;
	/**
	 * 被考核人名称
	 */
	private String teacherName;
	
	public EntityFile getEntityFile() {
		return entityFile;
	}
	public void setEntityFile(EntityFile entityFile) {
		this.entityFile = entityFile;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
