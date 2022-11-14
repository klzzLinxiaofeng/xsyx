<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加科目</title>
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
					<form class="form-horizontal" id="subject_form" action="javascript:void(0);">
<!-- 						<div class="control-group"> -->
<!-- 							<label class="control-label"><font style="color:red">*</font>学段</label> -->
<!-- 							<div class="controls"> -->
<!-- 								<select id="stageCode" id="stageCode" name="stageCode" class="span4"> -->
<%-- 			                           	<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="stage"> --%>
<%-- 			                           		<option value="${stage}"> --%>
<%-- 			                           			<jc:cache tableName="jc_stage" echoField="name" value="${stage }" paramName="code"></jc:cache> --%>
<!-- 			                           		</option> -->
<%-- 			                           	</c:forEach> --%>
<!-- 	                           	</select> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
						<input type="hidden" id="stageCode" name="stageCode" value="${sessionScope[sca:currentUserKey()].stageCode}">
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>课程范围</label>
							<div class="controls">
								 <select name="subjectClass" id="subjectClass" class="span4" onchange="getSubjectClass(this);">
                           		</select>
							</div>
						</div>
						
						<!-- 公共课程选择 -->
						<div class="control-group" id="publicSubjectId">
							<label class="control-label"><font style="color:red">*</font>公共科目名称</label>
							<div class="controls">
								<select  name="publicSubject" id="publicSubject" class="span4" >
<!-- 								onchange="getPublicSubject(this);" -->
<!-- 									onchange="checkPublicSubject(this);" -->
 								</select>
							</div>
						</div>
						
						<!-- 非公共课程 -->
						<div class="control-group" id="locationSubjectId">
							<label class="control-label"><font style="color:red">*</font>科目名称</label>
							<div class="controls">
								<input type="text" name="name" id="name" value="" class="span4"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">课程类别</label>
							<div class="controls">
								<select name="subjectType" id="subjectType" class="span4">
                           		</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">课程属性</label>
							<div class="controls">
								 <select name="subjectProperty" id="subjectProperty" class="span4">
                           		 </select>	
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">课程性质</label>
							<div class="controls">
								<select name="subjectCharacter" id="subjectCharacter" class="span4">
                                </select>
							</div>
						</div>
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
		//课程范围
		$.jcGcSelector("#subjectClass", {tc : "JY-KCFW"}, "", function() {
			
		});
		//课程类别
		$.jcGcSelector("#subjectType", {tc : "JY-KCLB"}, "", function() {
			
		});
		//课程属性
		$.jcGcSelector("#subjectProperty", {tc : "JY-KCSX"}, "", function() {
			
		});
		//课程性质
		$.jcGcSelector("#subjectCharacter", {tc : "JY-KCXZ"}, "", function() {
			
		});
 		checker = initValidator();
 		
 		var stageCode = document.getElementById("stageCode");
 		//getStageName(stageCode);
 		$("#locationSubjectId").hide();
 		$("#publicSubjectId").hide();
	});
	
	function getFullName(obj){
		var name = $(obj).text();
		$("#name").val(name);
	}
	
	function getSubjectClass(obj){
		var subjectVal = obj.value;
		if(subjectVal=="1"){//公共课程
			$("#name").val("");
			$("#locationSubjectId").hide();
			$("#publicSubjectId").show();
			$("#publicSubject").attr("disabled",false);
			$("#name").attr("disabled",true);
			//获得本校的公共课程
			getPublicSubject(obj);
		}else if(subjectVal=="2" || subjectVal=="3"){//地方课程和校本课程
			$("#locationSubjectId").show();
			$('#publicSubject').empty();
			$('#publicSubject').next("label").remove();
			$("#publicSubjectId").hide();
			$("#publicSubject").attr("disabled",true);
			$("#name").attr("disabled",false);
		}
	}
	
// 	function getPublicSubject(obj){
// 		var subjectText = obj.options[obj.selectedIndex].text;
// 		$("#name").val(subjectText);
// 	}

// 	function checkPublicSubject(obj) {
// 		var s = $("#publicSubject").val();
// 		alert(s)
// 	}

	function getPublicSubject(obj){
		url = "${pageContext.request.contextPath}/teach/subject/getPublicSubject";
		$.post(url, function(data, status) {
			if("success" === status) {
				var list = eval("(" + data + ")");
				var $publicSubject = $("#publicSubject");
				$publicSubject.append("<option value=''>请选择</option>");
				for(var i = 0; i <= list.length; i++) {
					$publicSubject.append("<option value="+list[i].code+">"+list[i].name+"</option>");
				}
			}
		});
	}
	
	
	
	function getStageName(obj){
		var stageCode = obj.value;
		var url = "${pageContext.request.contextPath}/teach/subject/getAjaxPubjectSubjectList";
		var aj = $.ajax({
		    url: url,
		    data:'stageCode=' + stageCode,
		    type:'post',
		    cache:false,
		    dataType:'json',
		    success:function(data) {
		    	loadTable(data);
		     },
		     error : function() {
		         $.alert("异常！");    
		     }
		});
	}
	
	function loadTable(data){
		var subjectTemp = "<option value=''>请选择</option>";
		for (var i = 0, len = data.length; i < len; i++) {
			subjectTemp += "<option value="+data[i].code+">"+data[i].name+"</option>"
		}
		$("#publicSubject").html(subjectTemp);
	}
	
	function initValidator() {
		return $("#subject_form")
			.validate(
					{
						errorClass : "myerror",
						rules : {
							"stageCode" : {
								required : true
							},
							"name" : {
								required : true,
								remote : {
									async : false,
									type : "GET",
									url : "${pageContext.request.contextPath}/teach/subject/checkerSubjectName",
									data : {
										'dxlx' : 'name',
										'name' : function() {
											return encodeURI($("#name").val()) ;
										},
										'stageCode':function(){
											return $("#stageCode").val();
										},
										'subjectClass':function(){
											return $("#subjectClass").val();
										}
									}
								}
							},
							"subjectClass":{
								required : true,
// 								remote : {
// 									async : false,
// 									type : "GET",
// 									url : "${pageContext.request.contextPath}/teach/subject/checkerSubjectClass",
// 									data : {
// 										'dxlx' : 'subjectClass',
// 										'subjectClass' : function() {
// 											return $("#subjectClass").val();
// 										}
// 									}
// 								}
							},
							"publicSubject" : {
								required : true,
								remote : {
									async : false,
									type : "GET",
									url : "${pageContext.request.contextPath}/teach/subject/checkerSubject",
									data : {
										'dxlx' : 'publicSubject',
										'publicSubject' : function() {
											return $("#publicSubject").val();
										}
									}
								}
							}
						},
						
						messages : {
							"stageCode" : {
								required:"学段必选"
							},
							"name":{
								required : "课程名称必选",
								remote:"课程已存在"
							},
							"subjectClass":{
								required : "课程范围必选",
// 								remote:"你没有权根选公共课程"
							},
							"publicSubject" : {
								required : "公共课程必选",
								remote : "公共课程已存在,不允许重复添加"
							}
						}
					});
	}

	function saveOrUpdate() {
		if(checker.form()){
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#subject_form");
			var url = "${pageContext.request.contextPath}/teach/subject/addSubject";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
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
					$.error("服务器异常！");
				}
				loader.close();
			});
		}
	}
</script>
</html>