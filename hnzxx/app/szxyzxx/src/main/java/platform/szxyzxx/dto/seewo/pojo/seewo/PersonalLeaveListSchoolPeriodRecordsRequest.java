package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 查询学校阶段内的请假记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PersonalLeaveListSchoolPeriodRecordsRequest extends OpenApiRequest<PersonalLeaveListSchoolPeriodRecordsParam, PersonalLeaveListSchoolPeriodRecordsResult> {

    public PersonalLeaveListSchoolPeriodRecordsRequest(PersonalLeaveListSchoolPeriodRecordsParam param) {
        this();
        setBizModel(param);
    }

    public PersonalLeaveListSchoolPeriodRecordsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/personal-leave/list-school-period-records");
        setHttpMethod("POST");
    }

    public Class<PersonalLeaveListSchoolPeriodRecordsResult> getResponseClass() {
        return PersonalLeaveListSchoolPeriodRecordsResult.class;
    }

    public Class<PersonalLeaveListSchoolPeriodRecordsParam> getDomainClass() {
        return PersonalLeaveListSchoolPeriodRecordsParam.class;
    }
}

