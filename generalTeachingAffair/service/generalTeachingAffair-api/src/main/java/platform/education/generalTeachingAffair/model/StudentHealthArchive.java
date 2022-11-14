package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.List;

/**
 * StudentHealthArchive
 * @author AutoCreate
 *
 */
public class StudentHealthArchive implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * pj_school.id
	 */
	private Integer schoolId;
	/**
	 * pj_team.id
	 */
	private Integer teamId;
	/**
	 * pj_student.id
	 */
	private Integer studentId;
	/**
	 * 类别
	 */
	private String type;

	private String types;

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 附件
	 */
	private String accessory;
	/**
	 * 删除标记
	 */
	private Integer isDelete;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	/**
	 * 图片说明uuid
	 */
	private String uuid;
	/**
	 * 返回完整的图片说明
	 */
	private String picUrl;

	public List<String> getUuids() {
		return uuids;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	/**
	 * 返回图片的集合
	 */
	private List<String> uuids;



	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public StudentHealthArchive() {
		
	}
	
	public StudentHealthArchive(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获取类别
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置类别
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取附件
	 * @return java.lang.String
	 */
	public String getAccessory() {
		return this.accessory;
	}
	
	/**
	 * 设置附件
	 * @param accessory
	 * @type java.lang.String
	 */
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 2021年1月18日10:12:58
	 */
	private String stuName;

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	private Integer typeId;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
}