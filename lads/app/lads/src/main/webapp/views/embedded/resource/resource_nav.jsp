<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.education.commonResource.web.common.contants.SysContants"%>
<%@ page import="platform.education.resource.contants.ResourceType"%>
<%@include file="/views/embedded/taglib_lads.jsp"%>
<style>
	input[type="text"]{
		box-sizing: border-box;
	}
	
/* 	.weike_top p .big { */
/*   		font-size: 20px; */
/* 	} */
	
</style>
<div class="weike_top">
	<p>
		<span><a href="${pageContext.request.contextPath}/resource/home" style="color:#808080">首页</a></span>
		<span class="${param.type == 'fenlei' ? 'big': 's2'}"><a href="${pageContext.request.contextPath}/resource/searcher/index" style="color:#808080">分类资源</a></span>
		<span><a href="${pageContext.request.contextPath}/resource/myResource?index=index&resType=<%=ResourceType.LEARNING_DESIGN %>" style="color:#808080">我的资源</a></span>
	</p>
	<div class="gjz_select">
		
		<c:choose>
			<c:when test="${param.isDef != null && !param.isDef}">
				<input type="text" name="title" style="width: 200px;" placeholder="请输入资源标题" value="${param.title}" id="search_key">
				<button class="btn btn-primary" id="search_btn">搜索</button>
<!-- 				<a href="javascript:void(0)" class="upload"><i class="fa fa-plus"></i> &nbsp; 上传资源</a> -->
			</c:when>
			<c:otherwise>
				<form action="${pageContext.request.contextPath}/resource/searcher/result/index" method="post" style="margin:0;">
					<input type="text" name="title" style="width: 200px;" placeholder="请输入资源标题" value="${param.title}" id="search_key">
					<button class="btn btn-primary" id="search_btn">搜索</button>
<!-- 					<a href="javascript:void(0)" class="upload"><i class="fa fa-plus"></i> &nbsp; 上传资源</a> -->
				</form>
			</c:otherwise>
		</c:choose>
		
	</div>
	<div class="clear"></div>
</div>

