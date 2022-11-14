package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 发送家长到校留言给家长指定学校所有孩子
 * 留言信息：XX学生， 你的家长已到校
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ThirdKidnoteServiceSendNoteToChildrenParam extends OpenApiParam {


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

    public static ThirdKidnoteServiceSendNoteToChildrenParamBuilder builder(){
        return new ThirdKidnoteServiceSendNoteToChildrenParamBuilder();
    }

    public static class ThirdKidnoteServiceSendNoteToChildrenParamBuilder{
        private RequestBody requestBody;

        public ThirdKidnoteServiceSendNoteToChildrenParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ThirdKidnoteServiceSendNoteToChildrenParam build(){
            ThirdKidnoteServiceSendNoteToChildrenParam param = new ThirdKidnoteServiceSendNoteToChildrenParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * body
         */
        private SendNoteToChildrenQuery query;

        public SendNoteToChildrenQuery getQuery() {
            return this.query;
        }

        public void setQuery(SendNoteToChildrenQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private SendNoteToChildrenQuery query;

            public RequestBodyBuilder query(SendNoteToChildrenQuery query){
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

    public static class SendNoteToChildrenQuery {
        /**
         * 学校uid，长度32位
         */
        private String schoolUid;
        /**
         * 家长手机号，长度11位
         */
        private String parentPhone;
        /**
         * 开放平台APPID
         */
        private String appId;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }


        public static SendNoteToChildrenQueryBuilder builder(){
            return new SendNoteToChildrenQueryBuilder();
        }

        public static class SendNoteToChildrenQueryBuilder{
            private String schoolUid;
            private String parentPhone;
            private String appId;

            public SendNoteToChildrenQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public SendNoteToChildrenQueryBuilder parentPhone(String parentPhone){
                this.parentPhone = parentPhone;
                return this;
            }
            public SendNoteToChildrenQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public SendNoteToChildrenQuery build(){
                SendNoteToChildrenQuery param = new SendNoteToChildrenQuery();
                param.setSchoolUid(schoolUid);
                param.setParentPhone(parentPhone);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
