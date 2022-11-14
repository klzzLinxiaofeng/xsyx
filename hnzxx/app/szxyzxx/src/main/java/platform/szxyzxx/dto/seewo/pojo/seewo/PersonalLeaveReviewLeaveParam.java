package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 审核请假记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PersonalLeaveReviewLeaveParam extends OpenApiParam {


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

    public static PersonalLeaveReviewLeaveParamBuilder builder(){
        return new PersonalLeaveReviewLeaveParamBuilder();
    }

    public static class PersonalLeaveReviewLeaveParamBuilder{
        private RequestBody requestBody;

        public PersonalLeaveReviewLeaveParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PersonalLeaveReviewLeaveParam build(){
            PersonalLeaveReviewLeaveParam param = new PersonalLeaveReviewLeaveParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Query query;

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query query;

            public RequestBodyBuilder query(Query query){
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

    public static class Query {
        /**
         * 请假记录ID
         */
        private String uid;
        /**
         * 审核状态（2：不通过，3：通过）
         */
        private Integer leaveStatus;
        /**
         * 不通过理由
         */
        private String refuseReason;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Integer getLeaveStatus() {
            return this.leaveStatus;
        }

        public void setLeaveStatus(Integer leaveStatus) {
            this.leaveStatus = leaveStatus;
        }

        public String getRefuseReason() {
            return this.refuseReason;
        }

        public void setRefuseReason(String refuseReason) {
            this.refuseReason = refuseReason;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String uid;
            private Integer leaveStatus;
            private String refuseReason;

            public QueryBuilder uid(String uid){
                this.uid = uid;
                return this;
            }
            public QueryBuilder leaveStatus(Integer leaveStatus){
                this.leaveStatus = leaveStatus;
                return this;
            }
            public QueryBuilder refuseReason(String refuseReason){
                this.refuseReason = refuseReason;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setUid(uid);
                param.setLeaveStatus(leaveStatus);
                param.setRefuseReason(refuseReason);
                return param;
            }
        }
    }


}
