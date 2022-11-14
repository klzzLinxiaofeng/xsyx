package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SeatChart
 * @author AutoCreate
 *
 */
public class SeatChart implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所属学校
	 */
	private Integer schoolId;
	/**
	 * schoolYear
	 */
	private String schoolYear;
	/**
	 * 教室编号
	 */
	private Integer classroomId;
	/**
	 * 教室名称
	 */
	private String classroomName;
	/**
	 * classroomType
	 */
	private String classroomType;
	/**
	 * gradeId
	 */
	private Integer gradeId;
	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * 教室座位类型
	 */
	private Integer seatType;
	/**
	 * 行数
	 */
	private Integer row;
	/**
	 * 组数（列数）
	 */
	private Integer col;
	/**
	 * 删除标记
	 */
	private Boolean isDelete;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否为主教室
	 */
	private Boolean isMainClassroom;
	
	public SeatChart() {
		
	}
	
	public SeatChart(Integer id) {
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
	 * 获取所属学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所属学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取schoolYear
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置schoolYear
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取教室编号
	 * @return java.lang.Integer
	 */
	public Integer getClassroomId() {
		return this.classroomId;
	}
	
	/**
	 * 设置教室编号
	 * @param classroomId
	 * @type java.lang.Integer
	 */
	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}
	
	/**
	 * 获取教室名称
	 * @return java.lang.String
	 */
	public String getClassroomName() {
		return this.classroomName;
	}
	
	/**
	 * 设置教室名称
	 * @param classroomName
	 * @type java.lang.String
	 */
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	
	/**
	 * 获取classroomType
	 * @return java.lang.String
	 */
	public String getClassroomType() {
		return this.classroomType;
	}
	
	/**
	 * 设置classroomType
	 * @param classroomType
	 * @type java.lang.String
	 */
	public void setClassroomType(String classroomType) {
		this.classroomType = classroomType;
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
	
	
	
	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	/**
	 * 获取行数
	 * @return java.lang.Integer
	 */
	public Integer getRow() {
		return this.row;
	}
	
	/**
	 * 设置行数
	 * @param row
	 * @type java.lang.Integer
	 */
	public void setRow(Integer row) {
		this.row = row;
	}
	
	/**
	 * 获取组数（列数）
	 * @return java.lang.Integer
	 */
	public Integer getCol() {
		return this.col;
	}
	
	/**
	 * 设置组数（列数）
	 * @param col
	 * @type java.lang.Integer
	 */
	public void setCol(Integer col) {
		this.col = col;
	}
	
	/**
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标记
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

	public Boolean getIsMainClassroom() {
		return isMainClassroom;
	}

	public void setIsMainClassroom(Boolean isMainClassroom) {
		this.isMainClassroom = isMainClassroom;
	}
	
}