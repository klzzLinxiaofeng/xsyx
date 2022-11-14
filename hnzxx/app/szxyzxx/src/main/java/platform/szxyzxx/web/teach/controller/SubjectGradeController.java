package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.SubjectGradeCondition;
import platform.education.generalTeachingAffair.vo.SubjectGradeVo;
import platform.education.generalcode.model.Stage;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/***
 * 年级科目管理
 * @author admin
 *
 */

@Controller
@RequestMapping("/teach/subjectGrade")
public class SubjectGradeController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(SubjectGradeController.class);
	
	/***
	 * 年级-科目列表
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/getSubjectGradeList")
	public ModelAndView getSubjectGradeList(
			@ModelAttribute("gradeCondition") GradeCondition gradeCondition,
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = new ModelAndView();
		String viewPath="/teach/subjectGrade/subjectGradeList";
//		try{
//			List<SchoolTermCurrent> schoolTermCurrentList = this.schoolTermCurrentService.findCurrentSchoolYear(user.getSchoolId());
//			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentList.get(0);
//			gradeCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
//			gradeCondition.setSchoolId(user.getSchoolId());
//			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
//			mav.addObject("gradeList", gradeList);
//		}catch(Exception e){
//			e.printStackTrace();
//			log.info("年级科目查询异常");
//		}
		mav.setViewName(viewPath);
		return mav;
	}
	
	@RequestMapping("/getSubjectGradeListByGradeCode")
	@ResponseBody
	public List<SubjectGradeVo> getSubjectGradeListByGradeCode(
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@ModelAttribute("subjectGradeCondition") SubjectGradeCondition subjectGradeCondition,
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
			SubjectGradeVo subjectGradeVo = null;
			Grade grade = this.gradeService.findGradeById(gradeId);
			Subject subject = null;
			List<SubjectGradeVo> subjectGradeVoList = new ArrayList<SubjectGradeVo>();
			try{
				subjectGradeCondition.setSchoolId(user.getSchoolId());
				subjectGradeCondition.setGradeCode(grade.getUniGradeCode());
				List<SubjectGrade> subjectGradeList = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, order);
//				List<SubjectGrade> subjectGradeList = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, page, order);
				for(SubjectGrade subjectGrade : subjectGradeList){
					subject = subjectService.findUnique(grade.getSchoolId(), subjectGrade.getSubjectCode());
					subjectGradeVo = new SubjectGradeVo();
					Stage stage = this.jcStageService.findStageByCode(subjectGrade.getStageCode());
//					Grade grade = this.gradeService.findGradeById(subjectGrade.getGradeId());
					subjectGradeVo.setGradeName(grade.getFullName());
					subjectGradeVo.setStageName(stage.getName());
					subjectGradeVo.setId(subjectGrade.getId());
					subjectGradeVo.setSubjectName(subject == null ? subjectGrade.getSubjectName() : subject.getName());
					subjectGradeVoList.add(subjectGradeVo);
				}
			}catch(Exception e ){
				e.printStackTrace();
			}
			return subjectGradeVoList;
	}
	
	/**
	 * 获得本学校年级科目
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getSubjectGrade", method = RequestMethod.GET)
	@ResponseBody
	public List<SubjectGrade> getSubjectGrade(@CurrentUser UserInfo user) {
		SubjectGradeCondition subjectGradeCondition = new SubjectGradeCondition();
		subjectGradeCondition.setSchoolId(user.getSchoolId());
		subjectGradeCondition.setDelete(false);
		List<SubjectGrade> subjectGradeList = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
		return subjectGradeList;
	}
	
	/**
	 * 修改年级科目
	 */
	@RequestMapping("/modifySubjectGradePage")
	public ModelAndView modifySubjectGradePage(
			@RequestParam(value = "subjectGradeId", required = true) String subjectGradeId,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@ModelAttribute("subjectCondition") SubjectCondition subjectCondition,
			@CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();
		String viewPath = "";
		try{
			Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
			subjectCondition.setSchoolId(user.getSchoolId());
			subjectCondition.setStageCode(grade.getStageCode());
			
			List<Subject> subjectList = this.subjectService.findSubjectByCondition(subjectCondition, null, null);
			SubjectGrade subjectGrade = this.subjectGradeService.findSubjectGardeById(Integer.parseInt(subjectGradeId));
			mav.addObject("subjectList", subjectList);
			mav.addObject("subjectGrade", subjectGrade);
			mav.addObject("stageCode", grade.getStageCode());
			mav.addObject("gradeId", gradeId);
			mav.addObject("subjectGradeId", subjectGradeId);
			viewPath = "/teach/subjectGrade/modifySubjectGradePage";
			mav.setViewName(viewPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
		
	}
	
	/***
	 * 更新年级科目
	 * @param subjectGradeId
	 * @param subjectId
	 * @return
	 */
	@RequestMapping("/updateSubjectGrade")
	@ResponseBody
	public String updateSubjectGrade(
			@RequestParam(value = "subjectGradeId", required = true) String subjectGradeId,
			@RequestParam(value = "subjectId", required = true) String subjectId){
		try{
			Subject subject = this.subjectService.findSubjectById(Integer.parseInt(subjectId));
			SubjectGrade subjectGrade = this.subjectGradeService.findSubjectGardeById(Integer.parseInt(subjectGradeId));
			subjectGrade.setSubjectCode(subject.getCode());
			subjectGrade.setSubjectName(subject.getName());
			subjectGrade.setModifyDate(new Date());
			subjectGradeService.modify(subjectGrade);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	
	@RequestMapping("/deleteSubjectGrade")
	@ResponseBody
	public String deleteSubjectGrade(
			@RequestParam(value = "subjectGradeId", required = true) String subjectGradeId){
		try{
			SubjectGrade subjectGrade = this.subjectGradeService.findSubjectGardeById(Integer.parseInt(subjectGradeId));
			subjectGrade.setDelete(true);
			subjectGradeService.modify(subjectGrade);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	
	@RequestMapping("/addSubjectGradePage")
	public ModelAndView addSubjectGradePage(
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@ModelAttribute("subjectCondition") SubjectCondition subjectCondition,
			@CurrentUser UserInfo user){
		ModelAndView mav = null;
		String viewPath ="";
		try{
			mav = new ModelAndView();
			Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
			subjectCondition.setSchoolId(user.getSchoolId());
			subjectCondition.setStageCode(grade.getStageCode());
			List<Subject> subjectList = this.subjectService.findSubjectByCondition(subjectCondition, null, null);
			
			mav.addObject("subjectList", subjectList);
			mav.addObject("stageCode", grade.getStageCode());
			mav.addObject("gradeId", gradeId);
			viewPath="/teach/subjectGrade/addSubjectGradePage";
			mav.setViewName(viewPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/addSubjectGradeBatchPage")
	public ModelAndView addSubjectGradeBatchPage(
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@ModelAttribute("subjectCondition") SubjectCondition subjectCondition,
			@CurrentUser UserInfo user){
		ModelAndView mav = null;
		String viewPath ="";
		try{
			mav = new ModelAndView();
			//获得本学校学制表信息
			//List<SchoolSystem> schoolSystemList = this.schoolSystemService.findDefaultGradesOfSchool(user.getSchoolId());
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolId(user.getSchoolId());
			gradeCondition.setSchoolYear(user.getSchoolYear());
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
			//获得本学校的授课科目
			List<Subject> subjectList = this.subjectService.findSubjectsOfSchool(user.getSchoolId());
			
			//mav.addObject("schoolSystemList", schoolSystemList);		
			mav.addObject("gradeList", gradeList);
			mav.addObject("subjectList", subjectList);
			mav.addObject("gradeId", gradeId);
			viewPath="/teach/subjectGrade/addSubjectGrade";
			mav.setViewName(viewPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}
	
	
	/**
	 * 批量添加功能
	 * @param subjectGrade
	 * @param gradeId
	 * @param subjectId
	 * @param stageCode
	 * @param user
	 * @return
	 */
	@RequestMapping("/addSubjectGradeOfBatch")
	@ResponseBody
	public String addSubjectGradeOfBatch(SubjectGrade subjectGrade,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "subjectIds", required = true) String subjectIds,
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@CurrentUser UserInfo user){
		List<Subject> sList = new ArrayList<Subject>();
		try{
			String[] subjectId = subjectIds.split(",");
			for(String sId : subjectId){
				Subject subject = this.subjectService.findSubjectById(Integer.parseInt(sId));
				sList.add(subject);
			}
			this.subjectGradeService.addByBatch(gradeId,sList,stageCode,user.getSchoolId());
		}catch(Exception e){
			e.printStackTrace();
			log.info("新增年科学科异常");
			return ResponseInfomation.OPERATION_FAIL;
		}finally{
			sList.clear();
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	@RequestMapping(value = "/saveSubjectGradeBatch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation saveSubjectGradeBatch(@CurrentUser UserInfo user,
			@RequestParam(value = "originalData", required = true) String originalData,
			@RequestParam(value = "selectedData", required = true) String selectedData) {
		try {
			Integer schoolId = user.getSchoolId();
			
			/* 原有的设置保持，取消的年级科目作废 */
			List<Integer> idTempList = new ArrayList<Integer>();
			if(!"".equals(originalData)) {
				String[] subjectGradeId = originalData.split(";");
				for(int i = 0; i < subjectGradeId.length; i++) {
					idTempList.add(Integer.parseInt(subjectGradeId[i]));
				}
			}
			
			List<Integer> idList = new ArrayList<Integer>();
			SubjectGradeCondition subjectGradeCondition = new SubjectGradeCondition();
			subjectGradeCondition.setSchoolId(schoolId);
			subjectGradeCondition.setDelete(false);
			List<SubjectGrade> subjectGradeList = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
			for(SubjectGrade subjectGrade : subjectGradeList) {
				idList.add(subjectGrade.getId());
			}
			
			idList.removeAll(idTempList);
			for(Integer idTemp : idList) {
				SubjectGrade subjectGrade = new SubjectGrade();
				subjectGrade.setId(idTemp);
				this.subjectGradeService.abandon(subjectGrade);
			}
			
			/* 保存新增加的年级科目  */
			Subject subject = new Subject();
			JSONArray jsonArraySd = JSONArray.fromObject(selectedData);
			for(int i = 0; i < jsonArraySd.size(); i++) {
				SubjectGrade subjectGrade = new SubjectGrade();
				JSONObject jsonTemp = jsonArraySd.getJSONObject(i);
				subjectGrade.setSchoolId(schoolId);     //学校ID
				subjectGrade.setStageCode(jsonTemp.getString("stageCode"));   //学段Code
				subjectGrade.setGradeCode(jsonTemp.getString("gradeCode"));   //年级Code
				subjectGrade.setSubjectCode(jsonTemp.getString("subjectCode"));  //科目Code
				subjectGrade.setCreateDate(new Date());
				subjectGrade.setModifyDate(new Date());
				subject = this.subjectService.findUnique(schoolId, jsonTemp.getString("subjectCode"));
				if(subject != null) {
					subjectGrade.setSubjectName(subject.getName());   //科目名称
				}
				subjectGrade = this.subjectGradeService.add(subjectGrade);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("批量新增年科学科异常");
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	
	@RequestMapping("/addSubjectGrade")
	@ResponseBody
	public String addSubjectGrade(SubjectGrade subjectGrade,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "subjectId", required = true) String subjectId,
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@CurrentUser UserInfo user){
		try{
			Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
			Subject subject = this.subjectService.findSubjectById(Integer.parseInt(subjectId));
//			subjectGrade.setGradeId(Integer.parseInt(gradeId));
			subjectGrade.setGradeCode(grade.getUniGradeCode());  //获得通用年级码
			subjectGrade.setSchoolId(user.getSchoolId());
			subjectGrade.setStageCode(stageCode);
			subjectGrade.setSubjectCode(subject.getCode());
			subjectGrade.setSubjectName(subject.getName());
			subjectGrade.setDelete(false);
			subjectGrade.setCreateDate(new Date());
			subjectGrade.setModifyDate(new Date());
			this.subjectGradeService.add(subjectGrade);
		}catch(Exception e){
			e.printStackTrace();
			log.info("新增年科学科异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 检查同一个年级下的课程是否重复
	 * @param subjectGradeCondition
	 * @param gradeId
	 * @param subjectId
	 * @param stageCode
	 * @param user
	 * @return
	 */
	@RequestMapping("/checkSubjectGrade")
	@ResponseBody
	public String checkSubjectGrade(
			@ModelAttribute("subjectGradeCondition") SubjectGradeCondition subjectGradeCondition,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "subjectId", required = true) String subjectId,
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@CurrentUser UserInfo user){
		try{
				Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
				Subject subject = this.subjectService.findSubjectById(Integer.parseInt(subjectId));
				subjectGradeCondition.setSchoolId(user.getSchoolId());
//				subjectGradeCondition.setGradeId(Integer.parseInt(gradeId));
				subjectGradeCondition.setGradeCode(grade.getUniGradeCode()); //获得通用年级码
				subjectGradeCondition.setStageCode(stageCode);
				subjectGradeCondition.setSubjectCode(subject.getCode());
				List<SubjectGrade>  subjectGradeList = subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
				
				if(subjectGradeList.isEmpty()){
					return "1";
				}else{
					return "0";
				}
		}catch(Exception e){
			e.printStackTrace();
			return "0";
		}
		
	}
	
	/**
	 * 检查同一个年级下的课程是否重复
	 * @param subjectGradeCondition
	 * @param gradeId
	 * @param subjectId
	 * @param stageCode
	 * @param user
	 * @return
	 */
	@RequestMapping("/checkSubjectGradeOfBatch")
	@ResponseBody
	public String checkSubjectGradeOfBatch(
			@ModelAttribute("subjectGradeCondition") SubjectGradeCondition subjectGradeCondition,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "subjectIds", required = true) String subjectIds,
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@CurrentUser UserInfo user){
		try{
			Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
			String[] subjectId = subjectIds.split(",");
			String isCk = "1";
			for(String sId : subjectId){
				Subject subject = this.subjectService.findSubjectById(Integer.parseInt(sId));
				subjectGradeCondition.setSchoolId(user.getSchoolId());
//				subjectGradeCondition.setGradeId(Integer.parseInt(gradeId));
				subjectGradeCondition.setGradeCode(grade.getUniGradeCode()); //获得通用年级码
				subjectGradeCondition.setStageCode(stageCode);
				subjectGradeCondition.setSubjectCode(subject.getCode());
				List<SubjectGrade>  subjectGradeList = subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
				
				if(subjectGradeList.isEmpty()){
					isCk = "1";
				}else{
					isCk = "0";
					break;
				}
			}
			return isCk;
		}catch(Exception e){
			e.printStackTrace();
			return "0";
		}
		
	}
	
}
