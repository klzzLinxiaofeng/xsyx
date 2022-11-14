package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 解绑绑定学生与班级关系
 * unbindStudentToClass
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentApiUnbindStudentToClassParam extends OpenApiParam {


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

    public static StudentApiUnbindStudentToClassParamBuilder builder(){
        return new StudentApiUnbindStudentToClassParamBuilder();
    }

    public static class StudentApiUnbindStudentToClassParamBuilder{
        private JSONRequestBody requestBody;

        public StudentApiUnbindStudentToClassParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentApiUnbindStudentToClassParam build(){
            StudentApiUnbindStudentToClassParam param = new StudentApiUnbindStudentToClassParam();
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
         * 学生学号
         */
        private List<String> studentCodes;
        /**
         * 第三方班级id（classId和seewoClassId必须有一个）
         */
        private String classId;
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

        public List<String> getStudentCodes() {
            return this.studentCodes;
        }

        public void setStudentCodes(List<String> studentCodes) {
            this.studentCodes = studentCodes;
        }

        public String getClassId() {
            return this.classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
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
            private List<String> studentCodes;
            private String classId;
            private String seewoClassId;
            private String schoolUid;
            private String appId;

            public MisThirdStudentQueryDtoBuilder studentCodes(List<String> studentCodes){
                this.studentCodes = studentCodes;
                return this;
            }
            public MisThirdStudentQueryDtoBuilder classId(String classId){
                this.classId = classId;
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
                param.setStudentCodes(studentCodes);
                param.setClassId(classId);
                param.setSeewoClassId(seewoClassId);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
