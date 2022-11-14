package platform.education.rest.common.filter;

/*import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;*/


public class ParamFilter /*implements Filter*/{
/*
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			*//**参数的值的集合*//*
			Object[] object = invocation.getArguments();
			*//**获取被调用的方法*//*
			Method method = invoker.getInterface().getDeclaredMethod(invocation.getMethodName(), invocation.getParameterTypes());
			*//**获取方法参数的注解集合， 因为存在多个参数和一个参数有多个注解。所以集合是一个二维数组*//*
			Annotation[][] parameterAnnotations  = method.getParameterAnnotations();
			
			for (int i = 0; i < parameterAnnotations.length; i++) {
				*//**第一个参数的所有注解集合*//*
				Annotation[] annotations = parameterAnnotations[i];
				*//**如果参数没有注解，跳过该次循环*//*
				if(annotations.length==0) {
					continue;
				}
				*//**参数名称*//*
				String paramName = "";
				*//**参数是否是必填*//*
				boolean require = false;
				
				for (int j = 0; j < annotations.length; j++) {
					Annotation annotation = annotations[j];
					*//**必填参数注解*//*
					if(annotation instanceof RequireParam){
						RequireParam requireParam = (RequireParam) annotation;
						require = requireParam.value();
					}
					*//**参数名称注解*//*
					if(annotation instanceof FormParam){
						FormParam formParam = (FormParam) annotation;
						paramName = formParam.value();
					}
				}
				*//**输出参数名对应的参数值*//*
				System.err.println(paramName + ": " + object[i]);
				
				*//**如果是AppKey, 则进行appKey的检验*//*
				*//**if("appKey".equals(paramName)) {
					RpcResult result = validateAppKey(object[i]);
					if(result!=null) {
						return result;
					}
					System.out.println("appKey检验通过!");
				}*//*
				*//**如果参数为必填且参数为空，直接返回提示信息*//*
				if(require && (object[i]==null || "".equals(object[i].toString()))) {
					return getResult(paramName);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		*//**filter继续走*//*
		return invoker.invoke(invocation);  
	}
	
	*//**参数为空时的返回值*//*
	private RpcResult getResult(String paramName) {
		ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setDetail(paramName + "参数必填");
		responseInfo.setMsg(paramName + "参数为空");
		responseInfo.setParam(paramName);
		ResponseError error = new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, responseInfo);
		
		RpcResult result = new RpcResult();
		result.setValue(error);
		
		return result;
	}
	
	*//**@Autowired
    @Qualifier("appEditionService")
	public AppEditionService appEditionService;*//*
	
	*//**检验appKey*//*
	*//**private RpcResult validateAppKey(Object appKey) {
		AppEdition app = this.appEditionService.findByAppKey(appKey.toString());
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			
			RpcResult result = new RpcResult();
			result.setValue(info);
		
			return result;
		} else {
			return null;
		}
	}*/
	
}
