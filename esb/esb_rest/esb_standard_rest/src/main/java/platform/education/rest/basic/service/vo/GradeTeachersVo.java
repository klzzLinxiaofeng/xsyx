package platform.education.rest.basic.service.vo;

import java.io.Serializable;

public class GradeTeachersVo implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/**
		 * 主健ID
		 */
		private Integer id;
		/**
		 * 所在学校
		 */
		private Integer schoolId;
		/**
		 * 对应的人
		 */
		private Integer personId;
		/**
		 * 用户帐号
		 */
		private Integer userId;
		/**
		 * 用户名
		 */
		private String userName;
		/**
		 * 姓名
		 */
		private String name;
		/**
		 * 性别
		 */
		private String sex;
		/**
		 * 全国统一工号
		 */
		private String jobNumber;
		/**
		 * 入职时间
		 */
		private java.util.Date enrollDate;
		/**
		 * 离职时间
		 */
		private java.util.Date leaveDate;
		/**
		 * 参加工作时间
		 */
		private java.util.Date workBeginDate;
		/**
		 * 学历
		 */
		private String qualification;
		/**
		 * 岗位职业码
		 */
		private String occupationCode;
		/**
		 * 职务
		 */
		private String position;
		/**
		 * 手机
		 */
		private String mobile;
		/**
		 * 办公电话
		 */
		private String telephone;
		/**
		 * 岗位编制 1=教学 2=行政 3=教辅 4=工勤 5= 校企 9=其它 0=无
		 */
		private String postStaffing;
		/**
		 * 工作状态 11=在职 01=退休 02=离休 03=死亡 05=调出 06=辞职 07=离职 08=开除 14=长假 15=因公出国16=停薪留职 18=合同终止 99=其它
		 */
		private String jobState;
		/**
		 * 别名
		 */
		private String alias;
		/**
		 * 部门ID
		 */
		private Integer deptId;
		/**
		 * 部门名称
		 */
		private String deptName;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getSchoolId() {
			return schoolId;
		}
		public void setSchoolId(Integer schoolId) {
			this.schoolId = schoolId;
		}
		public Integer getPersonId() {
			return personId;
		}
		public void setPersonId(Integer personId) {
			this.personId = personId;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getJobNumber() {
			return jobNumber;
		}
		public void setJobNumber(String jobNumber) {
			this.jobNumber = jobNumber;
		}
		public java.util.Date getEnrollDate() {
			return enrollDate;
		}
		public void setEnrollDate(java.util.Date enrollDate) {
			this.enrollDate = enrollDate;
		}
		public java.util.Date getLeaveDate() {
			return leaveDate;
		}
		public void setLeaveDate(java.util.Date leaveDate) {
			this.leaveDate = leaveDate;
		}
		public java.util.Date getWorkBeginDate() {
			return workBeginDate;
		}
		public void setWorkBeginDate(java.util.Date workBeginDate) {
			this.workBeginDate = workBeginDate;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getOccupationCode() {
			return occupationCode;
		}
		public void setOccupationCode(String occupationCode) {
			this.occupationCode = occupationCode;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getPostStaffing() {
			return postStaffing;
		}
		public void setPostStaffing(String postStaffing) {
			this.postStaffing = postStaffing;
		}
		public String getJobState() {
			return jobState;
		}
		public void setJobState(String jobState) {
			this.jobState = jobState;
		}
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
		public Integer getDeptId() {
			return deptId;
		}
		public void setDeptId(Integer deptId) {
			this.deptId = deptId;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
	}
