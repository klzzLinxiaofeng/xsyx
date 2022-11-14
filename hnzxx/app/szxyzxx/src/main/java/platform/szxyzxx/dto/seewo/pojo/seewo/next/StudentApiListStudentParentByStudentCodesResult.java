package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学号获取家长信息
 * 根据学号获取家长信息
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiListStudentParentByStudentCodesResult extends OpenApiResult {

    public StudentApiListStudentParentByStudentCodesResult(HttpResponse response) {
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
         * 学号
         */
        private String studentCode;

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

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

    }


}

