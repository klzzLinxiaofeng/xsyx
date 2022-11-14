package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 绑定学生与班级的关系
 * bindStudentToClass
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentApiBindStudentToClassParam extends OpenApiParam {


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

    public static StudentApiBindStudentToClassParamBuilder builder(){
        return new StudentApiBindStudentToClassParamBuilder();
    }

    public static class StudentApiBindStudentToClassParamBuilder{
        private JSONRequestBody requestBody;

        public StudentApiBindStudentToClassParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentApiBindStudentToClassParam build(){
            StudentApiBindStudentToClassParam param = new StudentApiBindStudentToClassParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdStudentQueryDto query;

        public MisThirdStudentQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdStudentQueryDto query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdStudentQueryDto query;

            public JSONRequestBodyBuilder query(MisThirdStudentQueryDto query){
                this.query = query;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class MisThirdStudentQueryDto {
        /**
         * 第三方班级id（classId和seewoClassId必须有一个）
         */
        private String classId;
        /**
         * 学生信息
         */
        private List<MisThirdStudentDto> students;
        /**
         * seewo的班级id（classId和seewoClassId必须有一个）
         */
        private String seewoClassId;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public String getClassId() {
            return this.classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public List<MisThirdStudentDto> getStudents() {
            return this.students;
        }

        public void setStudents(List<MisThirdStudentDto> students) {
            this.students = students;
        }

        public String getSeewoClassId() {
            return this.seewoClassId;
        }

        public void setSeewoClassId(String seewoClassId) {
            this.seewoClassId = seewoClassId;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }


        public static MisThirdStudentQueryDtoBuilder builder(){
            return new MisThirdStudentQueryDtoBuilder();
        }

        public static class MisThirdStudentQueryDtoBuilder{
            private String classId;
            private List<MisThirdStudentDto> students;
            private String seewoClassId;
            private String schoolUid;
            private String appId;

            public MisThirdStudentQueryDtoBuilder classId(String classId){
                this.classId = classId;
                return this;
            }
            public MisThirdStudentQueryDtoBuilder students(List<MisThirdStudentDto> students){
                this.students = students;
                return this;
            }
            public MisThirdStudentQueryDtoBuilder seewoClassId(String seewoClassId){
                this.seewoClassId = seewoClassId;
                return this;
            }
            public MisThirdStudentQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdStudentQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public MisThirdStudentQueryDto build(){
                MisThirdStudentQueryDto param = new MisThirdStudentQueryDto();
                param.setClassId(classId);
                param.setStudents(students);
                param.setSeewoClassId(seewoClassId);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }

    public static class MisThirdStudentDto {
        /**
         * 学生学号
         */
        private String studentCode;
        /**
         * 学生姓名
         */
        private String studentName;
        /**
         * 一卡通号
         */
        private String cardCode;
        /**
         * 家长姓名
         */
        private String parentName;
        /**
         * 家长手机号
         */
        private String parentPhone;

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getStudentName() {
            return this.studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getCardCode() {
            return this.cardCode;
        }

        public void setCardCode(String cardCode) {
            this.cardCode = cardCode;
        }

        public String getParentName() {
            return this.parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }


        public static MisThirdStudentDtoBuilder builder(){
            return new MisThirdStudentDtoBuilder();
        }

        public static class MisThirdStudentDtoBuilder{
            private String studentCode;
            private String studentName;
            private String cardCode;
            private String parentName;
            private String parentPhone;

            public MisThirdStudentDtoBuilder studentCode(String studentCode){
                this.studentCode = studentCode;
                return this;
            }
            public MisThirdStudentDtoBuilder studentName(String studentName){
                this.studentName = studentName;
                return this;
            }
            public MisThirdStudentDtoBuilder cardCode(String cardCode){
                this.cardCode = cardCode;
                return this;
            }
            public MisThirdStudentDtoBuilder parentName(String parentName){
                this.parentName = parentName;
                return this;
            }
            public MisThirdStudentDtoBuilder parentPhone(String parentPhone){
                this.parentPhone = parentPhone;
                return this;
            }

            public MisThirdStudentDto build(){
                MisThirdStudentDto param = new MisThirdStudentDto();
                param.setStudentCode(studentCode);
                param.setStudentName(studentName);
                param.setCardCode(cardCode);
                param.setParentName(parentName);
                param.setParentPhone(parentPhone);
                return param;
            }
        }
    }


}
