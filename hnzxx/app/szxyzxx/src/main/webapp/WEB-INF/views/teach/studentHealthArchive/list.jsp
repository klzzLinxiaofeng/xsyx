<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${studentHealthArchiveVos}" var="vo" varStatus="i">
	<tr id="${vo.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>
					<fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>${vo.gradeName}</td>--%>
				<td>${vo.teamName}</td>
				<td>${vo.stuName}</td>
<%--				<td><jcgc:cache tableCode="GB-JKZK3" value="${vo.types}" /></td>--%>
				<td>${vo.types}</td>
				<td>${vo.remark}</td>

		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${vo.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${vo.id}');">编辑</button>
			</c:if>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
<%-- 				<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${teamStudentVo.id}');">删除</button> --%>
<%-- 			</c:if> --%>
		</td>
	</tr>
</c:forEach>
