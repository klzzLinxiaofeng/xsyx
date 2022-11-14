<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><th>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</th></tr>
<c:forEach items="${items}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
		<td style="text-align:center"><input type="checkbox" name="ids" value="${item.id}:${item.classTime}" /></td>
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${item.classTime}</td>
		<td>${item.weekDate}</td>
		<td>${item.classTimeStart}</td>
		<td>${item.classTimeEnd}</td>

	</tr>
</c:forEach>

<script type="text/javascript">
	$(function(){
		$("#checkAll").prop({checked:false});
		$(".cancelBubble").each(function(index,value){
			var $id = $(value).find("a").attr("data-id");
			$("#ryxx_trs tr td input:checkbox[value='" + $id + "']").prop("checked", true);
		});
	});
</script>