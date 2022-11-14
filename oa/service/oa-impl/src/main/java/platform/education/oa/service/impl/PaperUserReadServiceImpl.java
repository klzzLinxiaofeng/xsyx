package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.PaperUserRead;
import platform.education.oa.vo.PaperUserReadCondition;
import platform.education.oa.vo.PaperUserReadVo;
import platform.education.oa.service.PaperUserReadService;
import platform.education.oa.dao.PaperUserReadDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaperUserReadServiceImpl implements PaperUserReadService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaperUserReadDao paperUserReadDao;

	public void setPaperUserReadDao(PaperUserReadDao paperUserReadDao) {
		this.paperUserReadDao = paperUserReadDao;
	}
	
	@Override
	public PaperUserRead findPaperUserReadById(Integer id) {
		try {
			return paperUserReadDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaperUserRead findByPaperIdAndUserId(Integer ownerId,String ownerType,Integer paperId,Integer userId) {
		try {
			return paperUserReadDao.findByPaperIdAndUserId(ownerId,ownerType,paperId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在paperId为 {} ", paperId+"userId为{}"+userId);
		}
		return null;
	}
	
	
	@Override
	public PaperUserRead add(PaperUserRead paperUserRead) {
		if(paperUserRead == null) {
    		return null;
    	}
    	Date createDate = paperUserRead.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paperUserRead.setCreateDate(createDate);
    	paperUserRead.setModifyDate(createDate);
		return paperUserReadDao.create(paperUserRead);
	}

	@Override
	public PaperUserRead modify(PaperUserRead paperUserRead) {
		if(paperUserRead == null) {
    		return null;
    	}
    	Date modify = paperUserRead.getModifyDate();
    	paperUserRead.setModifyDate(modify != null ? modify : new Date());
		return paperUserReadDao.update(paperUserRead);
	}
	
	@Override
	public void remove(PaperUserRead paperUserRead) {
		try {
			paperUserReadDao.delete(paperUserRead);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paperUserRead, e);
			}
		}
	}
	
	//根据paper_id进行删除
	@Override
	public void deleteByPaperId(PaperUserRead paperUserRead){
		try {
			paperUserReadDao.deleteByPaperId(paperUserRead);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在paperId为 {} ,异常为：{}", paperUserRead, e);
			}
		}
	}
	
	@Override
	public List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Page page, Order order) {
		return paperUserReadDao.findPaperUserReadByCondition(paperUserReadCondition, page, order);
	}
	
	@Override
	public List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition) {
		return paperUserReadDao.findPaperUserReadByCondition(paperUserReadCondition, null, null);
	}
	
	@Override
	public List<PaperUserReadVo> findPaperUserReadByConditionVo(PaperUserReadCondition paperUserReadCondition, Page page, Order order) {
		return paperUserReadDao.findPaperUserReadByConditionVo(paperUserReadCondition,page,order);
	}
	
	
	@Override
	public List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Page page) {
		return paperUserReadDao.findPaperUserReadByCondition(paperUserReadCondition, page, null);
	}
	
	@Override
	public List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Order order) {
		return paperUserReadDao.findPaperUserReadByCondition(paperUserReadCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paperUserReadDao.count(null);
	}

	@Override
	public Long count(PaperUserReadCondition paperUserReadCondition) {
		return this.paperUserReadDao.count(paperUserReadCondition);
	}

}
