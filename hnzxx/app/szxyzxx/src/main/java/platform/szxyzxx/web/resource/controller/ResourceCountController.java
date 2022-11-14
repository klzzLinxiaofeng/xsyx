package platform.szxyzxx.web.resource.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.exam.vo.ExamCondition;
import platform.education.exam.vo.ExamCount;
import platform.education.exam.vo.ExamCountByUserVo;
import platform.education.exam.vo.ResourceCount;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.homework.vo.HomeWorkCountByUserVo;
import platform.education.homework.vo.HomeworkCondition;
import platform.education.homework.vo.HomeworkCount;
import platform.education.learningDesign.vo.LearningDesignCondition;
import platform.education.learningDesign.vo.LearningDesignCount;
import platform.education.learningDesign.vo.LearningDesignCountByUserVo;
import platform.education.material.service.MaterialService;
import platform.education.material.vo.MaterialCondition;
import platform.education.material.vo.MaterialCount;
import platform.education.material.vo.MaterialCountByUser;
import platform.education.micro.contants.MicroType;
import platform.education.micro.vo.MicroCountByUserVo;
import platform.education.micro.vo.MicroLessonCondition;
import platform.education.micro.vo.MicroLessonCount;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.vo.ResourceCountByUser;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.teachingPlan.service.TeachingPlanService;
import platform.education.teachingPlan.vo.TeachingPlanCondition;
import platform.education.teachingPlan.vo.TeachingPlanCount;
import platform.education.teachingPlan.vo.TeachingPlanCountByUserVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.CalculateVisualFileSize;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.facility.poi.excel.config.ParseConfig;


@Controller
@RequestMapping("/resource")
public class ResourceCountController extends BaseController{
	
	private final static String viewBasePath = "/resource";
	
	private static String[] countTitles = null;
	private static String[] countNums = null;
	private static String[] countVols = null;
	private static String[] subjectCountTitles = null;
	private static String[] subjectCountVols = null;
	
	@Autowired
	@Qualifier("materialService")
	protected  MaterialService materialService;
	
	@Autowired
	@Qualifier("teachingPlanService")
	protected  TeachingPlanService teachingPlanService;
	
	@RequestMapping(value = "/resourceCount")
	public String resourceCount(Model model) {
		//课件
		LearningDesignCondition learningDesignCondition = new LearningDesignCondition();
		learningDesignCondition.setAppId(SysContants.SYSTEM_APP_ID);
		LearningDesignCount learningDesignCount = learningDesignService.findCountLearningDesign(learningDesignCondition);
		//教案
		TeachingPlanCondition tpCondition = new TeachingPlanCondition();
		tpCondition.setAppId(SysContants.SYSTEM_APP_ID);
		TeachingPlanCount teachPlanCount = teachingPlanService.findCountTeachingPlan(tpCondition);
		//试卷
		ExamCondition examCondition = new ExamCondition();
		examCondition.setAppId(SysContants.SYSTEM_APP_ID);
		ExamCount examCount = examService.findCountExam(examCondition);
		//微课
		MicroLessonCondition microCondition = new MicroLessonCondition();
		microCondition.setAppId(SysContants.SYSTEM_APP_ID);
		microCondition.setType(MicroType.MICRO_COURSE);
		MicroLessonCount microCount =  microLessonService.findCountMicroLesson(microCondition);
		//视频
		microCondition.setAppId(SysContants.SYSTEM_COMMON_RESOURCE_APP_ID);
		microCondition.setType(null);
		MicroLessonCount commonMicroCount = microLessonService.findCountMicroLesson(microCondition);
		
		model.addAttribute("learningDesignCount", learningDesignCount);
		model.addAttribute("teachPlanCount", teachPlanCount);
		model.addAttribute("examCount", examCount);
		model.addAttribute("microCount", microCount);
		model.addAttribute("commonMicroCount", commonMicroCount);
		return viewBasePath+"/resouceCount/resource_count";
	}
	
	@RequestMapping(value = "/countByGrade")
	public String countByGrade(@RequestParam(value = "grade")String grade,Model model,@CurrentUser UserInfo user){
		//课件
		LearningDesignCondition learningDesignCondition = new LearningDesignCondition();
		learningDesignCondition.setAppId(SysContants.SYSTEM_APP_ID);
		learningDesignCondition.setGradeCode(grade);
		List<LearningDesignCount> learningDesignCounts = learningDesignService.findCountSubjectLearningDesign(learningDesignCondition);
		//教案
		TeachingPlanCondition tpCondition = new TeachingPlanCondition();
		tpCondition.setAppId(SysContants.SYSTEM_APP_ID);
		tpCondition.setGradeCode(grade);
		List<TeachingPlanCount> teachPlanCounts = teachingPlanService.findCountSubjectTeachingPlan(tpCondition);
		//试卷
		ExamCondition examCondition = new ExamCondition();
		examCondition.setAppId(SysContants.SYSTEM_APP_ID);
		examCondition.setGradeCode(grade);
		List<ExamCount> examCounts = examService.findCountSubjectExam(examCondition);
		//微课
		MicroLessonCondition microCondition = new MicroLessonCondition();
		microCondition.setAppId(SysContants.SYSTEM_APP_ID);
		microCondition.setType(MicroType.MICRO_COURSE);
		microCondition.setGradeCode(grade );
		List<MicroLessonCount> microCounts =  microLessonService.findCountSubjectMicroLesson(microCondition);
		//视频
		microCondition.setAppId(SysContants.SYSTEM_COMMON_RESOURCE_APP_ID);
		microCondition.setType(null);
		microCondition.setGradeCode(grade);
		List<MicroLessonCount> commonMicroCounts =  microLessonService.findCountSubjectMicroLesson(microCondition);
		
		List<Subject> subjects = this.subjectService.findSubjectsOfSchool(user.getSchoolId());
		
		
		model.addAttribute("learningDesignCounts", learningDesignCounts);
		model.addAttribute("teachPlanCounts", teachPlanCounts);
		model.addAttribute("examCounts", examCounts);
		model.addAttribute("microCounts", microCounts);
		model.addAttribute("commonMicroCounts", commonMicroCounts);
		model.addAttribute("subjects", subjects);
		return viewBasePath+"/resouceCount/resource_count_list";
	}
	
	
	@RequestMapping(value = "/teachCount")
	public String countResource() {
		return viewBasePath+"/resouceCount/resource_teachCount";
	}
	
	/**
	 * 准备导出的数据
	 * @param countTitles
	 * @param countNums
	 * @param countVols
	 * @param subjectCountTitles
	 * @param subjectCountVols
	 * @author 陈业强
	 */
	@RequestMapping(value = "/preExportData")
	@ResponseBody
	public void preExportData(
			@RequestParam(value = "countTitles[]")String[] countTitles,
			@RequestParam(value = "countNums[]")String[] countNums,
			@RequestParam(value = "countVols[]")String[] countVols,
			@RequestParam(value = "subjectCountTitles[]")String[] subjectCountTitles,
			@RequestParam(value = "subjectCountVols[]")String[] subjectCountVols){
		ResourceCountController.countTitles = countTitles;
		ResourceCountController.countNums = countNums;
		ResourceCountController.countVols = countVols;
		ResourceCountController.subjectCountTitles = subjectCountTitles;
		ResourceCountController.subjectCountVols = subjectCountVols;
	}
	/**
	 * 导出总计数据
	 * @param response
	 * @param request
	 * @author 陈业强
	 */
	@RequestMapping(value = "/exportCountData")
	@ResponseBody
	public void exportCountData(HttpServletResponse response,HttpServletRequest request){
		ParseConfig countConfig = SzxyExcelTookit.getConfig();
		countConfig.setTitles(countTitles);
		countConfig.setFieldNames(countTitles);
		countConfig.setSheetIndex(0);
		countConfig.setSheetTitle("总计");
		//装配所有的数据项
		List<Object> countMaps = new ArrayList<Object>();
		// 装配单个数据
		if(countTitles.length > 0 ){
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> sizeMap = new HashMap<String, Object>();
			for (int i = 0; i < countTitles.length; i++) {
				map.put(countTitles[i], countNums[i]);
				sizeMap.put(countTitles[i], countVols[i]);
			}
			countMaps.add(map);
		}
		try {
			SzxyExcelTookit.exportExcelToWEB(countMaps, countConfig, request, response,"各类型教学资源统计表.xls");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ParseConfig config = SzxyExcelTookit.getConfig();
		config.setTitles(subjectCountTitles);
		config.setFieldNames(subjectCountTitles);
		config.setSheetIndex(0);
		config.setSheetTitle("分科统计");
		List<Object> maps = new ArrayList<Object>();
		if(subjectCountVols.length > 0){
			for(String vol : subjectCountVols){
				vol = vol.replace("[", "");
				vol = vol.replace("]", "");
				String[] vols = vol.split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				if(vols.length > 0){
					for (int i = 0; i < vols.length; i++) {
						map.put(subjectCountTitles[i], vols[i]);
					}
				}
				maps.add(map);
			}
		}
		try {
			SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,"分科教学资源统计表.xls");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出分科数据
	 * @param response
	 * @param request
	 * @author 陈业强
	 */
	@RequestMapping(value = "/exportSubjectData")
	@ResponseBody
	public void exportSubjectData(HttpServletResponse response,HttpServletRequest request){
		ParseConfig config = SzxyExcelTookit.getConfig();
		config.setTitles(subjectCountTitles);
		config.setFieldNames(subjectCountTitles);
		config.setSheetIndex(0);
		config.setSheetTitle("分科统计");
		List<Object> maps = new ArrayList<Object>();
		if(subjectCountVols.length > 0){
			for (int i = 0; i < subjectCountVols.length; i++) {
				String vol = subjectCountVols[i];
				vol = vol.replace("[", "");
				vol = vol.replace("]", "");
				vol = vol.replaceAll("\"","");
				String[] vols = vol.split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				if(vols.length > 0){
					for (int j = 0; j < vols.length; j++) {
						map.put(subjectCountTitles[j], vols[j]);
					}
				}
				maps.add(map);
			}
		}
		try {
			SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,"分科教学资源统计表.xls");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/personCount")
	public String personCountResource() {
		return viewBasePath+"/personCount/resource_personCount";
	}
	
	@RequestMapping(value = "/subjectCount")
	public String countSubjectResource() {
		return viewBasePath+"/resouceCount/resource_subjectCount";
	}
	
	
	@RequestMapping(value = "/getSubjectBygrade")
	@ResponseBody
	public List<SubjectGrade> getSubjectBygrade(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<SubjectGrade> subjectGradeList = new ArrayList<SubjectGrade>();
		if(gradeCode!=null&&gradeCode!=""){
			subjectGradeList = this.subjectGradeService.findSubjectGradeByGradeCode(user.getSchoolId(), gradeCode);
			SubjectGrade sub = new SubjectGrade();
			sub.setSubjectName("其他");
			sub.setSubjectCode("0");
			subjectGradeList.add(sub);
		}
		return subjectGradeList;
	}
	
	@RequestMapping(value = "/getSubjectByuserId")
	@ResponseBody
	public List<SubjectGrade> getSubjectByuserId(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<SubjectGrade> subjectGradeList = new ArrayList<SubjectGrade>();
		
		//List<SubjectGrade>  subjectGradeListTemp = this.subjectGradeService.findSubjectGradeByGradeCode(user.getSchoolId(), gradeCode);
		
		List<Subject> subjectList = new ArrayList<Subject>();
		if(user.getSchoolId() != null&&!"".equals(user.getSchoolId()+"")){
			subjectList = this.subjectService.findSubjectsOfSchool(user.getSchoolId());
		}
		 
		for (Subject subject : subjectList) {
			SubjectGrade sub = new SubjectGrade();
			sub.setSubjectName(subject.getName());
			sub.setSubjectCode(subject.getCode());
			subjectGradeList.add(sub);
		}
		SubjectGrade sub = new SubjectGrade();
		sub.setSubjectName("其他");
		sub.setSubjectCode("0");
		subjectGradeList.add(sub);
		
		return subjectGradeList;
	}
	
	@RequestMapping(value = "/getMi", method = RequestMethod.POST)
	@ResponseBody
	public List<MicroLessonCount> getMi(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<MicroLessonCount> miList = new ArrayList<MicroLessonCount>();
		miList = this.microLessonService.findSubjectMicroLesson(user.getSchoolId(),gradeCode);
		for(MicroLessonCount ml : miList){
			if(ml.getSubjectCode()==null||ml.getSubjectCode()==""){
				ml.setSubjectCode("0");
			}
		}
		return miList;
	}
	
	@RequestMapping(value = "/getCommonMi", method = RequestMethod.POST)
	@ResponseBody
	public List<MicroLessonCount> getCommonMi(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<MicroLessonCount> miList = new ArrayList<MicroLessonCount>();
		MicroLessonCondition condition = new MicroLessonCondition();
		condition.setUserId(user.getId());
		//condition.setGroupId(user.getGroupId());
		//condition.setGradeCode(gradeCode);
		condition.setType(MicroType.COMMON_MICRO);
		miList = this.microLessonService.findCountSubjectMicroLesson(condition);
		for(MicroLessonCount ml : miList){
			if(ml.getSubjectCode()==null||ml.getSubjectCode()==""){
				ml.setSubjectCode("0");
			}
		}
		return miList;
	}
	
	@RequestMapping(value = "/getMicroMi", method = RequestMethod.POST)
	@ResponseBody
	public List<MicroLessonCount> getMicroMi(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<MicroLessonCount> miList = new ArrayList<MicroLessonCount>();
		MicroLessonCondition condition = new MicroLessonCondition();
		condition.setUserId(user.getId());
		//condition.setGroupId(user.getGroupId());
		//condition.setGradeCode(gradeCode);
		condition.setType(MicroType.MICRO_COURSE);
		miList = this.microLessonService.findCountSubjectMicroLesson(condition);
		for(MicroLessonCount ml : miList){
			if(ml.getSubjectCode()==null||ml.getSubjectCode()==""){
				ml.setSubjectCode("0");
			}
		}
		return miList;
	}
	
	@RequestMapping(value = "/getEm", method = RequestMethod.POST)
	@ResponseBody
	public List<ExamCount> getEm(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<ExamCount> emList = new ArrayList<ExamCount>();
		emList = this.examService.findSubjectExam(user.getSchoolId(),gradeCode);
		for(ExamCount em : emList){
			if(em.getSubjectCode()==null||em.getSubjectCode()==""){
				em.setSubjectCode("0");
			}
		}
		return emList;
	}
	
	@RequestMapping(value = "/getCountEm", method = RequestMethod.POST)
	@ResponseBody
	public List<ExamCount> getCountEm(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<ExamCount> emList = new ArrayList<ExamCount>();
		//emList = this.examService.findSubjectExam(user.getSchoolId(),gradeCode);
		ExamCondition condition = new ExamCondition();
		//condition.setGroupId(groupId);
		condition.setUserId(user.getId());
		//condition.setGradeCode(gradeCode);
		emList = this.examService.findCountSubjectExam(condition);
		for(ExamCount em : emList){
			if(em.getSubjectCode()==null||em.getSubjectCode()==""){
				em.setSubjectCode("0");
			}
		}
		return emList;
	}
	
	@RequestMapping(value = "/getHw", method = RequestMethod.POST)
	@ResponseBody
	public List<HomeworkCount> getHw(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<HomeworkCount> hwList = new ArrayList<HomeworkCount>();
		hwList = this.homeworkService.findSubjectHomework(user.getSchoolId(),gradeCode);
		for(HomeworkCount hw : hwList){
			if(hw.getSubjectCode()==null||hw.getSubjectCode()==""){
				hw.setSubjectCode("0");
			}
		}
		return hwList;
	}
	
	@RequestMapping(value = "/getCountHw", method = RequestMethod.POST)
	@ResponseBody
	public List<HomeworkCount> getCountHw(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<HomeworkCount> hwList = new ArrayList<HomeworkCount>();
		HomeworkCondition condition = new HomeworkCondition();
		condition.setUserId(user.getId());
		hwList = this.homeworkService.findCountSubjectHomework(condition);
		for(HomeworkCount hw : hwList){
			if(hw.getSubjectCode()==null||hw.getSubjectCode()==""){
				hw.setSubjectCode("0");
			}
		}
		return hwList;
	}
	@RequestMapping(value = "/getLd", method = RequestMethod.POST)
	@ResponseBody
	public List<LearningDesignCount> getLd(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<LearningDesignCount> ldList = new ArrayList<LearningDesignCount>();
		ldList = this.learningDesignService.findSubjectLearningDesign(user.getSchoolId(),gradeCode);
		for(LearningDesignCount ld : ldList){
			if(ld.getSubjectCode()==null||ld.getSubjectCode()==""){
				ld.setSubjectCode("0");
			}
		}
		return ldList;
	}
	
	@RequestMapping(value = "/getCountLd", method = RequestMethod.POST)
	@ResponseBody
	public List<LearningDesignCount> getCountLd(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<LearningDesignCount> ldList = new ArrayList<LearningDesignCount>();
		LearningDesignCondition condition = new LearningDesignCondition();
		condition.setUserId(user.getId());
		ldList = this.learningDesignService.findCountSubjectLearningDesign(condition);
		for(LearningDesignCount ld : ldList){
			if(ld.getSubjectCode()==null||ld.getSubjectCode()==""){
				ld.setSubjectCode("0");
			}
		}
		return ldList;
	}
	
	@RequestMapping(value = "/getMl", method = RequestMethod.POST)
	@ResponseBody
	public List<MaterialCount> getMl(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<MaterialCount> mlList = new ArrayList<MaterialCount>();
		mlList = this.materialService.findSubjectMaterial(user.getSchoolId(),gradeCode);
		for(MaterialCount ml : mlList){
			if(ml.getSubjectCode()==null||ml.getSubjectCode()==""){
				ml.setSubjectCode("0");
			}
		}
		return mlList;
	}
	
	@RequestMapping(value = "/getCountMl", method = RequestMethod.POST)
	@ResponseBody
	public List<MaterialCount> getCountMl(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<MaterialCount> mlList = new ArrayList<MaterialCount>();
		MaterialCondition condition = new MaterialCondition();
		condition.setUserId(user.getId());
		mlList = this.materialService.findCountSubjectMaterial(condition);
		for(MaterialCount ml : mlList){
			if(ml.getSubjectCode()==null||ml.getSubjectCode()==""){
				ml.setSubjectCode("0");
			}
		}
		return mlList;
	}
	
	@RequestMapping(value = "/getTp", method = RequestMethod.POST)
	@ResponseBody
	public List<TeachingPlanCount> getTp(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = true) String gradeCode){
		List<TeachingPlanCount> tpList = new ArrayList<TeachingPlanCount>();
		tpList = this.teachingPlanService.findSubjectTeachingPlan(user.getSchoolId(), gradeCode);
		for(TeachingPlanCount tp : tpList){
			if(tp.getSubjectCode()==null||tp.getSubjectCode()==""){
				tp.setSubjectCode("0");
			}
		}
		return tpList;
	}
	
	@RequestMapping(value = "/getCountTp", method = RequestMethod.POST)
	@ResponseBody
	public List<TeachingPlanCount> getCountTp(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeCode", required = false) String gradeCode){
		List<TeachingPlanCount> tpList = new ArrayList<TeachingPlanCount>();
		TeachingPlanCondition condition = new TeachingPlanCondition();
		condition.setUserId(user.getId());
		tpList = this.teachingPlanService.findCountSubjectTeachingPlan(condition);
		for(TeachingPlanCount tp : tpList){
			if(tp.getSubjectCode()==null||tp.getSubjectCode()==""){
				tp.setSubjectCode("0");
			}
		}
		return tpList;
	}
	
	@RequestMapping(value = "/getOther", method = RequestMethod.POST)
	@ResponseBody
	public List<ResourceCount> getOther(@CurrentUser UserInfo user,Model model){
		List<ResourceCount> list = new ArrayList<ResourceCount>();
		ResourceCount rs = null;
		MicroLessonCount mi = this.microLessonService.findOtherMicroLesson(user.getSchoolId());
		if(mi!=null){
			rs = new ResourceCount();
			rs.setSubjectName("mi");
			rs.setCount(mi.getMiCount());
			list.add(rs);
		}
		ExamCount em = this.examService.findOtherExam(user.getSchoolId());
		if(em!=null){
			rs = new ResourceCount();
			rs.setSubjectName("em");
			rs.setCount(em.getEmCount());
			list.add(rs);
		}
		HomeworkCount hw = this.homeworkService.findOtherHomework(user.getSchoolId());
		if(hw!=null){
			rs = new ResourceCount();
			rs.setSubjectName("hw");
			rs.setCount(hw.getHwCount());
			list.add(rs);
		}
		LearningDesignCount ld = this.learningDesignService.findOtherLearningDesign(user.getSchoolId());
		if(ld!=null){
			rs = new ResourceCount();
			rs.setSubjectName("ld");
			rs.setCount(ld.getLdCount());
			list.add(rs);
		}
		MaterialCount ml = this.materialService.findOtherMaterial(user.getSchoolId());
		if(ml!=null){
			rs = new ResourceCount();
			rs.setSubjectName("ml");
			rs.setCount(ml.getMlCount());
			list.add(rs);
		}
		TeachingPlanCount tp = this.teachingPlanService.findOtherTeachingPlan(user.getSchoolId());
		if(tp!=null){
			rs = new ResourceCount();
			rs.setSubjectName("tp");
			rs.setCount(tp.getTpCount());
			list.add(rs);
		}
		return list;
	}
	
	@RequestMapping(value = "/getCountOther", method = RequestMethod.POST)
	@ResponseBody
	public List<ResourceCount> getCountOther(@CurrentUser UserInfo user,Model model){
		List<ResourceCount> list = new ArrayList<ResourceCount>();
		ResourceCount rs = null;
		MicroLessonCondition micondition = new MicroLessonCondition();
		micondition.setType(MicroType.COMMON_MICRO);
		micondition.setUserId(micondition.getUserId());
		//micondition.setGroupId(user.getGroupId());
		MicroLessonCount commonmi = this.microLessonService.findCountOtherMicroLesson(micondition);
		if(commonmi!=null){
			rs = new ResourceCount();
			rs.setSubjectName("commonmi");
			rs.setCount(commonmi.getMiCount());
			list.add(rs);
		}
		
		micondition.setType(MicroType.COMMON_MICRO);
		micondition.setUserId(micondition.getUserId());
		//micondition.setGroupId(user.getGroupId());
		MicroLessonCount micromi = this.microLessonService.findCountOtherMicroLesson(micondition);
		if(micromi!=null){
			rs = new ResourceCount();
			rs.setSubjectName("micromi");
			rs.setCount(micromi.getMiCount());
			list.add(rs);
		}
		ExamCondition emCondition = new ExamCondition();
		emCondition.setUserId(user.getId());
		ExamCount em = this.examService.findCountOtherExam(emCondition);
		if(em!=null){
			rs = new ResourceCount();
			rs.setSubjectName("em");
			rs.setCount(em.getEmCount());
			list.add(rs);
		}
		HomeworkCondition hwcondition = new HomeworkCondition();
		hwcondition.setUserId(user.getId());
		HomeworkCount hw = this.homeworkService.findCountOtherHomework(hwcondition);
		if(hw!=null){
			rs = new ResourceCount();
			rs.setSubjectName("hw");
			rs.setCount(hw.getHwCount());
			list.add(rs);
		}
		
		LearningDesignCondition ldcondition = new LearningDesignCondition();
		ldcondition.setUserId(user.getId());
		LearningDesignCount ld = this.learningDesignService.findCountOtherLearningDesign(ldcondition);
		if(ld!=null){
			rs = new ResourceCount();
			rs.setSubjectName("ld");
			rs.setCount(ld.getLdCount());
			list.add(rs);
		}
		
		MaterialCondition mecondition = new MaterialCondition();
		mecondition.setUserId(user.getId());
		MaterialCount ml = this.materialService.findCountOtherMaterial(mecondition);
		if(ml!=null){
			rs = new ResourceCount();
			rs.setSubjectName("ml");
			rs.setCount(ml.getMlCount());
			list.add(rs);
		}
		TeachingPlanCondition tpcondition = new TeachingPlanCondition();
		tpcondition.setUserId(user.getId());
		TeachingPlanCount tp = this.teachingPlanService.findCountOtherTeachingPlan(tpcondition);
		if(tp!=null){
			rs = new ResourceCount();
			rs.setSubjectName("tp");
			rs.setCount(tp.getTpCount());
			list.add(rs);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/getResBySchool", method = RequestMethod.POST)
	@ResponseBody
	public Map getResBySchool(@CurrentUser UserInfo user){
		Integer schoolId = user.getSchoolId();
		Integer sumCount = 0;
		MicroLessonCount mi = this.microLessonService.findMicroLesson(schoolId);
		ExamCount em = this.examService.findExam(schoolId);
		HomeworkCount hw = this.homeworkService.findHomework(schoolId);
		LearningDesignCount ld = this.learningDesignService.findLearningDesign(schoolId);
 		MaterialCount ml = this.materialService.findMaterial(schoolId);
		TeachingPlanCount tp = this.teachingPlanService.findTeachingPlan(schoolId);
		if(mi!=null){
			if(mi.getMiCount()==null){
				mi.setMiCount(0);
			}
			if(mi.getMiSize()==null){
				mi.setMiSize(0L);
			}
			sumCount+=mi.getMiCount();
		}
		if(em!=null){
			if(em.getEmCount()==null){
				em.setEmCount(0);
			}
			if(em.getEmSize()==null){
				em.setEmSize(0L);
			}
			sumCount+=em.getEmCount();
		}
		if(hw!=null){
			if(hw.getHwCount()==null){
				hw.setHwCount(0);
			}
			if(hw.getHwSize()==null){
				hw.setHwSize(0L);
			}
			sumCount+=hw.getHwCount();
		}
		if(ld!=null){
			if(ld.getLdCount()==null){
				ld.setLdCount(0);
			}
			if(ld.getLdSize()==null){
				ld.setLdSize(0L);
			}
			sumCount+=ld.getLdCount();
		}
		if(ml!=null){
			if(ml.getMlCount()==null){
				ml.setMlCount(0);
			}
			if(ml.getMlSize()==null){
				ml.setMlSize(0L);
			}
			sumCount+=ml.getMlCount();
		}
		if(tp!=null){
			if(tp.getTpCount()==null){
				tp.setTpCount(0);
			}
			if(tp.getTpSize()==null){
				tp.setTpSize(0L);
			}
			sumCount+=tp.getTpCount();
		}
		Map map = new HashMap();
		if(sumCount!=0){
			if(mi!=null){
				Double miDiv = (double) (mi.getMiCount()*100/sumCount);
				String miCount = CalculateVisualFileSize.getFileSize(mi.getMiSize());
				map.put("微课（数量"+ mi.getMiCount() +"）（容量"+ miCount+"）", miDiv);
			}
			if(em!=null){
				Double emDiv = (double) (em.getEmCount()*100/sumCount);
				String emCount = CalculateVisualFileSize.getFileSize(em.getEmSize());
				map.put("试卷（数量"+ em.getEmCount() +"）（容量"+ emCount +"）", emDiv);
			}
			if(hw!=null){
				Double hwDiv = (double) (hw.getHwCount()*100/sumCount);
				String hwCount = CalculateVisualFileSize.getFileSize(hw.getHwSize());
				map.put("作业（数量"+ hw.getHwCount() +"）（容量"+ hwCount +"）", hwDiv);
			}
			if(ld!=null){
				Double ldDiv = (double) (ld.getLdCount()*100/sumCount);
				String ldCount = CalculateVisualFileSize.getFileSize(ld.getLdSize());
				map.put("课件（数量"+ ld.getLdCount() +"）（容量"+ ldCount +"）", ldDiv);
			}
			if(ml!=null){
				Double mlDiv = (double) (ml.getMlCount()*100/sumCount);
				String mlCount = CalculateVisualFileSize.getFileSize(ml.getMlSize());
				map.put("素材（数量"+ ml.getMlCount() +"）（容量"+ mlCount +"）", mlDiv);
			}
			if(tp!=null){
				Double tpDiv = (double) (tp.getTpCount()*100/sumCount);
				String tpCount = CalculateVisualFileSize.getFileSize(tp.getTpSize());
				map.put("教案（数量"+ tp.getTpCount() +"）（容量"+ tpCount +"）", tpDiv);
			}
		}
		return map;
	}
	
	@RequestMapping(value = "/getResByUserId", method = RequestMethod.POST)
	@ResponseBody
	public Map getResByUserId(@CurrentUser UserInfo user){
		//Integer schoolId = user.getSchoolId();
		Integer sumCount = 0;
		//MicroLessonCount mi = this.microLessonService.findMicroLesson(schoolId);
		
		MicroLessonCondition miCondition = new MicroLessonCondition();
		//condition.setGroupId(user.getGroupId()); 统计个人资源去掉学校关系
		miCondition.setUserId(user.getId());
		miCondition.setType(MicroType.COMMON_MICRO);
    	MicroLessonCount commonMi = microLessonService.findCountMicroLesson(miCondition);
		
    	miCondition.setType(MicroType.MICRO_COURSE);
    	MicroLessonCount microMi = microLessonService.findCountMicroLesson(miCondition);
		
    	
		//ExamCount em = this.examService.findExam(schoolId);
    	ExamCondition exCondition = new ExamCondition();
    	//exCondition.setGroupId(user.getGroupId());
    	exCondition.setUserId(user.getId());
		ExamCount em = this.examService.findCountExam(exCondition);
		
		
		//HomeworkCount hw = this.homeworkService.findHomework(schoolId);
		HomeworkCondition hwcondition = new HomeworkCondition();
		//hwcondition.setGroupId(user.getGroupId());
		hwcondition.setUserId(user.getId());
		HomeworkCount hw = this.homeworkService.findCountHomework(hwcondition);
		
		
		//LearningDesignCount ld = this.learningDesignService.findLearningDesign(schoolId);
		
		LearningDesignCondition lncodition = new LearningDesignCondition();
		//lncodition.setGroupId(user.getGroupId());
		lncodition.setUserId(user.getId());
		LearningDesignCount ld = this.learningDesignService.findCountLearningDesign(lncodition);
		
		//MaterialCount ml = this.materialService.findMaterial(schoolId);
		MaterialCondition mecondition = new MaterialCondition();
		//mecondition.setGroupId(user.getGroupId());
		mecondition.setUserId(user.getId());
 		MaterialCount ml = this.materialService.findCountMaterial(mecondition);
 		
 		TeachingPlanCondition tpcondition = new TeachingPlanCondition();
 		//tpcondition.setGroupId(user.getGroupId());
 		tpcondition.setUserId(user.getId());
		TeachingPlanCount tp = this.teachingPlanService.findCountTeachingPlan(tpcondition);
		if(commonMi!=null){
			if(commonMi.getMiCount()==null){
				commonMi.setMiCount(0);
			}
			if(commonMi.getMiSize()==null){
				commonMi.setMiSize(0L);
			}
			sumCount+=commonMi.getMiCount();
		}
		
		if(microMi!=null){
			if(microMi.getMiCount()==null){
				microMi.setMiCount(0);
			}
			if(microMi.getMiSize()==null){
				microMi.setMiSize(0L);
			}
			sumCount+=microMi.getMiCount();
		}
		
		if(em!=null){
			if(em.getEmCount()==null){
				em.setEmCount(0);
			}
			if(em.getEmSize()==null){
				em.setEmSize(0L);
			}
			sumCount+=em.getEmCount();
		}
		if(hw!=null){
			if(hw.getHwCount()==null){
				hw.setHwCount(0);
			}
			if(hw.getHwSize()==null){
				hw.setHwSize(0L);
			}
			sumCount+=hw.getHwCount();
		}
		if(ld!=null){
			if(ld.getLdCount()==null){
				ld.setLdCount(0);
			}
			if(ld.getLdSize()==null){
				ld.setLdSize(0L);
			}
			sumCount+=ld.getLdCount();
		}
		if(ml!=null){
			if(ml.getMlCount()==null){
				ml.setMlCount(0);
			}
			if(ml.getMlSize()==null){
				ml.setMlSize(0L);
			}
			sumCount+=ml.getMlCount();
		}
		if(tp!=null){
			if(tp.getTpCount()==null){
				tp.setTpCount(0);
			}
			if(tp.getTpSize()==null){
				tp.setTpSize(0L);
			}
			sumCount+=tp.getTpCount();
		}
		Map map = new HashMap();
		if(sumCount!=0){
			if(commonMi!=null){
				Double miDiv = (double) (commonMi.getMiCount()*100/sumCount);
				String miCount = CalculateVisualFileSize.getFileSize(commonMi.getMiSize());
				map.put("普通微课（数量"+ commonMi.getMiCount() +"）（容量"+ miCount+"）", miDiv);
			}
			
			if(microMi!=null){
				Double miDiv = (double) (microMi.getMiCount()*100/sumCount);
				String miCount = CalculateVisualFileSize.getFileSize(microMi.getMiSize());
				map.put("微课笔微课（数量"+ microMi.getMiCount() +"）（容量"+ miCount+"）", miDiv);
			}
			
			if(em!=null){
				Double emDiv = (double) (em.getEmCount()*100/sumCount);
				String emCount = CalculateVisualFileSize.getFileSize(em.getEmSize());
				map.put("试卷（数量"+ em.getEmCount() +"）（容量"+ emCount +"）", emDiv);
			}
			if(hw!=null){
				Double hwDiv = (double) (hw.getHwCount()*100/sumCount);
				String hwCount = CalculateVisualFileSize.getFileSize(hw.getHwSize());
				map.put("作业（数量"+ hw.getHwCount() +"）（容量"+ hwCount +"）", hwDiv);
			}
			if(ld!=null){
				Double ldDiv = (double) (ld.getLdCount()*100/sumCount);
				String ldCount = CalculateVisualFileSize.getFileSize(ld.getLdSize());
				map.put("课件（数量"+ ld.getLdCount() +"）（容量"+ ldCount +"）", ldDiv);
			}
			if(ml!=null){
				Double mlDiv = (double) (ml.getMlCount()*100/sumCount);
				String mlCount = CalculateVisualFileSize.getFileSize(ml.getMlSize());
				map.put("素材（数量"+ ml.getMlCount() +"）（容量"+ mlCount +"）", mlDiv);
			}
			if(tp!=null){
				Double tpDiv = (double) (tp.getTpCount()*100/sumCount);
				String tpCount = CalculateVisualFileSize.getFileSize(tp.getTpSize());
				map.put("教案（数量"+ tp.getTpCount() +"）（容量"+ tpCount +"）", tpDiv);
			}
		}
		return map;
	}
	
	//按教师分科统计开始  2016-1-18 ====================================================
	@RequestMapping(value = "/getResByTeacher")
	@ResponseBody
	public List<Teacher> getTeacherBySchool(@CurrentUser UserInfo user,
			@RequestParam(value = "userType", required = true) String userType){
		List<Teacher> teacherList = new ArrayList<Teacher>();
		if(userType != null && "allUser".equals(userType)){
			teacherList = teacherService.findTeacherListBySchoolId(user.getSchoolId());
		}else{
			teacherService.findOfUser(user.getSchoolId(), Integer.valueOf(userType));
			Teacher teacher = new Teacher();
			teacherList.add(teacher);
		}
		return teacherList;
	}
	
	//获取微课  2016-1-18
	@RequestMapping(value = "/getMicroData", method = RequestMethod.POST)
	@ResponseBody
	public List<MicroCountByUserVo> getMicroData(@CurrentUser UserInfo user){
		List<MicroCountByUserVo> list = microLessonService.findCountMicroLessByUser(SysContants.SYSTEM_APP_ID);
		return list;
	}
	
	//获取教案  2016-1-18
	@RequestMapping(value = "/getTeachingPlanData", method = RequestMethod.POST)
	@ResponseBody
	public List<TeachingPlanCountByUserVo> getTeachingPlanData(@CurrentUser UserInfo user){
		List<TeachingPlanCountByUserVo> list = teachingPlanService.findCountTeachingPlanByUser(SysContants.SYSTEM_APP_ID);
		return list;
	}
	
	//获取试卷  2016-1-18
	@RequestMapping(value = "/getExamData", method = RequestMethod.POST)
	@ResponseBody
	public List<ExamCountByUserVo> getExamData(@CurrentUser UserInfo user){
		return examService.findCountExamByUser(SysContants.SYSTEM_APP_ID);
	}
	
	//获取作业  2016-1-18
	@RequestMapping(value = "/getHomeWorkData", method = RequestMethod.POST)
	@ResponseBody
	public List<HomeWorkCountByUserVo> getHomeWorkData(@CurrentUser UserInfo user){
		return homeworkService.findCountHomeworkByUser(SysContants.SYSTEM_APP_ID);
	}
	
	//获取课件  2016-1-18
	@RequestMapping(value = "/getLearningDesingData", method = RequestMethod.POST)
	@ResponseBody
	public List<LearningDesignCountByUserVo> getLearningDesingData(@CurrentUser UserInfo user){
		return learningDesignService.findCountLearningDesignByUser(SysContants.SYSTEM_APP_ID);
	}
	
	//获取素材  2016-1-18
	@RequestMapping(value = "/getmaTerialData", method = RequestMethod.POST)
	@ResponseBody
	public List<MaterialCountByUser> getmaTerialData(@CurrentUser UserInfo user){
		return materialService.findCountMaterialByUser(SysContants.SYSTEM_APP_ID);
	}
	
	//获取其他  2016-1-18
	@RequestMapping(value = "/getOtherData", method = RequestMethod.POST)
	@ResponseBody
	public List<ResourceCountByUser> getOtherData(@CurrentUser UserInfo user){
		List<ResourceCountByUser> relist = new ArrayList<ResourceCountByUser>();
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		resourceLibraryCondition.setAppId(SysContants.SYSTEM_APP_ID);
		resourceLibraryCondition.setOwerId(user.getSchoolId());
		List<ResourceLibrary> list = resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		if(list != null && list.size() > 0){
			relist = resourceService.findCountOtherByUser(SysContants.SYSTEM_APP_ID,list.get(0).getUuid(),ResourceType.OTHER);
		}
		return relist;
	}
	
	//获取其他  2016-1-18
	@RequestMapping(value = "/getSumData", method = RequestMethod.POST)
	@ResponseBody
	public List<ResourceCountByUser> getSumData(@CurrentUser UserInfo user){
		
		List<ResourceCountByUser> listOfSum = new ArrayList<ResourceCountByUser>();
		
		List<Teacher> teacherList = teacherService.findTeacherListBySchoolId(user.getSchoolId());
		
		List<ResourceCountByUser> listOfOther = new ArrayList<ResourceCountByUser>();
		
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		resourceLibraryCondition.setAppId(SysContants.SYSTEM_APP_ID);
		resourceLibraryCondition.setOwerId(user.getSchoolId());
		List<ResourceLibrary> list = resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		if(list != null && list.size() > 0){
			listOfOther = resourceService.findCountOtherByUser(SysContants.SYSTEM_APP_ID,list.get(0).getUuid(),ResourceType.OTHER);
		}
		Map<Integer,ResourceCountByUser> mapOfOther = new HashMap<Integer, ResourceCountByUser>();
		if(listOfOther != null && listOfOther.size() > 0){
			for(ResourceCountByUser vo : listOfOther){
				mapOfOther.put(vo.getUserId(), vo);
			}
		}
		
		List<MicroCountByUserVo> listOfMicro = microLessonService.findCountMicroLessByUser(SysContants.SYSTEM_APP_ID);
		Map<String,MicroCountByUserVo> mapOfMicro = new HashMap<String, MicroCountByUserVo>();
		if(listOfMicro != null && listOfMicro.size() > 0){
			for(MicroCountByUserVo vo : listOfMicro){
				mapOfMicro.put(""+vo.getUserId()+vo.getMicoType(), vo);
			}
		}
		
		List<TeachingPlanCountByUserVo> listOfTeach = teachingPlanService.findCountTeachingPlanByUser(SysContants.SYSTEM_APP_ID);
		Map<Integer,TeachingPlanCountByUserVo> mapOfTeach = new HashMap<Integer, TeachingPlanCountByUserVo>();
		if(listOfTeach != null && listOfTeach.size() > 0){
			for(TeachingPlanCountByUserVo vo : listOfTeach){
				mapOfTeach.put(vo.getUserId(), vo);
			}
		}
		
		List<ExamCountByUserVo> listOfExam = examService.findCountExamByUser(SysContants.SYSTEM_APP_ID);
		Map<Integer,ExamCountByUserVo> mapOfExam = new HashMap<Integer, ExamCountByUserVo>();
		if(listOfExam != null && listOfExam.size() > 0){
			for(ExamCountByUserVo vo : listOfExam){
				mapOfExam.put(vo.getUserId(), vo);
			}
		}
		
		List<HomeWorkCountByUserVo> listOfHome = homeworkService.findCountHomeworkByUser(SysContants.SYSTEM_APP_ID);
		Map<Integer,HomeWorkCountByUserVo> mapOfHome = new HashMap<Integer, HomeWorkCountByUserVo>();
		if(listOfHome != null && listOfHome.size() > 0){
			for(HomeWorkCountByUserVo vo : listOfHome){
				mapOfHome.put(vo.getUserId(), vo);
			}
		}
		
		List<LearningDesignCountByUserVo> listOflearn = learningDesignService.findCountLearningDesignByUser(SysContants.SYSTEM_APP_ID);
		Map<Integer,LearningDesignCountByUserVo> mapOflearn = new HashMap<Integer, LearningDesignCountByUserVo>();
		if(listOflearn != null && listOflearn.size() > 0){
			for(LearningDesignCountByUserVo vo : listOflearn){
				mapOflearn.put(vo.getUserId(), vo);
			}
		}
		
		List<MaterialCountByUser> listOfMater = materialService.findCountMaterialByUser(SysContants.SYSTEM_APP_ID);
		Map<Integer,MaterialCountByUser> mapOfMater = new HashMap<Integer, MaterialCountByUser>();
		if(listOfMater != null && listOfMater.size() > 0){
			for(MaterialCountByUser vo : listOfMater){
				mapOfMater.put(vo.getUserId(), vo);
			}
		}
		
		if(teacherList != null && teacherList.size() > 0){
			for(Teacher teacher : teacherList){
				ResourceCountByUser rcu = new ResourceCountByUser();
				rcu.setCountNumber(0);
				rcu.setUserId(teacher.getUserId());
				
				if(mapOfExam.get(teacher.getUserId()) != null){
					Integer sum = rcu.getCountNumber()+mapOfExam.get(teacher.getUserId()).getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOfHome.get(teacher.getUserId()) != null){
					Integer sum = rcu.getCountNumber()+mapOfHome.get(teacher.getUserId()).getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOflearn.get(teacher.getUserId()) != null){
					Integer sum = rcu.getCountNumber()+mapOflearn.get(teacher.getUserId()).getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOfMater.get(teacher.getUserId()) != null){
					Integer sum = rcu.getCountNumber()+mapOfMater.get(teacher.getUserId()).getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOfMicro.get(""+teacher.getUserId()+"common_micro") != null){
					Integer sum = rcu.getCountNumber()+mapOfMicro.get(""+teacher.getUserId()+"common_micro").getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOfMicro.get(""+teacher.getUserId()+"micro_course") != null){
					Integer sum = rcu.getCountNumber()+mapOfMicro.get(""+teacher.getUserId()+"micro_course").getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOfTeach.get(teacher.getUserId()) != null){
					Integer sum = rcu.getCountNumber()+mapOfTeach.get(teacher.getUserId()).getCountNumber();
					rcu.setCountNumber(sum);
				}
				if(mapOfOther.get(teacher.getUserId()) != null){
					Integer sum = rcu.getCountNumber()+mapOfOther.get(teacher.getUserId()).getCountNumber();
					rcu.setCountNumber(sum);
				}
				listOfSum.add(rcu);
			}
		}
		
		
		return listOfSum;
	}
	
	//======================================================
	
}
