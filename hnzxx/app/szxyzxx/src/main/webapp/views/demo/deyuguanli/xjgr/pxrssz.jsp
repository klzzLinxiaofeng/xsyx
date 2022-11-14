<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>发展卡评价</title>
<style>
	.reflective-evaluate th, .reflective-evaluate td{border-right:0 none;}
</style>
<script type="text/javascript">
function onlyNum() {
    if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}
</script>
</head>
<body style="background-color:#fff;">
<div class="outnumber">
    <table class="outnumber-installed" id="">
        <thead>
            <tr role="row">
                <th>年级</th>
                <th>班级之星</th>
                <th>年级之星</th>
                <th>全校之星</th>
            </tr>
        </thead>
        <tbody id="module_list_content">
            <tr class="">
                <td>一年级</td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td rowspan=6><input type="text" class="school-star" onkeydown="onlyNum();"></td>
            </tr>
            <tr class="">
                <td>二年级</td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td><input type="text" onkeydown="onlyNum();"></td>
            </tr>
            <tr class="">
                <td>三年级</td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td><input type="text" onkeydown="onlyNum();"></td>
            </tr>
            <tr class="">
                <td>四年级</td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td><input type="text" onkeydown="onlyNum();"></td>
            </tr>
            <tr class="">
                <td>五年级</td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td><input type="text" onkeydown="onlyNum();"></td>
            </tr>
            <tr class="">
                <td>六年级</td>
                <td><input type="text" onkeydown="onlyNum();"></td>
                <td><input type="text" onkeydown="onlyNum();"></td>
            </tr>
        </tbody>
    </table>
    <button class="btn-ensure">保存</button>
</div>
</body>
</html>