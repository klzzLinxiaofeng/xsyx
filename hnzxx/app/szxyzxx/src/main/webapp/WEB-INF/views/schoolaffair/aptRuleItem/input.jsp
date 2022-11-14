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
					<form class="form-horizontal tan_form" id="aptRuleItem_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>考核标准：
								</label>
								<div class="controls">
									<select name="ruleId" id="ruleId" class="span13" style="margin-bottom: 0; padding: 6px;">
										<option value="">请选择</option>
										<c:forEach items="${arList}" var="list">
											<option value="${list.id}" <c:if test="${aptRuleItem.ruleId == list.id}">selected="selected"</c:if>>${list.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"> -->
<!-- 									<span style="color: red;">*</span>考核类别： -->
<!-- 								</label> -->
<!-- 								<div class="controls"> -->
<!-- 									<select id="checkType" name="checkType" class="span13" style="margin-bottom: 0; padding: 6px;"> -->
<!-- 										<option value="">请选择</option> -->
<%-- 										<option value="01" <c:if test="${aptRuleItem.checkType == '01'}">selected="selected"</c:if>>日常</option> --%>
<%-- 										<option value="02" <c:if test="${aptRuleItem.checkType == '02'}">selected="selected"</c:if>>加分</option> --%>
<%-- 										<option value="03" <c:if test="${aptRuleItem.checkType == '03'}">selected="selected"</c:if>>减分</option> --%>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>内容分类：
								</label>
								<div class="controls">
								<input type="text" id="category" name="category" class="span13" placeholder="内容分类" value="${aptRuleItem.category}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="名称" value="${aptRuleItem.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>分值：
								</label>
								<div class="controls">
								<input type="text" id="score" name="score" class="span13" placeholder="分值" value="${aptRuleItem.score}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
									<textarea rows="5" cols="5" id="description" name="description" class="span13" placeholder="备注">${aptRuleItem.description}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${aptRuleItem.id}" />
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
		$("#ruleId").chosen();
		$("#checkType").chosen();
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#aptRuleItem_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					accCheck : false,
					maxlength : 30,
// 					remote : {
// 						async : false,
// 						type : "GET",
// 						url : "${pageContext.request.contextPath}/schoolAffair/aptRuleItem/itemChecker",
// 						data : {
// 							'name' : function() {
// 								return encodeURI($("#name").val());
// 							}
// 						}
// 					}
				},
// 				"checkType" : {
// 					selectNone : true
// 				},
				"category" : {
					required : true,
					maxlength : 20
				},
				"ruleId" : {
					selectNone : true
				},
				"score" : {
					required : true,
					number : true
				},
				"description" : {
					maxlength : 500
				}
			},
			messages : {
// 				"name" : {
// 					remote : "名称已存在"
// 				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#aptRuleItem_form");
			var url = "${ctp}/schoolAffair/aptRuleItem/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolAffair/aptRuleItem/" + $id;
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