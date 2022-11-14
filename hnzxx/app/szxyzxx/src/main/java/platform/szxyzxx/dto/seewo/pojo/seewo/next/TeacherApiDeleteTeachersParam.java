package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量删除教师
 * 批量删除教师
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherApiDeleteTeachersParam extends OpenApiParam {


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

    public static TeacherApiDeleteTeachersParamBuilder builder(){
        return new TeacherApiDeleteTeachersParamBuilder();
    }

    public static class TeacherApiDeleteTeachersParamBuilder{
        private JSONRequestBody requestBody;

        public TeacherApiDeleteTeachersParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TeacherApiDeleteTeachersParam build(){
            TeacherApiDeleteTeachersParam param = new TeacherApiDeleteTeachersParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdTeacherQueryDto query;

        public MisThirdTeacherQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdTeacherQueryDto query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdTeacherQueryDto query;

            public JSONRequestBodyBuilder query(MisThirdTeacherQueryDto query){
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

    public static class MisThirdTeacherQueryDto {
        /**
         * 教师手机号列表
         */
        private List<String> phones;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public List<String> getPhones() {
            return this.phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
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


        public static MisThirdTeacherQueryDtoBuilder builder(){
            return new MisThirdTeacherQueryDtoBuilder();
        }

        public static class MisThirdTeacherQueryDtoBuilder{
            private List<String> phones;
            private String schoolUid;
            private String appId;

            public MisThirdTeacherQueryDtoBuilder phones(List<String> phones){
                this.phones = phones;
                return this;
            }
            public MisThirdTeacherQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdTeacherQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public MisThirdTeacherQueryDto build(){
                MisThirdTeacherQueryDto param = new MisThirdTeacherQueryDto();
                param.setPhones(phones);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
