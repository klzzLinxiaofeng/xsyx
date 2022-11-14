package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.PjGroupStudentDao;
import platform.education.generalTeachingAffair.model.PjGroupStudent;
import platform.education.generalTeachingAffair.service.PjGroupStudentService;
import platform.education.generalTeachingAffair.vo.PjGroupStudentCondition;
import platform.education.generalTeachingAffair.vo.PjGroupStudentVo;

import java.util.Date;
import java.util.List;

public class PjGroupStudentServiceImpl implements PjGroupStudentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjGroupStudentDao pjGroupStudentDao;

	public void setPjGroupStudentDao(PjGroupStudentDao pjGroupStudentDao) {
		this.pjGroupStudentDao = pjGroupStudentDao;
	}
	
	@Override
	public PjGroupStudent findPjGroupStudentById(Integer id) {
		try {
			return pjGroupStudentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjGroupStudent add(PjGroupStudent pjGroupStudent) {
		if(pjGroupStudent == null) {
    		return null;
    	}
    	Date createDate = pjGroupStudent.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjGroupStudent.setCreateDate(createDate);
    	pjGroupStudent.setModifyDate(createDate);
		return pjGroupStudentDao.create(pjGroupStudent);
	}

	@Override
	public PjGroupStudent modify(PjGroupStudent pjGroupStudent) {
		if(pjGroupStudent == null) {
    		return null;
    	}
    	Date modify = pjGroupStudent.getModifyDate();
    	pjGroupStudent.setModifyDate(modify != null ? modify : new Date());
		return pjGroupStudentDao.update(pjGroupStudent);
	}
	
	@Override
	public void remove(PjGroupStudent pjGroupStudent) {
		try {
			pjGroupStudentDao.delete(pjGroupStudent);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjGroupStudent.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page, Order order) {
		return pjGroupStudentDao.findPjGroupStudentByCondition(pjGroupStudentCondition, page, order);
	}
	
	@Override
	public List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition) {
		return pjGroupStudentDao.findPjGroupStudentByCondition(pjGroupStudentCondition, null, null);
	}
	
	@Override
	public List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page) {
		return pjGroupStudentDao.findPjGroupStudentByCondition(pjGroupStudentCondition, page, null);
	}
	
	@Override
	public List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Order order) {
		return pjGroupStudentDao.findPjGroupStudentByCondition(pjGroupStudentCondition, null, order);
	}

	@Override
	public List<PjGroupStudentVo> findPjGroupStudentVoByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page, Order order){
		return pjGroupStudentDao.findPjGroupStudentVoByCondition(pjGroupStudentCondition, page, order);
	}
	

	public Long count() {
		return this.pjGroupStudentDao.count(null);
	}

	@Override
	public Long count(PjGroupStudentCondition pjGroupStudentCondition) {
		return this.pjGroupStudentDao.count(pjGroupStudentCondition);
	}

	@Override
	public List<PjGroupStudentVo> findPjGroupStudentVoByTeamId(Integer teamId){
		if(teamId==null){
			return null;
		}else{
			return this.pjGroupStudentDao.findPjGroupStudentVoByTeamId(teamId, null);
		}
	}

	@Override
	public List<PjGroupStudentVo> findPjGroupStudentVoByTeamId(Integer teamId, Integer groupId){
		if(teamId==null){
			return null;
		}else{
			return this.pjGroupStudentDao.findPjGroupStudentVoByTeamId(teamId, groupId);
		}
	}

	@Override
	public List<PjGroupStudentVo> findPjGroupStudentVoByStudentId(Integer studentId) {
		if(studentId==null){
			return null;
		}else{
			return this.pjGroupStudentDao.findPjGroupStudentVoByStudentId(studentId);
		}
	}

	@Override
	public void deleteByPjGroupStudentCondition(Integer groupId, Integer groupNumber) {
		if(groupId == null){
			try {
				throw new Exception("GroupNumber cannot null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(groupNumber == null){
			try {
				throw new Exception("GroupNumber cannot null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.pjGroupStudentDao.deleteByPjGroupStudentCondition(groupId, groupNumber);
		}
	}

}
