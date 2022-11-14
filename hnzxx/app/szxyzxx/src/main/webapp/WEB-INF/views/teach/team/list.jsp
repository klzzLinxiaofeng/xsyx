<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
    <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td>
</tr>
<c:forEach items="${teamList}" var="team">
   <tr>
       <td>${team.teamNumber}</td>
       <td>${team.name}</td>
       <td>${team.fullName}</td>
<!--        <td>${team.memberCount}</td> -->
      <%--  <td>${team.code}</td> --%>
<!--        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${team.beginDate}"></fmt:formatDate></td> -->
<!--        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${team.finishDate}"></fmt:formatDate></td> -->
       <td class="caozuo">
       	<%-- <a href="javascript:void(0);" onclick="loadModifyTeamPage('${team.id}');">【编辑】</a>
       	<a href="javascript:void(0);" onclick="deleteTeam('${team.id}');">【删除】</a> --%>
       		<button onclick="loadModifyTeamPage('${team.id}');" type="button" class="btn btn-blue">编辑</button>
			<button onclick="deleteTeam('${team.id}');" type="button" class="btn btn-red">删除</button>
       </td>
   </tr>
</c:forEach>
