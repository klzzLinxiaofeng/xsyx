package platform.szxyzxx.web.oa.controller;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.oa.model.Repair;
import platform.education.oa.model.RepairFile;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.RepairCondition;
import platform.education.oa.vo.RepairFileCondition;
import platform.education.oa.vo.RepairFileVo;
import platform.education.oa.vo.RepairVo;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.RepairStatus;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/oa/repair")
public class RepairController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/repair";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") RepairCondition condition,
			@ModelAttribute("page") Page page,@RequestParam(value = "isReceive", required = false ) String isReceive,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		if(isReceive==null || "".equals(isReceive)){
			condition.setPosterId(user.getId());
		}
		condition.setIsDelete(false);
		condition.setOwnerId(user.getSchoolId());
		List<RepairVo> items = this.repairService.findRepairAllByCondition(condition, page, order);
//		for(int i=0;i<items.size();i++){
//			if(items.get(i).getAppId()!=null && !"".equals(items.get(i).getAppId())){
//				items.get(i).setAppName(this.appService.findAppById(items.get(i).getAppId()).getName());
//			}
//			
//			if(items.get(i).getReceiverId()!=null){
//			TeacherCondition teacherCondition = new TeacherCondition();
//			teacherCondition.setSchoolId(user.getSchoolId());
//			teacherCondition.setUserId(Integer.parseInt(items.get(i).getReceiverId()));
//			List<Teacher> tId = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
//			if(tId!=null && tId.size()>0){
//				items.get(i).setReceiverId(tId.get(0).getName());
//			}else{
//				items.get(i).setReceiverId("");
//			}
//			}
//		}
		
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("isReceive", isReceive);
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Repair> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") RepairCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.repairService.findRepairByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model) {
		DepartmentCondition dc = new DepartmentCondition();
		dc.setIsDelete(false);
		model.addAttribute("departmentList", this.departmentService.findDepartmentByCondition(dc,null,null));
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(RepairVo repair, @CurrentUser UserInfo user) {
		if(repair.getDepartmentId()!=null){
			repair.setDepartmentName(this.departmentService.findDepartmentById(repair.getDepartmentId()).getName());
		}
		repair.setUuid(UUIDUtils.getUUID());
		repair.setPostTime(new Date());
		repair.setOwnerId(user.getSchoolId());
		repair.setOwner_type(user.getUserTypes());
		repair.setAppId(SysContants.SYSTEM_APP_ID);
		repair.setPosterId(user.getId());
		repair.setPosterName(user.getRealName());
		repair.setDealStatus(RepairStatus.apply);
		Repair re = null;
		try{
		//维修记录的保存
		re = this.repairService.add(repair);
		//维修附件的保存
			if(repair.getFileUuid()!=null && !"".equals(repair.getFileUuid())){
				String[] fileId = repair.getFileUuid().split(",");
				String[] fileUrl = repair.getThumbUrl().split(",");
				for(int i = 0;i < fileId.length; i++){
					RepairFile repairFile = new RepairFile();
					repairFile.setRepairId(re.getId());
					repairFile.setPosterId(user.getId());
					repairFile.setPostTime(new Date());
					repairFile.setFileUuid(fileId[i]);
					repairFile.setThumbUrl(fileUrl[i]);
					repairFileService.add(repairFile);
				}
			}
		}catch(Exception e){
			System.out.println(e);
		 
		}
		
		return re != null ? new ResponseInfomation(re.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Repair repair = this.repairService.findRepairById(id);
		DepartmentCondition dc = new DepartmentCondition();
		dc.setIsDelete(false);
		
		RepairFileCondition repairFileCondition = new RepairFileCondition();
		repairFileCondition.setRepairId(id);
		repairFileCondition.setIsDelete(false);
		List<RepairFileVo> list = repairFileService.findRepairFileAllByCondition(repairFileCondition);
		EntityFile entity = null;
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				entity = this.entityFileService.findFileByUUID(list.get(i).getFileUuid());
				if(entity != null){
					list.get(i).setRealFileName(entity.getFileName());
				}
			}
		}
		model.addAttribute("fileList", list);
		model.addAttribute("appList", this.appService.findAppByCondition(null));
		model.addAttribute("departmentList", this.departmentService.findDepartmentByCondition(dc,null,null));
		model.addAttribute("repair", repair);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model,@CurrentUser UserInfo user) {
		Repair repair = this.repairService.findRepairById(id);
		DepartmentCondition dc = new DepartmentCondition();
		dc.setIsDelete(false);
		
		RepairFileCondition repairFileCondition = new RepairFileCondition();
		repairFileCondition.setRepairId(id);
		repairFileCondition.setIsDelete(false);
		List<RepairFileVo> list = repairFileService.findRepairFileAllByCondition(repairFileCondition);
		EntityFile entity = null;
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				entity = this.entityFileService.findFileByUUID(list.get(i).getFileUuid());
				if(entity!=null){
					list.get(i).setRealFileName(entity.getFileName());
				}
			}
		}
		if(repair.getReceiverId()!=null){
			TeacherCondition teacherCondition = new TeacherCondition();
			teacherCondition.setSchoolId(user.getSchoolId());
			teacherCondition.setUserId(Integer.parseInt(repair.getReceiverId()));
			List<Teacher> tId = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
			if(tId!=null && tId.size()>0){
				repair.setReceiverId(tId.get(0).getName());
			}else{
				repair.setReceiverId("");
			}
			}
		model.addAttribute("fileList", list);
		model.addAttribute("appList", this.appService.findAppByCondition(null));
		model.addAttribute("departmentList", this.departmentService.findDepartmentByCondition(dc,null,null));
		model.addAttribute("isCK", "disable");
		model.addAttribute("repair", repair);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Repair repair) {
		if (repair != null) {
			repair.setId(id);
		}
		try {
			this.repairService.remove(repair);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			RepairVo repair,@CurrentUser UserInfo user) {
			repair.setId(id);
		//受理处理：通过判断是否受理 来确定
				if(repair.getDealStatus()!=null && repair.getDealStatus().equals(RepairStatus.receive)){  //接受受理
					repair.setReceiverId(user.getId().toString());
					repair.setDealTime(new Date());
					repair.setDealStatus(RepairStatus.receive);
				}else if(repair.getDealStatus()!=null && repair.getDealStatus().equals(RepairStatus.notReceive)){ //不受理
					repair.setDealTime(new Date());
					repair.setReceiverId(user.getId().toString());
					repair.setReceiverName(user.getUserName());
					repair.setDealStatus(RepairStatus.notReceive);
				}else if(repair.getDealStatus()!=null && repair.getDealStatus().equals(RepairStatus.finish)){   //受理完成处理
					repair.setDealStatus(RepairStatus.finish);
					repair.setFinishTime(new Date());
				}else if(repair.getDealStatus()!=null && repair.getDealStatus().equals(RepairStatus.assess)){   //完成评价处理
					repair.setDealStatus(RepairStatus.assess);
				}
				
				//修改部门ID的同时，修改部门名称
				if(this.departmentService.findDepartmentById(repair.getDepartmentId())!=null){
					repair.setDepartmentName(this.departmentService.findDepartmentById(repair.getDepartmentId()).getName());
				}
				Repair re = this.repairService.modify(repair);
		
		//删除图片后再创建,当前状态是申请，而不是受理、完成、评价下才执行
		if(repair.getDealStatus()==null || re.getDealStatus().equals(RepairStatus.apply)){
		RepairFileCondition repairFileCondition = new RepairFileCondition();
		repairFileCondition.setRepairId(re.getId());
		List<RepairFile> list = repairFileService.findRepairFileByCondition(repairFileCondition);
		if(list.size() > 0){
			for(int i = 0; i< list.size(); i++){
				RepairFile repairFile = new RepairFile();
				repairFile.setId(list.get(i).getId());
				repairFile.setIsDelete(true);
				repairFileService.modify(repairFile);	
			}
		}
		//再次创建图片，判断传过来的图片有没有
		if(repair.getFileUuid()!=null && !"".equals(repair.getFileUuid())){
			String[] fileId = repair.getFileUuid().split(",");
			String[] fileUrl = repair.getThumbUrl().split(",");
			for(int i = 0;i < fileId.length; i++){
				RepairFile repairFile = new RepairFile();
				repairFile.setRepairId(re.getId());
				repairFile.setPosterId(user.getId());
				repairFile.setPostTime(new Date());
				repairFile.setFileUuid(fileId[i]);
				repairFile.setThumbUrl(fileUrl[i]);
				repairFileService.add(repairFile);
			}
		}
		}
		
		return repair != null ? new ResponseInfomation(repair.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	@RequestMapping(value = "/rececive", method = RequestMethod.GET)
	public ModelAndView rececive(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		model.addAttribute("id", id);
		return new ModelAndView(structurePath("/receiveInput"), model.asMap());
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public ModelAndView finish(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		model.addAttribute("id", id);
		return new ModelAndView(structurePath("/finishInput"), model.asMap());
	}
	
	@RequestMapping(value = "/assess", method = RequestMethod.GET)
	public ModelAndView assess(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		model.addAttribute("id", id);
		return new ModelAndView(structurePath("/assessInput"), model.asMap());
	}
	private void conditionFilter(UserInfo user, RepairCondition condition) {
	}
}
