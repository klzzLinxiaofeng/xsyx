package platform.education.paper.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.paper.dao.PaperDao;
import platform.education.paper.model.Paper;
import platform.education.paper.service.PaperService;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.PaperCondition;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PaperServiceImpl implements PaperService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaperDao papaperDao;


	@Override
	public Paper findPaperById(Integer id) {
		try {
			return papaperDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Paper add(Paper paper) {
		if(paper == null) {
    		return null;
    	}
    	Date createDate = paper.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paper.setCreateDate(createDate);
    	paper.setModifyDate(createDate);
		return papaperDao.create(paper);
	}

	@Override
	public Paper modify(Paper paper) {
		if(paper == null) {
    		return null;
    	}
    	Date modify = paper.getModifyDate();
    	paper.setModifyDate(modify != null ? modify : new Date());
		return papaperDao.update(paper);
	}
	
	@Override
	public void remove(Paper paper) {
		try {
			papaperDao.delete(paper);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paper.getId(), e);
			}
		}
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page, Order order) {
		return papaperDao.findPaperByCondition(paperCondition, page, order);
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition) {
		return papaperDao.findPaperByCondition(paperCondition, null, null);
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page) {
		return papaperDao.findPaperByCondition(paperCondition, page, null);
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition, Order order) {
		return papaperDao.findPaperByCondition(paperCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.papaperDao.count(null);
	}

	@Override
	public Long count(PaperCondition paperCondition) {
		return this.papaperDao.count(paperCondition);
	}

	/* (非 Javadoc) 
	* <p>Title: findPaperByUuid</p> 
	* <p>Description: </p> 
	* @param paperUuid
	* @return 
	* @see platform.education.paper.service.PaperService#findPaperByUuid(java.lang.String) 
	*/
	@Override
	public Paper findPaperByUuid(String paperUuid) {
		
		return papaperDao.findPaperByUuid(paperUuid);
	}

	/**
	 * @return the papaperDao
	 */
	public PaperDao getPapaperDao() {
		return papaperDao;
	}

	/**
	 * @param papaperDao the papaperDao to set
	 */
	public void setPapaperDao(PaperDao papaperDao) {
		this.papaperDao = papaperDao;
	}

	public List<KnowledgeCountVo> findKnowledgeCountByPaperUuid(String paperUuid){
		return papaperDao.findKnowledgeCountByPaperUuid(paperUuid);
	}

	@Override
	public void batchUnitModify(Integer lpId) {
		papaperDao.batchUnitModify(lpId);
	}

	@Override
	public void bachTaskModify(Integer lpId) {
		papaperDao.bachTaskModify(lpId);
		
	}

	@Override
	public List<Map> findALL() {
		
		return papaperDao.findALL();
	}
}
