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
<font style="FONT-SIZE: 14pt; FONT-FAMILY: 华文行楷," color=#000000>学年：</font>
				
				<input id="code" name="name"  value="getStudentName(261);" ></input>	
	<form name="selectForm" id="selectForm">
      						<div class="select_div" >
      						      <input id="sub" type="hidden" name="sub" value="list"></input><br/>
      						       <input id="dm"  type="hidden" name="dm" value="${param.dm}"></input><br/>
									
 									<span id="lfont">  学年： </span>
 									<select id="schoolYear" name="schoolYear" onchange="onChangeSchoolYear();" class="chzn-select"style="width:160px;"></select>
									<!-- <input id="schoolYear" name="schoolYear" value="2015" style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px'></input> --><br/>
									</div>
									
									<div class="select_div" >
									
 									<span id="lfont">  学期： </span>
<!--  									<select id="termCode" name="termCode" class="chzn-select"style="width:160px;"></select>
 -->									<input id="termCode" name="termCode"  value="1-2015-1"></input>
 										<br/>
									</div>
									
									
									<div class="select_div" >
									
									<span id="lfont">  年级： </span>
									<select id="gradeId" name="gradeId" class="chzn-select" style="width:120px;"></select>
									<!-- <input id="gradeId" name="gradeId"  value="1"></input> -->
									<br/>
									</div>
									
									<div class="select_div" >
									
									<span id="lfont">  班级： </span>
									<select id="teamId" name="teamId" class="chzn-select" style="width:160px;"></select>
									<!-- <input id="teamId" name="teamId"  value="1"></input> -->
									<br/>
									</div>
									
									<div class="select_div" >
									
									<span id="lfont">  学生： </span>
									<select id="studentId" name="studentId" class="chzn-select" style="width:160px;"></select>
									<!--<input  id="studentId" name="studentId"  value="261"></input> -->
 										<br/>
								    </div>
								    
								    <div class="select_div" >
									<span id="lfont">考试类型： </span>
									<select id="examType" name="examType" class="chzn-select"style="width:160px;"></select>
<!-- 									<input id="examType" name="examType"  value="1"></input>
 -->									<br/>
									</div>
									<div class="select_div" >
									<span id="lfont">考试名称： </span>
<!-- 									<select id="examName" name="examName" class="chzn-select" style="width:160px;"></select>
 -->									<input  id="examName" name="examName"   value="testterm"></input>
									<br/>
									</div>
									
									<div class="select_div" >
									<span id="lfont">科目：   </span>
<!-- 									<select id="subjectCode" name="subjectCode" class="chzn-select" style="width:160px;"></select>
 -->									<input   id="subjectCode" name="subjectCode"  value="25"></input> 
									<br/>
									</div>
    <!--   <input type="submit" value="submit"/> -->
    <br/>
     <button type="button" class="btn btn-primary" onclick="search()">确定</button>
      </form>

 
