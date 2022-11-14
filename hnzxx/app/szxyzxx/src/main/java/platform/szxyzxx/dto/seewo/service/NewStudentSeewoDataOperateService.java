package platform.szxyzxx.dto.seewo.service;

import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;

import java.util.List;
import java.util.Map;

public interface NewStudentSeewoDataOperateService {

    List<Map<String,Object>> queryByClassUid(String classUid);

    List<Map<String,Object>> queryAll();

    BasicResponseResult addAll(List<StudentInfo> list, String classUid);

    BasicResponseResult deleteAll(List<Map<String,Object>> seewoQueryList,String classUid);


    BasicResponseResult updateCarNo(List<Map<String,Object>> seewoQueryList);

}
