package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.SupplierPojo;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipes;
import platform.education.generalTeachingAffair.vo.CanteenRecipesCondition;

import java.util.List;

/**
 * @description: 食谱管理业务层
 * @author: cmb
 * @create: 2020-11-02 11:34
 **/
public interface CanteenRecipesService {
    String OPERATE_SUCCESS = "success";
    String OPERATE_FAIL = "fail";
    String OPERATE_ERROR = "error";

    XwHqCanteenRecipes findXwHqCanteenRecipesById(Integer var1);

    XwHqCanteenRecipes create(XwHqCanteenRecipes var1);

    XwHqCanteenRecipes modify(XwHqCanteenRecipes var1);

    String abandonGoods(XwHqCanteenRecipes xwHqCanteenRecipes);

    String remove(Integer id);

    List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition var1, Page var2, Order var3);

    List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition var1);

    List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition var1, Page var2);

    List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition var1, Order var2);
    List<XwHqCanteenRecipes> findAllBySchoolId(Integer schooid);
    List<XwHqCanteenRecipes> findAllBydate(CanteenRecipesCondition var1, Page var2, Order var3);
    Long count();

    Long count(CanteenRecipesCondition var1);

    XwHqCanteenRecipes findByGoodsId(Integer id);

    void addGoods(XwHqCanteenRecipes xwHqCanteenRecipes);

    void update(XwHqCanteenRecipes canteenRecipesPojo);

    List<XwHqCanteenRecipes> findCuisineAndRecipesById(Integer id);

    //String abandonRecipes(Integer id);

    SupplierPojo create(Integer type, String uuid, String name);

    SupplierPojo modifySupplier(SupplierPojo supplierPojo);

    List<SupplierPojo> find(SupplierPojo supplierPojo);
}
