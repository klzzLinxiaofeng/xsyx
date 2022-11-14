<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sfm" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" uri="http://www.jiaoxueyun.com/showTime" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="acl" uri="http://www.jiaoxueyun.com/szxy/functions" %>

<%@taglib prefix="jc" uri="http://www.jiaoxueyun.com/cache/jc" %>
<%@taglib prefix="jcFn" uri="http://www.jiaoxueyun.com/cache/jc/functions" %>

<%@taglib prefix="jcgc" uri="http://www.jiaoxueyun.com/cache/jcgc"%>
<%@taglib prefix="jcgcFn" uri="http://www.jiaoxueyun.com/cache/jcgc/functions"%>

<%@taglib prefix="dota" uri="http://www.jiaoxueyun.com/cache/dota"%>
<%@taglib prefix="dotaFn" uri="http://www.jiaoxueyun.com/cache/dota/functions"%>

<%@taglib prefix="dou" uri="http://www.jiaoxueyun.com/cache/dou"%>
<%@taglib prefix="douFn" uri="http://www.jiaoxueyun.com/cache/dou/functions"%>

<%@taglib prefix="sca" uri="http://www.jiaoxueyun.com/szxy/contants" %>
<%@taglib prefix="avatar" uri="http://www.jiaoxueyun.com/avatar"%>

<%@taglib prefix="tbFn" uri="http://www.jiaoxueyun.com/resource/textbook/functions"%>

<%@taglib prefix="iconFn" uri="http://www.jiaoxueyun.com/resource/icon/functions"%>

<%@taglib prefix="thumbFn" uri="http://www.jiaoxueyun.com/resource/thumbnail/functions"%>

<%@taglib prefix="eurlFn" uri="http://www.jiaoxueyun.com/resource/entity/url/functions"%>

<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath %>"/>
<c:set scope="request" var="ctp" value="${pageContext.request.contextPath}"></c:set>

