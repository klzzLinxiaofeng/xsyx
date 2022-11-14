package platform.szxyzxx.web.teach.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
/**
 * 学生分班管理
 * @author zhoujin
 * 2015-4-28
 */
@Controller
@RequestMapping("/teach/placement")
public class PlacementController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(PlacementController.class);
	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/studentPlacement")
	public ModelAndView getStudentList(@CurrentUser UserInfo user){
		ModelAndView mav = null;
		String viewPath = "/teach/placement/studentPlacement";
		try{
			mav = new ModelAndView();
			//StudentCondition studentCondition0 = new StudentCondition();
			School school = this.schoolService.findSchoolById(user.getSchoolId());
			SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			List<Team> teamList = this.teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), schoolTermCurrent.getSchoolYear());
			
			//未分班的学生
			List<Student> studentList = this.studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"");
			//男生人数
			List<Student> studentList01 = this.studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"1");
			//男生所占比例
			String manPercent = getPercent(studentList01.size(),studentList.size());
			//女生人数
			List<Student> studentList02 = studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"2");
			//女生所占比例
			String wonPercent = getPercent(studentList02.size(),studentList.size());
			
			mav.addObject("noClassStuNum",studentList.size());//未分班部人数
			mav.addObject("manNum",studentList01.size());//男生人数
			mav.addObject("womNum",studentList02.size());//女生人数
			mav.addObject("wonPercent",wonPercent);
			mav.addObject("manPercent",manPercent);
			mav.addObject("schoolName", school.getName());
			mav.addObject("teamList",teamList);
			mav.addObject("studentList", studentList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询学生分页列表异常...");
			//e.printStackTrace();
		}
		return mav;
	}
	
	public String getPercent(int x,int y){
		String baifenbi="";//接受百分比的值  
		 double baiy=x*1.0;  
		 double baiz=y*1.0;  
		 double fen=baiy/baiz;
		 DecimalFormat df1 = new DecimalFormat("##.0%");    //##.00%   百分比格式，后面不足2位的用0补齐  
		 baifenbi= df1.format(fen);
		 if(y==0){
			 baifenbi = "0.0%";
		 }else if(x==0){
			 baifenbi = "0.0%";
		 }
		return baifenbi;
	}
	
	/**
	 * 添加分班信息页面
	 * @param user
	 * @return
	 */
	@RequestMapping("/placementStudentPage")
	public ModelAndView placementStudentPage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "studentId", required = true) String studentId){
		ModelAndView mav = null;
		String viewPath="/teach/placement/addPlacementPage";
		try{
			mav = new ModelAndView();
			mav.addObject("studentId", studentId);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("添加分班信息页面异常...");
			//e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 添加学生到班级里
	 */
	@RequestMapping("/addPlacementStudent")
	@ResponseBody
	public String addPlacementStudent(
			@CurrentUser UserInfo user,
			@RequestParam(value = "teamId", required = true) String teamId,
			@RequestParam(value = "studentId", required = true) String studentId
			){
		try{
			this.teamService.addPlacementStudent(teamId,studentId);
			//更新每个班级的人数
			studentService.upTeamStudentByTeamId();
		}catch(Exception e){
			log.info("分班异常");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 已分好班列表
	 * @param teamId
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/teamStudentList", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> teamStudentList(
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user)throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<StudentVo> studentVoList = new ArrayList<StudentVo>();
		TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
		try{
			teamStudentCondition.setTeamId(Integer.parseInt(teamId));
			teamStudentCondition.setFinishDate(new Date());
			List<TeamStudent> teamStudentList =	this.teamStudentService.findTeamStudentByConditionForTransfer(teamStudentCondition, null, null);
			//总人数
			int allStuNum = teamStudentList.size();
			//男生人数
			int manNum = 0;
			//女生人数
			int wonNum = 0;
			for(TeamStudent teamStudent : teamStudentList){
				StudentVo studentVo = new StudentVo();
				//System.out.println("teamStudent.getStudentId():"+teamStudent.getStudentId());
				Student student = this.studentService.findStudentById(teamStudent.getStudentId());
				if(student != null){
					studentVo.setId(student.getId());
					studentVo.setName(student.getName());
					studentVo.setStudentNumber(student.getStudentNumber());
					String sex = "";
					if(student.getSex()=="1" || "1".equals(student.getSex())){
						sex="男";
						manNum = ++manNum;
					}else if(student.getSex()=="2" || "2".equals(student.getSex())){
						sex="女";
						wonNum = ++wonNum;
					}
					studentVo.setSex(sex);
					studentVoList.add(studentVo);
				}
			}
			//男生百分比
			String manPercent = getPercent(manNum,allStuNum);
			//女生百分比
			String wonPercent = getPercent(wonNum,allStuNum);
			//System.out.println("studentVoList:"+studentVoList.size()+"=allStuNum="+allStuNum+"=manNum="+manNum+"=wonNum="+wonNum+"=manPercent="+manPercent+"=wonPercent:"+wonPercent);
			map.put("studentVoList", studentVoList);
			map.put("allStuNum", allStuNum);
			map.put("manNum", manNum);
			map.put("wonNum", wonNum);
			map.put("manPercent", manPercent);
			map.put("wonPercent", wonPercent);
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询已分好班学生信息");
		}
		return map;
	}


}
