<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	 <%@ include file="/views/embedded/common.jsp"%>
    <meta charset="UTF-8">
    <title>Document</title>
    <script type="text/javascript" src="${ctp}/res/js/common/jquery.tablesort.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/common/homework.css">
</head>
<script type="text/javascript">
   jQuery(function($){
   $.sortTable({tableId:"t1"});
   });
</script>
<body>
<div class="container-fluid">
<jsp:include page="/views/embedded/navigation.jsp">
               <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="试卷管理" name="title" />
                <jsp:param value="SHI_JUAN_GUAN_LI" name="menuId" />
            </jsp:include>
<div class="homework">
    <h3>${title}<a href="${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=SHI_JUAN_GUAN_LI"><i class="fa  fa-reply"style="margin-right: 5px;"></i>返回列表</a></h3>
    <div class="while">班级：<span>${teamName}</span>时间：<span>${time}</span></span></div>
    <div class="info">
        <button class="inventory">成绩清单</button>
<!--         <button class="export-score">导出成绩信息</button> -->
    </div>
</div>
<div class="accuracy">
    <table border="0" cellspacing="0" cellpadding="0" id="t1" class="table">
        <thead>
            <tr>
                <th style="width:60px;" class="noSort">名次</th>
                <th class="noSort">学号</th>
                <th class="noSort">姓名</th>
                <th class="noSort">完成时间</th>
                <th>得分<button class="sort">升降序</button></th>
<!--                 <th class="noSort" style="width:140px;">操作</th> -->
            </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${items}" var="item">
           <tr>
                <td>${item.rank}</td>
                <td>${item.studentNum}</td>
                <td>${item.studentName}</td>
               <td><fmt:formatDate value="${item.finishedTime}" pattern="yyyy年MM月dd日 "/></td>
                <td><b class="percent">${item.score}</b></td>
<!--                 <td><a href="javascript:void(0);">查看得分详情</a><a href="javascript:void(0);">写评语</a></td> -->
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</div>
</body>
</html>