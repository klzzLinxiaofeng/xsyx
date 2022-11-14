package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据发送时间限量获取留言列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class HomeSchoolServiceListNotesLimitedRequest extends OpenApiRequest<HomeSchoolServiceListNotesLimitedParam, HomeSchoolServiceListNotesLimitedResult> {

    public HomeSchoolServiceListNotesLimitedRequest(HomeSchoolServiceListNotesLimitedParam param) {
        this();
        setBizModel(param);
    }

    public HomeSchoolServiceListNotesLimitedRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/home-school-service/list-notes-limited");
        setHttpMethod("POST");
    }

    public Class<HomeSchoolServiceListNotesLimitedResult> getResponseClass() {
        return HomeSchoolServiceListNotesLimitedResult.class;
    }

    public Class<HomeSchoolServiceListNotesLimitedParam> getDomainClass() {
        return HomeSchoolServiceListNotesLimitedParam.class;
    }
}

