package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.SubjectVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SubjectService {
	
    Subject findSubjectById(Integer id);
    
    Subject findUnique(Integer schoolId,String stageCode,String code);
    
    Subject findUnique(Integer schoolId, String code);
	   
	Subject add(Subject subject);
	   
	Subject modify(Subject subject);
	   
	void remove(Subject subject);
	
	List<Subject> findSubjectByCondition(SubjectCondition subjectCondition, Page page, Order order);
	
	List<Subject> findSubjectByName(String name,Integer schoolId,String stageCode,String subjectClass);
	
	/**
	 * 功能描述：获得本学校的授课科目
	 * @param schoolId
	 * @return
	 */
	List<Subject> findSubjectsOfSchool(Integer schoolId);

	void updateSubjectSort(String[] subjectIds, Integer schoolId);
	
	List<Subject> findAllSubjectName();

	/**
	 * 获取学校的科目信息（可根据最后修改时间增量获取）
	 */
	List<SubjectVo> findIncrementDataByModifyDate(Integer schoolId, String subjectClass, Boolean isDeleted, Date modifyDate, Integer subjectId, Boolean isGetNew);
	
	Map<String,String>  findAllSubjectNameMap();

	Map<String, String> getSubjectMap(Integer schoolId);

	Subject addGeneralSubject(Subject subject);
	
}
