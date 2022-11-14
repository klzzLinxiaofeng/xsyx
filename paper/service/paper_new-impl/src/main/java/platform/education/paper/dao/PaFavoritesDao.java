package platform.education.paper.dao;

import platform.education.paper.model.PaFavorites;
import platform.education.paper.vo.PaFavoritesCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaFavoritesDao extends GenericDao<PaFavorites, java.lang.Integer> {

	List<PaFavorites> findPaFavoritesByCondition(PaFavoritesCondition paFavoritesCondition, Page page, Order order);
	
	PaFavorites findById(Integer id);
	
	Long count(PaFavoritesCondition paFavoritesCondition);
	
}
