package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学号获取学生
 * getStudentByCodes
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentApiGetStudentByCodesRequest extends OpenApiRequest<StudentApiGetStudentByCodesParam, StudentApiGetStudentByCodesResult> {

    public StudentApiGetStudentByCodesRequest(StudentApiGetStudentByCodesParam param) {
        this();
        setBizModel(param);
    }

    public StudentApiGetStudentByCodesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/student-api/get-student-by-codes");
        setHttpMethod("POST");
    }

    public Class<StudentApiGetStudentByCodesResult> getResponseClass() {
        return StudentApiGetStudentByCodesResult.class;
    }

    public Class<StudentApiGetStudentByCodesParam> getDomainClass() {
        return StudentApiGetStudentByCodesParam.class;
    }
}

