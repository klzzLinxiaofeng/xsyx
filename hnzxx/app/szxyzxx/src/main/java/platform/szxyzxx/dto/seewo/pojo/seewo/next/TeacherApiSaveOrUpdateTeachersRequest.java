package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 添加或更新教师
 * 添加或更新教师
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherApiSaveOrUpdateTeachersRequest extends OpenApiRequest<TeacherApiSaveOrUpdateTeachersParam, TeacherApiSaveOrUpdateTeachersResult> {

    public TeacherApiSaveOrUpdateTeachersRequest(TeacherApiSaveOrUpdateTeachersParam param) {
        this();
        setBizModel(param);
    }

    public TeacherApiSaveOrUpdateTeachersRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/teacher-api/save-or-update-teachers");
        setHttpMethod("POST");
    }

    public Class<TeacherApiSaveOrUpdateTeachersResult> getResponseClass() {
        return TeacherApiSaveOrUpdateTeachersResult.class;
    }

    public Class<TeacherApiSaveOrUpdateTeachersParam> getDomainClass() {
        return TeacherApiSaveOrUpdateTeachersParam.class;
    }
}

