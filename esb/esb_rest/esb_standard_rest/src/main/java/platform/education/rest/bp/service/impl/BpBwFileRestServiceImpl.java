package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.BpBwFile;
import platform.education.clazz.service.BpBwFileService;
import platform.education.clazz.vo.BpBwFileCondition;
import platform.education.clazz.vo.BpBwFileVo;
import platform.education.rest.bp.service.BpBwFileRestService;
import platform.education.rest.bp.service.vo.ResponseOrder;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Ken
 * @date 2017年4月28日 上午10:53:17
 */
public class BpBwFileRestServiceImpl implements BpBwFileRestService {

	@Autowired
	@Qualifier("bpBwFileService")
	private BpBwFileService bpBwFileService;
	
	@Override
	public Object getBpBwFileList(int id, String name, int teamId, int objectType, Integer searchId,
			Integer searchType, int pageNumber, int pageSize,
			String sortItem, boolean ascending) {
		if(searchType == null || "".equals(searchType)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("searchType参数必填");
			info.setMsg("searchType参数不能为空");
			info.setParam("searchType");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		BpBwFileCondition bwFileCondition = new BpBwFileCondition();
		bwFileCondition.setIsDeleted(false);
		bwFileCondition.setObjectType(objectType);
		if ( id != 0 ) {
			bwFileCondition.setId(id);
		}
		if ( name != null && name.length() > 0 ) {
			bwFileCondition.setName(name);
		}
		bwFileCondition.setTeamId(teamId);
		Page page = new Page();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		Order order = new Order(sortItem, ascending);
		
		if(searchType.equals(0)){ // 第一次查询
			page.setPageSize(pageSize);// pageSize
		}else if(searchType.equals(1)){ // 下拉查询
			bwFileCondition.setSearchId(searchId);
			bwFileCondition.setSearchType(searchType);
		}else if(searchType.equals(2)){ // 上拉查询
			bwFileCondition.setSearchId(searchId);
			bwFileCondition.setSearchType(searchType);
			page.setPageSize(pageSize);// pageSize
		}
		List<BpBwFileVo> bfList = bpBwFileService.findBwFileByCondition(bwFileCondition, page, order);
		long recordCount = bpBwFileService.count(bwFileCondition);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ResponseOrder result = new ResponseOrder("0", bfList, (int)recordCount);
		// new ResponseVo<Object>("0", result);
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object findAlbumsList(int teamId, Integer searchId,
			Integer searchType, int pageNumber, int pageSize,
			String sortItem, boolean ascending) {
		if(searchType == null || "".equals(searchType)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("searchType参数必填");
			info.setMsg("searchType参数不能为空");
			info.setParam("searchType");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		if ( teamId < 1 ) {
			return paramterError("fileId:"+teamId);
		}
		Page page = new Page();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		Order order = new Order(sortItem, ascending);
		List<BpBwFileVo> result = bpBwFileService.findAlbumsList(teamId, searchId, searchType, page, order);
		return new ResponseOrder("0", result, page.getTotalRows());
	}
	
	@Override
	public Object addBpBwFile(String name, int teamId, int objectType, String fileId, int postUserId) throws IllegalAccessException, InvocationTargetException {
		ResponseError errorResult = null;
		if ( fileId == null || fileId.length() == 0 ) {
			errorResult = paramterError("fileId");	
		}
		if ( objectType != 1 && objectType != 2 && objectType != 3 ) {
			errorResult = paramterError("objectType");
		}
		if ( postUserId == 0 ) {
			errorResult = paramterError("postUserId");
		}
		if ( errorResult != null ) {
			return errorResult;
		}
		BpBwFile bf = new BpBwFile();
		bf.setName(name);
		bf.setTeamId(teamId);
		bf.setObjectType(objectType);
		bf.setFileId(fileId);
		bf.setPostUserId(postUserId);
		bf = bpBwFileService.add(bf);
		return new ResponseVo<Integer>("0", bf.getId());
	}

	@Override
	public Object modifyBpBwFile(int id, String name, boolean isDelete) {
		ResponseError errorResult = null;
		if ( id < 1 ) {
			errorResult = paramterError("id");
		}
		BpBwFile bf = bpBwFileService.findBwFileById(id);
		if ( bf == null || bf.getIsDeleted() ) {
			errorResult = dataNotExist("id:"+ id);
		}
		if ( errorResult != null ) {
			return errorResult;
		}
		bf.setName(name);
		bf.setIsDeleted(isDelete);
		bf = bpBwFileService.modify(bf);
		return new ResponseVo<Boolean>("0", true);
	}
	
	private ResponseError paramterError(String property) {
		ResponseInfo info = new ResponseInfo();
		info.setDetail(property + "参数错误");
		info.setMsg(property + "参数为空或格式不正确");
		info.setParam(property);
		return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
	}
	
	private ResponseError dataNotExist(String property) {
		ResponseInfo info = new ResponseInfo();
		info.setDetail(property + "数据不存在");
		info.setMsg(property + "数据不存在");
		info.setParam(property);
		return new ResponseError(CommonCode.D$DATA_NOT_EXIST, info);
	}

	

	



}
