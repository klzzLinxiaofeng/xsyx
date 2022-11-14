package platform.education.generalTeachingAffair.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.TeamStudentInfo;
import platform.education.generalTeachingAffair.contants.StudentAlterationContants;
import platform.education.generalTeachingAffair.contants.StudentArchiveContants;
import platform.education.generalTeachingAffair.contants.StudentContants;
import platform.education.generalTeachingAffair.dao.FamilyMemberDao;
import platform.education.generalTeachingAffair.dao.StudentArchiveDao;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.DataImportCheck;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.contants.*;
import platform.education.user.model.*;
import platform.education.user.service.*;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.education.user.vo.UserBindingCondition;
import platform.education.user.vo.UserResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentServiceImpl implements StudentService {

    private String defaultPassword = "123456";

    private String studentType = "4";

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String DEFAULT_PASSWORD = "123456";

    private static final int SYSTEM_APP_ID = 1;

    private static final String USER_TYPE_STUDENT = "4";

    // 学生
    private StudentDao studentDao;

    // 学籍扩充表
    private StudentArchiveDao studentArchiveDao;

    private ParentService parentService;

    private ParentStudentService parentStudentService;

    private PersonService personService;

    @Autowired
    private BasicSQLService basicSQLService;


//	private TeamStudentDao teamStudentDao;

    private ParentProxyService parentProxyService;

    private UserBindingService userBindingService;

    private SchoolUserService schoolUserService;

    private TeamStudentService teamStudentService;

    private UserService userService;

    private GroupUserService groupUserService;

    private RoleService roleService;

    private TeamService teamService;

    private GroupService groupService;

    private AccountService accountService;

    private AppUserService appUserService;

    private UserRegionService userRegionService;

    private UserRoleService userRoleService;

    private ProfileService profileService;

    private JobControlService jobControlService;

    private GradeService gradeService;

    private FamilyMemberService familyMemberService;

    private StringRedisTemplate stringRedisTemplate;

    private FamilyMemberDao familyMemberDao;



    public void setFamilyMemberDao(FamilyMemberDao familyMemberDao) {
        this.familyMemberDao = familyMemberDao;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }


    private TeamUserService teamUserService;

    private StudentAlterationService studentAlterationService;

    private platform.education.generalcode.service.GradeService jcGradeService;


    public void setStudentAlterationService(StudentAlterationService studentAlterationService) {
        this.studentAlterationService = studentAlterationService;
    }

    // ======2016-7-21===========
    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public void setTeamUserService(TeamUserService teamUserService) {
        this.teamUserService = teamUserService;
    }

    public void setFamilyMemberService(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    public void setJobControlService(JobControlService jobControlService) {
        this.jobControlService = jobControlService;
    }

    public void setTeamStudentService(TeamStudentService teamStudentService) {
        this.teamStudentService = teamStudentService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setGroupUserService(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

//	public void setTeamStudentDao(TeamStudentDao teamStudentDao) {
//		this.teamStudentDao = teamStudentDao;
//	}

    public void setParentProxyService(ParentProxyService parentProxyService) {
        this.parentProxyService = parentProxyService;
    }

    public void setUserBindingService(UserBindingService userBindingService) {
        this.userBindingService = userBindingService;
    }

    public void setSchoolUserService(SchoolUserService schoolUserService) {
        this.schoolUserService = schoolUserService;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public void setStudentArchiveDao(StudentArchiveDao studentArchiveDao) {
        this.studentArchiveDao = studentArchiveDao;
    }

    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    public void setParentStudentService(
            ParentStudentService parentStudentService) {
        this.parentStudentService = parentStudentService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public void setUserRegionService(UserRegionService userRegionService) {
        this.userRegionService = userRegionService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

//	public void setUserRegionDao(UserRegionDao userRegionDao) {
//		this.userRegionDao = userRegionDao;
//	}
//
//	public void setUserRoleDao(UserRoleDao userRoleDao) {
//		this.userRoleDao = userRoleDao;
//	}
//
//	public void setUserBindingDao(UserBindingDao userBindingDao) {
//		this.userBindingDao = userBindingDao;
//	}
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//	public void setAccountDao(AccountDao accountDao) {
//		this.accountDao = accountDao;
//	}
//
//	public void setGroupDao(GroupDao groupDao) {
//		this.groupDao = groupDao;
//	}
//
//	public void setGroupUserDao(GroupUserDao groupUserDao) {
//		this.groupUserDao = groupUserDao;
//	}
//
//	public void setProfileDao(ProfileDao profileDao) {
//		this.profileDao = profileDao;
//	}
//
//	public void setAppUserDao(AppUserDao appUserDao) {
//		this.appUserDao = appUserDao;
//	}

    @Override
    public Student findStudentById(Integer id) {
        try {
            return studentDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public Student findStudentByName(String name) {
        try {
            return studentDao.findByName(name);
        } catch (Exception e) {
            log.info("数据库无存在为 {} ", name);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student add(Student student) {
        if (student == null) {
            return null;
        }
        Date date = new Date();
        student.setCreateDate(date);
        student.setModifyDate(date);
        return studentDao.create(student);
    }

    @Override
    public Student modify(Student student) {
        student.setModifyDate(new Date());
        return studentDao.update(student);
    }

    @Override
    public Integer updateTeamStudent(Integer teamId, Integer studentId) {
        return studentDao.updateTeamStudent2(teamId,studentId);
    }

    @Override
    public void remove(Student student) {
        try {
            studentDao.delete(student);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("删除数据库无存在ID为 {} ");
        }

    }

    @Override
    public List<Student> findStudentOfTeam(Integer teamId) {
        return studentDao.findStudentOfTeam(teamId);
    }

    @Override
    public List<Student> findStudentOfTeam2(Integer teamId,Integer gardeId) {
        return studentDao.findStudentOfTeam2(teamId,gardeId);
    }

    @Override
    public List<Student> findStudentByCondition(
            StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findStudentByCondition(studentCondition, page, order);
    }
    @Override
    public List<Student> findStudentByTeacherIdCondition(
            StudentCondition studentCondition, Page page, Order order, Integer teacherId) {
        return studentDao.findStudentByTeacherIdCondition(studentCondition, teacherId, page, order);
    }

    @Override
    public List<Student> findStudentByParent(ParentCondition parentCondition,
                                             Page page, Order order) {
        return studentDao.findStudentByParent(parentCondition, page, order);
    }

    @Override
    public List<Student> findStudentUniqByCondition(
            StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findStudentUniqByCondition(studentCondition, page,
                order);
    }

    @Override
    public List<Student> findStudentHasTeamByCondition(StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findStudentHasTeamByCondition(studentCondition, page,
                order);
    }

    @Override
    public List<Student> findTeamStudentByCondition(
            StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findTeamStudentByCondition(studentCondition, page,
                order);
    }

    @Override
    public List<Student> findStudentIsNotTeamBySchoolId(Integer schoolId,
                                                        String sex) {
        return studentDao.findStudentIsNotTeamBySchoolId(schoolId, sex);
    }

    @Override
    public Student modifyStudentSetTeamIsNull(Student student) {
        return studentDao.updateStudentSetTeamIsNull(student);
    }

    @Override
    public Integer findUniqueStudentId(Integer userId, Integer schoolId) {
        return this.studentDao.findUniqueStudentId(userId, schoolId);
    }

    @Override
    public Student findOfUser(Integer schoolId, Integer userId) {
        return studentDao.findOfUser(schoolId, userId);
    }

    @Override
    public List<Student> findStudentByTeamId(Integer teamId) {
        return studentDao.findStudentByTeamId(teamId);
    }

    /**
     * @author Mr.Chan
     */
    @Override
    public Student addStudent(Student student, User user, Role role,
                              Integer groupId, Integer appId, String regionCode, String level,
                              Integer createUserId) {
        Student tmp = null;
        try {
            if (student == null) {
                throw new IllegalArgumentException("Student is not null");
            }
            if (user == null) {
                throw new IllegalArgumentException("User is not null");
            }
            if (groupId == null) {
                throw new IllegalArgumentException("groupId is not null");
            }
            if (appId == null) {
                throw new IllegalArgumentException("appId is not null");
            }
            boolean isNew = true;
            boolean isBinding = false;
            Date date = new Date();
            String customUsername = user.getUserName();
            if (customUsername != null) {
                isNew = true;
                UserBinding ub = this.userBindingService
                        .findByBindingName(customUsername);
                if (ub != null) {
                    Integer userId = ub.getUserId();
                    User persistentUser = this.userService.findUserById(userId);
                    isNew = false;
                    user = persistentUser;
                } else {
                    isBinding = true;
                }
            }
            if (isNew) {
                Account account = this.accountService.getAccountByRandom(appId);
                if (account != null) {
                    String username = account.getUserName();
                    User flag = this.userService.findUserByUsername(username);
                    if (flag != null) {
                        throw new RuntimeException("UserName is exist");
                    }
                } else {
                    throw new RuntimeException("No assignable userName");
                }
                user.setUserName(account.getUserName());
                account.setToAppId(appId);
                account.setState(AccountContants.STATE_TYPE_USED);
                account.setApplyDate(date);
                this.accountService.modify(account);

                user.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder()
                        .encodePassword(user.getPassword()));
                user.setCreateDate(date);
                user = this.userService.add(user);
                Group group = this.groupService.findGroupById(groupId);
                if (group == null) {
                    throw new RuntimeException("学校未创建对应的group映射对象");
                }
                // 建立组跟用户关联
                GroupUser groupUser = new GroupUser();
                groupUser.setState(user.getState());
                groupUser.setUserId(user.getId());
                groupUser.setGroupId(group.getId());
                groupUser.setCreateDate(date);
                groupUser = this.groupUserService.add(groupUser);
                if (groupUser == null) {
                    throw new RuntimeException("创建用户与组的关联出现异常");
                }
                // 建立用户跟app关联
                AppUser appUser = new AppUser();
                appUser.setAppId(appId);
                appUser.setState(user.getState());
                appUser.setUserId(user.getId());
                appUser.setCreateDate(date);
                appUser = appUserService.add(appUser);
                if (appUser == null) {
                    throw new RuntimeException("创建用户与App的关联出现异常");
                }

                // 默认绑定原始账号
                UserBinding defUserBinding = new UserBinding();
                defUserBinding.setBindingName(user.getUserName());
                defUserBinding
                        .setBindingType(UserBindingContants.TYPE_ORIGINAL);
                defUserBinding.setUserId(user.getId());
                defUserBinding.setEnabled(true);
                defUserBinding.setCreateDate(date);
                defUserBinding = this.userBindingService.add(defUserBinding);

                if (isBinding) {
                    UserBinding bindUserInput = new UserBinding();
                    bindUserInput.setBindingName(customUsername);
                    bindUserInput
                            .setBindingType(UserBindingContants.TYPE_CUSTOM);
                    bindUserInput.setUserId(user.getId());
                    bindUserInput.setEnabled(true);
                    bindUserInput.setCreateDate(date);
                    this.userBindingService.add(bindUserInput);
                }
            }

            // 用户区域
            UserRegion userRegion = new UserRegion();
            userRegion.setAppId(appId);
            userRegion.setUserId(user.getId());
            userRegion.setRegionCode(regionCode);
            userRegion.setLevel(level);
            userRegion.setCreateUserId(createUserId);
            userRegion.setCreateDate(date);
            userRegion = this.userRegionService.add(userRegion);
            if (userRegion == null) {
                throw new IllegalArgumentException("添加学生所属区域异常");
            }

            // 增加学生
            student.setUserId(user.getId());
            student.setUserName(user.getUserName());
            student.setCreateDate(date);
            tmp = studentDao.create(student);
            if (tmp == null) {
                throw new IllegalArgumentException("添加学生信息异常");
            }

            // 新增角色
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setPriority(1);
            userRole.setCreateDate(date);
            UserRole ur = this.userRoleService.add(userRole);
            if (ur == null) {
                throw new IllegalArgumentException("添加角色异常");
            }
        } catch (Exception e) {
            throw new RuntimeException("添加学生信息失败 ：" + student, e);
        }
        return tmp;
    }

    /**
     * @author Mr.Chan
     */
    @Override
    public Student addStudent(Student student, User user, Role role,
                              Profile profile, Integer groupId, Integer appId, String regionCode,
                              String level, Integer createUserId) {
        Student tmp = null;
        try {
            if (student == null) {
                throw new IllegalArgumentException("Student is not null");
            }
            if (user == null) {
                throw new IllegalArgumentException("User is not null");
            }
            if (groupId == null) {
                throw new IllegalArgumentException("groupId is not null");
            }
            if (appId == null) {
                throw new IllegalArgumentException("appId is not null");
            }
            if (profile == null) {
                throw new IllegalArgumentException("profile is not null");
            }
            boolean isNew = true;
            boolean isBinding = false;
            Date date = new Date();
            String customUsername = user.getUserName();
            if (customUsername != null) {
                isNew = true;
                UserBinding ub = this.userBindingService
                        .findByBindingName(customUsername);
                if (ub != null) {
                    Integer userId = ub.getUserId();
                    User persistentUser = this.userService.findUserById(userId);
                    isNew = false;
                    user = persistentUser;
                } else {
                    isBinding = true;
                }
            }
            if (isNew) {
                Account account = this.accountService.getAccountByRandom(appId);
                if (account != null) {
                    String username = account.getUserName();
                    User flag = this.userService.findUserByUsername(username);
                    if (flag != null) {
                        throw new RuntimeException("UserName is exist");
                    }
                } else {
                    throw new RuntimeException("No assignable userName");
                }
                user.setUserName(account.getUserName());
                account.setToAppId(appId);
                account.setState(AccountContants.STATE_TYPE_USED);
                account.setApplyDate(date);
                this.accountService.modify(account);

                user.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder()
                        .encodePassword(user.getPassword()));
                user.setCreateDate(date);
                user = this.userService.add(user);
                Group group = this.groupService.findGroupById(groupId);
                if (group == null) {
                    throw new RuntimeException("学校未创建对应的group映射对象");
                }
                // 建立组跟用户关联
                GroupUser groupUser = new GroupUser();
                groupUser.setState(user.getState());
                groupUser.setUserId(user.getId());
                groupUser.setGroupId(group.getId());
                groupUser.setCreateDate(date);
                groupUser = this.groupUserService.add(groupUser);
                if (groupUser == null) {
                    throw new RuntimeException("创建用户与组的关联出现异常");
                }
                // 建立用户跟app关联
                AppUser appUser = new AppUser();
                appUser.setAppId(appId);
                appUser.setState(user.getState());
                appUser.setUserId(user.getId());
                appUser.setCreateDate(date);
                appUser = appUserService.add(appUser);
                if (appUser == null) {
                    throw new RuntimeException("创建用户与App的关联出现异常");
                }

                // 默认绑定原始账号
                UserBinding defUserBinding = new UserBinding();
                defUserBinding.setBindingName(user.getUserName());
                defUserBinding
                        .setBindingType(UserBindingContants.TYPE_ORIGINAL);
                defUserBinding.setUserId(user.getId());
                defUserBinding.setEnabled(true);
                defUserBinding.setCreateDate(date);
                defUserBinding = this.userBindingService.add(defUserBinding);

                if (isBinding) {
                    UserBinding bindUserInput = new UserBinding();
                    bindUserInput.setBindingName(customUsername);
                    bindUserInput
                            .setBindingType(UserBindingContants.TYPE_CUSTOM);
                    bindUserInput.setUserId(user.getId());
                    bindUserInput.setEnabled(true);
                    bindUserInput.setCreateDate(date);
                    this.userBindingService.add(bindUserInput);
                }
            }

            // 用户区域
            UserRegion userRegion = new UserRegion();
            userRegion.setAppId(appId);
            userRegion.setUserId(user.getId());
            userRegion.setRegionCode(regionCode);
            userRegion.setLevel(level);
            userRegion.setCreateUserId(createUserId);
            userRegion.setCreateDate(date);
            userRegion = this.userRegionService.add(userRegion);
            if (userRegion == null) {
                throw new IllegalArgumentException("添加学生所属区域异常");
            }

            // 增加学生
            student.setUserId(user.getId());
            student.setUserName(user.getUserName());
            student.setCreateDate(date);
            tmp = studentDao.create(student);
            if (tmp == null) {
                throw new IllegalArgumentException("添加学生信息异常");
            }

            // 新增角色
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setPriority(1);
            userRole.setCreateDate(date);
            UserRole ur = this.userRoleService.add(userRole);
            if (ur == null) {
                throw new IllegalArgumentException("添加角色异常");
            }

            // 新增个人信息
            profile.setId(null);
            profile.setUserId(user.getId());
            profile.setUserName(user.getUserName());
            profile.setIsDeleted(false);
            profile.setCreateDate(date);
            profile.setModifyDate(date);
            Profile pf = this.profileService.add(profile);
            if (pf == null) {
                throw new IllegalArgumentException("添加个人信息异常");
            }
        } catch (Exception e) {
            throw new RuntimeException("添加学生信息失败 ：" + student, e);
        }
        return tmp;
    }

    @Override
    public List<Student> findStudentByOnlyCondition(
            StudentCondition studentCondition, Page page, Order order) {
        return this.studentDao.findStudentByOnlyCondition(studentCondition,
                page, order);
    }

    @Override
    public List<Student> findStudentBySchoolRegion(SchoolCondition condition,
                                                   Group group, Page page, Order order) {
        return this.studentDao.findStudentBySchoolRegion(condition, group,
                page, order);
    }

    @Override
    public String findStudentNumBySchoolId(Integer schoolId) {
        Student student = studentDao.findStudentNumBySchoolId(schoolId);
        return student == null ? "0" : student.getStuNum();
    }

    @Override
    public List<Student> findStudentListByListId(List<Integer> idList,
                                                 String type, Integer schoolId) {
        List<Student> studentList = null;
        int size = idList.size();
        String[] array = new String[size];
        for (int i = 0; i < idList.size(); i++) {
            array[i] = idList.get(i).toString();
        }

        if (type == "1" || "1".equals(type)) {
            studentList = studentDao.findStudentOfTeamIdList(array, schoolId);
        } else if (type == "2" || "2".equals(type)) {
            studentList = studentDao.findStudentOfGradeIdList(array, schoolId);
        } else {
            studentList = new ArrayList<Student>();
        }
        return studentList;
    }

    @Override
    public List<Student> findStudentByTeam(StudentCondition studentCondition,
                                           Page page, Order order) {
        return studentDao.findStudentByTeam(studentCondition, page, order);
    }

    @Override
    public List<Student> findStudentByTeam(StudentCondition studentCondition,
                                           Page page) {
        return studentDao.findStudentByTeam(studentCondition, page, null);
    }

    @Override
    public List<Student> findStudentByTeam(StudentCondition studentCondition) {
        return studentDao.findStudentByTeam(studentCondition, null, null);
    }

    @Override
    public List<Student> findStudentByTeam(StudentCondition studentCondition,
                                           Order order) {
        return studentDao.findStudentByTeam(studentCondition, null, order);
    }

    @Override
    public List<Student> findStudentListBySchoolId(Integer schoolId) {
        return studentDao.findStudentListBySchoolId(schoolId);
    }

    @Override
    public Student findStudentByUserId(Integer userId) {
        return studentDao.findStudentByUserId(userId);
    }

    /**
     * 功能描述：用于学生统计 2015-11-5
     */
    @Override
    public Long count() {
        return this.studentDao.count(null);
    }

    /**
     * 功能描述：用于学生统计 2015-11-5
     */
    @Override
    public Long count(StudentCondition studentCondition) {
        return this.studentDao.count(studentCondition);
    }

    @Override
    public StudentVo findStudentNumberOfRaceData(Integer schoolId) {
        return this.studentDao.findStudentNumberOfRaceData(schoolId);
    }

    @Override
    public List<Student> findStudentByTeamId(Integer teamId, Page page,
                                             Order order) {
        return this.studentDao.findStudentByTeamId(teamId, page, order);
    }

    @Override
    public String findNoPhotoName(Integer teamId) {
        return this.studentDao.findNoPhotoName(teamId);
    }

    @Override
    public String findHasPhotoName(Integer teamId) {
        return this.studentDao.findHasPhotoName(teamId);
    }

    @Override
    public Long countByTeamId(Integer teamId) {
        return this.studentDao.countByTeamId(teamId);
    }

    @Override
    public Long countNoPhotoTeamId(Integer teamId) {
        return this.studentDao.countNoPhotoTeamId(teamId);
    }

    /**
     * @param studentId
     * @return
     * @function 根据学生ID获取学生的基本信息
     * @date 2016-7-21
     */
    @Override
    public StudentArchiveComplete getStudentArchiveComplete(Integer studentId) {

        StudentArchiveComplete studentArchiveComplete = new StudentArchiveComplete();

        StudentArchiveComplete.Basic basic = studentArchiveComplete.new Basic();

        StudentArchiveComplete.Extra extra = studentArchiveComplete.new Extra();

        StudentArchiveComplete.Parent parent = studentArchiveComplete.new Parent();

        StudentArchiveComplete.Remarks remarks = studentArchiveComplete.new Remarks();

        StudentArchiveComplete.Resumes resumes = studentArchiveComplete.new Resumes();

        StudentArchiveComplete.Traffic traffic = studentArchiveComplete.new Traffic();

        StudentArchiveComplete.Archive archive = studentArchiveComplete.new Archive();

        StudentArchiveComplete.Assist assist = studentArchiveComplete.new Assist();

        StudentArchiveComplete.Relation relation = studentArchiveComplete.new Relation();

        if (studentId == null) {
            return studentArchiveComplete;
        }

        Student student = studentDao.findById(studentId);
//		ParentStudent parentStudent = null;
//		ParentMess parentMess = null;
        List<ParentMess> parentMessList = new ArrayList<ParentMess>();
//		Parent par = null;
        Person person = null;
//		Person personOfParent = null;
        StudentArchive studentArchive = null;
        TeamStudent teamStudent = null;
        if (student != null) {
            person = personService.findPersonById(student.getPersonId());
            studentArchive = studentArchiveDao.findByStudentId(studentId);

            //2017-8-14	 家庭成员信息从family_member中拿，仅作为档案内容录入，不与平台家长用户信息做直接关联
            parentMessList = familyMemberService.findParentMessByStudentId(studentId);
            parent.setParentMess(parentMessList);
//			List<ParentStudent> list = parentStudentService.findParentStudentByStudentUserId(student.getUserId());
//			if (list != null && list.size() > 0) {
//				for (int i = 0; i < list.size(); i++) {
//					parentMess = new ParentMess();
//					parentStudent = list.get(i);
//					par = parentService.findUniqueByUserId(parentStudent.getParentUserId());
//					if (par != null) {
//						personOfParent = personService.findPersonById(par
//								.getPersonId());
//						if (personOfParent != null) {
//							parentMess.setParentUserId(par.getUserId());
//							parentMess.setAddress(personOfParent.getAddress());
//							parentMess.setIdCardNumber(personOfParent.getIdCardNumber());
//							parentMess.setIdCardType(personOfParent.getIdCardType());
//							parentMess.setMobile(personOfParent.getMobile());
//							parentMess.setName(personOfParent.getName());
//							parentMess.setPosition(personOfParent.getPosition());
//							parentMess.setRace(personOfParent.getRace());
//							parentMess.setRank(parentStudent.getRank());
//							//parentMess.setResidenceAddress(personOfParent.getResidenceAddress());
//							parentMess.setResidenceAddressCode(personOfParent.getResidenceAddress());
//							parentMess.setWorkingPlace(personOfParent.getWorkingPlace());
//							parentMess.setParentRelation(parentStudent.getParentRelation());
//							parentMess.setPrealtionRemarks(parentStudent.getRelationRemarks());
//						}
//					}
//					// 将家庭成员添加
//					parentMessList.add(parentMess);
//				}
//				parent.setParentMess(parentMessList);
//			}
            /*报错*/
            teamStudent = teamStudentService.findUnique(student.getTeamId(), studentId);

            basic.setName(student.getName());
            basic.setSex(student.getSex());

            archive.setStudentType(student.getStudentType());
            archive.setEnrollDate(student.getEnrollDate());
            archive.setLeaveDate(student.getLeaveDate());
            archive.setStudentNumber(student.getStudentNumber());
            archive.setStudyState(student.getStudyState());
            archive.setTeamId(student.getTeamId());
            archive.setGradeId(teamStudent.getGradeId());

            Team team = teamService.findTeamById(student.getTeamId() == null ? 0 : student.getTeamId());
            if (team != null) {
                archive.setTeamName(team.getName());
            }

            Grade grade = gradeService.findGradeById(teamStudent.getGradeId() == null ? 0 : teamStudent.getGradeId());
            if (grade != null) {
                archive.setGradeName(grade.getName());
            }
            //archive.setNumber(teamStudent.getNumber() == null ? "" : teamStudent.getNumber() + "");
            archive.setNumber((String) basicSQLService.findUnique("select emp_code from pj_student where id="+studentId));
            if (person != null) {
                basic.setBirthday(person.getBirthday());
                basic.setBirthPlaceCode(person.getBirthPlace());
                basic.setNativePlaceCode(person.getNativePlace());
                basic.setRace(person.getRace());
                basic.setNationality(person.getNationality());
                basic.setIdCardType(person.getIdCardType());
                basic.setIdCardNumber(person.getIdCardNumber());
                basic.setAbroadCode(person.getAbroadCode());
                basic.setPoliticalStatus(person.getPoliticalStatus());
                basic.setHealthStatus(person.getHealthStatus());
                basic.setBloodType(person.getBloodType());
                basic.setPhotoUuid(person.getPhotoUuid());

                assist.setPinyinName(person.getPinyinName());
                assist.setIdCardDate(person.getIdCardDate());
                //assist.setResidenceAddress(person.getResidenceAddress());
                assist.setResidenceAddressCode(person.getResidenceAddress());
                assist.setResidenceType(person.getResidenceType());
                assist.setSpecialSkill(person.getSpecialSkill());
                assist.setUsedName(person.getUsedName());

                relation.setAddress(person.getAddress());
                relation.setEmail(person.getEmail());
                relation.setHomeAddress(person.getHomeAddress());
                relation.setHomepage(person.getHomepage());
                relation.setMobile(person.getMobile());
                relation.setResideAddress(person.getResideAddress());
                relation.setTelephone(person.getTelephone());
                relation.setZipCode(person.getZipCode());

                extra.setIsOnlyChild(person.isOnlyChild());
                extra.setHouseAuthority(person.getHouseAuthority());

                remarks.setRemark(person.getRemark());
                resumes.setResume(person.getResume());
            }

            if (studentArchive != null) {
                archive.setAttendSchoolType(studentArchive.getAttendSchoolType());
                archive.setEnrollType(studentArchive.getEnrollType());
                archive.setStudentSource(studentArchive.getStudentSource());

                extra.setDisabilityType(studentArchive.getDisabilityType());
                extra.setFollowStudy(studentArchive.getFollowStudy());
                extra.setIsBuyedDegree(studentArchive.getIsBuyedDegree());
                extra.setIsCityLabourChild(studentArchive.getIsCityLabourChild());
                extra.setIsFirstRecommended(studentArchive.getIsFirstRecommended());
                extra.setIsMartyrChild(studentArchive.getIsMartyrChild());
                extra.setIsOrphan(studentArchive.getIsOrphan());
                extra.setIsPreeducated(studentArchive.getIsPreeducated());
                extra.setIsSponsored(studentArchive.getIsSponsored());
                extra.setIsUnattendedChild(studentArchive.getIsUnattendedChild());
                extra.setNeedSpecialCare(studentArchive.getNeedSpecialCare());
                extra.setAihao(studentArchive.getAihao());
                extra.setXingge(studentArchive.getXingge());
                extra.setLikeBook(studentArchive.getLikeBook());
                extra.setJingpei(studentArchive.getJingpei());
                extra.setZuoyouming(studentArchive.getZuoyouming());
                extra.setGanyan(studentArchive.getGanyan());
                extra.setGanyan1(studentArchive.getGanyan1());
                extra.setPictureUuid(studentArchive.getTupianUuid());

                traffic.setBySchoolBus(studentArchive.getBySchoolBus());
                traffic.setSchoolDistance(studentArchive.getSchoolDistance());
                traffic.setTrafficType(studentArchive.getTrafficType());
            }
        }

        studentArchiveComplete.setArchive(archive);
        studentArchiveComplete.setAssist(assist);
        studentArchiveComplete.setBasic(basic);
        studentArchiveComplete.setExtra(extra);
        studentArchiveComplete.setParent(parent);
        studentArchiveComplete.setRemarks(remarks);
        studentArchiveComplete.setResumes(resumes);
        studentArchiveComplete.setRelation(relation);
        studentArchiveComplete.setTraffic(traffic);

        return studentArchiveComplete;
    }
    @Override
    public StudentArchiveComplete getStudentArchiveComplete2(Integer studentId,String year) {

        StudentArchiveComplete studentArchiveComplete = new StudentArchiveComplete();

        StudentArchiveComplete.Basic basic = studentArchiveComplete.new Basic();

        StudentArchiveComplete.Extra extra = studentArchiveComplete.new Extra();

        StudentArchiveComplete.Parent parent = studentArchiveComplete.new Parent();

        StudentArchiveComplete.Remarks remarks = studentArchiveComplete.new Remarks();

        StudentArchiveComplete.Resumes resumes = studentArchiveComplete.new Resumes();

        StudentArchiveComplete.Traffic traffic = studentArchiveComplete.new Traffic();

        StudentArchiveComplete.Archive archive = studentArchiveComplete.new Archive();

        StudentArchiveComplete.Assist assist = studentArchiveComplete.new Assist();

        StudentArchiveComplete.Relation relation = studentArchiveComplete.new Relation();

        if (studentId == null) {
            return studentArchiveComplete;
        }

        Student student = studentDao.findById(studentId);
//		ParentStudent parentStudent = null;
//		ParentMess parentMess = null;
        List<ParentMess> parentMessList = new ArrayList<ParentMess>();
//		Parent par = null;
        Person person = null;
//		Person personOfParent = null;
        StudentArchive studentArchive = null;
        TeamStudent teamStudent = null;
        if (student != null) {
            person = personService.findPersonById(student.getPersonId());
            //学生拓展信息
            studentArchive = studentArchiveDao.findByStudentId(studentId);

            //2017-8-14	 家庭成员信息从family_member中拿，仅作为档案内容录入，不与平台家长用户信息做直接关联
            parentMessList = familyMemberService.findParentMessByStudentId(studentId);
            parent.setParentMess(parentMessList);
//			List<ParentStudent> list = parentStudentService.findParentStudentByStudentUserId(student.getUserId());
//			if (list != null && list.size() > 0) {
//				for (int i = 0; i < list.size(); i++) {
//					parentMess = new ParentMess();
//					parentStudent = list.get(i);
//					par = parentService.findUniqueByUserId(parentStudent.getParentUserId());
//					if (par != null) {
//						personOfParent = personService.findPersonById(par
//								.getPersonId());
//						if (personOfParent != null) {
//							parentMess.setParentUserId(par.getUserId());
//							parentMess.setAddress(personOfParent.getAddress());
//							parentMess.setIdCardNumber(personOfParent.getIdCardNumber());
//							parentMess.setIdCardType(personOfParent.getIdCardType());
//							parentMess.setMobile(personOfParent.getMobile());
//							parentMess.setName(personOfParent.getName());
//							parentMess.setPosition(personOfParent.getPosition());
//							parentMess.setRace(personOfParent.getRace());
//							parentMess.setRank(parentStudent.getRank());
//							//parentMess.setResidenceAddress(personOfParent.getResidenceAddress());
//							parentMess.setResidenceAddressCode(personOfParent.getResidenceAddress());
//							parentMess.setWorkingPlace(personOfParent.getWorkingPlace());
//							parentMess.setParentRelation(parentStudent.getParentRelation());
//							parentMess.setPrealtionRemarks(parentStudent.getRelationRemarks());
//						}
//					}
//					// 将家庭成员添加
//					parentMessList.add(parentMess);
//				}
//				parent.setParentMess(parentMessList);
//			}
            /*报错*/
            teamStudent = teamStudentService.findUnique2(student.getTeamId(), studentId,year);

            basic.setName(student.getName());
            basic.setSex(student.getSex());

            archive.setStudentType(student.getStudentType());
            archive.setEnrollDate(student.getEnrollDate());
            archive.setLeaveDate(student.getLeaveDate());
            archive.setStudentNumber(student.getStudentNumber());
            archive.setStudyState(student.getStudyState());
            archive.setTeamId(student.getTeamId());
            archive.setGradeId(teamStudent.getGradeId());

            Team team = teamService.findTeamById(student.getTeamId() == null ? 0 : student.getTeamId());
            if (team != null) {
                archive.setTeamName(team.getName());
            }

            Grade grade = gradeService.findGradeById(teamStudent.getGradeId() == null ? 0 : teamStudent.getGradeId());
            if (grade != null) {
                archive.setGradeName(grade.getName());
            }
            //archive.setNumber(teamStudent.getNumber() == null ? "" : teamStudent.getNumber() + "");
            archive.setNumber((String) basicSQLService.findUnique("select emp_code from pj_student where id="+studentId));
            if (person != null) {
                basic.setBirthday(person.getBirthday());
                basic.setBirthPlaceCode(person.getBirthPlace());
                basic.setNativePlaceCode(person.getNativePlace());
                basic.setRace(person.getRace());
                basic.setNationality(person.getNationality());
                basic.setIdCardType(person.getIdCardType());
                basic.setIdCardNumber(person.getIdCardNumber());
                basic.setAbroadCode(person.getAbroadCode());
                basic.setPoliticalStatus(person.getPoliticalStatus());
                basic.setHealthStatus(person.getHealthStatus());
                basic.setBloodType(person.getBloodType());
                basic.setPhotoUuid(person.getPhotoUuid());

                assist.setPinyinName(person.getPinyinName());
                assist.setIdCardDate(person.getIdCardDate());
                //assist.setResidenceAddress(person.getResidenceAddress());
                assist.setResidenceAddressCode(person.getResidenceAddress());
                assist.setResidenceType(person.getResidenceType());
                assist.setSpecialSkill(person.getSpecialSkill());
                assist.setUsedName(person.getUsedName());

                relation.setAddress(person.getAddress());
                relation.setEmail(person.getEmail());
                relation.setHomeAddress(person.getHomeAddress());
                relation.setHomepage(person.getHomepage());
                relation.setMobile(person.getMobile());
                relation.setResideAddress(person.getResideAddress());
                relation.setTelephone(person.getTelephone());
                relation.setZipCode(person.getZipCode());

                extra.setIsOnlyChild(person.isOnlyChild());
                extra.setHouseAuthority(person.getHouseAuthority());

                remarks.setRemark(person.getRemark());
                resumes.setResume(person.getResume());
            }

            if (studentArchive != null) {
                archive.setAttendSchoolType(studentArchive.getAttendSchoolType());
                archive.setEnrollType(studentArchive.getEnrollType());
                archive.setStudentSource(studentArchive.getStudentSource());

                extra.setDisabilityType(studentArchive.getDisabilityType());
                extra.setFollowStudy(studentArchive.getFollowStudy());
                extra.setIsBuyedDegree(studentArchive.getIsBuyedDegree());
                extra.setIsCityLabourChild(studentArchive.getIsCityLabourChild());
                extra.setIsFirstRecommended(studentArchive.getIsFirstRecommended());
                extra.setIsMartyrChild(studentArchive.getIsMartyrChild());
                extra.setIsOrphan(studentArchive.getIsOrphan());
                extra.setIsPreeducated(studentArchive.getIsPreeducated());
                extra.setIsSponsored(studentArchive.getIsSponsored());
                extra.setIsUnattendedChild(studentArchive.getIsUnattendedChild());
                extra.setNeedSpecialCare(studentArchive.getNeedSpecialCare());
                //2022,11,08新加
                extra.setXingge(studentArchive.getXingge());
                extra.setAihao(studentArchive.getAihao());
                extra.setJingpei(studentArchive.getJingpei());
                extra.setLikeBook(studentArchive.getLikeBook());
                extra.setZuoyouming(studentArchive.getZuoyouming());
                extra.setGanyan(studentArchive.getGanyan());
                extra.setGanyan1(studentArchive.getGanyan1());
                extra.setPictureUuid(studentArchive.getTupianUuid());

                //end
                traffic.setBySchoolBus(studentArchive.getBySchoolBus());
                traffic.setSchoolDistance(studentArchive.getSchoolDistance());
                traffic.setTrafficType(studentArchive.getTrafficType());
            }
        }

        studentArchiveComplete.setArchive(archive);
        studentArchiveComplete.setAssist(assist);
        studentArchiveComplete.setBasic(basic);
        studentArchiveComplete.setExtra(extra);
        studentArchiveComplete.setParent(parent);
        studentArchiveComplete.setRemarks(remarks);
        studentArchiveComplete.setResumes(resumes);
        studentArchiveComplete.setRelation(relation);
        studentArchiveComplete.setTraffic(traffic);

        return studentArchiveComplete;
    }

    private Person findOrSyncPerson(Integer personId, String name, Integer studentId, Integer userId) {
        Person person = null;
        if (personId != null) {
            person = personService.findPersonById(personId);
        }
        if (person == null) {
            person = new Person();
            person.setName(name);
            person.setIsDelete(false);
            person.setCreateDate(new Date());
            person.setModifyDate(new Date());
            person = personService.add(person);

            if (person != null) {
                personId = person.getId();
                Student student = new Student(studentId);
                student.setPersonId(personId);
                student.setModifyDate(new Date());
                studentDao.update(student);
                //basicSQLService.update("update pj_team_student set number="+student.getEmpCode()+",name+'"++"'");
                User user = new User(userId);
                user.setPersonId(personId);
                user.setModifyDate(new Date());
                userService.modify(user);

                SchoolUser schoolUser = schoolUserService.findSchoolUserListByUserIdAndType(userId, "4");
                if (schoolUser != null) {
                    schoolUser = new SchoolUser(schoolUser.getId());
                    schoolUser.setPersonId(personId);
                    schoolUser.setModifyDate(new Date());
                    schoolUserService.modify(schoolUser);
                }
            }
        }
        return person;
    }

    /**
     * @param studentId              学生的id
     * @param studentArchiveComplete json 格式的数据
     * @function 将学生的学籍信息完善
     */
    @Override
    public StudentArchiveComplete setStudentArchiveComplete(Integer studentId,
                                                            StudentArchiveComplete studentArchiveComplete, Boolean isComplet, Boolean isBindingMobile) {
        //2016-9-5 新增参数 isBindingMobile 是否绑定手机号为学生账号
        if (isBindingMobile == null) {
            isBindingMobile = false;
        }

        Student student = studentDao.findById(studentId);
        Person personOfStudent = null;
        Person personOfParent = null;
        ParentStudent parentStudent = null;
        StudentArchive studentArchive = null;
        ParentVo parentVo = null;
        UserDetailInfo userDetailInfo = null;
     /*   try {*/
            if (student != null) {
                // 查询并设置person表数据
//				personOfStudent = personService.findPersonById(student.getPersonId());
                // 2018-06-05	针对新版导入时，未创建person表数据
                personOfStudent = findOrSyncPerson(student.getPersonId(), student.getName(), student.getId(), student.getUserId());
                student.setPersonId(personOfStudent.getId());
                // 查询并设置studentArchive表数据
                studentArchive = studentArchiveDao.findByStudentId(studentId);
                if (studentArchive == null) {
                    studentArchive = new StudentArchive();
                }
                studentArchive.setStudentId(studentId);
                studentArchive.setPersonId(student.getPersonId());
                if (studentArchiveComplete != null) {
                    // 基本信息类
                    if (studentArchiveComplete.getBasic() != null) {
                        student.setName(studentArchiveComplete.getBasic().getName());
                        student.setSex(studentArchiveComplete.getBasic().getSex());
                        personOfStudent.setSex(studentArchiveComplete.getBasic().getSex());
                        personOfStudent.setAbroadCode(studentArchiveComplete.getBasic().getAbroadCode());
                        personOfStudent.setBirthday(studentArchiveComplete.getBasic().getBirthday());
                        personOfStudent.setBirthPlace(studentArchiveComplete.getBasic().getBirthPlaceCode());
                        personOfStudent.setNativePlace(studentArchiveComplete.getBasic().getNativePlaceCode());
                        personOfStudent.setRace(studentArchiveComplete.getBasic().getRace());
                        personOfStudent.setNationality(studentArchiveComplete.getBasic().getNationality());
                        personOfStudent.setIdCardNumber(studentArchiveComplete.getBasic().getIdCardNumber());
                        personOfStudent.setIdCardType(studentArchiveComplete.getBasic().getIdCardType());
                        personOfStudent.setPoliticalStatus(studentArchiveComplete.getBasic().getPoliticalStatus());
                        personOfStudent.setHealthStatus(studentArchiveComplete.getBasic().getHealthStatus());
                        personOfStudent.setBloodType(studentArchiveComplete.getBasic().getBloodType());
                        personOfStudent.setPhotoUuid(studentArchiveComplete.getBasic().getPhotoUuid());
                    }

                    // 学籍信息类
                    if (studentArchiveComplete.getArchive() != null) {
                        studentArchive.setAttendSchoolType(studentArchiveComplete.getArchive().getAttendSchoolType());
                        studentArchive.setEnrollType(studentArchiveComplete.getArchive().getEnrollType());
                        studentArchive.setStudentSource(studentArchiveComplete.getArchive().getStudentSource());

                        student.setStudyState(studentArchiveComplete.getArchive().getStudyState());
                        student.setStuNum(studentArchiveComplete.getArchive().getNumber());
                        student.setEmpCode(studentArchiveComplete.getArchive().getStudentNumber());
                        student.setStudentType(studentArchiveComplete.getArchive().getStudentType());
                        student.setEnrollDate(studentArchiveComplete.getArchive().getEnrollDate());
                        student.setLeaveDate(studentArchiveComplete.getArchive().getLeaveDate());
                        //student.setEmpCode(studentArchiveComplete.getSt);
                    }

                    // 辅助信息类
                    if (studentArchiveComplete.getAssist() != null) {
                        personOfStudent.setPinyinName(studentArchiveComplete.getAssist().getPinyinName());
                        personOfStudent.setIdCardDate(studentArchiveComplete.getAssist().getIdCardDate());
                        //personOfStudent.setResidenceAddress(studentArchiveComplete.getAssist().getResidenceAddress());
                        personOfStudent.setResidenceAddress(studentArchiveComplete.getAssist().getResidenceAddressCode());
                        personOfStudent.setResidenceType(studentArchiveComplete.getAssist().getResidenceType());
                        personOfStudent.setSpecialSkill(studentArchiveComplete.getAssist().getSpecialSkill());
                        personOfStudent.setUsedName(studentArchiveComplete.getAssist().getUsedName());
                    }

                    // 扩展信息类
                    if (studentArchiveComplete.getExtra() != null) {
                        personOfStudent.setOnlyChild(studentArchiveComplete.getExtra().getIsOnlyChild());
                        personOfStudent.setHouseAuthority(studentArchiveComplete.getExtra().getHouseAuthority());

                        studentArchive.setDisabilityType(studentArchiveComplete.getExtra().getDisabilityType());
                        studentArchive.setFollowStudy(studentArchiveComplete.getExtra().getFollowStudy());
                        studentArchive.setIsBuyedDegree(studentArchiveComplete.getExtra().getIsBuyedDegree());
                        studentArchive.setIsCityLabourChild(studentArchiveComplete.getExtra().getIsCityLabourChild());
                        studentArchive.setIsFirstRecommended(studentArchiveComplete.getExtra().getIsFirstRecommended());
                        studentArchive.setIsMartyrChild(studentArchiveComplete.getExtra().getIsMartyrChild());
                        studentArchive.setIsOrphan(studentArchiveComplete.getExtra().getIsOrphan());
                        studentArchive.setIsPreeducated(studentArchiveComplete.getExtra().getIsPreeducated());
                        studentArchive.setIsSponsored(studentArchiveComplete.getExtra().getIsSponsored());
                        studentArchive.setIsUnattendedChild(studentArchiveComplete.getExtra().getIsUnattendedChild());
                        studentArchive.setNeedSpecialCare(studentArchiveComplete.getExtra().getNeedSpecialCare());
                        studentArchive.setAihao(studentArchiveComplete.getExtra().getAihao());
                        studentArchive.setXingge(studentArchiveComplete.getExtra().getXingge());
                        studentArchive.setLikeBook(studentArchiveComplete.getExtra().getLikeBook());
                        studentArchive.setJingpei(studentArchiveComplete.getExtra().getJingpei());
                        studentArchive.setZuoyouming(studentArchiveComplete.getExtra().getZuoyouming());
                        studentArchive.setGanyan(studentArchiveComplete.getExtra().getGanyan());
                        studentArchive.setGanyan1(studentArchiveComplete.getExtra().getGanyan1());
                        studentArchive.setTupianUuid(studentArchiveComplete.getExtra().getPictureUuid());
                    }

                    // 家庭成员
                    if (studentArchiveComplete.getParent() != null) {
                        if (studentArchiveComplete.getParent().getParentMess() != null && studentArchiveComplete.getParent().getParentMess().size() > 0) {
                            List<FamilyMember> familyMemberList = familyMemberService.findByStudentId(studentId);
                            int size = 0;
                            if (familyMemberList != null) {
                                size = familyMemberList.size();
                            }
                            ParentMess parentMess = null;
                            FamilyMember familyMember = null;
                            for (int i = 0; i < studentArchiveComplete.getParent().getParentMess().size(); i++) {
                                parentMess = studentArchiveComplete.getParent().getParentMess().get(i);
                                familyMember = new FamilyMember();
                                familyMember.setStudentId(studentId);
                                familyMember.setName(parentMess.getName() != null ? parentMess.getName() : "");
                                familyMember.setMobile(parentMess.getMobile() != null ? parentMess.getMobile() : "");
                                familyMember.setRelation(parentMess.getParentRelation() != null ? parentMess.getParentRelation() : "");
                                familyMember.setRelationRemarks(parentMess.getPrealtionRemarks() != null ? parentMess.getPrealtionRemarks() : "");
                                familyMember.setRace(parentMess.getRace() != null ? parentMess.getRace() : "");
                                familyMember.setWorkingPlace(parentMess.getWorkingPlace() != null ? parentMess.getWorkingPlace() : "");
                                familyMember.setPosition(parentMess.getPosition() != null ? parentMess.getPosition() : "");
                                familyMember.setAddress(parentMess.getAddress() != null ? parentMess.getAddress() : "");
                                familyMember.setResidenceAddress(parentMess.getResidenceAddressCode() != null ? parentMess.getResidenceAddressCode() : "");
                                familyMember.setRank(parentMess.getRank() != null ? parentMess.getRank() : "");
                                familyMember.setIdCardType(parentMess.getIdCardType() != null ? parentMess.getIdCardType() : "");
                                familyMember.setIdCardNumber(parentMess.getIdCardNumber() != null ? parentMess.getIdCardNumber() : "");
                                familyMember.setModifyDate(new Date());
                                //家庭成员信息表中有的则更新，没有则新增
                                if (i < size) {
                                    familyMember.setId(familyMemberList.get(i).getId());
                                    familyMemberService.modify(familyMember);
                                } else {
                                    familyMember.setIsDeleted(false);
                                    familyMember.setCreateDate(new Date());
                                    familyMemberService.add(familyMember);
                                }
                                // 根据用户id查询到家长的档案，更新档案
//								Parent parent = parentService.findUniqueByUserId(studentArchiveComplete.getParent().getParentMess().get(i).getParentUserId());
//								if (parent != null) {
//									personOfParent = personService.findPersonById(parent.getPersonId());
//									if (personOfParent != null && personOfParent.getId() != null) {
//										personOfParent.setAddress(
//												studentArchiveComplete.getParent().getParentMess().get(i).getAddress());
//										personOfParent.setName(
//												studentArchiveComplete.getParent().getParentMess().get(i).getName());
//										personOfParent.setIdCardNumber(
//												studentArchiveComplete.getParent().getParentMess().get(i).getIdCardNumber());
//										personOfParent.setIdCardType(
//												studentArchiveComplete.getParent().getParentMess().get(i).getIdCardType());
//										personOfParent.setMobile(
//												studentArchiveComplete.getParent().getParentMess().get(i).getMobile());
//										personOfParent.setPosition(
//												studentArchiveComplete.getParent().getParentMess().get(i).getPosition());
//										personOfParent.setRace(
//												studentArchiveComplete.getParent().getParentMess().get(i).getRace());
//										//personOfParent.setResidenceAddress(
//										//		studentArchiveComplete.getParent().getParentMess().get(i).getResidenceAddress());
//										personOfParent.setResidenceAddress(
//												studentArchiveComplete.getParent().getParentMess().get(i).getResidenceAddressCode());
//										personOfParent.setWorkingPlace(
//												studentArchiveComplete.getParent().getParentMess().get(i).getWorkingPlace());
//										personService.modify(personOfParent);
//
//										parentStudent = parentStudentService.findUnique(parent.getUserId(), student.getUserId());
//										parentStudent.setRank(studentArchiveComplete.getParent().getParentMess().get(i).getRank());
//										parentStudent.setParentRelation(studentArchiveComplete.getParent().getParentMess().get(i).getParentRelation());
//										parentStudent.setRelationRemarks(studentArchiveComplete.getParent().getParentMess().get(i).getPrealtionRemarks());
//										parentStudentService.modify(parentStudent);
//
//
//										// 修改家长手机号和姓名等信息
//										parentVo = new ParentVo();
//										parentVo.setId(parent.getId());
//										parentVo.setStudentId(studentId);
//										parentVo.setMobile(studentArchiveComplete.getParent().getParentMess().get(i).getMobile());
//										parentVo.setName(studentArchiveComplete.getParent().getParentMess().get(i).getName());
//										try {
//											parentProxyService.modify(parentVo);
//										} catch (Exception e) {
//											log.info("修改家庭成员手机号有误");
//											e.printStackTrace();
//										}
//
//									}
//								}
                            }
                        }
                    }

                    // 备注
                    if (studentArchiveComplete.getRemarks() != null) {
                        personOfStudent.setRemark(studentArchiveComplete.getRemarks().getRemark());
                    }
                    // 简历
                    if (studentArchiveComplete.getResumes() != null) {
                        personOfStudent.setResume(studentArchiveComplete.getResumes().getResume());
                    }

                    // 联系方式
                    if (studentArchiveComplete.getRelation() != null) {
                        personOfStudent.setAddress(studentArchiveComplete.getRelation().getAddress());
                        personOfStudent.setEmail(studentArchiveComplete.getRelation().getEmail());
                        personOfStudent.setHomeAddress(studentArchiveComplete.getRelation().getHomeAddress());
                        personOfStudent.setHomepage(studentArchiveComplete.getRelation().getHomepage());
                        personOfStudent.setMobile(studentArchiveComplete.getRelation().getMobile());
                        personOfStudent.setResideAddress(studentArchiveComplete.getRelation().getResideAddress());
                        personOfStudent.setTelephone(studentArchiveComplete.getRelation().getTelephone());
                        personOfStudent.setZipCode(studentArchiveComplete.getRelation().getZipCode());
                    }

                    // 交通方式
                    if (studentArchiveComplete.getTraffic() != null) {
                        studentArchive.setBySchoolBus(studentArchiveComplete.getTraffic().getBySchoolBus());
                        studentArchive.setSchoolDistance(studentArchiveComplete.getTraffic().getSchoolDistance());
                        studentArchive.setTrafficType(studentArchiveComplete.getTraffic().getTrafficType());
                    }
                }

                // 修改person信息表
                if (personOfStudent != null && personOfStudent.getId() != null) {
                    personService.modify(personOfStudent);
                   /* try {*/
                        userDetailInfo = new UserDetailInfo();
                        userDetailInfo.setName(student.getName());
                        userDetailInfo.setSex(student.getSex());
                        userDetailInfo.setCellPhone(personOfStudent.getMobile());
                        userDetailInfo.setIsOnlyChild(personOfStudent.isOnlyChild());
                        userDetailInfo.setStudentId(studentId);
                        userDetailInfo.setUserId(student.getUserId());
                        userDetailInfo.setPersonId(student.getPersonId());
                        userDetailInfo.setStudentType(student.getStudentType());
                        userDetailInfo.setEnterDate(student.getEnrollDate());
                        userDetailInfo.setEndDate(student.getLeaveDate());
                        userDetailInfo.setState(student.getStudyState());
                        userDetailInfo.setStudentNum(student.getStuNum());
                        userDetailInfo.setCertificateNum(studentArchiveComplete.getBasic().getIdCardNumber());
                        //String number = student.getStuNum();
                        //userDetailInfo.setNumber(number != null && !"".equals(number) ? Integer.parseInt(number) : null);
                        userDetailInfo.setRemark(personOfStudent.getRemark());
                        userDetailInfo.setGradeId(studentArchiveComplete.getArchive().getGradeId().toString());
                        userDetailInfo.setTeamId(studentArchiveComplete.getArchive().getTeamId().toString());
                        userDetailInfo.setOrdergradeId(studentArchiveComplete.getArchive().getOrdelgradeId());
                        userDetailInfo.setNewTeamName(student.getTeamName());
                        //修改学生信息  食堂修改
                    try {
                        userDetailInfo = modfiyUserInfo(userDetailInfo, isBindingMobile);
                    } catch (Exception e) {
                        log.error("食堂修改异常");
                        e.printStackTrace();
                    }
                    studentArchiveComplete.getRemarks().setErrorCode(userDetailInfo.getErrorCode());
                   /* } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                }

                // 判断档案是否存在，存在则修改。不存在则编辑
                if (studentArchive != null && studentArchive.getId() != null) {
                    studentArchive = studentArchiveDao.update(studentArchive);
                } else {
                    studentArchive = studentArchiveDao.create(studentArchive);
                }

                //2016-8-9添加学生档案是否填写完成标志
                JobControlCondition jobControlCondition = new JobControlCondition();
                jobControlCondition.setIsDeleted(false);
                jobControlCondition.setName(StudentArchiveContants.TYPE_FINISH);
                jobControlCondition.setObjectId(student.getUserId());
                List<JobControl> jobControllist = jobControlService.findJobControlByCondition(jobControlCondition);
                if (jobControllist != null && jobControllist.size() == 1) {
                    JobControl jobControl = jobControllist.get(0);
                    jobControl.setInterrupteur(isComplet);
                    jobControl = jobControlService.modify(jobControl);
                } else {
                    JobControl jobControl = new JobControl();
                    jobControl.setAppKey(StudentArchiveContants.TYPE_ARCHIVE_APPKEY);
                    jobControl.setCreateDate(new Date());
                    jobControl.setIsDeleted(false);
                    jobControl.setModifyDate(new Date());
                    jobControl.setName(StudentArchiveContants.TYPE_FINISH);
                    jobControl.setObjectId(student.getUserId());
                    jobControl.setState("");
                    jobControl.setInterrupteur(isComplet);
                    jobControl = jobControlService.add(jobControl);
                }
            }

            //2016-8-18 添加：如果用户头像不存在，并且档案头像存在，则将档案头像当做用户头像，一把辛酸泪。。。。。
            if (studentArchiveComplete != null && studentArchiveComplete.getBasic() != null) {
                if (studentArchiveComplete.getBasic().getPhotoUuid() != null && !"".equals(studentArchiveComplete.getBasic().getPhotoUuid())) {
                    Profile profile = profileService.findByUserId(student.getUserId());
                    if (profile == null) {
                        profile = new Profile();
                        if (studentArchiveComplete.getRelation() != null) {
                            profile.setAddress(studentArchiveComplete.getRelation().getAddress());
                            profile.setEmail(studentArchiveComplete.getRelation().getEmail());
                            profile.setMobile(studentArchiveComplete.getRelation().getMobile());
                        }
                        profile.setBirthday(studentArchiveComplete.getBasic().getBirthday());
                        profile.setCreateDate(new Date());
                        profile.setIcon(studentArchiveComplete.getBasic().getPhotoUuid());
                        profile.setIsDeleted(false);
                        profile.setModifyDate(new Date());
                        profile.setName(studentArchiveComplete.getBasic().getName());
                        profile.setSex(studentArchiveComplete.getBasic().getSex());
                        profile.setUserName(student.getUserName());
                        profile.setUserId(student.getUserId());
                        profileService.add(profile);
                    } else {
                        if (profile.getIcon() == null || "".equals(profile.getIcon())) {
                            if (studentArchiveComplete.getRelation() != null) {
                                profile.setAddress(studentArchiveComplete.getRelation().getAddress());
                                profile.setEmail(studentArchiveComplete.getRelation().getEmail());
                                profile.setMobile(studentArchiveComplete.getRelation().getMobile());
                            }
                            profile.setBirthday(studentArchiveComplete.getBasic().getBirthday());
                            profile.setIcon(studentArchiveComplete.getBasic().getPhotoUuid());
                            profile.setIsDeleted(false);
                            profile.setModifyDate(new Date());
                            profile.setName(studentArchiveComplete.getBasic().getName());
                            profile.setSex(studentArchiveComplete.getBasic().getSex());
                            profile.setUserName(student.getUserName());
                            profile.setUserId(student.getUserId());
                            profileService.modify(profile);
                        }
                    }
                }
            }
      /*  } catch (Exception e) {
            e.printStackTrace();
        }*/
        if (studentArchive.getId() != null) {
            studentArchiveComplete.getRemarks().setErrorCode(userDetailInfo.getErrorCode());
            return studentArchiveComplete;
        }
        return null;
    }

    // 2016-7-23将修改学生的信息从teachservice移动到这里
    public UserDetailInfo modfiyUserInfo(UserDetailInfo userDetailInfo, Boolean isBindingMobile)
            throws Exception {
        UserDetailInfo detailInfo = null;
     /*   try {*/
            detailInfo = updateStudentName(userDetailInfo, isBindingMobile);
       /* } catch (Exception e) {
            e.printStackTrace();
        }*/
        return detailInfo;
    }

    //==============================为创建学生数据提供方法============================================//

    /**
     * 学生创建档案A流程   processOfRecordA
     */
    public UserDetailInfo processOfRecordA(UserDetailInfo userDetailInfo, String password, String userType, Integer appId, Boolean isBindingMobile) throws Exception {
        //是否提供真实姓名
        if (userDetailInfo.getName() != null && !"".equals(userDetailInfo.getName())) {
            //是否有身份证号码
            if (userDetailInfo.getCertificateNum() != null && !"".equals(userDetailInfo.getCertificateNum())) {
                //根据身份证获取个人档案
                Person person = getPersonByIdCardNumber(userDetailInfo.getCertificateNum());
                //判断个人档案是否为空,
                if (person == null) {
                    // processOfRecordB
                    userDetailInfo = processOfRecordB(userDetailInfo, password, userType, appId, isBindingMobile);
                } else {
                    userDetailInfo.setPersonId(person.getId());
                    //判断本校该生是否已存在
                    Student student = getStudentByPersonId(person, userDetailInfo.getSchoolId());

                    //如果学生已存在  退出
                    if (student != null) {
                        userDetailInfo.setMessage("身份证已存在");
                    } else {
                        // processOfRecordC1
                        processOfRecordC1(userDetailInfo, userType, appId);
                    }
                }
            } else {
                // processOfRecordB
                userDetailInfo = processOfRecordB(userDetailInfo, password, userType, appId, isBindingMobile);
            }
        } else {
            //没有提供真实姓名  数据不完整 退出
            userDetailInfo.setMessage("数据不完整");
        }

        return userDetailInfo;
    }

    /**
     * 学生创建档案B流程  processOfRecordB
     */
    public UserDetailInfo processOfRecordB(UserDetailInfo userDetailInfo, String password, String userType, Integer appId, Boolean isBindingMobile) {
        if (isBindingMobile) {
            if (userDetailInfo.getCellPhone() != null && !"".equals(userDetailInfo.getCellPhone())) {
                //根据手机号码查找用户，判断手机是否是否已注册用户
                User user = getUserByCellPhone(userDetailInfo);
                if (user != null) {
                    //获取用户的姓名  判断此用户的姓名与手机号 是否与输入的一致  user中的person
                    Person person = getPersonById(user.getPersonId());
                    if (person != null) {
                        //姓名与输入的 姓名 不一致  表示手机已被其他用户注册使用  退出
                        if (!person.getName().equals(userDetailInfo.getName())) {
                            userDetailInfo.setMessage("手机已被其他用户注册使用");
                        } else {
                            // processOfRecordC1
                            userDetailInfo.setUserId(user.getId());
                            userDetailInfo.setPersonId(person.getId());
                            processOfRecordC1(userDetailInfo, userType, appId);
                        }
                    } else {
                        //2016-1-22
                        userDetailInfo.setMessage("手机已被其他用户注册使用");
                    }
                } else {
                    // processOfRecordC
                    userDetailInfo = processOfRecordC(userDetailInfo, password, userType, appId, isBindingMobile);
                }
            } else {
                // processOfRecordC
                userDetailInfo = processOfRecordC(userDetailInfo, password, userType, appId, isBindingMobile);
            }
        } else {
            // processOfRecordC
            userDetailInfo = processOfRecordC(userDetailInfo, password, userType, appId, isBindingMobile);
        }
        return userDetailInfo;
    }

    /**
     * 学生创建档案C流程  processOfRecordC
     */
    public UserDetailInfo processOfRecordC(UserDetailInfo userDetailInfo, String password, String userType, Integer appId, Boolean isBindingMobile) {
        try {
            //创建个人档案
            Person person = savePerson(userDetailInfo);
            if (person != null) {
                userDetailInfo.setPersonId(person.getId());
            }

            //创建用户账号（如果有手机号码 ，用此创建）processOfUserD.1.1.D
            User user = new User();
            user.setIsDeleted(false);
            user.setCreateDate(new Date());
            user.setModifyDate(new Date());
            user.setPassword(password);
            user.setState(UserContants.STATE_NORMAL);
            user.setPersonId(userDetailInfo.getPersonId());
            UserResult ur = null;
            if (isBindingMobile == null) {
                isBindingMobile = false;
            }
            if (isBindingMobile) {
                ur = userService.addUser(user, userDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL, userDetailInfo.getCellPhone(), UserBindingContants.TYPE_PHONE, appId);
            } else {
                ur = userService.addUser(user, userDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL, null, null, appId);
            }
            if (UserResult.STATUS_USER_BINDING_NAME_EXIST.equals(ur.getStatusCode())) {//电话号码重复
                userDetailInfo.setMessage("电话号码已经存在");
            } else if (UserResult.STATUS_ACCOUNT_DB_NOT_ENOUGH.equals(ur.getStatusCode())) {//帐号库的帐号已用光
                userDetailInfo.setMessage("帐号库帐号已用完");
            } else if (UserResult.STATUS_CUSTOM_USER_NAME_EXIST.equals(ur.getStatusCode())) {//用户名已经存在
                userDetailInfo.setMessage("用户名已经存在");
            } else if (UserResult.STATUS_SUCCESS.equals(ur.getStatusCode())) {//成功
                userDetailInfo.setUserId(ur.getUser().getId());
                userDetailInfo.setUsername(ur.getUser().getUserName());
                processOfRecordC1(userDetailInfo, userType, appId);
            }
        } catch (Exception e) {
            log.info("保存用户详细信息异常.... {}", e);
        }

        return userDetailInfo;
    }

    /**
     * 学生创建档案C1流程  processOfRecordC1
     */
    public void processOfRecordC1(UserDetailInfo userDetailInfo, String userType, Integer appId) {
//				//将用户加入学校组
//				//添加学生的用户角色
        try {
            //============2016-2-26将C流程里面的创建用户账号下移一步（实际没有移动操作   复制了一份  通过判断是否有userId再进行创不创建用户的操作），因为在A流程中学生不存在时没有userId========//
            if (userDetailInfo.getUserId() == null || "".equals(userDetailInfo.getUserId())) {
                //创建用户账号（如果有手机号码 ，用此创建）processOfUserD.1.1.D
                User user = new User();
                user.setIsDeleted(false);
                user.setCreateDate(new Date());
                user.setModifyDate(new Date());
                user.setPassword(defaultPassword);
                user.setState(UserContants.STATE_NORMAL);
                user.setPersonId(userDetailInfo.getPersonId());
                UserResult ur = userService.addUser(user, userDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL, userDetailInfo.getCellPhone(), UserBindingContants.TYPE_PHONE, SYSTEM_APP_ID);
                if (UserResult.STATUS_USER_BINDING_NAME_EXIST.equals(ur.getStatusCode())) {//电话号码重复
                    userDetailInfo.setMessage("电话号码已经存在");
                } else if (UserResult.STATUS_ACCOUNT_DB_NOT_ENOUGH.equals(ur.getStatusCode())) {//帐号库的帐号已用光
                    userDetailInfo.setMessage("帐号库帐号已用完");
                } else if (UserResult.STATUS_CUSTOM_USER_NAME_EXIST.equals(ur.getStatusCode())) {//用户名已经存在
                    userDetailInfo.setMessage("用户名已经存在");
                } else if (UserResult.STATUS_SUCCESS.equals(ur.getStatusCode())) {//成功
                    userDetailInfo.setUserId(ur.getUser().getId());
                    userDetailInfo.setUsername(ur.getUser().getUserName());
                }
            }

            User user = userService.findUserById(userDetailInfo.getUserId());

            Role role = roleService.findRoleById(Integer.valueOf(userDetailInfo.getRole()));

            groupUserService.addUserToGruopAndRole(user, role, userDetailInfo.getSchoolId());

            processOfRecordD(userDetailInfo, userType, appId);
        } catch (Exception e) {
            log.info("保存用户详细信息异常.... {}", e);
        }

    }

    /**
     * 学生创建档案D流程  processOfRecordD
     */
    public UserDetailInfo processOfRecordD(UserDetailInfo userDetailInfo, String userType, Integer appId) {
        Student student = null;
        if (userDetailInfo.getStudentId() != null) {
            student = studentDao.findById(userDetailInfo.getStudentId());
        }
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(userDetailInfo.getSchoolId());
        studentCondition.setTeamId(userDetailInfo.getStudentTeamId());
        studentCondition.setIsDelete(false);
        studentCondition.setName(userDetailInfo.getName());
        List<Student> studentList = studentDao.findStudentByOnlyCondition(studentCondition, null, null);
        //判断student表是否有同名学生
        if (studentList.size() > 0) {
            //学生的别名不为空
            if (userDetailInfo.getAlias() != null && !"".equals(userDetailInfo.getAlias())) {
                //判断别名是否相同    页面转过来的别名 是否与student里面的别名一致
                boolean notExitst = true;
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getAlias().equals(userDetailInfo.getAlias())) {
                        notExitst = false;   //存在相同的别名
                        //2016-4-25 添加 如果找到存在相同别名 则退出循环
                        break;
                    }
                }
                if (notExitst) {
                    //如果别名不存在相同，保存此别名
                    userDetailInfo.setAlias(userDetailInfo.getAlias());
//							userDetailInfo = processOfRecordE(userDetailInfo,appId);
                } else {
                    userDetailInfo.setAlias(createAlias(userDetailInfo.getName(), userDetailInfo.getAlias(), userDetailInfo.getSchoolId(), student));
                }
            } else {
                //生成别名再判断是否一致
                userDetailInfo.setAlias(createAlias(userDetailInfo.getName(), userDetailInfo.getAlias(), userDetailInfo.getSchoolId(), student));
//						processOfRecordE(userDetailInfo,appId);
            }
        } else {
            // processOfRecordE
            //如果姓名不重复  将名字作为别名
            userDetailInfo.setAlias(userDetailInfo.getName());
//					userDetailInfo = processOfRecordE(userDetailInfo,appId);
        }

        userDetailInfo = processOfRecordE(userDetailInfo, userType, appId);

        StudentArchive studentArchive = studentArchiveDao.findByStudentId(userDetailInfo.getStudentId());
        if (studentArchive == null) {
            studentArchive = addStudnetArchive(userDetailInfo);
        } else {
            studentArchive = modifyStudnetArchive(userDetailInfo, studentArchive);
        }

        return userDetailInfo;
    }

    /**
     * 学生创建档案D流程  processOfOnlyRecordD
     * 修改时的Ｄ流程　不包含Ｅ流程的Ｄ流程
     */
    public UserDetailInfo processOfOnlyRecordD(UserDetailInfo userDetailInfo, Integer appId) {
        Student student = null;
        if (userDetailInfo.getStudentId() != null) {
            student = studentDao.findById(userDetailInfo.getStudentId());
        }
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(userDetailInfo.getSchoolId());
        studentCondition.setIsDelete(false);
        studentCondition.setName(userDetailInfo.getName());
        List<Student> studentList = studentDao.findStudentByOnlyCondition(studentCondition, null, null);
        //判断student表是否有同名学生
        if (studentList.size() > 0) {
            //学生的别名不为空
            if (userDetailInfo.getAlias() != null) {
                //判断别名是否相同    页面转过来的别名 是否与student里面的别名一致
                boolean notExitst = true;
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getAlias().equals(userDetailInfo.getAlias())) {
                        notExitst = false;   //存在相同的别名
                    }
                }
                if (notExitst) {
                    //如果别名不存在相同，保存此别名
                    userDetailInfo.setAlias(userDetailInfo.getAlias());
                } else {
                    userDetailInfo.setAlias(createAlias(userDetailInfo.getName(), userDetailInfo.getAlias(), userDetailInfo.getSchoolId(), student));
                }
            } else {
                //生成别名再判断是否一致
                userDetailInfo.setAlias(createAlias(userDetailInfo.getName(), userDetailInfo.getAlias(), userDetailInfo.getSchoolId(), student));
            }
        } else {
            //如果姓名不重复  将名字作为别名
            userDetailInfo.setAlias(userDetailInfo.getName());
        }

        return userDetailInfo;
    }


    /**
     * 学生创建档案E流程  processOfRecordE
     * Modified on 2017-08-04  学生的班级记录添加成功，产生变更记录
     */
    public UserDetailInfo processOfRecordE(UserDetailInfo userDetailInfo, String userType, Integer appId) {
        //创建学生档案记录  student表
        Student student = saveStudent(userDetailInfo);
        userDetailInfo.setStudentId(student.getId());

        //判断是否有班级  有班级则添加学生到teamStudent表中   在添加操作之前呢判断是否已存在该学生  存在则不添加    2016-2-26
        //2016-8-12 修改 ：将保存teamStudent的方法 改成 分班流程，原因是：之前做的创建学生档案，在不经过分班的情况下，不会保存到TeamUser表中
        if (userDetailInfo.getStudentTeamId() != null && !"".equals(userDetailInfo.getStudentTeamId())) {
//					saveTeamStudent(userDetailInfo);

            //2016-8-12添加，将学生添加到team_user表中
            String message = this.teamStudentService.assignStudentToTeam(Integer.valueOf(student.getId()), Integer.valueOf(userDetailInfo.getStudentTeamId()));

            /* Modified on 2017-08-04  更改说明：assignStudentToTeam() 方法中，判断学生是否已经存在当前班级，如果已经存在，不再重复添加记录，意味着学生变更类型，没有发生改变，此时学生变更历史表不需要添加新的记录 */
            if (message.equals(TeamStudentService.OPERATE_SUCCESS)) {
                //学生的班级记录添加成功，产生变更记录
                this.teamStudentService.addStudentAlteration(Integer.valueOf(student.getId()), null, Integer.valueOf(userDetailInfo.getStudentTeamId()), StudentAlterationContants.TYPE_DAORU);
            }

            saveTeamStudent(userDetailInfo);
        }

        //将学生加入到学校用户表中
        saveSchoolUser(userDetailInfo, userType);
        return userDetailInfo;
    }

    //添加班级学生操作  2016-2-26
    public TeamStudent saveTeamStudent(UserDetailInfo userDetailInfo) {
        TeamStudent ts = teamStudentService.findUnique(userDetailInfo.getStudentTeamId(), userDetailInfo.getStudentId());
        Team team = teamService.findTeamById(userDetailInfo.getStudentTeamId());
        if (team != null) {
            if (ts == null) {
                TeamStudent teamStudent = new TeamStudent();
                teamStudent.setCreateDate(new Date());
                teamStudent.setGradeId(team.getGradeId());
                teamStudent.setInState(true);
                teamStudent.setIsDelete(false);
                teamStudent.setJoinDate(new Date());
                teamStudent.setModifyDate(new Date());
                teamStudent.setName(userDetailInfo.getName());
                if (userDetailInfo.getNumber() == null || "".equals(userDetailInfo.getNumber())) {
                    teamStudent.setNumber(null);
                } else {
                    teamStudent.setNumber(Integer.valueOf(userDetailInfo.getNumber()));
                }

                teamStudent.setPosition(userDetailInfo.getPosition());
                teamStudent.setStudentId(userDetailInfo.getStudentId());
                teamStudent.setTeamId(team.getId());
                teamStudent.setUserId(userDetailInfo.getUserId());
                ts = teamStudentService.add(teamStudent);
            } else {
                ts.setGradeId(team.getGradeId());
                ts.setInState(true);
                ts.setIsDelete(false);
                ts.setJoinDate(new Date());
                ts.setModifyDate(new Date());
                ts.setName(userDetailInfo.getName());
                if (userDetailInfo.getNumber() == null || "".equals(userDetailInfo.getNumber())) {
                    ts.setNumber(null);
                } else {
                    ts.setNumber(Integer.valueOf(userDetailInfo.getNumber()));
                }

                ts.setPosition(userDetailInfo.getPosition());
                ts.setStudentId(userDetailInfo.getStudentId());
                ts.setTeamId(team.getId());
                ts.setUserId(userDetailInfo.getUserId());
                ts = teamStudentService.modify(ts);
            }
        }
        return ts;
    }


    /**
     * @param name  用户的真实姓名
     * @param alias 用户的别名
     * @return alias 最后返回的别名
     * @function 生成不重复的学生的别名
     */
    public String createAlias(String name, String alias, Integer schoolId, Student student) {
        if (alias == null || alias == "") {
            alias = name + InitAlias();
        }
        List<Student> list = getStudentByAlias(alias, schoolId);

        //当用户修改姓名    不修改别名时，别名所搜结果去除本身
        if (student != null) {
            for (int i = 0; i < list.size(); i++) {
                if (student.getAlias().equals(list.get(i).getAlias())) {
                    list.remove(i);
                }
            }
        }

        if (list.size() > 0) {
            alias = "";
            alias = name + InitAlias();
            createAlias(name, alias, schoolId, student);
        }
        return alias;
    }

    //初始化别名
    public String InitAlias() {
        Date date = new Date();
        Long dtime = date.getTime();
        String alias = dtime.toString().substring(dtime.toString().length() - 3, dtime.toString().length());
        return alias;
    }

    //保存到pj_person的数据  2015-11-13
    public Person savePerson(UserDetailInfo userDetailInfo) {
        Person p = null;
        if (userDetailInfo.getName() != null && !"".equals(userDetailInfo.getName())) {
            if (userDetailInfo.getCertificateNum() != null && !"".equals(userDetailInfo.getCertificateNum())) {
                //判断身份证号是否已经存在，如果记录存在 查看是否已经标记删除
                Person person = getPersonByIdCardNumber(userDetailInfo.getCertificateNum());

                //如果没有被删除，比较存在的记录和新建的记录的名称是否一致，一致：此身份已被存档，返回该记录
                //不一致：此身份已经存档，但与输入的用户信息不匹配，返回空
                if (person != null) {
                    //记录是否被删除，如果没有删除，判断记录的名字和准备创建的名字是否一致，如果一致，此身份已存档，返回该记录
                    //记录被删除：复活原有记录  覆盖新的属性。返回person记录。
                    if (!person.getIsDelete()) {
                        if (userDetailInfo.getName().equals(person.getName())) {
                            //名字一致，此身份已存档，返回现有的person记录
                            p = person;
                            userDetailInfo.setMessage("此身份证已存档");
                        } else {
                            //此身份证已存档，但与输入的用户不匹配，返回空
                            p = null;
                            userDetailInfo.setMessage("此身份证已存档，但与输入用户信息不匹配");
                        }
                    } else {
                        //记录被删除：复活原有记录  ，覆盖新的属性，返回这条person记录
                        p = modifyPerson(userDetailInfo, person);
                    }
                } else {
                    //创建用户档案，并返回新建记录
                    p = addPerson(userDetailInfo);
                }
            } else {
                //创建用户档案，并返回新建记录
                p = addPerson(userDetailInfo);
            }
        } else {
            userDetailInfo.setMessage("数据不完整");
        }
        return p;
    }


    //创建studentArchive记录
    public StudentArchive addStudnetArchive(UserDetailInfo userDetailInfo) {
        StudentArchive studentArchive = new StudentArchive();
        studentArchive.setStudentId(userDetailInfo.getStudentId());
        studentArchive.setPersonId(userDetailInfo.getPersonId());
        studentArchive.setEnrollType(userDetailInfo.getEnrollType());
        //studentArchive.setEnrollMark();
        //studentArchive.setCeNumber();
        studentArchive.setStudentSource(userDetailInfo.getStudentSource());
        //studentArchive.setGraduationSchool();
        studentArchive.setIsBoarded(userDetailInfo.getIsBoarded() == "1" ? true : false);
        studentArchive.setIsPreeducated(userDetailInfo.getIsPreeducated());
        studentArchive.setIsBuyedDegree(userDetailInfo.getIsBuyedDegree());
        studentArchive.setIsMartyrChild(userDetailInfo.getIsMartyrChild());
        studentArchive.setIsOrphan(userDetailInfo.getIsOrphan());
        studentArchive.setIsSponsored(userDetailInfo.getIsSponsored());
        studentArchive.setIsFirstRecommended(userDetailInfo.getIsFirstRecommended());
        //studentArchive.setIsSupportNourishment();
        studentArchive.setDisabilityType(userDetailInfo.getDisabilityType());
        studentArchive.setFollowStudy(userDetailInfo.getFollowStudy());
        studentArchive.setNeedSpecialCare(userDetailInfo.getNeedSpecialCare());
        studentArchive.setIsUnattendedChild(userDetailInfo.getIsUnattendedChild());
        studentArchive.setIsMigratedChild(userDetailInfo.getIsMartyrChild());
        studentArchive.setIsCityLabourChild(userDetailInfo.getIsCityLabourChild());
        studentArchive.setAttendSchoolType(userDetailInfo.getAttendSchoolType());
        studentArchive.setBySchoolBus(userDetailInfo.getBySchoolBus());
        studentArchive.setTrafficType(userDetailInfo.getTrafficType());
        studentArchive.setSchoolDistance(userDetailInfo.getSchoolDistance());

        StudentArchive sa = studentArchiveDao.create(studentArchive);
        return sa;
    }

    //修改studentArchive记录
    public StudentArchive modifyStudnetArchive(UserDetailInfo userDetailInfo, StudentArchive studentArchive) {
        studentArchive.setId(studentArchive.getId());
        studentArchive.setStudentId(userDetailInfo.getStudentId());
        studentArchive.setPersonId(userDetailInfo.getPersonId());
        studentArchive.setEnrollType(userDetailInfo.getEnrollType());
        //studentArchive.setEnrollMark();
        //studentArchive.setCeNumber();
        studentArchive.setStudentSource(userDetailInfo.getStudentSource());
        //studentArchive.setGraduationSchool();
        studentArchive.setIsBoarded(userDetailInfo.getIsBoarded() == "1" ? true : false);
        studentArchive.setIsPreeducated(userDetailInfo.getIsPreeducated());
        studentArchive.setIsBuyedDegree(userDetailInfo.getIsBuyedDegree());
        studentArchive.setIsMartyrChild(userDetailInfo.getIsMartyrChild());
        studentArchive.setIsOrphan(userDetailInfo.getIsOrphan());
        studentArchive.setIsSponsored(userDetailInfo.getIsSponsored());
        studentArchive.setIsFirstRecommended(userDetailInfo.getIsFirstRecommended());
        //studentArchive.setIsSupportNourishment();
        studentArchive.setDisabilityType(userDetailInfo.getDisabilityType());
        studentArchive.setFollowStudy(userDetailInfo.getFollowStudy());
        studentArchive.setNeedSpecialCare(userDetailInfo.getNeedSpecialCare());
        studentArchive.setIsUnattendedChild(userDetailInfo.getIsUnattendedChild());
        studentArchive.setIsMigratedChild(userDetailInfo.getIsMartyrChild());
        studentArchive.setIsCityLabourChild(userDetailInfo.getIsCityLabourChild());
        studentArchive.setAttendSchoolType(userDetailInfo.getAttendSchoolType());
        studentArchive.setBySchoolBus(userDetailInfo.getBySchoolBus());
        studentArchive.setTrafficType(userDetailInfo.getTrafficType());
        studentArchive.setSchoolDistance(userDetailInfo.getSchoolDistance());

        StudentArchive sa = studentArchiveDao.update(studentArchive);
        return sa;
    }

    //创建person记录
    public Person addPerson(UserDetailInfo userDetailInfo) {
        Person person = new Person();
        person.setName(userDetailInfo.getName());
        person.setEnglishName(userDetailInfo.getEnglishName());
        person.setSex(userDetailInfo.getSex());
        person.setBirthday(userDetailInfo.getBirthDate());
        person.setIdCardNumber(userDetailInfo.getCertificateNum());
        person.setIdCardType(userDetailInfo.getCertificateType());
        person.setNationality(userDetailInfo.getNationality());
        person.setNativePlace(userDetailInfo.getNativePlace());
        person.setBirthPlace(userDetailInfo.getBirthPlace());
        person.setRace(userDetailInfo.getNation());
        person.setPoliticalStatus(userDetailInfo.getPolitical());
        person.setReligion(userDetailInfo.getReligiousBelief());
        person.setMarriage(userDetailInfo.getMarriage());
        person.setAbroadCode(userDetailInfo.getAbroadCode());
        person.setHealthStatus(userDetailInfo.getHealthStatus());
        person.setBloodType(userDetailInfo.getBloodType());
        person.setResidenceType(userDetailInfo.getRegister());    /*户口类别*/
        person.setProvince(userDetailInfo.getProvince());
        person.setCity(userDetailInfo.getCity());
        person.setDistrict(userDetailInfo.getDistrict());
        person.setStreet(userDetailInfo.getStreet());
        person.setAddress(userDetailInfo.getNowAddress());        //现地址（身份证地址）
        person.setResideAddress(userDetailInfo.getRegisterPlace());
        person.setResidenceAddress(userDetailInfo.getRegisterPlace());    /*户口所在地（居民户口簿登记所在地）*/
        person.setMobile(userDetailInfo.getCellPhone());
        person.setTelephone(userDetailInfo.getTelephone());
        person.setEmail(userDetailInfo.getEmail());
        person.setSpecialSkill(userDetailInfo.getSpecialty());
        person.setOnlyChild(userDetailInfo.getIsOnlyChild());
        boolean flag = false;
        if (userDetailInfo.getIsFloatingPopulation() == "0") {
            flag = false;
        } else {
            flag = true;
        }
        person.setFloatingPopulation(flag);
        person.setEntityId(userDetailInfo.getEntityId());
        person.setRemark(userDetailInfo.getRemark());
        person.setCreateDate(new Date());
        person.setModifyDate(new Date());
        person.setIsDelete(false);

        person.setPhotoUuid(userDetailInfo.getPhotoUuid());
        person.setPinyinName(userDetailInfo.getPinyinName());
        person.setUsedName(userDetailInfo.getUsedName());
        person.setIdCardDate(userDetailInfo.getIdCardDate());
        person.setZipCode(userDetailInfo.getZipCode());
        person.setHomeAddress(userDetailInfo.getHomeAddress());        //家庭地址
        person.setResideAddress(userDetailInfo.getLiveAddress());    //居住地址，通信地址
        person.setHouseAuthority(userDetailInfo.getHouseAuthority());
        person.setHomepage(userDetailInfo.getHomepage());

        Person p = personService.add(person);
        return p;
    }

    //修改person记录
    public Person modifyPerson(UserDetailInfo userDetailInfo, Person person) {
        person.setId(person.getId());
        person.setName(userDetailInfo.getName());
        person.setEnglishName(userDetailInfo.getEnglishName());
        person.setSex(userDetailInfo.getSex());
        person.setBirthday(userDetailInfo.getBirthDate());
        person.setIdCardNumber(userDetailInfo.getCertificateNum());
        person.setIdCardType(userDetailInfo.getCertificateType());
        person.setNationality(userDetailInfo.getNationality());
        person.setNativePlace(userDetailInfo.getNativePlace());
        person.setBirthPlace(userDetailInfo.getBirthPlace());
        person.setRace(userDetailInfo.getNation());
        person.setPoliticalStatus(userDetailInfo.getPolitical());
        person.setReligion(userDetailInfo.getReligiousBelief());
        person.setMarriage(userDetailInfo.getMarriage());
        person.setAbroadCode(userDetailInfo.getAbroadCode());
        person.setHealthStatus(userDetailInfo.getHealthStatus());
        person.setBloodType(userDetailInfo.getBloodType());
        person.setResidenceType(userDetailInfo.getRegister());
        person.setProvince(userDetailInfo.getProvince());
        person.setCity(userDetailInfo.getCity());
        person.setDistrict(userDetailInfo.getDistrict());
        person.setStreet(userDetailInfo.getStreet());
        person.setAddress(userDetailInfo.getNowAddress());
        person.setResideAddress(userDetailInfo.getRegisterPlace());
        person.setMobile(userDetailInfo.getCellPhone());
        person.setTelephone(userDetailInfo.getTelephone());
        person.setEmail(userDetailInfo.getEmail());
        person.setSpecialSkill(userDetailInfo.getSpecialty());
        person.setOnlyChild(userDetailInfo.getIsOnlyChild());
        boolean flag = false;
        if (userDetailInfo.getIsFloatingPopulation() == "0") {
            flag = false;
        } else {
            flag = true;
        }
        person.setFloatingPopulation(flag);
        person.setEntityId(userDetailInfo.getEntityId());
        person.setRemark(userDetailInfo.getRemark());
        person.setCreateDate(new Date());
        person.setModifyDate(new Date());
        person.setIsDelete(false);
        Person p = personService.modify(person);
        return p;
    }

    //创建学生记录
    public Student saveStudent(UserDetailInfo userDetailInfo) {
        Student student = new Student();
        student.setSchoolId(userDetailInfo.getSchoolId());
        student.setPersonId(userDetailInfo.getPersonId());
        student.setUserId(userDetailInfo.getUserId());
        student.setUserName(userDetailInfo.getUsername());
        student.setName(userDetailInfo.getName());
        student.setSex(userDetailInfo.getSex());
        student.setStudentNumber(userDetailInfo.getStudentNum());
        student.setStudentNumber2(userDetailInfo.getStudentNum2());
        student.setEnrollDate(userDetailInfo.getEnterDate());    //入学时间
        student.setLeaveDate(userDetailInfo.getEndDate());        //离校时间
        student.setMobile(userDetailInfo.getCellPhone());
        student.setIsBoarded(userDetailInfo.getIsBoarded());
        student.setStudyState(userDetailInfo.getState());
        student.setStudentType(userDetailInfo.getStudentType());
        student.setPosition(userDetailInfo.getPosition());
        student.setCreateDate(new Date());
        student.setIsDelete(false);
        student.setAlias(userDetailInfo.getAlias());
        student.setEmpCode(userDetailInfo.getUsername());

        //客户端导入用到的班级ID  2016-2-26
        if (userDetailInfo.getStudentTeamId() != null && !"".equals(userDetailInfo.getStudentTeamId())) {
            Team team = teamService.findTeamById(userDetailInfo.getStudentTeamId());
            if (team != null) {
                student.setTeamId(team.getId());
                student.setTeamName(team.getName());
                userDetailInfo.setNewTeamName(team.getName());
                userDetailInfo.setNewCode(team.getCode());
            }
        }

        return studentDao.create(student);
    }

    //创建schoolUser
    public SchoolUser saveSchoolUser(UserDetailInfo userDetailInfo, String userType) {
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setCreateDate(new Date());
        schoolUser.setModifyDate(new Date());
        schoolUser.setName(userDetailInfo.getName());
        schoolUser.setOwnerId(userDetailInfo.getStudentId());
        schoolUser.setSchoolId(userDetailInfo.getSchoolId());
        schoolUser.setUserId(userDetailInfo.getUserId());
        schoolUser.setPersonId(userDetailInfo.getPersonId());
        schoolUser.setUserType(userType);
        schoolUser.setAlias(userDetailInfo.getAlias());
        schoolUser.setUserName(userDetailInfo.getUsername());
        schoolUser.setIsDeleted(false);
        schoolUser.setInSchool(true);
        return schoolUserService.add(schoolUser);
    }

    //根据手机号码查找用户
    public User getUserByCellPhone(UserDetailInfo userDetailInfo) {
        User user = null;
        UserBindingCondition ubCondition = new UserBindingCondition();
        ubCondition.setBindingName(userDetailInfo.getCellPhone());
        ubCondition.setBindingType(UserBindingContants.TYPE_PHONE);
        ubCondition.setIsDeleted(false);
        List<UserBinding> list = userBindingService.findUserBindingByCondition(ubCondition, null, null);
        if (list.size() > 0) {
            user = userService.findUserById(list.get(0).getUserId());
        }
        return user;
    }

    //根据personId获取person
    public Person getPersonById(Integer personId) {
        Person person = null;
        person = personService.findPersonById(personId);
        return person;
    }

    //根据学生姓名获取学生数据   2015-11-13
    public List<SchoolUser> getStudentByName(UserDetailInfo userDetailInfo) {
        SchoolUserCondition suc = new SchoolUserCondition();
        suc.setSchoolId(userDetailInfo.getSchoolId());
        suc.setName(userDetailInfo.getName());
        suc.setUserType("4");
        List<SchoolUser> list = schoolUserService.findSchoolUserByCondition(suc, null, null);
        return list;
    }

    //根据学生别名获取学生数据   2015-11-13
    public List<Student> getStudentByAlias(String alias, Integer schoolId) {
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(schoolId);
        studentCondition.setIsDelete(false);
        studentCondition.setAlias(alias);
        List<Student> list = studentDao.findStudentByOnlyCondition(studentCondition, null, null);
        return list;
    }

    //根据身份证获取个人档案
    public Person getPersonByIdCardNumber(String idCardNumber) {
        Person person = null;
        PersonCondition personCondition = new PersonCondition();
        personCondition.setIdCardNumber(idCardNumber);
        personCondition.setIsDelete(false);
        List<Person> personList = personService.findPersonByCondition(personCondition, null, null);
        if (personList.size() > 0) {
            person = personList.get(0);
        }
        return person;
    }

    //根据档案获取本校某生
    public Student getStudentByPersonId(Person person, Integer schoolId) {
        Student student = null;
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(schoolId);
        studentCondition.setIsDelete(false);
        studentCondition.setPersonId(person.getId());
        List<Student> studentList = studentDao.findStudentByCondition(studentCondition, null, null);
        if (studentList.size() > 0) {
            student = studentList.get(0);
        }
        return student;
    }

    //==============================为创建学生数据提供方法============================================//

    //==============================为修改学生姓名提供方法============================================//
    public UserDetailInfo updateStudentName(UserDetailInfo userDetailInfo, Boolean isBindingMobile) {
        //2016-9-5 新增参数 isBindingMobile 表示是否绑定学生手机号
        if (isBindingMobile == null) {
            isBindingMobile = false;
        }
        Student student = studentDao.findById(userDetailInfo.getStudentId());
    //	Student student = studentService.findOfUser(userDetailInfo.getSchoolId(), userDetailInfo.getUserId());
        //因为页面没有提供别名，所以即使有别名传过来的也是null，在这里set别名进去 2016-1-26
        if (student != null) {
            userDetailInfo.setAlias(student.getAlias() == null ? null : student.getAlias());
        } else {
            userDetailInfo.setErrorCode(StudentContants.STUDENT_NOT_EXIST);
            userDetailInfo.setMessage("数据异常，学生不存在");
            return userDetailInfo;
        }

        //如果用户修改了名字，进行D流程  去检测用户的姓名是否有重复
        if (!student.getName().equals(userDetailInfo.getName())) {
            //因为D流程中包含了E流程  在这里重新写一个只有D流程的流程，便于引用  2016-1-26
            userDetailInfo = processOfOnlyRecordD(userDetailInfo, null);
        }

        //如果用户修改手机  去修改绑定表
        //null.eq表达式不通过  暂时修改
        if (student.getMobile() == null) {
            student.setMobile("");
        }
        if (userDetailInfo.getCellPhone() == null) {
            userDetailInfo.setCellPhone("");
        }

        //判断身份证是否已存在   2016-2-25
        if (student.getPersonId() != null) {
            if (userDetailInfo.getCertificateNum() != null && !"".equals(userDetailInfo.getCertificateNum())) {
                List<Person> personList = getPersonByIdCard(userDetailInfo.getCertificateNum());
                if (personList != null) {
                    for (Person p : personList) {
                        if (p.getIdCardNumber() != null && !"".equals(p.getIdCardNumber()) && !p.getId().equals(student.getPersonId())) {
                            if (p.getIdCardNumber().equals(userDetailInfo.getCertificateNum())) {
                                userDetailInfo.setErrorCode(StudentContants.IDCARDNUMBER_EXIST);
                                userDetailInfo.setMessage("该身份证已存在");
                                return userDetailInfo;
                            }
                        }
                    }
                }
            }
        }

        //2016-9-5新增参数isBindingMobile 表示是否绑定手机号
        if (isBindingMobile) {
            //添加手机号是否重复的校验   2016-2-25
            if (!student.getMobile().equals(userDetailInfo.getCellPhone()) && userDetailInfo != null) {
                //判断手机号是否重复
                UserBinding ub = userBindingService.findByBindingName(userDetailInfo.getCellPhone());
                if (ub != null && !ub.getUserId().equals(student.getUserId())) {
                    userDetailInfo.setErrorCode(StudentContants.MOBILE_ALREADY_USED);
                    userDetailInfo.setMessage("修改的手机号已存在");
                    return userDetailInfo;
                }
                updateUsreBinding(userDetailInfo);
            }
        }

        //---------修改食堂数据 2021，11，16，食堂新增修改接口------------------------
        EmployeeList employeeList=new EmployeeList();
        //必传
        employeeList.setEmp_pycode(String.valueOf(student.getUserId()));
        if(userDetailInfo.getName()!=null && userDetailInfo.getName()!=""){
            //必传
            employeeList.setEmp_name(userDetailInfo.getName());
        }else{
            employeeList.setEmp_name(student.getName());
        }
      if(userDetailInfo.getSex()!=null && userDetailInfo.getSex()!=""){
          //必传
          employeeList.setEmp_sex(userDetailInfo.getSex());
      }else{
          if(student.getSex()=="1"){
              employeeList.setEmp_sex("男");
          }else if(student.getSex()=="0"){
              employeeList.setEmp_sex("女");
          }
      }
      if(userDetailInfo.getStudentNum()!=null && userDetailInfo.getStudentNum()!=""){
          //必传
          employeeList.setEmp_code(userDetailInfo.getStudentNum());
      }else{
          employeeList.setEmp_code(student.getEmpCode());
      }
      //部门名称
        String teamNames=banjimingchengzhuanhuan2(student.getTeamName());
        employeeList.setDept_name(teamNames);
        //必传
      employeeList.setEmp_card(student.getEmpCard());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        //必穿
        employeeList.setEmp_workdate(ft.format(student.getCreateDate()));

        if(userDetailInfo.getBirthDate()!=null && !userDetailInfo.getBirthDate().equals("") ){
            //必传
          employeeList.setEmp_birthday(ft.format(userDetailInfo.getBirthDate()));
      }
      if(userDetailInfo.getParentCellPhone()!=null && userDetailInfo.getParentCellPhone()!=""){
          employeeList.setEmp_tel(userDetailInfo.getParentCellPhone());
      }
      if(userDetailInfo.getCertificateNum()!=null && userDetailInfo.getCertificateNum()!=""){
          //必传
          employeeList.setEmp_idcard(userDetailInfo.getCertificateNum());
      }else{
          employeeList.setEmp_idcard(student.getUserName());
      }
      if(userDetailInfo.getAddress()!=null && userDetailInfo.getAddress()!=""){
          employeeList.setEmp_address(userDetailInfo.getAddress());
        }
        Object object = JSONObject.toJSON(employeeList);
        JSONObject param = new JSONObject();
        param.put("sign_name","kksss");
        //param.put("tran_code","emp_add");
        param.put("tran_code","emp_update");
        param.put("employeeList", object);
        //canteenThreadPoolExecutor.submit
        HttpRequestResult httpRequestResult=null;
        //10.191.109.85
        HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdate")
                .addHeader("content-type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        config.json(param.toString());
        try {
            httpRequestResult = HttpClientUtils.post(config);
            //判断食堂修改接口返回信息
            if (httpRequestResult == null) {
                log.info("食堂无返回信息");
            }
            if (200 == httpRequestResult.getCode()) {
                String responseText = httpRequestResult.getResponseText();
                if (("").equals(responseText) || responseText == null) {
                    log.info("食堂返回信息为空");
                }
                JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                System.out.println("返回信息"+responseText);
            }else {
                log.error("食堂接口--修改用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
            }
            log.info("食堂接口--食堂修改接口修改状态{}"," 返回信息: {}----"+httpRequestResult);
        }catch (IOException e) {
            log.info("食堂接口--食堂修改接口修改状态{}， 请求远程接口失败：{}"+ e);
        }

        //修改student表
        updateStudent(userDetailInfo);

        //修改person表
        updatePerson(userDetailInfo);

        //修改学校用户表schoolUser
        updateSchoolUser(userDetailInfo);

        //修改teamStudent表
        updateteamStudent(userDetailInfo);

        return userDetailInfo;
    }

    public List<Person> getPersonByIdCard(String idCard) {
        List<Person> personList = null;
        PersonCondition personCondition = new PersonCondition();
        personCondition.setIdCardNumber(idCard);
        personCondition.setIsDelete(false);
        personList = personService.findPersonByCondition(personCondition, null, null);
        return personList;
    }

    public void updateStudent(UserDetailInfo userDetailInfo) {
        Student student = new Student();
        student.setId(userDetailInfo.getStudentId());
        student.setSchoolId(userDetailInfo.getSchoolId());
        student.setPersonId(userDetailInfo.getPersonId());
        student.setUserId(userDetailInfo.getUserId());
        student.setUserName(userDetailInfo.getUsername());
        student.setName(userDetailInfo.getName());
        student.setSex(userDetailInfo.getSex());
        student.setStudentNumber(userDetailInfo.getStudentNum());
        student.setStudentNumber2(userDetailInfo.getStudentNum2());
        student.setEnrollDate(userDetailInfo.getEnterDate());    //入学时间
        student.setLeaveDate(userDetailInfo.getEndDate());        //离校时间
        student.setMobile(userDetailInfo.getCellPhone());
        student.setIsBoarded(userDetailInfo.getIsBoarded());
        student.setStudyState(userDetailInfo.getState());
        student.setStudentType(userDetailInfo.getStudentType());
        student.setPosition(userDetailInfo.getPosition());
        student.setCreateDate(new Date());
        student.setIsDelete(false);
        student.setAlias(userDetailInfo.getAlias());
        student.setEmpCode(userDetailInfo.getStudentNum());
        //2021,12,21增加修改班级 ，Zy，ლ(´ڡ`ლ)ﾞ
        student.setTeamId(Integer.parseInt(userDetailInfo.getTeamId()));
       Team team=teamService.findTeamById(Integer.parseInt(userDetailInfo.getTeamId()));
        student.setTeamName(team.getName());
        studentDao.update(student);
    }

    public void updatePerson(UserDetailInfo userDetailInfo) {
        Person person = new Person();
        person.setId(userDetailInfo.getPersonId());
        person.setName(userDetailInfo.getName());
        person.setEnglishName(userDetailInfo.getEnglishName());
        person.setSex(userDetailInfo.getSex());
        person.setBirthday(userDetailInfo.getBirthDate());
        person.setIdCardNumber(userDetailInfo.getCertificateNum());
        person.setIdCardType(userDetailInfo.getCertificateType());
        person.setNationality(userDetailInfo.getNationality());
        person.setNativePlace(userDetailInfo.getNativePlace());
        person.setBirthPlace(userDetailInfo.getBirthPlace());
        person.setRace(userDetailInfo.getNation());
        person.setPoliticalStatus(userDetailInfo.getPolitical());
        person.setReligion(userDetailInfo.getReligiousBelief());
        person.setMarriage(userDetailInfo.getMarriage());
        person.setAbroadCode(userDetailInfo.getAbroadCode());
        person.setHealthStatus(userDetailInfo.getHealthStatus());
        person.setBloodType(userDetailInfo.getBloodType());
        person.setResidenceType(userDetailInfo.getRegister());
        person.setProvince(userDetailInfo.getProvince());
        person.setCity(userDetailInfo.getCity());
        person.setDistrict(userDetailInfo.getDistrict());
        person.setStreet(userDetailInfo.getStreet());
        person.setAddress(userDetailInfo.getNowAddress());
        person.setResideAddress(userDetailInfo.getRegisterPlace());
        person.setMobile(userDetailInfo.getCellPhone());
        person.setTelephone(userDetailInfo.getTelephone());
        person.setEmail(userDetailInfo.getEmail());
        person.setSpecialSkill(userDetailInfo.getSpecialty());
        person.setOnlyChild(userDetailInfo.getIsOnlyChild());
        boolean flag = false;
        if (userDetailInfo.getIsFloatingPopulation() == "0") {
            flag = false;
        } else {
            flag = true;
        }
        person.setFloatingPopulation(flag);
        person.setEntityId(userDetailInfo.getEntityId());
        person.setRemark(userDetailInfo.getRemark());
        person.setCreateDate(new Date());
        person.setModifyDate(new Date());
        person.setIsDelete(false);
        personService.modify(person);
    }

    public void updateSchoolUser(UserDetailInfo userDetailInfo) {
        SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
        schoolUserCondition.setUserId(userDetailInfo.getUserId());
        schoolUserCondition.setPersonId(userDetailInfo.getPersonId());
        schoolUserCondition.setIsDeleted(false);
        List<SchoolUser> list = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
        if (list.size() > 0) {
            SchoolUser schoolUser = new SchoolUser();
            schoolUser.setId(list.get(0).getId());
            schoolUser.setCreateDate(new Date());
            schoolUser.setModifyDate(new Date());
            schoolUser.setName(userDetailInfo.getUsername());
            schoolUser.setOwnerId(userDetailInfo.getStudentId());
            schoolUser.setSchoolId(userDetailInfo.getSchoolId());
            schoolUser.setUserId(userDetailInfo.getUserId());
            schoolUser.setPersonId(userDetailInfo.getPersonId());
            schoolUser.setUserType(studentType);
            schoolUser.setAlias(userDetailInfo.getAlias());
            schoolUserService.modify(schoolUser);
        }
    }

    /**
     * @param userDetailInfo
     * @function 修改学生名字的时候  顺便将teamStudent表中的名字修改
     * @date 2016-2-24
     */
    public void updateteamStudent(UserDetailInfo userDetailInfo) {
        if (userDetailInfo != null) {
            TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
            teamStudentCondition.setStudentId(userDetailInfo.getStudentId());
            List<TeamStudent> teamStudentList = teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
            if (teamStudentList != null && teamStudentList.size() > 0) {
                for (TeamStudent teamStudent : teamStudentList) {
                    teamStudent.setModifyDate(new Date());
                    if (userDetailInfo.getStudentNum2() != null && !"".equals(userDetailInfo.getStudentNum2())) {
                        teamStudent.setNumber(Integer.parseInt(userDetailInfo.getStudentNum2()));
                    }
                    teamStudent.setNumber(userDetailInfo.getNumber());
                    teamStudent.setName(userDetailInfo.getName());
                    teamStudentService.modify(teamStudent);
                }
            }
            String sql="update pj_team_student set grade_id="+userDetailInfo.getGradeId()+
                    " ,team_id="+userDetailInfo.getTeamId()+" where student_id="+userDetailInfo.getStudentId()+" and grade_id="+userDetailInfo.getOrdergradeId()+" and  is_delete=0";
            basicSQLService.update(sql);
        }
    }

    public void updateUsreBinding(UserDetailInfo userDetailInfo) {
        UserBindingCondition ubc = new UserBindingCondition();
        ubc.setBindingType(UserBindingContants.TYPE_PHONE);
        ubc.setIsDeleted(false);
        ubc.setUserId(userDetailInfo.getUserId());
        List<UserBinding> list = userBindingService.findUserBindingByCondition(ubc, null, null);
        UserBinding ub = new UserBinding();

        //如果存在记录，将原记录删除
        if (list.size() > 0) {
            ub.setId(list.get(0).getId());
            userBindingService.remove(ub);
        }

        //如果用户修改手机号  则进行保存操作  如果是删除手机号 则不保存新纪录
        if (userDetailInfo.getCellPhone() != null && !"".equals(userDetailInfo.getCellPhone())) {
            ub = new UserBinding();
            ub.setBindingType(UserBindingContants.TYPE_PHONE);
            ub.setBindingName(userDetailInfo.getCellPhone());
            ub.setIsDeleted(false);
            ub.setCreateDate(new Date());
            ub.setEnabled(true);
            ub.setUserId(userDetailInfo.getUserId());
            userBindingService.add(ub);
        }
    }


    /**
     * 功能描述：导入学生 2016-2-2
     *
     * @param extImportStudentVo
     * @param appId
     * @return extImportTeacherErrorMsg 某个学生导入的错误信息
     */
    @Override
    public ExtImportErrorMsg ImportInfoForStudent(ExtImportStudentVo extImportStudentVo, String password, String userType, Integer appId, Integer schoolId, Integer teamId, Boolean isBindingMobile) {
        if (isBindingMobile == null) {
            isBindingMobile = false;
        }
        ExtImportErrorMsg msg = null;
        UserDetailInfo userDetailInfo = new UserDetailInfo();
        try {
            //根据appId和groupId查询学生角色
            //2016-3-22   添加  导入学生时候  学生角色不对应的问题
            Group group = groupService.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
            if (group != null) {
                Role role = roleService.findUniqueInGroup(appId, group.getId(), SysDefRole.STUDENT);
                if (role != null) {
                    userDetailInfo.setRole(role.getId() + "");
                }
            }

            if (extImportStudentVo != null && appId != null && schoolId != null) {
                userDetailInfo.setSchoolId(schoolId);
                userDetailInfo.setName(extImportStudentVo.getName());
                userDetailInfo.setAlias(extImportStudentVo.getAlias());
                userDetailInfo.setPosition(extImportStudentVo.getPosition());
                userDetailInfo.setNumber(extImportStudentVo.getNumber());
                userDetailInfo.setSex(extImportStudentVo.getSex());
                userDetailInfo.setStudentTeamId(teamId);
                UserDetailInfo mess = processOfRecordA(userDetailInfo, password, userType, appId, isBindingMobile);

                //判断有错误信息的时候才返回给客户端   2016-2-26
                if (mess.getMessage() != null && !"".equals(mess.getMessage())) {
                    msg = new ExtImportErrorMsg();
                    msg.setId(extImportStudentVo.getId());
                    msg.setMsg(mess.getMessage());
                }
            }
        } catch (Exception e) {
            log.debug("导入学生失败", e);
            return msg;
        }
        return msg;
    }

    @Override
    public UserDetailInfo saveUserInfo(UserDetailInfo userDetailInfo, String password, String userType,
                                       Integer appId, Boolean isBindingMobile) throws Exception {
        if (isBindingMobile == null) {
            isBindingMobile = false;
        }
        return processOfRecordA(userDetailInfo, password, userType, appId, isBindingMobile);
    }

    @Override
    public List<Student> findFinishState(
            StudentCondition studentCondition, Page page, Order desc) {
        if (studentCondition == null) {
            return null;
        }
        if (studentCondition.getTeamId() == null) {
            return null;
        }
        return studentDao.findFinishState(studentCondition, page, desc);
    }

    public List<StudentVo> findArchiveSummary(Integer teamId, Boolean isFinish) {
        return studentDao.findArchiveSummary(teamId, StudentArchiveContants.TYPE_FINISH, isFinish);
    }

    @Override
    /**
     *
     */
    public void upTeamStudentByTeamId() {
        List<TeamStudentVo> tsList = teamStudentService.findTeamNum();
        //System.out.println("tsList:"+tsList);
        for (TeamStudentVo teamStudentVo : tsList) {
            Team team = this.teamService.findTeamById(teamStudentVo.getTeamId());
            team.setMemberCount(Integer.parseInt(teamStudentVo.getStuNum()));
            teamService.modify(team);
        }
    }

    @Override
    public List<UserDetailInfo> findStudentDetailInfo(StudentCondition studentCondition) throws Exception {
        List<UserDetailInfo> userDetailInfoList = new ArrayList<UserDetailInfo>();

        List<Student> studentList = this.studentDao.findStudentByCondition(studentCondition, null, null);

        for (Student s : studentList) {
            UserDetailInfo UserDetailInfo = findUserDetailInfoById(s.getId().toString());
            userDetailInfoList.add(UserDetailInfo);
        }

        return userDetailInfoList;
    }

    public UserDetailInfo findUserDetailInfoById(String id) throws Exception {
        UserDetailInfo userDetailInfo = null;
        try {
            userDetailInfo = new UserDetailInfo();
            Student student = studentDao.findById(Integer.parseInt(id));
            User user = userService.findUserById(student.getUserId());
            Person person = personService.findPersonById(student.getPersonId());

            if (user != null) {
                userDetailInfo.setUserId(user.getId());
                userDetailInfo.setUsername(user.getUserName());
            }

            if (student != null) {
                userDetailInfo.setStudentId(student.getId());
                userDetailInfo.setStudentNum(student.getStudentNumber());
                userDetailInfo.setState(student.getStudyState());
                userDetailInfo.setStudentType(student.getStudentType());
                userDetailInfo.setPosition(student.getPosition());
                userDetailInfo.setEnterDate(student.getEnrollDate());
                userDetailInfo.setEndDate(student.getLeaveDate());
                userDetailInfo.setStudentNum2(student.getStudentNumber2());
            }

            if (person != null) {
                userDetailInfo.setPersonId(person.getId());
                userDetailInfo.setName(person.getName());
                userDetailInfo.setEnglishName(person.getEnglishName());
                userDetailInfo.setSex(person.getSex());
                userDetailInfo.setCertificateNum(person.getIdCardNumber());
                userDetailInfo.setCertificateType(person.getIdCardType());
                userDetailInfo.setNationality(person.getNationality());
                userDetailInfo.setNativePlace(person.getNativePlace());
                userDetailInfo.setBirthPlace(person.getBirthPlace());
                userDetailInfo.setNation(person.getRace());
                userDetailInfo.setPolitical(person.getPoliticalStatus());
                userDetailInfo.setReligiousBelief(person.getReligion());
                userDetailInfo.setMarriage(person.getMarriage());
                userDetailInfo.setAbroadCode(person.getAbroadCode());
                userDetailInfo.setHealthStatus(person.getHealthStatus());
                userDetailInfo.setBloodType(person.getBloodType());
                userDetailInfo.setProvince(person.getProvince());
                userDetailInfo.setCity(person.getCity());
                userDetailInfo.setDistrict(person.getDistrict());
                userDetailInfo.setStreet(person.getStreet());
                userDetailInfo.setNowAddress(person.getAddress());
                userDetailInfo.setRegisterPlace(person.getResideAddress());
                userDetailInfo.setTelephone(person.getTelephone());
                userDetailInfo.setCellPhone(person.getMobile());
                userDetailInfo.setEmail(person.getEmail());
                userDetailInfo.setSpecialty(person.getSpecialSkill());
                userDetailInfo.setIsOnlyChild(person.isOnlyChild());

                userDetailInfo.setBirthDate(person.getBirthday());
                userDetailInfo.setLiveAddress(person.getResideAddress());
                userDetailInfo.setRegister(person.getResidenceType());
                userDetailInfo.setRemark(person.getRemark());
                userDetailInfo.setEntityId(person.getEntityId());

                boolean b = person.isFloatingPopulation();
                String flag = "0";
                if (b == true) {
                    flag = "1";
                } else {
                    flag = "0";
                }
                userDetailInfo.setIsFloatingPopulation(flag);
            }

            if (student != null) {
                //查询出家庭成员
                ParentStudentCondition psCondition = new ParentStudentCondition();
                psCondition.setStudentUserId(student.getUserId());
                psCondition.setIsDelete(false);
                List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(psCondition);
                List<Parent> parents = new ArrayList<Parent>();
                for (ParentStudent parentStudent : psList) {
                    Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
                    if (parent != null) {
                        ParentVo parentVo = new ParentVo();
                        BeanUtils.copyProperties(parentVo, parent);
                        parentVo.setParentRelation(parentStudent.getParentRelation());
                        parents.add(parentVo);
                    }
                }
                userDetailInfo.setParentList(parents);
            }

        } catch (Exception e) {
            log.info("查询UserDetailInfo异常");
            //e.printStackTrace();
            throw e;
        }
        return userDetailInfo;
    }

    @Override
    public List<Student> findbyUserIds(Integer[] userIds) {
        List<Student> studentList = null;
        if (userIds.length > 0) {
            studentList = studentDao.findbyUserIds(userIds);
        }
        return studentList;
    }


    //---------- 学生统计   start ----------
    @Override
    public List<StatisticDate> findNumberOfGradeBySchoolId(
            Integer schoolId, String schoolYear) {
        return studentDao.findNumberOfGradeBySchoolId(schoolId, schoolYear);
    }

    @Override
    public List<StatisticDate> findTeamNumberBySchoolId(
            Integer schoolId, String schoolYear) {
        return studentDao.findTeamNumberBySchoolId(schoolId, schoolYear);
    }

    @Override
    public List<StatisticDate> findNativePlaceBySchoolId(
            Integer schoolId) {
        return studentDao.findNativePlaceBySchoolId(schoolId);
    }

    @Override
    public void setMonitor(Integer userId, Integer teamId) {
        List<Student> list = this.studentDao.findStudentByTeamId(teamId);
        for (Student student : list) {
            if (student.getUserId().equals(userId)) {
                student.setPosition("班长");
            } else {
                student.setPosition("");
            }
            this.studentDao.update(student);
        }
    }

    @Override
    public List<StatisticDate> findHealthStatusBySchoolId(
            Integer schoolId) {
        return studentDao.findHealthStatusBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findEnrollTypeBySchoolId(Integer schoolId) {
        return studentDao.findEnrollTypeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findAttendSchoolTypeBySchoolId(
            Integer schoolId) {
        return studentDao.findAttendSchoolTypeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findStudentSourceBySchoolId(
            Integer schoolId) {
        return studentDao.findStudentSourceBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findSexBySchoolId(Integer schoolId) {
        return studentDao.findSexBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findAgeBySchoolId(Integer schoolId) {
        return studentDao.findAgeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findPoliticalStatusBySchoolId(
            Integer schoolId) {
        return studentDao.findPoliticalStatusBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findRaceBySchoolId(Integer schoolId) {
        return studentDao.findRaceBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findResidenceTypeBySchoolId(
            Integer schoolId) {
        return studentDao.findResidenceTypeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findNumberOfSchoolByAreaCode(
            String areaCode) {
        return studentDao.findNumberOfSchoolByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findTeamNumberByAreaCode(
            String areaCode, String schoolYear) {
        return studentDao.findTeamNumberByAreaCode(areaCode, schoolYear);
    }

    @Override
    public List<StatisticDate> findNativePlaceByAreaCode(String areaCode) {
        return studentDao.findNativePlaceByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findHealthStatusByAreaCode(
            String areaCode) {
        return studentDao.findHealthStatusByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findEnrollTypeByAreaCode(String areaCode) {
        return studentDao.findEnrollTypeByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findAttendSchoolTypeByAreaCode(
            String areaCode) {
        return studentDao.findAttendSchoolTypeByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findStudentSourceByAreaCode(
            String areaCode) {
        return studentDao.findStudentSourceByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findSexByAreaCode(String areaCode) {
        return studentDao.findSexByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findAgeByAreaCode(String areaCode) {
        return studentDao.findAgeByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findPoliticalStatusByAreaCode(
            String areaCode) {
        return studentDao.findPoliticalStatusByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findRaceByAreaCode(String areaCode) {
        return studentDao.findRaceByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findResidenceTypeByAreaCode(
            String areaCode) {
        return studentDao.findResidenceTypeByAreaCode(areaCode);
    }
    //---------- 学生统计   end ----------

    @Override
    public List<StudentArchiveCompleteVo> findCompleteByUserIds(
            int[] userIds) {
        return this.studentDao.findCompleteByUserIds(userIds);
    }

    @Override
    public List<StudentVo> findIncrementDataByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer studentId, Boolean isGetNew) {
        return studentDao.findIncrementDataByModifyDate(schoolId, schoolYear, isDeleted, modifyDate, studentId, isGetNew);
    }

    @Override
    public List<Student> findTeamStudentByCondition1(StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findTeamStudentByCondition1(studentCondition, page, order);
    }

    @Override
    public List<StudentVo> findStudentVoByCondition(StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findStudentVoByCondition(studentCondition, page, order);
    }

    @Override
    public List<StudentVo> findByTeamOrName(Integer schoolId, String schoolYear, Integer teamId, String name) {
        return this.studentDao.findByTeamOrName(schoolId, schoolYear, teamId, name);
    }

    @Override
    public Map<String, Object> addStudentFromExcelImport(String kh,String gradeName, String teamName, String num, String studentname, String guardianPhone,
                                                         String guardian, Integer schoolId, String schoolYear, Integer appid, String studentType, String parentType, List<EmployeeList> list) {
        /**数据格式校验*/
        Map<String, Object> entity = DataImportCheck.checkStudentData(num, studentname, gradeName, teamName, guardian, guardianPhone);
        Integer status = (Integer) entity.get("status");
        if (status - 2 == 0) {
            return entity;
        }

        Grade grade = gradeService.addGradeUseSchoolIdYearAndName(schoolId, schoolYear, gradeName);
        if (grade == null) {
            entity.put("status", 2);
            entity.put("errorFiled", "grade");
            entity.put("errorInfo", "该学校无法创建该年级");
            return entity;
        }

        gradeName = grade.getName();
        Integer gradeId = grade.getId();
        /**创建班级*/
        Team team = teamService.findBySchoolGradeIdAndName(schoolId, gradeId, gradeName, teamName, schoolYear);
        if (team == null) {
            team = teamService.addTeamUseSchoolGradeIdAndName(schoolId, gradeId, gradeName, teamName, schoolYear);
            entity.put("teamCount", 1);


        }
        Integer teamId = team.getId();

        /**检查学生是否重复*/
        boolean checkStatus = checkStudentIsRepeat(teamId, studentname, guardianPhone);
        if (!checkStatus) {
            entity.put("status", 2);
            entity.put("errorFiled", "name");
            entity.put("errorInfo", "学生已经存在");
            return entity;
        }
        /**检查班内学号是否重复*/

        if(StringUtils.isEmpty(num) || StringUtils.isEmpty(kh)){
            entity.put("status", 2);
            entity.put("errorFiled", "num");
            entity.put("errorInfo", "学号、卡号不可为空");

            return entity;
        }

        //Integer number = checkNumIsRepeat(teamId, num);
        if (basicSQLService.findUniqueLong("select exists(select 1 from pj_student ps  inner join pj_team pt on pt.id=ps.team_id inner join pj_grade pg on pt.grade_id=pg.id where ps.is_delete=0 and pt.is_delete=0 and ps.emp_code='"+num+"'  and pg.school_year='"+schoolYear+"' and study_state='01') e;")>0) {
            entity.put("status", 2);
            entity.put("errorFiled", "num");
            entity.put("errorInfo", "学号已经存在");
            return entity;
        }

        if(StringUtils.isEmpty(kh) || kh.length()!=10){
            entity.put("status", 2);
            entity.put("errorFiled", "kh");
            entity.put("errorInfo", "卡号长度必须是10位");
            return entity;
        }


        if (basicSQLService.findUniqueLong("select exists(select 1 from pj_student where is_delete=0 and emp_card='"+kh+"' ) e")>0) {
            entity.put("status", 2);
            entity.put("errorFiled", "kh");
            entity.put("errorInfo", "卡号已经存在");
            return entity;
        }


        /**获取学生角色*/
        Role role = roleService.findStudentRoleBySchoolIdAndAppid(schoolId, appid);
        /**创建用户和账号*/
        User user = userService.addUserFromAccount(schoolId, appid, GroupContants.TYPE_SCHOOL);
        if (user == null) {
            entity.put("status", 2);
            entity.put("errorFiled", "os");
            entity.put("errorInfo", "系统账号已经分配完毕，请联系管理员");

            return entity;
        }
        Integer userId = user.getId();

        /**创建用户和角色关联关系*/
        userRoleService.addUserRole(user, role);

        /**创建学生*/
        Student student = addStudentByUserTeamSchoolIdAndName(schoolId, userId, teamId, studentname,num,kh);
        Date now = new Date();

        EmployeeList employeeList=new EmployeeList();
        // 名称
        employeeList.setEmp_name(studentname);
       // list.add(new DetailVo("emp_name", studentname, "String"));
        // 入学日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
        //list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
        // 用户名登录
        employeeList.setEmp_code(num);
       // list.add(new DetailVo("emp_code", num, "String"));
        employeeList.setEmp_pycode(userId.toString());
        // 食堂卡号
        employeeList.setEmp_card(kh);
        //list.add(new DetailVo("emp_card", kh, "String"));
        //employeeList.setDept_code("000002");
        //list.add(new DetailVo("dept_code", "000002", "String"));
        String str=banjimingchengzhuanhuan(gradeName,teamName);
        log.info("部门名称"+str);
        employeeList.setDept_name(str);
        //list.add(new DetailVo("dept_name", team.getName(), "String"));
        employeeList.setEmp_tel(guardianPhone);
        employeeList.setEmp_idcard(user.getUserName());
        list.add(employeeList);

//		//闸机接口数据
////		hikvisionPerson.setPersonId();
//		hikvisionPerson.setPersonName(student.getName());
//		if (student.getName()!=null && student.getName().equals("") && student.getAlias()!=null && student.getAlias().equals("")){
//			hikvisionPerson.setPersonName(student.getName()+student.getAlias());
//		}
//		else {
//			hikvisionPerson.setPersonName(student.getName());
//		}
//		hikvisionPerson.setGenderNum(Integer.valueOf(student.getSex()));
//		hikvisionPerson.setPhoneNo(student.getMobile());
//		hikvisionPerson.setOrgIndexCode(student.getSchoolId()+","+student.getTeamName());
//		BASE64Encoder base64Encoder = new BASE64Encoder();
//		hikvisionPerson.setFaceData(base64Encoder.encode(hikvisionPerson.getPersonName().getBytes()));

        Integer studentId = student.getId();
        /**把用户加到对应的学校*/
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setAlias(studentname);
        schoolUser.setCreateDate(now);
        schoolUser.setInSchool(true);
        schoolUser.setIsDeleted(false);
        schoolUser.setModifyDate(now);
        schoolUser.setName(studentname);
        schoolUser.setSchoolId(schoolId);
        schoolUser.setUserId(userId);
        schoolUser.setUserName(user.getUserName());
        schoolUser.setUserType(studentType);
        schoolUser.setOwnerId(studentId);
        schoolUserService.add(schoolUser);

        /**把学生加到对应的班级*/
        TeamStudent teamStudent = new TeamStudent();
        teamStudent.setCreateDate(now);
        teamStudent.setGradeId(gradeId);
        teamStudent.setGradeName(grade.getName());
        teamStudent.setInState(true);
        teamStudent.setIsDelete(false);
        teamStudent.setModifyDate(now);
        teamStudent.setName(studentname);
       try {
           teamStudent.setNumber(Integer.valueOf(num));
       }catch ( Exception e){}
        teamStudent.setRecordDate(now);
        teamStudent.setJoinDate(now);
        teamStudent.setStudentId(student.getId());
        teamStudent.setTeamId(teamId);
        teamStudent.setUserId(userId);
        teamStudentService.add(teamStudent);

        /**用户和班级的关系*/
        TeamUser teamUser = new TeamUser();
        teamUser.setCreateDate(now);
        teamUser.setIsMaster(false);
        teamUser.setIsParent(false);
        teamUser.setIsStudent(true);
        teamUser.setIsTeacher(false);
        teamUser.setModifyDate(now);
        teamUser.setName(studentname);
        teamUser.setSex("");
        teamUser.setTeamId(teamId);
        teamUser.setUserId(userId);
        teamUserService.add(teamUser);
        if (guardianPhone != null && !"".equals(guardianPhone)) {
            boolean result = parentService.addParentFromExcelImport(schoolId, appid, gradeId, teamId, userId, guardian, guardianPhone, parentType, "51", "1", null);
            if (result) {
                entity.put("parent", 1);
            }
        } else {
            entity.put("guardian", "");
        }
        entity.put("num", num);
        entity.put("studentId", student.getId());
        return entity;
    }
    //班级格式转换
    private String banjimingchengzhuanhuan2(String teamName){
        if(teamName==null && teamName.equals("")){
            return null;
        }else {
            if (teamName.equals("一年级(1)班")) {
                String str = "一年级一班";
                return str;
            }
            if (teamName.equals("一年级(2)班")) {
                String str = "一年级二班";
                return str;
            }
            if (teamName.equals("一年级(3)班")) {
                String str = "一年级三班";
                return str;
            }
            if (teamName.equals("一年级(4)班")) {
                String str = "一年级四班";
                return str;
            }
            if (teamName.equals("一年级(5)班")) {
                String str = "一年级五班";
                return str;
            }
            if (teamName.equals("一年级(6)班")) {
                String str = "一年级六班";
                return str;
            }
            if (teamName.equals("一年级(7)班")) {
                String str = "一年级七班";
                return str;
            }
            if (teamName.equals("一年级(8)班")) {
                String str = "一年级八班";
                return str;
            }
            if (teamName.equals("一年级(9)班")) {
                String str = "一年级九班";
                return str;
            }
            if (teamName.equals("一年级(10)班")) {
                String str = "一年级十班";
                return str;
            }
            if (teamName.equals("一年级(11)班")) {
                String str = "一年级十一班";
                return str;
            }
            if (teamName.equals("二年级(2)班")) {
                String str = "二年级二班";
                return str;
            }
            if (teamName.equals("二年级(3)班")) {
                String str = "二年级三班";
                return str;
            }
            if (teamName.equals("二年级(4)班")) {
                String str = "二年级四班";
                return str;
            }
            if (teamName.equals("二年级(5)班")) {
                String str = "二年级五班";
                return str;
            }if (teamName.equals("二年级(6)班")) {
                String str = "二年级六班";
                return str;
            }if (teamName.equals("二年级(7)班")) {
                String str = "二年级七班";
                return str;
            }if (teamName.equals("二年级(8)班")) {
                String str = "二年级八班";
                return str;
            }
            if (teamName.equals("二年级(9)班")) {
                String str = "二年级九班";
                return str;
            }
            if (teamName.equals("二年级(10)班")) {
                String str = "二年级十班";
                return str;
            }if (teamName.equals("二年级(11)班")) {
                String str = "二年级十一班";
                return str;
            }if (teamName.equals("二年级(1)班")) {
                String str = "二年级一班";
                return str;
            }if (teamName.equals("三年级(1)班")) {
                String str = "三年级一班";
                return str;
            }
            if (teamName.equals("三年级(2)班")) {
                String str = "三年级二班";
                return str;
            }
            if (teamName.equals("三年级(3)班")) {
                String str = "三年级三班";
                return str;
            }
            if (teamName.equals("三年级(4)班")) {
                String str = "三年级四班";
                return str;
            }
            if (teamName.equals("三年级(5)班")) {
                String str = "三年级五班";
                return str;
            }
            if (teamName.equals("三年级(6)班")) {
                String str = "三年级六班";
                return str;
            }
            if (teamName.equals("三年级(7)班")) {
                String str = "三年级七班";
                return str;
            }
            if (teamName.equals("三年级(8)班")) {
                String str = "三年级八班";
                return str;
            }
            if (teamName.equals("三年级(9)班")) {
                String str = "三年级九班";
                return str;
            }
            if (teamName.equals("三年级(10)班")) {
                String str = "三年级十班";
                return str;
            }
            if (teamName.equals("三年级(11)班")) {
                String str = "三年级十一班";
                return str;
            }if (teamName.equals("四年级(1)班")) {
                String str = "四年级一班";
                return str;
            }
            if (teamName.equals("四年级(2)班")) {
                String str = "四年级二班";
                return str;
            }
            if (teamName.equals("四年级(3)班")) {
                String str = "四年级三班";
                return str;
            }
            if (teamName.equals("四年级(4)班")) {
                String str = "四年级四班";
                return str;
            }
            if (teamName.equals("四年级(5)班")) {
                String str = "四年级五班";
                return str;
            }
            if (teamName.equals("四年级(6)班")) {
                String str = "四年级六班";
                return str;
            }
            if (teamName.equals("四年级(7)班")) {
                String str = "四年级七班";
                return str;
            }
            if (teamName.equals("四年级(8)班")) {
                String str = "四年级八班";
                return str;
            }
            if (teamName.equals("四年级(9)班")) {
                String str = "四年级九班";
                return str;
            }
            if (teamName.equals("四年级(10)班")) {
                String str = "四年级十班";
                return str;
            }
            if (teamName.equals("四年级(11)班")) {
                String str = "四年级十一班";
                return str;
            }if (teamName.equals("五年级(1)班")) {
                String str = "五年级一班";
                return str;
            }
            if (teamName.equals("五年级(2)班")) {
                String str = "五年级二班";
                return str;
            }
            if (teamName.equals("五年级(3)班")) {
                String str = "五年级三班";
                return str;
            }
            if (teamName.equals("五年级(4)班")) {
                String str = "五年级四班";
                return str;
            }
            if (teamName.equals("五年级(5)班")) {
                String str = "五年级五班";
                return str;
            }
            if (teamName.equals("五年级(6)班")) {
                String str = "五年级六班";
                return str;
            }
            if (teamName.equals("五年级(7)班")) {
                String str = "五年级七班";
                return str;
            }
            if (teamName.equals("五年级(8)班")) {
                String str = "五年级八班";
                return str;
            }
            if (teamName.equals("五年级(9)班")) {
                String str = "五年级九班";
                return str;
            }
            if (teamName.equals("五年级(10)班")) {
                String str = "五年级十班";
                return str;
            }
            if (teamName.equals("五年级(11)班")) {
                String str = "五年级十一班";
                return str;
            }if (teamName.equals("六年级(1)班")) {
                String str = "六年级一班";
                return str;
            }
            if (teamName.equals("六年级(2)班")) {
                String str = "六年级二班";
                return str;
            }
            if (teamName.equals("六年级(3)班")) {
                String str = "六年级三班";
                return str;
            }
            if (teamName.equals("六年级(4)班")) {
                String str = "六年级四班";
                return str;
            }
            if (teamName.equals("六年级(5)班")) {
                String str = "六年级五班";
                return str;
            }
            if (teamName.equals("六年级(6)班")) {
                String str = "六年级六班";
                return str;
            }
            if (teamName.equals("六年级(7)班")) {
                String str = "六年级七班";
                return str;
            }
            if (teamName.equals("六年级(8)班")) {
                String str = "六年级八班";
                return str;
            }
            if (teamName.equals("六年级(9)班")) {
                String str = "六年级九班";
                return str;
            }
            if (teamName.equals("六年级(10)班")) {
                String str = "六年级十班";
                return str;
            }
            if (teamName.equals("六年级(11)班")) {
                String str = "六年级十一班";
                return str;
            }
        }
        return null;
    }
    private String banjimingchengzhuanhuan(String gradeName,String teamName){
        if(gradeName==null && gradeName.equals("") && teamName==null && teamName.equals("")){
            return null;
        }else {
            if (teamName.equals("1")) {
                String str = gradeName + "一班";
                return str;
            }
            if (teamName.equals("2")) {
                String str = gradeName + "二班";
                return str;
            }
            if (teamName.equals("3")) {
                String str = gradeName + "三班";
                return str;
            }
            if (teamName.equals("4")) {
                String str = gradeName + "四班";
                return str;
            }
            if (teamName.equals("5")) {
                String str = gradeName + "五班";
                return str;
            }
            if (teamName.equals("6")) {
                String str = gradeName + "六班";
                return str;
            }
            if (teamName.equals("7")) {
                String str = gradeName + "七班";
                return str;
            }
            if (teamName.equals("8")) {
                String str = gradeName + "八班";
                return str;
            }
            if (teamName.equals("9")) {
                String str = gradeName + "九班";
                return str;
            }
            if (teamName.equals("10")) {
                String str = gradeName + "十班";
                return str;
            }
        }
        return null;
    }


    private boolean checkStudentIsRepeat(Integer teamId, String studentname, String guardianPhone) {
        if (guardianPhone == null || "".equals(guardianPhone)) {
            return true;
        }
        TeamStudentCondition condition = new TeamStudentCondition();
        condition.setTeamId(teamId);
        condition.setName(studentname);
        List<TeamStudent> result = teamStudentService.findTeamStudentByCondition(condition, null, null);
        for (TeamStudent teamStudent : result) {
            List<ParentVo> parent = parentService.findParentsByStudentUserId(teamStudent.getUserId());
            for (Parent parentvo : parent) {
                if (parentvo.getMobile().equals(guardianPhone)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Integer checkNumIsRepeat(Integer teamId, String num) {
        Integer number = teamStudentService.findMaxTeamNumberByTeamId(teamId);
        number = number + 1;
        if (num == null || "".equals(num)) {
            return number;
        }
        TeamStudentCondition condition = new TeamStudentCondition();
        condition.setTeamId(teamId);
        condition.setNumber(Integer.parseInt(num));
        condition.setIsDelete(false);
        List<TeamStudent> result = teamStudentService.findTeamStudentByCondition(condition, null, null);
        if (result.size() > 0) {
            return null;
        }
        return Integer.parseInt(num);
    }

    @Override
    public Student addStudentByUserTeamSchoolIdAndName(Integer schoolId, Integer userId, Integer teamId, String name,String num,String kh) {
        Student student = new Student();
        student.setSchoolId(schoolId);
        student.setUserId(userId);
        User user = userService.findUserById(userId);
        student.setUserName(user.getUserName());
        student.setEmpCode(num);
        student.setName(name);
        student.setSex("0");
        student.setTeamId(teamId);
        Team team = teamService.findTeamById(teamId);
        student.setTeamName(team.getName());
        student.setAlias(name);
        Date now = new Date();
        student.setCreateDate(now);
        student.setDelete(false);
        student.setModifyDate(now);
        student.setEmpCard(kh);
        studentDao.create(student);
        return student;
    }

    ;

    public platform.education.generalcode.service.GradeService getJcGradeService() {
        return jcGradeService;
    }

    public void setJcGradeService(platform.education.generalcode.service.GradeService jcGradeService) {
        this.jcGradeService = jcGradeService;
    }

    @Override
    public void removeAllStudentInfoById(Integer studentId, String studentType, String parentType) {
        if (studentId == null || studentType == null || "".equals(studentType)) {
            return;
        }
        Student student = studentDao.findById(studentId);
        if (student != null) {
            Date now = new Date();
            //删除学生记录
            student.setIsDelete(true);
            student.setModifyDate(now);
            studentDao.update(student);

            Integer userid = student.getUserId();
            Integer schoolid = student.getSchoolId();
            //删除schoolUser记录
            SchoolUser schoolUser = schoolUserService.findSchoolUserListByUserIdAndType(userid, studentType);
            if (schoolUser != null) {
                schoolUser.setIsDeleted(true);
                schoolUser.setModifyDate(now);
                schoolUserService.modify(schoolUser);
            }
            //删除用户信息
            userService.removeAllById(userid, true);

            //删除班级学生
            TeamStudentCondition condition = new TeamStudentCondition();
            condition.setUserId(userid);
            List<TeamStudent> teamStudnetList = teamStudentService.findTeamStudentByCondition(condition, null, null);
            for (TeamStudent teamStudent : teamStudnetList) {
                teamStudent.setIsDelete(true);
                teamStudent.setModifyDate(now);
                teamStudentService.modify(teamStudent);
            }

            //删除学生学籍表
            StudentArchive studentArchive = studentArchiveDao.findByStudentId(studentId);
            if (studentArchive != null) {
                studentArchiveDao.update(studentArchive);
            }

            //删除学生变更记录
            StudentAlterationCondition condtion = new StudentAlterationCondition();
            condtion.setStudentId(studentId);
            List<StudentAlteration> list = studentAlterationService.findStudentAlterationByCondition(condtion);
            for (StudentAlteration studentAlteration : list) {
                studentAlteration.setIsDelete(true);
                studentAlteration.setModifyDate(now);
                studentAlterationService.modify(studentAlteration);
            }

            //删除班级用户
            TeamUserCondition teamUserCondition = new TeamUserCondition();
            teamUserCondition.setUserId(userid);
            List<TeamUser> teamUserList = teamUserService.findTeamUserByCondition(teamUserCondition);
            for (TeamUser teamUser : teamUserList) {
                teamUser.setModifyDate(now);
                teamUser.setIsDeleted(true);
                teamUserService.modify(teamUser);
            }

            //删除家长与子女关系
            List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByStudentUserId(userid);
            for (ParentStudent parentStudent : parentStudentList) {
                Integer parentUserId = parentStudent.getParentUserId();
                List<ParentStudent> parentUserList = parentStudentService.findParentStudentByParentUserId(parentUserId);
                if (parentUserList.size() - 1 == 0) {
                    parentService.removeAllByUserId(parentUserId, schoolid, parentType);
                }

                parentStudent.setIsDelete(true);
                parentStudent.setModifyDate(now);
                parentStudentService.modifyNoRead(parentStudent, 0);
            }
        }
    }

    @Override
    public List<StudentVo> findStudentVoByTeam(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId) {
        return this.studentDao.findStudentVoByTeam(schoolId, schoolYear, gradeId, teamId);
    }

    @Override
    public List<Student> findStudentByNoSend(Student student) {
        return this.studentDao.findStudentByNoSend(student);
    }

    @Override
    public List<TeamStudentInfo> findBoNotSendSeewo() {
        return studentDao.findBoByIsSendSeewo();
    }

    @Override
    public List<Student> findCanSendSeewo() {
        return studentDao.findCanSendSeewo();
    }

    @Override
    public boolean updateAsSendSeewo(List<Student> students) {
        Integer[] ids = new Integer[students.size()];
        for (int i = 0; i < students.size(); i++) {
            ids[i] = students.get(i).getId();
        }
        return studentDao.updateAsSendSeewoByIds(ids) > 0;

    }

    @Override
    public boolean updateAsSendSeewoByIds(Integer[] ids) {
        return studentDao.updateAsSendSeewoByIds(ids) > 0;
    }

    @Override
    public boolean updateAsSendSeewoByTeamId(Integer teamId) {
        return studentDao.updateAsSendSeewoByTeamId(teamId) > 0;
    }

    @Override
    public List<Student> findStudentByNoSendCanteen(Student studentVO) {
        return studentDao.findStudentByNoSendCanteen(studentVO);
    }

    /**
     * 查询pj_student_archive 根据学生id
     *
     * @param stuId
     * @return
     */
    @Override
    public StudentArchive findStudentArchiveByStudentId(Integer stuId) {


        try {
            return studentArchiveDao.findByStudentId(stuId);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", stuId);
        }
        return null;
    }

    @Override
    public List<FamilyMember> findFamilyMemberByStudentId(Integer stuId) {
        try {
            return familyMemberDao.findByStudentId(stuId);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", stuId);
        }
        return null;
    }

    @Override
    public List<StudentInfo> findSendSeewoStu(String schoolYear) {
        return studentDao.findSendSeewoStu(schoolYear);
    }

    @Override
   public Map<String,Object> updateasdasdas(Student student){
        Map<String,Object> map=new HashMap<>();
        try {
            if(student.getId()==null || student.getTeamId()==null || student.getTeamName()==null
                ||student.getEmpCard()==null || student.getEmpCode()==null){
                map.put("message","参数为空");
                return map;
            }
            Integer student1=studentDao.findIddd(student.getId());
            if(student1==null){
                map.put("message","查无此人");
                return map;
            }
            String name=student.getTeamName();
            String sql="UPDATE pj_student set team_id="+student.getTeamId()+ ",team_name='"+name+"',emp_code='"+student.getEmpCode()+"',emp_card ='"+student.getEmpCard()+"'  WHERE id="+student.getId()+"  and is_delete=0";
            Integer a = basicSQLService.update(sql);
            if(a<0){
                map.put("message","修改失败");
                map.put("code",a);
                return map;
            }
            map.put("a",a);
            String sql2="UPDATE pj_team_student SET team_id="+student.getTeamId()+" WHERE student_id="+student.getId() +" and is_delete=0";
            Integer s =  basicSQLService.update(sql2);;
            map.put("s",s);
        }catch (Exception e){
        return (Map<String, Object>) map.put("错误",e);
        }
        map.put("message","修改成功");
        return map;
    }

    @Override
    public List<Student> findByStudentShitang() {
        return studentDao.findByStudentShitang();
    }
}
