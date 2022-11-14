package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 添加或更新教师
 * 添加或更新教师
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TeacherApiSaveOrUpdateTeachersParam extends OpenApiParam {


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

    public static TeacherApiSaveOrUpdateTeachersParamBuilder builder(){
        return new TeacherApiSaveOrUpdateTeachersParamBuilder();
    }

    public static class TeacherApiSaveOrUpdateTeachersParamBuilder{
        private JSONRequestBody requestBody;

        public TeacherApiSaveOrUpdateTeachersParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TeacherApiSaveOrUpdateTeachersParam build(){
            TeacherApiSaveOrUpdateTeachersParam param = new TeacherApiSaveOrUpdateTeachersParam();
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
         * 教师信息
         */
        private List<MisThirdTeacherDto> teachers;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public List<MisThirdTeacherDto> getTeachers() {
            return this.teachers;
        }

        public void setTeachers(List<MisThirdTeacherDto> teachers) {
            this.teachers = teachers;
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
            private List<MisThirdTeacherDto> teachers;
            private String schoolUid;
            private String appId;

            public MisThirdTeacherQueryDtoBuilder teachers(List<MisThirdTeacherDto> teachers){
                this.teachers = teachers;
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
                param.setTeachers(teachers);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }

    public static class MisThirdTeacherDto {
        /**
         * 教师手机号
         */
        private String phone;
        /**
         * 教师姓名
         */
        private String name;
        /**
         * 教师uid
         */
        private String uid;

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }


        public static MisThirdTeacherDtoBuilder builder(){
            return new MisThirdTeacherDtoBuilder();
        }

        public static class MisThirdTeacherDtoBuilder{
            private String phone;
            private String name;
            private String uid;

            public MisThirdTeacherDtoBuilder phone(String phone){
                this.phone = phone;
                return this;
            }
            public MisThirdTeacherDtoBuilder name(String name){
                this.name = name;
                return this;
            }
            public MisThirdTeacherDtoBuilder uid(String uid){
                this.uid = uid;
                return this;
            }

            public MisThirdTeacherDto build(){
                MisThirdTeacherDto param = new MisThirdTeacherDto();
                param.setPhone(phone);
                param.setName(name);
                param.setUid(uid);
                return param;
            }
        }
    }

    public static class Uid {


        public static UidBuilder builder(){
            return new UidBuilder();
        }

        public static class UidBuilder{


            public Uid build(){
                Uid param = new Uid();
                return param;
            }
        }
    }


}
