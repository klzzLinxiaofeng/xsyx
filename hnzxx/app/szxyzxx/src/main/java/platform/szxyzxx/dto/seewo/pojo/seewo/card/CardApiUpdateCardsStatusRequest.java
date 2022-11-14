package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 批量修改卡状态（启用/停用）
 * 根据学校uid查询班级详细信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiUpdateCardsStatusRequest extends OpenApiRequest<CardApiUpdateCardsStatusParam, CardApiUpdateCardsStatusResult> {

    public CardApiUpdateCardsStatusRequest(CardApiUpdateCardsStatusParam param) {
        this();
        setBizModel(param);
    }

    public CardApiUpdateCardsStatusRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-basis/card-api/update-cards-status");
        setHttpMethod("POST");
    }

    public Class<CardApiUpdateCardsStatusResult> getResponseClass() {
        return CardApiUpdateCardsStatusResult.class;
    }

    public Class<CardApiUpdateCardsStatusParam> getDomainClass() {
        return CardApiUpdateCardsStatusParam.class;
    }
}

