package platform.education.rest.common.filter;

/*import com.alibaba.dubbo.rpc.*;*/
/**
 * @功能描述: 数字签名过滤器
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年11月17日上午11:21:10
 */

public class OpenApiFilter /*implements Filter*/{
/*
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private OAuthService oAuthService;

	private AppValidator appValidator;

	public void setoAuthService(OAuthService oAuthService) {
		this.oAuthService = oAuthService;
	}

	public void setAppValidator(AppValidator appValidator) {
		this.appValidator = appValidator;
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		try {
			Method method = invoker.getInterface().getDeclaredMethod(invocation.getMethodName(), invocation.getParameterTypes());
			OpenApi clazz = method.getAnnotation(OpenApi.class);
			
			if(clazz==null) {
				return invoker.invoke(invocation);
			}
			
			//默认不加密
			if(clazz.value()) {
				//进入加密算法
				//由于获取不到HttpServletRequest对象 只能采用这种烂方法了。
				//通过拆字符串烂方法  数字签名要放到最后一位参数，时间戳放到倒数第二位参数，不然会爆炸的
				HttpServletRequest request = RpcContext.getContext().getRequest(HttpServletRequest.class);
				String appKey = request.getParameter("AppKey");
				if (appKey == null || "".equals(appKey)) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("参数错误");
					info.setDetail("缺少AppKey参数");
					info.setParam("AppKey");
					return getResult("缺少AppKey参数", "AppKey", CommonCode.$REQUIRED_PARAMETER_IS_NULL);
				}

				System.err.println("token-------------------------------------------->"+clazz.token());
				if (TokenType.OAUTH_TOKEN.equals(clazz.token())) {
					
					String token = request.getParameter("Token");
					if (token == null || "".equals(token)) {
						ResponseInfo info = new ResponseInfo();
						info.setMsg("参数错误");
						info.setDetail("缺少Token参数");
						info.setParam("Token");
						return getResult("缺少Token参数", "Token", CommonCode.$REQUIRED_PARAMETER_IS_NULL);
					}

					if (!oAuthService.checkAccessToken(token)) {
						ResponseInfo info = new ResponseInfo();
						info.setMsg("参数错误");
						info.setDetail("Token值无效");
						info.setParam("Token");
						return getResult("Token值无效", "Token", CommonCode.$REQUIRED_PARAMETER_IS_NULL);
					}
				}

				if (TokenType.SINGLE_SIGN_ON_TOKEN.equals(clazz.token())) {
					String token = request.getParameter("Token");
					if (token == null || "".equals(token)) {
						ResponseInfo info = new ResponseInfo();
						info.setMsg("参数错误");
						info.setDetail("缺少Token参数");
						info.setParam("Token");
						return getResult("缺少Token参数", "Token", CommonCode.$REQUIRED_PARAMETER_IS_NULL);
					}

					if (!oAuthService.checkAccessToken(token) && !oAuthService.checkSingleSignOnAccessToken(token)) {
						ResponseInfo info = new ResponseInfo();
						info.setMsg("参数错误");
						info.setDetail("Token值无效");
						info.setParam("Token");
						return getResult("Token值无效", "Token", CommonCode.$REQUIRED_PARAMETER_IS_NULL);
					}
				}


//				AppEdition app = this.appEditionService.findByAppKey(appKey);
				//要处理
				if (!this.appValidator.validateAppKey(appKey)) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("参数错误");
					info.setDetail("AppKey值无效");
					info.setParam("AppKey");
					return getResult("AppKey值无效", "AppKey", CommonCode.$PARAMETER_VALUE_CONTENT_ERROR);
				}

				String timestamp = request.getParameter("Timestamp");
				if (timestamp == null || "".equals(timestamp)) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("缺少Timestamp参数");
					info.setMsg("参数错误");
					info.setParam("Timestamp");
					return getResult("缺少Timestamp参数", "Timestamp", CommonCode.D$DATA_NOT_FOUND);
				}

				String signature = request.getParameter("Sign");
				if (signature == null || "".equals(signature)) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("缺少Sign参数");
					info.setMsg("参数错误");
					info.setParam("Sign");
					return getResult("缺少Sign参数", "Sign", CommonCode.D$DATA_NOT_FOUND);
				}

				//3.通过AppKey获取AppSecret
				String appSecret = this.appValidator.getSercet(appKey);
				//4.用AppKey + AppSecret + TimeStamp生成MD5签名
				String encoder = encoderUtil.encoder(appKey + appSecret + timestamp);

				//5.生成的签名和sign进行比较
				if (!encoder.equals(signature)) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("参数错误");
					info.setDetail("Sign解析失败");
					info.setParam("Sign");
					return getResult("Sign解析失败", "Sign", CommonCode.$FAILED_TO_INTERPRET_PARAMETERS);
				}

				String regexPattern = "^[0-9]*$";
				Pattern pattern = Pattern.compile(regexPattern);
				Matcher matcher = pattern.matcher(timestamp);
				long time = 0l;
				if (matcher.matches()) {
					time = Long.parseLong(timestamp);
				} else {
					Date nowDate = sdf.parse(timestamp);
					time = nowDate.getTime();
				}
				//6.获取服务器时间，判断时间是否超时
				if (Math.abs(System.currentTimeMillis() - time) > SysContants.REQUEST_ERROR_TIME) {
					ResponseInfo info = new ResponseInfo();
					info.setMsg("操作失败");
					info.setDetail("请求时间已超时");
					info.setParam("Timestamp");
					return getResult("请求时间已超时", "Timestamp", CommonCode.S$TIME_OUT);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数格式错误");
			info.setDetail("Timestamp的格式不正确");
			info.setParam("Timestamp");
			return getResult("Timestamp的格式不正确", "Timestamp", CommonCode.S$INVALID_DATA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//跳过，继续执行下面的逻辑
		return invoker.invoke(invocation);  
	}


	//统一返回异常信息
	private RpcResult getResult(String paramName,String param,String code) {
		ResponseError responseError = new ResponseError();
		ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setDetail(paramName);
		responseInfo.setMsg(paramName);
		responseInfo.setParam(param);
		responseError.setResult(code);
		responseError.setInfo(responseInfo);
		RpcResult result = new RpcResult();
		result.setValue(responseError);

		return result;
	}


	public static void main(String[] args) throws ParseException {
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		System.out.println(encoderUtil.encoder("mqt" + "educloud" + timestamp));
//		2016-06-02 12:20:40


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = sdf.parse("2016-06-02 12:20:40");
		System.out.println(date.getTime());

//		System.out.println(encoderUtil.encoder("mqt" + "educloud" + "2018-01-31 21:16:40"));
//		String regexPattern = "^[0-9]*$";
//		Pattern pattern = Pattern.compile(regexPattern);
//		Matcher matcher = pattern.matcher("15173982631561xx");
//		System.out.println(matcher.matches());

	}*/

}
