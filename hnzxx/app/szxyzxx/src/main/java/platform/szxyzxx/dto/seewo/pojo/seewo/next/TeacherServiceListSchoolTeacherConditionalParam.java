package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 条件查询学校老师列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceListSchoolTeacherConditionalParam extends OpenApiParam {


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

    public static TeacherServiceListSchoolTeacherConditionalParamBuilder builder(){
        return new TeacherServiceListSchoolTeacherConditionalParamBuilder();
    }

    public static class TeacherServiceListSchoolTeacherConditionalParamBuilder{
        private RequestBody requestBody;

        public TeacherServiceListSchoolTeacherConditionalParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TeacherServiceListSchoolTeacherConditionalParam build(){
            TeacherServiceListSchoolTeacherConditionalParam param = new TeacherServiceListSchoolTeacherConditionalParam();
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
         * appId
         */
        private String appId;
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 手机号列表
         */
        private List<String> phones;
        /**
         * 用户ID列表
         */
        private List<String> userUids;
        /**
         * page，默认1
         */
        private Integer page;
        /**
         * pageSize，默认20
         */
        private Integer pageSize;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public List<String> getPhones() {
            return this.phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
        }

        public List<String> getUserUids() {
            return this.userUids;
        }

        public void setUserUids(List<String> userUids) {
            this.userUids = userUids;
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
            private String appId;
            private String schoolUid;
            private List<String> phones;
            private List<String> userUids;
            private Integer page;
            private Integer pageSize;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder phones(List<String> phones){
                this.phones = phones;
                return this;
            }
            public QueryBuilder userUids(List<String> userUids){
                this.userUids = userUids;
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
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setPhones(phones);
                param.setUserUids(userUids);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
