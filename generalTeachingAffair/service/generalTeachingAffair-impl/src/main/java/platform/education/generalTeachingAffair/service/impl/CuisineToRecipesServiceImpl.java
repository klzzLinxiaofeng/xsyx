package platform.education.generalTeachingAffair.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.CuisineToRecipesDao;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipesToCuisin;
import platform.education.generalTeachingAffair.service.CuisineToRecipesService;

public class CuisineToRecipesServiceImpl implements CuisineToRecipesService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private CuisineToRecipesDao cuisineToRecipesDao;

    public void setCuisineToRecipesDao(CuisineToRecipesDao cuisineToRecipesDao) {
        this.cuisineToRecipesDao = cuisineToRecipesDao;
    }


    @Override
    public XwHqCanteenRecipesToCuisin create(XwHqCanteenRecipesToCuisin var1) {
        return this.cuisineToRecipesDao.create(var1);
    }

    @Override
    public void deleteByRecipesId(Integer recipesId) {
        this.cuisineToRecipesDao.deleteByRecipesId(recipesId);
    }
}
