package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.PaperDepartmentCount;
import platform.education.oa.vo.PaperDepartmentCountCondition;
import platform.education.oa.service.PaperDepartmentCountService;
import platform.education.oa.dao.PaperDepartmentCountDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaperDepartmentCountServiceImpl implements PaperDepartmentCountService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaperDepartmentCountDao paperDepartmentCountDao;

	public void setPaperDepartmentCountDao(PaperDepartmentCountDao paperDepartmentCountDao) {
		this.paperDepartmentCountDao = paperDepartmentCountDao;
	}
	
	@Override
	public PaperDepartmentCount findPaperDepartmentCountById(Integer id) {
		try {
			return paperDepartmentCountDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaperDepartmentCount add(PaperDepartmentCount paperDepartmentCount) {
		if(paperDepartmentCount == null) {
    		return null;
    	}
    	Date createDate = paperDepartmentCount.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paperDepartmentCount.setCreateDate(createDate);
    	paperDepartmentCount.setModifyDate(createDate);
		return paperDepartmentCountDao.create(paperDepartmentCount);
	}

	@Override
	public PaperDepartmentCount modify(PaperDepartmentCount paperDepartmentCount) {
		if(paperDepartmentCount == null) {
    		return null;
    	}
    	Date modify = paperDepartmentCount.getModifyDate();
    	paperDepartmentCount.setModifyDate(modify != null ? modify : new Date());
		return paperDepartmentCountDao.update(paperDepartmentCount);
	}
	
	@Override
	public void remove(PaperDepartmentCount paperDepartmentCount) {
		try {
			paperDepartmentCountDao.delete(paperDepartmentCount);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paperDepartmentCount.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Page page, Order order) {
		return paperDepartmentCountDao.findPaperDepartmentCountByCondition(paperDepartmentCountCondition, page, order);
	}
	
	@Override
	public List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition) {
		return paperDepartmentCountDao.findPaperDepartmentCountByCondition(paperDepartmentCountCondition, null, null);
	}
	
	@Override
	public List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Page page) {
		return paperDepartmentCountDao.findPaperDepartmentCountByCondition(paperDepartmentCountCondition, page, null);
	}
	
	@Override
	public List<PaperDepartmentCount> findPaperDepartmentCountByCondition(PaperDepartmentCountCondition paperDepartmentCountCondition, Order order) {
		return paperDepartmentCountDao.findPaperDepartmentCountByCondition(paperDepartmentCountCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paperDepartmentCountDao.count(null);
	}


	@Override
	public Long count(PaperDepartmentCountCondition paperDepartmentCountCondition) {
		return this.paperDepartmentCountDao.count(paperDepartmentCountCondition);
	}

}
