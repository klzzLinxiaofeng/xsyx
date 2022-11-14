package com.xunyunedu.medal.vo;

import com.xunyunedu.personinfor.pojo.StudentPojo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: education
 * @description: 颁发奖章类
 * @author: cmb
 * @create: 2020-10-13 15:37
 **/
public class MedalVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //    id
    private Integer id;

    //    奖章名称
    @NotBlank(message = "奖章名称不能为空")
    private String name;

    //     颁发奖项
    @NotBlank(message = "颁发奖项不能为空")
    private String grade;

    //     奖章背景
    @NotBlank(message = "奖章名称不能为空")
    private String background;
    //     奖章简介
    private String description  ;
    //    创建时间
    private Date createDate;
    //    修改时间;
    private Date modeifyDate;
    /**
     * 学生列表
     */
    @NotEmpty(message = "学生列表不能为空")
    @Valid
    private List<StudentPojo> studentPojos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


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

    public List<StudentPojo> getStudentPojos() {
        return studentPojos;
    }

    public void setStudentPojos(List<StudentPojo> studentPojos) {
        this.studentPojos = studentPojos;
    }

    @Override
    public String toString() {
        return "MedalVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", background='" + background + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", modeifyDate=" + modeifyDate +
                ", studentPojos=" + studentPojos +
                '}';
    }
}
