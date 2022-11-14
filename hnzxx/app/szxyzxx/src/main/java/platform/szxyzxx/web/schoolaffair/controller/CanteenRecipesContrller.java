package platform.szxyzxx.web.schoolaffair.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.SupplierPojo;
import platform.education.generalTeachingAffair.model.PublicTeacherVo;
import platform.education.generalTeachingAffair.model.XwHqCanteenCuisine;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipes;
import platform.education.generalTeachingAffair.model.XwHqCanteenRecipesToCuisin;
import platform.education.generalTeachingAffair.service.CanteenRecipesService;
import platform.education.generalTeachingAffair.service.CuisineToRecipesService;
import platform.education.generalTeachingAffair.service.XwHqCanteenCuisineService;
import platform.education.generalTeachingAffair.vo.CanteenCuisineCondition;
import platform.education.generalTeachingAffair.vo.CanteenRecipesCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 食谱管理
 * @author: cmb
 * @create: 2020-11-02 09:12
 **/
@RestController
@RequestMapping("/schoolaffair/recipes")
public class CanteenRecipesContrller extends BaseController {
    Logger log = LoggerFactory.getLogger(CanteenRecipesContrller.class);
    private final static String viewBasePath = "/schoolaffair/canteenManager/canteenRecipes";
    @Autowired
    @Qualifier("canteenRecipesService")
    private CanteenRecipesService canteenRecipesService;

    @Autowired
    @Qualifier("xwHqCanteenCuisineService")
    private XwHqCanteenCuisineService xwHqCanteenCuisineService;

    @Autowired
    @Qualifier("cuisineToRecipesService")
    private CuisineToRecipesService cuisineToRecipesService;


    /**
     * @param dm
     * @param sub
     * @param condition
     * @param page
     * @param order
     * @param model
     * @Description: 食谱首页
     * @Param: * @param user
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/2
     */
    @RequestMapping("/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") CanteenRecipesCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        //order.setProperty(order.getProperty() != null ? order.getProperty() : "date");
        //conditionFilter(user, condition);
        List<XwHqCanteenRecipes> canteenRecipes = this.canteenRecipesService.findCanteenRecipesByCondition(condition, page, order);

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("canteenRecipes", canteenRecipes);
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * @param id
     * @param isCK
     * @param model
     * @Description: 详情 编辑
     * @Param: * @param user
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/2
     */

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView modifyRecipes(@RequestParam(value = "id", required = true) Integer id,
                                      @RequestParam(value = "isCK", required = false) String isCK,
                                      Model model) {
//        XwHqCanteenRecipes canteenRecipes = this.canteenRecipesService.findXwHqCanteenRecipesById(id);
//        String cxList = canteenRecipes.getCxList();
//        String ids = canteenRecipes.getIds();
        List<XwHqCanteenRecipes> recipes = this.canteenRecipesService.findCuisineAndRecipesById(id);

        System.out.println(recipes);

//        cuisineToRecipesService.deleteByRecipesId(canteenRecipes.getId());
//        if (recipes!=null&&recipes.size()>0) {
//            for (XwHqCanteenRecipes pojo : recipes) {
//                XwHqCanteenRecipesToCuisin recipesToCuisin = new XwHqCanteenRecipesToCuisin();
//                recipesToCuisin.setRecipesId(canteenRecipes.getId());
//                //recipesToCuisin.setCuisinId(pojo.getcuisinId);
//                cuisineToRecipesService.create(recipesToCuisin);
//            }
//        }


        if (isCK.equals("disable")) {
            model.addAttribute("isCK", isCK);
        }
        if (recipes != null && recipes.size() > 0) {
            model.addAttribute("canteenRecipes", recipes.get(0));
        }


        return new ModelAndView(structurePath("/input"), model.asMap());


    }

    /**
     * 编辑
     * @param id
     * @param canteenRecipes
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, XwHqCanteenRecipes canteenRecipes) {
        canteenRecipes.setId(id);
        //XwHqCanteenRecipes recipes = this.canteenRecipesService.findByGoodsId(id);


        canteenRecipes = this.canteenRecipesService.modify(canteenRecipes);

        return canteenRecipes != null ? new ResponseInfomation(canteenRecipes.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    /**
     * 点击创建时，跳转到input界面
     */
    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/input"));
    }

    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    public ResponseInfomation addcanteenRecipes(@CurrentUser UserInfo user, XwHqCanteenRecipes canteenRecipes) {
        canteenRecipes.setSchoolId(user.getSchoolId());
        String dateStr = canteenRecipes.getDate();
        String cxList = canteenRecipes.getCxList();
        String ids = canteenRecipes.getIds();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.info("时间数据解析异常{}", e.getMessage());
        }
        canteenRecipes.setCreateDate(date);
        canteenRecipes.setModifyDate(date);
        canteenRecipes.setDescription(cxList);
        canteenRecipes.setIds(ids);

        XwHqCanteenRecipesToCuisin entity = new XwHqCanteenRecipesToCuisin();
        XwHqCanteenRecipes canteenRecipes1 = canteenRecipesService.create(canteenRecipes);
        entity.setIds(ids);
        if (ids.length() > 0) {
            String[] split = ids.split(",");
            Integer[] intArr = new Integer[split.length];
            for (int a = 0; a < split.length; a++) {
                intArr[a] = Integer.valueOf(split[a]);
            }
            for (int b = 0; b < intArr.length; b++) {
                entity.setCuisinId(intArr[b]);
                entity.setRecipesId(canteenRecipes1.getId());
                cuisineToRecipesService.create(entity);
            }
        }


        return canteenRecipes1 != null ? new ResponseInfomation(canteenRecipes1.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    /**
     * @Description: 设置返回路径
     * @Param: * @param subPath
     * @return: java.lang.String
     * @Author: cmb
     * @Date: 2020/11/2
     */

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    /**
     * @param canteenRecipesCondition
     * @Description: 判断学校id与是否删除
     * @Param: * @param userInfo
     * @return: void
     * @Author: cmb
     * @Date: 2020/11/2
     */
//    private void conditionFilter(UserInfo userInfo, CanteenRecipesCondition canteenRecipesCondition) {
//        Integer schoolId = canteenRecipesCondition.getSchoolId();
//        Integer isDeleted = canteenRecipesCondition.getIsDelete();
//        canteenRecipesCondition.setIsDelete(isDeleted != null ? isDeleted : 0);
//        canteenRecipesCondition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
//    }


    /**
     * @Description: 异步查询所有修改的时间
     * @Param: * @param schooid
     * @return: java.util.List<platform.education.generalTeachingAffair.model.XwHqCanteenRecipes>
     * @Author: cmb
     * @Date: 2020/11/2
     */

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private Map searchDate(@CurrentUser UserInfo user) {
        Map map = new HashMap<>();
        List<XwHqCanteenRecipes> canteenRecipes = canteenRecipesService.findAllBySchoolId(user.getSchoolId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashSet set = new HashSet();
        for (XwHqCanteenRecipes canteenRecipe : canteenRecipes) {

            Date modifyDate = canteenRecipe.getModifyDate();
            String format = simpleDateFormat.format(modifyDate);
            set.add(format);
        }
        map.put("canteenRecipes", set);
        return map;
    }


    /**
     * 菜谱删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, XwHqCanteenRecipes xwHqCanteenRecipes) {
        if (xwHqCanteenRecipes != null) {
            xwHqCanteenRecipes.setId(id);
        }
        return this.canteenRecipesService.abandonGoods(xwHqCanteenRecipes);

    }

    /**
     * 菜系预设首页
     *
     * @param dm
     * @param type
     * @param sub
     * @param condition
     * @param page
     * @param order
     * @param model
     * @return
     */
    @RequestMapping(value = "/goods/index")
    public ModelAndView goodsIndex(
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") CanteenCuisineCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        List<XwHqCanteenCuisine> items = this.xwHqCanteenCuisineService.findCanteenCuisineByCondition(condition, page, order);

        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                // 根据uuid查询封面的url
                FileResult file = fileService.findFileByUUID(items.get(i).getUuid());
                if (file != null) {
                    items.get(i).setPicUrl(file.getHttpUrl());
                }
            }
        }

        if ("list".equals(sub)) {
            viewPath = structurePath("/module/goodsList");
        } else {
            viewPath = structurePath("/module/index");
        }
        if ("all".equals(type)) {
            viewPath = structurePath("/module/goodsIndex");
        }
        model.addAttribute("items", items);
        model.addAttribute("jump_id", id);
        return new ModelAndView(viewPath, model.asMap());
    }


    /**
     * 菜系删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("ids") String ids) {
        return this.xwHqCanteenCuisineService.abandonCuisine(ids);
    }


    /**
     * 菜系编辑
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/goods/editor", method = RequestMethod.GET)
    public ModelAndView editorGoods(
            @RequestParam(value = "id") Integer id, Model model) {
        XwHqCanteenRecipes xwHqCanteenRecipes = this.canteenRecipesService.findByGoodsId(id);
        if (xwHqCanteenRecipes != null) {
//            // 根据uuid查询封面的url
//            FileResult file = fileService.findFileByUUID(xwHqCanteenRecipes.getCoverUuid());
//            if (file != null) {
//                xwHqCanteenRecipes.setCoverUrl(file.getHttpUrl());
//            }
            model.addAttribute("xwHqCanteenRecipes", xwHqCanteenRecipes);
        }
        return new ModelAndView(structurePath("/module/input"), model.asMap());
    }


    /**
     * 菜系预设添加
     *
     * @param xwHqCanteenCuisine
     * @return
     */
    @RequestMapping(value = "/goods/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creatorGoods(XwHqCanteenCuisine xwHqCanteenCuisine) {

        xwHqCanteenCuisineService.addCuisine(xwHqCanteenCuisine);

        //return xwHqCanteenCuisine != null ? new ResponseInfomation(xwHqCanteenCuisine.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();

        ResponseInfomation ss = xwHqCanteenCuisine != null ? new ResponseInfomation(xwHqCanteenCuisine.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
        return ss;
    }

    @RequestMapping(value = "/goods/creator", method = RequestMethod.GET)
    public ModelAndView creatorGoods(Model model) {
        return new ModelAndView(structurePath("/module/input"));
    }

    /**
     * 菜系预设编辑
     *
     * @param xwHqCanteenRecipes
     * @return
     */
    @RequestMapping(value = "/goods/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation update(XwHqCanteenRecipes xwHqCanteenRecipes) {
        this.canteenRecipesService.modify(xwHqCanteenRecipes);
        return xwHqCanteenRecipes != null ? new ResponseInfomation(xwHqCanteenRecipes.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    @RequestMapping(value = "recipes/creator", method = RequestMethod.GET)
    public ModelAndView creatorRecipes(Model model) {

        return new ModelAndView(structurePath("/module/input"));
    }

    @RequestMapping(value = "recipes/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creatorRecipes(PublicTeacherVo publicTeacher, @CurrentUser UserInfo user) {
        if (user != null && publicTeacher != null) {
            publicTeacher.setSchoolId(user.getSchoolId());
        }
        publicClassService.addTeacher(publicTeacher);
        ResponseInfomation ss = publicTeacher != null ? new ResponseInfomation(publicTeacher.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
        return ss;
    }


    /**
     * 供货商管理
     *
     * @return
     * @auth yhc
     */
    @RequestMapping(value = "/supplier/Management", method = RequestMethod.GET)
    public ModelAndView supplier(Model model) {

        SupplierPojo pojo1 = new SupplierPojo();

        List<SupplierPojo> supplier = canteenRecipesService.find(pojo1);
        // 0：视频1: 供应商资质2:宣传图片
        List<SupplierPojo> list = new ArrayList<>();
        List<SupplierPojo> list1 = new ArrayList<>();
        List<SupplierPojo> list2 = new ArrayList<>();

        for (SupplierPojo supplierPojo : supplier) {
            // 根据uuid查询封面的url
            FileResult file = fileService.findFileByUUID(supplierPojo.getUuid());
            if (file != null) {
                supplierPojo.setUrl(file.getHttpUrl());
                supplierPojo.setFileName(file.getEntityFile().getFileName());
            }
            if (0 == supplierPojo.getType()) {
                list.add(supplierPojo);
            } else if (1 == supplierPojo.getType()) {
                list1.add(supplierPojo);
            } else if (2 == supplierPojo.getType()) {
                list2.add(supplierPojo);
            }
        }
        SupplierPojo pojo = list != null && list.size() > 0 ? list.get(0) : null;
        model.addAttribute("video", pojo);
        model.addAttribute("supplier", list1);
        model.addAttribute("images", list2);
        return new ModelAndView("/schoolaffair/supplierManagement/index");
    }

    @RequestMapping(value = "/supplier/createSupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation createSupplier(@RequestParam("type") Integer type, @RequestParam("uuid") String uuid, @RequestParam(value = "name", required = false) String name) {
        SupplierPojo value = canteenRecipesService.create(type, uuid, name);
        return value != null ? new ResponseInfomation(value, ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    @RequestMapping(value = "/supplier/findCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer findCount(@ModelAttribute("pojo") SupplierPojo pojo) {
        List<SupplierPojo> list = canteenRecipesService.find(pojo);
        return list != null ? list.size() : 0;
    }

    /**
     * 删除图片
     * type 0：视频1: 供应商资质2:宣传图片
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/supplier/deleteSupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation deleteSupplier(@ModelAttribute("condition") SupplierPojo condition) {

        condition.setIsDelete(1);
        condition.setModifyDate(new Date());
        SupplierPojo value = canteenRecipesService.modifySupplier(condition);
        return value != null ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    /**
     * 跳转供应商修改
     *
     * @param condition.id
     * @return
     */
    @RequestMapping(value = "/supplier/readModifySupplier", method = RequestMethod.GET)
    public ModelAndView readModifySupplier(@ModelAttribute("condition") SupplierPojo condition, Model model) {
        if (condition != null && condition.getId() != null) {
            List<SupplierPojo> list = canteenRecipesService.find(condition);
            if (list != null && list.size() > 0) {
                SupplierPojo supplierPojo = list.get(0);
                FileResult file = fileService.findFileByUUID(supplierPojo.getUuid());
                if (file != null) {
                    supplierPojo.setUrl(file.getHttpUrl());
                    supplierPojo.setFileName(file.getEntityFile().getFileName());
                }
                model.addAttribute("supplier", supplierPojo);
            }
        }
        return new ModelAndView("/schoolaffair/supplierManagement/input");
    }

    /**
     * 修改/新增 供应商
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/supplier/modifySupplier", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation modifySupplier(@ModelAttribute("condition") SupplierPojo condition) {
        SupplierPojo value = null;
        if (condition != null && condition.getId() != null) {
            value = canteenRecipesService.modifySupplier(condition);
        } else if (condition != null) {
            value = canteenRecipesService.create(condition.getType(), condition.getUuid(), condition.getName());
        }
        return value != null ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }
}
