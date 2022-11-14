<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bbx/bbx.css">
<title>app下载</title>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
        <div class="row-fluid">
          
                <div class="xiazai">
                    <div class="android">
                        <h1>班班星Android端</h1>
                        <p style="margin-top:40px;">版本：V1.0.0</p>
                        <p>更新：2015-12-30</p>
                        <p>大小：56.1MB</p>
                        <a href="javascript:void(0);"class="xz"></a>
                        <div class="clear"></div>
                        <div class="ewm">
                            <img src="${pageContext.request.contextPath}/res/css/bbx/images/ewm.jpg" height="125" width="125">
                            <p>手机扫描二维码下载</p>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</body>
</html>
