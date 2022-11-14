package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

/***
 * 升级管理
 * @author admin
 *
 */

@Controller
@RequestMapping("/teach/upGrade")
public class UpGradeController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(UpGradeController.class);
	/**
	 * 小学生升级列表
	 */
	@RequestMapping("/getPrimaryList")
	public ModelAndView getPrimaryList(@CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();;
		String viewPath = "/teach/upGrade/getPrimaryList";
		try{
			
			//学段获取年级
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setStageCode("2");
			gradeCondition.setSchoolId(user.getSchoolId());
			gradeCondition.setSchoolYear(user.getSchoolYear());
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
			
			List<Grade> gradeList_ = new ArrayList<Grade>();
			for(Grade grade : gradeList){
				List<Team> teamList = this.teamService.findTeamOfGradeAndSchool(grade.getId(), user.getSchoolId());
				for(Team team : teamList){
					TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
					teamTeacherCondition.setType(1);
					teamTeacherCondition.setGradeId(grade.getId());
					teamTeacherCondition.setTeamId(team.getId());
					teamTeacherCondition.setSchoolYear(user.getSchoolYear());
					
					List<TeamTeacher>  teamTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
					String teamTeacherName = "";
					if(teamTeacherList.isEmpty()){
						 teamTeacherName = "无班主任";
					}else{
						TeamTeacher teamTeacher =teamTeacherList.get(0);
						teamTeacherName = teamTeacher.getName();
					}
					team.setClassTeacherName(teamTeacherName);
				}
				grade.setTeamList(teamList);
				gradeList_.add(grade);
			}
			
			mav.addObject("gradeList_",gradeList_);
			mav.setViewName(viewPath);
		}catch(Exception e){
			e.printStackTrace();
			log.info("小学生升级列表异常");
		}
		return mav;
	}
	
}
