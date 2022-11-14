package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 学生请假申请与查询api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class StudentLeaveStudentLeaveRequestHandlerRequest extends OpenApiRequest<StudentLeaveStudentLeaveRequestHandlerParam, StudentLeaveStudentLeaveRequestHandlerResult> {

    public StudentLeaveStudentLeaveRequestHandlerRequest(StudentLeaveStudentLeaveRequestHandlerParam param) {
        this();
        setBizModel(param);
    }

    public StudentLeaveStudentLeaveRequestHandlerRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-attendance-api/student-leave/student-leave-request-handler");
        setHttpMethod("POST");
    }

    public Class<StudentLeaveStudentLeaveRequestHandlerResult> getResponseClass() {
        return StudentLeaveStudentLeaveRequestHandlerResult.class;
    }

    public Class<StudentLeaveStudentLeaveRequestHandlerParam> getDomainClass() {
        return StudentLeaveStudentLeaveRequestHandlerParam.class;
    }
}

