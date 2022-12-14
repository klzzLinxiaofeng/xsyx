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
	 * ????????????
	 */
	private static String fileName;
	private static String address;
	private static String canteen;

	private static String libraryACnteen;
	private static String libraryLogin;
	private static String libraryCreate;

	/**
	 * ????????????
	 */
	private static String hikvisionHost;
	private static String hikvisionAppKey;
	private static String hikvisionAppSecret;
	private static String hikvisionAddPersonUrl;
	private static String hikvisionBindCarUrl;
	private static String untieCarUrl;

	static {
		fileName = "System.properties";
		// ??????
		address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
		canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");

		// ?????????
		libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
		libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
		libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

		//??????
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

	//?????????????????????????????????????????????????????????????????????????????????????????????
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
	 * ??????????????????
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

			/**????????????????????????*/
			List<DepartmentTeacher>  departmentTeacherList = departmentTeacherService
					.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(), schoolid);
			String departmentName = "";
			/**?????????????????????*/
			for (DepartmentTeacher departmentTeacher : departmentTeacherList) {
				departmentName=departmentName + departmentTeacher.getDepartmentName()+"???";
			}
			/**??????????????????*/
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
	 * ????????????
	 * @param userInfo ????????????
	 * @param data json????????????
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

			//?????????????????????
			httpService.addUserToHk(false, gh, kh, HikvisonJob.getHkConfig());

			// ??????????????????
			if (list != null && list.size() > 0) {
				// ?????????????????????????????????????????????
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
							log.info("?????????");
						}
						if (200 == httpRequestResult.getCode()) {
							String responseText = httpRequestResult.getResponseText();
							if (("").equals(responseText) || responseText == null) {
								log.info("?????????2");
							}
							JSONObject jsonObject2 = JSONObject.parseObject(responseText);
							String statusCode = jsonObject2.getString("statusCode");
							String result2 = jsonObject2.getString("result");
							if (("").equals(statusCode) || statusCode == null && ("").equals(result2) || result2 == null) {
								log.info("????????????3");
							}
							if ("200".equals(statusCode) && "true".equals(result2)) {
								List<Map<String,Object>> list2= (List<Map<String, Object>>) jsonObject2.get("data");
								if(list2.size()<0){
									log.info("???????????????4");
								}
								/*??????????????????
								 * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"??????11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
								 */
								for(Map<String,Object> aa:list2){
									if(aa.get("deal_msg")=="success"){
										log.info("????????????--?????????????????????????????????--??????????????????");
										Teacher teacher = new Teacher();
										teacher.setEmpCode((String) aa.get("emp_code"));
										teacher.setIsSendCanteen(1);
										teacherService.updateTeacherSendCanteen(teacher);
									}else{
										log.info("????????????"+aa.get("deal_msg"));
									}
								}
							} else {
								log.info("????????????--???????????????????????????????????????--?????????????????? ???????????? {}", httpRequestResult);
							}
						}else {
							log.error("????????????--???????????????????????????????????????, ???????????? {}", httpRequestResult);
						}
					log.info("????????????--??????????????????????????????{}"," ????????????: {}----"+httpRequestResult);
					} catch (IOException e) {
						log.info("????????????--??????????????????????????????{}??? ???????????????????????????{}"+ e);
					}
				} else {
					log.error("?????????????????????????????????????????????????????????????????????");
				}
					/*//???????????? 11???15
					List<List<DetailVo>> postData = new ArrayList<>();
					postData.add(list);
					// ???????????????????????????????????? ?????????????????????????????? ????????????
					httpService.addEmploye(null, address + canteen, null, 2, postData, 0, null);
				// ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                // ??????????????????????????????*/
                if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
                    // ??????????????????????????????
                    libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
                } else {
                    log.error("??????????????????????????????????????????????????????????????????????????????");
                }


				// ???????????????????????????????????????
//				if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//					ArtemisConfig artemisConfig = new ArtemisConfig();
//					artemisConfig.setHost(hikvisionHost);
//					artemisConfig.setAppKey(hikvisionAppKey);
//					artemisConfig.setAppSecret(hikvisionAppSecret);
//					// 0????????? 1?????????
//					httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
//				} else {
//					log.error("???????????????????????????????????????????????????????????????????????????");
//				}
            }
		}
		return result;
	}

	/**
	 * ??????????????????
	 * @param teacherVo
	 * @return SUCCESS????????????  DATA_NO_FIND???????????????
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Object add(@CurrentUser UserInfo user, TeacherVo teacherVo) {
		String result = "SUCCESS";

		String mobile = teacherVo.getMobile();
		String name = teacherVo.getName();
		String alias = teacherVo.getAlias();
		Teacher teacher = teacherService.findTeacherById(teacherVo.getId());

		/**?????????????????????????????????????????????????????????*/
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
		/**???????????????????????????????????????*/
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

		/**?????????????????????????????????????????????*/
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
	 * ??????????????????
	 * @param userInfo ????????????
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
			result.put("message", "Id=" + errorIds.toString() + "?????????????????????????????????????????????????????????");
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
		String value = "??????";
		if(sex!=null) {
			if("1".equals(sex)) {
				value="???";
			} else if("2".equals(sex)) {
				value="???";
			}
		}
		return value;
	}
}
