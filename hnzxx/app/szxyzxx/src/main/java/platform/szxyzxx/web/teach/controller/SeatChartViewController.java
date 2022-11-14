package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.SeatChartItemService;
import platform.education.generalTeachingAffair.service.SeatChartService;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping("/teach/seatChartView")
public class SeatChartViewController extends BaseController{
	
	private final static String viewBasePath = "/teach/seatChartView";
	
	@Autowired
	@Qualifier("seatChartService")
	private SeatChartService seatChartService;
	
	@Autowired
	@Qualifier("seatChartItemService")
	private SeatChartItemService seatChartItemService;
	
	@RequestMapping(value = "/teacher")
	public ModelAndView teacherIndex(@CurrentUser UserInfo user,Model model){
		String viewPath = null;
		viewPath = structurePath("/teacherViewSeat");
		List<TeamTeacherVo> teamTeacherList = null;
		if(user.getTeacherId() != null){
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeacherId(user.getTeacherId());
			teamTeacherCondition.setSchoolYear(user.getSchoolYear());
			teamTeacherList = this.teamTeacherService.findTeamTeacherVoByCondition(teamTeacherCondition);
			teamTeacherList = distinctTeacher(teamTeacherList);
		}
		model.addAttribute("teamTeacherList",teamTeacherList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/student")
	public ModelAndView studentIndex(@CurrentUser UserInfo user,Model model){
		String viewPath = null;
		viewPath = structurePath("/stuViewSeat");
		Student stu = this.studentService.findStudentById(user.getStudentId());
		model.addAttribute("stu",stu);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/**
	 * 根据teamId去重
	 * @param tList
	 * @return
	 */
	
	public static List<TeamTeacherVo> distinctTeacher(List<TeamTeacherVo> tList){
		List<TeamTeacherVo> list = new ArrayList<TeamTeacherVo>();
		for(int i=0;i<tList.size();i++){
			boolean flag = true;
			for(TeamTeacherVo t2 : list){
				if(tList.get(i).getTeamId().equals(t2.getTeamId())){
					flag = false;
				}
			}
			if(flag){
				list.add(tList.get(i));
			}
		}
		return list;
	}
}
