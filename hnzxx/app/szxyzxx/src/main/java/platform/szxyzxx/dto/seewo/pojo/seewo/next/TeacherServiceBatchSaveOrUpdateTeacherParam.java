package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量添加或更新老师
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceBatchSaveOrUpdateTeacherParam extends OpenApiParam {


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

    public static TeacherServiceBatchSaveOrUpdateTeacherParamBuilder builder(){
        return new TeacherServiceBatchSaveOrUpdateTeacherParamBuilder();
    }

    public static class TeacherServiceBatchSaveOrUpdateTeacherParamBuilder{
        private RequestBody requestBody;

        public TeacherServiceBatchSaveOrUpdateTeacherParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TeacherServiceBatchSaveOrUpdateTeacherParam build(){
            TeacherServiceBatchSaveOrUpdateTeacherParam param = new TeacherServiceBatchSaveOrUpdateTeacherParam();
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
         * schoolUid
         */
        private String schoolUid;
        /**
         * 老师信息列表（1-100）
         */
        private List<Teachers> teachers;

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

        public List<Teachers> getTeachers() {
            return this.teachers;
        }

        public void setTeachers(List<Teachers> teachers) {
            this.teachers = teachers;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private List<Teachers> teachers;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder teachers(List<Teachers> teachers){
                this.teachers = teachers;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setTeachers(teachers);
                return param;
            }
        }
    }

    public static class Teachers {
        /**
         * 用户账号（手机号/邮箱）
         */
        private String account;
        /**
         * 用户名字
         */
        private String name;
        /**
         * 账号类型
phone - 手机号
email - 邮箱
         */
        private String accountType;

        public String getAccount() {
            return this.account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccountType() {
            return this.accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }


        public static TeachersBuilder builder(){
            return new TeachersBuilder();
        }

        public static class TeachersBuilder{
            private String account;
            private String name;
            private String accountType;

            public TeachersBuilder account(String account){
                this.account = account;
                return this;
            }
            public TeachersBuilder name(String name){
                this.name = name;
                return this;
            }
            public TeachersBuilder accountType(String accountType){
                this.accountType = accountType;
                return this;
            }

            public Teachers build(){
                Teachers param = new Teachers();
                param.setAccount(account);
                param.setName(name);
                param.setAccountType(accountType);
                return param;
            }
        }
    }


}
