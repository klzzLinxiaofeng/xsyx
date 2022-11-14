<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>LADS创作系统</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/falgun/Font-Awesome-3.2.1/css/font-awesome.css">
        <link href="${pageContext.request.contextPath}/res/css/common/lads/style.css" type="text/css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/res/css/common/lads/learning/tbkt/ke.css" type="text/css" rel="stylesheet"/>
        <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader-lads/webuploader.css" rel="stylesheet"/>
        <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/editor-lads/themes/default/default.css" rel="stylesheet"/>
        <!-- jquery 1.7.2 与jwplayer冲突 -->
        <!--            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>-->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.4.2.min.js"></script>-->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery.tools.min.js"></script>-->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery.timer.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
        <!-- 窗体控件 -->
        <%@include file="/views/embedded/plugin/layer.jsp" %>
        <!--遮罩层,弹出框,提示框-->
        <%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor-lads/kindeditor-min.js" type="text/javascript"></script>
<%--        <script src="${pageContext.request.contextPath}/res/plugin/My97DatePicker/WdatePicker.js" type="text/javascript"></script>--%>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/jwplayer6.8/jwplayer.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader-lads/webuploader.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader-lads/xyuploader.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader-lads/md5_md5blob.js"></script>
    </head>
    <body>
        <!--        <div class="navbar navbar-inverse top-nav">
                    <div class="navbar-inner">
                        <div class="container">
                            <a class="brand" href="./index.html"><img src="images/logo.png" alt="Falgun"></a>
        
                            <div class="btn-toolbar pull-right notification-nav">
        
                                <div class="btn-group">
                                    <div class="dropdown">
                                        <a class="btn btn-notification dropdown-toggle" data-toggle="dropdown" style="width: 155px;"><img src="images/user-thumb.png" 
                                                                                                                                          style="width: 35px; height: 35px; border-radius: 50%;border:1px solid #116ebb;">
                                            <span style="">罗志明 <i class="fa fa-angle-down"></i></span>
                                        </a>
                                        <div class="dropdown-menu pull-right">
                                            <a style="width: 130px; color: #333; font-size: 16px; display: block; padding-left: 34px; line-height: 33px;" 
                                               href="javascript:void(0)"> <i class="icon-asterisk" style="padding-right: 5px;"></i>基本信息</a>
                                            <a style="width: 130px; color: #333; font-size: 16px; display: block; padding-left: 34px; line-height: 33px;" 
                                               href="javascript:void(0)"> <i class="icon-signout" style="padding-right: 5px;"></i>安全退出</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="btn-group">
                                    <div class="dropdown">
                                        <a class="btn btn-notification dropdown-toggle" data-toggle="dropdown"><i class="icon-comments-alt"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
        <form id="form" onsubmit="return checkSubmit();" action="${pageContext.request.contextPath}/lads/authoring/save" method="post">
            <div class="lads">
                <div class="side">
                      <!-- <div class="logo"><img src="${pageContext.request.contextPath}/res/images/common/lads/t_logo.jpg"/></div>-->
                    <div class="list" id="activityPanel">

                    </div>
                    <div class="count">当前活动数：<span id="activityCount">0</span>个
                        <!--弹出层-->
                        <div class="add_menu">
                            <c:forEach var="lib" items="${requestScope.toolLibraryList}">
                                <img onclick="createActivity('${lib.toolName}');" src="${pageContext.request.contextPath}${lib.image}"/>
                            </c:forEach>
                        </div>
                    </div>
                    <ul class="tool">
                        <li>
                            <a href="javascript:void(0)" onclick="toggleAddMenu()" id="addButton" class="add_hover"></a>
                            <a href="javascript:void(0)" onclick="createGroup()" id="addGroupButton" class="folder"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="upSort()" id="upButton" class="up_disable"></a>
                            <a href="javascript:void(0)" onclick="downSort()" id="downButton" class="down_disable"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="removeActivity()" id="deleteButton" class="del"></a>
                        </li>
                    </ul>
                </div>

                <!--右边div区域-->   
                <div class="container-fluid" style="margin:0 15px;overflow:hidden;min-height: 700px;background:#fff;">
                    <div class="row-fluid tb-list">
                        <div class="span12 add-head add-head-a m-top">
                            <button class="btn btn-c" type="button"><i class="icon-pencil"></i></button>
                            <input type="text" id="ldTitle" placeholder="请输入课程名称" class="search-query span2">

                            <!--                            <button class="m-back fr" type="button">返回</button>-->
                        </div>
                        <div class="m-hr"></div>
                    </div>
                    <div class="row-fluid">
                    </div>
                </div>
                <!--end右边div-->                 
            </div>
            <div class="lads_btn">
                <input value="${requestScope.ldId}" name="ldId" id="ldId" type="hidden"/>
                <input value="${requestScope.userId}" name="userId" id="userId" type="hidden"/>
                <input value="${requestScope.sysType}" name="sysType" id="sysType" type="hidden"/>
                <input value="${requestScope.emUrl}" name="emUrl" id="emUrl" type="hidden"/>
                <input value="${requestScope.learnerTemplate}" name="learnerTemplate" id="learnerTemplate" type="hidden"/>
                <input value="${requestScope.templateId}" name="templateId" id="templateId" type="hidden"/>
                <a href="javascript:void(0)" onclick="submitData()" class="save">保存<!--NULL--></a>
                <a href="javascript:void(0)" onclick="preview()" class="view">预览<!--NULL--></a>
                <!--                <a href="javascript:void(0)" class="release">NULL</a>
                                <a href="javascript:void(0)" class="down">NULL</a>-->

            </div>
        </form>
        <a id="previewA" target="_blank" ></a>
        <div id="saveLoading" style="display: none"><img  src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/></div>
        <div id="previewLoading" style="display: none"><img  src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/></div>
        <script type="text/javascript">
            //该变量是为了解决多份练习同时存在如何获取当前的操作的swf
            var chooseQuiz
            function Activity(actJson) {
                var at = ""
                var tn = "";
                var ti = "";
                var init = function () {
                    at = actJson.activityType;
                }
                this.createGroup = function (actJson) {
                    ti = actJson.groupTitle;
                    var random = actJson.id;
                    var stringifyJson = cloneJson(actJson);
                    stringifyJson["subActivitys"] = [];
                    var html = "<div id=\"group_" + random + "\" class=\"group\">"
                            + "<b onclick=\"toggleGroupContent('" + random + "')\" class=\"unfold\"></b>"
                            + "<textarea style=\"display:none\" id=\"groupJson_" + random + "\">" + JSON.stringify(stringifyJson) + "</textarea>"
                            + "<img class=\"folder\"  src=\"${pageContext.request.contextPath}/res/images/common/lads/t_side_list_folder.png\"/>"
                            + "<h2 onclick=\"setGroupHover('" + random + "')\" >" + ti + "</h2></div>"
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
                    tn = actJson.toolName;
                    var random = actJson.id;
                    var className;
                    //获取分数
                    var score ;
                    if (tn == "QuizTool") {
                        className = "quiz";
                        ti = actJson.quizTitle;
                        score = actJson.quizScore;
                    } else if (tn == "FaceTeachingTool") {
                        className = "teaching";
                        ti = actJson.faceTeachingTitle;
                        score = actJson.faceTeachingScore;
                    } else if (tn == "EditorTool") {
                        className = "study";
                        ti = actJson.editorTitle;
                        score = actJson.editorScore;
                    } else if (tn == "DiscussTool") {
                        className = "discuss";
                        ti = actJson.discussTitle;
                        score = actJson.discussScore;
                    } else if (tn == "SurveyTool") {
                        className = "survey";
                        ti = actJson.surveyTitle;
                        score = actJson.surveyScore;
                    } else if (tn == "PowerPointTool") {
                        className = "powerpoint";
                        ti = actJson.powerPointTitle;
                        score = actJson.powerPointScore;
                    } else if (tn == "MediaTool") {
                        className = "media";
                        ti = actJson.mediaTitle;
                        score = actJson.mediaScore;
                    }
                    score = (score!=null&&score!="")?score+"分":"";
                    var html = "<a id=\"button_" + random + "\" onclick=\"setHover('" + random + "')\" href=\"javascript:void(0)\">"
                            + "<input type=\"hidden\" id=\"toolName_" + random + "\" value=\"" + tn + "\"  />"
                            + "<textarea style=\"display:none\" id=\"toolJson_" + random + "\">" + JSON.stringify(actJson) + "</textarea>"
                            + "<span class=\"" + className + "\"></span>"
                            + "<dl><dt>" + ti + "</dt><span id=\"button_" + random + "_score\" >"+score+"</span><dd></dd></dl></a>";
                    if (groupId != null && groupId != "") {
                        $("#" + groupId).append(html);
                    } else {
                        if ($("#activityPanel div[class^='group_hover']").html() != null) {
                            $("#activityPanel div[class^='group_hover']").append(html);
                        } else {
                            $("#activityPanel").append(html);
                        }
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
                    $(".row-fluid").append("<div id='rightContent_" + random + "'></div>");
                    $("#rightContent_" + random).append("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>")
                    var ti = actJson.groupTitle;
                    var contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/groupPaper", {"title": ti, "id": random});
                }
                Activity.createContent = function (id) {
                    var actJson = JSON.parse($("#toolJson_" + id).text());
                    var random = actJson.id;
                    $(".row-fluid").append("<div id='rightContent_" + random + "'></div>");
                    $("#rightContent_" + random).append("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>")
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
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/quizToolPaper", {"title": ti, "id": random, "quizScore": actJson.quizScore, "quizXmlPath": actJson.quizXmlPath});
                    } else if (tn == "FaceTeachingTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/faceTeachingToolPaper", {"title": ti, "id": random, "faceTeachingScore": actJson.faceTeachingScore, "faceTeachingStartTime": actJson.faceTeachingStartTime, "faceTeachingStopTime": actJson.faceTeachingStopTime,
                            "faceTeachingAddress": actJson.faceTeachingAddress, "faceTeachingDescription": actJson.faceTeachingDescription});
                    } else if (tn == "EditorTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/editorToolPaper", {"title": ti, "id": random, "editorScore": actJson.editorScore, "editorContent": actJson.editorContent, "editorUploadList": actJson.editorUploadList});
                    } else if (tn == "DiscussTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/discussToolPaper", {"title": ti, "id": random, "discussContent": actJson.discussContent, "discussScore": actJson.discussScore, "discussStartTime": actJson.discussStartTime, "discussStopTime": actJson.discussStopTime,
                            "discussAllowUpload": actJson.discussAllowUpload});
                    } else if (tn == "SurveyTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/surveyToolPaper", {"title": ti, "id": random, "surveyScore": actJson.surveyScore, "surveyDescription": actJson.surveyDescription});
                    } else if (tn == "PowerPointTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/powerPointToolPaper", {"title": ti, "id": random, "powerPointScore": actJson.powerPointScore, "powerPointFileId": actJson.powerPointFileId});
                    } else if (tn == "MediaTool") {
                        contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/authoring/mediaToolPaper", {"title": ti, "id": random, "mediaScore": actJson.mediaScore, "mediaEntityId": actJson.mediaEntityId, "mediaUploadList": actJson.mediaUploadList});
                    }
                };
                init();
            }
            function createGroup() {
                var activity = {"activityType": "group", "groupTitle": "新建文件夹"};
                var random = (new Date().getTime()) ^ Math.random();
                activity["id"] = random;
                var act = new Activity(activity);
                //添加组
                act.createButton();
                setGroupHover(random);
                //更新活动数
                updateActivityCount();
            }
            function createActivity(toolName) {
                var activity = {"activityType": "tool", "toolName": toolName};
                var random = (new Date().getTime()) ^ Math.random();
                activity["id"] = random;
                if (toolName == "QuizTool") {
                    activity["quizTitle"] = "课程练习";
                } else if (toolName == "FaceTeachingTool") {
                    activity["faceTeachingTitle"] = "面授导学";
                } else if (toolName == "EditorTool") {
                    activity["editorTitle"] = "教学资料";
                } else if (toolName == "DiscussTool") {
                    activity["discussTitle"] = "主题讨论";
                } else if (toolName == "SurveyTool") {
                    activity["surveyTitle"] = "调查问卷";
                } else if (toolName == "PowerPointTool") {
                    activity["powerPointTitle"] = "幻灯片PPT";
                } else if (toolName == "MediaTool") {
                    activity["mediaTitle"] = "微课";
                }
                var act = new Activity(activity);
                //添加活动
                act.createButton();
                setHover(random)
                toggleAddMenu();
                //更新活动数
                updateActivityCount();
            }
            function updateActivityCount() {
                var size = 0;
                $("#activityPanel").children().each(function () {
                    size++;
                    if ($(this).attr("class") == "group") {
                        size = size + $(this).children("a[id^='button_']").size();
                    }
                });
                $("#activityCount").html(size);
            }
            function removeActivity() {
                var hoverEle = $("#activityPanel a[class='hover']");
                if (hoverEle.html() == null) {
                    hoverEle = $("#activityPanel div[class='group_hover']");
                    if (hoverEle.html() != null) {
                        hoverEle.children("a").each(function () {
                            var bid = $(this).attr("id").substring(hoverEle.attr("id").indexOf("_") + 1);
                            $("#rightContent_" + bid).remove();
                        })
                        var id = hoverEle.attr("id").substring(hoverEle.attr("id").indexOf("_") + 1);
                        $("#group_" + id).remove();
                        $("#rightContent_" + id).remove();
                        //更新活动数
                        updateActivityCount();
                    }
                } else {
                    var id = hoverEle.attr("id").substring(hoverEle.attr("id").indexOf("_") + 1);
                    $("#button_" + id).remove();
                    $("#rightContent_" + id).remove();
                    //更新活动数
                    updateActivityCount();
                }
            }
            function setGroupHover(id) {
                var group = "group_" + id;
                $("#activityPanel a[id^='button_']").each(function () {
                    $(this).attr("class", "");
                });
                $("#activityPanel div[id^='group_']").each(function () {
                    $(this).attr("class", "group");
                });
                $("#" + group).attr("class", "group_hover");
                if ($("#" + group).prev().html() == null) {
                    $("#upButton").attr("class", "up_disable");
                } else {
                    $("#upButton").attr("class", "up");
                }
                if ($("#" + group).next().html() == null) {
                    $("#downButton").attr("class", "down_disable");
                } else {
                    $("#downButton").attr("class", "down");
                }
                $(".row-fluid div[id^='rightContent_']").hide();
                showGroupContent(id);
            }
            function setHover(id) {
                var button = "button_" + id;
                $("#activityPanel a[id^='button_']").each(function () {
                    $(this).attr("class", "");
                });
                $("#activityPanel div[id^='group_']").each(function () {
                    $(this).attr("class", "group");
                });
                $("#" + button).attr("class", "hover");
                if ($("#" + button).parent().attr("id").indexOf("group_") != -1) {
                    //组内活动判断能否上移动
                    var tagName = $("#" + button).prev().get(0).tagName;
                    if (tagName == "h2" || tagName == "H2") {
                        $("#upButton").attr("class", "up_disable");
                    } else {
                        $("#upButton").attr("class", "up");
                    }
                } else {
                    //组外活动判断能否上移动
                    if ($("#" + button).prev().html() == null) {
                        $("#upButton").attr("class", "up_disable");
                    } else {
                        $("#upButton").attr("class", "up");
                    }
                }
                if ($("#" + button).next().html() == null) {
                    $("#downButton").attr("class", "down_disable");
                } else {
                    $("#downButton").attr("class", "down");
                }
                $(".row-fluid div[id^='rightContent_']").hide();
                showContent(id);
            }
            function showGroupContent(id) {
                var content = "rightContent_" + id;
                if ($("#" + content).html() == null) {
                    //没有加载过的
                    Activity.createGroupContent(id);
                } else {
                    //已经加载过的
                    $("#" + content).show();
                }
            }
            function showContent(id) {
                var content = "rightContent_" + id;
                //设置选中的练习
                chooseQuiz = id;
                if ($("#" + content).html() == null) {
                    //没有加载过的
                    Activity.createContent(id);
                } else {
                    //已经加载过的
                    if ($("#" + content).find("#powerPointContent_" + id).html() != null) {
                        //ppt工具的内容无论是否已经加载过都从新加载,解决ie下隐藏再显示后的黑屏问题
                        loadPowerPointContentIndex($("#powerPointFileId_" + id).val(), $("#powerPointContent_" + id).prev().text(), id);
                    }
                    $("#" + content).show();
                }
            }
            function upSort() {
                if ($("#upButton").attr("class") == "up") {
                    var o = $("a[class='hover']");
                    if (o.html() == null) {
                        o = $("div[class='group_hover']")
                    }
                    if (o.prev().length > 0) {
                        var tmp = o.clone();
                        var oo = o.prev();
                        o.remove();
                        oo.before(tmp);
                        if (o.attr("class") == "group_hover") {
                            setGroupHover(tmp.attr("id").substring(tmp.attr("id").indexOf("_") + 1));
                        } else {
                            setHover(tmp.attr("id").substring(tmp.attr("id").indexOf("_") + 1));
                        }
                    }
                }
            }
            function downSort() {
                if ($("#downButton").attr("class") == "down") {
                    var o = $("a[class='hover']");
                    if (o.html() == null) {
                        o = $("div[class='group_hover']")
                    }
                    if (o.next().length > 0) {
                        var tmp = o.clone();
                        var oo = o.next();
                        o.remove();
                        oo.after(tmp);
                        if (o.attr("class") == "group_hover") {
                            setGroupHover(tmp.attr("id").substring(tmp.attr("id").indexOf("_") + 1));
                        } else {
                            setHover(tmp.attr("id").substring(tmp.attr("id").indexOf("_") + 1));
                        }
                    }
                }
            }
            function toggleGroupContent(id) {
                if ($("#group_" + id + " b").attr("class") == "btn1") {
                    $("#group_" + id + " b").attr("class", "unfold");
                    $("#group_" + id + " a").show();
                } else {
                    $("#group_" + id + " b").attr("class", "btn1");
                    $("#group_" + id + " a").hide();
                }
            }
            function toggleAddMenu() {
                if ($("#addButton").attr("class") == "add_hover") {
                    $(".add_menu").hide();
                    $("#addButton").attr("class", "add");
                } else {
                    $(".add_menu").show();
                    $("#addButton").attr("class", "add_hover");
                }
            }
            function changeTitle(id, value) {
                $("#button_" + id + " dt").text(value);
                $("#group_" + id + " h2").text(value);
            }
            function checkScore(id, obj) {
                var partten = /^[0-9]*$/;
                var value = $(obj).val();
                if (!partten.test(value)) {
                    $(obj).val("");
                    $("#button_" + id + "_score").text("");
                    if ($(obj).next().html() == null) {
                        $(obj).parent().append("<font color='red'>请输入有效数字</font>");
                    }
                } else {
                    if (value != "") {
                        $("#button_" + id + "_score").text(value + "分");
                    } else {
                        $("#button_" + id + "_score").text(value);
                    }
                    $(obj).next().remove();
                }
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
            function checkTitle() {
                var title = $.trim($("#ldTitle").val());
                if (title == null || title == "") {
                    $.alert("请输入标题");
                    return false;
                }
                return true;
            }
            function checkActivity() {
                var actList = $.trim($("#activityPanel").html());
                if (actList == null || actList == "") {
                    $.alert("请创建活动");
                    return false;
                }
                return true;
            }
            function checkSubmit() {
                if (!checkTitle()) {
                    return false;
                }
                if (!checkActivity()) {
                    return false;
                }
                return true;
            }
            function getAuthoringJson(ldId) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/authoring/getAuthoringJson",
                    type: "POST",
                    dataType: "json",
                    data: {"ldId": ldId},
                    async: false,
                    success: function (json) {
                        $("#ldTitle").val(json.title);
                        loadActivitys(json);
                    }
                });
            }
            function getTemplateJson(templateId) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/authoring/getTemplateJson",
                    type: "POST",
                    dataType: "json",
                    data: {"templateId": templateId},
                    async: false,
                    success: function (json) {
                        $("#ldTitle").val(json.title);
                        loadActivitys(json);
                    }
                });
            }
            function loadActivitys(json) {
                var activitys = json.activitys;
                var firstId
                for (var i = 0; i < activitys.length; i++) {
                    var act = new Activity(activitys[i]);
                    //创建活动
                    act.createButton();
                }
                //显示第一个活动
                $("#activityPanel a[id^='button_']").each(function () {
                    firstId = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                    return false;
                });
                setHover(firstId);
                //计算活动总数
                updateActivityCount();
            }
            function submitData() {
                if (checkSubmit()) {
                    openLoadingOverlay("save");
                }
            }
            function preview() {
                if (checkActivity()) {
                    openLoadingOverlay("preview");
                }
            }
            function getSaveData(type) {
                var json = {"activitys": []};
                var title = $.trim($("#ldTitle").val());
                if ((type != null && type != "" && type == "preview") && (title == null || title == "")) {
                    title = "预览";
                }
                var userId = $("#userId").val();
                var ldId = $("#ldId").val();
                var TOOL = "tool";
                var GROUP = "group";
                json["ldId"] = ldId;
                json["title"] = title;
                json["userId"] = userId;
                $("#activityPanel").children().each(function (i) {
                    var id = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                    var activity = {};
                    activity["id"] = id;
                    if ($(this).attr("id").indexOf("button_") != -1) {
                        var toolName = $(this).find("#toolName_" + id).val();
                        activity["activityType"] = TOOL;
                        activity["toolName"] = toolName;
                        activity = createToolJson(activity, toolName, id);
                    } else if ($(this).attr("id").indexOf("group_") != -1) {
                        if ($("#rightContent_" + id).html() != null) {
                            activity["activityType"] = GROUP;
                            activity["groupTitle"] = $("#groupTitle_" + id).val();
                            activity["subActivitys"] = [];
                        } else {
                            activity = JSON.parse($("#groupJson_" + id).text());
                            activity["subActivitys"] = [];
                            if ($.trim($("#ldId").val()) != "") {
                                //确定是编辑状态时才判断活动是否被加载过
                                activity["inLoad"] = "false";
                            }
                        }
                        $("#group_" + id).find("a[id^='button_']").each(function (v) {
                            var id = $(this).attr("id").substring($(this).attr("id").indexOf("_") + 1);
                            var subActivity = {};
                            var toolName = $(this).find("#toolName_" + id).val();
                            subActivity["activityType"] = TOOL;
                            subActivity["toolName"] = toolName;
                            subActivity["id"] = id;
                            subActivity = createToolJson(subActivity, toolName, id);
                            activity["subActivitys"].push(subActivity);
                        });
                    }
                    json["activitys"].push(activity);
                });
                return json;
            }
            function createToolJson(activity, toolName, id) {
                if ($("#rightContent_" + id).html() != null) {
                    if (toolName == "QuizTool") {
                        activity["quizTitle"] = $("#quizTitle_" + id).val();
                        activity["quizScore"] = $("#quizScore_" + id).val();
                        activity["quizXmlContent"] = getQuizXmlByBuilderId(id);
                        activity["quizUploadPath"] = $("#uploadPath_" + id).val();
                    } else if (toolName == "FaceTeachingTool") {
                        activity["faceTeachingTitle"] = $("#faceTeachingTitle_" + id).val();
                        activity["faceTeachingScore"] = $("#faceTeachingScore_" + id).val();
                        activity["faceTeachingStartTime"] = $("#faceTeachingStartTime_" + id).val();
                        activity["faceTeachingStopTime"] = $("#faceTeachingStopTime_" + id).val();
                        activity["faceTeachingDescription"] = $("#faceTeachingDescription_" + id).val();
                        activity["faceTeachingAddress"] = $("#faceTeachingAddress_" + id).val();
                    } else if (toolName == "EditorTool") {
                        activity = getEditorToolSaveData(activity, id);
                    } else if (toolName == "DiscussTool") {
                        activity["discussTitle"] = $("#discussTitle_" + id).val();
                        activity["discussContent"] = $("#discussContent_" + id).val();
                        activity["discussScore"] = $("#discussScore_" + id).val();
                        activity["discussAllowUpload"] = $("#discussAllowUpload_" + id).attr("checked") == true ? $("#discussAllowUpload_" + id).val().toString() : "";
                        activity["discussStartTime"] = $("#discussStartTime_" + id).val();
                        activity["discussStopTime"] = $("#discussStopTime_" + id).val();
                    } else if (toolName == "SurveyTool") {
                        activity["surveyTitle"] = $("#surveyTitle_" + id).val();
                        activity["surveyScore"] = $("#surveyScore_" + id).val();
                        activity["surveyDescription"] = $("#surveyDescription_" + id).val();
                        activity["surveyQuestions"] = getSurveyQuestionsJson(id);
                    } else if (toolName == "PowerPointTool") {
                        activity["powerPointTitle"] = $("#powerPointTitle_" + id).val();
                        activity["powerPointScore"] = $("#powerPointScore_" + id).val();
                        activity["powerPointFileId"] = $("#powerPointFileId_" + id).val();
                    } else if (toolName == "MediaTool") {
                        activity = getMediaToolSaveData(activity, id);
                    }
                } else {
                    activity = JSON.parse($("#toolJson_" + id).text());
                    if ($.trim($("#ldId").val()) != "") {
                        //确定是编辑状态时才判断活动是否被加载过
                        activity["inLoad"] = "false";
                    }
                }
                return activity;
            }
            function closeLoadingOverlay() {
                //$("#dataLoading").hide();
                loadingOverlayObj.close();
            }
            function ajaxPreview() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/learning/savePreviewJson",
                    type: "POST",
                    data: {"json": JSON.stringify(getSaveData("preview"))},
                    async: true,
                    success: function (jsonId) {
                        closeLoadingOverlay();
                        //模拟a标签点击来代替window.open,解决浏览器阻止弹出窗口
                        var template = $.trim($("#learnerTemplate").val());
                        $("#previewA").attr("href", "${pageContext.request.contextPath}/lads/learning/preview?jsonId=" + jsonId + "&template=" + template);
                        $("#previewA")[0].click();
                    }
                });
            }

            //overlay弹出层公用函数
            var loadingOverlayObj;
            function openLoadingOverlay(fname) {
                loadingOverlayObj = new loadLayer();
                loadingOverlayObj.show();
                if (fname == "preview") {
                    ajaxPreview();
                } else if (fname == "save") {
                    ajaxSave();
                }

                //初始化数据遮罩层
//                var top = (document.body.scrollTop + document.body.clientHeight / 2 - 600 / 2) + "px";
//                var left = (document.body.scrollLeft + document.body.clientWidth / 2 - 100 / 2);
//                if (fname == "preview") {
//                    loadingOverlayObj = $("#previewLoading").overlay({
//                        api: true,
//                        closeOnClick: false,
//                        oneInstance: true,
//                        left: left,
//                        top: '30%',
//                        expose: {
//                            color: '#333',
//                            opacity: 0.7,
//                            zIndex: 10000
//                        },
//                        onLoad: function() {
//                            ajaxPreview();
//                        }
//                    });
//                } else if (fname == "save") {
//                    loadingOverlayObj = $("#saveLoading").overlay({
//                        api: true,
//                        closeOnClick: false,
//                        oneInstance: true,
//                        left: left,
//                        top: '30%',
//                        expose: {
//                            color: '#333',
//                            opacity: 0.7,
//                            zIndex: 10000
//                        },
//                        onLoad: function() {
//                            ajaxSave();
//                        }
//                    });
//                }
//                loadingOverlayObj.load();
//                $("#" + fname + "Loading").show();
            }
            function ajaxSave() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/lads/authoring/save",
                    type: "POST",
                    data: {"json": JSON.stringify(getSaveData())},
                    async: true,
                    success: function (ldId) {
                        $("#ldId").val(ldId);
                        embedLessonSave();
                    }
                });
            }
            //保存外部课程
            function embedLessonSave() {
                var emUrl = $("#emUrl").val();
                var data = {"ldId": $("#ldId").val(), "ldTitle": $.trim($("#ldTitle").val()), "lessonId": $("#userId").val(), "templateId": $("#templateId").val()};
                $.ajax({
                    url: "${pageContext.request.contextPath}" + emUrl,
                    type: "POST",
                    data: data,
                    async: false,
                    success: function (html) {
                        closeLoadingOverlay();
                        $.alert("保存成功");
                    }
                });
            }

            function cloneJson(para) {
                var rePara = null;
                var type = Object.prototype.toString.call(para);
                if (type.indexOf("Object") > -1) {
                    rePara = jQuery.extend(true, {}, para);
                } else if (type.indexOf("Array") > 0) {
                    rePara = [];
                    jQuery.each(para, function (index, obj) {
                        rePara.push(jQuery.cloneJSON(obj));
                    });
                } else {
                    rePara = para;
                }
                return rePara;
            }

            $(function () {
                var ldId = $("#ldId").val();
                if (ldId != null && ldId != "") {
                    //编辑功能
                    getAuthoringJson(ldId);
                } else {
                    var templateId = $("#templateId").val();
                    if (templateId != null && templateId != "") {
                        //使用模板
                        getTemplateJson(templateId);
                    }
                }
            });
        </script>
    </body>
</html>