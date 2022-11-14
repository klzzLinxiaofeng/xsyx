package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ExamWorksTeamDao;
import platform.education.generalTeachingAffair.model.ExamWorksTeam;
import platform.education.generalTeachingAffair.service.ExamWorksTeamService;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamCondition;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExamWorksTeamServiceImpl implements ExamWorksTeamService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamWorksTeamDao examWorksTeamDao;

	public void setExamWorksTeamDao(ExamWorksTeamDao examWorksTeamDao) {
		this.examWorksTeamDao = examWorksTeamDao;
	}
	
	@Override
	public ExamWorksTeam findExamWorksTeamById(Integer id) {
		try {
			return examWorksTeamDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamWorksTeam add(ExamWorksTeam examWorksTeam) {
		if(examWorksTeam == null) {
    		return null;
    	}
    	Date createDate = examWorksTeam.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	examWorksTeam.setCreateDate(createDate);
    	examWorksTeam.setModifyDate(createDate);
		return examWorksTeamDao.create(examWorksTeam);
	}

	@Override
	public ExamWorksTeam modify(ExamWorksTeam examWorksTeam) {
		if(examWorksTeam == null) {
    		return null;
    	}
    	Date modify = examWorksTeam.getModifyDate();
    	examWorksTeam.setModifyDate(modify != null ? modify : new Date());
		return examWorksTeamDao.update(examWorksTeam);
	}
	
	@Override
	public void remove(ExamWorksTeam examWorksTeam) {
		try {
			examWorksTeamDao.delete(examWorksTeam);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examWorksTeam.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Page page, Order order) {
		return examWorksTeamDao.findExamWorksTeamByCondition(examWorksTeamCondition, page, order);
	}
	
	@Override
	public List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition) {
		return examWorksTeamDao.findExamWorksTeamByCondition(examWorksTeamCondition, null, null);
	}
	
	@Override
	public List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Page page) {
		return examWorksTeamDao.findExamWorksTeamByCondition(examWorksTeamCondition, page, null);
	}
	
	@Override
	public List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Order order) {
		return examWorksTeamDao.findExamWorksTeamByCondition(examWorksTeamCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examWorksTeamDao.count(null);
	}

	@Override
	public Long count(ExamWorksTeamCondition examWorksTeamCondition) {
		return this.examWorksTeamDao.count(examWorksTeamCondition);
	}

	@Override
	public List<ExamWorksTeam> findOfExamWorks(Integer examWorksId, Integer gradeId) {
		return this.examWorksTeamDao.findOfExamWorks(examWorksId, gradeId);
	}

	@Override
	public ExamWorksTeam findUnique(Integer examWorksId, Integer teamId) {
		return this.examWorksTeamDao.findUnique(examWorksId, teamId);
	}

	@Override
	public List<Map<String, Object>> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId) {
		return this.examWorksTeamDao.findOfExamWorksWithScore(examWorksId, gradeId, null);
	}

	@Override
	public Map<String, Object> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId, Integer teamId) {
		List<Map<String, Object>> list = this.examWorksTeamDao.findOfExamWorksWithScore(examWorksId, gradeId, teamId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Long countPublishTeam(Integer examWorksId, Integer gradeId) {
		return this.examWorksTeamDao.countPublishTeam(examWorksId, gradeId);
	}

	@Override
	public Long countPublishStudent(Integer examWorksId, Integer gradeId) {
		return this.examWorksTeamDao.countPublishStudent(examWorksId, gradeId);
	}
}
