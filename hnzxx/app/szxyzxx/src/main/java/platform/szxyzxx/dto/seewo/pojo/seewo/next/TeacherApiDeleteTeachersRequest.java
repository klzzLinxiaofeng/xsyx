package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量删除教师
 * 批量删除教师
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherApiDeleteTeachersRequest extends OpenApiRequest<TeacherApiDeleteTeachersParam, TeacherApiDeleteTeachersResult> {

    public TeacherApiDeleteTeachersRequest(TeacherApiDeleteTeachersParam param) {
        this();
        setBizModel(param);
    }

    public TeacherApiDeleteTeachersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/teacher-api/delete-teachers");
        setHttpMethod("POST");
    }

    public Class<TeacherApiDeleteTeachersResult> getResponseClass() {
        return TeacherApiDeleteTeachersResult.class;
    }

    public Class<TeacherApiDeleteTeachersParam> getDomainClass() {
        return TeacherApiDeleteTeachersParam.class;
    }
}

