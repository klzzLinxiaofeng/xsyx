package platform.szxyzxx.web.teach.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.vo.SchoolSystemCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/schoolsystem")
public class SchoolSystemController extends BaseController { 
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolSystem> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SchoolSystemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.schoolSystemService.findSchoolSystemByCondition(condition, page, order);
	}
	

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(SchoolSystem schoolSystem, @CurrentUser UserInfo user) {
		Integer schoolId = schoolSystem.getSchoolId();
		if(schoolId == null) {
			schoolSystem.setSchoolId(user.getSchoolId());
		}
		schoolSystem = this.schoolSystemService.add(schoolSystem);
		return schoolSystem != null ? new ResponseInfomation(schoolSystem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, SchoolSystem schoolSystem) {
		if (schoolSystem != null) {
			schoolSystem.setId(id);
		}
		try {
			this.schoolSystemService.remove(schoolSystem);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SchoolSystem schoolSystem) {
		schoolSystem.setId(id);
		schoolSystem = this.schoolSystemService.modify(schoolSystem);
		return schoolSystem != null ? new ResponseInfomation(schoolSystem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private void conditionFilter(UserInfo user, SchoolSystemCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if(user != null && schoolId == null) {
			condition.setSchoolId(user.getSchoolId());
		}
	}
}
