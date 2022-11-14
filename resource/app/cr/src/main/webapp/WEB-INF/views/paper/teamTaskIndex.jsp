<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>试卷</title>
    <%@ include file="/views/embedded/common.jsp" %>
</head>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<style>
    ul, ol {
        padding: 0;
        margin: 0 0 0 0;
    }
</style>
<body>
<jsp:include page="./paper_head.jsp"/>
<c:choose>
    <c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].userId,'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
        <c:set var="tList" scope="request" value="${allList}"/>
    </c:when>
    <c:otherwise>
        <c:set var="tList" scope="request" value="${classList}"/>
    </c:otherwise>
</c:choose>
<div class="content_main">
    <div class="ku_select">
        <div class="xdkm_div">
            <div class="xd_km">
                <div class="xueduan" style="display: none;">
                    <label>学年：</label>
                    <div class="xd">
                        <c:forEach items="${tList}" var="sy" varStatus="i">
                            <div class="xn" style="display: block; float:left">
                                <a data-id="${sy.id }" data-i="${i.index }">${sy.name }</a>
                            </div>
                        </c:forEach>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="xueduan">
                    <label>年级</label>
                    <div class="xd">
                        <c:forEach items="${tList}" var="sy1" varStatus="i">
                            <div class="nj" style="display:none" data-i="${i.index}">
                                <c:forEach items="${sy1.childrens}" var="grade" varStatus="j">
                                    <div style="float:left">
                                        <a data-id="${grade.id}" data-j="${j.index}"
                                           data-i="${i.index}">${grade.name}</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="xueduan">
                    <div class="clear"></div>
                    <label>班级</label>
                    <div class="xd">
                        <c:forEach items="${tList}" var="sy2">
                            <div class="nj-team" style="display:none">
                                <c:forEach items="${sy2.childrens }" var="grade">
                                    <div class="team" style="display:none;float:left">
                                        <c:forEach items="${grade.childrens }" var="team">
                                            <div style="float:left">
                                                <a data-id="${team.id}" class="teamObj">${team.name}</a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="xueduan">
                    <div class="clear"></div>
                    <label>科目</label>
                    <div class="xd" id="subject">
                        <div style="float:left"><a data-id="" class="btn-blue">全部</a></div>
                        <c:forEach items="${sList}" var="s">
                            <div style="float: left;" class=""><a data-id="${s.code}">${s.name}</a></div>
                        </c:forEach>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="search_div">
                <label>搜索：</label>
                <div class="ss">
                    <input type="text" placeholder="试卷标题" id="title"/>
                    <a href="javascript:void(0)" class="btn-blue" onclick="go()">搜索</a>
                </div>
            </div>
        </div>
    </div>
    <div class="neirong_zs">
        <div class="nr_right" style="margin-left:0;">
            <div class="dxa_list" style="margin-bottom:20px;">
                <div id="emExamPublish_list_content">
                    <jsp:include page="./teamTaskList.jsp"/>
                </div>
            </div>
            <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                <jsp:param name="id" value="emExamPublish_list_content"/>
                <jsp:param name="url" value="/paperTask/team/task?index=list"/>
                <jsp:param name="pageSize" value="${page.pageSize}"/>
            </jsp:include>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script type="text/javascript">
    var frist = 0;

    function go() {
        var val = {};
        var name = $("#title").val();
        if (name != null && name != "") {
            val.title = name;
        }
        val.teamId = $(".team div .btn-blue").attr("data-id");
        val.subjectCode = $("#subject div .btn-blue").attr("data-id");
        var id = "emExamPublish_list_content";
        var url = "/paperTask/team/task?index=list";
        myPagination(id, val, url);
//         alert("ss");
    }

    $('.xn a').on('click', function () {
        $('.nj').css("display", "none");
        var i = $(this).attr("data-i");
        $(".nj").eq(i).css("display", "block");
        $(".nj").eq(i).find('div a').eq(0).click();
    })
    $('.nj div a').on('click', function () {
        $('.team').css("display", "none");
        $(".nj-team").css("display", "none");
        var i = $(this).attr("data-i");
        var j = $(this).attr("data-j");
        $(".nj-team").eq(i).css("display", "block");
        $(".nj-team").eq(i).find(".team").eq(j).css("display", "block");
        $('.teamObj').removeClass("btn-blue");
        $(".nj-team").eq(i).find(".team").eq(j).find('a').eq(0).click();
    })
    $('.team a').on('click', function () {
        var val = {};
        val.teamId = $(this).attr("data-id");
        val.subjectCode = $("#subject div .btn-blue").attr("data-id");
        var id = "emExamPublish_list_content";
        var url = "/paperTask/team/task?index=list";
        myPagination(id, val, url);
    })


    $(function () {
        $(".xdkm_div .xd_km .xueduan .xd a").click(function () {
            $(this).parent().siblings().children("a").removeClass("btn-blue");
            $(this).addClass("btn-blue");
        })
        $('#subject div a').on('click', function () {
            go();
        })
        $('.xn').eq(-1).find('a').click();
    })

    function del(id) {
        var r = confirm("是否要删除任务");
        if (r) {
            var teamId = $(".team div .btn-blue").attr("data-id");
            var val = {};
            val.taskId = id;
            val.teamId = teamId;
            $.ajax({
                url: "${pageContext.request.contextPath}/paperTask/del/task",
                type: "POST",
                data: val,
                async: false,
                success: function (data) {
//                 alert(data);
                    if (data === 'promise') {
                        alert("不是你发布的试卷，不能删除");
                    } else if (data !== 'fail') {
                        go();
                    }
                }
            });
        }
    }

    function look(id) {
        var URL = "${pageContext.request.contextPath}/paperTask/paper/viewer?paperId=" + id;
// 	   window.location.href="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
        window.open(URL);
    }

    function index(examId) {
//        var URL = "${pageContext.request.contextPath}/statistics/tj/index?examId=" + id;
// 	   window.location.href="${pageContext.request.contextPath}/statistics/tj/index?examId="+id;
        window.open("${pageContext.request.contextPath}/statistics/pa/tj/index?examId=" + examId);
    }

    function exports(id) {
        var teamId = $(".team div .btn-blue").attr("data-id");
        var val = {};
        val.taskId = id;
        val.teamId = teamId;
        val.type = 2;
        $.ajax({
            url: "${pageContext.request.contextPath}/statistics/checkExport",
            type: "POST",
            data: val,
            async: false,
            success: function (data) {
                if (data === 'noExam') {
                    $.alert("导学案无试卷单元，无法导出");
                } else if (data === 'fail') {
                    $.alert("请统计后，再导出");
                } else {
                    window.location.href = "${pageContext.request.contextPath}/statistics/export?taskId=" + id + "&teamId=" + teamId + "&type=2";
                }
            }
        });
    }
</script>
</body>
</html>