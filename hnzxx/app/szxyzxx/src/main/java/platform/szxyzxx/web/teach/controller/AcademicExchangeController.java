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
import platform.education.generalTeachingAffair.model.AcademicExchange;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.AcademicExchangeService;
import platform.education.generalTeachingAffair.vo.AcademicExchangeCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/teach/academicExchange")
public class AcademicExchangeController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/academicExchange";
	
	@Autowired
	@Qualifier("academicExchangeService")
	private AcademicExchangeService academicExchangeService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AcademicExchangeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<AcademicExchange> academicExchangeList = null; 
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			academicExchangeList = this.academicExchangeService.findAcademicExchangeByCondition(condition, page, order);
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("academicExchangeList", academicExchangeList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<AcademicExchange> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AcademicExchangeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.academicExchangeService.findAcademicExchangeByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addAcademicExchange(AcademicExchange academicExchange, @CurrentUser UserInfo user) {
		academicExchange.setSchoolId(user.getSchoolId());
		academicExchange.setIsDelete(false);
		academicExchange.setCreateDate(new Date());
		academicExchange.setModifyDate(new Date());
		String attendeesId = academicExchange.getAttendeesId();
		StringBuffer attendeesName = new StringBuffer();
		String[] attendeesArr = attendeesId.split(";");
		//根据id找name
		attendeesName = getNameById(attendeesId, attendeesArr, attendeesName);
		academicExchange.setAttendeesName(attendeesName.toString());
		academicExchange = this.academicExchangeService.add(academicExchange);
		return academicExchange != null ? new ResponseInfomation(academicExchange.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	/**
	 * 查看与编辑
	 * @param id
	 * @param user
	 * @param isCK
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		AcademicExchange academicExchange = this.academicExchangeService.findAcademicExchangeById(id);
		if(academicExchange!=null){
			String fileUuid = academicExchange.getFileUuid();
			if(fileUuid!=null){
				String[] uuidArr = fileUuid.split(";");
				List<EntityFile> entityList = new ArrayList<EntityFile>();
				for(int i=1;i<uuidArr.length;i++){
					EntityFile entity = this.entityFileService.findFileByUUID(uuidArr[i]);
					entityList.add(entity);
				}
				model.addAttribute("entityList",entityList);
			}
		}
		model.addAttribute("academicExchange",academicExchange);
		model.addAttribute("isCK",isCK);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 删除
	 * @param id
	 * @param academicExchange
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AcademicExchange academicExchange) {
		if (academicExchange != null) {
			academicExchange.setId(id);
		}
		return  this.academicExchangeService.moveTo(academicExchange);
	}

	/**
	 * 查看是否重名
	 * @param user
	 * @param dxlx
	 * @param name
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checker", method = RequestMethod.POST)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Integer id) {
		boolean flag = false;
		if ("name".equals(dxlx)) {
			List<AcademicExchange> list = academicExchangeService.findAcademicExchangeByNameAndSchool(name,user.getSchoolId());
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(AcademicExchange temp : list) {
					currentId = temp.getId();
					if(currentId != null && currentId.equals(id)) {
						flag = true;
					}else {
						flag = false;
					}
				}
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 编辑完成
	 * @param id
	 * @param academicExchange
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, 
			AcademicExchange academicExchange,
			@CurrentUser UserInfo user) {
		academicExchange.setId(id);
		academicExchange.setSchoolId(user.getSchoolId());
		academicExchange.setModifyDate(new Date());
		String attendeesId = academicExchange.getAttendeesId();
		StringBuffer attendeesName = new StringBuffer();
		String[] attendeesArr = attendeesId.split(";");
		attendeesName = getNameById(attendeesId, attendeesArr, attendeesName);
		academicExchange.setAttendeesName(attendeesName.toString());
		academicExchange = this.academicExchangeService.modify(academicExchange);
		return academicExchange != null ? new ResponseInfomation(academicExchange.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AcademicExchangeCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
	
	/**
	 * 通过id得到name
	 * @param id
	 * @param arr
	 * @param name
	 * @return
	 */
	private StringBuffer getNameById(String id,String[] arr,StringBuffer name){
		for(int i=1;i<arr.length;i++){
			Teacher teacher = this.teacherService.findTeacherById(Integer.valueOf(arr[i]));
			if(teacher!=null){
				if(i==1){
					name.append(teacher.getName());
				}else{
					name.append(";"+teacher.getName());
				}
			}
		}
		return name;
	}
}
