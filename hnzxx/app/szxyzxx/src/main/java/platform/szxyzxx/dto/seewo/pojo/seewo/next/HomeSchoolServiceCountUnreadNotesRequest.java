package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取孩子与家长的当前未读消息数
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class HomeSchoolServiceCountUnreadNotesRequest extends OpenApiRequest<HomeSchoolServiceCountUnreadNotesParam, HomeSchoolServiceCountUnreadNotesResult> {

    public HomeSchoolServiceCountUnreadNotesRequest(HomeSchoolServiceCountUnreadNotesParam param) {
        this();
        setBizModel(param);
    }

    public HomeSchoolServiceCountUnreadNotesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/home-school-service/count-unread-notes");
        setHttpMethod("POST");
    }

    public Class<HomeSchoolServiceCountUnreadNotesResult> getResponseClass() {
        return HomeSchoolServiceCountUnreadNotesResult.class;
    }

    public Class<HomeSchoolServiceCountUnreadNotesParam> getDomainClass() {
        return HomeSchoolServiceCountUnreadNotesParam.class;
    }
}

