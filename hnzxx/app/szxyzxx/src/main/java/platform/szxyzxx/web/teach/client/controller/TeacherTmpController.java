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
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherTmp;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.user.model.UserBinding;
import platform.education.user.service.UserBindingService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtils;

@Controller
@RequestMapping("/tmp/teacher/")
public class TeacherTmpController {
	@Autowired
	@Qualifier("schoolInitService")
	private SchoolInitService schoolInitService;
	@Autowired
	@Qualifier("baseRedisCache")
	private BaseRedisCache<Object> baseRedisCache;
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;
	
	private static final String DIR = "schoolInit/teacher";
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Object indexPage(@CurrentUser UserInfo userInfo) {
		ModelAndView model = new ModelAndView();
		
		String code = getCode(userInfo.getId());
		
		Long warnSize = schoolInitService.countTeacherTmpByCodeAndStatus(code, 1);
		Long errorSize = schoolInitService.countTeacherTmpByCodeAndStatus(code, 2);
		
		model.addObject("warnSize", warnSize);
		model.addObject("errorSize", errorSize);
		model.setViewName(DIR + "/teacher_tmp_index");
		
		return model;
	}
	
	@RequestMapping(value="/list")
	public Object list(@CurrentUser UserInfo userInfo, @ModelAttribute("page") Page page,
			@RequestParam(value="status", defaultValue="0") Integer status,
			@RequestParam(value="index", defaultValue="list") String index) {
		ModelAndView model = new ModelAndView();
		List<TeacherTmp> result = new ArrayList<TeacherTmp>();
		
		Integer userid = userInfo.getId();
		
		String code = getCode(userid);
		
		if(code!=null) {
			result = schoolInitService.findTeacherTmpByCodeAndStatus(code, status, page, Order.desc("id"));
			if(status-0==0) {
				for (TeacherTmp teacherTmp : result) {
					Teacher teacher = teacherService.findTeacherById(teacherTmp.getTeacherId());
					if(teacher==null) {
						continue;
					}
					Long diff = DateUtils.getDateBetween(new Date(), teacher.getCreateDate());
					if(diff-15>=0) {
						teacherTmp.setDelete(false);
					} else {
						teacherTmp.setDelete(true);
					}
				}
			}
		}
		
		model.addObject("result", result);
		model.addObject("status", status);
		if("index".equals(index)) {
			model.setViewName(DIR + "/teacher_tmp_list");
		} else {
			if(status-0==0) {
				model.setViewName(DIR + "/teacher_tmp_ok");
			} else if(status-1==0) {
				model.setViewName(DIR + "/teacher_tmp_warn");
			} else {
				model.setViewName(DIR + "/teacher_tmp_error");
			}
		}
		return model;
	}
	
	private String getCode(Integer userid) {
		String key = "teacher_tmp_" + userid;
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
		
		String key = "teacher_tmp_" + userid;
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
		if(value!=null && !"".equals(value)) {
			String code = (String) value.get("code");
			size = schoolInitService.countTeacherTmpByCodeAndStatus(code, status);
		}
		
		return size;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Object update(TeacherTmp tmp) {
		schoolInitService.modifyTeacherTmp(tmp, 1);
		return "success";
	}
	
	@RequestMapping(value="/getReal", method=RequestMethod.GET)
	public Object getReal(@RequestParam(value="id", required=true)Integer id) {
		ModelAndView model = new ModelAndView();
		TeacherTmp teacherTmp = schoolInitService.findTeacherTmpById(id);
		
		model.setViewName(DIR + "/teacher_tmp_editReal");
		model.addObject("result", teacherTmp);
		
		return model;
	}
	
	@RequestMapping(value="/updateReal", method=RequestMethod.POST)
	@ResponseBody
	public Object updateReal(@CurrentUser UserInfo user, TeacherTmp tmp) {
		String result = "SUCCESS";
		
		Integer teacherId = tmp.getTeacherId();
		String mobile = tmp.getPhone();
		String name = tmp.getName();
		String alias = tmp.getAlias();
		
		TeacherVo teacherVo = new TeacherVo();
		teacherVo.setId(teacherId);
		teacherVo.setMobile(mobile);
		teacherVo.setName(name);
		teacherVo.setAlias(alias);
		teacherVo.setSex(getSexValue(tmp.getSex()));
		
		Teacher teacher = teacherService.findTeacherById(teacherId);
		
		/**如果需要更新手机号，检查手机号是否重复*/
		if(!teacher.getMobile().equals(mobile)) {
			boolean check = teacherService.checkTeacherIsRepeat(mobile, user.getSchoolId());
			if(!check) {
				result = "PHONE_REPEAT";
			}
			UserBinding userBinding = userBindingService.findUnique(teacher.getUserId(), teacher.getMobile());
			if(userBinding!=null) {
				userBinding.setBindingName(mobile);
				userBinding.setModifyDate(new Date());
				userBindingService.modify(userBinding);
			}
		}
		/**修改别名时判断别名是否重复*/
		if(!name.equals(teacher.getName()) || !alias.equals(teacher.getAlias())) {
			boolean check = teacherService.checkAliasIsRepeat(name, alias, user.getSchoolId());
			if(!check) {
				if(alias==null || "".equals(alias)) {
					result = "NAME_REPEAT";
				} else {
					result = "ALIAS_REPEAT";
				}
			}
		}
		
		/**如果数据检验通过则修改教师信息*/
		if("SUCCESS".equals(result)) {
			teacherService.modify(teacherVo);
			schoolInitService.modifyTeacherTmp(tmp, 1);
		}
		return result;
		
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ModelAndView get(@RequestParam(value="id", required=true)Integer id) {
		ModelAndView model = new ModelAndView();
		TeacherTmp teacherTmp = schoolInitService.findTeacherTmpById(id);
		
		model.setViewName(DIR + "/teacher_tmp_edit");
		model.addObject("result", teacherTmp);
		
		return model;
	}
	
	@RequestMapping(value="batchDelete",  method=RequestMethod.POST)
	@ResponseBody
	public Object delete(@CurrentUser UserInfo userInfo,
			@RequestParam(value="ids", required=true)String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Integer> errorIds = new ArrayList<Integer>();
		
		Integer[] teacherIds = null;
		try {
			teacherIds = JSON.parseObject(ids, Integer[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(teacherIds==null) {
			result.put("status", "DATA_ERROR");
		} else if(teacherIds.length==0) {
			result.put("status", "DATA_NO_FIND");
		} else {
			String teacherType = SysContants.USER_TYPE_TEACHER;
			for (Integer id : teacherIds) {
				TeacherTmp teacher = schoolInitService.findTeacherTmpById(id);
				if(teacher.getTeacherId()!=null) {
					Teacher tmp = teacherService.findTeacherById(id);
					Long diff = DateUtils.getDateBetween(new Date(), tmp.getCreateDate());
					if(diff-15>=0) {
						errorIds.add(id);
					} else {
						teacherService.deleteTeacherAllInfoById(teacher.getTeacherId(), teacherType);
					}
				}
				schoolInitService.deleteTeacherTmpById(id);
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
	
	private String getSexValue(String sex) {
		String value = "0";
		if(sex!=null) {
			if("男".equals(sex)) {
				value="1";
			} else if("女".equals(sex)) {
				value="2";
			}
		}
		return value;
	}
}