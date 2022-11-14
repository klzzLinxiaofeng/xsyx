package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.OaApplycardDepartmentCount;
import platform.education.oa.vo.OaApplycardDepartmentCountCondition;
import platform.education.oa.service.OaApplycardDepartmentCountService;
import platform.education.oa.dao.OaApplycardDepartmentCountDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class OaApplycardDepartmentCountServiceImpl implements OaApplycardDepartmentCountService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private OaApplycardDepartmentCountDao oaApplycardDepartmentCountDao;

	public void setOaApplycardDepartmentCountDao(OaApplycardDepartmentCountDao oaApplycardDepartmentCountDao) {
		this.oaApplycardDepartmentCountDao = oaApplycardDepartmentCountDao;
	}
	
	@Override
	public OaApplycardDepartmentCount findOaApplycardDepartmentCountById(Integer id) {
		try {
			return oaApplycardDepartmentCountDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public OaApplycardDepartmentCount add(OaApplycardDepartmentCount oaApplycardDepartmentCount) {
		if(oaApplycardDepartmentCount == null) {
    		return null;
    	}
    	Date createDate = oaApplycardDepartmentCount.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	oaApplycardDepartmentCount.setCreateDate(createDate);
    	oaApplycardDepartmentCount.setModifyDate(createDate);
		return oaApplycardDepartmentCountDao.create(oaApplycardDepartmentCount);
	}

	@Override
	public OaApplycardDepartmentCount modify(OaApplycardDepartmentCount oaApplycardDepartmentCount) {
		if(oaApplycardDepartmentCount == null) {
    		return null;
    	}
    	Date modify = oaApplycardDepartmentCount.getModifyDate();
    	oaApplycardDepartmentCount.setModifyDate(modify != null ? modify : new Date());
		return oaApplycardDepartmentCountDao.update(oaApplycardDepartmentCount);
	}
	
	@Override
	public void remove(OaApplycardDepartmentCount oaApplycardDepartmentCount) {
		try {
			oaApplycardDepartmentCountDao.delete(oaApplycardDepartmentCount);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", oaApplycardDepartmentCount.getId(), e);
			}
		}
	}
	
	@Override
	public List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Page page, Order order) {
		return oaApplycardDepartmentCountDao.findOaApplycardDepartmentCountByCondition(oaApplycardDepartmentCountCondition, page, order);
	}
	
	@Override
	public List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition) {
		return oaApplycardDepartmentCountDao.findOaApplycardDepartmentCountByCondition(oaApplycardDepartmentCountCondition, null, null);
	}
	
	@Override
	public List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Page page) {
		return oaApplycardDepartmentCountDao.findOaApplycardDepartmentCountByCondition(oaApplycardDepartmentCountCondition, page, null);
	}
	
	@Override
	public List<OaApplycardDepartmentCount> findOaApplycardDepartmentCountByCondition(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition, Order order) {
		return oaApplycardDepartmentCountDao.findOaApplycardDepartmentCountByCondition(oaApplycardDepartmentCountCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.oaApplycardDepartmentCountDao.count(null);
	}

	@Override
	public Long count(OaApplycardDepartmentCountCondition oaApplycardDepartmentCountCondition) {
		return this.oaApplycardDepartmentCountDao.count(oaApplycardDepartmentCountCondition);
	}

	@Override
	public List<OaApplycardDepartmentCount> findOaApplycardSqnumByCondition(
			OaApplycardDepartmentCountCondition sqCondition) {
		return this.oaApplycardDepartmentCountDao.findOaApplycardSqnumByCondition(sqCondition);
	}

}
