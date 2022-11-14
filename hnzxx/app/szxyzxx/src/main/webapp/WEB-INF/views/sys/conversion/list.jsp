<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:choose>
	<c:when test="${!empty ConversionStatusResultVoList}">
		<c:forEach items="${ConversionStatusResultVoList}" var="conversionStatus" varStatus="index">
			<tr>
				<td>${(page.currentPage -1)*page.pageSize + index.count}</td>
				<td>${conversionStatus.name}</td>
				<td style="width:500px;">${conversionStatus.log}</td>
				<td><span class="unfinish_style">未完成</span></td>
				<td>
					<button class="btn btn-success" onclick="getDetail('${conversionStatus.uuid}');">详情</button>
					<button class="btn btn-success" onclick="conversionAgain('${conversionStatus.uuid}','${type}');">重新转换</button>
				</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="4" style="text-align: center">暂无数据</td>
		</tr>
	</c:otherwise>
</c:choose>
