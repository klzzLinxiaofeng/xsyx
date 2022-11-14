<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${items}" var="item" varStatus="i">
	<tr class="tr_${item.id}">
		<td>${item.subjectName}</td>
		<td><fmt:formatDate value="${item.examDate}" pattern="yyyy-MM-dd" /></td>
		<td>${item.studentCount} 人</td>
		<td class="caozuo">
				<button class="btn btn-primary" type="button" onclick="scoreManagement('${item.id}');">查看成绩统计</button>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}"> --%>
				<button class="btn btn-green" type="button" onclick="modifyExamStudent('${item.id}');">修改成绩</button>
<%-- 			</c:if>  --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
				<button class="btn btn-blue" type="button" onclick="examProModify('${item.subjectCode}',null,'${item.id}');">设置</button>
<%-- 			</c:if>  --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
				<button class="btn btn-warning" type="button" onclick="deleteScore(this, '${item.id}');">删除</button>
<%-- 			</c:if> --%>
		</td>
	</tr>
</c:forEach>
