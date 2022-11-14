package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 添加或更新班级
 * 添加或更新班级
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ClassApiSaveOrUpdateClassesResult extends OpenApiResult {

    public ClassApiSaveOrUpdateClassesResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private JSONResponseBody responseBody;


    public JSONResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(JSONResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class JSONResponseBody {
        /**
         * 
         */
        private String code;
        /**
         * 
         */
        private String message;
        /**
         * 
         */
        private MisClassDto data;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public MisClassDto getData() {
            return this.data;
        }

        public void setData(MisClassDto data) {
            this.data = data;
        }

    }

    public static class MisClassDto {
        /**
         * 业务主键
         */
        private String uid;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 学段信息
         */
        private String stageCode;
        /**
         * 年级，如7年级
         */
        private Integer grade;
        /**
         * 年级的届数，例如2017届
         */
        private Integer gradeYear;
        /**
         * 班级
         */
        private Integer clazz;
        /**
         * 班级别名
         */
        private String nickName;
        /**
         * 第三方班级id
         */
        private String thirdClassId;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getStageCode() {
            return this.stageCode;
        }

        public void setStageCode(String stageCode) {
            this.stageCode = stageCode;
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

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getThirdClassId() {
            return this.thirdClassId;
        }

        public void setThirdClassId(String thirdClassId) {
            this.thirdClassId = thirdClassId;
        }

    }


}

