<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@include file="/views/embedded/plugin/jquery.jsp" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
		#alert_info{height:470px;background:url("${pageContext.request.contextPath}/res/css/extra/images/logout.png") no-repeat center center;margin-top:7%;text-align:center;padding-top:130px;font-size:18px}
		#error_info{height:150px;text-align:center;padding-top:350px;font-size:18px}
		#alert_info span{color:#ED5565;font-size:30px;font-family:"微软雅黑";position:relative;left:-50px;}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		登出成功，正在为您跳转至登录页
	</title>
</head>
<body style="background-color:#E9E7E8">
<div id="alert_info" >
	<span>5</span>
</div>
</body>
<script type="text/javascript">
    var s = 5;
    var interVal = window.setInterval(showalert, 1000);
    function showalert() {
        s--;
        $("#alert_info").html("<span>" + s + "</span>");
    }
    //定时执行，5秒后执行show()
    window.setTimeout(show, 5000);
    function show() {
        window.clearInterval(interVal);
      if(top != null) {
            top.window.location.href = "${pageContext.request.contextPath}/casLogout";
        } else {
            window.location.href= "${pageContext.request.contextPath}/casLogout";
        } 
      document.cookie="schoolId="+null;
      
      var $requestData = {};
		 var url = "http://172.16.30.59:8112/cr/json/logout";
      $.ajax({
          async : false,
          url: url,
          type: "GET",
          dataType: 'jsonp',
          jsonp: 'callback',
          jsonpCallback:"jsonpcallback",
          data: $requestData,
          timeout: 5000,
          complete : function(json) {
          }
      });
    }
    <%-- var ennable = "<%= SysContants.COMMON_RESOURCE_ENABLE%>";
    if("true" === ennable) {
        var serverName = "${pageContext.request.serverName}";
        var basePath = "<%= SysContants.COMMON_RESOURCE_BASE_PATH%>";
        if (basePath.indexOf(";") != -1) {
            var basePaths = basePath.split(";");
            for (var i = 0;i < basePaths.length; i++)
            {
                if (basePaths[i].indexOf(serverName) != -1) {
                    basePath = basePaths[i].substring(basePaths[i].indexOf("=") + 1, basePaths[i].length);
                }

            }
        }
--%>
       
    
</script>
</html>
${logout_msg}