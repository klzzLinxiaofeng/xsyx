package platform.szxyzxx.web.micro.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BannerMicroService;
import platform.education.generalTeachingAffair.vo.MicroBanner;
import platform.education.generalTeachingAffair.vo.MicroBannerCondition;
import platform.education.generalTeachingAffair.vo.MicroBannerVo;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/wkx/microbanner")
public class MicroBannerController {

    private final static String viewBasePath = "wkx/banner";

    @Autowired
    @Qualifier("bannerMicroService")
    private BannerMicroService bannerMicroService;


    @Resource
    private FileService fileService;

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") MicroBannerCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        List<MicroBanner> items = this.bannerMicroService.findMicroBannerByCondition(condition, page, order);
        List<MicroBannerVo> voList = new ArrayList<MicroBannerVo>();
        if (items.size() > 0) {
            for (MicroBanner mb : items) {
                MicroBannerVo vo = new MicroBannerVo();
                BeanUtils.copyProperties(mb, vo);
                String entityId = mb.getEntityId();
                FileResult file = fileService.findFileByUUID(entityId);
                if (file != null) {
                    vo.setThumUrl(file.getHttpUrl());
                }
                voList.add(vo);
            }
        }
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
    public List<MicroBanner> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") MicroBannerCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {
        page = usePage ? page : null;
        return this.bannerMicroService.findMicroBannerByCondition(condition, page, order);
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/input"));
    }

    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(MicroBanner microBanner, @CurrentUser UserInfo user) {
        microBanner = this.bannerMicroService.add(microBanner);
        return microBanner != null ? new ResponseInfomation(microBanner.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        MicroBanner microBanner = this.bannerMicroService.findMicroBannerById(id);
        MicroBannerVo vo = new MicroBannerVo();
        BeanUtils.copyProperties(microBanner, vo);
        String entityId = microBanner.getEntityId();
        FileResult file = fileService.findFileByUUID(entityId);
        if (file != null) {
            vo.setThumUrl(file.getHttpUrl());
        }
        model.addAttribute("microBanner", vo);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        MicroBanner microBanner = this.bannerMicroService.findMicroBannerById(id);
        MicroBannerVo vo = new MicroBannerVo();
        BeanUtils.copyProperties(microBanner, vo);
        String entityId = microBanner.getEntityId();
        FileResult file = fileService.findFileByUUID(entityId);
        if (file != null) {
            vo.setThumUrl(file.getHttpUrl());
        }
        model.addAttribute("microBanner", vo);
        model.addAttribute("isCK", "disable");
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, MicroBanner microBanner) {
        if (microBanner != null) {
            microBanner.setId(id);
        }
        try {
            this.bannerMicroService.remove(microBanner);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, MicroBanner microBanner) {
        microBanner.setId(id);
        microBanner = this.bannerMicroService.modify(microBanner);
        return microBanner != null ? new ResponseInfomation(microBanner.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    @RequestMapping(value = "/checker", method = RequestMethod.POST)
    @ResponseBody
    public boolean checker(@RequestParam(value = "pushState") String radio, @RequestParam(value = "id") Integer id) {
        if (StrUtil.isNotEmpty(radio) && ("1").equals(radio)) {
            return true;
        }
        MicroBannerCondition condition = new MicroBannerCondition();
        condition.setPushState("0");
        condition.setNotId(id);
        List<MicroBanner> items = this.bannerMicroService.findMicroBannerByCondition(condition, null, null);
        if (CollUtil.isNotEmpty(items) && items.size() >= 5) {
            return false;
        }
        return true;
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}
