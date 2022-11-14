package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 解除班主任
 * 解除班主任
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiUnsetClassMasterRequest extends OpenApiRequest<ClassApiUnsetClassMasterParam, ClassApiUnsetClassMasterResult> {

    public ClassApiUnsetClassMasterRequest(ClassApiUnsetClassMasterParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiUnsetClassMasterRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/class-api/unset-class-master");
        setHttpMethod("POST");
    }

    public Class<ClassApiUnsetClassMasterResult> getResponseClass() {
        return ClassApiUnsetClassMasterResult.class;
    }

    public Class<ClassApiUnsetClassMasterParam> getDomainClass() {
        return ClassApiUnsetClassMasterParam.class;
    }
}

