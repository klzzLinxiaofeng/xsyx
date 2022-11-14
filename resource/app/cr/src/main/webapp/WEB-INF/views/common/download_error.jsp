<%@page import="platform.education.resource.contants.EntityIOStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>下载错误</title>
    </head>
    <body>
        
    </body>
    <script type="text/javascript">
       $(function(){
            var flag = "${flag}";
            if(flag == "<%=EntityIOStatus.DOWNLOAD_FAIL_NOT_EXIST%>"){
                $.alert("下载失败,远程文件不存在");
            }else if(flag == "<%=EntityIOStatus.SYSTEM_ERROR%>"){
                $.alert("下载失败,系统错误");
            }else if(flag == "<%=EntityIOStatus.ENTITY_NOT_EXIST%>"){
                $.alert("下载失败,数据库实体文件不存在");
            }else{
                $.alert("下载失败,未知错误");
            }
        });
    </script>
</html>
