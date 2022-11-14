<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td></tr>
<c:forEach items="${accounts}" var="account">
	<tr id="${account.id}_tr">
		<td><input class="user_checkbox" type="checkbox" name="ids" value="${account.userId}" data-username="${account.userName}"></td>
		<td>${account.userName}</td>
		<td class="stuName">${account.stuName}</td>
		<td class="teamName">${account.teamName}</td>
		<td>
<%-- 			<ca:cache tableName="t_dm_gy_ryxx" paramName="xm" id="${account.ryDm}" /> --%>
<%-- 			${account.ryMc} --%>
			${account.name}
		</td>
		<td class="mobile">
			${account.mobile}
		</td>
		<td class="schoolName">
			${account.schoolName}
		</td>
<!-- 		<td> -->
<%-- 			<ca:cache tableName="t_dm_gy_zhlx" paramName="zhlxMc" id="${account.yhlx}" /> --%>
<!-- 		</td> -->
		<td>
			<jcgc:cache tableCode="XY-YH-ZHZT" value="${account.userState}"></jcgc:cache>
		</td>
<%-- 		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${account.createDate}"></fmt:formatDate></td> --%>
		<td><t:showTime createTime="${account.createDate}" /></td>
		<td class="caozuo">
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
				<button class="btn btn-info update_permission" type="button" onclick="loadAssigningRolePage('${account.userId}');">分配角色</button>
				<button class="btn btn-blue update_permission" type="button" onclick="loadEditPage('${account.userId}','');">编辑</button>
				<button class="btn btn-warning update_permission" type="button" onclick="resetPwd('${account.userId}');">重置密码</button>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}"> --%>
				<button class="btn btn-green read_permission" type="button" onclick="loadBindNamesPage('${account.userId}');">查看所绑定的账号</button>
				<button class="btn btn-green read_permission" type="button" onclick="loadEditPage('${account.userId}','disable');">详情</button>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
<%-- 				<button class="btn btn-red del_permission" type="button" onclick="deleteObj(this, '${account.userId}');">删除</button> --%>
<%-- 			</c:if> --%>
		</td>
	</tr>
</c:forEach>


<script type="text/javascript">

	$(function() {
		var isUpdate = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}";
		var isRead = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}";
		var isDel = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}";

		if("true" != isUpdate) {
			$(".update_permission").remove();
		}

		if("true" != isRead) {
			$(".read_permission").remove();
		}

		if("true" != isDel) {
			$(".del_permission").remove();
		}

		$("#checkAll").prop("checked", false);

// 		$(".user_checkbox").prop("checked", $("#checkAll").prop("checked"));
		if(${roleType} == 0){
			$(".schoolName").show();
			$(".mobile").show();
			$(".nickName").text("用户昵称");
			$(".nickName2").text("用户昵称：");
			$(".stuName").hide();
			$(".teamName").hide();
			$("#stuNames").hide();
		}else if(${roleType} == 1){
			$(".schoolName").show();
			$(".mobile").hide();
			$("#mobile").val("");
		}else{
			$(".schoolName").hide();
			$(".mobile").show();
			$(".nickName").text("家长");
			$(".nickName2").text("家长：");
			$(".stuName").show();
			$(".teamName").show();
			$("#stuNames").show();
		}

	});

</script>
