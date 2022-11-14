package platform.szxyzxx.dto.seewo.basis;



import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量新增标准班级
 * 批量新增标准班级，若存在重复则忽略重复
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class ClassApiAddStandardClassesRequest extends OpenApiRequest<ClassApiAddStandardClassesParam, ClassApiAddStandardClassesResult> {

    public ClassApiAddStandardClassesRequest(ClassApiAddStandardClassesParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiAddStandardClassesRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/organization/class-api/add-standard-classes");
        setHttpMethod("POST");
    }

    public Class<ClassApiAddStandardClassesResult> getResponseClass() {
        return ClassApiAddStandardClassesResult.class;
    }

    public Class<ClassApiAddStandardClassesParam> getDomainClass() {
        return ClassApiAddStandardClassesParam.class;
    }
}

