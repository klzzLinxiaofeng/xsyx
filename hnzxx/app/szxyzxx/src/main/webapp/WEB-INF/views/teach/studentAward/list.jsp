<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>

<c:forEach items="${StudentAwardList}" var="item" varStatus="status">
	<tr id="${item.id}_tr">
				<td>${(page.currentPage-1)*page.pageSize+status.index+1}</td>
				<td>${item.schoolYearName}</td>
				<td>${item.gradeName}</td>
				<td>${item.teamName}</td>
				<td>${item.numInTeam}</td>
				<td>${item.studentName}</td>
				<%-- <td>${item.awardContent}</td> --%>
				<td><jcgc:cache tableCode="JY-JLFS" value="${item.awardContent}"></jcgc:cache></td>
				<%-- <td>${item.awardLevel}</td> --%>
				<td><jcgc:cache tableCode="JY-XSHJLB" value="${item.awardLevel}"></jcgc:cache></td>
				<td>${item.awardRanking}</td>
				<%-- <td>${item.awardType}</td> --%>
				<td><jcgc:cache tableCode="JY-HJLX" value="${item.awardType}"></jcgc:cache></td>
	    		<td><fmt:formatDate value="${item.awardDay}" pattern="yyyy/MM/dd" /></td>
				<td>${item.awardUnit}</td>
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
