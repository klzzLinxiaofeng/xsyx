<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
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
					<form class="form-horizontal tan_form" id="signageauto_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									教室
								</label>
								<div class="controls">
									<input type="text" val="roomName" class="span13" readonly="readonly" disabled="disabled" value="${roomName}"/>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期一
								</label>
								<div class="controls">
									<input type="text" id="mondayOpenTime" name="mondayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'mondayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.mondayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="mondayCloseTime" name="mondayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'mondayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.mondayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="mondayStatus" name="mondayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期二
								</label>
								<div class="controls">
									<input type="text" id="tuesdayOpenTime" name="tuesdayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'tuesdayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.tuesdayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="tuesdayCloseTime" name="tuesdayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'tuesdayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.tuesdayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="tuesdayStatus" name="tuesdayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期三
								</label>
								<div class="controls">
									<input type="text" id="wednesdayOpenTime" name="wednesdayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'wednesdayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.wednesdayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="wednesdayCloseTime" name="wednesdayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'wednesdayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.wednesdayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="wednesdayStatus" name="wednesdayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期四
								</label>
								<div class="controls">
									<input type="text" id="thursdayOpenTime" name="thursdayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'thursdayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.thursdayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="thursdayCloseTime" name="thursdayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'thursdayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.thursdayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="thursdayStatus" name="thursdayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期五
								</label>
								<div class="controls">
									<input type="text" id="fridayOpenTime" name="fridayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'fridayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.fridayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="fridayCloseTime" name="fridayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'fridayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.fridayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="fridayStatus" name="fridayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期六
								</label>
								<div class="controls">
									<input type="text" id="saturdayOpenTime" name="saturdayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'saturdayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.saturdayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="saturdayCloseTime" name="saturdayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'saturdayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.saturdayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="saturdayStatus" name="saturdayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									星期日
								</label>
								<div class="controls">
									<input type="text" id="sundayOpenTime" name="sundayOpenTime" placeholder="开机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'sundayCloseTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.sundayOpenTime}" pattern="HH:mm"/>" /> 至
									<input type="text" id="sundayCloseTime" name="sundayCloseTime" placeholder="关机时间"									
										class="Wdate" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"	
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'sundayOpenTime\')}', lang:'zh-cn', dateFmt:'HH:mm'})"
										value="<fmt:formatDate value="${signageAutoVo.sundayCloseTime}" pattern="HH:mm"/>" /> 
									<select id="sundayStatus" name="sundayStatus" style="width: 80px;margin-left: 5px">
										<option value="0">未启用</option>
										<option value="1">启用</option>
									</select>
								</div>
							</div>
						<div class="form-actions tan_bottom_1">
							<input type="hidden" id="roomId" name="roomId" value="${roomId}" />
							<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">保存</a>
							<a href="javascript:void(0)" onclick="$.closeWindow();">取消</a>
							<!-- <button class="btn btn-warning" type="button" onclick="saveOrUpdate();">保存</button> -->
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
		
		$("#mondayStatus").val("${signageAutoVo.mondayStatus}");
		$("#tuesdayStatus").val("${signageAutoVo.tuesdayStatus}");
		$("#wednesdayStatus").val("${signageAutoVo.wednesdayStatus}");
		$("#thursdayStatus").val("${signageAutoVo.thursdayStatus}");
		$("#fridayStatus").val("${signageAutoVo.fridayStatus}");
		$("#saturdayStatus").val("${signageAutoVo.saturdayStatus}");
		$("#sundayStatus").val("${signageAutoVo.sundayStatus}");
	});
	
	function initValidator() {
		return $("#signageauto_form").validate({
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
			var loader = new loadLayer();			
			var $requestData = {};	
			$requestData.roomId = $("#roomId").val();
			$requestData.mondayOpenTime = $("#mondayOpenTime").val();
			$requestData.mondayCloseTime = $("#mondayCloseTime").val();
			$requestData.mondayStatus = $("#mondayStatus").val();
			$requestData.tuesdayOpenTime = $("#tuesdayOpenTime").val();
			$requestData.tuesdayCloseTime = $("#tuesdayCloseTime").val();
			$requestData.tuesdayStatus = $("#tuesdayStatus").val();
			$requestData.wednesdayOpenTime = $("#wednesdayOpenTime").val();
			$requestData.wednesdayCloseTime = $("#wednesdayCloseTime").val();
			$requestData.wednesdayStatus = $("#wednesdayStatus").val();
			$requestData.thursdayOpenTime = $("#thursdayOpenTime").val();
			$requestData.thursdayCloseTime = $("#thursdayCloseTime").val();
			$requestData.thursdayStatus = $("#thursdayStatus").val();
			$requestData.fridayOpenTime = $("#fridayOpenTime").val();
			$requestData.fridayCloseTime = $("#fridayCloseTime").val();
			$requestData.fridayStatus = $("#fridayStatus").val();
			$requestData.saturdayOpenTime = $("#saturdayOpenTime").val();
			$requestData.saturdayCloseTime = $("#saturdayCloseTime").val();
			$requestData.saturdayStatus = $("#saturdayStatus").val();
			$requestData.sundayOpenTime = $("#sundayOpenTime").val();
			$requestData.sundayCloseTime = $("#sundayCloseTime").val();	
			$requestData.sundayStatus = $("#sundayStatus").val();			
			var url = "${ctp}/bbx/signageAutoByRoom/creator";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
							parent.core_iframe.searchData();
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