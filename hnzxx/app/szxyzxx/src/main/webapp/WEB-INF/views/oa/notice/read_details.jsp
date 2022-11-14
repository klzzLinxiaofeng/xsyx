<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
    <title>阅读详情</title>
    <script type="text/javascript">
        $(function () {
            $(".div_change").hide();//隐藏wenben
            $(".div_change:eq(0)").show();//显示第一个wenben
            $("#number a").click(function () {
                $(".check").removeClass("check");//移除样式
                $(this).addClass("check");//添加样式
                var i = $(this).index();//获得下标
                $(".div_change").hide();//隐藏wenben
                $(".div_change:eq(" + i + ")").show();//显示第i个wenben
            });
        });
    </script>
</head>
<body style="background-color:#fff !important">
<div class="row-fluid" style="padding:25px;">

    <div class="browse">
        <div id="number">
            <a href="javascript:void(0);" class="check">未浏览（<span>${noreadnum}</span>人）</a>
            <a href="javascript:void(0);">已浏览（<span>${readnum }</span>人）</a>
        </div>
        <div class="div_browse">
            <div class="div_change">
                <ul>
                    <c:forEach items="${noreaditems}" var="item">
                        <li><img src="<avatar:avatar userId='${item.key}' ></avatar:avatar>">
                            <p>${item.value} </p></li>
                    </c:forEach>

                </ul>
            </div>
            <div class="div_change">
                <ul>
                    <c:forEach items="${readitems}" var="item">
                        <li><p><img src="<avatar:avatar userId='${item.key}' ></avatar:avatar>">
                            <p>${item.value} </p></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>