package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 申请请假
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PersonalLeaveApplyForLeaveRequest extends OpenApiRequest<PersonalLeaveApplyForLeaveParam, PersonalLeaveApplyForLeaveResult> {

    public PersonalLeaveApplyForLeaveRequest(PersonalLeaveApplyForLeaveParam param) {
        this();
        setBizModel(param);
    }

    public PersonalLeaveApplyForLeaveRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/personal-leave/apply-for-leave");
        setHttpMethod("POST");
    }

    public Class<PersonalLeaveApplyForLeaveResult> getResponseClass() {
        return PersonalLeaveApplyForLeaveResult.class;
    }

    public Class<PersonalLeaveApplyForLeaveParam> getDomainClass() {
        return PersonalLeaveApplyForLeaveParam.class;
    }
}

