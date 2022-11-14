package platform.education.rest.ps.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjAptsTask;
import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.service.PjAptsTaskService;
import platform.education.generalTeachingAffair.service.PjAptsTaskUserService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.utils.DateUtil;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.ps.service.AptsTaskTeacherRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;

public class AptsTaskTeacherRestServiceImpl implements AptsTaskTeacherRestService{
@Resource
private AppEditionService appEditionService;

@Resource 
private PjAptsTaskService  pjAptsTaskService;
@Resource
private  SchoolTermService schoolTermService;
@Resource
private PjAptsTaskUserService  pjAptsTaskUserService;
@Resource 
private ProfileService profileService;
	@Override
	public Object list(String appKey, Integer userId,Integer type, Integer schoolId,Integer pageSize,
			Integer pageNumber) throws Exception {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Page page =new Page();
		page.setCurrentPage(pageNumber);
		page.setEnableGetTolaRows(false);
		page.setPageSize(pageSize);
		Order order=new Order();
		order.setProperty("start_date");
		order.setAscending(false);
		Date date=new Date();
		SchoolTerm st=schoolTermService.findSchoolYearByToday(schoolId, date);
		List<Map<String, Object>>pulist=pjAptsTaskService.findAptsTaskUserMapListByUserId(st.getCode(), type, userId, page, order);
		for(Map<String, Object> map :pulist){
			if(map.get("subjectName")==null){
				map.put("subjectName", "");
			}
		}
        return new ResponseVo<Object>("0", pulist);
	}

	@Override
	public Object detail(String appKey, Integer assessmentId) throws Exception {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String,Object> map=pjAptsTaskService.findAptsTaskUserMapByAssessmentId(assessmentId);
		if(map.size()==0){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("assessmentId不存在,请确认");
			info.setMsg("不存在该assessmentId");
			info.setParam("assessmentId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Integer[] userIds={(Integer) map.get("userId")};
		if(userIds.length!=0){
			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
			if(imgMap.get(map.get("userId"))!=null) {
				map.put("icon", imgMap.get(map.get("userId")));
			} else {
				/**如果不存在则使用默认头像*/
				map.put("icon", SysContants.APP_DEFAULT_USERICON);
			}
			map.remove("userId");
		}
		return new ResponseVo<Object>("0",map);
	}

}
