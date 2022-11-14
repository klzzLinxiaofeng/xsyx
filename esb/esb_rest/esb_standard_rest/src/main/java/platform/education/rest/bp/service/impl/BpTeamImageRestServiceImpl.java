package platform.education.rest.bp.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.BpBwInfo;
import platform.education.clazz.service.BpBwInfoService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.vo.BpBwInfoVo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.rest.bp.service.BpTeamImageRestService;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

public class BpTeamImageRestServiceImpl implements BpTeamImageRestService {
	
	@Autowired
	@Qualifier("bpBwInfoService")
	private BpBwInfoService bpBwInfoService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Override
	public Object createTeamImage(Integer teamId, String signature, String backgroundFile, String backgroundTemplate, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}		
		/*	if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}	*/
			
			BpBwInfo bpBwInfo = bpBwInfoService.findBpBwInfoByTeamId(teamId);
			if(bpBwInfo != null){
				bpBwInfo.setSignature(signature);
				bpBwInfo.setBackgroundFile(backgroundFile);
				bpBwInfo.setBackgroundTemplate(backgroundTemplate);
				bpBwInfo = this.bpBwInfoService.modify(bpBwInfo);
			}else{
				bpBwInfo = new BpBwInfo();
				bpBwInfo.setTeamId(teamId);
				bpBwInfo.setSignature(signature);
				bpBwInfo.setBackgroundFile(backgroundFile);
				bpBwInfo.setBackgroundTemplate(backgroundTemplate);
				bpBwInfo = this.bpBwInfoService.add(bpBwInfo);
			}	
			BpBwInfoVo vo = new BpBwInfoVo();
			PropertyUtils.copyProperties(vo,bpBwInfo);
			Team team = this.teamService.findTeamById(teamId);
			vo.setTeamName(team.getName());
			vo.setBackgroundUrl(fileService.relativePath2HttpUrlByUUID(vo.getBackgroundFile()));
			return new ResponseVo<BpBwInfoVo>("0", vo);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}

	@Override
	public Object getTeamImage(Integer teamId, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
			 	return checkAppKeyResult;
			}
			/*if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);	
			}*/
			BpBwInfo bpBwInfo = bpBwInfoService.findBpBwInfoByTeamId(teamId);
			BpBwInfoVo vo = new BpBwInfoVo();
			if(bpBwInfo != null && !"".equals(bpBwInfo)){
				PropertyUtils.copyProperties(vo,bpBwInfo);
				Team team = this.teamService.findTeamById(teamId);
				vo.setTeamName(team.getName());
				vo.setBackgroundUrl(fileService.relativePath2HttpUrlByUUID(vo.getBackgroundFile()));
			}
			return new ResponseVo<BpBwInfoVo>("0", vo);
		}catch (Exception e) {
		 	e.printStackTrace();
		 	ResponseInfo info = new ResponseInfo();
		 	info.setDetail("接口异常");
		 	info.setMsg("接口异常");
		 	return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		 }
	}
	
	

}
