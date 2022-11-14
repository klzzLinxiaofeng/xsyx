<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${scoreVoList}" var="score" varStatus="sta">
	<tr>
		<td>${sta.index + 1 + (page.currentPage-1)*page.pageSize}</td>
		<td>${score.userName}</td>
		<td>${score.judgeName}</td>
		<td>
			<c:choose>
				<c:when test="${score.checkType != '01'}">
					<a href="javascript:downloadFile('${score.attachmentFileUuid}','${score.fileName}');">${score.score}分</a>
<%-- 					<entity:getHttpUrl uuid="${score.attachmentFileUuid}"/> --%>
				</c:when>
				<c:otherwise>${score.score}分</c:otherwise>
			</c:choose>
		</td>
		<td>
			<fmt:formatDate value="${score.checkTime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
</c:forEach>
<script type="text/javascript">
	$(function(){
		var totalNum = "${totalNum}";
		var totalRow = "${totalRow}";
		$("#totalNum").html(Math.round(totalNum*100)/100);
		$("#totalRow").html(totalRow);
	});
	function downloadFile(uuid,fileName){
		var url = "${ctp}/uploader/download?entityFileUUID=" + uuid + "&fileName=" + fileName;
		location.href = url;
	}
</script>