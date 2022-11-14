package platform.education.rest.gc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalcode.model.KnowledgeNode;
import platform.education.generalcode.service.JcGcCacheService;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.vo.KnowledgeTreeMessVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.gc.service.GeneralCodeRestService;
import platform.education.rest.gc.service.vo.DesktopAppInfo;
import platform.education.rest.gc.service.vo.SyllabusSection;
import platform.education.rest.jw.service.vo.GeneralCode;
import platform.education.rest.jw.service.vo.GeneralCodeInfo;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;

public class GeneralCodeRestServiceImpl implements GeneralCodeRestService {
	
	@Autowired
	@Qualifier("jcGcCacheService")
	private JcGcCacheService jcGcCacheService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;
	
	@Override
	public Object getGeneralCodeList(String codes, String appKey,String version) {
		if(codes == null || "".equals(codes)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("codes参数不能为空");
			info.setMsg("codes为必填参数");
			info.setParam("codes");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<Map<String, Object>> itemMaps = null;
		GeneralCodeInfo gci = null;
		GeneralCode gc = null;
		List<GeneralCode>  gcList = new ArrayList<GeneralCode>();
		List<GeneralCodeInfo> gciList = new ArrayList<GeneralCodeInfo>();
		String[] codeArray = codes.split(",");
		
		if(version != null){
			if(SysContants.JC_CODE_VERSION.equals(version)){
				return new ResponseVo<List<GeneralCodeInfo>>("0",gciList);
			}else{
				if(codeArray != null && codeArray.length > 0){
					for(String tableCode : codeArray){
						gci = new GeneralCodeInfo();
						gcList = new ArrayList<GeneralCode>();
						itemMaps = this.jcGcCacheService.findByTableCode(tableCode, true);
						if(itemMaps != null && itemMaps.size() > 0){
							for(Map<String, Object> map : itemMaps){
								gc = new GeneralCode();
								gc.setName(map.get("name") != null ? map.get("name").toString() : "");
								gc.setValue(map.get("value") != null ? map.get("value").toString() : "");
								gcList.add(gc);
							}
							gci.setGeneralCode(gcList);
						}
						gci.setCode(tableCode);
						gci.setVersion(SysContants.JC_CODE_VERSION);
						gciList.add(gci);
					}
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("code参数非法");
					info.setMsg("code参数非法");
					info.setParam("code");
					return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
				}
			}
		}else{
			if(codeArray != null && codeArray.length > 0){
				for(String tableCode : codeArray){
					gci = new GeneralCodeInfo();
					gcList = new ArrayList<GeneralCode>();
					itemMaps = this.jcGcCacheService.findByTableCode(tableCode, true);
					if(itemMaps != null && itemMaps.size() > 0){
						for(Map<String, Object> map : itemMaps){
							gc = new GeneralCode();
							gc.setName(map.get("name") != null ? map.get("name").toString() : "");
							gc.setValue(map.get("value") != null ? map.get("value").toString() : "");
							gcList.add(gc);
						}
						gci.setGeneralCode(gcList);
					}
					gci.setCode(tableCode);
					gci.setVersion(SysContants.JC_CODE_VERSION);
					gciList.add(gci);
				}
			}
		}
		return new ResponseVo<List<GeneralCodeInfo>>("0",gciList);
	}

	@Override
	public Object getDesktopAppInfo(String appKey) {
		if(appKey == null || "".equals(appKey)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数不能为空");
			info.setMsg("appKey为必填参数");
			info.setParam("appKey");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		//判断APPKEY是否存在
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
		List<DesktopAppInfo> infoList = new ArrayList<DesktopAppInfo>();
		
		String dataList = SysContants.JC_DESKTOPAPPINFO_INFO;
		
		if(dataList != null && !"".equals(dataList)){
			JSONArray data = JSONArray.fromObject(dataList);
			if(data != null){
				JSONArray dataInfoAll = JSONArray.fromObject(data);
				if(dataInfoAll != null && !"".equals(dataInfoAll)){
					for(int i = 0;i < dataInfoAll.size();i++){
						JSONObject dataInfo = dataInfoAll.getJSONObject(i);
						DesktopAppInfo desktopAppInfo = JSON.parseObject(dataInfo+"", DesktopAppInfo.class);
						infoList.add(desktopAppInfo);
					}
				}
			}
		}
		return new ResponseVo<List<DesktopAppInfo>>("0",infoList);
	}

	@Override
	public Object getSyllabusSection(String appKey) {
		if(appKey == null || "".equals(appKey)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数不能为空");
			info.setMsg("appKey为必填参数");
			info.setParam("appKey");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		//判断APPKEY是否存在
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
		SyllabusSection syllabusSection = new SyllabusSection();
		syllabusSection.setMorning(Integer.parseInt(SysContants.MAX_LESSON_COUNT_MORING));
		syllabusSection.setAfternoon(Integer.parseInt(SysContants.MAX_LESSON_COUNT_AFTERNOON));
		syllabusSection.setEvening(Integer.parseInt(SysContants.MAX_LESSON_COUNT_EVENING));
		return new ResponseVo<SyllabusSection>("0",syllabusSection);
	}

	@Override
	public Object getKnolewgeNodeTree(String appKey, String subjectCode, String stageCode, Boolean isGetAllSubject) {
		if(appKey == null || "".equals(appKey)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数不能为空");
			info.setMsg("appKey为必填参数");
			info.setParam("appKey");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		//判断APPKEY是否存在
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if(isGetAllSubject == null){
			isGetAllSubject = false;
		}
		if(subjectCode == null || "".equals(subjectCode)){
			isGetAllSubject = true;
		}
		if(stageCode == null || "".equals(stageCode)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("stageCode参数不能为空");
			info.setMsg("stageCode为必填参数");
			info.setParam("stageCode");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		List<List<KnowledgeTreeMessVo>> list = knowledgeNodeService.findKnolewgeNodeToTree(subjectCode, stageCode, isGetAllSubject);
		return new ResponseVo<List<List<KnowledgeTreeMessVo>>>("0",list);
	}
	
	@Override
	public Object getKnolewgeNodeList(String appKey, String subjectCode, String stageCode) {
		if(appKey == null || "".equals(appKey)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数不能为空");
			info.setMsg("appKey为必填参数");
			info.setParam("appKey");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		//判断APPKEY是否存在
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if(subjectCode == null || "".equals(subjectCode)){
			subjectCode = null;
		}
		if(stageCode == null || "".equals(stageCode)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("stageCode参数不能为空");
			info.setMsg("stageCode为必填参数");
			info.setParam("stageCode");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		List<List<KnowledgeNode>> list = knowledgeNodeService.findKnowledgeNodeBySubjectAndStage(subjectCode, stageCode);
		return new ResponseVo<List<List<KnowledgeNode>>>("0",list);
	}
	
}
