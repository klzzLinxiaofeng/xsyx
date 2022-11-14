package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据学号获取学生
 * getStudentByCodes
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiGetStudentByCodesParam extends OpenApiParam {


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

    public static StudentApiGetStudentByCodesParamBuilder builder(){
        return new StudentApiGetStudentByCodesParamBuilder();
    }

    public static class StudentApiGetStudentByCodesParamBuilder{
        private JSONRequestBody requestBody;

        public StudentApiGetStudentByCodesParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentApiGetStudentByCodesParam build(){
            StudentApiGetStudentByCodesParam param = new StudentApiGetStudentByCodesParam();
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
            private String schoolUid;
            private String appId;

            public MisThirdStudentQueryDtoBuilder studentCodes(List<String> studentCodes){
                this.studentCodes = studentCodes;
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
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
