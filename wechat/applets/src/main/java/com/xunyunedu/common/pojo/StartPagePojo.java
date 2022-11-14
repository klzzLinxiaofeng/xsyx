package com.xunyunedu.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 启动页管理
 *
 * @author: yhc
 * @Date: 2020/12/14 9:37
 * @Description:
 */
public class StartPagePojo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态 0启用 1不启用
     */
    private Integer status;

    /**
     * 上传的文件或者图片
     */
    private String resourceType;

    /**
     *
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 文件uuid
     */
    private String uuid;

    /**
     * 返回完整的url
     */
    private String url;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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

