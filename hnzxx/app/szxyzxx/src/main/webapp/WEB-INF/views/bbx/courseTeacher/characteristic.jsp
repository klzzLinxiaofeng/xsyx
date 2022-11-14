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
/* .form-actions{ margin-top:0;margin-bottom:0;padding:15px 20px;} */
</style>
</head>
<body style="background-color: #F3F3F3 !important;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal padding15" style="padding: 15px;" id="bwuserinfo_form">
						<input type="hidden" id="userId" name="userId" value="${userId}" />					
						<div class="control-group">
							<textarea type="text" id="characteristic" name="characteristic" class="span12" placeholder="请输入上课特色" 
							style="resize: none;width: 550px; height: 100px;max-width: 650px;max-height: 100px;">
							</textarea>
						</div>											
					</form>
					<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwUserInfo.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		
		$("#characteristic").val("${bwUserInfo.characteristic}");
	});
	
	function initValidator() {
		return $("#bwuserinfo_form").validate({
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
// 			var id = $("#id").val();
			var userId = $("#userId").val();
			var characteristic = $("#characteristic").val();
			var $requestData = formData2JSONObj("#bwuserinfo_form");
			$requestData.userId = userId;
			var url = "${ctp}/bbx/courseTeacher/setCharacteristic";
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