package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据学校uid查询排课计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetableServiceListPlanParam extends OpenApiParam {


    /**
     * 响应体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static TimetableServiceListPlanParamBuilder builder(){
        return new TimetableServiceListPlanParamBuilder();
    }

    public static class TimetableServiceListPlanParamBuilder{
        private RequestBody requestBody;

        public TimetableServiceListPlanParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetableServiceListPlanParam build(){
            TimetableServiceListPlanParam param = new TimetableServiceListPlanParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private PlanQuery query;

        public PlanQuery getQuery() {
            return this.query;
        }

        public void setQuery(PlanQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private PlanQuery query;

            public RequestBodyBuilder query(PlanQuery query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class PlanQuery {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 页码
         */
        private Integer page;
        /**
         * 每页个数
         */
        private Integer pageSize;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }


        public static PlanQueryBuilder builder(){
            return new PlanQueryBuilder();
        }

        public static class PlanQueryBuilder{
            private String schoolUid;
            private Integer page;
            private Integer pageSize;

            public PlanQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public PlanQueryBuilder page(Integer page){
                this.page = page;
                return this;
            }
            public PlanQueryBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public PlanQuery build(){
                PlanQuery param = new PlanQuery();
                param.setSchoolUid(schoolUid);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
