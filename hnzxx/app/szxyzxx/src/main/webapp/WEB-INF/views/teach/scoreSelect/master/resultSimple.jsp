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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
				
					<table border="1" align="center" width="1400px">
							  <tr> 
								<th>序号</th>
								<th>学籍号</th>
								<th>姓名</th>
								<th>${subjectName}</th>
							 </tr> 
						<c:forEach items="${studentScoreVoList}" var="stuScore" varStatus="status">
								
								<tr>
									<td align="center" >${status.index+1}</td> 
									<td align="center" >${stuScore.studentNumber}</td> 
									<td align="center" >${stuScore.studentName}</td> 
									<td align="center" >${stuScore.score}</td> 
								</tr>
						</c:forEach>
					</table>
						
				</div>
			</div>
		</div>
	</div>
</body>
</html>