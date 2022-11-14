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
					<form class="form-horizontal tan_form" id="roomteam_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									班级：
								</label>
								<div class="controls">
								<input type="text" id="teamName" name="teamName" class="span13" placeholder="" value="${teamName}" readonly="readonly">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									绑定教室：
								</label>
								<div class="controls">
									<select id="room" name="room" class="span13">
										<option value="">请选择</option>
										<%-- <c:forEach>
											<option value=""></option>
										</c:forEach> --%>
									</select>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="teamId" name="teamId" value="${teamId}" />
							<input type="hidden" id="teamId" name="schoolId" value="${schoolId}" />
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">绑定</button>
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
		
		var schoolId = "${schoolId}";
		var roomId = "${roomId}";
		$.post(
			"${ctp}/bbx/RoomTeam/getRoomList", 
			{schoolId:schoolId},
			function(data) {
				var dataObj=eval("("+data+")");
				for(var i=0; i<dataObj.length; i++){
					if(dataObj[i].id == roomId){
						$("#room").append("<option selected=selected value=\"" + dataObj[i].id + "\">" + dataObj[i].name +"</option>");
					}else{
						$("#room").append("<option value=\"" + dataObj[i].id + "\">" + dataObj[i].name +"</option>");
					}
				}
			}
		);	
	});
	
	function initValidator() {
		return $("#roomteam_form").validate({
			errorClass : "myerror",
			rules : {
				"room" : {
					required : true,
					/* remote : {
						type : "POST",
						url : "${ctp}/bbx/RoomTeam/checkTeamRoom",
						data : {
							teamId : function() {return $("#teamId").val();},
							roomId : function() {return $("#room").val();}
						}
					} */
				},
			},
			messages : {
				
			}
		});
	}
	
	
	
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $requestData = {};
			$requestData.teamId = $("#teamId").val();
			$requestData.roomId = $("#room").val();	
			
			$.post("${ctp}/bbx/RoomTeam/checkTeamRoom",	$requestData, 
				function(data, status) {
					if(data == "false"){
						$.error("该教室已被其他班级绑定");
						return;
					}else{
						var loader = new loadLayer();
						var url = "${ctp}/bbx/RoomTeam/creator";
						loader.show();
						$.post(url, $requestData, function(data, status) {
							if("success" === status) {
								$.success('操作成功');
								data = eval("(" + data + ")");
								if("success" === data.info) {
									parent.core_iframe.ajaxFunction1();
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
			);
			
		}
	}
	
</script>
</html>