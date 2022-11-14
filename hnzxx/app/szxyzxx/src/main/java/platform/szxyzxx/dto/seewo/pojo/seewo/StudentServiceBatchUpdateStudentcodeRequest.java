package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量更新学生学号
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentServiceBatchUpdateStudentcodeRequest extends OpenApiRequest<StudentServiceBatchUpdateStudentcodeParam, StudentServiceBatchUpdateStudentcodeResult> {

    public StudentServiceBatchUpdateStudentcodeRequest(StudentServiceBatchUpdateStudentcodeParam param) {
        this();
        setBizModel(param);
    }

    public StudentServiceBatchUpdateStudentcodeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/student-service/batch-update-studentcode");
        setHttpMethod("POST");
    }

    public Class<StudentServiceBatchUpdateStudentcodeResult> getResponseClass() {
        return StudentServiceBatchUpdateStudentcodeResult.class;
    }

    public Class<StudentServiceBatchUpdateStudentcodeParam> getDomainClass() {
        return StudentServiceBatchUpdateStudentcodeParam.class;
    }
}

