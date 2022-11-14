package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 发送家长到校留言给家长指定学校所有孩子
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceSendNoteToChildrenRequest extends OpenApiRequest<HomeSchoolServiceSendNoteToChildrenParam, HomeSchoolServiceSendNoteToChildrenResult> {

    public HomeSchoolServiceSendNoteToChildrenRequest(HomeSchoolServiceSendNoteToChildrenParam param) {
        this();
        setBizModel(param);
    }

    public HomeSchoolServiceSendNoteToChildrenRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/home-school-service/send-note-to-children");
        setHttpMethod("POST");
    }

    public Class<HomeSchoolServiceSendNoteToChildrenResult> getResponseClass() {
        return HomeSchoolServiceSendNoteToChildrenResult.class;
    }

    public Class<HomeSchoolServiceSendNoteToChildrenParam> getDomainClass() {
        return HomeSchoolServiceSendNoteToChildrenParam.class;
    }
}

