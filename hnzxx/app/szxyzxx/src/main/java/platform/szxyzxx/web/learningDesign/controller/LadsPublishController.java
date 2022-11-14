package platform.szxyzxx.web.learningDesign.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.learningDesign.contants.LearningDesignType;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningDesignPublish;
import platform.education.learningDesign.model.LearningDesignPublishedRecord;
import platform.education.learningDesign.model.LearningDesignUserRecord;
import platform.education.learningDesign.service.LearningDesignPrepareService;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.service.LearningDesignUserRecordService;
import platform.education.learningDesign.vo.LadsLearningDesignStudyListVo;
import platform.education.learningDesign.vo.LearningDesignRelateVo;
import platform.education.learningDesign.vo.LearningDesignStudyListVo;
import platform.education.learningDesign.vo.LearningDesignStudyRecordVo;
import platform.education.learningDesign.vo.LearningDesignVo;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.message.service.MessageService;
import platform.education.micro.model.MiMicroPersonGroup;
import platform.education.micro.model.MiMicroPersonGroupUserId;
import platform.education.micro.service.MiMicroPersonGroupService;
import platform.education.micro.service.MiMicroPersonGroupUserIdService;
import platform.education.micro.vo.MiMicroPersonGroupCondition;
import platform.education.micro.vo.MiMicroPersonGroupUserIdCondition;
import platform.education.resource.contants.StudyFinishedFlag;
import platform.education.resource.utils.IconUtil;
import platform.service.storage.service.EntityFileService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.TagsTypeContans;
import platform.szxyzxx.web.resource.vo.MyResourceVo;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping(value = "/ladspublish")
public class LadsPublishController {

    //资源文件
    @Resource
    private EntityFileService entityFileService;
    //课件
    @Resource
    private LearningDesignService learningDesignService;
    //课件发布
    @Resource
    private LearningDesignPrepareService learningDesignPrepareService;
    //课件学习记录
    @Resource
    private LearningDesignUserRecordService learningDesignUserRecordService;
    //任课教师
    @Resource
    private TeamTeacherService teamTeacherService;
    // 当前学期
    @Resource
    private SchoolTermCurrentService schoolTermCurrentService;
    // 教师
    @Resource
    private TeacherService teacherService;
    // 学生
    @Resource
    private StudentService studentService;
    // 年级
    @Resource
    private GradeService gradeService;
    // 班
    @Resource
    private TeamService teamService;
    //信息中心
    @Resource
    private MessageService messageService;
    @Resource
    private MiMicroPersonGroupService miMicroPersonGroupService;
    @Resource
    private MiMicroPersonGroupUserIdService microPersonGroupUserIdService;
    
    private static final String COMMON_DIR = "learningDesign/common";
    private static final String DIR = "learningDesign/ladspublish";

    @RequestMapping(value = "/prepareLessonIndex")
    public String prepareLessonIndex(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {

        return DIR + "/prepareLessonIndex";
    }

    @RequestMapping(value = "/prepareLesson")
    public String prepareLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        Map classGradeMap = getClassGradeMap(user, true, true,true);
        request.setAttribute("classGradeMap", classGradeMap);
        return DIR + "/prepareLesson";
    }

    @RequestMapping(value = "/publishManagerIndex")
    public String publishManagerIndex(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws ParseException {
        Map classGradeMap = getClassGradeMap(user, false, false,true);
        request.setAttribute("relateId", request.getParameter("relateId"));
        request.setAttribute("classGradeMap", classGradeMap);
        return DIR + "/publishManagerIndex";
    }

    @RequestMapping(value = "/publishManager")
    public String publishManager(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws ParseException {
        String relateId = request.getParameter("relateId");
        String relateType = request.getParameter("relateType");
        Integer relateIdint = Integer.parseInt(relateId);
        List<Student> stList = new ArrayList<Student>();
        if (relateType == TagsTypeContans.TEAM_ID || relateType.equals(TagsTypeContans.TEAM_ID)) {
        	stList = this.studentService.findStudentOfTeam(relateIdint);
		}else if (relateType == TagsTypeContans.GRADE_ID || relateType.equals(TagsTypeContans.GRADE_ID)) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(relateIdint);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
					for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
						Student student = studentService.findOfUser(user.getSchoolId(), miMicroPersonGroupUserId.getUserId());
						stList.add(student);
					}
		}
        List<LearningDesignRelateVo> mlrList = this.learningDesignPrepareService.searchPublishedLesson(user.getId(), Integer.parseInt(relateId), LearningDesignType.LADS_LEARNINGDESIGN, page);
        List<LearningDesignRelateVo> reMlrList = new ArrayList<LearningDesignRelateVo>();
        for (LearningDesignRelateVo rv : mlrList) {
            Integer finishedCount = 0;
            Integer unFinishedCount = 0;
            for (Student st : stList) {
                LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(rv.getPublishMicroLessonId(), st.getUserId(), st.getName(), st.getStudentNumber());
                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    finishedCount++;
                } 
            }
            rv.setFinishedCount(finishedCount);
            unFinishedCount = stList.size()-finishedCount;
            rv.setUnFinishedCount(unFinishedCount);
            reMlrList.add(rv);
        }
        request.setAttribute("relateId", relateId);
        request.setAttribute("mlrList", reMlrList);
        return DIR + "/publishManager";
    }

    @RequestMapping(value = "/publishLesson")
    public String publishLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws ParseException, IOException {
        String publishData = request.getParameter("publishData");
        JSONObject obj = JSONObject.fromObject(publishData);
        String startDate = (String) obj.get("startDate");
        String finishedDate = (String) obj.get("finishedDate");
        String startClock = (String) obj.get("startClock");
        String finishedClock = (String) obj.get("finishedClock");
        String ldId = (String) obj.get("ldId");
        String microId = (String) obj.get("microId");
        String title = (String) obj.get("title");
        JSONArray classList = (JSONArray) obj.get("classList");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date sd = sdf.parse(startDate + " " + startClock);
        Date fd = sdf.parse(finishedDate + " " + finishedClock);
        //使用复制后的ldId
        JSONArray array = new JSONArray();
        JSONObject o = new JSONObject();
        o.put("id", microId);
        o.put("ldId", ldId);
        o.put("title", title);
        array.add(o);
        LearningDesignPublish mlp = this.learningDesignPrepareService.publishLearningDesign(array, classList, sd, fd, null, user.getId(), user.getRealName(), LearningDesignType.LADS_LEARNINGDESIGN);

        //发送通知
        sendMessageToStudent(user.getId(), user.getRealName(), classList, user.getSchoolId(),user);
        return null;
    }

    private void sendMessageToStudent(Integer publisherId, String teacherName, JSONArray classList, Integer schoolId,@CurrentUser UserInfo user) {
        Message message = new Message();
        message.setAppId(SysContants.SYSTEM_APP_ID);
        message.setContent("您有新的课件学习！");
        message.setPosterId(publisherId);
        message.setPosterName(teacherName);
        message.setRecordStatus(StatusDefaultContans.ZERO);
        message.setTag(MessageCenterContants.PATH_CODE_MICRO);
        List<Integer> receiverIdList = new ArrayList<Integer>();
        List<Integer> receiverIdList2 = new ArrayList<Integer>();
        for (int i = 0; i < classList.size(); i++) {
            JSONObject receivers = (JSONObject) classList.get(i);
            String receiverId = (String) receivers.get("relateId");
            String relateType = (String) receivers.get("relateType");
            if (relateType == TagsTypeContans.GRADE_ID || relateType.equals(TagsTypeContans.GRADE_ID)) {
            	receiverIdList2.add(Integer.parseInt(receiverId));
			}else if (relateType == TagsTypeContans.TEAM_ID || relateType.equals(TagsTypeContans.TEAM_ID)) {
				receiverIdList.add(Integer.parseInt(receiverId));
			}
        }
        if (receiverIdList !=null && !receiverIdList.isEmpty()) {
        	List<Student> studentList = this.studentService.findStudentListByListId(receiverIdList, TagsTypeContans.TEAM_ID, user.getSchoolId());
        	receiverIdList = getUserId(studentList);
        	PushMessageUtil.sendMessage(message, receiverIdList);
//          for(Integer userId : receiverIdList){
//          	PushMessageUtil.pushMessage(userId);
//          }
        	PushMessageUtil.pushMessageList(receiverIdList);
		}else if (receiverIdList2 !=null && !receiverIdList2.isEmpty()) {
			List<Integer> studentIds = new ArrayList<Integer>();
			for (Integer id : receiverIdList2) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(id);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
				for (MiMicroPersonGroupUserId MiMicroPersonGroupUserId : list) {
					studentIds.add(MiMicroPersonGroupUserId.getUserId());
					MiMicroPersonGroupUserId.setType("1");
					microPersonGroupUserIdService.modify(MiMicroPersonGroupUserId);
				}
			}
		     PushMessageUtil.sendMessage(message, studentIds);
	         PushMessageUtil.pushMessageList(studentIds);
		}
    }

    private List<Integer> getUserId(List<Student> studentList) {
        List<Integer> idList = new ArrayList<Integer>();
        if (studentList.size() > 0 && studentList != null) {
            for (Student student : studentList) {
                idList.add(student.getUserId());
            }
        }
        return idList;
    }
    
    @RequestMapping(value = "/myLearningDesign")
    public String myLearningDesign(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        page.setPageSize(5);
        List lessonList = new ArrayList();
        List<LearningDesignVo> examList = this.learningDesignService.searchLearningDesign(SysContants.SYSTEM_APP_ID, page, user.getId(), null, LearningDesignType.LADS_LEARNINGDESIGN, null, null, null);
        for (LearningDesignVo em : examList) {
            MyResourceVo mrvo = new MyResourceVo();
            mrvo.setResEnt(em);
            mrvo.setIconType(IconUtil.setIcon("lads"));
            lessonList.add(mrvo);
        }
        request.setAttribute("ladsType", LearningDesignType.LADS_LEARNINGDESIGN);
        request.setAttribute("microLessonList", lessonList);
        if (index != null && !"".equals(index)) {
            return DIR + "/myLearningDesignIndex";
        } else {
            return DIR + "/myLearningDesignList";
        }
    }

    @RequestMapping(value = "/ajaxGetStudyJson")
    public String ajaxGetStudyJson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String microPublishId = request.getParameter("microPublishId");
        JSONArray array = learningDesignPrepareService.getPublishedPlayJson(microPublishId);
        List voList = new ArrayList();
        Integer finishedCount = 0;
        for (int i = 0; i < array.size(); i++) {
            MyResourceVo mrvo = new MyResourceVo();
            LadsLearningDesignStudyListVo vo = new LadsLearningDesignStudyListVo();
            JSONObject obj = array.getJSONObject(i);
            String microId = (String) obj.get("id");
            LearningDesignUserRecord ur = this.learningDesignUserRecordService.getUniqueRecord(user.getId(), microId, microPublishId);
            mrvo.setIconType(IconUtil.setIcon("lads"));
            if (ur != null && ur.getFinishedFlag() != null) {
                if (ur.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    finishedCount++;
                }
            }
            vo.setRecord(ur);
            vo.setLearningDesign(obj);
            mrvo.setResEnt(vo);
            voList.add(mrvo);
        }
        request.setAttribute("microPublishId", microPublishId);
        request.setAttribute("finishedCount", finishedCount);
        request.setAttribute("microList", voList);
        return DIR + "/studyMicroList";
    }

    @RequestMapping(value = "/studyList")
    public String studyList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws IOException {
        Student s = this.studentService.findStudentById(user.getStudentId());
        if (s != null) {
        	Team team = this.teamService.findCurrentTeamOfStudent(s.getUserId(), user.getSchoolYear());
            Integer teamId = team != null ? team.getId() : null;
            if (teamId != null) {
                Date fdate = new Date();
                List<LearningDesignRelateVo> mlrvList = this.learningDesignPrepareService.searchHistoryPublishedLesson(null, teamId, null, LearningDesignType.LADS_LEARNINGDESIGN, fdate, false, page);
                request.setAttribute("microList", mlrvList);
            } else {
                request.setAttribute("errorFlag", "no_class");
            }
        } else {
            request.setAttribute("errorFlag", "not_a_student");
        }
        return DIR + "/studyList";
    }

    @RequestMapping(value = "/studyHistory")
    public String studyHistory(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws IOException {
        Student s = this.studentService.findStudentById(user.getStudentId());
        String index = request.getParameter("index");
        if (s != null) {
        	Team team = this.teamService.findCurrentTeamOfStudent(s.getUserId(), user.getSchoolYear());
            Integer teamId = team != null ? team.getId() : null;
            if (teamId != null) {
                Date fdate = new Date();
                List<LearningDesignStudyRecordVo> msrvList = new ArrayList();
                List<String> subjectList = this.learningDesignPrepareService.findSubjectNameByLearningDesign(null, teamId, fdate, true);
                String subjectName = request.getParameter("subjectName");
                //搜索全部
                if ("all".equals(subjectName)) {
                    subjectName = null;
                }
                List<LearningDesignRelateVo> mlrvList = this.learningDesignPrepareService.searchHistoryPublishedLesson(null, teamId, subjectName, LearningDesignType.LADS_LEARNINGDESIGN, fdate, true, page);
                if (mlrvList != null && mlrvList.size() > 0) {
                    for (LearningDesignRelateVo mr : mlrvList) {
                        LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(mr.getPublishMicroLessonId(), user.getId(), s.getName(), s.getStudentNumber());
                        String rsn = mr.getRelateName().substring(mr.getRelateName().indexOf("[") + 1, mr.getRelateName().indexOf("]"));
                        LearningDesignStudyRecordVo vo = new LearningDesignStudyRecordVo();
                        vo.setMlrv(mr);
                        vo.setMpr(mpr);
                        msrvList.add(vo);
                    }
                }
                request.setAttribute("subjectList", subjectList);
                request.setAttribute("recordList", msrvList);
            } else {
                request.setAttribute("errorFlag", "no_class");
            }
        } else {
            request.setAttribute("errorFlag", "not_a_student");
        }
        if (index != null && !"".equals(index)) {
            return DIR + "/studyHistoryIndex";
        } else {
            return DIR + "/studyHistoryList";
        }
    }
    
    @RequestMapping(value = "/resetDate")
    public String resetDate(HttpServletRequest request, HttpServletResponse response) {
        String publishId = request.getParameter("publishId");
        String relateId = request.getParameter("relateId");
        String startDate = request.getParameter("startDate");
        String finishedDate = request.getParameter("finishedDate");
        String[] ss = startDate.split(" ");
        String[] fs = finishedDate.split(" ");
        request.setAttribute("publishId", publishId);
        request.setAttribute("startDate", ss[0]);
        request.setAttribute("finishedDate", fs[0]);
        request.setAttribute("relateId", relateId);
        request.setAttribute("startClock", Integer.parseInt(ss[1]));
        request.setAttribute("finishedClock", Integer.parseInt(fs[1]));
        return DIR + "/resetDate";
    }
    
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String resId = request.getParameter("resId");
        Integer  flag = this.learningDesignService.deleteMyLearningDesign(SysContants.SYSTEM_APP_ID, user.getId(), resId);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(flag);
        return null;
    }

    private Map getClassGradeMap(UserInfo user, boolean includeSubject, boolean includeSameClass,boolean includeType) {
        Map classGradeMap = new LinkedHashMap();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
        Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), user.getId());
        if (teacher != null && schoolTermCurrent != null) {
            TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setTeacherId(teacher.getId());
            //1 班主任  2 任课教师
            teamTeacherCondition.setType(2);
            teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
            List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, Order.asc("grade_id"));
            MiMicroPersonGroupCondition miMicroPersonGroupCondition = new MiMicroPersonGroupCondition();
            miMicroPersonGroupCondition.setUserId(teacher.getUserId());
            for (TeamTeacher tt : teamTeacherList) {
                Grade grade = this.gradeService.findGradeById(tt.getGradeId());
                if (grade != null) {
                    List<Map> classList = new ArrayList();
                    String viewName = grade.getName() + "&&" + grade.getUniGradeCode();
                    if (classGradeMap.containsKey(viewName)) {
                        classList = (List<Map>) classGradeMap.get(viewName);
                    }
                    miMicroPersonGroupCondition.setGradeId(tt.getGradeId());
                	List<MiMicroPersonGroup> personGroups = this.miMicroPersonGroupService.findMiMicroPersonGroupByCondition(miMicroPersonGroupCondition);
                	if (!personGroups.isEmpty() && personGroups != null) {
                		if (!classGradeMap.containsKey(viewName)) {
                			for (MiMicroPersonGroup personGroup : personGroups) {
                				Map mmap = new HashMap();
                				String personGroupName = personGroup.getName();
                				if (includeType) {
									personGroupName = personGroupName + "&&"+TagsTypeContans.GRADE_ID;
								}
                    			mmap.put(personGroup.getId(), personGroupName);
                    			classList.add(mmap);
        					}
                        }
                		
					}
                    Team team = this.teamService.findTeamById(tt.getTeamId());
                    if (team != null) {
                        Map map = new HashMap();
                        String classSubjectName = team.getName();
                        if (includeSubject) {
                            classSubjectName = classSubjectName + "   [" + tt.getSubjectName() + "]" + "&&" + tt.getSubjectCode();
                        }
                        map.put(team.getId(), classSubjectName);
                        if (includeSameClass) {
                            classList.add(map);
                        } else if (!classList.contains(map)) {
                            classList.add(map);
                        }
                    }
                    classGradeMap.put(viewName, classList);
                }
            }
        }
        return classGradeMap;
    }

    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }

}
