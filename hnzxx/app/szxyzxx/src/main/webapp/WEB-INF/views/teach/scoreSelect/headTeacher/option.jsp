<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<!-- <font style="FONT-SIZE: 14pt; FONT-FAMILY: 华文行楷," color=#000000>学年：</font>
 -->				
					
	<form name="selectForm" id="selectForm" class="form-horizontal">
	 <input id="sub" type="hidden" name="sub" value="list"></input><br/>
      						       <input id="dm"  type="hidden" name="dm" value="${param.dm}"></input><br/>
	<div class="control-group">
			<label class="control-label"><span class="red">*</span>学年： </label>
			<div class="controls">
				<select id="schoolYear" name="schoolYear" onchange="onChangeSchoolYear();" class="chzn-select"style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> <span class="red">*</span>学期： </label>
			<div class="controls">
				<select id="termCode" name="termCode" class="chzn-select"style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>年级：</label>
			<div class="controls">
				<select id="gradeId" name="gradeId" class="chzn-select" style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>班级： </label>
			<div class="controls">
				<select id="teamId" name="teamId" class="chzn-select" style="width:220px;" onchange="onChangeTeam();"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学生：</label>
			<div class="controls">
				<select id="studentId" name="studentId" class="chzn-select" style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>考试类型： </label>
			<div class="controls">
				<select id="examType" name="examType" class="chzn-select"style="width:220px;" onchange="examCode();"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>考试名称： </label>
			<div class="controls">
				<select id="examName" name="examName" class="chzn-select" style="width:220px;" onchange="subjectName();">
										<option value="">请选择</option>
									</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目： </label>
			<div class="controls">
				<select id="subjectCode" name="subjectCode" class="chzn-select" style="width:220px;">
									<option value="">全部</option>
									</select>
			</div>
		</div>
      	<input type="hidden" name = "examCodeFlag" id="examCodeFlag" value="${ScoreCondition.examCodeFlag}" >
		<input type="hidden" name = "subjectCodeFlag" id="subjectCodeFlag" value="${ScoreCondition.subjectCodeFlag}">
     	<input type="hidden" name = "examTypeFlag" id="examTypeFlag" value="${ScoreCondition.examType}">
     <button type="button" class="btn btn-success" onclick="search()">确定</button>
      </form>

 
