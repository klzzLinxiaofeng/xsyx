<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 60%;
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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<input type="hidden" id="isCK" value="${isCK}"/>
					<form class="form-horizontal tan_form" id="repair_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color:red">*</span>受理状态：
								</label>
								<div class="controls">
									<select name="dealStatus" id="dealStatus" class="span13">
										<option value="02">受理</option>
										<option value="03">不受理</option>
									</select>
								</div>
							</div>
							<div class="control-group" id="han">
								<label class="control-label">
									<span style="color:red">*</span>负责人、维修人：
								</label>
								<div class="controls">
									<input type="text" name="handler" id="handler" class="span13 {maxlength:50}"/>
								</div>
							</div>
							<div class="control-group" id="pho">
								<label class="control-label">
									<span style="color:red">*</span>负责人联系电话：
								</label>
								<div class="controls">
									<input type="text" name="handlerPhone" id="handlerPhone" class="span13 {isTel:true}"/>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${id}" />
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
	var isCK = $("#isCK").val();
	if(isCK!=""){
		$(".controls").disable();
		$(".form-actions").hide();
	}
	$(function() {
		$("#dealStatus").chosen();
		checker = initValidator();
		isshow();
		});
	
	function isshow(){
	   $("#dealStatus").on("change", function(){
	        if($(this).val() == '03'){
				$("#han").css('display','none'); //隐藏  
	        	$("#pho").css('display', 'none'); 
	            $("#handler").val("");
	            $("#handlerPhone").val("");
	        }else{
	        	$("#han").css('display','block'); //显示 
	        	$("#pho").css('display', 'block'); 
	        }
	    })
	}
	function initValidator() {
		return $("#repair_form").validate({
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
			var $requestData = formData2JSONObj("#repair_form");
			var url = "${ctp}/oa/repair/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/repair/" + $id;
			}
			if($("#dealStatus").val()=='02'){
				if( $("#handler").val()=="" || $("#handlerPhone").val()==""){
					$.alert("维修人或联系电话不能为空！");
					return;
				}
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