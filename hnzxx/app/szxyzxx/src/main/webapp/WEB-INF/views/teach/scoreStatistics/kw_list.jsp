<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<tr style="display:none">
    <td>
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="status">
    <tr>
        <td>${status.count}</td>
        <td>${item.name}</td>

        <td>
            <c:choose>
                <c:when test="${item.examType == '20'}">
                    <fmt:formatDate value="${item.examDateBegin}" pattern="yyyy/MM/dd HH:mm"/>
                </c:when>
                <c:otherwise>
                    <fmt:formatDate value="${item.examDateBegin}" pattern="yyyy/MM/dd"/>
                    -
                    <fmt:formatDate value="${item.examDateEnd}" pattern="MM/dd"/>
                </c:otherwise>
            </c:choose>
        </td>
        <td class="caozuo">
            <c:choose>
                <c:when test="${item.examType == '20'}">
                    <button class="btn btn-green" onclick="toStudent('${item.id}','${item.teamId}')">查看详情</button>
                    <button class="btn btn-orange" onclick="toTeam('${item.id}','${item.teamId}');">班级成绩分析</button>
                    <%--<button class="btn btn-blue">导出班级分析报告</button>--%>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-green" onclick="toGrade('${item.id}', '${item.teamId}');">查看详情</button>
                </c:otherwise>
            </c:choose>

        </td>
    </tr>
</c:forEach>