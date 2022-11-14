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
	width: 227px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.form-horizontal .control-group{
	width:45%;
	float:left;
}
.form-horizontal .control-label {
	width: 80px;
}

.form-horizontal .controls {
	margin-left: 100px;
}

.table thead th, .table tbody td {
	font-weight: normal;
	font-size: 12px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form"
						id="moralEvaluationStudent_form" action="javascript:void(0);">

						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>学年</label>
							<div class="controls">
								<select id="schoolYear" name="schoolYear" class="chzn-select"
									style="width: 220px;" disabled="disabled">

								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>年级</label>
							<div class="controls">
								<select id="gradeId" name="gradeId" class="chzn-select"
									style="width: 220px;" disabled="disabled">

								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>班级</label>
							<div class="controls">
								<select id="teamId" name="teamId" class="chzn-select"
									style="width: 220px;" disabled="disabled">

								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>姓名</label>
							<div class="controls">
								<select id="studentId" name="studentId" class="chzn-select"
									style="width: 220px;" disabled="disabled">

								</select>
							</div>
						</div>

						<div class="control-group" style="width:100%;">
							<label class="control-label"><font style="color: red">*</font>
								总评价 </label>
							<div class="controls">
								<select id="totalEvaluation" name="totalEvaluation"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}
									class="span4">
									<option
										<c:if test="${moralEvaluationStudent.totalEvaluation == 1 }">selected</c:if>
										value="1">优秀</option>
									<option
										<c:if test="${moralEvaluationStudent.totalEvaluation == 2 }">selected</c:if>
										value="2">良好</option>
									<option
										<c:if test="${moralEvaluationStudent.totalEvaluation == 3 }">selected</c:if>
										value="3">合格</option>
									<option
										<c:if test="${moralEvaluationStudent.totalEvaluation == 4 }">selected</c:if>
										value="4">不合格</option>
								</select>
							</div>
						</div>

						<div class="control-group" style="width:100%;">
							<label class="control-label"><font style="color: red">*</font>
								评价 </label>
							<div class="controls">
								<table border="1" class="table  white" style="width: 95%;margin-bottom:0px;">
									<thead>
										<tr style="background-color: #fff;">
											<th>评价项目分类</th>
											<th>评价项目</th>
											<th style="width: 125px;">评价结果</th>
											<th style="width: 240px;">备注</th>
										</tr>
									</thead>
									<tbody style="background-color: #F9F9F9;">
										<c:forEach items="${itemVoList }" var="item" varStatus="i">
											<tr class="classification">
												<td class="td_${i.index}" id="${item.id}"><c:if
														test="${empty item.result }">
														<span style="color: red">(未评价)</span>
													</c:if>${item.classification}</td>
												<td>${item.name}</td>
												<td><select id="result_${i.index}" style="width: 80px"
													${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
														<option <c:if test="${item.result == 1 }">selected</c:if>
															value="1">优秀</option>
														<option <c:if test="${item.result == 2 }">selected</c:if>
															value="2">良好</option>
														<option <c:if test="${item.result == 3 }">selected</c:if>
															value="3">合格</option>
														<option <c:if test="${item.result == 4 }">selected</c:if>
															value="4">不合格</option>
												</select></td>
												<td><input id="remark_${i.index}" class="span4"
													data-id="spanRemark" name="remark_${i.index}"
													value="${item.resultRemark }"
													${isCK != null && isCk != '' ? "disabled='disabled'" : ""} /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="control-group" style="width:100%;">
							<label class="control-label"> 说明： </label>
							<div class="controls">
								<textarea id="remark" name="remark" rows="1" cols="1"
									style="width: 95%;margin-bottom:10px;"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${moralEvaluationStudent.remark}</textarea>

							</div>

						</div>

<div class="clear"></div>
						<div class="form-actions tan_bottom">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id"
									value="${moralEvaluationStudent.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
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
// 		checker = initValidatorAdd();
		checker = initValidator();
		$.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
 			"isEchoSelected" : true,
 			"yearSelectedVal" : "${moralEvaluationStudent.schoolYear}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${moralEvaluationStudent.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${moralEvaluationStudent.teamId}", //要回显的班级唯一标识
			"stuSelectedVal" : "${moralEvaluationStudent.studentId}" //要回显的学生唯一标识 
		});
	});

	function initValidator() {
		return $("#moralEvaluationStudent_form").validate({
			errorClass : "myerror",
			rules : {
				"totalEvaluation" : {
					selectNone : true
				},
				"remark" : {
					maxlength : 200
				}
			},
			messages : {

			}
		});
	}

	function initValidatorAdd() {
		var option1 = "{"; //option开始
		option1 = option1 + "\"remark\" : {\"maxlength\" : 5}";
		var result = "";
		var num = 0;
		$('input[data-id="spanRemark"]').each(function(index, value) {
			option1 = option1 + ",";
			result = $(this).attr("name");
// 			alert(result);
			var option2 = "";
			option2 = "\"" + result + "\"" + ":{\"maxlength\" : 2}";
			option1 = option1 + option2;
		});
		option1 = option1 + "}";
// 		alert(option1)
		var option = JSON.parse(option1);
// 		alert(option)

		return $("#moralEvaluationStudent_form").validate({
			errorClass : "myerror",
			rules : option,
			messages : {}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#moralEvaluationStudent_form");
			var classification = [];
			$(".classification").each(function(index, value) {
				var json = {};
				json.itemId = $(".td_" + index).attr("id");
				json.result = $("#result_" + index).val();
				json.remark = $("#remark_" + index).val();
				classification.push(json);
			});
			$requestData.classifications = JSON.stringify(classification);
			var url = "${ctp}/teach/moralEvaluationStudent/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/moralEvaluationStudent/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {

					}
				} else {
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>