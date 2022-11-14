package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据班级ID查询班级学生列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class StudentServiceListStudentByClassResult extends OpenApiResult {

    public StudentServiceListStudentByClassResult(HttpResponse response) {
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
         * code
         */
        private String code;
        /**
         * message
         */
        private String message;
        /**
         * data
         */
        private List<DataItem> data;

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

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

    }

    public static class DataItem {
        /**
         * 用户ID
         */
        private String userUid;
        /**
         * 姓名
         */
        private String userName;
        /**
         * 学号
         */
        private String studentCode;
        /**
         * 头像URL
         */
        private String photoUrl;
        /**
         * 一卡通卡号
         */
        private String cardId;
        /**
         * 家长列表
         */
        private List<ParentsItem> parents;

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getPhotoUrl() {
            return this.photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public List<ParentsItem> getParents() {
            return this.parents;
        }

        public void setParents(List<ParentsItem> parents) {
            this.parents = parents;
        }

    }

    public static class ParentsItem {
        /**
         * 家长名字，可空
         */
        private String name;
        /**
         * 家长号码
         */
        private String phone;

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

    }


}

