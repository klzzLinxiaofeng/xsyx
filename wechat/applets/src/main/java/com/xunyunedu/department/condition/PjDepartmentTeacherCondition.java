package com.xunyunedu.department.condition;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PjDepartmentTeacherCondition {

    private Integer schoolId;
    private Integer departmentId;
    private Boolean isDelete;
    private List<Integer> depIdArr;
}
