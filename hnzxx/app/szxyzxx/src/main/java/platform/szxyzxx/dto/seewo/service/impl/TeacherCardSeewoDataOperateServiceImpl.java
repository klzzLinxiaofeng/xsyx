package platform.szxyzxx.dto.seewo.service.impl;

import com.seewo.open.sdk.OpenApiRequest;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsRequest;
import platform.szxyzxx.dto.seewo.service.AbsBatchSeewoBatchDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeacherCardSeewoDataOperateService;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeacherCardSeewoDataOperateServiceImpl extends AbsBatchSeewoBatchDataOperateService<Teacher> implements TeacherCardSeewoDataOperateService {

    @Override
    protected OpenApiRequest createSeewoOpenApiRequest(List<Teacher> data) {
        CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
        //响应体，MimeType为 application/json
        CardApiSaveOrForceUpdateCardsParam.RequestBody requestBody = CardApiSaveOrForceUpdateCardsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        //query
        CardApiSaveOrForceUpdateCardsParam.Object query = CardApiSaveOrForceUpdateCardsParam.Object.builder()
                .appId(getAppId())
                .userType("1")//用户类型，0学生，1老师
                .type("0")//卡号类型，0一卡通，1校徽
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        //卡号信息列表
        List<CardApiSaveOrForceUpdateCardsParam.CardsItem> cards=new ArrayList<>(data.size());
        for (Teacher teacher : data) {
            CardApiSaveOrForceUpdateCardsParam.CardsItem card = CardApiSaveOrForceUpdateCardsParam.CardsItem.builder()
                    .number(teacher.getMobile())
                    .cardId(teacher.getEmpCard())
                    .name(teacher.getName())
                    .build();
            cards.add(card);
        }
        query.setCards(cards);
        param.setRequestBody(requestBody);
        return new CardApiSaveOrForceUpdateCardsRequest(param);
    }




}
