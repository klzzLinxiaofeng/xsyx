package platform.szxyzxx.services.path.service;

import platform.education.generalTeachingAffair.model.ResPath;

import java.util.List;

public interface ResPathService {


    List<ResPath> findAll();

    List<ResPath> findByUserTypeId(Integer userTypeId);
}
