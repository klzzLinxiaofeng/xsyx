package platform.education.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import platform.education.util.TokenUtils;


/**
 * 系统日志，切面处理类
 * @author Dell
 *
 */
@Aspect
@Component
public class SysLogAspect {
//	@Resource
//	private FrameworkLogServic sysLogService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Pointcut("@annotation(platform.education.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
//		saveSysLog(point, time);
		return result;
	}

//	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		FrameworkLogEntit sysLog = new FrameworkLogEntit();
//		SysLog syslog = method.getAnnotation(SysLog.class);
//		if(syslog != null){
//			//注解上的描述
//			sysLog.setOperation(syslog.value());
//		}
//		//请求的方法名
//		String className = joinPoint.getTarget().getClass().getName();
//		String methodName = signature.getName();
//		sysLog.setMethod(className + "." + methodName + "()");
//		//请求的参数
//		Object[] args = joinPoint.getArgs();
//		try{
//			String params = new Gson().toJson(args);
//			sysLog.setParams(params);
//		}catch (Exception e){
//
//		}
//		//获取request
//		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//		//设置IP地址
//		sysLog.setIp(IPUtils.getIpAddr(request));
//		//用户名
//		String username = tokenUtils.getOnlineUser().getUserAccount();
//		sysLog.setUsername(username);
//		sysLog.setTime(time+"");
//		sysLog.setCreateTime(new Date());
		//保存系统日志
//		sysLogService.save(sysLog);
//	}
}
