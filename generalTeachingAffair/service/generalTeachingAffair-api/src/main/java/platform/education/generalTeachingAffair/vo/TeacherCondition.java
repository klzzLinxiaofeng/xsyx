package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.Teacher;

/**
 * Teacher
 * 
 * @author AutoCreate
 *
 */
public class TeacherCondition extends Teacher {
	private static final long serialVersionUID = 1L;

	private Integer deptId;

	private String subjectCode;
	private String ids;
		
	private boolean excludeSelf = false;//是否排除自身
	
	private Boolean enableMultiCampus = false; //是否多校区  

	private String IdCardNumber; // 身份证号码
	/*
	 * 工作状态 11=在职 01=退休 02=离休 03=死亡 05=调出 06=辞职 07=离职 08=开除 14=长假 15=因公出国16=停薪留职
	 * 18=合同终止 99=其它
	 */
	public static String DEFAULT_ZAIZHI = "11";
	public static String DEFAULT_TUIXIU = "01";
	public static String DEFAULT_LIXIU = "02";
	public static String DEFAULT_SIWANG = "03";
	public static String DEFAULT_DIAOCHU = "05";
	public static String DEFAULT_CIZHI = "06";
	public static String DEFAULT_LIZHI = "07";
	public static String DEFAULT_KAICHU = "08";
	public static String DEFAULT_CHANGJIA = "14";
	public static String DEFAULT_YINGONGCHUGUO = "15";
	public static String DEFAULT_TINGXINLIUZHI = "16";
	public static String DEFAULT_HETONGZHONGZHI = "18";
	public static String DEFAULT_QITA = "99";

	// ======用于手机端分页，new_or_old 表示 baselineDate 这个时间的之前还是之后
	// page_num表示每页的记录数===//
	private String new_or_old;

	private String baselineDate;

	public String getNew_or_old() {
		return new_or_old;
	}

	public void setNew_or_old(String new_or_old) {
		this.new_or_old = new_or_old;
	}

	public String getBaselineDate() {
		return baselineDate;
	}

	public void setBaselineDate(String baselineDate) {
		this.baselineDate = baselineDate;
	}

	// =============================================================//

	private Boolean userNameLike = false;// 是否使用模糊查询，默认为否
	private Boolean nameLike = false;// 是否使用模糊查询，默认为否
	
	// 添加于2015-11-18
	/**
	 * 别名是否使用模糊查询，默认为否
	 */
	private Boolean aliasLike = false;

	// 添加于2015-11-18

	public Integer getDeptId() {
		return deptId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getIdCardNumber() {
		return IdCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		IdCardNumber = idCardNumber;
	}

	public Boolean getUserNameLike() {
		return userNameLike;
	}

	public void setUserNameLike(Boolean userNameLike) {
		this.userNameLike = userNameLike;
	}

	public Boolean getNameLike() {
		return nameLike;
	}

	public void setNameLike(Boolean nameLike) {
		this.nameLike = nameLike;
	}

	public boolean isExcludeSelf() {
		return excludeSelf;
	}

	public void setExcludeSelf(boolean excludeSelf) {
		this.excludeSelf = excludeSelf;
	}

	public Boolean getAliasLike() {
		return aliasLike;
	}

	public void setAliasLike(Boolean aliasLike) {
		this.aliasLike = aliasLike;
	}

	
	public Boolean getEnableMultiCampus() {
		return enableMultiCampus;
	}
	
	
	public void setEnableMultiCampus(Boolean enableMultiCampus) {
		this.enableMultiCampus = enableMultiCampus;
	}
	
	
}