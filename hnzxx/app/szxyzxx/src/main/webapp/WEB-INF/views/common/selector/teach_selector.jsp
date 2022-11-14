<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp"%>
<style>
.form-horizontal .control-label {
	width: 60px
}

.form-horizontal .controls {
	margin-left: 80px;
}

.form-horizontal.tan_form {
	padding-bottom: 100px;
}

.form-horizontal .seach {
	float: left;
}

.chzn-container {
	float: left
}

.close_1 {
	color: #C5C5C5;
	position: absolute;
	font-size: 12px;
	font-weight: bold;
	cursor: pointer;
	top: 5px;
	right: 20px;
}

.cancelBubble {
	border: 1px solid #DFDFDF;
	padding: 0 20px;
	border-radius: 15px;
	margin-right: 20px;
	margin-bottom: 10px;
	display: inline-block;
	width: 138px;
	height: 24px;
	line-height: 24px;
	position: relative;
}

.cancelBubble span {
	display: block;
	width: 125px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	display: block;
	word-wrap: normal;
}

#clear_button {
	position: relative;
	top: -10px;
}

.table th, .table td {
	padding-left: 15px;
}
</style>
</head>
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 10px 0 0;">
					<form class="form-horizontal tan_form" id="role_form"
						action="javascript:void(0);">
						<div class="control-group seach"
							style="padding-bottom: 10px; margin-bottom: 10px;">
							<label class="control-label">姓名：</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span3"
									placeholder="请输入姓名关键字" value="" style="width: 120px;" maxlength="10">
								<!-- 								<button class="btn btn-warning" onclick="search();">搜索</button> -->
								<!-- 								<span class="red">请输入姓名关键字</span> -->
							</div>
						</div>
						<c:if test='${enableMultiCampus eq "true"}'>
							<div class="control-group seach" style="padding-bottom: 10px; margin-bottom: 10px;">
								<label class="control-label">校区：</label>
								<div class="controls" id="school_seleted_id">
									<select id="school_selected" style="width: 140px;"></select>
								</div>
							</div>
						</c:if>
						
						<div class="control-group seach" style="padding-bottom: 10px; margin-bottom: 10px;">
							<label class="control-label">部门：</label>
							<div class="controls" id="dept_seleted_id">
								<!-- 								<div id="bumen_chzn" class="chzn-container chzn-container-single" style="width: 220px;" title=""> -->
								<!-- 									<a tabindex="-1" class="chzn-single" href="javascript:void(0)"> -->
								<!-- 									<span id="dept_selected_name_span">请选择</span> -->
								<!-- 										<div> -->
								<!-- 											<b></b> -->
								<!-- 										</div> -->
								<!-- 									</a> -->
								<!-- 									<div class="chzn-drop" style="left: -9000px; width: 218px; top: 30px;"> -->
								<!-- 										<ul id="treeDept" class="ztree"> -->
								<!-- 										</ul> -->
								<!-- 									</div> -->
								<!-- 								</div> -->
							</div>
						</div>
						<div class="control-group seach">
							<label class="control-label">科目：</label>
							<div class="controls">
								<select id="subject" name="subject" style="width: 120px;">
								</select>
								<button class="btn btn-warning" onclick="search();">搜索</button>
								<!-- 								<span class="red">请输入姓名关键字</span> -->
							</div>
						</div>
						<c:if test='${param.enableBatch}'>
							<div class="clear"></div>
							<div class="control-group"
								style="border-bottom: 1px solid #999; padding-bottom: 10px; margin-bottom: 10px;">
								<label class="control-label">已选：</label>
								<div id="batch_data" class="controls">
									<!-- 									<button id="clear_button" class="btn btn-danger" onclick="batchClear();">清除</button> -->
								</div>
							</div>
						</c:if>
						<div class="clear"></div>
						<div class="select_view" id="teacher_list"></div>
						<div class="form-actions tan_bottom" style="">
							<c:if test='${param.enableBatch}'>
								<button class="btn btn-warning" type="button"
									onclick="batchAdd();">确认</button>
							</c:if>
							<button class="btn" type="button" onclick="$.closeWindow();">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

	var isTop = "${param.isTop}";
	$(function() {
		initCheckBtn();
		$.createDeptSelector({
			"deptContainer" : "#dept_seleted_id"
		});
		 var selectSubject = "${selectSubject}";
		var selectSubjectForbidden = "${selectSubjectForbidden}";
		$.PjSubjectSelector({
			"selectedVal" : selectSubject,
			"afterHandler" : function() {
				if (selectSubjectForbidden == "true") {
					$("#subject").attr("disabled", true);
				}
			}
		}); 
		$(".select_view").on("click", ".add-btn", function() {
			var $this = $(this);
			var id = $this.attr("data-id");
			var name = $this.attr("data-name");
			var ids = new Array();
			var names = new Array();
			ids.push(id);
			names.push(name);
			var data = {
				"ids" : ids,
				"names" : names,
				"windowName" : window.name,
				"btnId" : "${param.id}",
				"frameIndex" : parent.layer.getFrameIndex(window.name),
				"targetWindowName" : "${param.targetWN}"
			};
			var isSuc;
			if ("true" === isTop) {
				var $ifm = $("#core_iframecore_iframe", window.parent.document);
				if ($ifm.length > 0) {
					var ifm = $ifm.get(0);
					isSuc = ifm.contentWindow.selectedHandler(data);
				}
			} else {
				isSuc = window.parent.selectedHandler(data);
			}
			if (isSuc) {
				$.success("设置成功");
			}
		});
	});
	function batchClear() {
		$("#batch_data").empty();
	}
	function batchAdd() {
		// 			var checkedInput = $("#ryxx_trs").find("input:checkbox:checked");
		var checkedInput = $("#batch_data .cancelBubble");
		if (checkedInput.length <= 0) {
			$.error("您还未选择教师");
			return;
		}

		var ids = new Array();
		var names = new Array();
		$.each(checkedInput, function(index, value) {
			ids.push($(value).find("a").attr("data-id"));
			names.push($(value).find("span").text());
		});
		var idss = ids.join(",");
		var namess = names.join(",");
		var data = {
			"ids" : idss,
			"names" : namess,
			"windowName" : window.name,
			"btnId" : "${param.id}",
			"frameIndex" : parent.layer.getFrameIndex(window.name),
			"targetWindowName" : "${param.targetWN}"
		};
		var isSuc;
		if ("true" === isTop) {
			var $ifm = $("#core_iframe", window.parent.document);
			if ($ifm.length > 0) {
				var ifm = $ifm.get(0);
				isSuc = ifm.contentWindow.selectedHandler(data);
			}
		} else {
			isSuc = window.parent.selectedHandler(data);
		}
		if (isSuc) {
			$.success("设置成功");
		}
	}

	function search() {
		var requestData = {
			"type" : "0"
		};
		var excludeSelf = "${excludeSelf}";
		
		var enableMultiCampus = "${enableMultiCampus}";
		
		requestData.enableMultiCampus = enableMultiCampus;
		requestData.excludeSelf = excludeSelf;
		requestData.name = $("#name").val();
		var subjectCode = $("#subject").val();
		var deptId = $("#dept_seleted_id").attr("data-id");
		var schoolId = $("#school_selected").val();
		if (subjectCode != null && subjectCode != ""
				&& subjectCode != "undefinded") {
			requestData.subjectCode = subjectCode;
		}

		if (deptId != null && deptId != "" && deptId != "undefinded") {
			requestData.deptId = deptId;
		}
		
		if(schoolId !=null && schoolId !="" && schoolId !="undefinded"){
			requestData.schoolId = schoolId;
		}
		
		
		requestData.enableBatch = "${param.enableBatch}";
		requestData.usePage = true;
		$("#teacher_list").load("${ctp}/teach/teacher/list", requestData,
				function() {

				});
	}

	function initCheckBtn() {
		$(".select_view").on("click","#checkAll",function() {
					if (this.checked) {
						$("#ryxx_trs tr td").find("input:checkbox:checked").click();
					}
					$("#teacher_list #ryxx_trs tr td input").click();
					$("#ryxx_trs tr td input:checkbox[name='ids']").prop("checked", this.checked);
					if (!this.checked) {
						$("#teacher_list #ryxx_trs tr td").click();
						$("#ryxx_trs tr td input:checkbox[name='ids']").prop("checked", this.checked);
	 					$(".cancelBubble").remove();
					}
				});
	}
</script>

<script type="text/javascript">

$.MembershipSelector({
	   "selector" : "#school_selected",
	   "selectedVal" : "",
	   "condition" : {ownerType : "pj.branch_school",ownerId:"${sessionScope[sca:currentUserKey()].schoolId}"},
	   "afterHandler" : function() {
		}
});





	$(document).ready(function() {
		var enableMultiCampus = "${enableMultiCampus}";
		if ("true" === enableMultiCampus) {
			
			
			$("#school_selected").change(function() {
				var schoolId = $(this).val();
				$.createDeptSelector({
					"deptContainer" : "#dept_seleted_id",
					"schoolId" : schoolId,
					"enableBatch" : false
				}); 
				
	/* 			$("#subject").trigger("chosen:updated"); 
				$("#subject").trigger("liszt:updated");   */
				
				
				
			});
			
			
			$("#school_selected").change(function(){
				var schoolId = $(this).val();
// 				$("#subject").trigger("liszt:updated");
				 $.PjSubjectSelector({
					   "selector" : "#subject",
					   "selectedVal" : "",
					   "condition" : {"schoolId" : schoolId},
					   "afterHandler" : function(selector) {
						   selector.trigger("liszt:updated");
						}
				}); 
				 
				 
			});
			
			
			
		} else {
			$.createDeptSelector({
				"deptContainer" : "#dept_seleted_id",
				"enableBatch" : true,
				"clickCallback" : function(data) {
				}
			});
			
			 $.PjSubjectSelector({
				   "selector" : "#subject",
				   "selectedVal" : "",
				   "afterHandler" : function() {
					}
			}); 
			
			
			
			
		}
	});
</script>
</html>