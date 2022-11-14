package platform.szxyzxx.dto.seewo.pojo.seewo.card;


import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 根据卡id获取用户信息
 * 根据卡id获取用户信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiGetUserByCardIdRequest extends OpenApiRequest<CardApiGetUserByCardIdParam, CardApiGetUserByCardIdResult> {

    public CardApiGetUserByCardIdRequest(CardApiGetUserByCardIdParam param) {
        this();
        setBizModel(param);
    }

    public CardApiGetUserByCardIdRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/card-api/get-user-by-card-id");
        setHttpMethod("POST");
    }

    public Class<CardApiGetUserByCardIdResult> getResponseClass() {
        return CardApiGetUserByCardIdResult.class;
    }

    public Class<CardApiGetUserByCardIdParam> getDomainClass() {
        return CardApiGetUserByCardIdParam.class;
    }
}

