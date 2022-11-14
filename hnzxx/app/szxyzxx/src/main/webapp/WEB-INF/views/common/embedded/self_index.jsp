<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/res/css/extra/index.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
    <title>三级页面</title>
</head>
<body>
<div class="second_main">
    <input type="hidden" id="code" value="${dm }"/>
    <ul>
        <!-- <li class="KE_REN_JIAO_SHI_GUAN_LI">
            <a href="javascript:void(0)">
                <span class="intro">
                    <span class="span1">人事管理</span>
                    <span class="span2">简单方便地布置微课任务，学生在线学习，轻松有效。</span>
                    <b></b>
                </span>
            </a>
        </li> -->
    </ul>
    <div class="clear"></div>
</div>
</body>
<script>
    $(function(){
        var dm = "${dm}";
        var url = "${url}";
        var level = "${level}";
        var type = "${type}";
        var $li;

        if (level == "second") {
            $li = $("#navigation li[data-menu-id='" + dm + "']", window.top.document);
        }
        if (level  == "third") {
            $li = $("#navigation li ul li[data-menu-id='" + dm + "']", window.top.document);
        }
        var name = $li.children().children("span").text();
        var desc = $li.attr("data-desc");

        if (url == "") {
            url = "javascript:void(0)";
        }
        if (type == "out") {
            $(".second_main ul").append("<li id=0 class=" + dm +"><a target='_blank' href='" + url + "'><span class='intro'><span class='span1'>" + name + "</span><span class='span2'>" + desc + "</span></span></a></li>");
        }
        if (type == "in") {
            $(".second_main ul").append("<li id=0 class=" + dm +"><a href='" + url + "'><span class='intro'><span class='span1'>" + name + "</span><span class='span2'>" + desc + "</span></span></a></li>");
        }
    });
</script>
</html>
