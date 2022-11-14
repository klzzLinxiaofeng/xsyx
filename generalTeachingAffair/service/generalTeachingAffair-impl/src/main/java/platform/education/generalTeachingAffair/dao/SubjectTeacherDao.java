package platform.education.generalTeachingAffair.dao;

import java.util.List;

import platform.education.generalTeachingAffair.model.SubjectTeacher;
import platform.education.generalTeachingAffair.vo.SubjectTeacherCondition;
import platform.education.generalTeachingAffair.vo.SubjectTeacherVo;
import platform.education.generalTeachingAffair.vo.SubjectTeamTeacherVo;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface SubjectTeacherDao extends GenericDao<SubjectTeacher, java.lang.Integer> {
	
	List<SubjectTeacher> findSubjectTeacherByCondition(SubjectTeacherCondition subjectTeacherCondition);

	List<SubjectTeacher> findSubjectTeacherByCondition(SubjectTeacherCondition subjectTeacherCondition, Page page, Order order);
	
	List<SubjectTeacherVo> findSubjectTeacherVoByCondition(SubjectTeacherCondition subjectTeacherCondition, Page page, Order order);
	
	SubjectTeacher findById(Integer id);
	
	List<SubjectTeacher>findSubjectTeacher();
	
	List<SubjectTeamTeacherVo> findSubjectsWithTeacher(Integer gradeId, Integer teamId);
	
}
