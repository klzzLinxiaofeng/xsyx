<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${tableList}" var="table" varStatus="status"> 
										<tr id="${table.id}_tr">
											<td>${status.index + 1}</td>
											<td>${table.name}</td>
											<td>${table.code}</td>
											<td><button class="btn btn-success" onclick="editTable('${table.id}');">编辑</button>
											<button class="btn btn-danger" onclick="delTable(this, '${table.id}');">删除</button></td>
										</tr>
										
									</c:forEach>
