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
.form-horizontal .control-group{
	float:left;
	width:50%;
	height:31px;
}
.form-horizontal .control-label {
	width:100px;
}
.form-horizontal .controls {
    margin-left: 120px;
}
input[type="radio"]{
	position:relative;
	margin:0 10px;
	top:-1px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="textbookmanager_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>学年：
								</label>
								<div class="controls">
								
								<select id="xn" name="schoolYear" disabled="disabled" class="chzn-select"style="width:200px;" onchange="onChangeSchoolYear();">
									<option value="">请选择</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>学期：
								</label>
								<div class="controls">
								<select id="termCode" name="termCode"  class="chzn-select" style="width:200px;">
									
								</select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
								    <span class="red">*</span> 使用年级：
								</label>
								<div class="controls">
								<select id="nj" name="gradeId" class="chzn-select"style="width:200px;" style="width:200px;" onchange="getTeamSelect();">
									<option value="">请选择</option>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span> 使用班级：
								</label>
								<div class="controls">
								<select id="bj" name="teamId" class="chzn-select"style="width:200px;" style="width:200px;" onchange="getNumFromTeam();">
<!-- 									<option value="" >请选择</option> -->
									 
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span> 任课教师：
								</label>
								<div class="controls">
								<select id="teacherId" name="teacherId" class="chzn-select" style="width:200px;">
									<option value="">请选择</option>
									 
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>学段：
								</label>
								<div class="controls">
								
								<input type="text" id="stageCodeName" name="stageCodeName" readonly="readonly"  class="span13" placeholder="" value="${textbookManager.stageCodeName}" style="width:200px;">
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>科目：
								</label>
								<div class="controls">
								
								<input type="text" id="subjectCodeName" name="subjectCodeName" readonly="readonly"  class="span13" placeholder="" value="${textbookManager.subjectCodeName}" style="width:200px;">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>版本：
								</label>
								<div class="controls">
								
								<input type="text" id="versionName" name="versionName" readonly="readonly"  class="span13" placeholder="" value="${textbookManager.versionName}" style="width:200px;">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
								     <span class="red">*</span>教材详情：
								</label>
								<div class="controls">
							
								<input type="text" id="gradeCodeNameVolumn" name="gradeCodeNameVolumn" readonly="readonly"  class="span13" placeholder="" value="${textbookManager.gradeCodeName}${textbookManager.volumnName}" style="width:200px;">
								</div>
							</div>
							
							<%-- <div class="control-group">
								<label class="control-label">
									<span class="red">*</span>册次：
								</label>
								<div class="controls">
								
								<input type="text" id="volumnName" name="volumnName" readonly="readonly"  class="span13" placeholder="" value="${textbookManager.volumnName}" style="width:200px;">
								</div>
							</div> --%>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>名称：
								</label>
								<div class="controls">
								
								<input type="text" id="name" name="name" class="span13" readonly="readonly"  placeholder="" value="${textbookManager.name}" style="width:200px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>书籍类型：
								</label>
								<div class="controls">
								<input type="text" id="type" name="type" class="span13" readonly="readonly"  placeholder="" value="${textbookManager.type}" style="width:200px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									国际标准书号：
								</label>
								<div class="controls">
								<input type="text" id="isbn" name="isbn" class="span13" readonly="readonly"  placeholder="" value="${textbookManager.isbn}" style="width:200px;">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									价格：
								</label>
								<div class="controls">
								<input type="text" id="price" name="price" readonly="readonly" class="span13" placeholder="" value="${textbookManager.price}" style="width:200px;">
								</div>
							</div>
							
							
							<div class="control-group" id="teamCount" style="width:100%;">
								<label class="control-label">
									<span class="red">*</span>数量：
								</label>
								<div class="controls">
								<input type="text" id="count" name="count" class="span13" placeholder="" value="${textbookManager.count}" style="width:100px;" onchange="getNumFromTeam();">
								<input type="radio"  id="countTypeTeam" name="countType"  placeholder="" value="0"  onchange="getNumFromTeam();">自动获取
								<input type="radio" id="countTypeHandle" name="countType"  placeholder="" value="1" >非自动获取
								
								</div>
							</div>
							
							
						<div class="form-actions tan_bottom">
						<input type="hidden" id="addType" name="addType" class="span13" placeholder="" value="${textbookManager.addType}">
							<input type="hidden" id="id" name="id" value="${textbookManager.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	var countTypeInit = 0;
	$(function() {
		
			   var optionTeam = {
						 "type" : "team",
						 "isEchoSelected" : true,
						 "yearSelectedVal":'${textbookManager.schoolYear}',
						 "gradeSelectedVal" : '${textbookManager.gradeId}', //要回显的年级唯一标识
						 "teamSelectedVal" : '${textbookManager.teamId}',//要回显的班级唯一标识
					     "teamCallback" : function($this){
					    	 getTeacherFromTeam();
					     }
				  } 
			$.initCascadeSelector(
					optionTeam);
			   
			   onChangeSchoolYear();
			  
			   getNumFromTeam();
			   
	   
	});
	

	
	function getTeacherFromTeam(){
		var teamId = $('#bj').val();
		var gradeId = $('#nj').val();
		var $teacherId = $("#teacherId");
		/* $teacherId.html(""); */	
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textbookManager/findTeacherFromTeam",
			data : {
				'teamId' : teamId,
				'gradeId' : gradeId,
				'schoolYear':$('#xn').val()
				
			},
			success : function(data) {
				$teacherId.html("");
				data = JSON.parse(data);
				$teacherId.append("<option value=''>请选择</option>");
				$.each(data, function(index,value ) {
				
				     if('${textbookManager.teacherId}'==value.teacherId){
						$teacherId.append("<option selected value='" + value.teacherId + "'>" + value.name + "</option>");
					}else{
						
						$teacherId.append("<option value='" + value.teacherId + "'>" + value.name + "</option>");
					} 
					
					
				});
			}
		});
		
	}

	
	function getNumFromTeam(){
		if(countTypeInit == 0){
			if('${textbookManager.countType}'==1){
				$("#countTypeHandle").attr("checked","checked");
				$("#countTypeTeam").removeAttr("checked");
			}else{
				$("#countTypeTeam").attr("checked","checked");
				$("#countTypeHandle").removeAttr("checked");
			}
			
			countTypeInit = 1;
		}
		
		var val_payPlatform = $('#teamCount input[name="countType"]:checked ').val();
		var teamId = $('#bj').val();
		var gradeId = $('#nj').val();
		if(val_payPlatform == 0&&Number(teamId)>0){
			
			$.ajax({
				type : "post",
				url : "${ctp}/teach/textbookManager/findStudentNumFromTeam",
				data : {
					'teamId' : teamId,
					'gradeId' : gradeId
					
				},
				success : function(data) {
					var num = eval("(" + data + ")");
					$("#count").val(num);
				}
			});
			
		}else{
			var tempNum = 0;
			if($("#count").val() == ""){
				tempNum = 0;
			}else{
				tempNum = ("#count").val();
			}
			$("#count").val(tempNum);
		}
// 		getTeacherFromTeam();
	}
	
    function findTextBook(name) {
		var selectVal = '#' + name;
		$(selectVal).empty();
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textbookManager/textBookMap",
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'type' : name
			},
			success : function(data) {
				var map = eval("(" + data + ")");
				$.each(map, function(key, values) {
					$("<option value="+values+">" + key + "</option>").appendTo(selectVal);
				});
			}
		});
		
		if(name == "publisherId"){
			getTeamSelect();
		}
	}
    
    function findTextBookConfig(name) {
		var selectVal = '#' + name;
		$(selectVal).empty();
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textbookManager/textBook",
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'type' : name
			},
			success : function(data) {
				var dataObj=eval("("+data+")");
				$("#name").val(dataObj.name);
				$("#isbn").val(dataObj.isbn);
				$("#price").val(dataObj.price);
				$("#type").val(dataObj.type);
				 
				
			}
		});
	}
	
	 function onChangeSchoolYear(){
		 var xn = $("#schoolYear").val();
			if(xn == null || xn == ""){
				xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
			 }
		  $.getSchoolTerm({"schoolYear" : xn}, function(data, status) {
				var $xq = $("#termCode");
				if(data.length == 0){
					$xq.html("");
					$xq.append("<option value=''>请选择 </option>");
				}else{
					$xq.html("");
					$.each(data, function(index, value) {
						var code = value.code;
						
						if('${textbookManager.termCode}'==code){
							$xq.append("<option value='" + value.code + "'  selected='selected'>" + value.name + "</option>");
							
						}else{
							$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
						}
						
					});
				}
				
			});
	  }
	
	function initValidator() {
		return $("#textbookmanager_form").validate({
			errorClass : "myerror",
			rules : {
				"schoolYear":{
					required : true
				},
				"termCode":{
					required : true
				},
				"gradeId":{
					required : true
				},
				"teamId":{
					required : true
				},
				"teacherId":{
					required : true
				},"stageCode":{
					required : true
				},
				"subjectCode":{
					required : true
				},
				"gradeCode":{
					required : true
				},
				"version":{
					required : true
				},
				"volumn":{
					required : true
				},"name":{
					required : true
				},
				"type":{
					required : true
				},"isbn":{
					required : true
				},
				"price":{
					required : true
				},
				"count":{
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		checker = initValidator();
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#textbookmanager_form");
			var url = "${ctp}/teach/textbookManager/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/textbookManager/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>