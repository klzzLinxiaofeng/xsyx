<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
				<td>${i.index+1}</td>
				<td title="${item.title}">
					<c:choose>
						<c:when test="${fn:length(item.title)>10}">
							<c:out value="${fn:substring(item.title,0,10)}..."></c:out>
						</c:when>
						<c:otherwise>${item.title}</c:otherwise>
					</c:choose>
				</td>
				<td>${item.posterName}</td>
	    		<td><fmt:formatDate value="${item.postTime}" pattern="yyyy/MM/dd" /></td>
				<td>${item.departmentName}</td>
				<td>
					<c:if test="${item.dealStatus=='01'}">申请</c:if>
					<c:if test="${item.dealStatus=='02'}">受理（维修中）</c:if>
					<c:if test="${item.dealStatus=='03'}">不受理</c:if>
					<c:if test="${item.dealStatus=='04'}">维修完成</c:if>
					<c:if test="${item.dealStatus=='08'}">评价完成</c:if>
				</td>
				<td>${item.receiverName}</td>
				<td title="${item.handler}">
					<c:choose>
						<c:when test="${fn:length(item.handler)>10}">
							<c:out value="${fn:substring(item.handler,0,10)}..."></c:out>
						</c:when>
						<c:otherwise>${item.handler}</c:otherwise>
					</c:choose>
				</td>
				<td>${item.handlerPhone}</td>
	    		<td><fmt:formatDate value="${item.dealTime}" pattern="yyyy/MM/dd" /></td>
	    		<td><fmt:formatDate value="${item.finishTime}" pattern="yyyy/MM/dd" /></td>
				<td title="${item.content}">
					<c:choose>
						<c:when test="${fn:length(item.content)>10}">
							<c:out value="${fn:substring(item.content,0,10)}..."></c:out>
						</c:when>
						<c:otherwise>${item.content}</c:otherwise>
					</c:choose>
				</td>
				<td>${item.score}</td>
				<td title="${item.comment}">
					<c:choose>
						<c:when test="${fn:length(item.comment)>10}">
							<c:out value="${fn:substring(item.comment,0,10)}..."></c:out>
						</c:when>
						<c:otherwise>${item.comment}</c:otherwise>
					</c:choose>
				</td>
		<td class="caozuo">
			<c:if test="${isReceive!=null && item.dealStatus=='01'}">
				<button class="btn btn-blue" type="button" onclick="loadRececivePage('${item.id}');">受理</button>
			</c:if>
			<c:if test="${isReceive!=null && item.dealStatus=='02'}">
				<button class="btn btn-blue" type="button" onclick="loadFinishPage('${item.id}');">维修完成</button>
			</c:if>
			<c:if test="${isReceive==null && item.dealStatus=='04'}">
				<button class="btn btn-blue" type="button" onclick="loadAssessPage('${item.id}');">评价</button>
			</c:if>
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
