package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学号查询家长列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ParentServiceListParentByStudentCodeResult extends OpenApiResult {

    public ParentServiceListParentByStudentCodeResult(HttpResponse response) {
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
         * 学生与家长列表
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
         * 学生学号
         */
        private String studentCode;
        /**
         * 学生ID
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
         * 家长名字
         */
        private String name;
        /**
         * 家长号码
         */
        private String phone;
        /**
         * 位置，0-3
         */
        private Integer index;

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

        public Integer getIndex() {
            return this.index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

    }


}

