<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.name}</td>
				<td>${item.count }</td>		
				<td>${item.description}</td>
				<td title="${item.groupPersonName}">
				<c:choose>
						<c:when test="${fn:length(item.groupPersonName)>100}">
							<c:out value="${fn:substring(item.groupPersonName,0,100)}"></c:out>
							<strong>. . .</strong>
						</c:when>
						<c:otherwise>${item.groupPersonName }</c:otherwise>
					</c:choose> 
				
				</td>
		<td class="caozuo">
			<%-- <button class="btn btn-green" type="button" onclick="loadEditPage('${item.id}');">详情</button>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button> --%>
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		</td>
	</tr>
</c:forEach>
