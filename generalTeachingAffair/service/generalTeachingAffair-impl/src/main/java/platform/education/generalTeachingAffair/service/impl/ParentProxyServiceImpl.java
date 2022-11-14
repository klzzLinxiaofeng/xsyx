package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.dao.TeamUserDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.contants.GroupContants;
import platform.education.user.contants.UserBindingContants;
import platform.education.user.contants.UserContants;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.model.UserRole;
import platform.education.user.service.*;
import platform.education.user.vo.RoleCondition;
import platform.education.user.vo.UserBindingCondition;
import platform.education.user.vo.UserCondition;
import platform.education.user.vo.UserResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParentProxyServiceImpl implements ParentProxyService {

    // private Logger log = LoggerFactory.getLogger(getClass());

    private ParentService parentService;
    private ParentStudentService parentStudentService;
    // private StudentService studentService;
    private UserService userService;
    private UserBindingService userBindingService;
    private TeamService teamService;
    private GradeService gradeService;

    @Autowired
    private BasicSQLService basicSQLService;

    private SchoolService schoolService;
    private UserRoleService userRoleService;
    private RoleService roleService;
    private SchoolUserService schoolUserService;

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    private TeamUserDao teamUserDao;

    public void setTeamUserDao(TeamUserDao teamUserDao) {
        this.teamUserDao = teamUserDao;
    }

    /**
     * 个人档案表，2015-11-21
     */
    private PersonService personService;

    /**
     * 学校用户组，2015-11-21
     */
    private GroupUserService groupUserService;

    // public void setTeacherService(TeacherService teacherService) {
    // this.teacherService = teacherService;
    // }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setSchoolUserService(SchoolUserService schoolUserService) {
        this.schoolUserService = schoolUserService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public ParentProxyServiceImpl() {
    }

    public GradeService getGradeService() {
        return gradeService;
    }

    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    public void setParentStudentService(ParentStudentService parentStudentService) {
        this.parentStudentService = parentStudentService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserBindingService(UserBindingService userBindingService) {
        this.userBindingService = userBindingService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public ParentService getParentService() {
        return parentService;
    }

    public ParentStudentService getParentStudentService() {
        return parentStudentService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserBindingService getUserBindingService() {
        return userBindingService;
    }

    public TeamService getTeamService() {
        return teamService;
    }

    public GroupUserService getGroupUserService() {
        return groupUserService;
    }

    public void setGroupUserService(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    /**
     * 查找家长信息，分页以学生信息为主
     */
    @Override
    public List<StudentMergeParentVo> findParentByStudent(ParentVo parentvo,
                                                          Page page, Order order) throws Exception {
        List<StudentMergeParentVo> studentMergeParentVoList = new ArrayList<StudentMergeParentVo>();// 定义返回结果集
        List<Student> studentList = null;
        if ((parentvo != null && parentvo.getMobile() != null && !"".equals(parentvo.getMobile().trim())) ||
				(parentvo != null && parentvo.getLicensePlate() != null && !"".equals(parentvo.getLicensePlate().trim()))
		) {// 当家长电话号码查询时
            ParentCondition parentCondition = new ParentCondition();
            parentCondition.setSchoolId(parentvo.getSchoolId());
            parentCondition.setSchoolYear(parentvo.getSchoolYear());
            parentCondition.setGradeId(parentvo.getGradeId());
            parentCondition.setTeamId(parentvo.getTeamId());
            //parentCondition.setStudentId(parentvo.getStudentId());
            parentCondition.setStudentName(parentvo.getStudentName());
            parentCondition.setStudentUserId(parentvo.getStudentUserId());
            parentCondition.setParentMobile(parentvo.getMobile());
            parentCondition.setParentMobileLike(true);
            parentCondition.setLicensePlate(parentvo.getLicensePlate());
            // 查询学生的信息，但是根据家长条件查询
            studentList = this.studentDao.findStudentByParentOrStuName(parentCondition, page, null);
            //studentList = this.studentDao.findStudentByParent(parentCondition, page, null);
        } else {
            StudentCondition studentCondition = new StudentCondition();// 分页以查询学生结果为主
            studentCondition.setSchoolId(parentvo.getSchoolId());
            studentCondition.setSchoolYear(parentvo.getSchoolYear());
            studentCondition.setGradeId(parentvo.getGradeId());
            studentCondition.setTeamId(parentvo.getTeamId());
            //studentCondition.setId(parentvo.getStudentId());
            studentCondition.setName(parentvo.getStudentName());
            studentCondition.setUserId(parentvo.getStudentUserId());// 添加查询条件
            // 只是查询的学生信息
            studentList = this.studentDao.findStudentHasTeamByCondition(studentCondition, page, order);
        }

        for (Student student : studentList) {// 查询每个学生家长的信息集合
            ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
            StudentMergeParentVo studentMergeParentVo = new StudentMergeParentVo();
            studentMergeParentVo.setStudentId(student.getId());
            studentMergeParentVo.setStudentName(student.getName());
            studentMergeParentVo.setStudentNumber(student.getStudentNumber());
            studentMergeParentVo.setTeamId(student.getTeamId());
            studentMergeParentVo.setTeamName(student.getTeamName());
            studentMergeParentVo.setSchoolId(student.getSchoolId());
            List<ParentVo> parentVoList = new ArrayList<ParentVo>();

            parentStudentCondition.setStudentUserId(student.getUserId());

            List<ParentStudent> parentstudentList = this.parentStudentService.findParentStudentByCondition(parentStudentCondition, order);
            for (ParentStudent parentStudent : parentstudentList) {
                ParentVo parentVo = new ParentVo();
                ParentCondition parentCondition = new ParentCondition();
                parentCondition.setUserId(parentStudent.getParentUserId());

                List<Parent> parentList = null;

                parentVo.setUserId(parentStudent.getParentUserId());

                if (parentvo != null && parentvo.getMobile() != null && !"".equals(parentvo.getMobile().trim())) {// 当家长电话号码查询时
                    parentCondition.setMobile(parentvo.getMobile());
                    parentCondition.setParentMobileLike(true);
                    parentList = parentService.findParentByCondition(parentCondition);

                    if (parentList == null || parentList.size() < 1) {
                        continue;
                    }
                } else {
                    parentList = parentService.findParentByCondition(parentCondition);
                    if (parentList.size() > 1) {
                        throw new Exception("根据用户账号找到的家长信息不唯一");
                    }
                    if (parentList.size() < 1) {
                        throw new Exception("根据用户账号找到的家长信息不存在");
                    }
                }

                Parent parent = parentList.get(0);
                parentVo.setId(parent.getId());
                parentVo.setName(parent.getName());
                parentVo.setUserName(parent.getUserName());
                parentVo.setParentRelation(parentStudent.getParentRelation());
                parentVo.setRank(parentStudent.getRank());
                parentVo.setEmail(parent.getEmail());
                parentVo.setMobile(parent.getMobile());
				parentVo.setLicensePlate(parent.getLicensePlate());

                parentVoList.add(parentVo);

            }
            studentMergeParentVo.setParentVoList(parentVoList);
            studentMergeParentVoList.add(studentMergeParentVo);
        }

        List<StudentMergeParentVo> studentMergeParentVoListEnd = new ArrayList<StudentMergeParentVo>();//
        for (StudentMergeParentVo studentMergeParentVo : studentMergeParentVoList) {
            Student stu = this.studentDao.findById(studentMergeParentVo.getStudentId());
            if (stu.getTeamId() == null || stu.getTeamId() < 1) {

            } else {
                studentMergeParentVo.setStudentParentNum(studentMergeParentVo.getParentVoList() != null ? studentMergeParentVo.getParentVoList().size() : 0);
                studentMergeParentVoListEnd.add(studentMergeParentVo);
            }
        }
        return studentMergeParentVoListEnd;
    }

    @Override
    public List<ParentVo> findParentByStudentUserId(Integer studentUserId) {
        List<ParentVo> parentVoList = new ArrayList<ParentVo>();
        ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
        parentStudentCondition.setStudentUserId(studentUserId);
        List<ParentStudent> parentStudentList = this.parentStudentService
                .findParentStudentByCondition(parentStudentCondition);
        if (parentStudentList == null || parentStudentList.size() < 1) {
            parentStudentList = new ArrayList<ParentStudent>();
        }
        for (ParentStudent parentStudent : parentStudentList) {

            ParentVo parentVo = new ParentVo();
            parentVo.setStudentUserId(parentStudent.getStudentUserId());
            parentVo.setStudentName(parentStudent.getStudentName());
            parentVo.setUserId(parentStudent.getParentUserId());
            parentVo.setParentRelation(parentStudent.getParentRelation());
            parentVo.setRank(parentStudent.getRank());
            Parent parent = parentService.findUniqueByUserId(parentStudent
                    .getParentUserId());
            if (parent != null && parent.getId() > 0) {
                parentVo.setId(parent.getId());
                parentVo.setUserName(parent.getUserName());
                parentVo.setMobile(parent.getMobile());
                parentVo.setEmail(parent.getEmail());
                parentVo.setPersonId(parent.getPersonId());
            }
            parentVoList.add(parentVo);
        }

        return parentVoList;

    }

    @Override
    public void deleteParentByStuAndParent(ParentVo parentVo) throws Exception {
        // 删除中间关联表信息
        ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
        parentStudentCondition.setParentUserId(parentVo.getUserId());
        // parentStudentCondition.setStudentUserId(parentVo.getStudentUserId());
        List<ParentStudent> parStuList = this.parentStudentService
                .findParentStudentByCondition(parentStudentCondition);
        int deleteParentStudent = 0;
        if (parStuList != null && parStuList.size() > 0) {
            for (ParentStudent parentStudent : parStuList) {
                if (parentStudent.getStudentUserId() != null
                        && parentVo.getStudentUserId() != null
                        && parentStudent.getStudentUserId().equals(
                        parentVo.getStudentUserId())) {
                    ParentStudent parentStudent2 = new ParentStudent();
                    parentStudent2.setId(parStuList.get(0).getId());
                    this.parentStudentService.remove(parentStudent2);
                    deleteParentStudent++;
                } else {

                }
            }

            if (deleteParentStudent == 1) {

            } else {
                throw new Exception("家长学生关系数据有问题");
            }

        } else {
            throw new Exception("家长学生关系数据有问题");
        }

        if (parStuList != null && parStuList.size() == 1
                && deleteParentStudent == 1) {// 当关系表不存在对应的关系时候，删除家长信息
            // 删除家长信息
            Parent parent = new Parent();
            parent.setId(parentVo.getId());
            parentService.remove(parent);
        } else {

        }

    }

    @Override
    public List<ParentVo> findStudentByParent(Integer parentUserId, Page page,
                                              Order order) throws Exception {
        List<ParentVo> parentVoList = new ArrayList<ParentVo>();
        ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
        parentStudentCondition.setParentUserId(parentUserId);
        List<ParentStudent> parentStudentList = this.parentStudentService
                .findParentStudentByCondition(parentStudentCondition, page,
                        order);
        if (parentStudentList != null && parentStudentList.size() > 0) {

            for (ParentStudent parentStudent : parentStudentList) {

                StudentCondition studentCondition = new StudentCondition();
                studentCondition.setUserId(parentStudent.getStudentUserId());
                List<Student> studentList = this.studentDao
                        .findStudentHasTeamByCondition(studentCondition, null,
                                null);
                if (studentList != null && studentList.size() > 0) {
                    for (Student student : studentList) {
                        ParentVo parentVo = new ParentVo();
                        parentVo.setUserId(parentStudent.getParentUserId());
                        parentVo.setRank(parentStudent.getRank());
                        parentVo.setParentRelation(parentStudent
                                .getParentRelation());
                        parentVo.setStudentId(student.getId());
                        parentVo.setStudentUserId(student.getUserId());
                        parentVo.setStudentUserName(student.getUserName());
                        parentVo.setStudentName(student.getName());
                        parentVo.setTeamId(student.getTeamId());
                        parentVo.setTeamName(student.getTeamName());

                        parentVo.setSchoolId(student.getSchoolId());
                        School school = this.schoolService
                                .findSchoolById(student.getSchoolId());
                        if (school != null && school.getId() > 0) {
                            parentVo.setSchoolName(school.getName());
                        }
                        parentVoList.add(parentVo);
                    }
                }
            }
        }

        return parentVoList;
    }

    /**
     * 导出家长信息数据服务
     */
    @Override
    public List<ParentVo> findDownLoadParentByStudent(ParentVo parentvo,
                                                      Page page, Order order) throws Exception {
        List<ParentVo> parentVoListEnd = new ArrayList<ParentVo>();// 定义返回结果集

        StudentCondition studentCondition = new StudentCondition();// 分页以查询学生结果为主

        studentCondition.setSchoolId(parentvo.getSchoolId());
        studentCondition.setSchoolYear(parentvo.getSchoolYear());
        studentCondition.setGradeId(parentvo.getGradeId());
        studentCondition.setTeamId(parentvo.getTeamId());
        studentCondition.setId(parentvo.getStudentId());

        List<Student> studentList = this.studentDao
                .findStudentHasTeamByCondition(studentCondition, null, null);

        for (Student student : studentList) {// 查询每个学生家长的信息集合

            ParentStudentCondition parentStudentCondition = new ParentStudentCondition();

            parentStudentCondition.setStudentUserId(student.getUserId());
            List<ParentStudent> parentstudentList = this.parentStudentService
                    .findParentStudentByCondition(parentStudentCondition);

            for (ParentStudent parentStudent : parentstudentList) {

                ParentVo vo = new ParentVo();
                vo.setStudentId(student.getId());
                vo.setStudentName(student.getName());
                vo.setStudentNumber(student.getStudentNumber());
                vo.setSchoolYear(parentvo.getSchoolYear());
                vo.setGradeId(parentvo.getGradeId());
                vo.setTeamId(parentvo.getTeamId());

                Team team = this.teamService.findTeamById(student.getTeamId());
                if (team != null && team.getId() > 0) {
                    vo.setTeamName(student.getTeamName());
                }
                Grade grade = this.gradeService
                        .findGradeById(team.getGradeId());
                if (grade != null && grade.getId() > 0) {
                    vo.setGradeName(grade.getName());
                }

                vo.setParentRelation(parentStudent.getParentRelation());
                vo.setRank(parentStudent.getRank());

                ParentCondition parentCondition = new ParentCondition();
                parentCondition.setUserId(parentStudent.getParentUserId());
                List<Parent> parentList = parentService
                        .findParentByCondition(parentCondition);
                if (parentList.size() > 1) {
                    vo.setErrorInfo("根据用户账号'" + parentStudent.getParentUserId()
                            + "'找到的家长信息不唯一");
                    throw new Exception("根据用户账号'"
                            + parentStudent.getParentUserId() + "'找到的家长信息不唯一");
                }
                if (parentList.size() < 1) {
                    vo.setErrorInfo("根据用户账号'" + parentStudent.getParentUserId()
                            + "'找到的家长信息不存在");
                    throw new Exception("根据用户账号'"
                            + parentStudent.getParentUserId() + "'找到的家长信息不存在");
                }

                Parent parent = parentList.get(0);
                vo.setId(parent.getId());
                vo.setName(parent.getName());
                vo.setUserName(parent.getUserName());
                vo.setEmail(parent.getEmail());
                vo.setMobile(parent.getMobile());
                parentVoListEnd.add(vo);
            }

        }

        return parentVoListEnd;
    }

    /**
     * 校验家长信息是否正确
     *
     * @param parentvo
     * @return
     */
    private boolean validataAddParent(ParentVo parentvo) {
        boolean result = true;
        if (parentvo != null) {
            Integer schoolId = parentvo.getSchoolId();
            Integer system_app_id = parentvo.getSystem_app_id();
            Integer studentId = parentvo.getStudentId();
            // Integer roleId = parentvo.getRoleId();
            String name = parentvo.getName();
            String mobile = parentvo.getMobile();
            // String email = parentvo.getEmail();
            String parentRelation = parentvo.getParentRelation();
            String rank = parentvo.getRank();

            result = validataInt(result, schoolId);// 校验学校Id，必填
            result = validataInt(result, system_app_id);// 校验appId，必填
            result = validataInt(result, studentId);// 校验学生Id，必填
            // 校验家长角色roleId，非必填
            result = validataString(result, name);// 校验家长姓名，必填
            result = validataString(result, mobile);// 校验家长手机号，必填

            result = checkMobile(mobile);
            // 校验家长邮箱，非必填
            result = validataString(result, parentRelation);// 校验学生与家长关系,必填
            result = validataString(result, rank);// 校验家长关系类别,必填，0或者1

        } else {
            result = false;
        }

        return result;
    }

    /**
     * 校验数字是否有值
     *
     * @param result
     * @param number
     * @return
     */
    private boolean validataInt(boolean result, Integer number) {
        if (number != null && number > 0) {

        } else {
            result = false;
        }
        return result;
    }

    /**
     * 校验字符串是否有值
     *
     * @param result
     * @param string
     * @return
     */
    private boolean validataString(boolean result, String string) {
        if (string != null && !string.trim().isEmpty()) {

        } else {
            result = false;
        }
        return result;
    }

    @Override
    public ParentVo add(ParentVo parentvo) throws Exception {

        if (!validataAddParent(parentvo)) {
            throw new Exception("添加家长信息参数异常");
        }

        String mobile = parentvo.getMobile();
        Integer userid = null;
        ParentVo parentvoend = null;
        if (mobile == null || "".equals(mobile)) {

        } else {
            userid = this.userBindingService.findUserId(mobile);
        }

        User user = null;
        // //////////拿到用户账号的信息///begin///////////////
        if (userid == null || userid < 0) {

            User userTemp = new User();
            userTemp.setPassword(UserCondition.DEFAULT_PARENT_PASSWORD);
            UserResult result = this.userService
                    .addUser(userTemp, parentvo.getSchoolId(),
                            GroupContants.TYPE_SCHOOL, mobile,
                            UserBindingContants.TYPE_PHONE,
                            parentvo.getSystem_app_id());
            if (result != null) {
                user = result.getUser();
            }

            if (user == null || user.getId() < 0) {
                throw new Exception("添加用户异常");
            }

            UserRole userRole = new UserRole();
            Role role = new Role();
            if (parentvo != null
                    && (parentvo.getRoleId() == null || "".equals(parentvo
                    .getRoleId()))) {
                RoleCondition roleCondition = new RoleCondition();
                roleCondition.setUserType(ParentVo.roleType);
                roleCondition.setDefaultRole(false);
                List<Role> roleList = this.roleService.findRoleByCondition(
                        roleCondition, null, null);
                if (roleList != null && roleList.size() > 0) {
                    role.setId(roleList.get(0).getId());
                }
            } else {
                role.setId(parentvo.getRoleId());
            }

            role.setCreateDate(new Date());

            userRole.setUser(user);
            userRole.setRole(role);
            if (user != null && user.getId() != null && role != null
                    && role.getId() != null) {

            } else {

                throw new Exception("添加角色入参异常");
            }

            UserRole ur = this.userRoleService.add(userRole);
            if (ur == null) {
                throw new Exception("添加角色异常");
            }
            userid = user.getId();

        } else {
            user = this.userService.findUserById(userid);
            userid = user.getId();
        }
        // //////////拿到用户账号的信息///end///////////////
        // //////////添加家长信息//start///////////////
        Parent addParent = new Parent();
        Parent parentadd = new Parent();

        ParentCondition parentCondition = new ParentCondition();
        parentCondition.setUserId(userid);
        // parentCondition.setName(parentvo.getName());
        parentCondition.setMobile(parentvo.getMobile());

        List<Parent> parentList = parentService
                .findParentByCondition(parentCondition);

        if (parentList.size() > 0) {
            parentadd = parentList.get(0);
            if (parentList.size() == 1) {
                if (parentvo.getName() != null && parentadd.getName() != null
                        && parentadd.getName().equals(parentvo.getName())) {

                } else {
                    throw new Exception("电话号码重复不能继续添加");
                }
            }
            // throw new Exception("家长信息已经存在不能继续添加");
        } else {

            addParent.setUserId(user.getId());
            addParent.setUserName(user.getUserName());

            addParent.setPersonId(1);
            addParent.setName(parentvo.getName());
            addParent.setMobile(parentvo.getMobile());
            addParent.setEmail(parentvo.getEmail());

            ParentCondition parentCondition2 = new ParentCondition();
            parentCondition2.setUserId(user.getId());
            parentCondition2.setUserName(user.getUserName());

            List<Parent> parList = parentService
                    .findParentByCondition(parentCondition);

            if (parList != null && parList.size() == 1) {
                parentadd = parList.get(0);
            } else if (parList != null && parList.size() > 1) {

            } else {
                parentadd = parentService.add(addParent);
                if (parentadd != null && parentadd.getId() > 0) {

                } else {
                    parentadd = new Parent();
                }
                SchoolUser schoolUser = new SchoolUser();

                // schoolUser.setUserType(SchoolUserCondition.DEFAULT_PARENT_USERTYPE);
                schoolUser.setUserType(String
                        .valueOf(SchoolUserCondition.DEFAULT_PARENT_USERTYPE));
                schoolUser.setName(parentvo.getName());
                schoolUser
                        .setOwnerId(SchoolUserCondition.DEFAULT_PARENT_OWER_ID);
                schoolUser.setSchoolId(parentvo.getSchoolId());
                schoolUser.setUserId(user.getId());
                schoolUser.setCreateDate(new Date());
                schoolUser.setModifyDate(new Date());
                this.schoolUserService.add(schoolUser);

            }
        }

        // //////////添加家长信息//end///////////////
        // //////////添加家长学生关系信息//end///////////////
        ParentStudent addparentStudent = new ParentStudent();
        Student student = this.studentDao.findById(parentvo.getStudentId());

        if (student == null || student.getId() == null || student.getId() < 0) {
            throw new Exception("不存在对应的学生，不能添加家长信息");
        } else {
            ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
            parentStudentCondition.setParentUserId(parentadd.getUserId());
            parentStudentCondition.setStudentUserId(student.getUserId());
            parentStudentCondition.setIsDelete(false);
            List<ParentStudent> parStuList = this.parentStudentService
                    .findParentStudentByCondition(parentStudentCondition);
            if (parStuList != null && parStuList.size() > 0) {

            } else {
                addparentStudent.setStudentUserId(student.getUserId());
                addparentStudent.setStudentName(student.getName());
                addparentStudent.setParentUserId(parentadd.getUserId());
                addparentStudent
                        .setParentRelation(parentvo.getParentRelation());
                addparentStudent.setRank(parentvo.getRank());
                this.parentStudentService.add(addparentStudent);
            }

        }

        // //////////添加家长学生关系信息//end///////////////

        parentvoend = new ParentVo();
        parentvoend.setUserId(userid);
        parentvoend.setStudentId(parentvo.getStudentId());
        parentvoend.setStudentUserId(student.getUserId());
        parentvoend.setUserName(user.getUserName());
        parentvoend.setId(parentadd.getId());
        return parentvoend;

    }

    /**
     * 通过excel添加
     */
    public ParentVo addExcel(ParentVo parentvo) throws Exception {

        ParentVo addResultVo = new ParentVo();
        StudentCondition studentCondition = new StudentCondition();
        if (parentvo != null && parentvo.getStudentNumber() != null
                && !"".equals(parentvo.getStudentNumber())) {
            studentCondition.setStudentNumber(parentvo.getStudentNumber());
        }
        studentCondition.setName(parentvo.getStudentName());
        List<Student> studentList = this.studentDao
                .findStudentHasTeamByCondition(studentCondition, null, null);
        if (studentList.size() > 1) {
            parentvo.setErrorInfo("家长信息针对的学生学籍号+姓名不唯一");
        } else if (studentList.size() < 1) {
            parentvo.setErrorInfo("家长信息针对的学生学籍号+姓名不存在");
        } else {

            // 对vo进行校验，处理，错误的单独处理，正确的返回list
            parentvo.setStudentId(studentList.get(0).getId());
            ParentVo addTempVo = this.add(parentvo);
            if (addTempVo != null && addTempVo.getId() != null
                    && addTempVo.getId() > 0) {
                addResultVo.setAddStatus(true);
                addResultVo.setId(addTempVo.getId());
                parentvo.setErrorInfo("添加成功");
            } else {
                addResultVo.setAddStatus(false);
                addResultVo.setId(0);
            }

        }

        addResultVo.setStudentName(parentvo.getStudentName());
        addResultVo.setStudentNumber(parentvo.getStudentNumber());
        addResultVo.setName(parentvo.getName());
        addResultVo.setMobile(parentvo.getMobile());
        addResultVo.setErrorInfo(parentvo.getErrorInfo());

        return addResultVo;

    }

    /**
     * 删除家长信息服务
     */
    @Override
    public void remove(ParentVo parentvo) throws Exception {
        Student student = this.studentDao.findById(parentvo.getStudentId());
        Parent parent = parentService.findParentById(parentvo.getId());

        List<ParentStudent> parentStudentList = new ArrayList<ParentStudent>();
        ParentStudentCondition condition = new ParentStudentCondition();
        condition.setStudentUserId(student.getUserId());
        condition.setParentUserId(parent.getUserId());
        parentStudentList = this.parentStudentService
                .findParentStudentByCondition(condition);
        if (parentStudentList.size() != 1) {
            throw new Exception("家长账号+" + parent.getUserId() + ",和学生账号:+"
                    + student.getUserId() + ",在家长学生表中关联关系不唯一");
        } else {
            this.parentStudentService.remove(parentStudentList.get(0));
        }
        ParentCondition parentCondition = new ParentCondition();
        parentCondition.setUserId(parent.getUserId());
        List<Parent> parentList = parentService
                .findParentByCondition(parentCondition);
        parentService.remove(parent);
        if (parentList.size() > 1) {
            // 一个家长对应多个学生，在针对学生移除家长时，家长用户信息保持不变
        } else if (parentList.size() < 1) {
            throw new Exception("没有对应的家长用户信息");
        } else {
            UserBinding userBinding = new UserBinding();
            userBinding.setBindingName(parent.getMobile());
            userBinding.setBindingType(1);
            userBinding.setUserId(parent.getUserId());
            // this.userBindingService.remove(userBinding);
        }

    }

    /**
     * 把学生修改信息页面需要的内容返回回去
     *
     * @throws Exception
     */
    @Override
    public ParentVo modifyPage(ParentVo parentVo) throws Exception {
        ParentVo parentVoResult = new ParentVo();
        Integer studentId = parentVo.getStudentId();
        Integer parentId = parentVo.getId();

        Student student = this.studentDao.findById(studentId);
        Parent parent = parentService.findParentById(parentId);
        ParentStudentCondition parentStudentCondition = new ParentStudentCondition();

        parentStudentCondition.setStudentUserId(student.getUserId());
        parentStudentCondition.setParentUserId(parent.getUserId());
        List<ParentStudent> parentStudentList = this.parentStudentService
                .findParentStudentByCondition(parentStudentCondition);
        if (parentStudentList.size() != 1) {
            throw new Exception("家长账号+" + parent.getUserId() + ",和学生账号:+"
                    + student.getUserId() + ",在家长学生表中关联关系不唯一");
        }
        ParentStudent parentStudent = parentStudentList.get(0);
        // 学年2003，年级id，班级id，学生id

        //Team team = this.teamService.findTeamById(student.getTeamId());
        Team team = this.teamService.findCurrentTeamOfStudent(student.getUserId(), parentVo.getSchoolYear());

        parentVoResult.setSchoolYear(team.getSchoolYear());
        parentVoResult.setGradeId(team.getGradeId());
        parentVoResult.setTeamId(team.getId());

        parentVoResult.setStudentId(studentId);
        parentVoResult.setStudentNumber(student.getStudentNumber());
        parentVoResult.setParentRelation(parentStudent.getParentRelation());
        parentVoResult.setRank(parentStudent.getRank());
        parentVoResult.setId(parent.getId());
        parentVoResult.setName(parent.getName());
        parentVoResult.setUserName(parent.getUserName());
        parentVoResult.setMobile(parent.getMobile());
        parentVoResult.setEmail(parent.getEmail());
        parentVoResult.setLicensePlate(parent.getLicensePlate());
        return parentVoResult;
    }

    /**
     * 修改家长信息
     *
     * @throws Exception
     */
    @Override
    public ParentVo modify(ParentVo parentVo) throws Exception {
        ParentVo parentVoResult = null;
        parentVoResult = updateParentPross(parentVo);
        //

        return parentVoResult;
    }

    /**
     * 查询学生信息
     *
     * @param parentvo
     * @return
     */
    @Override
    public List<ParentVo> getParentTemplate(ParentVo parentvo) {
        List<ParentVo> parentVoList = new ArrayList<ParentVo>();
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(parentvo.getSchoolId());
        studentCondition.setSchoolYear(parentvo.getSchoolYear());
        studentCondition.setGradeId(parentvo.getGradeId());
        studentCondition.setTeamId(parentvo.getTeamId());
        List<Student> studentList = this.studentDao
                .findStudentHasTeamByCondition(studentCondition, null, null);
        for (Student student : studentList) {
            String studentName = student.getName();
            String studentNumber = student.getStudentNumber();
            Team team = this.teamService.findTeamById(student.getTeamId());
            String teamName = team.getName();
            Grade grade = this.gradeService.findGradeById(team.getGradeId());
            String gradeName = grade.getName();
            String schoolYear = grade.getSchoolYear();

            ParentVo parentTemp = new ParentVo();
            parentTemp.setSchoolYear(schoolYear);
            parentTemp.setGradeName(gradeName);
            parentTemp.setTeamName(teamName);
            parentTemp.setStudentName(studentName);
            parentTemp.setStudentNumber(studentNumber);

            parentVoList.add(parentTemp);
        }

        return parentVoList;

    }

    public ParentVo findStudent(ParentVo parentvo) {
        ParentVo parentvoEnd = null;
        if (parentvo != null && parentvo.getStudentId() != null) {
            parentvoEnd = new ParentVo();
            Student student = null;
            Team team = null;
            student = this.studentDao.findById(parentvo.getStudentId());
            //team = this.teamService.findTeamById(student.getTeamId());
            team = this.teamService.findCurrentTeamOfStudent(student.getUserId(), parentvo.getSchoolYear());
            if (team != null && student != null) {
                parentvoEnd.setStudentId(student.getId());
                parentvoEnd.setTeamId(team.getId());
                parentvoEnd.setGradeId(team.getGradeId());
                parentvoEnd.setSchoolYear(team.getSchoolYear());
            }

        }

        return parentvoEnd;

    }

    @Override
    public List<ParentStudent> findParentStudentListBySchoolId(Integer schoolId) {

        List<ParentStudent> list = this.parentStudentService
                .findParentStudentListBySchoolId(schoolId);
        if (list == null) {
            list = new ArrayList<ParentStudent>();
        }

        return list;

    }

    @Override
    public Integer findCountParentStudentListBySchoolId(Integer schoolId) {

        List<ParentStudent> list = this.parentStudentService
                .findParentStudentListBySchoolId(schoolId);
        if (list == null) {
            list = new ArrayList<ParentStudent>();
        }

        return list.size();

    }

    @Override
    public List<Parent> findParentByGroupCondition(
            ParentStudentCondition parentStudentCondition, Page page,
            Order order) {
        List<Parent> parentList = new ArrayList<Parent>();

        /*
         * if(parentStudentCondition == null){ return parentList; }
         *
         * if(parentStudentCondition.getSchoolId() == null
         * &&parentStudentCondition.getGradeId() == null
         * &&parentStudentCondition.getTeamId() == null){ return parentList; }
         */
        if (parentStudentCondition == null) {
            parentStudentCondition = new ParentStudentCondition();
        }
        List<ParentStudent> parentStudentList = this.parentStudentService
                .findParentIdByCondition(parentStudentCondition, page, order);
        if (parentStudentList != null && parentStudentList.size() > 0) {
            for (ParentStudent parentStudent : parentStudentList) {

                Parent parent = parentService
                        .findUniqueByUserId(parentStudent.getParentUserId());
                if (parent != null && parent.getId() > 0) {
                    parentList.add(parent);
                }
            }
        }
        Collections.reverse(parentList);
        return parentList;
    }


    @Override
    public List<Parent> findParentByGroupCondition(
            ParentStudentCondition parentStudentCondition) {

        return this.findParentByGroupCondition(parentStudentCondition, null,
                null);
    }

    @Override
    public List<ParentStudent> findParentByGroupCondition(ParentStudentCondition parentStudentCondition, Page page) {
        return this.findParentByGroupConditionNew(parentStudentCondition, page);
    }


    public List<ParentStudent> findParentByGroupConditionNew(ParentStudentCondition parentStudentCondition, Page page) {
//		List<Parent> parentList = new ArrayList<Parent>();

        if (parentStudentCondition == null) {
            parentStudentCondition = new ParentStudentCondition();
        }
        List<ParentStudent> parentStudentList = this.parentStudentService
                .findParentIdByCondition(parentStudentCondition, page, null);
        if (parentStudentList != null && parentStudentList.size() > 0) {
            for (ParentStudent parentStudent : parentStudentList) {

                Parent parent = parentService
                        .findUniqueByUserId(parentStudent.getParentUserId());
                if (parent != null && parent.getId() > 0) {
                    parentStudent.setName(parent.getName());
                    parentStudent.setMobile(parent.getMobile());
                    parentStudent.setUserId(parent.getUserId());
                    parentStudent.setUserName(parent.getUserName());
                    parentStudent.setPersonId(parent.getPersonId());
                    parentStudent.setCreateDate(parent.getCreateDate());
//					parentList.add(parent);
                }
            }
        }
//		Collections.reverse(parentList);
        return parentStudentList;
    }


    @Override
    public List<Parent> findParentByGroupCondition(
            ParentStudentCondition parentStudentCondition, Order order) {

        return this.findParentByGroupCondition(parentStudentCondition, null,
                order);
    }

    @Override
    public List<ParentStudent> findSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page) {
        if (parentStudentCondition == null) {
            parentStudentCondition = new ParentStudentCondition();
        }
        List<ParentStudent> parentStudentList = this.parentStudentService.findSutBusByGroupCondition(parentStudentCondition, page, null);
        if (parentStudentList != null && parentStudentList.size() > 0) {
            for (ParentStudent parentStudent : parentStudentList) {

                Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
                if (parent != null && parent.getId() > 0) {
                    parentStudent.setName(parent.getName());
                    parentStudent.setMobile(parent.getMobile());
                    parentStudent.setUserId(parent.getUserId());
                    parentStudent.setUserName(parent.getUserName());
                    parentStudent.setPersonId(parent.getPersonId());
                    parentStudent.setCreateDate(parent.getCreateDate());
                }
            }
        }
        return parentStudentList;
    }

    @Override
    public List<ParentStudentBus> findParentSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page) {
        if (parentStudentCondition == null) {
            parentStudentCondition = new ParentStudentCondition();
        }
        List<ParentStudentBus> parentStudentList = this.parentStudentService.findParentSutBusByGroupCondition(parentStudentCondition, page, null);
        if (parentStudentList != null && parentStudentList.size() > 0) {
            for (ParentStudentBus parentStudent : parentStudentList) {

                Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
                if (parent != null && parent.getId() > 0) {
                    parentStudent.setName(parent.getName());
                    parentStudent.setMobile(parent.getMobile());
                }
            }
        }
        return parentStudentList;
    }

    public static void main(String[] args) {
        ParentProxyServiceImpl aa = new ParentProxyServiceImpl();
        ParentVo parentvo = new ParentVo();

        parentvo.setSchoolId(1);
        parentvo.setSystem_app_id(1);
        parentvo.setStudentId(1);
        // parentvo.setRoleId(1);
        parentvo.setName("!");
        parentvo.setMobile("12232222222");
        // parentvo.setEmail("!");
        parentvo.setParentRelation("!");
        parentvo.setRank("!");

        if (!aa.validataAddParent(parentvo)) {
            System.out.println("不正确");
        } else {
            System.out.println("ok");
        }

    }

    /**
     * 校验手机号码
     *
     * @param mobile
     * @return
     */
    @Override
    public boolean checkMobile(String mobile) {
        boolean isMobile = true;

        String check = "^1[0-9]{10}$";

        try {
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(mobile);
            isMobile = matcher.matches();
        } catch (Exception e) {
            isMobile = false;
        }
        return isMobile;
    }
    @Override
    public List<ParentStudent> findByUser(String stuName, String name, Integer state, String userName, String mobile, Page page) {
        return this.parentService.findByUser( stuName,  name,  state,  userName,  mobile,  page);
    }
    // =============================================
    // 添加家长信息修改（015-11-21）========================================//

    @Override
    public ParentVo addInfoForParent(ParentVo parentVo) {
        /**
         * 2016-5-18 添加 判断相同关系不可多次导入 也不能覆盖 直接返回code码和提示信息 不启用该判断 只需要注释掉即可
         */
        // ==========添加判断开始=================//
//		Student student = studentDao.findById(parentVo.getStudentId());
//		ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
//		parentStudentCondition.setParentRelation(parentVo.getParentRelation());
//		parentStudentCondition.setStudentUserId(student == null ? parentVo
//				.getStudentUserId() : student.getUserId());
//		parentStudentCondition.setIsDelete(false);
//		List<ParentStudent> parentStudentList = parentStudentService
//				.findParentStudentByCondition(parentStudentCondition);
//		if (parentStudentList != null && parentStudentList.size() > 0) {
//			parentVo.setDetail("学生关系已存在，不能再次导入！");
//			parentVo.setErrorCode("111006");
//			parentVo.setErrorInfo("学生关系已存在，不能再次导入！");
//			return parentVo;
//		}

        parentVo = processAOfParent(parentVo);
        return parentVo;
    }

    /**
     * 功能描述：检验家长信息参数是否完整 2015-11-21
     *
     * @param parentVo
     * @return
     */
    public String checkInfoIsNull(ParentVo parentVo) {
        String errorMessage = "";
        if (parentVo != null) {
            Integer schoolId = parentVo.getSchoolId();
            Integer system_app_id = parentVo.getSystem_app_id();
            Integer studentId = parentVo.getStudentId();
            // Integer roleId = parentVo.getRoleId();
            String name = parentVo.getName();
            String mobile = parentVo.getMobile();
            // String email = parentVo.getEmail();
            String parentRelation = parentVo.getParentRelation();
            String rank = parentVo.getRank();

            // 校验学校Id，必填
            if (schoolId == null || schoolId <= 0) {
                errorMessage = "学校必填;";
            }
            // 校验appId，必填
            if (system_app_id == null || system_app_id <= 0) {
                errorMessage = "AppId必填;";
            }
            // 校验学生Id，必填
            if (studentId == null || studentId <= 0) {
                errorMessage = "学生必填;";
            }
            // 校验家长姓名，必填
            if (name == "" || "".equals(name)) {
                errorMessage = "家长姓名必填;";
            }
            // 校验家长手机号，必填、并校验家长手机号码格式是否正确
            if (mobile == "" || "".equals(mobile)) {
                errorMessage = "家长手机号码必填;";
            } else {
                boolean isMobile = checkMobile(mobile);
                if (!isMobile) {
                    errorMessage = "家长手机号码格式不正确;";
                }
            }
            // 校验学生与家长关系,必填
            if (parentRelation == "" || "".equals(parentRelation)) {
                errorMessage = "学生与家长关系必填;";
            }
            // 校验家长关系类别,必填，0或者1
            if (rank == null || "".equals(rank)) {
                errorMessage = "家长关系类别必填;";
            }
        }

        if (errorMessage != "" && !"".equals(errorMessage)) {
            errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
        }
        return errorMessage;
    }

    /*
     * =============================================创建家长档案A流程开始==================
     * ==========================
     */

    public ParentVo processAOfParent(ParentVo parentVo) {
        Role role = null;
        // 创建家长档案A流程第一步（是否提供姓名和电话号码）
        Boolean flag = checkNameAndMobileIsOffer(parentVo.getName(),
                parentVo.getMobile());
        if (flag) {
            // 提供了姓名和手机号码 创建家长档案A流程第二步（根据手机号码判断家长记录是否已经存在）
            Parent par = checkMobileIsExist(parentVo.getMobile());
            if (par != null) {

                // 2016-4-21添加 关于用户号码重复获取不到userId的问题=====
                parentVo.setUserId(par.getUserId());
                parentVo.setId(par.getId());
                // ============================================

                // 根据手机号码获取家长记录，号码已经存在，判断该记录姓名与输入姓名是否一致
                Parent parent = checkParentIsSame(parentVo);
                if (parent != null) {
                    // 执行C流程
                    // 家长已经存在(手机号码、姓名都一样)
                    // 执行B1流程（将用户加入学校用户组yh_user_group、添加家长的用户角色）
                    parentVo.setUserId(parent.getUserId());
                    // 2016-3-3 添加
                    parentVo.setId(parent.getId());
                    role = this.roleService.findRoleById(parentVo.getRoleId());
                    User userTemp = this.userService.findUserById(parentVo
                            .getUserId());
                    addUserGroupForParent(userTemp, role,
                            parentVo.getSchoolId());
                    //	2017-12-08	返回用户名
                    parentVo.setUserName(userTemp.getUserName());
                    // 执行C1流程，判断家长是否已经存在（pj_school_usr），避免一个家长在同一所学校创建多条记录
                    if (ParentExitstInSchoolUser(parentVo)) {
                        // 学校用户表中家长记录已经存在，执行D流程，判断学生与家长的记录是否存在（pj_parent_student），不存在则创建记录，已存在退出
                        parentVo = createProssOfD(parentVo);
                    } else {
                        // 学校用户表中家长记录不存在，将家长加入学校用户表中（pj_school_user）
                        addParentToSchoolUser(parentVo);
                        // 执行D流程
                        parentVo = createProssOfD(parentVo);
                    }
                } else {

                    //TODO

                    // 家长记录已经存在，但不是同一个人，退出
                    //2016-10-20添加，获取是谁的父亲在使用这个号码
                    ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
                    parentStudentCondition.setParentUserId(par.getUserId());
                    parentStudentCondition.setIsDelete(false);
                    List<ParentStudent> list = parentStudentService.findParentStudentByCondition(parentStudentCondition);
                    String relation = "";
                    if (list != null && list.size() > 0) {
                        relation = list.get(0).getParentRelation();

                    }
                    parentVo.setUserId(par.getUserId());
                    parentVo.setErrorInfo("号码已经被注册使用(姓名：" + par.getName() + ")");
                    parentVo.setErrorCode("031012");
                    parentVo.setDetail("" + par.getUserId() + "|"
                            + par.getName() + "|" + parentVo.getMobile() + "|"
                            + (relation == "" ? "99" : relation));
                    return parentVo;
                }
            } else {
                // 该号码没有查找到被家长使用
                // 家长不存在 创建家长档案A流程第三步 （根据手机号码查找用户，判断手机号码是否已经注册用户）
                User user = findUserByMobile(parentVo.getMobile());
                role = this.roleService.findRoleById(parentVo.getRoleId());
                if (user == null) {
                    // 该手机号码没有被用户注册
                    // 执行B流程
                    parentVo = processBOfParent(parentVo);
                    // 执行C流程
                    parentVo = createProssOfC(parentVo);
                    // 执行D流程
                    parentVo = createProssOfD(parentVo);
                } else {
                    // 该手机号码已经被用户注册
                    // 创建家长档案A流程第四步（取得用户后判断用户的姓名和手机号码与输入的家长的姓名与手机号码是否相同）
                    Boolean flagTwo = checkParentIsSame(user, parentVo);
                    if (flagTwo) {
                        // 用户的姓名和手机号码与输入的家长的姓名与手机号码相同 创建教工档案B1流程第一步
                        addUserGroupForParent(user, role,
                                parentVo.getSchoolId());
                        parentVo.setUserId(user.getId());
                        parentVo.setUserName(user.getUserName());
                        parentVo.setPersonId(user.getPersonId());
                        // 执行C流程
                        parentVo = createProssOfC(parentVo);
                        // 执行D流程
                        parentVo = createProssOfD(parentVo);
                    } else {
                        // 2016-5-18 添加 用户的姓名和手机号码与输入的家长的姓名与手机号码不相同 退出
                        if (user != null && user.getPersonId() != null) {
                            Person person = personService.findPersonById(user.getPersonId());
                            parentVo.setErrorInfo("号码已经被注册使用(姓名："
                                    + person.getName() + ")");
                            //parentVo.setUserId(userId);
                            parentVo.setErrorCode("031012");
                            parentVo.setUserId(user.getId());
                            parentVo.setDetail("" + user.getId() + "|"
                                    + person.getName() + "|"
                                    + parentVo.getMobile() + "|"
                                    + parentVo.getParentRelation());
                        }
                        return parentVo;
                    }
                }
            }
        } else {
            // 数据不完整 退出
            parentVo.setErrorInfo("数据不完整");
        }
        return parentVo;
    }

    /**
     * 创建家长档案A流程第一步 功能描述：判断是否提供姓名和手机号码 2015-11-21
     *
     * @param name
     * @param mobile
     * @return
     */
    public Boolean checkNameAndMobileIsOffer(String name, String mobile) {
        boolean flag = false;
        if (name == null || "".equals(name) || mobile == null
                || "".equals(mobile)) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 功能描述：根据手机号码获取家长记录，判断该手机号码已被使用 2015-11-21
     *
     * @param mobile
     * @return
     */
    public Parent checkMobileIsExist(String mobile) {
        Parent parent = null;
        ParentCondition condition = new ParentCondition();
        condition.setMobile(mobile);
        condition.setIsDelete(false);
        List<Parent> parentList = parentService
                .findParentByCondition(condition);
        if (parentList.size() > 0) {
            parent = parentList.get(0);
        }
        return parent;
    }

    /**
     * 创建家长档案A流程第二步 功能描述：根据家长姓名和手机号码判断家长记录是否已经存在 2015-11-21
     *
     * @param parentVo
     * @return
     */
    public Parent checkParentIsSame(ParentVo parentVo) {
        Parent parent = null;
        ParentCondition condition = new ParentCondition();
        // condition.setSchoolId(parentVo.getSchoolId());
        condition.setIsDelete(false);
        condition.setMobile(parentVo.getMobile());
        //condition.setName(parentVo.getName());
        List<Parent> parentList = parentService
                .findParentByCondition(condition);
        if (parentList.size() > 0) {
            parent = parentList.get(0);
            parentVo.setId(parentList.get(0).getId());
            parentVo.setUserId(parentList.get(0).getUserId());
        }
        return parent;
    }

    /**
     * 创建家长档案A流程第三步 功能描述：根据手机号码查找用户，判断手机号码是否已经注册用户 2015-11-21
     *
     * @param mobile
     * @return
     */
    public User findUserByMobile(String mobile) {
        User user = null;
        UserBindingCondition condition = new UserBindingCondition();
        condition.setBindingName(mobile);
        condition.setBindingType(UserBindingContants.TYPE_PHONE);
        condition.setIsDeleted(false);
        List<UserBinding> userBindingList = this.userBindingService
                .findUserBindingByCondition(condition, null, null);
        if (userBindingList.size() > 0) {
            user = this.userService.findUserById(userBindingList.get(0)
                    .getUserId());
        }
        return user;
    }

    /**
     * 创建家长档案A流程第四步 功能描述：取得用户后判断用户的姓名和手机号码与输入的家长的姓名与手机号码是否相同 2015-11-21
     *
     * @param user
     * @param parentVo
     * @return
     */
    public Boolean checkParentIsSame(User user, ParentVo parentVo) {
        Boolean flag = false;
        Person person = null;
        if (user != null) {
            person = this.personService.findPersonById(user.getPersonId());
            // 存在个人档案记录，判断个人档案表中的姓名与手机号码和输入的姓名和手机号码是否相同
            if (person != null) {
                if (person.getMobile().equals(parentVo.getMobile())) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /*
     * =============================================创建家长档案A流程结束==================
     * ==========================
     */

    /*
     * =============================================创建家长档案B流程开始==================
     * ==========================
     */

    public ParentVo processBOfParent(ParentVo parentVo) {
        // 该身份证号不存在，创建教工档案B流程第一步-->U2.1创建用户档案第三步（功能描述：创建个人档案pj_person）
        parentVo = addPersonForParent(parentVo);
        // 创建教工档案B流程第二步U1.1D-->A（功能描述：创建用户，获得用户账号）
        parentVo = addUserForParent(parentVo);
        return parentVo;
    }

    /**
     * 创建家长档案B流程第一步 -->U2.1创建用户档案第一步 功能描述：判断输入的身份证号是否已经存在 2015-11-21
     *
     * @param idCardNumber
     * @return
     */
    public Boolean checkIdCardNumerIsExist(String idCardNumber) {
        Boolean flag = false;
        // 判断是否提供了身份证号
        if (idCardNumber == null || "".equals(idCardNumber)
                || idCardNumber == "") {
            flag = false;
        } else {
            // 提供了身份证号，判断该身份证号是否已经存在
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
     * 创建家长档案B流程第一步 -->U2.1创建用户档案第二步
     * 功能描述：判断输入的身份证号和姓名与个人档案记录（pj_person）的身份证号和姓名是否一致
     * 不一致表明此身份证号已经存档，但与输入用户信息不匹配，返回空 一致表明此身份证号已经存档，返回已有记录
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
     * 创建家长档案B流程第一步-->U2.1创建用户档案第三步 功能描述：创建个人档案pj_person 2015-11-21
     *
     * @param parentVo
     * @return
     */
    public ParentVo addPersonForParent(ParentVo parentVo) {
        Person person = new Person();
        person.setName(parentVo.getName());
        person.setMobile(parentVo.getMobile());
        person.setEmail(parentVo.getEmail());
        person.setCreateDate(new Date());
        person.setModifyDate(new Date());
        person.setIsDelete(false);
        Person p = personService.add(person);
        if (p != null) {
            parentVo.setPersonId(p.getId());
            ;
        }
        return parentVo;
    }

    /**
     * 创建家长档案B流程第二步U1.1D-->A 功能描述：创建用户，获得用户账号 2015-11-21
     *
     * @param parentVo
     * @return
     */
    public ParentVo addUserForParent(ParentVo parentVo) {
        // 添加用户信息
        User user = new User();
        user.setIsDeleted(false);
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user.setPassword(UserCondition.DEFAULT_PARENT_PASSWORD);
        user.setState(UserContants.STATE_NORMAL);
        user.setPersonId(parentVo.getPersonId());

        UserResult ur;
        try {
            ur = this.userService
                    .addUser(user, parentVo.getSchoolId(),
                            GroupContants.TYPE_SCHOOL, parentVo.getMobile(),
                            UserBindingContants.TYPE_PHONE,
                            parentVo.getSystem_app_id());
            if (UserResult.STATUS_USER_BINDING_NAME_EXIST.equals(ur
                    .getStatusCode())) {// 电话号码重复
                // parentVo.setErrorInfo("手机号码已经存在");
                parentVo.setErrorInfo("手机号码已经被其他用户注册使用(姓名："
                        + parentVo.getName() + ")");
                parentVo.setErrorCode("031012");
                parentVo.setDetail("" + parentVo.getUserId() + "|"
                        + parentVo.getName() + "|" + parentVo.getMobile() + "|"
                        + parentVo.getParentRelation());
            } else if (UserResult.STATUS_ACCOUNT_DB_NOT_ENOUGH.equals(ur
                    .getStatusCode())) {// 帐号库的帐号已用光
                parentVo.setErrorInfo("帐号库帐号已用完");
            } else if (UserResult.STATUS_CUSTOM_USER_NAME_EXIST.equals(ur
                    .getStatusCode())) {// 用户名已经存在
                parentVo.setErrorInfo("用户名已经存在");
            } else if (UserResult.STATUS_SUCCESS.equals(ur.getStatusCode())) {// 成功
                parentVo.setUserId(ur.getUser().getId());
                parentVo.setUserName(ur.getUser().getUserName());
                // 执行B1流程（将用户加入学校用户组yh_user_group、添加家长的用户角色）
                Role role = this.roleService.findRoleById(parentVo.getRoleId());
                addUserGroupForParent(ur.getUser(), role,
                        parentVo.getSchoolId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentVo;
    }

    /**
     * 功能描述：将用户加入学校用户组以及添加家长的用户角色
     *
     * @param user
     * @param role
     * @param schoolId
     */
    public void addUserGroupForParent(User user, Role role, Integer schoolId) {
        groupUserService.addUserToGruopAndRole(user, role, schoolId);
    }

    /*
     * =============================================创建家长档案B流程结束==================
     * ==========================
     */

    /**
     * 创建家长的C流程
     *
     * @param parentVo
     * @return
     */
    public ParentVo createProssOfC(ParentVo parentVo) {
        try {
            parentVo = addParent(parentVo);
            if (!ParentExitstInSchoolUser(parentVo)) {
                addParentToSchoolUser(parentVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentVo;
    }

    /**
     * 创建家长的D流程
     *
     * @param parentVo
     * @return
     */
    public ParentVo createProssOfD(ParentVo parentVo) {
        try {
            Student student = null;
            if (parentVo.getStudentId() != null) {
                student = this.studentDao.findById(parentVo.getStudentId());
                if (student != null && student.getUserId() != null) {
                    parentVo.setStudentUserId(student.getUserId());
                }
            }
            if (parentVo.getStudentUserId() != null) {
                student = this.studentDao.findStudentByUserId(parentVo
                        .getStudentUserId());
                if (student != null && student.getId() != null) {
                    parentVo.setStudentId(student.getId());
                }
            }
            if (student != null) {
                parentVo.setStudentName(student.getName());
                parentVo.setStudentUserId(student.getUserId());
            } else {
                parentVo.setErrorInfo("找不到对应的学生！");
                return parentVo;
            }

            if (!studentParentExitst(parentVo)) {
                addStudentParent(parentVo);
            }

            // 添加主监护人==============================
            if ((parentVo.getRank() != null && !"".equals(parentVo.getRank()) && "1"
                    .equals(parentVo.getRank())) || "1" == parentVo.getRank()) {
                ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
                parentStudentCondition.setStudentUserId(parentVo
                        .getStudentUserId());
                parentStudentCondition.setRank("1");
                List<ParentStudent> list = parentStudentService
                        .findParentStudentByCondition(parentStudentCondition);
                if (list != null && list.size() > 0) {
                    ParentStudent ps = list.get(0);
                    ps.setRank("0");
                    parentStudentService.modify(ps);
                }
                parentStudentService.modifyMainGuardian(parentVo.getUserId(),
                        parentVo.getStudentUserId());
            }

            //2016-8-16 向team_user添加数据   应该是教务优化的时候，还没有这张新表
            if (student != null && student.getTeamId() != null) {
                TeamUser teamUser = teamUserDao.findUnique(student.getTeamId(), parentVo.getUserId());
                Person person = null;
                if (parentVo.getPersonId() != null) {
                    person = personService.findPersonById(parentVo.getPersonId());
                }
                if (teamUser != null) {
                    teamUser.setModifyDate(new Date());
                    teamUser.setIsParent(true);
                    teamUserDao.update(teamUser);
                } else {
                    teamUser = new TeamUser();
                    teamUser.setCreateDate(new Date());
                    teamUser.setIsMaster(false);
                    teamUser.setIsParent(true);
                    teamUser.setIsStudent(false);
                    teamUser.setIsTeacher(false);
                    teamUser.setModifyDate(new Date());
                    teamUser.setName(parentVo.getName());
                    teamUser.setSex(person == null ? null : person.getSex());
                    teamUser.setTeamId(student.getTeamId());
                    teamUser.setUserId(parentVo.getUserId());
                    teamUserDao.create(teamUser);
                }
            }

            //2018-4-18 修改parent表，用于同步
            Parent parent = parentService.findUniqueByUserId(parentVo.getUserId());
            if (parent != null) {
                parent = new Parent(parent.getId());
                parent.setModifyDate(new Date());
                parentService.modify(parent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentVo;
    }

    /**
     * 创建家长档案记录
     */
    public ParentVo addParent(ParentVo parentVo) {
        Parent parent = new Parent();
        parent.setCreateDate(new Date());
        parent.setIsDelete(false);
        parent.setEmail(parentVo.getEmail());
        parent.setUserId(parentVo.getUserId());
        parent.setUserName(parentVo.getUserName());
        parent.setName(parentVo.getName());
        parent.setPersonId(parentVo.getPersonId());
        parent.setMobile(parentVo.getMobile());
        Parent p = parentService.add(parent);
        if (p != null) {
            parentVo.setId(p.getId());
        }
        return parentVo;
    }

    /**
     * 根据userId查找家长是否存在schooluser表 true exitst false notExitst
     *
     * @return true or false
     */
    public boolean ParentExitstInSchoolUser(ParentVo parentVo) {
        boolean exitst = false;
        SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
        schoolUserCondition.setSchoolId(parentVo.getSchoolId());
        schoolUserCondition.setIsDeleted(false);
        schoolUserCondition.setUserId(parentVo.getUserId());
        schoolUserCondition.setUserType(String
                .valueOf(SchoolUserCondition.DEFAULT_PARENT_USERTYPE));
        List<SchoolUser> list = schoolUserService.findSchoolUserByCondition(
                schoolUserCondition, null, null);
        if (list.size() > 0) {
            exitst = true;
        }
        return exitst;
    }

    /**
     * 向schooluser表添加家长信息
     *
     * @param parentVo
     */
    public void addParentToSchoolUser(ParentVo parentVo) {
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setSchoolId(parentVo.getSchoolId());
        schoolUser.setUserType(String
                .valueOf(SchoolUserCondition.DEFAULT_PARENT_USERTYPE));
        schoolUser.setUserId(parentVo.getUserId());
        schoolUser.setPersonId(parentVo.getPersonId());
        schoolUser.setOwnerId(parentVo.getId());
        schoolUser.setName(parentVo.getName());
        schoolUser.setUserName(parentVo.getUserName());
        schoolUser.setInSchool(true); // true 在校，false 不在校
        schoolUser.setIsDeleted(false);
        schoolUser.setCreateDate(new Date());
        schoolUser.setModifyDate(new Date());
        schoolUserService.add(schoolUser);
    }

    /**
     * 判断学生与家长的记录是否存在
     */
    public boolean studentParentExitst(ParentVo parentVo) {
        boolean exits = false;
        ParentStudentCondition psc = new ParentStudentCondition();
        psc.setStudentUserId(parentVo.getStudentUserId());
        psc.setParentUserId(parentVo.getUserId());
        psc.setIsDelete(false);
        List<ParentStudent> list = parentStudentService
                .findParentStudentByCondition(psc);
        if (list.size() > 0) {
            exits = true;
        }
        return exits;
    }

    /**
     * 添加学生与家长的信息
     */
    public ParentStudent addStudentParent(ParentVo parentVo) {
        ParentStudent parentStudent = new ParentStudent();
        parentStudent.setParentRelation(parentVo.getParentRelation());
        parentStudent.setParentUserId(parentVo.getUserId());
        parentStudent.setRank(parentVo.getRank() == null ? "0" : parentVo
                .getRank());
        parentStudent.setStudentUserId(parentVo.getStudentUserId());
        parentStudent.setStudentName(parentVo.getStudentName());
        parentStudent.setIsDelete(false);
        parentStudent.setCreateDate(new Date());
        parentStudent.setModifyDate(new Date());
        parentStudent = parentStudentService.add(parentStudent);
        return parentStudent;
    }

    // =============================================
    // 添加家长信息修改（2015-11-21）========================================//

    // ============================修改家长信息================================//
    public ParentVo updateParentPross(ParentVo parentVo) {
        parentVo = updateParentProssOfA(parentVo);
        return parentVo;
    }

    public ParentVo updateParentProssOfA(ParentVo parentVo) {
        // ParentCondition parentCondition = new ParentCondition();
        // parentCondition.setUserName(parentVo.getUserName());
        // List<Parent> pList =
        // parentService.findParentByCondition(parentCondition);
        // if(pList.size() > 0){
        // parentVo.setUserId(pList.get(0).getUserId());
        // }
        String parentMobile = "";
        Parent parent = parentService.findParentById(parentVo.getId());
        if(StringUtils.isNotEmpty(parentVo.getLicensePlate())) {
            String[] carNums = parentVo.getLicensePlate().split(",");
            for (String carNum : carNums) {
                long count = basicSQLService.findUniqueLong("select exists(select 1 from pj_parent where is_delete=0 and license_plate like '%" + carNum + "%' and id!='" + parent.getId() + "') e");
                if (count > 0) {
                    parentVo.setErrorInfo("车牌【" + carNum + "】已被其他人绑定");
                    parentVo.setErrorCode("031012");
                    return parentVo;
                }
            }
        }

        if (parent != null) {
            parentVo.setPersonId(parent.getPersonId());
            parentVo.setUserId(parent.getUserId());
            // 获得家长原有手机号码
            parentMobile = parent.getMobile();
        }




        if (isModifyMobile(parentVo)) {
            if (mobileIsExitst(parentVo)) {
                // 修改的手机号码已经被其他用户注册使用，退出
                parentVo.setErrorInfo("手机号码已经被他人使用");
                parentVo.setErrorCode("031012");

                return parentVo;
            } else {
                // 手机号码没有被其他用户使用，修改家长表(pj_parent)
                updateParent(parentVo);
                // 修改个人档案表(pj_person)
                updatePerson(parentVo);
                // 修改家长学生表（parentStudent）添加于2016-2-25
                updateParentStudent(parentVo);
                // 执行B流程
                parentVo = updateParentProssOfB(parentVo);
                // 执行C流程
                parentVo = updateParentProssOfC(parentVo, parentMobile);
            }
        } else {
            // 修改家长表(pj_parent)
            updateParent(parentVo);
            // 修改个人档案表(pj_person)
            updatePerson(parentVo);
            // 修改家长学生表（parentStudent）添加于2016-2-25
            updateParentStudent(parentVo);
        }
        return parentVo;
    }

    /**
     * @param parentVo
     * @return
     * @function 修改parentStudent表
     * @date 2016-2-25
     */
    public void updateParentStudent(ParentVo parentVo) {
        Parent parent = parentService.findParentById(parentVo.getId());
        Student student = studentDao.findById(parentVo.getStudentId());
        ParentStudent ps = null;
        ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
        parentStudentCondition.setParentUserId(parent.getUserId());
        parentStudentCondition.setStudentUserId(student.getUserId());
        parentStudentCondition.setTeamId(student.getTeamId());
        List<ParentStudent> parentStudentList = parentStudentService
                .findParentStudentByCondition(parentStudentCondition);
        if (parentStudentList != null && parentStudentList.size() > 0) {
            ps = parentStudentList.get(0);
            ps.setParentRelation(parentVo.getParentRelation());
            ps.setRank(parentVo.getRank());
            ps.setModifyDate(new Date());
            parentStudentService.modify(ps);

            if ("1".equals(parentVo.getRank())) {
                ParentStudentCondition condition = new ParentStudentCondition();
                condition.setStudentUserId(student.getUserId());
                condition.setRank("1");
                condition.setIsDelete(false);
                List<ParentStudent> list = parentStudentService.findParentStudentByCondition(condition);
                if (list != null && list.size() > 0) {
                    for (ParentStudent parentStudent : list) {
                        if (!parent.getUserId().equals(parentStudent.getParentUserId())) {
                            parentStudent.setRank("0");
                            parentStudent.setModifyDate(new Date());
                            parentStudentService.modify(parentStudent);
                            //更新修改时间，同步家长接口
                            Parent p = parentService.findUniqueByUserId(parentStudent.getParentUserId());
                            if (p != null) {
                                p = new Parent(p.getId());
                                p.setModifyDate(new Date());
                                parentService.modify(p);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 功能描述：修改家长手机号码B流程 2015-11-27
     *
     * @param parentVo
     * @return
     */
    public ParentVo updateParentProssOfB(ParentVo parentVo) {
        updateAllTeachers(parentVo);
        updateAllStudents(parentVo);
        updateAllParents(parentVo);
        return parentVo;
    }

    /**
     * 功能描述：修改家长手机号码C流程 2015-11-27
     *
     * @param parentVo
     * @return
     */
    public ParentVo updateParentProssOfC(ParentVo parentVo, String parentMobile) {
        removeBinding(parentVo.getUserId(), parentMobile);
        addBinding(parentVo);
        return parentVo;
    }

    /**
     * 判断是否是修改手机号码
     */
    public boolean isModifyMobile(ParentVo parentVo) {
        // Parent p = parentService.findUniqueByUserId(parentVo.getUserId());
        Parent parent = parentService.findParentById(parentVo.getId());
        boolean isModifyMobile = false;
        if (parent != null) {
            if (!parent.getMobile().equals(parentVo.getMobile())) {
                isModifyMobile = true;
            }
        }
        return isModifyMobile;
    }

    /**
     * 判断家长修改的号码是否已经存在
     *
     * @param parentVo
     * @return
     */
    public boolean mobileIsExitst(ParentVo parentVo) {
        boolean exitst = false;
        UserBinding ub = userBindingService.findByBindingName(parentVo
                .getMobile());
        if (ub != null) {
            exitst = true;
        }
        return exitst;
    }

    /**
     * 修改家长的信息
     *
     * @param parentVo
     * @return
     */
    public Parent updateParent(ParentVo parentVo) {
        Parent parent = new Parent();
        parent.setId(parentVo.getId());
        parent.setEmail(parentVo.getEmail());
        parent.setName(parentVo.getName());
        parent.setMobile(parentVo.getMobile());
        parent.setLicensePlate(parentVo.getLicensePlate());
        Parent p = null;
        try {
            p = parentService.modify(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 修改个人档案表
     */
    public void updatePerson(ParentVo parentVo) {
        if (parentVo.getPersonId() != null) {
            Person person = new Person();
            person.setId(parentVo.getPersonId());
            person.setMobile(parentVo.getMobile());
            person.setName(parentVo.getName());
            person.setEmail(parentVo.getEmail());
            personService.modify(person);
        }
    }

    /*
     * =========================================修改家中手机号码B流程开始====================
     * =============
     */

    /**
     * 功能描述：根据用户ID查找出（pj_teacher）表中所有记录 2015-11-27
     *
     * @return
     */
    public List<Teacher> findTeacherByUserId(Integer userId) {
        TeacherCondition condition = new TeacherCondition();
        condition.setIsDelete(false);
        condition.setUserId(userId);
        List<Teacher> list = teacherDao.findTeacherByCondition(condition, null,
                null);
        return list;
    }

    /**
     * 功能描述：修改（pj_teacher）表中所有相同用户的手机号码 2015-11-27
     *
     * @param parentVo
     */
    public void updateAllTeachers(ParentVo parentVo) {
        List<Teacher> list = findTeacherByUserId(parentVo.getUserId());
        Teacher teacher = null;
        if (list.size() > 0) {
            for (Teacher t : list) {
                teacher = new Teacher();
                teacher.setId(t.getId());
                teacher.setMobile(parentVo.getMobile());
                teacher.setModifyDate(new Date());
                teacherDao.update(teacher);
            }
        }
    }

    /**
     * 功能描述：根据用户ID查找出（pj_student）表中所有记录 2015-11-27
     *
     * @param userId
     * @return
     */
    public List<Student> findStudentByUserId(Integer userId) {
        StudentCondition condition = new StudentCondition();
        condition.setIsDelete(false);
        condition.setUserId(userId);
        ;
        List<Student> list = studentDao.findStudentByOnlyCondition(condition,
                null, null);
        return list;
    }

    /**
     * 功能描述：修改（pj_student）表中所有相同用户的手机号码 2015-11-27
     *
     * @param parentVo
     */
    public void updateAllStudents(ParentVo parentVo) {
        List<Student> list = findStudentByUserId(parentVo.getUserId());
        Student student = null;
        if (list.size() > 0) {
            for (Student s : list) {
                student = new Student();
                student.setId(s.getId());
                student.setMobile(parentVo.getMobile());
                student.setModifyDate(new Date());
                studentDao.update(student);
            }
        }
    }

    /**
     * 功能描述：根据用户ID查找出(pj_parent)表中所有记录 2015-11-27
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
     * 功能描述：修改（pj_parent）表中所有相同用户的手机号码 2015-11-27
     *
     * @param parentVo
     */
    public void updateAllParents(ParentVo parentVo) {
        List<Parent> list = findParentByUserId(parentVo.getUserId());
        Parent parent = null;
        if (list.size() > 0) {
            for (Parent p : list) {
                parent = new Parent();
                parent.setId(p.getId());
                parent.setMobile(parentVo.getMobile());
                parentService.modify(parent);
            }
        }
    }

    /*
     * =========================================修改家中手机号码B流程结束====================
     * =============
     */

    /**
     * 移除家长用户的绑定记录
     */
    public void removeBinding(Integer userId, String mobile) {
        UserBinding ub = userBindingService.findUnique(userId, mobile);
        if (ub != null) {
            userBindingService.remove(ub);
        }
    }

    /**
     * 添加新的家长绑定记录
     */
    public void addBinding(ParentVo parentVo) {
        UserBinding ub = new UserBinding();
        ub.setBindingName(parentVo.getMobile());
        ub.setBindingType(1);
        ub.setCreateDate(new Date());
        ub.setEnabled(true);
        ub.setIsDeleted(false);
        ub.setModifyDate(new Date());
        ub.setUserId(parentVo.getUserId());
        userBindingService.add(ub);
    }

    // ============================修改家长信息================================//


    @Override
    public List<ParentAccountStudent> findParentAccountAndStuInfo(ParentStudentCondition parentStudentCondition, Page page) {
        return parentStudentService.findParentAccountAndStuInfo(parentStudentCondition,page);
    }
}
