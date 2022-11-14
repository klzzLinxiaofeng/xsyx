package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentAward
 * @author AutoCreate
 *
 */
public class StudentAward implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所在班级ID,根据班级id可以查找到学校，学年，年纪信息.关联表pj_team.id
	 */
	private Integer teamId;
	/**
	 * 学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 */
	private Integer studentId;
	/**
	 * 奖励内容
	 */
	private String awardContent;
	/**
	 * 奖励级别
	 */
	private String awardLevel;
	/**
	 * 奖励名次
	 */
	private String awardRanking;
	/**
	 * 奖励类型
	 */
	private String awardType;
	/**
	 * 奖励日期
	 */
	private java.util.Date awardDay;
	/**
	 * 奖励单位
	 */
	private String awardUnit;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标志,1为删除，0为未删除
	 */
	private Boolean isDelete;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 班内编号
	 */
	private String numInTeam;
	
	
	public String getNumInTeam() {
		return numInTeam;
	}


	public void setNumInTeam(String numInTeam) {
		this.numInTeam = numInTeam;
	}


	public StudentAward() {
		
	}
	

	public Integer getKey() {
		return this.id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getTeamId() {
		return teamId;
	}


	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public String getAwardContent() {
		return awardContent;
	}


	public void setAwardContent(String awardContent) {
		this.awardContent = awardContent;
	}


	public String getAwardLevel() {
		return awardLevel;
	}


	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}


	public String getAwardRanking() {
		return awardRanking;
	}


	public void setAwardRanking(String awardRanking) {
		this.awardRanking = awardRanking;
	}


	public String getAwardType() {
		return awardType;
	}


	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}


	public java.util.Date getAwardDay() {
		return awardDay;
	}


	public void setAwardDay(java.util.Date awardDay) {
		this.awardDay = awardDay;
	}


	public String getAwardUnit() {
		return awardUnit;
	}


	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Boolean getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}


	public java.util.Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}


	public java.util.Date getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	
}