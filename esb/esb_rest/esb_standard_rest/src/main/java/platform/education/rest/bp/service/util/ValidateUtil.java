package platform.education.rest.bp.service.util;

import platform.education.clazz.model.BpBwSignage;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;

/**
 * 校验参数工具
 * @author ken
 *
 */
public class ValidateUtil {

	/**
	 * 检查appKey和signage
	 * @param appKey
	 * @param signage 不存在,则传入null
	 * @return 检查正常返回null
	 */
	public static Object checkAppKeyAndSignae(String appKey, String signage, AppEditionService appEditionService, BpBwSignageService bpBwSignageService) {
		Object result = null;
		if ( BpCommonConstants.APP_KEY_SIGNAGE.equals(appKey) ) {
			result = checkAppKeyBySignage(appKey, signage, appEditionService, bpBwSignageService);
		} else if (BpCommonConstants.APP_KEY_MOBILE.equals(appKey)) {
			result = checkAppKeyByMobile(appKey, appEditionService);
		}else{
			result = appKeyIsWrong();
		}
		return result;
	}

	/**
	 * 检查展示端signageAppKey和signage
	 * @param signageAppKey
	 * @param signage
	 * @param bpBwSignageService
	 * @param appEditionService
	 * @return 检查通过返回null
	 */
	private static Object checkAppKeyBySignage(String signageAppKey, String signage, AppEditionService appEditionService, BpBwSignageService bpBwSignageService) {
		// 判断是否为班牌展示端设备appKey
		if ( !BpCommonConstants.APP_KEY_SIGNAGE.equals(signageAppKey) ) {
			return appKeyIsWrong();
		}
		Object checkAppKeyResult = checkAppKey(signageAppKey, appEditionService);
		if ( checkAppKeyResult != null ) {
			return checkAppKeyResult;
		}
		// 检查设备id是否正确,正确返回null
	/*	if(StringUtils.isEmpty(signage)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("signage参数不能为空");
			info.setMsg("signage为必填参数");
			info.setParam("signage");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
		}*/
		BpBwSignage Signage = bpBwSignageService.findBwSignageByName(signage);
		if(Signage == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("signage不存在,请确认");
			info.setMsg("不存在该signage");
			info.setParam("signage");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		return null;
	}

	/**
	 * 检查手机端appKey
	 * @param appKey
	 * @param appEditionService
	 * @return 检查通过返回null
	 */
	private static Object checkAppKeyByMobile(String appKey, AppEditionService appEditionService) {
		if (!BpCommonConstants.APP_KEY_MOBILE.equals(appKey)) {
			return appKeyIsWrong();
		}
		return checkAppKey(appKey, appEditionService);
	}

	/**
	 * 检查appKey是否正确
	 * @param appKey
	 * @param appEditionService
	 * @return 正确返回null
	 */
	private static Object checkAppKey(String appKey, AppEditionService appEditionService) {
		if(appKey == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数不能为空");
			info.setMsg("appKey为必填参数");
			info.setParam("appKey");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
		}
		AppEdition app = appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		return null;
	}

	private static Object appKeyIsWrong() {
		ResponseInfo info = new ResponseInfo();
		info.setDetail("appKey参数有误");
		info.setMsg("appKey参数有误");
		info.setParam("appKey");
		return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR,info);
	}

}
