<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<th style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</th>
</tr>
<c:forEach items="${list}" var="item">
	<tr id="${item.id}_tr">
				<td style="text-align:center"><input type="checkbox" name="ids" value="${item.id}" data-name="${item.title}" /></td>
				<td>${item.title}</td>
				<td>
					${empty item.lessonLength ? 0 : item.lessonLength}
				</td>
				<td>${item.name}</td>
	    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
		<td>
			<button class="btn btn-red" type="button" onclick="selectValue('${item.id}');">推荐为热门</button>
		</td>
	</tr>
</c:forEach>
<script type="text/javascript">
	$(function(){
		$("#checkAll").prop({checked:false});
		$(".cancelBubble").each(function(index,value){
			var $id = $(value).find("a").attr("data-id");
			$("#microLessonHot_list_content tr td input:checkbox[value='" + $id + "']").prop("checked", true);
		});
	});
</script>