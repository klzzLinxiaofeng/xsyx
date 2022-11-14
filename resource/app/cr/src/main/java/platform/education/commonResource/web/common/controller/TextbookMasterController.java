package platform.education.commonResource.web.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.service.*;
import platform.education.generalcode.vo.*;

import java.util.*;

@Controller
@RequestMapping("/teach/textBookMaster/master")
public class TextbookMasterController {

	// 教材目录
	@Autowired
	@Qualifier("jcTextbookCatalogService")
	protected TextbookCatalogService jcTextbookCatalogService;
	// 教材
	@Autowired
	@Qualifier("jcTextbookService")
	protected TextbookService jcTextbookService;
	// 教材分类目录
	@Autowired
	@Qualifier("jcTextbookDirectoryService")
	protected TextbookDirectoryService jcTextbookDirectoryService;
	// 教材出版社
	@Autowired
	@Qualifier("jcTextbookPublisherService")
	protected TextbookPublisherService jcTextbookPublisherService;
	// 教材综合服务
	@Autowired
	@Qualifier("jcTextbookMasterService")
	protected TextbookMasterService jcTextbookMasterService;
	// 校本教材目录
	@Autowired
	@Qualifier("resTextbookCatalogService")
	protected ResTextbookCatalogService resTextbookCatalogService;
	// 校本教材
	@Autowired
	@Qualifier("resTextbookService")
	protected ResTextbookService resTextbookService;
	// 科目
	@Autowired
	@Qualifier("subjectService")
	protected SubjectService subjectService;
	
	@Autowired
	@Qualifier("teamStudentService")
	protected TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("gradeService")
	protected GradeService gradeService;

	/**
	 * 获取公共或者校本的教材
	 * @param user 当前登录用户
	 * @param stageCode	学段
	 * @param subjectCode 科目
	 * @param version	版本
	 * @param gradeCodeVolumn 册次
	 * @param type 根据那个字段去获取 type==stageCode刚按照stageCode获取
	 * @param isPublish 1为获取公共教材， 其它情况获取当前登录人员所属学校教材
	 * @return
	 */
	@RequestMapping(value = "/textBook")
	@ResponseBody
	public Map<String, String> findTextBook(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "isPublish", required = false) Integer isPublish) {
		Map<String, String> map = new HashMap<String, String>();
		String[] stageCodes = {"2", "3", "4"};
		if(user != null && user.getStageCodes() != null && user.getStageCodes().length>0){
			stageCodes = user.getStageCodes();     
		}
		/**判断获取公共教材还是校本教材*/
		if(isPublish!=null&&isPublish==1) {
			/**获取公共教材*/
			map = this.jcTextbookMasterService.findTextBook(stageCodes,stageCode, subjectCode,gradeCodeVolumn, version, type);
		} else {
			/**资源库标识*/
			Integer schoolId = user.getSchoolId();
			if("subjectCode".equals(type)) {
				map = this.getSubject(stageCode, schoolId);
			} else {
				/**获取当前登录用户所属学校教材*/
				map = this.resTextbookService.findTextBook(stageCodes,stageCode, subjectCode,gradeCodeVolumn, version, type, schoolId);
			}
			
		}
		
		return map;
	}

	@RequestMapping(value = "/textBookCatalog")
	@ResponseBody
	public TextbookCatalogVo findTextBookCatalog(Integer id) {
		TextbookCatalogVo vo = new TextbookCatalogVo();
		if (id == null || id == 0) {

		} else {
			vo = this.jcTextbookMasterService.findTextbookCatalogList(id);
		}

		return vo;
	}

	@RequestMapping(value = "/textBookCatalogSelect")
	@ResponseBody
	public List<TextbookCatalog> findTextBookCatalogSelect(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "type", required = false) String type) {
		List<TextbookCatalog> resultList = new ArrayList<TextbookCatalog>();
		TextbookCondition condition = new TextbookCondition();
		condition.setStageCode(stageCode);
		condition.setSubjectCode(subjectCode);
		condition.setGradeCode(gradeCode);
		condition.setVolumn(volumn);
		// condition.setPublisherId(version);
		condition.setVersion(version);
		List<Textbook> list = this.jcTextbookService
				.findTextbookByCondition(condition);
		if (list.size() != 1) {

		} else {
			Integer textBookId = list.get(0).getId();
			resultList = this.jcTextbookMasterService
					.findTextBookCatalogSelect(textBookId, parentId, type);
		}
		
		return resultList;
	}
	
	@RequestMapping(value = "/resTextBookCatalogSelect")
	@ResponseBody
	public List<ResTextbookCatalog> findResTextBookCatalogSelect(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "type", required = false) String type) {
		List<ResTextbookCatalog> resultList = new ArrayList<ResTextbookCatalog>();
		/**根据校本教材上一级目录获取下一级目录*/
		ResTextbookCondition condition = new ResTextbookCondition();
		condition.setStageCode(stageCode);
		condition.setSubjectCode(subjectCode);
		condition.setGradeCode(gradeCode);
		condition.setVolumn(volumn);
		// condition.setPublisherId(version);
		condition.setVersion(version);
		condition.setResourceLibId(user.getSchoolId());
		List<ResTextbook> list = this.resTextbookService
				.findResTextbookByCondition(condition);
		if (list.size() == 0) {

		} else {
			Integer textBookId = list.get(0).getId();
			resultList = this.resTextbookCatalogService
					.findResTextBookCatalogSelect(textBookId, parentId, type);
		}
		
		return resultList;
	}


	@RequestMapping(value = "/jcTextBookCatalogSelect")
	@ResponseBody
	public List<TextbookCatalog> findJcTextBookCatalogSelect(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "isPublish", required = false) Integer isPublish,
			@RequestParam(value = "type", required = false) String type) {
		List<TextbookCatalog> resultList = new ArrayList<TextbookCatalog>();
		/**根据公共教材上一级目录获取下一级目录*/
		List<Textbook> list1 = null;
		TextbookCondition condition1 = new TextbookCondition();
		condition1.setStageCode(stageCode);
		condition1.setSubjectCode(subjectCode);
		condition1.setGradeCode(gradeCode);
		condition1.setVolumn(volumn);
		condition1.setVersion(version);
		list1 = jcTextbookService.findTextbookByCondition(condition1);
		if (list1.size() == 0) {

		} else {
				TextbookCatalogVo root = new TextbookCatalogVo();
				Integer textBookId = list1.get(0).getId();
				if(textBookId != null && textBookId >0){
					resultList = this.jcTextbookMasterService.findTextBookCatalogSelect(textBookId,parentId,type);

				}
			}
		return resultList;
	}


	@RequestMapping(value = "/textBookCatalogList")
	@ResponseBody
	public List<TextbookCatalogVo> findTextBookCatalogList(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "catalogId", required = false) Integer catalogId) {
		TextbookCatalogVo root = new TextbookCatalogVo();
		List<TextbookCatalogVo> resultList = new ArrayList<TextbookCatalogVo>();
		if (type != null && "select".equals(type)) {

			TextbookCondition condition = new TextbookCondition();
			condition.setStageCode(stageCode);
			condition.setSubjectCode(subjectCode);
			if(gradeCode!= null&&"0".equals(gradeCode)){//前端显示gradeCode必填值，不能为空，所以用0做转换
				gradeCode = "";
			}
			condition.setGradeCode(gradeCode);
			condition.setVolumn(volumn);
			// condition.setPublisherId(version);
			condition.setVersion(version);
			List<Textbook> list = this.jcTextbookService
					.findTextbookByCondition(condition);
			if (list.size() == 0) {

			} else {
				catalogId = list.get(0).getId();
			}
		}
		if (catalogId != null && catalogId > 0) {
			root = this.jcTextbookMasterService
					.findTextbookCatalogList(catalogId);

		}
		if (root.getId() != null && root.getId() > 0) {
			resultList = this.jcTextbookMasterService
					.nodeOutListExcludeQuote(root);
		}
		return resultList; 
	}
	
	@RequestMapping(value = "/resTextBookCatalogList")
	@ResponseBody
	public List<ResTextbookCatalogVo> findResTextBookCatalogList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "catalogId", required = false) Integer catalogId) {
		ResTextbookCatalogVo root = new ResTextbookCatalogVo();
		List<ResTextbookCatalogVo> resultList = new ArrayList<ResTextbookCatalogVo>();
		if (type != null && "select".equals(type)) {

			ResTextbookCondition condition = new ResTextbookCondition();
			condition.setStageCode(stageCode);
			condition.setSubjectCode(subjectCode);
			if(gradeCode!= null&&"0".equals(gradeCode)){//前端显示gradeCode必填值，不能为空，所以用0做转换
				gradeCode = "";
			}
			condition.setGradeCode(gradeCode);
			condition.setVolumn(volumn);
			// condition.setPublisherId(version);
			condition.setVersion(version);
			condition.setResourceLibId(user.getSchoolId());
			List<ResTextbook> list = this.resTextbookService
					.findResTextbookByCondition(condition);
			if (list.size() == 0) {

			} else {
				catalogId = list.get(0).getId();
			}
		}
		if (catalogId != null && catalogId > 0) {
			root = this.resTextbookCatalogService
					.findResTextbookCatalogList(catalogId);

		}
		if (root.getId() != null && root.getId() > 0) {
			resultList = this.resTextbookCatalogService
					.nodeOutListExcludeQuote(root);
		}
		return resultList;
	}
	
	@RequestMapping(value = "/resTextCatalogList/{code}")
	@ResponseBody
	public List<ResTextbookCatalogVo> findCatalogList(@PathVariable(value = "code") String catalogCode) {
		ResTextbookCatalogCondition condition = new ResTextbookCatalogCondition();
		condition.setCode(catalogCode);
		List<ResTextbookCatalog> textbookCatalogList = resTextbookCatalogService.findResTextbookCatalogByCondition(condition);
		List<ResTextbookCatalogVo> lists = new ArrayList<ResTextbookCatalogVo>();
		if(textbookCatalogList!=null&&textbookCatalogList.size()>0){
			
			ResTextbookCatalog textbookCatalog = textbookCatalogList.get(0);
			ResTextbook textbook = resTextbookService.findResTextbookById(textbookCatalog.getTestBookId());
			
			ResTextbookCatalogVo root = new ResTextbookCatalogVo();
			List<ResTextbookCatalogVo> resultList = new ArrayList<ResTextbookCatalogVo>();
			if (catalogCode != null && !"".equals(catalogCode)) {
				root = this.resTextbookCatalogService
						.findTextbookCatalogList(textbook.getId());
				
			}
			if (root.getId() != null && root.getId() > 0) {
				resultList = this.resTextbookCatalogService
						.nodeOutListExcludeQuote(root);
			}
			root = new ResTextbookCatalogVo();
			root.setId(textbookCatalog.getId());
			root.setParentId(textbookCatalog.getParentId());
			root.setLevel(textbookCatalog.getLevel());
			if(resultList!=null){
				resultList.get(0).setSelected(1);
				lists.add(resultList.get(0));
				getParent(resultList, root, lists);
				getEqLevel(resultList, root, lists);
			}
		}
		return lists; 
	}
	
	@RequestMapping(value = "/subject/list")
	@ResponseBody
	public Object findSubject(@CurrentUser UserInfo user) {
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setSchoolId(user.getSchoolId());
		List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
		
		return subjectList;
	}
	
	private void getParent(List<ResTextbookCatalogVo> lists, ResTextbookCatalogVo root, List<ResTextbookCatalogVo> list){
		for (ResTextbookCatalogVo vo : lists) {
			if(vo.getId()-root.getParentId()==0) {
				getParent(lists, vo, list);
				getEqLevel(lists, vo, list);
			}
		}
	}

	private void getEqLevel(List<ResTextbookCatalogVo> lists, ResTextbookCatalogVo root, List<ResTextbookCatalogVo> list) {
		for (ResTextbookCatalogVo vo : lists) {
			if(vo.getParentId()-0==0) {
				continue;
			}
			if(vo.getLevel()-root.getLevel()==0 && root.getParentId()-vo.getParentId()==0) {
				if(root.getId()-vo.getId()==0) {
					vo.setSelected(1);
				} else {
					vo.setSelected(0);
				}
				list.add(vo);
			}
		}
	}
	
	
	
	private Map<String, String> getSubject(String stageCode, Integer schoolId) {
		List<ResTextbook> textbookList = resTextbookService.getResTextBookByCondition(stageCode,
				null,null, null,null,null,schoolId);
		Map<String, String> map = new HashMap<String, String>();
		for (ResTextbook textbook : textbookList) {
			map.put(textbook.getSubjectCode(), textbook.getSubjectCode());
		}
		Set<String> set = map.keySet();
		map = new LinkedHashMap<String, String>();
		map.put("请选择", "");
		for (String value : set) {
			if ("请选择".equals(value)) {
				continue;
			}
			SubjectCondition subjectCondition = new SubjectCondition();
			subjectCondition.setCode(value);
			List<Subject> subjectList = this.subjectService.findSubjectByCondition(subjectCondition, null, null);
			if (subjectList.size() >= 1) {
				String key = subjectList.get(0).getName();
				map.put(key, value);
			} else {
				map.put(value + ":错误", "0");
			}
		}
		return map;
	}

}
