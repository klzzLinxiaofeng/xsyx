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
					<form class="form-horizontal tan_form" id="facility_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									设施号：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13" readonly="readonly" placeholder="" value="${facility.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设施产权：
								</label>
								<div class="controls">
								<select name="facilitiyProperty" disabled="disabled">
									<c:choose>
										<c:when test="${facility.facilitiyProperty==1 }">
										<option>产权非属学校共同使用</option>
										</c:when>
										<c:when test="${facility.facilitiyProperty==2 }">
											<option>学校独立产权</option>
										</c:when>
										<c:otherwise>
											<option>其他</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设施使用状况 :
								</label>
								<div class="controls">
								<select name="facilitiyUseState" disabled="disabled">
									<c:choose>
										<c:when test="${facility.facilitiyUseState==false }">
										<option>使用</option>
										</c:when>
										<c:when test="${facility.facilitiyUseState==true }">
											<option>停用</option>
										</c:when>
										<c:otherwise>
											<option>其他</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设施名称：
								</label>
								<div class="controls">
								<input type="text" id="name" readonly="readonly" name="name" class="span13" placeholder="" value="${facility.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									地址：
								</label>
								<div class="controls">
								<input type="text" id="address" readonly="readonly" name="address" class="span13" placeholder="" value="${facility.address}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									建设年月：
								</label>
								<div class="controls">
								<input type="text" id="buildDate" readonly="readonly" name="buildTime" class="span13" value="<fmt:formatDate value="${facility.buildDate}" pattern="yyyy-MM-dd" />" placeholder="建设年月"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									建设费用：
								</label>
								<div class="controls">
								<input type="text" id="buildCost" readonly="readonly" name="buildCost" class="span13" placeholder="" value="${facility.buildCost}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修缮年月：
								</label>
								<div class="controls">
								<input type="text" id="repairDate" readonly="readonly" name="repairTime" class="span13" value="<fmt:formatDate value="${facility.repairDate}" pattern="yyyy-MM-dd" />" placeholder="修缮年月"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修缮费用：
								</label>
								<div class="controls">
								<input type="text" id="repairCost" readonly="readonly" name="repairCost" class="span13" placeholder="" value="${facility.repairCost}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea rows="5" cols="5" id="remark" readonly="readonly" name="remark" class="span13">${facility.remark}</textarea>
								</div>
							</div>
						
						<%-- <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${facility.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div> --%>
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
		return $("#facility_form").validate({
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
			var $requestData = formData2JSONObj("#facility_form");
			var url = "${ctp}/school.affair/facility/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/school.affair/facility/" + $id;
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