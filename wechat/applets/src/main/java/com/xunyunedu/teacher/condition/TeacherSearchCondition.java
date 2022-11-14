package com.xunyunedu.teacher.condition;

import lombok.Data;

/**
 * 老师搜索条件
 * @author chenjiaxin
 */
@Data
public class TeacherSearchCondition {
    /**
     * 老师姓名
     */
    private String name;
    /**
     * 所属部门id
     */
    private Integer departId;
    /**
     * 老师userId
     */
    private Integer userId;

}
