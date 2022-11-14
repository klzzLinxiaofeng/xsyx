package platform.education.generalTeachingAffair.dao;

import java.util.Date;
import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.vo.ClassEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseCountData;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;

public interface ApsTaskScoreDao extends GenericDao<ApsTaskScore, java.lang.Integer> {

	ApsTaskScore findById(Integer id);
	
	/**
	 * 获取评分记录（班级评价/激励评价）--通过项目id、对象id和日期
	 */
	ApsTaskScore findUnique(Integer taskItemId, Integer objectId, Date checkDate);
	
	/**
	 * 获取评分记录（班级评价/激励评价）--通过项目id、对象id和日期
	 */
	List<ApsTaskScore> findItemsOfTime(Integer taskItemId, Integer objectId, Date beginDate, Date endDate);
	
	/**
	 * 获取评分记录（课堂优化）--通过项目id、对象Id、日期和时间段（节数）
	 */
	ApsTaskScore findUniqueByCheckRange(Integer taskItemId, Integer objectId, Date checkDate, String checkRange);
	
	/**
	 * 获取某一节课 的项目记录（班级的所有学生，用于课堂优化）
	 */
	List<ApsTaskScore> findStudentsOfItemId(Integer taskItemId, Integer ParentObjectId, Date checkDate, String checkRange);
	
	/**
	 * 获取某一节课 的项目记录（班级的所有学生，用于激励评价）
	 */
	List<ApsTaskScore> findStudentOfIncrease(Integer taskItemId, Integer ParentObjectId, Date checkDate);
	
	/**
	 * 获取评价对象 某天的 某种类型的 所有记录
	 */
	List<ApsTaskScore> findScoresOfCheckType(Integer taskId, Integer objectId, Date checkDate, String checkType);
	
	/**
	 * 获取评价对象 某个时间段 某类型的 所有记录
	 */
	List<ApsTaskScore> findScoresOfTime(Integer taskId, Integer objectId, String checkType, Date beginDate, Date endDate);

	/**
	 * 根据上级id找到项目并根据对象id分组
	 */
	List<ApsTaskScore> findByParentObjectId(Integer taskId, Integer parentObjectId);
	
	/**
	 * 根据项目id 找到某个班级 某时间段 所有的记录次数(学生评价记录汇总)
	 */
	List<ApsTaskScore> findCountForTeam(Integer taskItemId, Integer ParentObjectId, Date beginDate, Date endDate);
	
	
	/**
	 * 获取评价任务下的记录，并按类型和分类返回记录次数（激励评价统计）
	 */
	List<ApsTaskScore> findIncreaseCountForTeam(Integer taskId, Integer ParentObjectId, Integer objectId, Date beginDate, Date endDate, String checkType, String category);
	
	/**
	 * 获取评分记录（发展评价）--通过项目id、对象id和日期
	 */
	ApsTaskScore findNormalScore(Integer taskItemId, Integer objectId, Date checkDate);
	
	/**
	 * 获取班级学生 对应的差评项目
	 */
	List<ClassEvaScoreData> findClassScoresForTeam(Integer taskId, Integer parentObjectId, Date beginDate, Date endDate);
	
	
	List<ApsTaskScore> findApsTaskScoreByCondition(ApsTaskScore apsTaskScore);

	/**
	 * 班级所有项目的分数（含0分）
	 */
	List<TeamEvaScoreData> findAllItemScoreForTeam(Integer taskId, String checkType, Integer teamId, Date beginDate, Date endDate);
}  

