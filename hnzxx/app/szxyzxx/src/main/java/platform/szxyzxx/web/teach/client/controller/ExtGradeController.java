package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import framework.generic.cache.redis.core.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.contants.TeamTeacherContants;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ExtImportStudentOfInSchoolVo;
import platform.education.generalTeachingAffair.vo.ExtImportStudentVo;
import platform.education.generalTeachingAffair.vo.ExtImportTeacherErrorMsg;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.ExtGradeTeamVo;
import platform.szxyzxx.web.teach.client.vo.ExtGradeTeamsStudentsVo;
import platform.szxyzxx.web.teach.client.vo.ExtImportErrorMsg;
import platform.szxyzxx.web.teach.client.vo.ExtStudentVo;
import platform.szxyzxx.web.teach.client.vo.ExtTeamTeacherVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * @function 级务
 * @date 2016-2-18
 * @author panfeiExtGradeController
 *
 */

@Controller
@RequestMapping("/school/grade")
public class ExtGradeController extends BaseController{
	private Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * @function 根据年级获取年级下的所有班级以及教师的一些信息
	 * @param gradeId
	 * @date 2016-2-18
	 * @return
	 */
	@RequestMapping("/teacher/list")
	@ResponseBody
	public Object getTeacherByGrade(HttpServletRequest request,@RequestParam(value = "gradeId", required = false) Integer gradeId){
		//存放年级信息
		List<ExtGradeTeamVo> gradeTeamVoList = new ArrayList<ExtGradeTeamVo>();
		
		ExtGradeTeamVo gradeTeamVo = null;
		//存放班级所有教师信息
		List<ExtTeamTeacherVo> teamTeacherVoList = null;
		
		//存放班级某个教师信息
		ExtTeamTeacherVo teamTeacherVo = null;
		
		//默认的头像位置
		String defaultUserIcon = request.getServletContext().getRealPath("/") + "res/images/no_pic.jpg";
		
		//判断是否传参
		if(gradeId == null || "".equals(gradeId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("gradeId");
			return new ResponseError("060111", info);
		}
		
		
		try{
			//根据年级获取年级下的所有班级
			List<Team> teamList = this.teamService.findTeamOfGrade(gradeId);
			
			if(teamList != null && teamList.size() > 0){
				for(Team team : teamList){
					//单个班级下的所有教师
//					List<TeamTeacher> teamTeacherList = teamTeacherService.findClassTeacherByGradeIdAndTeamId(gradeId, team.getId());
					TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
					teamTeacherCondition.setTeamId(team.getId());
					teamTeacherCondition.setGradeId(gradeId);
					teamTeacherCondition.setDelete(false);
					List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
					teamTeacherVoList = new ArrayList<ExtTeamTeacherVo>();
					if(teamTeacherList != null && teamTeacherList.size() > 0){
						for(TeamTeacher teamTeacher : teamTeacherList){
							Teacher teacher = teacherService.findTeacherById(teamTeacher.getTeacherId());
							if(teacher != null){
								String userIcon =  getUserIcon(teacher.getUserId());
								//如果用户没有头像  将默认头像赋给当前教师
								if("".equals(userIcon)) {
									userIcon = defaultUserIcon;
								}
								teamTeacherVo = new ExtTeamTeacherVo();
								teamTeacherVo.setId(teacher.getUserId());
								teamTeacherVo.setName(teacher.getName());
								teamTeacherVo.setSex(teacher.getSex());
								teamTeacherVo.setSubjectCode(teamTeacher.getSubjectCode());
								teamTeacherVo.setSubjectName(teamTeacher.getSubjectName());
								teamTeacherVo.setUserIcon(userIcon);
								teamTeacherVo.setType(teamTeacher.getType()+"");
								
								teamTeacherVoList.add(teamTeacherVo);
							}
						}
					}
					gradeTeamVo = new ExtGradeTeamVo();
					gradeTeamVo.setTeamId(team.getId());
					gradeTeamVo.setTeamName(team.getName());
					gradeTeamVo.setTeachers(teamTeacherVoList);
					gradeTeamVoList.add(gradeTeamVo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取年级下的班级教师信息失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取年级下的班级教师信息异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		
		return new ResponseVo<List<ExtGradeTeamVo>>("0", gradeTeamVoList);
	}
	
	public String getUserIcon(Integer userId){
		//默认的头像位置
		String userIcon = "";
		
		//获取头像
		Profile profile = this.profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			if(icon != null) {
				FileResult file = this.fileService.findFileByUUID(icon);
				if(file != null) {
					userIcon = file.getHttpUrl();
				}
			}
		}
		
		return userIcon;
	}
	
	/**
	 * 功能描述：导入全年级任课教师和班主任
	 * @date 2016-03-03
	 * @param gradeId
	 * @param dataJson
	 * @return
	 */
	@RequestMapping("/teacher/import")
	@ResponseBody
	public Object teacherImport(
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "data", required = false) String dataJson) {
		
		try {
			
			if(gradeId == null || "".equals(gradeId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}
			
			Integer schoolId = null;
			Grade grade = gradeService.findGradeById(gradeId);
			if(grade != null) {
				 schoolId = grade.getSchoolId();
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("所填参数gradeId找不到对应的年级");
				info.setMsg("没有对应的年级");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}
			
			//从SchoolUser表获取学校所有在校教师
//			List<SchoolUser> schoolUserList = schoolUserService.findSchoolUserOfTeacher(schoolId);
			
			Integer groupId = null;
			Group group = this.groupService.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
			if(group != null) {
				groupId = group.getId();
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("gradeId对应的学校没有对应的组...");
				info.setMsg("所在学校找不到对应的组");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}
			
			//用于存放错误信息
			List<ExtImportErrorMsg> msgList = new ArrayList<ExtImportErrorMsg>();
			
			//解析JSON数据
			JSONArray data = JSONArray.fromObject(dataJson);
			if(data != null && data.size() > 0){
				for(int i = 0; i < data.size(); i++){
					JSONObject jsonJ = data.getJSONObject(i);
					Integer teamId = jsonJ.getInt("teamId");
					JSONArray jsonForTeacher = jsonJ.getJSONArray("teachers");
					
					//2017-3-7 用于删除原表中的科目教师
					List<TeamTeacher> teamTeacherList = this.teamTeacherService.findClassTeacherByGradeIdAndTeamId(gradeId, teamId);
					
					if(jsonForTeacher != null && jsonForTeacher.size() > 0) {
						for(int j = 0; j < jsonForTeacher.size(); j++) {
							Integer userId;
							JSONObject jsonLessons = jsonForTeacher.getJSONObject(j);
							TeamTeacher teamTeacher = new TeamTeacher();
							String type = jsonLessons.getString("type");
							String strUserId = jsonLessons.getString("id");
							String subjectCode = jsonLessons.getString("subjectCode");
							
							teamTeacher.setTeamId(teamId);
							teamTeacher.setGradeId(gradeId);
							
							//2016-3-16 添加  科目名称  另：因为类型和教师ID是必填的 如果客户端发过来的这两个没有值或者不是数据类型  将会跳过  继续下一个人  防止客户端发空数据
							String subjectName = jsonLessons.getString("subjectName");
							teamTeacher.setSubjectName(subjectName);
							if(type == null || "".equals(type) || !isInteger(type) || strUserId == null || "".equals(strUserId) || !isInteger(strUserId)){
								continue;
							}else{
								userId = Integer.valueOf(strUserId);
								teamTeacher.setUserId(userId);
							}
							
							if("".equals(type)) {
								teamTeacher.setType(null);
							}else {
								teamTeacher.setType(Integer.valueOf(type));
							}
							
							if("".equals(subjectCode)) {
								teamTeacher.setSubjectCode(null);
							}else {
								teamTeacher.setSubjectCode(subjectCode);
							}
							
							//用于存放该教师的错误信息，用于返回给客户端
							ExtImportErrorMsg msg = new ExtImportErrorMsg();
							
							Teacher teach = this.teacherService.findOfUser(schoolId, userId);
							
							if(teach == null) {
								//查询相关教师信息
								msg.setId(userId);
								msg.setMsg("找不到该教师相关信息");
							}else {
								//该教师信息存在，获取其ID
								teamTeacher.setTeacherId(teach.getId());
								
								if("".equals(type) || "".equals(userId)) {
									//返回错误信息
									msg.setId(userId);
									msg.setMsg("必填参数为空");
								}else {
									
									String message = "";
									
									//判断输入的用户是教师类型还是班主任类型
									if(Integer.valueOf(type).equals(TeamTeacherContants.TYPE_SUBJECT_TEACHER)) {
										if(subjectCode == null && "".equals(subjectCode)) {
											 //返回错误信息
											msg.setId(userId);
											msg.setMsg("必填参数为空");
										 }else {
											 //任课教师的添加操作
											 message = teamTeacherService.addOrModifyClassRoomTeacherOfTeam(SysContants.SYSTEM_APP_ID, groupId, teamTeacher);
										 }
									}else if(Integer.valueOf(type).equals(TeamTeacherContants.TYPE_MASTER)) {
										//班主任的添加操作
										message = teamTeacherService.addOrModifyClassTeacher(SysContants.SYSTEM_APP_ID, groupId, String.valueOf(gradeId), String.valueOf(teamId), String.valueOf(teach.getId()));
									}
									
									if("".equals(message) || "error".equals(message)) {
										msg.setId(userId);
										msg.setMsg("系统添加失败");
									}
									
									
									//从删除列表中去掉
									if(teamTeacherList != null && teamTeacherList.size() > 0){
										for(int k = 0; k < teamTeacherList.size(); k++){
											TeamTeacher old = teamTeacherList.get(k);
											if(old.getTeacherId().equals(teamTeacher.getTeacherId())
													&& old.getUserId().equals(teamTeacher.getUserId())
													&& old.getType().equals(teamTeacher.getType())){
												//subjectCode都为空，为班主任；不为空且相等为科任教师，从列表中删除
												if(teamTeacher.getSubjectCode() != null && !"".equals(teamTeacher.getSubjectCode())){
													if(teamTeacher.getSubjectCode().equals(old.getSubjectCode())){
														teamTeacherList.remove(k);
														break;
													}
												}else if(old.getSubjectCode() == null || "".equals(old.getSubjectCode())){
													teamTeacherList.remove(k);
													break;
												}
											}
										}
									}
									
								}
							}
							
							if(msg != null && !"".equals(msg)) {
								if(msg.getMsg() != null && !"".equals(msg.getMsg())){
									msgList.add(msg);
								}
							}
						}
					}
					
					//删除科目教师
					if(teamTeacherList != null && teamTeacherList.size() > 0){
						for(TeamTeacher old : teamTeacherList){
							teamTeacherService.removeTeamTeacherFromTeam(old, SysContants.SYSTEM_APP_ID, groupId, schoolId);
						}
					}
					
				}
			}
			
			if(msgList != null && msgList.size() > 0){
				return new ResponseVo<List<ExtImportErrorMsg>>("060115", msgList);
			}else{
				//然后测试环境先构建起来。 
				//return null;
				return new ResponseVo<List<ExtImportTeacherErrorMsg>>("0", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("导入失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("导入失败");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}

	/**
	 * 功能描述：获得全年级班级学生
	 * @param request
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/student/list")
	@ResponseBody
	public Object getStudentByGrade(HttpServletRequest request,@RequestParam(value = "gradeId", required = false) Integer gradeId){
		
		//判断是否传参
		if(gradeId == null || "".equals(gradeId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("gradeId");
			return new ResponseError("060111", info);
		}
		
		//判断传的参数是否存在年级信息
		Grade grade = gradeService.findGradeById(gradeId);
		if(grade == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("所指定的参数找不到数据...");
			info.setMsg("找不到年级信息");
			info.setParam("gradeId");
			return new ResponseError("020101", info);
		}
		
		//默认的头像位置
		String defaultUserIcon = request.getServletContext().getRealPath("/") + "res/images/no_pic.jpg";
		//根据年级获取班级
		List<Team> teamList = teamService.findTeamOfGrade(gradeId);
		//存放单个学生
		ExtStudentVo studentVo = new ExtStudentVo();
		//存放多个学生信息
		List<ExtStudentVo> sttudentVoLsit = new ArrayList<ExtStudentVo>();
		//存放单个班级的学生
		ExtGradeTeamsStudentsVo gtsVo = new ExtGradeTeamsStudentsVo();
		//存放多个班级的学生（最终返回的数据）
		List<ExtGradeTeamsStudentsVo> gtsList = new ArrayList<ExtGradeTeamsStudentsVo>();
		
		List<Student> studentList = null;
		try{
			if(teamList != null && teamList.size() > 0){
				for(Team team : teamList){
					if(team != null){
						studentList = studentService.findStudentByTeamId(team.getId());
						sttudentVoLsit = new ArrayList<ExtStudentVo>();
						if(studentList != null){
							for(Student student : studentList){
								studentVo = new ExtStudentVo(); 
								String userIcon =  getUserIcon(student.getUserId());
								//如果用户没有头像  将默认头像赋给当前学生
								if("".equals(userIcon)) {
									userIcon = defaultUserIcon;
								}
								studentVo.setId(student.getUserId());
								studentVo.setName(student.getName());
								studentVo.setNumber(student.getStudentNumber2());
								studentVo.setSex(student.getSex());
								studentVo.setUserIcon(userIcon);
								/* 新增studentID、userName Added on 2017-07-26 */
								studentVo.setStudentId(student.getId());
								studentVo.setUserName(student.getUserName());
								/* 新增studentID、userName Added on 2017-07-26 */
								sttudentVoLsit.add(studentVo);
							}
						}
						gtsVo = new ExtGradeTeamsStudentsVo();
						gtsVo.setStudents(sttudentVoLsit);
						gtsVo.setTeamId(team.getId());
						gtsVo.setTeamName(team.getName());
						gtsList.add(gtsVo);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取年级下的学生信息失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取年级下的学生信息异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		
		return new ResponseVo<List<ExtGradeTeamsStudentsVo>>("0", gtsList);
	}



	/**
	 * 功能描述：导入全年级班级学生（在校生）
	 * @date 2017-07-26
	 * @param gradeId  年级ID
	 * @param dataJson  分班学生
	 * @param type 分班类型
	 * @return
	 */

	@RequestMapping("/student/import")
	@ResponseBody
	public Object ImportStudent(
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "data", required = false) String dataJson,
			@RequestParam(value = "type", required = false) String type){
		try{

			if(gradeId == null || "".equals(gradeId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}

			if(type == null || "".equals(type)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("type");
				return new ResponseError("060111", info);
			}

			//用于存放错误信息
			List<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg> msgList = new ArrayList<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg>();

			//用于存放一开始就检测的错误信息
			List<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg> msgCheckList = new ArrayList<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg>();

			//用于存放保存过程中错误信息
			List<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg> msgSaveList = new ArrayList<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg>();

			//用于存放单个学生属性
			ExtImportStudentVo extImportStudentVo = null;

			//存放自检正确的学生集合
			List<ExtImportStudentOfInSchoolVo> extImportStudentVoList = new ArrayList<ExtImportStudentOfInSchoolVo>();

			//存放导入的错误信息
			platform.education.generalTeachingAffair.vo.ExtImportErrorMsg msg = null;

			//根据年级获取学校
			Grade grade = gradeService.findGradeById(gradeId);

			Integer schoolId = null;
			if(grade != null){
				schoolId = grade.getSchoolId();
			}

			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("该年级所在学校不存在...");
				info.setMsg("年级所在学校不存在");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}

			if(grade == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("该年级不存在...");
				info.setMsg("年级不存在");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}

			//获得当前年级下的所有班级学生记录 用于检查是否学生是否已经分班 防止学生记录重复
			TeamStudentCondition tsCondition = new TeamStudentCondition();
			tsCondition.setGradeId(gradeId);
			tsCondition.setInState(true);
			List<TeamStudent> teamStudentList = new ArrayList<TeamStudent>();
			teamStudentList = teamStudentService.findTeamStudentByCondition(tsCondition, null, null);

			//解析JSON数据
			JSONArray data = JSONArray.fromObject(dataJson);

			//导入数据之前的检验

			if(data != null && data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					JSONObject jsonJ = data.getJSONObject(i);
					Integer teamId = jsonJ.getInt("teamId");
					JSONArray jsonForStudent = jsonJ.getJSONArray("students");
					if (jsonForStudent != null && jsonForStudent.size() > 0) {
						for (int j = 0; j < jsonForStudent.size(); j++) {
							JSONObject jsonStu = jsonForStudent.getJSONObject(j);
							Integer id = jsonStu.getInt("id");
							String name = jsonStu.getString("name");
//							Integer number = jsonStu.getInt("number");
							String number = jsonStu.getString("number");
							Integer userId = jsonStu.getInt("userId");
							String userName = jsonStu.getString("userName");
							Integer oldTeamId = jsonStu.getInt("oldTeamId");
							Boolean isExist = false;
							if(teamStudentList != null && teamStudentList.size() > 0) {
								//检查该学生是否已在当前年级下的班级存在
								if ("1".equals(type)) {
									for (TeamStudent ts : teamStudentList) {
										if (ts != null) {
											//升级并分班，升级需要查询当前年级下是否已经分班
											if(id.equals(ts.getStudentId())) {
												isExist = true;
												Team  team = teamService.findTeamById(ts.getTeamId());
												if(team != null) {
													msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
													msg.setId(id);
													msg.setMsg("学生已经分班");
													msg.setDetail(team.getName());
													msgCheckList.add(msg);
												}
											}
										}
									}
								} else if ("2".equals(type)) {
									for (TeamStudent ts : teamStudentList) {
										if (ts != null) {
											//同年级分班 需要检查在当前班级是否已经存在或者在当前年级的某个班级存在（排除原班级）
											if(id.equals(ts.getStudentId()) && !oldTeamId.equals(ts.getTeamId())) {
												if(teamId.equals(ts.getTeamId())) {
													//检查在当前班级是否已经存在
													isExist = true;
													Team  team = teamService.findTeamById(ts.getTeamId());
													if(team != null) {
														msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
														msg.setId(id);
														msg.setMsg("学生已经分班");
														msg.setDetail(team.getName());
														msgCheckList.add(msg);
													}
												} else {
													//检查不在当前班级存在，而是在当前年级的某个班是否存在（排除原班级）
													isExist = true;
													Team  team = teamService.findTeamById(ts.getTeamId());
													if(team != null) {
														msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
														msg.setId(id);
														msg.setMsg("学生已经分在其他班");
														msg.setDetail(team.getName());
														msgCheckList.add(msg);
													}
												}
											}

										}
									}
								}

							}
							if (isExist) {
								//已经存在，不再重复分班，返回分班信息
							}else {
								//当前分班学生加入待导入数据集合
								ExtImportStudentOfInSchoolVo vo = new ExtImportStudentOfInSchoolVo();
								vo.setId(id);
								vo.setName(name);
//								vo.setNumber(number);
								vo.setUserId(userId);
								vo.setUserName(userName);
								vo.setTeamId(teamId);
								vo.setOldTeamId(oldTeamId);
								if ("".equals(number)) {
									vo.setNumber(null);
								}else {
									vo.setNumber(Integer.valueOf(number));
								}
								extImportStudentVoList.add(vo);
							}
						}
					}
				}
			}

			//导入数据
			if(extImportStudentVoList != null && extImportStudentVoList.size() > 0){
/*
				String key = "jw_zxsfb_" + gradeId; //教务_在校生分班_年级ID
				RedisLock lock =  new RedisLock(redisTemplate, key, 50000, 50000);
				try {
					if(lock.lock()){
						//需上锁执行的程序
						if("1".equals(type)) {
							//升级并分班
							for(ExtImportStudentOfInSchoolVo extStudentVo : extImportStudentVoList){
								try{
//							String message = this.teamStudentService.assignStudentToTeam(extStudentVo.getId(), extStudentVo.getTeamId());
									String message = this.teamStudentService.upgradeAndAssignToTeam(extStudentVo.getId(), extStudentVo.getTeamId(), extStudentVo.getOldTeamId());
									//将学生导入的错误信息收集
									if(TeamStudentService.OPERATE_ERROR.equals(message)) {
										msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
										msg.setId(extStudentVo.getId());
										msg.setMsg("导入失败");
										msg.setErrorCode("");
										msg.setDetail("程序异常");
										msgSaveList.add(msg);
									}
								}catch(Exception e){
									log.debug("导入失败");
									msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
									msg.setId(extStudentVo.getId());
									msg.setMsg("导入失败");
									msg.setErrorCode("");
									msg.setDetail("程序异常");
									msgSaveList.add(msg);
								}
							}
						} else if("2".equals(type)) {
							//同年级分班 学生变更类型为调班
							for (ExtImportStudentOfInSchoolVo extStudentVo : extImportStudentVoList) {
								String message = this.teamStudentService.moveStudentToTeam(extStudentVo.getId(), extStudentVo.getOldTeamId(), extStudentVo.getTeamId());
								if (TeamStudentService.OPERATE_ERROR.equals(message)) {
									msgSaveList.add(msg);
								}
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}*/

				if("1".equals(type)) {
					//升级并分班
					for(ExtImportStudentOfInSchoolVo extStudentVo : extImportStudentVoList){
						try{
//							String message = this.teamStudentService.assignStudentToTeam(extStudentVo.getId(), extStudentVo.getTeamId());
							String message = this.teamStudentService.upgradeAndAssignToTeam(extStudentVo.getId(), extStudentVo.getTeamId(), extStudentVo.getOldTeamId());
							//将学生导入的错误信息收集
							if(TeamStudentService.OPERATE_ERROR.equals(message)) {
								msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
								msg.setId(extStudentVo.getId());
								msg.setMsg("导入失败");
								msg.setErrorCode("");
								msg.setDetail("程序异常");
								msgSaveList.add(msg);
							}
						}catch(Exception e){
							log.debug("导入失败");
							msg = new platform.education.generalTeachingAffair.vo.ExtImportErrorMsg();
							msg.setId(extStudentVo.getId());
							msg.setMsg("导入失败");
							msg.setErrorCode("");
							msg.setDetail("程序异常");
							msgSaveList.add(msg);
						}
					}
				} else if("2".equals(type)) {
					//同年级分班 学生变更类型为调班
					for (ExtImportStudentOfInSchoolVo extStudentVo : extImportStudentVoList) {
						String message = this.teamStudentService.moveStudentToTeam(extStudentVo.getId(), extStudentVo.getOldTeamId(), extStudentVo.getTeamId());
						if (TeamStudentService.OPERATE_ERROR.equals(message)) {
							msgSaveList.add(msg);
						}
					}
				}

			}

			if(msgSaveList != null && msgSaveList.size() > 0) {
				msgList.addAll(msgSaveList);
			}

			if(msgCheckList != null && msgCheckList.size() > 0) {
				msgList.addAll(msgCheckList);
			}

			if(msgList != null && msgList.size() > 0){
				return new ResponseVo<List<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg>>("060115", msgList);
			}else{
				return new ResponseVo<List<platform.education.generalTeachingAffair.vo.ExtImportErrorMsg>>("0", null);
			}

		}catch(Exception e) {
			e.printStackTrace();
			log.debug("导入失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("导入失败");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}

	
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	  }  
}
