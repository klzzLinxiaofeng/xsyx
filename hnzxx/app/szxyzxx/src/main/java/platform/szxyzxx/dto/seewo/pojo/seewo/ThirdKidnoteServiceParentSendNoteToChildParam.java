package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 家长发送留言给孩子
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ThirdKidnoteServiceParentSendNoteToChildParam extends OpenApiParam {


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

    public static ThirdKidnoteServiceParentSendNoteToChildParamBuilder builder(){
        return new ThirdKidnoteServiceParentSendNoteToChildParamBuilder();
    }

    public static class ThirdKidnoteServiceParentSendNoteToChildParamBuilder{
        private RequestBody requestBody;

        public ThirdKidnoteServiceParentSendNoteToChildParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ThirdKidnoteServiceParentSendNoteToChildParam build(){
            ThirdKidnoteServiceParentSendNoteToChildParam param = new ThirdKidnoteServiceParentSendNoteToChildParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private SaveNoteQuery query;

        public SaveNoteQuery getQuery() {
            return this.query;
        }

        public void setQuery(SaveNoteQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private SaveNoteQuery query;

            public RequestBodyBuilder query(SaveNoteQuery query){
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

    public static class SaveNoteQuery {
        /**
         * 学校uid，必需，32位
         */
        private String schoolUid;
        /**
         * 发送者手机号，必需，11位
         */
        private String senderPhone;
        /**
         * 接受者学号，必需，最大30位
         */
        private String receiverStudentCode;
        /**
         * 留言类型，必需：1-快捷回复（多选），2-小投票（单选）
         */
        private Integer type;
        /**
         * 留言内容，必需，最大50个字
         */
        private String content;
        /**
         * 选项内容，可选，最多5项，内容长度最大50个字
         */
        private List<String> options;
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

        public String getSenderPhone() {
            return this.senderPhone;
        }

        public void setSenderPhone(String senderPhone) {
            this.senderPhone = senderPhone;
        }

        public String getReceiverStudentCode() {
            return this.receiverStudentCode;
        }

        public void setReceiverStudentCode(String receiverStudentCode) {
            this.receiverStudentCode = receiverStudentCode;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getOptions() {
            return this.options;
        }

        public void setOptions(List<String> options) {
            this.options = options;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }


        public static SaveNoteQueryBuilder builder(){
            return new SaveNoteQueryBuilder();
        }

        public static class SaveNoteQueryBuilder{
            private String schoolUid;
            private String senderPhone;
            private String receiverStudentCode;
            private Integer type;
            private String content;
            private List<String> options;
            private String appId;

            public SaveNoteQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public SaveNoteQueryBuilder senderPhone(String senderPhone){
                this.senderPhone = senderPhone;
                return this;
            }
            public SaveNoteQueryBuilder receiverStudentCode(String receiverStudentCode){
                this.receiverStudentCode = receiverStudentCode;
                return this;
            }
            public SaveNoteQueryBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public SaveNoteQueryBuilder content(String content){
                this.content = content;
                return this;
            }
            public SaveNoteQueryBuilder options(List<String> options){
                this.options = options;
                return this;
            }
            public SaveNoteQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public SaveNoteQuery build(){
                SaveNoteQuery param = new SaveNoteQuery();
                param.setSchoolUid(schoolUid);
                param.setSenderPhone(senderPhone);
                param.setReceiverStudentCode(receiverStudentCode);
                param.setType(type);
                param.setContent(content);
                param.setOptions(options);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
