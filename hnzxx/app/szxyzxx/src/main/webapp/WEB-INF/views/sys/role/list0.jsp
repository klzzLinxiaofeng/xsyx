<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${roles}" var="role">
	<tr id="${role.id}_tr">
		<td>
			${role.code}
		</td>
		<td>
			${role.name}
		</td>
		<td>
			类型
<%-- 			<ca:cache tableName="t_dm_gy_xxjbxx" paramName="xxMc" id="${role.xxDm}" /> --%>
		</td>
<!-- 		<td> -->
<%-- 			<input type="checkbox" ${role.jyBj == "0" ? 'checked="checked"' : ''} onclick="enableOrDisable(this, '${role.id}')"> --%>
<!-- 		</td> -->
		<td><t:showTime createTime="${role.createDate}" /></td>
		<td>
			<a href="javascript:accredit('${role.id}');">【授权】</a>
			<a href="javascript:void(0);" onclick="loadEditPage('${role.id}');">【编辑】</a>
			<a href="javascript:deleteObj(this, '${role.id}');">【删除】</a>
		</td>
	</tr>
</c:forEach>
