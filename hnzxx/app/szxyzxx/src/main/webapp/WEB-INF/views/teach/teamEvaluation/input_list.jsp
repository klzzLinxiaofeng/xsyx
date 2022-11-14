<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/views/embedded/common.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
    $(".define-item").hide();//隐藏wenben
    $(".define-item:eq(0)").show();//显示第一个wenben
    $(".plus-minus-nape a").click(function(){
        $(".plus-minus").removeClass("plus-minus");//移除样式
        $(this).addClass("plus-minus");//添加样式
        var i=$(this).index();//获得下标
        $(".define-item").hide();//隐藏wenben
        $(".define-item:eq("+i+")").show();//显示第i个wenben
    });

    $(".click-add").click(function(){
        $(this).prev("ul").append("<li class='add-nape'><input type='checkbox'><input type='text' maxlength='6' class='add-name'placeholder='请输入项目标题'></li>");
  });
    $(".define-item ul li").hover(function(){
    	$(this).css("background-color","#f1f1f1")
    	$(this).children(".delete").show();
    },function(){
    	$(this).css("background-color","#fff")
    	$(this).children(".delete").hide();
    });
    $(".define-item ul li .delete").click(function(){
    	$(this).parent().remove();
    })
});

</script>
</head>
<body>
    <div class="plus-minus-nape">
        <a href="javascript:void(0);" class="plus-minus">减分项</a>
        <a href="javascript:void(0);">加分项</a>
    </div>
    <div class="define-item">
        <div class="plus-minus-frame" id="jian">
            <ul>
            	<c:forEach items="${reduceItems}" var="reduce">
            		<input id="taskId" type="hidden" value="${reduce.taskId }">
					<li>
						<c:choose>
							<c:when test="${reduce.enable eq true }">
								<input id="${reduce.id }" type="checkbox" onchange="change(this)" checked="checked">
							</c:when>
							<c:otherwise>
								<input id="${reduce.id }" type="checkbox" onchange="change(this)">
							</c:otherwise>
						</c:choose>
					<span>${reduce.name }</span>
<%-- 					<c:if test="${reduce.listOrder=='0' }"> --%>
					<a href="javascript:void(0)" class="delete" onclick="del(${reduce.id})"></a>
<%-- 					</c:if> --%>
					</li>
				</c:forEach>
                <li class="add-nape"><input type="checkbox"><input type="text"class="add-name" onchange="addjcx(03)" maxlength="6" placeholder="请输入项目标题"></li>
            </ul>
            <a href="javascript:void(0);" class="click-add"></a>
        </div>
<!--         <div class="confirm-evaluate"><button class="btn btn-warning finish" onclick="refresh()">确定</button></div> -->
    </div>
    <div class="define-item">
        <div class="plus-minus-frame" id="add">
            <ul>
            	<c:forEach items="${addItems}" var="add">
					<li>
						<c:choose>
							<c:when test="${add.enable eq true }">
								<input id="${add.id }" onchange="change(this)" type="checkbox" checked="checked">
							</c:when>
							<c:otherwise>
								<input id="${add.id }" onchange="change(this)" type="checkbox">
							</c:otherwise>
						</c:choose>
					<span>${add.name }</span>
<%-- 					<c:if test="${add.listOrder=='0' }"> --%>
					<a href="javascript:void(0)" class="delete" onclick="del(${add.id})"></a>
<%-- 					</c:if> --%>
					</li>
				</c:forEach>
                <li class="add-nape"><input type="checkbox"><input type="text"class="add-name" maxlength="6" placeholder="请输入项目标题"></li>
            </ul>
            <a href="javascript:void(0);" class="click-add"></a>
        </div>
<!--          <div class="confirm-evaluate"><button class="btn btn-warning finish" onclick="refresh()">确定</button></div> -->
    </div>
</body>
<script type="text/javascript">




</script>
</html>