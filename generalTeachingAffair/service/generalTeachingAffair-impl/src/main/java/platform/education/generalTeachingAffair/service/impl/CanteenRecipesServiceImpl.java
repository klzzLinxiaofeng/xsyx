package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.SupplierCanteenRecipesDao;
import platform.education.generalTeachingAffair.dao.CuisineToRecipesDao;
import platform.education.generalTeachingAffair.dao.XwHqCanteenRecipesDao;
import platform.education.generalTeachingAffair.model.PublicTeacherVo;
import platform.education.generalTeachingAffair.model.SupplierPojo;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipes;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipesToCuisin;
import platform.education.generalTeachingAffair.service.CanteenRecipesService;
import platform.education.generalTeachingAffair.vo.CanteenRecipesCondition;
import java.util.Date;
import java.util.List;

/**
 * @description: 食谱管理业务层接口
 * @author: cmb
 * @create: 2020-11-02 11:42
 **/
public class CanteenRecipesServiceImpl implements CanteenRecipesService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private XwHqCanteenRecipesDao xwHqCanteenRecipesDao;

    private SupplierCanteenRecipesDao supplierCanteenRecipesDao;

    public void setSupplierCanteenRecipesDao(SupplierCanteenRecipesDao supplierCanteenRecipesDao) {
        this.supplierCanteenRecipesDao = supplierCanteenRecipesDao;
    }



    private CuisineToRecipesDao cuisineToRecipesDao;

    public void setCuisineToRecipesDao(CuisineToRecipesDao cuisineToRecipesDao) {
        this.cuisineToRecipesDao = cuisineToRecipesDao;
    }

    @Override
    public String abandonGoods(XwHqCanteenRecipes xwHqCanteenRecipes) {
        if (xwHqCanteenRecipes != null) {
            xwHqCanteenRecipes.setIsDelete(1);

                xwHqCanteenRecipesDao.update(xwHqCanteenRecipes);

            return CanteenRecipesService.OPERATE_SUCCESS;
        }
        return CanteenRecipesService.OPERATE_FAIL;
    }

    public CanteenRecipesServiceImpl() {
    }

    public void setXwHqCanteenRecipesDao(XwHqCanteenRecipesDao xwHqCanteenRecipesDao) {
        this.xwHqCanteenRecipesDao = xwHqCanteenRecipesDao;
    }

    public XwHqCanteenRecipes findXwHqCanteenRecipesById(Integer id) {
            return this.xwHqCanteenRecipesDao.findById(id);

    }
    public XwHqCanteenRecipes create(XwHqCanteenRecipes xwHqCanteenRecipes) {
           return this.xwHqCanteenRecipesDao.create(xwHqCanteenRecipes);

    }
    @Override
    public List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition canteenRecipesCondition, Page page, Order order) {
        List<XwHqCanteenRecipes> xwHqCanteenRecipesByCondition=null;

           xwHqCanteenRecipesByCondition = this.xwHqCanteenRecipesDao.findXwHqCanteenRecipesByCondition(canteenRecipesCondition, page, order);

        return xwHqCanteenRecipesByCondition;

    }

    @Override
    public List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition canteenRecipesCondition) {
        return this.xwHqCanteenRecipesDao.findXwHqCanteenRecipesByCondition(canteenRecipesCondition, (Page)null, (Order)null);
    }

    @Override
    public List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition canteenRecipesCondition, Page page) {
        return this.xwHqCanteenRecipesDao.findXwHqCanteenRecipesByCondition(canteenRecipesCondition, page, (Order)null);
    }

    @Override
    public List<XwHqCanteenRecipes> findCanteenRecipesByCondition(CanteenRecipesCondition canteenRecipesCondition, Order order) {
        return this.xwHqCanteenRecipesDao.findXwHqCanteenRecipesByCondition(canteenRecipesCondition, (Page)null, order);
    }
/**
* @Description: 查询所有修改的时间
* @Param: * @param schooid
* @return: java.util.List<platform.education.generalTeachingAffair.model.XwHqCanteenRecipes>
* @Author: cmb
* @Date: 2020/11/2
*/

    @Override
    public List<XwHqCanteenRecipes> findAllBySchoolId(Integer schooid) {
            return this.xwHqCanteenRecipesDao.findAllBySchoolId(schooid);
    }
/**
* @Description: 通过日期查询数据
* @return: java.util.List<platform.education.generalTeachingAffair.model.XwHqCanteenRecipes>
* @Author: cmb
* @Date: 2020/11/2
*/

    @Override
    public List<XwHqCanteenRecipes> findAllBydate(CanteenRecipesCondition var1, Page var2, Order var3) {

            return this.xwHqCanteenRecipesDao.findAllBydate( var1,  var2,  var3);


    }
    @Override
    public String remove(Integer id) {
        try {
            return xwHqCanteenRecipesDao.dropItemById(id);
        } catch(Exception e) {
            if(log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", e);
            }
            return null;
        }
    }

    public Long count() {
        return this.xwHqCanteenRecipesDao.count((CanteenRecipesCondition)null);
    }

    public Long count(CanteenRecipesCondition canteenRecipesCondition) {
        return this.xwHqCanteenRecipesDao.count(canteenRecipesCondition);
    }
    @Override
    public XwHqCanteenRecipes findByGoodsId(Integer id) {
        try {
            return xwHqCanteenRecipesDao.findByGoodsId(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }
        /**
         * 添加
         *
         * @param xwHqCanteenRecipes
         * @return
         */
        @Override
        public void addGoods(XwHqCanteenRecipes xwHqCanteenRecipes) {
            if (xwHqCanteenRecipes == null) {
                return;
            }
            Date createDate = xwHqCanteenRecipes.getCreateDate();
            if (createDate == null) {
                createDate = new Date();
            }
            xwHqCanteenRecipes.setCreateDate(createDate);
            xwHqCanteenRecipes.setModifyDate(createDate);
            xwHqCanteenRecipes.setIsDelete(0);
            xwHqCanteenRecipesDao.create(xwHqCanteenRecipes);
        }

    @Override
    public void update(XwHqCanteenRecipes canteenRecipesPojo) {
        xwHqCanteenRecipesDao.update(canteenRecipesPojo);
    }

    @Override
    public List<XwHqCanteenRecipes> findCuisineAndRecipesById(Integer id) {
        return xwHqCanteenRecipesDao.findCxAndRecipesById(id);
    }


    @Override
    public XwHqCanteenRecipes modify(XwHqCanteenRecipes xwHqCanteenRecipes){
        if (xwHqCanteenRecipes == null) {
            return null;
        }



        xwHqCanteenRecipes.setModifyDate(new Date());
        xwHqCanteenRecipes.setCreateDate(xwHqCanteenRecipes.getCreateDate());
        xwHqCanteenRecipes.setIsDelete(0);
        xwHqCanteenRecipes.setCxList(xwHqCanteenRecipes.getCxList());
        xwHqCanteenRecipes.setDescription(xwHqCanteenRecipes.getCxList());
        xwHqCanteenRecipes.setIds(xwHqCanteenRecipes.getIds());
        Integer id = xwHqCanteenRecipes.getId();
        cuisineToRecipesDao.deleteByRecipesId(id);
        String ids = xwHqCanteenRecipes.getIds();
        XwHqCanteenRecipesToCuisin entity = new XwHqCanteenRecipesToCuisin();
        entity.setIds(ids);
        if (ids.length() > 0) {
            String[] split = ids.split(",");
            Integer[] intArr = new Integer[split.length];
            for (int a = 0; a < split.length; a++) {
                intArr[a] = Integer.valueOf(split[a]);
            }
            for (int b = 0; b < intArr.length; b++) {
                entity.setCuisinId(intArr[b]);
                entity.setRecipesId(xwHqCanteenRecipes.getId());
                cuisineToRecipesDao.create(entity);
            }
        }

        xwHqCanteenRecipesDao.update(xwHqCanteenRecipes);
        return xwHqCanteenRecipes;


    }


    @Override
    public SupplierPojo create(Integer type, String uuid, String name) {
        if (type != null && uuid != null){
            SupplierPojo supplierPojo = new SupplierPojo();
            supplierPojo.setCreateDate(new Date());
            supplierPojo.setType(type);
            supplierPojo.setUuid(uuid);
            supplierPojo.setName(name);
            supplierCanteenRecipesDao.create(supplierPojo);
            return supplierPojo;
        }
        return null;
    }

    @Override
    public SupplierPojo modifySupplier(SupplierPojo supplierPojo) {
        return supplierCanteenRecipesDao.update(supplierPojo);
    }

    @Override
    public List<SupplierPojo> find(SupplierPojo supplierPojo) {
        return supplierCanteenRecipesDao.findSupplier(supplierPojo);
    }

}
