package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.ParentDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.DataImportCheck;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.*;
import platform.education.user.service.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ParentServiceImpl implements ParentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ParentDao parentDao;
	
	private UserService userService;
	
	private StudentService studentService;
	
	private ParentStudentService parentStudentService;
	
	private SchoolUserService schoolUserService;
	
	private TeamUserService teamUserService;
	
	private UserBindingService userBindingService;
	
	private RoleService roleService;
	
	private UserRoleService userRoleService;
	
	private GroupService groupService;

	private GradeService gradeService;

	private TeamService teamService;

	private TeamStudentService teamStudentService;

	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setUserBindingService(UserBindingService userBindingService) {
		this.userBindingService = userBindingService;
	}

	public void setParentDao(ParentDao parentDao) {
		this.parentDao = parentDao;
	}
	
	@Override
	public Parent findParentById(Integer id) {
		try {
			return parentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("??????????????????ID??? {} ", id);
		}
		return null;
	}
	
	@Override
	public Parent add(Parent parent) {
		if(parent == null) {
    		return null;
    	}
    	Date createDate = parent.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	parent.setIsDelete(false);
    	parent.setCreateDate(createDate);
    	parent.setModifyDate(createDate);
		return parentDao.create(parent);
	}

	@Override
	public Parent modify(Parent parent) {
		if(parent == null) {
    		return null;
    	}
    	Date modify = parent.getModifyDate();
    	parent.setModifyDate(modify != null ? modify : new Date());
		return parentDao.update(parent);
	}
	
	@Override
	public void remove(Parent parent) {
		parent.setIsDelete(true);
		parent.setModifyDate(new Date());
		 parentDao.delete(parent);
	}
	
	@Override
	public List<Parent> findParentByCondition(ParentCondition parentCondition, Page page, Order order) {
		return parentDao.findParentByCondition(parentCondition, page, order);
	}
	
	@Override
	public List<Parent> findParentByCondition(ParentCondition parentCondition) {
		return parentDao.findParentByCondition(parentCondition, null, null);
	}
	
	@Override
	public List<Parent> findParentByCondition(ParentCondition parentCondition, Page page) {
		return parentDao.findParentByCondition(parentCondition, page, null);
	}
	
	@Override
	public List<Parent> findParentByCondition(ParentCondition parentCondition, Order order) {
		return parentDao.findParentByCondition(parentCondition, null, order);
	}
	@Override
	public List<Parent> findParentByCondition2(ParentCondition parentCondition, Page page, Order order) {
		return parentDao.findParentByCondition2(parentCondition, page, order);
	}
	
	@Override
	public List<Parent> findParentByCondition2(ParentCondition parentCondition) {
		return parentDao.findParentByCondition2(parentCondition, null, null);
	}
	
	@Override
	public List<Parent> findParentByCondition2(ParentCondition parentCondition, Page page) {
		return parentDao.findParentByCondition2(parentCondition, page, null);
	}
	
	@Override
	public List<Parent> findParentByCondition2(ParentCondition parentCondition, Order order) {
		return parentDao.findParentByCondition2(parentCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.parentDao.count(null);
	}

	@Override
	public Long count(ParentCondition parentCondition) {
		return this.parentDao.count(parentCondition);
	}

	/**
	 * ?????????????????????userId??????????????????????????????
	 * @param userId
	 * @return
	 */
	@Override
	public Parent findUniqueByUserId(Integer userId) {
		try {
			return parentDao.findUniqueByUserId(userId);
		} catch (Exception e) {
			log.info("????????????????????? {} ", userId);
		}
		return null;
	}

	@Override
	public List<ParentVo> findIncrementDataByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer parentId, Boolean isGetNew) {
		return this.parentDao.findIncrementDataByModifyDate(schoolId, isDeleted, modifyDate, parentId, isGetNew);
	}

	@Override
	public ParentVo findParentVoByUserId(Integer userId) {
		return this.parentDao.findParentVoByUserId(userId);
	}

	@Override
	public List<ParentVo> findParentsByStudentUserId(Integer studentUserId) {
		return this.parentDao.findParentsByStudentUserId(studentUserId);
	}

	@Override
	public boolean addParentFromExcelImport(Integer schoolId, Integer appid, Integer gradeId, Integer teamId,
			Integer studentUserid, String guardian, String guardianPhone, String parentType, String parentRelation, String rank, String email) {
		boolean result = false;
		Integer userid = null;
		String username = null;
		
		Date now = new Date();
		Student student = studentService.findStudentByUserId(studentUserid);
		if(guardian==null || "".equals(guardian)) {
			guardian = student.getName()+"?????????";
		}
		
		User user = null;
		/**??????????????????UserBinding??????*/
		boolean ubRecord = false;
		/**??????????????????SchoolUser??????*/
		boolean suRecord = false;
		
		List<UserBinding> userBindingList = userBindingService.findUserListByBindingName(guardianPhone);
		if(userBindingList!=null || userBindingList.size()>0) {
			for (UserBinding userBinding : userBindingList) {
				SchoolUserCondition condition = new SchoolUserCondition();
				condition.setUserId(userBinding.getUserId());
				condition.setSchoolId(schoolId);
				condition.setIsDeleted(false);

				List<SchoolUser> tmpList = schoolUserService.findSchoolUserByCondition(condition, null, null);
				if(tmpList.size()>0) {
					if(!ubRecord) {
						ubRecord = true;
						user = userService.findUserById(userBinding.getUserId());
					}
				}
				for (SchoolUser schoolUser : tmpList) {
					if(parentType.equals(schoolUser.getUserType())) {
						if(!suRecord) {
							suRecord = true;
						}
					}
				}
			}
		}


		/**??????????????????*/
		Group group = groupService.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
		Role role = roleService.findUniqueInGroup(appid, group.getId(), "PARENT");
		if(userBindingList==null || userBindingList.size()==0 || !ubRecord) {
			/**????????????*/
			user = userService.addUserFromAccount(schoolId, appid, GroupContants.TYPE_SCHOOL);

			/**?????????????????????????????????*/
	userRoleService.addUserRole(user, role);
			
			/**???????????????????????????*/
			UserBinding userBinding = new UserBinding();
			userBinding.setBindingName(guardianPhone);
			userBinding.setBindingType(1);
			userBinding.setCreateDate(now);
			userBinding.setEnabled(true);
			userBinding.setIsDeleted(false);
			userBinding.setModifyDate(now);
			userBinding.setUserId(user.getId());

			userBindingService.addUserBinding(userBinding);
		}
		userid = user.getId();
		username = user.getUserName();

		UserRole userRole =userRoleService.findByUserIdAndRoleId(userid, role.getId());
		if(userRole==null) {
			/**?????????????????????????????????*/
			userRoleService.addUserRole(user, role);
		}

		/**??????????????????????????????*/
		ParentStudent parentStudent = new ParentStudent();
		parentStudent.setCreateDate(now);
		parentStudent.setIsDelete(false);
		parentStudent.setModifyDate(now);
		parentStudent.setParentUserId(userid);
		parentStudent.setStudentUserId(studentUserid);
		parentStudent.setStudentName(student.getName());
		parentStudent.setParentRelation(parentRelation);
		parentStudent.setRank(rank);
		if (!parentStudentExist(userid, studentUserid)) {
			parentStudentService.add(parentStudent);
			modifyMainRelation(rank, studentUserid, userid, suRecord);
		}

		if(!suRecord) {
			/**????????????*/
			Parent parent = new Parent();
			parent.setCreateDate(now);
			parent.setIsDelete(false);
			parent.setMobile(guardianPhone);
			parent.setModifyDate(now);
			parent.setName(guardian);
			parent.setUserId(userid);
			parent.setUserName(user.getUserName());
			parent.setEmail(email);
			parentDao.create(parent);
			
			result = true;
			
			/**??????????????????????????????*/
			SchoolUser schoolUser = new SchoolUser();
			schoolUser.setAlias(guardian);
			schoolUser.setCreateDate(now);
			schoolUser.setInSchool(true);
			schoolUser.setIsDeleted(false);
			schoolUser.setModifyDate(now);
			schoolUser.setName(guardian);
			schoolUser.setSchoolId(schoolId);
			schoolUser.setUserId(userid);
			schoolUser.setUserName(username);
			schoolUser.setUserType(parentType);
			schoolUser.setOwnerId(parent.getId());
			schoolUserService.addSchoolUser(schoolUser);
			
		}
		
		TeamUser teamUser = teamUserService.findUnique(teamId, userid);
		if(teamUser==null) {
			/**????????????????????????*/
			teamUser = new TeamUser();
			teamUser.setCreateDate(now);
			teamUser.setIsMaster(false);
			teamUser.setIsParent(true);
			teamUser.setIsStudent(false);
			teamUser.setIsTeacher(false);
			teamUser.setModifyDate(now);
			teamUser.setName(guardian);
			teamUser.setSex("");
			teamUser.setTeamId(teamId);
			teamUser.setUserId(userid);
			teamUserService.add(teamUser);
		} else {
			if(!teamUser.getIsParent()) {
				teamUser.setIsParent(true);
				teamUserService.modify(teamUser);
			}
		}
		
		return result;
	}

	private boolean parentStudentExist(Integer parentUserId, Integer studentUserId){
		boolean isExist = false;
		ParentStudent unique = parentStudentService.findUnique(parentUserId, studentUserId);
		if (unique != null) {
			isExist = true;
		}
		return isExist;
	}

	private void modifyMainRelation(String rank, Integer studentUserId, Integer parentUserId, Boolean isExist){
		//???????????????????????????????????????????????????????????????????????????????????????
		if ("1".equals(rank)) {
			ParentStudentCondition condition = new ParentStudentCondition();
			condition.setStudentUserId(studentUserId);
			condition.setRank("1");
			condition.setIsDelete(false);
			List<ParentStudent> list = parentStudentService.findParentStudentByCondition(condition);
			if (list != null && list.size() > 0) {
				for (ParentStudent parentStudent : list) {
					if (!parentUserId.equals(parentStudent.getParentUserId())) {
						parentStudent.setRank("0");
						parentStudent.setModifyDate(new Date());
						parentStudentService.modify(parentStudent);
						//???????????????????????????????????????
						Parent parent = parentDao.findUniqueByUserId(parentStudent.getParentUserId());
						if (parent != null) {
							parent = new Parent(parent.getId());
							parent.setModifyDate(new Date());
							parentDao.update(parent);
						}
					}
				}
			}
		}
		//??????????????????????????????????????????????????? ??????
		if (isExist) {
			Parent parent = parentDao.findUniqueByUserId(parentUserId);
			if (parent != null) {
				parent = new Parent(parent.getId());
				parent.setModifyDate(new Date());
				parentDao.update(parent);
			}
		}


	}


	@Override
	public Parent findParentByMobile(String mobile) {
		ParentCondition condition =  new ParentCondition();
		condition.setMobile(mobile);
		condition.setIsDelete(false);
		List<Parent> result = parentDao.findParentByCondition(condition);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setParentStudentService(ParentStudentService parentStudentService) {
		this.parentStudentService = parentStudentService;
	}
	
	public void setSchoolUserService(SchoolUserService schoolUserService) {
		this.schoolUserService = schoolUserService;
	}

	public void setTeamUserService(TeamUserService teamUserService) {
		this.teamUserService = teamUserService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	public void removeAllByUserId(Integer parentUserId, Integer schoolid, String parentType) {
		List<UserRole> userRoleList = userRoleService.findByUserId(parentUserId);
		//??????????????????
		if(userRoleList.size()==1) {
		userService.removeAllById(parentUserId, true);
		}
		Date date = new Date();
		
		//??????????????????
		Parent parent = parentDao.findUniqueByUserId(parentUserId);
		parent.setIsDelete(true);
		parent.setModifyDate(date);
		parentDao.updateNoRead(parent, 0);
		
		//???????????????????????????
		List<ParentStudent> parentUserList = parentStudentService.findParentStudentByParentUserId(parentUserId);
		for (ParentStudent parentStudent : parentUserList) {
			parentStudent.setIsDelete(true);
			parentStudent.setModifyDate(date);
			parentStudentService.modifyNoRead(parentStudent, 0);
		}
		
		//??????schoolUser??????
		SchoolUser schoolUser = schoolUserService.findSchoolUserListByUserIdAndType(parentUserId, parentType);
		if(schoolUser!=null) {
			schoolUser.setModifyDate(date);
			schoolUser.setIsDeleted(true);
			schoolUserService.modify(schoolUser);
		}
		
		//??????????????????
		TeamUserCondition teamUserCondition = new TeamUserCondition();
		teamUserCondition.setUserId(parentUserId);
		List<TeamUser> teamUserList = teamUserService.findTeamUserByCondition(teamUserCondition);
		for (TeamUser teamUser : teamUserList) {
			teamUser.setModifyDate(date);
			teamUser.setIsDeleted(true);
			teamUserService.modify(teamUser);
		}
		
	}

	@Override
	public Parent findParentByMobileAndSchoolId(String mobile, Integer schoolId) {
		ParentCondition condition = new ParentCondition();
		condition.setMobile(mobile);
		condition.setIsDelete(false);
		List<Parent> list = parentDao.findParentByCondition(condition);
		for (Parent parent : list) {
			SchoolUser schoolUser = schoolUserService.findSchoolUserByUserId(parent.getUserId(), schoolId);
			if(schoolUser!=null) {
				return parent;
			}
		}
		return null;
	}
	
	@Override
	public String deleteParentStudentRelate(Integer parentId, Integer studentId, String parentType) {
		String result = "success";
		if(parentId!=null && studentId!=null) {
			Parent parent = parentDao.findById(parentId);
			if(parent==null) {
				result = "??????????????????????????? : " + parentId;
				return result;
			}
			Student student = studentService.findStudentById(studentId);
			if(student==null) {
				result = "??????????????????????????? : " + studentId;
				return result;
			}
			
			Integer parentUserId = parent.getUserId();
			Integer studentUserId = student.getUserId();
			
			List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByParentUserId(parentUserId);
			Integer parentStudentSize = parentStudentList.size();
			
			boolean relation = false;
			
			Date now = new Date();
			for (ParentStudent parentStudent : parentStudentList) {
				/**??????????????????????????????*/
				if(parentStudent.getStudentUserId()-studentUserId==0) {
					relation = true;
					parentStudent.setIsDelete(true);
					parentStudent.setModifyDate(now);
					/**????????????*/
					parentStudentService.modify(parentStudent);

					Parent p = new Parent(parent.getId());
					p.setModifyDate(new Date());
					parentDao.update(p);
				}
			}
			
			if(!relation) {
				result = "?????????"+parent.getName()+"}????????????"+parent.getName()+"?????????????????????";
				return result;
			}
			
			/**?????????*/
			if(parentStudentSize<=1) {
				deleteParent(parent, parentType);
			}
			return result;
		} else {
			result = "parentId, studentId????????????";
			return result;
		}
		
	}

	private void deleteParent(Parent parent, String parentType) {
		Date now = new Date();
		Integer userId = parent.getUserId();
		Integer schoolId = null;
		
		List<UserRole> userRoleList = userRoleService.findByUserId(userId);
		/**??????schoolUser??????*/
		SchoolUser schoolUser = schoolUserService.findSchoolUserListByUserIdAndType(userId, parentType);
		if(schoolUser!=null) {
			schoolId = schoolUser.getSchoolId();
			schoolUser.setIsDeleted(true);
			schoolUser.setModifyDate(now);
			schoolUserService.modify(schoolUser);
		}
		/**??????????????????*/
		Role role =roleService.findParentRoleBySchoolIdAndAppid(schoolId, 1);
		if(role!=null) {
			UserRole userRole = userRoleService.findByUserIdAndRoleId(userId, role.getId());
			if(userRole!=null) {
				userRole.setIsDeleted(true);
				userRole.setModifyDate(now);
				/**????????????*/
				userRoleService.modify(userRole);
			}
		}
		/**????????????*/
		parent.setIsDelete(true);
		parent.setModifyDate(now);
		parentDao.update(parent);
		
		Integer userRoleSize = userRoleList.size();
		/**?????????*/
		if(userRoleSize==1) {
			userService.removeAllById(userId, true);
		}
	}

	@Override
	public Map<String, Object> addParentFromExcelImport(String gradeName, String teamNumber, String studentName, Integer number, String parentName, String mobile, String relation, Integer schoolId, String schoolYear, Integer groupId, Integer appId) {
		Map<String, Object> entity = DataImportCheck.checkParentStudentData(gradeName, teamNumber, studentName, number, parentName, mobile);
		Integer status = (Integer) entity.get("status");
		if (status.intValue() == 2) {
			return entity;
		}

		Grade grade = gradeService.findGradeBySchoolIdYearAndName(schoolId, schoolYear, gradeName);
		if (grade == null) {
			entity.put("status", 2);
			entity.put("errorFiled", "gradeName");
			entity.put("errorInfo", "???????????????");
			return entity;
		}
		Integer gradeId = grade.getId();
		entity.put("gradeId", gradeId);

		Team team = teamService.findBySchoolGradeIdAndName(schoolId, gradeId, gradeName, teamNumber, schoolYear);
		if (team == null) {
			entity.put("status", 2);
			entity.put("errorFiled", "teamNumber");
			entity.put("errorInfo", "???????????????");
			return entity;
		}
		Integer teamId = team.getId();
		entity.put("teamId", teamId);

		Integer studentId = null;
		Integer studentUserId = null;
		TeamStudent teamStudent = teamStudentService.findByTeamIdAndNumber(teamId, number);
		if (teamStudent != null && teamStudent.getName().equals(studentName)) {
			studentId = teamStudent.getStudentId();
			studentUserId = teamStudent.getUserId();
			entity.put("studentId", studentId);
		} else {
			entity.put("status", 2);
			entity.put("errorFiled", "number???studentName");
			entity.put("errorInfo", "?????????????????????");
			return entity;
		}

		entity.put("relation", relation);
		if ("??????".equals(relation)) {
			relation = "51";
		} else if ("??????".equals(relation)) {
			relation = "52";
		} else {
			relation = "99";
		}

		String rank = "0";
		ParentStudent parentStudent = parentStudentService.findMainGuardian(studentUserId);
		if (parentStudent == null) {
			rank = "1";
		}
		entity.put("rank", rank);
		addParentFromExcelImport(schoolId, appId, gradeId, teamId, studentUserId, parentName, mobile, "3", relation, rank, null);

		return entity;
	}

	@Override
	public List<ParentStudent> findByUser(String stuName, String name, Integer state, String userName, String mobile, Page page) {
		return parentDao.findByUser( stuName,  name,  state,  userName,  mobile,  page);
	}
}
