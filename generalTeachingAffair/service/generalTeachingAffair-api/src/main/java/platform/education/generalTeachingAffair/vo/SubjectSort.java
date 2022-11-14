package platform.education.generalTeachingAffair.vo;

public class SubjectSort implements Comparable{
	
	private Integer scoreNum;
	
	public Integer getScoreNum() {
		return scoreNum;
	}

	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}
	/**
	 * 主健
	 */
	private Integer id;
	
	/**
	 * 课程代码
	 */
	private String code;
	/**
	 * 学校开设的课程
	 */
	private Integer schoolId;
	
	
	/**
	 * 学段代码
	 */
	private String stageCode;
	/**
	 * 科目名称
	 */
	private String name;
	/**
	 * 编号：排序次序
	 */
	private Integer listOrder;
	/**
	 * 课程级别 1=公共课程 2=地方课程 3=校本课程
	 */
	private String subjectClass;
	/**
	 * 课程类别 1=理论类 2=语言类 3=实验（实训）类 4=体育类 5=实践类 6=艺术类 9=其它类
	 */
	private String subjectType;
	/**
	 * 课程属性 1=必修 2=限选  3=任选 4=辅修 5=实践
	 */
	private String subjectProperty;
	/**
	 * 课程性质
	 */
	private String subjectCharacter;
	/**
	 * 删除标识
	 */
	private boolean isDelete;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	

	/**
	 * 获取主健
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主健
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取课程代码
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置课程代码
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取学校开设的课程
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校开设的课程
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取学段代码
	 * @return java.lang.String
	 */
	public String getStageCode() {
		return this.stageCode;
	}
	
	/**
	 * 设置学段代码
	 * @param stageCode
	 * @type java.lang.String
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	
	/**
	 * 获取科目名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置科目名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取编号：排序次序
	 * @return java.lang.Integer
	 */
	public Integer getListOrder() {
		return this.listOrder;
	}
	
	/**
	 * 设置编号：排序次序
	 * @param listOrder
	 * @type java.lang.Integer
	 */
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	
	/**
	 * 获取课程级别 1=公共课程 2=地方课程 3=校本课程
	 * @return java.lang.String
	 */
	public String getSubjectClass() {
		return this.subjectClass;
	}
	
	/**
	 * 设置课程级别 1=公共课程 2=地方课程 3=校本课程
	 * @param subjectClass
	 * @type java.lang.String
	 */
	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}
	
	/**
	 * 获取课程类别 1=理论类 2=语言类 3=实验（实训）类 4=体育类 5=实践类 6=艺术类 9=其它类
	 * @return java.lang.String
	 */
	public String getSubjectType() {
		return this.subjectType;
	}
	
	/**
	 * 设置课程类别 1=理论类 2=语言类 3=实验（实训）类 4=体育类 5=实践类 6=艺术类 9=其它类
	 * @param subjectType
	 * @type java.lang.String
	 */
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	/**
	 * 获取课程属性 1=必修 2=限选  3=任选 4=辅修 5=实践
	 * @return java.lang.String
	 */
	public String getSubjectProperty() {
		return this.subjectProperty;
	}
	
	/**
	 * 设置课程属性 1=必修 2=限选  3=任选 4=辅修 5=实践
	 * @param subjectProperty
	 * @type java.lang.String
	 */
	public void setSubjectProperty(String subjectProperty) {
		this.subjectProperty = subjectProperty;
	}
	
	/**
	 * 获取课程性质
	 * @return java.lang.String
	 */
	public String getSubjectCharacter() {
		return this.subjectCharacter;
	}
	
	/**
	 * 设置课程性质
	 * @param subjectCharacter
	 * @type java.lang.String
	 */
	public void setSubjectCharacter(String subjectCharacter) {
		this.subjectCharacter = subjectCharacter;
	}
	
	/**
	 * 获取删除标识
	 * @return java.lang.String
	 */
	public boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标识
	 * @param isDelete
	 * @type java.lang.String
	 */
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int compareTo(Object o) {
		int result = 0;
		SubjectSort subjectSort = (SubjectSort)o;
		if(subjectSort != null&&this.code != null&&subjectSort.getCode() != null){
			result = this.code.compareTo(subjectSort.getCode());
		}
		
		return result;
	}
	


}
