<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.code}</td>
				<td>${item.name}</td>
<%-- 				<td>${item.englishName}</td> --%>
				<td><jcgc:cache tableCode="JY-CQ" value="${item.propertyRight}"></jcgc:cache></td>
				<td><jcgc:cache tableCode="JY-SYZK" value="${item.serviceCondition}"></jcgc:cache></td>
<%-- 				<td>${item.category}</td> --%>
<%-- 				<td>${item.model}</td> --%>
<%-- 	    		<td><fmt:formatDate value="${item.exFactoryDate}" pattern="yyyy/MM/dd" /></td> --%>
<%-- 	    		<td><fmt:formatDate value="${item.purchaseDate}" pattern="yyyy/MM/dd" /></td> --%>
<%-- 				<td>${item.manufacturer}</td> --%>
				<td><jcgc:cache tableCode="XY-JY-SBLY" value="${item.sourceType}"></jcgc:cache></td>
<%-- 				<td>${item.documentNumber}</td> --%>
<%-- 				<td>${item.place}</td> --%>
				<td>${item.blidingName}</td>
				<td>${item.roomName}</td>
				<td>${item.price}元</td>
				<td>${item.totalNumber}</td>
<%-- 	    		<td><fmt:formatDate value="${item.warrantyExpDate}" pattern="yyyy/MM/dd" /></td> --%>
<%-- 				<td>${item.remark}</td> --%>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
