<%@ page language="java" import="platform.szxyzxx.web.common.contants.SysContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>角色创建</title>
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
	width:22%;
	display:inline-block;
	padding-left:10px;
}
.chzn-container .chzn-results{max-height:80px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid" >
		<div class="span4">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal tan_form" id="role_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>角色编号</label>
							<div class="controls">
								<input type="text" id="code" name="code" class="span4" placeholder="请输入唯一角色编号, 不能为空" value="${role.code}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>角色名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="请输入角色名称, 不能为空" value="${role.name}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<shiro:hasRole name="${sca:getSuperAdminCode()}">
							<div class="control-group">
								<label class="control-label">所属组</label>
								<div class="controls">
									<select class="span4" id="groupId" name="groupId" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
							</div>
							<script type="text/javascript">
							
								$(function() {
									$.GroupSelector({
										"selector" : "#groupId",
										"selectedVal" : "${role.groupId}"
									});
								});
							
							</script>
						</shiro:hasRole>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>类型</label>
							<div class="controls">
								<select class="span4" id="userType" name="userType" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="">请选择</option>
									<option value="<%= SysContants.USER_TYPE_ADMIN %>"><%= SysContants.USER_TYPE_ADMIN_STR %></option>
									<option value="<%= SysContants.USER_TYPE_TEACHER %>"><%= SysContants.USER_TYPE_TEACHER_STR %></option>
									<option value="<%= SysContants.USER_TYPE_STUDENT %>"><%= SysContants.USER_TYPE_STUDENT_STR %></option>
									<option value="<%= SysContants.USER_TYPE_PARENT %>"><%= SysContants.USER_TYPE_PARENT_STR %></option>
								</select>
							</div>
						</div>
						<%-- 						<jsp:include page="/views/embedded/xx_select.jsp"> --%>
<%-- 							<jsp:param name="xxDm" value="${role.xxDm}"></jsp:param> --%>
<%-- 						</jsp:include> --%>
<!-- 						<div class="control-group"> -->
<!-- 							<label class="control-label">启用</label> -->
<!-- 							<div class="controls"> -->
<%-- 								<select class="span4" id="jyBj" name="jyBj" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}> --%>
<%-- 									<option value="<%= AbandonedDefaultStatus.ENABLED %>">是</option> --%>
<%-- 									<option value="<%= AbandonedDefaultStatus.DISABLE %>">否</option> --%>
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
						
						
							<div class="form-actions tan_bottom">
								<input type="hidden" id="id" name="id" value="${role.id}"/>
								<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
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
		$("#userType").val("${role.userType}").chosen();
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#role_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength : 30
				},
// 				"code" : {
// 					required : true,
// 					accCheck : true,
// 					maxlength : 30,
// 					remote : {
// 						async : false,
// 						type : "GET",
// 						url  : "${pageContext.request.contextPath}/sys/role/checker",
// 						data : {
// 							'dxlx' : 'code',
// 							'code' : function() {
// 								return $("#code").val();
// 							},
// 							'id' : function() {
// 								return $("#id").val();
// 							}
// 						}
// 					}
// 				},
				"userType" : {
					required : true
				}
			},
			messages : {
				"code" : {
					remote : "编码已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#role_form");
			var url = "${pageContext.request.contextPath}/sys/role/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/sys/role/" + $id;
			}
			var loader = new loadLayer();
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
					} else if ("roleConflict" === data.info) {
						$.error('当前角色编号已在当前学校存在');
					} else {
						$.error('操作失败');
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