<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><th>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</th></tr>
<c:forEach items="${items}" var="item" varStatus="status">
	<tr>
		<c:if test="${param.enableBatch}">
		<td style="text-align:center"><input type="checkbox" name="ids" value="${item.id}" data-name="${item.name}" /></td>
		</c:if>
		<td >${status.count}</td>
		<td id="name">${item.name}</td>
		<td
			${item.sex != null && item.sex != "" ? jcgcFn:getColValue("GB-XB", item.sex, "name") : "无"}
		</td>
		<td>${item.mobile != null ? item.mobile : "无"}</td>
<%-- 		<td>${item.departmentName != null ? item.departmentName : "无"}</td> --%>
		<td>${item.jobState != null && item.jobState != "" ? jcgcFn:getColValue("JY-JZGDQZT", item.jobState, "name") : "无"}</td>
		<td>
			<button class="btn btn-success add-btn" type="button" data-id="${item.id}" data-name="${item.name}">确定</button>
<!-- 			<button class="btn btn-blue view-btn" type="button">查看</button> -->
		</td>
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