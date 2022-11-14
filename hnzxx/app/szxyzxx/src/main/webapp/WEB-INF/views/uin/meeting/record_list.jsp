<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="sta">
	<tr id="${item.meetId}_tr">
		<td>${item.meetId}</td>
<%-- 		<td><fmt:formatDate value="${item.startDate}" pattern="yyyy/MM/dd HH:mm" /></td> --%>
		<td>${item.meetSubject}</td>
		<td>${fn:length(item.invitees)}</td>
		<td class="caozuo">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${sta.index+10*(page.currentPage-1)}');">查看与会详情</button>
		</td>
	</tr>
</c:forEach>
<c:if test="${empty items}">
	<tr>
		<td colspan="5">暂无视频会议</td>
	</tr>
</c:if>
<script  type="text/javascript">
	$(function(){
		var dataLength = "${fn:length(items)}";
		if(dataLength == 0){
			$("#page_div").hide();
		}else{
			$("#page_div").show();
		}
	});
</script>