<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none">
    <td>
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${accounts}" var="account">
    <tr id="${account.pid}_tr">
        <td><input class="user_checkbox" type="checkbox" name="ids" value="${account.userId}"data-username="${account.userName}"></td>
        <td>${account.name}</td>
        <td > ${account.mobile}</td>
        <td>${account.carNo}</td>
        <td>${account.userName}</td>
        <td>

            <c:choose>
                <c:when test="${account.status==0}">
                    正常
                </c:when>
                <c:when test="${account.status==1}">
                    禁用
                </c:when>
                <c:when test="${account.status==2}">
                    锁定
                </c:when>
                <c:otherwise>
                    未知
                </c:otherwise>
            </c:choose>

        </td>
        <td class="stuName">${account.stuInfo}</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${account.createTime}"></fmt:formatDate></td>
        <td class="caozuo">

<%--            <button class="btn btn-info update_permission" type="button"--%>
<%--                    onclick="loadAssigningRolePage('${account.userId}');">分配角色--%>
<%--            </button>--%>

            <button class="btn btn-blue update_permission" type="button"
                    onclick="loadEditPage('${account.userId}','');">编辑
            </button>

            <button class="btn btn-warning update_permission" type="button" onclick="resetPwd('${account.userId}');">
                重置密码
            </button>

                <%-- 			</c:if> --%>
                <%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}"> --%>
<%--            <button class="btn btn-green read_permission" type="button"--%>
<%--                    onclick="loadBindNamesPage('${account.userId}');">查看所绑定的账号--%>
<%--            </button>--%>
<%--            <button class="btn btn-green read_permission" type="button"--%>
<%--                    onclick="loadEditPage('${account.userId}','disable');">详情--%>
<%--            </button>--%>
                <%-- 			</c:if> --%>
                <%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
                <%-- 				<button class="btn btn-red del_permission" type="button" onclick="deleteObj(this, '${account.userId}');">删除</button> --%>
                <%-- 			</c:if> --%>
        </td>
    </tr>
</c:forEach>


<script type="text/javascript">

    $(function () {
        var isUpdate = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}";
        <%--var isRead = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}";--%>
        <%--var isDel = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}";--%>

        // if ("true" != isUpdate) {
        //     $(".update_permission").remove();
        // }

        // if ("true" != isRead) {
        //     $(".read_permission").remove();
        // }
        //
        // if ("true" != isDel) {
        //     $(".del_permission").remove();
        // }

        $("#checkAll").prop("checked", false);


    });

</script>
