package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.ExamStatDao;
import platform.education.generalTeachingAffair.dao.PjExamDao;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.PaperStatisticResult;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.vo.ExamStatCondition;
import platform.education.generalTeachingAffair.vo.ExamStatVo;
import platform.education.generalTeachingAffair.vo.ThreeRatiosVo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamStatServiceImpl implements ExamStatService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamStatDao examStatDao;

	private PjExamDao pjExamDao;
	
	public void setExamStatDao(ExamStatDao examStatDao) {
		this.examStatDao = examStatDao;
	}
	
	public void setPjExamDao(PjExamDao pjExamDao) {
		this.pjExamDao = pjExamDao;
	}
	
	@Override
	public ExamStat findExamStatById(Integer id) {
		try {
			return examStatDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamStat add(ExamStat examStat) {
		if(examStat == null) {
    		return null;
    	}
    	Date createDate = examStat.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	examStat.setCreateDate(createDate);
    	examStat.setModifyDate(createDate);
		return examStatDao.create(examStat);
	}

	@Override
	public ExamStat modify(ExamStat examStat) {
		if(examStat == null) {
    		return null;
    	}
    	Date modify = examStat.getModifyDate();
    	examStat.setModifyDate(modify != null ? modify : new Date());
		return examStatDao.update(examStat);
	}
	
	@Override
	public void remove(ExamStat examStat) {
		try {
			examStatDao.delete(examStat);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examStat.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Page page, Order order) {
		return examStatDao.findExamStatByCondition(examStatCondition, page, order);
	}
	
	@Override
	public List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition) {
		return examStatDao.findExamStatByCondition(examStatCondition, null, null);
	}
	
	@Override
	public List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Page page) {
		return examStatDao.findExamStatByCondition(examStatCondition, page, null);
	}
	
	@Override
	public List<ExamStat> findExamStatByCondition(ExamStatCondition examStatCondition, Order order) {
		return examStatDao.findExamStatByCondition(examStatCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examStatDao.count(null);
	}

	@Override
	public Long count(ExamStatCondition examStatCondition) {
		return this.examStatDao.count(examStatCondition);
	}

	@Override
	public ExamStat findExamStatByExamId(Integer examId) {
		try {
			return examStatDao.findExamStatByExamId(examId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查找记录失败 ");
		}
		return null;
	}

	/* (非 Javadoc) 
	* <p>Title: findPaperStatisticByjointExamCode</p> 
	* <p>Description: </p> 
	* @param jointExamCode
	* @return 
	* @see platform.education.generalTeachingAffair.service.ExamStatService#findPaperStatisticByjointExamCode(java.lang.String) 
	*/
	@Override
	public List<PaperStatisticResult> findPaperStatisticByExamId(Integer examId,Integer orderBy) {
		List<PaperStatisticResult> list = null;
		PjExam pjExam = pjExamDao.findById(examId);
		if(pjExam != null){
			list = examStatDao.findPaperStatisticByjointExamCode(pjExam.getJointExamCode(),orderBy);
		}
		return list;
	}

	@Override
	public List<ExamStatVo> findExamStatByExamIds(Integer[] examIds){
		return examStatDao.findExamStatByExamIds(examIds);
	}

	@Override
	public List<ThreeRatiosVo> findExamStatTreeRatiosByExamIds(Integer[] examIds, Order order){
		return examStatDao.findExamStatTreeRatiosByExamIds(examIds,order);
	}

	@Override
	public List<ExamStatVo> findExamStatRankByExamIds(Integer[] examIds){
		return examStatDao.findExamStatRankByExamIds(examIds);
	}

	@Override
	public Integer countHighAndLowAndPass(Integer param, Integer examId) {
		
		return examStatDao.countHighAndLowAndPass(param, examId);
	}

	@Override
	public void batchUpdateExamStat(List<ExamStat> list) {
		if(list != null && list.size() > 0){
			for(ExamStat es:list){
				examStatDao.update(es);
			}
		}
	}

	
	@Override
	public Map<Integer, ExamStat> findExamStatRankByExamIdObj(Object[] examIdObj) {
		Map<Integer, ExamStat> map = null;
		if(examIdObj != null && examIdObj.length > 0) {
			
			List<ExamStat> examStatList =  examStatDao.findExamStatByExamIdObj(examIdObj);
			if(examStatList != null && examStatList.size() > 0){
				map = new HashMap<Integer, ExamStat>();
				for(ExamStat examStat :examStatList){
					map.put(examStat.getExamId(), examStat);
				}
			}
		}
		return map;
	}

	@Override
	public void createBatch(ExamStat[] eslist) {
		if(eslist.length>0){
			examStatDao.createBatch(eslist);
		}
		
	}

	@Override
	public Float findSumScoreByExamIds(Integer[] examIds) {
		
		return examStatDao.findSumScoreByExamIds(examIds);
	}

}
