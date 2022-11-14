<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta name="format-detection" content="telphone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>软件下载</title>
</head>
<style>

    body,html{ height: 100%; width: 100%;  margin:0;padding: 0; overflow: hidden; max-width: 640px; margin: 0 auto}
    body{ background: url("${pageContext.request.contextPath}/res/images/bbx/bp/download/download.jpg") 0 50% no-repeat;  background-size: 100%;}
    .but a{ background: #fff; display: block; width: 60%; margin: 0 auto; border-radius: 100px; margin-top: 7% }
    .but  a img{ width: 100%;vertical-align: top}
    a:active { background: #dae9f0}
    a:active img { background: none; list-style: none}
    .but{ margin-top: 68%}
</style>
<body>
        <div class="but">
            <a href="${downloadUrl} "><img src="${pageContext.request.contextPath}/res/images/bbx/bp/download/android.png" alt=""></a>
            <a href="https://itunes.apple.com/cn/app/%E6%B1%89%E6%95%99%E4%BA%91/id1261222022?mt=8"><img src="${pageContext.request.contextPath}/res/images/bbx/bp/download/ios.png" alt=""></a>
        </div>
</body>
<script type="text/javascript">

</script>
</html>