package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.vo.SyllabusCondition;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.education.generalTeachingAffair.vo.SyllabusVoCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.Date;
import java.util.List;

public interface SyllabusDao extends GenericDao<Syllabus, java.lang.Integer> {

	List<Syllabus> findSyllabusByCondition(SyllabusCondition syllabusCondition, Page page, Order order);
	
	Syllabus findById(Integer id);

	Syllabus findSyllabusByAdjust(Integer teamId, Date applyDate);
	
	Long count(SyllabusCondition syllabusCondition);
	
	Syllabus findTeamSyllabus(int teamId, String termCode);
	
	List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition condition, Page page, Order order);
	
	Long findSyllabusOfCount(SyllabusVoCondition condition);

	Syllabus findSyllabusByTeamIdAndTermCode(Integer teamId, String termCode);
	List<SyllabusVo> findByAllKeBiao(Integer schoolId,Integer TeamId,String scholYear,String teamCode);
	SyllabusVo findByXiangQing(Integer id,Integer jieshu,Integer zhoushu);
}


