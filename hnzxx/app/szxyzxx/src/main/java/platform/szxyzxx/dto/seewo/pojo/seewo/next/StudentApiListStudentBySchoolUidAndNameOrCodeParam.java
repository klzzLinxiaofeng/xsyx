package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 通过学校id|名字|学号获取学生
 * listStudentBySchoolUidAndNameOrCode
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiListStudentBySchoolUidAndNameOrCodeParam extends OpenApiParam {


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

    public static StudentApiListStudentBySchoolUidAndNameOrCodeParamBuilder builder(){
        return new StudentApiListStudentBySchoolUidAndNameOrCodeParamBuilder();
    }

    public static class StudentApiListStudentBySchoolUidAndNameOrCodeParamBuilder{
        private JSONRequestBody requestBody;

        public StudentApiListStudentBySchoolUidAndNameOrCodeParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentApiListStudentBySchoolUidAndNameOrCodeParam build(){
            StudentApiListStudentBySchoolUidAndNameOrCodeParam param = new StudentApiListStudentBySchoolUidAndNameOrCodeParam();
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
         * 学生姓名或者学号
         */
        private String studentNameOrCode;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public String getStudentNameOrCode() {
            return this.studentNameOrCode;
        }

        public void setStudentNameOrCode(String studentNameOrCode) {
            this.studentNameOrCode = studentNameOrCode;
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
            private String studentNameOrCode;
            private String schoolUid;
            private String appId;

            public MisThirdStudentQueryDtoBuilder studentNameOrCode(String studentNameOrCode){
                this.studentNameOrCode = studentNameOrCode;
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
                param.setStudentNameOrCode(studentNameOrCode);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
