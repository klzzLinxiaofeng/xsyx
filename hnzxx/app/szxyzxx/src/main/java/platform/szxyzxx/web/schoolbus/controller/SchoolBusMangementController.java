package platform.szxyzxx.web.schoolbus.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.model.SchoolBusMangerVo;
import platform.education.generalTeachingAffair.service.ParentProxyService;
import platform.education.generalTeachingAffair.service.SchoolBusService;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.user.vo.UserCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolbus.vo.Pages;
import platform.szxyzxx.web.schoolbus.vo.StuVo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.util.Date;
import java.util.List;


/**
 * @author: yhc
 * @Date: 2021/2/25 16:04
 * @Description: 校车管理
 */
@Controller
@RequestMapping("/schoolBus/management")
public class SchoolBusMangementController extends BaseController {

    private final static String viewBasePath = "/schoolBusSystems/schoolBusMangement";


    private static Integer isSend;
    private static String url;
    private static String url2;

    static {
        String fileName = "System.properties";
        // 是否发送到校车车队
        isSend = Integer.valueOf(PropertiesUtil.getProperty(fileName, "schoolBus.send"));
        // 昊吉顺车队
        url = PropertiesUtil.getProperty(fileName, "schoolBus.send.url");
        // 国盛车队
        url2 = PropertiesUtil.getProperty(fileName, "schoolBus.send.url2");
    }


    /**
     * 家长信息服务类
     */
    @Autowired
    @Qualifier("parentProxyService")
    protected ParentProxyService parentProxyService;

    @Autowired
    private SchoolBusService schoolBusService;

    @RequestMapping(value = "/index")
    public ModelAndView indexStu(
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = structurePath("/index");
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/json")
    @ResponseBody
    public StuVo json(
            @CurrentUser UserInfo user,
            @RequestParam(value = "name", required = false) String name,
            ParentStudentCondition parentStudentCondition,
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        conditionFilter(user, condition);
        parentConditionFilter(user, parentStudentCondition);
        if (!StringUtils.isEmpty(condition.getUserName())) {
            parentStudentCondition.setUserName(condition.getUserName());
        }
        if (name != null && !"".equals(name)) {
            parentStudentCondition.setName(name);
        }

        if (condition != null && condition.getMobile() != null) {
            parentStudentCondition.setMobile(condition.getMobile());
        }
        List<ParentStudent> list = parentProxyService.findSutBusByGroupCondition(parentStudentCondition, page);

        StuVo stuVo = new StuVo();
        stuVo.setList(list);
        stuVo.setPage(page);
        return stuVo;
    }

    /**
     * 提供给第三方校车调用
     *
     * @param user
     * @param parentStudentCondition
     * @param condition
     * @param page
     * @return
     */
    @RequestMapping(value = "/getStuList")
    @ResponseBody
    public ResponseInfomation getStuList(
            @CurrentUser UserInfo user,
            ParentStudentCondition parentStudentCondition,
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("page") Page page) {
        conditionFilter(user, condition);
        parentConditionFilter(user, parentStudentCondition);
        if (!StringUtils.isEmpty(condition.getUserName())) {
            parentStudentCondition.setUserName(condition.getUserName());
        }

        if (condition != null && condition.getMobile() != null) {
            parentStudentCondition.setMobile(condition.getMobile());
        }
        // 返回给校车方的数据不加限制，但是，查询完成的数据，下次自动推送功能时：不会再推送
        List<ParentStudentBus> list = parentProxyService.findParentSutBusByGroupCondition(parentStudentCondition, page);

        Pages pages = new Pages();
        BeanUtils.copyProperties(page, pages);
        pages.setList(list);
        // 将查询的数据推送状态改为1   查询完成的数据，下次自动推送功能时：不会再推送
//        schoolBusService.update(list, systemId);
        return new ResponseInfomation(pages, "success");
    }


    /**
     * 批量添加用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTime(@RequestParam("ids") String ids) {
        SchoolBusMangerVo schoolBusMangerVo = new SchoolBusMangerVo();
        schoolBusMangerVo.setCreateDate(new Date());
        schoolBusMangerVo.setModifyDate(new Date());
        // 添加完成需要将用户推送到对应的校车系统
        /**
         * 1. 批量/单条 推送过去
         * 2. 还有一个定时任务用来执行没有推送/推送失败的
         *
         * 如果删除之后，再次添加，那么这条数据就会推送两次，需要添加一个判断
         *
         */
        SchoolBusMangerVo schoolBusManger = this.schoolBusService.creates(ids, schoolBusMangerVo, isSend, url, url2);
        return schoolBusManger == null ? ResponseInfomation.OPERATION_FAIL : ResponseInfomation.OPERATION_SUC;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @param systemId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("ids") String ids) {
        return this.schoolBusService.abandonTeacher(ids);
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void parentConditionFilter(UserInfo user, ParentStudentCondition condition) {
        Integer schoolId = condition.getSchoolId();
        if (user != null) {
            if (schoolId == null) {
                condition.setSchoolId(user.getSchoolId());
            }
        }
        condition.setIsDelete(false);
    }

    private void conditionFilter(UserInfo user, UserCondition condition) {
        Integer groupId = condition.getGroupId();
        Integer appId = condition.getAppId();
        if (user != null) {
            if (groupId == null) {
                condition.setGroupId(user.getGroupId());
            }

            if (appId == null) {
                condition.setAppId(SysContants.SYSTEM_APP_ID);
            }
        }
        condition.setIsDeleted(false);
    }
}
