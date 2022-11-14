package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.PaperStatisticResult;
import platform.education.generalTeachingAffair.vo.ExamStatCondition;
import platform.education.generalTeachingAffair.vo.ExamStatVo;
import platform.education.generalTeachingAffair.vo.ThreeRatiosVo;

import java.util.List;
import java.util.Map;

public interface ExamStatService {
    ExamStat findExamStatById(Integer id);
	   
	ExamStat add(ExamStat examStat);
	   
	ExamStat modify(ExamStat examStat);
	   
	void remove(ExamStat examStat);
	   
	List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Page page, Order order);
	
	List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition);
	
	List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Page page);
	
	List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Order order);
	
	Long count();
	
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
	* @param examId
	* @return    设定文件 
	* @return List<PaperStatisticResult>    返回类型 
	* @throws
	 */
	List<PaperStatisticResult> findPaperStatisticByExamId(Integer examId,Integer orderBy);

	/**
	 * @function 根据examID集合获取考试信息
	 * @param examIds
	 * @return
     */
	List<ExamStatVo> findExamStatByExamIds(Integer[] examIds);

	List<ThreeRatiosVo> findExamStatTreeRatiosByExamIds(Integer[] examIds,Order order);

	List<ExamStatVo> findExamStatRankByExamIds(Integer[] examIds);
	/**
	 * 统计高分人数，低分人数，及格人数
	 * @param param highCount,lowCount,passCount
	 * @param examId examId
	 * @return
	 */
	Integer countHighAndLowAndPass(Integer param,Integer examId);
	
	void batchUpdateExamStat(List<ExamStat> list);	
	
	Map<Integer,ExamStat> findExamStatRankByExamIdObj(Object[] examIdObj);
	
	void createBatch(ExamStat[] eslist);
	
	Float findSumScoreByExamIds(Integer[] examIds);
	
}
