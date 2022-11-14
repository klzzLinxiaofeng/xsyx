<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>详情</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.manage_statistics{
    width: 500px;
    font-family: '微软雅黑';
    font-size: 12px;
    margin: 30px auto;
}
.manage_statistics table{
    width: 100%;
    border-right:1px #ccc solid;
    border-left: 1px #ccc solid;
}
.manage_statistics table tr{
    height: 35px;
}

.manage_sc{
	width:80px;
    text-align: center;
    border-right: 1px #ccc solid;
    border-bottom: 1px #ccc solid;
}
.manage_mc{
    border-bottom: 1px #ccc solid;
    padding-left: 15px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important">
	<div class="manage_statistics">
    <table cellspacing="0">
        <caption style="border:1px #ccc solid;height:30px;line-height:30px;">${name }</caption>
        <tbody>
            <tr>
                <td class="manage_sc">已上传人</td>
                <td class="manage_mc">${hasPhoto }</td>
            </tr>
            <tr>
                <td class="manage_sc">未上传人</td>
                <td class="manage_mc">${noPhoto }</td>
            </tr>
        </tbody>
    </table>
	</div>
</div>
</body>
</html>