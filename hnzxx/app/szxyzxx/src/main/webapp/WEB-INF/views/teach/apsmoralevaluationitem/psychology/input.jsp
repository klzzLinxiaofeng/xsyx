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
					<form class="form-horizontal tan_form" id="apsmoralevaluationitem_form" action="javascript:void(0);">
							<input type="hidden" name="type" value="4">
							<div class="control-group">
								<label class="control-label">
									心理健康教师名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${apsMoralEvaluationItem.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否专职教师：
								</label>
								<div class="controls">
								<select name="isFullTeach">
									<c:choose>
										<c:when test="${apsMoralEvaluationItem.isFullTeach==0 }">
											<option value="0" selected="selected">否</option>
											<option value="1">是</option>
										</c:when>
										<c:otherwise>
											<option value="0" >否</option>
											<option value="1" selected="selected">是</option>
										</c:otherwise>
									</c:choose>
								
								</select>
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否心理专业：
								</label>
								<div class="controls">
								<select name="isMind">
									<c:choose>
										<c:when test="${apsMoralEvaluationItem.isMind==0 }">
											<option value="0" selected="selected">否</option>
											<option value="1">是</option>
										</c:when>
										<c:otherwise>
											<option value="0" >否</option>
											<option value="1" selected="selected">是</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									时间：
								</label>
								<div class="controls">
								<input type="text" id="publishTime" name="publishTime" class="span13" placeholder="" value="<fmt:formatDate value="${apsMoralEvaluationItem.pubishDate}" pattern="yyyy-MM-dd" />" placeholder="时间" onclick="WdatePicker();"  >
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${apsMoralEvaluationItem.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
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
		return $("#apsmoralevaluationitem_form").validate({
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
			
			var publishTime = $("#publishTime").val();
			if(publishTime == null || publishTime == ''){
				alert("时间不能为空");
				return false ;
			}
			
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#apsmoralevaluationitem_form");
			var url = "${ctp}/teach/apsmoralevaluationitem/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/apsmoralevaluationitem/" + $id;
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