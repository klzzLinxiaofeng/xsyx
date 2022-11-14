<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <title>搜索结果</title>
    <style>
        body,html,.kwgl{height:100%;}
        .search_result{min-height:80%;}
    </style>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>搜到${count}条记录</p>
        <a href="javascript:void(0)" class="btn btn-lightGray" onclick="toIndex();">返回</a>
    </div>
    <div class="search_result" id="s1">
        <div class="search_div">
            <p class="title">在校生（<span>${fn:length(in)}</span>）</p>
            <div>
                <ul>
                    <c:forEach items="${in}" var="item">
                        <li>
                            <a href="javascript:void(0)" data-id="${item.userId}"><img src="<avatar:avatar userId='${item.userId }'></avatar:avatar>"/></a>
                            <p class="p1">${item.teamName}</p>
                            <p class="p2">${item.name}</p>
                        </li>
                    </c:forEach>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
        <div class="search_div">
            <p class="title">毕业生（<span>${fn:length(out)}</span>）</p>
            <div>
                <ul>
                    <c:forEach items="${out}" var="item">
                        <li>
                            <a href="javascript:void(0)" data-id="${item.userId}"><img src="<avatar:avatar userId='${item.userId }'></avatar:avatar>"/></a>
                            <p class="p1">${item.teamName}</p>
                            <p class="p2">${item.name}</p>
                        </li>
                    </c:forEach>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="search_result" id="s2">
        <div class="search_div">
            <p class="title">${title}</p>
            <div>
                <ul>
                    <c:forEach items="${list}" var="item">
                        <li>
                            <a href="javascript:void(0)" data-id="${item.userId}"><img src="<avatar:avatar userId='${item.userId }'></avatar:avatar>"/></a>
                            <p class="p2">${item.name}</p>
                        </li>
                    </c:forEach>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        var type = "${type}";
        if (type == "team") {
            $("#s2").show();
            $("#s1").hide();
        } else {
            $("#s1").show();
            $("#s2").hide();
        }
    });
    $(".search_div a").click(function(){
       var id = $(this).data("id");
       window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/student/exam?userId=" + id;
    });

    function toIndex() {
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/index?type=student";
    }

</script>
</html>