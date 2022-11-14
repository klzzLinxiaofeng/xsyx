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
			
				<div class="widget-container"  style="background-color:#fff;padding:0">
				<div class="widget-head" style="height:40px;border-bottom:0 none">
						<p style="float: right;margin-bottom:0" class="btn_link">
							 <a id="downLoadExcel"  class="a3" href="" onclick="downLoadExcel();"><i class="fa fa-plus"></i>导出班级成绩查询记录</a>
						</p>
					</div>
					<table align="center" class="table  table-striped responsive table-bordered ">
							  <tr> 
								<th>序号</th>
								<th>学籍号</th>
								<th>姓名</th>
								<th>${subjectName}</th>
								<th>班级排名</th>
								<th>年级排名</th>
							 </tr> 
						<c:forEach items="${studentScoreVoList}" var="stuScore" varStatus="status">
								
								<tr>
									<td align="center" >${status.index+1}</td> 
									<td align="center" >${stuScore.studentNumber}</td> 
									<td align="center" >${stuScore.studentName}</td> 
									<td align="center" >${stuScore.score}</td> 
									<td align="center" >${stuScore.teamRank}</td> 
									<td align="center" >${stuScore.gradeRank}</td>
								</tr>
						</c:forEach>
					</table>
						
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
//导出对话框
function downLoadExcel(){
	
	var schoolYear = $("#schoolYear").val();
	var termCode = $("#termCode").val();
	var gradeId = $("#gradeId").val();
	var teamId = $("#teamId").val();
	var studentId = $("#studentId").val();
	var examType = $("#examType").val();
	var examName = $("#examName").val();
	
	var subjectCode = $("#subjectCode").val();
	var param = "schoolYear="+schoolYear+"&"+"termCode="+termCode+"&"+"gradeId="+gradeId+"&"+"teamId="+teamId+"&"+"studentId="+studentId+"&"+"examType="+examType+"&"+"examName="+examName+"&"+"subjectCode="+subjectCode;
	var url = "${pageContext.request.contextPath}/teach/scoreSelect/headTeacher/downLoadExcel?";
	 url = url+param;
	 url =  encodeURI(url);
	  $("#downLoadExcel").attr("href", url);
	  
}
</script>
</html>