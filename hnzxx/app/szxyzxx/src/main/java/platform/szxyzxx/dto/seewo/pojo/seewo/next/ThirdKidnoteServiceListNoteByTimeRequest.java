package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取限定时间内学校或用户留言列表，最多返回500条留言
 * 按留言修改时间由老到新排序，最多返回500条
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ThirdKidnoteServiceListNoteByTimeRequest extends OpenApiRequest<ThirdKidnoteServiceListNoteByTimeParam, ThirdKidnoteServiceListNoteByTimeResult> {

    public ThirdKidnoteServiceListNoteByTimeRequest(ThirdKidnoteServiceListNoteByTimeParam param) {
        this();
        setBizModel(param);
    }

    public ThirdKidnoteServiceListNoteByTimeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/third-kidnote-service/list-note-by-time");
        setHttpMethod("POST");
    }

    public Class<ThirdKidnoteServiceListNoteByTimeResult> getResponseClass() {
        return ThirdKidnoteServiceListNoteByTimeResult.class;
    }

    public Class<ThirdKidnoteServiceListNoteByTimeParam> getDomainClass() {
        return ThirdKidnoteServiceListNoteByTimeParam.class;
    }
}

