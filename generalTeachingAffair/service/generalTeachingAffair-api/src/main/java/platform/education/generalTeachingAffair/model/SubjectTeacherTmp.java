package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjSubjectTeacherTmp
 * @author AutoCreate
 *
 */
public class SubjectTeacherTmp implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * gradeId
	 */
	private Integer gradeId;
	/**
	 * gradeName
	 */
	private String gradeName;
	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * teamNumber
	 */
	private String teamNumber;
	/**
	 * teacherName
	 */
	private String teacherName;
	/**
	 * subjectName
	 */
	private String subjectName;
	/**
	 * alias
	 */
	private String alias;
	/**
	 * phone
	 */
	private String phone;
	/**
	 * status
	 */
	private Integer status;
	/**
	 * errorFiled
	 */
	private String errorFiled;
	/**
	 * errorInfo
	 */
	private String errorInfo;
	/**
	 * code
	 */
	private String code;
	/**
	 * subjectTeacherId
	 */
	private Integer subjectTeacherId;
	
	public SubjectTeacherTmp() {
		
	}
	
	public SubjectTeacherTmp(Integer id) {
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
	 * 获取gradeId
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置gradeId
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取gradeName
	 * @return java.lang.String
	 */
	public String getGradeName() {
		return this.gradeName;
	}
	
	/**
	 * 设置gradeName
	 * @param gradeName
	 * @type java.lang.String
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	/**
	 * 获取teamId
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置teamId
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取teamNumber
	 * @return java.lang.String
	 */
	public String getTeamNumber() {
		return this.teamNumber;
	}
	
	/**
	 * 设置teamNumber
	 * @param teamNumber
	 * @type java.lang.String
	 */
	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	/**
	 * 获取teacherName
	 * @return java.lang.String
	 */
	public String getTeacherName() {
		return this.teacherName;
	}
	
	/**
	 * 设置teacherName
	 * @param teacherName
	 * @type java.lang.String
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	/**
	 * 获取subjectName
	 * @return java.lang.String
	 */
	public String getSubjectName() {
		return this.subjectName;
	}
	
	/**
	 * 设置subjectName
	 * @param subjectName
	 * @type java.lang.String
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * 获取alias
	 * @return java.lang.String
	 */
	public String getAlias() {
		return this.alias;
	}
	
	/**
	 * 设置alias
	 * @param alias
	 * @type java.lang.String
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * 获取phone
	 * @return java.lang.String
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * 设置phone
	 * @param phone
	 * @type java.lang.String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取status
	 * @return java.lang.Integer
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	/**
	 * 设置status
	 * @param status
	 * @type java.lang.Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取errorFiled
	 * @return java.lang.String
	 */
	public String getErrorFiled() {
		return this.errorFiled;
	}
	
	/**
	 * 设置errorFiled
	 * @param errorFiled
	 * @type java.lang.String
	 */
	public void setErrorFiled(String errorFiled) {
		this.errorFiled = errorFiled;
	}
	
	/**
	 * 获取errorInfo
	 * @return java.lang.String
	 */
	public String getErrorInfo() {
		return this.errorInfo;
	}
	
	/**
	 * 设置errorInfo
	 * @param errorInfo
	 * @type java.lang.String
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	/**
	 * 获取code
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置code
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取subjectTeacherId
	 * @return java.lang.Integer
	 */
	public Integer getSubjectTeacherId() {
		return this.subjectTeacherId;
	}
	
	/**
	 * 设置subjectTeacherId
	 * @param subjectTeacherId
	 * @type java.lang.Integer
	 */
	public void setSubjectTeacherId(Integer subjectTeacherId) {
		this.subjectTeacherId = subjectTeacherId;
	}

	@Override
	public String toString() {
		return "SubjectTeacherTmp{" +
				"id=" + id +
				", gradeId=" + gradeId +
				", gradeName='" + gradeName + '\'' +
				", teamId=" + teamId +
				", teamNumber='" + teamNumber + '\'' +
				", teacherName='" + teacherName + '\'' +
				", subjectName='" + subjectName + '\'' +
				", alias='" + alias + '\'' +
				", phone='" + phone + '\'' +
				", status=" + status +
				", errorFiled='" + errorFiled + '\'' +
				", errorInfo='" + errorInfo + '\'' +
				", code='" + code + '\'' +
				", subjectTeacherId=" + subjectTeacherId +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		boolean flag = false;
		if (obj instanceof SubjectTeacherTmp) {
			SubjectTeacherTmp tmp = (SubjectTeacherTmp) obj;
			if (tmp.getGradeName().equals(this.getGradeName())
					&& tmp.getTeamNumber().equals(this.getTeamNumber())
					&& tmp.getSubjectName().equals(this.getSubjectName())
					&& tmp.getStatus().equals(this.getStatus())
					&& tmp.getErrorFiled().equals(this.getErrorFiled())
					&& tmp.getErrorInfo().equals(this.getErrorInfo())
					&& tmp.getCode().equals(this.getCode())
					) {
				flag = true;
				if (tmp.getGradeId() != null) {
					if (tmp.getGradeId().equals(this.getGradeId())) {
						flag = true;
					} else {
						flag = false;
					}
				}
				if (tmp.getTeamId() != null) {
					if (tmp.getTeamId().equals(this.getTeamId())) {
						flag = true;
					} else {
						flag = false;
					}
				}
				if (tmp.getTeacherName() != null) {
					if (tmp.getTeacherName().equals(this.getTeacherName())) {
						flag = true;
					} else {
						flag = false;
					}
				}
				if (tmp.getSubjectTeacherId() != null) {
					if (tmp.getSubjectTeacherId().equals(this.getSubjectTeacherId())) {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		}
		return flag;
	}
}