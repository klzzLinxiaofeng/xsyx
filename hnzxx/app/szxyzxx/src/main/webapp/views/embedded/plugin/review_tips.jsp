<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <%@ include file="/views/embedded/common.jsp"%> --%>
<link href="${pageContext.request.contextPath}/res/css/extra/buttons.css" rel="stylesheet">
<title>温馨提示</title>
</head>
<style type="text/css">
    .ts{
    background: #fff;
}
.extension{
    width: 750px;  margin: 0 auto;background: #fff;
}
.link{
    color:#1b8cfa;font-size: 18px;text-align: center;margin: 0 auto;font-family:'微软雅黑';
}
.install{
    /*float: left;*/width:500px;float: right;margin-top: 70px;margin-right: 65px;text-align: center;
}
.install p{
    margin: 0;float: left;width: 100%;font-size: 24px;font-family: 'FZY3JW';margin-bottom: 20px;
}
img{
    float: left;margin:60px 0 0 90px;
}
a{
	text-decoration:underline;
}
</style>
<body >
<div class="ts">
    <div class="extension">
        <img alt="" src="${pageContext.request.contextPath}/res/images/flash.png">
        <div class="install">
            <p>您没有安装或禁用了<span style="color:#ed5565">flash</span>插件，请安装或启用后刷新页面</p>
            <a  href="https://get.adobe.com/cn/flashplayer/"  target="_blank" class="link">到Adobe去下载flash插件</a>
        </div>
    </div>
</div>

<div>
	
</div>
</body>
<script type="text/javascript">
   
</script>
</html>