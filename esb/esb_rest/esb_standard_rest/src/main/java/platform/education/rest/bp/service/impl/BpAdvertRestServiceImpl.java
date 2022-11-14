package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.service.AdvertService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.vo.AdvertCondition;
import platform.education.clazz.vo.AdvertVo;
import platform.education.rest.bp.service.BpAdvertRestService;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;

import java.util.List;

public class BpAdvertRestServiceImpl implements BpAdvertRestService {

	@Autowired
	@Qualifier("advertService")
	private AdvertService advertService;
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Override
	public Object getAdvertList(Integer roomId, Integer pageNumber, Integer pageSize, String sortItem,
			boolean ascending, String appKey, String signage) {
		try {
			if ( roomId == null || "".equals(roomId) ) {
				return ResponseUtil.paramerIsNull("roomId:"+roomId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			Page page = new Page();
			page.setCurrentPage(pageNumber);
			page.setPageSize(pageSize);
			Order order = new Order(sortItem, ascending);
			
			AdvertCondition condition = new AdvertCondition();
			condition.setRoomId(roomId);
			condition.setIsDeleted(false);
			List<AdvertVo> advertList = advertService.findAdvertByCondition(condition, page, order);
			
			return new ResponseVo<List<AdvertVo>>("0", advertList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
		
	}

}
