package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量添加班级学生
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentServiceBatchSaveClassStudentsRequest extends OpenApiRequest<StudentServiceBatchSaveClassStudentsParam, StudentServiceBatchSaveClassStudentsResult> {

    public StudentServiceBatchSaveClassStudentsRequest(StudentServiceBatchSaveClassStudentsParam param) {
        this();
        setBizModel(param);
    }

    public StudentServiceBatchSaveClassStudentsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/student-service/batch-save-class-students");
        setHttpMethod("POST");
    }

    public Class<StudentServiceBatchSaveClassStudentsResult> getResponseClass() {
        return StudentServiceBatchSaveClassStudentsResult.class;
    }

    public Class<StudentServiceBatchSaveClassStudentsParam> getDomainClass() {
        return StudentServiceBatchSaveClassStudentsParam.class;
    }
}

