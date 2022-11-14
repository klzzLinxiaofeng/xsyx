package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量删除卡信息
 * 批量删除卡信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiDeleteCardsRequest extends OpenApiRequest<CardApiDeleteCardsParam, CardApiDeleteCardsResult> {

    public CardApiDeleteCardsRequest(CardApiDeleteCardsParam param) {
        this();
        setBizModel(param);
    }

    public CardApiDeleteCardsRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/card-api/delete-cards");
        setHttpMethod("POST");
    }

    public Class<CardApiDeleteCardsResult> getResponseClass() {
        return CardApiDeleteCardsResult.class;
    }

    public Class<CardApiDeleteCardsParam> getDomainClass() {
        return CardApiDeleteCardsParam.class;
    }
}

