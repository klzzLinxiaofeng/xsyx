package platform.education.rest.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalcode.model.Grade;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.model.TextbookVersion;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.service.GradeService;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.service.TextbookVolumnService;
import platform.education.generalcode.vo.GradeCondition;
import platform.education.generalcode.vo.ResTextbookCatalogCondition;
import platform.education.generalcode.vo.ResTextbookCondition;
import platform.education.generalcode.vo.StageCondition;
import platform.education.generalcode.vo.TextbookVersionCondition;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.education.rest.basic.service.TextbookRestService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AclService;
import platform.education.user.service.AppEditionService;

public class TextbookRestServiceImpl implements TextbookRestService{
	
	@Autowired
	@Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;
	
	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;

    @Autowired
    @Qualifier("aclService")
    private AclService aclService;
    
    @Autowired
    @Qualifier("jcTextbookVersionService")
    private TextbookVersionService jcTextbookVersionService;
    
    @Autowired
    @Qualifier("jcTextbookVolumnService")
    private TextbookVolumnService jcTextbookVolumnService;
    
    @Autowired
    @Qualifier("jcStageService")
    private StageService stageService;
    
    @Autowired
    @Qualifier("jcGradeService")
    private GradeService gradeService;
    
    @Autowired
    @Qualifier("gradeService")
    private platform.education.generalTeachingAffair.service.GradeService jwGradeService;
    
    @Autowired
    @Qualifier("teamStudentService")
    private TeamStudentService teamStudentService;
    
    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
    
	@Override
	public Object findTextbookList(Integer schoolId, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(schoolId==null) {
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);//找不到数据
			}
			ResTextbookCondition resTextbookCondition = new ResTextbookCondition();
			resTextbookCondition.setResourceLibId(schoolId);
			resTextbookCondition.setIsDelete(false);
			
			List<ResTextbook> resTextbookList = resTextbookService.findResTextbookByCondition(resTextbookCondition);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, String> gradeMap = this.getGradeMap();
			Map<String, String> stageMap = this.getStageMap();
			Map<String, String> subjectMap = this.getSubjectMap(schoolId);
			Map<String, String> versionMap = this.getVersionMap();
			Map<String, String> volumnMap = this.getVolumnMap();
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", 0);
			map1.put("name", "");
			map1.put("publishID", 0);
			map1.put("stageCode", "");
			map1.put("subjectCode", "-1");
			map1.put("gradeCode", "");
			map1.put("volumn", "");
			map1.put("version", "");
			map1.put("image", "");
			map1.put("stageName", "");
			map1.put("subjectName", "全部科目");
		    map1.put("gradeName", "");
			map1.put("volumnName", "");
			map1.put("publishName", "");
			list.add(map1);
			for (ResTextbook resTextbook : resTextbookList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", resTextbook.getId());
				map.put("name", resTextbook.getName());
				map.put("publishID", resTextbook.getPublisherId());
				map.put("stageCode", resTextbook.getStageCode());
				map.put("subjectCode", resTextbook.getSubjectCode());
				map.put("gradeCode", resTextbook.getGradeCode());
				map.put("volumn", resTextbook.getVolumn());
				map.put("version", resTextbook.getVersion());
				map.put("image", resTextbook.getImage());
				
				map.put("stageName", stageMap.get(resTextbook.getStageCode()));
				map.put("subjectName", subjectMap.get(resTextbook.getSubjectCode()));
				if(gradeMap.get(resTextbook.getGradeCode())==null) {
					map.put("gradeName", "");
				} else {
					map.put("gradeName", gradeMap.get(resTextbook.getGradeCode()));
				}
				map.put("volumnName", volumnMap.get(resTextbook.getVolumn()));
				map.put("publishName", versionMap.get(resTextbook.getVersion()));
				list.add(map);
			}
			
			if(resTextbookList.size()==0) {
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//找不到数据
			}
			
			return new ResponseVo<Object>("0",list);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取学校书籍列表异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object findSchoolTreeList(Integer schoolId, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(schoolId==null) {
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);//找不到数据
			}
			
			List<ResTextbook> resTextbookList = resTextbookService.findResTextbookByLibId(schoolId);
			
			Map<String, String> stageMap = this.getStageMap();
			Map<String, String> gradeMap = this.getGradeMap();
			Map<String, String> subjectMap = this.getSubjectMap(schoolId);
			Map<String, String> versionMap = this.getVersionMap();
			Map<String, String> volumnMap = this.getVolumnMap();
			
			TreeSet<String> subjectSet = new TreeSet<String>();
			subjectSet.add("-1");
			for (ResTextbook textbook : resTextbookList) {
				subjectSet.add(textbook.getSubjectCode());
			}
			
			Iterator<String> its = subjectSet.iterator();
			
			/**科目的集合*/
			List<Map<String, Object>> sublectList = new ArrayList<Map<String, Object>>();
			
			while(its.hasNext()) {
				String subjectCode = its.next();
				
				/**科目下版本的集合*/
				List<Map<String, Object>> versionList = new ArrayList<Map<String, Object>>();
				
				TreeSet<String> versionCodes=new TreeSet<>();
				
				for (ResTextbook textbook : resTextbookList) {
					if(textbook.getSubjectCode().equals(subjectCode)) {
						
						versionCodes.add(textbook.getVersion());
						
					}
				}
				
				Iterator<String> versionCodesIts=versionCodes.iterator();
				while(versionCodesIts.hasNext()) {
					String versionCode=versionCodesIts.next();
					
					/**版本信息*/
					Map<String, Object> resVersionmap = new HashMap<String, Object>();
					resVersionmap.put("code", versionCode);
					resVersionmap.put("name", versionMap.get(versionCode));
					versionList.add(resVersionmap);
					
				}
				
				
				/**信息信息*/
				Map<String, Object> resSubjectMap = new HashMap<String, Object>();
				resSubjectMap.put("code", subjectCode);
				String subjectName = subjectMap.get(subjectCode);
				if(subjectName==null || "".equals(subjectName)) {
					subjectName = "未知科目";
				}
				if(subjectCode.equals("-1")){
					subjectName = "全部科目";
				}
				resSubjectMap.put("name", subjectName);
				resSubjectMap.put("versionList", versionList);
				/**把相关版本和科目关联起来*/
				sublectList.add(resSubjectMap);
			}
			
			for (Map<String, Object> resSubjectMap : sublectList) {
				String subjectCode = (String) resSubjectMap.get("code");
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> versionList = (List<Map<String, Object>>) resSubjectMap.get("versionList");
				for (Map<String, Object> resVersionmap : versionList) {
					String version = (String) resVersionmap.get("code");
					
					/**版本下年级册次的集合*/
					List<Map<String, Object>> gradeVolumnList = new ArrayList<Map<String, Object>>();
					for (ResTextbook textbook : resTextbookList) {
						/**如果是该科目和版本下的书籍*/
						if(textbook.getSubjectCode().equals(subjectCode) && textbook.getVersion().equals(version)) {
							/**年级册次信息*/
							Map<String, Object> gradeVolumnMap = new HashMap<String, Object>();
							gradeVolumnMap.put("code", textbook.getId());
							/**年级册次的name拼装*/
							String name = "";
							if(gradeMap.get(textbook.getGradeCode())!=null) {
								name+=gradeMap.get(textbook.getGradeCode());
							}
							name+=volumnMap.get(textbook.getVolumn());
							gradeVolumnMap.put("name", name);
							/**添加到map*/
							gradeVolumnList.add(gradeVolumnMap);
							
							String stageName = stageMap.get(textbook.getStageCode());
							gradeVolumnMap.put("stageName", stageName);
							
						}
					}
					/**把年级册次信息和版本关联起来*/
					resVersionmap.put("sakujiList", gradeVolumnList);
				}
			}
			return new ResponseVo<Object>("0",sublectList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取学校书籍列表异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}

	@Override
	public Object findTextbookCatalogList(Integer textbookId, Integer parentId, Integer level, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(textbookId==null) {
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//找不到数据
			}
			ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
			resTextbookCatalogCondition.setTestBookId(textbookId);
			resTextbookCatalogCondition.setParentId(parentId);
			List<ResTextbookCatalog> resTextbookCatalogList = resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
			
			if(resTextbookCatalogList.size()==0) {
				return new ResponseVo<Object>("0",new ArrayList<Object>());
			}
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			
			for (ResTextbookCatalog resTextbookCatalog : resTextbookCatalogList) {
				Map<String, Object> map = getCatalogInfo(resTextbookCatalog);
				
				if(level!=null) {
					if(level-resTextbookCatalog.getLevel()>=0){
						returnList.add(map);
					}
				} else {
					returnList.add(map);
				}
			}
			
			return new ResponseVo<Object>("0",returnList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取学校书籍列表异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object findTextbookCatalogTree(Integer textbookId, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(textbookId==null) {
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//找不到数据
			}
			/**获取教材目录一级接口*/
			ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
			resTextbookCatalogCondition.setTestBookId(textbookId);
			resTextbookCatalogCondition.setLevel(1);
			List<ResTextbookCatalog> resTextbookCatalogList = resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
			
			if(resTextbookCatalogList.size()==0) {
				return new ResponseVo<Object>("0",new ArrayList<Object>());
			}
			
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			
			for (ResTextbookCatalog resTextbookCatalog : resTextbookCatalogList) {
				/**获取目录信息并放入map中*/
				Map<String, Object> map = getCatalogInfo(resTextbookCatalog);
				
				/**获取一级目录的子目录(二级目录)*/
				resTextbookCatalogCondition = new ResTextbookCatalogCondition();
				resTextbookCatalogCondition.setParentId(resTextbookCatalog.getId());
				List<ResTextbookCatalog> list = resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
				
				/**二级目录信息列表*/
				List<Map<String, Object>> info = new ArrayList<Map<String, Object>>();
				/**存在二级目录*/
				if(list.size()>0) {
					for (ResTextbookCatalog catalog : list) {
						Map<String, Object> temp = getCatalogInfo(catalog);
						info.add(temp);
					}
				} else {
					/**不存在二级目录，构建一个*/
					Map<String, Object> temp = getCatalogInfo(resTextbookCatalog);
					temp.put("name", "");
					info.add(temp);
				}
				
				/**二级目录依赖到一级目录*/
				map.put("children", info);
				returnList.add(map);
			}
			
			return new ResponseVo<Object>("0",returnList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取教材目录列表异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}
	
	
	@Override
	public Object hasXiaoBenCPermission(Integer userId, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			boolean result = aclService.hasPermission(userId, "YUN_JIAO_XUE_DAO_XUE_AN", 0, 1);
			Map<String, Boolean> map = new HashMap<String, Boolean>(1);
			map.put("hasPermission", result);
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取是否拥有备课组老师权限异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object findSubjectList(Integer schoolId, Integer userId, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(schoolId==null) {
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//找不到数据
			}
			ResTextbookCondition resTextbookCondition = new ResTextbookCondition();
			resTextbookCondition.setResourceLibId(schoolId);
			resTextbookCondition.setIsDelete(false);
			
			List<ResTextbook> resTextbookList = resTextbookService.findResTextbookByCondition(resTextbookCondition);
			
			if(resTextbookList.size()==0) {
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//找不到数据
			}
			
			TeamStudentCondition teamStudentByCondition = new TeamStudentCondition();
			teamStudentByCondition.setUserId(userId);
			teamStudentByCondition.setSchoolId(schoolId);
			List<TeamStudent> list = teamStudentService.findTeamStudentByCondition(teamStudentByCondition, null, null);
			String stage = null;
			if(list.size()>0) {
				platform.education.generalTeachingAffair.model.Grade grade = jwGradeService.findGradeById(list.get(0).getGradeId());
				if(grade!=null) {
					stage = grade.getStageCode();
				}
			}
			
			Map<String, String> subjectMap = this.getSubjectMap(schoolId);
			Map<String, Object> map = new HashMap<String, Object>();
			for (ResTextbook resTextbook : resTextbookList) {
				if(stage!=null) {
					if(stage.equals(resTextbook.getStageCode())) {
						map.put(resTextbook.getSubjectCode(), subjectMap.get(resTextbook.getSubjectCode()));
					}
				} else {
					map.put(resTextbook.getSubjectCode(), subjectMap.get(resTextbook.getSubjectCode()));
				}
			}
			
			List<Map<String, String>> subjectList = new ArrayList<Map<String, String>>();
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Entry<String, String>) it.next();
				Map<String, String> temp = new HashMap<String,String>();
				temp.put("sujectCode", entry.getKey());
				temp.put("sujectName", entry.getValue());
				subjectList.add(temp);
			}
			
			return new ResponseVo<Object>("0",subjectList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取学校科目列表异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}

	
	private Map<String, Object> getCatalogInfo(ResTextbookCatalog catalog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", catalog.getId());
		map.put("testBookId", catalog.getTestBookId());
		map.put("name", catalog.getName());
		map.put("level", catalog.getLevel());
		map.put("code", catalog.getCode());
		map.put("listOrder", catalog.getListOrder());
		return map;
	}
	
	
	private Map<String, String> getStageMap() {
		Map<String, String> stageMap = new HashMap<String, String>();
		List<Stage> stageList = stageService.findStageByCondition(new StageCondition(), null, null);
		for (Stage stage : stageList) {
			stageMap.put(stage.getCode(), stage.getName());
		}
		return stageMap;
	}
	
	private Map<String, String> getSubjectMap(Integer schoolId) {
		Map<String, String> subjectMap = new HashMap<String, String>();
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setSchoolId(schoolId);
		List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
		for (Subject subject : subjectList) {
			subjectMap.put(subject.getCode()+"", subject.getName());
		}
		return subjectMap;
	}
	
	private Map<String, String> getGradeMap() {
		Map<String, String> gradeMap = new HashMap<String, String>();
		List<Grade> stageList = gradeService.findGradeByCondition(new GradeCondition(), null, null);
		for (Grade stage : stageList) {
			gradeMap.put(stage.getCode(), stage.getName());
		}
		return gradeMap;
	}
	
	private Map<String, String> getVersionMap() {
		Map<String, String> stageMap = new HashMap<String, String>();
		List<TextbookVersion> stageList = jcTextbookVersionService.findTextbookVersionByCondition(new TextbookVersionCondition());
		for (TextbookVersion stage : stageList) {
			stageMap.put(stage.getId()+"", stage.getName());
		}
		return stageMap;
	}
	
	private Map<String, String> getVolumnMap() {
		Map<String, String> stageMap = new HashMap<String, String>();
		List<TextbookVolumn> stageList = jcTextbookVolumnService.findTextbookVolumnByCondition(new TextbookVolumnCondition());
		for (TextbookVolumn stage : stageList) {
			stageMap.put(stage.getCode(), stage.getName());
		}
		return stageMap;
	}

}
