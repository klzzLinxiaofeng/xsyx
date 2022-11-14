package platform.szxyzxx.web.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ui.Model;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/office/mailList")
public class MailListController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(MailListController.class);
	private final static String BASE_PATH = "oa/mail/";
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	/**
	 * 教师通讯录列表 
	 */
	
	@RequestMapping(value = "/mailList")
	public ModelAndView list(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("teacherCondition") TeacherCondition teacherCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		String viewPath = null;
		
		try {
			
			//教师信息列表
			teacherCondition.setSchoolId(user.getSchoolId());
			List<Teacher>teacherList = teacherService.findTeacherByCondition(teacherCondition, page, order);
			if ("list".equals(sub)) {
				viewPath = structurePath("/list");
			} else {
				viewPath = structurePath("/index");
			}
			model.addAttribute("teacherList", teacherList);
		} catch (Exception e) {
			log.info("查询教师列表异常...");
			e.printStackTrace();
		}
		
		return new ModelAndView(viewPath, model.asMap());
     }
	
	
		private String structurePath(String subPath) {
			return BASE_PATH + subPath;
		}
        }



