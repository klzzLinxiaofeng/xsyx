<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%-- <tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr> --%> 
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />

<div class="bjxc_list" id="layer-photos-demo">
	<c:forEach items="${items}" var="item">
			<div class="img_div"><img src="${item.fileUrl}"></div>
		<tr id="${item.id}_tr">
		</tr>
	</c:forEach>
	<%-- <div class="img_div"><img src="${ctp}/res/images/pdf.png"></div> --%>
	<div class="clear"></div>
</div>

<%-- <ul id="layer-photos-demo">
	<div class="entry">
		<c:forEach items="${items}" var="item">
						<tr id="${item.id}_tr">
						<td >${item.id}</td>
						<td >${item.teamId}</td>
						<td>${item.name}</td>
						<td >${item.fileId}</td>
						<td><img src="${item.fileId}" ></td>
						<td >${item.postUserId}</td>
						<td >${item.objectType}</td>
			    		<td ><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
			    		<td ><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
						<td >${item.isDeleted}</td>
				
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
			</tr> 
		</c:forEach>
	</div>
</ul> --%>
<script>
	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	  layer.photos({
	    photos: '#layer-photos-demo',
	 	area: 'auto'
	  });
	}); 
</script>
