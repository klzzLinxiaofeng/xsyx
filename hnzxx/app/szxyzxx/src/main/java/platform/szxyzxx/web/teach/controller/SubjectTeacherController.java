package platform.szxyzxx.web.teach.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.SubjectTeacherCondition;
import platform.education.generalTeachingAffair.vo.SubjectTeacherVo;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalcode.vo.StageCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 科任教师管理
 * 
 * @author huangyanchun
 *
 */

@RequestMapping(value = "/teach/subjectTeacher")
public class SubjectTeacherController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(SubjectTeacherController.class);
	private final static String viewBasePath = "/teach/subjectTeacher";

	@RequestMapping(value = "/subjectTeacherList")
	public ModelAndView getSubjectTeacherList(
			@ModelAttribute("stageCondition") StageCondition stageCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		try {
		} catch (Exception e) {
			log.info("查询科任教师列表异常");
			e.printStackTrace();
		}

		mav.setViewName(viewBasePath + "/subjectTeacherIndex");
		return mav;
	}

	/**
	 * 异步加载科目列表
	 * 
	 * @param stageCode
	 *            * @param schoolId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getSubjectList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSubjectList(
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @CurrentUser UserInfo user)
			throws IOException {
		schoolId = user.getSchoolId();
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setStageCode(stageCode);
		subjectCondition.setSchoolId(schoolId);
		Map<String, Object> result = new HashMap<String, Object>();
		// 科目表
		List<Subject> subjectList = this.subjectService.findSubjectByCondition(
				subjectCondition, null, null);
		result.put("subjectList", subjectList);

		return result;
	}

	/**
	 * 异步加载科任教师列表
	 * 
	 * @param stageCode
	 *            * @param schoolId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getSubjectTeacherList", method = RequestMethod.POST)
	public ModelAndView getSubjectTeacherList(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @CurrentUser UserInfo user,
			Model model) throws IOException {
		schoolId = user.getSchoolId();
		SubjectTeacherCondition subjectTeacherCondition = new SubjectTeacherCondition();
		subjectTeacherCondition.setSchoolId(schoolId);
		subjectTeacherCondition.setStageCode(stageCode);
		subjectTeacherCondition.setSubjectCode(subjectCode);
		List<SubjectTeacherVo> subjectTeacherList = this.subjectTeacherService
				.findSubjectTeacherVoByCondition(subjectTeacherCondition, page,
						null);
		model.addAttribute("subjectTeacherList", subjectTeacherList);
		return new ModelAndView(viewBasePath + "/subjectTeacherList",
				model.asMap());
	}

	/**
	 * 新增科任教师页面
	 */
	@RequestMapping("/addSubjectTeacherPage")
	public ModelAndView addSubjectTeacherPage(
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@RequestParam(value = "subjectCode", required = true) String subjectCode,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		schoolId = user.getSchoolId();
		// 教师ID
		TeacherCondition teacherCondition = new TeacherCondition();
		teacherCondition.setSchoolId(schoolId);
		List<Teacher> teacherList = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
		// 担任科目
		Subject subject = this.subjectService.findUnique(schoolId, stageCode != null ? stageCode.trim() : "-1", subjectCode);
		
		mav.addObject("stageCode", stageCode);
		mav.addObject("subject", subject);
		mav.addObject("teacherList", teacherList);
		mav.setViewName(viewBasePath + "/addSubjectTeacherPage");
		return mav;
	}

	/**
	 * 保存科任教师
	 */
	@RequestMapping("/addSubjectTeacher")
	@ResponseBody
	public String addSubjectTeacher(
			@ModelAttribute("SubjectTeacherCondition") SubjectTeacherCondition condition,
			@CurrentUser UserInfo user) {
		String message = ResponseInfomation.OPERATION_SUC;
//		String name = "";
		try {
			String teacherIds =condition.getIds().substring(1);
			String [] teacherId = teacherIds.split(",");
			boolean flag = teacherId[0]=="";
			if(!flag){
				Map<String,String>teacherIdMaps = new HashMap<String, String>();
				for(int i=0;i<teacherId.length;i++){
					teacherIdMaps.put(teacherId[i], teacherId[i]);
					
				}
				
				for(String tid:teacherIdMaps.keySet()){
					SubjectTeacherCondition subjectTeacherCondition = new SubjectTeacherCondition();
					subjectTeacherCondition.setSchoolId(user.getSchoolId());
					subjectTeacherCondition.setTeacherId(Integer.parseInt(tid));
					subjectTeacherCondition.setSubjectCode(condition.getSubjectCode());
					subjectTeacherCondition.setStageCode(condition.getStageCode());
					List<SubjectTeacher> subjectTeacherList = this.subjectTeacherService
							.findSubjectTeacherByCondition(subjectTeacherCondition, null,
									null);
					
					Teacher t = this.teacherService.findTeacherById(Integer.parseInt(tid));
					SubjectTeacher st = new SubjectTeacher();
					if(subjectTeacherList.size()==0){
						st.setSchoolId(user.getSchoolId());
						st.setStageCode(condition.getStageCode());
						st.setSubjectCode(condition.getSubjectCode());
						st.setTeacherId(t.getId());
						st.setName(t.getName());
						st.setCreateDate(new Date());
						st.setModifyDate(new Date());
						this.subjectTeacherService.add(st);
					}else{
//						name += t.getName()+",";
						
						//System.out.println("teacherId为:"+tid+"的科任教师已存在!");
					}
				}
			}
		} catch (Exception e) {
			log.info("新增科任教师异常..");
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		/*if(name!=null&&!"".equals(name.trim())){
			message = name+"科任教师已存在!";
		}
		
		return message; */
		return message;

		}

	/**
	 * 修改科任教师页面
	 */

	@RequestMapping("/modifySubjectTeacherPage")
	public ModelAndView modifySubjectTeacherPage(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@RequestParam(value = "subjectCode", required = true) String subjectCode,
			@CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		schoolId = user.getSchoolId();
		try {
			SubjectTeacher subjectTeacher = this.subjectTeacherService
					.findSubjectTeacherById(id);
			// 科目表
			SubjectCondition subjectCondition = new SubjectCondition();
			subjectCondition.setSchoolId(schoolId);
			subjectCondition.setStageCode(stageCode != null ? stageCode.trim() : "-1");
			List<Subject> subjectList = this.subjectService
					.findSubjectByCondition(subjectCondition, null, null);

			mav.addObject("stageCode", stageCode);
			mav.addObject("subjectList", subjectList);
			mav.addObject("subjectTeacher", subjectTeacher);
			mav.setViewName(viewBasePath + "/modifySubjectTeacherPage");

		} catch (Exception e) {
			log.info("====修改班级异常===");
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 更新科任教师
	 */
	@RequestMapping("/updateSubjectTeacher")
	@ResponseBody
	public ResponseInfomation updateupdateSubjectTeacher(
			SubjectTeacher subjectTeacher) {
		SubjectTeacher st = null;
		try {

			//根据条件验证该教师是否已经任课了该课程
			SubjectTeacherCondition subjectTeacherCondition = new SubjectTeacherCondition();
			subjectTeacherCondition.setSchoolId(subjectTeacher.getSchoolId());
			subjectTeacherCondition.setTeacherId(subjectTeacher.getTeacherId());
			subjectTeacherCondition.setSubjectCode(subjectTeacher.getSubjectCode());
			subjectTeacherCondition.setStageCode(subjectTeacher.getStageCode());
			List<SubjectTeacher> subjectTeacherList = this.subjectTeacherService
					.findSubjectTeacherByCondition(subjectTeacherCondition, null,
							null);
			if(subjectTeacherList.size()==0){
				subjectTeacher.setModifyDate(new Date());
				st = subjectTeacherService.modify(subjectTeacher);
			}
		} catch (Exception e) {
			log.info("...更新科目异常...");
			e.printStackTrace();
		}
		return st != null ? new ResponseInfomation(subjectTeacher.getId(),  
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 删除科任教师
	 */
	@RequestMapping("/deleteSubjectTeacher")
	@ResponseBody
	public ResponseInfomation deleteSubjectTeacher(
			@RequestParam(value = "id", required = true) Integer id) {
		SubjectTeacher s = null;
		try {
			SubjectTeacher subjectTeacher = new SubjectTeacher();
			subjectTeacher.setId(id);
			s = subjectTeacherService.modify(subjectTeacher);
			subjectTeacherService.remove(subjectTeacher);
		} catch (Exception e) {
			log.info("删除科任教师异常....");
			e.printStackTrace();
		}
		return s != null ? new ResponseInfomation(s.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 验证该科任教师是否存在
	 * @param condition
	 * @param user
	 * @return
	 */
	@RequestMapping("/checkTeacher")
	@ResponseBody
	public List<String> checkTeacher(@ModelAttribute("SubjectTeacherCondition") SubjectTeacherCondition condition,
			@CurrentUser UserInfo user){
		List<String>check = new ArrayList<String>();
		String isTeacher = "";
		String name="";
		try {

			String teacherIds = condition.getIds().substring(1,condition.getIds().length());
			String [] teacherId = teacherIds.split(",");
			boolean flag = teacherId[0]=="";
			if(!flag){
				Map<String,String>teacherIdMaps = new HashMap<String, String>();
				for(int i=0;i<teacherId.length;i++){
					teacherIdMaps.put(teacherId[i], teacherId[i]);
					
				}
				
				for(String tid:teacherIdMaps.keySet()){
					SubjectTeacherCondition subjectTeacherCondition = new SubjectTeacherCondition();
					subjectTeacherCondition.setSchoolId(user.getSchoolId());
					subjectTeacherCondition.setTeacherId(Integer.parseInt(tid));
					subjectTeacherCondition.setSubjectCode(condition.getSubjectCode());
					subjectTeacherCondition.setStageCode(condition.getStageCode());
					List<SubjectTeacher> subjectTeacherList = this.subjectTeacherService
							.findSubjectTeacherByCondition(subjectTeacherCondition, null,
									null);
					if(subjectTeacherList.size()>0){
						Teacher t = this.teacherService.findTeacherById(Integer.parseInt(tid));
						name+=t.getName()+",";
						isTeacher = ResponseInfomation.OPERATION_SUC;
					}else{
						isTeacher = ResponseInfomation.OPERATION_FAIL;
					}
				}
				check.add(name+isTeacher);
			}
			
		} catch (Exception e) {

			log.info("该科任教师已存在..");
			e.printStackTrace();
			return check;
//			return ResponseInfomation.OPERATION_FAIL;
		}
		return check;
	}
	
	
	
}
