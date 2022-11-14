package platform.education.rest.open.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import framework.generic.dao.Order;
import platform.education.generalcode.model.TextbookRelatedCode;
import platform.education.generalcode.model.TextbookVersion;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.model.CatalogTree;
import platform.education.generalcode.model.Grade;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.model.Subject;
import platform.education.generalcode.service.GradeService;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.service.KnowledgeTreeService;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.service.SubjectService;
import platform.education.generalcode.service.TextbookCatalogService;
import platform.education.generalcode.service.TextbookService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.service.TextbookVolumnService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.open.service.TextbookRestService;

public class TextbookRestServiceImpl implements TextbookRestService{
	
	@Autowired
	@Qualifier("jcTextbookService")
	private TextbookService jcTextbookService;
	@Autowired
	@Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;
	@Autowired
	@Qualifier("jcStageService")
	private StageService jcStageService;
	@Autowired
	@Qualifier("jcSubjectService")
	private SubjectService jcSubjectService;
	@Autowired
	@Qualifier("jcTextbookVersionService")
	private TextbookVersionService textbookVersionService;
	@Autowired
	@Qualifier("jcTextbookVolumnService")
	private TextbookVolumnService textbookVolumnService;
	@Autowired
	@Qualifier("jcGradeService")
	private GradeService jcGradeService;
	@Autowired
	@Qualifier("subjectService")
	private platform.education.generalTeachingAffair.service.SubjectService subjectService;
	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	@Autowired
	@Qualifier("jcTextbookCatalogService")
	private TextbookCatalogService textbookCatalogService;
	@Autowired
	@Qualifier("knowledgeTreeService")
	private KnowledgeTreeService knowledgeTreeService;
	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;

	@Override
	public Object stageList(String sign, String appKey, String timeStamp, Integer schoolId, String subjectCode,
			String versionCode) {
		List<TextbookRelatedCode>  relatedCodeList = new ArrayList<TextbookRelatedCode>();
		String extension = "stage_code";
		if(schoolId-0==0) {
			relatedCodeList = jcTextbookService
			.findTextbookRelaedCode(null, subjectCode, versionCode, null, null, extension, Order.asc(extension));
		} else {
			relatedCodeList = resTextbookService
			.findTextbookRelaedCode(schoolId, null, subjectCode, versionCode, null, null, extension, Order.asc(extension));
		}
		
		if(relatedCodeList.size()==0) {
			return getCommonErrorResult("schoolId, subjectCode, versionCode");
		} else {
			Map<String, Object> entity = new TreeMap<String, Object>();
			for (TextbookRelatedCode resTextbookRelatedCode : relatedCodeList) {
				String stageCode = resTextbookRelatedCode.getStageCode();
				Stage stage = jcStageService.findStageByCode(stageCode);
				if(stage!=null) {
					entity.put(stageCode, stage.getName());
				} else {
					entity.put(stageCode, "未知学段");
				}
			}
			Iterator<Entry<String, Object>> its = entity.entrySet().iterator();
			
			List<Object> resultList = new ArrayList<Object>();
			while(its.hasNext()) {
				Entry<String, Object> map = its.next();
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("stageCode", map.getKey());
				result.put("stageName", map.getValue());
				resultList.add(result);
			}
			return new ResponseVo<Object>("0", resultList);
		}
		
	}

	@Override
	public Object subjectList(String sign, String appKey, String timeStamp, Integer schoolId, String stageCode,
			String versionCode) {
		List<TextbookRelatedCode>  relatedCodeList = new ArrayList<TextbookRelatedCode>();
		String extension = "subject_code";
		if(schoolId-0==0) {
			relatedCodeList = jcTextbookService
			.findTextbookRelaedCode(stageCode, null, versionCode, null, null, extension, Order.asc(extension));
		} else {
			relatedCodeList = resTextbookService
			.findTextbookRelaedCode(schoolId, stageCode, null, versionCode, null, null, extension, Order.asc(extension));
		}
		
		if(relatedCodeList.size()==0) {
			return getCommonErrorResult("schoolId, stageCode, versionCode");
		} else {
			Map<String, Object> entity = new TreeMap<String, Object>();
			for (TextbookRelatedCode resTextbookRelatedCode : relatedCodeList) {
				String subjectCode = resTextbookRelatedCode.getSubjectCode();
				String subjectName = null;
				if(schoolId-0==0) {
					Subject subject = jcSubjectService.findByCode(Integer.parseInt(subjectCode));
					if(subject!=null) {
						subjectName = subject.getName();
					}
				} else {
					platform.education.generalTeachingAffair.model.Subject subject = subjectService.findUnique(schoolId, subjectCode);
					if(subject!=null) {
						subjectName = subject.getName();
					}
				}
				if(subjectName!=null) {
					entity.put(subjectCode, subjectName);
				} else {
					entity.put(subjectCode, "未知科目");
				}
				
			}
			Iterator<Entry<String, Object>> its = entity.entrySet().iterator();
			
			List<Object> resultList = new ArrayList<Object>();
			while(its.hasNext()) {
				Entry<String, Object> map = its.next();
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("subjectCode", map.getKey());
				result.put("subjectName", map.getValue());
				resultList.add(result);
			}
			return new ResponseVo<Object>("0", resultList);
		}
	}

	@Override
	public Object versionList(String sign, String appKey, String timeStamp, Integer schoolId, String stageCode,
			String subjectCode) {
		List<TextbookRelatedCode>  relatedCodeList = new ArrayList<TextbookRelatedCode>();
		String extension = "version";
		if(schoolId-0==0) {
			relatedCodeList = jcTextbookService
			.findTextbookRelaedCode(stageCode, subjectCode, null, null, null, extension, Order.asc(extension));
		} else {
			relatedCodeList = resTextbookService
			.findTextbookRelaedCode(schoolId, stageCode, subjectCode, null, null, null, extension, Order.asc(extension));
		}
		
		if(relatedCodeList.size()==0) {
			return getCommonErrorResult("schoolId, stageCode, subjectCode");
		} else {
			Map<String, Object> entity = new TreeMap<String, Object>();
			for (TextbookRelatedCode resTextbookRelatedCode : relatedCodeList) {
				String versionCode = resTextbookRelatedCode.getVersionCode();
				TextbookVersion textbookVersion = textbookVersionService.findTextbookVersionById(Integer.parseInt(versionCode));
				if(textbookVersion==null) {
					entity.put(versionCode, "未知版本");
				} else {
					entity.put(versionCode, textbookVersion.getName());
				}
			}
			
			Iterator<Entry<String, Object>> its = entity.entrySet().iterator();
			
			List<Object> resultList = new ArrayList<Object>();
			while(its.hasNext()) {
				Entry<String, Object> map = its.next();
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("versionCode", map.getKey());
				result.put("versionName", map.getValue());
				resultList.add(result);
			}
			return new ResponseVo<Object>("0", resultList);
		}
	}

	@Override
	public Object volumnList(String sign, String appKey, String timeStamp, Integer schoolId, String stageCode,
			String subjectCode, String versionCode) {
		List<TextbookRelatedCode>  relatedCodeList = new ArrayList<TextbookRelatedCode>();
		String extension = "volumn";
		if(schoolId-0==0) {
			relatedCodeList = jcTextbookService
			.findTextbookRelaedCode(stageCode, subjectCode, versionCode, null, null, extension, Order.asc(extension));
		} else {
			relatedCodeList = resTextbookService
			.findTextbookRelaedCode(schoolId, stageCode, subjectCode, versionCode, null, null, extension, Order.asc(extension));
		}
		
		if(relatedCodeList.size()==0) {
			return getCommonErrorResult("schoolId, stageCode ,subjectCode");
		} else {
			Map<String, Object> entity = new TreeMap<String, Object>();
			for (TextbookRelatedCode resTextbookRelatedCode : relatedCodeList) {
				String volumnCode = resTextbookRelatedCode.getVolumnCode();
				TextbookVolumn textbookVolumn = textbookVolumnService.findTextbookVolumnByCode(volumnCode);
				String gradeCode = resTextbookRelatedCode.getGradeCode();
				if(textbookVolumn==null) {
					entity.put(versionCode, "未知册次");
				} else {
					String volumnName = textbookVolumn.getName();
					Grade grade = jcGradeService.findGradeByCode(gradeCode);
					if(grade!=null) {
						volumnName = grade.getName()+volumnName;
						volumnCode = volumnCode+"_"+grade.getCode();
					}
					entity.put(volumnCode, volumnName);
				}
			}
			
			Iterator<Entry<String, Object>> its = entity.entrySet().iterator();
			List<Object> resultList = new ArrayList<Object>();
			while(its.hasNext()) {
				Entry<String, Object> map = its.next();
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("volumnCode", map.getKey());
				result.put("volumnName", map.getValue());
				resultList.add(result);
			}
			return new ResponseVo<Object>("0", resultList);
		}
	}

	@Override
	public Object catalogList(String sign, String appKey, String timeStamp, Integer schoolId, String stageCode,
			String subjectCode, String versionCode, String volumnCode) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//		if (verify != null) {
//			return verify;
//		}
		List<TextbookRelatedCode>  relatedCodeList = new ArrayList<TextbookRelatedCode>();
		
		String[] volumnGrade = volumnCode.split("_");
		
		String garde = null;
		if(volumnGrade.length==2) {
			garde = volumnGrade[1];
		}
		
		if(schoolId-0==0) {
			relatedCodeList = jcTextbookService
					.findTextbookRelaedCode(stageCode, subjectCode, versionCode, volumnGrade[0], garde, null, null);
		} else {
			relatedCodeList = resTextbookService
					.findTextbookRelaedCode(schoolId, stageCode, subjectCode, versionCode, volumnGrade[0], garde, null, null);
		}
		if(relatedCodeList.size()==0) {
			return getCommonErrorResult("schoolId, stageCode, subjectCode, versionCode, volumnCode");
		} else {
			TextbookRelatedCode textbookRelatedCode = relatedCodeList.get(0);
			Integer textbookId = textbookRelatedCode.getTextbookId();
			CatalogTree tree = null;
			
			if(schoolId-0==0) {
				tree = textbookCatalogService.findCatalogTreeObjByTextbookId(textbookId);
			} else {
				tree = resTextbookCatalogService.findCatalogTreeByTextbookId(textbookId);
			}
			if(tree == null){
				return getCommonErrorResult("schoolId, stageCode, subjectCode, versionCode, volumnCode");
			} else {
				List<Object> result = new ArrayList<Object>();
				result.add(tree);
				return new ResponseVo<Object>("0", result);
			}
		}
	}
	
	private ResponseError getCommonErrorResult(String param) {
		ResponseInfo info = new ResponseInfo();
		info.setMsg("找不到数据");
		info.setDetail("找不到数据");
		info.setParam(param);
		return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
	}

}
