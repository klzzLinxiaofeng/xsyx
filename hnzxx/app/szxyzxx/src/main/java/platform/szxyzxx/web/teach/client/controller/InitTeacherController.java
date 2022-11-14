package platform.szxyzxx.web.teach.client.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.EmployeeList;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.generalcode.service.JcGcCacheService;
import platform.education.user.model.UserBinding;
import platform.education.user.service.UserBindingService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.job.HikvisonJob;
import platform.szxyzxx.web.common.job.UserCardJob;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtils;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/teacher/init")
public class InitTeacherController {
	private static final Logger log = LoggerFactory.getLogger(InitTeacherController.class);
	/**
	 * 食堂接口
	 */
	private static String fileName;
	private static String address;
	private static String canteen;

	private static String libraryACnteen;
	private static String libraryLogin;
	private static String libraryCreate;

	/**
	 * 海康接口
	 */
	private static String hikvisionHost;
	private static String hikvisionAppKey;
	private static String hikvisionAppSecret;
	private static String hikvisionAddPersonUrl;
	private static String hikvisionBindCarUrl;
	private static String untieCarUrl;

	static {
		fileName = "System.properties";
		// 食堂
		address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
		canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");

		// 图书馆
		libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
		libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
		libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

		//闸机
		hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
		hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
		hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
		hikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");
		hikvisionBindCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.bindings");
		untieCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.deletion");
	}



	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	@Autowired
	@Qualifier("departmentTeacherService")
	private DepartmentTeacherService departmentTeacherService;
	@Autowired
	@Qualifier("subjectTeacherService")
	private SubjectTeacherService subjectTeacherService;
	@Autowired
	@Qualifier("teacherAssistService")
	private TeacherAssistService teacherAssistService;
	@Autowired
	@Qualifier("teacherSortService")
	private TeacherSortService teacherSortService;
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	@Autowired
	@Qualifier("baseRedisCache")
	private BaseRedisCache<Object> baseRedisCache;
	@Autowired
	@Qualifier("jcGcCacheService")
	protected JcGcCacheService jcGcCacheService;
	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;
	@Autowired
	@Qualifier("schoolInitService")
	private SchoolInitService schoolInitService;

	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;

	@Autowired
	@Qualifier("httpService")
	private HttpService httpService;

	@Autowired
	@Qualifier("libraryService")
	private LibraryService libraryService;

	//注入当个定时任务用于获取新增用户的食堂卡号和推送至海康门禁系统
	@Autowired
	private UserCardJob userCardJob;
	@Autowired
	private HikvisonJob hikvisonJob;


	private static final String DIR = "schoolInit/teacher";

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Object indexPage() {
		ModelAndView model = new ModelAndView();

		model.setViewName(DIR + "/teacher_index");

		return model;
	}

	@RequestMapping(value="/import", method=RequestMethod.GET)
	public Object index(@CurrentUser UserInfo userInfo) {
		ModelAndView model = new ModelAndView();

		String key = "teacher_tmp_" + userInfo.getId();
		Object value = baseRedisCache.getCacheObject(key);

		model.setViewName(DIR + "/teacher_import");
		model.addObject("value", value);
		model.addObject("userid", userInfo.getId());
		model.addObject("schoolid", userInfo.getSchoolId());
		model.addObject("schoolYear", userInfo.getSchoolYear());

		return model;
	}

	/**
	 * 查询老师列表
	 * @param userInfo
	 * @param page
	 * @param index
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(@CurrentUser UserInfo userInfo, @ModelAttribute("page") Page page,
		@RequestParam(value="name", required=false)String name,
		@RequestParam(value="index", defaultValue="index")String index) throws IllegalAccessException, InvocationTargetException {

		Integer schoolid = userInfo.getSchoolId();
		List<Teacher> list = teacherService.findTeacherByLikeNameAndSchool(name, schoolid, page, Order.desc("id"));

		List<Object> result = new ArrayList<Object>(list.size());

		for (Teacher teacher : list) {
			TeacherVo entity = new TeacherVo();
			BeanUtils.copyProperties(teacher, entity);
			entity.setSex(getSexValue(teacher.getSex()));
			entity.setEmpCard(teacher.getEmpCard());
			Long diff = DateUtils.getDateBetween(new Date(), entity.getCreateDate());
			if(diff-15>=0) {
				entity.setIsDelete(false);
			} else {
				entity.setIsDelete(true);
			}

			/**查询教师所在部门*/
			List<DepartmentTeacher>  departmentTeacherList = departmentTeacherService
					.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(), schoolid);
			String departmentName = "";
			/**部门用、分隔开*/
			for (DepartmentTeacher departmentTeacher : departmentTeacherList) {
				departmentName=departmentName + departmentTeacher.getDepartmentName()+"、";
			}
			/**去掉最后的、*/
			if(!"".equals(departmentName)) {
				departmentName=departmentName.substring(0, departmentName.length()-1);
			}
			entity.setDepartmentName(departmentName);
			entity.setJobState(getDictNameByValue("JY-JZGDQZT",teacher.getJobState()));
			result.add(entity);
		}

		ModelAndView model = new ModelAndView();
		if(index.equals("list")) {
			model.setViewName(DIR + "/teacher_manage_list");
		} else {
			model.setViewName(DIR + "/teacher_manage");
		}
		model.addObject("result", result);

		return model;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public Object get() {
		ModelAndView model = new ModelAndView();
		model.setViewName(DIR+"/teacher_add");
		return model;
	}

	/**
	 * 添加教师
	 * @param userInfo 当前用户
	 * @param data json格式对象
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Object add(@CurrentUser UserInfo userInfo,
			@RequestParam(value="data", required=true)String data) {
		JSONObject jsonObject = JSON.parseObject(data);
		String name = jsonObject.getString("name");
		String sex = jsonObject.getString("sex");
		String alias = jsonObject.getString("alias");
		String phone = jsonObject.getString("phone");
		String department = jsonObject.getString("department");
		String position = jsonObject.getString("position");
		String gh = jsonObject.getString("gh");
		String kh = jsonObject.getString("kh");
		Integer schoolId = userInfo.getSchoolId();
		Integer appid = SysContants.SYSTEM_APP_ID;
		String teacherType = SysContants.USER_TYPE_TEACHER;
		List<EmployeeList> list = new ArrayList<>();
		Map<String, Object> result = teacherService.addTeacherFromExcelImport(gh,kh,name, sex, alias, phone, department, position,
				schoolId, appid, teacherType, list);

		TeacherTmp tmp = JSONObject.parseObject(JSON.toJSONString(result), TeacherTmp.class);
		Integer status = tmp.getStatus();

		if(status==0) {

			//推送到海康门禁
			httpService.addUserToHk(false, gh, kh, HikvisonJob.getHkConfig());

			// 食堂接口数据
			if (list != null && list.size() > 0) {
				// 将保存的教师信息发送到远程接口
				if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
					Object object = JSONObject.toJSON(list);
					JSONObject param = new JSONObject();
					param.put("sign_name","kksss");
					//param.put("tran_code","emp_add");
					param.put("tran_code","emp_add");
					param.put("employeeList",object);
					//canteenThreadPoolExecutor.submit
					HttpRequestResult httpRequestResult=null;
					HttpRequestConfig config = HttpRequestConfig.create().url(address + canteen)
							.addHeader("content-type", "application/json")
							.httpEntityType(HttpEntityType.ENTITY_STRING);
					//config.json(shiTangDate.toString());
					config.json(param.toString());
					try {
						httpRequestResult = HttpClientUtils.post(config);
						if (httpRequestResult == null) {
							log.info("空数据");
						}
						if (200 == httpRequestResult.getCode()) {
							String responseText = httpRequestResult.getResponseText();
							if (("").equals(responseText) || responseText == null) {
								log.info("空数据2");
							}
							JSONObject jsonObject2 = JSONObject.parseObject(responseText);
							String statusCode = jsonObject2.getString("statusCode");
							String result2 = jsonObject2.getString("result");
							if (("").equals(statusCode) || statusCode == null && ("").equals(result2) || result2 == null) {
								log.info("返回数据3");
							}
							if ("200".equals(statusCode) && "true".equals(result2)) {
								List<Map<String,Object>> list2= (List<Map<String, Object>>) jsonObject2.get("data");
								if(list2.size()<0){
									log.info("返回空数据4");
								}
								/*食堂返回信息
								 * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
								 */
								for(Map<String,Object> aa:list2){
									if(aa.get("deal_msg")=="success"){
										log.info("食堂接口--添加用户信息到远程接口--食堂接口成功");
										Teacher teacher = new Teacher();
										teacher.setEmpCode((String) aa.get("emp_code"));
										teacher.setIsSendCanteen(1);
										teacherService.updateTeacherSendCanteen(teacher);
									}else{
										log.info("添加失败"+aa.get("deal_msg"));
									}
								}
							} else {
								log.info("食堂接口--添加用户信息到远程接口失败--食堂接口成功 错误信息 {}", httpRequestResult);
							}
						}else {
							log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
						}
					log.info("食堂接口--食堂添加接口添加状态{}"," 返回信息: {}----"+httpRequestResult);
					} catch (IOException e) {
						log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}"+ e);
					}
				} else {
					log.error("调用远程接口失败，请检查配置接口信息是否正确！");
				}
					/*//教师修改 11，15
					List<List<DetailVo>> postData = new ArrayList<>();
					postData.add(list);
					// 判断教师信息是否添加成功 调用远程接口发送数据 数据类型
					httpService.addEmploye(null, address + canteen, null, 2, postData, 0, null);
				// 新增时没有添加卡号功能，所以暂时注释，因为在补卡或者叫修改卡号的时候，也会重新推送用户，修改卡号和新增用户使用同一个接口（由于图书馆没有提供修改卡号功能）
                // 发送用户信息到图书馆*/
                if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
                    // 发送用户信息到图书馆
                    libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
                } else {
                    log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
                }


				// 定时任务发送用户信息到海康
//				if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//					ArtemisConfig artemisConfig = new ArtemisConfig();
//					artemisConfig.setHost(hikvisionHost);
//					artemisConfig.setAppKey(hikvisionAppKey);
//					artemisConfig.setAppSecret(hikvisionAppSecret);
//					// 0：学生 1：教师
//					httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
//				} else {
//					log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
//				}
            }
		}
		return result;
	}

	/**
	 * 更新教师信息
	 * @param teacherVo
	 * @return SUCCESS更新成功  DATA_NO_FIND找不到记录
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Object add(@CurrentUser UserInfo user, TeacherVo teacherVo) {
		String result = "SUCCESS";

		String mobile = teacherVo.getMobile();
		String name = teacherVo.getName();
		String alias = teacherVo.getAlias();
		Teacher teacher = teacherService.findTeacherById(teacherVo.getId());

		/**如果需要更新手机号，检查手机号是否重复*/
		if(!teacher.getMobile().equals(mobile)) {
			Integer schoolId = user.getSchoolId();
			boolean check = teacherService.checkTeacherIsRepeat(mobile, schoolId);

			if(!check) {
				result = "PHONE_REPEAT";
				return result;
			}
			Parent parent = parentService.findParentByMobileAndSchoolId(mobile, schoolId);
			if(parent!=null) {
				result = "PHONE_REPEAT";
				return result;
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
			boolean check = this.checkAliasIsRepeat(name, alias, user.getSchoolId(), teacher.getId());
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
		}
		return result;
	}


	private boolean checkAliasIsRepeat(String name, String alias, Integer schoolId, Integer teacherid) {
		List<Teacher> result = null;

		TeacherCondition condition = new TeacherCondition();
		condition.setSchoolId(schoolId);
		if(alias==null || "".equals(alias)) {
			alias = "";
		} else {
			condition.setAlias(alias);
			result = teacherService.findTeacherByCondition(condition, null, null);
			if(result.size()>0) {
				return false;
			}
		}

		condition.setName(name);
		result = teacherService.findTeacherByCondition(condition, null, null);
		for (Teacher teacher : result) {
			if(teacherid-teacher.getId()==0) {
				continue;
			}
			String tmp = teacher.getAlias();
			if(tmp==null) {
				tmp="";
			}
			if(alias.equals(tmp)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 删除教师信息
	 * @param userInfo 当前用户
	 * @return
	 */
	@RequestMapping(value="batchDelete")
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
				Teacher teacher = teacherService.findTeacherById(id);
				Long diff = DateUtils.getDateBetween(new Date(), teacher.getCreateDate());
				if(diff-15>=0) {
					errorIds.add(id);
				} else {
					teacherService.deleteTeacherAllInfoById(id, teacherType);
					schoolInitService.deleteTeacherTmpByTeacherId(id);
				}

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

	public String getDictNameByValue(String tableCode,String val){
		if(val!="" || !"".equals(val)){
			Object obj = this.jcGcCacheService.getNameByValue(tableCode, val);
			return obj==null?"":obj.toString();
		}
		return "";
	}

	private String getSexValue(String sex) {
		String value = "未知";
		if(sex!=null) {
			if("1".equals(sex)) {
				value="男";
			} else if("2".equals(sex)) {
				value="女";
			}
		}
		return value;
	}
}
