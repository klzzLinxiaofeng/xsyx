package platform.szxyzxx.web.teach.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.vo.NormalSummaryData;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/school/moral")
public class MoralH5PageController extends BaseController{
	
	private final static String BASE_PATH = "/teach/MoralH5Page/";
	
	@RequestMapping("/task/add")
	@ResponseBody
	public ResponseInfomation addSchoolTask(
			@RequestParam(value = "schoolIds",required = false) String schoolIds){
		try {
			String[] idArr = schoolIds.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < idArr.length; i++) {
				list.add(Integer.parseInt(idArr[i]));
			}
			if(list != null && list.size() > 0){
				for(Integer schoolId : list){
					if(schoolId != null){
						List<SchoolTerm> termList = schoolTermService.getSchoolTermOfSchool(schoolId);
						for(SchoolTerm term : termList){
							teamApsService.AddTeamTask(schoolId, term.getCode());
							studentApsService.addStudentEvaluationTask(schoolId, term.getCode());
						}
					}
				}
			}
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
	}
	
	
	//班集体评价h5页面
	@RequestMapping("/team/summany")//此路径用于进入h5页面
	public ModelAndView teamSummany(
			@RequestParam(value = "schoolId",required = false) String schoolId,
			@RequestParam(value = "userId",required = false)String userId,
			@RequestParam(value = "role",required = false)String role,
			@RequestParam(value = "teamId",required = false)String teamId,
			@RequestParam(value = "gradeId",required = false)String gradeId,
			@RequestParam(value = "schoolYear",required = false)String schoolYear,
			@RequestParam(value = "schoolTermCode",required = false)String schoolTermCode,
			@RequestParam(value = "weekName",required = false)String weekName,
			@RequestParam(value = "beginDate",required = false)String beginDate,
			@RequestParam(value = "endDate",required = false)String endDate,
			@RequestParam(value = "flag",required = false)String flag,
			Model model){
		String path;
		if("1".equals(flag)){
			path = "classIntoTeamH5Page";
		}else{
			path = "teamH5Page";
		}
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("userId",userId);
		model.addAttribute("role",role);
		model.addAttribute("teamId",teamId);
		model.addAttribute("gradeId",gradeId);
		model.addAttribute("schoolYear",schoolYear);
		model.addAttribute("schoolTermCode",schoolTermCode);
		model.addAttribute("weekName",weekName);
		model.addAttribute("beginDate",beginDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("flag",flag);
		return new ModelAndView(BASE_PATH + path);
	}
	
	//发展评价卡h5页面
	@RequestMapping("/normal/summany")
	public ModelAndView normalSummanyForTeacher(
			@RequestParam(value = "schoolId",required = false) String schoolId,
			@RequestParam(value = "userId",required = false)String userId,
			@RequestParam(value = "role",required=false)String role,Model model){
		String path="normalH5Page";
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("userId",userId);
		model.addAttribute("role",role);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//激励评价h5页面
	@RequestMapping("/incentive/summany")
	public ModelAndView incentiveSummany(
			@RequestParam(value = "schoolId",required = false) String schoolId,
			@RequestParam(value = "userId",required = false)String userId,
			@RequestParam(value = "role",required=false)String role,Model model){
		String path = "incentiveH5Page";
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("userId",userId);
		model.addAttribute("role",role);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//课堂优化h5页面
	@RequestMapping("/class/summany")
	public ModelAndView classSummany(
			@RequestParam(value = "schoolId",required = false) String schoolId,
			@RequestParam(value = "userId",required = false)String userId,
			@RequestParam(value = "role",required=false)String role,Model model){
		String path = "classH5Page";
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("userId",userId);
		model.addAttribute("role",role);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
}