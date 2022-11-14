package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 发送家长到校留言给家长指定学校所有孩子
 * 留言信息：XX学生， 你的家长已到校
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ThirdKidnoteServiceSendNoteToChildrenRequest extends OpenApiRequest<ThirdKidnoteServiceSendNoteToChildrenParam, ThirdKidnoteServiceSendNoteToChildrenResult> {

    public ThirdKidnoteServiceSendNoteToChildrenRequest(ThirdKidnoteServiceSendNoteToChildrenParam param) {
        this();
        setBizModel(param);
    }

    public ThirdKidnoteServiceSendNoteToChildrenRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/third-kidnote-service/send-note-to-children");
        setHttpMethod("POST");
    }

    public Class<ThirdKidnoteServiceSendNoteToChildrenResult> getResponseClass() {
        return ThirdKidnoteServiceSendNoteToChildrenResult.class;
    }

    public Class<ThirdKidnoteServiceSendNoteToChildrenParam> getDomainClass() {
        return ThirdKidnoteServiceSendNoteToChildrenParam.class;
    }
}

