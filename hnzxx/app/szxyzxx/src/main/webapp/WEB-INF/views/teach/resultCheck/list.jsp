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
					<c:when test="${resultVo.audit==0}"><span style="color:green">未审核</span></c:when>
   					<c:when test="${resultVo.audit==1}"><span style="color:red">未通过</span></c:when>
   					<c:when test="${resultVo.audit==2}"><span style="color:blue">通过</span></c:when>
   					<c:otherwise>通过</c:otherwise>
   				</c:choose>
   				</td>	
		<td class="caozuo">
		<c:choose>
			<c:when test="${resultVo.audit==0}">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${resultVo.id}');">审核</button>
			</c:if>
			</c:when>
			<c:otherwise>
			<button class="btn btn-green" type="button" disabled="disabled">已审核</button>	
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
</c:forEach>
