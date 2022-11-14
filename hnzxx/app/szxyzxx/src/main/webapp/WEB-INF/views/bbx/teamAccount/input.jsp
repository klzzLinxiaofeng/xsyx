<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp }/res/css/bbx/bbx.css">
<style>
	.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
} 

</style>
</head>
<body>
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="teamAccount_form">
						<div class="control-group">
							<label class="control-label">学校</label>
							<div class="controls">
								<select id="schoolId" name="schoolId" disabled='disabled'><option value="${s.id }">${s.name }</option></select>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">班级</label>
							<div class="controls">
								<select id="teamId" name="teamId" disabled='disabled'><option value="${t.id }">${t.name }</option></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">密码</label>
							<div class="controls">
								<input type="password" id="password" name="password" class="left_red {required : true,maxlength:16}" placeholder="请输入6-16个字符的密码" style="width:220px;height:30px;">
							</div>
						</div>
						<div class="form-actions tan_bottom_1">
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">开通</a>
								<a href="javascript:void(0)" onclick="$.closeWindow();">取消</a>
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
	});

	function initValidator() {
		return $("#teamAccount_form").validate({
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
			var $requestData = formData2JSONObj("#teamAccount_form");
			$requestData.password = $("#password").val();
			var url = "${ctp}/bbx/teamAccount/openTeamAccount";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					if ("success" === data) {
						$.post("${ctp}/bbx/circle/openTeamCircle", {"teamId":$requestData.teamId}, function(data, status) {
							if ("success" === status) {
								if ("success" === data) {
								} else {
									$.error("班级圈子开通失败", 1);
								}
							}
						});
						$.success("开通成功");
						if (parent.core_iframe != null) {
// 							parent.core_iframe.window.location.reload();
							parent.core_iframe.search();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$.error("开通失败", 1);
					}
				}
				loader.close();
			});
		}
	}
	
	
	

</script>
</html>