package platform.szxyzxx.web.common.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import framework.generic.dao.Page;
/**
 * <p>Title:EasyUIPageMethodArgumentResolver.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：esayUi分页组件解析器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class EasyUIPageMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public EasyUIPageMethodArgumentResolver() {

	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(EasyUIPage.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, 
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//		EasyUIPage easyUIPageAnnotation = parameter.getParameterAnnotation(EasyUIPage.class);
		String currentPage = webRequest.getParameter("page");
		String pageSize = webRequest.getParameter("rows");
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setPageSize(Integer.parseInt(pageSize));
		return page;
	}
}
