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
						<div class="control-group">
							<label class="control-label">用户账号</label>
							<div class="controls">
								<input type="text" id="userName" name="userName" class="span4" placeholder="请输入用户账号, 不能为空" value="${account.userName}" ${account != null ? "disabled='disabled'" : ""}>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">用户密码</label>
							<div class="controls">
								<input type="password" id="password" name="password"
									class="span4" placeholder="请输入用户密码, 不能为空"
									value="${account.password}" ${account != null ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
<%-- 						<c:if test='${sessionScope[sca:currentUserKey()].schoolId == null || sessionScope[sca:currentUserKey()].schoolId == ""}'> --%>
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"><font style="color: red">*</font>所属学校</label> -->
<!-- 								<div class="controls"> -->
<%-- 									<select class="span4" id="groupOwnerId" name="groupOwnerId" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}> --%>
										
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<%-- 						</c:if> --%>
						
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>用户状态</label>
							<div class="controls">
								<select class="span4" id="state" name="state" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
<%-- 									<option value="<%=UserContants.STATE_NORMAL%>"><%=UserContants.STATE_NORMAL_STR%></option> --%>
<%-- 									<option value="<%=UserContants.STATE_INVAILD%>"><%=UserContants.STATE_INVAILD_STR%></option> --%>
<%-- 									<option value="<%=UserContants.STATE_LOCK%>"><%=UserContants.STATE_LOCK_STR%></option> --%>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">失效时间</label>
							<div class="controls">
								<input type="text" id="validDate" name="validDate" class="span4"
									placeholder="请输入失效时间"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${account.validDate}"></fmt:formatDate>'
									onclick="WdatePicker();"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${account.id}" />
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">确定</button>
							</c:if>
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
		
		$.SchoolSelector({
			"selector" : "#groupOwnerId",
		});
		
		$.jcGcSelector("#state", {"tc" : "XY-YH-ZHZT"}, "${account.state}", function() {});
	});

	function initValidator() {
		return $("#user_form").validate({
			errorClass : "myerror",
			rules : {
				"userName" : {
					required : true,
					accCheck : true,
					maxlength : 30,
					remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/sys/user/checker",
						data : {
							'dxlx' : 'userName',
							'username' : function() {
								return $("#userName").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"password" : {
					required : true,
					minlength : 6,
					maxlength : 16,
					accCheck : true
				},
				"state" : {
					required : true
				}
			},
			messages : {
				"userName" : {remote : "账号已存在",  maxlength : "最多字符不能超过xxx"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate($this) {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = {};
			$requestData.userName = $("#userName").val();
			$requestData.groupOwnerId = $("#groupOwnerId").val();
			$requestData.password = $("#password").val();
			$requestData.state = $("#state").val();
			$requestData.validDate = $("#validDate").val();
			var url = "${pageContext.request.contextPath}/sys/user/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/sys/user/" + $id;
			}
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
						$.error('操作失败');
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