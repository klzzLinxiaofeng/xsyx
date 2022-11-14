package platform.szxyzxx.dto.seewo.basis;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 绑定学生与班级的关系
 * bindStudentToClass
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class StudentApiBindStudentToClassRequest extends OpenApiRequest<StudentApiBindStudentToClassParam, StudentApiBindStudentToClassResult> {

    public StudentApiBindStudentToClassRequest(StudentApiBindStudentToClassParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiBindStudentToClassRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/bind-student-to-class");
        setHttpMethod("POST");
    }

    public Class<StudentApiBindStudentToClassResult> getResponseClass() {
        return StudentApiBindStudentToClassResult.class;
    }

    public Class<StudentApiBindStudentToClassParam> getDomainClass() {
        return StudentApiBindStudentToClassParam.class;
    }
}
