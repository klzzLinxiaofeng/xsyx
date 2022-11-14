package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据班级获取学生列表
 * getStudentByClass
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentApiGetStudentByClassResult extends OpenApiResult {

    public StudentApiGetStudentByClassResult(HttpResponse response) {
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
        private List<MisStudentParentDto> data;

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

        public List<MisStudentParentDto> getData() {
            return this.data;
        }

        public void setData(List<MisStudentParentDto> data) {
            this.data = data;
        }

    }

    public static class MisStudentParentDto {
        /**
         * 家长手机号
         */
        private String parentPhone;
        /**
         * 家长名称
         */
        private String parentName;
        /**
         * 班级id
         */
        private String classId;
        /**
         * 学号
         */
        private String studentCode;
        /**
         * 一卡通号
         */
        private String cardId;
        /**
         * 用户uid
         */
        private String uid;
        /**
         * 用户组织
         */
        private String unitId;
        /**
         * 用户账号
         */
        private String username;
        /**
         * 别名
         */
        private String nickName;
        /**
         * 真实名称
         */
        private String realName;
        /**
         * 头像地址
         */
        private String photoUrl;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 注册的系统code
         */
        private String appCode;

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }

        public String getParentName() {
            return this.parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public String getClassId() {
            return this.classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUnitId() {
            return this.unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRealName() {
            return this.realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPhotoUrl() {
            return this.photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAppCode() {
            return this.appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

    }


}

