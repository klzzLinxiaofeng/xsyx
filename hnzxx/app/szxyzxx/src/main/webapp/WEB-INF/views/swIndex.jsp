<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>西沃测试</title>
    <script src="/res/plugin/falgun/js/jquery.js"></script>

    <style>
        .sw-iframe{
            width: 100%;
            height: 1000px
        }
    </style>
</head>

<body>

<button onclick="login()">自动登录</button>

<iframe id="sw" class="sw-iframe"
        src="//campus.seewo.com/#/"
        frameborder="0"></iframe>
</body>

</html>

<script type="text/javascript">

    $(function(){

    });

    function login(){
        alert($("#sw").contents().find("title").html())
    }
</script>
