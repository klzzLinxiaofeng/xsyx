package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学号查询家长列表[已下架]
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentServiceListParentByStudentCodeRequest extends OpenApiRequest<StudentServiceListParentByStudentCodeParam, StudentServiceListParentByStudentCodeResult> {

    public StudentServiceListParentByStudentCodeRequest(StudentServiceListParentByStudentCodeParam param) {
        this();
        setBizModel(param);
    }

    public StudentServiceListParentByStudentCodeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/student-service/list-parent-by-student-code");
        setHttpMethod("POST");
    }

    public Class<StudentServiceListParentByStudentCodeResult> getResponseClass() {
        return StudentServiceListParentByStudentCodeResult.class;
    }

    public Class<StudentServiceListParentByStudentCodeParam> getDomainClass() {
        return StudentServiceListParentByStudentCodeParam.class;
    }
}

