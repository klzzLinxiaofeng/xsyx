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
.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
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
							<input type="hidden" id="seatId" name="seatId" value="${seatId }" />
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>座位类型
								</label>
								<div class="controls">
								      <input type="radio" id="seatType" name="seatType" class="span5"  value="1" checked="checked" />单人桌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="seatType" name="seatType" class="span5"  value="2" />双人桌
								</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>行数</label>
							<div class="controls">
								<input type="text" id="row" name="row" class="span4"
									placeholder="请输入行数, 不能为空" value="">
							</div>
							</div>
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>列数</label>
							<div class="controls">
								<input type="text" id="col" name="col" class="span4"
									placeholder="请输入列数, 不能为空" value="">
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
	});
	function initValidator() {
		return $("#seatmanager_form").validate({
			errorClass : "myerror",
			rules : {
				"seatType" : {
					selectNone : true,
				},
				"row" : {
					required : true,
					isIntGtZero:true,
					max:10
				},
				"col" : {
					required : true,
					isIntGtZero:true,
					max:10,
					remote:{
						url:"${ctp}/teach/seatChart/checkCol",
						type:"post",
						dataType:"json",
						data:{
							"seatType":function(){return $('input:radio:checked').val();},
							"col":function(){return $("#col").val();}
						}
					}
				}
			},
			messages : {
				"col" : {
					remote:"双人座列数为偶数"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = {
					"id":$("#seatId").val(),
					"row":$("#row").val(),
					"col":$("#col").val(),
					"seatType":$('input:radio:checked').val()
			};
			var url = "${ctp}/teach/seatChart/updateSeatSize";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					//parent.core_iframe.window.location.reload();
					var classroomType = $("#js .on", parent.core_iframe.window.document).attr("data-obj-id");
					var teamId = $("#classId", parent.core_iframe.window.document).val();
					parent.core_iframe.ajaxSeatChartFunction(classroomType,teamId);
					$.closeWindow();
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>