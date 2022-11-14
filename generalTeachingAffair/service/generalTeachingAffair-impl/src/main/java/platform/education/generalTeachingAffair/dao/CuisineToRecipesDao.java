package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import org.apache.ibatis.annotations.Param;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipesToCuisin;

public interface CuisineToRecipesDao extends GenericDao<XwHqCanteenRecipesToCuisin, Integer> {

    XwHqCanteenRecipesToCuisin create(XwHqCanteenRecipesToCuisin entity);

    void deleteByRecipesId(Integer recipesId);
}
