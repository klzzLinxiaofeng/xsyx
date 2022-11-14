package platform.szxyzxx.web.teach.controller;

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

/***
 * 学生调班
 * @author zhoujin
 * 2015-5-11
 *
 */

@Controller
@RequestMapping("/teach/transfer")
public class TransferClassesController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(TransferClassesController.class);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/transferClass")
	public ModelAndView getStudentList(@CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();;
		String viewPath = "/teach/transfer/transferClass";
		try{
			School school = this.schoolService.findSchoolById(user.getSchoolId());
			SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			List<Team> teamList = this.teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), schoolTermCurrent.getSchoolYear());
			
			mav.addObject("schoolName", school.getName());
			mav.addObject("teamList",teamList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("学生调班列表异常...");
			//e.printStackTrace();
		}
		return mav;
	}
	
	/****
	 * 调往班级列表
	 */
	@RequestMapping("/transferToClassTeamList")
	@ResponseBody
	public List<Team> transferToClassTeamList(@CurrentUser UserInfo user,
			@RequestParam(value = "teamId", required = true) String teamId){
		List<Team> teamListTemp = null;
		try{
			SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			teamListTemp = this.teamService.findCurrentTeamOfSchoolAndYearNotExistCurrentClass(user.getSchoolId(), schoolTermCurrent.getSchoolYear(),Integer.parseInt(teamId));
		}catch(Exception e){
			log.info("调往班级列表异常...");
			//e.printStackTrace();
		}
		return teamListTemp;
	}
	
	
	/**
	 * 当前班下的学员信息
	 * @param user
	 * @param teamId
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping("/currentTeamStudent")
	@ResponseBody
	public Map<String,Object> currentTeamStudent(
			@CurrentUser UserInfo user,
			@RequestParam(value = "teamId", required = true) String teamId){
		Map<String,Object> map = new HashMap<String,Object>();
		List<StudentVo> studentVoList = new ArrayList<StudentVo>();
		TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
		try{
			Team team = this.teamService.findTeamById(Integer.parseInt(teamId));
			teamStudentCondition.setTeamId(Integer.parseInt(teamId));
			teamStudentCondition.setGradeId(team.getGradeId());
			teamStudentCondition.setFinishDate(new Date());
			List<TeamStudent> teamStudentList = this.teamStudentService.findTeamStudentByConditionForTransfer(teamStudentCondition, null, null);
			//总人数
			int allStuNum = teamStudentList.size();
			//男生人数
			int manNum = 0;
			//女生人数
			int wonNum = 0;
			for(TeamStudent teamStudent : teamStudentList){
				StudentVo studentVo = new StudentVo();
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
			log.info("----当前班下的学员信息查询异常----");
			//e.printStackTrace();
		}
	  return map;
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
	 * 保存调班后的学生信息
	 * 2016-12-28
	 * @param user
	 * @param teamId0
	 * @param studentId0
	 * @param teamId1
	 * @param studentId1
	 * @return
	 */
	@RequestMapping("/saveOrUpdateStudentInfo")
	@ResponseBody
	public String saveOrUpdateStudentInfo(
			@RequestParam(value = "teamId0", required = true) Integer teamId0,
			@RequestParam(value = "studentId0", required = true) String studentId0,
			@RequestParam(value = "teamId1", required = true) Integer teamId1,
			@RequestParam(value = "studentId1", required = true) String studentId1){
		try{
			
			Boolean teamId0IsNull = teamId0 == null || "".equals(teamId0);
//			Boolean studentId0IsNull = studentId0 != null || !"".equals(studentId0);
			Boolean teamId1IsNull = teamId1 == null || "".equals(teamId1);
//			Boolean studentId1IsNull = studentId1 != null || !"".equals(studentId1);
			
			if(!teamId0IsNull && !teamId1IsNull) {
				if(!"".equals(studentId0)) {
					String[] studentIds0 = studentId0.split(",");
					for(String stuId0 : studentIds0) {
						teamStudentService.moveStudentToTeam(Integer.valueOf(stuId0), teamId1, teamId0);
					} 
				}
				
				if(!"".equals(studentId1)) {
					String[] studentIds1 = studentId1.split(",");
					for(String stuId1 : studentIds1) {
						teamStudentService.moveStudentToTeam(Integer.valueOf(stuId1), teamId0, teamId1);
					}
				}
			}
			
//			teachService.addOrUpdateStudent(teamId0,studentId0,teamId1,studentId1);
//			//更新每个班级的人数
//			teachService.upTeamStudentByTeamId();
		}catch(Exception e){
			e.printStackTrace();
			log.info("保存调班后的学生信息异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
}
