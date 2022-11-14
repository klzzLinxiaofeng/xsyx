package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 添加或更新卡信息
 * 添加或更新卡信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiSaveOrUpdateCardsParam extends OpenApiParam {


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

    public static CardApiSaveOrUpdateCardsParamBuilder builder(){
        return new CardApiSaveOrUpdateCardsParamBuilder();
    }

    public static class CardApiSaveOrUpdateCardsParamBuilder{
        private JSONRequestBody requestBody;

        public CardApiSaveOrUpdateCardsParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public CardApiSaveOrUpdateCardsParam build(){
            CardApiSaveOrUpdateCardsParam param = new CardApiSaveOrUpdateCardsParam();
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
         * 卡类型
         */
        private Integer type;
        /**
         * 用户类型
         */
        private Integer userType;
        /**
         * 卡信息
         */
        private List<MisThirdCardDto> cards;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getUserType() {
            return this.userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public List<MisThirdCardDto> getCards() {
            return this.cards;
        }

        public void setCards(List<MisThirdCardDto> cards) {
            this.cards = cards;
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
            private Integer type;
            private Integer userType;
            private List<MisThirdCardDto> cards;
            private String schoolUid;
            private String appId;

            public MisThirdCardQueryDtoBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public MisThirdCardQueryDtoBuilder userType(Integer userType){
                this.userType = userType;
                return this;
            }
            public MisThirdCardQueryDtoBuilder cards(List<MisThirdCardDto> cards){
                this.cards = cards;
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
                param.setType(type);
                param.setUserType(userType);
                param.setCards(cards);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }

    public static class MisThirdCardDto {
        /**
         * 姓名
         */
        private String name;
        /**
         * 卡号
         */
        private String cardId;
        /**
         * 手机号或者学号
         */
        private String number;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number) {
            this.number = number;
        }


        public static MisThirdCardDtoBuilder builder(){
            return new MisThirdCardDtoBuilder();
        }

        public static class MisThirdCardDtoBuilder{
            private String name;
            private String cardId;
            private String number;

            public MisThirdCardDtoBuilder name(String name){
                this.name = name;
                return this;
            }
            public MisThirdCardDtoBuilder cardId(String cardId){
                this.cardId = cardId;
                return this;
            }
            public MisThirdCardDtoBuilder number(String number){
                this.number = number;
                return this;
            }

            public MisThirdCardDto build(){
                MisThirdCardDto param = new MisThirdCardDto();
                param.setName(name);
                param.setCardId(cardId);
                param.setNumber(number);
                return param;
            }
        }
    }


}
