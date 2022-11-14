<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增班级</title>
<style>
	.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
.row-fluid .span4 {
	width: 227px;
}

input[class*="span"], select[class*="span"], textarea[class*="span"],
	.uneditable-input[class*="span"], .row-fluid input[class*="span"],
	.row-fluid select[class*="span"], .row-fluid textarea[class*="span"],
	.row-fluid .uneditable-input[class*="span"] {
	width: 227px;
}
</style>
<script type="text/javascript">
	function getName(){

		var teamNumberValue = $("#teamNumber").val();
		var gradeFullName = $("#gradeFullName").val();
		var gradeName = $("#gradeName").val();
		var gradeCode = $("#gradeCode").val();
		
		var fullName_=gradeFullName+"("+teamNumberValue+")班";
		var name_ = gradeName+"("+teamNumberValue+")班";
		var code_ = gradeCode+"-"+teamNumberValue;
		
		$("#fullName").val(fullName_);
		$("#name").val(name_);
		$("#code").val(code_);
		
		var schoolYear = $("#schoolYear").val();      //获得年级的学年记录
		var gradeNumber = $("#gradeNumber").val();   //获得年级在学段中的顺序
		var stageCode = $("#stageCode").val();       //获得通用学段代码
		//X-小学、C-初中、G-高中、O-其他
		var stage = "";
		if(stageCode == "2") {  
			stage = "X";
		} else if(stageCode == "3") {
			stage = "C"
		} else if(stageCode == "4") {
			stage = "G";
		}else {
			stage = "O";
		}
		schoolYear = Number(schoolYear);
		var stageYear = schoolYear - gradeNumber + 1;  //例如2014-15学年的初三，初三年级次序为，2014-3+1=2012
		stageYear = stageYear.toString();
		stageYear = stageYear.substring(2, 4);         //截取年份后两位
		if(teamNumberValue.length < 2) {
			teamNumberValue = "0" + teamNumberValue;
		}
// 		if(teamNumberValue < 10) {
// 			if(teamNumberValue.length == 1) {
// 				teamNumberValue = "0" + teamNumberValue;
// 			}else if(teamNumberValue.length == 2){
// 				teamNumberValue = teamNumberValue;
// 			}else if(teamNumberValue.length > 2) {
// 				teamNumberValue = teamNumberValue.substring(teamNumberValue.length - 2);
// 			}
// 		}
		var code2 = stage + stageYear + teamNumberValue  //C1501
		$("#code2").val(code2);
		
// 		document.getElementById("fullName").value=gradeFullName+"("+teamNumberValue+")班";
// 		document.getElementById("name").value=
// 		document.getElementById("code").value=
	}
</script>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<input type="hidden"  id="gradeFullName" name="gradeFullName" value="${grade.fullName }"/>
					<input type="hidden"  id="gradeName" name="gradeName" value="${grade.name }"/>
					<input type="hidden"  id="gradeCode" name="gradeCode" value="${grade.code }"/>
					<input type="hidden"  id="stageCode" name="stageCode" value="${grade.stageCode }"/>
					<input type="hidden"  id="gradeNumber" name="gradeNumber" value="${grade.gradeNumber }"/>
					<form class="form-horizontal" id="team_form" action="javascript:void(0);">
						<input type="hidden"  id="schoolId" name="schoolId" value="${grade.schoolId }"/>
						<input type="hidden"  id="gradeId" name="gradeId" value="${grade.id }"/>
						<input type="hidden"  id="schoolYear" name="schoolYear" value="${grade.schoolYear }"/>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>顺序编号</label>
							<div class="controls">
								<input type="text" id="teamNumber" name="teamNumber" value="" onkeyup="getName()" class="span4" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>班级名称</label>
							<div class="controls">
								 <input type="text" id="fullName" name="fullName" value="" class="span4" disabled="disabled">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>校内名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" value="" class="span4" >
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>班级编号</label>
							<div class="controls">
								<input type="text" id="code2" name="code2" value="" class="span4" disabled="disabled">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">班级类别</label>
							<div class="controls">
								<select id="teamType" name="teamType" class="span4">
									
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">班级属性</label>
							<div class="controls">
								 <select id="teamProperty" name="teamProperty" class="span4">
								 </select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								 <input type="hidden" id="code" name="code" value=""/>
							</div>
						</div>
<!-- 						 <div class="control-group"> -->
<!-- 							<label class="control-label"><font style="color:red">*</font>启用时间</label> -->
<!-- 							<div class="controls"> -->
<!-- 								 <input type="text" id="beginDate" name="beginDate" value="" onfocus="WdatePicker();" class="span4" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="control-group"> -->
<!-- 							<label class="control-label"><font style="color:red">*</font>结束时间</label> -->
<!-- 							<div class="controls"> -->
<!-- 								 <input type="text" id="finishDate" name="finishDate" value="" onfocus="WdatePicker();" class="span4" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var checker;
$(function () {
	//班级类别
	$.jcGcSelector("#teamType", {tc : "XY-JY-BJSX"}, "0", function() {
		
	});
	//班级属性
	$.jcGcSelector("#teamProperty", {tc : "JY-ZXXBJLX"}, "", function() {
		
	});
	checker = initValidator();
});

function initValidator() {
	return $("#team_form")
			.validate(
					{
						errorClass : "myerror",
						rules : {
							"teamNumber" : {
							    required : true,
							    digits : true,
							    min : 1
							},
							"fullName" : {
								required : true
							},
							"name":{
								required : true
							},
// 							"code":{
// // 								required : true,
// 								minlength : 3,
// 								maxlength : 20,
// 								remote : {
// 									async : false,
// 									type : "GET",
// 									url : "${pageContext.request.contextPath}/teach/team/checkerTeamCode",
// 									data : {
// 										'dxlx' : 'code',
// 										'gradeId' : function() {
// 											return $("#gradeId").val();
// 										},
// 										'name' : function() {
// 											return $("#code").val();
// 										}
// 									}
// 								}
// 							},
							"code2" : {
								required : true
							}
						},
						messages : {
// 							"code":{
// 								remote:"班级已存在"
// 							},
							"code2" : {
								remote : "班级在学校的唯一编号，编号以入学年份为准，不随年级升迁而改变"
							}
						}
					});
	}
	
	function saveOrUpdate() {
		if(checker.form()){
			var loader = new loadLayer();
			var gradeId = $("#gradeId").val();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#team_form");
			var url = "${pageContext.request.contextPath}/teach/team/addTeam";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('保存成功');
						if(parent.core_iframe != null) {
								parent.core_iframe.window.ajaxFunction(gradeId, null);
							} else {
								parent.window.ajaxFunction(gradeId, null);
							}
						$.closeWindow();
					}else if("fail" === data.info) {
						$.error("班级已经存在，不允许再添加");
					}else {
// 						$.error("保存失败");
					}
				}else{
					$.error("保存失败");
// 					$.error("服务器异常");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>