package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.mergin.dao.StudentArchiveDao;
import com.xunyunedu.mergin.dao.StudentTwoDao;
import com.xunyunedu.mergin.util.SchoolUserCondition;
import com.xunyunedu.mergin.util.StudentArchiveContants;
import com.xunyunedu.mergin.util.StudentContants;
import com.xunyunedu.mergin.util.UserBindingContants;
import com.xunyunedu.mergin.vo.*;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.user.model.Profile;
import com.xunyunedu.mergin.service.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceTwoImpl implements StudentServiceTwo {

    private String studentType = "4";
    @Autowired
    private TeamStudentService teamStudentService;

    @Autowired
    private StudentTwoDao studentDao;

    @Autowired
    private StudentArchiveDao studentArchiveDao;

    @Autowired
    private FamilyMemberService familyMemberService;

    @Autowired
    private UserBindingService userBindingService;
    @Autowired
    private PersonService personService;
    @Autowired
    private UserServiceTwo userService;
    @Autowired
    private SchoolUserService schoolUserService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private JobControlService jobControlService;
    @Autowired
    private TeamTwoService teamService;
    @Autowired
    private GradeTwoService gradeService;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public List<Student> findStudentByCondition(
            StudentCondition studentCondition, Page page, Order order) {
        return studentDao.findStudentByCondition(studentCondition, page, order);
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
        try {
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
                    try {
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
                        //String number = student.getStuNum();
                        //userDetailInfo.setNumber(number != null && !"".equals(number) ? Integer.parseInt(number) : null);
                        userDetailInfo.setRemark(personOfStudent.getRemark());
                        userDetailInfo = modfiyUserInfo(userDetailInfo, isBindingMobile);
                        studentArchiveComplete.getRemarks().setErrorCode(userDetailInfo.getErrorCode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
                    //chuji
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (studentArchive.getId() != null) {
            studentArchiveComplete.getRemarks().setErrorCode(userDetailInfo.getErrorCode());
            return studentArchiveComplete;
        }
        return null;
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

    // 2016-7-23将修改学生的信息从teachservice移动到这里
    public UserDetailInfo modfiyUserInfo(UserDetailInfo userDetailInfo, Boolean isBindingMobile)
            throws Exception {
        UserDetailInfo detailInfo = null;
        try {
            detailInfo = updateStudentName(userDetailInfo, isBindingMobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailInfo;
    }

    public UserDetailInfo updateStudentName(UserDetailInfo userDetailInfo, Boolean isBindingMobile) {
        //2016-9-5 新增参数 isBindingMobile 表示是否绑定学生手机号
        if (isBindingMobile == null) {
            isBindingMobile = false;
        }
        Student student = studentDao.findById(userDetailInfo.getStudentId());
//				Student student = studentService.findOfUser(userDetailInfo.getSchoolId(), userDetailInfo.getUserId());
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
    //根据学生别名获取学生数据   2015-11-13
    public List<Student> getStudentByAlias(String alias, Integer schoolId) {
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(schoolId);
        studentCondition.setIsDelete(false);
        studentCondition.setAlias(alias);
        List<Student> list = studentDao.findStudentByOnlyCondition(studentCondition, null, null);
        return list;
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
        }
    }

}
