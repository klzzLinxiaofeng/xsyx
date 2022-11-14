package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据教师手机号查询教师课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetableServiceListByTeacherPhonesParam extends OpenApiParam {


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

    public static TimetableServiceListByTeacherPhonesParamBuilder builder(){
        return new TimetableServiceListByTeacherPhonesParamBuilder();
    }

    public static class TimetableServiceListByTeacherPhonesParamBuilder{
        private RequestBody requestBody;

        public TimetableServiceListByTeacherPhonesParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetableServiceListByTeacherPhonesParam build(){
            TimetableServiceListByTeacherPhonesParam param = new TimetableServiceListByTeacherPhonesParam();
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
         * 教师手机号列表
         */
        private List<String> teacherPhones;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public List<String> getTeacherPhones() {
            return this.teacherPhones;
        }

        public void setTeacherPhones(List<String> teacherPhones) {
            this.teacherPhones = teacherPhones;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private String schoolUid;
            private List<String> teacherPhones;

            public RequestBodyBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public RequestBodyBuilder teacherPhones(List<String> teacherPhones){
                this.teacherPhones = teacherPhones;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setSchoolUid(schoolUid);
                param.setTeacherPhones(teacherPhones);
                return param;
            }
        }
    }


}
