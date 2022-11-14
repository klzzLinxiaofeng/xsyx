<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
</td></tr>
<c:forEach items="${accounts}" var="account">
	<tr id="${account.id}_tr">
		<td>${account.userName}</td>
		<td>
			<jcgc:cache tableCode="XY-YH-ZHZT" value="${account.state}"></jcgc:cache>
		</td>	
		<td><t:showTime createTime="${account.createDate}" /></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue update_permission" type="button" onclick="loadAssigningRegionPage('${account.id}');">分配区域</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
<script type="text/javascript">
	
	$(function() {
		var isUpdate = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}";
// 		var isRead = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}";
// 		var isDel = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}";
		
		if("true" != isUpdate) {
			$(".update_permission").remove();
		}
		
// 		if("true" != isRead) {
// 			$(".read_permission").remove();
// 		}
		
// 		if("true" != isDel) {
// 			$(".del_permission").remove();
// 		}
	});

</script>