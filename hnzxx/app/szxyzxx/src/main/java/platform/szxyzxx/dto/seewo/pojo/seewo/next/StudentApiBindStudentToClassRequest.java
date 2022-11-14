package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 绑定学生与班级的关系
 * bindStudentToClass
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
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

