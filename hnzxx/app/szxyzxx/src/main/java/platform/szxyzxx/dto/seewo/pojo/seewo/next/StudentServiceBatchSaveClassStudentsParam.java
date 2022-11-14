package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量添加班级学生
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentServiceBatchSaveClassStudentsParam extends OpenApiParam {


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

    public static StudentServiceBatchSaveClassStudentsParamBuilder builder(){
        return new StudentServiceBatchSaveClassStudentsParamBuilder();
    }

    public static class StudentServiceBatchSaveClassStudentsParamBuilder{
        private RequestBody requestBody;

        public StudentServiceBatchSaveClassStudentsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentServiceBatchSaveClassStudentsParam build(){
            StudentServiceBatchSaveClassStudentsParam param = new StudentServiceBatchSaveClassStudentsParam();
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
         * 希沃学校ID
         */
        private String schoolUid;
        /**
         * 希沃班级ID
         */
        private String classUid;
        /**
         * 学生列表，最大100条
         */
        private List<StudentsItem> students;

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

        public List<StudentsItem> getStudents() {
            return this.students;
        }

        public void setStudents(List<StudentsItem> students) {
            this.students = students;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String classUid;
            private List<StudentsItem> students;

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
            public QueryBuilder students(List<StudentsItem> students){
                this.students = students;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setClassUid(classUid);
                param.setStudents(students);
                return param;
            }
        }
    }

    public static class StudentsItem {
        /**
         * 姓名
         */
        private String studentName;
        /**
         * 学号
         */
        private String studentCode;
        /**
         * 性别 1男2女
         */
        private Integer gender;
        /**
         * 一卡通卡号
         */
        private String cardNo;

        public String getStudentName() {
            return this.studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public Integer getGender() {
            return this.gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getCardNo() {
            return this.cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }


        public static StudentsItemBuilder builder(){
            return new StudentsItemBuilder();
        }

        public static class StudentsItemBuilder{
            private String studentName;
            private String studentCode;
            private Integer gender;
            private String cardNo;

            public StudentsItemBuilder studentName(String studentName){
                this.studentName = studentName;
                return this;
            }
            public StudentsItemBuilder studentCode(String studentCode){
                this.studentCode = studentCode;
                return this;
            }
            public StudentsItemBuilder gender(Integer gender){
                this.gender = gender;
                return this;
            }
            public StudentsItemBuilder cardNo(String cardNo){
                this.cardNo = cardNo;
                return this;
            }

            public StudentsItem build(){
                StudentsItem param = new StudentsItem();
                param.setStudentName(studentName);
                param.setStudentCode(studentCode);
                param.setGender(gender);
                param.setCardNo(cardNo);
                return param;
            }
        }
    }


}
