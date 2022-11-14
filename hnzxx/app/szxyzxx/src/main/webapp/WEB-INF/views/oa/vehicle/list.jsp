<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="count">
	<tr id="${item.id}_tr">
				<td>${count.count}</td>
<%-- 				<td>${item.name}</td> --%>
				<td>${item.plateNumber}</td>
				<td>${item.model}</td>
				<td>${item.frameNumber}</td>
				<td>${item.engineNumber}</td>
	    		<td><fmt:formatDate value="${item.purchaseDate}" pattern="yyyy/MM/dd" /></td>
				<td><jcgc:cache tableCode="XY-JY-CLLX" value="${item.vehicleType}"></jcgc:cache></td>
				<td>
					<c:if test="${item.serviceCondition=='0'}"><span style="color: green;">空闲</span></c:if>
					<c:if test="${item.serviceCondition=='1'}"><span style="color: red">使用中</span></c:if>
				</td>
				<td>${item.remark}</td>
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
