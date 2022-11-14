package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 条件查询学校老师列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceListSchoolTeacherConditionalRequest extends OpenApiRequest<TeacherServiceListSchoolTeacherConditionalParam, TeacherServiceListSchoolTeacherConditionalResult> {

    public TeacherServiceListSchoolTeacherConditionalRequest(TeacherServiceListSchoolTeacherConditionalParam param) {
        this();
        setBizModel(param);
    }

    public TeacherServiceListSchoolTeacherConditionalRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/teacher-service/list-school-teacher-conditional");
        setHttpMethod("POST");
    }

    public Class<TeacherServiceListSchoolTeacherConditionalResult> getResponseClass() {
        return TeacherServiceListSchoolTeacherConditionalResult.class;
    }

    public Class<TeacherServiceListSchoolTeacherConditionalParam> getDomainClass() {
        return TeacherServiceListSchoolTeacherConditionalParam.class;
    }
}

