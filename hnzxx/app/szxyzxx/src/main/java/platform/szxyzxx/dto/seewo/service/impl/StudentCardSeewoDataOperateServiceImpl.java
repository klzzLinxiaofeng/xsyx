package platform.szxyzxx.dto.seewo.service.impl;

import com.seewo.open.sdk.OpenApiRequest;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsRequest;
import platform.szxyzxx.dto.seewo.service.AbsBatchSeewoBatchDataOperateService;
import platform.szxyzxx.dto.seewo.service.StudentCardSeewoDataOperateService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCardSeewoDataOperateServiceImpl extends AbsBatchSeewoBatchDataOperateService<Student> implements StudentCardSeewoDataOperateService {

    @Override
    protected OpenApiRequest createSeewoOpenApiRequest(List<Student> data) {
        CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
        //响应体，MimeType为 application/json
        CardApiSaveOrForceUpdateCardsParam.RequestBody requestBody = CardApiSaveOrForceUpdateCardsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        //query
        CardApiSaveOrForceUpdateCardsParam.Object query = CardApiSaveOrForceUpdateCardsParam.Object.builder()
                .appId(getAppId())
                .userType("0")//用户类型，0学生，1老师
                .type("0")//卡号类型，0一卡通，1校徽
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        //卡号信息列表
        List<CardApiSaveOrForceUpdateCardsParam.CardsItem> cards=new ArrayList<>(data.size());
        for (Student stu : data) {
            CardApiSaveOrForceUpdateCardsParam.CardsItem card = CardApiSaveOrForceUpdateCardsParam.CardsItem.builder()
                    .number(stu.getId().toString())
                    .cardId(stu.getEmpCard())
                    .name(stu.getName())
                    .build();
            cards.add(card);
        }
        query.setCards(cards);
        param.setRequestBody(requestBody);
        return new CardApiSaveOrForceUpdateCardsRequest(param);
    }




}
