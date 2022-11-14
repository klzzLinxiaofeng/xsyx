<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<style>
.shebei-table{
	width: 100%;
	border-top: 1px #ccc solid;
    border-left: 1px #ccc solid;
}
.shebei-table tr{
	height:30px;
	line-height:30px;
}
.shebei-table th,.shebei-table td{
	text-align: center;
	border-bottom: 1px #ccc solid;
    border-right: 1px #ccc solid;
    font-size: 14px;
    font-family: '微软雅黑';
    color: #444;
}
</style>
<table border="0" cellpadding="0" cellspacing="0" class="shebei-table">
<th>仪器名称</th>
<th>数量</th>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.equipmentName}</td>
				<td>${item.count}</td>
	</tr>
</c:forEach>
</table>