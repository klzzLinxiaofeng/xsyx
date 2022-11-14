<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.title}</td>
				<td>
					<img alt="缩略图" style="width:155px;height:80px;" src="${item.thumUrl}">
				</td>
				<td>
					<c:choose>
						<c:when test="${item.pushState == 0}"><span style="color: green;">启用</span></c:when>
						<c:otherwise><span style="color: orange;">不启用</span></c:otherwise>
					</c:choose>
				</td>
	    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd" /></td>
		
		<td class="caozuo">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		</td>
	</tr>
</c:forEach>
