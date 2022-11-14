package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.dao.PaQuestionCatalogDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaQuestionCatalogServiceImpl implements PaQuestionCatalogService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaQuestionCatalogDao paQuestionCatalogDao;

	public void setPaQuestionCatalogDao(PaQuestionCatalogDao paQuestionCatalogDao) {
		this.paQuestionCatalogDao = paQuestionCatalogDao;
	}
	
	@Override
	public PaQuestionCatalog findPaQuestionCatalogById(Integer id) {
		try {
			return paQuestionCatalogDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaQuestionCatalog add(PaQuestionCatalog paQuestionCatalog) {
		if(paQuestionCatalog == null) {
    		return null;
    	}
		return paQuestionCatalogDao.create(paQuestionCatalog);
	}

	@Override
	public PaQuestionCatalog modify(PaQuestionCatalog paQuestionCatalog) {
		if(paQuestionCatalog == null) {
    		return null;
    	}
		return paQuestionCatalogDao.update(paQuestionCatalog);
	}
	
	@Override
	public void remove(PaQuestionCatalog paQuestionCatalog) {
		try {
			paQuestionCatalogDao.delete(paQuestionCatalog);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paQuestionCatalog.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Page page, Order order) {
		return paQuestionCatalogDao.findPaQuestionCatalogByCondition(paQuestionCatalogCondition, page, order);
	}
	
	@Override
	public List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition) {
		return paQuestionCatalogDao.findPaQuestionCatalogByCondition(paQuestionCatalogCondition, null, null);
	}
	
	@Override
	public List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Page page) {
		return paQuestionCatalogDao.findPaQuestionCatalogByCondition(paQuestionCatalogCondition, page, null);
	}
	
	@Override
	public List<PaQuestionCatalog> findPaQuestionCatalogByCondition(PaQuestionCatalogCondition paQuestionCatalogCondition, Order order) {
		return paQuestionCatalogDao.findPaQuestionCatalogByCondition(paQuestionCatalogCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paQuestionCatalogDao.count(null);
	}

	@Override
	public Long count(PaQuestionCatalogCondition paQuestionCatalogCondition) {
		return this.paQuestionCatalogDao.count(paQuestionCatalogCondition);
	}

	@Override
	public void removeByQuestionIds(Integer[] questionIds) {
		if(questionIds.length>0) {
			paQuestionCatalogDao.deleteByQuestionIds(questionIds);
		}
	}

	@Override
	public List<PaQuestionCatalog> findPaQuestionCatalogByQuestionIds(Integer[] questionIds) {
		if(questionIds.length==0) {
			return new ArrayList<PaQuestionCatalog>();
		}
		return paQuestionCatalogDao.findPaQuestionCatalogByQuestionIds(questionIds);
	}

	@Override
	public PaQuestionCatalog findPaQuestionCatalogByQuestionId(Integer questionId) {
		PaQuestionCatalogCondition paQuestionCatalogCondition = new PaQuestionCatalogCondition();
		paQuestionCatalogCondition.setQuestionId(questionId);
		List<PaQuestionCatalog> result = this.findPaQuestionCatalogByCondition(paQuestionCatalogCondition);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

}
