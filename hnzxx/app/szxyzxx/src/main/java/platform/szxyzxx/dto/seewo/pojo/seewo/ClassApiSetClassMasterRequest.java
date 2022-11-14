package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 设置班主任
 * 设置班主任
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ClassApiSetClassMasterRequest extends OpenApiRequest<ClassApiSetClassMasterParam, ClassApiSetClassMasterResult> {

    public ClassApiSetClassMasterRequest(ClassApiSetClassMasterParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiSetClassMasterRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/class-api/set-class-master");
        setHttpMethod("POST");
    }

    public Class<ClassApiSetClassMasterResult> getResponseClass() {
        return ClassApiSetClassMasterResult.class;
    }

    public Class<ClassApiSetClassMasterParam> getDomainClass() {
        return ClassApiSetClassMasterParam.class;
    }
}

