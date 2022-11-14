package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.ExamTeamSubject;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ExamTeamSubjectService {
	List<ExamTeamSubjectVo> findGradeByCondition(Integer gradeId,Integer schoolId,String schoolYear,String schoolTrem,Integer type);
	   List<Double> findAvgScoreTeam(Integer schoolId,String schoolYear,String schoolTrem,Integer gradeId,String code,String type);
	List<Double> findAvgScoreGrade(Integer schoolId,String schoolYear,String schoolTrem,Integer gradeId,String code);
	   ExamTeamSubject add(ExamTeamSubject examTeamSubject);
	
	ExamTeamSubject addVo(ExamTeamSubjectVo examTeamSubjectVo);
	   
	ExamTeamSubject modify(ExamTeamSubject examTeamSubject);
	   
	void remove(ExamTeamSubject examTeamSubject);
	
	
	Long count();
	
	Long count(ExamTeamSubjectCondition examTeamSubjectCondition);


	ExamTeamSubjectVo addVo(GradeService gradeService, TeamService teamService,
			ExamTeamSubjectVo examTeamSubjectVo,String uuid);

	List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(TeamService teamService,
			StudentService studentService,
			ExamTeamSubjectCondition examTeamSubjectCondition, Page page,
			Order order);

	List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(
			StudentService studentService,
			ExamTeamSubjectCondition examTeamSubjectCondition);

	List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(
			StudentService studentService,
			ExamTeamSubjectCondition examTeamSubjectCondition, Page page);

	List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(
			StudentService studentService,
			ExamTeamSubjectCondition examTeamSubjectCondition, Order order);


	ExamTeamSubjectVo findExamTeamSubjectVoById(TeamService teamService,
			StudentService studentService, Integer id);
	
	List<Integer> getTeamIdByGrade(GradeService gradeService,TeamService teamService,ExamTeamSubjectVo examTeamSubjectVo);

	ExamTeamSubject findById(Integer id);
	ExamTeamSubject findByExamId(Integer id);

}
