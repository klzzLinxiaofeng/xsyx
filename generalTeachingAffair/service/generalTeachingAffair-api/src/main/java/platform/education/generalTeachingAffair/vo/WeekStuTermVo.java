package platform.education.generalTeachingAffair.vo;

import framework.generic.dao.Model;

import java.util.Date;

/**
 *  @author: yhc
 *  @Date: 2021/5/8 15:55
 *  @Description:
 */
public class WeekStuTermVo implements Model<Integer> {
    /**
     * id
     */
    private Integer id;
    private Integer teacherId;

    /**
     * 学年 学期id
     */
    private Integer yearTermId;

    /**
     * 第几周
     */
    private String name;


    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 文件
     */
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYearTermId() {
        return yearTermId;
    }

    public void setYearTermId(Integer yearTermId) {
        this.yearTermId = yearTermId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public Integer getKey() {
        return this.id;
    }
}

