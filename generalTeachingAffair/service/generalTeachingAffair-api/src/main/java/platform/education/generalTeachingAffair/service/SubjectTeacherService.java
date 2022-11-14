package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SubjectTeacher;
import platform.education.generalTeachingAffair.vo.SubjectTeacherCondition;
import platform.education.generalTeachingAffair.vo.SubjectTeacherVo;
import platform.education.generalTeachingAffair.vo.SubjectTeamTeacherVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SubjectTeacherService {
    SubjectTeacher findSubjectTeacherById(Integer id);
	   
	SubjectTeacher add(SubjectTeacher subjectTeacher);
	   
	SubjectTeacher modify(SubjectTeacher subjectTeacher);
	   
	void remove(SubjectTeacher subjectTeacher);
	   
	List<SubjectTeacher> findSubjectTeacherByCondition(SubjectTeacherCondition subjectTeacherCondition, Page page, Order order);
	
	List<SubjectTeacherVo> findSubjectTeacherVoByCondition(SubjectTeacherCondition subjectTeacherCondition, Page page, Order order);

	void removeByTeacherId(Integer id);

	List<SubjectTeacher> findSubjectTeacherByTeacherId(Integer teacherId);
	
	/**
	 * 获取班级的课程(无论是否有科目教师)
	 */
	List<SubjectTeamTeacherVo> findSubjectsWithTeacher(Integer gradeId, Integer teamId);
	
}
