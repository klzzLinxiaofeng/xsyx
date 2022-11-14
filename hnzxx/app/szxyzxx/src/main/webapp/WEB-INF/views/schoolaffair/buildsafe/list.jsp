<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="status">
	<tr id="${item.id}_tr">
				<td>${status.index + 1 }</td>
				<td>${item.name}</td>
	    		<td><fmt:formatDate value="${item.checkDate}" pattern="yyyy-MM-dd" /></td>
				<td>
					<c:choose>
						<c:when test="${item.isSurveyData == false}">否</c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${item.isDesignDoc == false}">否</c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${item.isConstructionDoc == false}">否</c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${item.isCheckDoc == false}">否</c:when>
						<c:otherwise>是</c:otherwise>
				</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${item.isCompletionDoc == false}">否</c:when>
						<c:otherwise>是</c:otherwise>
				</c:choose>
				</td>
				<td>
				<c:choose>
					<c:when test="${item.fireSate == 1}">
						一级
					</c:when>
					<c:when test="${item.fireSate == 2}">
						二级
					</c:when>
					<c:when test="${item.fireSate ==3}">
						三级
					</c:when>
					<c:when test="${item.fireSate ==4}">
						四级
					</c:when>
				</c:choose>
				</td>
				<td>
				<c:choose>
						<c:when test="${item.isFireSate == false}">否</c:when>
						<c:otherwise>是</c:otherwise>
				</c:choose>
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
