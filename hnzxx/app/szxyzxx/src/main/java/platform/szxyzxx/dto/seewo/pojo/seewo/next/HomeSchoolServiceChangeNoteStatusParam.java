package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 修改指定手机号家长接收的所有未读留言的状态为已读
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class HomeSchoolServiceChangeNoteStatusParam extends OpenApiParam {


    /**
     * 响应体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static HomeSchoolServiceChangeNoteStatusParamBuilder builder(){
        return new HomeSchoolServiceChangeNoteStatusParamBuilder();
    }

    public static class HomeSchoolServiceChangeNoteStatusParamBuilder{
        private RequestBody requestBody;

        public HomeSchoolServiceChangeNoteStatusParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public HomeSchoolServiceChangeNoteStatusParam build(){
            HomeSchoolServiceChangeNoteStatusParam param = new HomeSchoolServiceChangeNoteStatusParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private ChangeNoteStatusQuery query;

        public ChangeNoteStatusQuery getQuery() {
            return this.query;
        }

        public void setQuery(ChangeNoteStatusQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private ChangeNoteStatusQuery query;

            public RequestBodyBuilder query(ChangeNoteStatusQuery query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class ChangeNoteStatusQuery {
        /**
         * 家长手机号码
         */
        private String phone;
        /**
         * 应用id，必需
         */
        private String appId;

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }


        public static ChangeNoteStatusQueryBuilder builder(){
            return new ChangeNoteStatusQueryBuilder();
        }

        public static class ChangeNoteStatusQueryBuilder{
            private String phone;
            private String appId;

            public ChangeNoteStatusQueryBuilder phone(String phone){
                this.phone = phone;
                return this;
            }
            public ChangeNoteStatusQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public ChangeNoteStatusQuery build(){
                ChangeNoteStatusQuery param = new ChangeNoteStatusQuery();
                param.setPhone(phone);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
