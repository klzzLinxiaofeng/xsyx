package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 通过学校id|名字|学号获取学生
 * listStudentBySchoolUidAndNameOrCode
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentApiListStudentBySchoolUidAndNameOrCodeRequest extends OpenApiRequest<StudentApiListStudentBySchoolUidAndNameOrCodeParam, StudentApiListStudentBySchoolUidAndNameOrCodeResult> {

    public StudentApiListStudentBySchoolUidAndNameOrCodeRequest(StudentApiListStudentBySchoolUidAndNameOrCodeParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiListStudentBySchoolUidAndNameOrCodeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/list-student-by-school-uid-and-name-or-code");
        setHttpMethod("POST");
    }

    public Class<StudentApiListStudentBySchoolUidAndNameOrCodeResult> getResponseClass() {
        return StudentApiListStudentBySchoolUidAndNameOrCodeResult.class;
    }

    public Class<StudentApiListStudentBySchoolUidAndNameOrCodeParam> getDomainClass() {
        return StudentApiListStudentBySchoolUidAndNameOrCodeParam.class;
    }
}

