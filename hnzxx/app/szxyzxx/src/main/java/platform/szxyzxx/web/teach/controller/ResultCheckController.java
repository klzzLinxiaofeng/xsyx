package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import platform.education.generalTeachingAffair.model.Result;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ResultService;
import platform.education.generalTeachingAffair.vo.ResultCondition;
import platform.education.generalTeachingAffair.vo.ResultVo;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/teach/resultCheck")
public class ResultCheckController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/resultCheck";
	
	@Autowired
	@Qualifier("resultService")
	private ResultService resultService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ResultCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<ResultVo> resultVoList = null;
		resultVoList = this.resultService.findResultVoByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("resultVoList", resultVoList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Result> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ResultCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.resultService.findResultByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Result result = this.resultService.findResultById(id);
		if(result!=null){
			String fileUuid = result.getFileUuid();
			if(fileUuid!=null){
				String[] uuidArr = fileUuid.split(";");
				List<EntityFile> entityList = new ArrayList<EntityFile>();
				for(int i=1;i<uuidArr.length;i++){
					EntityFile entity =  this.entityFileService.findFileByUUID(uuidArr[i]);
					entityList.add(entity);
				}
				model.addAttribute("entityList",entityList);
			}
		}
		model.addAttribute("result",result);
		model.addAttribute("isCK",isCK);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			Result result,
			@CurrentUser UserInfo user) {
		result.setId(id);
		result.setSchoolId(user.getSchoolId());
		result.setModifyDate(new Date());
		Teacher teacher = this.teacherService.findTeacherById(Integer.parseInt(result.getTeachId()));
		if(teacher!=null){
			result.setTeachName(teacher.getName());
		}
		result = this.resultService.modify(result);
		return result != null ? new ResponseInfomation(result.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ResultCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
