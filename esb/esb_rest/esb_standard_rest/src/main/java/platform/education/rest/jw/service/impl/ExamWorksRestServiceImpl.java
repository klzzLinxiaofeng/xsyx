package platform.education.rest.jw.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.model.ExamWorksSubjectTemplate;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ExamStatMajorStudentService;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.service.ExamStudentService;
import platform.education.generalTeachingAffair.service.ExamWorksGradeService;
import platform.education.generalTeachingAffair.service.ExamWorksService;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectService;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectTemplateService;
import platform.education.generalTeachingAffair.service.ExamWorksTeamService;
import platform.education.generalTeachingAffair.service.ExamWorksTeamSubjectService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.ScoreAnalysisHandleService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectVo;
import platform.education.generalTeachingAffair.vo.ExamWorksVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreAnalysisDataVo;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreSortVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.ExamWorksRestService;
import platform.education.rest.util.DateUtil;
import platform.education.rest.util.NameUtil;

/**
 * Created by Administrator on 2018/1/27.
 */
public class ExamWorksRestServiceImpl implements ExamWorksRestService {

    @Autowired
    @Qualifier("examWorksService")
    private ExamWorksService examWorksService;
    
    @Autowired
    @Qualifier("examWorksGradeService")
    private ExamWorksGradeService examWorksGradeService;
    
    @Autowired
    @Qualifier("examWorksTeamSubjectService")
    private ExamWorksTeamSubjectService examWorksTeamSubjectService;

    @Autowired
    @Qualifier("examWorksSubjectService")
    private ExamWorksSubjectService examWorksSubjectService;
    
    @Autowired
    @Qualifier("examWorksSubjectTemplateService")
    private ExamWorksSubjectTemplateService examWorksSubjectTemplateService;

    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
    
    @Autowired
    @Qualifier("pjExamService")
    private PjExamService pjExamService;
    
    @Autowired
    @Qualifier("examStudentService")
    private ExamStudentService examStudentService;

    @Autowired
    @Qualifier("examStatService")
    private ExamStatService examStatService;
    
    @Autowired
    @Qualifier("examStatMajorStudentService")
    private ExamStatMajorStudentService examStatMajorStudentService;

    @Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("examWorksTeamService")
    private ExamWorksTeamService examWorksTeamService;
    
    @Autowired
    @Qualifier("scoreAnalysisHandleService")
    private ScoreAnalysisHandleService scoreAnalysisHandleService;
    
    @Override
    public Object getMajorExamWorks(Integer schoolId, String schoolYear, String termCode, String examType) {
        if(schoolId == null){
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "schoolId参数不能为空", "schoolId"));
        }
        if(schoolYear == null || "".equals(schoolYear)){
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "schoolYear参数不能为空", "schoolYear"));
        }
        if(termCode == null || "".equals(termCode)){
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "termCode参数不能为空", "termCode"));
        }

        List<Object> list = null;
        try {
            list = new ArrayList<>();
            if (examType != null && !"".equals(examType)) {
                if ("01".equals(examType) || "02".equals(examType) || "03".equals(examType) || "12".equals(examType) || "20".equals(examType)) {
                    Map<String, Object> map = getExamWorks(schoolId, schoolYear, termCode, examType);
                    list.add(map);
                } else {
                    return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR,
                            new ResponseInfo("参数错误","测试类型不存在","examType"));
                }
            } else {
                Map<String, Object> midMap = getExamWorks(schoolId, schoolYear, termCode, "01");
                Map<String, Object> finalMap = getExamWorks(schoolId, schoolYear, termCode, "02");
                Map<String, Object> practiceMap = getExamWorks(schoolId, schoolYear, termCode, "03");
                Map<String, Object> monthlyMap = getExamWorks(schoolId, schoolYear, termCode, "12");
                list.add(midMap);
                list.add(finalMap);
                list.add(practiceMap);
                list.add(monthlyMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
        }

        return new ResponseVo<Object>("0", list);
    }

    private String getTypeName(String examType) {
        String typeName = "";
        if ("01".equals(examType)) {
            typeName = "期中考试";
        }
        if ("02".equals(examType)) {
            typeName = "期末考试";
        }
        if ("03".equals(examType)) {
            typeName = "模拟考试";
        }
        if ("12".equals(examType)) {
            typeName = "月考测试";
        }
        if ("20".equals(examType)) {
            typeName = "班级测试";
        }
        return typeName;
    }

    private Map<String, Object> getExamWorks(Integer schoolId, String schoolYear, String termCode, String examType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();
        map.put("examType", examType);
        map.put("typeName", getTypeName(examType));
        List<Object> examList = new ArrayList<>();
        List<ExamWorks> examWorksList = examWorksService.findMajorExamWorksByType(schoolId, schoolYear, termCode, examType);
        if (examWorksList != null && examWorksList.size() > 0) {
            for (ExamWorks examWorks : examWorksList) {
                Map<String, Object> worksMap = new HashMap<>();
                worksMap.put("name", examWorks.getName());
                worksMap.put("examWorksId", examWorks.getId());
                worksMap.put("beginDate", sdf.format(examWorks.getExamDateBegin()));
                worksMap.put("endDate", sdf.format(examWorks.getExamDateEnd()));
                examList.add(worksMap);
            }
        }
        map.put("examList", examList);
        return map;
    }

    @Override
    public Object getExamWorksContext(Integer examWorksId, String subjectCode, Integer gradeId) {
        if(gradeId == null){
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "gradeId参数不能为空", "gradeId"));
        }
        if(subjectCode == null || "".equals(subjectCode)){
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "subjectCode参数不能为空", "subjectCode"));
        }

        Map<String, Object> map = new HashMap<>();
        try {
            if (examWorksId != null) {
                ExamWorksSubject worksSubject = examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
                if (worksSubject != null) {
                    map.put("fullScore", worksSubject.getFullScore());
                    map.put("highScore", worksSubject.getHighScore());
                    map.put("lowScore", worksSubject.getLowScore());
                    map.put("passScore", worksSubject.getPassScore());
                }
            } else {
                Grade grade = gradeService.findGradeById(gradeId);
                ExamWorksSubjectTemplate subjectTemplate = examWorksSubjectTemplateService.findSubjectTemplate(grade.getSchoolId(), gradeId, subjectCode);
                if (subjectTemplate != null) {
                    map.put("fullScore", subjectTemplate.getFullScore());
                    map.put("highScore", subjectTemplate.getHighScore());
                    map.put("lowScore", subjectTemplate.getLowScore());
                    map.put("passScore", subjectTemplate.getPassScore());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
        }
        return new ResponseVo<Object>("0", map);
    }


    @Override
    public Object getExamWorksContextList(Integer examWorksId, Integer userId, String type) {
        if (examWorksId == null) {
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "examWorksId参数不能为空", "examWorksId"));
        }
        List<Map<String, Object>> list = new ArrayList<>();
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks == null) {
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, new ResponseInfo("找不到数据", "获取不到对应的考务信息", "examWorksId"));
        }
        Integer schoolId = examWorks.getSchoolId();
        String schoolYear = examWorks.getSchoolYear();
        Teacher teacher = teacherService.findOfUser(schoolId, userId);
        if (teacher == null) {
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, new ResponseInfo("找不到数据", "无匹配的教师", "userId"));
        }

        try {
            List<ExamWorksTeamSubjectVo> context = examWorksTeamSubjectService.findExamWorksContext(examWorksId);
            List<ExamWorksTeamSubjectVo> filtrateDate = new ArrayList<>();      //存放筛选后数据
            // 管理员返回结果不变，教师根据任教班级筛选
            if ("1".equals(type)) {
                TeamTeacherCondition condition = new TeamTeacherCondition();
                condition.setSchoolId(schoolId);
                condition.setSchoolYear(schoolYear);
                condition.setTeacherId(teacher.getId());
                List<TeamTeacherVo> teamTeacherVos = teamTeacherService.findTeamTeacherVoByCondition(condition);
                for (ExamWorksTeamSubjectVo ewts : context) {
                    boolean flag = false;
                    for (TeamTeacherVo vo : teamTeacherVos) {
                        //班主任--所有科目；科任教师--单班单科
                        if ((vo.getType() == 1 && ewts.getTeamId().equals(vo.getTeamId())) ||
                                (vo.getType() == 2 && ewts.getTeamId().equals(vo.getTeamId()) && ewts.getSubjectCode().equals(vo.getSubjectCode()))) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        filtrateDate.add(ewts);
                    }
                }

            } else {
                filtrateDate = context;
            }

            Map<Integer, List<ExamWorksTeamSubjectVo>> gMap = new HashMap<>();
            List<ExamWorksTeamSubjectVo> teamVos = null;
            for (ExamWorksTeamSubjectVo vo : filtrateDate) {
                //将查询的结果按 年级划分
                Integer gradeId = vo.getGradeId();
                if (gMap.containsKey(gradeId)) {
                    teamVos = gMap.get(gradeId);
                } else {
                    teamVos = new ArrayList<>();
                }
                teamVos.add(vo);
                gMap.put(gradeId, teamVos);
            }

            for (Integer gradeId : gMap.keySet()) {
                List<ExamWorksTeamSubjectVo> vos = gMap.get(gradeId);
                Map<Integer, List<ExamWorksTeamSubjectVo>> tMap = new HashMap<>();
                List<ExamWorksTeamSubjectVo> subjectVos = null;
                for (ExamWorksTeamSubjectVo vo : vos) {
                    //按班级划分
                    Integer teamId = vo.getTeamId();
                    if (tMap.containsKey(teamId)) {
                        subjectVos = tMap.get(teamId);
                    } else {
                        subjectVos = new ArrayList<>();
                    }
                    subjectVos.add(vo);
                    tMap.put(teamId, subjectVos);
                }

                List<Object> teamList = new ArrayList<>();
                for (Integer teamId : tMap.keySet()) {
                    List<ExamWorksTeamSubjectVo> subjectInfo = tMap.get(teamId);
                    List<Object> subjectList = new ArrayList<>();
                    for (ExamWorksTeamSubjectVo vo : subjectInfo) {
                        Map<String, Object> subjectMap = new HashMap<>();
                        subjectMap.put("subjectCode", vo.getSubjectCode());
                        subjectMap.put("subjectName", vo.getSubjectName());
                        subjectMap.put("fullScore", vo.getFullScore());
                        subjectMap.put("highScore", vo.getHighScore());
                        subjectMap.put("lowScore", vo.getLowScore());
                        subjectMap.put("passScore", vo.getPassScore());
                        subjectList.add(subjectMap);
                    }
                    Map<String, Object> teamMap = new HashMap<>();
                    teamMap.put("teamId", teamId);
                    teamMap.put("teamName", NameUtil.getAbbrTeamName(subjectInfo.get(0).getTeamName(), subjectInfo.get(0).getTeamNumber()));
                    teamMap.put("teamNumber", subjectInfo.get(0).getTeamNumber());
                    teamMap.put("subjectList", subjectList);
                    teamList.add(teamMap);
                }
                Map<String, Object> gradeMap = new HashMap<>();
                gradeMap.put("gradeId", gradeId);
                gradeMap.put("gradeName", vos.get(0).getGradeName());
                gradeMap.put("gradeCode", vos.get(0).getGradeCode());
                gradeMap.put("teamList", teamList);
                list.add(gradeMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
        }
        sortList(list);

        return new ResponseVo<Object>("0", list);
    }

    private void sortList(List<Map<String, Object>> list) {
        sortGrade(list);
        for (Map<String, Object> gradeMap : list) {
            List<Map<String, Object>> teamList = (List<Map<String, Object>>) gradeMap.get("teamList");
            sortTeam(teamList);
        }
    }

    private void sortGrade(List<Map<String, Object>> list){
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer code1 = Integer.valueOf((String) o1.get("gradeCode"));
                Integer code2 = Integer.valueOf((String) o2.get("gradeCode"));
                if (code1 > code2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    private void sortTeam(List<Map<String, Object>> list){
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer n1 = (Integer) o1.get("teamNumber");
                Integer n2 = (Integer) o2.get("teamNumber");
                if (n1 > n2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    @Override
    public Object getClassExamWorks(Integer schoolId, String schoolYear, String termCode,  Integer userId) {
        if (schoolId == null) {
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "schoolId参数不能为空", "schoolId"));
        }
        if (userId == null) {
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "userId参数不能为空", "userId"));
        }
        if (schoolYear == null || "".equals(schoolYear)) {
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "schoolYear参数不能为空", "schoolYear"));
        }
        if (termCode == null || "".equals(termCode)) {
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
                    new ResponseInfo("参数错误", "termCode参数不能为空", "termCode"));
        }

        Teacher teacher = teacherService.findOfUser(schoolId, userId);
        if (teacher == null) {
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, new ResponseInfo("找不到数据", "无匹配的教师", "userId"));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Object> list = new ArrayList<>();
        try {
            List<ExamWorksVo> voList = examWorksService.findClassExamWorksByTeacherId(schoolId, schoolYear, termCode, teacher.getId(), null, null);
            if (voList != null && voList.size() > 0) {
                for (ExamWorksVo vo : voList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("examWorksId", vo.getId());
                    map.put("gradeId", vo.getGradeId());
                    map.put("gradeName", vo.getGradeName());
                    map.put("teamId", vo.getTeamId());
                    map.put("teamName", NameUtil.getAbbrTeamName(vo.getTeamName(), vo.getTeamNumber()));
                    map.put("subjectCode", vo.getSubjectCode());
                    map.put("subjectName", vo.getSubjectName());
                    map.put("name", vo.getName());
                    map.put("beginDate", sdf.format(vo.getExamDateBegin()));
                    map.put("endDate", sdf.format(vo.getExamDateEnd()));
                    map.put("fullScore", vo.getFullScore());
                    map.put("highScore", vo.getHighScore());
                    map.put("lowScore", vo.getLowScore());
                    map.put("passScore", vo.getPassScore());
                    list.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
        }
        return new ResponseVo<Object>("0", list);
    }

    @Override
    public Object importTeamStudentScore(
    		final Integer schoolId,final String schoolYear, final String termCode,final Integer examWorksId,
    		final  String subjectCode,final Integer gradeId,final Integer teamId,final String examTime,final String dataJson,
    		final Float fullScore,final Float highScore,final Float lowScore, final Float passScore,final Integer examType,final Integer userId) {
    		final ObjectMapper mapper = new ObjectMapper();
    	try {
    		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
            if (examWorks == null) {
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, new ResponseInfo("找不到数据","获取不到对应的考务信息","examWorksId"));
            }
    		final boolean isJoinExam = examWorks.getIsJointExam();
    		//1. 反序列模板数据
    		final List<ScoreAnalysisDataVo> scoreAnalysisData = mapper.readValue(dataJson, new TypeReference<List<ScoreAnalysisDataVo>>(){});
    		//先把学生成绩入库。。。
    		ExamWorksTeamSubject examWorksTeamSubject =	scoreAnalysisHandleService.updateExamDate(examWorksId,teamId,examTime,subjectCode);
    		if(examWorksTeamSubject != null ) {
    			final Integer examId = examWorksTeamSubject.getExamId();
        		final List<ScoreSortVo> list = new ArrayList<ScoreSortVo>();
				ScoreSortVo mp = null;
				//3. 更新pjExamStudent表的score字段
				List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(examId);
				if(examStudentList != null && examStudentList.size() > 0) {
					for(ExamStudent examStudent:examStudentList) {
						for(ScoreAnalysisDataVo scoreAnalysisDataVo:scoreAnalysisData) {
							//找到对应的学生更新得分
                            if (scoreAnalysisDataVo.getScore() >= 0) {
                                if(examStudent.getUserId() - scoreAnalysisDataVo.getUserId() == 0) {
                                    mp = new ScoreSortVo();
                                    mp.setId(examStudent.getId());
                                    mp.setScore(scoreAnalysisDataVo.getScore());
                                    list.add(mp);
                                }
                            }
						}
					}
				}
				
				//批量更新学生数据
				if(list != null && list.size() > 0) {
					examStudentService.batchUpdateExamStudentScore(list.toArray());
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            importData(isJoinExam, mapper, dataJson, examWorksId, examTime, teamId, fullScore, highScore, lowScore, passScore, schoolId, gradeId, subjectCode,scoreAnalysisData,list,examId);
                        }
                    });
                }
        	}
    		
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, new ResponseInfo("参数格式错误","dataJson格式有误","dataJson"));
		}
    	
    	//导入成功更新 pj_exam_works_team_subject表 导入人，导入时间等字段
    	ExamWorksTeamSubject examWorksTeamSubject = this.examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
    	if(examWorksTeamSubject != null) {
    		List<Teacher> teacherList = this.teacherService.findTeacherByUserId(userId);
    		if(teacherList != null && teacherList.size() > 0) {
    			Teacher teacher = teacherList.get(0);
    			examWorksTeamSubject.setPostTeacherId(teacher.getId());
    		}
    		examWorksTeamSubject.setPostTime(new Date());
    		examWorksTeamSubjectService.modify(examWorksTeamSubject);
    	}
    	
        //班级小测导入成功后更新 pj_exam_works表的起止时间
        if (examType == 20) {
            ExamWorks examWorks = new ExamWorks(examWorksId);
            examWorks.setExamDateBegin(DateUtil.strToDate(examTime, "yyyy-MM-dd HH:mm:ss"));
            examWorks.setExamDateEnd(DateUtil.strToDate(examTime, "yyyy-MM-dd HH:mm:ss"));
            examWorksService.modify(examWorks);
        }

    	return new ResponseVo<Object>("0", null);
    }
    
    private void importData(Boolean isJoinExam,ObjectMapper mapper, String dataJson,Integer examWorksId,String examTime,Integer teamId,float fullScore,float highScore,float lowScore,float passScore,Integer schoolId,Integer gradeId,String subjectCode,List<ScoreAnalysisDataVo> scoreAnalysisData,List<ScoreSortVo> list,Integer examId) {
    	try {
        	
        	if(scoreAnalysisData != null && scoreAnalysisData.size() > 0) {
        		
        		if(isJoinExam) {
        			scoreAnalysisHandleService.importGeneralExamScore(examWorksId,gradeId,teamId,subjectCode,examTime,scoreAnalysisData,list,examId);
        		}else {
        			scoreAnalysisHandleService.importTeamExamScore(examWorksId,teamId,examTime,fullScore,highScore,lowScore,passScore,schoolId,gradeId,subjectCode,scoreAnalysisData,list,examId);
        		}
        	}  
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    
    }
	
}
