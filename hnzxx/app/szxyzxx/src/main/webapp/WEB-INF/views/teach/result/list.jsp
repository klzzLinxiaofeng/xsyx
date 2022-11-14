<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${resultVoList}" var="resultVo" varStatus="i">
	<tr id="${resultVo.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td> 
				<td>${resultVo.teachName}</td>
				<td>  
					<c:if test="${resultVo.jobNumber!=null}">${resultVo.jobNumber}</c:if>
					<c:if test="${empty resultVo.jobNumber}">暂无工号</c:if>
   				</td>
				<td>${resultVo.name}</td>
				<td><jcgc:cache tableCode="XY-JY-CGLX" value="${resultVo.type}"></jcgc:cache></td>
				<td><jcgc:cache tableCode="XY-JY-CGJB" value="${resultVo.level}"></jcgc:cache></td>
				<td> 
				<c:choose> 
					<c:when test="${resultVo.audit==0}">未审核</c:when>
   					<c:when test="${resultVo.audit==1}">未通过</c:when>
   					<c:when test="${resultVo.audit==2}">通过</c:when>
   					<c:otherwise></c:otherwise>
   				</c:choose>
   				</td>	
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${resultVo.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${resultVo.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${resultVo.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
