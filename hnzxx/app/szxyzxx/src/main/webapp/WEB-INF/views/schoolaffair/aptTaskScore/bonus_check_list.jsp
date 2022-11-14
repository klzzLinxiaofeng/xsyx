<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>

		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

<c:forEach items="${taskScores}" var="item" varStatus="sta">
	<tr id="${item.id}_tr">
		<td><fmt:formatDate value="${item.checkTime}" pattern="yyyy-MM-dd"/></td>
<%-- 		<td>${sta.index + 1 + (page.currentPage-1)*10}</td> --%>
		<td>${item.teacherName}</td>
		<td>${item.score}</td>
		<td><a href="javascript:downloadFile('${item.entityFile.uuid}','${item.entityFile.fileName}')">${item.entityFile.fileName}</a></td>
   		<td>${item.description}</td>
		<td class="caozuo">
<%-- 			<c:if test="${edit}"> --%>
<%-- 				<button class="btn btn-warning" type="button" onclick="bonusEdit('${item.id}');">编辑</button> --%>
<%-- 			</c:if> --%>
<%-- 			<button class="btn btn-green" type="button" onclick="checkView('${item.id}');">删除</button> --%>
			<c:if test="${edit}">
				<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
<script type="text/javascript">
function downloadFile(uuid,fileName){
	var url = "${ctp}/uploader/download?entityFileUUID=" + uuid + "&fileName=" + fileName;
	location.href = url;
}
</script>