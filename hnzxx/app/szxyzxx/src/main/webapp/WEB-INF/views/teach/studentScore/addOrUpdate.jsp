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

.row-fluid .spanScore {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 35%;
	display: inline-block;
	padding-left: 10px;
}
h4{
	color:#000;
	font-weight:bold;
	font-size:16px;
	font-family:"微软雅黑"
}
h4 span{
	font-size:14px;
	color:#666;
}
</style>
</head>
<body >
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" >
				
					<form class="form-horizontal tan_form" id="studentscore_form" action="javascript:void(0);">
					<h4 >
							<!-- <font style="FONT-SIZE: 14pt; FONT-FAMILY: 华文行楷," color=#000000>学年：</font> -->
						
							学年：<span>${StudentScoreCondition.schoolYearName}</span>
							学期：<span>${StudentScoreCondition.termName}</span>
								
							考试类型：<span><jcgc:cache tableCode="NEW_JY-KSLX" value="${StudentScoreCondition.examType}"></jcgc:cache></span>
							年级：<span><jc:cache tableName="pj_grade" echoField="name" value="${StudentScoreCondition.gradeId}" paramName="id"></jc:cache></span>
							班级：<span><jc:cache tableName="pj_team" echoField="name" value="${StudentScoreCondition.teamId}" paramName="id"></jc:cache></span>
							考试名称：<span>${StudentScoreCondition.examName}</span>
							考试科目：<span><jc:cache tableName="jc_subject" echoField="name" value="${StudentScoreCondition.subjectCode}" paramName="code"></jc:cache></span>
							
							<input type="hidden" disabled="disabled"  name = "subjectCode" id = "subjectCode" value="${StudentScoreCondition.subjectCode}" style="width:100px;""/>
							
							<input  type="hidden" disabled="disabled" name = "examTeamSubjectId" id = "examTeamSubjectId" value="${StudentScoreCondition.examTeamSubjectId}" style="display:none;"/>
							
							<input type="hidden" disabled="disabled"  name = "schoolYear" id = "schoolYear" value="${StudentScoreCondition.schoolYear}" style="width:100px;""/>
							<input type="hidden" disabled="disabled"  name = "termCode" id = "termCode" value="${StudentScoreCondition.termCode}" style="width:100px;""/>
							<input type="hidden" disabled="disabled"  name = "gradeId" id = "gradeId" value="${StudentScoreCondition.gradeId}" style="width:100px;""/>
							<input type="hidden" disabled="disabled"  name = "teamId" id = "teamId" value="${StudentScoreCondition.teamId}" style="width:100px;""/>
							<input type="hidden" disabled="disabled"  name = "examType" id = "examType" value="${StudentScoreCondition.examType}" style="width:100px;""/>
							<input type="hidden" disabled="disabled"  name = "examName" id = "examName" value="${StudentScoreCondition.examName}" style="width:100px;""/>
							
							评分类型：<%-- <input type="text" name = "rateType" id = "rateType" value="${StudentScoreCondition.rateType}" style="width:100px;"/>status.index+1 --%>
							<select name = "rateType" id="rateType"  style="width:100px;">
							 <option value="0" <c:if test="${StudentScoreCondition.rateType == 0}">selected</c:if>>未定义</option>
							 <option value="1" <c:if test="${StudentScoreCondition.rateType == 1}">selected</c:if>>标准分</option>
							</select>
							
							</h4> 
							<table border="1" class="table  table-striped responsive table-bordered white">
							
							<thead>
								<tr> 
									
									<th>序号</th> 
									<th>学籍号</th> 
									<th>姓名</th> 
									<th>成绩</th>
									<th>评语</th>
								</tr> 
								</thead>
								<tbody>
						<c:forEach items="${studentScoreList}" var="studentScore" varStatus="status">
								<tr> 
							
								<td>
								<input type="hidden" readonly="readonly" id="studentId" name="studentScoreList['${status.index}'].studentId" class="span13" placeholder="" value="${studentScore.studentId}">
								<input type="hidden" readonly="readonly" id="id" name="studentScoreList['${status.index}'].id" class="span13" placeholder="" value="${studentScore.id}">	
								    <input type="text" readonly="readonly" id="indexId" name="indexId" class="span13" placeholder="" value="${status.index+1}"></td>  
									<td>${studentScore.studentNumber}</td> 
									<td>
									<input type="text" readonly="readonly" id="studentName" name="studentScoreList['${status.index}'].studentName" class="span13" placeholder="" value="${studentScore.studentName}">
									
									</td> 
									<td><input  type="text" id="studentScoreList['${status.index}'].score" name="studentScoreList['${status.index}'].score" class="spanScore" placeholder="" value="${studentScore.score}" ></td>
									<td><input  type="text" id="studentScoreList['${status.index}'].comment" name="studentScoreList['${status.index}'].comment" class="span13" placeholder="" value="${studentScore.comment}" ></td>
								</tr>
							</c:forEach>
							</tbody>
							</table>
							
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>