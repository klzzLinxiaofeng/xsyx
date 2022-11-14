<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<tr style="display:none">
    <td colspan="8">
        <input type="hidden" id="currentPage" name="currentPage"
               value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages"
               value="${page.totalPages}" />
        <input type="hidden" id="total" name="total"
               value="${page.totalRows}" />
    </td>
</tr>

<c:forEach items="${items}" var="item">
    <tr>
        <td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}${item.icon}"></span>
        </td>
        <td>${item.appletName}</td>
        <c:choose>
            <c:when test="${item.lineType==0}">
                <td class="xj">下架</td>
            </c:when>
            <c:otherwise>
                <td class="sj">上架</td>
            </c:otherwise>
        </c:choose>
        <td id="" title="<c:if test="${fn:length(item.roles)>3}">${item.rolesString}</c:if>">
            <c:choose>
                <c:when test="${fn:length(item.roles)>3}">
                    <c:forEach items="${item.roles}" var="role" begin="0" end="2" step="1" varStatus="status">
                        ${role}<c:if test="${status.index+1<3}">、</c:if>
                    </c:forEach>
                    …
                </c:when>
                <c:otherwise>
                    <c:forEach items="${item.roles}" var="role" begin="0" end="${fn:length(item.roles)+1}" step="1" varStatus="status">
                        ${role}<c:if test="${status.index+1<fn:length(item.roles)}">、</c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </td>

        <td class="caozuo">
            <c:choose>
                <c:when test="${item.appletKey eq 'YINGYONG_ZHONGXIN'}">
                    <c:if test='${fn:toLowerCase(sessionScope[sca:currentUserKey()].userName) eq "superadmin"}'>
                        <button class="btn btn-green " data-appletId="${item.appletId}" onclick="qxsz(this,${item.appletId},${item.ownerId})">权限设置</button>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-green " data-appletId="${item.appletId}" onclick="qxsz(this,${item.appletId},${item.ownerId})">权限设置</button>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</c:forEach>