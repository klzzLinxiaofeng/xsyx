<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/buttons.css" rel="stylesheet">
<title>应用下载</title>
</head>
<style type="text/css">
    .download{
        background: #fff;  margin: 0 auto;width: 520px;padding-bottom: 20px;
    }
    .z{
        float: left;margin: 20px 0 0 30px;background: #fff;
    }
    .z img{
    	width:70px;height:70px;
    	}
    .z p{
        color:#808080;font-family:'微软雅黑';font-size: 14px;margin: 9px 0 0 0;line-height:23px;width:170px;
    }
    h6{
        font-family:'微软雅黑';margin: 0;font-size: 16px;color: #323232;
    }
    .xian{
        width: 1px;height:150px;background:#ccc;float: left;margin: 20px 30px 0 33px;
    }
    .lj a{
       margin-right: 25px;margin-top: 20px;
    }
    .lj a img{
          margin-top: 20px;
    }
    .sm{
        margin-left: -10px;
    }
    span.sm img{
          margin:18px 12px 0; width:155px;height:155px;border:1px solid #eee;
    }
    .z a{
    	display:block;width:97px;height:32px;text-indent:-9999px;float:left;margin:25px 25px 0 0;
    }
    .android{
    	background:url("../../res/images/android.png") no-repeat;
    }
    .ios{
    	background:url("../../res/images/ios.png") no-repeat;
    }
</style>
<body >
<div class="download" style="display:none;">
    <div class="z">
        <img alt="" src="${pageContext.request.contextPath}/res/images/yy_5.png">
        <div style="float:right;margin-left:10px;">
            <h6>教育云家长端</h6>
            <p style="">教育云家长端，随时随地，想学就学</p>
        </div>
        <div class="clear"></div>
        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.szxy.myedutohome.ui" target="_blank" class="android">Android</a>
        <a href="https://itunes.apple.com/cn/app/shu-zi-xiao-yuan-jia-zhang/id1076503499?mt=8" target="_blank" class="ios ">iOS</a>
    </div>
    <i class="xian"></i>
    <div class="y">
        <div class="lj">
        
        <!-- <a href="http://101.200.190.215:8888/education/szxyjz.apk" class="button button-primary button-pill ">PC</a> -->
        <span class="sm"><img src="${pageContext.request.contextPath}/res/images/szxy_jz.png"></span>
<%--         <span class="sm"><img src="${pageContext.request.contextPath}/res/images/eweima3.png"></span> --%>
    </div>
</div>
</div>
<div class="download" style="display:none;">
    <div class="z">
        <img alt="" src="${pageContext.request.contextPath}/res/images/yy_6.png">
        <div style="float:right;margin-left:10px;">
            <h6>教育云教师端</h6>
            <p >教育云教师端，随时随地，想学就学</p>
        </div>
        <div class="clear"></div>
        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.szxy.teacher.ui" target="_blank" class="android">Android</a>
        <a href="https://itunes.apple.com/cn/app/shu-zi-xiao-yuan-jiao-shi/id1076493213?mt=8" target="_blank" class="ios ">iOS</a>
    </div>
    <i class="xian"></i>
    <div class="y">
        <div class="lj">
        
        <!-- <a href="http://101.200.190.215:8888/education/szxyjz.apk" class="button button-primary button-pill ">PC</a> -->
        <span class="sm"><img src="${pageContext.request.contextPath}/res/images/szxy_js.png"></span>
<%--         <span class="sm"><img src="${pageContext.request.contextPath}/res/images/eweima3.png"></span> --%>
    </div>
</div>
</div>
</body>
<script type="text/javascript">
var r = window.location.search;
r = r.substring(1,r.Length);
$(function(){
	$(".download").hide();
	$(".download").eq(r).show();
})
</script>
</html>
