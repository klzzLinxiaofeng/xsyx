<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<table class="table">
    <thead>
    <tr><th>年级</th><th>班级</th><th>班内学号</th><th>学生姓名</th><th>监护人</th><th>手机号码</th><th>与学生关系</th><th>异常提示</th></tr>
    </thead>
    <tbody style="background-color: <c:choose><c:when test="${status eq 0}">#fff</c:when><c:otherwise>#fff1f1</c:otherwise></c:choose>;">
    <c:forEach items="${list}" var="item">
        <c:choose>
            <c:when test="${item.status eq 0}">
                <tr>
                    <td>${item.gradeName}</td>
                    <td>${item.teamNumber}班</td>
                    <td>${item.number}</td>
                    <td>${item.studentName}</td>
                    <td>${item.parentName}</td>
                    <td>${item.mobile}</td>
                    <td>${item.relation}</td>
                    <td class="color_f44336">${item.errorInfo}</td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td <c:if test="${fn:contains(item.errorFiled, 'gradeName')}">class="color_f44336"</c:if>>${item.gradeName}</td>
                    <td <c:if test="${fn:contains(item.errorFiled, 'teamNumber')}">class="color_f44336"</c:if>>${item.teamNumber}班</td>
                    <td <c:if test="${fn:contains(item.errorFiled, 'number')}">class="color_f44336"</c:if>>${item.number}</td>
                    <td <c:if test="${fn:contains(item.errorFiled, 'studentName')}">class="color_f44336"</c:if>>${item.studentName}</td>
                    <td <c:if test="${fn:contains(item.errorFiled, 'parentName')}">class="color_f44336"</c:if>>${item.parentName}</td>
                    <td <c:if test="${fn:contains(item.errorFiled, 'mobile')}">class="color_f44336"</c:if>>${item.mobile}</td>
                    <td>${item.relation}</td>
                    <td class="color_f44336">${item.errorInfo}</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    </tbody>
</table>

<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

