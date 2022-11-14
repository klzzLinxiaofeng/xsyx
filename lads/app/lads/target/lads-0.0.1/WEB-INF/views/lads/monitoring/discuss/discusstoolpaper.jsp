<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm">
        <b>标题：</b>${requestScope.title}
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <b>分数：</b>
        ${requestScope.discussScore}
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%--<b>时间：</b>
        ${requestScope.discussStartTime} - ${requestScope.discussStopTime}--%>
    </li>
    <li class="rr">

    </li>
</ul>
<div class="statistics"><strong>活动统计</strong>
    <div>总人数：<b>${fn:length(requestScope.voList)}人</b>
        评论数：<b>${requestScope.allComments}条</b>发表人数：<b>${requestScope.commented}人</b>
        未发表：<b>${requestScope.notComment}人</b>
    </div>
    <span></span>
</div>
<div class="check">
    <ul>
        <li onclick="discussTabSwitch('content', '${requestScope.id}')" class="on"><a href="javascript:void(0)">按学习内容查看</a></li>
        <li onclick="discussTabSwitch('student', '${requestScope.id}')"><a href="javascript:void(0)">按学员查看</a></li>
<!--        <li onclick="discussTabSwitch('file','${requestScope.id}')"><a href="javascript:void(0)">按文件查看</a></li>-->
    </ul>
</div>
<div id="discussContentDiv_${requestScope.id}" style="padding-left: 25px; padding-right:25px;">

</div>


<script type="text/javascript">
    function discussSaveScore(id) {
        var score = $("#discussEditScore_" + id).val();
        if (!checkDiscussScore(score)) {
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/lads/tool/discuss/editUserScore",
            type: "POST",
            data: {"score": score, "statusId": id},
            async: false
        });
        $("#discussScoreSpan_" + id).html(score);
    }
    function checkDiscussScore(score) {
        var flag = true;
        if (!(/^-?\d+\.?\d*$/.test(score))) {
            $.alert("请输入有效实数");
            flag = false;
            return flag;
        }
        return flag;
    }
    function discussScore(id) {
        if ($("#discussEditScore_" + id).html() != null) {
            discussSaveScore(id);
        } else {
            var pre_score = $("#discussScoreSpan_" + id).html().replace(/[^0-9.]/ig, "");
            $("#discussScoreSpan_" + id).empty();
            $("#discussScoreSpan_" + id).append("<input style=\"width:100px\" onkeyup=\"checkScore('"+id+"', this)\" name='score' id='discussEditScore_" + id + "' value='" + pre_score + "' type='text' class='score_input'/>");
        }
    }
    function loadDiscussReplyList(id, userId) {
        $("#discussContentDiv_" + id).html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
        var url;
        var timestmp = (new Date()).valueOf();//将时间戳追加到url上面
        if (userId != null && userId != "") {
            url = "${pageContext.request.contextPath}/lads/tool/discuss/getMonitoringReplyList?toolId=" + id + "&userId=" + userId;
        } else {
            url = "${pageContext.request.contextPath}/lads/tool/discuss/getMonitoringReplyList?toolId=" + id;
        }
        $("#discussContentDiv_" + id).load(url + "&t=" + timestmp, function () {
            if ($.trim($("#discussReplyDiv_" + id).html()) != "") {
                 //使用setIimeout方法延迟1秒加载
                 setTimeout("discussReplyImgWidthCon('" + id + "')", 200);
            }
        });
    }
    function loadDiscussStudentVoList(id) {
        $("#discussContentDiv_" + id).html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
        var timestmp = (new Date()).valueOf();//将时间戳追加到url上面
        var url = "${pageContext.request.contextPath}/lads/tool/discuss/getStudentVoList?ldId=" + $("#ldId").val() + "&toolId=" + id;
        $("#discussContentDiv_" + id).load(url + "&t=" + timestmp);
    }
    function loadDiscussStudentVoList(id, type) {
        $("#discussContentDiv_" + id).html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
        var timestmp = (new Date()).valueOf();//将时间戳追加到url上面
        var url = "${pageContext.request.contextPath}/lads/tool/discuss/getStudentVoList?ldId=" + $("#ldId").val() + "&toolId=" + id;
        if (type == "file") {
            url = url + "&type=file"
        }
        $("#discussContentDiv_" + id).load(url + "&t=" + timestmp);
    }
    function discussTabSwitch(type, id) {
        if (type == "content") {
            loadDiscussReplyList(id);
        } else if (type == "student") {
            loadDiscussStudentVoList(id);
        } else if (type == "file") {
            loadDiscussStudentVoList(id, "file");
        }
    }
    function discussNameSearch(id) {
        var value = $("#discussRealName_" + id).val();
        if (value == null || value == "") {
            $.alert("请输入搜索姓名");
            return;
        }
        var scrollFlag = true;
        $("#discussTable_" + id + " td[class='discussRealNameTitle']").each(function () {
            var name = $.trim($(this).html());
            $(this).parent().removeAttr("style");
            if (name.indexOf(value) != -1) {
                $(this).parent().css("border", "2px solid #FF0000");
                if (scrollFlag) {
                    $('html, body').scrollTop($(this).parent().offset().top);
                }
                scrollFlag = false;
            }
        });
    }
    $(function () {
        $(".check ul li").click(function () {
            $(this).siblings().removeClass("on");
            $(this).addClass("on");
        });
        discussTabSwitch('content', '${requestScope.id}')
    });
</script>



