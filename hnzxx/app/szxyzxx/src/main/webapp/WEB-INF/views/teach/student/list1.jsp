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
		<td style="text-align:center"><input type="checkbox" name="ids" value="${item.id}" data-name="${item.name}"/></td>
		<td>${status.count}</td>
		<td>${item.name}</td>
		<td>
			<c:if test="${empty item.sex}">未知</c:if>
			<c:if test="${not empty item.sex}">
				<jcgc:cache tableCode="GB-XB" value="${item.sex}"/>
			</c:if>
		</td>
		<td>${not empty item.mobile ? item.mobile : "无"}</td>
		<td>${not empty item.parentMobile ? item.parentMobile : "无"}</td>
		<td>${not empty item.teamName ? item.teamName : "无"}</td>
		<td>
			<c:if test="${empty item.studyState}">未知</c:if>
			<c:if test="${not empty item.studyState}">
				<jcgc:cache tableCode="JY-XSDQZT" value="${item.studyState}"></jcgc:cache>
			</c:if>
		</td>
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