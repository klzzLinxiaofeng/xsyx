<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}
.form-horizontal .controls{ margin:0;}
.form-horizontal .control-label{
 width:auto;
}
/* .form-horizontal{padding-left:50px;} */
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.radio_new input{ margin:0 5px; }
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="signageauto_form" action="javascript:void(0);">
							<div class="control-group" style="padding-left:50px;">
								<label class="control-label">
									上学时间：
								</label>
								<div class="controls">
									<input type="text" id="lateTime" name="lateTime" placeholder="上学时间" 
										value="<fmt:formatDate value="${lateTime}" pattern="HH:mm"/>"
										class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm'})"
										style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
								</div>
							</div>
							<div class="control-group" style="padding-left:50px;">
								<label class="control-label">
									放学时间：
								</label>
								<div class="controls">
									<input type="text" id="outEarlyTime" name="outEarlyTime" placeholder="放学时间" 
										value="<fmt:formatDate value="${outEarlyTime}" pattern="HH:mm"/>"
										class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm'})"
										style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
								</div>
							</div>
						<div class="form-actions tan_bottom tan_bottom_1">
							<input type="hidden" id="gradeId" name="gradeId" value="${gradeId}" />
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>


<%-- <body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="bwattendancetime_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label">
								上学时间:
							</label>
							<div class="controls">
							<input type="text" id="lateTime" name="lateTime" placeholder="上学时间" 
								value="<fmt:formatDate value="${bwAttendanceTime.attendanceTime}" pattern="HH:mm"/>"
								class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm'})"
								style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								放学时间:
							</label>
							<div class="controls">
							<input type="text" id="outEarlyTime" name="outEarlyTime" placeholder="开始时间" 
								value="<fmt:formatDate value="${bwAttendanceTime.attendanceTime}" pattern="HH:mm"/>"
								class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm'})"
								style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
							</div>
						</div>
						<!-- <div class="control-group radio_new">
							<input name="type" id="type" value="1" type="radio"/>上学
							<input name="type" id="type" value="2" type="radio"/>放学
						</div> -->
						<div class="form-actions tan_bottom_1">
							<input type="hidden" id="gradeId" name="gradeId" value="${gradeId}" />
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>				
				</div>
			</div>
		</div>
	</div>
</body> --%>
<script type="text/javascript">
	
	
	//保存或更新修改
	function saveOrUpdate() {
			var loader = new loadLayer();
			var gradeId = $("#gradeId").val();
			var lateTime = $("#lateTime").val();
			var outEarlyTime = $("#outEarlyTime").val();
			var $requestData = {};
			var url = "${ctp}/bbx/bpBwAttendanceTime/creator";
			if(lateTime === "" || lateTime ===undefined){
				$.error("请设置上学时间");
				return;
			}
			if(outEarlyTime === "" || outEarlyTime ===undefined){
				$.error("请设置放学时间");
				return;
			}
			$requestData.gradeId = gradeId;
			$requestData.lateTime = lateTime;
			$requestData.outEarlyTime = outEarlyTime;
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						parent.core_iframe.search();
						/* if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						} */
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
	
</script>
</html>