package platform.szxyzxx.web.teach.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.vo.AutoPlacementInfo;
import framework.generic.dao.Order;

/***
 * 学生自动分班
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/autoPlacement")
public class AutoPlacementController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(AutoPlacementController.class);
	
	/**
	 * 获取未分班的学生信息
	 */
	@RequestMapping("/getNotTeamStudentList")
	public ModelAndView getNotTeamStudentList(@CurrentUser UserInfo user){
		ModelAndView mav = null;
		String viewPath = "/teach/autoPlacement/autoPlacementList";
		try{
			mav = new ModelAndView();
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
			
			//男生所占比量数--无百分号
			String manPercentNot = getPercentNot(studentList01.size(),studentList.size());
			//女生所占比量数--无百分号
			String wonPercentNot = getPercentNot(studentList02.size(),studentList.size());
			
			mav.addObject("noClassStuNum",studentList.size());//未分班部人数
			mav.addObject("manNum",studentList01.size());//男生人数
			mav.addObject("womNum",studentList02.size());//女生人数
			mav.addObject("wonPercent",wonPercent);
			mav.addObject("manPercent",manPercent);
			mav.addObject("studentList", studentList);
			
			mav.addObject("manPercentNot",manPercentNot);
			mav.addObject("wonPercentNot",wonPercentNot);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("学生自动分班获取未分班的学生信息");
		}
		return mav;
	}
	
	/***
	 * 设置分班页面
	 */
	@RequestMapping("/setPlacementPage")
	public ModelAndView setPlacementPage(
			@RequestParam(value="noClassStuNum",required=true) String noClassStuNum,
			@RequestParam(value="manNum",required=true) String manNum,
			@RequestParam(value="womNum",required=true) String womNum,
			@RequestParam(value="wonPercentNot",required=true) String wonPercentNot,
			@RequestParam(value="manPercentNot",required=true) String manPercentNot){
		System.out.println("wonPercent:"+manPercentNot+"manPercent:"+wonPercentNot);
		String viewPath = "/teach/autoPlacement/setPlacement";
		ModelAndView mav = new ModelAndView();
		mav.addObject("noClassStuNum",noClassStuNum);
		mav.addObject("manNum",manNum);
		mav.addObject("womNum",womNum);
		mav.addObject("manPercentNot",manPercentNot);
		mav.addObject("wonPercentNot",wonPercentNot);
		mav.setViewName(viewPath);
		return mav;
	}
	
	
	/**
	 * 计算 并保存自动分班
	 */
	@RequestMapping("/saveAutoPlavement")
	@ResponseBody
	public String saveAutoPlavement(@CurrentUser UserInfo user,
			AutoPlacementInfo autoPlacementInfo){
		try{
			List<Team> teamList = this.teamService.findTeamOfGradeAndSchool(Integer.parseInt(autoPlacementInfo.getGradeId()), user.getSchoolId());
			for(Team team : teamList){
				//男生人数
				List<Student> studentList01 = this.studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"1");
				//女生人数
				List<Student> studentList02 = studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"2");
				//每个班要取多少男生
				int getMenNum = (int) (Integer.parseInt(autoPlacementInfo.getPerTeamNum()) * Float.parseFloat(autoPlacementInfo.getManPercentNot()));
				for(int i=0;i<getMenNum;i++){
					Student student01= studentList01.get(i);
					this.teamService.autoPlacementStudent(String.valueOf(team.getId()), String.valueOf(student01.getId()));
				}
				//每个班要取多少女生
				int getWomNum = (int) (Integer.parseInt(autoPlacementInfo.getPerTeamNum()) * Float.parseFloat(autoPlacementInfo.getWonPercentNot()));
				for(int k=0;k<getWomNum;k++){
					Student student02 = studentList02.get(k);
					this.teamService.autoPlacementStudent(String.valueOf(team.getId()), String.valueOf(student02.getId()));
				}
			}
		}catch(Exception e){
			log.info("计算 并保存自动分班异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 计算 并保存  自动分班
	 * 逻辑：
	 * 1、将男女总数 x 除以 班级数量  是否大于1
	 * 		1.1 大于1 ：分班的时候  每个班级都可以分到人   （用班级作为循环条件的长度）
	 * 		1.2 小于1 ：分班的时候  只有 x个班级分到人（x小于班级数）  （用总人数作为循环条件的长度）
	 * 2、将男生人数b 与 班级数量 比较大小 
	 * 		2.1 男生人数 大于 班级数量  每个班级都可以分到男生   （用班级作为循环条件的长度）
	 * 		2.2 男生人数 小于 班级数量  只有b个班级分到男生     （用男生人数作为循环条件的长度）
	 * 3、将女生人数g 与 班级人数 比较大小
	 * 		3.1 女生人数 大于 班级数量  每个班级都可以分到女生    （用班级作为循环条件的长度）
	 * 		3.2 女生人数 小于 班级数量  只有g个班级分到女生   （用女生人数作为循环条件的长度）
	 */
	@RequestMapping("/saveAutoPlavement1")
	@ResponseBody
	public String saveAutoPlavement1(@CurrentUser UserInfo user,
			AutoPlacementInfo autoPlacementInfo){
		try{
			//男生人数
			List<Student> studentList01 = this.studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"1");
			List<Student> studentList001 = this.studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"1");
			//女生人数
			List<Student> studentList02 = studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"2");
			//所有
			List<Student> studentList03 = studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"");
			List<Student> allStudent = studentService.findStudentIsNotTeamBySchoolId(user.getSchoolId(),"");
			//班级数量
			List<Team> teamList = this.teamService.findTeamOfGradeAndSchool(Integer.parseInt(autoPlacementInfo.getGradeId()), user.getSchoolId());
			
			//学生数量是否大于班级数量  不大于就把学生数量当作班级数量
			int allSize = studentList03.size()/teamList.size() >= 1 ? teamList.size() : studentList03.size();
			
			int flagg = 0;
			int flagb = 0;
			int flagn = 0;
			 
			List<Integer> slist = null;
			
			if(studentList03.size() >0 && teamList.size() >0){
				//获取没有性别数据的学生
				studentList001.addAll(studentList02);
				slist = removeRep(studentList03,studentList001);
				
				//每个班能拿到几个男生
				int getBoyNum = 0;
				if(studentList01.size() > 0){
					getBoyNum = studentList01.size() >= teamList.size()  ? studentList01.size()/teamList.size() : 1;
				}
				
				//每个班级能拿到几个女生
				int getGrilNum = 0;
				if(studentList02.size() > 0){
					getGrilNum = studentList02.size() >= teamList.size()  ? studentList02.size()/teamList.size() : 1;
				}
				
				//每个班级能拿到几个未知性别的学生
				int getNoSexNum = 0;
				if(studentList03.size() > 0){
					getNoSexNum = slist.size() >= teamList.size()  ? slist.size()/teamList.size() : 1;
				}
				
				//是否需要随机分配  需要则将分配的循环 teamList.get(i).getId() 里的 i 换成 number
				
				for(int i = 0; i < allSize; i++){
					
					//将男生进行分配
					for(int b=0;b<getBoyNum;b++){
		//				int number = getRadomNum(allSize);
						if(flagb < studentList01.size()){
							int c =  i*getBoyNum + b ;
							Student student01= studentList01.get(c);
							this.teamService.autoPlacementStudent(String.valueOf(teamList.get(i).getId()), String.valueOf(student01.getId()));
							allStudent = removeStudentById(allStudent, studentList01.get(c).getId());
							flagb = flagb + 1;
						}
					}
					
					//将女生进行分配
					for(int g=0;g<getGrilNum;g++){
						//int number = getRadomNum(allSize);
						if(flagg < studentList02.size()){
							int c =  i*getGrilNum + g ;
							Student student02 = studentList02.get( c );
							this.teamService.autoPlacementStudent(String.valueOf(teamList.get(i).getId()), String.valueOf(student02.getId()));
							allStudent = removeStudentById(allStudent, studentList02.get(c).getId());
							flagg = flagg + 1;
						}
					}
					
					//将未知性别进行分配
					for(int k=0;k<getNoSexNum;k++){
						//int number = getRadomNum(allSize);
						if(flagn < slist.size()){
							int c =  i*getNoSexNum + k ;
//							Student student03 = slist.get(c);
							this.teamService.autoPlacementStudent(String.valueOf(teamList.get(i).getId()), String.valueOf(slist.get(c)));
							allStudent = removeStudentById(allStudent, slist.get(c));
							flagn = flagn + 1;
						}
					}
					
				}
				
				//分完之后剩余未分配的学生
				int noClassStudent = allStudent.size();
				for(int i = 0; i < noClassStudent; i++){
					Student student = allStudent.get(i);
					int number = getRadomNum(allSize);
					this.teamService.autoPlacementStudent(String.valueOf(teamList.get(number).getId()), String.valueOf(student.getId()));
				}
				
			}
		}catch(Exception e){
			log.info("计算 并保存自动分班异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	public String getPercentNot(int x,int y){
		String baifenbi="";//接受百分比的值  
		 double baiy=x*1.0;  
		 double baiz=y*1.0;  
		 double fen=baiy/baiz;
		 baifenbi = String.valueOf(fen);
		return baifenbi;
	}
	
	public String getPercent(int x,int y){
		String baifenbi="";//接受百分比的值  
		 double baiy=x*1.0;  
		 double baiz=y*1.0;  
		 double fen=baiy/baiz;
		// System.out.println("fen:"+fen);
		 DecimalFormat df1 = new DecimalFormat("##.0%");//##.00%   百分比格式，后面不足2位的用0补齐  
		 baifenbi= df1.format(fen);
		 if(y==0){
			 baifenbi = "0.0%";
		 }else if(x==0){
			 baifenbi = "0.0%";
		 }
		return baifenbi;
	}
	
	/**
	 * 获取年级
	 */
	@RequestMapping("/getGradeListByStageCode")
	@ResponseBody
	public Grade getGradeListByStageCode(
			@CurrentUser UserInfo user,
			@RequestParam(value="stage_",required=true) String stage_,
			@RequestParam(value="noClassStuNum",required=true) String noClassStuNum){
		Grade grade = null;
		try{
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolId(user.getSchoolId());
			gradeCondition.setSchoolYear(user.getSchoolYear());
			gradeCondition.setStageCode(stage_);
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, Order.asc("uni_grade_code"));
			if(!gradeList.isEmpty()){
				grade = gradeList.get(0);
				//System.out.println("grade:"+grade.getId());
				//班级数量
				List<Team> teamList = this.teamService.findTeamOfGradeAndSchool(grade.getId(),user.getSchoolId());
				String teamNum = "0";
				if(!teamList.isEmpty()){
					teamNum = teamList.size()+"";
				}
				grade.setTeamCount(Integer.parseInt(teamNum));
				int perTeamNum = 0;
				int lastNum = 0;
				if(teamNum!="0" || !"0".equals(teamNum)){
					//班级数量
					Integer teamNum_ = Integer.parseInt(teamNum);
					//System.out.println("teamNum_:"+teamNum_);
					//学生总人数
					Integer noClassStuNum_ = Integer.parseInt(noClassStuNum);
					//System.out.println("noClassStuNum_:"+noClassStuNum_);
					//每个班多少人
					perTeamNum = noClassStuNum_/teamNum_;
					//System.out.println("perTeamNum:"+perTeamNum);
					int bb = teamNum_*perTeamNum;
					//剩余多少人
					lastNum = noClassStuNum_ - bb;
					//System.out.println("lastNum:"+lastNum);
				}
				grade.setPerTeamNum(perTeamNum);
				grade.setLastNum(lastNum);
			}else{
				grade = new Grade();
				grade.setFullName("");
			}
			
		}catch(Exception e){
			//e.printStackTrace();
			log.info("获取年级异常");
		}
		return grade;
	}
	
	public List<Integer> removeRep(List<Student> allStudent,List<Student> student){
		List<Integer> stuIdList = new ArrayList<Integer>();
		List<Integer> stuIdList1 = new ArrayList<Integer>();
		if(allStudent.size() > 0){
			for(Student stu : student){
				stuIdList.add(stu.getId());
			}
			
			for(Student stu1 : allStudent){
				stuIdList1.add(stu1.getId());
			}
			
			stuIdList1.removeAll(stuIdList);
			
		}
		return stuIdList1;
	}
	
	public List<Student> removeStudentById(List<Student> allStudent,Integer studentId){
		for(int i = 0; i < allStudent.size(); i++){
			if(allStudent.get(i).getId().equals(studentId)){
				allStudent.remove(i);
				break;
			}
		}
		return allStudent;
	}
	
	public static Integer getRadomNum(Integer classNum){
		int number = 0;
		number = (int) (Math.random()*classNum) ;
		if(number > classNum){
			getRadomNum(classNum);
		}
		return number;
	}
	
}
