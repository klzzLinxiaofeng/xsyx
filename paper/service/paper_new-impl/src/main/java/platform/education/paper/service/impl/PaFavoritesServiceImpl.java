package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.paper.model.PaFavorites;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.vo.PaFavoritesCondition;
import platform.education.paper.service.PaFavoritesService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.dao.PaFavoritesDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaFavoritesServiceImpl implements PaFavoritesService{

	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaFavoritesDao paFavoritesDao;

	public void setPaFavoritesDao(PaFavoritesDao paFavoritesDao) {
		this.paFavoritesDao = paFavoritesDao;
	}
	
	@Override
	public PaFavorites findPaFavoritesById(Integer id) {
		try {
			return paFavoritesDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaFavorites add(PaFavorites paFavorites) {
		if(paFavorites == null) {
    		return null;
    	}
    	Date createDate = paFavorites.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paFavorites.setCreateDate(createDate);
    	paFavorites.setModifyDate(createDate);
		return paFavoritesDao.create(paFavorites);
	}

	@Override
	public PaFavorites modify(PaFavorites paFavorites) {
		if(paFavorites == null) {
    		return null;
    	}
    	Date modify = paFavorites.getModifyDate();
    	paFavorites.setModifyDate(modify != null ? modify : new Date());
		return paFavoritesDao.update(paFavorites);
	}
	
	@Override
	public void remove(PaFavorites paFavorites) {
		try {
			paFavoritesDao.delete(paFavorites);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paFavorites.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Page page, Order order) {
		return paFavoritesDao.findPaFavoritesByCondition(paFavoritesCondition, page, order);
	}
	
	@Override
	public List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition) {
		return paFavoritesDao.findPaFavoritesByCondition(paFavoritesCondition, null, null);
	}
	
	@Override
	public List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Page page) {
		return paFavoritesDao.findPaFavoritesByCondition(paFavoritesCondition, page, null);
	}
	
	@Override
	public List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Order order) {
		return paFavoritesDao.findPaFavoritesByCondition(paFavoritesCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paFavoritesDao.count(null);
	}

	@Override
	public Long count(PaFavoritesCondition paFavoritesCondition) {
		return this.paFavoritesDao.count(paFavoritesCondition);
	}

	//--------------业务方法----------
	
	//是否已收藏
	@Override
	public Boolean isFav(Integer objectId, Integer posterId,Integer objectType) {
		PaFavoritesCondition paFavoritesCondition=new PaFavoritesCondition();
		paFavoritesCondition.setObjectId(objectId);
		paFavoritesCondition.setPosterId(posterId);
		paFavoritesCondition.setObjectType(objectType);
		return paFavoritesDao.count(paFavoritesCondition)!=0?true:false;
	}


	// 收藏和取消收藏操作
	@Override
	public boolean doFavorite(Integer id, Integer type, String addFav, Integer posterId) {

		PaFavorites paFavorites = new PaFavorites();
		paFavorites.setObjectId(id);
		paFavorites.setObjectType(type);
		paFavorites.setPosterId(posterId);

		if ("true".equals(addFav)) {// 收藏
			if (this.add(paFavorites) == null) {
				return false;
			} else {
				if (type == PaperContans.PAPER) {// 试卷还是试题
					PaPaper paPaper=paPaperService.findPaPaperById(id);
					paPaper.setFavCount(paPaper.getFavCount()+1);
					paPaper.setModifyDate(paPaper.getModifyDate());
					paPaperService.modify(paPaper);
				} else {
					PaQuestion paQuestion=paQuestionService.findPaQuestionById(id);
					paQuestion.setFavCount(paQuestion.getFavCount()+1);
					paQuestion.setModifyDate(paQuestion.getModifyDate());
					paQuestionService.modifyNotWithModifyDate(paQuestion);
				}
			}
		} else {// 取消收藏
			PaFavoritesCondition paFavoritesCondition = new PaFavoritesCondition();
			paFavoritesCondition.setObjectId(id);
			paFavoritesCondition.setObjectType(type);
			paFavoritesCondition.setPosterId(posterId);
			List<PaFavorites> pl = this.findPaFavoritesByCondition(paFavoritesCondition);
			if (pl.isEmpty()) {
				return false;
			}
			this.remove(paFavorites = pl.get(0));
			if (type == PaperContans.PAPER) {// 试卷还是试题
				PaPaper paPaper=paPaperService.findPaPaperById(id);
				paPaper.setFavCount(paPaper.getFavCount()-1);
				paPaper.setModifyDate(paPaper.getModifyDate());
				paPaperService.modify(paPaper);
			} else {
				PaQuestion paQuestion=paQuestionService.findPaQuestionById(id);
				paQuestion.setFavCount(paQuestion.getFavCount()-1);
				paQuestion.setModifyDate(paQuestion.getModifyDate());
				paQuestionService.modifyNotWithModifyDate(paQuestion);
			}
		}
		return true;
	}

}
