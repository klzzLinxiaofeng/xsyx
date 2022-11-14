package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 获取孩子与家长的当前未读消息数
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceCountUnreadNotesParam extends OpenApiParam {


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

    public static HomeSchoolServiceCountUnreadNotesParamBuilder builder(){
        return new HomeSchoolServiceCountUnreadNotesParamBuilder();
    }

    public static class HomeSchoolServiceCountUnreadNotesParamBuilder{
        private RequestBody requestBody;

        public HomeSchoolServiceCountUnreadNotesParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public HomeSchoolServiceCountUnreadNotesParam build(){
            HomeSchoolServiceCountUnreadNotesParam param = new HomeSchoolServiceCountUnreadNotesParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 请求内容
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
         * 希沃学校ID
         */
        private String schoolUid;
        /**
         * 家长手机号
         */
        private String parentPhone;
        /**
         * 学生学号
         */
        private String studentCode;

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

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String parentPhone;
            private String studentCode;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder parentPhone(String parentPhone){
                this.parentPhone = parentPhone;
                return this;
            }
            public QueryBuilder studentCode(String studentCode){
                this.studentCode = studentCode;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setParentPhone(parentPhone);
                param.setStudentCode(studentCode);
                return param;
            }
        }
    }


}
