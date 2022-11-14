package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.school.affair.model.SchoolLand;
import platform.education.school.affair.service.SchoolLandService;
import platform.education.school.affair.vo.SchoolLandCondition;
import platform.service.storage.service.FileService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/schoolaffair/schoolland")
public class SchoolLandController { 
	
	private final static String viewBasePath = "/schoolaffair/schoolland";
	
	@Autowired
	@Qualifier("schoolLandService")
	private SchoolLandService schoolLandService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") SchoolLandCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		if(user != null) {
			condition.setSchoolId(user.getSchoolId());
			condition.setIsDeleted(false);
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			List<SchoolLand> items = this.schoolLandService.findSchoolLandByCondition(condition, page, order);
			if ("list".equals(sub)) {
				viewPath = structurePath("/list");
			} else {
				viewPath = structurePath("/index");
			}
			model.addAttribute("items", items);
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolLand> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SchoolLandCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.schoolLandService.findSchoolLandByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(SchoolLand schoolLand, @CurrentUser UserInfo user) {
		
		if(user != null) {
			schoolLand.setSchoolId(user.getSchoolId());
			schoolLand = this.schoolLandService.add(schoolLand);
		}
		return schoolLand != null ? new ResponseInfomation(schoolLand.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		SchoolLand schoolLand = this.schoolLandService.findSchoolLandById(id);
		model.addAttribute("schoolLand", schoolLand);
		if(schoolLand != null) {
			builFile(model,schoolLand.getFiles());
		}
		
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		SchoolLand schoolLand = this.schoolLandService.findSchoolLandById(id);
		if(schoolLand != null) {
			builFile(model,schoolLand.getFiles());
		}
		model.addAttribute("isCK", "disable");
		model.addAttribute("schoolLand", schoolLand);
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, SchoolLand schoolLand) {
		if (schoolLand != null) {
			schoolLand.setId(id);
		}
		try {
			schoolLand = this.schoolLandService.findSchoolLandById(id);
			if(schoolLand != null) {
				schoolLand.setIsDeleted(true);
				this.schoolLandService.modify(schoolLand);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SchoolLand schoolLand) {
		schoolLand.setId(id);
		schoolLand = this.schoolLandService.modify(schoolLand);
		return schoolLand != null ? new ResponseInfomation(schoolLand.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	

	private void builFile(Model uiModel ,String files) {
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		Map<String,Object> mp = null;
		
			if(files != null && !"".equals(files) && files.contains(",")) {
				String [] filess = files.split(",");
				if(filess != null &&filess.length>0) {
					for(int i=0;i<filess.length ;i++) {
						mp = new HashMap<String,Object>();
						String url = fileService.relativePath2HttpUrlByUUID(filess[i]);
						mp.put("url", url);
						mp.put("uuid", filess[i]);
						fileList.add(mp);
					}
					
				}
			}else {
				mp = new HashMap<String,Object>();
				String url = fileService.relativePath2HttpUrlByUUID(files);
				mp.put("url", url);
				mp.put("uuid", files);
				fileList.add(mp);
			}
			
			uiModel.addAttribute("fileList", fileList);
		
	}
	
}
