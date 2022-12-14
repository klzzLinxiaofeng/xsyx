package platform.education.generalTeachingAffair.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import platform.education.generalTeachingAffair.contants.StudentArchiveContants;
import platform.education.generalTeachingAffair.contants.SysContants;
import platform.education.generalTeachingAffair.contants.TeamTeacherContants;
import platform.education.generalTeachingAffair.dao.DepartmentDao;
import platform.education.generalTeachingAffair.dao.GradeDao;
import platform.education.generalTeachingAffair.dao.SchoolTermCurrentDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.DataImportCheck;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.contants.*;
import platform.education.user.dao.*;
import platform.education.user.model.*;
import platform.education.user.service.*;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.education.user.vo.UserBindingCondition;
import platform.education.user.vo.UserResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TeacherServiceImpl implements TeacherService {

    private Logger log = LoggerFactory.getLogger(getClass());

//    @Autowired
//    @Qualifier("stringRedisTemplate")
//    private StringRedisTemplate stringRedisTemplate;


    private StudentService studentService;
    //??????
    private PersonService personService;
    //??????
    private UserService userService;
    //????????????
    private TeamTeacherService teamTeacherService;
    //??????
    private UserRoleService userRoleService;
    //??????--??????
    protected ParentStudentService parentStudentService;
    //??????
    protected ParentService parentService;
    //????????????
    private TeamStudentService teamStudentService;
    //??????
    protected DepartmentService departmentService;
    //???????????????
    protected DepartmentTeacherService departmentTeacherService;
    //????????????
    private SchoolUserService schoolUserService;
    //???????????????
    protected UserBindingService userBindingService;
    //??????
    private RoleService roleService;
    //???????????????
    private GroupUserService groupUserService;
    // ??????????????? ?????????2015-11-19
    protected ProfileService profileService;
    //????????????  ?????????2015-11-19
    protected SubjectTeacherService subjectTeacherService;
    //??????
    protected TeamService teamService;
    //??????
    protected ParentProxyService parentProxyService;

    private GroupService groupService;

    private TeacherDao teacherDao;

    private UserRegionDao userRegionDao;

    private UserRoleDao userRoleDao;

    private UserBindingDao userBindingDao;

    private UserDao userDao;

    private AccountDao accountDao;

    private GroupDao groupDao;

    private GradeDao gradeDao;

    private SchoolTermCurrentDao schoolTermCurrentDao;

    private GroupUserDao groupUserDao;

    private ProfileDao profileDao;

    private AppUserDao appUserDao;

    private JobControlService jobControlService;

    private TeacherSortService teacherSortService;

    private TeacherAssistService teacherAssistService;

    private SyllabusService syllabusService;

    private TeamUserService teamUserService;

    private DepartmentDao departmentDao;

    @Autowired
    private BasicSQLService basicSQLService;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setTeamUserService(TeamUserService teamUserService) {
        this.teamUserService = teamUserService;
    }

    public void setSyllabusService(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    public void setJobControlService(JobControlService jobControlService) {
        this.jobControlService = jobControlService;
    }

    public void setTeacherAssistService(TeacherAssistService teacherAssistService) {
        this.teacherAssistService = teacherAssistService;
    }

    public void setGradeDao(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
    }

    public void setSchoolTermCurrentDao(SchoolTermCurrentDao schoolTermCurrentDao) {
        this.schoolTermCurrentDao = schoolTermCurrentDao;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setUserRegionDao(UserRegionDao userRegionDao) {
        this.userRegionDao = userRegionDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public void setUserBindingDao(UserBindingDao userBindingDao) {
        this.userBindingDao = userBindingDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setGroupUserDao(GroupUserDao groupUserDao) {
        this.groupUserDao = groupUserDao;
    }

    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public void setAppUserDao(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public TeamTeacherService getTeamTeacherService() {
        return teamTeacherService;
    }

    public void setTeamTeacherService(TeamTeacherService teamTeacherService) {
        this.teamTeacherService = teamTeacherService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public ParentStudentService getParentStudentService() {
        return parentStudentService;
    }

    public void setParentStudentService(ParentStudentService parentStudentService) {
        this.parentStudentService = parentStudentService;
    }

    public ParentService getParentService() {
        return parentService;
    }

    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    public TeamStudentService getTeamStudentService() {
        return teamStudentService;
    }

    public void setTeamStudentService(TeamStudentService teamStudentService) {
        this.teamStudentService = teamStudentService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public DepartmentTeacherService getDepartmentTeacherService() {
        return departmentTeacherService;
    }

    public void setDepartmentTeacherService(
            DepartmentTeacherService departmentTeacherService) {
        this.departmentTeacherService = departmentTeacherService;
    }

    public SchoolUserService getSchoolUserService() {
        return schoolUserService;
    }

    public void setSchoolUserService(SchoolUserService schoolUserService) {
        this.schoolUserService = schoolUserService;
    }

    public UserBindingService getUserBindingService() {
        return userBindingService;
    }

    public void setUserBindingService(UserBindingService userBindingService) {
        this.userBindingService = userBindingService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public GroupUserService getGroupUserService() {
        return groupUserService;
    }

    public void setGroupUserService(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public SubjectTeacherService getSubjectTeacherService() {
        return subjectTeacherService;
    }

    public void setSubjectTeacherService(SubjectTeacherService subjectTeacherService) {
        this.subjectTeacherService = subjectTeacherService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public TeamService getTeamService() {
        return teamService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public ParentProxyService getParentProxyService() {
        return parentProxyService;
    }

    public void setParentProxyService(ParentProxyService parentProxyService) {
        this.parentProxyService = parentProxyService;
    }

    @Override
    public Teacher findTeacherById(Integer id) {
        try {
            return teacherDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("??????????????????ID??? {} ", id);
        }
        return null;
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        try {
            return teacherDao.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("??????????????????ID??? {} ", id);
        }
        return null;
    }

    @Override
    public Teacher findTeacherByName(String name) {
        try {
            return teacherDao.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("??????????????????name??? {} ", name);
        }
        return null;
    }

    @Override
    public Teacher add(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        Date date = new Date();
        teacher.setCreateDate(date);
        teacher.setModifyDate(date);
        return teacherDao.create(teacher);
    }

    @Override
    public Teacher modify(Teacher teacher) {
        teacher.setModifyDate(new Date());
        return teacherDao.update(teacher);
    }

    @Override
    public void remove(Teacher teacher) {
        try {
            teacherDao.delete(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("????????????????????????ID??? {} ");
        }

    }

    @Override
    public List<Teacher> getTeacherOfSchool(Integer schoolId) {
        return teacherDao.findTeacherOfSchool(schoolId);
    }

    @Override
    public List<Teacher> findActiveTeacherOfSchool(Integer schoolId) {
        return teacherDao.findActiveTeacherOfSchool(schoolId);
    }

    @Override
    public List<Teacher> getMasterOfTeam(Integer teamId) {
        return teacherDao.findMasterOfTeam(teamId);
    }

    @Override
    public List<Teacher> getSubjectTeacherOfTeam(Integer teamId) {
        return teacherDao.findSubjectTeacherOfTeam(teamId);
    }

    @Override
    public List<Teacher> findTeacherByCondition(TeacherCondition teacherCondition, Page page, Order order) {
        return teacherDao.findTeacherByCondition(teacherCondition, page, order);
    }

    @Override
    public List<TeacherVo> findTeacherVoByCondition(TeacherCondition condition, Page page, Order order) {
        return teacherDao.findTeacherVoByCondtion(condition, page, order);
    }

    @Override
    public List<TeacherVo> findTeacherVoByGroupCondtion(TeacherCondition condition, Page page, Order order) {
        return teacherDao.findTeacherVoByGroupCondtion(condition, page, order);
    }

    @Override
    public Teacher findOfUser(Integer schoolId, Integer userId) {
        return teacherDao.findOfUser(schoolId, userId);
    }

    @Override
    public Integer findUnqiueTeacherId(Integer userId, Integer schoolId) {
        return this.teacherDao.findUnqiueTeacherId(userId, schoolId);
    }

    @Override
    public List<Teacher> findTeacherByNameAndSchool(String name, Integer schoolId) {
        return this.teacherDao.findTeacherByNameAndSchool(name, schoolId);
    }

    @Override
    public List<Teacher> findTeacherByLikeNameAndSchool(String name, Integer schoolId, Page page, Order order) {
        return this.teacherDao.findTeacherByLikeNameAndSchool(name, schoolId, page, order);
    }

    @Override
    public List<Teacher> findTeacherByNoSend(Teacher teacher) {
        return this.teacherDao.findTeacherByNoSend(teacher);
    }

    @Override
    public List<Teacher> findTeacherListByTeamId(Integer teamId) {
        return this.teacherDao.findTeacherListByTeamId(teamId);
    }

    @Override
    public List<Teacher> findTeacherListByUserName(String userName) {
        return this.teacherDao.findTeacherListByUserName(userName);
    }

    @Override
    public List<TeacherVo> findTeacherVoByConditionMore(TeacherCondition condition, Page page, Order order) {
        return teacherDao.findTeacherVoByConditionMore(condition, page, order);
    }

    /**
     * @author Mr.Chan
     */
    @Override
    public Teacher addTeacher(Teacher teacher, User user, Role role, Integer groupId, Integer appId, String regionCode, String level, Integer createUserId) {
        Teacher tmp = null;
        try {
            if (teacher == null) {
                throw new IllegalArgumentException("Teacher is not null");
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
                UserBinding ub = this.userBindingDao.findByBindingName(customUsername);
                if (ub != null) {
                    Integer userId = ub.getUserId();
                    User persistentUser = this.userDao.findById(userId);
                    isNew = false;
                    user = persistentUser;
                } else {
                    isBinding = true;
                }
            }
            if (isNew) {
                Account account = this.accountDao.findAccountByRandom(appId);
                if (account != null) {
                    String username = account.getUserName();
                    User flag = this.userDao.findByUsername(username);
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
                this.accountDao.update(account);

                user.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(user.getPassword()));
                user.setCreateDate(date);
                user = this.userDao.create(user);
                Group group = this.groupDao.findById(groupId);
                if (group == null) {
                    throw new RuntimeException("????????????????????????group????????????");
                }
                // ????????????????????????
                GroupUser groupUser = new GroupUser();
                groupUser.setState(user.getState());
                groupUser.setUserId(user.getId());
                groupUser.setGroupId(group.getId());
                groupUser.setCreateDate(date);
                groupUser = this.groupUserDao.create(groupUser);
                if (groupUser == null) {
                    throw new RuntimeException("???????????????????????????????????????");
                }
                // ???????????????app??????
                AppUser appUser = new AppUser();
                appUser.setAppId(appId);
                appUser.setState(user.getState());
                appUser.setUserId(user.getId());
                appUser.setCreateDate(date);
                appUser = appUserDao.create(appUser);
                if (appUser == null) {
                    throw new RuntimeException("???????????????App?????????????????????");
                }

                // ????????????????????????
                UserBinding defUserBinding = new UserBinding();
                defUserBinding.setBindingName(user.getUserName());
                defUserBinding.setBindingType(UserBindingContants.TYPE_ORIGINAL);
                defUserBinding.setUserId(user.getId());
                defUserBinding.setEnabled(true);
                defUserBinding.setCreateDate(date);
                defUserBinding = this.userBindingDao.create(defUserBinding);

                if (isBinding) {
                    UserBinding bindUserInput = new UserBinding();
                    bindUserInput.setBindingName(customUsername);
                    bindUserInput.setBindingType(UserBindingContants.TYPE_CUSTOM);
                    bindUserInput.setUserId(user.getId());
                    bindUserInput.setEnabled(true);
                    bindUserInput.setCreateDate(date);
                    this.userBindingDao.create(bindUserInput);
                }
            }

            // ????????????
            UserRegion userRegion = new UserRegion();
            userRegion.setAppId(appId);
            userRegion.setUserId(user.getId());
            userRegion.setRegionCode(regionCode);
            userRegion.setLevel(level);
            userRegion.setCreateUserId(createUserId);
            userRegion.setCreateDate(date);
            this.userRegionDao.create(userRegion);

            // ????????????
            teacher.setUserId(user.getId());
            teacher.setUserName(user.getUserName());
            teacher.setCreateDate(date);
            tmp = teacherDao.create(teacher);

            // ????????????
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setPriority(1);
            userRole.setCreateDate(date);
            UserRole ur = this.userRoleDao.create(userRole);
            if (ur == null) {
                throw new RuntimeException("??????????????????");
            }
        } catch (Exception e) {
            throw new RuntimeException("????????????????????????");
        }
        return tmp;
    }

    /**
     * @author Mr.Chan
     */
    @Override
    public Teacher addTeacher(Teacher teacher, User user, Role role, Profile profile, Integer groupId, Integer appId, String regionCode, String level, Integer createUserId) {
        Teacher tmp = null;
        try {
            if (teacher == null) {
                throw new IllegalArgumentException("Teacher is not null");
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
                UserBinding ub = this.userBindingDao.findByBindingName(customUsername);
                if (ub != null) {
                    Integer userId = ub.getUserId();
                    User persistentUser = this.userDao.findById(userId);
                    isNew = false;
                    user = persistentUser;
                } else {
                    isBinding = true;
                }
            }
            if (isNew) {
                Account account = this.accountDao.findAccountByRandom(appId);
                if (account != null) {
                    String username = account.getUserName();
                    User flag = this.userDao.findByUsername(username);
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
                this.accountDao.update(account);

                user.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(user.getPassword()));
                user.setCreateDate(date);
                user = this.userDao.create(user);
                Group group = this.groupDao.findById(groupId);
                if (group == null) {
                    throw new RuntimeException("????????????????????????group????????????");
                }
                // ????????????????????????
                GroupUser groupUser = new GroupUser();
                groupUser.setState(user.getState());
                groupUser.setUserId(user.getId());
                groupUser.setGroupId(group.getId());
                groupUser.setCreateDate(date);
                groupUser = this.groupUserDao.create(groupUser);
                if (groupUser == null) {
                    throw new RuntimeException("???????????????????????????????????????");
                }
                // ???????????????app??????
                AppUser appUser = new AppUser();
                appUser.setAppId(appId);
                appUser.setState(user.getState());
                appUser.setUserId(user.getId());
                appUser.setCreateDate(date);
                appUser = appUserDao.create(appUser);
                if (appUser == null) {
                    throw new RuntimeException("???????????????App?????????????????????");
                }

                // ????????????????????????
                UserBinding defUserBinding = new UserBinding();
                defUserBinding.setBindingName(user.getUserName());
                defUserBinding.setBindingType(UserBindingContants.TYPE_ORIGINAL);
                defUserBinding.setUserId(user.getId());
                defUserBinding.setEnabled(true);
                defUserBinding.setCreateDate(date);
                defUserBinding = this.userBindingDao.create(defUserBinding);

                if (isBinding) {
                    UserBinding bindUserInput = new UserBinding();
                    bindUserInput.setBindingName(customUsername);
                    bindUserInput.setBindingType(UserBindingContants.TYPE_CUSTOM);
                    bindUserInput.setUserId(user.getId());
                    bindUserInput.setEnabled(true);
                    bindUserInput.setCreateDate(date);
                    this.userBindingDao.create(bindUserInput);
                }
            }

            // ????????????
            UserRegion userRegion = new UserRegion();
            userRegion.setAppId(appId);
            userRegion.setUserId(user.getId());
            userRegion.setRegionCode(regionCode);
            userRegion.setLevel(level);
            userRegion.setCreateUserId(createUserId);
            userRegion.setCreateDate(date);
            this.userRegionDao.create(userRegion);

            // ????????????
            teacher.setUserId(user.getId());
            teacher.setUserName(user.getUserName());
            teacher.setCreateDate(date);
            tmp = teacherDao.create(teacher);

            // ????????????
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setPriority(1);
            userRole.setCreateDate(date);
            UserRole ur = this.userRoleDao.create(userRole);
            if (ur == null) {
                throw new RuntimeException("??????????????????");
            }

            // ??????????????????
            profile.setId(null);
            profile.setUserId(user.getId());
            profile.setUserName(user.getUserName());
            profile.setIsDeleted(false);
            profile.setCreateDate(date);
            profile.setModifyDate(date);
            Profile pf = this.profileDao.create(profile);
            if (pf == null) {
                throw new IllegalArgumentException("????????????????????????");
            }
        } catch (Exception e) {
            throw new RuntimeException("????????????????????????");
        }
        return tmp;
    }

    @Override
    public List<Teacher> findTeacherBySchoolRegion(SchoolCondition condition, Group group, Page page, Order order) {
        return this.teacherDao.findTeacherBySchoolRegion(condition, group, page, order);
    }

    @Override
    public String findTeacherNumBySchoolId(Integer schoolId) {
        Teacher teacher = this.teacherDao.findTeacherNumBySchoolId(schoolId);
        return teacher == null ? "0" : teacher.getTeacherNum();
    }

    @Override
    public String findTeacherNumAndStudentNumBySchoolId(Integer schoolId) {
        Teacher teacher = this.teacherDao.findTeacherNumBySchoolId(schoolId);
        String teacherNum = teacher == null ? "0" : teacher.getTeacherNum();
        String studentNum = this.studentService.findStudentNumBySchoolId(schoolId);
        Integer countNum = Integer.parseInt(teacherNum) + Integer.parseInt(studentNum);
        return String.valueOf(countNum);
    }

    @Override
    public List<Teacher> findTeacherListByListId(List<Integer> idList, String type, Integer schoolId) {

        List<Teacher> teacherList = null;
        int size = idList.size();
        String[] array = new String[size];
        for (int i = 0; i < idList.size(); i++) {
            array[i] = idList.get(i).toString();
        }
        if (type == "1" || "1".equals(type)) {
            teacherList = teacherDao.findTeacherOfTeamIdList(array, schoolId);
        } else if (type == "2" || "2".equals(type)) {
            teacherList = teacherDao.findTeacherOfGradeIdList(array, schoolId);
        } else {
            teacherList = new ArrayList<Teacher>();
        }
        return teacherList;

    }

    public List<Teacher> findTeacherListBySchoolId(Integer schoolId) {
        return teacherDao.findTeacherListBySchoolId(schoolId);
    }

    @Override
    public List<Teacher> findTeacherListBySchoolId(Integer schoolId, Page page) {
        return teacherDao.findTeacherListBySchoolId(schoolId, page);
    }

    @Override
    public TeacherVo findTeamTeacherNumberOfSexData(Integer schoolId) {
        return this.teacherDao.findTeamTeacherNumberOfSexData(schoolId);
    }

    @Override
    public List<TeacherVo> findTeacherNumberOfStationData(Integer schoolId, String tableCode) {
        return this.teacherDao.findTeacherNumberOfStationData(schoolId, tableCode);
    }

    @Override
    public List<TeacherVo> findTeacherNumberOfQualificationData(Integer schoolId, String tableCode) {
        return this.teacherDao.findTeacherNumberOfQualificationData(schoolId, tableCode);
    }

    @Override
    public List<TeacherVo> findTeacherNumberOfAgeData(Integer schoolId) {
        return this.teacherDao.findTeacherNumberOfAgeData(schoolId);
    }

    @Override
    public TeacherVo findTeacherNumberOfPoliticsData(Integer schoolId) {
        return this.teacherDao.findTeacherNumberOfPoliticsData(schoolId);
    }

    @Override
    public List<TeacherVo> findNoDepartmentTeacherVoByCondition(
            TeacherCondition tCondition, Page page, Order order) {
        return this.teacherDao.findNoDepartmentTeacherVoByCondition(tCondition, page, order);
    }

    @Override
    public List<Teacher> findTeacherListByTeamId(Integer teamId, Order order) {
        return this.teacherDao.findTeacherListByTeamId(teamId, order);
    }

    @Override
    public List<Teacher> findTeacherListByTeamId(Integer teamId, Page page, Order order) {
        return this.teacherDao.findTeacherListByTeamId(teamId, page, order);
    }

    /**
     * ??????????????????????????????
     * 2015-12-09
     */
    @Override
    public List<Teacher> getMastersOfTeam(Integer teamId) {
        Integer type = TeamTeacherContants.TYPE_MASTER;
        return this.teacherDao.findMastersOfTeam(type, teamId);
    }

    /**
     * ???????????????????????????????????????
     * 2015-12-09
     */
    @Override
    public List<Teacher> getSubjectTeachersOfTeam(Integer teamId) {
        Integer type = TeamTeacherContants.TYPE_SUBJECT_TEACHER;
        return this.teacherDao.findSubjectTeachersOfTeam(type, teamId, null);
    }

    /**
     * ??????????????????????????????????????????????????????
     * 2015-12-09
     */
    @Override
    public List<Teacher> getSubjectTeachersOfTeam(Integer teamId,
                                                  String subjectCode) {
        Integer type = TeamTeacherContants.TYPE_SUBJECT_TEACHER;
        return this.teacherDao.findSubjectTeachersOfTeam(type, teamId, subjectCode);
    }

    @Override
    public TeacherVo findTeamTeacherNumberOfQualData(Integer school, String qualification) {
        return teacherDao.findTeamTeacherNumberOfData(school, null, null, qualification, null);
    }

    @Override
    public TeacherVo findTeamTeacherNumberOfPoliticalStatusData(Integer school, String politicalStatus) {
        return teacherDao.findTeamTeacherNumberOfData(school, politicalStatus, null, null, null);
    }

    @Override
    public TeacherVo findTeamTeacherNumberOfPoststaffingData(Integer school, String postStaffing) {
        return teacherDao.findTeamTeacherNumberOfData(school, null, postStaffing, null, null);
    }

    @Override
    public TeacherVo findTeamTeacherNumberOfExternalData(Integer school, Boolean isExternal) {
        return teacherDao.findTeamTeacherNumberOfData(school, null, null, null, isExternal);
    }

    /**
     * @param
     * @param schoolYear
     * @return
     * @function ???????????????????????????????????????
     * @date 2016-12-9
     * @author panfei
     */
    @Override
    public List<Teacher> findGradeOfTeacher(Integer gradeId, String schoolYear, Boolean isGetAll) {
        if (gradeId == null) {
            return new ArrayList<Teacher>();
        }
        Grade grade = gradeDao.findById(gradeId);
        if (schoolYear == null || "".equals(schoolYear)) {
            if (grade != null) {
                List<SchoolTermCurrent> stc = schoolTermCurrentDao.findCurrentSchoolYearById(grade.getSchoolId());
                if (stc != null && stc.size() > 0) {
                    schoolYear = stc.get(0).getSchoolYear();
                }
            }
        }
        if (schoolYear == null || "".equals(schoolYear)) {
            return new ArrayList<Teacher>();
        }
        return teacherDao.findGradeOfTeacher(gradeId, schoolYear, isGetAll, grade.getSchoolId());
    }

    @Override
    public TeacherDetailInfo findTeacherDetailInfoByUserId(Integer userId, Integer schoolId) {
        if (userId == null) {
            return new TeacherDetailInfo();
        }
        return teacherDao.findTeacherDetailInfoByUserId(userId, schoolId);
    }

    @Override
    public TeacherDetailInfoVo findTeacherDetailInfoById(Integer id) {
        TeacherDetailInfoVo teacherDetailInfo = null;
        try {
            teacherDetailInfo = new TeacherDetailInfoVo();
            Teacher teacher = teacherDao.findById(id);
            User user = this.userService.findUserById(teacher.getUserId());
            Person person = this.personService.findPersonById(teacher
                    .getPersionId());
            if (user != null) {
                teacherDetailInfo.setUserId(user.getId());
                teacherDetailInfo.setUserName(user.getUserName());
            }

            if (teacher != null) {
                teacherDetailInfo.setTeacherId(teacher.getId());
                teacherDetailInfo.setSchoolId(teacher.getSchoolId());
                teacherDetailInfo.setName(teacher.getName());
                teacherDetailInfo.setSex(teacher.getSex());
                teacherDetailInfo.setJobNumber(teacher.getEmpCode());
                teacherDetailInfo.setEnrollDate(teacher.getEnrollDate());
                teacherDetailInfo.setLeaveDate(teacher.getLeaveDate());
                teacherDetailInfo.setWorkBeginDate(teacher.getWorkBeginDate());
                teacherDetailInfo.setQualification(teacher.getQualification());
                teacherDetailInfo.setOccupationCode(teacher.getOccupationCode());
                teacherDetailInfo.setPosition(teacher.getPosition());
                teacherDetailInfo.setMobile(teacher.getMobile());
                teacherDetailInfo.setTelephone(teacher.getTelephone());
                teacherDetailInfo.setPostStaffing(teacher.getPostStaffing());
                teacherDetailInfo.setJobState(teacher.getJobState());
                teacherDetailInfo.setWorkBeginDate(teacher.getWorkBeginDate());
                teacherDetailInfo.setIsExternal(teacher.getIsExternal());  //????????????   1--?????????  0--????????????
                teacherDetailInfo.setAlias(teacher.getAlias()); // ??????
                teacherDetailInfo.setEmpCard(teacher.getEmpCard()); //2022.09.6????????????
            }

            if (person != null) {
                teacherDetailInfo.setEntityId(person.getEntityId());//??????ID
                teacherDetailInfo.setPhotoUuid(person.getPhotoUuid());    //??????id 2016-08-08??????
                teacherDetailInfo.setPersionId(person.getId());
                teacherDetailInfo.setEnglishName(person.getEnglishName());
                teacherDetailInfo.setCertificateType(person.getIdCardType());
                teacherDetailInfo.setCertificateNum(person.getIdCardNumber());
                teacherDetailInfo.setNationality(person.getNationality());
                teacherDetailInfo.setNation(person.getRace());
                teacherDetailInfo.setNativePlace(person.getNativePlace());
                teacherDetailInfo.setBirthPlace(person.getBirthPlace());
                teacherDetailInfo.setMarriage(person.getMarriage());
                teacherDetailInfo.setPolitical(person.getPoliticalStatus());
                teacherDetailInfo.setSpecialty(person.getSpecialSkill());
                teacherDetailInfo.setReligiousBelief(person.getReligion());
                teacherDetailInfo.setRegister(person.getResidenceType());
                teacherDetailInfo.setRegisterPlace(person.getResidenceAddress());
                teacherDetailInfo.setNowAddress(person.getAddress());
                teacherDetailInfo.setEmail(person.getEmail());
                teacherDetailInfo.setLiveAddress(person.getResideAddress());
                teacherDetailInfo.setRemark(person.getRemark());
                teacherDetailInfo.setBirthDate(person.getBirthday());
            }
        } catch (Exception e) {
            log.info("????????????????????????...");
            e.printStackTrace();
        }
        return teacherDetailInfo;
    }

    public void updateTeacherDetailInforById(Integer id) throws Exception {
        try {
            Teacher teacher = teacherDao.findById(id);
            User user = this.userService.findUserById(teacher.getUserId());
            Person person = this.personService.findPersonById(teacher.getPersionId());
            teacher.setIsDelete(true);
            user.setIsDeleted(true);
            person.setIsDelete(true);

            TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setTeacherId(id);
            List<TeamTeacher> teamTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
            if (!teamTeacherList.isEmpty()) {
                for (TeamTeacher teamTeacher : teamTeacherList) {
                    TeamTeacher teamTeacherTemp = teamTeacherService.findTeamTeacherById(teamTeacher.getId());
                    teamTeacherTemp.setDelete(true);
                    teamTeacherService.modify(teamTeacherTemp);
                }
            }
            personService.modify(person);
            userService.modify(user);
            teacherDao.update(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("??????????????????");
            throw e;
        }

    }

    @Override
    //??????????????????
    public void saveUserInfoList(List<UserDetailInfoVo> successMeaageVoList, String password, Integer appId) throws Exception {
        try {
            for (int i = 0; i < successMeaageVoList.size(); i++) {
                UserDetailInfoVo udv = successMeaageVoList.get(i);
                Person person = new Person();
                person.setName(udv.getName());
                person.setSex(udv.getSex());
                person.setCreateDate(new Date());
                person.setModifyDate(new Date());
                person.setIsDelete(false);
                Person p = personService.add(person);
                //??????????????????
                User user = new User();
                user.setIsDeleted(false);
                user.setCreateDate(new Date());
                user.setModifyDate(new Date());
                user.setPassword(SysContants.DEFAULT_PASSWORD);
                user.setPassword(password);
                user.setValidDate(new Date());
                user.setState(UserContants.STATE_NORMAL);
//				user.setUserName(UserAccountUtil.getUserAccount());
                //System.out.println("=====udv.getName()========"+udv.getName());
                User u = this.userService.add(user);
                //User u = this.userService.addUser(user,udv.getSchoolId(),GroupContants.TYPE_SCHOOL,SysContants.SYSTEM_APP_ID);
                UserResult ur = userService.addUser(user, udv.getSchoolId(), GroupContants.TYPE_SCHOOL, udv.getCellPhone(), UserBindingContants.TYPE_PHONE, appId);

                //??????????????????
                Student student = new Student();
                student.setSchoolId(udv.getSchoolId());
                student.setPersonId(p.getId());
                student.setUserId(ur.getUser().getId() == null ? 0 : ur.getUser().getId());
                student.setUserName(ur.getUser().getUserName());
                student.setName(udv.getName());
                student.setSex(udv.getSex());
                student.setStudentNumber(udv.getStudentNum());
                student.setCreateDate(new Date());
                student.setIsDelete(false);
                studentService.add(student);

                //????????????
                UserRole userRole = new UserRole();
                Role role = new Role();
                role.setId(udv.getRoleId());
                role.setCreateDate(new Date());
                userRole.setUser(ur.getUser());
                userRole.setRole(role);
                UserRole ur1 = this.userRoleService.add(userRole);
                if (ur1 == null) {
                    throw new Exception("??????????????????");
                }
            }

        } catch (Exception e) {
            log.info("??????????????????????????????....");
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    //??????????????????
    public void saveImportUserInfo(UserDetailInfoVo userDetailInfoVo, String password, Integer appId) throws Exception {

        try {
            //UserDetailInfoVo udv = successMeaageVoList.get(i);
            Person person = new Person();
            person.setName(userDetailInfoVo.getName());
            person.setSex(userDetailInfoVo.getSex());
            person.setCreateDate(new Date());
            person.setModifyDate(new Date());
            person.setIsDelete(false);
            Person p = personService.add(person);

            User user = new User();
            user.setIsDeleted(false);
            user.setCreateDate(new Date());
            user.setModifyDate(new Date());
            user.setPassword(password);
            //user.setValidDate(new Date());
            user.setState(UserContants.STATE_NORMAL);
            //System.out.println("=====udv.getName()========"+userDetailInfoVo.getName());
            //User u = this.userService.addUser(user,userDetailInfoVo.getSchoolId(),GroupContants.TYPE_SCHOOL,SysContants.SYSTEM_APP_ID);

            UserResult ur = userService.addUser(user, userDetailInfoVo.getSchoolId(), GroupContants.TYPE_SCHOOL, userDetailInfoVo.getCellPhone(), UserBindingContants.TYPE_PHONE, appId);
            Student student = new Student();
            student.setSchoolId(userDetailInfoVo.getSchoolId());
            student.setPersonId(p.getId());
            student.setUserId(ur.getUser().getId() == null ? 0 : ur.getUser().getId());
            student.setUserName(ur.getUser().getUserName());
            student.setName(userDetailInfoVo.getName());
            student.setSex(userDetailInfoVo.getSex());
            student.setStudentNumber(userDetailInfoVo.getStudentNum());
            student.setCreateDate(new Date());
            student.setIsDelete(false);
            studentService.add(student);
            //????????????
            TeamStudent teamStudent = new TeamStudent();
            teamStudent.setGradeId(Integer.parseInt(userDetailInfoVo.getGradeId()));
            teamStudent.setTeamId(Integer.parseInt(userDetailInfoVo.getTeamId()));
//				teamStudent.setStudentId(s.getId());
            teamStudent.setName(userDetailInfoVo.getName());
            this.teamStudentService.add(teamStudent);
//
        } catch (Exception e) {
            log.info("??????????????????????????????....");
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public UserDetailInfo findUserDetailInfoById(String id) throws Exception {
        UserDetailInfo userDetailInfo = null;
        try {
            userDetailInfo = new UserDetailInfo();
            Student student = studentService.findStudentById(Integer.parseInt(id));
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
                //?????????????????????
                ParentStudentCondition psCondition = new ParentStudentCondition();
                psCondition.setStudentUserId(student.getUserId());
                psCondition.setIsDelete(false);
                List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(psCondition);
                List<Parent> parents = new ArrayList<Parent>();
                for (ParentStudent parentStudent : psList) {
                    Parent parent = this.parentService.findUniqueByUserId(parentStudent.getParentUserId());
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
            log.info("??????UserDetailInfo??????");
            //e.printStackTrace();
            throw e;
        }
        return userDetailInfo;
    }

    @Override
    public List<UserDetailInfo> findAllUserDetailInfo(Integer schoolId) throws Exception {
        List<UserDetailInfo> userDetailInfoList = new ArrayList<UserDetailInfo>();
        try {
            StudentCondition studentCondition = new StudentCondition();
            studentCondition.setSchoolId(schoolId);
            List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);

            for (Student s : studentList) {
                UserDetailInfo UserDetailInfo = findUserDetailInfoById(s.getId().toString());
                userDetailInfoList.add(UserDetailInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetailInfoList;
    }

    @Override
    public void updateUserDetailInforById(Integer id) throws Exception {
        try {
            Student student = this.studentService.findStudentById(id);
            User user = this.userService.findUserById(student.getUserId());
            Person person = this.personService.findPersonById(student.getPersonId());
            student.setIsDelete(true);
            user.setIsDeleted(true);
            person.setIsDelete(true);

            TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
            teamStudentCondition.setStudentId(id);
            List<TeamStudent> teamStudentList = this.teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
            if (!teamStudentList.isEmpty()) {
                for (TeamStudent teamStudent : teamStudentList) {
                    TeamStudent teamStudentTemp = this.teamStudentService.findTeamStudentById(teamStudent.getId());
                    teamStudentTemp.setIsDelete(true);
                    teamStudentService.modify(teamStudentTemp);
                }
            }

            personService.modify(person);
            userService.modify(user);
            studentService.modify(student);


        } catch (Exception e) {
            e.printStackTrace();
            log.info("??????????????????");
            throw e;
        }
    }


    @Override
    /**
     * ??????????????????????????????
     */
    public TeacherDetailInfo addTeacherInfo(TeacherDetailInfo teacherDetailInfo, Integer appId, String password, String userType) throws Exception {
        Teacher t = null;
        TeacherDetailInfo tdi = null;
        try {
            tdi = new TeacherDetailInfo();
            //??????????????????
            User user = new User();
            user.setIsDeleted(false);
            user.setCreateDate(new Date());
            user.setModifyDate(new Date());
            user.setPassword(password);
            user.setState(UserContants.STATE_NORMAL);

            UserResult ur = userService.addUser(user, teacherDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL, teacherDetailInfo.getMobile(), UserBindingContants.TYPE_PHONE, appId);
            if (UserResult.STATUS_USER_BINDING_NAME_EXIST.equals(ur.getStatusCode())) {//??????????????????
                tdi.setMessage("????????????????????????");
            } else if (UserResult.STATUS_ACCOUNT_DB_NOT_ENOUGH.equals(ur.getStatusCode())) {//???????????????????????????
                tdi.setMessage("????????????????????????");
            } else if (UserResult.STATUS_CUSTOM_USER_NAME_EXIST.equals(ur.getStatusCode())) {//?????????????????????
                tdi.setMessage("?????????????????????");
            } else if (UserResult.STATUS_SUCCESS.equals(ur.getStatusCode())) {//??????

                Person person = new Person();
                person.setName(teacherDetailInfo.getName());
                person.setEnglishName(teacherDetailInfo.getEnglishName());
                person.setSex(teacherDetailInfo.getSex());
                person.setIdCardNumber(teacherDetailInfo.getCertificateNum());
                person.setBirthday(teacherDetailInfo.getBirthDate());

                person.setIdCardType(teacherDetailInfo.getCertificateType());
                person.setNationality(teacherDetailInfo.getNationality());
                person.setNativePlace(teacherDetailInfo.getNativePlace());
                person.setBirthPlace(teacherDetailInfo.getBirthPlace());
                person.setRace(teacherDetailInfo.getNation());
                person.setPoliticalStatus(teacherDetailInfo.getPolitical());
                person.setReligion(teacherDetailInfo.getReligiousBelief());
                person.setMarriage(teacherDetailInfo.getMarriage());
                person.setAddress(teacherDetailInfo.getNowAddress());
                person.setResideAddress(teacherDetailInfo.getLiveAddress());
                person.setResidenceAddress(teacherDetailInfo.getRegisterPlace());
                person.setMobile(teacherDetailInfo.getMobile());
                person.setTelephone(teacherDetailInfo.getTelephone());
                person.setEmail(teacherDetailInfo.getEmail());
                person.setSpecialSkill(teacherDetailInfo.getSpecialty());
                person.setResidenceType(teacherDetailInfo.getRegister());
                person.setRemark(teacherDetailInfo.getRemark());
                person.setEntityId(teacherDetailInfo.getEntityId());
                person.setPhotoUuid(teacherDetailInfo.getPhotoUuid());    //??????id 2016-08-08??????
                person.setCreateDate(new Date());
                person.setModifyDate(new Date());
                person.setIsDelete(false);
                Person p = personService.add(person);

                SchoolUser schoolUser = new SchoolUser();
                schoolUser.setCreateDate(new Date());
                schoolUser.setModifyDate(new Date());
                schoolUser.setName(p.getName());
                schoolUser.setOwnerId(p.getId());
                schoolUser.setSchoolId(teacherDetailInfo.getSchoolId());
                schoolUser.setUserId(ur.getUser().getId());
//				schoolUser.setUserType(Integer.parseInt(SysContants.USER_TYPE_TEACHER));
                schoolUser.setUserType(userType);
                schoolUserService.add(schoolUser);

                Teacher teacher = new Teacher();
                teacher.setSchoolId(teacherDetailInfo.getSchoolId());
                teacher.setPersionId(p.getId());
                teacher.setUserId(ur.getUser().getId());
                teacher.setUserName(ur.getUser().getUserName());
                teacher.setName(p.getName());
                teacher.setSex(p.getSex());
                teacher.setJobNumber(teacherDetailInfo.getJobNumber());
                teacher.setEnrollDate(teacherDetailInfo.getEnrollDate());
                teacher.setLeaveDate(teacherDetailInfo.getLeaveDate());
                teacher.setWorkBeginDate(teacherDetailInfo.getWorkBeginDate());
                teacher.setQualification(teacherDetailInfo.getQualification());
                teacher.setOccupationCode(teacherDetailInfo.getOccupationCode());
                teacher.setPosition(teacherDetailInfo.getPosition());
                teacher.setMobile(teacherDetailInfo.getMobile());
                teacher.setTelephone(teacherDetailInfo.getTelephone());

                teacher.setPostStaffing(teacherDetailInfo.getPostStaffing());
                if (teacherDetailInfo != null && teacherDetailInfo.getJobState() != null && !"".equals(teacherDetailInfo.getJobState())) {
                    teacher.setJobState(TeacherCondition.DEFAULT_ZAIZHI);
                } else {
                    teacher.setJobState(teacherDetailInfo.getJobState());
                }

                teacher.setCreateDate(new Date());
                teacher.setModifyDate(new Date());
                teacher.setIsDelete(false);
                t = this.teacherDao.create(teacher);
                tdi.setTeacherId(t.getId());
                tdi.setName(p.getName());
                //????????????
                UserRole userRole = new UserRole();
                Role role = new Role();
                role.setId(Integer.parseInt(teacherDetailInfo.getRole()));
                role.setCreateDate(new Date());
                userRole.setUser(ur.getUser());
                userRole.setRole(role);
                UserRole ur1 = this.userRoleService.add(userRole);
                if (ur1 == null) {
                    throw new Exception("??????????????????");
                }
                //??????
                if (teacherDetailInfo.getDepartmentName() != null && !"".equals(teacherDetailInfo.getDepartmentName())) {
                    DepartmentCondition departmentCondition = new DepartmentCondition();
                    departmentCondition.setName(teacherDetailInfo.getDepartmentName());
                    departmentCondition.setSchoolId(teacherDetailInfo.getSchoolId());
                    departmentCondition.setIsDelete(false);
                    List<Department> deptList = departmentService.findDepartmentByCondition(departmentCondition, null, null);
                    if (!deptList.isEmpty()) {
                        Department department = deptList.get(0);
                        DepartmentTeacher departmentTeacher = new DepartmentTeacher();
                        departmentTeacher.setDepartmentId(department.getId());
                        departmentTeacher.setDepartmentName(department.getName());
                        departmentTeacher.setSchoolId(teacherDetailInfo.getSchoolId());
                        departmentTeacher.setTeacherId(t.getId());
                        departmentTeacher.setTeacherName(t.getName());
                        departmentTeacherService.add(departmentTeacher);
                    }
                }
            }

        } catch (Exception e) {
            log.info("????????????????????????");
            tdi.setMessage("??????????????????");
            //e.printStackTrace();
            throw e;
        }
        return tdi;
    }

    @Override
    public Teacher modifyTeacherInfo(TeacherDetailInfo teacherDetailInfo) {
        Teacher t = null;
        try {
            Teacher teacher = teacherDao.findById(teacherDetailInfo.getTeacherId());
            User user = userService.findUserById(teacherDetailInfo.getUserId());
            Person person = personService.findPersonById(teacherDetailInfo.getPersionId());

            person.setName(teacherDetailInfo.getName());
            person.setEnglishName(teacherDetailInfo.getEnglishName());
            person.setSex(teacherDetailInfo.getSex());
            person.setIdCardNumber(teacherDetailInfo.getCertificateNum());
            person.setBirthday(teacherDetailInfo.getBirthDate());
            person.setIdCardType(teacherDetailInfo.getCertificateType());
            person.setNationality(teacherDetailInfo.getNationality());
            person.setNativePlace(teacherDetailInfo.getNativePlace());
            person.setBirthPlace(teacherDetailInfo.getBirthPlace());
            person.setRace(teacherDetailInfo.getNation());
            person.setPoliticalStatus(teacherDetailInfo.getPolitical());
            person.setReligion(teacherDetailInfo.getReligiousBelief());
            person.setMarriage(teacherDetailInfo.getMarriage());

            person.setAddress(teacherDetailInfo.getNowAddress());
            person.setResideAddress(teacherDetailInfo.getLiveAddress());
            person.setResidenceAddress(teacherDetailInfo.getRegisterPlace());
            person.setMobile(teacherDetailInfo.getMobile());
            person.setTelephone(teacherDetailInfo.getTelephone());
            person.setEmail(teacherDetailInfo.getEmail());
            person.setSpecialSkill(teacherDetailInfo.getSpecialty());
            person.setResidenceType(teacherDetailInfo.getRegister());
            person.setRemark(teacherDetailInfo.getRemark());
            person.setEntityId(teacherDetailInfo.getEntityId());//??????ID
            person.setPhotoUuid(teacherDetailInfo.getPhotoUuid());    //??????id 2016-08-08??????
            person.setCreateDate(new Date());
            person.setModifyDate(new Date());
            person.setIsDelete(false);
            Person p = personService.modify(person);

            user.setModifyDate(new Date());
            User u = userService.modify(user);

            teacher.setUserName(u.getUserName());
            teacher.setName(p.getName());
            teacher.setSex(teacherDetailInfo.getSex());
            teacher.setJobNumber(teacherDetailInfo.getJobNumber());
            teacher.setEnrollDate(teacherDetailInfo.getEnrollDate());
            teacher.setLeaveDate(teacherDetailInfo.getLeaveDate());
            teacher.setWorkBeginDate(teacherDetailInfo.getWorkBeginDate());
            teacher.setQualification(teacherDetailInfo.getQualification());
            teacher.setOccupationCode(teacherDetailInfo.getOccupationCode());
            teacher.setPosition(teacherDetailInfo.getPosition());
            teacher.setMobile(teacherDetailInfo.getMobile());
            teacher.setTelephone(teacherDetailInfo.getTelephone());
            teacher.setPostStaffing(teacherDetailInfo.getPostStaffing());
            teacher.setJobState(teacherDetailInfo.getJobState());
            teacher.setModifyDate(new Date());
            t = this.teacherDao.update(teacher);
        } catch (Exception e) {
            log.info("????????????????????????");
            e.printStackTrace();
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return t;
    }

    @Override
    public TeacherDetailInfo addInfoForTeacher(
            TeacherDetailInfo teacherDetailInfo, TeacherAssist teacherAssist, String password, Integer appId, String userType)
            throws Exception {
        teacherDetailInfo = processAOfTeacher(teacherDetailInfo, password, appId, userType);
        if (teacherDetailInfo.getUserId() != null && teacherAssist != null) {
            teacherAssist.setSchoolId(teacherDetailInfo.getSchoolId());
            teacherAssist.setUserId(teacherDetailInfo.getUserId());
            teacherAssist.setIsDelete(false);
            teacherAssist.setCreateDate(new Date());
            teacherAssist.setModifyDate(new Date());
            this.teacherAssistService.add(teacherAssist);
        }
        return teacherDetailInfo;
    }

    /* ==========??????????????????A????????????============== */
    public TeacherDetailInfo processAOfTeacher(
            TeacherDetailInfo teacherDetailInfo, String password, Integer appId, String userType) throws Exception {
        Role role = new Role();
        // ??????????????????A??????????????????????????????????????????????????????
        Boolean flag = checkNameAndMobileIsOffer(teacherDetailInfo);
        if (flag) {
            // ?????????????????????????????? ??????????????????A??????????????????????????????????????????????????????
            Boolean flagOne = checkTeacherIsExist(teacherDetailInfo);
            if (flagOne) {
                // ??????????????????????????? ??????
                teacherDetailInfo.setMessage("??????????????????");
            } else {
                // ???????????????????????? ??????????????????A??????????????? ?????????????????????????????????????????????????????????????????????????????????
                User user = findUserByMobile(teacherDetailInfo.getMobile(), teacherDetailInfo.getSchoolId());
                if (user == null) {
                    // ????????????????????????????????????
                    // ??????B??????
                    teacherDetailInfo = processBOfTeacher(teacherDetailInfo, password, appId);

                    //2016-3-21  ???????????? ????????????????????????  ??????????????????  ??????????????????  ?????????????????????
                    if (teacherDetailInfo.getMessage() == null || "".equals(teacherDetailInfo.getMessage())) {
                        // ??????C??????
                        teacherDetailInfo = processCOfTeacher(teacherDetailInfo);
                        // ??????D??????
                        teacherDetailInfo = processDOfTeacher(teacherDetailInfo, userType);
                    }
                } else {
                    //???personId??????   2016-3-3??????
                    teacherDetailInfo.setPersionId(user.getPersonId());
                    teacherDetailInfo.setUserId(user.getId());
                    teacherDetailInfo.setUserName(user.getUserName());

                    // ????????????????????????????????????
                    // ??????????????????A??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    Boolean flagTwo = checkTeacherIsSame(user, teacherDetailInfo);
                    if (flagTwo) {
                        // ?????????????????????????????????????????????????????????????????????????????? ??????????????????B1???????????????
                        role = this.roleService.findRoleById(Integer.valueOf(teacherDetailInfo.getRole()));
                        if (role != null) {
                            addUserGroupForTeacher(user, role, teacherDetailInfo.getSchoolId());

                            //?????????B1??????????????????C D??????   2016-3-3??????
                            processCOfTeacher(teacherDetailInfo);
                            processDOfTeacher(teacherDetailInfo, userType);
                        }
                    } else {
                        // ????????????????????????????????????????????????????????????????????????????????? ??????
                        teacherDetailInfo.setMessage("?????????????????????????????????????????????");
                    }
                }
            }
        } else {
            // ??????????????? ??????
            teacherDetailInfo.setMessage("???????????????");
        }
        return teacherDetailInfo;
    }

    /**
     * ??????????????????A??????????????? ???????????????????????????????????????????????? 2015-11-18
     *
     * @param teacherDetailInfo
     * @return
     */
    public Boolean checkNameAndMobileIsOffer(TeacherDetailInfo teacherDetailInfo) {
        Boolean flag = false;
        if (teacherDetailInfo.getName() == null || "".equals(teacherDetailInfo.getName()) || teacherDetailInfo.getMobile() == null || "".equals(teacherDetailInfo.getMobile())) {
            // teacherDetailInfo.setMessage("???????????????");
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * ??????????????????A??????????????? ???????????????????????????????????????????????? 2015-11-17
     *
     * @param teacherDetailInfo
     * @return
     */
    public Boolean checkTeacherIsExist(TeacherDetailInfo teacherDetailInfo) {
        Boolean flag = false;
        TeacherCondition teacherCondition = new TeacherCondition();
        teacherCondition.setSchoolId(teacherDetailInfo.getSchoolId());
        teacherCondition.setIsDelete(false);
        teacherCondition.setName(teacherDetailInfo.getName());
        teacherCondition.setMobile(teacherDetailInfo.getMobile());
        List<Teacher> teacherList = this.teacherDao.findTeacherByCondition(
                teacherCondition, null, null);
        if (teacherList.size() > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * ??????????????????A??????????????? ?????????????????????????????????????????????????????????????????????????????????????????? 2015-11-17
     * 2018-06-04??????	????????????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param mobile
     * @return
     */
    public User findUserByMobile(String mobile, Integer schoolId) {
        User user = null;
        UserBindingCondition condition = new UserBindingCondition();
        condition.setBindingName(mobile);
        condition.setBindingType(UserBindingContants.TYPE_PHONE);
        condition.setIsDeleted(false);
        List<UserBinding> userBindingList = this.userBindingService.findUserBindingByCondition(condition, null, null);
        if (userBindingList.size() > 0) {
            user = this.userService.findUserById(userBindingList.get(0).getUserId());
            for (UserBinding binding : userBindingList) {
                Integer userId = binding.getUserId();
                SchoolUserCondition suCondition = new SchoolUserCondition();
                suCondition.setUserId(userId);
                suCondition.setSchoolId(schoolId);
                suCondition.setIsDeleted(false);
                List<SchoolUser> schoolUserList = schoolUserService.findSchoolUserByCondition(suCondition, null, null);
                if (schoolUserList != null && schoolUserList.size() > 0) {
                    user = this.userService.findUserById(userId);
                    break;
                }
            }
        }
        return user;
    }

    /**
     * ??????????????????A??????????????? ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? 2015-11-17
     *
     * @param user
     * @param teacherDetailInfo
     * @return
     */
    public Boolean checkTeacherIsSame(User user,
                                      TeacherDetailInfo teacherDetailInfo) {
        Boolean flag = true;
        if (user != null) {
            Teacher teacher = teacherDao.findOfUser(teacherDetailInfo.getSchoolId(), user.getId());
            if (teacher != null) {
                if (!teacher.getName().equals(teacherDetailInfo.getName()) || !teacher.getMobile().equals(teacherDetailInfo.getMobile())) {
                    flag = false;
                }
            }
//			person = this.personService.findPersonById(user.getPersonId());
//			if (person != null) {
//				if (person.getName().equals(teacherDetailInfo.getName())
//						&& person.getMobile().equals(
//								teacherDetailInfo.getMobile())) {
//					flag = true;
//				} else {
//					flag = false;
//				}
//
//			}
        }
        return flag;
    }

    /* ==========??????????????????A????????????============== */

    /* ==========??????????????????B????????????============== */
    public TeacherDetailInfo processBOfTeacher(
            TeacherDetailInfo teacherDetailInfo, String password, Integer appId) {
        // ??????????????????B??????????????? -->U2.1?????????????????????????????????????????????????????????????????????????????????????????????
        Boolean flag = checkIdCardNumerIsExist(teacherDetailInfo.getCertificateNum());
        if (flag) {
            // ????????????????????????????????????????????????B???????????????
            // -->U2.1??????????????????????????????????????????????????????????????????????????????????????????pj_person??????????????????????????????????????????
            Person person = checkPersonIsSame(
                    teacherDetailInfo.getCertificateNum(),
                    teacherDetailInfo.getName());
            if (person != null) {
                // ????????????????????????
                teacherDetailInfo.setPersionId(person.getId());
            } else {
                // ??????????????????
                teacherDetailInfo.setPersionId(null);
            }
        } else {
            // ?????????????????????????????????????????????B???????????????-->U2.1???????????????????????????????????????????????????????????????pj_person???
            teacherDetailInfo = addPersonForTeacher(teacherDetailInfo);
        }
        // ??????????????????B???????????????U1.1D-->A??????????????????????????????????????????????????????
        teacherDetailInfo = addUserForTeacher(teacherDetailInfo, password, appId);
        return teacherDetailInfo;
    }

    /**
     * ??????????????????B??????????????? -->U2.1??????????????????????????? ???????????????????????????????????????????????????????????? 2015-11-19
     *
     * @param idCardNumber
     * @return
     */
    public Boolean checkIdCardNumerIsExist(String idCardNumber) {
        Boolean flag = false;
        // ?????????????????????????????????
        if (idCardNumber == null || "".equals(idCardNumber)
                || idCardNumber == "") {
            flag = false;
        } else {
            // ???????????????????????????????????????????????????????????????
            PersonCondition condition = new PersonCondition();
            condition.setIsDelete(false);
            condition.setIdCardNumber(idCardNumber);
            List<Person> personList = this.personService.findPersonByCondition(
                    condition, null, null);
            if (personList.size() > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * ??????????????????B??????????????? -->U2.1???????????????????????????
     * ???????????????????????????????????????????????????????????????????????????pj_person???????????????????????????????????????
     * ?????????????????????????????????????????????????????????????????????????????????????????? ????????????????????????????????????????????????????????????
     *
     * @param idCardNumber
     * @param name
     * @return
     */
    public Person checkPersonIsSame(String idCardNumber, String name) {
        Person person = null;
        PersonCondition condition = new PersonCondition();
        condition.setIsDelete(false);
        condition.setIdCardNumber(idCardNumber);
        condition.setName(name);
        List<Person> personList = this.personService.findPersonByCondition(
                condition, null, null);
        if (personList.size() > 0) {
            person = personList.get(0);
        }
        return person;
    }

    /**
     * ??????????????????B???????????????-->U2.1??????????????????????????? ?????????????????????????????????pj_person 2015-11-17
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo addPersonForTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        Person person = new Person();
        person.setName(teacherDetailInfo.getName());
        person.setEnglishName(teacherDetailInfo.getEnglishName());
        person.setSex(teacherDetailInfo.getSex());
        person.setIdCardNumber(teacherDetailInfo.getCertificateNum());
        person.setBirthday(teacherDetailInfo.getBirthDate());

        person.setIdCardType(teacherDetailInfo.getCertificateType());
        person.setNationality(teacherDetailInfo.getNationality());
        person.setNativePlace(teacherDetailInfo.getNativePlace());
        person.setBirthPlace(teacherDetailInfo.getBirthPlace());
        person.setRace(teacherDetailInfo.getNation());
        person.setPoliticalStatus(teacherDetailInfo.getPolitical());
        person.setReligion(teacherDetailInfo.getReligiousBelief());
        person.setMarriage(teacherDetailInfo.getMarriage());
        person.setAddress(teacherDetailInfo.getNowAddress());
        person.setResideAddress(teacherDetailInfo.getLiveAddress());
        person.setResidenceAddress(teacherDetailInfo.getRegisterPlace());
        person.setMobile(teacherDetailInfo.getMobile());
        person.setTelephone(teacherDetailInfo.getTelephone());
        person.setEmail(teacherDetailInfo.getEmail());
        person.setSpecialSkill(teacherDetailInfo.getSpecialty());
        person.setResidenceType(teacherDetailInfo.getRegister());
        person.setRemark(teacherDetailInfo.getRemark());
        person.setEntityId(teacherDetailInfo.getEntityId());
        person.setPhotoUuid(teacherDetailInfo.getPhotoUuid());    //??????id 2016-08-08??????
        person.setCreateDate(new Date());
        person.setModifyDate(new Date());
        person.setIsDelete(false);
        Person p = personService.add(person);
        if (p != null) {
            teacherDetailInfo.setPersionId(p.getId());
        }
        return teacherDetailInfo;
    }

    /**
     * ??????????????????B???????????????U1.1D-->A ???????????????????????????????????????????????? 2015-11-17
     *
     * @param teacherDetailInfo
     * @return
     * @throws Exception
     */
    public TeacherDetailInfo addUserForTeacher(
            TeacherDetailInfo teacherDetailInfo, String password, Integer appId) {
        // TeacherDetailInfo tdi = new TeacherDetailInfo();
        // BeanUtils.copyProperties(tdi, teacherDetailInfo);
        // ??????????????????
        User user = new User();
        user.setIsDeleted(false);
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user.setPassword(password);
        user.setState(UserContants.STATE_NORMAL);
        user.setPersonId(teacherDetailInfo.getPersionId());

        try {
            UserResult ur = userService.addUser(user,
                    teacherDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL,
                    teacherDetailInfo.getMobile(),
                    UserBindingContants.TYPE_PHONE, appId);
            if (UserResult.STATUS_USER_BINDING_NAME_EXIST.equals(ur
                    .getStatusCode())) {// ??????????????????
                teacherDetailInfo.setMessage("????????????????????????");
            } else if (UserResult.STATUS_ACCOUNT_DB_NOT_ENOUGH.equals(ur
                    .getStatusCode())) {// ???????????????????????????
                teacherDetailInfo.setMessage("????????????????????????");
            } else if (UserResult.STATUS_CUSTOM_USER_NAME_EXIST.equals(ur
                    .getStatusCode())) {// ?????????????????????
                teacherDetailInfo.setMessage("?????????????????????");
            } else if (UserResult.STATUS_SUCCESS.equals(ur.getStatusCode())) {// ??????
                teacherDetailInfo.setUserId(ur.getUser().getId());
                teacherDetailInfo.setUserName(ur.getUser().getUserName());

                // ??????B1???????????????????????????????????????yh_user_group????????????????????????????????????
                Role role = this.roleService.findRoleById(Integer
                        .parseInt(teacherDetailInfo.getRole()));
                addUserGroupForTeacher(ur.getUser(), role,
                        teacherDetailInfo.getSchoolId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacherDetailInfo;
    }

    // ??????????????????B1???????????????
    // ??????????????????B1???????????????
    // ??????????????????B1???????????????
    public void addUserGroupForTeacher(User user, Role role, Integer schoolId) {
        groupUserService.addUserToGruopAndRole(user, role, schoolId);
    }

    /* ==========??????????????????B????????????============== */
    /* ==========??????????????????C????????????============== */
    public TeacherDetailInfo processCOfTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        // ??????????????????C??????????????? ?????????????????? pj_teacher

        // ??????????????????C??????????????????????????????????????????????????????
        Boolean flag = checkTeacherNameIsSame(teacherDetailInfo);
        if (flag) {
            // ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            String alias = createAliasForTeacher(teacherDetailInfo.getName(),
                    teacherDetailInfo.getAlias(),
                    teacherDetailInfo.getSchoolId());
            teacherDetailInfo.setAlias(alias);
        } else {
            // ??????????????????????????????????????????????????????????????????
            teacherDetailInfo.setAlias(teacherDetailInfo.getName());
        }
        return teacherDetailInfo;
    }

    // ??????????????????C??????????????? ?????????????????? pj_teacher

    /**
     * ??????????????????C??????????????? ???????????????????????????????????????????????? 2015-11-18
     *
     * @param teacherDetailInfo
     * @return
     */
    public Boolean checkTeacherNameIsSame(TeacherDetailInfo teacherDetailInfo) {
        Boolean flag = false;
        List<Teacher> teacherList = this.teacherDao
                .findTeacherByNameAndSchool(teacherDetailInfo.getName(),
                        teacherDetailInfo.getSchoolId());
        if (teacherList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * ??????????????????C??????????????? ?????????????????????????????????????????????????????? 2015-11-19
     *
     * @param
     * @return
     */
    public Boolean checkTeacherIsOfferAlias(String alias) {
        Boolean flag = false;
        if (alias != null && !"".equals(alias)) {
            flag = true;
        }
        return flag;
    }

    /**
     * ??????????????????C??????????????? ????????????????????????????????? ???????????????????????????????????????????????????????????????????????????????????????????????????
     * ?????????????????????????????????????????????????????? 2015-11-18
     *
     * @param name
     * @param alias
     * @param schoolId
     * @return
     */
    public String createAliasForTeacher(String name, String alias,
                                        Integer schoolId) {
        if (alias == null || "".equals(alias)) {
            alias = name + initAliasForTeacer();
        }
        Boolean flag = checkTeacherAliasIsSame(alias, schoolId);
        if (flag == true) {
            alias = "";
            alias = name + initAliasForTeacer();
            createAliasForTeacher(name, alias, schoolId);
        }
        return alias;
    }

    /**
     * ?????????????????? 2015-11-19
     *
     * @return
     */
    public String initAliasForTeacer() {
        Date date = new Date();
        Long dtime = date.getTime();
        String alias = dtime.toString().substring(
                dtime.toString().length() - 3, dtime.toString().length());
        return alias;
    }

    /**
     * ??????????????????C??????????????? ????????????????????????????????????????????????????????? 2015-11-18
     *
     * @param
     * @param alias
     * @param schoolId
     * @return
     */
    public Boolean checkTeacherAliasIsSame(String alias, Integer schoolId) {
        Boolean flag = false;
        TeacherCondition condition = new TeacherCondition();
        condition.setSchoolId(schoolId);
        condition.setIsDelete(false);
        condition.setAlias(alias);
        List<Teacher> teacherList = this.teacherDao.findTeacherByCondition(
                condition, null, null);
        if (teacherList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /* ==========??????????????????C????????????============== */

    /* ==========??????????????????D????????????============== */
    public TeacherDetailInfo processDOfTeacher(
            TeacherDetailInfo teacherDetailInfo, String userType) throws Exception {
        // ??????????????????D?????????????????????????????????????????????
        teacherDetailInfo = addTeacher(teacherDetailInfo);
        // ??????????????????D??????????????????????????????????????????????????????
        addSchoolUserForTeacher(teacherDetailInfo, userType);
        // ??????????????????D????????????????????????????????????????????????????????????????????????
        addDepartmentTeacher(teacherDetailInfo);
        // ??????????????????D??????????????? ??????pj_teacher???
        return teacherDetailInfo;
    }

    /**
     * ??????????????????D??????????????? ??????????????????????????????????????? 2015-11-18
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo addTeacher(TeacherDetailInfo teacherDetailInfo) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setSchoolId(teacherDetailInfo.getSchoolId());
        teacher.setUserId(teacherDetailInfo.getUserId());
        teacher.setUserName(teacherDetailInfo.getUserName());
        teacher.setPersionId(teacherDetailInfo.getPersionId());
        // teacher.setPersionId(p.getId());
        // teacher.setUserId(ur.getUser().getId());
        // teacher.setUserName(ur.getUser().getUserName());
        // teacher.setName(p.getName());
        // teacher.setSex(p.getSex());
        teacher.setName(teacherDetailInfo.getName());
        teacher.setAlias(teacherDetailInfo.getAlias()); // ??????
        teacher.setSex(teacherDetailInfo.getSex());
        teacher.setJobNumber(teacherDetailInfo.getJobNumber());
        teacher.setEnrollDate(teacherDetailInfo.getEnrollDate());
        teacher.setLeaveDate(teacherDetailInfo.getLeaveDate());
        teacher.setWorkBeginDate(teacherDetailInfo.getWorkBeginDate());
        teacher.setQualification(teacherDetailInfo.getQualification());
        teacher.setOccupationCode(teacherDetailInfo.getOccupationCode());
        teacher.setPosition(teacherDetailInfo.getPosition());
        teacher.setMobile(teacherDetailInfo.getMobile());
        teacher.setTelephone(teacherDetailInfo.getTelephone());
        teacher.setPostStaffing(teacherDetailInfo.getPostStaffing());

        if (teacherDetailInfo.getIsExternal() == null && "".equals(teacherDetailInfo.getIsExternal())) {
            teacher.setIsExternal(false);   //????????????  1--????????????????????????
        } else {
            teacher.setIsExternal(teacherDetailInfo.getIsExternal());   //????????????  1--????????????????????????
        }

        if (teacherDetailInfo.getJobState() == null
                && "".equals(teacherDetailInfo.getJobState())) {
            teacher.setJobState(TeacherCondition.DEFAULT_ZAIZHI);
        } else {
            teacher.setJobState(teacherDetailInfo.getJobState());
        }

        teacher.setCreateDate(new Date());
        teacher.setModifyDate(new Date());
        teacher.setIsDelete(false);
        teacher.setEmpCode(teacherDetailInfo.getUserName());
        Teacher t = this.teacherDao.create(teacher);
        if (t != null) {
            teacherDetailInfo.setTeacherId(t.getId());
        }
        return teacherDetailInfo;
    }

    /**
     * ??????????????????D??????????????? ???????????????????????????????????????????????? 2015-11-18
     *
     * @param teacherDetailInfo
     * @return
     */
    public SchoolUser addSchoolUserForTeacher(
            TeacherDetailInfo teacherDetailInfo, String userType) {
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setSchoolId(teacherDetailInfo.getSchoolId());
        schoolUser.setCreateDate(new Date());
        schoolUser.setModifyDate(new Date());
        // schoolUser.setName(p.getName());
        // schoolUser.setOwnerId(p.getId());
        // schoolUser.setUserId(ur.getUser().getId());
        // schoolUser.setUserType(Integer.parseInt(SysContants.USER_TYPE_TEACHER));
        schoolUser.setName(teacherDetailInfo.getName());
        schoolUser.setOwnerId(teacherDetailInfo.getTeacherId());
        schoolUser.setUserId(teacherDetailInfo.getUserId());
        schoolUser.setPersonId(teacherDetailInfo.getPersionId());
        schoolUser.setUserName(teacherDetailInfo.getUserName());
        schoolUser.setAlias(teacherDetailInfo.getAlias()); // ??????
        schoolUser.setUserType(userType);
        schoolUser.setInSchool(true); // ?????????????????????true--?????????false--??????
        schoolUser.setIsDeleted(false);
        SchoolUser su = schoolUserService.add(schoolUser);
        return su;
    }

    /**
     * ??????????????????D??????????????? ?????????????????????????????????????????????????????????????????? 2015-11-18
     *
     * @param teacherDetailInfo
     * @return
     */
    public List<DepartmentTeacher> addDepartmentTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        List<DepartmentTeacher> departmentTeacherList = new ArrayList<DepartmentTeacher>();
        String departmentIds = teacherDetailInfo.getDepartmentIds();
        if (departmentIds != null && !"".equals(departmentIds)) {
            String[] deptIds = departmentIds.split(",");
            for (int i = 0; i < deptIds.length; i++) {
                Integer deptId = Integer.parseInt(deptIds[i]);

                Department department = this.departmentService
                        .findDepartmentById(deptId);

                DepartmentTeacher deptTeach = null;
                if (department != null) {
                    deptTeach = new DepartmentTeacher();
                    deptTeach.setDepartmentId(deptId);
                    deptTeach.setSchoolId(teacherDetailInfo.getSchoolId());
                    deptTeach.setTeacherId(teacherDetailInfo.getTeacherId());
                    deptTeach.setTeacherName(teacherDetailInfo.getName());
                    deptTeach.setDepartmentName(department.getName());
                    deptTeach = this.departmentTeacherService.add(deptTeach);
                }
                if (deptTeach != null) {
                    departmentTeacherList.add(deptTeach);
                }
            }
        }
        return departmentTeacherList;
    }

    // ??????????????????D??????????????? ??????pj_teacher???
    /* ==========??????????????????D????????????============== */



    /* ===============???????????????2015-1-19???=========== */

    /**
     * ??????????????????????????????
     *
     * @param name
     * @return
     */
    public String trimName(String name) {
        if (name == null || "".equals(name)) {
            return "";
        }
        name = name.trim();
        // ????????????????????????
        name = name.replaceAll(" ", "");
        // ??????????????????????????????
        name = name.replaceAll("???", "");
        // String regStartSpace = "^[??? ]*";
        // String regEndSpace = "[??? ]*$";
        // ???????????? replaceAll
        // ???????????????????????????????????? ?????????????????????????????????
        // String name = name.replaceAll(regStartSpace,
        // "").replaceAll(regEndSpace, "");
        return name;
    }

    @Override
    /**
     * ?????????????????? C2.2?????????????????????C2.3?????????????????????C2.4?????????????????? 2015-11-20
     *
     * @param teacherDetailInfo
     * @return
     * @throws Exception
     */
    public TeacherDetailInfo modifyInfoForTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        try {
            //?????????????????????????????????????????????
            Boolean flagTemp = checkNameAndMobileIsOffer(teacherDetailInfo);
            if (flagTemp) {
                //???????????????????????????????????????
                // ??????????????????????????????
                String name = teacherDetailInfo.getName();
                name = trimName(name);
                teacherDetailInfo.setName(name);

                // ?????????????????????
                String mobile = teacherDetailInfo.getMobile();

                // ?????????????????????
                Teacher teacher = teacherDao.findById(teacherDetailInfo
                        .getTeacherId());
                if (teacher != null) {

                    String teacherMobile = teacher.getMobile();
                    // ??????????????????????????????
                    if (teacherMobile.equals(mobile)) {
                        // ???????????????????????????????????????
                    } else {
                        // ???????????????????????????????????????????????????????????????
                        User user = findUserByMobile(mobile, teacher.getSchoolId());
                        if (user == null) {
                            // ????????????????????????????????????????????????
                            //????????????????????????B??????
                            modifyMobile(teacherDetailInfo.getUserId(), mobile);
                            //????????????????????????C??????
                            RemoveUserBinding(teacherDetailInfo, teacherMobile);
                            addUserBindingForTeacher(teacherDetailInfo);
                        } else {
                            // ?????????????????????????????????????????????
                            // throw new Exception("????????????????????????????????????????????????????????????????????????????????????????????????????????????");
                            teacherDetailInfo.setMessage("?????????????????????????????????????????????????????????????????????????????????????????????????????????");
                            return teacherDetailInfo;
                        }
                    }

                    String teacherName = teacher.getName(); // ?????????????????????
                    teacherName = trimName(teacherName);

                    String teacherAlias = teacher.getAlias() == null ? "" : teacher.getAlias(); // ?????????????????????
                    teacherAlias = trimName(teacherAlias);

                    //2016-3-8?????????????????????????????????????????????????????????
                    Person person = null;
                    if (teacher.getPersionId() != null && !"".equals(teacher.getPersionId())) {
                        person = personService.findPersonById(teacher.getPersionId());
                    } else {
                        // 2018-06-05  ??????person????????????????????????????????????
                        person = addAndSyncPerson(teacherName, teacherDetailInfo.getTeacherId(), teacherDetailInfo.getUserId(), teacherDetailInfo.getSchoolId());
                        teacherDetailInfo.setPersionId(person.getId());
                    }
                    String teacherIdCardNumber = person == null ? "" : person.getIdCardNumber();  //??????????????????????????????
                    teacherIdCardNumber = trimName(teacherIdCardNumber);

                    // ???????????????????????????
                    if (name.equals(teacherName)) {
                        // ??????????????????????????????????????????????????????
                        Boolean flag = checkTeacherIsOfferAlias(teacherDetailInfo.getAlias());
                        if (flag) {
                            // ????????????????????????????????????????????????????????????????????????
                            if (teacherAlias.equals(teacherDetailInfo.getAlias())) {
                                // ?????????????????????????????????
                            } else {
                                // ??????????????????????????????????????????????????????????????????????????????
                                String alias = createAliasForTeacher(teacherDetailInfo.getName(), teacherDetailInfo.getAlias(), teacherDetailInfo.getSchoolId());
                                teacherDetailInfo.setAlias(alias);
                                if ("".equals(alias) || alias == null) {
                                    // throw new Exception("??????????????????");
                                    teacherDetailInfo.setMessage("??????????????????");
                                    return teacherDetailInfo;
                                }
                            }
                        } else {
                            // ????????????????????????
                        }
                    } else {
                        // ?????????????????????????????????????????????????????????????????????????????????????????????
                        if (teacherDetailInfo.getAlias() == teacherAlias || teacherAlias.equals(teacherDetailInfo.getAlias())) {
                            // ???????????????????????????????????????????????????????????????
                        } else {
                            //??????????????????????????????????????????????????????????????????????????????
                            teacherDetailInfo = processCOfTeacher(teacherDetailInfo);
                        }
                    }

                    //?????????2016-02-29
                    //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    if (teacherDetailInfo != null && teacherDetailInfo.getCertificateNum() != null && !"".equals(teacherDetailInfo.getCertificateNum())) {
                        if (teacherIdCardNumber.equals(teacherDetailInfo.getCertificateNum())) {
                            //??????????????????????????????????????????
                        } else {
                            //????????????????????????????????????????????????????????????????????????
                            Boolean flag = checkIdCardNumerIsExist(teacherDetailInfo.getCertificateNum());
                            if (flag) {
                                teacherDetailInfo.setMessage("???????????????????????????");
                                return teacherDetailInfo;
                            } else {
                                //????????????????????????????????????????????????
                            }
                        }
                    }

                    //??????????????????
                    //?????????????????? 2021???11???16???????????????????????????
                    EmployeeList employeeList=new EmployeeList();
                    //??????
                    employeeList.setEmp_pycode(String.valueOf(teacher.getUserId()));
                    if(teacherDetailInfo.getName()!=null && teacherDetailInfo.getName()!=""){
                        //??????
                        employeeList.setEmp_name(teacherDetailInfo.getName());
                    }else{
                        employeeList.setEmp_name(teacher.getName());
                    }
                    if(teacherDetailInfo.getSex()!=null && teacherDetailInfo.getSex()!=""){
                        //??????
                        employeeList.setEmp_sex(teacherDetailInfo.getSex());
                    }else{
                        if(teacher.getSex()=="1"){
                            employeeList.setEmp_sex("???");
                        }else if(teacher.getSex()=="0"){
                            employeeList.setEmp_sex("???");
                        }
                    }
                    if(teacherDetailInfo.getJobNumber()!=null && teacherDetailInfo.getJobNumber()!=""){
                        //??????
                        employeeList.setEmp_code(teacherDetailInfo.getJobNumber());
                    }else{
                        employeeList.setEmp_code(teacher.getEmpCode());
                    }
                    //??????
                    employeeList.setEmp_card(teacher.getEmpCard());
                    //????????????
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    employeeList.setEmp_workdate(ft.format(teacher.getCreateDate()));
                    if(teacherDetailInfo.getBirthDate()!=null && !teacherDetailInfo.getBirthDate().equals("")){
                        employeeList.setEmp_birthday(ft.format(teacherDetailInfo.getBirthDate()));
                    }
                    if(teacherDetailInfo.getMobile()!=null && teacherDetailInfo.getMobile()!=""){
                        employeeList.setEmp_tel(teacherDetailInfo.getMobile());
                    }
                    if(teacherDetailInfo.getNowAddress()!=null && teacherDetailInfo.getNowAddress()!=""){
                        employeeList.setEmp_address(teacherDetailInfo.getNowAddress());
                    }
                    if(teacherDetailInfo.getCertificateNum()!=null && teacherDetailInfo.getCertificateNum()!="" ){
                        employeeList.setEmp_idcard(teacherDetailInfo.getCertificateNum());
                    }else{
                        employeeList.setEmp_idcard(teacher.getUserName());
                    }
                    Object object = JSONObject.toJSON(employeeList);
                    JSONObject param = new JSONObject();
                    param.put("sign_name","kksss");
                    //param.put("tran_code","emp_add");
                    param.put("tran_code","emp_update");
                    param.put("employeeList", object);
                    //canteenThreadPoolExecutor.submit
                    HttpRequestResult httpRequestResult=null;
                    HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdate")
                            .addHeader("content-type", "application/json")
                            .httpEntityType(HttpEntityType.ENTITY_STRING);
                    config.json(param.toString());
                    try {
                        httpRequestResult = HttpClientUtils.post(config);
                        //????????????????????????????????????
                        if (httpRequestResult == null) {
                            log.info("?????????????????????");
                        }
                        if (200 == httpRequestResult.getCode()) {
                            String responseText = httpRequestResult.getResponseText();
                            if (("").equals(responseText) || responseText == null) {
                                log.info("????????????????????????");
                            }
                            JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                            log.info("????????????"+jsonObject2);
                        }else {
                            log.error("????????????--???????????????????????????????????????, ???????????? {}", httpRequestResult);
                        }
                        log.info("????????????--??????????????????????????????{}"," ????????????: {}----"+httpRequestResult);
                    }catch (IOException e) {
                        log.info("????????????--??????????????????????????????{}??? ???????????????????????????{}"+ e);
                    }
                    // ?????????????????????
                    teacherDetailInfo = modifyTeacher(teacherDetailInfo);
                    // ???????????????????????????
                    teacherDetailInfo = modifyPersonForTeacher(teacherDetailInfo);
                    // ???????????????????????????
                    teacherDetailInfo = modifyProfileForTeacher(teacherDetailInfo);
                    // ???????????????????????????
                    teacherDetailInfo = modifySchoolUserForTeacher(teacherDetailInfo);
                    // ???????????????????????????
                    teacherDetailInfo = modifyTeamTeacher(teacherDetailInfo);
                    // ???????????????????????????
                    teacherDetailInfo = modifySubjectTeacher(teacherDetailInfo);
                    // ???????????????????????????
                    teacherDetailInfo = modifyDepartmentTeacher(teacherDetailInfo);
                    //????????????????????????????????????????????????????????????????????????????????????
                    modifyName(teacherDetailInfo.getUserId(), teacherDetailInfo.getName());
                    //???????????????????????????????????????
                   if(teacherDetailInfo.getJobState().equals("11")){

                        basicSQLService.update("update yh_user set state=0 where id="+teacherDetailInfo.getUserId());
                    }else{
                        basicSQLService.update("update yh_user set state=1 where id="+teacherDetailInfo.getUserId());
                    }
                }
            } else {
                //????????????????????????????????????
                teacherDetailInfo.setMessage("???????????????");
            }

        } catch (Exception e) {
            log.info("????????????????????????");
            e.printStackTrace();
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return teacherDetailInfo;
    }


    private Person addAndSyncPerson(String name, Integer teacherId, Integer userId, Integer schoolId) {
        Person person = new Person();
        person.setName(name);
        person.setIsDelete(false);
        person.setCreateDate(new Date());
        person.setModifyDate(new Date());
        person = personService.add(person);

        if (person != null) {
            Integer personId = person.getId();
            Teacher teacher = new Teacher(teacherId);
            teacher.setPersionId(personId);
            teacher.setModifyDate(new Date());
            teacherDao.update(teacher);

            SchoolUser schoolUser = schoolUserService.findSchoolUserListByUserIdAndType(userId, "1");
            if (schoolUser != null) {
                schoolUser = new SchoolUser(schoolUser.getId());
                schoolUser.setPersonId(personId);
                schoolUser.setModifyDate(new Date());
                schoolUserService.modify(schoolUser);
            }

            User user = new User(userId);
            user.setPersonId(personId);
            user.setModifyDate(new Date());
            userDao.update(user);
        }
        return person;
    }

    /*=========== C2.2 ??????????????????????????????   ===========  */

    /**
     * ????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifyTeacher(TeacherDetailInfo teacherDetailInfo) {
        Teacher teacher = teacherDao.findById(teacherDetailInfo.getTeacherId());
        // User user = userService.findUserById(teacherDetailInfo.getUserId());
        // Person person =
        // personService.findPersonById(teacherDetailInfo.getPersionId());
        // teacher.setUserName(u.getUserName());
        if (teacher != null) {
            teacher.setName(teacherDetailInfo.getName());
            teacher.setAlias(teacherDetailInfo.getAlias()); // ??????
            teacher.setSex(teacherDetailInfo.getSex());
            teacher.setEmpCode(teacherDetailInfo.getJobNumber());
            teacher.setEnrollDate(teacherDetailInfo.getEnrollDate());
            teacher.setLeaveDate(teacherDetailInfo.getLeaveDate());
            teacher.setWorkBeginDate(teacherDetailInfo.getWorkBeginDate());
            teacher.setQualification(teacherDetailInfo.getQualification());
            teacher.setOccupationCode(teacherDetailInfo.getOccupationCode());
            teacher.setPosition(teacherDetailInfo.getPosition());
            teacher.setMobile(teacherDetailInfo.getMobile()); // ????????????
            teacher.setTelephone(teacherDetailInfo.getTelephone());
            teacher.setPostStaffing(teacherDetailInfo.getPostStaffing());
            teacher.setJobState(teacherDetailInfo.getJobState());
            teacher.setIsExternal(teacherDetailInfo.getIsExternal()); // ????????????
            teacher.setModifyDate(new Date());
            teacher = this.teacherDao.update(teacher);
        }
        return teacherDetailInfo;
    }

    /**
     * ?????????????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifyPersonForTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        // Person person = personService.findPersonById(teacher.getPersionId());
        Person person = personService.findPersonById(teacherDetailInfo
                .getPersionId());
        if (person != null) {
            person.setName(teacherDetailInfo.getName());
            person.setEnglishName(teacherDetailInfo.getEnglishName());
            person.setSex(teacherDetailInfo.getSex());
            person.setIdCardNumber(teacherDetailInfo.getCertificateNum());
            person.setBirthday(teacherDetailInfo.getBirthDate());
            person.setIdCardType(teacherDetailInfo.getCertificateType());
            person.setNationality(teacherDetailInfo.getNationality());
            person.setNativePlace(teacherDetailInfo.getNativePlace());
            person.setBirthPlace(teacherDetailInfo.getBirthPlace());
            person.setRace(teacherDetailInfo.getNation());
            person.setPoliticalStatus(teacherDetailInfo.getPolitical());
            person.setReligion(teacherDetailInfo.getReligiousBelief());
            person.setMarriage(teacherDetailInfo.getMarriage());

            person.setAddress(teacherDetailInfo.getNowAddress());
            person.setResideAddress(teacherDetailInfo.getLiveAddress());
            person.setResidenceAddress(teacherDetailInfo.getRegisterPlace());
            person.setMobile(teacherDetailInfo.getMobile()); // ????????????
            person.setTelephone(teacherDetailInfo.getTelephone());
            person.setEmail(teacherDetailInfo.getEmail());
            person.setSpecialSkill(teacherDetailInfo.getSpecialty());
            person.setResidenceType(teacherDetailInfo.getRegister());
            person.setRemark(teacherDetailInfo.getRemark());
            person.setEntityId(teacherDetailInfo.getEntityId());// ??????ID
            person.setCreateDate(new Date());
            person.setModifyDate(new Date());
            person.setIsDelete(false);

            person.setPhotoUuid(teacherDetailInfo.getPhotoUuid());    //??????id 2016-08-08

            person = personService.modify(person);
        }
        return teacherDetailInfo;
    }

    /**
     * ?????????????????????????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifyProfileForTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        Profile profile = this.profileService.findByUserId(teacherDetailInfo
                .getUserId());
        if (profile != null) {
            profile.setName(teacherDetailInfo.getName());
            profile = profileService.modify(profile);
        }
        return teacherDetailInfo;
    }

    /**
     * ??????????????????????????????????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifySchoolUserForTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        SchoolUser schoolUser = null;
        SchoolUserCondition condition = new SchoolUserCondition();
        condition.setSchoolId(teacherDetailInfo.getSchoolId());
        condition.setIsDeleted(false);
        condition.setUserId(teacherDetailInfo.getUserId());
//		condition.setUserType(SysContants.USER_TYPE_TEACHER);
        condition.setUserType(teacherDetailInfo.getUserType());
        condition.setOwnerId(teacherDetailInfo.getTeacherId());
        List<SchoolUser> schoolUserList = this.schoolUserService
                .findSchoolUserByCondition(condition, null, null);
        if (schoolUserList.size() > 0) {
            schoolUser = schoolUserList.get(0);
            if (schoolUser != null) {
                schoolUser.setName(teacherDetailInfo.getName());
                schoolUser.setAlias(teacherDetailInfo.getAlias());
                schoolUser = schoolUserService.modify(schoolUser);
            }
        }
        return teacherDetailInfo;
    }

    /**
     * ????????????????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifyTeamTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        TeamTeacherCondition condition = new TeamTeacherCondition();
        condition.setTeacherId(teacherDetailInfo.getTeacherId());
        condition.setDelete(false);
        List<TeamTeacher> teamTeacherList = this.teamTeacherService.findTeamTeacherByCondition(condition, null, null);
        for (TeamTeacher teamTeacher : teamTeacherList) {
            teamTeacher.setName(teacherDetailInfo.getName());
            teamTeacher.setModifyDate(new Date());
            teamTeacher = this.teamTeacherService.modify(teamTeacher);
        }
        return teacherDetailInfo;
    }

    /**
     * ????????????????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifySubjectTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        SubjectTeacherCondition condition = new SubjectTeacherCondition();
        condition.setTeacherId(teacherDetailInfo.getTeacherId());
        List<SubjectTeacher> subjectTeacherList = this.subjectTeacherService.findSubjectTeacherByCondition(condition, null, null);
        for (SubjectTeacher subjectTeacher : subjectTeacherList) {
            subjectTeacher.setName(teacherDetailInfo.getName());
            subjectTeacher.setModifyDate(new Date());
            subjectTeacher = this.subjectTeacherService.modify(subjectTeacher);
        }
        return teacherDetailInfo;
    }

    /**
     * ????????????????????????????????????????????? 2015-11-19
     *
     * @param teacherDetailInfo
     * @return
     */
    public TeacherDetailInfo modifyDepartmentTeacher(
            TeacherDetailInfo teacherDetailInfo) {
        DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
        condition.setTeacherId(teacherDetailInfo.getTeacherId());
        condition.setIsDeleted(false);
        List<DepartmentTeacher> departmentTeacherList = this.departmentTeacherService.findDepartmentTeacherByCondition(condition, null, null);
        for (DepartmentTeacher departmentTeacher : departmentTeacherList) {
            departmentTeacher.setTeacherName(teacherDetailInfo.getName());
            departmentTeacher.setModifyDate(new Date());
            departmentTeacher = this.departmentTeacherService.modify(departmentTeacher);
        }
        return teacherDetailInfo;
    }

    /*=========== C2.2 ??????????????????????????????   ===========  */

    /**
     * C2.4????????????????????????C???????????????
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * 2015-11-24
     *
     * @param teacherDetailInfo
     * @param teacherMobile
     */
    public void RemoveUserBinding(TeacherDetailInfo teacherDetailInfo, String teacherMobile) {
        UserBinding userBinding = new UserBinding();
        userBinding = this.userBindingService.findUnique(teacherDetailInfo.getUserId(), teacherMobile);
        if (userBinding != null) {
            this.userBindingService.remove(userBinding);
        }
    }

    /**
     * C2.4????????????????????????C???????????????
     * ????????????????????????????????????????????????????????????
     * 2015-11-24
     *
     * @param teacherDetailInfo
     * @return
     */
    public UserBinding addUserBindingForTeacher(TeacherDetailInfo teacherDetailInfo) {
        UserBinding userBinding = new UserBinding();
        userBinding.setUserId(teacherDetailInfo.getUserId());
        userBinding.setBindingName(teacherDetailInfo.getMobile());
        userBinding.setBindingType(UserBindingContants.TYPE_PHONE);
        userBinding.setEnabled(true);
        userBinding.setIsDeleted(false);
        userBinding = this.userBindingService.add(userBinding);
        return userBinding;
    }

    /* ===============???????????????2015-11-19???=========== */

    /*=======================??????????????????????????????????????????????????????????????????????????????????????????(2015-11-27)======================*/
    public void modifyMobile(Integer userId, String mobile) {
        updateAllTeachers(userId, mobile);
        updateAllStudents(userId, mobile);
        updateAllParents(userId, mobile);
    }

    /**
     * ???????????????????????????ID????????????pj_teacher?????????????????????
     * 2015-11-27
     *
     * @param
     * @return
     */
    public List<Teacher> findTeacherByUserId(Integer userId) {
        TeacherCondition condition = new TeacherCondition();
        condition.setIsDelete(false);
        condition.setUserId(userId);
        List<Teacher> list = teacherDao.findTeacherByCondition(condition, null, null);
        return list;
    }

    /**
     * ????????????????????????pj_teacher??????????????????????????????????????????
     * 2015-11-27
     *
     * @param
     */
    public void updateAllTeachers(Integer userId, String mobile) {
        List<Teacher> list = findTeacherByUserId(userId);
        Teacher teacher = null;
        if (list.size() > 0) {
            for (Teacher t : list) {
                teacher = new Teacher();
                teacher.setId(t.getId());
                teacher.setMobile(mobile);
                teacherDao.update(teacher);
            }
        }
    }

    /**
     * ???????????????????????????ID????????????pj_student?????????????????????
     * 2015-11-27
     *
     * @param userId
     * @return
     */
    public List<Student> findStudentByUserId(Integer userId) {
        StudentCondition condition = new StudentCondition();
        condition.setIsDelete(false);
        condition.setUserId(userId);
        ;
        List<Student> list = studentService.findStudentByOnlyCondition(condition, null, null);
        return list;
    }

    /**
     * ????????????????????????pj_student??????????????????????????????????????????
     * 2015-11-27
     *
     * @param
     */
    public void updateAllStudents(Integer userId, String mobile) {
        List<Student> list = findStudentByUserId(userId);
        Student student = null;
        if (list.size() > 0) {
            for (Student s : list) {
                student = new Student();
                student.setId(s.getId());
                student.setMobile(mobile);
                studentService.modify(student);
            }
        }
    }

    /**
     * ???????????????????????????ID?????????(pj_parent)??????????????????
     * 2015-11-27
     *
     * @param userId
     * @return
     */
    public List<Parent> findParentByUserId(Integer userId) {
        ParentCondition condition = new ParentCondition();
        condition.setIsDelete(false);
        condition.setUserId(userId);
        ;
        List<Parent> list = parentService.findParentByCondition(condition);
        return list;
    }

    /**
     * ????????????????????????pj_parent??????????????????????????????????????????
     * 2015-11-27
     *
     * @param
     */
    public void updateAllParents(Integer userId, String mobile) {
        List<Parent> list = findParentByUserId(userId);
        Parent parent = null;
        if (list.size() > 0) {
            for (Parent p : list) {
                parent = new Parent();
                parent.setId(p.getId());
                parent.setMobile(mobile);
                parentService.modify(parent);
            }
        }
    }


    /*=======================??????????????????????????????????????????????????????????????????????????????????????????(2015-11-27)======================*/

    @Override
    //????????????????????????
    public void saveTeacherInfoList(List<TeacherDetailInfoVo> successMeaageVoList, String password, Integer appId) throws Exception {
        try {
            for (int i = 0; i < successMeaageVoList.size(); i++) {
                TeacherDetailInfoVo tdv = successMeaageVoList.get(i);
                Person person = new Person();
                person.setName(tdv.getName());
                person.setIdCardNumber(tdv.getCertificateNum());
                person.setSex(tdv.getSex());
                person.setMobile(tdv.getMobile());
                person.setEnglishName(tdv.getEnglishName());

//					person.setBirthday(tdv.getBirthDate());
//					person.setIdCardType(tdv.getCertificateType());
//					person.setNationality(tdv.getNationality());
//					person.setNativePlace(tdv.getNativePlace());
//					person.setBirthPlace(tdv.getBirthPlace());
//					person.setRace(tdv.getNation());
//					person.setPoliticalStatus(tdv.getPolitical());
//					person.setReligion(tdv.getReligiousBelief());
//					person.setMarriage(tdv.getMarriage());
//					person.setAddress(tdv.getNowAddress());
//					person.setResideAddress(tdv.getRegisterPlace());
//					person.setTelephone(tdv.getTelephone());
//					person.setEmail(tdv.getEmail());
//					person.setSpecialSkill(tdv.getSpecialty());
//					person.setResidenceType(tdv.getRegister());
//					person.setRemark(tdv.getRemark());

                person.setCreateDate(new Date());
                person.setModifyDate(new Date());
                person.setIsDelete(false);

                Person p = personService.add(person);

                User user = new User();
                user.setIsDeleted(false);
                user.setCreateDate(new Date());
                user.setModifyDate(new Date());
                user.setPassword(password);
                //user.setValidDate(new Date());
                user.setState(UserContants.STATE_NORMAL);
                //user.setUserName(UserAccountUtil.getUserAccount());
                //User u = userService.add(user);
                //User u = this.userService.addUser(user,tdv.getSchoolId(),GroupContants.TYPE_SCHOOL,SysContants.SYSTEM_APP_ID);
                UserResult ur = userService.addUser(user, tdv.getSchoolId(), GroupContants.TYPE_SCHOOL, tdv.getMobile(), UserBindingContants.TYPE_PHONE, appId);

                Teacher teacher = new Teacher();
                teacher.setPersionId(p.getId());
                teacher.setSchoolId(tdv.getSchoolId());
                teacher.setUserId(ur.getUser().getId());
                teacher.setUserName(ur.getUser().getUserName());
                teacher.setName(p.getName());
                teacher.setSex(tdv.getSex());
                teacher.setMobile(tdv.getMobile());
                teacher.setCreateDate(new Date());
                teacher.setModifyDate(new Date());
                teacher.setIsDelete(false);

                //????????????
                UserRole userRole = new UserRole();
                Role role = new Role();
                role.setId(tdv.getRoleId());
                role.setCreateDate(new Date());
                userRole.setUser(ur.getUser());
                userRole.setRole(role);
                this.userRoleService.add(userRole);

                //System.out.println("=============tdv.getName():"+tdv.getName());
                this.teacherDao.create(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<TeacherDetailInfo> findAllTeacherDetailInfo(Integer schoolId) throws Exception {
        List<TeacherDetailInfo> teacherDetailInfo = new ArrayList<TeacherDetailInfo>();
        try {
            TeacherCondition teacherCondition = new TeacherCondition();
            teacherCondition.setSchoolId(schoolId);
            List<Teacher> teacherList = this.teacherDao.findTeacherByCondition(teacherCondition, null, null);
            for (Teacher t : teacherList) {
                TeacherDetailInfo teacherDetail = findTeacherDetailInfoById(t.getId());
                teacherDetailInfo.add(teacherDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherDetailInfo;
    }

    @Override
    public List<TeacherDetailInfo> findTeacherDetailInfoByCondition(TeacherCondition teacherCondition) throws Exception {
        List<TeacherDetailInfo> teacherDetailInfo = new ArrayList<TeacherDetailInfo>();
        try {
            List<Teacher> teacherList = this.teacherDao.findTeacherByCondition(teacherCondition, null, null);
            for (Teacher t : teacherList) {
                TeacherDetailInfo teacherDetail = findTeacherDetailInfoById(t.getId());
                teacherDetailInfo.add(teacherDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherDetailInfo;
    }

    @Override
    /**
     * ??????????????????????????? 2016-1-29
     * ??????autoCreateDept???????????????????????????
     * @param teacherDetailInfoList
     * @param appId
     * @param autoCreateDept ????????????????????????
     * @return extImportTeacherErrorMsg ?????????????????????????????????
     */
    public ExtImportTeacherErrorMsg ImportInfoForTeacher(ExtImportTeacherVo extImportTeacherVo, Integer appId, Boolean autoCreateDept, Integer schoolId, String password, String userType) {
        ExtImportTeacherErrorMsg msg = null;
        Department departmet = null;
        TeacherDetailInfo teacherDetailInfo = null;
        try {
            //???????????????????????????
            teacherDetailInfo = new TeacherDetailInfo();
            teacherDetailInfo.setName(extImportTeacherVo.getBasic().getName());
            teacherDetailInfo.setMobile(extImportTeacherVo.getBasic().getMobile());
            teacherDetailInfo.setSex(extImportTeacherVo.getBasic().getSex());
            teacherDetailInfo.setAlias(extImportTeacherVo.getBasic().getAlias());
            teacherDetailInfo.setDepartmentName(extImportTeacherVo.getBasic().getDepartment());
            teacherDetailInfo.setPosition(extImportTeacherVo.getBasic().getPosition());
            teacherDetailInfo.setPostStaffing(extImportTeacherVo.getBasic().getPostStaffing());

            //???????????????????????????ID???yh_role.id???2016-02-29
            Role role = null;
            Group group = null;
            group = this.groupService.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
            if (group != null) {
                role = this.roleService.findUniqueInGroup(appId, group.getId(), SysDefRole.TEACHER);
                if (role != null) {
                    teacherDetailInfo.setRole(String.valueOf(role.getId()));
                }
            }

            //??????
            teacherDetailInfo.setDepartmentIds(null);
            teacherDetailInfo.setDepartmentName(null);

            //??????
            teacherDetailInfo.setSchoolId(schoolId);

            //???????????????
            if (extImportTeacherVo.getBasic().getDepartment() != null && !"".equals(extImportTeacherVo.getBasic().getDepartment())) {

                //????????????????????????????????????????????????
                departmet = departmentService.findDepartmentByNameAndSchoolId(extImportTeacherVo.getBasic().getDepartment(), schoolId);

                //????????????????????????????????????????????????????????????????????????????????????ID?????????????????????????????????????????????????????????
                if (departmet == null) {

                    //????????????????????????
                    if (autoCreateDept) {

                        //????????????
                        Department dep = createDepartment(extImportTeacherVo.getBasic().getDepartment(), schoolId);

                        //?????????????????????????????????
                        if (dep != null) {
                            teacherDetailInfo.setDepartmentIds(dep.getId() + "");
                            teacherDetailInfo.setDepartmentName(dep.getName());
                        }

                    }
                } else {
                    teacherDetailInfo.setDepartmentIds(departmet.getId() + "");
                    teacherDetailInfo.setDepartmentName(departmet.getName());
                }
            }

            //??????????????????    ????????????????????????????????????  2016-2-17
//			Role role = getRole(extImportTeacherVo.getPosition(),schoolId);
//			if(role != null){
//				teacherDetailInfo.setRole(role.getCode());
//			}

            //?????????????????????
            TeacherDetailInfo tdi = addInfoForTeacher(teacherDetailInfo, null, password, appId, userType);

            //???????????????
            if (!"".equals(tdi.getMessage())) {
                msg = new ExtImportTeacherErrorMsg();
                msg.setId(extImportTeacherVo.getBasic().getId());
                msg.setMsg(tdi.getMessage());
            }
            return msg;
        } catch (Exception e) {
            log.debug("??????????????????");
            return msg;
        }
    }

    /**
     * ???????????????????????????
     * ???????????????????????????????????????  ??????????????????
     *
     * @param depName  ????????????
     * @param schoolId ??????ID
     * @return department ???????????????
     */

    public Department createDepartment(String depName, Integer schoolId) {
        Department department = departmentService.findDepartmentByNameAndSchoolId(depName, schoolId);
        if (department != null) {
            return department;
        }
        Department dep = new Department();
        dep.setChildrens(null);
        dep.setCreateDate(new Date());
        dep.setIsDelete(false);
        dep.setListOrder(null);
        dep.setModifyDate(new Date());
        dep.setName(depName);
        dep.setSchoolId(schoolId);
        dep.setParent(null);
        dep.setParentId(0);
        dep.setLevel(1);
        return departmentService.add(dep);
    }

    @Override
    /***
     * ?????????????????????
     */
    public UserDetailInfo saveUserInfoFromClient(UserDetailInfoVo userDetailInfo, Integer appId, String password, String studentType, String parentRank, String parentRelation) throws Exception {
        UserDetailInfo udi = null;
        try {
            udi = new UserDetailInfo();
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
            person.setResidenceType(userDetailInfo.getRegister());
            person.setProvince(userDetailInfo.getProvince());
            person.setCity(userDetailInfo.getCity());
            person.setDistrict(userDetailInfo.getDistrict());
            person.setStreet(userDetailInfo.getStreet());
            person.setAddress(userDetailInfo.getNowAddress());
            person.setResideAddress(userDetailInfo.getRegisterPlace());
            person.setMobile(userDetailInfo.getTelephone());
            person.setTelephone(userDetailInfo.getCellPhone());
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
            Person p = personService.add(person);

            User user = new User();
            user.setIsDeleted(false);
            user.setCreateDate(new Date());
            user.setModifyDate(new Date());
//			user.setPassword(SysContants.DEFAULT_PASSWORD);
            user.setPassword(password);
            //user.setUserName(UserAccountUtil.getUserAccount());
            //user.setUserName(userDetailInfo.getUsername());
            //user.setValidDate(new Date());
            user.setState(UserContants.STATE_NORMAL);
            user.setIsDeleted(false);


            //User u = this.userService.addUser(user, userDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL, appId);
            UserResult ur = userService.addUser(user, userDetailInfo.getSchoolId(), GroupContants.TYPE_SCHOOL, userDetailInfo.getCellPhone(), UserBindingContants.TYPE_PHONE, appId);

            SchoolUser schoolUser = new SchoolUser();
            schoolUser.setCreateDate(new Date());
            schoolUser.setModifyDate(new Date());
            schoolUser.setName(p.getName());
            schoolUser.setOwnerId(p.getId());
            schoolUser.setSchoolId(userDetailInfo.getSchoolId());
            schoolUser.setUserId(ur.getUser().getId());
//			schoolUser.setUserType(Integer.parseInt(SysContants.USER_TYPE_STUDENT));
//			schoolUser.setUserType(SysContants.USER_TYPE_STUDENT);
            schoolUser.setUserType(studentType);
            schoolUserService.add(schoolUser);

            Student student = new Student();
            student.setSchoolId(userDetailInfo.getSchoolId());
            student.setPersonId(p.getId());
            student.setUserId(ur.getUser().getId());
            student.setUserName(ur.getUser().getUserName());
            student.setName(userDetailInfo.getName());
            student.setSex(userDetailInfo.getSex());
            student.setStudentNumber(userDetailInfo.getStudentNum());
            student.setEnrollDate(userDetailInfo.getEnterDate());    //????????????  2016-02-23
            student.setLeaveDate(userDetailInfo.getEndDate());        //???????????? 2016-02-23
            student.setMobile(userDetailInfo.getTelephone());

            student.setStudyState(userDetailInfo.getState());
            student.setStudentType(userDetailInfo.getStudentType());
            student.setPosition(userDetailInfo.getPosition());
            student.setIsBoarded(userDetailInfo.getIsBoarded());
            Team team = this.teamService.findTeamById(Integer.parseInt(userDetailInfo.getTeamId()));

            student.setTeamId(team.getId());
            student.setTeamName(team.getName());
            student.setCreateDate(new Date());
            student.setIsDelete(false);
            Student s = studentService.add(student);


            //????????????
            UserRole userRole = new UserRole();
            Role role = new Role();
            role.setId(Integer.parseInt(userDetailInfo.getRole()));
            role.setCreateDate(new Date());
            userRole.setUser(ur.getUser());
            userRole.setRole(role);
            UserRole ur1 = this.userRoleService.add(userRole);
            if (ur1 == null) {
                throw new Exception("??????????????????");
            }
            udi.setStudentId(s.getId());
            udi.setPersonId(p.getId());
            udi.setUserId(ur.getUser().getId());
            udi.setUsername(ur.getUser().getUserName());

            //????????????
            TeamStudent teamStudent = new TeamStudent();
            teamStudent.setGradeId(Integer.parseInt(userDetailInfo.getGradeId()));
            teamStudent.setTeamId(team.getId());
            teamStudent.setStudentId(s.getId());
            teamStudent.setName(userDetailInfo.getName());
            teamStudent.setJoinDate(new Date());
            teamStudent.setCreateDate(new Date());
            this.teamStudentService.add(teamStudent);

            //??????????????????
            if (userDetailInfo.getParentCellPhone() != null && !"".equals(userDetailInfo.getParentCellPhone())) {
                ParentVo parentvo = new ParentVo();
                parentvo.setSchoolId(userDetailInfo.getSchoolId());
                parentvo.setSystem_app_id(appId);
                parentvo.setStudentId(s.getId());
                parentvo.setName(userDetailInfo.getParentCellPhone());
                parentvo.setMobile(userDetailInfo.getParentCellPhone());
                //???????????? parentRelation
//				parentvo.setParentRelation(SysContants.TEACHINGAFFAIR_PARENT_PARENTRELATION);
                parentvo.setParentRelation(parentRelation);
                //???????????? parentRank
//				parentvo.setRank(SysContants.TEACHINGAFFAIR_PARENT_RANK);
                parentvo.setRank(parentRank);
                this.parentProxyService.add(parentvo);
            }

        } catch (Exception e) {
            log.info("??????????????????????????????....");
            e.printStackTrace();
            throw e;
        }
        return udi;

    }

    @Override
    public TeacherArchiveVolist getTeacherArchiveMess(Integer departmentId) {
        if (departmentId == null) {
            return null;
        }

        //??????????????????????????????
        List<TeacherArchiveVo> finish = new ArrayList<TeacherArchiveVo>();

        //??????????????????????????????
        List<TeacherArchiveVo> unFinish = new ArrayList<TeacherArchiveVo>();

        //??????????????????????????????
        TeacherArchiveVolist teacherArchiveVolist = new TeacherArchiveVolist();

        TeacherArchiveVo tav = null;
        List<TeacherMessVo> teacherMessVoList = teacherDao.findTeacherVoByDeptId(departmentId);
        if (teacherMessVoList != null) {
            JobControlCondition jobControlCondition = null;
            for (TeacherMessVo teacherMessVo : teacherMessVoList) {
                if (teacherMessVo != null && teacherMessVo.getUserId() != null) {
                    jobControlCondition = new JobControlCondition();
                    jobControlCondition.setAppKey(StudentArchiveContants.TYPE_ARCHIVE_APPKEY);
                    jobControlCondition.setIsDeleted(false);
                    jobControlCondition.setName(StudentArchiveContants.TYPE_TEACHER_FINISH);
                    jobControlCondition.setObjectId(teacherMessVo.getUserId());
                    List<JobControl> jobControlList = jobControlService.findJobControlByCondition(jobControlCondition);
                    if (jobControlList != null && jobControlList.size() > 0) {
                        if (jobControlList.get(0) != null && jobControlList.get(0).getInterrupteur()) {
                            tav = teacherMessVo2TeacherArchiveVo(teacherMessVo);
                            if (tav != null) {
                                finish.add(tav);
                            }
                        } else {
                            tav = teacherMessVo2TeacherArchiveVo(teacherMessVo);
                            if (tav != null) {
                                unFinish.add(tav);
                            }
                        }
                    } else {
                        tav = teacherMessVo2TeacherArchiveVo(teacherMessVo);
                        if (tav != null) {
                            unFinish.add(tav);
                        }
                    }
                }
            }
        }
        teacherArchiveVolist.setFinish(finish);
        teacherArchiveVolist.setUnFinish(unFinish);
        return teacherArchiveVolist;
    }

    public TeacherArchiveVo teacherMessVo2TeacherArchiveVo(TeacherMessVo teacherMessVo) {
        TeacherArchiveVo tav = null;
        if (teacherMessVo != null) {
            tav = new TeacherArchiveVo();
            tav.setIconUUID(teacherMessVo.getIconUUID());
            tav.setName(teacherMessVo.getName());
            tav.setNumber(teacherMessVo.getJobNumber());
            tav.setUserIcon(null);
            tav.setUserId(teacherMessVo.getUserId());
        }
        return tav;
    }

    @Override
    public List<TeacherMessVo> findTeacherVoByDeptId(Integer departmentId) {
        if (departmentId == null) {
            return null;
        }
        return teacherDao.findTeacherVoByDeptId(departmentId);
    }

    /**
     * ????????????????????????????????????????????????????????????????????????????????????
     *
     * @param userId
     * @param name
     */
    public void modifyName(Integer userId, String name) {
        List<Teacher> teacherList = findTeacherByUserId(userId);
        Teacher teacher = null;
        if (teacherList != null && teacherList.size() > 0) {
            for (Teacher t : teacherList) {
                teacher = new Teacher();
                teacher.setId(t.getId());
                teacher.setName(name);
                teacherDao.update(teacher);
            }
        }
        List<Student> studentList = findStudentByUserId(userId);
        Student student = null;
        if (studentList != null && studentList.size() > 0) {
            for (Student s : studentList) {
                student = new Student();
                student.setId(s.getId());
                student.setName(name);
                studentService.modify(student);
            }
        }
        List<Parent> parentList = findParentByUserId(userId);
        Parent parent = null;
        if (parentList != null && parentList.size() > 0) {
            for (Parent p : parentList) {
                parent = new Parent();
                parent.setId(p.getId());
                parent.setName(name);
                parentService.modify(parent);
            }
        }
    }    //-----------------????????????  start-----------------------

    @Override
    public List<StatisticDate> findPostStaffingBySchoolId(Integer schoolId) {
        return teacherDao.findPostStaffingBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findNumberOfGradeBySchoolId(Integer schoolId,
                                                           String schoolYear) {
        return teacherDao.findNumberOfGradeBySchoolId(schoolId, schoolYear);
    }

    @Override
    public List<StatisticDate> findSalaryBySchoolId(Integer schoolId,
                                                    String year, String month) {
        return teacherDao.findSalaryBySchoolId(schoolId, year, month);
    }

    @Override
    public List<StatisticDate> findQualificationBySchoolId(Integer schoolId) {
        return teacherDao.findQualificationBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findIdCardTypeBySchoolId(Integer schoolId) {
        return teacherDao.findIdCardTypeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findMarriageBySchoolId(Integer schoolId) {
        return teacherDao.findMarriageBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findJobStateBySchoolId(Integer schoolId) {
        return teacherDao.findJobStateBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findSexBySchoolId(Integer schoolId) {
        return teacherDao.findSexBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findAgeBySchoolId(Integer schoolId) {
        return teacherDao.findAgeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findPoliticalStatusBySchoolId(Integer schoolId) {
        return teacherDao.findPoliticalStatusBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findRaceBySchoolId(Integer schoolId) {
        return teacherDao.findRaceBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findResidenceTypeBySchoolId(Integer schoolId) {
        return teacherDao.findResidenceTypeBySchoolId(schoolId);
    }

    @Override
    public List<StatisticDate> findNumberOfSchoolByAreaCode(String areaCode) {
        return teacherDao.findNumberOfSchoolByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findSalaryByAreaCode(String areaCode,
                                                    String year, String month) {
        return teacherDao.findSalaryByAreaCode(areaCode, year, month);
    }

    @Override
    public List<StatisticDate> findPostStaffingByAreaCode(String areaCode) {
        return teacherDao.findPostStaffingByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findQualificationByAreaCode(String areaCode) {
        return teacherDao.findQualificationByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findIdCardTypeByAreaCode(String areaCode) {
        return teacherDao.findIdCardTypeByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findMarriageByAreaCode(String areaCode) {
        return teacherDao.findMarriageByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findJobStateByAreaCode(String areaCode) {
        return teacherDao.findJobStateByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findSexByAreaCode(String areaCode) {
        return teacherDao.findSexByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findAgeByAreaCode(String areaCode) {
        return teacherDao.findAgeByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findPoliticalStatusByAreaCode(String areaCode) {
        return teacherDao.findPoliticalStatusByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findRaceByAreaCode(String areaCode) {
        return teacherDao.findRaceByAreaCode(areaCode);
    }

    @Override
    public List<StatisticDate> findResidenceTypeByAreaCode(String areaCode) {
        return teacherDao.findResidenceTypeByAreaCode(areaCode);
    }
    //-----------------????????????  end-----------------------


    @Override
    public List<TeacherVo> findIncrementDataByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer teacherId, Boolean isGetNew) {
        return this.teacherDao.findIncrementDataByModifyDate(schoolId, isDeleted, modifyDate, teacherId, isGetNew);
    }

    @Override
    public List<TeacherVo> getAllSubjectTeachersByTeamId(Integer teamId) {
        return this.teacherDao.findAllSubjectTeachersByTeamId(teamId);
    }

    /**
     * @Date: 2020/9/28 15:46 ?????? yhc
     * @param name
     * @param sex
     * @param alias
     * @param phone
     * @param department
     * @param position
     * @param schoolId
     * @param appid
     * @param teacherType
     * @param list ?????????????????????????????????
     * @return
     */
    @Override
    public Map<String, Object> addTeacherFromExcelImport(String gh,String kh,String name, String sex, String alias, String phone,
                                                         String department, String position, Integer schoolId, Integer appid,
                                                         String teacherType, List<EmployeeList> list) {
        Map<String, Object> entity = DataImportCheck.checkTeacherData(name, alias, phone, department, position, sex);


        Integer status = (Integer) entity.get("status");
        if (status - 2 == 0) {
            return entity;
        }
        boolean checkStatus = checkTeacherIsRepeat(phone, schoolId);
        if (!checkStatus) {
            entity.put("status", 2);
            entity.put("errorFiled", "phone");
            entity.put("errorInfo", "??????????????????");
            return entity;
        }
        checkStatus = checkAliasIsRepeat(name, alias, schoolId);

        if (!checkStatus) {
            if (alias == null || "".equals(alias)) {
                entity.put("errorFiled", "name");
                entity.put("errorInfo", "????????????????????????");
            } else {
                entity.put("errorFiled", "alias");
                entity.put("errorInfo", "??????????????????");
            }
            entity.put("status", 2);
            return entity;
        }

        if(StringUtils.isEmpty(gh) || basicSQLService.findUniqueLong("select exists(select 1 from pj_teacher where is_delete=0 and emp_code='"+gh+"' ) e")>0){
            entity.put("status", 2);
            entity.put("errorFiled", "gh");
            entity.put("errorInfo", "????????????");
            return entity;
        }

        if(StringUtils.isEmpty(kh) || kh.length()!=10){
            entity.put("status", 2);
            entity.put("errorFiled", "kh");
            entity.put("errorInfo", "?????????????????????10???");
            return entity;
        }



        if(StringUtils.isEmpty(gh) || basicSQLService.findUniqueLong("select exists(select 1 from pj_teacher where is_delete=0 and emp_card='"+kh+"' ) e")>0){
            entity.put("status", 2);
            entity.put("errorFiled", "kh");
            entity.put("errorInfo", "????????????");
            return entity;
        }



        User user = null;
        Date now = new Date();
        /**??????????????????UserBinding??????*/
        boolean ubRecord = false;
        /**??????????????????SchoolUser??????*/
        boolean suRecord = false;

        List<UserBinding> userBindingList = userBindingService.findUserListByBindingName(phone);
        log.debug("userBindingList" + userBindingList != null ? "userBindingList is--------------------:null" : "userBindingList size is--------------------------:" + String.valueOf(userBindingList.size()));
        if (userBindingList != null || userBindingList.size() > 0) {
            for (UserBinding userBinding : userBindingList) {
                SchoolUserCondition condition = new SchoolUserCondition();
                condition.setUserId(userBinding.getUserId());
                condition.setSchoolId(schoolId);
                condition.setUserType(teacherType);
                condition.setIsDeleted(false);
                List<SchoolUser> result = schoolUserService.findSchoolUserByCondition(condition, null, null);
                if (result.size() > 0) {
                    if (!ubRecord) {
                        ubRecord = true;
                        user = userService.findUserById(userBinding.getUserId());
                    }
                }

                for (SchoolUser schoolUser : result) {
                    if (teacherType.equals(schoolUser.getUserType())) {
                        if (!suRecord) {
                            suRecord = true;
                        }
                    }
                }
            }
        }

        if (userBindingList == null || userBindingList.size() == 0 || !ubRecord) {
            log.debug("userBIndList ----------------------null-----------------!ubrecord");
            /**??????????????????*/
            Role role = roleService.findTeacherRoleBySchoolIdAndAppid(schoolId, appid);
            log.debug("role is studnets schooleId" + schoolId);
            /**?????????????????????*/
            user = userService.addUserFromAccount(schoolId, appid, GroupContants.TYPE_SCHOOL);
            /**?????????????????????????????????*/
            userRoleService.addUserRole(user, role);

            /**???????????????????????????*/
            UserBinding userBinding = new UserBinding();
            userBinding.setBindingName(phone);
            userBinding.setBindingType(1);
            userBinding.setCreateDate(now);
            userBinding.setEnabled(true);
            userBinding.setIsDeleted(false);
            userBinding.setModifyDate(now);
            userBinding.setUserId(user.getId());
            userBindingService.addUserBinding(userBinding);
        }

        Integer userId = user.getId();
        String username = user.getUserName();
        /**????????????*/
        Teacher teacher = new Teacher();
        teacher.setSchoolId(schoolId);
        teacher.setUserId(userId);
        teacher.setUserName(username);
        if (alias != null && !"".equals(alias)) {
            name = alias;
        }
        teacher.setName(name);
        teacher.setAlias(alias);
        teacher.setSex(getSex(sex));
        teacher.setPosition(position);
        teacher.setMobile(phone);
        teacher.setIsDelete(false);
        teacher.setCreateDate(now);
        teacher.setModifyDate(now);
        teacher.setEmpCode(gh);
        teacher.setEmpCard(kh);
        teacherDao.create(teacher);
        log.debug("Tearcherdao----------???" + teacher.getName());

        Integer teacherid = teacher.getId();


        if (!suRecord) {
            /**????????????????????????*/
            SchoolUser schoolUser = new SchoolUser();
            schoolUser.setAlias(alias);
            schoolUser.setCreateDate(now);
            schoolUser.setInSchool(true);
            schoolUser.setIsDeleted(false);
            schoolUser.setModifyDate(now);
            schoolUser.setName(name);
            schoolUser.setSchoolId(schoolId);
            schoolUser.setUserId(userId);
            schoolUser.setUserName(username);
            schoolUser.setUserType(teacherType);
            schoolUser.setOwnerId(teacherid);
            schoolUserService.addSchoolUser(schoolUser);
            log.debug("schoolserverImapl=============>" + schoolUser.getName());
            boolean addRole = false;
            /**??????????????????*/
            Role role = roleService.findTeacherRoleBySchoolIdAndAppid(schoolId, appid);
            log.debug("role ===========>" + role.getName());
            Integer roleId = role.getId();

            List<Integer> roles = userRoleDao.findRoleIdsByUserId(userId, appid);
            log.debug("roles ===========>" + roles.size());
            for (Integer tmpId : roles) {
                if (roleId - tmpId == 0) {
                    addRole = true;
                    break;
                }
            }

            if (!addRole) {
                /**?????????????????????????????????*/
                userRoleService.addUserRole(user, role);
            }

        }
        /**
         * ??????????????????
         */
        // ????????????
        EmployeeList employeeList=new EmployeeList();
        employeeList.setEmp_name(name);
        //list.add(new DetailVo("emp_name", name, "String"));
        // ????????????
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
		//list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
//		// ??????
        employeeList.setEmp_code(gh);
		//list.add(new DetailVo("emp_code", gh, "String"));
        // ????????????
        employeeList.setEmp_card(kh);
       // list.add(new DetailVo("emp_card", kh, "String"));
        //?????????????????? userId
        employeeList.setEmp_pycode(String.valueOf(userId));
        //??????????????? userName
        employeeList.setEmp_idcard(username);


//        /**
//         * ??????????????????
//         */
//        if (username!=null && username.equals("") && alias!=null && alias.equals("")){
//            String usernameAlias=username+alias;
//            hikvisionPerson.setPersonName(usernameAlias);
//        }
//        else {
//            hikvisionPerson.setPersonName(username);
//        }
//        hikvisionPerson.setOrgIndexCode(schoolId+","+department);
//        hikvisionPerson.setGenderNum(Integer.valueOf(sex));
//        hikvisionPerson.setPhoneNo(phone);
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        hikvisionPerson.setFaceData(base64Encoder.encode(hikvisionPerson.getPersonName().getBytes()));

        log.debug("departemnt==============>" + department.isEmpty());
        if (department != null && !"".equals(department)) {
            Department depart = departmentService.findDepartmentByNameAndSchoolId(department, schoolId);

            if (depart == null) {
                log.debug("depart===================>null");
                depart = new Department();
                depart.setCreateDate(now);
                depart.setIsDelete(false);
                depart.setLevel(0);
                depart.setListOrder(0);
                depart.setMemberCount(0);
                depart.setModifyDate(now);
                depart.setName(department);
                depart.setParentId(0);
                depart.setSchoolId(schoolId);
                departmentService.add(depart);
                // ?????????????????????

                employeeList.setDept_code("000001");
                employeeList.setDept_name("????????????");
                //list.add(new DetailVo("dept_code", "000001", "String"));
                //list.add(new DetailVo("dept_name", "????????????", "String"));
            } else {
                // ??????id
                employeeList.setDept_code("000001");
                employeeList.setDept_name(department);
                //list.add(new DetailVo("dept_code", "000001", "String"));
                // ????????????
             //   list.add(new DetailVo("dept_name", department, "String"));
            }
            DepartmentTeacher departmentTeacher = new DepartmentTeacher();
            departmentTeacher.setCreateDate(now);
            departmentTeacher.setDepartmentId(depart.getId());
            departmentTeacher.setDepartmentName(department);
            departmentTeacher.setIsDeleted(false);
            departmentTeacher.setModifyDate(now);
            departmentTeacher.setOrderNumber(0);
            departmentTeacher.setSchoolId(schoolId);
            departmentTeacher.setTeacherId(teacherid);
            departmentTeacher.setTeacherName(name);
            departmentTeacherService.add(departmentTeacher);
            log.debug("d==============>" + departmentTeacher.getDepartmentName());
        } else {
            // ?????????????????????
            employeeList.setDept_code("000001");
            employeeList.setDept_name("????????????");
        }
        list.add(employeeList);
        entity.put("teacherId", teacherid);

        return entity;
    }

    private String getSex(String sex) {
        String sextmp = "0";
        if (sextmp != null) {
            if ("???".equals(sex)) {
                sextmp = "1";
            } else if ("???".equals(sex)) {
                sextmp = "2";
            }
        }
        return sextmp;
    }

    @Override
    public boolean checkTeacherIsRepeat(String phone, Integer schoolId) {
        TeacherCondition condition = new TeacherCondition();
        condition.setMobile(phone);
        condition.setSchoolId(schoolId);
        List<Teacher> result = teacherDao.findTeacherByCondition(condition);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAliasIsRepeat(String name, String alias, Integer schoolId) {
        List<Teacher> result = null;

        TeacherCondition condition = new TeacherCondition();
        condition.setSchoolId(schoolId);
        if (alias == null || "".equals(alias)) {
            alias = "";
        } else {
            condition.setAlias(alias);
            result = teacherDao.findTeacherByCondition(condition);
            if (result.size() > 0) {
                return false;
            }
        }

        condition.setName(name);
        result = teacherDao.findTeacherByCondition(condition);
        for (Teacher teacher : result) {
            String tmp = teacher.getAlias();
            if (tmp == null) {
                tmp = "";
            }
            if (alias.equals(tmp)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteTeacherAllInfoById(Integer id, String teacherType) {
        Teacher teacher = teacherDao.findById(id);
        if (teacher != null) {
            Integer userid = teacher.getUserId();

            /**??????schoolUser??????*/
            SchoolUser schoolUser = null;

            /**??????userid??????schoolUser*/
            List<SchoolUser> schoolUserList = schoolUserService.findSchoolUserListByUserId(userid);
            /**????????????????????????*/
            boolean other = false;
            for (SchoolUser tmp : schoolUserList) {
                /**?????????????????????????????????*/
                if (!teacherType.equals(tmp.getUserType())) {
                    other = true;
                } else {
                    /**????????????*/
                    schoolUser = tmp;
                }
            }

            /**?????? parent*/
            Parent parent = parentService.findUniqueByUserId(userid);
            /**?????????????????????????????????????????????(??????????????????,??????????????????????????????????????????schoolUser??????)??????user???????????????*/
            if (!other && parent == null) {
                userService.removeAllById(userid, true);
            }

            Date now = new Date();
            if (schoolUser != null) {
                schoolUser.setIsDeleted(true);
                schoolUser.setModifyDate(now);
                schoolUserService.modify(schoolUser);
            }
            //??????????????????
            /**??????????????????*/
            Role role = roleService.findTeacherRoleBySchoolIdAndAppid(teacher.getSchoolId(), 1);
            UserRole userRole = userRoleService.findByUserIdAndRoleId(userid, role.getId());
            if (userRole != null) {
                userRole.setIsDeleted(true);
                userRole.setModifyDate(now);
                userRoleService.modify(userRole);
            }
            //??????????????????
            teacher.setIsDelete(true);
            teacher.setModifyDate(now);
            teacherDao.update(teacher);

            teamTeacherService.removeByTeacherId(id);

            subjectTeacherService.removeByTeacherId(id);

            syllabusService.removeByTeacher(id);

            teamUserService.removeTeacherByUserId(userid);

            departmentTeacherService.removeByTeacherId(id);

            return true;
        } else {
            return false;
        }

    }

    public TeacherSortService getTeacherSortService() {
        return teacherSortService;
    }

    public void setTeacherSortService(TeacherSortService teacherSortService) {
        this.teacherSortService = teacherSortService;
    }


    @Override
    public List<Teacher> findNotSendSeewo(){
        return teacherDao.findNotSendSeewo();
    }

    @Override
    public List<Teacher> findCanSendSeewo() {
        return teacherDao.findCanSendSeewo();
    }

    @Override
    public boolean updateSeewoStatusByIds(Integer[] ids) {
        Teacher t=new Teacher();
        t.setIsSendSeewo(true);
        return teacherDao.updateByIds(t,ids)>0;
    }

    @Override
    public List<Teacher> findTeacherByNoSendCanteen(Teacher teacherVo) {

        return teacherDao.findTeacherByNoSendCanteen(teacherVo);
    }
    @Override
    public List<TeacherApiVo> findTeacherApiVoByCondition(TeacherCondition teacherCondition, Page page, Order order) {
        return this.teacherDao.findTeacherApiVoByCondition(teacherCondition, page, order);
    }

    @Override
    public List<Map<String, Object>> findStuByTeacherId(Integer schoolId, Integer teacherId, String schoolYear) {
        return teacherDao.findStuByTeacherId(schoolId, teacherId, schoolYear);
    }

    @Override
    public void updateTeacherSendCanteen(Teacher teacher) {
         teacherDao.updateTeacherSendCanteen(teacher);
    }

    @Override
    public List<TeacherVo> findByUser(String name, Integer state, String userName, String modbie, Page page) {
        return teacherDao.findByUser( name,  state,  userName,  modbie,  page);
    }


}
