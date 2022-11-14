package platform.education.rest.jw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Membership;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.MembershipService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;
import platform.education.generalTeachingAffair.vo.MembershipCondition;
import platform.education.generalcode.model.Item;
import platform.education.generalcode.service.JcGcCacheService;
import platform.education.im.model.AppAuthorization;
import platform.education.im.model.ImAccount;
import platform.education.im.model.ImProvider;
import platform.education.im.service.AppAuthorizationService;
import platform.education.im.service.ImAccountService;
import platform.education.im.service.ImProviderService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.MembershipRestService;
import platform.education.rest.jw.service.constant.MembershipConstants;
import platform.education.rest.jw.service.vo.DepartmentInfo;
import platform.education.rest.jw.service.vo.DepartmentTeacherInfo;
import platform.education.rest.jw.service.vo.MembershipInfo;
import platform.education.rest.jw.service.vo.MembershipSchoolInfo;
import platform.education.rest.jw.service.vo.ParentVo;
import platform.education.rest.jw.service.vo.TeacherVo;
import platform.education.rest.jw.service.vo.TeamParentInfo;
import platform.education.rest.jw.service.vo.TeamTeacherInfo;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;

public class MembershipRestServiceImpl implements MembershipRestService {

	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;

	@Autowired
	@Qualifier("membershipService")
	private MembershipService membershipService;

	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;

	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;

	@Autowired
	@Qualifier("departmentTeacherService")
	private DepartmentTeacherService departmentTeacherService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;

	@Autowired
	@Qualifier("teamUserService")
	private TeamUserService teamUserService;

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;

	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@Autowired
	@Qualifier("jcGcCacheService")
	private JcGcCacheService jcGcCacheService;
	
	//app授权
	@Autowired
	@Qualifier("appAuthorizationService")
	private AppAuthorizationService appAuthorizationService;
	
	//环信提供商
	@Resource
	private ImProviderService imProviderService;
	
	//环信账号	
	@Resource
	private ImAccountService imAccountService;
	
	@Override
	public Object getMembership(String appKey, Integer schoolId, Boolean isGetDep) {
		MembershipInfo topTree = null;
		
		isGetDep = isGetDep == null ? true : isGetDep;
		
		try {

			if (appKey == null || appKey == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			if (schoolId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			AppEdition app = this.appEditionService.findByAppKey(appKey);

			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			School school = schoolService.findSchoolById(schoolId);

			if (school == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("学校不存在,请确认");
				info.setMsg("不存在该学校");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			// 拿结构
			List<MembershipInfo> ztrListVo = new ArrayList<MembershipInfo>();
			// 只拿学校
			List<MembershipSchoolInfo> listVo = new ArrayList<MembershipSchoolInfo>();
			MembershipSchoolInfo msi = new MembershipSchoolInfo();

			// 根据传参schoolId获取多校区的信息
			MembershipCondition membershipCondition = new MembershipCondition();
			membershipCondition.setOwnerId(schoolId + "");
			membershipCondition.setOwnerType(MembershipConstants.Type_Branch_School);
			List<Membership> membershipList = membershipService.findMembershipByCondition(membershipCondition);
			if (membershipList != null && membershipList.size() > 0) {
				Membership ms = membershipList.get(0);
				MembershipCondition mc = new MembershipCondition();
				mc.setParentId(ms.getParentId());
				mc.setOwnerType(MembershipConstants.Type_Branch_School);
				List<Membership> membershipAll = membershipService.findMembershipByCondition(mc);
				MembershipInfo zi = null;

				if (membershipAll != null && membershipAll.size() > 0) {
					DepartmentCondition departmentCondition = null;
					for (Membership m : membershipAll) {
						if (!isGetDep) {
							msi = new MembershipSchoolInfo();
							msi.setId(Integer.parseInt(m.getOwnerId()));
							msi.setName(m.getName());
							msi.setOwnerId(m.getOwnerId());
							msi.setOwnnerType(m.getOwnerType());
							listVo.add(msi);
						} else {
							departmentCondition = new DepartmentCondition();
							departmentCondition.setParentId(0);
							departmentCondition.setSchoolId(Integer.parseInt(m.getOwnerId()));
							List<Department> depList = departmentService.findDepartmentByCondition(departmentCondition, null, null);
							zi = new MembershipInfo();
							zi.setId(m.getId());
							zi.setName(m.getName());
							zi.setOwnerId(m.getOwnerId());
							zi.setOwnnerType(MembershipConstants.Type_Branch_School);
							zi.setChildren(dep2Tree(depList));
							ztrListVo.add(zi);
						}
					}
				}
			} else {
				// 单个学校节点
				if (!isGetDep) {
					School m = schoolService.findSchoolById(schoolId);
					msi = new MembershipSchoolInfo();
					msi.setId(m.getId());
					msi.setName(m.getName());
					msi.setOwnerId(m.getId() + "");
					msi.setOwnnerType(MembershipConstants.Type_General_School);
					listVo.add(msi);
				} else {
					DepartmentCondition departmentCondition = new DepartmentCondition();
					departmentCondition.setSchoolId(schoolId);
					departmentCondition.setParentId(0);
					departmentCondition.setIsDelete(false);
					List<Department> depList = departmentService.findDepartmentByCondition(departmentCondition, null, null);

					ztrListVo = dep2Tree(depList);
				}
			}

			// 创建顶级节点
			if (!isGetDep) {
				return new ResponseVo<List<MembershipSchoolInfo>>("0", listVo);
			} else {
				topTree = new MembershipInfo();
				if (membershipList != null && membershipList.size() > 0) {
					Membership membership = membershipService.findMembershipById(membershipList.get(0).getParentId());
					if (membership != null) {
						topTree.setId(membership.getId());
						topTree.setName(membership.getName());
						topTree.setOwnerId(membership.getId() + "");
					}
					topTree.setOwnnerType(MembershipConstants.Type_General_School);
				} else {
					topTree.setId(schoolId);
					topTree.setName(school.getName());
					topTree.setOwnerId(school.getId() + "");
					topTree.setOwnnerType(MembershipConstants.Type_School);
				}

				topTree.setChildren(ztrListVo);
			}
		} catch (IllegalArgumentException e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不合法");
			info.setMsg("非法参数");
			info.setParam("appKey，schoolId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		return new ResponseVo<MembershipInfo>("0", topTree);
	}

	// 构造部门树
	public List<MembershipInfo> dep2Tree(List<Department> topDepts) {
		List<MembershipInfo> ztrListVo = new ArrayList<MembershipInfo>();
		MembershipInfo pvo = null;
		for (Department dp : topDepts) {
			List<Department> departments = departmentService.findByParentId(dp.getId(), new Page());

			pvo = new MembershipInfo();
			pvo.setId(dp.getId());
			pvo.setName(dp.getName());
			pvo.setOwnerId(dp.getId() + "");
			pvo.setOwnnerType(MembershipConstants.Type_Dept);
			pvo.setChildren(dep2Tree(departments));

			ztrListVo.add(pvo);
		}
		return ztrListVo;
	}

	@Override
	public Object getDepartmentTeacher(String appKey, Integer departmentId) {
		Teacher teacher = null;
		DepartmentTeacherInfo dti = null;
		List<DepartmentTeacherInfo> dtiList = new ArrayList<DepartmentTeacherInfo>();
		try {
			if (appKey == null || appKey == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			if (departmentId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("departmentId参数必填");
				info.setMsg("departmentId参数不能为空");
				info.setParam("departmentId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			AppEdition app = this.appEditionService.findByAppKey(appKey);

			//获取默认的环信提供商
			ImProvider ip = this.imProviderService.findDefaultProvider();
			
			//获取app授权
			AppAuthorization appAuth = this.appAuthorizationService.findByAppKey(appKey);
			
			
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			Department department = departmentService.findDepartmentById(departmentId);

			if (department == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("部門不存在,请确认");
				info.setMsg("不存在该部門");
				info.setParam("departmentId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			List<DepartmentTeacher> departmentTeacherList = departmentTeacherService.findByDepartmentId(departmentId);

			if (departmentTeacherList != null && departmentTeacherList.size() > 0) {
				for (DepartmentTeacher departmentTeacher : departmentTeacherList) {
					if (departmentTeacher != null && departmentTeacher.getId() != null) {
						teacher = teacherService.findTeacherById(departmentTeacher.getTeacherId());
						if (teacher != null && teacher.getId() != null) {
							//查询环信账号
							ImAccount ia = this.imAccountService.findByUserIdAndAppKey(teacher.getUserId(), ip!=null?ip.getImType():"1", appAuth != null ? appAuth.getImAccountApp() : appKey);
							dti = new DepartmentTeacherInfo();
							dti.setIcon(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
							dti.setMobile(teacher.getMobile());
							dti.setName(teacher.getName());
							dti.setPosition(teacher.getPosition());
							dti.setSex(teacher.getSex());
							dti.setTeacherId(teacher.getId());
							dti.setTelephone(teacher.getTelephone());
							dti.setUserId(teacher.getUserId());
							dti.setUserName(teacher.getUserName());
							//获取环信账号
							dti.setImAccount(ia!=null?ia.getAccountName():"");
							dtiList.add(dti);
						}
					}
				}
			}

			if (dtiList.size() == 0) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("查詢不到数据");
				info.setMsg("根据部门ID查询不到任何数据");
				info.setParam("departmentId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		} catch (IllegalArgumentException e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("參數異常");
			info.setMsg("參數異常");
			info.setParam("(String)appKey,(Intrger)departmentId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		return new ResponseVo<List<DepartmentTeacherInfo>>("0", dtiList);
	}

	@Override
	public Object getSchoolDepartmentTeachers(String appKey, Integer schoolId) {
		DepartmentInfo depInfo = null;
		DepartmentTeacherInfo depTeaInfo = null;
		List<DepartmentTeacherInfo> teachers = null;
		List<DepartmentInfo> depInfoList = new ArrayList<DepartmentInfo>();
		;
		try {
			if (appKey == null || appKey == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			if (schoolId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			AppEdition app = this.appEditionService.findByAppKey(appKey);
			
			//获取默认的环信提供商
			ImProvider ip = this.imProviderService.findDefaultProvider();
			
			//获取app授权
			AppAuthorization appAuth = this.appAuthorizationService.findByAppKey(appKey);

			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			School school = schoolService.findSchoolById(schoolId);

			if (school == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("学校不存在,请确认");
				info.setMsg("不存在该学校");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			// 获取到所有的部门
			List<Department> departmentList = departmentService.findDepartmentBySchoolId(schoolId, null, null);

			if (departmentList != null && departmentList.size() > 0) {
				for (Department department : departmentList) {
					if (department != null && department.getId() != null) {
						teachers = new ArrayList<DepartmentTeacherInfo>();

						depInfo = new DepartmentInfo();
						depInfo.setDepartmentId(department.getId());
						depInfo.setDepartmentName(department.getName());

						List<DepartmentTeacherVo> departmentTeacherVoList = departmentTeacherService
								.findDepartmentTeacherVoByDepartmentId(department.getId());
						if (departmentTeacherVoList != null && departmentTeacherVoList.size() > 0) {
							for (DepartmentTeacherVo dep : departmentTeacherVoList) {
								Teacher teacher = teacherService.findTeacherById(dep.getTeacherId());
								if (teacher != null) {
									//查询环信账号
									ImAccount ia = this.imAccountService.findByUserIdAndAppKey(teacher.getUserId(), ip!=null?ip.getImType():"1", appAuth != null ? appAuth.getImAccountApp() : appKey);
									
									depTeaInfo = new DepartmentTeacherInfo();
									depTeaInfo.setIcon(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
									depTeaInfo.setMobile(teacher.getMobile());
									depTeaInfo.setUserId(teacher.getUserId());
									depTeaInfo.setName(teacher.getName());
									depTeaInfo.setPosition(teacher.getPosition());
									depTeaInfo.setSex(teacher.getSex());
									depTeaInfo.setTeacherId(teacher.getId());
									depTeaInfo.setTelephone(teacher.getTelephone());
									depTeaInfo.setUserId(teacher.getUserId());
									depTeaInfo.setUserName(teacher.getUserName());
									depTeaInfo.setImAccount(ia!=null?ia.getAccountName():"");
									teachers.add(depTeaInfo);
								}
							}
						}
						depInfo.setTeachers(teachers);
					}
					depInfoList.add(depInfo);
				}
			}
		} catch (IllegalArgumentException e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("參數異常");
			info.setMsg("參數異常");
			info.setParam("(String)appKey,(Intrger)schoolId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		return new ResponseVo<List<DepartmentInfo>>("0", depInfoList);
	}

	@Override
	public Object getTeamTeachers(String appKey, String teamIds) {
		TeamTeacherInfo teamInfo = null;
		List<TeamTeacherInfo> teamInfoList = new ArrayList<TeamTeacherInfo>();
		TeacherVo teacherVo = null;
//		List<TeacherVo> teacherVoList = null;
		Map<Integer,TeacherVo>teacherVoMap = null;
		

		try {
			if (appKey == null || appKey == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			if (teamIds == null || teamIds == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamIds参数必填");
				info.setMsg("teamIds参数不能为空");
				info.setParam("teamIds");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			AppEdition app = this.appEditionService.findByAppKey(appKey);

			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			//获取默认的环信提供商
			ImProvider ip = this.imProviderService.findDefaultProvider();
			
			//获取app授权
			AppAuthorization appAuth = this.appAuthorizationService.findByAppKey(appKey);
			
			String[] teamIdArr = teamIds.split(",");
			Team team = null;
			if (teamIdArr != null && teamIdArr.length > 0) {
				for (String teamId : teamIdArr) {
					team = teamService.findTeamById(Integer.parseInt(teamId));
					if (team != null) {
//						teacherVoList = new ArrayList<TeacherVo>();
						teacherVoMap = new HashMap();
						
						teamInfo = new TeamTeacherInfo();
						teamInfo.setTeamId(team.getId());
						teamInfo.setTeamName(team.getName());
						List<Teacher> teacherList = teacherService.findTeacherListByTeamId(Integer.parseInt(teamId));
						for (Teacher teacher : teacherList) {
							if (teacher != null && teacher.getId() != null) {
								//查询环信账号
								ImAccount ia = this.imAccountService.findByUserIdAndAppKey(teacher.getUserId(), ip!=null?ip.getImType():"1", appAuth != null ? appAuth.getImAccountApp() : appKey);
								teacherVo = new TeacherVo();
								teacherVo.setName(teacher.getName());
								teacherVo.setUserId(teacher.getUserId());
								teacherVo.setUserName(teacher.getUserName());
								teacherVo.setMobile(teacher.getMobile());
								teacherVo.setUserIcon(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
								teacherVo.setImAccount(ia!=null?ia.getAccountName():"");
//							    teacherVoList.add(teacherVo);
								teacherVoMap.put(teacherVo.getUserId(), teacherVo);
									
							}
						}
//						teamInfo.setTeachers(teacherVoList);
						teamInfo.setTeachers(new ArrayList(teacherVoMap.values()));
						teamInfoList.add(teamInfo);
					}
				}
			}
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("參数異常");
			info.setMsg("參数異常");
			info.setParam("(String)appKey,(String)teamIds");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		return new ResponseVo<List<TeamTeacherInfo>>("0", teamInfoList);
	}

	@Override
	public Object getTeamParents(String appKey, String teamIds) {
		TeamParentInfo teamParentInfo = null;
		List<TeamParentInfo> teamParentInfoList = new ArrayList<TeamParentInfo>();
		ParentVo parentVo = null;
		List<ParentVo> parentVoList = null;
		try {

			if (appKey == null || appKey == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			if (teamIds == null || teamIds == "") {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamIds参数必填");
				info.setMsg("teamIds参数不能为空");
				info.setParam("teamIds");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			AppEdition app = this.appEditionService.findByAppKey(appKey);

			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			//获取默认的环信提供商
			ImProvider ip = this.imProviderService.findDefaultProvider();
			
			//获取app授权
			AppAuthorization appAuth = this.appAuthorizationService.findByAppKey(appKey);
			
			String[] teamIdArr = teamIds.split(",");
			Team team = null;
			Parent parent = null;
			List<ParentStudent> parentStudentList = null;
			List<Student> studentList = null;
			List<Item> list = jcGcCacheService.findItemsByTableCode("GB-JTGX");
			Map<String, String> map = new HashMap<String, String>();
			for (Item item : list) {
				map.put(item.getValue(), item.getName());
			}
			if (teamIdArr != null && teamIdArr.length > 0) {
				for (String teamId : teamIdArr) {
					team = teamService.findTeamById(Integer.parseInt(teamId));
					if (team != null) {

						teamParentInfo = new TeamParentInfo();
						teamParentInfo.setTeamId(team.getId());
						teamParentInfo.setTeamName(team.getName());
						parentVoList = new ArrayList<ParentVo>();

						studentList = studentService.findStudentByTeamId(team.getId());
						if (studentList != null && studentList.size() > 0) {
							for (Student student : studentList) {
								parentStudentList = parentStudentService
										.findParentStudentByStudentUserId(student.getUserId());
								if (parentStudentList != null && parentStudentList.size() > 0) {
									for (ParentStudent parentStudent : parentStudentList) {
										if (parentStudent != null && parentStudent.getId() != null) {
											//查询im账号
											ImAccount ia = this.imAccountService.findByUserIdAndAppKey(parentStudent.getParentUserId(), ip!=null?ip.getImType():"1", appAuth != null ? appAuth.getImAccountApp() : appKey);
											parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
											parentVo = new ParentVo();
											parentVo.setMobile(parent.getMobile());
											parentVo.setUserName(parent.getUserName());
											parentVo.setParentName(parent.getName());
											parentVo.setParentUserId(parent.getUserId());
											parentVo.setStudentName(student.getName());
											parentVo.setStudentUserId(student.getUserId());
											parentVo.setImAccount(ia!=null?ia.getAccountName():"");
											if (map != null) {
												String viewName = student.getName() + "的家长("
														+ map.get(parentStudent.getParentRelation()) + ")";
												parentVo.setViewName(viewName);
											} else {
												parentVo.setViewName(student.getName() + "的家长(未说明)");
											}
											parentVo.setParentIcon(
													ImgUtil.getImgSrc(parent.getUserId(), profileService));
											parentVoList.add(parentVo);
										}
									}
								}
							}
						}
						teamParentInfo.setParents(parentVoList);
						teamParentInfoList.add(teamParentInfo);
					}
				}
			}
		} catch (IllegalArgumentException e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("參數異常");
			info.setMsg("參數異常");
			info.setParam("(String)appKey,(String)teamIds");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		return new ResponseVo<List<TeamParentInfo>>("0", teamParentInfoList);
	}

}
