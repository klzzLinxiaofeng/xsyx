<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${voList}" var="vo" varStatus="i">
	<tr id="${vo.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>${vo.name}</td>
				<td><a href="javascript:void(0)" onclick="checkName('${vo.id}', 1);"><dota:fieldVal type="teacher" code="${vo.evaluatedList[0].personnelId }"></dota:fieldVal>...</a></td>
				<td><fmt:formatDate value="${vo.effectiveDate}" pattern="yyyy/MM/dd" /></td>
	    		<td><fmt:formatDate value="${vo.expiryDate}" pattern="yyyy/MM/dd" /></td>
				<td><c:choose>
	    			<c:when test="${vo.type == 0}"><span style="color:blue">考核完成</span></c:when>
	    			<c:when test="${vo.type == 1}"><span style="color:blue">考核中</span></c:when>
	    			<c:otherwise><span style="color:red"></span></c:otherwise>
	    			</c:choose></td>
		
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${vo.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<c:choose>
			<c:when test="${vo.type == 0}"></c:when>
			<c:when test="${vo.type == 1}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${vo.id}');">考核</button>
			</c:when>
			</c:choose>
			</c:if>
		</td>
	</tr>
</c:forEach>
