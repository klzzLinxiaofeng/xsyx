package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据学号查询家长列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ParentServiceListParentByStudentCodeParam extends OpenApiParam {


    /**
     * 
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static ParentServiceListParentByStudentCodeParamBuilder builder(){
        return new ParentServiceListParentByStudentCodeParamBuilder();
    }

    public static class ParentServiceListParentByStudentCodeParamBuilder{
        private RequestBody requestBody;

        public ParentServiceListParentByStudentCodeParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ParentServiceListParentByStudentCodeParam build(){
            ParentServiceListParentByStudentCodeParam param = new ParentServiceListParentByStudentCodeParam();
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
         * 学号列表，最大200
         */
        private List<String> studentCodes;

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

        public List<String> getStudentCodes() {
            return this.studentCodes;
        }

        public void setStudentCodes(List<String> studentCodes) {
            this.studentCodes = studentCodes;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private List<String> studentCodes;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder studentCodes(List<String> studentCodes){
                this.studentCodes = studentCodes;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setStudentCodes(studentCodes);
                return param;
            }
        }
    }


}
