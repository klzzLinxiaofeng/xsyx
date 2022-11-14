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
				
					<form class="form-horizontal tan_form" id="studentscore_form" action="javascript:void(0);">
							<table border="1" align="center">
							<h4>
							<!-- <font style="FONT-SIZE: 14pt; FONT-FAMILY: 华文行楷," color=#000000>学年：</font> -->
							
							学年：<jc:cache tableName="pj_school_year" echoField="name" value="${StudentScoreCondition.schoolYear}" paramName="year"></jc:cache>
							学期：${StudentScoreCondition.termCode}	
							考试类型：<jcgc:cache tableCode="JY-KSXS" value="${StudentScoreCondition.examType}"></jcgc:cache>
							年级：<jc:cache tableName="pj_grade" echoField="name" value="${StudentScoreCondition.gradeId}" paramName="id"></jc:cache>
							班级：<jc:cache tableName="pj_team" echoField="name" value="${StudentScoreCondition.teamId}" paramName="id"></jc:cache>
							考试名称：${StudentScoreCondition.examName}
							考试科目：<jc:cache tableName="jc_subject" echoField="name" value="${StudentScoreCondition.subjectCode}" paramName="code"></jc:cache>
							
							<%-- <input type="text"  name = "schoolYear" id = "schoolYear" value="${StudentScoreCondition.schoolYear}"  style="width:100px;"/>
							<input type="text" name = "termCode" id = "termCode" value="${StudentScoreCondition.termCode}" style="width:100px;"/>
							<input type="text" name = "examType" id = "examType" value="${StudentScoreCondition.examType}" style="width:100px;"/>
							<input type="text" name = "gradeId" id = "gradeId" value="${StudentScoreCondition.gradeId}" style="width:100px;"/>
							<input type="text" name = "teamId" id = "teamId" value="${StudentScoreCondition.teamId}" style="width:100px;"/> 
							<input type="text" name = "examName" id = "examName" value="${StudentScoreCondition.examName}" style="width:100px;""/>--%>
							
							<input type="hidden" disabled="disabled"  name = "subjectCode" id = "subjectCode" value="${StudentScoreCondition.subjectCode}" style="width:100px;""/>
							
							<input  type="hidden" disabled="disabled" name = "examTeamSubjectId" id = "examTeamSubjectId" value="${StudentScoreCondition.examTeamSubjectId}" style="display:none;"/>
							评分类型：<input type="text" name = "rateType" id = "rateType" value="${StudentScoreCondition.rateType}" style="width:100px;"/>
							</h4> 
								<tr> 
									
									<th>序号</th> 
									<th>学籍号</th> 
									<th>姓名</th> 
									<th>成绩</th>
								</tr> 
						<c:forEach items="${studentScoreList}" var="studentScore" varStatus="status">
								<tr> 
<%-- 									<input type="text" id="studentId" name="studentScoreList['${status.index}'].studentId" class="span13" placeholder="" value="${studentScore.studentId}"/>
 --%>									<td><input type="text" readonly="readonly" id="id" name="studentScoreList['${status.index}'].id" class="span13" placeholder="" value="${studentScore.id}"></td>  
									<td>${studentScore.studentNumber}</td> 
									<td><input type="text" readonly="readonly" id="studentId" name="studentScoreList['${status.index}'].studentId" class="span13" placeholder="" value="${studentScore.studentId}"></td> 
									<td><input type="text" id="score" name="studentScoreList['${status.index}'].score" class="span13" placeholder="" value="${studentScore.score}"></td> 
								</tr>
							</c:forEach>
							</table>
							
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>