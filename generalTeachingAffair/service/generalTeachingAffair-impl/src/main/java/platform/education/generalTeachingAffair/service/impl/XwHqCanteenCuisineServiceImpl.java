package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.XwHqCanteenCuisineDao;
import platform.education.generalTeachingAffair.model.XwHqCanteenCuisine;
import platform.education.generalTeachingAffair.service.XwHqCanteenCuisineService;
import platform.education.generalTeachingAffair.vo.CanteenCuisineCondition;

import java.util.Date;
import java.util.List;


public class XwHqCanteenCuisineServiceImpl implements XwHqCanteenCuisineService {

    private XwHqCanteenCuisineDao xwHqCanteenCuisineDao;

    public void setXwHqCanteenCuisineDao(XwHqCanteenCuisineDao xwHqCanteenCuisineDao) {
        this.xwHqCanteenCuisineDao = xwHqCanteenCuisineDao;
    }

    @Override
    public XwHqCanteenCuisine addCuisine(XwHqCanteenCuisine xwHqCanteenCuisine) {
        xwHqCanteenCuisine.setCreateDate(new Date());
        xwHqCanteenCuisine.setIsDelete(0);
        xwHqCanteenCuisine.setModifyDate(new Date());
        XwHqCanteenCuisine cuisine = xwHqCanteenCuisineDao.create(xwHqCanteenCuisine);
        return cuisine;
    }

    @Override
    public List<XwHqCanteenCuisine> findCanteenCuisineByCondition(CanteenCuisineCondition condition, Page page, Order order) {
        return xwHqCanteenCuisineDao.findxwHqCanteenCuisineByCondition(condition, page, order);

    }


    @Override
    public String abandonCuisine(String ids) {
        if (ids != null && !("").equals(ids)) {
            XwHqCanteenCuisine cuisine = new XwHqCanteenCuisine();
            cuisine.setIsDelete(1);
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                Date modify = cuisine.getModifyDate();
                cuisine.setModifyDate(modify != null ? modify : new Date());
                cuisine.setId(Integer.parseInt(split[i]));
                xwHqCanteenCuisineDao.update(cuisine);
            }
        }
        return XwHqCanteenCuisineService.OPERATE_SUCCESS;
    }

    @Override
    public List<XwHqCanteenCuisine> findByCuisineId(Integer id) {
        return xwHqCanteenCuisineDao.findByCuisineId(id);
    }

}
