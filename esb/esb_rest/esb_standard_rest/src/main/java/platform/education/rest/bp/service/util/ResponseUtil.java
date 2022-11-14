package platform.education.rest.bp.service.util;

import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.constants.CommonCode;

/**
 * 响应工具
 * @author ken
 *
 */
public class ResponseUtil {

	/**
	 * 获取参数错误或格式错误的响应结果
	 * @param property 响应的错误信息
	 * @return 
	 */
	public static ResponseError paramterError(String property) {
		ResponseInfo info = new ResponseInfo();
		info.setDetail(property + "参数错误");
		info.setMsg(property + "参数为空或格式不正确");
		info.setParam(property);
		return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
	}
	
	/**
	 * 获取参数为空的响应结果
	 * @param property 响应错误的字段
	 * @return
	 */
	public static ResponseError paramerIsNull(String property) {
		ResponseInfo info = new ResponseInfo();
		info.setDetail(property+"参数必填");
		info.setMsg(property + "参数不能为空");
		info.setParam(property);
		return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
	}
	
	/**
	 * 获取数据不存在的响应结果
	 * @param property 数据不存在的响应信息
	 * @return
	 */
	public static ResponseError dataNotExist(String property) {
		ResponseInfo info = new ResponseInfo();
		info.setDetail(property + "数据不存在");
		info.setMsg(property + "数据不存在");
		info.setParam(property);
		return new ResponseError(CommonCode.D$DATA_NOT_EXIST, info);
	}
}
