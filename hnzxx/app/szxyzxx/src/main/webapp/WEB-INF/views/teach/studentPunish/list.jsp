<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td></tr>
<c:forEach items="${studentPunishList}" var="item" varStatus="status">
	<tr id="${item.id}_tr">
				<td>${(page.currentPage-1)*page.pageSize+status.index+1}</td>
				<td>${item.schoolYearName}</td>
				<td>${item.gradeName}</td>
				<td>${item.teamName}</td>
				<td>${item.studentName}</td>
				<td>${item.punishType}</td>
				 <td><jcgc:cache tableCode="JY-WJLB" value="${item.punishCause}" /></td>
				<td><fmt:formatDate value="${item.punishDay}" pattern="yyyy/MM/dd" /></td>
				<td><fmt:formatDate value="${item.punishEndDay}" pattern="yyyy/MM/dd" /></td>
				<td><fmt:formatDate value="${item.repealDay}" pattern="yyyy/MM/dd" /></td>
				
				
				
	    	<%-- 	<td><fmt:formatDate value="${item.punishDay}" pattern="yyyy/MM/dd" /></td>
	    		
	    		<c:choose>
					<c:when test="${item.punishEndDay}==''">
					<td><fmt:formatDate value="${item.repealDay}" pattern="yyyy/MM/dd" /></td>
					
					</c:when>
					<c:otherwise>
					<td><fmt:formatDate value="${item.punishEndDay}" pattern="yyyy/MM/dd" /></td>
					</c:otherwise>
				</c:choose>	
	    		
	    		<c:choose>
					<c:when test="${item.repealDay}==''">
					
					<td>无</td>
					</c:when>
					<c:otherwise>
					<td><fmt:formatDate value="${item.repealDay}" pattern="yyyy/MM/dd" /></td>
					</c:otherwise>
				</c:choose>	 
				<td>${item.isRepeal}</td>
				--%>
	    		
	    		<c:choose>
					<c:when test="${item.isRepeal}">
					<td>是</td>
					</c:when>
					<c:otherwise>
					<td>否</td>
					</c:otherwise>
				</c:choose>		
				
				
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
