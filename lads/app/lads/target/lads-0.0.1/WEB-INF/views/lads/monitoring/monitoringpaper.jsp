<%--
    Document   : monitoringpaper
    Created on : 2012-8-21, 15:42:01
    Author     : Administrator
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>LADS监控系统</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/falgun/Font-Awesome-3.2.1/css/font-awesome.css">
        <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/editor-lads/themes/default/default.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/res/css/common/lads/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/highcharts-lads/js/highcharts.js" type="text/javascript"></script>
        <!-- 窗体控件 -->
        <%@include file="/views/embedded/plugin/layer.jsp" %>
        <!--遮罩层,弹出框,提示框-->
        <%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor-lads/kindeditor-min.js" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js" type="text/javascript"></script>
        <style>
    .btn{
        background: #2EA3EC;
    }
    .btn2{
        background: #2473A5;
    }
</style>
    </head>
    <body>
        <dl id="header">
            <dt>
            <img src="${pageContext.request.contextPath}/res/images/common/lads/lads.jpg" />
            <h3 id="ldTitle" style="color: #7DCCD5;padding-top:8px;" ></h3>
            <input class="btn " id="learnerRecordButton" style="height: 27px;" type="button" onclick="learnerRecord()" value="学员记录" />
            <input class="btn" id="dataStatisticsButton" style="height: 27px;" type="button" onclick="dataStatistics()" value="数据统计图" />
            <input class="btn btn2" id="ladsButton" style="height: 27px;" type="button" onclick="lads()" value="课程环节监控" />
            </dt>
            <dd><!--NULL--></dd>
        </dl>
        <div id="lads" class="lads">
            <div class="side">
                <div class="line"><!--NULL--></div>
                <div class="list" id="activityPanel">
                </div>
                <div class="count">
                    活动总数：<span id="activityCount">0</span>个
                </div>
                <div class="tool"></div>
            </div>
            <ul class="content">
                <li class="u">
                    <dl>
                        <dt><!--NULL--></dt>
                        <dd><!--NULL--></dd>
                    </dl>
                </li>
                <li id="contentLi" class="m">

                </li>
                <li class="b">
                    <dl>
                        <dt><!--NULL--></dt>
                        <dd><!--NULL--></dd>
                    </dl>
                </li>
            </ul>
            <input value="${requestScope.ld.uuid}" name="ldId" id="ldId" type="hidden"/>
        </div>
        <div id="dataStatistics"></div>
        <div id="learnerRecord"></div>
        <div id="singleRecord"></div>
        <script type="text/javascript">
            function Activity(actJson){
                var at=""
                var tn="";
                var ti="";
                var init = function(){
                    at = actJson.activityType;
                }
                this.createGroup = function(actJson){
                    var random = actJson.id;
                    ti = actJson.groupTitle;
                    var html = "<div id=\"group_"+random+"\" class=\"group\">"
                        +"<b onclick=\"toggleGroupContent('"+random+"')\" class=\"unfold\"></b>"
                        +"<textarea style=\"display:none\" id=\"groupJson_"+random+"\">"+JSON.stringify(actJson)+"</textarea>"
                        +"<img class=\"folder\" src=\"${pageContext.request.contextPath}/res/images/common/lads/t_side_list_folder.png\"/>"
                        +"<h2>"+ti+"</h2></div>"
                    $("#activityPanel").append(html);
                    var subActivitys = actJson.subActivitys;
                    if(subActivitys!=null){
                        for(var i=0;i<subActivitys.length;i++){
                            this.createTool(subActivitys[i],"group_"+random);
                        }
                    }
                    return html;
                }
                this.createTool = function(actJson,groupId){
                    var random = actJson.id;
                    tn = actJson.toolName;
                    var className;
                    var score = "";
                    var startTime="";
                    var stopTime = "";
                    if(tn=="QuizTool"){
                        className = "quiz";
                        ti = actJson.quizTitle;
                        score = actJson.quizScore;
                    }else if(tn=="FaceTeachingTool"){
                        className = "teaching";
                        ti = actJson.faceTeachingTitle;
                        score = actJson.faceTeachingScore;
                        startTime = actJson.faceTeachingStartTime;
                        stopTime = actJson.faceTeachingStopTime;
                    }else if(tn=="EditorTool"){
                        className = "study";
                        ti = actJson.editorTitle;
                        score = actJson.editorScore;
                    }else if(tn=="DiscussTool"){
                        className = "discuss";
                        ti = actJson.discussTitle;
                        score = actJson.discussScore;
                        startTime = actJson.discussStartTime;
                        stopTime = actJson.discussStopTime;
                    }else if(tn=="SurveyTool"){
                        className = "survey";
                        ti = actJson.surveyTitle;
                        score = actJson.surveyScore;
                    }else if(tn=="PowerPointTool"){
                        className = "powerpoint";
                        ti = actJson.powerPointTitle;
                        score = actJson.powerPointScore;
                    }else if(tn=="MediaTool"){
                        className = "media";
                        ti = actJson.mediaTitle;
                        score = actJson.mediaScore;
                    }
                    var centerHtml;
                    if(startTime!=""||stopTime!=""){
                        var now = new Date();
                        now = changeTimeToString(now);
                        var dateSrc = "";
                        if(now<startTime){
                            dateSrc = "${pageContext.request.contextPath}/res/images/common/lads/start.jpg";
                        }
                        if(startTime<now&&now<stopTime){
                            dateSrc = "${pageContext.request.contextPath}/res/images/common/lads/doing.jpg";
                        }
                        if(stopTime<now){
                            dateSrc = "${pageContext.request.contextPath}/res/images/common/lads/end.jpg";
                        }
                        if(score!=null&&score!=""){
                            centerHtml = "<ul>"
                                +"<li class=\"t\"><img src=\""+dateSrc+"\"/></li>"
                                +"<li class=\"m\"><strong class=\"green\">"+ti+"</strong><span id=\"button_"+random+"_score\" >"+score+"分</span></li>"
                                +"<li class=\"b\">"+startTime+"-"+stopTime+"</li>"
                                +"</ul>"
                        }else{
                            centerHtml = "<ul>"
                                +"<li class=\"t\"><img src=\""+dateSrc+"\"/></li>"
                                +"<li class=\"m\"><strong class=\"green\">"+ti+"</strong></li>"
                                +"<li class=\"b\">"+startTime+"-"+stopTime+"</li>"
                                +"</ul>"
                        }
                    }else{
                        if(score!=null&&score!=""){
                            centerHtml ="<dl><dt>"+ti+"</dt><span id=\"button_"+random+"_score\" >"+score+"分</span><dd></dd></dl>";
                        }else{
                            centerHtml ="<dl><dt>"+ti+"</dt><dd></dd></dl>";
                        }
                    }
                    var html = "<a href=\"javascript:void(0)\" id=\"button_"+random+"\" onclick=\"setHover('"+random+"')\" >"
                        +"<span class=\""+className+"\"></span>"
                        +"<textarea style=\"display:none\" id=\"toolJson_"+random+"\">"+JSON.stringify(actJson)+"</textarea>"
                        +centerHtml
                        +"</a>";
                    if(groupId!=null&&groupId!=""){
                        $("#"+groupId).append(html);
                    }else{
                        $("#activityPanel").append(html);
                    }
                    return html;
                }
                this.createButton = function(){
                    if(at=="tool"){
                        this.createTool(actJson);
                    }else if(at=="group"){
                        this.createGroup(actJson);
                    }
                }
                Activity.createGroupContent = function(id){
                    var actJson = JSON.parse($("#groupJson_"+id).text());
                    var random = actJson.id;
                    var contentHtml = ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/groupPaper",{"title":ti,"id":random});
                    $("#contentLi").append("<div id='rightContent_"+random+"'></div>");
                    $("#rightContent_"+random).append(contentHtml);
                }
                Activity.createContent = function(id){
                    var actJson = JSON.parse($("#toolJson_"+id).text());
                    var random = actJson.id;
                    $("#contentLi").append("<div id='rightContent_"+random+"'></div>");
                    $("#rightContent_"+random).append("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>");
                    var contentHtml;
                    var tn = actJson.toolName;
                    var ti;
                    if(tn=="QuizTool"){
                        ti = actJson.quizTitle;
                    }else if(tn=="FaceTeachingTool"){
                        ti = actJson.faceTeachingTitle;
                    }else if(tn=="EditorTool"){
                        ti = actJson.editorTitle;
                    }else if(tn=="DiscussTool"){
                        ti = actJson.discussTitle;
                    }else if(tn=="SurveyTool"){
                        ti = actJson.surveyTitle;
                    }else if(tn=="PowerPointTool"){
                        ti = actJson.powerPointTitle;
                    }else if(tn=="MediaTool"){
                        ti = actJson.mediaTitle;
                    }
                    if(tn=="QuizTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/quizToolPaper",
                        {"title":ti,"id":random,"quizXmlPath":actJson.quizXmlPath,"quizResultUrl":actJson.quizResultUrl});
                    }else if(tn=="FaceTeachingTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/faceTeachingToolPaper",
                        {"title":ti,"id":random,"faceTeachingStartTime":actJson.faceTeachingStartTime,"faceTeachingStopTime":actJson.faceTeachingStopTime,
                            "faceTeachingAddress":actJson.faceTeachingAddress,"faceTeachingDescription":actJson.faceTeachingDescription,"faceTeachingScore":actJson.faceTeachingScore,
                            "ldId":"${requestScope.ld.uuid}"});
                    }else if(tn=="EditorTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/editorToolPaper",
                        {"title":ti,"id":random,"editorContent":actJson.editorContent,"editorScore":actJson.editorScore,"ldId":"${requestScope.ld.uuid}"});
                    }else if(tn=="DiscussTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/discussToolPaper",
                        {"title":ti,"id":random,"discussScore":actJson.discussScore,"discussStartTime":actJson.discussStartTime,"discussStopTime":actJson.discussStopTime,
                            "discussAllowUpload":actJson.discussAllowUpload,"ldId":"${requestScope.ld.uuid}"});
                    }else if(tn=="SurveyTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/surveyToolPaper",
                        {"title":ti,"id":random,"surveyScore":actJson.surveyScore,"surveyDescription":actJson.surveyDescription});
                    }else if(tn=="PowerPointTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/powerPointToolPaper",
                        {"title":ti,"id":random,"ldId":"${requestScope.ld.uuid}","powerPointScore":actJson.powerPointScore,"powerPointFileId":actJson.powerPointFileId});
                    }else if(tn=="MediaTool"){
                        contentHtml =ajaxGetHtml("${pageContext.request.contextPath}/lads/monitoring/mediaToolPaper",
                        {"title":ti,"id":random,"ldId":"${requestScope.ld.uuid}","mediaScore":actJson.mediaScore});
                    }
                };
                init();
            }

            function setHover(id){
                var button = "button_"+id;
                $("#activityPanel a[id^='button_']").each(function(){
                    $(this).attr("class","");
                });
                $("#activityPanel div[id^='group_']").each(function(){
                    $(this).attr("class","group");
                });
                $("#"+button).attr("class","hover");
                $("#contentLi div[id^='rightContent_']").hide();
                showContent(id);
                return false;
            }
            function showContent(id){
                var content = "rightContent_"+id;
                if($("#"+content).html()==null){
                    //没有加载过的
                    Activity.createContent(id);
                }else{
                    //已经加载过的
                    $("#"+content).show();
                }
            }
            function toggleGroupContent(id){
                if($("#group_"+id+" b").attr("class")=="btn1"){
                    $("#group_"+id+" b").attr("class","unfold");
                    $("#group_"+id+" a").show();
                }else{
                    $("#group_"+id+" b").attr("class","btn");
                    $("#group_"+id+" a").hide();
                }
            }
            function updateActivityCount(){
                var size = 0;
                $("#activityPanel").children().each(function(){
                    size++;
                    if($(this).attr("class")=="group"){
                        size = size +$(this).children("a[id^='button_']").size();
                    }
                });
                $("#activityCount").html(size);
            }
            function checkScore(id, obj) {
                var partten = /^[0-9]*$/;
                var value = $(obj).val();
                if (!partten.test(value)) {
                    $(obj).val("");
                    if ($(obj).next().html() == null) {
                        $(obj).parent().append("<font color='red'>请输入有效数字</font>");
                    }
                } else {
                    $(obj).next().remove();
                }
            }
            function ajaxGetHtml(url,data){
                var contentHtml
                $.ajax({
                    url:url,
                    type: "POST",
                    data:data,
                    async: true,
                    success:function(html){
                        contentHtml=html;
                        $("#rightContent_"+data.id).html(contentHtml);
                    }
                });
                return contentHtml;
            }
            function getMonitorJson(ldId){
                $.ajax({
                    url:"${pageContext.request.contextPath}/lads/monitoring/getMonitorJson",
                    type: "POST",
                    dataType:"json",
                    data:{"ldId":ldId},
                    async: false,
                    success:function(json){
                        loadActivitys(json);
                    }
                });
            }
            function loadActivitys(json){
                //初始化大标题
                if(json.title!=null&&json.title!=""){
                    $("#ldTitle").text(json.title);
                }
                var activitys = json.activitys;
                var firstId
                for(var i=0;i<activitys.length;i++){
                    var act = new Activity(activitys[i]);
                    //创建活动
                    act.createButton();
                }
                //显示第一个活动
                $("#activityPanel a[id^='button_']").each(function(){
                    firstId = $(this).attr("id").substring($(this).attr("id").indexOf("_")+1);
                    return false;
                });
                setHover(firstId);
                //计算活动总数
                updateActivityCount();
            }
        
            //数据统计
            function dataStatistics(){
                switchMonitorMap("dataStatistics");
                var html = $("#dataStatistics").html();
                var url = "${pageContext.request.contextPath}/lads/monitoring/dataStatistics";
                var data = {"ldId":"${requestScope.ld.uuid}"};
                if(html==null||html==""){
                    $("#dataStatistics").append("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>");
                    $("#dataStatistics").load(url,data);
                }
                $("#dataStatistics").show();
            }
        
            //学员记录
            function learnerRecord(){
                switchMonitorMap("learnerRecord");
                var html = $("#learnerRecord").html();
                var url = "${pageContext.request.contextPath}/lads/monitoring/learnerRecord";
                var data = {"ldId":"${requestScope.ld.uuid}"};
                if(html==null||html==""){
                    $("#learnerRecord").append("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>");
                    $("#learnerRecord").load(url,data);
                }
                $("#learnerRecord").show();
            }
            
            //课程环节
            function lads(){
                switchMonitorMap("lads");
                $("#lads").show();
            }
            
            function switchMonitorMap(flag){
                $(".btn").each(function(){
                    $(this).removeClass("btn2");
                });
                $("#"+flag+"Button").addClass("btn2");
                $("body > div[id!='"+flag+"']").each(function(){
                    $(this).hide();
                });
            }

            function changeTimeToString(DateIn){
                var Month=0;
                var Day=0;
                var Hour = 0;
                var Minute = 0;
                var CurrentDate="";
                //初始化时间
                Month     = DateIn.getMonth()+1;
                Day       = DateIn.getDate();
                Hour      = DateIn.getHours();
                Minute    = DateIn.getMinutes();

                if (Month >= 10 )
                {
                    CurrentDate = CurrentDate + Month + ".";
                }
                else
                {
                    CurrentDate = CurrentDate + "0" + Month + ".";
                }
                if (Day >= 10 )
                {
                    CurrentDate = CurrentDate + Day ;
                }
                else
                {
                    CurrentDate = CurrentDate + "0" + Day ;
                }

                if(Hour >=10)
                {
                    CurrentDate = CurrentDate + " " + Hour ;
                }
                else
                {
                    CurrentDate = CurrentDate + " 0" + Hour ;
                }
                if(Minute >=10)
                {
                    CurrentDate = CurrentDate + ":" + Minute ;
                }
                else
                {
                    CurrentDate = CurrentDate + ":0" + Minute ;
                }
                return CurrentDate;
            }
            $(function(){
                //监控模式
                getMonitorJson("${requestScope.ld.uuid}")
            })
        </script>
    </body>
</html>
