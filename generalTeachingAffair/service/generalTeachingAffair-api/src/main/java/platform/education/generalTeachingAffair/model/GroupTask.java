package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * GroupTask
 * @author AutoCreate
 *
 */
public class GroupTask implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * pj_school.id
	 */
	private Integer schoolId;
	/**
	 * 课时
	 */
	private java.util.Date classHour;
	/**
	 * 第几节课
	 */
	private Integer classPeriod;
	/**
	 * pj_group.id
	 */
	private Integer groupId;
	/**
	 * 题目标题
	 */
	private String name;
	/**
	 * 分数
	 */
	private Float grades;
	/**
	 * 题目序号
	 */
	private Integer questionNum;
	/**
	 * 题目图片
	 */
	private String questionFiles;
	/**
	 * 上传题目的teacherID
	 */
	private Integer teacherId;
	/**
	 * 分组序号，属于哪个组（组1，组2）
	 */
	private Integer groupNumber;
	/**
	 * 上传答案学生ID
	 */
	private Integer studentId;
	/**
	 * 学生名称
	 */
	private String studentName;
	/**
	 * 回答图片
	 */
	private String answerFiles;
	/**
	 * 提交时间
	 */
	private java.util.Date submissionTime;
	/**
	 * 得分
	 */
	private Float score;
    /**
     * 是否提交答案 0=false 1=true
     */
    private Boolean isSubmit;
    /**
     * 是否分配得分 0=false 1=true
     */
    private Boolean isAllocation;
	/**
	 * 创建时间（申请时间）
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	
	public GroupTask() {
		
	}
	
	public GroupTask(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取pj_school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置pj_school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取课时
	 * @return java.util.Date
	 */
	public java.util.Date getClassHour() {
		return this.classHour;
	}
	
	/**
	 * 设置课时
	 * @param classHour
	 * @type java.util.Date
	 */
	public void setClassHour(java.util.Date classHour) {
		this.classHour = classHour;
	}
	
	/**
	 * 获取第几节课
	 * @return java.lang.Integer
	 */
	public Integer getClassPeriod() {
		return this.classPeriod;
	}
	
	/**
	 * 设置第几节课
	 * @param classPeriod
	 * @type java.lang.Integer
	 */
	public void setClassPeriod(Integer classPeriod) {
		this.classPeriod = classPeriod;
	}
	
	/**
	 * 获取pj_group.id
	 * @return java.lang.Integer
	 */
	public Integer getGroupId() {
		return this.groupId;
	}
	
	/**
	 * 设置pj_group.id
	 * @param groupId
	 * @type java.lang.Integer
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 获取题目标题
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置题目标题
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取分数
	 * @return java.lang.Float
	 */
	public Float getGrades() {
		return this.grades;
	}
	
	/**
	 * 设置分数
	 * @param grades
	 * @type java.lang.Float
	 */
	public void setGrades(Float grades) {
		this.grades = grades;
	}
	
	/**
	 * 获取题目序号
	 * @return java.lang.Integer
	 */
	public Integer getQuestionNum() {
		return this.questionNum;
	}
	
	/**
	 * 设置题目序号
	 * @param questionNum
	 * @type java.lang.Integer
	 */
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	
	/**
	 * 获取题目图片
	 * @return java.lang.String
	 */
	public String getQuestionFiles() {
		return this.questionFiles;
	}
	
	/**
	 * 设置题目图片
	 * @param questionFiles
	 * @type java.lang.String
	 */
	public void setQuestionFiles(String questionFiles) {
		this.questionFiles = questionFiles;
	}
	
	/**
	 * 获取上传题目的teacherID
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置上传题目的teacherID
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取分组序号，属于哪个组（组1，组2）
	 * @return java.lang.Integer
	 */
	public Integer getGroupNumber() {
		return this.groupNumber;
	}
	
	/**
	 * 设置分组序号，属于哪个组（组1，组2）
	 * @param groupNumber
	 * @type java.lang.Integer
	 */
	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	/**
	 * 获取上传答案学生ID
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置上传答案学生ID
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取学生名称
	 * @return java.lang.String
	 */
	public String getStudentName() {
		return this.studentName;
	}
	
	/**
	 * 设置学生名称
	 * @param studentName
	 * @type java.lang.String
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 获取回答图片
	 * @return java.lang.String
	 */
	public String getAnswerFiles() {
		return this.answerFiles;
	}
	
	/**
	 * 设置回答图片
	 * @param answerFiles
	 * @type java.lang.String
	 */
	public void setAnswerFiles(String answerFiles) {
		this.answerFiles = answerFiles;
	}
	
	/**
	 * 获取提交时间
	 * @return java.util.Date
	 */
	public java.util.Date getSubmissionTime() {
		return this.submissionTime;
	}
	
	/**
	 * 设置提交时间
	 * @param submissionTime
	 * @type java.util.Date
	 */
	public void setSubmissionTime(java.util.Date submissionTime) {
		this.submissionTime = submissionTime;
	}
	
	/**
	 * 获取得分
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置得分
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取0=false 1=true
	 * @return java.lang.Boolean
	 */
	public Boolean getIsSubmit() {
		return this.isSubmit;
	}
	
	/**
	 * 设置0=false 1=true
	 * @param isSubmit
	 * @type java.lang.Boolean
	 */
	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

    /**
     * 获取是否分配得分 0=false 1=true
     * @return java.lang.Boolean
     */
    public Boolean getIsAllocation() {
        return this.isAllocation;
    }

    /**
     * 设置是否分配得分 0=false 1=true
     * @param isAllocation
     * @type java.lang.Boolean
     */
    public void setIsAllocation(Boolean isAllocation) {
        this.isAllocation = isAllocation;
    }

	/**
	 * 获取创建时间（申请时间）
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间（申请时间）
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}