package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据班级ID查询班级学生列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentServiceListStudentByClassRequest extends OpenApiRequest<StudentServiceListStudentByClassParam, StudentServiceListStudentByClassResult> {

    public StudentServiceListStudentByClassRequest(StudentServiceListStudentByClassParam param) {
        this();
        setBizModel(param);
    }

    public StudentServiceListStudentByClassRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/student-service/list-student-by-class");
        setHttpMethod("POST");
    }

    public Class<StudentServiceListStudentByClassResult> getResponseClass() {
        return StudentServiceListStudentByClassResult.class;
    }

    public Class<StudentServiceListStudentByClassParam> getDomainClass() {
        return StudentServiceListStudentByClassParam.class;
    }
}

