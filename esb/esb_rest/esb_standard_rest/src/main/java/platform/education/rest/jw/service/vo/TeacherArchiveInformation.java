package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import platform.education.generalTeachingAffair.model.TeacherDetailInfo;

public class TeacherArchiveInformation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String name;
	private TeacherDetailInfo basic;
	private TeacherAssistVo assist;
	private List<SalaryFieldValue> valueList;
	
	public List<SalaryFieldValue> getValueList() {
		return valueList;
	}
	public void setValueList(List<SalaryFieldValue> valueList) {
		this.valueList = valueList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TeacherDetailInfo getBasic() {
		return basic;
	}
	public void setBasic(TeacherDetailInfo basic) {
		this.basic = basic;
	}
	public TeacherAssistVo getAssist() {
		return assist;
	}
	public void setAssist(TeacherAssistVo assist) {
		this.assist = assist;
	}
	public class TeacherAssistVo{
		
		/**
		 * 年龄
		 */
		private int age;
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
		
		
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getJoinReason() {
			return joinReason;
		}
		public void setJoinReason(String joinReason) {
			this.joinReason = joinReason;
		}
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String identity) {
			this.identity = identity;
		}
		public String getActualPosition() {
			return actualPosition;
		}
		public void setActualPosition(String actualPosition) {
			this.actualPosition = actualPosition;
		}
		public String getManagementType() {
			return managementType;
		}
		public void setManagementType(String managementType) {
			this.managementType = managementType;
		}
		public java.util.Date getCurrentPositionDate() {
			return currentPositionDate;
		}
		public void setCurrentPositionDate(java.util.Date currentPositionDate) {
			this.currentPositionDate = currentPositionDate;
		}
		public String getPersonnelFundsType() {
			return personnelFundsType;
		}
		public void setPersonnelFundsType(String personnelFundsType) {
			this.personnelFundsType = personnelFundsType;
		}
		public java.util.Date getCurrentPositionPeriod() {
			return currentPositionPeriod;
		}
		public void setCurrentPositionPeriod(java.util.Date currentPositionPeriod) {
			this.currentPositionPeriod = currentPositionPeriod;
		}
		public java.util.Date getJoinPartyDate() {
			return joinPartyDate;
		}
		public void setJoinPartyDate(java.util.Date joinPartyDate) {
			this.joinPartyDate = joinPartyDate;
		}
		public String getLowPost() {
			return lowPost;
		}
		public void setLowPost(String lowPost) {
			this.lowPost = lowPost;
		}
		public String getCheckInterrupt() {
			return checkInterrupt;
		}
		public void setCheckInterrupt(String checkInterrupt) {
			this.checkInterrupt = checkInterrupt;
		}
		public java.util.Date getLowPostDate() {
			return lowPostDate;
		}
		public void setLowPostDate(java.util.Date lowPostDate) {
			this.lowPostDate = lowPostDate;
		}
		public String getInterruptworkYears() {
			return interruptworkYears;
		}
		public void setInterruptworkYears(String interruptworkYears) {
			this.interruptworkYears = interruptworkYears;
		}
		public String getCadrePosts() {
			return cadrePosts;
		}
		public void setCadrePosts(String cadrePosts) {
			this.cadrePosts = cadrePosts;
		}
		public String getWorkYears() {
			return workYears;
		}
		public void setWorkYears(String workYears) {
			this.workYears = workYears;
		}
		public String getRankType() {
			return rankType;
		}
		public void setRankType(String rankType) {
			this.rankType = rankType;
		}
		public String getAccruedAge() {
			return accruedAge;
		}
		public void setAccruedAge(String accruedAge) {
			this.accruedAge = accruedAge;
		}
		public Date getRankChangeDate() {
			return rankChangeDate;
		}
		public void setRankChangeDate(Date rankChangeDate) {
			this.rankChangeDate = rankChangeDate;
		}
		public String getFirstDegree() {
			return firstDegree;
		}
		public void setFirstDegree(String firstDegree) {
			this.firstDegree = firstDegree;
		}
		public String getTechnicalPosition() {
			return technicalPosition;
		}
		public void setTechnicalPosition(String technicalPosition) {
			this.technicalPosition = technicalPosition;
		}
		public String getFristDegreeGraduateSchool() {
			return fristDegreeGraduateSchool;
		}
		public void setFristDegreeGraduateSchool(String fristDegreeGraduateSchool) {
			this.fristDegreeGraduateSchool = fristDegreeGraduateSchool;
		}
		public String getNonLeadershipFlag() {
			return nonLeadershipFlag;
		}
		public void setNonLeadershipFlag(String nonLeadershipFlag) {
			this.nonLeadershipFlag = nonLeadershipFlag;
		}
		public String getFirstDegreeMajor() {
			return firstDegreeMajor;
		}
		public void setFirstDegreeMajor(String firstDegreeMajor) {
			this.firstDegreeMajor = firstDegreeMajor;
		}
		public String getPost() {
			return post;
		}
		public void setPost(String post) {
			this.post = post;
		}
		public String getPostType() {
			return postType;
		}
		public void setPostType(String postType) {
			this.postType = postType;
		}
		public String getWages() {
			return wages;
		}
		public void setWages(String wages) {
			this.wages = wages;
		}
		public String getHighestDegree() {
			return highestDegree;
		}
		public void setHighestDegree(String highestDegree) {
			this.highestDegree = highestDegree;
		}
		public String getExaminationResult() {
			return examinationResult;
		}
		public void setExaminationResult(String examinationResult) {
			this.examinationResult = examinationResult;
		}
	}
	
}
