package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * StudentScore
 * @author AutoCreate
 *
 */
public class StudentScore implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 考试日程安排表主键 pj_exam_team_subject.id
	 */
	private Integer examTeamSubjectId;
	/**
	 * 学生id，关联学生表主键 pj_student.id
	 */
	private Integer studentId;
	/**
	 * 科目代码
	 */
	private String subjectCode;
	/**
	 * 成绩
	 */
	private String score;
	/**
	 * 班级排名
	 */
	private String teamRank;
	/**
	 * 年级排名
	 */
	private String gradeRank;
	/**
	 * 是否删除，0==不删除，1===删除
	 */
	private Boolean isDelete;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 方便统计学生成绩字段
	 */
	private String schoolYear;//学年
	private String termCode;//学期
	private  Integer gradeId;//年级
	private  Integer teamId;//班级
	private String examType;//考试类型
	private String examName;//考试名称
	private  Integer schoolId;


	private String comment;//评语

	
	public StudentScore() {
		
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public StudentScore(Integer id) {
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
	 * 获取考试日程安排表主键 pj_exam_team_subject.id
	 * @return java.lang.Integer
	 */
	public Integer getExamTeamSubjectId() {
		return this.examTeamSubjectId;
	}
	
	/**
	 * 设置考试日程安排表主键 pj_exam_team_subject.id
	 * @param examTeamSubjectId
	 * @type java.lang.Integer
	 */
	public void setExamTeamSubjectId(Integer examTeamSubjectId) {
		this.examTeamSubjectId = examTeamSubjectId;
	}
	
	/**
	 * 获取学生id，关联学生表主键 pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生id，关联学生表主键 pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取科目代码
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目代码
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取成绩
	 * @return java.lang.String
	 */
	public String getScore() {
		if(StringUtils.isNotEmpty(score)){
			try {
				return new BigDecimal(score).stripTrailingZeros().toPlainString();
			} catch (Exception e) { }
		}
		return this.score;
	}
	
	/**
	 * 设置成绩
	 * @param score
	 * @type java.lang.String
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * 获取班级排名
	 * @return java.lang.String
	 */
	public String getTeamRank() {
		return this.teamRank;
	}
	
	/**
	 * 设置班级排名
	 * @param teamRank
	 * @type java.lang.String
	 */
	public void setTeamRank(String teamRank) {
		this.teamRank = teamRank;
	}
	
	/**
	 * 获取年级排名
	 * @return java.lang.String
	 */
	public String getGradeRank() {
		return this.gradeRank;
	}
	
	/**
	 * 设置年级排名
	 * @param gradeRank
	 * @type java.lang.String
	 */
	public void setGradeRank(String gradeRank) {
		this.gradeRank = gradeRank;
	}
	
	/**
	 * 获取是否删除，0==不删除，1===删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除，0==不删除，1===删除
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
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
	 * 获取最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}