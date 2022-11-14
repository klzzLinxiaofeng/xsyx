package platform.education.generalTeachingAffair.dao;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipes;
import platform.education.generalTeachingAffair.vo.CanteenRecipesCondition;

import java.util.List;

public interface XwHqCanteenRecipesDao extends GenericDao<XwHqCanteenRecipes, java.lang.Integer> {
    List<XwHqCanteenRecipes> findXwHqCanteenRecipesByCondition(CanteenRecipesCondition var1, Page var2, Order var3);
    List<XwHqCanteenRecipes> findXwHqCanteenRecipesNameByCondition(CanteenRecipesCondition var1);

    XwHqCanteenRecipes findById(Integer var1);
    Long count(CanteenRecipesCondition var1);
    List<XwHqCanteenRecipes> findAllBySchoolId(Integer schooid);
    List<XwHqCanteenRecipes> findAllBydate(CanteenRecipesCondition var1, Page var2, Order var3);

    XwHqCanteenRecipes findByGoodsId(Integer id);

    String dropItemById(Integer id);

    XwHqCanteenRecipes create(XwHqCanteenRecipes entity);

    List<XwHqCanteenRecipes> findCxAndRecipesById(Integer id);
}