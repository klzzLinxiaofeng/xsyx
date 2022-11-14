package com.xunyunedu.department.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 部门教师
 *
 * @author edison
 *
 */
@Data
public class PjDepartmentTeacher {

    private Integer id;
    private Integer schoolId;
    private Integer departmentId;
    private String departmentName;
    private Integer teacherId;
    private String teacherName;
    private Boolean isDeleted;
    private Date createDate;
    private Date modifyDate;
    private Integer  orderNumber;
}
