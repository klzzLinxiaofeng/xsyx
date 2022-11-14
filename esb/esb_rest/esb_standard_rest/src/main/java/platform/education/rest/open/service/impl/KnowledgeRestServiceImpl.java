package platform.education.rest.open.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalcode.model.KnowledgeNodeTree;
import platform.education.generalcode.model.KnowledgeTree;
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
import platform.education.rest.open.service.KnowledgeRestService;
import platform.education.rest.open.service.util.VerificationUtil;

public class KnowledgeRestServiceImpl implements KnowledgeRestService{
	
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
	public Object knowledgeList(String sign, String appKey, String timeStamp, String stageCode, String subjectCode) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//		if (verify != null) {
//			return verify;
//		}
		
		List<KnowledgeTree> knowledgeTreeList = knowledgeTreeService.findKnowledgeTreeBySubjectAndStage(subjectCode, stageCode);
		if(knowledgeTreeList.size()==0) {
			return getCommonErrorResult("stageCode, subjectCode");
		}
		Integer treeId = knowledgeTreeList.get(0).getId();
		List<KnowledgeNodeTree> result = knowledgeNodeService.findKnowledgeNodeTree(treeId);
		if(result.size() == 0){
			return getCommonErrorResult("stageCode, subjectCode");
		} else {
			return new ResponseVo<Object>("0", result);
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
