
package platform.education.commonResource.web.common.ftlUtil.weekTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ContextLoader;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.ftlUtil.GeneratorHtml;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.OperationWeekcount;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.OperationWeekcountService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceOperationService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.OperationWeekcountCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;

public class FtlReport {
	@Autowired
    @Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("operationWeekcountService")
	private OperationWeekcountService operationWeekcountService;
	
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	
	@Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;
	
	public void process(){
		GeneratorHtml cf = new GeneratorHtml();
		String templateFilePath = FtlContants.FTL_TEMPLATEPATH;
		String htmlFilePath = FtlContants.FTL_HTMLPATH;
		List<ResourceLibrary> list = this.resourceLibraryService.findResourceLibraryByCondition(null);
	    Integer relateAppId = null;
	    Integer relateSchoolId = null;
		for(ResourceLibrary rl : list){
			relateAppId = rl.getAppId();
			relateSchoolId = rl.getOwerId();
			String htmlFileName = "index_"+ relateAppId +"_"+ relateSchoolId +"_"+ rl.getUuid() +".html";
			ResourceVoCondition condition = new ResourceVoCondition();
			List<ResourceVo> vos = new ArrayList<ResourceVo>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			/**
			 * 小学语文科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosOneChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosTwoChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosThreeChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosFourChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosFiveChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosSixChinese", vos);
			/**
			 * 初中语文科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleOneChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeChinese", vos);
			/**
			 * 小学数学科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosOneMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosTwoMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosThreeMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosFourMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosFiveMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosSixMath", vos);
			/**
			 * 初中数学科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleOneMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeMath", vos);
			/**
			 * 小学英语科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosThreeEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosFourEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosFiveEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosSixEnglish", vos);
			/**
			 * 初中英语科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleOneEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeEnglish", vos);
			/**
			 * 语文精品推荐
			 */
			vos = getResourceBySubject(condition.SUBJECT_CHINESE,relateAppId, relateSchoolId);
			data.put("vosChineseRecommend", vos);
			/**
			 * 数学精品推荐
			 */
			vos = getResourceBySubject(condition.SUBJECT_MATH,relateAppId, relateSchoolId);
			data.put("vosMathRecommend", vos);
			/**
			 * 英语精品推荐
			 */
			vos = getResourceBySubject(condition.SUBJECT_ENGLISH,relateAppId, relateSchoolId);
			data.put("vosEnglishRecommend", vos);
			
			String context = FtlContants.HTML_BASE_PATH;
			data.put("contextPath", context);
		    cf.geneHtmlFile(FtlContants.FTL_TEMPLATERESOURCENAME, templateFilePath, data, htmlFilePath, htmlFileName);
		}
	}
	
	private List<ResourceVo> getResourceByGradeCode(String gradeCode, String subjectCode,Integer relateAppId,Integer relateSchoolId) {
		Page page = new Page();
		Order order = new Order();
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		if (relateAppId != null) {
			condition.setRelateAppId(relateAppId);
		} else {
			condition.setRelateAppId(appId);
		}
		if (relateSchoolId != null) {
			condition.setRelateSchoolId(relateSchoolId);
		} else {
			condition.setRelateSchoolId(ownerId);
		}
		condition.setGradeCode(gradeCode);
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		condition.setSubjectCode(subjectCode);
		order.setAscending(false);
		order.setProperty("create_date");
		page.setPageSize(7);// 页面拿数据几条数据
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		condition.setResType(ResourceType.LEARNING_DESIGN);
		List<ResourceVo> kjvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.HOMEWORK);
		List<ResourceVo> zyvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.EXAM);
		List<ResourceVo> sjvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.TEACHING_PLAN);
		List<ResourceVo> javos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.MATERIAL);
		List<ResourceVo> scvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		if ((kjvos.size() + zyvos.size() + sjvos.size() + javos.size() + scvos.size()) <= 7) {
			vos.addAll(kjvos);
			vos.addAll(zyvos);
			vos.addAll(sjvos);
			vos.addAll(javos);
			vos.addAll(scvos);
		} else {
			for (int i = 0; i < 7; i++) {

				if (kjvos.size() > i) {
					vos.add(kjvos.get(i));
					if (vos.size() == 7) {
						break;
					}

				}
				if (zyvos.size() > i) {
					vos.add(zyvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (sjvos.size() > i) {
					vos.add(sjvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (javos.size() > i) {
					vos.add(javos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (scvos.size() > i) {
					vos.add(scvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
			}
		}
		return vos;
	}
	
	private List<ResourceVo> getResourceBySubject(String subjectCode, Integer relateAppId,Integer relateSchoolId){
		Page page = new Page();
		Order order = new Order();
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		condition.setRelateAppId(relateAppId);
		condition.setRelateSchoolId(relateSchoolId);
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		condition.setSubjectCode(subjectCode);
		page.setPageSize(6);
		order.setAscending(false);
		order.setProperty("uuid");
		vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		return vos;
	}
}
