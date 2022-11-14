package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 解绑绑定学生与班级关系
 * unbindStudentToClass
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiUnbindStudentToClassRequest extends OpenApiRequest<StudentApiUnbindStudentToClassParam, StudentApiUnbindStudentToClassResult> {

    public StudentApiUnbindStudentToClassRequest(StudentApiUnbindStudentToClassParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiUnbindStudentToClassRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/unbind-student-to-class");
        setHttpMethod("POST");
    }

    public Class<StudentApiUnbindStudentToClassResult> getResponseClass() {
        return StudentApiUnbindStudentToClassResult.class;
    }

    public Class<StudentApiUnbindStudentToClassParam> getDomainClass() {
        return StudentApiUnbindStudentToClassParam.class;
    }
}

