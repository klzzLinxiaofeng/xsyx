<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
</td></tr>
<c:forEach items="${accounts}" var="account">
	<c:if test="${!empty account.user}">
	<tr id="${account.user.id}_tr">
		<td><input type="checkbox" name="ids" value="${account.user.id}"></td>
		<td>${account.user.userName}</td>
		<td>
			<jcgc:cache tableCode="XY-YH-ZHZT" value="${account.user.state}"></jcgc:cache>
		</td>	
		<td><t:showTime createTime="${account.user.createDate}" /></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-info" type="button" onclick="loadAssigningRolePage('${account.user.id}');">分配角色</button>
<%-- 				<button class="btn btn-blue" type="button" onclick="loadEditPage('${account.user.id}','');">编辑</button> --%>
				<button class="btn btn-warning" type="button" onclick="resetPwd('${account.user.id}');">重置密码</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
				<button class="btn btn-green" type="button" onclick="loadEditPage('${account.user.id}','disable');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${account.user.id}');">删除</button>
			</c:if>
		</td>
	</tr>
	</c:if>
</c:forEach>
