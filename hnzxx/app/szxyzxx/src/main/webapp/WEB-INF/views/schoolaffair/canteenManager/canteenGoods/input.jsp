<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>菜单创建</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
}  
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
					<form class="form-horizontal tan_form" id="canteengoods_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>商品编号</label>
								<div class="controls">
									<input type="text" id="code" name="code" class="span4"
										placeholder="请输入商品编号, 不能为空" <c:if test="${not empty canteenGoods.code}">disabled="disabled"</c:if> value="${canteenGoods.code}"
										${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
						
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>商品名称</label>
								<div class="controls">
									<input type="text" id="name" name="name" class="span4"
										placeholder="请输入商品名称, 不能为空" value="${canteenGoods.name}"
										${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>是否启用
								</label>
								<div class="controls">
								 <c:choose>
								   <c:when test="${not empty canteenGoods.enable }">
								      <input type="radio" id="enable" name="enable" class="span5"  value="true"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} <c:if test="${canteenGoods.enable==true }">checked</c:if>/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="enable" name="enable" class="span5"  value="false" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} <c:if test="${canteenGoods.enable==false }">checked</c:if>/>否
								   </c:when>
								   <c:otherwise>
								      <input type="radio" id="enable" name="enable" class="span5"  value="true" checked="checked" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="enable" name="enable" class="span5"  value="false" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}/>否
								   </c:otherwise>
								 </c:choose>
								</div>
							</div>
							
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${canteenGoods.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
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
	});

	function initValidator() {
		return $("#canteengoods_form").validate({
			errorClass : "myerror",
			rules : {
				"code" : {
					required : true,
					accCheck : true,
					maxlength : 30,
					remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/schoolaffair/canteenGoods/checker",
						data : {
							'dxlx' : 'code',
							'code' : function() {
								return $("#code").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"name" : {
					required : true,
					minlength : 1,
					maxlength : 20,
					stringCheck:true,
					remote : {
						async : false,
						type : "POST",
						url : "${pageContext.request.contextPath}/schoolaffair/canteenGoods/nameChecker",
						data : {
							'dxlx' : 'name',
							'name' : function() {
								return $("#name").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"enable" : {
					selectNone : true
				}
			},
			messages : {
				"code" : {
					remote : "编号已存在"
				},
				"name" : {
					stringCheck:"只能输入中文字符",
					remote:"名称已存在"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#canteengoods_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/canteenGoods/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/schoolaffair/canteenGoods/" + $id;
			}
			var loader = new loadLayer();
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
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>