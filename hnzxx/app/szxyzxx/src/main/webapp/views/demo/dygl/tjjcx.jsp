<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>添加检查项</title>
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
        $(this).prev("ul").append("<li class='add-nape'><input type='checkbox'><input type='text'class='add-name'placeholder='自定义添加'></li>");
  });
});
</script>
</head>
<body style="background-color:#fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
					<form class="form-horizontal" id="user_form">
						<div class="check-list">
    <div class="choice-prom"><span>学年：</span><input type="text" value="2015-2016学年上学期"></div>
    <div class="plus-minus-nape">
        <a href="javascript:void(0);" class="plus-minus">减分项</a>
        <a href="javascript:void(0);">加分项</a>
    </div>
    <div class="define-item">
        <div class="plus-minus-frame">
            <ul>
                <li><input type="checkbox"><span>心智卡加分</span></li>
                <li><input type="checkbox"><span>心智卡加分</span></li>
                <li><input type="checkbox"><span>心智卡加分</span></li>
                <li class="add-nape"><input type="checkbox"><input type="text"class="add-name"placeholder="自定义添加"></li>
            </ul>
            <a href="javascript:void(0);" class="click-add"></a>
        </div>
        <div class="confirm-evaluate"><button class="btn btn-warning finish">确定</button></div>
    </div>
    <div class="define-item">
        <div class="plus-minus-frame">
            <ul>
                <li><input type="checkbox"><span>心智卡加分</span></li>
                <li><input type="checkbox"><span>心智卡加分</span></li>
                <li><input type="checkbox"><span>心智卡加分</span></li>
                <li class="add-nape"><input type="checkbox"><input type="text"class="add-name"placeholder="自定义添加"></li>
            </ul>
            <a href="javascript:void(0);" class="click-add"></a>
        </div>
    </div>
</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">确定</button>
						</div>
					</form>
				</div>
			</div>
	</div>
</body>
</html>