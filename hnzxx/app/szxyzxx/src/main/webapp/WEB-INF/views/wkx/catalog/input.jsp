<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
b.row{
	line-height: 35px;
}
#title{
	line-height: 35px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid" > 
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0;padding:0 20px">
				<div class="widget-container" style="padding: 20px 0 0;">
					<div align="center" id="title" style="font-weight: bold;font-size: 28px;line-height: 35px;">
						<span><b class="row">${microCatalog.title}</b></span>
					</div>
					</br></br>
					<div>
						<span style="word-break:break-all;">${microCatalog.content}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>