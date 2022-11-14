package platform.education.service.service;

import org.springframework.web.multipart.MultipartFile;
import platform.education.service.model.Substitute;
import platform.education.service.vo.*;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SubstituteService {

    Substitute findSubstituteById(Integer id);

    Substitute add(Substitute substitute);

    Substitute modify(Substitute substitute);

    void remove(Substitute substitute);

    List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Page page, Order order);

    List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition);

    List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Page page);

    List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Order order);

    Long count();

    Long count(SubstituteCondition substituteCondition);

    Substitute addSubstitute(Integer userId, Integer schoolId, Integer receiver, Long startTime, Long endTime, String description, String account, String password, MultipartFile file, String appId);

    List<ExaminerVo> findExaminerVoByCondition(SubstituteCondition substituteCondition, Page page, Order order);

    List<ApprovalVo> findSubstituteGetStatusBySchoolId(Integer schoolId);

    Object updateSubstitute(Integer id, Integer userId, Integer status, String feedback);

    List<ExaminerVo> findSenderByUserIdAndSchoolId(Integer userId, Integer schoolId, Integer pageSize, Integer currentPage);

    ReceiverVo findSenderByIdAndUserIdAndSchoolId(Integer id, Integer userId, Integer schoolId);

    List<ExaminerVo> findReceiverByUserIdAndSchoolId(Integer userId, Integer schoolId, Integer pageSize, Integer currentPage);

    Object findReceiverByIdAndUserIdAndSchoolId(Integer id, Integer userId, Integer schoolId);
}
