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
								<input type="text" id="code" name="code" maxlength="80" class="span13" placeholder="" value="${facility.code}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设施产权：
								</label>
								<div class="controls">
								<select name="facilitiyProperty">
									<c:choose>
										<c:when test="${facility.facilitiyProperty == 1}">
											<option value="1" selected="selected">产权非属学校共同使用</option>
											<option value="2">学校独立产权</option>
											<option value="99">其他</option>
										</c:when>
										<c:when test="${facility.facilitiyProperty == 2}">
											<option value="1" >产权非属学校共同使用</option>
											<option value="2" selected="selected">学校独立产权</option>
											<option value="99">其他</option>
										</c:when>
										<c:when test="${facility.facilitiyProperty == 99}">
											<option value="1" >产权非属学校共同使用</option>
											<option value="2" >学校独立产权</option>
											<option value="99" selected="selected">其他</option>
										</c:when>
										
										<c:otherwise>
											<option value="1" >产权非属学校共同使用</option>
											<option value="2">学校独立产权</option>
											<option value="99">其他</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设施使用状况：
								</label>
								<div class="controls">
								<select name="facilitiyUseState">
									<c:choose>
										<c:when test="${facility.facilitiyUseState == false}">
											<option value="0" selected="selected">使用</option>
											<option value="1">停用</option>
										</c:when>
										<c:when test="${facility.facilitiyUseState == true}">
											<option value="0" >使用</option>
											<option value="1" selected="selected">停用</option>
										</c:when>
										<c:otherwise>
											<option value="0" >使用</option>
											<option value="1" >停用</option>
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
								<input type="text" id="name" name="name" maxlength="80" class="span13" placeholder="" value="${facility.name}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									地址：
								</label>
								<div class="controls">
								<input type="text" id="address" name="address" maxlength="80" class="span13" placeholder="" value="${facility.address}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									建设年月：
								</label>
								<div class="controls">
								<input type="text" id="buildTime" name="buildTime" class="span13" value="<fmt:formatDate value="${facility.buildDate}" pattern="yyyy-MM-dd" />" placeholder="建设年月" onclick="WdatePicker();"/>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									建设费用：
								</label>
								<div class="controls">
								<input type="text" id="buildCost" name="buildCost" class="span13" placeholder="" value="${facility.buildCost}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修缮年月：
								</label>
								<div class="controls">
								<input type="text" id="repairTime" name="repairTime" class="span13" value="<fmt:formatDate value="${facility.repairDate}" pattern="yyyy-MM-dd" />" placeholder="修缮年月" onclick="WdatePicker();"/>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修缮费用：
								</label>
								<div class="controls">
								<input type="text" id="repairCost" name="repairCost" class="span13" placeholder="" value="${facility.repairCost}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea rows="5" cols="5" id="remark" name="remark" class="span13">${facility.remark}</textarea>
								</div>
							</div>
						
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${facility.id}" />
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
			
			var code = $("#code").val();
			if(code == null || code == ''){
				alert("设施号不能为空");
				return false ;
			}
			
			var name = $("#name").val();
			if(name == null || name == ''){
				alert("设施名称不能为空");
				return false ;
			}
			
			
			
			var buildTime = $("#buildTime").val();
			if(buildTime == null || buildTime == ''){
				alert("建设年月不能为空");
				return false ;
			}
			
			var repairTime = $("#repairTime").val();
			if(repairTime == null || repairTime == ''){
				alert("修缮年月不能为空");
				return false ;
			}
			

			var buildCost = $("#buildCost").val();
			if (!checkNumber(buildCost)) {
				  alert("建设费用，请输入正确数字");
				  return false;
			}
			
			var repairCost = $("#repairCost").val();
			if (!checkNumber(repairCost)) {
				  alert("修缮费用，请输入正确数字");
				  return false;
			}
			
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#facility_form");
			var url = "${ctp}/schoolaffair/facility/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/facility/" + $id;
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
	
	//验证字符串是否是数字
	function checkNumber(theObj) {
	  var reg = /^[0-9]+.?[0-9]*$/;
	  if (reg.test(theObj)) {
	    return true;
	  }
	  return false;
	}
	
</script>
</html>