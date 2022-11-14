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
        background: #fff;  margin: 0 auto;width: 516px;padding-bottom: 20px;
    }
    .z{
        float: left;margin: 20px 0 0 30px;background: #fff;
    }
    .z img{
    	width:70px;height:70px;
    	}
    .z p{
        color:#b2b2b2;font-family:'微软雅黑';font-size: 14px;margin: 5px 0 0 0;
    }
    h6{
        font-family:'微软雅黑';margin: 0;font-size: 16px;color: #323232;
    }
    .xian{
        width: 1px;height:150px;background:#ccc;float: left;margin: 20px 30px 0 55px;
    }
    .lj a{
       margin-right: 35px;margin-top: 20px;
    }
    .lj a img{
          margin-top: 20px;
    }
    .sm{
        margin-left: -10px;
    }
    span.sm img{
          margin:10px 21px 0;
    }
</style>
<body >
<div class="download" style="display:none;">
    <div class="z">
        <img alt="" src="${pageContext.request.contextPath}/res/images/yy_5.png">
        <div style="float:right;margin-left:10px;">
            <h6>教育云家长端</h6>
            <p>v1.0 · 8.3MB</p>
            <p>更新于2015-10-13</p>
        </div>
        <p style="color:#666;margin-top:20px;">教育云家长端，随时随地，想学就学</p>
    </div>
    <i class="xian"></i>
    <div class="y">
        <div class="lj">
        <a href="http://101.200.190.215:8888/education/szxyjz.apk" class="button button-action button-pill">Android</a>
        <!-- <a href="http://www.bootcss.com/" class="button button-highlight button-pill ">iOS</a>
        <a href="http://101.200.190.215:8888/education/szxyjz.apk" class="button button-primary button-pill ">PC</a> -->
        <span class="sm"><img src="${pageContext.request.contextPath}/res/images/eweima1.png"></span>
    </div>
</div>
</div>
<div class="download" style="display:none;">
    <div class="z">
        <img alt="" src="${pageContext.request.contextPath}/res/images/yy_6.png">
        <div style="float:right;margin-left:10px;">
            <h6>教育云教师端</h6>
            <p>v1.0 · 7.5MB</p>
            <p>更新于2015-10-13</p>
        </div>
        <p style="color:#666;margin-top:20px;">教育云教师端，随时随地，想学就学</p>
    </div>
    <i class="xian"></i>
    <div class="y">
        <div class="lj">
        <a href="http://101.200.190.215:8888/education/szxyjs.apk" class="button button-action button-pill">Android</a>
        <!-- <a href="http://www.bootcss.com/" class="button button-highlight button-pill ">iOS</a>
        <a href="http://101.200.190.215:8888/education/szxyjz.apk" class="button button-primary button-pill ">PC</a> -->
        <span class="sm"><img src="${pageContext.request.contextPath}/res/images/eweima2.png"></span>
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