package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.PaperStatisticResult;
import platform.education.generalTeachingAffair.vo.ExamStatCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.ExamStatVo;
import platform.education.generalTeachingAffair.vo.ThreeRatiosVo;

import java.util.List;

public interface ExamStatDao extends GenericDao<ExamStat, java.lang.Integer> {

	List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Page page, Order order);
	
	ExamStat findById(Integer id);
	
	Long count(ExamStatCondition examStatCondition);
	
	/**
	 * 功能描述：根据examId查找出唯一记录（与pj_exam是一对一关系）
	 * 2016-01-07
	 * @param examId
	 * @return
	 */
	ExamStat findExamStatByExamId(Integer examId);
	
	
	/**
	 * 
	* @Title: findPaperStatisticByjointExamCode
	* @author pantq 
	* @Description: 根据jointExamCode 统计整份试卷
	* @param jointExamCode
	* @return    设定文件 
	* @return List<PaperStatisticResult>    返回类型 
	* @throws
	 */
	List<PaperStatisticResult> findPaperStatisticByjointExamCode(String jointExamCode,Integer orderBy);

	/**
	 * @function 根据exam ID集合获取考试的统计信息
	 * @param examIds
	 * @return
     */
	List<ExamStatVo> findExamStatByExamIds(Integer[] examIds);

	List<ThreeRatiosVo> findExamStatTreeRatiosByExamIds(Integer[] examIds, Order order);

	List<ExamStatVo> findExamStatRankByExamIds(Integer[] examIds);
	/**
	 * 统计高分人数，低分人数，及格人数
	 * @param param 1,2,3
	 * @param examId examId
	 * @return
	 */
	Integer countHighAndLowAndPass(Integer param,Integer examId);
	
	
	/**
	 * 查询本班最高分
	 * @param examId
	 * @return
	 */
	Float findExamStudentHighestScoreByExamId(Integer examId);
	
	/**
	 * 查询本班最低分
	 * @param examId
	 * @return
	 */
	Float findExamStudentLowestScoreByExamId(Integer examId);
	
	void batchUpdateExamStat(Object [] list);
	
	List<ExamStat> findExamStatByExamIdObj(Object [] list);
	
	void updateExamStatNew(ExamStat examStat);
	
	void createBatch(ExamStat[] eslist);
	
	Float findSumScoreByExamIds(Integer[] examIds);
}
