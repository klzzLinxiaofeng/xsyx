package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据场地id查询场地课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetableServiceListByClassRoomsParam extends OpenApiParam {


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

    public static TimetableServiceListByClassRoomsParamBuilder builder(){
        return new TimetableServiceListByClassRoomsParamBuilder();
    }

    public static class TimetableServiceListByClassRoomsParamBuilder{
        private RequestBody requestBody;

        public TimetableServiceListByClassRoomsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetableServiceListByClassRoomsParam build(){
            TimetableServiceListByClassRoomsParam param = new TimetableServiceListByClassRoomsParam();
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
         * 批量场地id
         */
        private List<String> classroomUids;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public List<String> getClassroomUids() {
            return this.classroomUids;
        }

        public void setClassroomUids(List<String> classroomUids) {
            this.classroomUids = classroomUids;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private String schoolUid;
            private List<String> classroomUids;

            public RequestBodyBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public RequestBodyBuilder classroomUids(List<String> classroomUids){
                this.classroomUids = classroomUids;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setSchoolUid(schoolUid);
                param.setClassroomUids(classroomUids);
                return param;
            }
        }
    }


}
