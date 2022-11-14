package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量修改卡状态（启用/停用）
 * 根据学校uid查询班级详细信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiUpdateCardsStatusParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private JSONRequestBody requestBody;


    public JSONRequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(JSONRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static CardApiUpdateCardsStatusParamBuilder builder(){
        return new CardApiUpdateCardsStatusParamBuilder();
    }

    public static class CardApiUpdateCardsStatusParamBuilder{
        private JSONRequestBody requestBody;

        public CardApiUpdateCardsStatusParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public CardApiUpdateCardsStatusParam build(){
            CardApiUpdateCardsStatusParam param = new CardApiUpdateCardsStatusParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdUpdateCardStatusQuery query;

        public MisThirdUpdateCardStatusQuery getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdUpdateCardStatusQuery query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdUpdateCardStatusQuery query;

            public JSONRequestBodyBuilder query(MisThirdUpdateCardStatusQuery query){
                this.query = query;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class MisThirdUpdateCardStatusQuery {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;
        /**
         * 用户类型：0-学生，1-老师
         */
        private Integer userType;
        /**
         * 卡状态：0-正常，1-停用
         */
        private Integer status;
        /**
         * 要修改的卡号列表（一次最大300）
         */
        private List<String> cardIds;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public Integer getUserType() {
            return this.userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public List<String> getCardIds() {
            return this.cardIds;
        }

        public void setCardIds(List<String> cardIds) {
            this.cardIds = cardIds;
        }


        public static MisThirdUpdateCardStatusQueryBuilder builder(){
            return new MisThirdUpdateCardStatusQueryBuilder();
        }

        public static class MisThirdUpdateCardStatusQueryBuilder{
            private String schoolUid;
            private String appId;
            private Integer userType;
            private Integer status;
            private List<String> cardIds;

            public MisThirdUpdateCardStatusQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdUpdateCardStatusQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public MisThirdUpdateCardStatusQueryBuilder userType(Integer userType){
                this.userType = userType;
                return this;
            }
            public MisThirdUpdateCardStatusQueryBuilder status(Integer status){
                this.status = status;
                return this;
            }
            public MisThirdUpdateCardStatusQueryBuilder cardIds(List<String> cardIds){
                this.cardIds = cardIds;
                return this;
            }

            public MisThirdUpdateCardStatusQuery build(){
                MisThirdUpdateCardStatusQuery param = new MisThirdUpdateCardStatusQuery();
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                param.setUserType(userType);
                param.setStatus(status);
                param.setCardIds(cardIds);
                return param;
            }
        }
    }


}
