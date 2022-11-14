<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title></title>
<style>
	.row-fluid .span4 {
	width: 227px;
}
.row-fluid .span3 {
	width: 125px;
}

.myerror {  
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.form-horizontal .control-group.dxq_gl{
	border-bottom:0 none;
	padding:60px 0 20px 0;
}
.dxq_gl .control-label{
	width:240px;
}
.dxq_gl .controls {
    margin-left: 260px;
}
</style>
</head>
<body style="background-color:#fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0;">
				<div class="widget-container" style="padding:0 20px;">
					<form class="form-horizontal" id="membership_form" style="padding-bottom:0">
						<div class="control-group dxq_gl">
							<label class="control-label"><span style="color:#ff0000;">*</span>总学校名称：</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4 left_red {required : true,stringCheck:true,maxlength:50}" placeholder="请输入总学校名称" >
                            </div>
						</div>
						<div class="new_btn">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
								<button class="btn btn-blue" type="button" onclick="$.closeWindow();;">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#membership_form").validate({
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
			var $requestData = formData2JSONObj("#membership_form");
			var url = "${ctp}/teach/membership/creator";
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
</body>
</html>