<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/16
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta name="apple-touch-fullscreen" content="YES">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="pragram" content="no-cache">
	<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">  -->
    <link href="${pageContext.request.contextPath}/res/css/extra/oauth2login.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
    <title>授权登录页</title>
</head>
<body>
<header>
    <a href="javascript:void(0)"></a>
</header>
<div>
    <div class="div_all">
        <div class="div1">
            <form action="" method="post">
            <p class="explain">用<span style="color:#2299ee;">定邦教育云</span>账号登录，一键通行更轻松</p>
            <div>
                <p >
                    <i class="i1"></i><input type="text" name="username" placeholder="请输入您的账号"/>
                </p>
                <p class="p2">
                    <i class="i2"></i><input type="password" name="password" placeholder="请输入您的密码"/>
                </p>
                <input class="login" type="submit" value="授权并登录" >
                <%--<a href="javascript:void(0)" class="login">授权并登录</a>--%>
            </div>
            <%--<a href="javascript:void(0)"  class="forget_mima">忘记密码？</a>--%>
            </form>
        </div>
        <div class="div2">
            <p class="p1"><span style="color:#1795ef;">${appName} </span>将获得以下权限：</p>
            <div class="div2_1">
                <p>
                    <i class="all_choose on"></i><span>全选</span>
                </p>
                <p>
                    <i class="choose on"></i><span>获得您的基本信息</span>
                </p>
            </div>
            <p class="p2">
                <%--授权后表明你已同意 <a href="javascript:void(0)"style="color:#1795ef;">定邦教育云登录服务协议</a>--%>
            </p>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script>
    $(function(){
        $('.div1 div p').click(function () {
            $(this).addClass('aa');
            $(this).siblings().removeClass('aa');
        });
        $('.div2_1 p i.choose').click(function(){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
            }else{
                $(this).addClass('on');
            }
        });
        $('.all_choose').click(function(){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                $('.choose').removeClass('on');

            }else{
                $(this).addClass('on');
                $('.choose').addClass('on');
            }
        });

    })

</script>
</body>
</html>
