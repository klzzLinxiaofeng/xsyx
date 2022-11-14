<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/res/css/bp/h5/login/style.css">
    <%@ include file="/views/embedded/common.jsp"%>
    <title>选课页面</title>
    <style>

        html{ background: url("/res/images/bp/h5/course/bg.png") no-repeat 0 bottom; background-size: 100%; height: 100%; max-width: 640px; margin: 0 auto}
        .head{
            background: -webkit-linear-gradient(115deg, #75cfff, #06a2f5); /* Safari 5.1 - 6.0 */
            background: -o-linear-gradient(115deg,#75cfff, #06a2f5); /* Opera 11.1 - 12.0 */
            background: -moz-linear-gradient(115deg, #75cfff, #06a2f5); /* Firefox 3.6 - 15 */
            background: linear-gradient(115deg,#75cfff, #06a2f5); /* 标准的语法（必须放在最后） */
            padding-bottom:12px;padding-top: 0px;;
            position: relative;
        }
        .head p{ color: #fff; text-align: center;font-size: 16px; line-height: 2;}
        .list_span { color: #06a2f5; font-size: 16px; line-height: 25px;}
        .binp{ text-align: center; padding-top: 12%;}
      </style>
</head>
<body>
    <div class="head">
         <p style="position: absolute; left: 8%; top: 12px;">
        	<a href="${ctp}/bp/h5/home"><img src="/res/images/bp/h5/course/index_ico.png" alt="" width="20px"></a>
        </p>
        <p style=" padding: 8px 0; font-weight: bold; font-size: 18px;padding-bottom:0;">${gradeName}自主选课</p>
        <p>选课截至时间</p>
        <p style="font-size: 20px;line-height: 1.2">已结束</p>
    </div>
<div class="binp">
    <p style="color: #666666;">你的选择结果是</p>
    <p style="color: #333333; font-size: 20px; font-weight: bold; line-height: 2; margin-bottom: 25px;">${courseConfigDetail.courseNames}</p>
    <!--<p style="color: #666; font-size: 20px; font-weight: bold; line-height: 2; margin-bottom: 25px;">语文</p>-->
    <p style="color: #16a8f6; font-size: 20px">最终结果</p>
    <c:choose>
    	<c:when test="${not empty resultCourseConfigDetail}">
    		<p style="color: #333333; font-size: 20px; font-weight: bold; line-height: 2; margin-bottom: 25px;">${resultCourseConfigDetail.courseNames}</p>
    	</c:when>
    	<c:otherwise>等待学校确认</c:otherwise>
    </c:choose>
    <!--<p style="color: #333333; font-size: 20px; font-weight: bold; line-height: 2; margin-bottom: 25px;">语文</p>-->
</div>
</body>
</html>
<script>
    $('.list_ul').on('click','li',function(){
        if($(this).hasClass('sect')){
//            $(this).removeClass('sect')
        }else{
            $('.list_ul ul li').eq($(this).index()).addClass('sect').siblings().removeClass('sect');
        }
    })
    $('.list_ul').on('click','.but_right',function(){
        alert('选择成功')
    })
</script>