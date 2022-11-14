<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:choose> 
	<c:when test="${task.scopeType == '2'}">
		<tr>
			<td>1</td>
			<td>${taskVo.departmentName}</td>
			<td>${taskVo.departmentNum}人</td>
			<td>${taskVo.dailyCount/taskVo.departmentNum}分</td>
			<td>${taskVo.deductCount/taskVo.departmentNum}分</td>
			<td>${taskVo.bonusCount/taskVo.departmentNum}分</a></td>
			<td>${(taskVo.dailyCount + taskVo.deductCount + taskVo.bonusCount) /taskVo.departmentNum}分</td>
		</tr>
	</c:when>
	<c:otherwise>
	
	</c:otherwise>
</c:choose>
<!-- <tr> -->
<!-- 	<td>2015.1.1</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td><a href="javascript:void(0)" class="detail">大赛获奖3分</a></td> -->
<!-- 	<td>30分</td> -->
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>2015.1.2</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>2015.1.3</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td>2015.1.4</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- 	<td>20分</td> -->
<!-- 	<td>30分</td> -->
<!-- </tr> -->