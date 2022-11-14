<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.micro.contants.MicroContans" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.title}</td>
				<td><%=MicroContans.MICRO_HOT_PATH%>${item.microUuid}</td>
				<td id="push_state">
					<c:choose>
						<c:when test="${item.pushState == 0}"><span id="push_1" style="color: green;">启用</span><span id="push_2" style="color: orange;display: none;">不启用</span></c:when>
						<c:otherwise><span id="push_1" style="color: green;display: none;">启用</span><span id="push_2" style="color: orange;">不启用</span></c:otherwise>
					</c:choose>
				</td>
				<td>${item.lessonAuthor}</td>
	    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
		
		<td class="caozuo">
<%-- 			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button> --%>
			<c:choose>
				<c:when test="${item.pushState == 0}">
					<button id="push_yes" class="btn btn-blue" style="display: none;" type="button" onclick="changPush('${item.id}',this,0);">启用</button>
					<button id="push_no" class="btn btn-blue" type="button" onclick="changPush('${item.id}',this,1);">禁用</button>
				</c:when>
				<c:otherwise>
					<button id="push_yes" class="btn btn-blue" type="button" onclick="changPush('${item.id}',this,0);">启用</button>
					<button id="push_no" class="btn btn-blue" style="display: none;" type="button" onclick="changPush('${item.id}',this,1);">禁用</button>
				</c:otherwise>
			</c:choose>
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		</td>
	</tr>
</c:forEach>
