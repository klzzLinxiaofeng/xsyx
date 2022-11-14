<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="count">
	<tr id="${item.id}_tr">
				<td>${count.count}</td>
				<td>${item.plateNumber}</td>
				<td>${item.cardUser}</td>
	    		<td><fmt:formatDate value="${item.useDate}" pattern="yyyy/MM/dd" /></td>
				<td><dota:fieldVal type="teacher" code="${item.proposer}"></dota:fieldVal>
				</td>
				<td><c:if test="${empty item.approval}"></c:if>
					<c:if test="${not empty item.approval}">
						<dota:fieldVal type="teacher" code="${item.approval}"></dota:fieldVal>
					</c:if>
				</td>
				<td>${item.application}</td>
				<td>
					<c:if test="${item.status=='0'}"><span style="color: green;">待审批</span></c:if>
					<c:if test="${item.status=='1'}"><span style="color: red;">使用中</span></c:if>
					<c:if test="${item.status=='2'}"><span style="color: red;">归还中</span></c:if>
					<c:if test="${item.status=='3'}"><span style="color: green;">已归还</span></c:if>
					<c:if test="${item.status=='4'}"><span style="color: red;">不同意</span></c:if>
				</td>
				<td>${item.remark}</td>
		<td class="caozuo">
			<c:if test="${type=='shenpi'}">
				<button class="btn btn-blue" type="button" <c:if test="${item.status=='1' || item.status=='2' || item.status=='3' || item.status=='4'}">disabled="disabled"</c:if>
				 onclick="loadAuditPage('${item.id}','${item.status}');">审批</button>
			</c:if>
			<c:if test="${type=='' || type==null}">
				<c:if test="${item.status=='1'}">
					<button class="btn btn-blue" type="button" <c:if test="${item.status=='3'}">disabled="disabled"</c:if> onclick="loadReturnPage('${item.id}','${item.status}');">还车</button>
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
			</c:if>
		</td>
	</tr>
</c:forEach>
