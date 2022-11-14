package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据学号查询家长列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ParentServiceListParentByStudentCodeRequest extends OpenApiRequest<ParentServiceListParentByStudentCodeParam, ParentServiceListParentByStudentCodeResult> {

    public ParentServiceListParentByStudentCodeRequest(ParentServiceListParentByStudentCodeParam param) {
        this();
        setBizModel(param);
    }

    public ParentServiceListParentByStudentCodeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/parent-service/list-parent-by-student-code");
        setHttpMethod("POST");
    }

    public Class<ParentServiceListParentByStudentCodeResult> getResponseClass() {
        return ParentServiceListParentByStudentCodeResult.class;
    }

    public Class<ParentServiceListParentByStudentCodeParam> getDomainClass() {
        return ParentServiceListParentByStudentCodeParam.class;
    }
}

