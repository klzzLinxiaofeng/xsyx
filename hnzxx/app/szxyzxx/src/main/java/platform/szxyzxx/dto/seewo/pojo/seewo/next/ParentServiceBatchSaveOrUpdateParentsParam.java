package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量添加或更新学生家长
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ParentServiceBatchSaveOrUpdateParentsParam extends OpenApiParam {


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

    public static ParentServiceBatchSaveOrUpdateParentsParamBuilder builder(){
        return new ParentServiceBatchSaveOrUpdateParentsParamBuilder();
    }

    public static class ParentServiceBatchSaveOrUpdateParentsParamBuilder{
        private RequestBody requestBody;

        public ParentServiceBatchSaveOrUpdateParentsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ParentServiceBatchSaveOrUpdateParentsParam build(){
            ParentServiceBatchSaveOrUpdateParentsParam param = new ParentServiceBatchSaveOrUpdateParentsParam();
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
         * 学生与家长列表，最大100条
         */
        private List<StudentParentsItem> studentParents;

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

        public List<StudentParentsItem> getStudentParents() {
            return this.studentParents;
        }

        public void setStudentParents(List<StudentParentsItem> studentParents) {
            this.studentParents = studentParents;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private List<StudentParentsItem> studentParents;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder studentParents(List<StudentParentsItem> studentParents){
                this.studentParents = studentParents;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setStudentParents(studentParents);
                return param;
            }
        }
    }

    public static class StudentParentsItem {
        /**
         * 学生学号
         */
        private String studentCode;
        /**
         * 家长列表，最多4个
         */
        private List<ParentsItem> parents;

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public List<ParentsItem> getParents() {
            return this.parents;
        }

        public void setParents(List<ParentsItem> parents) {
            this.parents = parents;
        }


        public static StudentParentsItemBuilder builder(){
            return new StudentParentsItemBuilder();
        }

        public static class StudentParentsItemBuilder{
            private String studentCode;
            private List<ParentsItem> parents;

            public StudentParentsItemBuilder studentCode(String studentCode){
                this.studentCode = studentCode;
                return this;
            }
            public StudentParentsItemBuilder parents(List<ParentsItem> parents){
                this.parents = parents;
                return this;
            }

            public StudentParentsItem build(){
                StudentParentsItem param = new StudentParentsItem();
                param.setStudentCode(studentCode);
                param.setParents(parents);
                return param;
            }
        }
    }

    public static class ParentsItem {
        /**
         * 名字
         */
        private String name;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 位置，取值0-3，会替换对应位置的数据
         */
        private Integer index;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getIndex() {
            return this.index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }


        public static ParentsItemBuilder builder(){
            return new ParentsItemBuilder();
        }

        public static class ParentsItemBuilder{
            private String name;
            private String phone;
            private Integer index;

            public ParentsItemBuilder name(String name){
                this.name = name;
                return this;
            }
            public ParentsItemBuilder phone(String phone){
                this.phone = phone;
                return this;
            }
            public ParentsItemBuilder index(Integer index){
                this.index = index;
                return this;
            }

            public ParentsItem build(){
                ParentsItem param = new ParentsItem();
                param.setName(name);
                param.setPhone(phone);
                param.setIndex(index);
                return param;
            }
        }
    }


}
