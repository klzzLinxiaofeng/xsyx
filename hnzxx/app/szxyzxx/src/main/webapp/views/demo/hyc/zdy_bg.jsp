<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<style>
</style>
<body style="background-color: #F3F3F3 !important;">
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
			<input type="hidden" id="teamId" name="teamId" value="${teamId }" />
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal padding15" id="user_form" >
						<div class="control-group">
							<div class="zdy_upload">
								<a href="javascript:void(0)" ></a>
								<img alt="" src="${ctp}/res/css/bbx/images/zdy.jpg">
							</div>
						</div>
					</form>
					<div class="form-actions tan_bottom_1">
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">确定</a>
								<a href="javascript:void(0)" onclick="closeWin();">取消</a>
						</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	var imgId = 0;
	var imgSrc;
	$(function() {
		$(".ban-active a img").on("click", function(){
			$(".ban-active a img").removeClass("on");
			$(this).addClass("on");
			imgId = $(this).attr("data-id");
			imgSrc = $(this).attr("src");
		});
	});
	
	function saveOrUpdate() {
		/* var teamId = $("#teamId").val();
		var url = '${ctp}/clazz/teamActivity/getImg?teamId='+teamId+'&imgId='+imgId+"&imgSrc="+imgSrc;
		$.initWinOnTopFromLeft('组织活动', url , '700', '600'); */
	    $("#activityImage", parent.window.document).val(imgId);
	    $("#src",parent.window.document).attr("src",imgSrc);
		$.closeWindow();
	}
	
	function closeWin(){
		$.confirm("确定离开此页面？", function() {
			$.closeWindow();
		});
	}
</script>


</html>