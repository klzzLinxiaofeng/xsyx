<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>校园广播登录</title>
    <%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<script type="text/javascript">
    $(function(){
        toIndexPage();
    })

    function toIndexPage(){
        //沿河接口路径
        var url = "http://119.1.223.145:70/jld/login.aspx";
        var param = "?uid=${uid}&pwd=${pwd}";
        window.open(url + param);
    }
</script>
</body>
</html>