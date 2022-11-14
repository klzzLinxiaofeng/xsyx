package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学校获取教师列表
 * 根据学校获取教师列表
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherApiListBySchoolUidRequest extends OpenApiRequest<TeacherApiListBySchoolUidParam, TeacherApiListBySchoolUidResult> {

    public TeacherApiListBySchoolUidRequest(TeacherApiListBySchoolUidParam param) {
        this();
        setBizModel(param);
    }

    public TeacherApiListBySchoolUidRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/teacher-api/list-by-school-uid");
        setHttpMethod("POST");
    }

    public Class<TeacherApiListBySchoolUidResult> getResponseClass() {
        return TeacherApiListBySchoolUidResult.class;
    }

    public Class<TeacherApiListBySchoolUidParam> getDomainClass() {
        return TeacherApiListBySchoolUidParam.class;
    }
}

