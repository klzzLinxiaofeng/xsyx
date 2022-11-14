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
					<form class="form-horizontal tan_form" id="aptTaskItem_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>考核类别：
								</label>
								<div class="controls">
									<select id="checkType" name="checkType" class="span13" style="margin-bottom: 0; padding: 6px;">
										<option value="">请选择</option>
										<option value="02">加分</option>
										<option value="03">减分</option>
									</select>
								</div>
							</div>
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"> -->
<!-- 									<span style="color: red;">*</span>名称： -->
<!-- 								</label> -->
<!-- 								<div class="controls"> -->
<%-- 								<input type="text" id="name" name="name" class="span13" placeholder="名称" value="${taskItem.name}"> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"> -->
<!-- 									<span style="color: red;">*</span>分值： -->
<!-- 								</label> -->
<!-- 								<div class="controls"> -->
<%-- 								<input type="text" id="score" name="score" class="span13" placeholder="分值" value="${taskItem.score}"> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>负责人：
								</label>
								<div class="controls">
									<input type="text" id="judgeNames" class="span13" placeholder="负责人" value="${taskItem.judgeName}">
									<input type="hidden" id="judgeName" name="judgeName" value="${taskItem.judgeName}">
									<input type="hidden" id="teacherIds" name="teacherIds" value="${teacherIds}">
									<input type="button" id="cleanBtn" value="清除"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
									<textarea rows="5" cols="5" id="description" name="description" class="span13" placeholder="备注">${taskItem.description}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${taskItem.id}" />
							<input type="hidden" id="taskId" name="aptTaskId" value="${taskId}" />
							
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
		$("#checkType option[value='${taskItem.checkType}']").attr("selected",true);
		//初始化教师选择弹出框
		var option = {
				"inputIdSelector" : "#judgeNames",
				"ry_type" : "teach",
				"enableBatch" : true,
				"layerOp" : {
					"type" : 2,
					"shift" : 'top'
				}
			};
		$.createMemberSelector(option);
		
		//清除
		$("#cleanBtn").click(function(){
			$("#judgeNames_select").val("");
			$("#judgeName").val("");
			$("#teacherIds").val("");
		});
		
// 		$("#ruleId").chosen();
		$("#checkType").chosen();
		checker = initValidator();
	});
	
	function selectedHandler(data){
		if(data != null && data != "undefined"){
			$("#judgeNames_select").val(data.names);
			$("#judgeName").val(data.names);
			$("#teacherIds").val(data.ids);
			$.closeWindowByName(data.windowName);
		}
	}
	
	function initValidator() {
		return $("#aptTaskItem_form").validate({
			errorClass : "myerror",
			rules : {
// 				"name" : {
// 					required : true,
// 					accCheck : false,
// 					maxlength : 30,
// 				},
				"checkType" : {
					selectNone : true,
					accCheck : false,
					remote : {
						async : false,
						type : "GET",
						url : "${ctp}/schoolAffair/aptTask/chcekTypeChecker",
						data : {
							'checkType' : function() {
								return encodeURI($("#checkType").val());
							},
							'taskId' : function() {
								return "${taskId}";
							},
							'itemId' : function() {
								return "${taskItem.id}";
							}
						}
					}
				},
// 				"ruleId" : {
// 					selectNone : true
// 				},
// 				"score" : {
// 					required : true,
// 					number : true
// 				},
				"judgeName" : {
					required : true
				}
			},
			messages : {
				"checkType" : {
					remote : "该项已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#aptTaskItem_form");
			var url = "${ctp}/schoolAffair/aptTask/taskItemCreator";
			if ("" != $id) {
				url = "${ctp}/schoolAffair/aptTask/taskItemEdit";
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