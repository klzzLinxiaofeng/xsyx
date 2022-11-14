package platform.education.paper.dao;

import platform.education.paper.model.PaTaskFavorites;
import platform.education.paper.vo.PaTaskFavoritesCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaTaskFavoritesDao extends GenericDao<PaTaskFavorites, java.lang.Integer> {

	List<PaTaskFavorites> findPaTaskFavoritesByCondition(PaTaskFavoritesCondition paTaskFavoritesCondition, Page page, Order order);
	
	PaTaskFavorites findById(Integer id);
	
	Long count(PaTaskFavoritesCondition paTaskFavoritesCondition);
	
}
