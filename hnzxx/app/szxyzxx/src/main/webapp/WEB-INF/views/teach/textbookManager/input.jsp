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
									学年：
								</label>
								<div class="controls">
								<%-- <input type="text" id="schoolYear" name="schoolYear" class="span13" placeholder="" value="${textbookmanager.schoolYear}" style="width:200px;"> --%>
								
								<select id="xn" name="schoolYear" class="chzn-select"style="width:200px;" onchange="onChangeSchoolYear();">
									<option value="">请选择</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学期：
								</label>
								<div class="controls">
								<select id="termCode" name="termCode" class="chzn-select" style="width:160px;">
									
								</select>
								<%-- <input type="text" id="termCode" name="termCode" class="span13" placeholder="" value="${textbookmanager.termCode}" style="width:200px;"> --%>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
								     使用年级：
								</label>
								<div class="controls">
								<select id="nj" name="gradeId" class="chzn-select"style="width:200px;" style="width:200px;" onchange="getTeamSelect();">
									<option value="">请选择</option>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									 使用班级：
								</label>
								<div class="controls">
								<select id="bj" name="teamId" class="chzn-select"style="width:200px;" style="width:200px;" onchange="getNumFromTeam();">
									<option value="">请选择</option>
									 
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									 任课教师：
								</label>
								<div class="controls">
								<select id="teacherId" name="teacherId" class="chzn-select"style="width:200px;" style="width:200px;">
									<option value="">请选择</option>
									 
								</select>
								</div>
							</div>
							
							<%-- <span>学段:
				<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode');">
				<c:forEach items="${stageCodeMap}" var="map">
					<option value="${map.value }">${map.key }</option>
				</c:forEach>
				</select>
				</span>
				
				<span>科目:<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('gradeCode');">
				<option value="">请选择</option>
				</select></span>
				
				<span>年级:<select id="gradeCode" name="gradeCode" class="chzn-select"style="width:160px;" onchange="findTextBook('publisherId');">
				
				<option value="">请选择</option>
				</select></span>
				<span>版本:<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="findTextBook('volumn');">
				<option value="">请选择</option>
				</select></span>
				
				<span>册数:<select id="volumn" name="volumn" class="chzn-select"style="width:160px;">
				<option value="">请选择</option>
				</select></span> --%>
							<div class="control-group">
								<label class="control-label">
									学段：
								</label>
								<div class="controls">
								
								<select id="stageCode" name="stageCode" class="chzn-select"style="width:200px;" onchange="findTextBook('subjectCode');" style="width:200px;">
								<c:forEach items="${stageCodeMap}" var="map">
									<option value="${map.value }">${map.key }</option>
								</c:forEach>
								</select>
								
								<%-- <input type="text" id="stageCode" name="stageCode" class="span13" placeholder="" value="${textbookmanager.stageCode}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									科目：
								</label>
								<div class="controls">
								<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:200px;"  onchange="findTextBook('gradeCode');" style="width:200px;">
									<option value="">请选择</option>
								</select>
								<%-- <input type="text" id="subjectCode" name="subjectCode" class="span13" placeholder="" value="${textbookmanager.subjectCode}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
								     教材年级：
								</label>
								<div class="controls">
								<select id="gradeCode" name="gradeCode" class="chzn-select"style="width:200px;" onchange="findTextBook('publisherId');" style="width:200px;">
									<option value="">请选择</option>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									版本：
								</label>
								<div class="controls">
								<select id="publisherId" name="version" class="chzn-select"style="width:200px;"  onchange="findTextBook('volumn');" style="width:200px;">
									<option value="">请选择</option>
								</select>
								<%-- <input type="text" id="version" name="version" class="span13" placeholder="" value="${textbookmanager.version}"> --%>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									册次：
								</label>
								<div class="controls">
								<select id="volumn" name="volumn" class="chzn-select"style="width:200px;" style="width:200px;"   onchange="findTextBookConfig('name');" >
									<option value="">请选择</option>
								</select>
								<%-- <input type="text" id="volumn" name="volumn" class="span13" placeholder="" value="${textbookmanager.volumn}"> --%>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								
								<!-- <select id="name" name="name" class="chzn-select"style="width:200px;" style="width:200px;"   onchange="findTextBook('name');" >
									<option value="">请选择</option>
								</select> -->
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${textbookmanager.name}" style="width:200px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									书籍类型：
								</label>
								<div class="controls">
								<input type="text" id="type" name="type" class="span13" placeholder="" value="${textbookmanager.type}" style="width:200px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									国际标准书号：
								</label>
								<div class="controls">
								<input type="text" id="isbn" name="isbn" class="span13" placeholder="" value="${textbookmanager.isbn}" style="width:200px;">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									价格：
								</label>
								<div class="controls">
								<input type="text" id="price" name="price" class="span13" placeholder="" value="${textbookmanager.price}" style="width:200px;">
								</div>
							</div>
							
							
							<div class="control-group" id="teamCount" style="width:100%">
								<label class="control-label">
									数量：
								</label>
								<div class="controls">
								<input type="text" id="count" name="count" class="span13" placeholder="" value="${textbookmanager.count}" style="width:100px;" onchange="getNumFromTeam();">
								<input type="radio" checked="checked" id="countTypeTeam" name="countType"  placeholder="" value="0"  onchange="getNumFromTeam();">自动获取
								<input type="radio" id="countTypeHandle" name="countType"  placeholder="" value="1" >非自动获取
								
								</div>
							</div>
							
							<%-- <div class="control-group">
								<label class="control-label">
									添加类型，默认为从基础教材表获取 0 ，现在不存在手动添加 1：
								</label>
								<div class="controls">
								<input type="text" id="addType" name="addType" class="span13" placeholder="" value="${textbookmanager.addType}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
						<input type="hidden" id="addType" name="addType" class="span13" placeholder="" value="${textbookmanager.addType}">
							<input type="hidden" id="id" name="id" value="${textbookmanager.id}" />
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
	$(function() {
		
	    if('${textbookmanager.schoolYear}' == ""||'${textbookmanager.schoolYear}' == undefind){
	    	$.SchoolYearSelector({
				"selector" : "#schoolYear",
				"condition" : {
					
				},
				"afterHandler" : function(selector) {
					
		}
		});
	    }else{
	    	$.SchoolYearSelector({
				"selector" : "#schoolYear",
				"condition" : {
					
				},
				"selectedVal":'${textbookmanager.schoolYear}',
				"afterHandler" : function(selector) {
					
		}
		});
	    }
		
		
		  var optionTeam = {
				 "type" : "team",
				 "isEchoSelected" : true,
				 "yearSelectedVal":'2015',
				 "gradeSelectedVal" : '1', //要回显的年级唯一标识
				 "teamSelectedVal" : '1'//要回显的班级唯一标识
				 
		  } 
		
		$.initCascadeSelector(
				optionTeam);
	    
	    getNumFromTeam();
	    onChangeSchoolYear();
	    
	    
		checker = initValidator();
	});
	

	
	function getTeacherFromTeam(){
		var teamId = $('#bj').val();
		var gradeId = $('#nj').val();
		var $teacherId = $("#teacherId");
		$teacherId.html("");		
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textbookManager/findTeacherFromTeam",
			data : {
				'teamId' : teamId,
				'gradeId' : gradeId,
				'schoolYear':$('#xn').val()
				
			},
			success : function(data) {
				//data = eval("(" + data + ")");
				
				data = JSON.parse(data);
				
				$teacherId.append("<option value=''>请选择</option>");
				$.each(data, function(index,value ) {
					
					$teacherId.append("<option value='" + value.teacherId + "'>" + value.name + "</option>");
					
				});
			}
		});
		
	}

	
	function getNumFromTeam(){
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
		getTeacherFromTeam();
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
						if(('${textbookmanager.termCode}'==""||'${textbookmanager.termCode}'==undefined)){
							$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
						}else{
							$xq.append("<option value='" + value.code + "'  selected='selected'>" + value.name + "</option>");
						}
						
					});
				}
				
			});
	  }
	
	function initValidator() {
		return $("#textbookmanager_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
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