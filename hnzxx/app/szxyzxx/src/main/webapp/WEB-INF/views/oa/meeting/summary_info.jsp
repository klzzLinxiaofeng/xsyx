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
            <h3>会议纪要</h3>
          </div>
		 
		<div class="span11">
					 ${summary.summaryContent } 
				</div>
	</div>
</body>
 
</html>