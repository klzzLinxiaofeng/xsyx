package platform.education.generalTeachingAffair.service.impl;

import platform.education.generalTeachingAffair.dao.ResPaidResourceDao;
import platform.education.generalTeachingAffair.model.ResPaidResource;
import platform.education.generalTeachingAffair.service.ResPaidResourceService;

import java.util.UUID;

/**
 * 付费资源
 */
public class ResPaidResourceServiceImpl implements ResPaidResourceService {

    ResPaidResourceDao resPaidResourceDao;

    public void setResPaidResourceDao(ResPaidResourceDao resPaidResourceDao) {
        this.resPaidResourceDao = resPaidResourceDao;
    }

    @Override
    public ResPaidResource add(ResPaidResource resPaidResource){

        UUID uuid = UUID.randomUUID();
        String result = uuid.toString();
        result = result.toLowerCase().replaceAll("-", "");
        resPaidResource.setUuid(result);

        return  resPaidResourceDao.create(resPaidResource);
    }


    @Override
    public ResPaidResource findByUuid(String uuid){

        return resPaidResourceDao.findByUuid(uuid);
    }

}
