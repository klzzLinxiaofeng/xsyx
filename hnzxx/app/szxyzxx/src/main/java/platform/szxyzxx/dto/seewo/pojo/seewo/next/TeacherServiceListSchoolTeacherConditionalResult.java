package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 条件查询学校老师列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherServiceListSchoolTeacherConditionalResult extends OpenApiResult {

    public TeacherServiceListSchoolTeacherConditionalResult(HttpResponse response) {
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
        private Data data;

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

        public Data getData() {
            return this.data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public static class Data {
        /**
         * pageSize
         */
        private Integer pageSize;
        /**
         * page
         */
        private Integer page;
        /**
         * totalCount
         */
        private Integer totalCount;
        /**
         * result
         */
        private List<ResultItem> result;

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultItem> getResult() {
            return this.result;
        }

        public void setResult(List<ResultItem> result) {
            this.result = result;
        }

    }

    public static class ResultItem {
        /**
         * 用户ID
         */
        private String uid;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 性别，1男2女，未填写则为空
         */
        private String gender;
        /**
         * 姓名
         */
        private String realName;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getGender() {
            return this.gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRealName() {
            return this.realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

    }


}

