package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 添加或更新班级
 * 添加或更新班级
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ClassApiSaveOrUpdateClassesParam extends OpenApiParam {


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

    public static ClassApiSaveOrUpdateClassesParamBuilder builder(){
        return new ClassApiSaveOrUpdateClassesParamBuilder();
    }

    public static class ClassApiSaveOrUpdateClassesParamBuilder{
        private JSONRequestBody requestBody;

        public ClassApiSaveOrUpdateClassesParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassApiSaveOrUpdateClassesParam build(){
            ClassApiSaveOrUpdateClassesParam param = new ClassApiSaveOrUpdateClassesParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdClassQueryDto query;

        public MisThirdClassQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdClassQueryDto query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdClassQueryDto query;

            public JSONRequestBodyBuilder query(MisThirdClassQueryDto query){
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

    public static class MisThirdClassQueryDto {
        /**
         * 第三方班级id
         */
        private String classId;
        /**
         * 学段信息，小学：CodeSchoolStage_0;初中:CodeSchoolStage_1;高中:CodeSchoolStage_2
         */
        private String stageCode;
        /**
         * 班级信息,如：1，2，3
         */
        private Integer clazz;
        /**
         * 年级 值为1-12
         */
        private Integer grade;
        /**
         * 学年
         */
        private Integer gradeYear;
        /**
         * 班级名称
         */
        private String name;
        /**
         * 创建者手机号
         */
        private String phone;
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

        public String getStageCode() {
            return this.stageCode;
        }

        public void setStageCode(String stageCode) {
            this.stageCode = stageCode;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
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

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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


        public static MisThirdClassQueryDtoBuilder builder(){
            return new MisThirdClassQueryDtoBuilder();
        }

        public static class MisThirdClassQueryDtoBuilder{
            private String classId;
            private String stageCode;
            private Integer clazz;
            private Integer grade;
            private Integer gradeYear;
            private String name;
            private String phone;
            private String schoolUid;
            private String appId;

            public MisThirdClassQueryDtoBuilder classId(String classId){
                this.classId = classId;
                return this;
            }
            public MisThirdClassQueryDtoBuilder stageCode(String stageCode){
                this.stageCode = stageCode;
                return this;
            }
            public MisThirdClassQueryDtoBuilder clazz(Integer clazz){
                this.clazz = clazz;
                return this;
            }
            public MisThirdClassQueryDtoBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public MisThirdClassQueryDtoBuilder gradeYear(Integer gradeYear){
                this.gradeYear = gradeYear;
                return this;
            }
            public MisThirdClassQueryDtoBuilder name(String name){
                this.name = name;
                return this;
            }
            public MisThirdClassQueryDtoBuilder phone(String phone){
                this.phone = phone;
                return this;
            }
            public MisThirdClassQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdClassQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public MisThirdClassQueryDto build(){
                MisThirdClassQueryDto param = new MisThirdClassQueryDto();
                param.setClassId(classId);
                param.setStageCode(stageCode);
                param.setClazz(clazz);
                param.setGrade(grade);
                param.setGradeYear(gradeYear);
                param.setName(name);
                param.setPhone(phone);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
