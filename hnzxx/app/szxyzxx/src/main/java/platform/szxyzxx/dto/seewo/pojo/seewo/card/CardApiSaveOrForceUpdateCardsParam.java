package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 添加或强制更新卡信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiSaveOrForceUpdateCardsParam extends OpenApiParam {


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

    public static CardApiSaveOrForceUpdateCardsParamBuilder builder(){
        return new CardApiSaveOrForceUpdateCardsParamBuilder();
    }

    public static class CardApiSaveOrForceUpdateCardsParamBuilder{
        private RequestBody requestBody;

        public CardApiSaveOrForceUpdateCardsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public CardApiSaveOrForceUpdateCardsParam build(){
            CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Object query;

        public Object getQuery() {
            return this.query;
        }

        public void setQuery(Object query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Object query;

            public RequestBodyBuilder query(Object query){
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

    public static class Object {
        /**
         * 卡号信息列表
         */
        private List<CardsItem> cards;
        /**
         * appId
         */
        private String appId;
        /**
         * 用户类型，0学生，1老师
         */
        private String userType;
        /**
         * 卡号类型，0一卡通，1校徽
         */
        private String type;
        /**
         * 希沃学校UID
         */
        private String schoolUid;

        public List<CardsItem> getCards() {
            return this.cards;
        }

        public void setCards(List<CardsItem> cards) {
            this.cards = cards;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getUserType() {
            return this.userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }


        public static ObjectBuilder builder(){
            return new ObjectBuilder();
        }

        public static class ObjectBuilder{
            private List<CardsItem> cards;
            private String appId;
            private String userType;
            private String type;
            private String schoolUid;

            public ObjectBuilder cards(List<CardsItem> cards){
                this.cards = cards;
                return this;
            }
            public ObjectBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public ObjectBuilder userType(String userType){
                this.userType = userType;
                return this;
            }
            public ObjectBuilder type(String type){
                this.type = type;
                return this;
            }
            public ObjectBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }

            public Object build(){
                Object param = new Object();
                param.setCards(cards);
                param.setAppId(appId);
                param.setUserType(userType);
                param.setType(type);
                param.setSchoolUid(schoolUid);
                return param;
            }
        }
    }

    public static class CardsItem {
        /**
         * 学号或手机号
         */
        private String number;
        /**
         * 卡号
         */
        private String cardId;
        /**
         * 用户名
         */
        private String name;

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public static CardsItemBuilder builder(){
            return new CardsItemBuilder();
        }

        public static class CardsItemBuilder{
            private String number;
            private String cardId;
            private String name;

            public CardsItemBuilder number(String number){
                this.number = number;
                return this;
            }
            public CardsItemBuilder cardId(String cardId){
                this.cardId = cardId;
                return this;
            }
            public CardsItemBuilder name(String name){
                this.name = name;
                return this;
            }

            public CardsItem build(){
                CardsItem param = new CardsItem();
                param.setNumber(number);
                param.setCardId(cardId);
                param.setName(name);
                return param;
            }
        }
    }


}
