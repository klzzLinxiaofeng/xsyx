<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<table class="table">
    <thead>
    <tr><th>班级</th><th>教师</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.teamNumber}班</td>
            <td>
                <c:forEach items="${item.teacherList}" var="teacher" varStatus="status">
                    <a href="javascript:void(0)" data-id="${teacher.teacherId}">${teacher.name}</a>
                    <c:if test="${!status.last}">、</c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>