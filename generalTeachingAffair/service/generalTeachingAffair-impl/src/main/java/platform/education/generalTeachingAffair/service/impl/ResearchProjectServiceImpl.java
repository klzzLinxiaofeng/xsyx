package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.ResearchProjectDao;
import platform.education.generalTeachingAffair.model.ResearchProject;
import platform.education.generalTeachingAffair.service.ResearchProjectService;
import platform.education.generalTeachingAffair.vo.ResearchProjectCondition;

public class ResearchProjectServiceImpl implements ResearchProjectService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ResearchProjectDao researchProjectDao;

	public void setResearchProjectDao(ResearchProjectDao researchProjectDao) {
		this.researchProjectDao = researchProjectDao;
	}
	
	@Override
	public ResearchProject findResearchProjectById(Integer id) {
		try {
			return researchProjectDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ResearchProject add(ResearchProject researchProject) {
		if(researchProject == null) {
    		return null;
    	}
    	Date createDate = researchProject.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	researchProject.setCreateDate(createDate);
    	researchProject.setModifyDate(createDate);
		return researchProjectDao.create(researchProject);
	}

	@Override
	public ResearchProject modify(ResearchProject researchProject) {
		if(researchProject == null) {
    		return null;
    	}
    	Date modify = researchProject.getModifyDate();
    	researchProject.setModifyDate(modify != null ? modify : new Date());
		return researchProjectDao.update(researchProject);
	}
	
	@Override
	public void remove(ResearchProject researchProject) {
		try {
			researchProjectDao.delete(researchProject);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", researchProject.getId(), e);
			}
		}
	}
	
	@Override
	public List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Page page, Order order) {
		return researchProjectDao.findResearchProjectByCondition(researchProjectCondition, page, order);
	}
	
	@Override
	public List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition) {
		return researchProjectDao.findResearchProjectByCondition(researchProjectCondition, null, null);
	}
	
	@Override
	public List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Page page) {
		return researchProjectDao.findResearchProjectByCondition(researchProjectCondition, page, null);
	}
	
	@Override
	public List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Order order) {
		return researchProjectDao.findResearchProjectByCondition(researchProjectCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.researchProjectDao.count(null);
	}

	@Override
	public Long count(ResearchProjectCondition researchProjectCondition) {
		return this.researchProjectDao.count(researchProjectCondition);
	}

	@Override
	public String moveTo(ResearchProject researchProject) {
		if(researchProject != null) {
			researchProject.setIsDelete(true);
			try {
				researchProject = this.researchProjectDao.update(researchProject);
				if(researchProject != null){
					return ResearchProjectService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", researchProject.getId(), e);
				}
				return ResearchProjectService.OPERATE_ERROR;
			}
		}
		return ResearchProjectService.OPERATE_FAIL;
	}

}
