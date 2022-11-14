<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${examTeamSubjectVoList}" var="item" varStatus="status">
    <tr id="${item.id}_tr">

        <td>${(page.currentPage-1)*page.pageSize+status.index+1}</td>

        <td>${item.schoolYearName}</td>
        <td>${item.termCodeName}</td>

        <td>${item.examName}</td>

        <td>${item.teamName}
        <td>
        <c:if test="${item.examType==1}">
            期中考试
        </c:if>
        <c:if test="${item.examType==2}">
            期末考试
        </c:if>
        <c:if test="${item.examType==3}">
            平时考试
        </c:if>
        <c:if test="${item.examType==4}">
            单元测试
        </c:if>
    </td>
        <td>${item.subjectName}</td>

        <%--<td>${item.taskOnlineName}</td>--%>
        <td><fmt:formatDate value="${item.preciseStartDate}" pattern="yyyy-MM-dd"/></td>
        <td>${item.teamSum}</td>
        <td class="caozuo">
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
                <button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
            </c:if>
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                <c:if test="${item.isSuoDing==0}">
                    <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
                </c:if>
            </c:if>

            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
                <c:if test="${item.isSuoDing==0}">
                    <button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
                </c:if>
            </c:if>
            <c:if test="${item.isSuoDing==0}">
                <button class="btn btn-red" type="button" onclick="shezhi('${item.id}');">设置分值</button>
            </c:if>
            <c:if test="${item.isSuoDing==0}">
                <button class="btn btn-red" type="button" onclick="daoru('${item.id}');">导入成绩</button>
            </c:if>
            <c:if test="${juese}">
                <c:if test="${item.isSuoDing==0}">
                    <button class="btn btn-red" type="button" onclick="suoding('${item.id}');">锁定</button>
                </c:if>
                <c:if test="${item.isSuoDing==1}">
                    <button class="btn btn-red" type="button" onclick="jiesuo('${item.id}');">解锁</button>
                </c:if>
            </c:if>
        </td>
    </tr>
</c:forEach>
