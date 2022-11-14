package platform.szxyzxx.dto.seewo.pojo.seewo.card;


import com.seewo.open.sdk.OpenApiRequest;


/**
 * seewo-open API: 添加或更新卡信息
 * 添加或更新卡信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiSaveOrUpdateCardsRequest extends OpenApiRequest<CardApiSaveOrUpdateCardsParam, CardApiSaveOrUpdateCardsResult> {

    public CardApiSaveOrUpdateCardsRequest(CardApiSaveOrUpdateCardsParam param) {
        this();
        setBizModel(param);
    }

    public CardApiSaveOrUpdateCardsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/card-api/save-or-update-cards");
        setHttpMethod("POST");
    }

    public Class<CardApiSaveOrUpdateCardsResult> getResponseClass() {
        return CardApiSaveOrUpdateCardsResult.class;
    }

    public Class<CardApiSaveOrUpdateCardsParam> getDomainClass() {
        return CardApiSaveOrUpdateCardsParam.class;
    }
}

