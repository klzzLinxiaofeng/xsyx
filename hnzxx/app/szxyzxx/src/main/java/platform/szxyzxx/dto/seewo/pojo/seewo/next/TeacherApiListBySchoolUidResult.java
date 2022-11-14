package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学校获取教师列表
 * 根据学校获取教师列表
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TeacherApiListBySchoolUidResult extends OpenApiResult {

    public TeacherApiListBySchoolUidResult(HttpResponse response) {
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
        private List<MisThirdTeacherDto> data;

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

        public List<MisThirdTeacherDto> getData() {
            return this.data;
        }

        public void setData(List<MisThirdTeacherDto> data) {
            this.data = data;
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
         * 教师的uid
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

    }


}

