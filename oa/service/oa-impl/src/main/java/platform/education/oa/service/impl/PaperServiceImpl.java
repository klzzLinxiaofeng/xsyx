package platform.education.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Notice;
import platform.education.oa.model.Paper;
import platform.education.oa.vo.NoticeCondition;
import platform.education.oa.vo.PaperCondition;
import platform.education.oa.vo.PaperVo;
import platform.education.oa.service.PaperService;
import platform.education.oa.dao.PaperDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaperServiceImpl implements PaperService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaperDao paperDao;

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}
	
	@Override
	public Paper findPaperById(Integer id) {
		try {
			return paperDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaperVo findPaperById1(Integer id) {
		try {
			return paperDao.findById1(id);
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
		return paperDao.create(paper);
	}

	@SuppressWarnings("null")
	@Override
	public Paper modify(Paper paper) {
		if(paper == null) {
    		return null;
    	}
    	Date modify = paper.getModifyDate();
    	paper.setModifyDate(modify != null ? modify : new Date());
    	
    	
    	String publish = paper.getPublishDate();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(publish == null || publish.equals("")){
    		paper.setPublishDate(sdf.format(new Date()));
    	}else{
    		paper.setPublishDate(publish);
    	}
//    	paper.setPublishDate(publish!=null|| !publish.equals("")?publish:sdf.format(new Date()));
    	
		return paperDao.update(paper);
	}
	
	@Override
	public void remove(Paper paper) {
		try {
			paperDao.delete(paper);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paper.getId(), e);
			}
		}
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page, Order order) {
		return paperDao.findPaperByCondition(paperCondition, page, order);
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition) {
		return paperDao.findPaperByCondition(paperCondition, null, null);
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page) {
		return paperDao.findPaperByCondition(paperCondition, page, null);
	}
	
	@Override
	public List<Paper> findPaperByCondition(PaperCondition paperCondition, Order order) {
		return paperDao.findPaperByCondition(paperCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paperDao.count(null);
	}

	@Override
	public Long count(PaperCondition paperCondition) {
		return this.paperDao.count(paperCondition);
	}

	@Override
	public List<Paper> findPaperToUser(Integer ownerId,String ownerType, Integer userId,
			Page page, Order order) {
		 
		return this.paperDao.findPaperToUser(ownerId,ownerType, userId, page, order);
	}

	@Override
	public List<PaperVo> findPaperByRelated(PaperCondition condition,
			Page page, Order order) {
		return paperDao.findPaperByRelated(condition, page, order);
	}
	
}
