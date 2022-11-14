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
					<form class="form-horizontal tan_form" id="buildsafe_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									建筑物名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${buildSafe.name}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									排查日期：
								</label>
								<div class="controls">
								<input type="text" id="checkTime" name="checkTime" class="span13" value="<fmt:formatDate value="${buildSafe.checkDate}" pattern="yyyy-MM-dd" />" placeholder="排查日期" onclick="WdatePicker();"/>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									勘察资料是否完整：
								</label>
								<div class="controls">
								<select name="isSurveyData">
									<c:choose>
										<c:when test="${buildSafe.isSurveyData == false}">
											<option value="1">是</option>
											<option value="0" selected="selected">否</option>
										</c:when>
										<c:when test="${buildSafe.isSurveyData == true}">
											<option value="1" selected="selected">是</option>
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1" >是</option>
											<option value="0" >否</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设计文件是否完整：
								</label>
								<div class="controls">
								
								<select name="isDesignDoc">
									<c:choose>
										<c:when test="${buildSafe.isDesignDoc == false}">
											<option value="1">是</option>
											<option value="0" selected="selected">否</option>
										</c:when>
										<c:when test="${buildSafe.isDesignDoc == true}">
											<option value="1" selected="selected">是</option>
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1" >是</option>
											<option value="0" >否</option>
										</c:otherwise>
									</c:choose>
								</select>
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									施工资料是否完整：
								</label>
								<div class="controls">
								<select name="isConstructionDoc">
									<c:choose>
										<c:when test="${buildSafe.isConstructionDoc == false}">
											<option value="1">是</option>
											<option value="0" selected="selected">否</option>
										</c:when>
										<c:when test="${buildSafe.isConstructionDoc == true}">
											<option value="1" selected="selected">是</option>
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1" >是</option>
											<option value="0" >否</option>
										</c:otherwise>
									</c:choose>
								</select>
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									检查文件是否完整：
								</label>
								<div class="controls">
								
								<select name="isCheckDoc">
									<c:choose>
										<c:when test="${buildSafe.isCheckDoc == false}">
											<option value="1">是</option>
											<option value="0" selected="selected">否</option>
										</c:when>
										<c:when test="${buildSafe.isCheckDoc == true}">
											<option value="1" selected="selected">是</option>
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1" >是</option>
											<option value="0" >否</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									竣工资料是否完整：
								</label>
								<div class="controls">
								
								<select name="isCompletionDoc">
									<c:choose>
										<c:when test="${buildSafe.isCompletionDoc == false}">
											<option value="1">是</option>
											<option value="0" selected="selected">否</option>
										</c:when>
										<c:when test="${buildSafe.isCompletionDoc == true}">
											<option value="1" selected="selected">是</option>
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1" >是</option>
											<option value="0" >否</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									消防耐火等级:
								</label>
								<div class="controls">
								<select name="fireSate">
									<c:choose>
										<c:when test="${buildSafe.fireSate == 1}">
											<option value="1" selected="selected">一级</option>
											<option value="2">二级</option>
											<option value="3">三级</option>
											<option value="4">四级</option>
										</c:when>
										<c:when test="${buildSafe.fireSate == 2}">
											<option value="1" >一级</option>
											<option value="2" selected="selected">二级</option>
											<option value="3">三级</option>
											<option value="4">四级</option>
										</c:when>
										<c:when test="${buildSafe.fireSate == 3}">
											<option value="1" >一级</option>
											<option value="2" >二级</option>
											<option value="3" selected="selected">三级</option>
											<option value="4">四级</option>
										</c:when>
										<c:when test="${buildSafe.fireSate == 4}">
											<option value="1" >一级</option>
											<option value="2" >二级</option>
											<option value="3" >三级</option>
											<option value="4" selected="selected">四级</option>
										</c:when>
										<c:otherwise>
											<option value="1" >一级</option>
											<option value="2" >二级</option>
											<option value="3" >三级</option>
											<option value="4" >四级</option>
										</c:otherwise>
									</c:choose>
								</select>
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									消防耐火等级是否符合规范要求:
								</label>
								<div class="controls">
								<select name="isFireSate">
									<c:choose>
										<c:when test="${buildSafe.isFireSate == false}">
											<option value="1">是</option>
											<option value="0" selected="selected">否</option>
										</c:when>
										<c:when test="${buildSafe.isFireSate == true}">
											<option value="1" selected="selected">是</option>
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1" >是</option>
											<option value="0" >否</option>
										</c:otherwise>
									</c:choose>
								</select>
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
									<textarea cols="5" rows="5" name="remark" class="span13">${buildSafe.remark}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${buildSafe.id}" />
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
		return $("#buildsafe_form").validate({
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
			var name = $("#name").val();
			if(name == null || name == ''){
				alert("建筑物名称不能为空");
				return false ;
			}
			
			var checkTime = $("#checkTime").val();
			if(checkTime == null || checkTime == ''){
				alert("排查日期不能为空");
				return false ;
			}
			
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#buildsafe_form");
			var url = "${ctp}/schoolaffair/buildsafe/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/buildsafe/" + $id;
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