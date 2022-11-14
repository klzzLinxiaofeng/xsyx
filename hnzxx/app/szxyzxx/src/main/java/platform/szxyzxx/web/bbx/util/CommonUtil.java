package platform.szxyzxx.web.bbx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


















import javax.servlet.http.HttpServletRequest;

import org.springframework.core.task.TaskExecutor;

import platform.education.clazz.model.TeamAccount;
import platform.education.clazz.model.TeamMessage;
import platform.education.clazz.service.TeamAccountService;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.generalcode.model.Item;
import platform.education.generalcode.service.JcGcCacheService;
import platform.education.im.service.IMService;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;
import platform.szxyzxx.web.bbx.contants.ContansOfBbx;



/**
 * 根据条件获取对应的推送对象以及实现推送
 * 
 * @author huangyanchun
 *
 */
public class CommonUtil {
	
	
	
	//根据条件查找家长与学生关系信息
		public static List<ParentStudent>parentList(Integer teamId,Integer parentUserId,ParentStudentService  parentStudentService){
			ParentStudentCondition condition = new ParentStudentCondition();
			condition.setTeamId(teamId);
			condition.setParentUserId(parentUserId);
			condition.setIsDelete(false);
			List<ParentStudent>psList = parentStudentService.findParentStudentByCondition(condition);
			return psList;
		}
		
		//根据班级、家长userId查找对应的家长学生关系
		public static String parentStudentName(Integer teamId,Integer parentUserId,ParentStudentService  parentStudentService,JcGcCacheService jcGcCacheService,StudentService studentService){
			List<ParentStudent>parentStudentList = parentList(teamId,parentUserId,parentStudentService);
			 String parentName = "";
			 if(parentStudentList!=null && parentStudentList.size()>0){
				 String parentStudentName = "";
				 Object object = null;
				 for(int i=0,length=parentStudentList.size();i<length;i++){
					 //家庭关系
					 object =jcGcCacheService.getNameByValue("GB-JTGX", parentStudentList.get(i).getParentRelation());
					 parentStudentName+=studentService.findStudentByUserId(parentStudentList.get(i).getStudentUserId()).getName()+"的"+(object==null ?"家长":object.toString())+",";
				 }
				 parentName+=parentStudentName;
				 
			 }
			 
			 return "".equals(parentName)?"":parentName.substring(0, parentName.length()-1);
		}
	
	
	
	
	
	/**
	 * 根据班级id（teamId）查找对应班级的家长用户列表
	 * @param teamId
	 * @return
	 */
	public static List<Integer>findByParentTeam(Integer teamId,TeamUserService teamUserService,TeamTeacherService teamTeacherService, TeacherService teacherService){
		
		//根据条件查找到对应的家长列表
		 List<TeamUser>parentList = teamUserService.findTeamUserOfParents(teamId);
		 
		 //需要返回的家长的userId列表
		 Map<Integer,Integer>puserMaps = new HashMap();
		 
		 if(parentList.size()>0){
			 for(TeamUser tu :parentList){
				 puserMaps.put(tu.getId(), tu.getId());
				 
			 }
		 }
		
		return new ArrayList(puserMaps.values());
	}
	
	
	/**
	 * 根据班级id（teamId）查找对应班级的教师 用户列表
	 * @param teamId
	 * @param teamTeacherService
	 * @param teacherService
	 * @return
	 */
	public static List<Integer>findTeacherByTeam(Integer teamId,TeamTeacherService teamTeacherService, TeacherService teacherService){
		
		TeamTeacherCondition condition = new TeamTeacherCondition();
		condition.setTeamId(teamId);
		//根据条件查找对应的教师列表
		List<TeamTeacherVo> ttList = teamTeacherService.findTeamTeacherVoByCondition(condition);
		
		//需要返回的教师的userId列表
		Map<Integer,Integer>tUserMaps = new HashMap();
		
		if(ttList.size()>0){
			for(TeamTeacherVo ttv:ttList){
				Teacher t = teacherService.findTeacherById(ttv.getTeacherId());
				if(t!=null){
					tUserMaps.put(t.getId(), t.getUserId());
				}
			}
		}
		
		
		
		
		return new ArrayList(tUserMaps.values());
	}
	
	
	/**
	 * 根据学校id和班级id查找对应已经开通的班级账号相关信息
	 * @param schoolId
	 * @param teamId
	 * @param teamAccountService
	 * @return
	 */
	public static List<String>findTeamAccountByTeam(Integer schoolId,String teamIds, TeamAccountService teamAccountService){
		//需要返回的已经开通的班级账号信息的team_uuid列表
		Map ownerIdMaps = new HashMap();
		String [] teamId = null;
		if(teamIds!=null){
			teamId = teamIds.split(",");
		}
		
		if(teamId.length>0){
			for(int i=0;i<teamId.length;i++){
				//根据条件查找对应已经开通的班级账号信息
				TeamAccount account = teamAccountService.findBySchoolIdAndTeamId(
						schoolId, Integer.parseInt(teamId[i]));
				if(account!=null){
					ownerIdMaps.put(account.getId(), account.getTeamUuid());
				}
				
			}
		}
		
		
		
		
		
		return new ArrayList(ownerIdMaps.values());
	}
	
	
	
	
	/**
	 * 公共方法利用用户的id获取图片的src
	 * @param userId
	 * @param request
	 * @return
	 */
	public static String getImgSrc(Integer userId,HttpServletRequest request,ProfileService profileService){
		String outPutHtml = "";
		String def = "res/images/no_pic.jpg";
		Profile profile = profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
		}
		if ("".equals(outPutHtml)) {
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  
			outPutHtml = tempContextUrl+def;
		}
		return outPutHtml;
	}
	
	
	
	

	/**
	 * 根据条件查找对应的教师用户
	 * 
	 * @param teamId
	 * @return
	 */
	public static List<Teacher> findByTerm(Integer teamId,
			TeamTeacherService teamTeacherService, TeacherService teacherService) {
		List<Teacher> tList = new ArrayList<Teacher>();
		TeamTeacherCondition condition = new TeamTeacherCondition();
		condition.setTeamId(teamId);
		List<TeamTeacherVo> ttList = teamTeacherService
				.findTeamTeacherVoByCondition(condition);
		Teacher teacher1 = null;
		for (TeamTeacherVo tt : ttList) {
			teacher1 = teacherService.findTeacherById(tt.getTeacherId());
			if (teacher1 != null) {
				tList.add(teacher1);
			}
		}

		// 根据teacherId去除教师中的重复值
		tList = DistinctUtil.distinctTeacherOfTeacherId(tList);
		return tList;
	}

	/**
	 * 根据学校id和班级id查找对应已经开通的班级账号相关信息
	 * 
	 * @param schoolId
	 * @param teamId
	 * @return
	 */
	public static List<TeamAccount> findByCondition(Integer schoolId,
			Integer teamId, TeamAccountService teamAccountService) {
		List<TeamAccount> taList = new ArrayList<TeamAccount>();
		TeamAccount account = teamAccountService.findBySchoolIdAndTeamId(
				schoolId, teamId);
		if (account != null) {
			taList.add(account);
		}

		return taList;

	}
	
	/**
	 * 根据 班级id  teamId查找对应相关的教师信息
	 * @param teamId
	 * @return
	 */
		public static List<Teacher> findByTeamId(Integer teamId,TeamTeacherService teamTeacherService, TeacherService teacherService) {
			List<Teacher> tList = new ArrayList<Teacher>();
			TeamTeacherCondition condition = new TeamTeacherCondition();
			condition.setTeamId(teamId);
			List<TeamTeacherVo> ttList = teamTeacherService
					.findTeamTeacherVoByCondition(condition);
			Teacher teacher1 = null;
			for (TeamTeacherVo tt : ttList) {
				teacher1 = teacherService.findTeacherById(tt.getTeacherId());
				if (teacher1 != null) {
					tList.add(teacher1);
				}
			}

			// 根据teacherId去除教师中的重复值
			tList = DistinctUtil.distinctTeacherOfTeacherId(tList);
			return tList;
		}


	
	

	/**
	 * 根据年级id gradeId查找对应教师信息
	 * 
	 * @param gradeId
	 * @param teamTeacherService
	 * @param teacherService
	 * @return
	 */
	public static List<Teacher> findByGradeId(Integer gradeId,
			TeamTeacherService teamTeacherService, TeacherService teacherService) {
		List<Teacher> tList = new ArrayList<Teacher>();
		TeamTeacherCondition condition = new TeamTeacherCondition();
		condition.setGradeId(gradeId);
		List<TeamTeacherVo> ttList = teamTeacherService
				.findTeamTeacherVoByCondition(condition);
		Teacher teacher1 = null;
		for (TeamTeacherVo tt : ttList) {
			teacher1 = teacherService.findTeacherById(tt.getTeacherId());
			if (teacher1 != null) {
				tList.add(teacher1);

			}
		}

		// 根据teacherId去除教师中的重复值
		tList = DistinctUtil.distinctTeacherOfTeacherId(tList);
		return tList;
	}

	

	/**
	 * 根据通知类型查找对应相关的教师信息
	 * @param teamMessage
	 * @return
	 */
	public static List<Teacher> findByTeamMessage(TeamMessage teamMessage,TeamTeacherService teamTeacherService, TeacherService teacherService) {
		
		TeamTeacherCondition condition = new TeamTeacherCondition();
		List<TeamTeacherVo> ttList = null;
		List<Teacher> tList = new ArrayList<Teacher>();
		Map<Integer,Teacher> map = new HashMap<Integer,Teacher>();
		Map<Integer,Teacher> allTeacher = new HashMap<Integer,Teacher>();
		
		tList = findBySchoolId(teamMessage.getSchoolId(),teacherService);
		
		for(Teacher t : tList){
			map.put(t.getId(), t);
		}
		
		if (teamMessage.getReceiverType().equals(ContansOfBbx.allTeacher)) {
			// 全部教师
			allTeacher = map;

		} else if (teamMessage.getReceiverType().equals(ContansOfBbx.assignGrade)
				|| teamMessage.getReceiverType().equals(ContansOfBbx.assignTeam)) {
			ttList = teamTeacherService.findTeamTeacherVoByCondition(condition);
			Teacher teacher1 = null;
			for (TeamTeacherVo tt : ttList) {
				teacher1 = new Teacher();
				teacher1 = map.get(tt.getTeacherId());
				if(teacher1 != null){
					allTeacher.put(teacher1.getId(), teacher1);
					
				}
			}
		}

		List<Teacher> mapValuesList = new ArrayList<Teacher>(allTeacher.values()); 

		return mapValuesList;
		
	}
	
	
	/**
	 * 根据学校id schoolId查找相关的教师信息
	 * @param schoolId
	 * @param teacherService
	 * @return
	 */
	public static List<Teacher> findBySchoolId(Integer schoolId, TeacherService teacherService) {
		List<Teacher> tList = new ArrayList<Teacher>();
		TeacherCondition tCondition = new TeacherCondition();
		tCondition.setSchoolId(schoolId);
		tCondition.setIsDelete(false);
		List<Teacher> teacherList = teacherService.findTeacherByCondition(
				tCondition, null, null);
		for (Teacher t1 : teacherList) {
			if (t1 != null) {
				tList.add(t1);
			}
		}

		// 根据teacherId去除教师中的重复值
		tList = DistinctUtil.distinctTeacherOfTeacherId(tList);

		return tList;
	}
	
	
	/**
	 * 根据发布对象查找对应的班班星班级相关信息
	 * @param teamMessage
	 * @return
	 */
		public static List<TeamAccount> findByReceiverType(TeamMessage teamMessage,TeamAccountService teamAccountService) {
			TeamTeacherCondition condition = new TeamTeacherCondition();
			List<TeamAccount> taList = new ArrayList<TeamAccount>();
			if (teamMessage.getReceiverType().equals(ContansOfBbx.allTeacher)) {
				// 全部教师
				taList = teamAccountService.findBySchoolId(teamMessage
						.getSchoolId());

			} else if (teamMessage.getReceiverType().equals(ContansOfBbx.assignGrade)) {
				// 指定年级
				condition.setGradeId(teamMessage.getReceiverId());

				taList = teamAccountService.findBySchoolIdAndGradeId(
						teamMessage.getSchoolId(), teamMessage.getReceiverId());

			} else if (teamMessage.getReceiverType().equals(ContansOfBbx.assignTeam)) {
				// 指定班级
				condition.setTeamId(teamMessage.getReceiverId());
				TeamAccount ta = teamAccountService.findByTeamId(teamMessage
						.getReceiverId());
				if (ta != null) {
					taList.add(ta);
				}

			}
			return taList;
		}
	
	/**
	 * 消息推送
	 * 
	 * @param tList
	 * @param taList
	 * @param praiseId
	 * @param type
	 */
	/*public static void messagePush(TeamService teamService,TeamTeacherService teamTeacherService,TeacherService teacherService,TeamAccountService teamAccountService,Integer teamId, String object_type, Integer object_id,
			String operation_type,String object_data, PushService pushService, TaskExecutor taskExecutor) {
		
		List<Teacher> tList = new ArrayList<Teacher>();
		List<TeamAccount> taList = new ArrayList<TeamAccount>();
		Team t  = null;
		if(teamId!=null){
			t = teamService.findTeamById(teamId);
			if(t!=null){
				// 推送教师端
				tList = findByTerm(t.getId(), teamTeacherService,
						teacherService);
				// 推送对应的开通班级账号
				taList =findByCondition(t.getSchoolId(), t.getId(),
						teamAccountService);
			}
			
		}

		// 推送给教师用户
		if (!tList.isEmpty()) {
			PushUtils.pushOfTaskExecutor(tList, object_type, object_id, object_data, operation_type,
					pushService, taskExecutor);
		}

		// 推送给班级用户
		if (!taList.isEmpty()) {
			PushUtils.pushOfTeamAccountTaskExecutor(taList, object_type, object_id,
					object_data, operation_type, pushService, taskExecutor);
		}
	}
	*/

}
