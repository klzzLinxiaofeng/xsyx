<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>下载</title>
<%@ include file="/views/embedded/common.jsp"%>
<%-- <script src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bbx/ban.css">
</head>
<body style="overflow-x:hidden">
<div class="top w1200">
	<h1><img src="${pageContext.request.contextPath}/res/images/bbx/main/logo.png"/></h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/bbx/login">首页</a></li>
		<li class="on"><a href="javascript:void(0)">下载</a></li>
	</ul>
	<div class="tel"><span>400-6088260</span><i>客服电话</i></div>
</div>
<div class="wrap">
	<div class="img img-a">
		<div style="width:870px;margin:0 auto;padding:353px 0 0 130px;">
			<a href="" style="display:block;width:276px;height:71px;" id="bbx_pc_download"></a>
			<p style="color:#fff;padding:16px 0 0 0;bottom:50px;left:50px;" id="bbx_pc_download_desc">
<!-- 				版本号：v3.1.0&nbsp;大小：72.28MB&nbsp;更新日期：2016/8/25 -->
			</p>
		</div>
	</div>
	<div class="img img-c">
	<div style="width:287px;margin:0 auto; padding:214px 0 0 612px">
		<a href="http://files.jiaoxueyun.com/develop/resources/mobileVersion/temp/2016/08-06/other/1470447931517.apk" style="display:block;width:276px;height:71px;padding-left:5px;"></a>
		<p style="color:#fff;padding:11px 0 0 2px;bottom:50px;left:50px;">版本：v1.0&nbsp;大小：9.8MB&nbsp;日期：2016-08-06</p>
	</div>
	</div>
	<div class="img img-b">
	<div style="width:287px;margin:0 auto; padding:214px 713px 0 0;">
		<a href="" style="display:block;width:276px;height:71px;padding-left:5px;" id="bbx_app_download"></a>
		<p style="color:#fff;padding:11px 0 0 2px;bottom:50px;left:50px;" id="bbx_app_download_desc">
<!-- 			版本：v3.0&nbsp;大小：8.61MB&nbsp;更新日期：2016/6/20 -->
		</p>
	</div>
	</div>
</div>

<div class="footer">
	<div class="w1200 bg_img">
		<a class="qq">在线客服</a>
	</div>
</div>
<script type="text/javascript">
	/* $(function(){
		$.getJSON("http://jiaoxueyun.com/common/mobile/mobileVersionAction_findLatestVersion.action?appCode=014", function(json){
			  alert("JSON Data: " + json.appCode);
			});
	}); */
	$(function(){
		$.post("${ctp}/public/app/release/getCurrent",{"appKey":"xunyun#bbx#pc"}, function(data, status){
			if ("success" === status) {
				data = eval("(" + data + ")");
				$("#bbx_pc_download").attr("href", data.data[0].setupFile);
				$("#bbx_pc_download").show();
				$("#bbx_pc_download_desc").html("版本：" + data.data[0].version + "&nbsp;大小：" + data.data[0].packageSize + "&nbsp;日期：" + data.data[0].releaseDate.substring(0, 10));
			}
		});
		
		$.post("${ctp}/public/app/release/getCurrent",{"appKey":"xunyun#educloud#mobile#android"}, function(data, status){
			if ("success" === status) {
				data = eval("(" + data + ")");
				$("#bbx_app_download").attr("href", data.data[0].setupFile);
				$("#bbx_app_download").show();
				$("#bbx_app_download_desc").html("版本：" + data.data[0].version + "&nbsp;大小：" + data.data[0].packageSize + "&nbsp;日期：" + data.data[0].releaseDate.substring(0, 10));
			}
		});
	})
</script>
</body>
</html>