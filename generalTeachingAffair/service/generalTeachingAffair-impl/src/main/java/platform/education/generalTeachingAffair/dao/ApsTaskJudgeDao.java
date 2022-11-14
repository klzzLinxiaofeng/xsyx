package platform.education.generalTeachingAffair.dao;

import java.util.Date;
import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ApsTaskJudge;
import platform.education.generalTeachingAffair.vo.DutyTeacherStatData;

public interface ApsTaskJudgeDao extends GenericDao<ApsTaskJudge, java.lang.Integer> {

	ApsTaskJudge findById(Integer id);
	
	List<ApsTaskJudge> findApsTaskJudgeByCondition(ApsTaskJudge apsTaskJudge);
	
	//教师的值日数据----可用于判断权限
	List<ApsTaskJudge> findByTeacher(Integer taskId, Integer teacherId, Date beginDate, Date endDate);
	
	//该年级当天(当周)的所有值日教师
	List<ApsTaskJudge> findByGrade(Integer taskId, Integer gradeId, Date beginDate, Date endDate);

	//时间相同时可获取唯一记录
	List<ApsTaskJudge> findUnique(Integer taskId, Integer gradeId, Integer teacherId, Date beginDate, Date endDate);
	
	//某年级/全校 值日教师的值日数据
	List<DutyTeacherStatData> findTeachersForStat(Integer schoolId, String schoolYear, String termCode, 
			Integer gradeId, Date beginDate, Date endDate, Page page, Order order);
			
}
