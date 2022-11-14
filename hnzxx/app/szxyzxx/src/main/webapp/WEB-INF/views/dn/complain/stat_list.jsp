<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">

<table class="responsive table table-striped" id="data-table">
    <thead>
    <tr>
        <th>序号</th>
        <th>投诉人</th>
        <th>部门</th>
        <th>投诉次数</th>
        <th>处理评价</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${complainList}" var="item" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${item.complainName}</td>
            <td>${item.departName}</td>
            <td>${item.count}</td>
            <td><div class="p7"><div class="pf"><p style="width:${item.percent}"></p></div></div></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
