package platform.szxyzxx.web.schoolaffair.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedback;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedbackContent;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.HomeToSchoolFeedbackContentService;
import platform.education.generalTeachingAffair.service.HomeToSchoolFeedbackService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 家校反馈后台
 * @author Eternityhua
 * @create 2020-12-03 18:42
 */
@Controller
@RequestMapping("/schoolaffair/htsFeedback")
public class HomeToSchoolFeedbackController extends BaseController {

    /**
     * 家校反馈
     */

    private Logger log = LoggerFactory.getLogger(getClass());
    /*定义返回的路径*/
    private final static String viewBasePath = "/schoolaffair/HomeToSchoolFeedback";



    @Autowired
    @Qualifier("homeToSchoolFeedbackService")
    protected HomeToSchoolFeedbackService homeToSchoolFeedbackService;

    @Autowired
    @Qualifier("homeToSchoolFeedbackContentService")
    protected HomeToSchoolFeedbackContentService homeToSchoolFeedbackContentService;


    @Autowired
    private BasicSQLService basicSQLService;


    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") HomeToSchoolFeedback condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String sql="select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+user.getId();
        List<Map<String,Object>> list=basicSQLService.find(sql);
        int a=0;
        for(Map<String,Object> bb:list){
            if(bb.get("code").toString().equals("JIAXIAO_FANKUI")){
                model.addAttribute("quanxian", true);
                a=1;
            }
        }
        if(a==0){
            model.addAttribute("quanxian", false);
        }
        model.addAttribute("teacherId",user.getTeacherId());
        String viewPath = null;
        conditionFilter(user, condition);
        order.setProperty(order.getProperty() != null ? order.getProperty() : "id");
        condition.setSchoolId(user.getSchoolId());
        List<HomeToSchoolFeedback> items = this.homeToSchoolFeedbackService.findHomeToSchoolFeedbackByCondition(condition, page, order);

        if (items != null && items.size() > 0) {
            for (HomeToSchoolFeedback item : items) {
                // 根据uuid查询图片的url

                String uuid = item.getUuid();
                if (uuid != null && !("").equals(uuid)) {
                    String[] split = uuid.split(",");
                    for (int i = 0; i < split.length; i++) {
                        FileResult file = fileService.findFileByUUID(split[i]);
                        if(file != null){
                            if (i == 0){
                                item.setPicUrl(file.getHttpUrl());
                            } else if (i == 1){
                                item.setPicUrl2(file.getHttpUrl());
                            } else {
                                item.setPicUrl3(file.getHttpUrl());
                            }

                        }

                    }
                }



            }
        }
        /*
        * 路径拼接
        * */
        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("items", items);

        return new ModelAndView(viewPath, model.asMap());
    }




    /*
    * 逻辑删除
    * */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void delete(@PathVariable(value = "id") Integer id) {
//        if (entity != null) {
//            entity.setId(id);
//        }
        this.homeToSchoolFeedbackService.abandon(id);
    }





    /*
    详情
    * */
    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView editor(
            @ModelAttribute("condition") HomeToSchoolFeedback condition,
            @RequestParam(value = "id")
                    Integer id,
            @RequestParam(value = "type") Integer type,
            @RequestParam(value = "edit", required = false) Integer edit,
            @RequestParam(value = "isCK", required = false) String isCK,
                    Model model) {
        HomeToSchoolFeedback entity = homeToSchoolFeedbackService.findContentById(id);

        String coverUuid = entity.getUuid();
        if (coverUuid != null && !("").equals(coverUuid)) {
            // 根据uuid查询封面的url
            String uuid = entity.getUuid();
            if (uuid != null && !("").equals(uuid)) {
                String[] split = uuid.split(",");
                for (int i = 0; i < split.length; i++) {
                    FileResult file = fileService.findFileByUUID(split[i]);
                    if(file != null){
                        if (i == 0){
                            entity.setPicUrl(file.getHttpUrl());
                        } else if (i == 1){
                            entity.setPicUrl2(file.getHttpUrl());
                        } else {
                            entity.setPicUrl3(file.getHttpUrl());
                        }

                    }
                }
            }
        }

        String viewPath = null;

        model.addAttribute("entity", entity);
        model.addAttribute("type", type);
        model.addAttribute("edit", edit);
        model.addAttribute("isCK", isCK);

        viewPath = structurePath("/viewer");
        return new ModelAndView(viewPath, model.asMap());
    }


    /*
    * 回复
    * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation saveFeedback(@CurrentUser UserInfo user,
                                     @RequestParam(value = "id") Integer id,
                                     @ModelAttribute(value = "content") HomeToSchoolFeedbackContent content) {


        HomeToSchoolFeedback entity = this.homeToSchoolFeedbackService.findContentById(id);


        if (entity != null){
            content.setFeedbackId(entity.getId());
            content.setSchoolId(entity.getSchoolId());
            homeToSchoolFeedbackService.updateCondition(entity.getId());
            homeToSchoolFeedbackContentService.createBatch(content);
        }
        return entity != null ? new ResponseInfomation(entity.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }




    /*
     * 编辑
     * */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation editFeedback(@CurrentUser UserInfo user,
                                           @RequestParam(value = "id") Integer id,
                                           @ModelAttribute(value = "content") HomeToSchoolFeedbackContent content) {
        HomeToSchoolFeedback entity = this.homeToSchoolFeedbackService.findContentById(id);
        homeToSchoolFeedbackContentService.deleteById(entity.getId());




        if (entity != null){
            content.setFeedbackId(entity.getId());
            content.setSchoolId(entity.getSchoolId());
            homeToSchoolFeedbackService.updateCondition(entity.getId());

            homeToSchoolFeedbackContentService.createBatch(content);
        }
        return entity != null ? new ResponseInfomation(entity.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }



    @RequestMapping(value = "/updateTeacher")
    @ResponseBody
    public String updateTeacher(@RequestParam("id") Integer id,
                                @RequestParam("teacher") Integer teacher){
      String falh=  homeToSchoolFeedbackService.updateTeacher(id,teacher);
      return falh;
    }


    private void conditionFilter(UserInfo user, HomeToSchoolFeedback condition) {
        Integer schoolId = condition.getSchoolId();
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
    }


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }




}
