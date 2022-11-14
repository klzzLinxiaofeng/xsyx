package platform.education.rest.common.filter;

import platform.education.user.service.AppEditionService;

/**
 * @功能描述: 数字签名过滤器
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年11月17日上午11:21:10
 */

public class MqtApiFilter /*implements Filter*/{

	private AppEditionService appEditionService;
	public void setAppEditionService(AppEditionService appEditionService) {
		this.appEditionService = appEditionService;
	}
/*
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			//获取被调用的接口
			
			Method method = invoker.getInterface().getDeclaredMethod(invocation.getMethodName(), invocation.getParameterTypes());
			
			MqtApi clazz = method.getAnnotation(MqtApi.class);
			if(clazz != null) {
				
				//默认不加密
				if(!clazz.value()) {
					//进入加密算法
					//由于获取不到HttpServletRequest对象 只能采用这种烂方法了。
					//通过拆字符串烂方法  数字签名要放到最后一位参数，时间戳放到倒数第二位参数，不然会爆炸的
					HttpServletRequest request = RpcContext.getContext().getRequest(HttpServletRequest.class);
					String appKey = request.getParameter("appKey") ;
					if (appKey == null || "".equals(appKey)) {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("appKey参数必填");
						info.setMsg("appKey参数不能为空");
						info.setParam("appKey");
						return getResult("appkey不能为空,请确认","appKey", CommonCode.$REQUIRED_PARAMETER_IS_NULL);
					}
					
					AppEdition app = this.appEditionService.findByAppKey(appKey);
					if(app == null){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("appkey不存在,请确认");
						info.setMsg("不存在该appKey");
						info.setParam("appKey");
						return getResult("appkey不存在,请确认","appKey", CommonCode.D$DATA_NOT_FOUND);
					}
					
					String timestamp = request.getParameter("timestamp") ;
					if(timestamp == null || "".equals(timestamp)){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("timestamp不正确,请确认");
						info.setMsg("timestamp不正确,请确认");
						info.setParam("timestamp");
						return getResult("timestamp不正确,请确认","timestamp", CommonCode.D$DATA_NOT_FOUND);
					}
					
					String signature = request.getParameter("signature") ;
					if(signature == null || "".equals(signature)){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("signature不正确,请确认");
						info.setMsg("signature不正确,请确认");
						info.setParam("signature");
						return getResult("signature不正确,请确认","signature", CommonCode.D$DATA_NOT_FOUND);
					}
					
					//参数集合
					Object[] object = invocation.getArguments();
					List<Integer> list = new ArrayList<Integer>();
					if(object != null && object.length > 0) {
						Annotation[][] parameterAnnotations  = method.getParameterAnnotations();
						for (int i = 0; i < parameterAnnotations.length; i++) {
							boolean isDefaultValue = false;
							*//**第一个参数的所有注解集合*//*
							Annotation[] annotations = parameterAnnotations[i];
							*//**如果参数没有注解，跳过该次循环*//*
							if(annotations.length==0) {
								continue;
							}
							
							for (int j = 0; j < annotations.length; j++) {
								
								Annotation annotation = annotations[j];
								*//**参数名称注解*//*
								if(annotation instanceof DefaultValue){
									isDefaultValue = true;
								}
								if(isDefaultValue) {
									if(annotation instanceof FormParam){
										FormParam formParam = (FormParam) annotation;
										//formParam.
										if(request.getParameter(formParam.value()) == null){
											list.add(i);
										}
										
									}
								}
							}
						}
						
						if(list != null && list.size() > 0) {
							object = removeByIndex(object,list);
						}
						
						boolean isEx = false;
						List<String> pamas = new ArrayList<String>();
						//为了兼容以前没有APPKEY的接口加入的一套逻辑
						for(int z=0;z<object.length;z++) {
							if(object[z] != null ) {
								if(object[z].toString().contains(("maiqituo#"))) {
									isEx = true;
								}
								pamas.add(object[z]+""); 
							}
						}
						if(!isEx) {
							pamas.add(appKey);
						}
					//pamas.add(appKey);
						pamas.add(timestamp);
						if(pamas != null && pamas.size() > 0) {
							Integer flag = ClientCallbackApi.isLegitimacyApi(pamas.toArray(new String[pamas.size()]),timestamp,signature);
							if(flag == 1) {
								return invoker.invoke(invocation);
							}else if(flag == -1) {
								return getResult("已超过接口允许调用有效期，再调我就报警","timestamp", CommonCode.$EXPIRED_DATE);
							}else if(flag == 0) {
								return getResult("签名不对，接口非法调用，再调我就报警","signature", CommonCode.$SIGNATURE_IS_NOT_CORRECT);
							}
						}
					}else {
						return getResult("接口参数不完整","pamas", CommonCode.$INCOMPLETE_PARAMETER);
					}
				}
			}
					
		
		} catch (Exception e) {
			e.printStackTrace();
			return getResult("服务器异常","server", CommonCode.S$SERVER_BUSY);
		}
		
		//跳过，继续执行下面的逻辑
		return invoker.invoke(invocation);  
	}
	
	
	public Object[] removeByIndex(Object[] oldArr,List<Integer> indexList) {
		// new 一个 List
		List<Object> list = new ArrayList<Object>();
		// 循环迭代，把数组放进List里面
		for (Object i : oldArr) {
			list.add(i);
		}
		
		for(int i=indexList.size()-1;i>=0;i--){ //倒序  
            if(i<=list.size()){  
                System.out.println("删除了第  "+(indexList.get(i)+1)+" 个元素");  
                list.remove(indexList.get(i).intValue());  
            }   
        }  
		//list.removeAll(indexList);
		// 根据索引删除
	//	list.remove(index);
		// 重新new 一个数组
		Object[] newArr = new Object[list.size()];
		// 把List里面的集合全部添加到数组里面
		for (int i = 0; i < list.size(); i++) {
			newArr[i] = list.get(i);
		}
		return newArr;
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
		
		return result;*/
	//}

}
