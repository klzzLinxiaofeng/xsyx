<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="totalPages" value="${page.totalPages}"/>
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<c:forEach items="${items}" var="item">
    <tr id="tr_${item.id}">
        <td>${item.name}</td>
        <td style="width:100px;text-align:center">
            <c:choose>
                <c:when test='${item.isDeleted}'>是</c:when>
                <c:otherwise>否</c:otherwise>
            </c:choose>
        </td>
        <td style="width:150px;text-align:center"><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd"/></td>
        <td class="caozuo" style="width:200px;text-align:center">
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
            </c:if>
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
                <button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
            </c:if>
        </td>
    </tr>
</c:forEach>