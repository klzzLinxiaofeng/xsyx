package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.model.SchoolBusMangerVo;

import java.util.List;

public interface SchoolBusService {

    SchoolBusMangerVo creates(String ids, SchoolBusMangerVo schoolBusMangerVo, Integer isSend, String url, String url2);

    String abandonTeacher(String ids);

    void update(List<ParentStudentBus> list, Integer systemId);
}
