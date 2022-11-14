<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding:0;border:0 none;display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td> 
</tr>
<tr>
	<th>序号</th>
	<th>课程名称</th>
	<th>上课教师</th>
	<th>开始上课日期</th>
	<th>课程总节数</th>
	<th>人数上限</th>
	<th>已报名人数</th>
	<th>报名截止日期</th>
	<th>报名状态</th>
	<th  class="caozuo">操作</th>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>${item.name}</td>
				<td>${item.teacherName}</td>
	    		<td><fmt:formatDate value="${item.beginDate}" pattern="yyyy/MM/dd" /></td>
				<td>${item.classNumber}</td>
				<td>${item.maxMember}</td>
				<td>${item.enrollNumber}</td>
	    		<td><fmt:formatDate value="${item.expiryDate}" pattern="yyyy/MM/dd" /></td>
	    		<td>
	    		<c:choose>
	    			<c:when test="${item.enrollState == '0'}">未报名</c:when>
	    			<c:when test="${item.enrollState == '1'}">已报名</c:when>
	    			<c:when test="${item.enrollState == '2'}">未报名</c:when>
	    			<c:otherwise></c:otherwise>
	    		</c:choose></td>
		
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
				<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<c:choose>
					<c:when test="${item.isEnroll == '1' }">
						<c:choose>
							<c:when test="${item.enrollState == '0' }">
								<button class="btn btn-yellow" type="button" onclick="enroll('${item.id}');">进行报名</button>
							</c:when>
							<c:when test="${item.enrollState == '1' }">
								<button class="btn btn-red" type="button" onclick="cancelEnroll('${item.id}');">取消报名</button>
							</c:when>
							<c:when test="${item.enrollState == '2' }">
<%-- 								<button class="btn btn-red" type="button" onclick="cancelEnroll('${item.id}');">取消报名</button> --%>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</c:if>
			
		</td>
	</tr>
</c:forEach>
