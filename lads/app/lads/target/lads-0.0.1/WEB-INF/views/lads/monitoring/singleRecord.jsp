<%@page import="platform.education.lads.contants.FinishedStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<div class="module">
    <div class="m_title">
        <h3 class="grey">学员:
            <span class="stare">
                ${requestScope.userName}
            </span>
            的学习进度
        </h3>
        <a href="javascript:void(0)" onclick="backToLearnerRecord()" class="btn">返回</a>
        <div class="clear"></div>
    </div>
    <div class="m_content">
        <table class="m_content_table">
            <thead>
                <tr>
                    <th width='54%'>活动名称</th>
                    <th width='23%'>完成情况</th>
                    <th width='13%'>分数</th>
                    <th width='13%'>得分</th>
                </tr>
            </thead>
            <tbody id="scoreTable">
                <tr>
                    <td class='fc'>活动总数：<span id="totalActs">0</span>个</td>
                    <td>
                        <img id="finishedImg" src="${pageContext.request.contextPath}/lads/monitoring/finishedImage?percent=0"/>
                    </td>
                    <td>总分：<span class="total-score"></span></td>
                    <td><span class="user-score" style="color: red;font-size: 40px" ></span></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function backToLearnerRecord() {
        $("#learnerRecord").show();
        $("#singleRecord").hide();
    }

    function SingleRecordActivity(actJson) {
        var at = ""
        var tn = "";
        var ti = "";
        var init = function () {
            at = actJson.activityType;
        }
        this.createGroup = function (actJson) {
            var random = actJson.id;
            ti = actJson.groupTitle;
            var subActivitys = actJson.subActivitys;
            var rowspan = 1;
            if (subActivitys != null) {
                rowspan = subActivitys.length + 1;
            }
            var html = "<tr class=\"t_item_title\">"
                    + "<td class=\"fc line\">"
                    + ti
                    + "</td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "</tr>"
            $("#scoreTable").append(html);
            if (subActivitys != null) {
                for (var i = 0; i < subActivitys.length; i++) {
                    this.createTool(subActivitys[i], "group_" + random);
                }
            }
            return html;
        }
        this.createTool = function (actJson, groupId) {
            var random = actJson.id;
            var userScore = actJson.userScore;
            var finished = actJson.finished;
            if ((userScore == null || userScore == "")) {
                userScore = "0";
            }
            tn = actJson.toolName;
            var taught = actJson.taught;
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
                className = "powerpoint";
                ti = actJson.powerPointTitle;
                score = actJson.powerPointScore;
            } else if (tn == "MediaTool") {
                className = "media";
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
            if (score == null || score == "") {
                score = "0";
            }
            var finishedHtml = "";
            if (finished != null && finished ==<%=FinishedStatus.FINISHED%>) {
                finishedHtml = "<a class=\"t_item_sign\"><img src =\"${pageContext.request.contextPath}/res/images/common/lads/finished.png\"/></a>";
            } else {
                finishedHtml = "<a class=\"t_item_sign\"><img src =\"${pageContext.request.contextPath}/res/images/common/lads/unfinished.png\"/></a>";
            }
            var html = "<tr>"
                    + "<td class='fc line'><span class='.lads .side .list a span.study'></span>" + ti + "</td>"
                    + "<td class='line'>" + finishedHtml + "</td>"
                    + "<td name=\"totalScore\">"
                    + score
                    + "</td>"
                    + "<td name=\"userScore\">" + userScore + "</td>"
                    + "</tr>";
            $("#scoreTable").append(html);
            $("#totalActs").text(parseInt($("#totalActs").text()) + 1);
            return html;
        }
        this.createButton = function () {
            if (at == "tool") {
                this.createTool(actJson);
            } else if (at == "group") {
                this.createGroup(actJson);
            }
        }
        init();
    }
    function loadSingleRecordActivitys(json) {
        var activitys = json.activitys;
        for (var i = 0; i < activitys.length; i++) {
            var act = new SingleRecordActivity(activitys[i]);
            //创建活动
            act.createButton();
        }
    }
    function getSingleRecordMonitoringJson(ldId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/lads/monitoring/getMonitorJson",
            type: "POST",
            dataType: "json",
            data: {"ldId": ldId, "studyUserId": "${requestScope.userId}"},
            async: false,
            success: function (json) {
                loadSingleRecordActivitys(json);
            }
        });
    }

    function updateSingleFinished() {
        var finished = $("#scoreTable .t_item_sign img[src*='/finished.png']").size();
        var size = 0;
        $("#scoreTable tr[class!='t_item_title']").each(function (i) {
            if (i != 0) {
                size++;
            }
        });
        var all = size;
        var percent = Math.round((finished / all) * 100);
        $("#finishedImg").attr("src", "${pageContext.request.contextPath}/lads/monitoring/finishedImage?percent=" + percent);
        if (percent < 100) {
            $("#finishedImg").parent().append("<img src =\"${pageContext.request.contextPath}/res/images/common/lads/unfinished.png\"/>");
        } else {
            $("#finishedImg").parent().append("<img src =\"${pageContext.request.contextPath}/res/images/common/lads/finished.png\"/>");
        }

    }

    function updateSingleTotalScore() {
        var total = 0;
        var user = 0;
        $("td[name='totalScore']").each(function () {
            total = total + parseInt($.trim($(this).text()));
        });
        $(".total-score").text(total);
        $("td[name='userScore']").each(function () {
            user = user + parseInt($.trim($(this).text()));
        });
        $(".user-score").text(user);
    }

    $(function () {
        getSingleRecordMonitoringJson($("#ldId").val());
        updateSingleTotalScore();
        updateSingleFinished();
    })
</script>