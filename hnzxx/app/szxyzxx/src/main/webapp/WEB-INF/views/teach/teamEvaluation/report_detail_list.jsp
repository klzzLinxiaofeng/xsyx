<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>评价明细报表</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style>
	.up,.down {
    color: #7B7B7B;
    font-size: 12px;
    position: relative;
    top: 0px;
}
</style>
</head>
<body>
                        
		                     <p>${title }</p>
		<div class="mxbb">
		<table border="0" cellpadding="0" cellspacing="1" class="table_1">
			<thead>
		        <tr>
		            <th >
		                <div class="out">
		                    <b class="xiangmu">项目</b> <em class="banji">班级</em>
		                </div>
		            </th>
		        </tr>
		    </thead>
		</table>
		<table border="0" cellpadding="0" cellspacing="1" class="table_2">
			<thead>
		        <tr>
		            <c:forEach items="${itemList}" var="item" varStatus="i">
						<th onclick="$.sortTable.sort('main_table','${i.index }')">
						<div class="thtd_div">
							${item.name }
							<span id="span_${i.index }" class="up" ></span>
						</div></th>
					</c:forEach> 
		        </tr>
		    </thead>
		</table>
		<table class="table_3" border="0" cellpadding="0" cellspacing="1">
			 <c:forEach items="${teamList}" var="team" varStatus="i">
				<tr id="${team.id}">
					<td class="gray">${team.name }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="div_4">
		<table class="table_4" border="0" cellpadding="0" cellspacing="1" id="main_table">
			 <c:forEach items="${teamList}" var="team" varStatus="i">
				<tr data-id="${team.id}">
				 <c:forEach items="${itemList}" var="item" varStatus="j">
						<c:choose>
							<c:when test="${scoreList[i.index][j.index] < 0.0}">
								<td style="color: #ec3e3e;font-weight:bold;"><div class="thtd_div">${scoreList[i.index][j.index]}分</div></td>
							</c:when>
							<c:when test="${scoreList[i.index][j.index] > 0.0}">
								<td style="color: #2399dc;font-weight:bold;"><div class="thtd_div">${scoreList[i.index][j.index]}分</div></td>
							</c:when>
							<c:otherwise>
								<td><div class="thtd_div"></div></td>
							</c:otherwise>
						</c:choose>
					</c:forEach> 
				</tr>
			</c:forEach> 
		</table>
		</div>
<!-- 			<table border="0" cellpadding="0" cellspacing="1"> -->
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th style="width: 130px;"> -->
<!-- 							<div class="out"> -->
<!-- 			                    <b class="xiangmu">项目</b> <em class="banji">班级</em> -->
<!-- 			                </div> -->
<!-- 			            </th> -->
<%-- 						<c:forEach items="${itemList}" var="item"> --%>
<%-- 							<th>${item.name }</th> --%>
<%-- 						</c:forEach> --%>
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody> -->
<%-- 					<c:forEach items="${teamList}" var="team" varStatus="i"> --%>
<!-- 						<tr> -->
<%-- 							<td class="gray">${team.name }</td> --%>
<%-- 							<c:forEach items="${itemList}" var="item" varStatus="j"> --%>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${scoreList[i.index][j.index] < 0.0}"> --%>
<%-- 										<td style="color: #ec3e3e;font-weight:bold;">${scoreList[i.index][j.index]}分</td> --%>
<%-- 									</c:when> --%>
<%-- 									<c:when test="${scoreList[i.index][j.index] > 0.0}"> --%>
<%-- 										<td style="color: #2399dc;font-weight:bold;">${scoreList[i.index][j.index]}分</td> --%>
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										<td></td> -->
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
<%-- 							</c:forEach> --%>
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
					
<!-- 				</tbody> -->
<!-- 			</table> -->
		</div>
		                
           <div class="clear"></div>
                        
					
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/numberSort.js"></script>
<script type="text/javascript">
$(function(){
	var table_width//项目个数占的位置
    var table_width=99*$(".table_2 tr th").length;  
	var div_width;//div_4的宽度
   	div_width=$("body").width()-254;
	if(table_width>div_width){
    $(".mxbb .table_2,.mxbb .table_4").css("width",table_width);
	}else{
		$(".mxbb .table_2,.mxbb .table_4").css("width",div_width);
	}
	var div_height;//div_4的高度
	div_height=$(".table_3").height();
	div_height_1=div_height+45
	if(div_height<407){
		$(".mxbb").css("height",div_height_1);
		$(".div_4").css("height",div_height);
	}
   $(".div_4").css("width",div_width);
   $(window).resize(function(){
    	div_width=$(".mxbb").width()-130;
    	if(table_width>div_width){
    	    $(".mxbb .table_2,.mxbb .table_4").css("width",table_width);
    		}else{
    			$(".mxbb .table_2,.mxbb .table_4").css("width",div_width);
    		}
    $(".div_4").css("width",div_width);
    });
//    滚动事件
	$(".div_4").scroll(function(){
	    var left=130-$(this).scrollLeft();
	    var top=46-$(this).scrollTop();
	    $(".table_2").css("left",left)
	    $(".table_3").css("top",top)
	});
// 	滚动条美化
    $(".div_4").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
})
</script>

</html>