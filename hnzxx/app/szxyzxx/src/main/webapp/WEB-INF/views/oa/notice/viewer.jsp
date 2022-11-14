<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
h3{
	text-align:center;
}
</style>
</head>
<body style="background-color: #fff !important">
	<div class="row-fluid">
	<div class="page-header">
            <h3>${notice.title }</h3>
          </div>
		<div style="text-align:center">公告类型：${notice.type }  &nbsp;&nbsp;&nbsp;&nbsp; 时间：<fmt:formatDate value="${notice.createDate}" pattern="yyyy/MM/dd" /></div>
		<div class="span11">
					 ${notice.content } 
				</div>
	</div>
</body>
 
</html>