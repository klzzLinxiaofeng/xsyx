package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学校uid查询班级详细信息
 * 根据学校uid查询班级详细信息
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ClassApiQueryClassDetailBySchoolUidRequest extends OpenApiRequest<ClassApiQueryClassDetailBySchoolUidParam, ClassApiQueryClassDetailBySchoolUidResult> {

    public ClassApiQueryClassDetailBySchoolUidRequest(ClassApiQueryClassDetailBySchoolUidParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiQueryClassDetailBySchoolUidRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/class-api/query-class-detail-by-school-uid");
        setHttpMethod("POST");
    }

    public Class<ClassApiQueryClassDetailBySchoolUidResult> getResponseClass() {
        return ClassApiQueryClassDetailBySchoolUidResult.class;
    }

    public Class<ClassApiQueryClassDetailBySchoolUidParam> getDomainClass() {
        return ClassApiQueryClassDetailBySchoolUidParam.class;
    }
}

