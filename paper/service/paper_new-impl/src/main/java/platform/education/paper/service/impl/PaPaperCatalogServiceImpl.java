package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.vo.AssemblySubject;
import platform.education.paper.vo.PaPaperCatalogCondition;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.dao.PaPaperCatalogDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaPaperCatalogServiceImpl implements PaPaperCatalogService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaPaperCatalogDao paPaperCatalogDao;

	public void setPaPaperCatalogDao(PaPaperCatalogDao paPaperCatalogDao) {
		this.paPaperCatalogDao = paPaperCatalogDao;
	}
	
	@Override
	public PaPaperCatalog findPaPaperCatalogById(Integer id) {
		try {
			return paPaperCatalogDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaPaperCatalog add(PaPaperCatalog paPaperCatalog) {
		if(paPaperCatalog == null) {
    		return null;
    	}
		return paPaperCatalogDao.create(paPaperCatalog);
	}

	@Override
	public PaPaperCatalog modify(PaPaperCatalog paPaperCatalog) {
		if(paPaperCatalog == null) {
    		return null;
    	}
		return paPaperCatalogDao.update(paPaperCatalog);
	}
	
	@Override
	public void remove(PaPaperCatalog paPaperCatalog) {
		try {
			paPaperCatalogDao.delete(paPaperCatalog);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paPaperCatalog.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Page page, Order order) {
		return paPaperCatalogDao.findPaPaperCatalogByCondition(paPaperCatalogCondition, page, order);
	}
	
	@Override
	public List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition) {
		return paPaperCatalogDao.findPaPaperCatalogByCondition(paPaperCatalogCondition, null, null);
	}
	
	@Override
	public List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Page page) {
		return paPaperCatalogDao.findPaPaperCatalogByCondition(paPaperCatalogCondition, page, null);
	}
	
	@Override
	public List<PaPaperCatalog> findPaPaperCatalogByCondition(PaPaperCatalogCondition paPaperCatalogCondition, Order order) {
		return paPaperCatalogDao.findPaPaperCatalogByCondition(paPaperCatalogCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paPaperCatalogDao.count(null);
	}

	@Override
	public Long count(PaPaperCatalogCondition paPaperCatalogCondition) {
		return this.paPaperCatalogDao.count(paPaperCatalogCondition);
	}

	@Override
	public void removeByPaperId(Integer paperId) {
		paPaperCatalogDao.deleteByPaperId(paperId);
	}

	@Override
	public PaPaperCatalog findPaPaperCatalogByPaperId(Integer paPaperId) {
		PaPaperCatalogCondition paPaperCatalogCondition = new PaPaperCatalogCondition();
		paPaperCatalogCondition.setPaperId(paPaperId);
		List<PaPaperCatalog> result = paPaperCatalogDao.findPaPaperCatalogByCondition(paPaperCatalogCondition, null, null);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public PaPaperCatalog findPaPaperCatalogByPaperIdAndSubjectCode(Integer paperId, String subjectCode) {
		PaPaperCatalogCondition paPaperCatalogCondition = new PaPaperCatalogCondition();
		paPaperCatalogCondition.setPaperId(paperId);
		paPaperCatalogCondition.setSubjectCode(subjectCode);
		List<PaPaperCatalog> result = this.findPaPaperCatalogByCondition(paPaperCatalogCondition);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

}
