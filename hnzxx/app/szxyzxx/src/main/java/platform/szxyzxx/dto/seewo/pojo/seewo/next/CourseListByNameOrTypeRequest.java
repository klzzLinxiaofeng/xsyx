package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 通过课程类型查询课程列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class CourseListByNameOrTypeRequest extends OpenApiRequest<CourseListByNameOrTypeParam, CourseListByNameOrTypeResult> {

    public CourseListByNameOrTypeRequest(CourseListByNameOrTypeParam param) {
        this();
        setBizModel(param);
    }

    public CourseListByNameOrTypeRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/timetable-api/course/list-by-name-or-type");
        setHttpMethod("POST");
    }

    public Class<CourseListByNameOrTypeResult> getResponseClass() {
        return CourseListByNameOrTypeResult.class;
    }

    public Class<CourseListByNameOrTypeParam> getDomainClass() {
        return CourseListByNameOrTypeParam.class;
    }
}

