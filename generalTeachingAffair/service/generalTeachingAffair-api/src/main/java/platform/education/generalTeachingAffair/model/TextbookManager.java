package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * TextbookManager
 * @author AutoCreate
 *
 */
public class TextbookManager implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 使用学段
	 */
	private String stageCode;
	/**
	 * 年级
	 */
	private String gradeCode;
	/**
	 * 适用学科
	 */
	private String subjectCode;
	/**
	 * 册次，卷
	 */
	private String volumn;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 版本类型，如人教版
	 */
	private Integer publisherId;
	/**
	 * isbn 国际标准书号（International Standard Book Number），简称ISBN，是专门为识别图书等文献而设计的国际编号
	 */
	private String isbn;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyTime;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 书籍类型
	 */
	private String type;
	/**
	 * 是否删除，0==不删除，1===删除
	 */
	private Boolean isDelete;
	/**
	 * 数量
	 */
	private String count;
	/**
	 * 数量类型-手工填写1，还是自动从班级获取0
	 */
	private String countType;
	/**
	 * 添加类型，默认为从基础教材表获取 0 ，现在不存在手动添加 1
	 */
	private String addType;
	/**
	 * 学校
	 */
	private Integer schoolId;
	/**
	 * 学年
	 */
	private String schoolYear;
	/**
	 * 学期
	 */
	private String termCode;
	
	private String teacherName;
	private Integer gradeId;
	private Integer teamId;
	private Integer teacherId;
	//存储各个code id的实际值
	private String stageCodeName;
	private String gradeCodeName;
	private String subjectCodeName;
	private String volumnName;
	private String versionName;
	private String gradeIdName;
	private String teamIdName;
	
	
	
	public String getStageCodeName() {
		return stageCodeName;
	}

	public void setStageCodeName(String stageCodeName) {
		this.stageCodeName = stageCodeName;
	}

	public String getGradeCodeName() {
		return gradeCodeName;
	}

	public void setGradeCodeName(String gradeCodeName) {
		this.gradeCodeName = gradeCodeName;
	}

	public String getSubjectCodeName() {
		return subjectCodeName;
	}

	public void setSubjectCodeName(String subjectCodeName) {
		this.subjectCodeName = subjectCodeName;
	}

	public String getVolumnName() {
		return volumnName;
	}

	public void setVolumnName(String volumnName) {
		this.volumnName = volumnName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getGradeIdName() {
		return gradeIdName;
	}

	public void setGradeIdName(String gradeIdName) {
		this.gradeIdName = gradeIdName;
	}

	public String getTeamIdName() {
		return teamIdName;
	}

	public void setTeamIdName(String teamIdName) {
		this.teamIdName = teamIdName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public TextbookManager() {
		
	}
	
	public TextbookManager(Integer id) {
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
	 * 获取使用学段
	 * @return java.lang.String
	 */
	public String getStageCode() {
		return this.stageCode;
	}
	
	/**
	 * 设置使用学段
	 * @param stageCode
	 * @type java.lang.String
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	
	/**
	 * 获取年级
	 * @return java.lang.String
	 */
	public String getGradeCode() {
		return this.gradeCode;
	}
	
	/**
	 * 设置年级
	 * @param gradeCode
	 * @type java.lang.String
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	/**
	 * 获取适用学科
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置适用学科
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取册次，卷
	 * @return java.lang.String
	 */
	public String getVolumn() {
		return this.volumn;
	}
	
	/**
	 * 设置册次，卷
	 * @param volumn
	 * @type java.lang.String
	 */
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	
	/**
	 * 获取版本
	 * @return java.lang.String
	 */
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * 设置版本
	 * @param version
	 * @type java.lang.String
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 获取名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取版本类型，如人教版
	 * @return java.lang.Integer
	 */
	public Integer getPublisherId() {
		return this.publisherId;
	}
	
	/**
	 * 设置版本类型，如人教版
	 * @param publisherId
	 * @type java.lang.Integer
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	
	/**
	 * 获取isbn 国际标准书号（International Standard Book Number），简称ISBN，是专门为识别图书等文献而设计的国际编号
	 * @return java.lang.String
	 */
	public String getIsbn() {
		return this.isbn;
	}
	
	/**
	 * 设置isbn 国际标准书号（International Standard Book Number），简称ISBN，是专门为识别图书等文献而设计的国际编号
	 * @param isbn
	 * @type java.lang.String
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyTime
	 * @type java.util.Date
	 */
	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	/**
	 * 获取价格
	 * @return java.lang.String
	 */
	public String getPrice() {
		return this.price;
	}
	
	/**
	 * 设置价格
	 * @param price
	 * @type java.lang.String
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * 获取书籍类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置书籍类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
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
	 * 获取数量
	 * @return java.lang.String
	 */
	public String getCount() {
		return this.count;
	}
	
	/**
	 * 设置数量
	 * @param count
	 * @type java.lang.String
	 */
	public void setCount(String count) {
		this.count = count;
	}
	
	/**
	 * 获取数量类型-手工填写1，还是自动从班级获取0
	 * @return java.lang.String
	 */
	public String getCountType() {
		return this.countType;
	}
	
	/**
	 * 设置数量类型-手工填写1，还是自动从班级获取0
	 * @param countType
	 * @type java.lang.String
	 */
	public void setCountType(String countType) {
		this.countType = countType;
	}
	
	/**
	 * 获取添加类型，默认为从基础教材表获取 0 ，现在不存在手动添加 1
	 * @return java.lang.String
	 */
	public String getAddType() {
		return this.addType;
	}
	
	/**
	 * 设置添加类型，默认为从基础教材表获取 0 ，现在不存在手动添加 1
	 * @param addType
	 * @type java.lang.String
	 */
	public void setAddType(String addType) {
		this.addType = addType;
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
	 * 获取学年
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学期
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学期
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
}