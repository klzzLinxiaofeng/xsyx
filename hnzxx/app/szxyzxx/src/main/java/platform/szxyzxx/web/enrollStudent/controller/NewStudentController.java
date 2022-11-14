package platform.szxyzxx.web.enrollStudent.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.enrollStudent.model.NewStudent;
import platform.education.enrollStudent.vo.NewStudentCondition;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/***
 * 招生管理--新生报名
 * @author admin
 *
 */
@Controller
@RequestMapping("/entrollStudent/newStudent")
public class NewStudentController  extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(NewStudentController.class);
	
	@RequestMapping("/getNewStudentList")
	public ModelAndView getNewStudentList(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("newStudentCondition") NewStudentCondition newStudentCondition,
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			newStudentCondition.setSchoolId(user.getSchoolId());
			List<NewStudent> newStudentList = this.newStudentService.findNewStudentByCondition(newStudentCondition, page, order);
			if(newStudentList != null && newStudentList.size() > 0){
				for(int i = 0; i < newStudentList.size(); i++){
					if(newStudentList.get(i).getSchoolYear() != null && !"".equals(newStudentList.get(i).getSchoolYear())){
						SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
						schoolYearCondition.setYear(newStudentList.get(i).getSchoolYear());
						schoolYearCondition.setIsDelete(false);
						List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, null);
						if(schoolYearList != null && schoolYearList.size() > 0){
							newStudentList.get(i).setSchoolYear(schoolYearList.get(0).getName());
						}
					}
				}
			}
			if("list".equals(sub)){
				viewPath="/enrollStudent/newStudent/list";
			}else{
				viewPath="/enrollStudent/newStudent/getNewStudentList";
			}
			mav.addObject("newStudentList", newStudentList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询新生注册列表异常...");
			e.printStackTrace();
		}
		mav.setViewName(viewPath);
		return mav;
	}
	
	@RequestMapping("/addNewStudentPage")
	public ModelAndView addNewStudentPage(){
		ModelAndView mav = new ModelAndView();
		String viewPath="/enrollStudent/newStudent/addNewStudent";
		mav.setViewName(viewPath);
		return mav;
	}
	
	/**
	 * 招生管理--保存新生
	 * @param newStudent
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/addNewStudent")
	@ResponseBody
	public ResponseInfomation addNewStudent(NewStudent newStudent,
			@CurrentUser UserInfo userInfo){
		try{
			/**
			 * 2016-3-14 修改：判断学生是否已经登记过 
			 * 2016-3-22 修改：同名同身份证同号码还可以再次添加  但是需要学生类别不一致
			 */
			List<NewStudent> newStudentList = null;
			NewStudentCondition newStudentCondition = new NewStudentCondition();
			newStudentCondition.setSchoolId(userInfo.getSchoolId());
			newStudentCondition.setDelete(false);
			if(newStudent.getIdCardNumber() != null && !"".equals(newStudent.getIdCardNumber())){
				newStudentCondition.setIdCardNumber(newStudent.getIdCardNumber());
				newStudentCondition.setStudentType(newStudent.getStudentType());
				newStudentList = newStudentService.findNewStudentByCondition(newStudentCondition, null, null);
				if(newStudentList != null && newStudentList.size() > 0){
					//身份证已存在，创建失败
					return new ResponseInfomation("idCardNumberExit");
				}
			}
			if(newStudent.getTelephone() != null && !"".equals(newStudent.getTelephone())){
				newStudentCondition.setIdCardNumber(null);
				newStudentCondition.setTelephone(newStudent.getTelephone());
				newStudentList = newStudentService.findNewStudentByCondition(newStudentCondition, null, null);
				if(newStudentList != null && newStudentList.size() > 0){
					//号码已存在，创建失败
					return new ResponseInfomation("telephoneNumberExit");
				}
			}
			
			String schoolYear = null;
			List<SchoolTermCurrent> currentSchoolTermList = schoolTermCurrentService.findCurrentSchoolYear(userInfo.getSchoolId());
			if(currentSchoolTermList.size() > 0){
				schoolYear = currentSchoolTermList.get(0).getSchoolYear();
			}
			newStudent.setSchoolYear(schoolYear);
			newStudent.setSchoolId(userInfo.getSchoolId());
			newStudent.setRegionStates("1");//1:未注册 2：已注册
			newStudent.setCreateDate(new Date());
			newStudent.setIsDelete(false);
			this.newStudentService.add(newStudent);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	/***
	 * 招生管理--修改新生注册
	 */
	@RequestMapping("/modifyNewStudentPage")
	public ModelAndView modifyNewStudentPage(@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = null;
		try{
		    mav = new ModelAndView();
		    NewStudent newStudent = newStudentService.findNewStudentById(Integer.parseInt(id));
			mav.addObject("newStudent", newStudent);
			mav.setViewName("/enrollStudent/newStudent/modifyNewStudentPage");
		}catch(Exception e){
			log.info("修改新生注册异常.....");
			e.printStackTrace();
		}
		return mav;
	}
	
	/***
	 * 招生管理--更新新生注册
	 */
	@RequestMapping("/updateNewStudent")
	@ResponseBody
	public ResponseInfomation updateNewStudent(NewStudent newStudent,@CurrentUser UserInfo userInfo){
		try{
			/**
			 * 2016-3-14 修改：判断学生是否已经登记过
			 * 2016-3-22 修改：同名同身份证同号码可再次添加  但需要学生类型不一致
			 */
			NewStudent ns = newStudentService.findNewStudentById(newStudent.getId());
			List<NewStudent> newStudentList = null;
			NewStudentCondition newStudentCondition = new NewStudentCondition();
			newStudentCondition.setSchoolId(userInfo.getSchoolId());
			newStudentCondition.setDelete(false);
			if(newStudent.getIdCardNumber() != null && !"".equals(newStudent.getIdCardNumber())){
				newStudentCondition.setIdCardNumber(newStudent.getIdCardNumber());
				newStudentCondition.setStudentType(newStudent.getStudentType());
				newStudentList = newStudentService.findNewStudentByCondition(newStudentCondition, null, null);
				if(newStudentList != null && newStudentList.size() > 0){
					for(NewStudent student : newStudentList){
						if(!student.getId().equals(ns.getId())){
							//身份证已存在，创建失败
							return new ResponseInfomation("idCardNumberExit");
						}
					}
				}
			}
			
			if(newStudent.getTelephone() != null && !"".equals(newStudent.getTelephone())){
				newStudentCondition.setIdCardNumber(null);
				newStudentCondition.setTelephone(newStudent.getTelephone());
				newStudentList = newStudentService.findNewStudentByCondition(newStudentCondition, null, null);
				if(newStudentList != null && newStudentList.size() > 0){
					for(NewStudent student : newStudentList){
						if(!student.getId().equals(ns.getId())){
							//号码已存在，创建失败
							return new ResponseInfomation("telephoneNumberExit");
						}
					}
				}
			}
			
			newStudentService.modify(newStudent);
		}catch(Exception e){
			log.info("招生管理--更新新生注册异常.....");
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	/**
	 * 删除新注册的学生
	 */
	@RequestMapping("/deleteNewStudent")
	@ResponseBody
	public String deleteNewStudent(
			@RequestParam(value = "id", required = true) String id){
		try{
			NewStudent newStudent = newStudentService.findNewStudentById(Integer.parseInt(id));
			newStudent.setIsDelete(true);
			newStudentService.modify(newStudent);
		}catch(Exception e){
			log.info("删除新注册的学生异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	
}
