<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.name}</td>
        <td><img src="${item.coverUrl}" style="width: 200px;height: 200px"></td>
        <td>${item.fullName}</td>
        <td>${item.teacherName}</td>
        <td>${item.classTime}</td>
        <td><fmt:formatDate value="${item.beginDate}" pattern="yyyy-MM-dd HH:mm"/></td>
        <td>${item.classNumber}</td>
        <td>${item.maxMember}</td>
        <td><a href="javascript:void(0)" onclick="checkEnrollStudent('${item.id}');">${item.enrollNumber}</a></td>
        <td><fmt:formatDate value="${item.expiryDate}" pattern="yyyy-MM-dd HH:mm"/></td>
        <td>
            <c:if test="${item.classType == 0}">
                学校社团
            </c:if>
            <c:if test="${item.classType == 1}">
                课后5+2课程
            </c:if>
            <c:if test="${item.classType == 2}">
                寒暑假
            </c:if>
        </td>
        <td>
                ${item.xuefei}
        </td>
        <td>
            <c:if test="${item.isCailiao == 0}">
                ${item.cailiaofei}
            </c:if>
            <c:if test="${item.isCailiao == 1}">
                ${item.cailiaofei}
            </c:if>
        </td>
        <td>
            <c:if test="${item.leixing == 1}">
                基本托管类
            </c:if>
            <c:if test="${item.leixing == 2}">
                科技益智类
            </c:if>
            <c:if test="${item.leixing == 3}">
                音乐表演类
            </c:if>
            <c:if test="${item.leixing == 4}">
                美术艺术类
            </c:if>
            <c:if test="${item.leixing == 5}">
                体育运动类
            </c:if>
            <c:if test="${item.leixing == 6}">
                综合素质类
            </c:if>
            <c:if test="${item.leixing == 7}">
                阅读鉴赏类
            </c:if>
            <c:if test="${item.leixing == 8}">
                户外拓展类
            </c:if>

        </td>

        <td class="caozuo">
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
                <button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
            </c:if>
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
            </c:if>
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
                <button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
            </c:if>
        </td>
    </tr>
</c:forEach>
