package platform.szxyzxx.web.bbx.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import platform.education.clazz.model.BpBwAttendanceTime;
import platform.education.clazz.model.BpBwAttendances;
import platform.education.clazz.model.BwGradeSyllabus;
import platform.education.clazz.model.BwSyllabus;
import platform.education.clazz.model.SchoolCourse;
import platform.education.clazz.service.BpBwAttendanceTimeService;
import platform.education.clazz.service.BpBwAttendancesService;
import platform.education.clazz.service.BwGradeSyllabusService;
import platform.education.clazz.service.BwSyllabusService;
import platform.education.clazz.service.SchoolCourseService;
import platform.education.clazz.vo.BwSyllabusCondition;
import platform.education.clazz.vo.SchoolCourseCondition;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SyllabusService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.szxyzxx.web.bbx.contants.BpAttendancesContants;
import platform.szxyzxx.web.bbx.util.DateUtil;

public class BpAttendanceJob {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

    @Resource
    private BpBwAttendancesService bpBwAttendancesService;

    @Resource
    private BpBwAttendanceTimeService bpBwAttendanceTimeService;

    @Resource
    private SchoolService schoolService;

    @Resource
    private GradeService gradeService;

    @Resource
    private TeamService teamService;

    @Resource
    private StudentService studentService;

    @Resource
    private SchoolTermCurrentService schoolTermCurrentService;

    @Resource
    private SchoolCourseService schoolCourseService;

    @Resource
    private BwSyllabusService bwSyllabusService;

    @Resource
    private SyllabusService syllabusService;

    @Resource
    private BwGradeSyllabusService bwGradeSyllabusService;

    public void executeInternal() {
        try {
            //获取昨天日期 yyyy-MM-dd
            Date today = DateUtil.getDay(new Date(), 1, false);
            Date yesterday = sdf.parse(sdf.format(today));
            int weekNum = DateUtil.getWeekOfDateInt(yesterday);

            BpBwAttendances bwAttendances = null;
            //获取全部学校
            SchoolCondition schoolCondition = new SchoolCondition();
            schoolCondition.setDelete(false);
            List<School> schoolList = schoolService.findSchoolByCondition(schoolCondition, null, null);
            if (schoolList != null && schoolList.size() > 0) {
                for (School school : schoolList) {
                    //获取该学校是否走班
                    Boolean isOpen = false;
                    SchoolCourseCondition schoolCourseCondition = new SchoolCourseCondition();
                    schoolCourseCondition.setSchoolId(school.getId());
                    List<SchoolCourse> schoolCourseList = schoolCourseService.findSchoolCourseByCondition(schoolCourseCondition);
                    if (schoolCourseList != null && schoolCourseList.size() > 0) {
                        isOpen = schoolCourseList.get(0).getIsOpen();
                    }

                    SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(school.getId());
                    //获取学校全部年级
                    List<Grade> gradeList = this.gradeService.findGrageListBySchoolId(school.getId());
                    if (gradeList != null && gradeList.size() > 0) {
                        for (Grade grade : gradeList) {
                            //迟到列表
                            BpBwAttendanceTime lateTimeAttendanc = this.bpBwAttendanceTimeService
                                    .findBpBwAttendanceTimeByGradeIdAndType(grade.getId(), BpAttendancesContants.ATTENDANCE_LATE_TIME);
                            BpBwAttendanceTime outEarlyTimeAttendanc = this.bpBwAttendanceTimeService
                                    .findBpBwAttendanceTimeByGradeIdAndType(grade.getId(), BpAttendancesContants.ATTENDANCE_OUT_EARLY_TIME);
                            if (lateTimeAttendanc == null || outEarlyTimeAttendanc == null) {
                                continue;
                            }
                            Date lateTime = sdf1.parse(sdf1.format(lateTimeAttendanc.getAttendanceTime()));
                            Date outEarlyTime = sdf1.parse(sdf1.format(outEarlyTimeAttendanc.getAttendanceTime()));

                            List<Team> teamList = this.teamService.findTeamOfGrade(grade.getId());
                            if (teamList != null && teamList.size() > 0) {
                                for (Team team : teamList) {
                                    if (isOpen) {
                                        BwSyllabusCondition syllabusCondition = new BwSyllabusCondition();
                                        syllabusCondition.setTeamId(team.getId());
                                        syllabusCondition.setTermCode(stc.getSchoolTermCode());
                                        List<BwSyllabus> syllabusList = bwSyllabusService.findBwSyllabusByCondition(syllabusCondition);
                                        if (syllabusList != null && syllabusList.size() > 0) {
                                            BwSyllabus syllabus = syllabusList.get(0);
                                            BwGradeSyllabus bwGradeSyllabus =
                                                    this.bwGradeSyllabusService.findBwGradeSyllabusById(syllabus.getGradeSyllabusId());
                                            if (bwGradeSyllabus != null) {
                                                String dayPlans = bwGradeSyllabus.getDaysPlan();
                                                String[] dayPlanArr = dayPlans.split(",");
                                                if (dayPlanArr != null && dayPlanArr.length > 0) {
                                                    Boolean flag = false;
                                                    for (String dayPlan : dayPlanArr) {
                                                        if (Integer.valueOf(dayPlan).equals(weekNum)) {
                                                            flag = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!flag) {
                                                        continue;
                                                    }
                                                } else {
                                                    continue;
                                                }
                                            } else {
                                                continue;
                                            }
                                        } else {
                                            continue;
                                        }
                                    } else {
                                        Syllabus syllabus = this.syllabusService.getTeamSyllabus(team.getId(), stc.getSchoolTermCode());
                                        if (syllabus != null) {
                                            String dayPlans = syllabus.getDaysPlan();
                                            String[] dayPlanArr = dayPlans.split(",");
                                            if (dayPlanArr != null && dayPlanArr.length > 0) {
                                                Boolean flag = false;
                                                for (String dayPlan : dayPlanArr) {
                                                    if (Integer.valueOf(dayPlan).equals(weekNum)) {
                                                        flag = true;
                                                        break;
                                                    }
                                                }
                                                if (!flag) {
                                                    continue;
                                                }
                                            } else {
                                                continue;
                                            }
                                        } else {
                                            continue;
                                        }

                                    }

                                    List<Student> studentList = this.studentService.findStudentByTeamId(team.getId());
                                    if (studentList != null && studentList.size() > 0) {
                                        for (Student student : studentList) {
                                            bwAttendances = this.bpBwAttendancesService
                                                    .findBwAttendancesByStudentIdAndDay(student.getUserId(), yesterday);
                                            if (bwAttendances == null) {
                                                bwAttendances = new BpBwAttendances();
                                                bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
                                                bwAttendances.setStudentId(student.getUserId());
                                                bwAttendances.setSchoolYear(stc.getSchoolYear());
                                                bwAttendances.setSchoolId(team.getSchoolId());
                                                bwAttendances.setGradeId(team.getGradeId());
                                                bwAttendances.setTeamId(team.getId());
                                                bwAttendances.setTermCode(stc.getSchoolTermCode());
                                                bwAttendances.setAttendanceDay(yesterday);
                                                bwAttendances.setIsAbsent(true);
                                                bwAttendances = this.bpBwAttendancesService.add(bwAttendances);
                                            } else if (!bwAttendances.getIsLeave()) {
                                                //若该学生没有请假，则标记其他考情状态
                                                Date inTime = bwAttendances.getInTime();
                                                Date outTime = bwAttendances.getOutTime();
                                                if (inTime == null) {
                                                    //第一次没签，代表整天都没签
                                                    bwAttendances.setIsAbsent(true);
                                                } else {
                                                    //第一次签到迟于上学时间，标记迟到
                                                    if (sdf1.parse(sdf1.format(inTime)).after(lateTime)) {
                                                        bwAttendances.setIsLate(true);
                                                    }
                                                    //第二次没签，标记早退
                                                    if (outTime == null) {
                                                        bwAttendances.setIsOutEarly(true);
                                                    } else {
                                                        //第二次签到早于放学时间，标记早退
                                                        if (sdf1.parse(sdf1.format(outTime)).before(outEarlyTime)) {
                                                            bwAttendances.setIsOutEarly(true);
                                                        }
                                                    }
                                                }
                                            }
                                            bwAttendances = this.bpBwAttendancesService.modify(bwAttendances);
                                        }
                                    }
                                }
                            }



							/*BpBwAttendancesCondition bpBwAttendancesCondition = new BpBwAttendancesCondition();
							//获取该年级考勤时间
							//迟到列表
							BpBwAttendanceTime lateTimeAttendanc = this.bpBwAttendanceTimeService
									.findBpBwAttendanceTimeByGradeIdAndType(grade.getId(), BpAttendancesContants.ATTENDANCE_LATE_TIME);
							if(lateTimeAttendanc != null){
								bpBwAttendancesCondition.setGradeId(grade.getId());
								bpBwAttendancesCondition.setLateTime(sdf1.format(lateTimeAttendanc.getAttendanceTime()));

								bpBwAttendancesCondition.setCheckType(BpAttendancesContants.ADMINISTRATION);
								bpBwAttendancesCondition.setAttendanceDay(yesterday);
								List<BpBwAttendances> lateAttendanceList = this.bpBwAttendancesService.findBwAttendancesByCondition(bpBwAttendancesCondition);
								if(lateAttendanceList != null && lateAttendanceList.size() > 0){
									for(BpBwAttendances bpBwAttendances: lateAttendanceList){
										bpBwAttendances.setIsLate(true);
										this.bpBwAttendancesService.modify(bpBwAttendances);
									}
								}
							}

							BpBwAttendanceTime outEarlyTimeAttendanc = this.bpBwAttendanceTimeService
									.findBpBwAttendanceTimeByGradeIdAndType(grade.getId(), BpAttendancesContants.ATTENDANCE_OUT_EARLY_TIME);
							if(outEarlyTimeAttendanc != null){
								//早退列表
								bpBwAttendancesCondition.setLateTime(null);
								bpBwAttendancesCondition.setOutEarlyTime(sdf1.format(outEarlyTimeAttendanc.getAttendanceTime()));
								List<BpBwAttendances> outEarlyAttendanceList = this.bpBwAttendancesService.findBwAttendancesByCondition(bpBwAttendancesCondition);
								if(outEarlyAttendanceList != null && outEarlyAttendanceList.size() > 0){
									for(BpBwAttendances bpBwAttendances: outEarlyAttendanceList){
										bpBwAttendances.setIsOutEarly(true);
										this.bpBwAttendancesService.modify(bpBwAttendances);
									}
								}
							}*/

                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
