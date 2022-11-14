<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

<c:forEach items="${items}" var="item" varStatus="sta">
	<tr id="${item.id}_tr">
		<td>${sta.index + 1 + (page.currentPage-1)*10}</td>
		<td>${item.name}</td>
		<td>
			<c:if test="${item.scopeType == 1}">
				<a href="javascript::void(0)">
					全校教职工
				</a>
			</c:if>
			<c:if test="${item.scopeType == 2}">
				<a href="javascript::void(0)" title="${item.departmentName}">
					${item.departmentName}
				</a>
			</c:if>
			<c:if test="${item.scopeType == 9}">
				<a href="javascript::void(0)" title="${item.userNames}">
					<c:if test="${fn:length(item.userNames) >= 1}">${item.userNames[0]}...</c:if>
					<c:if test="${fn:length(item.userNames) < 1}">${item.userNames}</c:if>
				</a>
			</c:if>
		</td>
   		<td><fmt:formatDate value="${item.startDate}" pattern="yyyy/MM/dd" /></td>
   		<td><fmt:formatDate value="${item.finishDate}" pattern="yyyy/MM/dd" /></td>
   		<td style="color: blue;">
   			${item.state}
   		</td>
		<td class="caozuo">
			<c:if test="${'考核中' == item.state}">
				<c:if test="${item.bonusExit}">
					<button class="btn btn-warning" type="button" onclick="bonusEdit('${item.id}');">加减分考核</button>
				</c:if>
				<c:if test="${item.dailyExit}">
					<button class="btn btn-blue" type="button" onclick="checkEdit('${item.id}');">日常考核</button>
				</c:if>
			</c:if>
			<c:if test="${item.dailyExit}">
				<button class="btn btn-green" type="button" onclick="checkView('${item.id}');">日常考核详情</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
