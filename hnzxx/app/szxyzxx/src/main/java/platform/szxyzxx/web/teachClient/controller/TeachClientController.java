package platform.szxyzxx.web.teachClient.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.DictClient;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SeatChart;
import platform.education.generalTeachingAffair.model.SeatChartItem;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherDetailInfo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.model.UserDetailInfo;
import platform.education.generalTeachingAffair.service.SeatChartItemService;
import platform.education.generalTeachingAffair.service.SeatChartService;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalTeachingAffair.vo.SeatChartItemVo;
import platform.education.generalTeachingAffair.vo.SeatChartVo;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.school.affair.model.Classroom;
import platform.education.user.model.Profile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.DateUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.utils.JsonWriteUtils;
import framework.generic.dao.Order;
import framework.generic.dao.Page;



/***
 * ??????????????????Controller
 * @author admin
 *
 */

@Controller
@RequestMapping("/teachClient")
public class TeachClientController extends BaseController{
	
	@Autowired
	@Qualifier("seatChartService")
	private SeatChartService seatChartService;

	@Autowired
	@Qualifier("seatChartItemService")
	private SeatChartItemService seatChartItemService;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	/***
	 * ???????????????????????????????????????????????????????????????
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/getGradeAndTeanList")
	@ResponseBody
	public List<Grade> getStudentList(@RequestParam(value = "schoolId", required = true) String schoolId){
		List<Grade> gradeList = teachClientService.findGradeAndTeamInfoBySchool(Integer.parseInt(schoolId));
		return gradeList;
	}
	
	/***
	 * ?????????????????????
	 */
	@RequestMapping("/getDictList")
	@ResponseBody
	public DictClient getDictList(){
		 DictClient dictClient = new DictClient();
		 try{
			 //??????
			 List<Map<String, Object>> xbMapList = jcGcCacheService.findByTableCode("GB-XB");
			 //????????????
			 List<Map<String, Object>> zJXYMapList = jcGcCacheService.findByTableCode("GB-ZJXY");
			 //????????????
			 List<Map<String, Object>> xSLBMapList = jcGcCacheService.findByTableCode("JY-XSLB");
			 //??????
			 List<Map<String, Object>> gJMapList = jcGcCacheService.findByTableCode("GB-GJ");
			 //??????
			 List<Map<String, Object>> mZMapList = jcGcCacheService.findByTableCode("GB-MZ");
			 //????????????
			 List<Map<String, Object>> hKLBMapList = jcGcCacheService.findByTableCode("GB-HKLB");
			 //????????????
			 List<Map<String, Object>> jKZKMapList = jcGcCacheService.findByTableCode("GB-JKZK");
			 //????????????
			 List<Map<String, Object>> xSDQZTMapList = jcGcCacheService.findByTableCode("JY-XSDQZT");
			 //??????
			 List<Map<String, Object>> xXMapList = jcGcCacheService.findByTableCode("JY-XX");
			 //????????????
			 List<Map<String, Object>> sFZJLXMapList = jcGcCacheService.findByTableCode("JY-SFZJLX");
			 //????????????
			 List<Map<String, Object>> zZMMMapList = jcGcCacheService.findByTableCode("GB-ZZMM");
			 //??????
			 List<Map<String, Object>> xlMapList = jcGcCacheService.findByTableCode("GB-XL");
			 //????????????
			 List<Map<String, Object>> jZGDQZTMapList = jcGcCacheService.findByTableCode("JY-JZGDQZT");
			//????????????
			 List<Map<String, Object>> hYZKMapList = jcGcCacheService.findByTableCode("GB-HYZK");
			 //????????????
			 List<Map<String, Object>> cYZKMapList = jcGcCacheService.findByTableCode("GB-CYZK");
			 
			 dictClient.setXbMapList(xbMapList);
			 dictClient.setZJXYMapList(zJXYMapList);
			 dictClient.setgJMapList(gJMapList);
			 dictClient.sethKLBMapList(hKLBMapList);
			 dictClient.setjKZKMapList(jKZKMapList);
			 dictClient.setmZMapList(mZMapList);
			 dictClient.setsFZJLXMapList(sFZJLXMapList);
			 dictClient.setxSDQZTMapList(xSDQZTMapList);
			 dictClient.setxSLBMapList(xSLBMapList);
			 dictClient.setxXMapList(xXMapList);
			 dictClient.setzZMMMapList(zZMMMapList);
			 dictClient.setXlMapList(xlMapList);
			 dictClient.setjZGDQZTMapList(jZGDQZTMapList);
			 dictClient.sethYZKMapList(hYZKMapList);
			 dictClient.setcYZKMapList(cYZKMapList);
		 }catch(Exception e){
			 log.info("?????????????????????????????????");
			 //e.printStackTrace();
		 }
		return dictClient;
	}
	/***
	 * ?????????????????????????????? ??????????????????????????????
	 * @param schoolId
	 * @param teamId
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/getStudentListByGradeIdAndTeamId")
	@ResponseBody
	public List<Student> getStudentListByGradeIdAndTeamId(
			@RequestParam(value = "schoolId", required = true) String schoolId,
			@RequestParam(value = "teamId", required = true) String teamId,
			@RequestParam(value = "gradeId", required = true) String gradeId){
		List<Student> studentList = null;
		try{
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setSchoolId(Integer.parseInt(schoolId));
			studentCondition.setGradeId(Integer.parseInt(gradeId));
			studentCondition.setTeamId(Integer.parseInt(teamId));
			studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
			//System.out.println("studentList:"+studentList.size());
		}catch(Exception e){
			log.info("?????????????????????????????????????????????");
			//e.printStackTrace();
		}
		return studentList;
	}
	
	/**
	 * ??????????????????????????????
	 * 
	 * @param name
	 * @return
	 */
	public String trimName(String name) {
		name = name.trim();
		// ????????????????????????
		name = name.replaceAll(" ", "");
		// ??????????????????????????????
		name = name.replaceAll("???", "");
		return name;
	}
	
	/**
	 * ?????????????????????????????????
	 * @param grade
	 */
	@RequestMapping("/addStudentInfor")
	@ResponseBody
	public String addStudentInfor(
			platform.education.generalTeachingAffair.vo.UserDetailInfoVo userDetailInfoVo,
			@RequestParam(value = "schoolId", required = true) String schoolId,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "teamId", required = true) String teamId){
		try{
			String name = trimName(userDetailInfoVo.getName());
			if(name != "" && !"".equals(name)) {
				userDetailInfoVo.setSchoolId(Integer.parseInt(schoolId));
				userDetailInfoVo.setTeamId(teamId);
				userDetailInfoVo.setGradeId(gradeId);
				teacherService.saveUserInfoFromClient(userDetailInfoVo, SysContants.SYSTEM_APP_ID,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_STUDENT,SysContants.TEACHINGAFFAIR_PARENT_RANK ,SysContants.TEACHINGAFFAIR_PARENT_PARENTRELATION);
			}else {
				//???????????????????????????
				return ResponseInfomation.OPERATION_FAIL;
			}
		}catch(Exception e){
			log.info("???????????????????????????????????????....");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	
	/**
	 * ??????????????????????????????
	 * @param id
	 * @param mav
	 * @return
	 */
	@RequestMapping("/modifyStudentInfor")
	@ResponseBody
	public UserDetailInfo modifyStudentInfor(
			@RequestParam(value = "id", required = true) String id,
			ModelAndView mav){
		UserDetailInfo userDetailInfo = null;
		try{
			userDetailInfo = teacherService.findUserDetailInfoById(id);
			userDetailInfo.setEndDateTemp(DateUtil.dateToStr(userDetailInfo.getEndDate()));
			userDetailInfo.setBirthDateTemp(DateUtil.dateToStr(userDetailInfo.getBirthDate()));
			userDetailInfo.setEnterDateTemp(DateUtil.dateToStr(userDetailInfo.getEnterDate()));
		}catch(Exception e){
			log.info("?????????????????????????????????");
			//e.printStackTrace();
		}
		return userDetailInfo;
	}
	
	/**
	 * ????????????
	 * @param grade
	 * @return
	 */
	@RequestMapping("/updateStudentInfo")
	@ResponseBody
	public String updateStudentInfo(UserDetailInfo userDetailInfo,Boolean isBindingMobile){
		if(isBindingMobile == null){
			isBindingMobile = false;
		}
		try{
			studentService.modfiyUserInfo(userDetailInfo,isBindingMobile);
		}catch(Exception e){
			log.info("?????????????????????????????????");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * ???????????????????????????
	 * @param id
	 */
	@RequestMapping("/deleteStudentInfor")
	@ResponseBody
	public String deleteStudentInfor(@RequestParam(value="id",required=true) String id){
		try{
			teacherService.updateUserDetailInforById(Integer.parseInt(id));
		}catch(Exception e){
			log.info("?????????????????????????????????....");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//????????????????????????
	@RequestMapping("/saveBathUploadStudentInfo")
	@ResponseBody
	public String saveBathUploadStudentInfo(
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="nickName",required=false) String nickName,
			@RequestParam(value="studentNum",required=false) String studentNum,
			@RequestParam(value="sex",required=false) String sex,
			@RequestParam(value="roleId",required=true) String roleId,
			@RequestParam(value="schoolId",required=true) String schoolId,
			@RequestParam(value="gradeId",required=true) String gradeId,
			@RequestParam(value="teamId",required=true) String teamId,
			@RequestParam(value="isBoarded",required=true) String isBoarded,
			@RequestParam(value="parentCellPhone",required=true) String parentCellPhone){
			try{
				//System.out.println("name:"+name+"nickName:"+nickName+"=isBoarded="+isBoarded);
				//String stuName = new String(name.getBytes("iso8859-1"),"UTF-8"); 
				//String nickName_ = new String(nickName.getBytes("iso8859-1"),"UTF-8"); 
 				StudentCondition studentCondition = new StudentCondition();
				studentCondition.setTeamId(Integer.parseInt(teamId));
				studentCondition.setName(name);
				boolean falg = false;
				boolean isEmpt = false;
				if("".equals(nickName) || nickName==""){
					List<Student> studentList = this.studentService.findStudentUniqByCondition(studentCondition, null, null);
					//System.out.println("studentList:"+studentList.size());
					isEmpt = studentList.isEmpty();
				}else{
					falg = true;
				}
				if(falg || isEmpt){
					platform.education.generalTeachingAffair.vo.UserDetailInfoVo userDetailInfoVo = new platform.education.generalTeachingAffair.vo.UserDetailInfoVo();
			    	userDetailInfoVo.setSchoolId(Integer.parseInt(schoolId));
			    	userDetailInfoVo.setGradeId(gradeId);
			    	userDetailInfoVo.setTeamId(teamId);
			    	userDetailInfoVo.setName(name);
			    	userDetailInfoVo.setNickName(nickName);
			    	userDetailInfoVo.setStudentNum(studentNum);
			    	userDetailInfoVo.setSex(sex);
			    	userDetailInfoVo.setRole(roleId);
			    	userDetailInfoVo.setIsBoarded(isBoarded);
			    	userDetailInfoVo.setParentCellPhone(parentCellPhone);
			    	teacherService.saveUserInfoFromClient(userDetailInfoVo, SysContants.SYSTEM_APP_ID,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_STUDENT,SysContants.TEACHINGAFFAIR_PARENT_RANK ,SysContants.TEACHINGAFFAIR_PARENT_PARENTRELATION);
				}else{
					return ResponseInfomation.DATA_REPEAT;
				}
			}catch(Exception e){
				log.info("???????????????????????????????????????");
				e.printStackTrace();
				return ResponseInfomation.OPERATION_FAIL;
			}
		  return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * ????????????????????????????????????
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/getTeacherListBySchoolId")
	@ResponseBody
	public List<TeacherDetailInfo> getTeacherListBySchoolId(
			@RequestParam(value="schoolId",required=true) String schoolId){
		List<TeacherDetailInfo> teacherDeailList = null;
		try {
			teacherDeailList = teacherService.findAllTeacherDetailInfo(Integer.parseInt(schoolId));
		} catch (NumberFormatException e) {
			log.info("??????ID??????");
			//e.printStackTrace();
		} catch (Exception e) {
			log.info("??????????????????????????????????????????");
			//e.printStackTrace();
		}
		return teacherDeailList;
	}
	
	//??????????????????????????????
	@RequestMapping("/saveBathUploadTeacherInfo")
	@ResponseBody
	public String saveBathUploadTeacherInfo(
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="deptName",required=true) String deptName,
			@RequestParam(value="sex",required=true) String sex,
			@RequestParam(value="cardNum",required=false) String cardNum,
			@RequestParam(value="roleId",required=true) String roleId,
			@RequestParam(value="schoolId",required=true) String schoolId,
			@RequestParam(value="mobile",required=true) String mobile){
		try{
			if(mobile != null && mobile != "" && !"".equals(mobile)){
				//String name_ = new String(name.getBytes("iso8859-1"),"UTF-8"); 
				TeacherCondition teacherCondition = new TeacherCondition();
				teacherCondition.setMobile(mobile);
				List<Teacher>  teacherList = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
				if(teacherList.isEmpty()){
					TeacherDetailInfo teacherDetailInfo = new TeacherDetailInfo();
					teacherDetailInfo.setSchoolId(Integer.parseInt(schoolId));
					teacherDetailInfo.setName(name);
					teacherDetailInfo.setSex(sex);
					teacherDetailInfo.setCertificateNum(cardNum);
					teacherDetailInfo.setCertificateType("1");
					teacherDetailInfo.setRole(roleId);
					teacherDetailInfo.setMobile(mobile);
					teacherDetailInfo.setDepartmentName(deptName);
//					this.teachService.addTeacherInfo(teacherDetailInfo, SysContants.SYSTEM_APP_ID);
					this.teacherService.addInfoForTeacher(teacherDetailInfo,null,SysContants.DEFAULT_PASSWORD ,SysContants.SYSTEM_APP_ID,SysContants.USER_TYPE_TEACHER);
				}else{
					return ResponseInfomation.DATA_REPEAT;
				}
			}
		}catch(Exception e){
			log.info("?????????????????????????????????????????????");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		 return ResponseInfomation.OPERATION_SUC;
	}
	
	/***
	 * 
	 * ??????????????????
	 * 
	 **/
	@RequestMapping("/getGradeSubjectList")
	@ResponseBody
	public List<Grade> getGradeSubjectList(
			@RequestParam(value="schoolId",required=true) String schoolId){
		List<SubjectGrade> gradeSubjectList = null;
		List<Grade> gradeList = null;
		try{
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolId(Integer.parseInt(schoolId));
			gradeList = gradeService.findGradeByConditionTemp(gradeCondition, null, null);
			for(Grade grade : gradeList){
				gradeSubjectList = subjectGradeService.findSubjectGradeByGradeCode(Integer.parseInt(schoolId), grade.getCode());
				grade.setGradeSubjectList(gradeSubjectList);
			}
			
		}catch(Exception e){
			log.info("???????????????????????????????????????");
		}
		return gradeList;
	}
	
	//??????????????????????????????
	@RequestMapping("/getTeamTeacherList")
	@ResponseBody
	public List<TeamTeacher> getTeamTeacherList(
			@RequestParam(value="schoolId",required=true) String schoolId,
			@RequestParam(value="teamId",required=true) String teamId){
		List<TeamTeacher> teamTeacherList = null;
		try{
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeamId(Integer.parseInt(teamId));
			teamTeacherCondition.setType(2);//2:????????????
			teamTeacherList =  this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		}catch(Exception e){
			log.info("???????????????????????????????????????????????????");
		}
		return teamTeacherList;
	}
	
	/**
	 * ??????????????????????????????????????????
	 */
	@RequestMapping("/saveTeamAndTeacher")
	@ResponseBody
	public String saveTeamAndTeacher(
			@RequestParam(value="schoolId",required=true) String schoolId,
			@RequestParam(value="teacherId",required=true) String teacherId,
			@RequestParam(value="gradeId",required=true) String gradeId,
			@RequestParam(value="teamId",required=true) String teamId,
			@RequestParam(value="subjectCode",required=true) String subjectCode,
			@RequestParam(value="subjectName",required=true) String subjectName,
			@RequestParam(value="schoolYear",required=true) String schoolYear,
			@RequestParam(value="type",required=true) String type){
		try{
			//String subjectName_ = new String(subjectName.getBytes("iso8859-1"),"UTF-8"); 
			TeamTeacher teamTeacher = new TeamTeacher();
			Teacher teacher = this.teacherService.findTeacherById(Integer.parseInt(teacherId));
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeamId(Integer.parseInt(teamId));
			teamTeacherCondition.setGradeId(Integer.parseInt(gradeId));
			teamTeacherCondition.setSchoolYear(schoolYear);
			if(type=="2" || "2".equals(type)){
				teamTeacherCondition.setSubjectCode(subjectCode);
			}
			teamTeacherCondition.setType(Integer.parseInt(type));
			List<TeamTeacher> teamTeacherList =  teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
			if(teamTeacherList.isEmpty()){
				teamTeacher.setTeamId(Integer.parseInt(teamId));
				teamTeacher.setGradeId(Integer.parseInt(gradeId));
				teamTeacher.setTeacherId(teacher.getId());
				teamTeacher.setName(teacher.getName());
				if(type=="2" || "2".equals(type)){
					teamTeacher.setSubjectCode(subjectCode);	
					teamTeacher.setSubjectName(subjectName);
				}
				teamTeacher.setSchoolYear(schoolYear);
				teamTeacher.setType(Integer.parseInt(type));//1:????????? 2???????????????
				teamTeacher.setCreateDate(new Date());
				teamTeacher.setModifyDate(new Date());
				this.teamTeacherService.add(teamTeacher);
			}else{
				TeamTeacher teamTeacher_ = teamTeacherList.get(0);
				teamTeacher_.setTeamId(Integer.parseInt(teamId));
				teamTeacher_.setGradeId(Integer.parseInt(gradeId));
				teamTeacher_.setTeacherId(teacher.getId());
				teamTeacher_.setName(teacher.getName());
				if(type=="2" || "2".equals(type)){
					teamTeacher_.setSubjectCode(subjectCode);	
					teamTeacher_.setSubjectName(subjectName);
				}
				teamTeacher_.setSchoolYear(schoolYear);
				teamTeacher_.setType(Integer.parseInt(type));//1:????????? 2???????????????
				teamTeacher_.setCreateDate(new Date());
				teamTeacher_.setModifyDate(new Date());
				this.teamTeacherService.modify(teamTeacher_);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("?????????????????????????????????????????????");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
		
	}
	
	/**
	 * ???????????????id??????????????????????????????????????????
	 */
	@RequestMapping(value = "/getAjaxClassRoomList", method = RequestMethod.POST)
	@ResponseBody
	public List<SeatChartVo> getAjaxTeamList(
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user) {
		List<SeatChart> seatChartList = this.seatChartService.findSeatChartOfTeamAndSchool(Integer.parseInt(teamId), user.getSchoolId());
		List<SeatChartVo> SeatChartVoList = new ArrayList<SeatChartVo>();
		SeatChartVo vo = null;
		Classroom classroom = null;
		for(SeatChart seatChart :seatChartList){
			classroom = this.classroomService.findClassroomById(seatChart.getClassroomId());
			if(classroom!=null){
				vo = new SeatChartVo();
				BeanUtils.copyProperties(seatChart, vo);
				List<Map<String,Object>> result = this.jcGcCacheService.findByCondition("JY-JSLX", "value", seatChart.getClassroomType(), null,false);
				if(result.size()==1){
					Map<String,Object> map = result.get(0);
					String name = (String)map.get("name");
					vo.setClassroomTypeName(name);
					SeatChartVoList.add(vo);
				}
			}
		}
		return SeatChartVoList;
	}
	
	/**
	 * ??????????????????????????????????????????
	 */
	@RequestMapping(value = "/getSeatItem", method = RequestMethod.POST)
	@ResponseBody
	public List<SeatChartItemVo> getSeatItem(@RequestParam(value = "seatId", required = true) String seatId,
			@CurrentUser UserInfo user) {
		List<SeatChartItemVo> ItemVoList = this.seatChartItemService.findSeatChartItemVoBySeatId(Integer.parseInt(seatId));
		return ItemVoList;
	}
	
	/**
	 * ??????????????????????????????
	 * ???????????????json
	 * @param user
	 * @param seats
	 * @param seatId
	 * @return
	 */
	@RequestMapping(value = "/saveSeatItem", method = RequestMethod.POST)
	@ResponseBody
	public String addSeatItems(@CurrentUser UserInfo user,
			@RequestParam(value = "seats", required = true) String seats,
			@RequestParam(value = "seatId", required = true) String seatId ) {
		List<SeatChartItem> itemList = this.seatChartItemService.findBySeatId(Integer.parseInt(seatId));
		if(itemList.size()>0){
			this.seatChartItemService.deleteBySeatId(Integer.parseInt(seatId));
		}
		if(seats != null && !"".equals(seats)){
			JSONObject jsonObject = JSONObject.fromObject(seats);
			JSONArray jsonArray = jsonObject.getJSONArray("items");
			SeatChartItem item = null;
			Date date = new Date();
			try {
				for(int i=0; i<jsonArray.size() ;i ++){
					JSONObject json = (JSONObject) jsonArray.get(i);
					Integer position = Integer.parseInt(json.getString("position"));
					Integer studentId = Integer.parseInt(json.getString("studentId"));
				    item = new SeatChartItem();
				    item.setCreateDate(date);
				    item.setModifyDate(date);
				    item.setSchoolId(user.getSchoolId());
				    item.setSeatId(Integer.parseInt(seatId));
				    item.setIsDelete(false);
				    item.setStudentId(studentId);
				    item.setPositionX(position/10);
				    item.setPositionY(position%10);
				    this.seatChartItemService.add(item);
				}
				return ResponseInfomation.OPERATION_SUC;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		return ResponseInfomation.OPERATION_FAIL;
	}
	
	/**
	 * ???????????????????????????????????????
	 * @param seatId
	 * @param teamId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getStudentDoNotHasSeat", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> loadStudentDoNotHasSeat(@RequestParam(value = "seatId", required = true) String seatId,
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user) {
		Integer teamId1 = Integer.parseInt(teamId);
		Integer seatId1 = Integer.parseInt(seatId);
		List<Student> studentList = this.seatChartItemService.findStudentDoNotHasSeatInTeam(teamId1, seatId1);
		return studentList;
	}
	
	public String getValue(String str){
		String strValue="";
		if(str=="???" || "???".equals(str)){
			strValue="1";
		}else if(str=="???" || "???".equals(str)){
			strValue="0";
		}else{
			strValue="0";
		}
		return strValue;
	}
	
	/**
	 * ?????????????????????????????????
	 * ????????????ID?????????????????????????????????????????????????????????????????????????????????
	 * @param request
	 * @param response
	 * @param page
	 * @param order
	 * 2015-11-03 xiemeijie
	 */
	@RequestMapping("/client/getTeamTeacherList")
	@ResponseBody
	public void getTeamTeachers(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		JSONObject json_return = new JSONObject();
		List<TeamTeacherVo> teamTeachrVos = new ArrayList<TeamTeacherVo>();
		String schoolId = request.getParameter("schoolId");
		String teamId = request.getParameter("teamId");
		String usePage = request.getParameter("usePage");
		//usePage ?????????????????????1?????????0?????????
		if(usePage == "0" || usePage.equals("0") || usePage == null || usePage == "") {
			page = null;
		}
		
		List<TeamTeacher> teamTeacherList = null;
		try{
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setSchoolId(Integer.valueOf(schoolId));
			teamTeacherCondition.setTeamId(Integer.parseInt(teamId));
			teamTeacherCondition.setDelete(false);
			teamTeacherCondition.setType(2);//2:????????????
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			teamTeacherList =  this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, page, order);
			Profile profile = null;
			Teacher teacher = null;
			String icon = null;
			String imgUrl = null;  //??????????????????
			if(teamTeacherList.size() > 0) {
				for(TeamTeacher teamTeacher : teamTeacherList) {
					TeamTeacherVo teamTeacherVo = new TeamTeacherVo();
					BeanUtils.copyProperties(teamTeacher, teamTeacherVo);
					//??????????????????
					teacher = teacherService.findTeacherById(teamTeacher.getTeacherId());
					if(teacher != null) {
						profile = this.profileService.findByUserId(teacher.getUserId());
						if(profile != null) {
							icon = profile.getIcon();
							if(icon != null && icon.length() > 0) {
								imgUrl = this.fileService.relativePath2HttpUrlByUUID(icon);
							}
						}
						teamTeacherVo.setUserId(teacher.getUserId());
						teamTeacherVo.setUserName(teacher.getUserName());
						teamTeacherVo.setImgUrl(imgUrl);
						teamTeacherVo.setName(teacher.getName());
						teamTeacherVo.setSex(teacher.getSex());
						teamTeacherVo.setMobile(teacher.getMobile());
						teamTeacherVo.setPosition(teacher.getPosition());
					}
					teamTeachrVos.add(teamTeacherVo);
				}
			}
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", teamTeachrVos);
			JsonWriteUtils.setJson(json_return, response);
		}catch(Exception e){
			log.info("???????????????????????????????????????????????????");
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if(json_return != null) {
				json_return.clear();
			}
		}
	}
	
	
	/**
	 * ?????????????????????????????????
	 * ????????????ID???????????????????????????????????????????????????????????????????????????
	 * @param request
	 * @param response
	 * @param page
	 * @param order
	 * 2015-11-03 xiemeijie
	 */
	@RequestMapping("/client/getStudentList")
	@ResponseBody
	public void getStudentList(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		JSONObject json_return = new JSONObject();
		List<StudentVo> studentVos = new ArrayList<StudentVo>();
		String schoolId = request.getParameter("schoolId");
		String teamId = request.getParameter("teamId");
		String usePage = request.getParameter("usePage");
		//usePage ?????????????????????1?????????0?????????
		if(usePage == "0" || usePage.equals("0") || usePage == null || usePage == "") {
			page = null;
		}
		
		List<Student> studentList = null;
		try{
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setSchoolId(Integer.parseInt(schoolId));
			studentCondition.setTeamId(Integer.parseInt(teamId));
			studentCondition.setIsDelete(false);
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			studentList = this.studentService.findStudentByCondition(studentCondition, page, order);
			Profile profile = null;
			String icon = null;
			String imgUrl = null;  //??????????????????
			if(studentList.size() > 0) {
				for(Student student : studentList) {
					StudentVo studentVo = new StudentVo();
					BeanUtils.copyProperties(student, studentVo);
					profile = this.profileService.findByUserId(student.getUserId());
					if(profile != null) {
						icon = profile.getIcon();
						if(icon != null && icon.length() > 0) {
							imgUrl = this.fileService.relativePath2HttpUrlByUUID(icon);
							studentVo.setImgUrl(imgUrl);
						}
					}
					studentVos.add(studentVo);
				}
			}
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", studentVos);
			JsonWriteUtils.setJson(json_return, response);
		}catch(Exception e){
			log.info("?????????????????????????????????????????????");
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if(json_return != null) {
				json_return.clear();
			}
		}
	}
	
	/**
	 * ?????????????????????????????????
	 * ??????????????????????????????????????????????????????????????????????????????????????????????????????
	 * @param request
	 * @param response
	 * @param page
	 * @param order
	 * 2015-11-03 xiemeijie
	 */
	@RequestMapping("/client/getSchoolList")
	@ResponseBody
	public void getSchoolList(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		JSONObject json_return = new JSONObject();
		List<School> schools = new ArrayList<School>();
		String province = request.getParameter("province");  //????????????
		String city = request.getParameter("city");          //?????????
		String district = request.getParameter("district");  //?????????
		String usePage = request.getParameter("usePage");
		//usePage ?????????????????????1?????????0?????????
		if(usePage == "0" || usePage.equals("0") || usePage == null || usePage == "") {
			page = null;
		}
		try{
			SchoolCondition schoolCondition = new SchoolCondition();
			schoolCondition.setProvince(province);
			schoolCondition.setCity(city);
			schoolCondition.setDistrict(district);
			schoolCondition.setDelete(false);
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			schools = this.schoolService.findSchoolByCondition(schoolCondition, page, order);
			
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", schools);
			JsonWriteUtils.setJson(json_return, response);
		}catch(Exception e){
			log.info("??????????????????????????????????????????????????????");
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if(json_return != null) {
				json_return.clear();
			}
		}
	}
	
	/**
	 * ?????????????????????????????????
	 * ??????????????????????????????????????????????????????????????????????????????
	 * @param request
	 * @param response
	 * @param page
	 * @param order
	 * 2015-11-04 xiemeijie
	 */
	@RequestMapping("/client/getGradeList")
	@ResponseBody
	public void getGradeList(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		JSONObject json_return = new JSONObject();
		List<Grade> grades = new ArrayList<Grade>();
		String schoolId = request.getParameter("schoolId");
		String usePage = request.getParameter("usePage");
		//usePage ?????????????????????1?????????0?????????
		if(usePage == "0" || usePage.equals("0") || usePage == null || usePage == "") {
			page = null;
		}
		try{
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(Integer.valueOf(schoolId));
			String schoolYear = schoolTermCurrent.getSchoolYear();
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolId(Integer.valueOf(schoolId));
			gradeCondition.setDelete(false);
			gradeCondition.setSchoolYear(schoolYear);
//			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			grades = gradeService.findGradeByCondition(gradeCondition, page, null);
//			grades = gradeService.findGradeBySchoolYear(Integer.valueOf(schoolId), schoolYear);
			
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", grades);
			JsonWriteUtils.setJson(json_return, response);
		}catch(Exception e){
			log.info("?????????????????????ID????????????????????????????????????????????????");
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if(json_return != null) {
				json_return.clear();
			}
		}
	}
	
	/**
	 * ?????????????????????????????????
	 * ????????????ID??????????????????????????????
	 * @param request
	 * @param response
	 * @param page
	 * @param order
	 * 2015-11-04 xiemeijie
	 */
	@RequestMapping("/client/getTeamList")
	@ResponseBody
	public void getTeamList(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		JSONObject json_return = new JSONObject();
		List<Team> teams = new ArrayList<Team>();
		String schoolId = request.getParameter("schoolId");
		String gradeId = request.getParameter("gradeId");
		String usePage = request.getParameter("usePage");
		//usePage ?????????????????????1?????????0?????????
		if(usePage == "0" || usePage.equals("0") || usePage == null || usePage == "") {
			page = null;
		}
		try{
			TeamCondition teamCondition = new TeamCondition();
			teamCondition.setSchoolId(Integer.valueOf(schoolId));
			teamCondition.setIsDelete(false);;
			teamCondition.setGradeId(Integer.valueOf(gradeId));
//			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			teams = teamService.findTeamByCondition(teamCondition, page, null);
//			grades = gradeService.findGradeBySchoolYear(Integer.valueOf(schoolId), schoolYear);
			
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", teams);
			JsonWriteUtils.setJson(json_return, response);
		}catch(Exception e){
			log.info("?????????????????????ID??????????????????????????????");
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if(json_return != null) {
				json_return.clear();
			}
		}
	}
	
}
