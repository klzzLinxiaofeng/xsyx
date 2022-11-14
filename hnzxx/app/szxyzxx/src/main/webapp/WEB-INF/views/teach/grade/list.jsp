<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td>
</tr>
	<c:forEach items="${gradeList}" var="grade">
	 <tr>
	     <td>${grade.fullName}</td>
	     <td>${grade.name}</td>
	     <td>${grade.yearName}</td>
	     <td><jc:cache tableName="jc_stage" echoField="name" value="${grade.stageCode}" paramName="code"></jc:cache></td>
<!-- 	     <td>${grade.teamCount}</td> -->
	    <%--  <td><fmt:formatDate value="${grade.createDate}" /></td> --%>
<!-- 	     <td><fmt:formatDate value="${grade.finishDate}" /></td> -->
	     <td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button onclick="loadModifyPage('${grade.id}');" type="button" class="btn btn-blue">编辑</button>
			</c:if>
	     	 <%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
	     		<button onclick="deleteGrade('${grade.id}');" type="button" class="btn btn-red">删除</button>
	     	</c:if>--%>
	     </td>
	   </tr>
	</c:forEach>
