package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 修改指定手机号家长接收的所有未读留言的状态为已读
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceChangeNoteStatusRequest extends OpenApiRequest<HomeSchoolServiceChangeNoteStatusParam, HomeSchoolServiceChangeNoteStatusResult> {

    public HomeSchoolServiceChangeNoteStatusRequest(HomeSchoolServiceChangeNoteStatusParam param) {
        this();
        setBizModel(param);
    }

    public HomeSchoolServiceChangeNoteStatusRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/home-school-service/change-note-status");
        setHttpMethod("POST");
    }

    public Class<HomeSchoolServiceChangeNoteStatusResult> getResponseClass() {
        return HomeSchoolServiceChangeNoteStatusResult.class;
    }

    public Class<HomeSchoolServiceChangeNoteStatusParam> getDomainClass() {
        return HomeSchoolServiceChangeNoteStatusParam.class;
    }
}

