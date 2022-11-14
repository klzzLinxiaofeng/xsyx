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
								<c:forEach items="${subjectList}" var="subject" varStatus="statusSubject">
									<th>${subject.name}</th>
								</c:forEach>
								<th>平均分</th>
								<th>总分</th>
								<th>班级排名</th>
								<th>年级排名</th>
							   </tr> 
						<c:forEach items="${examScoreVoList}" var="exam" varStatus="status">
								<c:set var="flag">0</c:set>
								<c:set var="count">0</c:set>
								
								<tr> 
									<td align="center" >${status.index+1}</td> 
									<td align="center" >${exam.studentNumber}</td> 
								    <td align="center" >${exam.studentName}</td> 
								
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
												
											 </td>
											 </c:if>
											
											</c:otherwise>
										  </c:choose>
										  
									  </c:forEach>
								   </c:forEach>
									<td align="center" >${exam.stuAverage}</td> 
									<td align="center" >${exam.stuTotal}</td> 
									<td align="center" >${exam.stuTeamRank}</td> 
									<td align="center" >${exam.stuGradeRank}</td> 
								</tr>
						</c:forEach>
					</table>
						
				</div>
			</div>
		</div>
	</div>
</body>
</html>