package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据条件分页查询学校班级列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceListByConditionalParam extends OpenApiParam {


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

    public static ClassServiceListByConditionalParamBuilder builder(){
        return new ClassServiceListByConditionalParamBuilder();
    }

    public static class ClassServiceListByConditionalParamBuilder{
        private RequestBody requestBody;

        public ClassServiceListByConditionalParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassServiceListByConditionalParam build(){
            ClassServiceListByConditionalParam param = new ClassServiceListByConditionalParam();
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
         * 班级类型，1行政班，2走班，默认1
         */
        private Integer type;
        /**
         * 学段
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
         * 学院ID，高教版特有
         */
        private String departmentUid;
        /**
         * 专业ID，高教版特有
         */
        private String majorId;
        /**
         * 年级ID，高教版特有
         */
        private String gradeId;
        /**
         * page
         */
        private Integer page;
        /**
         * pageSize
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

        public String getDepartmentUid() {
            return this.departmentUid;
        }

        public void setDepartmentUid(String departmentUid) {
            this.departmentUid = departmentUid;
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
            private Integer type;
            private String stageCode;
            private Integer grade;
            private Integer gradeYear;
            private Integer clazz;
            private String departmentUid;
            private String majorId;
            private String gradeId;
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
            public QueryBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public QueryBuilder stageCode(String stageCode){
                this.stageCode = stageCode;
                return this;
            }
            public QueryBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public QueryBuilder gradeYear(Integer gradeYear){
                this.gradeYear = gradeYear;
                return this;
            }
            public QueryBuilder clazz(Integer clazz){
                this.clazz = clazz;
                return this;
            }
            public QueryBuilder departmentUid(String departmentUid){
                this.departmentUid = departmentUid;
                return this;
            }
            public QueryBuilder majorId(String majorId){
                this.majorId = majorId;
                return this;
            }
            public QueryBuilder gradeId(String gradeId){
                this.gradeId = gradeId;
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
                param.setType(type);
                param.setStageCode(stageCode);
                param.setGrade(grade);
                param.setGradeYear(gradeYear);
                param.setClazz(clazz);
                param.setDepartmentUid(departmentUid);
                param.setMajorId(majorId);
                param.setGradeId(gradeId);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
