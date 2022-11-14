<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />

<c:forEach items="${taskItems}" var="item" varStatus="sta">
	<tr id="${item.id}_tr" class="list_content">
				<td>${sta.index + 1 + (page.currentPage-1)*10}</td>
				<td>
					<c:choose>
						<c:when test="${item.checkType == '01'}">日常</c:when>
						<c:when test="${item.checkType == '02'}">加分</c:when>
						<c:when test="${item.checkType == '03'}">减分</c:when>
						<c:otherwise>其他</c:otherwise>					
					</c:choose>				
				</td>
				<td>${item.category == null ? "-- --" : item.category}</td>
				<td>${item.name == null ? "-- --" : item.name}</td>
				<td>${item.score == null ? "-- --" : item.score}</td>
				<td>${item.judgeName}</td>
				<td><div title="${item.description}"  class="description">${item.description}</div></td>
	<td class="caozuo">
		<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
		<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
	</td>
	</tr>
</c:forEach>
<script type="text/javascript">
	var isAllExit = 0;
	$(function(){
		$(".list_content").each(function(index,value){
			var checkType = $(value).find("td").eq(1).html();
			checkType = checkType.trim();
			if("加分" === checkType){
				isAllExit = 1;
			}
			if("减分" === checkType){
				isAllExit = 2;
			}
			if(isAllExit >= 2){
				$(".a4").hide();
			}
		});
	});
</script>