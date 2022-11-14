<%-- 
    Document   : learningpaper
    Created on : 2012-8-21, 15:42:01
    Author     : Administrator
--%>

<%@page import="platform.education.lads.contants.FinishedStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<html class="reading-view">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>LADS学习系统</title>
        <link href="${pageContext.request.contextPath}/res/css/common/lads/learning/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
        <!-- 窗体控件 -->
        <%@include file="/views/embedded/plugin/layer.jsp" %>
        <!--遮罩层,弹出框,提示框-->
        <%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor-lads/kindeditor-min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/jwplayer6.8/jwplayer.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="ark-reader">
            <div class="aside">
                <ul class="panel">
                    <li id="fn-list"><a href="javascript:void(0)" class="icon-list" title="活动列表">活动列表</a></li>
                    <li id="fn-helper"><a href="javascript:void(0)" title="指南" class="icon-helper">指南</a></li>
                </ul>
                <div class="pop">
                    <h2><div name="ldTitle"></div><a id="close" href="javascript:void(0)" title="收起">收起</a></h2>
                    <div id="activityPanel" class="content">
                    </div>
                </div>
            </div>
            <div class="article">
                <div class="mask-spine"></div>
                <div class="inner" style="right: auto;"><!-- 44 -->
                    <!--                    <div class="page">
                                        </div>-->
                    <div id="contentDiv" class="page">
                        <!-- 《《《《《《《《《《《 文本区域 》》》》》》》》》》》》 -->
                        <h6>
                            <label id="currentGroupTitle" style="width:350px;overflow: hidden;float:left;" ></label>
                            <span style="width:400px;overflow: hidden;"><div name="ldTitle" style="text-align:right"></div></span>
                        </h6>
                    </div>
                </div>
                <div class="paging">
                    <ul>
                        <li id="page-prev" onclick="prevActivity($('#currentId').val());" class="page-prev" data-title="前翻"><b class="arrow-prev"></b></li>
                        <li id="page-next" onclick="nextActivity($('#currentId').val());" class="page-next" data-title="后翻"><b class="arrow-next"></b></li>
                        <!--                        <li id="curr-page" class="curr-page on" data-title="页数"><span class="page-num">2</span></li>-->
                        <li class="total-page">共<span id="activityCount"></span>个</li>
                    </ul>
                </div>
            </div>
        </div> 
        <input id="currentId" type="hidden" />
        <input value="${requestScope.sysType}" name="sysType" id="sysType" type="hidden"/>
        <script type="text/javascript">
            //该变量用于表现预览模式
            var previewMode = false;
            //该变量用于记录当前某些工具的学习记录定时器
            var statusInterval = null;
            function Activity(actJson) {
                var at = ""
                var tn = "";
                var ti = "";
                var init = function () {
                    at = actJson.activityType;
                }
                this.createGroup = function (actJson) {
                    var random = actJson.id;
                    ti = actJson.groupTitle;
                    var html = "<div id=\"group_" + random + "\" class=\"directory\">"
                            + "<i class=\"icon minus\" onclick=\"toggleGroupContent('" + random + "')\" >&nbsp;</i>"
                            + "<textarea style=\"display:none\" id=\"groupJson_" + random + "\">" + JSON.stringify(actJson) + "</textarea>"
                            + "<b id=\"groupTitle_" + random + "\" class=\"text\">" + ti + "</b></div>";
                    $("#activityPanel").append(html);
                    var subActivitys = actJson.subActivitys;
                    if (subActivitys != null) {
                        for (var i = 0; i < subActivitys.length; i++) {
                            this.createTool(subActivitys[i], "group_" + random);
                        }
                    }
                    return html;
                }
                this.createTool = function (actJson, groupId) {
                    var random = actJson.id;
                    var taught = actJson.taught;
                    var finished = actJson.finished;
                    tn = actJson.toolName;
                    var className;
                    var score = "";
                    var startTime = "";
                    var stopTime = "";
                    if (tn == "QuizTool") {
                        className = "quiz";
                        ti = actJson.quizTitle;
                        score = actJson.quizScore;
                    } else if (tn == "FaceTeachingTool") {
                        className = "teaching";
                        ti = actJson.faceTeachingTitle;
                        score = actJson.faceTeachingScore;
                        startTime = actJson.faceTeachingStartTime;
                        stopTime = actJson.faceTeachingStopTime;
                    } else if (tn == "EditorTool") {
                        className = "study";
                        ti = actJson.editorTitle;
                        score = actJson.editorScore;
                    } else if (tn == "DiscussTool") {
                        className = "discuss";
                        ti = actJson.discussTitle;
                        score = actJson.discussScore;
                        startTime = actJson.discussStartTime;
                        stopTime = actJson.discussStopTime;
                    } else if (tn == "SurveyTool") {
                        className = "survey";
                        ti = actJson.surveyTitle;
                        score = actJson.surveyScore;
                    } else if (tn == "PowerPointTool") {
                        className = "powerPoint";
                        ti = actJson.powerPointTitle;
                        score = actJson.powerPointScore;
                    } else if (tn == "MediaTool") {
                        className = "c";
                        ti = actJson.mediaTitle;
                        score = actJson.mediaScore;
                    }
                    if (ti != null) {
                        if (ti.length >= 20) {
                            ti = ti.substring(0, 18)
                        }
                    } else {
                        ti = "";
                    }
                    var taughtHtml = "";
                    if (taught != null && taught != "" && taught == "必修") {
                        taughtHtml = "<img src=\"${pageContext.request.contextPath}/image/common/lads/compulsory.png\">"
                    }
                    var finishedHtml = "";
                    if (finished != null && finished ==<%=FinishedStatus.FINISHED%>) {
                        finishedHtml = "<span class=\"status finish\">&nbsp;</span>";
                    }
                    var html = "<div class=\"directory bg\" id=\"button_" + random + "\" >"
                            + "<textarea style=\"display:none\" id=\"toolJson_" + random + "\">" + JSON.stringify(actJson) + "</textarea>"
                            + "<b class=\"text child\"><a onclick=\"setHover('" + random + "')\" href=\"javascript:void(0)\">" + ti + "</a>"
                            + taughtHtml
                            + finishedHtml
                            + "</b>";
                    +"</div>";
                    if (groupId != null && groupId != "") {
                        $("#" + groupId).append(html);
                        $("#" + "button_" + random).prepend("<i class=\"icon more\"></i>");
                    } else {
                        $("#activityPanel").append(html);
                    }
                    return html;
                }
                this.createButton = function () {
                    if (at == "tool") {
                        this.createTool(actJson);
                    } else if (at == "group") {
                        this.createGroup(actJson);
                    }
                }
                Activity.createGroupContent = function (id) {
                    var actJson = JSON.parse($("#groupJson_" + id).text());
                    var random = actJson.id;
                    var contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/groupPaper", {"title": ti, "id": random});
                    $("#contentDiv").append("<div id='rightContent_" + random + "'></div>");
                    $("#rightContent_" + random).append(contentHtml);
                }
                Activity.createContent = function (id) {
                    var actJson = JSON.parse($("#toolJson_" + id).text());
                    var random = actJson.id;
                    $("#contentDiv").append("<div id='rightContent_" + random + "'></div>");
                    $("#rightContent_" + random).append("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>");
                    var contentHtml;
                    var tn = actJson.toolName;
                    var ti;
                    if (tn == "QuizTool") {
                        ti = actJson.quizTitle;
                    } else if (tn == "FaceTeachingTool") {
                        ti = actJson.faceTeachingTitle;
                    } else if (tn == "EditorTool") {
                        ti = actJson.editorTitle;
                    } else if (tn == "DiscussTool") {
                        ti = actJson.discussTitle;
                    } else if (tn == "SurveyTool") {
                        ti = actJson.surveyTitle;
                    } else if (tn == "PowerPointTool") {
                        ti = actJson.powerPointTitle;
                    } else if (tn == "MediaTool") {
                        ti = actJson.mediaTitle;
                    }
                    if (tn == "QuizTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/quizToolPaper",
                                {"title": ti, "id": random, "quizXmlPath": actJson.quizXmlPath, "quizResultUrl": actJson.quizResultUrl});
                    } else if (tn == "FaceTeachingTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/faceTeachingToolPaper",
                                {"title": ti, "id": random, "faceTeachingStartTime": actJson.faceTeachingStartTime, "faceTeachingStopTime": actJson.faceTeachingStopTime,
                                    "faceTeachingAddress": actJson.faceTeachingAddress, "faceTeachingDescription": actJson.faceTeachingDescription});
                    } else if (tn == "EditorTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/editorToolPaper",
                                {"title": ti, "id": random, "editorScore": actJson.editorScore, "editorContent": actJson.editorContent, "editorUploadList": JSON.stringify(actJson.editorUploadList)});
                    } else if (tn == "DiscussTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/discussToolPaper",
                                {"title": ti, "id": random, "discussContent": actJson.discussContent, "discussStartTime": actJson.discussStartTime, "discussStopTime": actJson.discussStopTime, "discussAllowUpload": actJson.discussAllowUpload});
                    } else if (tn == "SurveyTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/surveyToolPaper",
                                {"title": ti, "id": random, "surveyDescription": actJson.surveyDescription});
                    } else if (tn == "PowerPointTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/powerPointToolPaper",
                                {"title": ti, "id": random, "powerPointScore": actJson.powerPointScore, "powerPointFileId": actJson.powerPointFileId});
                    } else if (tn == "MediaTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/learning/mediaToolPaper",
                                {"title": ti, "id": random, "mediaScore": actJson.mediaScore, "mediaEntityId": actJson.mediaEntityId, "mediaUploadList": JSON.stringify(actJson.mediaUploadList)});
                    }
                };
                init();
            }

            function setHover(id) {
                var button = "button_" + id;
                $("#activityPanel div[id^='button_']").each(function () {
                    $(this).find("b").attr("class", "text");
                });
                $("#activityPanel div[id^='group_']").each(function () {
                    $(this).find("b[class^='text']").attr("class", "text");
                });
                $("#" + button + " b").attr("class", "text on");
                hideContent();
                if ($("#" + button).parent().attr("id").indexOf("group") != -1) {
                    $("#currentGroupTitle").text($("#" + button).parent().children("b[id^='groupTitle']").text());
                } else {
                    $("#currentGroupTitle").text("");
                }
                $("#currentId").val(id);
                $("#close").click();
                showContent(id);
                //判断是否为第一个活动隐藏前一个活动的按钮
                confirmPrev(button)
                //判断是否为最后一个活动隐藏下一个活动的按钮
                confirmNext(button)
                //移动滚动条到顶部
                $('html, body').animate({scrollTop: $("body").offset().top});
                //非预览模式下记录最后学习到哪个活动
                if (previewMode != true) {
                    saveLastStudyRecord(id);
                }
                //非预览模式下清除某些工具的学习记录定时器
                if (previewMode != true && statusInterval != null) {
                    clearInterval(statusInterval);
                }
                return false;
            }
            function confirmPrev(button) {
                var flag;
                if ($("#" + button).parent().attr("id").indexOf("group") != -1) {
                    var prev = $("#" + button).prev();
                    if (prev.html() == null) {
                        if ($("#" + button).parent().prev().html() == null) {
                            $("#page-prev").hide();
                            flag = false;
                        } else if ($("#" + button).parent().prev().attr("id").indexOf("group") != -1 && $("#" + button).parent().prev().find("div[id^='button']").size() <= 0) {
                            $("#page-prev").hide();
                            flag = false;
                        } else {
                            $("#page-prev").show();
                            flag = true;
                        }
                    } else {
                        $("#page-prev").show();
                        flag = true;
                    }
                } else if ($("#" + button).parent().attr("id").indexOf("group") == -1) {
                    if ($("#" + button).prevAll().find("div[id^='button']").size() <= 0 && $("#" + button).prevAll("div[id^='button']").size() <= 0) {
                        $("#page-prev").hide();
                        flag = false;
                    } else {
                        $("#page-prev").show();
                        flag = true;
                    }
                }
                return flag;
            }
            function confirmNext(button) {
                var flag;
                if ($("#" + button).parent().attr("id").indexOf("group") != -1) {
                    var next = $("#" + button).next();
                    if (next.html() == null) {
                        if ($("#" + button).parent().next().html() == null) {
                            $("#page-next").hide();
                            flag = false;
                        } else if ($("#" + button).parent().next().attr("id").indexOf("group") != -1) {
                            if ($("#" + button).parent().nextAll().find("div[id^='button']").size() <= 0 && $("#" + button).parent().nextAll("div[id^='button']").size() <= 0) {
                                $("#page-next").hide();
                                flag = false;
                            } else {
                                $("#page-next").show();
                                flag = true;
                            }
                        } else {
                            $("#page-next").show();
                            flag = true;
                        }
                    } else {
                        $("#page-next").show();
                        flag = true;
                    }
                } else if ($("#" + button).parent().attr("id").indexOf("group") == -1) {
                    if ($("#" + button).nextAll().find("div[id^='button']").size() <= 0 && $("#" + button).nextAll("div[id^='button']").size() <= 0) {
                        $("#page-next").hide();
                        flag = false;
                    } else {
                        $("#page-next").show();
                        flag = true;
                    }
                }
                return flag;
            }
            function showContent(id) {
                var content = "rightContent_" + id;
                if ($("#" + content).html() == null) {
                    //没有加载过的
                    Activity.createContent(id);
                } else {
                    //已经加载过的
                    if ($("#" + content).find("#powerPointContent_" + id).html() != null) {
                        //ppt工具的内容无论是否已经加载过都从新加载,解决ie下隐藏再显示后的黑屏问题
                        $("#" + content).remove();
                        Activity.createContent(id)
                    } else {
                        $("#" + content).show();
                    }
                }
            }
            function hideContent() {
                $(".page div[id^=rightContent_]").hide();
            }
            function emptyDirNext(next) {
                if (next.attr("id").indexOf("group") != -1) {
                    var first = next.find("div[id^='button_']:first");
                    if (first.size() > 0) {
                        first.each(function (i) {
                            var nextId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                            setHover(nextId);
                        });
                    } else {
                        //空文件夹
                        emptyDirNext(next.next());
                    }
                } else {
                    if (next.html() != null) {
                        var nextId = next.attr("id").substring(next.attr("id").indexOf("_") + 1);
                        setHover(nextId);
                    }
                }
            }
            function emptyDirPrev(prev) {
                if (prev.attr("id").indexOf("group") != -1) {
                    var last = prev.find("div[id^='button_']:last");
                    if (last.size() > 0) {
                        last.each(function (i) {
                            var prevId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                            setHover(prevId);
                        });
                    } else {
                        //空文件夹
                        emptyDirPrev(prev.prev());
                    }
                } else {
                    if (prev.html() != null) {
                        var nextId = prev.attr("id").substring(prev.attr("id").indexOf("_") + 1);
                        setHover(nextId);
                    }
                }
            }

            function nextActivity(currentId) {
                if ($(".page").is(":animated")) {
                    //判断元素是否正处于动画状态
                    //如果当前没有进行动画，则添加新动画
                    return;
                }
                $(".page").animate({left: '+=900px'}, "normal", "linear", function () {
                    hideContent();
                    $(".page").css("left", "-900px");
                    $(".page").animate({left: '+=900px'}, "normal", "linear", function () {
                        var button = "button_" + currentId;
                        if ($("#" + button).next().html() != null) {
                            var next = $("#" + button).next();
                            var nextId = next.attr("id").substring(next.attr("id").indexOf("_") + 1);
                            if (next.attr("id").indexOf("button") != -1) {
                                setHover(nextId);
                            } else if (next.attr("id").indexOf("group") != -1) {
                                var first = next.find("div[id^='button_']:first");
                                if (first.size() > 0) {
                                    first.each(function (i) {
                                        nextId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                                        setHover(nextId);
                                    });
                                } else {
                                    //空文件夹
                                    emptyDirNext(next.next());
                                }
                            }
                        } else if ($("#" + button).parent().attr("id").indexOf("group") != -1) {
                            var next = $("#" + button).parent().next();
                            var nextId = next.attr("id").substring(next.attr("id").indexOf("_") + 1);
                            if (next.attr("id").indexOf("button") != -1) {
                                setHover(nextId);
                            } else if (next.attr("id").indexOf("group") != -1) {
                                var first = next.find("div[id^='button_']:first");
                                if (first.size() > 0) {
                                    first.each(function (i) {
                                        nextId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                                        setHover(nextId);
                                    });
                                } else {
                                    //空文件夹
                                    emptyDirNext(next.next());
                                }
                            }
                        }
                    });
                });
            }
            function prevActivity(currentId) {
                if ($(".page").is(":animated")) {
                    //判断元素是否正处于动画状态
                    //如果当前没有进行动画，则添加新动画
                    return;
                }
                $(".page").animate({left: '-=900px'}, "normal", "linear", function () {
                    hideContent();
                    $(".page").css("left", "900px");
                    $(".page").animate({left: '-=900px'}, "normal", "linear", function () {
                        var button = "button_" + currentId;
                        if ($("#" + button).prev().html() != null && $("#" + button).parent().attr("id").indexOf("group") == -1) {
                            var prev = $("#" + button).prev();
                            var prevId = prev.attr("id").substring(prev.attr("id").indexOf("_") + 1);
                            if (prev.attr("id").indexOf("button") != -1) {
                                setHover(prevId);
                            } else if (prev.attr("id").indexOf("group") != -1) {
                                var last = prev.find("div[id^='button_']:last");
                                if (last.size() > 0) {
                                    last.each(function (i) {
                                        var prevId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                                        setHover(prevId);
                                    });
                                } else {
                                    //空文件夹
                                    emptyDirPrev(prev.prev());
                                }
                            }
                        } else if ($("#" + button).parent().attr("id").indexOf("group") != -1) {
                            var prev = $("#" + button).prev();
                            if (prev.html() == null || prev.attr("id").indexOf("button") == -1) {
                                prev = $("#" + button).parent().prev();
                            }
                            var prevId = prev.attr("id").substring(prev.attr("id").indexOf("_") + 1);
                            if (prev.attr("id").indexOf("button") != -1) {
                                setHover(prevId);
                            } else if (prev.attr("id").indexOf("group") != -1) {
                                var last = prev.find("div[id^='button_']:last");
                                if (last.size() > 0) {
                                    last.each(function (i) {
                                        var prevId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                                        setHover(prevId);
                                    });
                                } else {
                                    //空文件夹
                                    emptyDirPrev(prev.prev());
                                }
                            }
                        }
                    });
                });
            }
            function toggleGroupContent(id) {
                if ($("#group_" + id + " i[class^='icon']").attr("class") == "icon plus") {
                    $("#group_" + id + " i[class='icon plus']").attr("class", "icon minus");
                    $("#group_" + id + " div").show();
                } else {
                    $("#group_" + id + " i[class='icon minus']").attr("class", "icon plus");
                    $("#group_" + id + " div").hide();
                }
            }
            function updateActivityCount() {
                var size = 0;
                $("#activityPanel div[id^='button_']").children().each(function () {
                    size++;
                    if ($(this).attr("class") == "directory") {
                        size = size + $(this).children("div[id^='button_']").size();
                    }
                });
                $("#activityCount").html($("#activityPanel div[id^='button_']").size());
            }
            function saveLastStudyRecord(toolId) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/learning/saveLadsRecord",
                    type: "POST",
                    data: {"ldId": "${requestScope.ld.uuid}", "toolId": toolId, "userId": "<lads:getEmbedUser />"},
                    async: true,
                    success: function (html) {

                    }
                });
            }
            function ajaxGetHtml(url, data) {
                var contentHtml
                $.ajax({
                    url: url,
                    type: "POST",
                    data: data,
                    async: true,
                    success: function (html) {
                        contentHtml = html;
                        $("#rightContent_" + data.id).html(contentHtml);
                    }
                });
                return contentHtml;
            }
            function getLearningJson(ldId, pm) {
                var data = {};
                data["ldId"] = ldId;
                if (pm == true) {
                    previewMode = true;
                } else {
                    //非预览模式返回的json包括用户学习信息
                    data["status"] = "status";
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/learning/getLearningJson",
                    type: "POST",
                    dataType: "json",
                    data: data,
                    async: false,
                    success: function (json) {
                        loadActivitys(json);
                    }
                });
            }
            function getPreviewJson(jsonId) {
                previewMode = true;
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/learning/getPreviewJson",
                    type: "POST",
                    dataType: "json",
                    data: {"jsonId": jsonId},
                    async: false,
                    success: function (pjson) {
                        loadActivitys(pjson);
                    }
                });
            }
            function loadActivitys(json) {
                //初始化大标题
                if (json.title != null && json.title != "") {
                    $("div[name='ldTitle']").text(json.title);
                }
                var activitys = json.activitys;
                var firstId = "${requestScope.toolId}";
                for (var i = 0; i < activitys.length; i++) {
                    var act = new Activity(activitys[i]);
                    //创建活动
                    act.createButton();
                }
                if (firstId == "" || firstId == "null") {
                    //如果没有特指显示哪个活动就显示第一个活动
                    $("#activityPanel div[id^='button_']").each(function () {
                        firstId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                        return false;
                    });
                }
                setHover(firstId);
                //计算活动总数
                updateActivityCount();
            }

            function changeTimeToString(DateIn) {
                var Month = 0;
                var Day = 0;
                var Hour = 0;
                var Minute = 0;
                var CurrentDate = "";
                //初始化时间
                Month = DateIn.getMonth() + 1;
                Day = DateIn.getDate();
                Hour = DateIn.getHours();
                Minute = DateIn.getMinutes();

                if (Month >= 10)
                {
                    CurrentDate = CurrentDate + Month + "-";
                } else
                {
                    CurrentDate = CurrentDate + "0" + Month + ".";
                }
                if (Day >= 10)
                {
                    CurrentDate = CurrentDate + Day;
                } else
                {
                    CurrentDate = CurrentDate + "0" + Day;
                }

                if (Hour >= 10)
                {
                    CurrentDate = CurrentDate + " " + Hour;
                } else
                {
                    CurrentDate = CurrentDate + " 0" + Hour;
                }
                if (Minute >= 10)
                {
                    CurrentDate = CurrentDate + ":" + Minute;
                } else
                {
                    CurrentDate = CurrentDate + ":0" + Minute;
                }
                return CurrentDate;
            }

            $(document).ready(function (e) {
                var cls = $("#close");
                var list = $("#fn-list");
                var helper = $("#fn-helper");
                var prev = $("#page-prev");
                var next = $("#page-next");
                var curr = $("#curr-page");
                var total = $("#total-page");
                var popMenu = $(".pop");
                var content = $(".content");

                popMenu.css("height", $(window).height() - 12);
                content.css("height", $(window).height() - 12 - 34);

                list.click(function () {
                    if (popMenu.is(":hidden")) {
                        $(this).children("a").css({"position": "fixed", "top": "10px", "left": "8px", "width": "42px", "background-color": "#e8eced", "border-right": "none", "z-index": 902});
                        popMenu.show();
                    } else {
                        $(this).children("a").removeAttr("style");
                        popMenu.hide();
                    }
                });

                cls.click(function () {
                    popMenu.hide();
                    list.children("a").removeAttr("style");
                });

                helper.click(function () {
                    var S = $("#tipsDiv strong");
                    if (S.length > 0) {
                        S.remove();
                        $(this).children("a").removeAttr("style");
                    } else {
                        var EL = new Array([list, "<b></b>课程目录", 6, 52], [helper, "<b></b>页面指南", 6, 52], [prev, "<p></p>前一活动", 60, -76], [next, "<p></p>后一活动", 60, -76], [curr, "<p></p>当前活动", 6, -76], [total, "<p></p>总共活动", 3, -76]);
                        $("body").append("<div id=\"tipsDiv\"></div>");
                        for (var j = 0; j < EL.length; j++) {
                            var iE = document.createElement("strong");
                            iE.className = "tips";
                            iE.setAttribute("style", "top:" + (parseInt(EL[j][0].offset().top) - parseInt($(document).scrollTop()) + parseInt(EL[j][2])) + "px;left:" + (parseInt(EL[j][0].offset().left) - parseInt($(document).scrollLeft()) + parseInt(EL[j][3])) + "px");
                            iE.innerHTML = EL[j][1];
                            $("#tipsDiv").append($(iE))
                        }
                        $(this).children("a").attr("style", "background-color:#e8eced");
                    }
                });

                //浏览器改变窗口宽度时的设置
                window.onresize = function () {
                    var popMenu = $(".pop");
                    var content = $(".content");
                    popMenu.css("height", $(window).height() - 12);
                    content.css("height", $(window).height() - 12 - 34);
                };
            });

            $(document).keydown(function (event) {
                var id = $("#currentId").val();
                var button = "button_" + id;
                //判断当event.keyCode 为37时（即左方面键);
                //判断当event.keyCode 为39时（即右方面键）;
                if (event.keyCode == 37) {
                    if (confirmPrev(button)) {
                        prevActivity(id);
                    }
                } else if (event.keyCode == 39) {
                    if (confirmNext(button)) {
                        nextActivity(id);
                    }
                }
            });

            $(function () {
            <c:choose>
                <c:when test = '${"true" eq requestScope.preview}' >
                //预览模式
                getPreviewJson("${requestScope.jsonId}");
                </c:when>
                <c:when test = '${"true" eq requestScope.info}' >
                //查看模式
                getLearningJson("${requestScope.ld.id}", true);
                </c:when>
                <c:otherwise >
                //学习模式
                getLearningJson("${requestScope.ld.uuid}");
                </c:otherwise>
            </c:choose>
            });
        </script>
    </body>
</html>
