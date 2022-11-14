package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.School;
import platform.education.user.model.Group;
import platform.education.user.model.Role;
import platform.education.user.model.UserRole;
import platform.education.user.service.GroupUserService;
import platform.education.user.vo.RoleCondition;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.ExtRoleVo;
import platform.szxyzxx.web.teach.client.vo.ExtSchoolVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;
import platform.szxyzxx.web.teach.client.vo.SchoolRoles;

@Controller
@RequestMapping("/school/user")
public class ExtUserController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("groupUserService")
	private GroupUserService groupUserService;
	
	public void setGroupUserService(GroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}

	/**
	 * @function 获取一个用户所在的所有学校记录
	 * @return 
	 */
	@RequestMapping("/getMySchools")
	@ResponseBody
	public Object getMySchools(
			@RequestParam(value = "userId", required = false) Integer userId){
		Group group = null;
		if(userId == null){
			return new ResponseError("060111",null);//必填参数为空
		}
		List<ExtSchoolVo> schoolInfoList = new ArrayList<ExtSchoolVo>();
		ExtSchoolVo schoolVo = null;
		try{
			List<Integer> groupList = groupUserService.findGroupIds(userId);
			if(groupList.size() > 0){
				for(Integer groupId : groupList){
					group = groupService.findGroupById(groupId);
					if(group != null && group.getOwnerId() != null){
						School school = schoolService.findSchoolById(group.getOwnerId());
						if(school != null){
							schoolVo = new ExtSchoolVo();
							schoolVo.setId(school.getId());
							schoolVo.setSchoolName(school.getName());
							schoolVo.setCode(school.getCode());
							schoolVo.setSchoolType(school.getSchoolType());
							schoolVo.setAddress(school.getAddress());
							schoolInfoList.add(schoolVo);
						}
					}
				}
			}
			if(schoolInfoList.size() <= 0){
				return new ResponseError("020101", null);//找不到数据
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug("获取数据异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取用户的学校异常");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		return new ResponseVo<List<ExtSchoolVo>>("0", schoolInfoList);
	}
	
	/**
	 * @function 获取一个用户在某所学校的所有角色
	 * @param schoolId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getRolesOfSchool")
	@ResponseBody
	public Object getRolesOfSchool(
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "ext", required = false, defaultValue = "false" ) boolean ext){
		if(schoolId == null){
			return new ResponseError("060111",null);//必填参数为空
		}
		if(userId == null){
			return new ResponseError("060111",null);//必填参数为空
		}
		
		ExtRoleVo roleVo = null;
		List<ExtRoleVo> roleVoList = new ArrayList<ExtRoleVo>();
		
		try{
			Group group = groupService.findUnique(schoolId, "1");
			RoleCondition roleCondition = new RoleCondition();
			if(group != null){
				roleCondition.setGroupId(group.getId());
				roleCondition.setAppId(SysContants.SYSTEM_APP_ID);
				List<Role> roleList = roleService.findRoleByCondition(roleCondition, null, null);
				Map<Integer, Role> roleMap = new HashMap<Integer, Role>();
				for (Role role : roleList) {
					roleMap.put(role.getId(), role);
				}
				List<Integer> roleIds = userRoleService.findRoleIdsByUserId(userId, Integer.valueOf(1));
				for (Integer integer : roleIds) {
					if ( roleMap.containsKey(integer) ) {
						Role role = roleMap.get(integer);
						if(ext){
							if(SchoolRoles.R$CLASS_MASTER.equals(role.getCode())
									|| SchoolRoles.R$PARENT.equals(role.getCode())
									|| SchoolRoles.R$SCHOOL_LEADER.equals(role.getCode())
									|| SchoolRoles.R$SCHOOL_MASTER.equals(role.getCode())
									|| SchoolRoles.R$STUDENT.equals(role.getCode())
									|| SchoolRoles.R$SUBJECT_TEACHER.equals(role.getCode())
									|| SchoolRoles.R$TEACHER.equals(role.getCode())
									|| SchoolRoles.R$SCHOOL_MANAGER.equals(role.getCode())
									|| SchoolRoles.R$SCHOOL_OPERATOR.equals(role.getCode())
									|| role.getCode().endsWith("school_admin")){
									roleVo = new ExtRoleVo();
									roleVo.setId(role.getId());
									roleVo.setName(role.getName());
									roleVo.setCode(role.getCode());
									roleVoList.add(roleVo);
								}
						}else{
							//筛选角色
							if(SchoolRoles.R$CLASS_MASTER.equals(role.getCode())
								|| SchoolRoles.R$PARENT.equals(role.getCode())
								|| SchoolRoles.R$SCHOOL_LEADER.equals(role.getCode())
								|| SchoolRoles.R$SCHOOL_MASTER.equals(role.getCode())
								|| SchoolRoles.R$STUDENT.equals(role.getCode())
								|| SchoolRoles.R$SUBJECT_TEACHER.equals(role.getCode())
								|| SchoolRoles.R$TEACHER.equals(role.getCode())
								|| SchoolRoles.R$SCHOOL_MANAGER.equals(role.getCode())
								|| SchoolRoles.R$SCHOOL_OPERATOR.equals(role.getCode())){
								roleVo = new ExtRoleVo();
								roleVo.setId(role.getId());
								roleVo.setName(role.getName());
								roleVo.setCode(role.getCode());
								roleVoList.add(roleVo);
							}
						}
					}
					
				}
			}
			if(roleVoList.size() <= 0){
				return new ResponseError("020101", null);//找不到数据
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug("获取数据异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取用户的角色异常");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		return new ResponseVo<List<ExtRoleVo>>("0", roleVoList);
	}
}
