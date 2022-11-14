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
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.UserDetailInfo;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 招生管理---新生注册
 * @author admin
 *
 */

@Controller
@RequestMapping("/entrollStudent/newStudentRegist")
public class NewStudentRegistController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(NewStudentRegistController.class);
	
	@RequestMapping("/getNewStudentRegistList")
	public ModelAndView getNewStudentRegistList(
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
			if(newStudentCondition.getRegionStates() == null || "".equals(newStudentCondition.getRegionStates())){
				newStudentCondition.setRegionStates("2");
			}
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
				viewPath="/enrollStudent/newStudentRegist/list";
			}else{
				viewPath="/enrollStudent/newStudentRegist/getNewStudentRegistList";
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
	
	/***
	 * 新生注册页面
	 */
	@RequestMapping("/addNewStudentRegistPage")
	public ModelAndView addNewStudentRegistPage(
			@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = null;
		try{
		    mav = new ModelAndView();
		    NewStudent newStudent = newStudentService.findNewStudentById(Integer.parseInt(id));
			mav.addObject("newStudent", newStudent);
			mav.setViewName("/enrollStudent/newStudentRegist/addNewStudentRegistPage");
		}catch(Exception e){
			log.info("新生注册页面.....");
			e.printStackTrace();
		}
		return mav;
	}
	
	/***
	 * 查看新生注册页面
	 */
	@RequestMapping("/viewNewStudentRegistPage")
	public ModelAndView viewNewStudentRegistPage(
			@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = null;
		try{
		    mav = new ModelAndView();
		    NewStudent newStudent = newStudentService.findNewStudentById(Integer.parseInt(id));
			mav.addObject("newStudent", newStudent);
			mav.setViewName("/enrollStudent/newStudentRegist/viewNewStudentRegistPage");
		}catch(Exception e){
			log.info("查看新生注册页面.....");
			//e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 删除新注册的学生
	 */
	@RequestMapping("/deleteNewStudentRegist")
	@ResponseBody
	public String deleteNewStudentRegist(
			@RequestParam(value = "id", required = true) String id){
		try{
			NewStudent newStudent = newStudentService.findNewStudentById(Integer.parseInt(id));
			newStudent.setIsDelete(true);
			newStudentService.modify(newStudent);
		}catch(Exception e){
			log.info("删除新注册的学生异常");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 新增新生注册
	 */
	@RequestMapping("/addNewStudentRegist")
	@ResponseBody
	public ResponseInfomation addNewStudentRegist(NewStudent newStudent,
			@CurrentUser UserInfo userInfo){
		try{
			newStudent.setCreateDate(new Date());
			newStudent.setSchoolId(userInfo.getSchoolId());
			UserDetailInfo userDetailInfo = teachService.saveNewStudentInfo(newStudent);
			if(userDetailInfo != null && userDetailInfo.getMessage() != null && !"".equals(userDetailInfo.getMessage())){
				//保存失败提示失败原因
				return new ResponseInfomation(userDetailInfo,userDetailInfo.getMessage());
			}
		}catch(Exception e){
			log.info("新增新生注册异常");
			e.printStackTrace();
			new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	/***
	 * 编辑新生注册
	 */
	@RequestMapping("/editNewStudentRegistPage")
	public ModelAndView editNewStudentRegistPage(
			@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = null;
		try{
		    mav = new ModelAndView();
		    NewStudent newStudent = newStudentService.findNewStudentById(Integer.parseInt(id));
		    UserDetailInfo userDetailInfo = teacherService.findUserDetailInfoById(String.valueOf(newStudent.getStudentId()));
		    //System.out.println("==="+userDetailInfo.getEntityId());
			mav.addObject("userDetailInfo", userDetailInfo);
			mav.addObject("newStudent", newStudent);
			mav.setViewName("/enrollStudent/newStudentRegist/editNewStudentRegistPage");
		}catch(Exception e){
			log.info("编辑新生注册.....");
			e.printStackTrace();
		}
		return mav;
	}
	
	/***
	 * 更新新生注册
	 */
	@RequestMapping("/updateNewStudentRegist")
	@ResponseBody
	public ResponseInfomation updateNewStudentRegist(UserDetailInfo userDetailInfo){
		try{
			teachService.modifyNewStudentInfo(userDetailInfo);
		}catch(Exception e){
			log.info("更新新生注册异常");
			//e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
}
