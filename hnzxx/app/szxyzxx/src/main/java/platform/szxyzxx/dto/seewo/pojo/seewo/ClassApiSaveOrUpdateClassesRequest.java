package platform.szxyzxx.dto.seewo.pojo.seewo;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 添加或更新班级
 * 添加或更新班级
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ClassApiSaveOrUpdateClassesRequest extends OpenApiRequest<ClassApiSaveOrUpdateClassesParam, ClassApiSaveOrUpdateClassesResult> {

    public ClassApiSaveOrUpdateClassesRequest(ClassApiSaveOrUpdateClassesParam param) {
        this();
        setBizModel(param);
    }

    public ClassApiSaveOrUpdateClassesRequest() {
        setServerUrl("https://openapi.test.seewo.com");
        setPath("/mis-basis/class-api/save-or-update-classes");
        setHttpMethod("POST");
    }

    public Class<ClassApiSaveOrUpdateClassesResult> getResponseClass() {
        return ClassApiSaveOrUpdateClassesResult.class;
    }

    public Class<ClassApiSaveOrUpdateClassesParam> getDomainClass() {
        return ClassApiSaveOrUpdateClassesParam.class;
    }
}

