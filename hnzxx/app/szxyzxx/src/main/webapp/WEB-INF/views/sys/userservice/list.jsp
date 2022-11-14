<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.schoolId}_tr">
<%-- 				<td>${item.id}</td> --%>
<%-- 				<td>${item.serviceType}</td> --%>
				<td><input class="school_checkbox" type="checkbox" name="ids" value="${item.schoolId}" data-schoolName="${item.schoolName}" data-schoolEnglishName="${item.schoolEnglishName}"></td>
				<td>${item.schoolId}</td>
				<td>${item.schoolName}</td>
				<c:choose>
					<c:when test="${item.openResult == 0}">
						<td style="color: #179F4B;">已开通</td>
					</c:when>
					<c:when test="${item.openResult == 1}">
						<td style="color: #e88a05;">开通失败</td>
					</c:when>
					<c:otherwise>
						<td style="color: #e88a05;">未开通</td>
					</c:otherwise>
				</c:choose>
				<td>${item.account}</td>
				<td>${item.ext}</td>
	    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
		
		<td class="caozuo">
			<c:if test="${item.openResult != 0}">
				<button class="btn btn-green" type="button" onclick="register('${item.schoolId}','${item.schoolName}','${item.schoolEnglishName}');">开通</button>
			</c:if>
			<button class="btn btn-red" style="display: none;" type="button" onclick="editSuffix('${item.schoolId}');">自定义开通</button>
<%-- 			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button> --%>
		</td>
	</tr>
</c:forEach>
<script type="text/javascript">
	$(function(){
		$("#checkAll").prop("checked", false);	
	});
</script>