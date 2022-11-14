package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学号查询家长列表[已下架]
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentServiceListParentByStudentCodeResult extends OpenApiResult {

    public StudentServiceListParentByStudentCodeResult(HttpResponse response) {
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
         * 学号
         */
        private String studentCode;
        /**
         * 用户ID
         */
        private String userUid;
        /**
         * 家长列表
         */
        private List<ParentsItem> parents;

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
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
         * 家长名，可为空
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

