package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.PjAptsTaskUserDao;
import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.service.PjAptsTaskUserService;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserVo;

import java.util.Date;
import java.util.List;

public class PjAptsTaskUserServiceImpl implements PjAptsTaskUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjAptsTaskUserDao pjAptsTaskUserDao;

	public void setPjAptsTaskUserDao(PjAptsTaskUserDao pjAptsTaskUserDao) {
		this.pjAptsTaskUserDao = pjAptsTaskUserDao;
	}
	
	@Override
	public PjAptsTaskUser findPjAptsTaskUserById(Integer id) {
		try {
			return pjAptsTaskUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjAptsTaskUser add(PjAptsTaskUser pjAptsTaskUser) {
		if(pjAptsTaskUser == null) {
    		return null;
    	}
    	Date createDate = pjAptsTaskUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjAptsTaskUser.setCreateDate(createDate);
    	pjAptsTaskUser.setModifyDate(createDate);
		return pjAptsTaskUserDao.create(pjAptsTaskUser);
	}

	@Override
	public PjAptsTaskUser modify(PjAptsTaskUser pjAptsTaskUser) {
		if(pjAptsTaskUser == null) {
    		return null;
    	}
    	Date modify = pjAptsTaskUser.getModifyDate();
    	pjAptsTaskUser.setModifyDate(modify != null ? modify : new Date());
		return pjAptsTaskUserDao.update(pjAptsTaskUser);
	}
	
	@Override
	public void remove(PjAptsTaskUser pjAptsTaskUser) {
		try {
			pjAptsTaskUserDao.delete(pjAptsTaskUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjAptsTaskUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Page page, Order order) {
		return pjAptsTaskUserDao.findPjAptsTaskUserByCondition(pjAptsTaskUserCondition, page, order);
	}
	
	@Override
	public List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition) {
		return pjAptsTaskUserDao.findPjAptsTaskUserByCondition(pjAptsTaskUserCondition, null, null);
	}
	
	@Override
	public List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Page page) {
		return pjAptsTaskUserDao.findPjAptsTaskUserByCondition(pjAptsTaskUserCondition, page, null);
	}
	
	@Override
	public List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Order order) {
		return pjAptsTaskUserDao.findPjAptsTaskUserByCondition(pjAptsTaskUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjAptsTaskUserDao.count(null);
	}

	@Override
	public Long count(PjAptsTaskUserCondition pjAptsTaskUserCondition) {
		return this.pjAptsTaskUserDao.count(pjAptsTaskUserCondition);
	}

	@Override
	public List<PjAptsTaskUserVo> findPjAptsTaskUserByJudgeId(Integer judgeId,
			Page page, Order order) {
		return pjAptsTaskUserDao.findPjAptsTaskUserByJudgeId(judgeId, page, order) ;
	}

	//班主任和科任教师评价记录
	@Override
	public List<PjAptsTaskUserVo> findPjAptsTaskUserVoByCondition(PjAptsTaskUserCondition condition, Page page, Order order){
		return pjAptsTaskUserDao.findPjAptsTaskUserVoByCondition(condition,page,order);
	}

	@Override
	public PjAptsTaskUserVo findAptsTaskUserVoById(Integer id) {
		return pjAptsTaskUserDao.findAptsTaskUserVoById(id);
	}

	@Override
	public List<PjAptsTaskUserVo> findAptsTaskUserVoByUserId(String termCode,
			Integer type, Integer userId, Page page, Order order) {
		
		return pjAptsTaskUserDao.findAptsTaskUserVoByUserId(termCode, type, userId, page, order);
	}

}
