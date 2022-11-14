package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.Praise;
import platform.education.clazz.model.PraiseMedal;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.PraiseCondition;
import platform.education.clazz.vo.PraiseMedalCondition;
import platform.education.clazz.vo.PraiseVo;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpPraiseRestService;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.DateUtil;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.BpPraiseClientVo;
import platform.education.rest.bp.service.vo.PraiseClientVo;
import platform.education.rest.bp.service.vo.ResponseOrder;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BpPraiseRestServiceImpl implements BpPraiseRestService {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	//private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("praiseService")
	private PraiseService praiseService;
	
	@Autowired
	@Qualifier("praiseMedalService")
	private PraiseMedalService praiseMedalService;
	
	@Autowired
	@Qualifier("praiseUserService")
	private PraiseUserService praiseUserService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("personService")
	protected PersonService personService;
	
	@Autowired
	@Qualifier("studentService")
	protected StudentService studentService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	@Resource(name = "bbx_praise_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findPraise(Integer teamId, String searchDate,Integer searchType, String pageSize,String appKey,String signage) {
		
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			/*if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}	*/
			if(searchType == null || "".equals(searchType)){
				return ResponseUtil.paramerIsNull("searchType:"+searchType);
			}
			List<BpPraiseClientVo> bpPraiseClientVoList = new ArrayList<BpPraiseClientVo>();
			Order order = new Order();
			Page page = new Page();
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			PraiseCondition praiseCondition = new PraiseCondition();
			if(searchType.equals(0)){
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}else if(searchType.equals(1)){
				praiseCondition.setSearchDate(searchDate);
				praiseCondition.setSearchType(searchType);
			}else if(searchType.equals(2)){
				praiseCondition.setSearchDate(searchDate);
				praiseCondition.setSearchType(searchType);
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}
			// 如果指定班级则按班级查询.
			if (teamId != null) {
				praiseCondition.setTeamIds(teamId + "");
			}
			List<String> createDateList = this.praiseService.findPraiseCreateDateByCondition(praiseCondition, page, order);
			if(createDateList != null && createDateList.size() > 0){
				order = new Order();
				order.setProperty("id");
				order.setAscending(false);
				for(String createDate: createDateList){
					BpPraiseClientVo bpPraiseClientVo = new BpPraiseClientVo();					
					List<PraiseClientVo> praiseClientList = new ArrayList<PraiseClientVo>();					
					// 组拼手机端需要的信息
					praiseCondition.setCreateDateStr(createDate);
					List<PraiseVo> praiseVos = praiseService.findPraiseVoByCondition(praiseCondition, null, order);
					if (praiseVos != null && praiseVos.size() > 0) {
						//默认头像
						String imgUrl = ImgUtil.getImgUrl(null);
						PraiseClientVo praiseClientVo;
						for (PraiseVo praiseVo : praiseVos) {
							Integer personId = this.studentService.findStudentByUserId(Integer.parseInt(praiseVo.getReceiverId())).getPersonId();
							imgUrl = ImgUtil.getStudentIconUrl(personId, personService);
							praiseClientVo = new PraiseClientVo();
							PropertyUtils.copyProperties(praiseClientVo, praiseVo);
							List<Map<String, Object>> students = praiseClientVo.getStudents();
							if ( students != null && students.size() > 0 ) {
								for(Map<String, Object> map :students){
									String url = map.get("iconUrl").toString();
									if("".equals(url)){
										map.put("iconUrl", imgUrl);
									}
								}
							}
							praiseClientVo.setPostTime(praiseVo.getCreateDate());
							praiseClientVo.setModifyTime(DateUtil.dateToString(praiseVo.getModifyDate()));
							praiseClientList.add(praiseClientVo);
						}
					}
					bpPraiseClientVo.setCreateDate(SDF.parse(createDate).getTime());
					bpPraiseClientVo.setPraiseClientList(praiseClientList);
					bpPraiseClientVoList.add(bpPraiseClientVo);
				}
			}
			return new ResponseOrder<List<BpPraiseClientVo>>("0",bpPraiseClientVoList, page.getTotalRows());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	
	@Override
	public Object findPraiseMedalsList(String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			
			List<Map<String, Object>> praiseMedalMaps = null;
			PraiseMedalCondition praiseMedalCondition = new PraiseMedalCondition();
			// praiseMedalCondition.setSchoolId(schoolId);// 当前学校
			List<PraiseMedal> praiseMedals = praiseMedalService.findPraiseMedalByCondition(praiseMedalCondition);
			if (praiseMedals != null && praiseMedals.size() > 0) {
				praiseMedalMaps = new ArrayList<Map<String, Object>>();
				Map<String, Object> map;
				for (PraiseMedal praiseMedal : praiseMedals) {
					map = new HashMap<String, Object>();
					map.put("id", praiseMedal.getId());
					map.put("rank", praiseMedal.getRank());
					// 勋章图片处理
					String fileUUID = praiseMedal.getFileId();
					if (fileUUID != null && !fileUUID.trim().equals("")) {
						map.put("imgUrl", fileService.relativePath2HttpUrlByUUID(fileUUID));
					}
					praiseMedalMaps.add(map);
				}
			}
			return new ResponseVo<List<Map<String, Object>>>("0", praiseMedalMaps);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}


	@Override
	public Object createPraise(Integer schoolId, Integer teamId, Integer medalId, String content, 
			Integer posterId,String students, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			Praise praise = new Praise();
			praise.setSchoolId(schoolId);
			praise.setTeamId(teamId);
			praise.setMedalId(medalId);
			praise.setContent(content);
			praise.setPosterId(posterId);
			// 设置表扬学生receiverId, receiverName;
			String receiverId = "";
			String receiverName = "";
			StudentCondition studentCondition;
			if (students != null && !students.trim().equals("")) {
				Integer[] studentIds = (Integer[]) JSONArray.toArray(JSONArray.fromObject(students), Integer.class);
				if (studentIds != null && studentIds.length > 0) {
					for (Integer id : studentIds) {
						receiverId += id + ",";
						studentCondition = new StudentCondition();
						studentCondition.setUserId(id);
						List<Student> list = studentService.findStudentByCondition(studentCondition, null,null);
						if (list != null && list.size() > 0) {
							receiverName += list.get(0).getName() + ",";
						} else {
							//log.info("发布表扬异常...");
							ResponseInfo info = new ResponseInfo();
							info.setDetail("发布表扬异常...");
							info.setMsg("找不到userId为" + id + "的学生.");
							info.setParam("students");
							return new ResponseError("060001", info);
						}
					}
				} else {
					//log.info("发布表扬异常...");
					ResponseInfo info = new ResponseInfo();
					info.setDetail("发布表扬异常...");
					info.setMsg("请输入要表扬的学生userId.");
					info.setParam("students");
					return new ResponseError("060001", info);
				}
			}
			receiverId = receiverId.substring(0, receiverId.length() - 1);
			receiverName = receiverName.substring(0, receiverName.length() - 1);
			praise.setReceiverId(receiverId);
			praise.setReceiverName(receiverName);
			praise = this.praiseService.saveOrUpdate(praise);
			//ResponseAdd add = new ResponseAdd(praise.getId(), DateUtil.dateToString(praise.getCreateDate()));
			
			//推送开始=====
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(praise.getTeamId());
			//推送
//			IMPushUtil.push(teamIds, DataAction.D$add, praise.getId(), "123", receiverName+"获得表扬啦！", 
//					 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, praise.getId(),"123",receiverName+"获得表扬啦！", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,praise.getId(),"123", receiverName+"获得表扬啦！", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			return new ResponseVo<Integer>("0", praise.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object deletePraise(Integer praiseId, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			
			Praise praise = this.praiseService.findPraiseById(praiseId);
			if(praise == null ){
				return ResponseUtil.paramerIsNull("praiseId:"+praiseId);
			}	
			this.praiseUserService.removeByPraiseId(praise.getId());
			this.praiseService.remove(praise);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

}
