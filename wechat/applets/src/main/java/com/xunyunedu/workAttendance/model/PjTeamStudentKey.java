package com.xunyunedu.workAttendance.model;

import java.io.Serializable;
import lombok.Data;

/**
 * pj_team_student
 * @author 
 */
@Data
public class PjTeamStudentKey implements Serializable {
    private Integer id;

    /**
     * 所在年级ID
     */
    private Integer gradeId;

    private static final long serialVersionUID = 1L;
}