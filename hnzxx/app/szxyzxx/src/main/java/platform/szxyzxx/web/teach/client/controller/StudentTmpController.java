package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentTmp;
import platform.education.generalTeachingAffair.model.TeacherTmp;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.user.model.UserBinding;
import platform.education.user.service.UserBindingService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtils;

@Controller
@RequestMapping("/tmp/student/")
public class StudentTmpController {
	@Autowired
	@Qualifier("schoolInitService")
	private SchoolInitService schoolInitService;
	@Autowired
	@Qualifier("baseRedisCache")
	private BaseRedisCache<Object> baseRedisCache;
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;
	
	private static final String DIR = "schoolInit/student";
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Object indexPage(@CurrentUser UserInfo userInfo) {
		ModelAndView model = new ModelAndView();
		
		String code = getCode(userInfo.getId());
		
		Long warnSize = schoolInitService.countStudentTmpByCodeAndStatus(code, 1);
		Long errorSize = schoolInitService.countStudentTmpByCodeAndStatus(code, 2);
		
		model.addObject("warnSize", warnSize);
		model.addObject("errorSize", errorSize);
		model.setViewName(DIR + "/student_tmp_index");
		
		return model;
	}
	
	@RequestMapping(value="/list")
	public Object list(@CurrentUser UserInfo userInfo, @ModelAttribute("page") Page page,
			@RequestParam(value="status", defaultValue="0") Integer status,
			@RequestParam(value="index", defaultValue="list") String index) {
		ModelAndView model = new ModelAndView();
		List<StudentTmp> result = new ArrayList<StudentTmp>();
		
		Integer userid = userInfo.getId();
		String code = getCode(userid);
		
		if(code!=null) {
			result = schoolInitService.findStudentTmpByCodeAndStatus(code, status, page, Order.desc("id"));
			if(status-0==0) {
				for (StudentTmp studentTmp : result) {
					Student student = studentService.findStudentById(studentTmp.getStudentId());
					if(student==null) {
						studentTmp.setDelete(true);
						continue;
					}
					Long diff = DateUtils.getDateBetween(new Date(), student.getCreateDate());
					if(diff-15>=0) {
						studentTmp.setDelete(false);
					} else {
						studentTmp.setDelete(true);
					}
				}
			}
		}
		
		model.addObject("result", result);
		model.addObject("status", status);
		if("index".equals(index)) {
			model.setViewName(DIR + "/student_tmp_list");
		} else {
			if(status-0==0) {
				model.setViewName(DIR + "/student_tmp_ok");
			} else if(status-1==0) {
				model.setViewName(DIR + "/student_tmp_warn");
			} else {
				model.setViewName(DIR + "/student_tmp_error");
			}
		}
		return model;
	}
	
	private String getCode(Integer userid) {
		String key = "student_tmp_" + userid;
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
		if(value!=null && !"".equals(value)) {
			String code = (String) value.get("code");
			return code;
		}
		return null;
	}
	
	@RequestMapping(value="/count", method=RequestMethod.GET)
	@ResponseBody
	public Object count(UserInfo userInfo,
			@RequestParam(value="status", defaultValue="0") Integer status) {
		Long size = 0L;
		
		Integer userid = userInfo.getId();
		
		String key = "student_tmp_" + userid;
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
		if(value!=null && !"".equals(value)) {
			String code = (String) value.get("code");
			size = schoolInitService.countStudentTmpByCodeAndStatus(code, status);
		}
		
		return size;
	}
	
	@RequestMapping(value="/updateReal", method=RequestMethod.POST)
	@ResponseBody
	public Object updateReal(@RequestParam(value="tmpId")Integer tmpId,
			@RequestParam(value="studentId")Integer studentId,
			@RequestParam(value="name")String name,
			@RequestParam(value="oldMobile")String oldMobile,
			@RequestParam(value="newMobile")String newMobile) {
		StudentTmp tmp = schoolInitService.findStudentTmpById(tmpId);
		
		Parent parent = parentService.findParentByMobile(newMobile);
		if(parent!=null) {
			return "PHONE_REPEAT";
		}
		parent = parentService.findParentByMobile(oldMobile);
		
		/**原电话号码和新电话号码不一样，需要修改到userbing*/
		if(!newMobile.equals(newMobile)) {
			parent.setMobile(newMobile);
			tmp.setGuardianPhone(newMobile);
			
			UserBinding userBinding = userBindingService.findUnique(parent.getUserId(), oldMobile);
			if(userBinding!=null) {
				userBinding.setBindingName(newMobile);
				userBinding.setModifyDate(new Date());
				userBindingService.modify(userBinding);
			}
		}
		parent.setName(name);
		parentService.modify(parent);
		
		tmp.setGuardianPhone(newMobile);
		tmp.setId(tmpId);
		tmp.setGuardian(name);
		schoolInitService.modifyStudentTmp(tmp, 1);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Object update(StudentTmp tmp) {
		schoolInitService.modifyStudentTmp(tmp, 1);
		return "success";
	}
	
	@RequestMapping(value="/getReal", method=RequestMethod.GET)
	public Object getReal(@RequestParam(value="id", required=true)Integer id) {
		ModelAndView model = new ModelAndView();
		StudentTmp studentTmp = schoolInitService.findStudentTmpById(id);
		
		model.setViewName(DIR + "/student_tmp_editReal");
		model.addObject("result", studentTmp);
		
		return model;
	}
	
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ModelAndView get(@RequestParam(value="id", required=true)Integer id) {
		ModelAndView model = new ModelAndView();
		StudentTmp studentTmp = schoolInitService.findStudentTmpById(id);
		
		model.setViewName(DIR + "/student_tmp_edit");
		model.addObject("result", studentTmp);
		
		return model;
	}
	
	@RequestMapping(value="batchDelete",  method=RequestMethod.POST)
	@ResponseBody
	public Object delete(@CurrentUser UserInfo userInfo,
			@RequestParam(value="ids", required=true)String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Integer> errorIds = new ArrayList<Integer>();
		
		Integer[] studentIds = null;
		try {
			studentIds = JSON.parseObject(ids, Integer[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(studentIds==null) {
			result.put("status", "DATA_ERROR");
		} else if(studentIds.length==0) {
			result.put("status", "DATA_NO_FIND");
		} else {
			String studentType = SysContants.USER_TYPE_STUDENT;
			String parentType = SysContants.USER_TYPE_PARENT;
			for (Integer id : studentIds) {
				StudentTmp student = schoolInitService.findStudentTmpById(id);
				Integer studentId = student.getStudentId();
				if(studentId!=null) {
					Student tmp = studentService.findStudentById(studentId);
					Long diff = DateUtils.getDateBetween(new Date(), tmp.getCreateDate());
					if(diff-15>=0) {
						errorIds.add(studentId);
					} else {
						studentService.removeAllStudentInfoById(studentId, studentType, parentType);
					}
					
				}
				schoolInitService.deleteStudentTmpById(id);
			}
		}
		if(errorIds.size()>0) {
			result.put("status", "ERROR");
			result.put("message", "Id=" + errorIds.toString() + "的记录不在删除时间内，无法删除这些记录");
		} else {
			result.put("status", "SUCCESS");
		}
		return result;
	}
}