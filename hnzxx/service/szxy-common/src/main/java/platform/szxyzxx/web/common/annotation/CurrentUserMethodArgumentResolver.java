package platform.szxyzxx.web.common.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import platform.szxyzxx.web.common.util.SessionManager;
/**
 * <p>Title:CurrentUserMethodArgumentResolver.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：获取当前用户信息注解解析器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() {

	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, 
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
		Object obj = null;
		if(currentUserAnnotation.sessionId()) {
			String jsessionId = webRequest.getParameter("jsessionId");
			if(jsessionId != null) {
				obj = SessionManager.getUserInfoShiro(jsessionId);
			}
		} else {
			obj = SessionManager.getAttribute(currentUserAnnotation.value()); 
		}
		return obj;
	}
}
