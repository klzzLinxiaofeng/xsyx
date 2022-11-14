package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量设置班主任
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchSetClassMastersParam extends OpenApiParam {


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

    public static TeacherServiceBatchSetClassMastersParamBuilder builder(){
        return new TeacherServiceBatchSetClassMastersParamBuilder();
    }

    public static class TeacherServiceBatchSetClassMastersParamBuilder{
        private RequestBody requestBody;

        public TeacherServiceBatchSetClassMastersParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TeacherServiceBatchSetClassMastersParam build(){
            TeacherServiceBatchSetClassMastersParam param = new TeacherServiceBatchSetClassMastersParam();
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
         * 班级ID
         */
        private String classUid;
        /**
         * 老师手机号列表
         */
        private List<String> userPhones;

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

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public List<String> getUserPhones() {
            return this.userPhones;
        }

        public void setUserPhones(List<String> userPhones) {
            this.userPhones = userPhones;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String classUid;
            private List<String> userPhones;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder classUid(String classUid){
                this.classUid = classUid;
                return this;
            }
            public QueryBuilder userPhones(List<String> userPhones){
                this.userPhones = userPhones;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setClassUid(classUid);
                param.setUserPhones(userPhones);
                return param;
            }
        }
    }


}
