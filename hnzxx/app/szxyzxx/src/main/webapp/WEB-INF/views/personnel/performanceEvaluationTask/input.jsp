<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%
	SimpleDateFormat format = new SimpleDateFormat("yyyy");
	String value = format.format(new Date());
%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
input[type="text"]{
	padding: 0 6px;
	height:28px;
	line-height:30px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="performanceEvaluationTask_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									考核任务名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" value="${task.name}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									考核人：
								</label>
								<div class="controls">
								<input type="text" id="evaluate" name="evaluate" class="span4" data-id="${evaluateId}" placeholder="批量添加考核人" value="${evaluateName }" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<input type="hidden" id="evaluateId" name="evaluateId" value="${evaluateId }" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<button onclick="emptyEvaluate();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>清除</button>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									被考核人：
								</label>
								<div class="controls">
								<input type="text" id="evaluated" name="evaluated" class="span4" data-id="${evaluatedId}" placeholder="批量添加被考核人" value="${evaluatedName }" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<input type="hidden" id="evaluatedId" name="evaluatedId" value="${evaluatedId }" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<button onclick="emptyEvaluated();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>清除</button>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									考核项目：
								</label>
								<div class="controls">
								<table border="1" id="itemTable" class="table table-bordered nr_table">
									<thead>
										<tr>
											<th style="width:100px;">考核项目</th>
											<th style="width:120px;">分值范围</th>
											<th style="width:100px;">分值描述</th>
											<th style="width:60px;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${items }" var="item">
											<tr class="saveItem" id="tr_${item.id}">
												<td class="itemId" id="${item.id}"><input type="text" style="width:100px;" value="${item.name }" disabled="disabled"></td>
												<td><input type="text" style="width:40px;" value="${item.startScore }" disabled="disabled">&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text" style="width:40px;" value="${item.endScore }" disabled="disabled"></td>
												<td><input type="text" style="width:100px;" value="${item.description }" disabled="disabled"></td>
												<td><button onclick="delItem(obj, ${item.id});" class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>移除</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
								<div class="control-group">
									<label class="control-label">
									</label>
								<div class="controls"><button class="btn btn-success " id="new-add" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>新增</button></div>
								</div>	
							</div>
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>考核阶段开始日期：</label>
							<div class="controls">
								<input type="text" id="effectiveDate" name="effectiveDate" class="span4"
									placeholder="请输入生效日期 如2014-09-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${task.effectiveDate}"></fmt:formatDate>'
									onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'expiryDate\')}'})"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>考核阶段结束日期：</label>
							<div class="controls">
								<input type="text" id="expiryDate" name="expiryDate"
									class="span4" placeholder="请输入终止日期 如2015-07-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${task.expiryDate}"></fmt:formatDate>'
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'effectiveDate\')}'})"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<input type=hidden id="year" name="year" class="span4" placeholder="" value="<%=value %>">
						<div class="control-group">
							<label class="control-label">
								备注：
							</label>
							<div class="controls">
							<textarea rows="3" cols="1" id="remark" name="remark" class="span4" placeholder="" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${task.remark}</textarea>
							</div>
						</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${task.id}" />
							<c:if test="${isCK == null || isCK == '' }">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
							</c:if>
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
// 		checker = initValidator();
		$.createMemberSelector({
			"inputIdSelector" : "#evaluate",
			"isOnTopWindow" : true
		});
		$.createMemberSelector({
			"inputIdSelector" : "#evaluated",
			"isOnTopWindow" : true
		});
		
// 		alert($("#evaluate_select").val());
	});
	
	function initValidator() {
		return $("#performanceEvaluationTask_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength : 30
				},
				"evaluate" : {
					required : true
				},	
				"evaluated" : {
					required : true
				},
				"effectiveDate" : {
// 					lessThanEndDate : true,
					required : true
				},
				"expiryDate" : {
					required : true
				},
				"remark" : {
					maxlength : 200
				},
				"itemName" : {
					required : true
				},
				"startScore" : {
					required : true,
					digits : true,
					max : 0
				},
				"endScore" : {
					required : true,
					digits : true,
					max : 100
				},
				"description" : {
					maxlength : 15
				}
			},
			messages : {
				
			}
		});
	}
	
	
	$(function(){
		$("#new-add").click(function(){
			var i = $("#itemTable tr").length;
			var tr = $("#itemTable").find("tr");
			var td = $(tr[i-1]).find("td");
			var text1 = $(td[0]).find("input").val();
			var text2 = $(td[1]).find("input").eq(0).val();
			var text3 = $(td[1]).find("input").eq(1).val();
			var text4 = $(td[2]).find("input").val();
			if(text1 === "" || text2 === "" || text3 === "") {
				$.alert("请完善上一条数据");
				return;
			}
			if(text1 !== "" && text2 !== "" && text3 != ""){
				checker = initValidator();
				$(td[0]).find("input").removeAttr("name");
				$(td[0]).find("input").next("label").remove();
				$(td[1]).find("input").eq(0).removeAttr("name");
				$(td[1]).find("label[for='input2']").remove();
				$(td[1]).find("input").eq(1).removeAttr("name");
				$(td[1]).find("label[for='input3']").remove();
				$(td[2]).find("input").removeAttr("name");
				$(td[2]).find("input").next("label").remove();
				$(".nr_table").children("tbody").append("<tr data-id='" + i + "' class='addItem'><td><input type='text' name='itemName' id='input1' data-id='input1' style='width:100px;'></td><td><input type='text' name='startScore' id='input2' style='width:40px;'>&nbsp;&nbsp;~&nbsp;&nbsp;<input type='text' name='endScore' id='input3' style='width:40px;'></td><td><input type='text' name='description' id='input4' style='width:100px;'></td><td></td></tr>")
			}
		})
	})
	
// 	$(function(){
// 		$("#new-add").click(function(){
// 			$(".nr_table").children("tbody").append("<tr class='addItem'><td><input type='text' name='itemName' id='input1' style='width:100px;'></td><td><input type='text' name='startScore' id='input2' style='width:40px;'>&nbsp;&nbsp;~&nbsp;&nbsp;<input type='text' name='endScore' id='input3' style='width:40px;'></td><td><input type='text' name='description' id='input4' style='width:100px;'></td><td></td></tr>")
// 		})
// 	})
	
	function delItem(obj, id){
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	
	// 	执行移除
	function executeDel(obj, id) {
		$("#tr_" + id).remove();
	}
	
	function emptyEvaluate() {
		$.confirm("确定执行此次操作？", function() {
			executeEvaluate();
		});
	}
	
	function emptyEvaluated() {
		$.confirm("确定执行此次操作？", function() {
			executeEvaluated();
		});
	}

	//清空考核人
	function executeEvaluate(){
		$("#evaluate_select").val("");
		$("#evaluateId").val("");
		$("#evaluate").val("");
	}	
	
	//清空被考核人
	function executeEvaluated(){
		$("#evaluated_select").val("");
		$("#evaluatedId").val("");
		$("#evaluated").val("");
	}
	
	
	//保存或更新修改
	function saveOrUpdate() {
		checker = initValidator();
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#performanceEvaluationTask_form");
			var saveItem = [];
			var addItem = [];
			$(".saveItem").each(function(index, value) {
				var saveJson = {};
				saveJson.itemId = $(this).find(".itemId").attr("id");
				saveItem.push(saveJson);
			});
			$(".addItem").each(function(index, value) {
				var addJson = {};
				addJson.name = $(this).find("#input1").val();
				addJson.startScore = $(this).find("#input2").val();
				addJson.endScore = $(this).find("#input3").val();
				addJson.description = $(this).find("#input4").val();
				addItem.push(addJson);
			});
			$requestData.saveItems = JSON.stringify(saveItem);
			$requestData.addItems = JSON.stringify(addItem);
			var url = "${ctp}/personnel/performanceEvaluationTask/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/personnel/performanceEvaluationTask/" + $id;
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