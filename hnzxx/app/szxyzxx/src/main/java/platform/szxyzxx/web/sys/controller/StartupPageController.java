
package platform.szxyzxx.web.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.startuppage.model.Startup;
import platform.education.startuppage.service.StartupService;

import platform.education.startuppage.vo.StartupCondition;
import platform.education.startuppage.vo.StartupVo;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/sys/startup")
public class StartupPageController {

    private final static String viewBasePath = "sys/startup";

    @Resource
    private StartupService StartupService;
    @Resource
    private FileService fileService;

    /**
     * @param user      session信息
     * @param dm        yh_permission的code
     * @param sub
     * @param condition 实体类名
     * @param page      页数
     * @param order
     * @param model
     * @Description: 首页
     * @Param: * @param user
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StartupCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        //分页查询start_up表数据
        List<Startup> items = this.StartupService.findStartupCondition(condition, page, order);
        List<StartupVo> voList = new ArrayList<>();
        //        通过uuid查询res_entity_file中的文件
        if (items.size() > 0) {
            for (Startup mb : items) {
                StartupVo vo = new StartupVo();
                BeanUtils.copyProperties(mb, vo);
                String entityId = mb.getEntityId();
                FileResult file = fileService.findFileByUUID(entityId);
                //                添加文件到列表中
                if (file != null) {
                    vo.setThumUrl(file.getHttpUrl());
                }
                voList.add(vo);
            }
        }
        //       将数据返回到list或index页面
        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("items", voList);
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * @param user      session信息
     * @param condition 实体类名
     * @param page      页数
     * @param order
     * @Description: 展示数据列表
     * @return: java.util.List<platform.education.startuppage.model.Startup>
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Startup> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") StartupCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {
        page = usePage ? page : null;
        return this.StartupService.findStartupCondition(condition, page, order);
    }

    /**
     * @Description: 点击创建时跳转页面到编辑页面
     * @Param: * @param
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/10
     */
    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/input"));
    }

    /**
     * @param user
     * @Description: 创建信息
     * @Param: * @param Startup
     * @return: platform.szxyzxx.web.common.util.ResponseInfomation
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(Startup Startup, @CurrentUser UserInfo user) {
        Startup = this.StartupService.add(Startup);
        return Startup != null ? new ResponseInfomation(Startup.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    /**
     * @param model
     * @Description: 获取文件信息
     * @Param: * @param id
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/10
     */


    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        //        通过id查询对象信息
        Startup Startup = this.StartupService.findStartupById(id);
        StartupVo vo = new StartupVo();
        BeanUtils.copyProperties(Startup, vo);
        String entityId = Startup.getEntityId();
        //        通过uuid查询对应res_entity_file表中的文件
        FileResult file = fileService.findFileByUUID(entityId);
        if (file != null) {
            vo.setThumUrl(file.getHttpUrl());
        }
        model.addAttribute("Startup", vo);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    /**
     * @param model
     * @Description: 展示文件url中的信息
     * @Param: * @param id
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        //        通过id查询对象信息
        Startup Startup = this.StartupService.findStartupById(id);
        StartupVo vo = new StartupVo();
        BeanUtils.copyProperties(Startup, vo);
        String entityId = Startup.getEntityId();
        //        通过uuid查询对应的文件
        FileResult file = fileService.findFileByUUID(entityId);
        if (file != null) {
            vo.setThumUrl(file.getHttpUrl());
        }
        model.addAttribute("Startup", vo);
        model.addAttribute("isCK", "disable");
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    /**
     * @param Startup
     * @Description: 删除列表数据
     * @Param: * @param id
     * @return: java.lang.String
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, Startup Startup) {
        if (Startup != null) {
            Startup.setId(id);
        }
        try {
            this.StartupService.remove(Startup);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * @param Startup
     * @Description: 修改数据信息
     * @Param: * @param id
     * @return: platform.szxyzxx.web.common.util.ResponseInfomation
     * @Author: cmb
     * @Date: 2020/11/10
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Startup Startup) {
        Startup.setId(id);
        Startup = this.StartupService.modify(Startup);
        return Startup != null ? new ResponseInfomation(Startup.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    /**
     * @Description: 基础url
     * @Param: * @param subPath
     * @return: java.lang.String
     * @Author: cmb
     * @Date: 2020/11/10
     */

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}
