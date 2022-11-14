package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.XwHqCanteenRecipesToCuisin;

public interface CuisineToRecipesService {

    XwHqCanteenRecipesToCuisin create(XwHqCanteenRecipesToCuisin var1);

    void deleteByRecipesId(Integer recipesId);
}
