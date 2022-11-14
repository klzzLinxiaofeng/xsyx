package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 课程考勤api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class LessonAttendanceLessonRequestHandlerParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private JSONRequestBody requestBody;


    public JSONRequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(JSONRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static LessonAttendanceLessonRequestHandlerParamBuilder builder(){
        return new LessonAttendanceLessonRequestHandlerParamBuilder();
    }

    public static class LessonAttendanceLessonRequestHandlerParamBuilder{
        private JSONRequestBody requestBody;

        public LessonAttendanceLessonRequestHandlerParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public LessonAttendanceLessonRequestHandlerParam build(){
            LessonAttendanceLessonRequestHandlerParam param = new LessonAttendanceLessonRequestHandlerParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 查询参数
         */
        private AttendanceLessonQuery dto;

        public AttendanceLessonQuery getDto() {
            return this.dto;
        }

        public void setDto(AttendanceLessonQuery dto) {
            this.dto = dto;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private AttendanceLessonQuery dto;

            public JSONRequestBodyBuilder dto(AttendanceLessonQuery dto){
                this.dto = dto;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setDto(dto);
                return param;
            }
        }
    }

    public static class AttendanceLessonQuery {
        /**
         * 学校UID
         */
        private String schoolUid;
        /**
         * 查询班级考勤结果日期
         */
        private String queryDate;
        /**
         * 班级ID
         */
        private String classId;
        /**
         * 课程ID
         */
        private String courseId;
        /**
         * 课节ID
         */
        private String lessonId;
        /**
         * 查询班级考勤结果：getClassAttendanceRecords；
         */
        private String method;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getQueryDate() {
            return this.queryDate;
        }

        public void setQueryDate(String queryDate) {
            this.queryDate = queryDate;
        }

        public String getClassId() {
            return this.classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getCourseId() {
            return this.courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getLessonId() {
            return this.lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getMethod() {
            return this.method;
        }

        public void setMethod(String method) {
            this.method = method;
        }


        public static AttendanceLessonQueryBuilder builder(){
            return new AttendanceLessonQueryBuilder();
        }

        public static class AttendanceLessonQueryBuilder{
            private String schoolUid;
            private String queryDate;
            private String classId;
            private String courseId;
            private String lessonId;
            private String method;

            public AttendanceLessonQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public AttendanceLessonQueryBuilder queryDate(String queryDate){
                this.queryDate = queryDate;
                return this;
            }
            public AttendanceLessonQueryBuilder classId(String classId){
                this.classId = classId;
                return this;
            }
            public AttendanceLessonQueryBuilder courseId(String courseId){
                this.courseId = courseId;
                return this;
            }
            public AttendanceLessonQueryBuilder lessonId(String lessonId){
                this.lessonId = lessonId;
                return this;
            }
            public AttendanceLessonQueryBuilder method(String method){
                this.method = method;
                return this;
            }

            public AttendanceLessonQuery build(){
                AttendanceLessonQuery param = new AttendanceLessonQuery();
                param.setSchoolUid(schoolUid);
                param.setQueryDate(queryDate);
                param.setClassId(classId);
                param.setCourseId(courseId);
                param.setLessonId(lessonId);
                param.setMethod(method);
                return param;
            }
        }
    }


}
