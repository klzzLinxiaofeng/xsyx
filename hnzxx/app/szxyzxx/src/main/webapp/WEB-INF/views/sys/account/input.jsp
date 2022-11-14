<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 40%;
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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="account_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									应用：
								</label>
								<div class="controls">
								<select id="appId" name="appId" class="span13"></select>
							</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									账号的业务前缀：
								</label>
								<div class="controls">
								<input type="text" id="preffix" name="preffix" class="span13"
									placeholder="" value="">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									除前缀之外还需的位数：
								</label>
								<div class="controls">
								<input type="text" id="suffixBit" name="suffixBit" class="span13"
									placeholder="" value="">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									需要生成的账号个数：
								</label>
								<div class="controls">
								<input type="text" id="count" name="count" class="span13"
									placeholder="" value="">
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
	$(function() {
		checker = initValidator();
		
		$.AppSelector({
			"selector" : "#appId"
		});
	});
	
	function initValidator() {
		return $("#account_form").validate({
			errorClass : "myerror",
			rules : {
				"preffix" : {
					
				},
				"suffixBit" : {
					required : true,
					isInteger : true,
					min : 5
				},
				"count" : {
					required : true,
					isInteger : true,
					max : 1000
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#account_form");
			var url = "${ctp}/sys/account/creator";
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