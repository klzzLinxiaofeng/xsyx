package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据学号查询学生课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetableServiceListByStudentCodesParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static TimetableServiceListByStudentCodesParamBuilder builder(){
        return new TimetableServiceListByStudentCodesParamBuilder();
    }

    public static class TimetableServiceListByStudentCodesParamBuilder{
        private RequestBody requestBody;

        public TimetableServiceListByStudentCodesParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetableServiceListByStudentCodesParam build(){
            TimetableServiceListByStudentCodesParam param = new TimetableServiceListByStudentCodesParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 学生学号列表
         */
        private List<String> studentCodes;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public List<String> getStudentCodes() {
            return this.studentCodes;
        }

        public void setStudentCodes(List<String> studentCodes) {
            this.studentCodes = studentCodes;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private String schoolUid;
            private List<String> studentCodes;

            public RequestBodyBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public RequestBodyBuilder studentCodes(List<String> studentCodes){
                this.studentCodes = studentCodes;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setSchoolUid(schoolUid);
                param.setStudentCodes(studentCodes);
                return param;
            }
        }
    }


}
