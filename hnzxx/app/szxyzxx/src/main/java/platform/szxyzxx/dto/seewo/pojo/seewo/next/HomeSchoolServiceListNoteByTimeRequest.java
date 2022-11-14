package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取限定时间内学校或用户留言列表，最多返回500条留言
 * 按留言修改时间增量排序，最多返回500条
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class HomeSchoolServiceListNoteByTimeRequest extends OpenApiRequest<HomeSchoolServiceListNoteByTimeParam, HomeSchoolServiceListNoteByTimeResult> {

    public HomeSchoolServiceListNoteByTimeRequest(HomeSchoolServiceListNoteByTimeParam param) {
        this();
        setBizModel(param);
    }

    public HomeSchoolServiceListNoteByTimeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/home-school-service/list-note-by-time");
        setHttpMethod("POST");
    }

    public Class<HomeSchoolServiceListNoteByTimeResult> getResponseClass() {
        return HomeSchoolServiceListNoteByTimeResult.class;
    }

    public Class<HomeSchoolServiceListNoteByTimeParam> getDomainClass() {
        return HomeSchoolServiceListNoteByTimeParam.class;
    }
}

