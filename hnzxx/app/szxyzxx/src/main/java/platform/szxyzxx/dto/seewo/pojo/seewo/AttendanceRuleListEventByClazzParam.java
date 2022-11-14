package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 查询班级的考勤规则（事件+课程）列表  
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceRuleListEventByClazzParam extends OpenApiParam {


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

    public static AttendanceRuleListEventByClazzParamBuilder builder(){
        return new AttendanceRuleListEventByClazzParamBuilder();
    }

    public static class AttendanceRuleListEventByClazzParamBuilder{
        private RequestBody requestBody;

        public AttendanceRuleListEventByClazzParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceRuleListEventByClazzParam build(){
            AttendanceRuleListEventByClazzParam param = new AttendanceRuleListEventByClazzParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * attendDate
         */
        private String attendDate;
        /**
         * appId
         */
        private String appId;
        /**
         * grade
         */
        private Integer grade;
        /**
         * classsUid
         */
        private String classsUid;
        /**
         * clazz
         */
        private Integer clazz;
        /**
         * schoolUid
         */
        private String schoolUid;

        public String getAttendDate() {
            return this.attendDate;
        }

        public void setAttendDate(String attendDate) {
            this.attendDate = attendDate;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public String getClasssUid() {
            return this.classsUid;
        }

        public void setClasssUid(String classsUid) {
            this.classsUid = classsUid;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private String attendDate;
            private String appId;
            private Integer grade;
            private String classsUid;
            private Integer clazz;
            private String schoolUid;

            public RequestBodyBuilder attendDate(String attendDate){
                this.attendDate = attendDate;
                return this;
            }
            public RequestBodyBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public RequestBodyBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public RequestBodyBuilder classsUid(String classsUid){
                this.classsUid = classsUid;
                return this;
            }
            public RequestBodyBuilder clazz(Integer clazz){
                this.clazz = clazz;
                return this;
            }
            public RequestBodyBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setAttendDate(attendDate);
                param.setAppId(appId);
                param.setGrade(grade);
                param.setClasssUid(classsUid);
                param.setClazz(clazz);
                param.setSchoolUid(schoolUid);
                return param;
            }
        }
    }


}
