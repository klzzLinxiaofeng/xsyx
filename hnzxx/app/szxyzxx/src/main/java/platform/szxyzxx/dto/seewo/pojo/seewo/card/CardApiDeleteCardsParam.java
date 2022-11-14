package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量删除卡信息
 * 批量删除卡信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiDeleteCardsParam extends OpenApiParam {


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

    public static CardApiDeleteCardsParamBuilder builder(){
        return new CardApiDeleteCardsParamBuilder();
    }

    public static class CardApiDeleteCardsParamBuilder{
        private JSONRequestBody requestBody;

        public CardApiDeleteCardsParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public CardApiDeleteCardsParam build(){
            CardApiDeleteCardsParam param = new CardApiDeleteCardsParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdCardQueryDto query;

        public MisThirdCardQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdCardQueryDto query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdCardQueryDto query;

            public JSONRequestBodyBuilder query(MisThirdCardQueryDto query){
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

    public static class MisThirdCardQueryDto {
        /**
         * 卡号列表
         */
        private List<String> cardIds;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public List<String> getCardIds() {
            return this.cardIds;
        }

        public void setCardIds(List<String> cardIds) {
            this.cardIds = cardIds;
        }

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


        public static MisThirdCardQueryDtoBuilder builder(){
            return new MisThirdCardQueryDtoBuilder();
        }

        public static class MisThirdCardQueryDtoBuilder{
            private List<String> cardIds;
            private String schoolUid;
            private String appId;

            public MisThirdCardQueryDtoBuilder cardIds(List<String> cardIds){
                this.cardIds = cardIds;
                return this;
            }
            public MisThirdCardQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdCardQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public MisThirdCardQueryDto build(){
                MisThirdCardQueryDto param = new MisThirdCardQueryDto();
                param.setCardIds(cardIds);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
