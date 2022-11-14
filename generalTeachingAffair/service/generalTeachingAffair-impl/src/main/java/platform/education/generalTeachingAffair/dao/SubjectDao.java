package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.SubjectVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SubjectDao extends GenericDao<Subject, java.lang.Integer> {

	List<Subject> findSubjectByCondition(SubjectCondition subjectCondition, Page page, Order order);
	
	Subject findById(Integer id);
	
	Subject findUnique(Integer schoolId,String stageCode,String code);
	
	List<Subject>  findSubjectBySchoolIdAndStageCode(Integer schoolId,String stageCode);
	
	List<Subject>  findSubjectByName(String name, Integer schoolId, String stageCode, String subjectClass);
	
	/**
	 * 功能描述：获得本学校的授课科目
	 * @param schoolId
	 * @return
	 */
	List<Subject> findSubjectsOfSchool(Integer schoolId);
	List<Subject> findAllSubjectName();

	/**
	 * 获取学校的科目信息（可根据最后修改时间增量获取）
	 */
	List<SubjectVo> findIncrementDataByModifyDate(
			Integer schoolId, String subjectClass, Boolean isDeleted, Date modifyDate, Integer subjectId, Boolean isGetNew);
}
