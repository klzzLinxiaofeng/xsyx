package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * InSchoolRoom
 *
 * @author AutoCreate
 */
public class InSchoolRoom implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 学校ID
     */
    private Integer schoolId;
    /**
     * 活动室名称
     */
    private String name;
    /**
     * 是否删除
     */
    private Boolean isDeleted;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    public InSchoolRoom() {

    }

    public InSchoolRoom(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取id
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学校ID
     *
     * @return java.lang.Integer
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * 设置学校ID
     *
     * @param schoolId
     * @type java.lang.Integer
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取活动室名称
     *
     * @return java.lang.String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置活动室名称
     *
     * @param name
     * @type java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否删除
     *
     * @return java.lang.Boolean
     */
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted
     * @type java.lang.Boolean
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取createDate
     *
     * @return java.util.Date
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     *
     * @param createDate
     * @type java.util.Date
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取modifyDate
     *
     * @return java.util.Date
     */
    public java.util.Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置modifyDate
     *
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}