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
					<form class="form-horizontal tan_form" id="jctextbook_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${jcTextbook.name}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									ISBN：
								</label>
								<div class="controls">
								<input type="text" id="isbn" name="isbn" class="span13"
									placeholder="" value="${jcTextbook.isbn}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									应用领域：
								</label>
								<div class="controls">
								<input type="text" id="domain" name="domain" class="span13"
									placeholder="" value="${jcTextbook.domain}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									出版社：
								</label>
								<div class="controls">
								<input type="text" id="publisherId" name="publisherId" class="span13"
									placeholder="" value="${jcTextbook.publisherId}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									使用学段：
								</label>
								<div class="controls">
								<input type="text" id="stageCode" name="stageCode" class="span13"
									placeholder="" value="${jcTextbook.stageCode}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									适用学科：
								</label>
								<div class="controls">
								<input type="text" id="subjectCode" name="subjectCode" class="span13"
									placeholder="" value="${jcTextbook.subjectCode}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									年级：
								</label>
								<div class="controls">
								<input type="text" id="gradeCode" name="gradeCode" class="span13"
									placeholder="" value="${jcTextbook.gradeCode}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									册次/卷：
								</label>
								<div class="controls">
								<input type="text" id="volumn" name="volumn" class="span13"
									placeholder="" value="${jcTextbook.volumn}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									版本：
								</label>
								<div class="controls">
								<input type="text" id="version" name="version" class="span13"
									placeholder="" value="${jcTextbook.version}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									出版日期：
								</label>
								<div class="controls">
								<input type="text" id="publishDate" name="publishDate" class="span13"
									placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${jcTextbook.publishDate}"></fmt:formatDate>' onclick="WdatePicker();" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									说明：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13"
									placeholder="" value="${jcTextbook.description}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									缩略图：
								</label>
								<div class="controls">
								<input type="text" id="image" name="image" class="span13"
									placeholder="" value="${jcTextbook.image}" style="width:220px;">
								</div>
								
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${jcTextbook.id}" />
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
		
		if('${isCK}' == 'disable'){
			
			$("form input").prop("readonly", true);	
		}
		
		
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#jctextbook_form").validate({
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
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#jctextbook_form");
			var url = "${ctp}/teach/textBookMaster/textBook/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/textBookMaster/textBook/" + $id;
			}
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