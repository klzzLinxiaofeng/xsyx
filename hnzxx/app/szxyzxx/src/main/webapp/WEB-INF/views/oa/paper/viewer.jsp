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
            <h3>${paper.title }</h3>
          </div>
		<div style="text-align:center">发布者：${paper.posterName }  &nbsp;&nbsp;&nbsp;&nbsp; 时间：<fmt:formatDate value="${paper.createDate}" pattern="yyyy/MM/dd" /></div>
		<div class="span11">
					 ${paper.content } 
				</div>
	</div>
</body>
 
</html>