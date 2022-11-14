package com.xunyunedu.canteen.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 食谱表
 *
 * @author: yhc
 * @Date: 2020/12/31 15:11
 * @Description:
 */

public class CanteenRecipesPojo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 所属学校
     */
    private Integer schoolId;

    /**
     * 食谱详情
     */
    private String description;

    /**
     * 删除标志
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    private List<CanteenCuisinPojo> canteenCuisinPojoList;


    private static final long serialVersionUID = 1L;

    public List<CanteenCuisinPojo> getCanteenCuisinPojoList() {
        return canteenCuisinPojoList;
    }

    public void setCanteenCuisinPojoList(List<CanteenCuisinPojo> canteenCuisinPojoList) {
        this.canteenCuisinPojoList = canteenCuisinPojoList;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

