package platform.szxyzxx.web.schoolaffair.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.DormitoryPerson;
import platform.education.school.affair.vo.DormitoryPersonCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 宿舍人员ajax请求
 * @author huangyanchun
 *
 */
@Controller
@RequestMapping("/schoolaffair/dormitoryPerson/ajax")
public class DormitoryPersonAjaxController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(DormitoryPersonAjaxController.class);
	
	
	/**
	 * 当前宿舍下的学生信息
	 * @param user
	 * @param floorCode
	 * @param dormitoryCode
	 * @return
	 */
	@RequestMapping("/currentDormitoryStudent")
	@ResponseBody
	public Map<String,Object>currentDormitoryStudent(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = true) String year,
			@RequestParam(value = "floorCode", required = true) String floorCode,
			@RequestParam(value = "dormitoryCode", required = true) String dormitoryCode){
		    Integer schoolId = user.getSchoolId();
			Map<String,Object> map = new HashMap<String,Object>();
			Dormitory dormitory = new Dormitory();
			dormitory.setFloorCode(floorCode);
			dormitory.setSchoolId(schoolId);
			dormitory.setDormitoryCode(dormitoryCode);
			
			//根据year得到学年的id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(year);
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			
			DormitoryPersonCondition personCondition = new DormitoryPersonCondition();
			personCondition.setSchoolYearId(sy.getId());
			personCondition.setSchoolId(schoolId);
			personCondition.setDormitoryId(this.dormitoryService.findByDormitory(dormitory)==null?-1:this.dormitoryService.findByDormitory(dormitory).getId());
			personCondition.setDormitoryCode(dormitoryCode);
			
		try {
			List<DormitoryPerson>personList = this.dormitoryPersonService.findDormitoryPersonByCondition(personCondition);
			Dormitory dor = this.dormitoryService.findDormitory(dormitory);
			map.put("size", personList.size());
			map.put("personList",personList);
			map.put("dormitory", dor);
		} catch (Exception e) {
               e.printStackTrace();
		}
		return map;
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
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "teamId", required = true) String teamId,
			@RequestParam(value = "selectSex", required = true) String selectSex){
		Map<String,Object> map = new HashMap<String,Object>();
		
		//当前班下的学生已经分配了宿舍的学生住宿信息
		DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
		dpCondition.setIsDeleted(0);
		dpCondition.setTeamNumber(Integer.parseInt(teamId));
		List<DormitoryPerson>dpList = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition);
	
		try{
			
			StudentCondition stuCondition = new StudentCondition();
			stuCondition.setSchoolId(user.getSchoolId());
			stuCondition.setIsDelete(false);
			stuCondition.setTeamId(Integer.parseInt(teamId));
			
			//根据选中的班级得到的没有去重的所有的学生信息
			List<Student>studentList = this.studentService.findStudentByCondition(stuCondition, null, null);
			//根据选中的班级得到的去重后的所有的学生信息
			List<Student>studentLists = new ArrayList<Student>();
			studentLists = distinctStudentOfTeamId(studentList,dpList);
			studentLists = distinctStudentOfStudentId(studentLists);
			
			stuCondition.setSex(selectSex);
			//根据下拉框选中的性别得到的没有去重的学生信息
			List<Student>stuLists= this.studentService.findStudentByCondition(stuCondition, null, null);
			//根据下拉框选中的性别得到的去重后的学生信息
			List<Student> stuList = new ArrayList<Student>();
			stuList = distinctStudentOfTeamId(stuLists,dpList);
			stuList = distinctStudentOfStudentId(stuList);
			
			StudentCondition stuCondition1 = new StudentCondition();
			stuCondition1.setSchoolId(user.getSchoolId());
			stuCondition1.setIsDelete(false);
			stuCondition1.setTeamId(Integer.parseInt(teamId));
			//如果下拉框选中的性别是男的则把是女的条件设置进去，如果是女的则把男的设置进去
			if(selectSex.equals("1")){
				stuCondition1.setSex("2");
			}else if(selectSex.equals("2")){
				stuCondition1.setSex("1");
			}
			
			
			//计算没有去重之前男女下拉框中没有选中的那个性别的学生人数
			List <Student>stuList11 = this.studentService.findStudentByCondition(stuCondition1, null, null);
			//计算去重之后男女下拉框中没有选中的那个性别的学生人数
			List<Student>stuList1 = new ArrayList<Student>();
			stuList1 = distinctStudentOfTeamId(stuList11,dpList);
			stuList1 = distinctStudentOfStudentId(stuList1);
			
			
			
			//根据性别查询到的人数
			int sexStuNum = stuList.size();
			
			//下拉框没有选中的那个性别的的人数
			int otherSexStuNum = stuList1.size();
			
			//总人数
			int allStuNum = studentLists.size();
			
			
			map.put("stuList", stuList);	
			map.put("allStuNum", allStuNum);
			map.put("sexStuNum", sexStuNum);
			map.put("otherSexStuNum", otherSexStuNum);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	  return map;
	}
	
	/**
	 * 判断所搬出的学生是否属于这个班级
	 * @param studentId1
	 * @param teamId
	 * @param user
	 * @return
	 */
	@RequestMapping("/checkStudentIn")
	@ResponseBody
	public List<String>checkStudentIn(@RequestParam(value = "studentId1", required = true) String studentId1,
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user){
		List<String>checkStudent = new ArrayList<String>();
		String isStudent = "";
		String name = "";
		try {
			String [] studentId1_ = studentId1.split(",");
			boolean flag = studentId1_[0]=="";
			if(!flag){
				Map<String,String>studentIdMaps = new HashMap<String, String>();
				for(int i=0;i<studentId1_.length;i++){
					studentIdMaps.put(studentId1_[i], studentId1_[i]);
				}
				
				for(String stId:studentIdMaps.keySet()){
					// 获取该学校当前学年
					//根据学生id在宿舍人员表中查找对应的信息
					SchoolTermCurrent stc = this.schoolTermCurrentService
							.findSchoolTermCurrentBySchoolId(user.getSchoolId());

					Team team = this.teamService.findTeamById(Integer.parseInt(teamId));
					
					TeamStudentCondition tsCondition = new TeamStudentCondition();
					tsCondition.setSchoolId(user.getSchoolId());
					tsCondition.setSchoolYear(stc.getSchoolYear());
					tsCondition.setGradeId(team.getGradeId());
					tsCondition.setTeamId(team.getId());
					tsCondition.setStudentId(Integer.parseInt(stId));
					
					List<TeamStudent>tsList = this.teamStudentService.findCurrentTeamStudentByCondition(tsCondition, null, null);
					if(tsList.size()==0){
						
						Student s = this.studentService.findStudentById(Integer
								.parseInt(stId));
						name += s.getName() + ",";
					}
				}
				
				if(name==""){
					isStudent= ResponseInfomation.OPERATION_FAIL;
				}else{
					isStudent = ResponseInfomation.OPERATION_SUC;
				}
				
				checkStudent.add(name+isStudent);
			}
		} catch (Exception e) {
			log.info("该学生不存在..");
			e.printStackTrace();
			return checkStudent;
		}
		
		
		return checkStudent;
	}
	
	
	
	/**
	 * @function 根据studentId 去掉List<Student> 中的重复值
	 * @param List<Student>
	 * @return List<Student>
	 */
	
	public static List<Student> distinctStudentOfTeamId(List<Student> stuList,List<DormitoryPerson>dpList){
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++){
			boolean flag = true;
			for(DormitoryPerson dp : dpList){
				if(dp.getStudentId().equals(stuList.get(i).getId())){
					flag = false;
				}
			}
			if(flag){
				list.add(stuList.get(i));
			}
		}
		return list;
	}
	
	
	/**
	 * @function 根据studentId 去掉List<Student> 中的重复值
	 * @param List<Student>
	 * @return List<Student>
	 */
	public static List<Student> distinctStudentOfStudentId(List<Student> stuList){
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++){
			boolean flag = true;
			for(Student stu2 : list){
				if(stuList.get(i).getUserId().equals(stu2.getUserId())){
					flag = false;
				}
			}
			if(flag){
				list.add(stuList.get(i));
			}
		}
		return list;
	}
	
	
}
