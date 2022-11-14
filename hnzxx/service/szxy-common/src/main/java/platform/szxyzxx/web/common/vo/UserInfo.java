package platform.szxyzxx.web.common.vo;

import java.io.Serializable;

/**
 * <p>
 * Title:UserInfo.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 *
 * @Function 功能描述：session用户信息Vo
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月25日
 */
public class UserInfo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -5179686936016731378L;

	/**
	 * 用户ID
	 */
	private Integer id;

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 原始账号
	 */
	private String originalUsername;

	/**
	 * 用户账号密码
	 */
	private String password;

	/**
	 * 
	 */
	private String inputPassword;

	/**
	 * 学校学段数组
	 */
	private String[] stageCodes;

	/**
	 * 学校学段
	 */
	private String stageCode;

	/**
	 * 学校名称
	 */
	private String schoolName;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 手机
	 */
	private String telephone;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 教师ID teacher_id
	 */
	private Integer teacherId;

	/**
	 * 学生ID student_id
	 */
	private Integer studentId;

	/**
	 * 学校代码
	 */
	private Integer schoolId;

	/**
	 * 学校的uuid全局唯一，暂只供oa模块使用
	 */
	private String schooUUID;

	/**
	 * 组ID
	 */
	private Integer groupId;

	/**
	 * 类型
	 */
	private String groupType;

	/**
	 * 用户账号状态, 0=正常 , 1=失效 , 2=锁定
	 */
	private Integer state;

	/**
	 * 学校的当前学年
	 */
	private String schoolYear;

	/**
	 * 学校的当前学期
	 */
	private String schoolTermCode;

	/**
	 * 学校的当前学期 pj_school_term id
	 */
	private Integer schoolTermId;

	/**
	 * 用户当前访问的系统代码
	 */
	private String currentSystemId;

	/**
	 * 当前用户所管理的区域代码
	 */
	private String regionCode;

	/**
	 * 当前用户所管理的区域级别 省级：1；市级：2；县级：3；乡镇：4；
	 */
	private String level;

	/**
	 * 用户所属类型 如果多种类型用逗号隔开；如1,2
	 */
	private String userTypes;

	/**
	 * 
	 */
	private String schoolLogo;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 当前用户操作的角色对象，暂仅用于班班星业务
	 */
	private String currentRoleCode;

	/**
	 * 即时通讯账号
	 */
	private String imAccount;

	public Integer getSchoolTermId() {
		return schoolTermId;
	}

	public void setSchoolTermId(Integer schoolTermId) {
		this.schoolTermId = schoolTermId;
	}

	public String getImAccount() {
		return imAccount;
	}

	public void setImAccount(String imAccount) {
		this.imAccount = imAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCurrentSystemId() {
		return currentSystemId;
	}

	public void setCurrentSystemId(String currentSystemId) {
		this.currentSystemId = currentSystemId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String[] getStageCodes() {
		return stageCodes;
	}

	public void setStageCodes(String[] stageCodes) {
		this.stageCodes = stageCodes;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSchoolTermCode() {
		return schoolTermCode;
	}

	public void setSchoolTermCode(String schoolTermCode) {
		this.schoolTermCode = schoolTermCode;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSchoolLogo() {
		return schoolLogo;
	}

	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	/**
	 * @Method getSchooUUID
	 * @Function 功能描述：学校的uuid全局唯一，暂只供oa模块使用
	 * @return
	 * @Date 2015年7月3日
	 */
	public String getSchooUUID() {
		return schooUUID;
	}

	/**
	 * @Method setSchooUUID
	 * @Function 功能描述：学校的uuid全局唯一，暂只供oa模块使用
	 * @param schooUUID
	 * @Date 2015年7月3日
	 */
	public void setSchooUUID(String schooUUID) {
		this.schooUUID = schooUUID;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentRoleCode() {
		return currentRoleCode;
	}

	public void setCurrentRoleCode(String currentRoleCode) {
		this.currentRoleCode = currentRoleCode;
	}

	public String getOriginalUsername() {
		return originalUsername;
	}

	public void setOriginalUsername(String originalUsername) {
		this.originalUsername = originalUsername;
	}

}
