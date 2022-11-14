package platform.szxyzxx.dto.seewo.pojo.seewo.card;


import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 添加或强制更新卡信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiSaveOrForceUpdateCardsRequest extends OpenApiRequest<CardApiSaveOrForceUpdateCardsParam, CardApiSaveOrForceUpdateCardsResult> {

    public CardApiSaveOrForceUpdateCardsRequest(CardApiSaveOrForceUpdateCardsParam param) {
        this();
        setBizModel(param);
    }

    public CardApiSaveOrForceUpdateCardsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/card-api/save-or-force-update-cards");
        setHttpMethod("POST");
    }

    public Class<CardApiSaveOrForceUpdateCardsResult> getResponseClass() {
        return CardApiSaveOrForceUpdateCardsResult.class;
    }

    public Class<CardApiSaveOrForceUpdateCardsParam> getDomainClass() {
        return CardApiSaveOrForceUpdateCardsParam.class;
    }
}

