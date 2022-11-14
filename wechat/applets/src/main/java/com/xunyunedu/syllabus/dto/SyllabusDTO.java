package com.xunyunedu.syllabus.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SyllabusDTO {

    @NotNull(message = "学校id不能为空")
    private Integer schoolId;

    @NotNull(message = "班级id不能为空")
    private Integer teamId;

    private String startDate;
}
