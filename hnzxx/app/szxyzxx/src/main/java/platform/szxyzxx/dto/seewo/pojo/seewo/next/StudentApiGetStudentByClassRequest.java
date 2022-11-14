package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据班级获取学生列表
 * getStudentByClass
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiGetStudentByClassRequest extends OpenApiRequest<StudentApiGetStudentByClassParam, StudentApiGetStudentByClassResult> {

    public StudentApiGetStudentByClassRequest(StudentApiGetStudentByClassParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiGetStudentByClassRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/get-student-by-class");
        setHttpMethod("POST");
    }

    public Class<StudentApiGetStudentByClassResult> getResponseClass() {
        return StudentApiGetStudentByClassResult.class;
    }

    public Class<StudentApiGetStudentByClassParam> getDomainClass() {
        return StudentApiGetStudentByClassParam.class;
    }
}

