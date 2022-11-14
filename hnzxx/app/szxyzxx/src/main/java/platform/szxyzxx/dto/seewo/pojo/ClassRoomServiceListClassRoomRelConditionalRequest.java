package platform.szxyzxx.dto.seewo.pojo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 条件查询班级场地绑定列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class ClassRoomServiceListClassRoomRelConditionalRequest extends OpenApiRequest<ClassRoomServiceListClassRoomRelConditionalParam, ClassRoomServiceListClassRoomRelConditionalResult> {

    public ClassRoomServiceListClassRoomRelConditionalRequest(ClassRoomServiceListClassRoomRelConditionalParam param) {
        this();
        setBizModel(param);
    }

    public ClassRoomServiceListClassRoomRelConditionalRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/class-room-service/list-class-room-rel-conditional");
        setHttpMethod("POST");
    }

    public Class<ClassRoomServiceListClassRoomRelConditionalResult> getResponseClass() {
        return ClassRoomServiceListClassRoomRelConditionalResult.class;
    }

    public Class<ClassRoomServiceListClassRoomRelConditionalParam> getDomainClass() {
        return ClassRoomServiceListClassRoomRelConditionalParam.class;
    }
}

