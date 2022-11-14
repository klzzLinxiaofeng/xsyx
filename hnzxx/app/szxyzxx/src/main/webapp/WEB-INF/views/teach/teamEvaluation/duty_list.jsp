<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style type="text/css">
.select_b .select_div{
	margin: 7px 0 0 0;
}
.attendant{
    min-height: 600px;
    background: #fff;
}
.time-period{
    color: #535353;
    font-size: 12px;
    font-weight: bold;
    text-align: center;
    padding:25px 0 15px 0;
}
.personnel{
    margin: 0 25px;
    border-top: 1px #d4d4d4 solid;
    min-height: 200px;
    border-right: 1px #d4d4d4 solid;
}
.personnel ul li:first-child{
    height: 40px;
    line-height: 40px;
    background: #daf0fb;
    color: #4c708a;
    font-size: 12px;
}
.personnel ul li{
    height: 40px;
    line-height: 40px;
    text-align: center;
    border-left: 1px #d4d4d4 solid;
    border-bottom: 1px #d4d4d4 solid;
}
.confirm-staff{
    background:url(${pageContext.request.contextPath}/res/css/dygl/images/confirm_staff.png) no-repeat #99ffa5 75% 15px;
}
.restrict{
    float: left;
    width: 18.9%;
}
.time-period span {
    color: #333;
    line-height: 65px;
    text-align: center;
    margin: 0;
    font-size: 16px;
    font-weight: bold;
}
</style>
<script type="text/javascript">
$(function(){
	$(".personnel").on("click","ul li",function(){
        if($(this).hasClass("cannot")){
        }else{
            $(this).toggleClass("confirm-staff");
        }
    })
});
</script>
</head>
<body>

	<div class="time-period"><span>${title }</span></div>
	<div class="personnel">
		<ul style="width:5.5%;" class="restrict">
			<li class="cannot">编号</li>
			<c:forEach var="number" begin="1" end="${count }">
				<li class="cannot">${number }</li>
			</c:forEach>
		</ul>
		<c:forEach items="${dayList}" var="day">
			<ul class="restrict">
				<li class="cannot" data-date="${day.date}">${day.dayOfWeek }</li>
				<c:forEach items="${day.teacherList}" var="teacher">
					<li <c:if test="${teacher.isChoose == 1}">class="confirm-staff"</c:if> 
						data-ti="${teacher.teacherId }" data-ui="${teacher.userId }">
						${teacher.teacherName}
					</li>
				</c:forEach>
				
			</ul>
		</c:forEach>
		
		<div class="clear"></div>
	</div>

</body>
<script type="text/javascript">
// 	var week = $("#select_week").val();
// 	week = week.substring(week.indexOf("(")+1,week.indexOf(")"));
// 	var weekStr = week.split("~");
// 	var begin = weekStr[0];
// 	var end = weekStr[1];
// 	var beginDate = Date.parse(new Date(begin));   //转为毫秒数
// 	var endDate = Date.parse(new Date(end));   //转为毫秒数
	
	var beginDate = Date.parse(new Date("${beginDate}"));   
	var endDate = Date.parse(new Date("${endDate}"));   
	var today = Date.parse(new Date("${today}"));
	//当前时间小于终止时间+1 时可以保存
	if(today >= (endDate+86400000)){
		$("#btn_save").css("display","none");
		$(".personnel li").each(function(){
			$(this).addClass("cannot");
		});
	}else{
		$("#btn_save").css("display","block");
	}
	
	
	
</script>
</html>