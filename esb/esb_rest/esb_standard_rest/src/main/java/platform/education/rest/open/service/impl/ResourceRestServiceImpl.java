package platform.education.rest.open.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.storage.StorageStatusCode;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalcode.model.*;
import platform.education.generalcode.service.*;
import platform.education.oauth2.service.OAuthService;
import platform.education.resource.contants.ActionType;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.CatalogResource;
import platform.education.resource.model.Favorites;
import platform.education.resource.model.Likes;
import platform.education.resource.model.Resource;
import platform.education.resource.service.*;
import platform.education.resource.vo.ResData;
import platform.education.resource.vo.ResKnowledgeResourceVo;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;
import platform.education.rest.common.PageResponseVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.code.AppValidator;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.open.service.ResourceRestService;
import platform.education.rest.util.encoderUtil;
import platform.education.user.service.AclService;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.ConversionStatusService;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.utils.FileMd5Util;
import platform.service.storage.vo.FileConversionResult;
import platform.service.storage.vo.FileResult;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NoContentException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.alibaba.dubbo.common.json.JSON;

public class ResourceRestServiceImpl implements ResourceRestService {

	@Autowired
	@Qualifier("catalogResourceService")
	private CatalogResourceService catalogResourceService;
	
	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("resKnowledgeResourceService")
	private ResKnowledgeResourceService resKnowledgeResourceService;
	
	@Autowired
	@Qualifier("entityFileService")
	private EntityFileService entityFileService;
	
	@Autowired
	@Qualifier("likesService")
	private LikesService likesService;
	
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	
	@Autowired
	@Qualifier("userActionService")
	private UserActionService userAtionService;
	
	@Autowired
	@Qualifier("actionCodeService")
	private ActionCodeService actionCodeService;
	
	@Autowired
	@Qualifier("favoritesService")
	private FavoritesService favoritesService;
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService; 
	
	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	
	@Autowired
	@Qualifier("jcTextbookCatalogService")
	private TextbookCatalogService textbookCatalogService;
	
	@Autowired
	@Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;
	
	@Autowired
	@Qualifier("jcTextbookService")
	private TextbookService textbookService;
	
	@Autowired
	@Qualifier("jcStageService")
	private StageService stageService;
	
	@Autowired
	@Qualifier("jcTextbookVersionService")
	private TextbookVersionService versionService;
	
	@Autowired
	@Qualifier("jcGradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;
	
	@Autowired
	@Qualifier("jcTextbookVolumnService")
	private TextbookVolumnService volumnService;
	
	@Autowired
	@Qualifier("aclService")
	private AclService aclService;
	
	@Autowired
	@Qualifier("conversionStatusService")
	private ConversionStatusService conversionStatusService;
	
	@Autowired
	@Qualifier("appValidator")
	private AppValidator appValidator;
	
	@Autowired
	@Qualifier("resourceFulltextRetrievalService")
	private ResourceFulltextRetrievalService resourceFulltextRetrievalService;
	
	@Autowired
	@Qualifier("oAuthService")
	private OAuthService oAuthService;

	@Override
	public Object listByCatalog(String sign, String appKey, String timeStamp, 
			String catalogCode, String resType, Integer pageNumber, Integer pageSize
			,String sortItem, Integer sortType) {
		Page page = getPage(pageSize, pageNumber);
		Order order = getOrder(sortItem, sortType);
		if(order==null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("不支持的排序项");
			info.setMsg("不支持的排序项");
			info.setParam("sortItem");
			return  new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		
		/**是否是根节点*/
		Boolean isRoot = false;
		/**是否是校本*/
		Boolean isSchool = false;
		Integer textbookId = null;
		
		if(catalogCode.contains("custom_")) {
			ResTextbookCatalog catalog = resTextbookCatalogService.findResTextbookCatalogByCode(catalogCode);
			if(catalog!=null) {
				textbookId = catalog.getTestBookId();
				if(catalog.getParentId()-0==0) {
					isRoot = true;
					isSchool = true;
				}
			}
		} else {
			List<TextbookCatalog> catalogList = textbookCatalogService.findTextbookCatalogByCode(catalogCode);
			if(catalogList.size()>0) {
				TextbookCatalog catalog = catalogList.get(0);
				textbookId = catalog.getTestBookId();
				if(catalog.getParentId()-0==0) {
					isRoot = true;
				}
			}
		}
		
		if(textbookId==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("catalogCode");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<ResourceVo> resourceVoList = new ArrayList<ResourceVo>();
		
		/**最顶级目录则查询该书籍的全部资源*/
		if(isRoot) {
			resourceVoList = findTextbookResourceVoList(textbookId, isSchool, resType, page, order);
		} else {
			/**子节点则查询单一子节点资源*/
			List<Integer> resourceIds = catalogResourceService.findResourceIdsByCatalogCode(catalogCode, null);
			resourceVoList = resourceService.findSubFiledByResourceIdsAndType(resourceIds, resType, page, order);
			page.setTotalRows(resourceIds.size());
		}
		
		if(resourceVoList.size()==0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("catalogCode, resType");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for (ResourceVo resourceVo : resourceVoList) {
			Map<String, Object> map = getListByCatalogResultVo(resourceVo);
			result.add(map);
		}
		
		return new PageResponseVo<Object>("0", result, page.getTotalRows());
	}

	private List<ResourceVo> findTextbookResourceVoList(Integer testBookId, Boolean isSchool, String resType,  Page page, Order order) {
		ResourceVoCondition condition = new ResourceVoCondition();
		if(!"0".equals(resType)) {
			try {
				condition.setResType(Integer.parseInt(resType));
			} catch (Exception e) {
				System.out.println("类型错误");
			}
			
		}
		if(isSchool) {
			ResTextbook textbook = resTextbookService.findResTextbookById(testBookId);
			/**目录存在但是相应的book不存在异常处理*/
			if(textbook==null) {
				return new ArrayList<ResourceVo>();
			}
			condition.setStageCode(textbook.getStageCode());
			condition.setGradeCode(textbook.getGradeCode());
			condition.setSubjectCode(textbook.getSubjectCode());
			condition.setVolumnCode(textbook.getVolumn());
			condition.setVersionCode(textbook.getVersion());
			condition.setRelateSchoolId(textbook.getResourceLibId());
			condition.setVerify("res_school");
			condition.setAppId(1);
		} else {
			Textbook textbook = textbookService.findTextbookById(testBookId);
			/**目录存在但是相应的book不存在异常处理*/
			if(textbook==null) {
				return new ArrayList<ResourceVo>();
			}
			condition.setStageCode(textbook.getStageCode());
			condition.setGradeCode(textbook.getGradeCode());
			condition.setSubjectCode(textbook.getSubjectCode());
			condition.setVolumnCode(textbook.getVolumn());
			condition.setVersionCode(textbook.getVersion());
			condition.setVerify("0");
			condition.setRelateSchoolId(0);
			condition.setAppId(6);
		}
		
		List<ResourceVo> result = resourceService.findResourceVoByMoreCondition(condition, page, order);
		return result;
	}

	@Override
	public Object listByKnowledge(String sign, String appKey, String timeStamp, 
			Integer nodeId, String resType, Integer pageNumber, Integer pageSize) {
		Page page = getPage(pageSize, pageNumber);
		List<ResKnowledgeResourceVo> resKnowledgeResourceList = resKnowledgeResourceService.
				findSubFiledByNodeIdAndType(nodeId, resType, page);
		
		if(resKnowledgeResourceList.size()==0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("nodeId, resType");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for (ResKnowledgeResourceVo resKnowledgeResourceVo : resKnowledgeResourceList) {
			Map<String, Object> map = getListByKnowledgeResultVo(resKnowledgeResourceVo);
			result.add(map);
		}
		return new ResponseVo<Object>("0", result);
	}
	
	/**
	 * 获取资源详情
	 */
	@Override
	public Object getDetail(String sign, String appKey, String timeStamp, 
			String resUuid, Integer userId, String Token) {
		/**创建资源操作记录*/
		ResourceVo resourceVo = resourceService.createResourceOperation(resUuid, userId, OperationType.CLICK);
		if(resourceVo==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		String entityId = resourceService.getEntityIdByObjectIdAndType(resourceVo.getObjectId(), resourceVo.getResType());
		
		EntityFile entityFile = entityFileService.findFileByUUID(entityId);
		if(entityFile==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid, resType");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		Map<String, Object> result = getGetDetailResult(resourceVo, entityFile, userId);
		/**创建用户行为操作记录*/
		resourceService.createUserAction(resUuid, userId, ActionType.CLICK);
		/**更改用户点击数*/
		Resource resource = new Resource();
		resource.setId(resourceVo.getId());
		resource.setClickCount(resourceVo.getClickCount()+1);
		resourceService.modify(resource);
		
		return new ResponseVo<Object>("0", result);
	}
	
	@Override
	public Object resourceNeedList(String sign, String appKey, String timeStamp, Integer schoolId, String content,
			String stageCode, String subjectCode, String resType, Integer pageNumber, Integer pageSize, String sortItem,
			Integer sortType) {
		Page page = getPage(pageSize, pageNumber);
		Order order = getOrder(sortItem, sortType);
		
		if(order==null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("不支持的排序项");
			info.setMsg("不支持的排序项");
			info.setParam("sortItem");
			return  new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		/**按时间排转换成按id排*/
		if("create_date".equals(sortItem)) {
			order.setProperty("id");
		}
		Integer type = Integer.parseInt(resType);
		List<ResourceVo> resourceVoList = null;
		if(schoolId-0==0 & resourceFulltextRetrievalService.isEnable()) {
			resourceVoList = resourceFulltextRetrievalService
					.findByFullTextRetrieval(content, type, stageCode, subjectCode, page, order);
		} else {
			resourceVoList = resourceService.findResourceNeedListByParam(
					schoolId, content, stageCode, subjectCode, resType, page, order);
		}
		
		
		
		
		if(resourceVoList.size()==0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("schoolId, content, stageCode, subjectCode, resType");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<Object> result = getResourceNeedListResult(resourceVoList);
		
		return new PageResponseVo<Object>("0", result, page.getTotalRows());
	}

	@Override
	public Object personalList(String sign, String appKey, String timeStamp, Integer userId, String resType,
			String content, Integer pageNumber, Integer pageSize, String Token) {
		Page page = getPage(pageSize, pageNumber);
		List<ResourceVo> resourceVoList = resourceService.findResourcePersonalListByParam(userId, resType, content, page);
		
		if(resourceVoList.size()==0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("userId, resType, content");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<Object> result = getPersonalListResult(resourceVoList);
		return new PageResponseVo<Object>("0", result, page.getTotalRows());
	}

	@Override
	public Object personalUpload(String sign, String appKey, String timeStamp, Integer userId, String resData, String Token) {
		ResData data = null;
		String uuid = "";
		try {
			//data = JSON.parse(resData, ResData.class);
			ResponseInfo info = new ResponseInfo();
			String paramName = "";
			boolean validate = false;
			String title = data.getTitle();
			Integer resType = data.getResType();
			String fileUuid = data.getFileUuid();
			
			if(title==null || "".equals(title)) {
				paramName = "资源标题";
				validate = true;
			} else if(resType==null) {
				paramName = "资源类型";
				validate = true;
			} else if(fileUuid==null || "".equals(fileUuid)) {
				paramName = "文件UUID";
				validate = true;
			}
			if(validate) {
				info.setDetail(paramName + "参数必填");
				info.setMsg(paramName + "参数为空");
				info.setParam(paramName);
				return  new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(!validateResType(resType)) {
				info.setMsg("不支持的资源类型");
				info.setDetail("不支持的资源类型");
				info.setParam("resType");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			uuid = fileUuid;
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数格式错误");
			info.setDetail("参数格式错误");
			info.setParam("resData");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
		FileResult fileResult = fileService.findFileByUUID(uuid);
		if(fileResult==null || fileResult.getEntityFile()==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数格式错误");
			info.setDetail("参数格式错误");
			info.setParam("resData");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
		SchoolUser user = schoolUserService.findSchoolUserByUserId(userId);
		String username = "";
		Integer schoolId = 0;
		if(user!=null) {
			schoolId = user.getSchoolId();
			username = user.getUserName();
		}
		
		Object result = resourceService.insertPersonalResource(userId, schoolId, username, data);
		return new ResponseVo<Object>("0", result);
	}

	@Override
	public Object schoolUpload(String sign, String appKey, String timeStamp, Integer userId, String resData, String Token) {
		ResData data = null;
		try {
			//data = JSON.parse(resData, ResData.class);
			ResponseInfo info = new ResponseInfo();
			String paramName = "";
			boolean validate = false;
			
			if(data.getTitle()==null || "".equals(data.getTitle())) {
				validate = true;
				paramName = "资源标题";
			} else if(data.getResType()==null || "".equals(data.getResType())) {
				paramName = "资源类型";
				validate = true;
			} else if(data.getFileUuid()==null || "".equals(data.getFileUuid())) {
				paramName = "文件UUID";
				validate = true;
			} else if(data.getCatalogCode()==null || "".equals(data.getCatalogCode())) {
				paramName = "教材目录Code";
				validate = true;
			}
			if(validate) {
				info.setDetail(paramName + "参数必填");
				info.setMsg(paramName + "参数为空");
				info.setParam(paramName);
				return  new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(!validateResType(data.getResType())) {
				info.setMsg("不支持的资源类型");
				info.setDetail("不支持的资源类型");
				info.setParam("resType");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数格式错误");
			info.setDetail("参数格式错误");
			info.setParam("resData");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		boolean permission = aclService.hasPermission(userId, "XIAO_BEN_ZI_YUAN", 0, 1);
		if(!permission) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数内容错误");
			info.setDetail("该账号无上传校本资源的权限");
			info.setParam("userId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		ResTextbookCatalog catalog = resTextbookCatalogService.findResTextbookCatalogByCode(data.getCatalogCode());
		if(catalog==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数内容错误");
			info.setDetail("参数内容错误");
			info.setParam("catalogCode");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		} 
		ResTextbook textbook = resTextbookService.findResTextbookById(catalog.getTestBookId());
		if(textbook==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数内容错误");
			info.setDetail("参数内容错误");
			info.setParam("catalogCode");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		} 
		SchoolUser user = schoolUserService.findSchoolUserByUserId(userId);
		String username = "";
		Integer schoolId = 0;
		if(user!=null) {
			schoolId = user.getSchoolId();
			username = user.getUserName();
		}
		
		Integer libId = textbook.getResourceLibId();
		if(libId==null || libId-schoolId!=0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数内容错误");
			info.setDetail("找不到该目录");
			info.setParam("catalogCode");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		
		CatalogResource catalogResource = getCatalogResource(textbook, data.getCatalogCode(), schoolId);
		
		Object result = resourceService.insertSchoolResource(userId, schoolId, username, data, catalogResource);
		
		return new ResponseVo<Object>("0", result);
	}
	
	@Override
	public Object getM5d(MultipartFormDataInput input) throws IOException {
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		InputPart inputPart = inputParts.get(0);
		/**获取文件流*/
		InputStream inputStream = inputPart.getBody(InputStream.class, null);
		
		String md5 = FileMd5Util.getFileMd5(inputStream, 5);
		return md5;
	}
	
	@Override
	public Object fileUpload(MultipartFormDataInput input) {
		
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> userIdParts = uploadForm.get("userId");
		List<InputPart> timestamps = uploadForm.get("Timestamp");
		List<InputPart> signs = uploadForm.get("Sign");
		List<InputPart> appKeys = uploadForm.get("AppKey");
		List<InputPart> inputParts = uploadForm.get("file");
		
		boolean validate = false;
		String filed = "";
		if(userIdParts==null || userIdParts.size()==0) {
			filed = "userId";
			validate = true;
		}else if(timestamps==null || timestamps.size()==0) {
			filed = "Timestamp";
			validate = true;
		}else if(signs==null || signs.size()==0) {
			filed = "Sign";
			validate = true;
		} else if(appKeys==null || appKeys.size()==0) {
			filed = "AppKey";
			validate = true;
		} else if(inputParts==null || inputParts.size()==0) {
			filed = "file";
			validate = true;
		}
		if(validate) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail(filed + "参数必填");
			info.setMsg(filed + "参数为空");
			info.setParam(filed);
			return  new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		//InputPart userIdPart = userIdParts.get(0);
		InputPart timeStampPart = timestamps.get(0);
		InputPart signPart = signs.get(0);
		InputPart appKeyPart = appKeys.get(0);
		InputPart inputPart = inputParts.get(0);
		try {
			//Integer	userId = userIdPart.getBody(Integer.class, null);
			String	timeStamp = timeStampPart.getBody(String.class, null);
			String	sign = signPart.getBody(String.class, null);
			String	appKey = appKeyPart.getBody(String.class, null);
			Object verify = this.verifySign(sign, appKey, timeStamp);
	        if (verify != null) {
	            return verify;
	        }
			
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			/**获取文件名*/
			String fileName = getFileName(header);
			
			System.err.println("fileName: " + fileName);
			
			/**解析获取文件名和文件后缀*/
			String[] fileNames = fileName.split("\\.");
			String name = "";
			String suffix = "tmp";
			if(fileNames.length>0) {
				name = fileNames[0];
			}
			if(fileNames.length>1) {
				suffix = fileNames[1];
			}
			/**获取文件流*/
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			String contentType = "application/octet-stream";
			/**文件上传到ftp*/
			FileResult fileResult = fileService.upload(inputStream, suffix, contentType, name, "OpenApi");
			
			if(fileResult.getStatusCode().equals(StorageStatusCode.UPLOAD_SUCCESS)) {
				/**文件上传成功*/
				EntityFile entityFile = fileResult.getEntityFile();
				
				/**返回参数封装*/
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("uuid", entityFile.getUuid());
				result.put("createdTime", dateFormat.format(entityFile.getCreateDate()));
				
				return new ResponseVo<Object>("0", result);
			} else {
				ResponseInfo info = new ResponseInfo();
		        info.setDetail("上传文件发生异常，请与系统管理员联系");
		        info.setMsg("未知错误");
		        return new ResponseError("000001", info);
			}
		} catch (IOException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
	        info.setDetail("上传文件发生异常，请与系统管理员联系");
	        info.setMsg("未知错误");
	        return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object filePartUpload(MultipartFormDataInput input) {
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> userIdParts = uploadForm.get("userId");
		List<InputPart> currentChunks = uploadForm.get("chunk");
		List<InputPart> totalChunks = uploadForm.get("chunkSize");
		List<InputPart> md5Parts = uploadForm.get("md5");
		List<InputPart> timestamps = uploadForm.get("Timestamp");
		List<InputPart> signs = uploadForm.get("Sign");
		List<InputPart> appKeys = uploadForm.get("AppKey");
		List<InputPart> inputParts = uploadForm.get("file");
		
		boolean validate = false;
		
		String filed = "";
		if(userIdParts==null || userIdParts.size()==0) {
			filed = "userId";
			validate = true;
		}else if(timestamps==null || timestamps.size()==0) {
			filed = "Timestamp";
			validate = true;
		}else if(signs==null || signs.size()==0) {
			filed = "Sign";
			validate = true;
		} else if(appKeys==null || appKeys.size()==0) {
			filed = "AppKey";
			validate = true;
		} else if(inputParts==null || inputParts.size()==0) {
			filed = "file";
			validate = true;
		}
		if(validate) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail(filed + "参数必填");
			info.setMsg(filed + "参数为空");
			info.setParam(filed);
			return  new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		try {
			
			//InputPart userIdPart = userIdParts.get(0);
			InputPart timeStampPart = timestamps.get(0);
			InputPart signPart = signs.get(0);
			InputPart appKeyPart = appKeys.get(0);
			InputPart inputPart = inputParts.get(0);
			
			InputPart md5Part = null;
			InputPart currentChunkPart = null;
			InputPart totalChunkPart = null;
			if(md5Parts!=null) {
				md5Part = md5Parts.get(0);
			}
			if(currentChunks!=null) {
				currentChunkPart = currentChunks.get(0);
			}
			if(totalChunks!=null) {
				totalChunkPart = totalChunks.get(0);
			}
		
			Integer currentChunk = null;
			Integer totalChunk = null;
			boolean isComplete = false;
			String	md5 = null;
			//Integer	userId = userIdPart.getBody(Integer.class, null);
			if(currentChunkPart!=null) {
				currentChunk = currentChunkPart.getBody(Integer.class, null);
			}
			if(totalChunkPart!=null) {
				totalChunk = totalChunkPart.getBody(Integer.class, null);
			}
			if(currentChunk == null || totalChunk == null) {
				isComplete = true;
			} else if(currentChunk == (totalChunk - 1)){
				isComplete = true;
			}
			if(md5Part!=null) {
				md5 = md5Part.getBody(String.class, null);
			}
			
			String	timeStamp = timeStampPart.getBody(String.class, null);
			String	sign = signPart.getBody(String.class, null);
			String	appKey = appKeyPart.getBody(String.class, null);
			Object verify = this.verifySign(sign, appKey, timeStamp);
	        if (verify != null) {
	            return verify;
	        }
			
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			/**获取文件名*/
			String fileName = getFileName(header);
			
			/**解析获取文件名和文件后缀*/
			String[] fileNames = fileName.split("\\.");
			String name = "";
			String suffix = "tmp";
			if(fileNames.length>0) {
				name = fileNames[0];
			}
			if(fileNames.length>1) {
				suffix = fileNames[1];
			}
			/**获取文件流*/
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			String contentType = "application/octet-stream";
			/**获取Content-Type*/
			List<String> contentTypes = header.get("Content-Type");
			if(contentTypes!=null && contentTypes.size()>0) {
				contentType = contentTypes.get(0);
			} 
			
			/**文件上传到ftp*/
			FileResult fileResult = null;
			if(md5==null || "".equals(md5) || "null".equalsIgnoreCase(md5)) {
				fileResult = fileService.upload(inputStream, suffix, contentType, name, String.valueOf(SysContants.SYSTEM_APP_ID));
			} else {
				fileResult = fileService.resumeUpload(inputStream, suffix,
						md5, contentType, name, String.valueOf(SysContants.SYSTEM_APP_ID), isComplete);
			}
			
			if(fileResult.getStatusCode().equals(StorageStatusCode.UPLOAD_SUCCESS)) {
				/**文件上传成功*/
				EntityFile entityFile = fileResult.getEntityFile();
				if(entityFile==null) {
					result.put("finishedStatus", "0");
					return new ResponseVo<Object>("0", result);
				}
				
				/**返回参数封装*/
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
				result.put("finishedStatus", "1");
				result.put("uuid", entityFile.getUuid());
				result.put("createdTime", dateFormat.format(entityFile.getCreateDate()));
				
				return new ResponseVo<Object>("0", result);
			} else {
				ResponseInfo info = new ResponseInfo();
		        info.setDetail("上传文件发生异常，请与系统管理员联系");
		        info.setMsg("未知错误");
		        return new ResponseError("000001", info);
			}
		} catch(NoContentException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
	        info.setDetail("参数异常，请检查您的参数类型和参数内容");
	        info.setMsg("未知错误");
	        return new ResponseError("000001", info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
	        info.setDetail("上传文件发生异常，请与系统管理员联系");
	        info.setMsg("上传文件发生异常，请与系统管理员联系");
	        return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object resourceUpdate(String resUuid, Integer userId, Integer resType,
			String title, String description,  String catalogCode, String fileUuid) {
		try {
			boolean tab = validateResType(resType);
			if(!tab) {
				ResponseInfo info = new ResponseInfo();
				info.setMsg("不支持的资源类型");
				info.setDetail("不支持的资源类型");
				info.setParam("resType");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			ResourceVo resource = resourceService.findSubFiledByResUuid(resUuid);
			if(resource==null) {
				ResponseInfo info = new ResponseInfo();
				info.setMsg("找不到数据");
				info.setDetail("找不到数据");
				info.setParam("resUuid");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			Integer ruserid = resource.getUserId();
			if(ruserid==null || ruserid-userId!=0) {
				ResponseInfo info = new ResponseInfo();
				info.setMsg("不允许修改不属于自己的资源");
				info.setDetail("不允许修改不属于自己的资源");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			/**校验目录是否存在*/
			ResTextbookCatalog catalog = null;
			ResTextbook textbook = null;
			if(catalogCode!=null && !"".equals(catalogCode)) {
				if(resource.getIsPersonal()) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("个人资源不允许挂载到目录");
					info.setDetail("个人资源不允许挂载到目录");
					info.setParam("catalogCode");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				catalog = resTextbookCatalogService.findResTextbookCatalogByCode(catalogCode);
				if(catalog==null) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("找不到数据");
					info.setDetail("找不到数据");
					info.setParam("catalogCode");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				/**检验目录相应的书箱是否存在*/
				textbook = resTextbookService.findResTextbookById(catalog.getTestBookId());
				if(textbook==null) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("参数内容错误");
					info.setDetail("参数内容错误");
					info.setParam("catalogCode");
					return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
				} 
			}
			
			/**检验文件是否存在*/
			FileResult fileResult = null;
			if(fileUuid!=null && !"".equals(fileUuid)) {
				fileResult = fileService.findFileByUUID(fileUuid);
				if(fileResult==null || fileResult.getEntityFile()==null) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("找不到数据");
					info.setDetail("找不到数据");
					info.setParam("fileUuid");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
			}
			/**更改标题*/
			if(title!=null && !"".equals(title)) {
				resource.setTitle(title);
			}
			/**更改详情*/
			if(description!=null && !"".equals(description)) {
				resource.setDescription(description);
			}
			/**新类型*/
			Integer newResType = resType;
			/**旧类型*/
			Integer oldResType = resource.getResType();
			/**资源类型实体的uuid*/
			String objectId = resource.getObjectId();
			/**资源id*/
			Integer resourceId = resource.getId();
			/**文件uuid*/
			String entityId = resourceService.getEntityIdByObjectIdAndType(objectId, oldResType);
			/**如果需要修改文件，则将entityId绑定到新文件*/
			if(fileResult!=null && fileResult.getEntityFile()!=null) {
				entityId = fileUuid;
			}
			
			/**删除原有的资源类型实体*/
			resourceService.removeEntityIdByObjectIdAndType(objectId, oldResType);
			
			resource.setResType(newResType);
			if(newResType-ResourceType.OTHER==0) {
				resource.setObjectId(entityId);
			} else {
				/**新增新的资源类型实体*/
				resourceService.insertEntity(resource, entityId);
			}
			
			if(catalog!=null) {
				/**查找用户所在学校*/
				SchoolUser user = schoolUserService.findSchoolUserByUserId(userId);
				Integer schoolId = 0;
				if(user!=null) {
					schoolId = user.getSchoolId();
				}
				
				/**将资源挂载到目录*/
				CatalogResource catalogResource = getCatalogResource(textbook, catalogCode, schoolId);
				catalogResource.setResourceType(newResType);
				catalogResource.setResourceId(resourceId);
				catalogResource.setObjectId(objectId);
				/**资源原来的目录*/
				CatalogResource real = catalogResourceService.findCatalogResourceByResourceId(resourceId);
				/**如果存在原来的目录，刚更新相应的信息*/
				if(real!=null) {
					catalogResource.setId(real.getId());
					catalogResource.setCreateDate(real.getCreateDate());
					catalogResourceService.modify(catalogResource);
				/**如果不存在相应的目录，则新增*/
				} else {
					catalogResourceService.add(catalogResource);
				}
			}
			resource.setModifyDate(new Date());
			resourceService.modify(resource);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
	        info.setDetail("修改资源信息接口调用异常，请与系统管理员联系");
	        info.setMsg("未知错误");
	        return new ResponseError("000001", info);
		}
		
		return new ResponseVo<Object>("0", new HashMap<Object, Object>());
	}
	
	@Override
	public Object fileIsExit(String md5) {
		try {
			FileResult result = this.fileService.preResumeUpload(md5, String.valueOf(SysContants.SYSTEM_APP_ID));
	    	EntityFile entityFile = result.getEntityFile();
	    	long size = result.getTempFileSize();
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	if (entityFile != null) {
	    		map.put("url", result.getHttpUrl());
	    		map.put("uuid", entityFile.getUuid());
	    		map.put("finishedStatus", "1");
	    		map.put("size", entityFile.getSize());
	    	} else if (size > 0){
	    		map.put("finishedStatus", "0");
	    		map.put("size", (int)result.getTempFileSize());
	    	} else {
	    		map.put("size", 0);
	    		map.put("finishedStatus", "-1");
	    	}
	    	return new ResponseVo<Object>("0", map);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
	        info.setDetail("文件是否存在接口调用异常，请与系统管理员联系");
	        info.setMsg("未知错误");
	        return new ResponseError("000001", info);
		}
	}
	
	
	private CatalogResource getCatalogResource(ResTextbook textbook, String catalogCode, Integer schoolId) {
		Date now = new Date();
		CatalogResource catalogResource = new CatalogResource();
		catalogResource.setAppId(1);
		catalogResource.setCatalogCode(catalogCode);
		catalogResource.setCreateDate(now);
		catalogResource.setModifyDate(now);
		
		String gradeCode = textbook.getGradeCode();
		String stageCode = textbook.getStageCode();
		String subjectCode = textbook.getSubjectCode();
		String versionCode = textbook.getVersion();
		String volumnCode = textbook.getVolumn();
		Grade grade = gradeService.findGradeByCode(gradeCode);
		Stage stage = stageService.findStageByCode(stageCode);
		Subject subject = subjectService.findUnique(schoolId, subjectCode);
		Integer versionId = versionCode!=null?Integer.parseInt(versionCode) : 0;
		TextbookVersion version = versionService.findTextbookVersionById(versionId);
		TextbookVolumn volumn = volumnService.findTextbookVolumnByCode(volumnCode);
		
		catalogResource.setGradeCode(gradeCode);
		catalogResource.setStageCode(stageCode);
		catalogResource.setSubjectCode(subjectCode);
		catalogResource.setVersionCode(versionCode);
		catalogResource.setVolumnCode(volumnCode);
		catalogResource.setGradeName(grade!=null?grade.getName():"");
		catalogResource.setStageName(stage!=null?stage.getName():"");
		catalogResource.setSubjectName(subject!=null?subject.getName():"");
		catalogResource.setVersionName(version!=null?version.getName():"");
		catalogResource.setVolumnName(volumn!=null?volumn.getName():"");
		
		return catalogResource;
	}
	
	private List<Object> getPersonalListResult(List<ResourceVo> resourceVoList) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<Object> result = new ArrayList<Object>(resourceVoList.size());
		for (ResourceVo resourceVo : resourceVoList) {
			String objectId = resourceVo.getObjectId();
			Integer type = resourceVo.getResType();
			
			Map<String, Object> map = new HashMap<String, Object>();
			String entityId = resourceService.getEntityIdByObjectIdAndType(objectId, type);
			String fileSuffix = resourceService.getFileSuffixByEntityId(entityId);
			String thumbnailUrl = fileService.thumb2HttpUrlByUUID(entityId);
			
			map.put("resUuid", resourceVo.getUuid());
			map.put("title", resourceVo.getTitle());
			map.put("resType", resourceVo.getResType());
			map.put("thumbnailUrl", thumbnailUrl);
			map.put("fileSuffix", fileSuffix);
			map.put("description", resourceVo.getDescription());
			map.put("createdTime", dataFormat.format(resourceVo.getCreateDate()));
			map.put("modifiedTime", dataFormat.format(resourceVo.getModifyDate()));
			
			result.add(map);
		}
		return result;
	}
	
	private List<Object> getResourceNeedListResult(List<ResourceVo> resourceVoList) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<Object> result = new ArrayList<Object>(resourceVoList.size());
		for (ResourceVo resourceVo : resourceVoList) {
			String objectId = resourceVo.getObjectId();
			Integer type = resourceVo.getResType();

			Map<String, Object> map = new HashMap<String, Object>();
			String entityId = resourceService.getEntityIdByObjectIdAndType(objectId, type);
			String fileSuffix = resourceService.getFileSuffixByEntityId(entityId);
			String thumbnailUrl = fileService.thumb2HttpUrlByUUID(entityId);

			map.put("resUuid", resourceVo.getUuid());
			map.put("title", resourceVo.getTitle());
			map.put("resType", resourceVo.getResType());
			
			Integer favCount = resourceVo.getFavCount();
			Integer likeCount = resourceVo.getLikeCount();
			Integer commentCount = resourceVo.getCommentCount();
			Integer clickCount = resourceVo.getClickCount();
			Integer downloadCount = resourceVo.getDownloadCount();
			
			map.put("favCount", favCount==null?0:favCount);
			map.put("likeCount", likeCount==null?0:likeCount);
			map.put("commentCount", commentCount==null?0:commentCount);
			map.put("clickCount", clickCount==null?0:clickCount);
			map.put("downloadCount", downloadCount==null?0:downloadCount);
			map.put("thumbnailUrl", thumbnailUrl);
			map.put("fileSuffix", fileSuffix);
			map.put("modifiedTime", dataFormat.format(resourceVo.getModifyDate()));
			map.put("description", resourceVo.getDescription());
			map.put("createdTime", dataFormat.format(resourceVo.getCreateDate()));

			result.add(map);
		}
		return result;
	}

	private Map<String, Object> getGetDetailResult(ResourceVo resourceVo, EntityFile entityFile, Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(entityFile==null) {
			return result;
		}
		String uuid = entityFile.getUuid();
		
		String pdfUrl = getPdfUrlByEntityUuid(uuid);
		String thumbnailUrl = fileService.thumb2HttpUrlByUUID(uuid);
		String fileUrl = fileService.relativePath2HttpUrlByUUID(uuid);
		
		result.put("fileId", entityFile.getId());
		result.put("uuid", uuid);
		result.put("fileName", entityFile.getFileName());
		result.put("extension", entityFile.getExtension());
		result.put("size", entityFile.getSize());
		result.put("thumbnailUrl", thumbnailUrl);
		result.put("fileUrl", fileUrl);
		result.put("pdfUrl", pdfUrl);
		result.put("fileUuid", entityFile.getUuid());
		result.put("resUuid", resourceVo.getUuid());
		result.put("description", resourceVo.getDescription());
		result.put("favCount", resourceVo.getFavCount());
		result.put("likeCount", resourceVo.getLikeCount());
		result.put("commentCount", resourceVo.getCommentCount());
		result.put("clickCount", resourceVo.getClickCount());
		result.put("downloadCount", resourceVo.getDownloadCount());
		Favorites favorites = favoritesService.findUnique(null, userId, resourceVo.getId());
		Likes likes = likesService.findUnique(null, userId, resourceVo.getObjectId());
		if(favorites!=null) {
			result.put("isFavorite", true);
		} else {
			result.put("isFavorite", false);
		}
		if(likes!=null) {
			result.put("isLike", true);
		} else {
			result.put("isLike", false);
		}
		return result;
	}

	private String getPdfUrlByEntityUuid(String uuid) {
		FileConversionResult fileConversionResult = conversionStatusService.getTargetFileBySourceUUid(uuid);
		String pdfUrl = "";
		String pdfUuid = null;
		String htmlUuid = null;
		Map<Integer,Map<Integer,String>> resultMap = fileConversionResult.getResultMap();
		if(resultMap != null){
			if(resultMap.containsKey(1)){
				Map<Integer,String> uuidMap = resultMap.get(1);
				if(!uuidMap.isEmpty() && uuidMap != null){
					if(resultMap.containsKey(1)) {
						pdfUuid = uuidMap.get(1);
						if(pdfUuid != null && !"".equals(pdfUuid)){
							FileResult file = fileService.findFileByUUID(pdfUuid);
							if(file != null ){
								pdfUrl = file.getHttpUrl();
							}
						}
					}
					if(resultMap.containsKey(1)) {
						htmlUuid = uuidMap.get(1);
						if(htmlUuid != null && !"".equals(htmlUuid)){
							FileResult file = fileService.findFileByUUID(htmlUuid);
							if(file != null ){
								pdfUrl = file.getHttpUrl();
							}
						}
					}
				}
			}
		}
		return pdfUrl;
	}

	private Map<String, Object> getListByKnowledgeResultVo(ResKnowledgeResourceVo resKnowledgeResourceVo) {
		String objectId = resKnowledgeResourceVo.getOwnerUuid();
		Integer type = resKnowledgeResourceVo.getOwnerType();
		
		Map<String, Object> map = new HashMap<String, Object>();
		String entityId = resourceService.getEntityIdByObjectIdAndType(objectId, type);
		String fileSuffix = resourceService.getFileSuffixByEntityId(entityId);
		
		map.put("resourceId", resKnowledgeResourceVo.getId());
		map.put("title", resKnowledgeResourceVo.getKnowledgeName());
		map.put("resType", resKnowledgeResourceVo.getOwnerType());
		map.put("objectId", entityId);
		map.put("fileSuffix", fileSuffix);
		return map;
	}

	private Map<String, Object> getListByCatalogResultVo(ResourceVo resourceVo) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String objectId = resourceVo.getObjectId();
		Integer type = resourceVo.getResType();
		
		Map<String, Object> map = new HashMap<String, Object>();
		String entityId = resourceService.getEntityIdByObjectIdAndType(objectId, type);
		String fileSuffix = resourceService.getFileSuffixByEntityId(entityId);
		String thumbnailUrl = fileService.thumb2HttpUrlByUUID(entityId);
		
		map.put("resUuid", resourceVo.getUuid());
		map.put("title", resourceVo.getTitle());
		map.put("resType", resourceVo.getResType());
		map.put("favCount", resourceVo.getFavCount());
		map.put("likeCount", resourceVo.getLikeCount());
		map.put("commentCount", resourceVo.getCommentCount());
		map.put("thumbnailUrl", thumbnailUrl);
		map.put("fileSuffix", fileSuffix);
		map.put("modifiedTime", dataFormat.format(resourceVo.getModifyDate()));
		map.put("clickCount", resourceVo.getClickCount());
		map.put("downloadCount", resourceVo.getDownloadCount());
		map.put("description", resourceVo.getDescription());
		map.put("createdTime", dataFormat.format(resourceVo.getCreateDate()));
		
		return map;
	}
	
	private Page getPage(Integer pageSize, Integer pageNumber) {
		Page page = new Page();
		if(pageSize!=null) {
			page = new Page();
			page.setPageSize(pageSize);
		}
		if(pageNumber!=null) {
			page.setCurrentPage(pageNumber);
		}
		return page;
	}
	
	private Order getOrder(String sortItem, Integer sortType) {
		if(sortItem==null) {
			return null;
		}
		boolean validate = false;
		if("fav_count".equals(sortItem)) {
			validate = true;
		} else if("like_count".equals(sortItem)) {
			validate = true;
		} else if("comment_count".equals(sortItem)) {
			validate = true;
		} else if("click_count".equals(sortItem)) {
			validate = true;
		} else if("download_count".equals(sortItem)) {
			validate = true;
		} else if("create_date".equals(sortItem)) {
			validate = true;
		} else if("modify_date".equals(sortItem)) {
			validate = true;
		}
		if(!validate) {
			return null;
		}
		Order order = new Order(sortItem);
		if(sortType-1==0) {
			order.setAscending(false);
		} else {
			order.setAscending(true);
		}
		return order;
	}

    private String getFileName(MultivaluedMap<String, String> header) throws UnsupportedEncodingException {  
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");  
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {  
                String[] name = filename.split("=");  
                String finalFileName = name[1].trim().replaceAll("\"", "");  
                return finalFileName;  
            }  
        }  
        return "unknown";  
    } 
    
    private Object verifySign(String sign, String appKey, String timeStamp){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //1.判断Sign、AppKey是否存在
            if (sign == null || "".equals(sign)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("缺少Sign参数");
                info.setParam("Sign");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null || "".equals(appKey)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("缺少AppKey参数");
                info.setParam("AppKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }

            //2.验证AppKey是否正确
            if (!this.appValidator.validateAppKey(appKey)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("AppKey值无效");
                info.setParam("AppKey");
                return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
            }

            //3.通过AppKey获取AppSecret
            String appSecret = this.appValidator.getSercet(appKey);

            //4.用AppKey + AppSecret + TimeStamp生成MD5签名
            String encoder = encoderUtil.encoder(appKey + appSecret + timeStamp);

            //5.生成的签名和sign进行比较
            if (!encoder.equals(sign)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("Sign解析失败");
                info.setParam("Sign");
                return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
            }

            //6.获取服务器时间，判断时间是否超时
            Date time = null;
            if (timeStamp.contains(":") && timeStamp.contains("-")) {
                time = sdf.parse(timeStamp);
            } else {
                time = new Date(Long.parseLong(timeStamp));
            }
            Date now = new Date();
            
            if (Math.abs(now.getTime() - time.getTime()) > SysContants.REQUEST_ERROR_TIME) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("操作失败");
                info.setDetail("请求时间已超时");
                info.setParam("Timestamp");
                return new ResponseError(CommonCode.S$TIME_OUT, info);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数格式错误");
            info.setDetail("Timestamp的格式不正确，不符合{yyyy-MM-dd HH:mm:ss}标准");
            info.setParam("Timestamp");
            return new ResponseError(CommonCode.D$DATA_NOT_EXIST, info);
        }
        return null;
    }
    
    private boolean validateResType(Integer resType) {
    	Integer[] resTypeArray = new Integer[]{1,2,3,4,5,6,11,99};
    	for (int i = 0; i < resTypeArray.length; i++) {
    		Integer tmp = resTypeArray[i];
    		if(tmp-resType==0) {
    			return true;
    		}
		}
    	return false;
    }

}
