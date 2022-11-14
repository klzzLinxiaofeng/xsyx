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
				<div class="widget-container " style="background-color:#fff;padding:0">
					<div class="widget-head" style="height:40px;border-bottom:0 none">
						<p style="float: right;margin-bottom:0" class="btn_link">
							<a id="downLoadExcel"  class="a3" href="" onclick="downLoadExcel();"><i class="fa fa-plus"></i>导出学生成绩记录</a>
						</p>
					</div>
					<table align="center" class="responsive table table-striped table-bordered">
					<thead>
							  <tr> 
								<th >考试类型</th>
								<th>考试名称</th>
								<c:forEach items="${subjectList}" var="subject" varStatus="statusSubject">
									<th>${subject.name}</th>
								</c:forEach>
								<th>平均分</th>
								<th>总分</th>
								<th>班级排名</th>
								<th>年级排名</th>
							   </tr>
							   </thead>
							   <tbody>
						<c:forEach items="${examScoreVoList}" var="exam" varStatus="status">
								<c:set var="flag">0</c:set>
								<c:set var="count">0</c:set>
								
								<tr> 
									<td align="center" >
									<jcgc:cache tableCode="NEW_JY-KSLX" value="${exam.examType}"></jcgc:cache>
									</td> 
									<td align="center" >${exam.examName}</td> 
								
								
								  <c:forEach items="${exam.studentScoreSort}" var="score" varStatus="statusScore">
								 		  <c:set var="subjectnum">0</c:set>
								 	 <c:forEach items="${subjectList}" var="subject" varStatus="statusSubject">
								 	 
									 	<c:if test="${subjectnum==0}">
									  	 <c:if test="${count>0}">
									  	  	<c:set var="count" value="${count-1}"></c:set>
									  	 </c:if>
									  	</c:if>
									  	
									  	 <c:if test="${count==0}">
									  	  	<c:set var="flag">0</c:set>
									  	 </c:if>
									  	 
									  	 <c:choose>
										  	 <c:when test="${score.subjectCode==subject.code}">
										  	 <c:set var="count" value="${statusSubject.index+2}"></c:set>
										  	 <c:set var="flag" value="${flag+1}"></c:set>
										  	 <c:set var="subjectnum"  value="${subjectnum+1}"></c:set>
											   		<td  align="center" >${score.score}</td>
											   		
											 </c:when>
											  <c:otherwise>
											 <c:if test="${flag==0}">
											  <td>
												<%--  <input type="text" value="${statusSubject.index}" style="width:20px;"> --%>
											 </td>
											 </c:if>
											
											</c:otherwise>
										  </c:choose>
										  
									  </c:forEach>
								   </c:forEach>
								   
								   <c:if test="${exam.scoreNum+1<=count}">
								   		<c:forEach  begin="${count}" end="${fn:length(subjectList)}">
												<td></td>
										</c:forEach>
								   </c:if>
								   <c:if test="${exam.scoreNum==0}">
								   		<c:forEach  begin="${count+1}" end="${fn:length(subjectList)}">
												<td></td>
										</c:forEach>
								   </c:if>
								  
									<td align="center" >${exam.stuAverage}</td> 
									<td align="center" >${exam.stuTotal}</td> 
									<td align="center" >${exam.stuTeamRank}</td> 
									<td align="center" >${exam.stuGradeRank}</td> 
								</tr>
						</c:forEach>
								</tbody> 
					</table>
						
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function downLoadExcel(){
	var schoolYear = $("#schoolYear").val();
	var termCode = $("#termCode").val();
	var gradeId = $("#gradeId").val();
	var teamId = $("#teamId").val();
	var studentId = $("#studentId").val();
	var examType = $("#examType").val();
	
	var param = "schoolYear="+schoolYear+"&"+"termCode="+termCode+"&"+"gradeId="+gradeId+"&"+"teamId="+teamId+"&"+"studentId="+studentId+"&"+"examType="+examType;
	var url = "${pageContext.request.contextPath}/teach/scoreSelect/student/downLoadExcel?";
	  url = url+param;
	  $("#downLoadExcel").attr("href", url);
	  
}

</script>
</html>