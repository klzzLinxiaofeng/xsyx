package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BpBwExamInfo;
import platform.education.clazz.service.BpBwExamInfoService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.vo.BpBwExamInfoCondition;
import platform.education.clazz.vo.BpBwExamInfoVo;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalcode.model.Subject;
import platform.education.generalcode.service.SubjectService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpExamRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

public class BpExamRestServiceImpl implements BpExamRestService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("bpBwExamInfoService")
	private BpBwExamInfoService bpBwExamInfoService;
	
	@Autowired
	@Qualifier("jcSubjectService")
	protected SubjectService jcSubjectService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
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

	@Resource(name = "bp_exam_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findExamInfo(Integer teamId,Long currentTime,Integer searchId,Integer searchType, String pageSize,
			String appKey,String signage) {
		Order order = new Order();
		Page page = new Page();
		try {
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			BpBwExamInfoCondition condition = new BpBwExamInfoCondition();
			condition.setTeamId(teamId);
			condition.setIsDeleted(false);
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			if(searchType != null && !"".equals(searchType)){
				if(searchType.equals(0)){
					page.setPageSize(Integer.parseInt(pageSize));// pageSize
				}else if(searchType.equals(1)){
					if(searchId == null || "".equals(searchId)){
						return ResponseUtil.paramerIsNull("searchId:"+searchId);
					}
					condition.setSearchId(searchId);
					condition.setSearchType(searchType);
				}else if(searchType.equals(2)){
					if(searchId == null || "".equals(searchId)){
						return ResponseUtil.paramerIsNull("searchId:"+searchId);
					}
					condition.setSearchId(searchId);
					condition.setSearchType(searchType);
					page.setPageSize(Integer.parseInt(pageSize));// pageSize
				}
			}
			List<BpBwExamInfoVo> newList = new ArrayList<BpBwExamInfoVo>();
			List<BpBwExamInfo> list = this.bpBwExamInfoService.findBwExamInfoByCondition(condition, page, order);
			Date date = new Date(currentTime);
			BpBwExamInfoVo vo = null;
			if(searchType != null && !"".equals(searchType)){
				for(BpBwExamInfo info:list){
						vo = new BpBwExamInfoVo();
						PropertyUtils.copyProperties(vo,info);
						Subject subject = this.jcSubjectService.findByCode(Integer.parseInt(info.getSubjectCode()));
						vo.setSubjectName(subject.getName());
						newList.add(vo);
				}
			}else{
				for(BpBwExamInfo info:list){
					if(info.getStartTime().before(date) && info.getFinishTime().after(date)){
						vo = new BpBwExamInfoVo();
						PropertyUtils.copyProperties(vo,info);
						Subject subject = this.jcSubjectService.findByCode(Integer.parseInt(info.getSubjectCode()));
						vo.setSubjectName(subject.getName());
						newList.add(vo);
						break;
					}
				}
				if(newList.size()==0){
					vo = new BpBwExamInfoVo();
					BpBwExamInfo first = list.get(0);
					PropertyUtils.copyProperties(vo,first);
					Subject subject = this.jcSubjectService.findByCode(Integer.parseInt(first.getSubjectCode()));
					vo.setSubjectName(subject.getName());
					newList.add(vo);
				}
				return new ResponseVo<List<BpBwExamInfoVo>>("0", newList);
			}
			return new ResponseVo<List<BpBwExamInfoVo>>("0", newList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	@Override
	public Object findExamInfo(Integer teamId,Integer searchId,Integer searchType, String pageSize,
			String appKey,String signage) {
		Order order = new Order();
		Page page = new Page();
		try {
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			BpBwExamInfoCondition condition = new BpBwExamInfoCondition();
			condition.setTeamId(teamId);
			condition.setIsDeleted(false);
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			if(searchType != null && !"".equals(searchType)){
				if(searchType.equals(0)){
					page.setPageSize(Integer.parseInt(pageSize));// pageSize
				}else if(searchType.equals(1)){
					if(searchId == null || "".equals(searchId)){
						return ResponseUtil.paramerIsNull("searchId:"+searchId);
					}
					condition.setSearchId(searchId);
					condition.setSearchType(searchType);
				}else if(searchType.equals(2)){
					if(searchId == null || "".equals(searchId)){
						return ResponseUtil.paramerIsNull("searchId:"+searchId);
					}
					condition.setSearchId(searchId);
					condition.setSearchType(searchType);
					page.setPageSize(Integer.parseInt(pageSize));// pageSize
				}
			}
			List<BpBwExamInfoVo> newList = new ArrayList<BpBwExamInfoVo>();
			List<BpBwExamInfo> list = this.bpBwExamInfoService.findBwExamInfoByCondition(condition, page, order);
			if(list != null && list.size() > 0){
				Date date = new Date();
				BpBwExamInfoVo vo = null;
				if(searchType != null && !"".equals(searchType)){
					for(BpBwExamInfo info:list){
							vo = new BpBwExamInfoVo();
							PropertyUtils.copyProperties(vo,info);
							Subject subject = this.jcSubjectService.findByCode(Integer.parseInt(info.getSubjectCode()));
							vo.setSubjectName(subject.getName());
							newList.add(vo);
					}
				}else{
					for(BpBwExamInfo info:list){
						if(info.getStartTime().before(date) && info.getFinishTime().after(date)){
							vo = new BpBwExamInfoVo();
							PropertyUtils.copyProperties(vo,info);
							Subject subject = this.jcSubjectService.findByCode(Integer.parseInt(info.getSubjectCode()));
							vo.setSubjectName(subject.getName());
							newList.add(vo);
							break;
						}
					}
					if(newList.size()==0){
						vo = new BpBwExamInfoVo();
						BpBwExamInfo first = list.get(0);
						PropertyUtils.copyProperties(vo,first);
						Subject subject = this.jcSubjectService.findByCode(Integer.parseInt(first.getSubjectCode()));
						vo.setSubjectName(subject.getName());
						newList.add(vo);
					}
			}		
				return new ResponseVo<List<BpBwExamInfoVo>>("0", newList);
			}
			return new ResponseVo<List<BpBwExamInfoVo>>("0", newList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	

	@Override
	public Object createExam(String appKey,String signage,Integer teamId, String teacherName,
			String subjectCode, String room, Long startTime, Long finishTime,
			Integer schoolId, String content) {
		try{
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(startTime == null || "".equals(startTime)){
				return ResponseUtil.paramerIsNull("startTime:"+startTime);
			}
			if(finishTime == null || "".equals(finishTime)){
				return ResponseUtil.paramerIsNull("finishTime:"+finishTime);
			}
			BpBwExamInfo exam = new BpBwExamInfo();
			exam.setContent(content);
			exam.setExamRoomName(room);
			exam.setTeamId(teamId);
			exam.setStartTime(new Date(startTime));
			exam.setFinishTime(new Date(finishTime));
			exam.setSubjectCode(subjectCode);
			exam.setTeacherName(teacherName);
			exam.setSchoolId(schoolId);
			exam = this.bpBwExamInfoService.add(exam);
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(teamId);
			//推送内容
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("startTime", exam.getStartTime().getTime());	
			map.put("endTime", exam.getFinishTime().getTime());
			map.put("modeType", BpCommonConstants.PUSH_EXAM_INFO);
			map.put("priority", BpCommonConstants.PRIORITY_2);
			map.put("immediately", 0);
//			IMPushUtil.push(teamIds, DataAction.D$add, exam.getId(), BpCommonConstants.PUSH_EXAM_INFO, map.toString(),
//				 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, exam.getId(),BpCommonConstants.PUSH_EXAM_INFO, map.toString(), bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,exam.getId(), BpCommonConstants.PUSH_EXAM_INFO, map.toString(), bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Integer>("0", exam.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}

	@Override
	public Object delete(Integer examId, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			BpBwExamInfo exam = this.bpBwExamInfoService.findBwExamInfoById(examId);
			if ( exam == null ) {
				return ResponseUtil.dataNotExist("exam:"+exam);
			}
			this.bpBwExamInfoService.remove(exam);
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(exam.getTeamId());
//			IMPushUtil.push(teamIds, DataAction.D$delete, exam.getId(), BpCommonConstants.PUSH_EXAM_INFO, null,
//				 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$delete, exam.getId(),BpCommonConstants.PUSH_EXAM_INFO,null, bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$delete,exam.getId(), BpCommonConstants.PUSH_EXAM_INFO, null, bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Boolean>("0", true);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}
	
	


	
}
