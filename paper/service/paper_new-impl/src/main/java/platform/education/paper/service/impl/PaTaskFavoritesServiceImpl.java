package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.ExamQuestionService;
import platform.education.generalTeachingAffair.vo.ExamQuestionCondition;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
import platform.education.paper.model.PaTaskFavorites;
import platform.education.paper.model.PaUserQuestion;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.vo.PaTaskFavoritesCondition;
import platform.education.paper.vo.TaskTeamCondition;
import platform.education.paper.service.PaTaskFavoritesService;
import platform.education.paper.service.PaUserQuestionService;
import platform.education.paper.service.TaskTeamService;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.dao.PaTaskFavoritesDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaTaskFavoritesServiceImpl implements PaTaskFavoritesService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaTaskFavoritesDao paTaskFavoritesDao;
	@Resource
	private  PaUserQuestionService paUserQuestionService;
	@Resource
	private TaskTeamService taskTeamService;
	@Resource 
	private  ExamQuestionService examQuestionService;
	@Resource
	private  LpTaskExamUnitService lpTaskExamUnitService;
	public void setPaTaskFavoritesDao(PaTaskFavoritesDao paTaskFavoritesDao) {
		this.paTaskFavoritesDao = paTaskFavoritesDao;
	}
	
	@Override
	public PaTaskFavorites findPaTaskFavoritesById(Integer id) {
		try {
			return paTaskFavoritesDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaTaskFavorites add(PaTaskFavorites paTaskFavorites) {
		if(paTaskFavorites == null) {
    		return null;
    	}
    	Date createDate = paTaskFavorites.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paTaskFavorites.setCreateDate(createDate);
    	paTaskFavorites.setModifyDate(createDate);
		return paTaskFavoritesDao.create(paTaskFavorites);
	}

	@Override
	public PaTaskFavorites modify(PaTaskFavorites paTaskFavorites) {
		if(paTaskFavorites == null) {
    		return null;
    	}
    	Date modify = paTaskFavorites.getModifyDate();
    	paTaskFavorites.setModifyDate(modify != null ? modify : new Date());
		return paTaskFavoritesDao.update(paTaskFavorites);
	}
	
	@Override
	public void remove(PaTaskFavorites paTaskFavorites) {
		try {
			paTaskFavoritesDao.delete(paTaskFavorites);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paTaskFavorites.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Page page, Order order) {
		return paTaskFavoritesDao.findPaTaskFavoritesByCondition(paTaskFavoritesCondition, page, order);
	}
	
	@Override
	public List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition) {
		return paTaskFavoritesDao.findPaTaskFavoritesByCondition(paTaskFavoritesCondition, null, null);
	}
	
	@Override
	public List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Page page) {
		return paTaskFavoritesDao.findPaTaskFavoritesByCondition(paTaskFavoritesCondition, page, null);
	}
	
	@Override
	public List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Order order) {
		return paTaskFavoritesDao.findPaTaskFavoritesByCondition(paTaskFavoritesCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paTaskFavoritesDao.count(null);
	}

	@Override
	public Long count(PaTaskFavoritesCondition paTaskFavoritesCondition) {
		return this.paTaskFavoritesDao.count(paTaskFavoritesCondition);
	}

	@Override
	public void modifyPaTaskFavoritesByUserQuestionId(Integer userQuestionId,
			Integer isFavorites,Integer userId) {
		Date date=new Date();
		PaUserQuestion pq=paUserQuestionService.findPaUserQuestionById(userQuestionId);
		if(pq!=null){
			PaTaskFavoritesCondition condition=new PaTaskFavoritesCondition();
			condition.setUserQuestionId(userQuestionId);
			condition.setUserId(userId);
//			condition.setIsDeleted(false);
			List<PaTaskFavorites>pflist=paTaskFavoritesDao.findPaTaskFavoritesByCondition(condition, null, null);
			if(pflist.size()>0){
				PaTaskFavorites pf=pflist.get(0);
				pf.setIsDeleted(isFavorites==0?true:false);
				paTaskFavoritesDao.update(pf);
			}else{
				if(isFavorites==0){
					
				}else{
					Integer examQuestionId=0;
					PaTaskFavorites pf=new PaTaskFavorites();
					pf.setCreateDate(date);
					pf.setModifyDate(date);
					pf.setStudentUserId(pq.getUserId());
					pf.setTaskId(pq.getOwnerId());
					pf.setType(pq.getType());
					if(pq.getType().intValue()==PaperContans.LEARNNINGPLAYTYPE){
						
						pf.setUnitId(pq.getObjectId());
					}
					pf.setUserQuestionId(userQuestionId);
					pf.setTeamId(pq.getTeamId());
					pf.setUserId(userId);
					if(pq.getType().intValue()==PaperContans.PAPERTYPE){
						TaskTeamCondition taskTeamCondition=new TaskTeamCondition();
						taskTeamCondition.setTaskId(pq.getOwnerId());
						taskTeamCondition.setTeamId(pq.getTeamId());
						taskTeamCondition.setIsDeleted(false);
						List<TaskTeam>list=taskTeamService.findTaskTeamByCondition(taskTeamCondition);
						if(list.size()>0){
							Integer examId=list.get(0).getPjExamId();
							ExamQuestionCondition eqCondition=new ExamQuestionCondition();
							eqCondition.setExamId(examId);
							eqCondition.setQuestionUuid(pq.getQuestionUuid());
							eqCondition.setIsDeleted(false);
							List<ExamQuestion> eqlist= examQuestionService.findExamQuestionByCondition(eqCondition);
							if(eqlist!=null){
								examQuestionId=eqlist.get(0).getId();
							}
						}
						
					}else{
						LpTaskExamUnitCondition lpTaskExamUnitCondition=new LpTaskExamUnitCondition();
						lpTaskExamUnitCondition.setTaskId(pq.getOwnerId());
						lpTaskExamUnitCondition.setUnitId(pq.getObjectId());
						List<LpTaskExamUnit> list=lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
						if(list.size()>0){
							Integer examId=list.get(0).getExamId();
							ExamQuestionCondition eqCondition=new ExamQuestionCondition();
							eqCondition.setExamId(examId);
							eqCondition.setQuestionUuid(pq.getQuestionUuid());
							eqCondition.setIsDeleted(false);
							List<ExamQuestion> eqlist= examQuestionService.findExamQuestionByCondition(eqCondition);
							if(eqlist!=null){
								examQuestionId=eqlist.get(0).getId();
							}
						}
						
					}
					pf.setExamQuestionId(examQuestionId);
					paTaskFavoritesDao.create(pf);
				}
			}
		}
	}

}
