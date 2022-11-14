package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学校uid查询班级详细信息
 * 根据学校uid查询班级详细信息
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiQueryClassDetailBySchoolUidResult extends OpenApiResult {

    public ClassApiQueryClassDetailBySchoolUidResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private ResponseBody responseBody;


    public ResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class ResponseBody {
        /**
         * 状态码
         */
        private String code;
        /**
         * data
         */
        private List<DataItem> data;
        /**
         * 消息
         */
        private String message;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static class DataItem {
        /**
         * 是否为毕业班
         */
        private Boolean isGraduate;
        /**
         * 年级名称
         */
        private String gradeName;
        /**
         * 班级uid
         */
        private String uid;
        /**
         * 学段名称
         */
        private String stageName;
        /**
         * 班级名称
         */
        private String nickName;
        /**
         * 年级
         */
        private Integer grade;
        /**
         * 班级类型，1 普通班， 2 走班
         */
        private Integer type;
        /**
         * 毕业年份
         */
        private Integer gradeYear;
        /**
         * 班级
         */
        private Integer clazz;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 学段编号
         */
        private String stageCode;
        /**
         * 班主任姓名
         */
        private String master;
        /**
         * 班主任电话
         */
        private String masterPhone;

        public Boolean getIsGraduate() {
            return this.isGraduate;
        }

        public void setIsGraduate(Boolean isGraduate) {
            this.isGraduate = isGraduate;
        }

        public String getGradeName() {
            return this.gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getStageName() {
            return this.stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
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

        public String getMaster() {
            return this.master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public String getMasterPhone() {
            return this.masterPhone;
        }

        public void setMasterPhone(String masterPhone) {
            this.masterPhone = masterPhone;
        }

    }


}

