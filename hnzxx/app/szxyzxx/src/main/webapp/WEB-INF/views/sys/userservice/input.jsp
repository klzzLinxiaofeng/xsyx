<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>用户创建</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

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
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="user_form">
						<input name="schoolId" value="${schoolInfo.id}" type="hidden"/>
						<input name="schoolName" value="${schoolInfo.name}" type="hidden"/>
						<input name="schoolEnglishName" value="${schoolInfo.englishName}" type="hidden"/>
						<div class="control-group">
							<label class="control-label"><span style="color: red;">*</span>后缀名</label>
							<div class="controls">
								<input type="text" id="suffix" name="suffix" class="span4" placeholder="请输入后缀名, 不能为空">
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
	});

	function initValidator() {
		return $("#user_form").validate({
			errorClass : "myerror",
			rules : {
				"suffix": {
					required : true,
					maxlength : 40
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#user_form");
			var url = "${pageContext.request.contextPath}/user/service/customReg";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$("#suffix").val("");
						$.error('后缀名已存在，请重新输入！');
					}
				} else {
					$.error('操作失败');
				}
				loader.close();
			});
		}
	}
	
</script>
</html>