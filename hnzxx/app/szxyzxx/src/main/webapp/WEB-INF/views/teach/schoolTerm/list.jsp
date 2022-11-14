<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr style="display:none">
	<td><input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${schoolTermList}" var="schoolTerm" varStatus="i">
	<tr id="${schoolTerm.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${schoolTerm.schoolYearName}</td>
		<td>${schoolTerm.name}</td>
		<td><fmt:formatDate value='${schoolTerm.beginDate}' pattern='yyyy-MM-dd'/></td>
		<td><fmt:formatDate value='${schoolTerm.finishDate}' pattern='yyyy-MM-dd'/></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
				<button class="btn btn-green" type="button" onclick="loadEditPage('${schoolTerm.id}','disable','');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue" type="button" onclick="loadEditPage('${schoolTerm.id}','','disable');">编辑</button>
			</c:if>
			<!--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<c:choose>
					<c:when test="${schoolTermCurrent.schoolTermCode == schoolTerm.code }">
						<button class="btn btn-blue del-current" type="button" onclick="deleteObj(this, '${schoolTerm.id}');" disabled="disabled">删除</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-blue del-current" type="button" onclick="deleteObj(this, '${schoolTerm.id}');">删除</button>
					</c:otherwise>
				</c:choose>
			</c:if>-->
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<c:choose>
					<c:when test="${schoolTermCurrent.schoolTermCode == schoolTerm.code }">
						<button type="button" class="btn btn-success set-current">设为当前学期</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-blue set-current" type="button" onclick="setCurrentTerm('${schoolTerm.id}', this);">设为当前学期</button>
					</c:otherwise>
				</c:choose>
			</c:if>
		</td>
	</tr>
</c:forEach>
