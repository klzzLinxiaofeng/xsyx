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
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="knowledgeCatalog_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${catalog.name}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>排序：
								</label>
								<div class="controls">
								<input type="text" id="level" name="sort" class="span13" placeholder="" value="${catalog.sort}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									父知识点：
								</label>
								<div class="controls">
									<input type="text" disabled="disabled" id="parentName" class="span13" placeholder="" value="${ empty parent.name ? '无' : parent.name}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
								<input type="hidden" name="knowledgeVersionCode" class="span13" placeholder="" value="${catalog.knowledgeVersionCode}">
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
		return $("#knowledgeCatalog_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength: 50,
					remote : {
						type : "GET",
						async : false,
						url  : "${ctp}/teach/catalog/checker",
						data : {
							'opera' : 'update',
							'name' : function() {
								return encodeURI($("#name").val());
							},
							"id" : "${catalog.id}"
						}
					}
				},
				"level" : {
					required : true,
					isIntGtZero : true
				}
			},
			messages : {
				"level" : {
					isIntGtZero : "请输入正整数"
				},
				"name" : {
					remote : "名称已存在"
				}
			}
		});
	}
	
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#knowledgeCatalog_form");
			var url = "${ctp}/teach/catalog/${catalog.id}";
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