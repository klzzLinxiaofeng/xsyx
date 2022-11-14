<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${studentMergeParentVoList}" var="ps">
			<tr>
				<td>${ps.stu_name}</td>
				<td>${ps.team_name}</td>
				<td>${ps.emp_card}</td>

				<td>${ps.parent_name}</td>
				<td>${ps.parent_relation}</td>
				<td>${ps.rank eq '1' ? "是":"否"}</td>
				<td>${ps.user_name}</td>
				<td>${ps.mobile}</td>
				<td>${ps.license_plate}</td>
				<td>${ps.email}</td>
				<td class="caozuo">
<%--					<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
						<button class="btn btn-blue" type="button" onclick="loadEditPage('${ps.id}','${ps.stu_id}');">编辑</button>
						<button class="btn btn-blue" type="button" 
								onclick="deleteRelate('${ps.id}','${ps.stu_id}', '${ps.stu_name }', '${ps.parent_name }');">解除关系</button>
						<button class="btn btn-blue" type="button" onclick="loadCreatePage('${ps.stu_id}');">添加家长</button>
<%--					</c:if>--%>
				 </td>
		    </tr>
</c:forEach>

		
		
