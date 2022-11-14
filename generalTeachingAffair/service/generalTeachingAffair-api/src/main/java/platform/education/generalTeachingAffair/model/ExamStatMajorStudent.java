package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ExamStatMajorStudent
 * @author AutoCreate
 *
 */
public class ExamStatMajorStudent implements Model<Integer> {


	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 对应统考考务记录
	 */
	private Integer examWorksId;
	/**
	 * 同年级统考标识UUID
	 */
	private String jointExamCode;
	/**
	 * 班级
	 */
	private Integer teamId;
	/**
	 * 学生
	 */
	private Integer studentId;
	/**
	 * 多科总分
	 */
	private Float totalScore;
	/**
	 * 班级排名
	 */
	private Integer teamRank;
	/**
	 * 班级名次变化情况
	 */
	private Integer teamRankChange;
	/**
	 * 年级排名
	 */
	private Integer gradeRank;
	/**
	 * 年级名次变化情况
	 */
	private Integer gradeRankChange;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 记录最后修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 记录删除标志
	 */
	private Boolean isDeleted;

	public ExamStatMajorStudent() {

	}

	public ExamStatMajorStudent(Integer id) {
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
	 * 获取对应统考考务记录
	 * @return java.lang.Integer
	 */
	public Integer getExamWorksId() {
		return this.examWorksId;
	}

	/**
	 * 设置对应统考考务记录
	 * @param examWorksId
	 * @type java.lang.Integer
	 */
	public void setExamWorksId(Integer examWorksId) {
		this.examWorksId = examWorksId;
	}

	/**
	 * 获取同年级统考标识UUID
	 * @return java.lang.String
	 */
	public String getJointExamCode() {
		return this.jointExamCode;
	}

	/**
	 * 设置同年级统考标识UUID
	 * @param jointExamCode
	 * @type java.lang.String
	 */
	public void setJointExamCode(String jointExamCode) {
		this.jointExamCode = jointExamCode;
	}

	/**
	 * 获取班级
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}

	/**
	 * 设置班级
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * 获取学生
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}

	/**
	 * 设置学生
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获取多科总分
	 * @return java.lang.Float
	 */
	public Float getTotalScore() {
		return this.totalScore;
	}

	/**
	 * 设置多科总分
	 * @param totalScore
	 * @type java.lang.Float
	 */
	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * 获取班级排名
	 * @return java.lang.Integer
	 */
	public Integer getTeamRank() {
		return this.teamRank;
	}

	/**
	 * 设置班级排名
	 * @param teamRank
	 * @type java.lang.Integer
	 */
	public void setTeamRank(Integer teamRank) {
		this.teamRank = teamRank;
	}

	/**
	 * 获取班级名次变化情况
	 * @return java.lang.Integer
	 */
	public Integer getTeamRankChange() {
		return this.teamRankChange;
	}

	/**
	 * 设置班级名次变化情况
	 * @param teamRankChange
	 * @type java.lang.Integer
	 */
	public void setTeamRankChange(Integer teamRankChange) {
		this.teamRankChange = teamRankChange;
	}

	/**
	 * 获取年级排名
	 * @return java.lang.Integer
	 */
	public Integer getGradeRank() {
		return this.gradeRank;
	}

	/**
	 * 设置年级排名
	 * @param gradeRank
	 * @type java.lang.Integer
	 */
	public void setGradeRank(Integer gradeRank) {
		this.gradeRank = gradeRank;
	}

	/**
	 * 获取年级名次变化情况
	 * @return java.lang.Integer
	 */
	public Integer getGradeRankChange() {
		return this.gradeRankChange;
	}

	/**
	 * 设置年级名次变化情况
	 * @param gradeRankChange
	 * @type java.lang.Integer
	 */
	public void setGradeRankChange(Integer gradeRankChange) {
		this.gradeRankChange = gradeRankChange;
	}

	/**
	 * 获取记录创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置记录创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取记录最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * 设置记录最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 获取记录删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * 设置记录删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}