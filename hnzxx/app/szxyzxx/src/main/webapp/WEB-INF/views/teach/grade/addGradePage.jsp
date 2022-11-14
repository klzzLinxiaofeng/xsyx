<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增年级</title>
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

</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="grade_form" name="grade_form">
						<input type="hidden" id="schoolId" name="schoolId" value="${schoolId}"/>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>学年:</label>
							<div class="controls">
<%-- 								<input type="hidden" id="schoolYear" name="schoolYear" value="${schoolTermCurrent.schoolYear }" /> --%>
<%-- 								<span class="span4">${schoolTermCurrent.schoolYearName }</span> --%>
								<select id="schoolYear" name="schoolYear" class="span4" onchange="getCode();" ></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>学段:</label>
							<div class="controls">
								<select id="stageCode" name="stageCode" onchange="getStageName();" class="span4">
			                           	<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="stage">
			                           		<c:if test="${stage != -1}">
				                           		<option value="${stage}">
				                           			<jc:cache tableName="jc_stage" echoField="name" value="${stage}" paramName="code"></jc:cache>
				                           		</option>
			                           		</c:if>
			                           	</c:forEach>
	                           	</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>年级:</label>
							<div class="controls">
								<select id="uniGradeCode" name="uniGradeCode" onchange="getCode();" class="span4">
		                        </select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>校内名称:</label>
							<div class="controls">
								 <input type="text" id="name" name="name" value="" class="span4" >
							</div>
						</div>
						<input type="hidden" id="fullName" name="fullName" value="" class="span4" >
						<input type="hidden" id="gradeNumber" name="gradeNumber" value="" class="span4" >
<!-- 						<div class="control-group"> -->
<!-- 							<label class="control-label">年级全称</label> -->
<!-- 							<div class="controls"> -->
<!-- 								 <input type="text" id="fullName" name="fullName" value="" class="span4" > -->
<!-- 							</div> -->
<!-- 						</div> -->
 						<div class="control-group">
 							<label class="control-label"></label>
 							<div class="controls">
								<input type="hidden" id="code" name="code" value="" readonly="readonly" class="span4" />
 							</div>
 						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${role.id}"/>
							<c:if test="${isCK == null || isCk == ''}">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker="";
	$(function() {
		$.SchoolYearSelector({
			"selector" : "#schoolYear",
			"isUseChosen" : false
		});
		$("#stageCode").trigger("change");
		checker =  initValidator();
	});
	
	function getStageName(){
		var stage_ =$("#stageCode").val();
		$("#code").next("label").remove();
		$('#uniGradeCode').empty();
		$("#code").val("");
		$("#name").val("");
		$("#fullName").val("");
		$("#gradeNumber").val("");
		$.SchoolSystemSelector({
			"selector" : "#uniGradeCode",
			"condition" : {"stageCode" : stage_},
			"isUseChosen" : false,
			"afterHandler" : function(selector) {
				
			}
		});
 		//getCode();
	}
	
	function getCode(){
		$("#code").next("label").remove();
		var uniGradeCodeValue = $("#uniGradeCode").find("option:selected").val();
		if (typeof(uniGradeCodeValue) == "undefined" || uniGradeCodeValue == "") { 
			$("#name").val("");
			$("#fullName").val("");
			$("#code").val("");
			$("#gradeNumber").val("");
		}else{
			//班级名称
			$("#name").val($("#uniGradeCode").find("option:selected").text());
			//全名
			var gradeName = $("#uniGradeCode").find("option:selected").text();
			var stageName = $("#stageCode").find("option:selected").text();
			var temp_stage = $.trim(gradeName);
			$("#fullName").val(temp_stage);
			
			var stageNumber = $("#uniGradeCode").find("option:selected").attr("data-num");
			$("#gradeNumber").val(stageNumber);
			
			//代码
			var schoolId = $("#schoolId").val();//schoolId
			var schoolYearValue = $("#schoolYear").val();
			if(schoolYearValue==""){
				$.error("请先去设定当前学年");
			}else{
				var temp = schoolId+"-"+schoolYearValue+"-"+uniGradeCodeValue;
				$("#code").val(temp);
			}
			
		} 
	}
	

    function initValidator() {
		return $("#grade_form").validate(
						{errorClass : "myerror",
							rules : {
								"schoolYear":{
									required : true
								},
								"stageCode" : {
									required : true
								},
								"uniGradeCode" : {
									required : true
								},
								"code":{
// 									required : true,
									minlength : 3,
									maxlength : 20,
									remote : {
										async : false,
										type : "GET",
										url : "${pageContext.request.contextPath}/teach/grade/checkerGradeCode",
										data : {
											'dxlx' : 'code',
											'name' : function() {
												return $("#code").val();
											}
										}
									}
								},
								"name":{
									required : true,
									minlength : 2,
									maxlength : 20
								}
							},
							messages : {
								"schoolYear":{
									required : "学年为必填项，请先设定当前学年"
								},
								"stageCode" : {
									required:"学段必选"
								},
								"uniGradeCode":{
									required : "学段年级必选"
								},
								"code":{
									remote:"年级已存在"
								},
								"name":{
									required : "班级名称必填"
								}
								
							}
						});
	}
    
    function saveOrUpdate() {
    	if(checker.form()){
    		var loader = new loadLayer();
    		var $requestData = formData2JSONObj("#grade_form");
    		var url = "${pageContext.request.contextPath}/teach/grade/addGrade";
    		loader.show();
    		$.post(url, $requestData, function(data, status) {
    			if("success" === status) {
    				//data = eval("(" + data + ")");
    				if("success" === data) {
    					$.success('保存成功');
    					if(parent.core_iframe != null) {
    							parent.core_iframe.window.location.reload();
    						} else {
    							parent.window.location.reload();
    						}
    					$.closeWindow();
    				} else {
    					$.error("保存失败");
    				}
    			}else{
    				$.error("服务器异常");
    			}
    			loader.close();
    		});
    	}
		
	}
</script>
</html>