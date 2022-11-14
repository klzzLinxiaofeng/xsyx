package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量更新学生学号
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentServiceBatchUpdateStudentcodeParam extends OpenApiParam {


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

    public static StudentServiceBatchUpdateStudentcodeParamBuilder builder(){
        return new StudentServiceBatchUpdateStudentcodeParamBuilder();
    }

    public static class StudentServiceBatchUpdateStudentcodeParamBuilder{
        private RequestBody requestBody;

        public StudentServiceBatchUpdateStudentcodeParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentServiceBatchUpdateStudentcodeParam build(){
            StudentServiceBatchUpdateStudentcodeParam param = new StudentServiceBatchUpdateStudentcodeParam();
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
         * schoolUid
         */
        private String schoolUid;
        /**
         * 学生ID与新学号
         */
        private List<UserCodesItem> userCodes;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public List<UserCodesItem> getUserCodes() {
            return this.userCodes;
        }

        public void setUserCodes(List<UserCodesItem> userCodes) {
            this.userCodes = userCodes;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String schoolUid;
            private List<UserCodesItem> userCodes;

            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder userCodes(List<UserCodesItem> userCodes){
                this.userCodes = userCodes;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setSchoolUid(schoolUid);
                param.setUserCodes(userCodes);
                return param;
            }
        }
    }

    public static class UserCodesItem {
        /**
         * uid
         */
        private String uid;
        /**
         * code
         */
        private String code;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }


        public static UserCodesItemBuilder builder(){
            return new UserCodesItemBuilder();
        }

        public static class UserCodesItemBuilder{
            private String uid;
            private String code;

            public UserCodesItemBuilder uid(String uid){
                this.uid = uid;
                return this;
            }
            public UserCodesItemBuilder code(String code){
                this.code = code;
                return this;
            }

            public UserCodesItem build(){
                UserCodesItem param = new UserCodesItem();
                param.setUid(uid);
                param.setCode(code);
                return param;
            }
        }
    }


}
