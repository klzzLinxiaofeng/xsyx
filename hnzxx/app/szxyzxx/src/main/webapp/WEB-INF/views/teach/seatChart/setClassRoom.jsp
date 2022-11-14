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
					<form class="form-horizontal tan_form" id="seatmanager_form" action="javascript:void(0);">
							<input type="hidden" id="gradeId" name="gradeId" value="${seatChart.gradeId }" />
							<input type="hidden" id="classId" name="classId" value="${seatChart.teamId }" />
							<input type="hidden" id="schoolYear" name="schoolYear" value="${seatChart.schoolYear }" />
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>类型:</label>
								<div class="controls">
									<select class="span4" id="classroomType" name="classroomType" onchange="onChangeClassroomType();">
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									教室名称:
								</label>
								<div class="controls">
								<select class="span4 chzn-select chzn-done" id="name" name="name" onchange="onChangeClassroomName();">
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所属大楼:
								</label>
								<div class="controls">
									<input type="text" id="floorId" name="floorId" class="span4" placeholder="" value="" disabled='disabled'>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所在楼层:
								</label>
								<div class="controls">
								<input type="text" id="storey" name="storey" class="span4" placeholder="" value="" disabled='disabled'>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									具体位置:
								</label>
								<div class="controls">
								<input type="text" id="position" name="position" class="span4" placeholder="" value="" disabled='disabled'>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${seatmanager.id}" />
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
		$.jcGcSelector("#classroomType", {"tc" : "JY-JSLX"}, '${seatChart.classroomType}');
		$("#name").html("");
	});
	
	function onChangeClassroomType(){
		$("#floorId").val("");
		$("#storey").val("");
		$("#position").val("");
		var type =  $("#classroomType").val();
		if(type==""){
			$("#name").html("");
		}
		if(type!=""){
			$.getClassroom({"type" : type}, function(data, status) {
				var $name = $("#name");
				$name.html("");
				$.each(data, function(index, value) {
					$name.append("<option value='" + value.id + "'>" + value.name + "</option>");
				});
				$("#name").find("option:first").attr("selected",true);
				var id = $("#name option:selected").val();
				var $requestData = {
						"id" : id
				};
				var url = "${ctp}/teach/seatChart/getClassroom";
				$.post(url, $requestData, function(data, status) {
					if("success" === status) {
						data = eval("(" + data + ")");
						$("#floorId").val(data.floorName);
						$("#storey").val(data.storey);
						$("#position").val(data.position);
					}else{
						$.error("操作失败");
					}
				});
			});
		}
	}
	
	function onChangeClassroomName(){
		var $requestData = {
				"id" : $("#name").val()
		};
		var url = "${ctp}/teach/seatChart/getClassroom";
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#floorId").val(data.floorName);
				$("#storey").val(data.storey);
				$("#position").val(data.position);
			}else{
				$.error("操作失败");
			}
		});
	}
	
	function initValidator() {
		return $("#seatmanager_form").validate({
			errorClass : "myerror",
			rules : {
				"classroomType" : {
					selectNone : true,
					remote:{
						url:"${ctp}/teach/seatChart/checkClassroomType",
						type:"post",
						dataType:"json",
						data:{
							"classroomType":function(){return $("#classroomType").val();},
							"teamId":function(){return $("#classId").val();}
						}
					}
				},
				"name" : {
					selectNone : true
				}
			},
			messages : {
				"classroomType" : {
					remote:"该类型的教室本班级已经拥有"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = {};
			$requestData.gradeId = $("#gradeId").val();
			$requestData.teamId = $("#classId").val();
			$requestData.schoolYear = $("#schoolYear").val();
			$requestData.classroomType = $("#classroomType option:selected").val();
			$requestData.classroomId = $("#name option:selected").val();
			$requestData.classroomName = $("#name option:selected").html();
			//alert(JSON.stringify($requestData));
			//return;
			var url = "${ctp}/teach/seatChart/creator";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							//parent.core_iframe.window.location.reload();
 							/* var $nj = $("#nj .on", parent.core_iframe.window.document);
 							var gradeId = $nj.attr('data-obj-id'); */
 							var teamId = $("#classId").val();
 							parent.core_iframe.ajaxClassFunction(teamId, function(){});
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