package platform.education.paper.service;
import platform.education.paper.model.PaFavorites;
import platform.education.paper.vo.PaFavoritesCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaFavoritesService {
    PaFavorites findPaFavoritesById(Integer id);
	   
	PaFavorites add(PaFavorites paFavorites);
	   
	PaFavorites modify(PaFavorites paFavorites);
	   
	void remove(PaFavorites paFavorites);
	   
	List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Page page, Order order);
	
	List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition);
	
	List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Page page);
	
	List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Order order);
	
	Long count();
	
	Long count(PaFavoritesCondition paFavoritesCondition);

	Boolean isFav(Integer objectId, Integer posterId,Integer objectType);

	boolean doFavorite(Integer id, Integer type, String addFav, Integer posterId);
	
}
