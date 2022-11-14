package platform.szxyzxx.web.office.controller;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.service.model.InSchoolRoom;
import platform.education.service.service.InSchoolActivityService;
import platform.education.service.service.InSchoolRoomService;
import platform.education.service.vo.InSchoolActivityCondition;
import platform.education.service.vo.InSchoolRoomCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

@Controller
@RequestMapping("/office/in/school")
public class InSchoolRoomController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String viewBasePath = "office/in/school";

    @Autowired
    private InSchoolRoomService inSchoolRoomService;
    @Autowired
    private InSchoolActivityService inSchoolActivityService;

    @RequestMapping(value = "/room/index")
    public ModelAndView room2index(@CurrentUser UserInfo userInfo,
                                   InSchoolRoomCondition inSchoolRoomCondition,
                                   @ModelAttribute("page") Page page,
                                   @RequestParam(value = "sub", required = false) String sub,
                                   @RequestParam(value = "dm", required = false) String dm,
                                   Model model) {

        List<InSchoolRoom> inSchoolRoomList = this.inSchoolRoomService.findInSchoolRoomByCondition(inSchoolRoomCondition, page, Order.desc("create_date"));
        model.addAttribute("items", inSchoolRoomList);
        model.addAttribute("dm", dm);
        String subPath = "/room/index";
        if ("list".equals(sub)) {
            subPath = "/room/list";
        }
        return new ModelAndView(viewBasePath + subPath, model.asMap());
    }

    @RequestMapping(value = "/room/input", method = RequestMethod.GET)
    public ModelAndView room2input4get(Model model) {
        return new ModelAndView(viewBasePath + "/room/input", model.asMap());
    }

    @ResponseBody
    @RequestMapping(value = "/room/input", method = RequestMethod.POST)
    public ResponseInfomation room2input4post(@CurrentUser UserInfo userInfo,
                                              InSchoolRoomCondition inSchoolRoomCondition) {
        inSchoolRoomCondition.setSchoolId(userInfo.getSchoolId());
        InSchoolRoom inSchoolRoom;
        try {
            if (this.inSchoolRoomService.findInSchoolRoomByName(inSchoolRoomCondition.getName()) != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("msg", "已存在相同名称");
                this.log.warn("已存在相同名称");
                return new ResponseInfomation(jsonObject, ResponseInfomation.OPERATION_FAIL);
            }
            inSchoolRoom = this.inSchoolRoomService.add(inSchoolRoomCondition);
        } catch (Exception e) {
            this.log.debug(e.getMessage());
            return new ResponseInfomation();
        }
        return new ResponseInfomation(inSchoolRoom == null ? ResponseInfomation.OPERATION_FAIL : ResponseInfomation.OPERATION_SUC);
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public ModelAndView room2id4get(@PathVariable("id") Integer id,
                                    Model model) {
        model.addAttribute("inSchoolRoom", this.inSchoolRoomService.findInSchoolRoomById(id));
        return new ModelAndView(viewBasePath + "/room/input", model.asMap());
    }

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method = RequestMethod.POST)
    public ResponseInfomation room2id4post(@CurrentUser UserInfo userInfo,
                                           @PathVariable("id") Integer id,
                                           InSchoolRoomCondition inSchoolRoomCondition) {
        InSchoolRoom inSchoolRoom;
        try {

            inSchoolRoomCondition.setId(id);
            inSchoolRoom = this.inSchoolRoomService.modify(inSchoolRoomCondition);

        } catch (Exception e) {
            this.log.debug(e.getMessage());
            return new ResponseInfomation();
        }
        return new ResponseInfomation(inSchoolRoom == null ? ResponseInfomation.OPERATION_FAIL : ResponseInfomation.OPERATION_SUC);
    }

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method = RequestMethod.DELETE)
    public ResponseInfomation room2id4delete(@CurrentUser UserInfo userInfo,
                                             @PathVariable("id") Integer id) {
        try {
            JSONObject jsonObject = new JSONObject();
            InSchoolRoom inSchoolRoom = this.inSchoolRoomService.findInSchoolRoomById(id);

            InSchoolActivityCondition inSchoolActivityCondition = new InSchoolActivityCondition();
            inSchoolActivityCondition.setRoomId(id);
            if (this.inSchoolActivityService.count(inSchoolActivityCondition) > 0) {
                jsonObject.put("msg", "此记录在使用中");
                this.log.warn("此记录在使用中");
                return new ResponseInfomation(jsonObject, ResponseInfomation.OPERATION_FAIL);
            }
            this.inSchoolRoomService.remove(inSchoolRoom);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);

        } catch (Exception e) {
            this.log.debug(e.getMessage());
            return new ResponseInfomation();
        }
    }
}
