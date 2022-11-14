<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="resFn" uri="http://www.jiaoxueyun.com/resource/number/functions"%>
<table class="table table-striped" style="border:1px solid #e4e8eb; ">
	<thead><tr><th style="width:52%">他的上传</th><th>浏览量</th><th>下载量</th><th>积分值</th><th>上传时间</th></tr></thead>
	<tbody>
		<c:forEach items="${result}" var="resource">
			<tr>
				<td>
					<div class="wztt">
						<i class="${resFn:getIconClass(resource.iconType) }"></i>
						<c:if test="${resource.resType==7 }">
							<a onclick="previewLp(${resource.lpId})" href="javascript:void(0)">${resource.title }</a>
						</c:if>
						<c:if test="${resource.resType!=7 }">
							<a href="${pageContext.request.contextPath}/resource/Play?id=${resource.id }">${resource.title }</a>
						</c:if>
					</div>
				</td>
				<td>${resFn:getSpecialCount(resource.id, 0) }</td>
				<td>${resFn:getSpecialCount(resource.id, 3) }</td>
				<td>${resource.integral }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${resource.createDate }" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
function previewLp(lpId) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&editable=false&id="+lpId;
	window.open(url, "预览");
}
</script>