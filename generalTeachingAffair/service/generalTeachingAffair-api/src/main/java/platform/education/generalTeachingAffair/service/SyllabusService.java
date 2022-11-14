package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.ResponseDataCarrier;
import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.vo.SyllabusCondition;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.education.generalTeachingAffair.vo.SyllabusVoCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.List;

public interface SyllabusService {
	
    Syllabus findSyllabusById(Integer id);
    
    SyllabusLesson findSyllabusLessonById(Integer id);
	   
	Syllabus add(Syllabus syllabus);

	Syllabus adjustClass(Syllabus syllabus);

	SyllabusLesson addSyllabusLesson(SyllabusLesson syllabusLesson);
	
	Syllabus modify(Syllabus syllabus);
	
	SyllabusLesson modifySyllabusLesson(SyllabusLesson syllabusLesson);
	   
	void remove(Syllabus syllabus);
	
	void removeSyllabusLesson(SyllabusLesson syllabusLesson);
	
	List<Syllabus> findSyllabusByCondition(SyllabusCondition syllabusCondition, Page page, Order order);
	
	List<Syllabus> findSyllabusByCondition(SyllabusCondition syllabusCondition);
	
	List<Syllabus> findSyllabusByCondition(SyllabusCondition syllabusCondition, Page page);
	
	List<Syllabus> findSyllabusByCondition(SyllabusCondition syllabusCondition, Order order);

	Syllabus findSyllabusByAdjust(Integer teamId, Date applyDate);
	
	List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition, Page page, Order order);
	
	List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition);
	
	List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition, Page page);
	
	List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition, Order order);
	
	Long count();
	
	Long count(SyllabusCondition syllabusCondition);
	
	Long findSyllabusOfCount();
	
	Long findSyllabusOfCount(SyllabusVoCondition condition);
	
	ResponseDataCarrier verifyRules(Syllabus syllabus);
	
	/**
	 * 根据 班级ID以及学期代码获取 当前课表主体信息 包括课表结构
	 * @param teamId 班级ID
	 * @param termCode 学期唯一代码
	 * @return
	 */
	Syllabus getTeamSyllabus(Integer teamId, String termCode);
	
	/**
	 * @param syllabusId 根据课表主体ID 获取对应的课件安排信息，与getTeamSyllabus 接口搭配使用
	 * @return
	 */
	List<SyllabusLesson> getSyllabusLessonBySyllabusId(Integer syllabusId);
	
	/**
	 * 根据教师ID 以及 学期代码 获取当前教师在某个学期下所教的科目以及对应的班级，因此只能返回 具体的课节安排
	 * @param teacherId
	 * @param termCode
	 * @return
	 */
	List<SyllabusLesson> getTeacherSyllabus(Integer teacherId, String termCode);
	
	Integer removeSyllabusLessonBySyllabusId(Integer syllabusId);

	/**
	 * @function 根据班级ID及学期代码获取某个班级下的课程表
	 * @param teamId
	 * @param termCode
	 * @date 2016-2-19
	 * @return
	 */
	List<SyllabusLesson> findSyllabusLessonByCondition(
			SyllabusLessonCondition syllabusLessonCondition, Page page,
			Order order);

	/**
	 * @function 为客户端课程表提供导入功能
	 * @date 2016-3-10
	 * @param syllabus
	 * @param syllabusLessonList
	 * @return syllabus
	 */
	Syllabus importSyllabus(Syllabus syllabus, List<SyllabusLesson> syllabusLessonList);

	/**
	 * 根据老师id删除
	 * @param teacherid
	 */
	void removeByTeacher(Integer teacherid);

}
