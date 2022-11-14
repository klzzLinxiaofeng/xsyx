package platform.education.generalTeachingAffair.model;

import java.util.Date;

import framework.generic.dao.Model;
/**
 * TeacherAssist
 * @author AutoCreate
 *
 */
public class TeacherAssist implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学校
	 */
	private Integer schoolId;
	/**
	 * 用户
	 */
	private Integer userId;
	/**
	 * 进入单位原因
	 */
	private String joinReason;
	/**
	 * 学位
	 */
	private String degree;
	/**
	 * 身份
	 */
	private String identity;
	/**
	 * 时任职务
	 */
	private String actualPosition;
	/**
	 * 管理方式
	 */
	private String managementType;
	/**
	 * 当前职务任命时间
	 */
	private java.util.Date currentPositionDate;
	/**
	 * 人员经费形式
	 */
	private String personnelFundsType;
	/**
	 * 当前职务任命期限
	 */
	private java.util.Date currentPositionPeriod;
	/**
	 * 入党时间
	 */
	private java.util.Date joinPartyDate;
	/**
	 * 低一职务
	 */
	private String lowPost;
	/**
	 * 考核中断
	 */
	private String checkInterrupt;
	/**
	 * 低一职务任命时间
	 */
	private java.util.Date lowPostDate;
	/**
	 * 中断工龄
	 */
	private String interruptworkYears;
	/**
	 * 干部职务名称
	 */
	private String cadrePosts;
	/**
	 * 工龄
	 */
	private String workYears;
	/**
	 * 职级分类
	 */
	private String rankType;
	/**
	 * 应计学龄
	 */
	private String accruedAge;
	/**
	 * 职级变动时间
	 */
	private Date rankChangeDate;
	/**
	 * 第一学历
	 */
	private String firstDegree;
	/**
	 * 专业技术职业
	 */
	private String technicalPosition;
	/**
	 * 第一学历毕业学校
	 */
	private String fristDegreeGraduateSchool;
	/**
	 * 非领导标志
	 */
	private String nonLeadershipFlag;
	/**
	 * 第一学历所学专业
	 */
	private String firstDegreeMajor;
	/**
	 * 职务
	 */
	private String post;
	/**
	 * 岗位职务
	 */
	private String postType;
	/**
	 * 工资待遇
	 */
	private String wages;
	/**
	 * 最高学位
	 */
	private String highestDegree;
	/**
	 * 考核结果
	 */
	private String examinationResult;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标志
	 */
	private Boolean isDelete;
	
	public TeacherAssist() {
		
	}
	
	public TeacherAssist(Integer id) {
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
	 * 获取学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取用户
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取进入单位原因
	 * @return java.lang.String
	 */
	public String getJoinReason() {
		return this.joinReason;
	}
	
	/**
	 * 设置进入单位原因
	 * @param joinReason
	 * @type java.lang.String
	 */
	public void setJoinReason(String joinReason) {
		this.joinReason = joinReason;
	}
	
	/**
	 * 获取学位
	 * @return java.lang.String
	 */
	public String getDegree() {
		return this.degree;
	}
	
	/**
	 * 设置学位
	 * @param degree
	 * @type java.lang.String
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	/**
	 * 获取身份
	 * @return java.lang.String
	 */
	public String getIdentity() {
		return this.identity;
	}
	
	/**
	 * 设置身份
	 * @param identity
	 * @type java.lang.String
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	/**
	 * 获取时任职务
	 * @return java.lang.String
	 */
	public String getActualPosition() {
		return this.actualPosition;
	}
	
	/**
	 * 设置时任职务
	 * @param actualPosition
	 * @type java.lang.String
	 */
	public void setActualPosition(String actualPosition) {
		this.actualPosition = actualPosition;
	}
	
	/**
	 * 获取管理方式
	 * @return java.lang.String
	 */
	public String getManagementType() {
		return this.managementType;
	}
	
	/**
	 * 设置管理方式
	 * @param managementType
	 * @type java.lang.String
	 */
	public void setManagementType(String managementType) {
		this.managementType = managementType;
	}
	
	/**
	 * 获取当前职务任命时间
	 * @return java.util.Date
	 */
	public java.util.Date getCurrentPositionDate() {
		return this.currentPositionDate;
	}
	
	/**
	 * 设置当前职务任命时间
	 * @param currentPositionDate
	 * @type java.util.Date
	 */
	public void setCurrentPositionDate(java.util.Date currentPositionDate) {
		this.currentPositionDate = currentPositionDate;
	}
	
	/**
	 * 获取人员经费形式
	 * @return java.lang.String
	 */
	public String getPersonnelFundsType() {
		return this.personnelFundsType;
	}
	
	/**
	 * 设置人员经费形式
	 * @param personnelFundsType
	 * @type java.lang.String
	 */
	public void setPersonnelFundsType(String personnelFundsType) {
		this.personnelFundsType = personnelFundsType;
	}
	
	/**
	 * 获取当前职务任命期限
	 * @return java.util.Date
	 */
	public java.util.Date getCurrentPositionPeriod() {
		return this.currentPositionPeriod;
	}
	
	/**
	 * 设置当前职务任命期限
	 * @param currentPositionPeriod
	 * @type java.util.Date
	 */
	public void setCurrentPositionPeriod(java.util.Date currentPositionPeriod) {
		this.currentPositionPeriod = currentPositionPeriod;
	}
	
	/**
	 * 获取入党时间
	 * @return java.util.Date
	 */
	public java.util.Date getJoinPartyDate() {
		return this.joinPartyDate;
	}
	
	/**
	 * 设置入党时间
	 * @param joinPartyDate
	 * @type java.util.Date
	 */
	public void setJoinPartyDate(java.util.Date joinPartyDate) {
		this.joinPartyDate = joinPartyDate;
	}
	
	/**
	 * 获取低一职务
	 * @return java.lang.String
	 */
	public String getLowPost() {
		return this.lowPost;
	}
	
	/**
	 * 设置低一职务
	 * @param lowPost
	 * @type java.lang.String
	 */
	public void setLowPost(String lowPost) {
		this.lowPost = lowPost;
	}
	
	/**
	 * 获取考核中断
	 * @return java.lang.String
	 */
	public String getCheckInterrupt() {
		return this.checkInterrupt;
	}
	
	/**
	 * 设置考核中断
	 * @param checkInterrupt
	 * @type java.lang.String
	 */
	public void setCheckInterrupt(String checkInterrupt) {
		this.checkInterrupt = checkInterrupt;
	}
	
	/**
	 * 获取低一职务任命时间
	 * @return java.util.Date
	 */
	public java.util.Date getLowPostDate() {
		return this.lowPostDate;
	}
	
	/**
	 * 设置低一职务任命时间
	 * @param lowPostDate
	 * @type java.util.Date
	 */
	public void setLowPostDate(java.util.Date lowPostDate) {
		this.lowPostDate = lowPostDate;
	}
	
	/**
	 * 获取中断工龄
	 * @return java.lang.String
	 */
	public String getInterruptworkYears() {
		return this.interruptworkYears;
	}
	
	/**
	 * 设置中断工龄
	 * @param interruptworkYears
	 * @type java.lang.String
	 */
	public void setInterruptworkYears(String interruptworkYears) {
		this.interruptworkYears = interruptworkYears;
	}
	
	/**
	 * 获取干部职务名称
	 * @return java.lang.String
	 */
	public String getCadrePosts() {
		return this.cadrePosts;
	}
	
	/**
	 * 设置干部职务名称
	 * @param cadrePosts
	 * @type java.lang.String
	 */
	public void setCadrePosts(String cadrePosts) {
		this.cadrePosts = cadrePosts;
	}
	
	/**
	 * 获取工龄
	 * @return java.lang.String
	 */
	public String getWorkYears() {
		return this.workYears;
	}
	
	/**
	 * 设置工龄
	 * @param workYears
	 * @type java.lang.String
	 */
	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}
	
	/**
	 * 获取职级分类
	 * @return java.lang.String
	 */
	public String getRankType() {
		return this.rankType;
	}
	
	/**
	 * 设置职级分类
	 * @param rankType
	 * @type java.lang.String
	 */
	public void setRankType(String rankType) {
		this.rankType = rankType;
	}
	
	/**
	 * 获取应计学龄
	 * @return java.lang.String
	 */
	public String getAccruedAge() {
		return this.accruedAge;
	}
	
	/**
	 * 设置应计学龄
	 * @param accruedAge
	 * @type java.lang.String
	 */
	public void setAccruedAge(String accruedAge) {
		this.accruedAge = accruedAge;
	}
	
	/**
	 * 获取职级变动时间
	 * @return java.lang.String
	 */
	public Date getRankChangeDate() {
		return this.rankChangeDate;
	}
	
	/**
	 * 设置职级变动时间
	 * @param rankChangeDate
	 * @type java.lang.String
	 */
	public void setRankChangeDate(Date rankChangeDate) {
		this.rankChangeDate = rankChangeDate;
	}
	
	/**
	 * 获取第一学历
	 * @return java.lang.String
	 */
	public String getFirstDegree() {
		return this.firstDegree;
	}
	
	/**
	 * 设置第一学历
	 * @param firstDegree
	 * @type java.lang.String
	 */
	public void setFirstDegree(String firstDegree) {
		this.firstDegree = firstDegree;
	}
	
	/**
	 * 获取专业技术职业
	 * @return java.lang.String
	 */
	public String getTechnicalPosition() {
		return this.technicalPosition;
	}
	
	/**
	 * 设置专业技术职业
	 * @param technicalPosition
	 * @type java.lang.String
	 */
	public void setTechnicalPosition(String technicalPosition) {
		this.technicalPosition = technicalPosition;
	}
	
	/**
	 * 获取第一学历毕业学校
	 * @return java.lang.String
	 */
	public String getFristDegreeGraduateSchool() {
		return this.fristDegreeGraduateSchool;
	}
	
	/**
	 * 设置第一学历毕业学校
	 * @param fristDegreeGraduateSchool
	 * @type java.lang.String
	 */
	public void setFristDegreeGraduateSchool(String fristDegreeGraduateSchool) {
		this.fristDegreeGraduateSchool = fristDegreeGraduateSchool;
	}
	
	/**
	 * 获取非领导标志
	 * @return java.lang.String
	 */
	public String getNonLeadershipFlag() {
		return this.nonLeadershipFlag;
	}
	
	/**
	 * 设置非领导标志
	 * @param nonLeadershipFlag
	 * @type java.lang.String
	 */
	public void setNonLeadershipFlag(String nonLeadershipFlag) {
		this.nonLeadershipFlag = nonLeadershipFlag;
	}
	
	/**
	 * 获取第一学历所学专业
	 * @return java.lang.String
	 */
	public String getFirstDegreeMajor() {
		return this.firstDegreeMajor;
	}
	
	/**
	 * 设置第一学历所学专业
	 * @param firstDegreeMajor
	 * @type java.lang.String
	 */
	public void setFirstDegreeMajor(String firstDegreeMajor) {
		this.firstDegreeMajor = firstDegreeMajor;
	}
	
	/**
	 * 获取职务
	 * @return java.lang.String
	 */
	public String getPost() {
		return this.post;
	}
	
	/**
	 * 设置职务
	 * @param post
	 * @type java.lang.String
	 */
	public void setPost(String post) {
		this.post = post;
	}
	
	/**
	 * 获取岗位职务
	 * @return java.lang.String
	 */
	public String getPostType() {
		return this.postType;
	}
	
	/**
	 * 设置岗位职务
	 * @param postType
	 * @type java.lang.String
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	/**
	 * 获取工资待遇
	 * @return java.lang.String
	 */
	public String getWages() {
		return this.wages;
	}
	
	/**
	 * 设置工资待遇
	 * @param wages
	 * @type java.lang.String
	 */
	public void setWages(String wages) {
		this.wages = wages;
	}
	
	/**
	 * 获取最高学位
	 * @return java.lang.String
	 */
	public String getHighestDegree() {
		return this.highestDegree;
	}
	
	/**
	 * 设置最高学位
	 * @param highestDegree
	 * @type java.lang.String
	 */
	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}
	
	/**
	 * 获取考核结果
	 * @return java.lang.String
	 */
	public String getExaminationResult() {
		return this.examinationResult;
	}
	
	/**
	 * 设置考核结果
	 * @param examinationResult
	 * @type java.lang.String
	 */
	public void setExaminationResult(String examinationResult) {
		this.examinationResult = examinationResult;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
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
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标志
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}