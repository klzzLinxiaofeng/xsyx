<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
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
#uploader{
	position: absolute;
    display: block;
    left:177px;
    margin-bottom: 80px;
    margin-top:-7px;
}
#SWFUpload_0{
    left: 4px;
    top: 1px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="app_form" action="javascript:void(0);">
					
					<input type="hidden" id="parentId" name="parentId" value="${parent.id}">
					
					<input type="hidden" id="appId" name="appId" value="${parent.appId}">
					
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>产品名称： </label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${appEdition.name}">
							</div>

						</div>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>AppKey： </label>
							<div class="controls">
								<input type="text" id="appKey" name="appKey"
									class="span13" placeholder="" value="${appEdition.appKey}">
							</div>

						</div>
						
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>记录类型： </label>
							<div class="controls">
								<select id="recordType" name="recordType">
									<c:if test="${empty appEdition }">
										<option value="0">分组</option>
										<option value="2">版本</option>
									</c:if>
									<c:if test="${appEdition.recordType eq 0}">
										<option value="0"  selected="selected">分组</option>
										<option value="2">版本</option>
									</c:if>
									<c:if test="${appEdition.recordType eq 2}">
										<option value="0" >分组</option>
										<option value="2" selected="selected">版本</option>
									</c:if>
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"> 运行环境： </label>
							<div class="controls">
								<input type="text" id="runtime" name="runtime"
									class="span13" placeholder="" value="${appEdition.runtime}">
							</div>

						</div>
						
						<div class="control-group">
							<label class="control-label"> 描述： </label>
							<div class="controls">
								<textarea id="description" class="span13" name="description">${appEdition.description}</textarea>
							</div>

						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${appEdition.id}" />
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
	var checker;
	$(function() {
		
		checker = initValidator();
		$("#recordType").value(appEdition.recordType);
	});

	function initValidator() {
		return $("#app_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength:100,
					stringCheck:true,
					remote:{
						async : false,
						url:"${ctp}/sys/appTree/appEdition/check",
						type:"post",
						dataType:"json",
						data:{
							'dxlx' : 'name',
							"name":function(){return $("#name").val();},
							'id' : function() {return $("#id").val();}
						}
					}
				},
				"appKey" : {
					required : true,
					maxlength:100 ,
					remote:{
						async : false,
						url:"${ctp}/sys/appTree/appEdition/check",
						type:"post",
						dataType:"json",
						data:{
							'dxlx' : 'appKey',
							"appKey":function(){return $("#appKey").val();},
							'id' : function() {return $("#id").val();}
						}
					}
				},
				"recordType" : {
					selectNone : true
				}
			},
			messages : {
				"name":{
					stringCheck:"只能输入中文字符",
					remote:"名称已存在"
				},
				"appKey" : {
					remote:"appKey不能重复"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			$("#name").val($("#name").val().replace(/<\/?[^>]*>/g,""));
			var $requestData = formData2JSONObj("#app_form");
			var url = "${ctp}/sys/appTree/appEdition_creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/sys/appTree/appEdition/" + $id;
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