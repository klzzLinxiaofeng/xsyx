<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<style>
.abc {
	color: black;
	font-size: 14px;
	line-height: 25px;
	height:25px;
	width: 260px;
overflow: hidden;
text-overflow:ellipsis;
white-space: nowrap;width:257px;}
</style>
<%-- <tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr> --%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<%-- <div class="bjsp_list">
	<a class="img_div"><img src="${ctp}/res/images/pdf.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/pic.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/ppt.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/rar.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/pdf.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/pic.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/ppt.png"><b></b></a>
	<a class="img_div"><img src="${ctp}/res/images/rar.png"><b></b></a>
	<div class="clear"></div>
</div> --%>
 <div class="bjsp_list">
 	<%-- <a class="img_div"><img src="${ctp}/res/images/pdf.png   ${item.thumbnailUrl}"><b></b></a> --%>
	<c:forEach items="${items}" var="item">
		<a class="img_div" onclick="play('${item.fileId}');"><img src="${item.thumbnailUrl}" ><b></b><p class="abc">${item.name}</p></a>
		<%-- <tr id="${item.id}_tr">
					<td>${item.id}</td>
					<td>${item.teamId}</td>
					<td>${item.name}</td>
					<td>${item.fileId}</td>
					<td>${item.postUserId}</td>
					<td>${item.objectType}</td>
		    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
		    		<td><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
					<td>${item.isDeleted}</td> 
					<td><button class="btn btn-green" type="button" onclick="play('${item.fileId}');">播放</button></td>
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
		</tr> --%>
	</c:forEach>
	
	<div class="clear"></div>
</div>
