<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 180px;
}

.row-fluid .span4 {
	width: 60%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
input[type="radio"]{
	margin:0 5px;
	position:relative;
	top:1px;
}
</style>
</head>
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 40px 0 0;">
					<form class="form-horizontal tan_form" id="appauthorization_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>授权版本或产品:
								</label>
								<div class="controls">
								<select id="appKey" name="appKey" class="span4 chzn-select" >
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>默认使用的IM服务商:
								</label>
								<div class="controls">
								<select id="imType" name="imType" class="span4 chzn-select" >
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>是否授权使用IM:
								</label>
								<div class="controls" style="line-height:30px;">
								 <c:choose>
								   <c:when test="${not empty appAuthorization.state }">
								      <input type="radio" id="state" name="state" class=""  value="1"  <c:if test="${appAuthorization.state==1 }">checked</c:if>/>是&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="state" name="state" class=""  value="0"  <c:if test="${appAuthorization.state==0 }">checked</c:if>/>否
								   </c:when>
								   <c:otherwise>
								      <input type="radio" id="state" name="state" class=""  value="1"  checked="true"/>是&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="state" name="state" class=""  value="0" />否
								   </c:otherwise>
								 </c:choose>
								</div>
							</div>							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>App互踢原则:
								</label>
								<c:choose>
									<c:when test="${empty appAuthorization.state }">
										<div  id="able" class="controls">
											<div style="line-height:30px;">
												<input type="radio" id="enable1" name="enable" class=""  value="true" onclick="removeDiv()" checked="checked" />不被其他App踢下线
											</div>
											<div style="line-height:30px;">
												<p style="float:left;margin:0 20px 0 0;"><input type="radio" id="enable" name="enable" class=""  value="false" onclick="getParentAppGroups()" />在选定的App组内允许互踢:</p>
												<div id="accountType" style="float:left">
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div  id="able" class="controls">
											<c:if test="${appAuthorization.imAccountApp ==  appAuthorization.appKey}">
												<div style="line-height:30px;">
													<input type="radio" id="enable1" name="enable" class=""  value="true" onclick="removeDiv()" checked="checked" />不被其他App踢下线
												</div>
												<div style="line-height:30px;">
													<p style="float:left;margin:0 20px 0 0;"><input type="radio" id="enable" name="enable" class=""  value="false" onclick="getParentAppGroups()" />在选定的App组内允许互踢:</p>
													<div id="accountType" style="float:left">
													</div>
												</div>
											</c:if>
											
											<c:if test="${appAuthorization.imAccountApp !=  appAuthorization.appKey}">
												<div style="line-height:30px;">
													<input type="radio" id="enable1" name="enable" class=""  value="true" onclick="removeDiv()" />不被其他App踢下线
												</div>
												<div style="line-height:30px;">
													<p style="float:left;margin:0 20px 0 0;"><input type="radio" id="enable" name="enable" class=""  value="false" checked="checked" onclick="getParentAppGroups()" />在选定的App组内允许互踢:</p>
													<div id="accountType" style="float:left">
														<select id='imAccountApp' name='imAccountApp' class='span13 chzn-select' >
														</select>
													</div>
												</div>
											</c:if>
										</div>
									</c:otherwise>
								</c:choose>
								
								
								
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${appAuthorization.id}" />
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
		$.IMProviderSelector({
			"selector" : "#imType",
			"selectedVal"  : "${appAuthorization.imType}"
		});
		$.AllAppEditionsSelector({
			"selector" : "#appKey",
			"selectedVal"  : "${appAuthorization.appKey}"
		});
		$.ParentAppGroupsSelector({
			"selector" : "#imAccountApp",
			"condition" : {"appKey":"${appAuthorization.appKey}"},
			"selectedVal"  : "${appAuthorization.imAccountApp}"
		});
	});
	
	$("#appKey").on("change", function(){
		$("#enable1").click();
	});
	
	function removeDiv(){
		$("#accountType").html("");
	}
	
	function getParentAppGroups(){
		$("#accountType").append("<select id='imAccountApp' name='imAccountApp' class='span13 chzn-select' ></select>");
		var appKey = $("#appKey").val();
		$.ParentAppGroupsSelector({
			"selector" : "#imAccountApp",
			"condition" : {"appKey":appKey}
		});
	}
	
	$.ParentAppGroupsSelector = function(options){
		var defOption = {
				"selector" : "#app",
				"condition" : {"appKey":appKey},
				"selectedVal" :  "",
				"afterHandler" : function() {},
				"firstOptionTitle" : "请选择",
				"isUseChosen" : true
			};
		options = $.extend({}, defOption, options || {});
		
		var selector = $(options.selector);
		selector.html("");
		selector.append("<option value=''>"+ options.firstOptionTitle +"</option>");
		
		$.getParentAppGroups(options.condition, function(data) {
			$.each(data, function(index, value) {
				selector.append("<option value='" + value.appKey + "'>" + value.name + "</option>")
			});
			selector.val(options.selectedVal);
			options.afterHandler(selector);
			if(options.isUseChosen == null || options.isUseChosen) {
				selector.chosen();
			}
		});
	}
	
	$.getParentAppGroups = function(conditionJson, afterHandler) {
		$.get("${ctp}/im/appAuthorization/getParentAppGroups", conditionJson, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				afterHandler(data);
			}
		});
	}
	
	function initValidator() {
		return $("#appauthorization_form").validate({
			errorClass : "myerror",
			rules : {
				appKey : {
					selectNone : true,
					remote:{
						async : false,
	  					url:"${pageContext.request.contextPath}/im/appAuthorization/check",
	  					type:"post",
	  					data:{
	  						'dxlx' : 'appKey',
	  						"appKey":function(){return $("#appKey").val();},
							'id' : function() {return $("#id").val();}
	  					}
	  				}
				},
				imType : {
					selectNone : true
				},
				imAccountApp : {
					selectNone : true
				}
			},
			messages : {
				appKey : {
					required : "请选择授权版本或产品",
					remote: "已存在"
				},
				imType : {
					required : "请选择IM服务商"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#appauthorization_form");
			var url = "${ctp}/im/appAuthorization/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/im/appAuthorization/" + $id;
			}
 			//alert(JSON.stringify($requestData));//打桩查看 
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