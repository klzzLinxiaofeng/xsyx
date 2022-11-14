package platform.szxyzxx.web.parent.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.user.model.User;
import platform.education.user.service.UserService;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.education.user.vo.UserCondition;

@Controller
@RequestMapping(value = "/parent/login")
public class ParentLoginController {

	@Resource
	private UserService userService;
	@Resource
	private SchoolTermCurrentService schoolTermCurrentService;
	@Resource
	private StudentService studentService;
	@Resource
	private TeamStudentService teamStudentService;
	/**
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/in")
	@ResponseBody
	public String wechat_loginPerformer(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {
		String md5Password = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(password);
		User u=userService.findUserByUsername(username);
		String code="";
		if(u==null){
			code="-1";
		}else{
			UserCondition uc=new UserCondition();
			uc.setUserName(username);
			uc.setPassword(md5Password);
			List<User> uList=userService.findUserByCondition(uc, null, null);
			if(uList.size()==0){
				code="-2";
			}else{
				u=uList.get(0);
				if(u.getState().equals("0")){
					Student s=studentService.findStudentByUserId(u.getId());
					code="-4";
					if(s!=null){
					SchoolTermCurrent st=	schoolTermCurrentService.findSchoolTermCurrentBySchoolId(s.getSchoolId());
					if(st!=null){
						TeamStudentCondition tsCondition=new TeamStudentCondition();
						tsCondition.setUserId(u.getId());
						tsCondition.setSchoolYear(st.getSchoolYear());
						tsCondition.setInState(true);
						tsCondition.setIsDelete(false);
						List<TeamStudent>tsList=teamStudentService.findTeamStudentByCondition(tsCondition, null, null);
						if(tsList.size()>0){
							code=u.getId()+"";
						}
					}
					}
				}else{
					code="-3";
				}
			}
			
		}
		return code;
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String wechat_loginPerformer(
			HttpServletRequest request) {
		
		return "/parentLogin/index";
	}
}
