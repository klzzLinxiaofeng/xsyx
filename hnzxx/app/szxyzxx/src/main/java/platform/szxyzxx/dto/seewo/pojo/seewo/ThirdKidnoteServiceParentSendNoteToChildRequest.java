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
public class ThirdKidnoteServiceParentSendNoteToChildRequest extends OpenApiRequest<ThirdKidnoteServiceParentSendNoteToChildParam, ThirdKidnoteServiceParentSendNoteToChildResult> {

    public ThirdKidnoteServiceParentSendNoteToChildRequest(ThirdKidnoteServiceParentSendNoteToChildParam param) {
        this();
        setBizModel(param);
    }

    public ThirdKidnoteServiceParentSendNoteToChildRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/third-kidnote-service/parent-send-note-to-child");
        setHttpMethod("POST");
    }

    public Class<ThirdKidnoteServiceParentSendNoteToChildResult> getResponseClass() {
        return ThirdKidnoteServiceParentSendNoteToChildResult.class;
    }

    public Class<ThirdKidnoteServiceParentSendNoteToChildParam> getDomainClass() {
        return ThirdKidnoteServiceParentSendNoteToChildParam.class;
    }
}

