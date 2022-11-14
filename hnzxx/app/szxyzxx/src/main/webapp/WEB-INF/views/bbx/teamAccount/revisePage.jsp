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
							<label class="control-label">旧密码</label>
							<div class="controls">
								<input type="password" id="oldPassword" name="oldPassword" class="left_red {required : true,maxlength:16}" placeholder="请输入旧的密码" style="width:220px;height:30px;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">新密码</label>
							<div class="controls">
								<input type="password" id="newPassword" name="newPassword" class="left_red {required : true,maxlength:16}" placeholder="请输入6-16个字符的密码" style="width:220px;height:30px;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">确认密码</label>
							<div class="controls">
								<input type="password" id="checkPassword" name="checkPassword" class="left_red {required : true,maxlength:16}" placeholder="请再次输入新的密码" style="width:220px;height:30px;">
							</div>
						</div>
						<div class="form-actions tan_bottom_1">
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">确定</a>
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
				"oldPassword":{
					remote : {
						async : true,
						url : "${ctp}/bbx/teamAccount/checkPassword",
						type : "GET",
						dataType: "json",
						data : {
							'schoolId':$("#schoolId").val(),
							'teamId':$("#teamId").val()
						}
					}
				},
				"checkPassword":{
					equalTo:"#newPassword"
				}

			},
			messages : {
				"oldPassword":{
					remote:"旧密码不正确!"
				},
				"checkPassword":{
					equalTo:"输入错误，请再次输入新密码！"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#teamAccount_form");
			$requestData.newPassword = $("#newPassword").val(); 
			var url = "${ctp}/bbx/teamAccount/revisePassword";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success("修改成功");
					if (parent.core_iframe != null) {
						parent.core_iframe.search();
					} else {
						parent.window.location.reload();
					}
					$.closeWindow();
				} else {
					$.error("修改失败", 1);
				}
				loader.close();
			});
		}
	}
	
	
	

</script>
</html>