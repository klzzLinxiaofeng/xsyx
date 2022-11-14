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
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="pushobject_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>推送内容名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${pushObject.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>推送内容编码：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13" placeholder="" value="${pushObject.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									推送内容分类：
								</label>
								<div class="controls">
								<input type="text" id="category" name="category" class="span13" placeholder="" value="${pushObject.category}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									推送内容描述：
								</label>
								<div class="controls">
								<textarea  id="content" name="content" class="span4" rows="3" cols="1">${pushObject.content }</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${pushObject.id}" />
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
	});
	
	function initValidator() {
		return $("#pushobject_form").validate({
			errorClass : "myerror",
			rules : {
				name : {
					required : true
				},
				code : {
					required : true,
					remote:{
						async : false,
	  					url:"${pageContext.request.contextPath}/im/pushObject/check",
	  					type:"post",
	  					data:{
	  						'code':function(){
	  							return $("#code").val();
	  						}
	  					}
	  				}
				},
				category : {
					required : true
				}
			},
			messages : {
				name : {
					required : "请输入推送内容名称"
				},
				code : {
					required : "请输入推送内容编码",
					remote:"已存在"
				},
				category : {
					required : "请输入推送内容分类"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			//alert($id);
			var $requestData = formData2JSONObj("#pushobject_form");
			var url = "${ctp}/im/pushObject/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/im/pushObject/" + $id;
			}
			//alert(JSON.stringify($requestData));
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