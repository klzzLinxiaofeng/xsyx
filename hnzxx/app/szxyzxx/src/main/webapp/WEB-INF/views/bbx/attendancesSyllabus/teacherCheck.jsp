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
</head>
<body>
<style>
.check_con{width: 90%; margin:0 auto; color: #444}
.check_con .check_con_top{ overflow: hidden; line-height: 30px; margin: 35px 0; margin-top:12px;}
.check_con_top .check_con_top_left{ float: left; line-height: 32px;}
.check_con_top .check_con_top_left span{line-height: 30px;display: inline-block;margin-right: 30px;}
.check_con_top .check_con_top_right{ float: right;overflow:hidden; line-height: 30px;}
.check_con_top .check_con_top_right>div{ float:left; margin-left: 10px; line-height: 32px;}
.check_con_top .check_con_top_right>div input{ line-height: 30px; }
.check_con_top .check_con_top_right>div select{ height: 30px; width: 140px}
.check_but{background: #e68923; border: none; color:#fff; padding:0 15px; cursor:pointer}
.check_con_bottom_title{border-bottom:1px solid #ebebeb; overflow: hidden; margin-bottom: 10px;}
.check_con_bottom_title .check_con_title_left{ float: left; font-weight: bold}
.check_con_bottom_title .check_con_title_right{float: right}
.check_con_bottom_title .check_con_title_right{ display: inline-block;}
.check_con_bottom_list ul li{ width: 25%; float: left; }
.check_con_bottom_list ul li span{text-align: center; background: #ebebeb;display: block; width: 90%; margin: 10px auto;}
#table_check{ width: 100%}
#table_check th{ font-weight: bold; border-bottom: 1px solid #ebebeb; text-align: left}
#table_check td{ line-height: 35px}
#table_check td:nth-child(2){ color: #0fa228}
#table_check td:nth-child(2).red{ color: red;padding:0;}
</style>
<div class="check_con">
    <div class="check_con_top">
        <div class="check_con_top_left">
        	<span>?????????<jcgc:cache tableCode="XY-JY-XINGQI" value="${dayOfWeek}"></jcgc:cache></span>
        	<span>?????????${lesson }</span>
        	<input type="hidden" id="syllabusType" name="syllabusType" value="${syllabusType}">
        </div>
        <div class="check_con_top_right">
            <div>???????????????</div>
            <div>
<!--                 <select id="week" onchange="search()"> -->
                <select id="week">
                   <!--  <option value ="volvo">????????????</option> -->
                    <c:forEach items="${weeks}" var="week">
                    	<c:choose>
                    		<c:when test="${week.isCurrent eq true}">
                    			<option value ="${week.week }" selected="selected">???${week.week }???</option>
                    		</c:when>
                    		<c:otherwise>
			                    <option value ="${week.week }">???${week.week }???</option>
                    		</c:otherwise>
                    	</c:choose>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="button"  value="??????" onclick="search()" class="check_but">
            </div>
        </div>
    </div>
    <input type="hidden" id="lessonId" value="${lessonId }"/>
    <div class="check_con_bottom">
<!--         <div class="check_con_bottom_title"> -->
<!--             <div class="check_con_title_left">??????????????????</div> -->
<!--             <div class="check_con_title_right"> -->
<!-- 	            <span style="margin-right: 35px;">????????????56???</span> -->
<!-- 	            <span style="color: red">????????????4</span> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="check_con_bottom_list"> -->
<!--             <ul> -->
<!--                 <li><span>??????</span></li> -->
<!--                 <li><span>??????</span></li> -->
<!--                 <li><span>??????</span></li> -->
<!--                 <li><span>??????</span></li> -->
<!--                 <li><span>??????</span></li> -->
<!--                 <li><span>??????</span></li> -->
<!--             </ul> -->
<!--         </div> -->
    </div>
</div>
</body>
<script>
function search(){
	var loader = new loadLayer();
	var week = $('.check_con_top_right option:selected').val();
	var lessonId = $("#lessonId").val();
	var $requestData = {};
	$requestData.week = week;
	$requestData.syllabusType = $("#syllabusType").val();
	$requestData.lessonId = lessonId;
	var url = "${ctp}/bbx/attendancesSyllabus/teacher/list";
	loader.show();
	$.post(url,$requestData,function(data,status){
		if(status === "success"){
			$(".check_con_bottom").html(data);
		}
	loader.close();
	});
}
search();
</script>
</html>