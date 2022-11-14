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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="schoolservermanage_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<font style="color: red">*</font>学校：
								</label>
								<div class="controls">
								<select id="school" name="school"></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font style="color: red">*</font>服务器地址：
								</label>
								<div class="controls">
								<input type="text" id="eduServer" name="eduServer" class="span13" placeholder="" value="">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font style="color: red">*</font>服务器端口：
								</label>
								<div class="controls">
								<input type="text" id="eduPort" name="eduPort" class="span13" placeholder="" value="">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font style="color: red">*</font>api地址：
								</label>
								<div class="controls">
								<input type="text" id="apiServer" name="apiServer" class="span13" placeholder="" value="">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font style="color: red">*</font>api端口：
								</label>
								<div class="controls">
								<input type="text" id="apiPort" name="apiPort" class="span13" placeholder="" value="">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${schoolservermanage.id}" />
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

	$(function() {
// 		initCheckBtn();
		$.GroupSelector({
			"selector" : "#school"
		});
	});


	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#schoolservermanage_form").validate({
			errorClass : "myerror",
			rules : {
				"school" : {
					required : true
				},
				"eduServer" : {
					required : true
				},
				"eduPort" : {
					required : true
				},
				"apiServer" : {
					required : true
				},
				"apiPort" : {
					required : true
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
			var $id = $("#id").val();
			var gorupId = $("#school").val();
			var $requestData = formData2JSONObj("#schoolservermanage_form");
			$requestData.gorupId = gorupId;
			var url = "${ctp}/sys/schoolServerManage/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/sys/schoolServerManage/" + $id;
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