package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学号获取家长信息
 * 根据学号获取家长信息
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiListStudentParentByStudentCodesRequest extends OpenApiRequest<StudentApiListStudentParentByStudentCodesParam, StudentApiListStudentParentByStudentCodesResult> {

    public StudentApiListStudentParentByStudentCodesRequest(StudentApiListStudentParentByStudentCodesParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiListStudentParentByStudentCodesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/list-student-parent-by-student-codes");
        setHttpMethod("POST");
    }

    public Class<StudentApiListStudentParentByStudentCodesResult> getResponseClass() {
        return StudentApiListStudentParentByStudentCodesResult.class;
    }

    public Class<StudentApiListStudentParentByStudentCodesParam> getDomainClass() {
        return StudentApiListStudentParentByStudentCodesParam.class;
    }
}

