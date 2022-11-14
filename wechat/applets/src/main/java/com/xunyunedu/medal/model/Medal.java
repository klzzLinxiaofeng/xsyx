package com.xunyunedu.medal.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * 奖章类
 */
public class Medal implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    private Integer id;
    /**
     * 奖章名称
     */

    @NotBlank(message = "奖章名称不能为空")
    private String name;
    /**
     * 颁发奖项
     */
    @NotBlank(message = "颁发奖项不能为空")
    private String grade;
    /**
     * 奖章背景
     */
    @NotBlank(message = "奖章背景不能为空")
    private String background;
    /**
     * 奖章简介
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间;
     */
    private Date modeifyDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModeifyDate() {
        return modeifyDate;
    }

    public void setModeifyDate(Date modeifyDate) {
        this.modeifyDate = modeifyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Medal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", background='" + background + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", modeifyDate=" + modeifyDate +
                '}';
    }

}
