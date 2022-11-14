package platform.education.generalTeachingAffair.dao;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.SupplierPojo;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipes;
import platform.education.generalTeachingAffair.vo.CanteenRecipesCondition;

import java.util.List;

public interface SupplierCanteenRecipesDao extends GenericDao<SupplierPojo, Integer> {

    List<SupplierPojo> findSupplier(SupplierPojo supplierPojo);
}