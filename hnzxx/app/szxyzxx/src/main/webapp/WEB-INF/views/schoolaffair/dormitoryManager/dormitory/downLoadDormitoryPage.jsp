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
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
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
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form action="${pageContext.request.contextPath}/schoolaffair/dormitory/downLoadDormitoryInfo" method="get"  id="dormitory_downLoad_form" class="form-horizontal left-align form-well" novalidate="novalidate">	
							<div class="control-group">
								<label class="control-label">
									大楼名称：
								</label>
								<div class="controls">
								    <select id="floorName" name="floorName" class="span4 chzn-select"  ></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									宿舍楼号：
								</label>
								<div class="controls">
								<input type="text" id="floorCode" name="floorCode" class="span4" placeholder="" value="" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									入住性别：
								</label>
								<div class="controls">
								      <input type="radio" id="sex" name="sex" class="span5"  value="1" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="sex" name="sex" class="span5"  value="2" />女
								</div>
							</div>
							
							
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="submit"
									onclick="downLoadModel();">导出模板数据</button>
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
		
		$.HqFloorSelector({
			   "selector" : "#floorName",
			   "afterHandler" : function() {
				}
		   });
		$("#floorName").on("change", function() {
			var $floorName = $("#floorName").val();
			if($floorName != "" && $floorName != null){
				$("#floorCode").val($floorName);
			}
		
		});
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#dormitory_downLoad_form").validate({
			errorClass : "myerror",
			rules : {
				
				"floorName":{
					required : true
				}
				
			},
			messages : {
				
			}
		});
	}
	
	
	
	function downLoadModel() {
	if (checker.form()) {
		var loader = new loadLayer();
			var $requestData = formData2JSONObj("#dormitory_downLoad_form");
			$requestData._method="get";
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitory/downLoadParentInfo";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('保存成功');
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error(data.info);
						
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