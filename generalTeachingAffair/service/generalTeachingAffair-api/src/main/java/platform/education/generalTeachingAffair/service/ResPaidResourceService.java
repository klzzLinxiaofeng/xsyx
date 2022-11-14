package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.ResPaidResource;

public interface ResPaidResourceService {
    ResPaidResource add(ResPaidResource resPaidResource);

    ResPaidResource findByUuid(String uuid);
}
