package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import platform.education.paper.model.PaPaperTree;
import platform.education.paper.vo.BasketGroupJson;
import platform.education.paper.vo.PaPaperTreeCondition;
import platform.education.paper.service.PaPaperTreeService;
import platform.education.paper.dao.PaPaperTreeDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaPaperTreeServiceImpl implements PaPaperTreeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaPaperTreeDao paPaperTreeDao;

	public void setPaPaperTreeDao(PaPaperTreeDao paPaperTreeDao) {
		this.paPaperTreeDao = paPaperTreeDao;
	}
	
	@Override
	public PaPaperTree findPaPaperTreeById(Integer id) {
		try {
			return paPaperTreeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaPaperTree add(PaPaperTree paPaperTree) {
		if(paPaperTree == null) {
    		return null;
    	}
    	Date createDate = paPaperTree.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paPaperTree.setCreateDate(createDate);
    	paPaperTree.setModifyDate(createDate);
		return paPaperTreeDao.create(paPaperTree);
	}

	@Override
	public PaPaperTree modify(PaPaperTree paPaperTree) {
		if(paPaperTree == null) {
    		return null;
    	}
    	Date modify = paPaperTree.getModifyDate();
    	paPaperTree.setModifyDate(modify != null ? modify : new Date());
		return paPaperTreeDao.update(paPaperTree);
	}
	
	@Override
	public void remove(PaPaperTree paPaperTree) {
		try {
			paPaperTreeDao.delete(paPaperTree);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paPaperTree.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Page page, Order order) {
		return paPaperTreeDao.findPaPaperTreeByCondition(paPaperTreeCondition, page, order);
	}
	
	@Override
	public List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition) {
		return paPaperTreeDao.findPaPaperTreeByCondition(paPaperTreeCondition, null, null);
	}
	
	@Override
	public List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Page page) {
		return paPaperTreeDao.findPaPaperTreeByCondition(paPaperTreeCondition, page, null);
	}
	
	@Override
	public List<PaPaperTree> findPaPaperTreeByCondition(PaPaperTreeCondition paPaperTreeCondition, Order order) {
		return paPaperTreeDao.findPaPaperTreeByCondition(paPaperTreeCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paPaperTreeDao.count(null);
	}

	@Override
	public Long count(PaPaperTreeCondition paPaperTreeCondition) {
		return this.paPaperTreeDao.count(paPaperTreeCondition);
	}

	@Override
	public void removeByPaperId(Integer paperId) {
		paPaperTreeDao.deleteByPaperId(paperId);
	}

	@Override
	public List<PaPaperTree> findPaPaperTreeByPaperIdAndType(Integer paperId, Integer nodeType) {
		PaPaperTreeCondition paPaperTreeCondition = new PaPaperTreeCondition();
		paPaperTreeCondition.setPaperId(paperId);
		paPaperTreeCondition.setNodeType(nodeType);
		return this.findPaPaperTreeByCondition(paPaperTreeCondition, Order.asc("pos"));
	}

	@Override
	public String findBasketJsonByPaperId(Integer paperId) {
		List<BasketGroupJson> basketGroupJsons=null;
		List<PaPaperTree> paperGroups=paPaperTreeDao.findGroupByPaperId(paperId);
		if(paperGroups!=null&&!paperGroups.isEmpty()) {
			basketGroupJsons=new ArrayList<BasketGroupJson>(paperGroups.size());
			PaPaperTreeCondition condition=new PaPaperTreeCondition();
			for(PaPaperTree group:paperGroups) {
					BasketGroupJson basketGroupJson=new BasketGroupJson();
					basketGroupJson.setGroupName(group.getTitle());
					basketGroupJson.setPos(group.getPos());
					
					List<Integer> questionIds=paPaperTreeDao.findQuestionByGroupId(group.getId());
					basketGroupJson.setQuestionId(questionIds);
					
					if(questionIds!=null&&!questionIds.isEmpty()) {
						List<Integer> ids=paPaperTreeDao.findIdByGroupId(group.getId());
							Long i=0L;
						for(Integer id: ids) {
							condition.setParentId(id);
							Long count=paPaperTreeDao.count(condition);
							if(count==0) {
								i++;
							}else {
								i+=count;
							}
						}
						basketGroupJson.setQuestionSize(i);
					}else {
						basketGroupJson.setQuestionSize(0L);
					}
					
					basketGroupJsons.add(basketGroupJson);
			}
		}
		
    	ObjectMapper mapper = new ObjectMapper();
    	String json=null;
    	try {
			json=mapper.writeValueAsString(basketGroupJsons);
			log.debug("***********************************888");
			log.debug(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return json;
	}
}
