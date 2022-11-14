<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
<style>
.check_con_bottom_list ul{ overflow:hidden; display:none;}
.tan_bottom{left:0;}
.check_con_title_right span{ cursor: pointer;}
.check_con_title_right span.backg_span{ color:#fff;}
.check_con_title_right span{ display:inline-block; float:left; width:auto; padding:0 10px; line-height:30px; color:#e0dddd; margin-left:10px;}
.check_con_bottom_list ul li span{border: 1px solid #00b2ff;
padding: 5px 0;background:none; cursor: pointer;}
.check_con_bottom_list ul li span.over{    background: #00b2ff; color: #fff;}
p{padding:0; margin:0;}
</style>
</head>
 <div class="check_con_bottom_title">
    <div class="check_con_title_left">学生列表</div>
    <%--<div class="check_con_title_right">
     <span style="background:#00b2ff;">签到人数${turnOutList.size() }</span>
     <span style="background:#ef401b;" class="backg_span">缺勤人数${absenceList.size() }</span>
    </div>--%>
</div> 
<div class="check_con_bottom_list">
    <%-- <ul style="">
    	<c:forEach items="${turnOutList}" var="list">
	        <li><span id="${list.studentUserId }">${list.studentName }</span></li>
    	</c:forEach>
    </ul> --%>
    <ul style="display:block;" class='a_ul'>
    	<c:forEach items="${items}" var="item">
	        <li><%-- <span id="${list.studentUserId }"  studentId="${list.id }">${list.studentName }</span> --%>
	        	<p style="font-size:16px;">${item.studentName}</p>
	        	<p style="font-size:12px; color:#8a8a8a">${item.teamName}</p>
	        	
	        </li>
    	</c:forEach>
    </ul>
</div>
<div class="form-actions tan_bottom">
		<button class="btn btn-danger" type="button" onclick="$.closeWindow();">关闭</button>
</div>

</html>
<script>


</script>