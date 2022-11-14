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
					<form class="form-horizontal tan_form" id="uin_createMeeting_form" action="javascript:void(0);">
							<jsp:useBean id="now" class="java.util.Date" scope="page"/>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>会议主题：
								</label>
								<div class="controls">
									<input type="text" id="meetSubject" name="meetSubject" class="span13" placeholder="会议主题"  style="width:200px;" value="">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									开始时间：<br>
								</label>
								<div class="controls">
									 <input type="text" id="startTime" name="startTime" placeholder="开始时间,不填表示立即开始"
                           		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d'})" class="Wdate span13"  style="width:200px;" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>固定会议：
								</label>
								<div class="controls">
									<input type="radio" checked="checked" id="isRegular" name="isRegular" value="0"> 不是 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" id="isRegular" name="isRegular" value="1"> 是
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>密码：
								</label>
								<div class="controls">
									<input type="radio" checked="checked" name="needPswd" value="0"> 不需要 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="needPswd" value="1"> 需要
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									录制权限：
								</label>
								<div class="controls">
									<input type="radio" checked="checked" name="recordConf" value="0"> 没有 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="recordConf" value="1"> 有
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									会议发言：
								</label>
								<div class="controls">
									<input type="radio" checked="checked" name="autoMute" value="0"> 不允许 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="autoMute" value="1"> 允许
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									进入退出提示：
								</label>
								<div class="controls">
									<input type="radio" name="loginIvr" value="0"> 不播放 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="loginIvr" value="1"> 播放
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									语音发言申请：
								</label>
								<div class="controls">
									<input type="radio" checked="checked" name="autoAudio" value="0"> 不接受 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="autoAudio" value="1"> 接受
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建白板和演示文档：
								</label>
								<div class="controls">
									<input type="radio" name="createWB" value="0"> 不允许 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="createWB" value="1"> 允许
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									保存他人白板和演示文档：
								</label>
								<div class="controls">
									<input type="radio" name="saveWB" value="0"> 不允许 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="saveWB" value="1"> 允许
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									公聊：
								</label>
								<div class="controls">
									<input type="radio" name="publicIM" value="0"> 不允许 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="publicIM" value="1"> 允许
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									私聊：
								</label>
								<div class="controls">
									<input type="radio" name="privateIM" value="0"> 不允许 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="privateIM" value="1"> 允许
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									上传文件：
								</label>
								<div class="controls">
									<input type="radio" name="shareOrUpload" value="0"> 不允许 &nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" checked="checked" name="shareOrUpload" value="1"> 允许
								</div>
							</div>
						<div class="form-actions tan_bottom">
								<input type="hidden" name="knowledgeVersionCode" class="span13" placeholder="" value="${empty parent.knowledgeVersionCode ? code : parent.knowledgeVersionCode}">
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
		return $("#uin_createMeeting_form").validate({
			errorClass : "myerror",
			rules : {
				"meetSubject" : {
					required : true,
					maxlength: 50
				},
			},
			messages : {
			}
		});
	}
	
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#uin_createMeeting_form");
			var url = "${ctp}/uin/meeting/creator";
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