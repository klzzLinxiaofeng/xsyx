package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 家长发送留言给孩子
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceParentSendNoteToChildRequest extends OpenApiRequest<HomeSchoolServiceParentSendNoteToChildParam, HomeSchoolServiceParentSendNoteToChildResult> {

    public HomeSchoolServiceParentSendNoteToChildRequest(HomeSchoolServiceParentSendNoteToChildParam param) {
        this();
        setBizModel(param);
    }

    public HomeSchoolServiceParentSendNoteToChildRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/home-school-service/parent-send-note-to-child");
        setHttpMethod("POST");
    }

    public Class<HomeSchoolServiceParentSendNoteToChildResult> getResponseClass() {
        return HomeSchoolServiceParentSendNoteToChildResult.class;
    }

    public Class<HomeSchoolServiceParentSendNoteToChildParam> getDomainClass() {
        return HomeSchoolServiceParentSendNoteToChildParam.class;
    }
}

