package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.XwHqCanteenCuisine;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipes;
import platform.education.generalTeachingAffair.vo.CanteenCuisineCondition;

import java.util.List;

public interface XwHqCanteenCuisineService {

    /**
     * 当前接口crud操作 成功时返回的状态值
     */
    public final static String OPERATE_SUCCESS = "success";

    /**
     * 当前接口crud操作 失败时返回的状态值
     */
    public final static String OPERATE_FAIL = "fail";

    /**
     * 系统异常造成的操作失败 系统返回的状态值
     */
    public final static String OPERATE_ERROR = "error";

    XwHqCanteenCuisine addCuisine(XwHqCanteenCuisine xwHqCanteenCuisine);

    List<XwHqCanteenCuisine> findCanteenCuisineByCondition(CanteenCuisineCondition condition, Page page, Order order);

    String abandonCuisine(String ids);

    List<XwHqCanteenCuisine> findByCuisineId(Integer id);
}
