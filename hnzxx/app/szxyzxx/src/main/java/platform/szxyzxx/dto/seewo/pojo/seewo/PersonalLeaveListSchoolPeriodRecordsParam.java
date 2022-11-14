package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 查询学校阶段内的请假记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PersonalLeaveListSchoolPeriodRecordsParam extends OpenApiParam {


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

    public static PersonalLeaveListSchoolPeriodRecordsParamBuilder builder(){
        return new PersonalLeaveListSchoolPeriodRecordsParamBuilder();
    }

    public static class PersonalLeaveListSchoolPeriodRecordsParamBuilder{
        private RequestBody requestBody;

        public PersonalLeaveListSchoolPeriodRecordsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PersonalLeaveListSchoolPeriodRecordsParam build(){
            PersonalLeaveListSchoolPeriodRecordsParam param = new PersonalLeaveListSchoolPeriodRecordsParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Query query;

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query query;

            public RequestBodyBuilder query(Query query){
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

    public static class Query {
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 开始日期 yyyy-MM-dd
         */
        private String startDate;
        /**
         * 结束日期 yyyy-MM-dd
         */
        private String endDate;
        /**
         * 当前页
         */
        private Integer page;
        /**
         * 分页条数
         */
        private Integer pageSize;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String schoolUid;
            private String startDate;
            private String endDate;
            private Integer page;
            private Integer pageSize;

            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder startDate(String startDate){
                this.startDate = startDate;
                return this;
            }
            public QueryBuilder endDate(String endDate){
                this.endDate = endDate;
                return this;
            }
            public QueryBuilder page(Integer page){
                this.page = page;
                return this;
            }
            public QueryBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setSchoolUid(schoolUid);
                param.setStartDate(startDate);
                param.setEndDate(endDate);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
