package platform.education.paper.service;
import platform.education.paper.model.PaTaskFavorites;
import platform.education.paper.vo.PaTaskFavoritesCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaTaskFavoritesService {
    PaTaskFavorites findPaTaskFavoritesById(Integer id);
	   
	PaTaskFavorites add(PaTaskFavorites paTaskFavorites);
	   
	PaTaskFavorites modify(PaTaskFavorites paTaskFavorites);
	   
	void remove(PaTaskFavorites paTaskFavorites);
	   
	List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Page page, Order order);
	
	List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition);
	
	List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Page page);
	
	List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Order order);
	
	Long count();
	
	Long count(PaTaskFavoritesCondition paTaskFavoritesCondition);
	/**
	 * 
	 * @param userQuestionId
	 * @param isFavorites  0 取消收藏  1收藏
	 */
	void modifyPaTaskFavoritesByUserQuestionId(Integer userQuestionId,Integer isFavorites,Integer userId);
	
}
