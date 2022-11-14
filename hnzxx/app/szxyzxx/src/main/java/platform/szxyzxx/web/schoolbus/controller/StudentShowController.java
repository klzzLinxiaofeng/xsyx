package platform.szxyzxx.web.schoolbus.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.service.StudentShowService;
import platform.education.generalTeachingAffair.vo.PerformancePojo;
import platform.education.user.vo.UserCondition;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/12 19:34
 * @Description: 学生表现
 */
@Controller
@RequestMapping("/student/show")
public class StudentShowController {


    private final static String viewBasePath = "/student/student_show";

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    @Autowired
    private StudentShowService studentShowService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;

    @RequestMapping(value = "/index")
    public ModelAndView indexStu(
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = structurePath("/index");
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/indexAdmin")
    public ModelAndView indexAdmin(
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = structurePath("/indexAdmin");
        return new ModelAndView(viewPath, model.asMap());
    }


    /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/addRelaseShow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation addRelaseShow(@CurrentUser UserInfo user, PerformancePojo pojo) {
        Integer teacherId = user.getTeacherId();
        if (teacherId == null) {
            return new ResponseInfomation("角色不符", ResponseInfomation.OPERATION_FAIL);
        }

        pojo.setTeacherId(teacherId);
        studentShowService.add(pojo);
        return pojo == null ? new ResponseInfomation() : new ResponseInfomation(pojo.getId(), ResponseInfomation.OPERATION_SUC);
    }

    /**
     * 删除根据表现id
     *
     * @return
     */
    @RequestMapping(value = "/delRelaseShow", method = RequestMethod.POST)
    @ResponseBody
    public String delRelaseShow(Integer id) {
        studentShowService.delete(id);
        return PublicClassService.OPERATE_SUCCESS;
    }

    @RequestMapping(value = "/json")
    @ResponseBody
    public StuVo json(
            @CurrentUser UserInfo user,
            PerformancePojo performancePojo,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        Integer teacherId = user.getTeacherId();
        if (performancePojo == null) {
            performancePojo = new PerformancePojo();
        }
        if (teacherId != null) {
            performancePojo.setTeacherId(teacherId);
        }
        List<PerformancePojo> list = studentShowService.findSutBusByGroupCondition(performancePojo, page);
        for (PerformancePojo pojo : list) {
            String uuid = pojo.getUuid();
            if(StringUtils.isNotEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    String[] url = new String[split.length];
                    for (int j = 0; j < split.length; j++) {
                        FileResult file = fileService.findFileByUUID(split[j]);
                        if (file != null) {
                            url[j] = file.getHttpUrl();
                        }
                    }
                    pojo.setUrl(url);
                }
            }
        }
        StuVo stuVo = new StuVo();
        stuVo.setList(list);
        stuVo.setPage(page);
        return stuVo;
    }

}
