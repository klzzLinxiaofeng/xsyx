package platform.education.service.dao;

import platform.education.service.model.Substitute;
import platform.education.service.vo.ApprovalVo;
import platform.education.service.vo.ExaminerVo;
import platform.education.service.vo.SubstituteCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.service.vo.SubstituteVo;

import java.util.List;

public interface SubstituteDao extends GenericDao<Substitute, Integer> {

    List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Page page, Order order);

    Substitute findById(Integer id);

    Long count(SubstituteCondition substituteCondition);

    List<ExaminerVo> findExaminerVoByCondition(SubstituteCondition substituteCondition, Page page, Order order);

    List<ApprovalVo> findSubstituteGetStatusBySchoolId(Integer schoolId);

    SubstituteVo findSubstituteVoById(Integer id);
}
