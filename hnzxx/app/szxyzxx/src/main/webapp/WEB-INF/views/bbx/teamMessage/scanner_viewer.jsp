<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title>备忘录</title>
<script type="text/javascript">
$(function(){
    $(".div_change").hide();//隐藏wenben
    $(".div_change:eq(0)").show();//显示第一个wenben
    $("#number a").click(function(){
        $(".check").removeClass("check");//移除样式
        $(this).addClass("check");//添加样式
        var i=$(this).index();//获得下标
        $(".div_change").hide();//隐藏wenben
        $(".div_change:eq("+i+")").show();//显示第i个wenben
    });
});
</script>
</head>
<body style="background-color:#fff !important">
 	<div style="padding:25px;"> 
		
	<div class="browse">
    <div id="number">
        <a href="javascript:void(0);" class="check">未浏览（<span>${notReadParent.size() }</span>人）</a>
        <a href="javascript:void(0);">已浏览（<span>${readParent.size() }</span>人）</a>
    </div>
    <div class="div_browse">
        <div class="div_change">
            <ul>
              <c:if test="${notReadParent.size()>0 }">
			     <c:forEach items="${notReadParent }" var="nrp">
		    	     <li><img src="<avatar:avatar userId='${nrp.studentParentUserId}' ></avatar:avatar>"><p title="${nrp.studentParent }">${nrp.studentParent }</p></li>
				 </c:forEach>
			  </c:if>
               <%--  <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李束带结发开的萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
				<li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
				<li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li> --%>
            </ul>
        </div>
        <div class="div_change">
            <ul>
              <c:if test="${readParent.size()>0 }">
			     <c:forEach items="${readParent }" var="rp">
					 <li><img src="<avatar:avatar userId='${rp.studentParentUserId}' ></avatar:avatar>"><p title="${rp.studentParent }">${rp.studentParent }</p></li>
				 </c:forEach>
			 </c:if>
               <%-- <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li>
               <li><img src="${ctp}/res/images/noPhoto.jpg"><p>李萌1</p></li> --%>
            </ul>
        </div>
    </div>
    </div>
	</div> 
</body>
</html>