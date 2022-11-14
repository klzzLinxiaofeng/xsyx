package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量新增班级
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceBatchSaveClassParam extends OpenApiParam {


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

    public static ClassServiceBatchSaveClassParamBuilder builder(){
        return new ClassServiceBatchSaveClassParamBuilder();
    }

    public static class ClassServiceBatchSaveClassParamBuilder{
        private RequestBody requestBody;

        public ClassServiceBatchSaveClassParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassServiceBatchSaveClassParam build(){
            ClassServiceBatchSaveClassParam param = new ClassServiceBatchSaveClassParam();
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
         * 班级信息列表
         */
        private List<ClassListItem> classList;

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

        public List<ClassListItem> getClassList() {
            return this.classList;
        }

        public void setClassList(List<ClassListItem> classList) {
            this.classList = classList;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private List<ClassListItem> classList;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder classList(List<ClassListItem> classList){
                this.classList = classList;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setClassList(classList);
                return param;
            }
        }
    }

    public static class ClassListItem {
        /**
         * 班级类型：1行政班，2走班，默认1
         */
        private Integer type;
        /**
         * 学段：CodeSchoolStage_0小学，CodeSchoolStage_1初中，CodeSchoolStage_2高中，CodeSchoolStage_3自定义，CodeSchoolStage_4幼儿园
         */
        private String stageCode;
        /**
         * 年级序号
         */
        private Integer grade;
        /**
         * 学年
         */
        private Integer gradeYear;
        /**
         * 班级序号
         */
        private Integer clazz;
        /**
         * 班级名称，默认为年级+班级+‘班’
         */
        private String nickName;
        /**
         * 学院ID，大学学段时必须
         */
        private String departmentId;
        /**
         * 专业ID，大学学段时必须
         */
        private String majorId;
        /**
         * 年级ID，大学学段时必须
         */
        private String gradeId;

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getStageCode() {
            return this.stageCode;
        }

        public void setStageCode(String stageCode) {
            this.stageCode = stageCode;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public Integer getGradeYear() {
            return this.gradeYear;
        }

        public void setGradeYear(Integer gradeYear) {
            this.gradeYear = gradeYear;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getDepartmentId() {
            return this.departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getMajorId() {
            return this.majorId;
        }

        public void setMajorId(String majorId) {
            this.majorId = majorId;
        }

        public String getGradeId() {
            return this.gradeId;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }


        public static ClassListItemBuilder builder(){
            return new ClassListItemBuilder();
        }

        public static class ClassListItemBuilder{
            private Integer type;
            private String stageCode;
            private Integer grade;
            private Integer gradeYear;
            private Integer clazz;
            private String nickName;
            private String departmentId;
            private String majorId;
            private String gradeId;

            public ClassListItemBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public ClassListItemBuilder stageCode(String stageCode){
                this.stageCode = stageCode;
                return this;
            }
            public ClassListItemBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public ClassListItemBuilder gradeYear(Integer gradeYear){
                this.gradeYear = gradeYear;
                return this;
            }
            public ClassListItemBuilder clazz(Integer clazz){
                this.clazz = clazz;
                return this;
            }
            public ClassListItemBuilder nickName(String nickName){
                this.nickName = nickName;
                return this;
            }
            public ClassListItemBuilder departmentId(String departmentId){
                this.departmentId = departmentId;
                return this;
            }
            public ClassListItemBuilder majorId(String majorId){
                this.majorId = majorId;
                return this;
            }
            public ClassListItemBuilder gradeId(String gradeId){
                this.gradeId = gradeId;
                return this;
            }

            public ClassListItem build(){
                ClassListItem param = new ClassListItem();
                param.setType(type);
                param.setStageCode(stageCode);
                param.setGrade(grade);
                param.setGradeYear(gradeYear);
                param.setClazz(clazz);
                param.setNickName(nickName);
                param.setDepartmentId(departmentId);
                param.setMajorId(majorId);
                param.setGradeId(gradeId);
                return param;
            }
        }
    }


}
