package com.xunyunedu.seewo.pojo;

import lombok.Data;

@Data
public class SeewoClassAttendanceQueryParam {
    /**
     * 考勤日期
     */
    private String attendDate;
    /**
     * 教师id（用户必须是班主任）
     */
    private Integer teacherId;
}
