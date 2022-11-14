package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 审核请假记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PersonalLeaveReviewLeaveRequest extends OpenApiRequest<PersonalLeaveReviewLeaveParam, PersonalLeaveReviewLeaveResult> {

    public PersonalLeaveReviewLeaveRequest(PersonalLeaveReviewLeaveParam param) {
        this();
        setBizModel(param);
    }

    public PersonalLeaveReviewLeaveRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/personal-leave/review-leave");
        setHttpMethod("POST");
    }

    public Class<PersonalLeaveReviewLeaveResult> getResponseClass() {
        return PersonalLeaveReviewLeaveResult.class;
    }

    public Class<PersonalLeaveReviewLeaveParam> getDomainClass() {
        return PersonalLeaveReviewLeaveParam.class;
    }
}

