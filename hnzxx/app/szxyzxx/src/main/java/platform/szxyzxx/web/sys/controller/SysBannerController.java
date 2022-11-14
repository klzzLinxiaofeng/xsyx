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

import platform.education.sysbanner.model.SysBanner;
import platform.education.sysbanner.service.SysBannerService;
import platform.education.sysbanner.vo.SysBannerCondition;
import platform.education.sysbanner.vo.SysBannerVo;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/sys/sysbanner")
public class SysBannerController {

    private final static String viewBasePath = "sys/banner";

    @Resource
    private SysBannerService SysBannerService;
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
     * @Param: *
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") SysBannerCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        //分页查询sys_banner表数据
        List<SysBanner> items = this.SysBannerService.findSysBannerCondition(condition, page, order);
        List<SysBannerVo> voList = new ArrayList<SysBannerVo>();
//        通过uuid查询res_entity_file中的文件
        if (items.size() > 0) {
            for (SysBanner mb : items) {
                SysBannerVo vo = new SysBannerVo();
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

    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    /**
     * @Description: josn数据列表
     * @Param:
     * @param user session信息
     * @param condition 实体类名
     * @param page 页数
     * @param order
     * @param model
     * @return: java.util.List<platform.education.sysbanner.model.SysBanner>
     * @Author: cmb
     * @Date: 2020/11/10
     */

    public List<SysBanner> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") SysBannerCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {
        page = usePage ? page : null;
        return this.SysBannerService.findSysBannerCondition(condition, page, order);
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
     * @Param: * @param SysBanner
     * @return: platform.szxyzxx.web.common.util.ResponseInfomation
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(SysBanner SysBanner, @CurrentUser UserInfo user) {
        //添加数据到sys_banner表
        SysBanner = this.SysBannerService.add(SysBanner);
        return SysBanner != null ? new ResponseInfomation(SysBanner.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    /**
     * @param id
     * @param model
     * @Description: 获取文件信息
     * @Param:
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/11/10
     */
    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        //        通过id查询对象信息
        SysBanner SysBanner = this.SysBannerService.findSysBannerById(id);
        SysBannerVo vo = new SysBannerVo();
        BeanUtils.copyProperties(SysBanner, vo);
        String entityId = SysBanner.getEntityId();
        //        通过uuid查询对应res_entity_file表中的文件
        FileResult file = fileService.findFileByUUID(entityId);
        if (file != null) {
            vo.setThumUrl(file.getHttpUrl());
        }
        model.addAttribute("SysBanner", vo);
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
        SysBanner SysBanner = this.SysBannerService.findSysBannerById(id);
        SysBannerVo vo = new SysBannerVo();
        BeanUtils.copyProperties(SysBanner, vo);
        String entityId = SysBanner.getEntityId();
//        通过uuid查询对应的文件
        FileResult file = fileService.findFileByUUID(entityId);
        if (file != null) {
            vo.setThumUrl(file.getHttpUrl());
        }
        model.addAttribute("SysBanner", vo);
        model.addAttribute("isCK", "disable");
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    /**
     * @param SysBanner
     * @Description: 删除列表数据
     * @Param: * @param id
     * @return: java.lang.String
     * @Author: cmb
     * @Date: 2020/11/10
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, SysBanner SysBanner) {
        //通过id删除sys_banner表对应数据
        if (SysBanner != null) {
            SysBanner.setId(id);
        }
        try {
            this.SysBannerService.remove(SysBanner);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * @param SysBanner
     * @Description: 修改数据信息
     * @Param: * @param id
     * @return: platform.szxyzxx.web.common.util.ResponseInfomation
     * @Author: cmb
     * @Date: 2020/11/10
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SysBanner SysBanner) {
        SysBanner.setId(id);
        //通过id修改sys_banner表中的数据信息
        SysBanner = this.SysBannerService.modify(SysBanner);
        return SysBanner != null ? new ResponseInfomation(SysBanner.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    /**
     * @param subPath 基础url
     * @Description: 拼装url
     * @Param: *
     * @return: java.lang.String
     * @Author: cmb
     * @Date: 2020/11/10
     */

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}
