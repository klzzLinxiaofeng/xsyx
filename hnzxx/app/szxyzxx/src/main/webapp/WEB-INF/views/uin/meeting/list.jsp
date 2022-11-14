<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.meetId}_tr">
		<td>${item.meetId}</td>
		<td>${item.hostName}</td>
		<td><fmt:formatDate value="${item.startDate}" pattern="yyyy/MM/dd HH:mm" /></td>
		<td>${item.meetSubject}</td>
		<td>
			<c:choose>
				<c:when test="${currentUser.userName == item.userNum}">
					${item.hostPwd}
				</c:when>
				<c:otherwise>
					${item.pswd}
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${item.status == 0}">未开始</c:when>
				<c:when test="${item.status == 1}">进行中</c:when>
				<c:when test="${item.status == 2}">已结束</c:when>
				<c:otherwise>未知</c:otherwise>
			</c:choose>
		</td>
		<td>${item.memberCount}</td>
		<td class="caozuo">
			<c:choose>
				<c:when test="${item.status == 0}">
					<c:if test="${currentUser.userName == item.userNum || fn:contains(currentUser.userTypes,'2')}">
					<button class="btn btn-red" type="button" onclick="closeConf();">结束会议</button>
					</c:if>
				</c:when>
				<c:when test="${item.status == 1}">
					<c:choose>
						<c:when test="${currentUser.userName == item.userNum}">
							<button class="btn btn-green" type="button" onclick="joinConf('${item.hostUrl}');">进入会议</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-green" type="button" onclick="joinConf('${item.publicUrl}');">进入会议</button>
						</c:otherwise>
					</c:choose>
					<c:if test="${currentUser.userName == item.userNum || fn:contains(currentUser.userTypes,'2')}">
					<button class="btn btn-red" type="button" onclick="closeConf('${item.meetId}');">结束会议</button>
					</c:if>
					
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
<c:if test="${empty items}">
	<tr>
		<td colspan="8">目前暂无视频会议</td>
	</tr>
</c:if>
