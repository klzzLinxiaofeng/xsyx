package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentPunish
 * @author AutoCreate
 *
 */
public class StudentPunish implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
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
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标志,1为删除，0为未删除
	 */
	private Boolean isDelete;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改日期
	 */
	private java.util.Date modifyDate;
	/**
	 * 处分类型
	 */
	private String punishType;
	/**
	 * 处分原因
	 */
	private String punishCause;
	/**
	 * 处分日期
	 */
	private java.util.Date punishDay;
	/**
	 * 到期日期
	 */
	private java.util.Date punishEndDay;
	/**
	 * 撤销日期
	 */
	private java.util.Date repealDay;
	/**
	 * 是否撤销处分
	 */
	private Boolean isRepeal;
	
	
	
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsRepeal() {
		return isRepeal;
	}

	public void setIsRepeal(Boolean isRepeal) {
		this.isRepeal = isRepeal;
	}

	public StudentPunish() {
		
	}
	
	public StudentPunish(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在班级ID,根据班级id可以查找到学校，学年，年纪信息.关联表pj_team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置所在班级ID,根据班级id可以查找到学校，学年，年纪信息.关联表pj_team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取删除标志,1为删除，0为未删除
	 * 设置删除标志,1为删除，0为未删除
	 * 
	 */
	
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取最后修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置最后修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取处分类型
	 * @return java.lang.String
	 */
	public String getPunishType() {
		return this.punishType;
	}
	
	/**
	 * 设置处分类型
	 * @param punishType
	 * @type java.lang.String
	 */
	public void setPunishType(String punishType) {
		this.punishType = punishType;
	}
	
	/**
	 * 获取处分原因
	 * @return java.lang.String
	 */
	public String getPunishCause() {
		return this.punishCause;
	}
	
	/**
	 * 设置处分原因
	 * @param punishCause
	 * @type java.lang.String
	 */
	public void setPunishCause(String punishCause) {
		this.punishCause = punishCause;
	}
	
	/**
	 * 获取处分日期
	 * @return java.util.Date
	 */
	public java.util.Date getPunishDay() {
		return this.punishDay;
	}
	
	/**
	 * 设置处分日期
	 * @param punishDay
	 * @type java.util.Date
	 */
	public void setPunishDay(java.util.Date punishDay) {
		this.punishDay = punishDay;
	}
	
	/**
	 * 获取到期日期
	 * @return java.util.Date
	 */
	public java.util.Date getPunishEndDay() {
		return this.punishEndDay;
	}
	
	/**
	 * 设置到期日期
	 * @param punishEndDay
	 * @type java.util.Date
	 */
	public void setPunishEndDay(java.util.Date punishEndDay) {
		this.punishEndDay = punishEndDay;
	}
	
	/**
	 * 获取撤销日期
	 * @return java.util.Date
	 */
	public java.util.Date getRepealDay() {
		return this.repealDay;
	}
	
	/**
	 * 设置撤销日期
	 * @param repealDay
	 * @type java.util.Date
	 */
	public void setRepealDay(java.util.Date repealDay) {
		this.repealDay = repealDay;
	}

	
}