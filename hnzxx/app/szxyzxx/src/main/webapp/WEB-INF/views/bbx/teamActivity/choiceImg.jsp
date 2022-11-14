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
.ban-active a img.on{border:2px solid red;}
</style>
<body style="background-color: #F3F3F3 !important;">
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
			<input type="hidden" id="teamId" name="teamId" value="${teamId }" />
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal padding15" id="user_form">
						
						<div class="control-group">
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="0" src="${ctp }/res/css/bbx/images/hd_0.png"></a>
								<div class="wenzi">默认封面</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="1" src="${ctp }/res/css/bbx/images/hd_zs_1.png"></a>
								<div class="wenzi">植树</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="2" src="${ctp }/res/css/bbx/images/hd_yd_2.png"></a>
								<div class="wenzi">运动</div>
							</div>
							<div class="ban-active left ">
								<a href="javascript:void(0)" class="feng"><img data-id="3" src="${ctp }/res/css/bbx/images/hd_wh_3.png"></a>
								<div class="wenzi">晚会</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="4" src="${ctp }/res/css/bbx/images/hd_mhyl_4.png"></a>
								<div class="wenzi">缅怀英烈</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="5" src="${ctp }/res/css/bbx/images/hd_jr_5.png"></a>
								<div class="wenzi">节日</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="6" src="${ctp }/res/css/bbx/images/hd_jy_6.png"></a>
								<div class="wenzi">郊游</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="7" src="${ctp }/res/css/bbx/images/hd_hb_7.png"></a>
								<div class="wenzi">环保</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="8" src="${ctp }/res/css/bbx/images/hd_gy_8.png"></a>
								<div class="wenzi">公益</div>
							</div>
							<div class="ban-active left">
								<a href="javascript:void(0)" class="feng"><img data-id="9" src="${ctp }/res/css/bbx/images/hd_ct_9.png"></a>
								<div class="wenzi">传统</div>
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