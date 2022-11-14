package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SeatChartItem
 * @author AutoCreate
 *
 */
public class SeatChartItem implements Model<Integer> {
	
	
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
	 * seatId
	 */
	private Integer seatId;
	/**
	 * studentId
	 */
	private Integer studentId;
	/**
	 * 行数
	 */
	private Integer positionX;
	/**
	 * 组数（列数）
	 */
	private Integer positionY;
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
	
	public SeatChartItem() {
		
	}
	
	public SeatChartItem(Integer id) {
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
	 * 获取seatId
	 * @return java.lang.Integer
	 */
	public Integer getSeatId() {
		return this.seatId;
	}
	
	/**
	 * 设置seatId
	 * @param seatId
	 * @type java.lang.Integer
	 */
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	
	/**
	 * 获取studentId
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置studentId
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	
	/**
	 * 获取行数
	 * @return java.lang.Integer
	 */
	public Integer getPositionX() {
		return this.positionX;
	}
	
	/**
	 * 设置行数
	 * @param positionX
	 * @type java.lang.Integer
	 */
	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}
	
	/**
	 * 获取组数（列数）
	 * @return java.lang.Integer
	 */
	public Integer getPositionY() {
		return this.positionY;
	}
	
	/**
	 * 设置组数（列数）
	 * @param positionY
	 * @type java.lang.Integer
	 */
	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
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
	
}