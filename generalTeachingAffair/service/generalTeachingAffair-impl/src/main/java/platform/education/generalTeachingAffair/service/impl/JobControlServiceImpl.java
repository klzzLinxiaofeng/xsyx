package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.JobControl;
import platform.education.generalTeachingAffair.vo.JobControlCondition;
import platform.education.generalTeachingAffair.service.JobControlService;
import platform.education.generalTeachingAffair.contants.StudentArchiveContants;
import platform.education.generalTeachingAffair.dao.JobControlDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class JobControlServiceImpl implements JobControlService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private JobControlDao jobControlDao;

	public void setJobControlDao(JobControlDao jobControlDao) {
		this.jobControlDao = jobControlDao;
	}
	
	@Override
	public JobControl findJobControlById(Integer id) {
		try {
			return jobControlDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public JobControl add(JobControl jobControl) {
		if(jobControl == null) {
    		return null;
    	}
    	Date createDate = jobControl.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	jobControl.setCreateDate(createDate);
    	jobControl.setModifyDate(createDate);
		return jobControlDao.create(jobControl);
	}

	@Override
	public JobControl modify(JobControl jobControl) {
		if(jobControl == null) {
    		return null;
    	}
    	Date modify = jobControl.getModifyDate();
    	jobControl.setModifyDate(modify != null ? modify : new Date());
		return jobControlDao.update(jobControl);
	}
	
	@Override
	public void remove(JobControl jobControl) {
		try {
			jobControlDao.delete(jobControl);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", jobControl.getId(), e);
			}
		}
	}
	
	@Override
	public List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Page page, Order order) {
		return jobControlDao.findJobControlByCondition(jobControlCondition, page, order);
	}
	
	@Override
	public List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition) {
		return jobControlDao.findJobControlByCondition(jobControlCondition, null, null);
	}
	
	@Override
	public List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Page page) {
		return jobControlDao.findJobControlByCondition(jobControlCondition, page, null);
	}
	
	@Override
	public List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Order order) {
		return jobControlDao.findJobControlByCondition(jobControlCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.jobControlDao.count(null);
	}

	@Override
	public Long count(JobControlCondition jobControlCondition) {
		return this.jobControlDao.count(jobControlCondition);
	}

	/**
	 * 允许学生档案公开给个人修改
	 */
	@Override
	public JobControl enableStudentArchiveEditing(Integer teamID, Boolean interrupteur) {
		JobControl jobControl = null;
		try{
			if(teamID == null ){
				throw new IllegalArgumentException("teamId is not null");
			}
			if(interrupteur == null){
				interrupteur = false;
			}
			String name = StudentArchiveContants.TYPE_EDIT;
			jobControl = jobControlDao.findByObjectId(teamID,name);
			if(jobControl != null){
				jobControl.setInterrupteur(interrupteur);
				jobControl = jobControlDao.update(jobControl);
			}else{
				jobControl = new JobControl();
				jobControl.setAppKey(StudentArchiveContants.TYPE_ARCHIVE_APPKEY);
				jobControl.setName(StudentArchiveContants.TYPE_EDIT);
				jobControl.setObjectId(teamID);
				jobControl.setInterrupteur(interrupteur);
				jobControl.setCreateDate(new Date());
				jobControl = jobControlDao.create(jobControl);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return jobControl;
	}
	
	/**
	 * 是否允许学生档案公开给个人修改
	 */
	@Override
	public Boolean studentArchiveCanEdit(Integer teamID) {
		Boolean interrupteur = null;
		try{
			if(teamID == null ){
				throw new IllegalArgumentException("teamId is not null");
			}
			String name = StudentArchiveContants.TYPE_EDIT;
			JobControl jobControl = jobControlDao.findByObjectId(teamID,name);
			if(jobControl == null){
				return true;
			}
			interrupteur = jobControl.getInterrupteur();
			return interrupteur;
			
		}catch(Exception e){
			log.error(e.toString());
		}
		return interrupteur;
	}


	/**
	 * 是否允许学生档案公开给个人修改
	 * @param name
	 */
	@Override
	public Boolean studentArchiveCanEditApplets(String name) {
		Boolean interrupteur = null;
		try{
			JobControl jobControl = jobControlDao.findByName(name);
			if(jobControl == null){
				return false;
			}
			interrupteur = jobControl.getInterrupteur();
			return interrupteur;
		}catch(Exception e){
			log.error(e.toString());
		}
		return interrupteur;
	}


	@Override
	public JobControl findJobControl(String name, Integer objectId) {
		if(objectId == null ){
			throw new IllegalArgumentException("objectId is not null");
		}
		if(name == null || "".equals(name)){
			throw new IllegalArgumentException("name is not null");
		}
		return jobControlDao.findByObjectId(objectId, name);
	}

	@Override
	public JobControl updateArchiveStatus(String name, Integer userId, Boolean isCompleted, String appKey) {
		JobControl jobControl = jobControlDao.findByObjectId(userId, name);
		if(isCompleted == null){
			isCompleted = false;
		}
		if(appKey == null || "".equals(appKey)){
			appKey = StudentArchiveContants.TYPE_ARCHIVE_APPKEY;
		}
		if(jobControl != null){
			jobControl.setInterrupteur(isCompleted);
			jobControl = jobControlDao.update(jobControl);
		}else{
			jobControl = new JobControl();
			jobControl.setAppKey(appKey);
			jobControl.setName(name);
			jobControl.setObjectId(userId);
			jobControl.setInterrupteur(isCompleted);
			jobControl.setState("");
			jobControl.setIsDeleted(false);
			jobControl.setCreateDate(new Date());
			jobControl.setModifyDate(new Date());
			jobControl = jobControlDao.create(jobControl);
		}
		return jobControl;
	}

	@Override
	public void modifyAppletsInterrupteur(Boolean boo, String name) {
		JobControl job = jobControlDao.findByName(name);
		if(job != null && job.getId() != null){
			JobControl jobControl = new JobControl();
			jobControl.setInterrupteur(boo);
			jobControl.setModifyDate(new Date());
			jobControl.setId(job.getId());
			jobControlDao.update(jobControl);
		}
	}

}
