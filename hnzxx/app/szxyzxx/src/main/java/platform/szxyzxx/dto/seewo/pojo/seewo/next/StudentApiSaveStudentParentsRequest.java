package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 保存|更新多个学生的家长信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiSaveStudentParentsRequest extends OpenApiRequest<StudentApiSaveStudentParentsParam, StudentApiSaveStudentParentsResult> {

    public StudentApiSaveStudentParentsRequest(StudentApiSaveStudentParentsParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiSaveStudentParentsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/save-student-parents");
        setHttpMethod("POST");
    }

    public Class<StudentApiSaveStudentParentsResult> getResponseClass() {
        return StudentApiSaveStudentParentsResult.class;
    }

    public Class<StudentApiSaveStudentParentsParam> getDomainClass() {
        return StudentApiSaveStudentParentsParam.class;
    }
}

