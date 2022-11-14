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

import platform.education.generalTeachingAffair.model.ResearchProject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ResearchProjectService;
import platform.education.generalTeachingAffair.vo.ResearchProjectCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/researchProject")
public class ResearchProjectController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/researchProject";
	
	@Autowired
	@Qualifier("researchProjectService")
	private ResearchProjectService researchProjectService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ResearchProjectCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<ResearchProject> researchProjectList = this.researchProjectService.findResearchProjectByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("researchProjectList", researchProjectList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ResearchProject> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ResearchProjectCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.researchProjectService.findResearchProjectByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addResearchProject(ResearchProject researchProject, @CurrentUser UserInfo user) {
		researchProject.setSchoolId(user.getSchoolId());
		researchProject.setIsDelete(false);
		researchProject.setCreateDate(new Date());
		researchProject.setModifyDate(new Date());
		//根据组织人员的id找到name
		String masterId = researchProject.getMasterId();
		StringBuffer masterName = new StringBuffer();
		String[] masterArr = masterId.split(";");
		masterName = getNameById(masterId, masterArr, masterName);
		researchProject.setMasterName(masterName.toString());
		//根据参与人员的id找到name
		String attendeesId = researchProject.getAttendeesId();
		StringBuffer attendeesName = new StringBuffer();
		String[] attendeesArr = attendeesId.split(";");
		attendeesName = getNameById(attendeesId, attendeesArr, attendeesName);
		researchProject.setAttendeesName(attendeesName.toString());
		researchProject = this.researchProjectService.add(researchProject);
		return researchProject != null ? new ResponseInfomation(researchProject.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		ResearchProject researchProject = this.researchProjectService.findResearchProjectById(id);
		if(researchProject!=null){
			String fileUuid = researchProject.getFileUuid();
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
		model.addAttribute("researchProject",researchProject);
		model.addAttribute("isCK",isCK);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ResearchProject researchProject) {
		if (researchProject != null) {
			researchProject.setId(id);
		}
		return  this.researchProjectService.moveTo(researchProject);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, 
			ResearchProject researchProject,
			@CurrentUser UserInfo user) {
		researchProject.setId(id);
		researchProject.setSchoolId(user.getSchoolId());
		researchProject.setModifyDate(new Date());
		//根据组织人员的id找到name
		String masterId = researchProject.getMasterId();
		StringBuffer masterName = new StringBuffer();
		String[] masterArr = masterId.split(";");
		masterName = getNameById(masterId, masterArr, masterName);
		researchProject.setMasterName(masterName.toString());
		//根据参与人员的id找到name
		String attendeesId = researchProject.getAttendeesId();
		StringBuffer attendeesName = new StringBuffer();
		String[] attendeesArr = attendeesId.split(";");
		attendeesName = getNameById(attendeesId, attendeesArr, attendeesName);
		researchProject.setAttendeesName(attendeesName.toString());
		researchProject = this.researchProjectService.modify(researchProject);
		return researchProject != null ? new ResponseInfomation(researchProject.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean flag = false;
		ResearchProjectCondition condition = new ResearchProjectCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setCode(code);
		condition.setIsDelete(false);
		if ("code".equals(dxlx)) {
			List<ResearchProject> list = researchProjectService.findResearchProjectByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(ResearchProject temp : list) {
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
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ResearchProjectCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
	/**
	 * 根据教师的id找name
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
