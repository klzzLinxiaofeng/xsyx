package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 通过课程类型查询课程列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class CourseListByNameOrTypeParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static CourseListByNameOrTypeParamBuilder builder(){
        return new CourseListByNameOrTypeParamBuilder();
    }

    public static class CourseListByNameOrTypeParamBuilder{
        private RequestBody requestBody;

        public CourseListByNameOrTypeParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public CourseListByNameOrTypeParam build(){
            CourseListByNameOrTypeParam param = new CourseListByNameOrTypeParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * courseReq
         */
        private CourseReq courseReq;

        public CourseReq getCourseReq() {
            return this.courseReq;
        }

        public void setCourseReq(CourseReq courseReq) {
            this.courseReq = courseReq;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private CourseReq courseReq;

            public RequestBodyBuilder courseReq(CourseReq courseReq){
                this.courseReq = courseReq;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setCourseReq(courseReq);
                return param;
            }
        }
    }

    public static class CourseReq {
        /**
         * 课程名称列表
         */
        private List<String> courseNames;
        /**
         * 排课计划uid
         */
        private String planUid;
        /**
         * 类型 : 1: 行政班 2：教学班
         */
        private Integer type;
        /**
         * 页码
         */
        private Integer pageNo;
        /**
         * 每页个数
         */
        private Integer pageSize;

        public List<String> getCourseNames() {
            return this.courseNames;
        }

        public void setCourseNames(List<String> courseNames) {
            this.courseNames = courseNames;
        }

        public String getPlanUid() {
            return this.planUid;
        }

        public void setPlanUid(String planUid) {
            this.planUid = planUid;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getPageNo() {
            return this.pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }


        public static CourseReqBuilder builder(){
            return new CourseReqBuilder();
        }

        public static class CourseReqBuilder{
            private List<String> courseNames;
            private String planUid;
            private Integer type;
            private Integer pageNo;
            private Integer pageSize;

            public CourseReqBuilder courseNames(List<String> courseNames){
                this.courseNames = courseNames;
                return this;
            }
            public CourseReqBuilder planUid(String planUid){
                this.planUid = planUid;
                return this;
            }
            public CourseReqBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public CourseReqBuilder pageNo(Integer pageNo){
                this.pageNo = pageNo;
                return this;
            }
            public CourseReqBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public CourseReq build(){
                CourseReq param = new CourseReq();
                param.setCourseNames(courseNames);
                param.setPlanUid(planUid);
                param.setType(type);
                param.setPageNo(pageNo);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
