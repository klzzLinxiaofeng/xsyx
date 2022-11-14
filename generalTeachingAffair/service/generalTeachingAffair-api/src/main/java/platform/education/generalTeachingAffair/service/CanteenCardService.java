package platform.education.generalTeachingAffair.service;

import com.hikvision.artemis.sdk.config.ArtemisConfig;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.CanteenCardPojo;

import java.util.List;

public interface CanteenCardService {
    List<CanteenCardPojo> findCanteenByCondition(CanteenCardPojo condition, Page page, Order order);

    String modify(CanteenCardPojo canteen, ArtemisConfig artemisConfig,String shitangUrl);

}
