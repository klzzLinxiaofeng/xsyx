<%-- 
    Document   : studyPaper
    Created on : 2013-8-8, 10:47:16
    Author     : Administrator
--%>

<%@page import="com.gzxtjy.paper.constants.AnswerConstants"%>
<%@page import="com.gzxtjy.paper.constants.QuestionType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="paper" uri="/WEB-INF/paper" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
        <title><s:property value="#request.paper.title"/></title>
        <link rel="stylesheet" type="text/css" href="/css/common/paper/bag.css"/>
        <script type="text/javascript" src="/js/common/jquery-1.4.2.min.js"></script>
    </head>
    <body>
        <div class="main_content_2">
            <div class="left">
                <div class="shijuan">
                    <div class="title_2">
                        <p><s:property value="#request.paper.title"/></p>
<!--                        <div class="course">
                            <h1 class="i" style="width:10%"></h1>
                            <span>题目已完成2/15</span>
                        </div>-->
                    </div>
                    <div class="test">
                        <p></p>
                        <span></span>
                    </div>
                </div>
                <div class="paper">
                    <div id="paperDiv" ></div>
                    <div class="end">
                        <div class="e1">
                            <s:if test='#request.pos.split(",").length==1'>
                                <s:if test="#request.pos>1&&#request.pos<=#request.questionSize">
                                    <a href="/common/paper/paperAction_studyPaper.action?paperId=<s:property value="#request.paper.id"/>&pos=<s:property value="@java.lang.Integer@parseInt(#request.pos)-1"/>"><h1 class="pre"></h1></a> 
                                    </s:if>
                                    <s:if test="#request.pos<#request.questionSize">
                                    <a href="/common/paper/paperAction_studyPaper.action?paperId=<s:property value="#request.paper.id"/>&pos=<s:property value="@java.lang.Integer@parseInt(#request.pos)+1"/>"><h1 class="next"></h1></a> 
                                    </s:if>
                                </s:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right">
                <div class="time" style="background-color: #E6F9FD;">
                    <p id="paperTime"><span id="RemainH">00</span>:<span id="RemainM">00</span>:<span id="RemainS">00</span></p>
                </div>
                <div class="choose">
                    <ul>
                        <a href="/common/paper/paperAction_studyPaper.action?paperId=<s:property value="#request.paper.id"/>">
                            <li class="a1 " style="background-color: #FFFFFF;">
                                <div class="active">
                                    <p>多题模式</p>
                                </div>
                            </li>
                        </a>
                        <a href="#">
                            <li class="a2" style="background-color: #FFFFFF;">
                                <div>
                                    <p>暂停</p>
                                </div>
                            </li>
                        </a>
                        <a href="javascript:void(0)" onclick="saveResult()" >
                            <li class="a3" style="background-color: #FFFFFF;">
                                <div>
                                    <p>我要交卷</p>
                                </div>
                            </li>
                        </a>
                        <a href="javascript:void(0)" onclick="goToTop()" >
                            <li class="a4" style="background-color: #FFFFFF;">
                                <div>
                                    <p>回到顶部</p>
                                </div>
                            </li>
                        </a>
                    </ul>
                </div>
            </div>
            <div class="clear"></div>
            <div class="bottom">
                <a class="a" href="javascript:void(0)" onclick="$('.num').toggle();" ><div class="close"></div></a>
                <div class="num">
                    <ul>
                        <s:bean name="org.apache.struts2.util.Counter" id="counter">
                            <s:param name="first" value="1"></s:param>
                            <s:param name="last" value="#request.questionSize"></s:param>
                            <s:iterator>
                                <li class="active">
                                    <a href="/common/paper/paperAction_studyPaper.action?paperId=<s:property value="#request.paper.id"/>&pos=<s:property/>">
                                        <s:property/>
                                    </a>
                                </li>
                            </s:iterator>
                        </s:bean>
                    </ul>
                </div>
            </div>
        </div>	
    </body>
    <script type="text/javascript">
                            /*计时器*/
                            var startTime = new Date();
                            startTime.setFullYear(2013, 10, 15);
                            startTime.setHours(18);
                            startTime.setMinutes(30);
                            startTime.setSeconds(20);
                            startTime.setMilliseconds(999);
                            var EndTime = startTime.getTime();
                            function GetRTime() {
                                var NowTime = new Date();
                                var nMS = EndTime - NowTime.getTime();
                                var nD = Math.floor(nMS / (1000 * 60 * 60 * 24));
                                var nH = Math.floor(nMS / (1000 * 60 * 60)) % 24;
                                var nM = Math.floor(nMS / (1000 * 60)) % 60;
                                var nS = Math.floor(nMS / 1000) % 60;
                                if (nMS < 0) {
                                    $("#paperTime").hide();
                                    $("#daoend").show();
                                } else {
                                    $("#paperTime").show();
                                    $("#daoend").hide();
                                    $("#RemainD").text(nD);
                                    $("#RemainH").text(nH);
                                    $("#RemainM").text(nM);
                                    $("#RemainS").text(nS);
                                }
                            }

                            /*回到顶部功能*/
                            function goToTop() {
                                $('html, body').animate({scrollTop: $("body").offset().top}, "slow");
                            }

                            var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
                            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                            function createMainQuestion(ques, typeStr) {
                                var html = "<div class=\"problem\">"
                                        + "<span  class=\"pos\" >" + ques.pos + "</span>"
                                        + "<input type=\"hidden\" value=\"" + ques.type + "\" id=\"quesType_" + ques.questionId + "\"  ></input>"
                                        + "<input type=\"hidden\" value=\"" + ques.questionId + "\" id=\"quesId_" + ques.questionId + "\"  ></input>"
                                        + "<p><strong>【" + typeStr + "】</strong>" + ques.content + "</p>"
                                        + "</div>"
                                return html;
                            }
                            function chooseAnswer(obj, qType) {
                                var answerClass = $(obj).attr("class");
                                if (qType == "<%=QuestionType.RADIO%>") {
                                    $(obj).parent().children().each(function() {
                                        $(this).attr("class", "i1")
                                    });
                                } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                }
                                if (answerClass == "i1") {
                                    $(obj).attr("class", "i1 active")
                                } else {
                                    $(obj).attr("class", "i1");
                                }
                            }
                            function createRadio(paperDiv, ques) {
                                var answerHtml = "";
                                if (ques.answer != null && ques.answer.length > 0) {
                                    var answer = ques.answer;
                                    var userAnswer = ques.userAnswer;
                                    //var split = answer.split(splitFlag);
                                    for (var i = 0; i < answer.length; i++) {
                                        var userCheck = userAnswer == letters[i] ? "i1 active" : "i1";
                                        answerHtml = answerHtml + "<li onclick=\"chooseAnswer(this,'<%=QuestionType.RADIO%>')\" class=\"" + userCheck + "\">"
                                                + " <a href=\"javascript:void(0)\"  >"
                                                + "<span class=\"option1\"></span>"
                                                + "<p>" + letters[i] + "</p>"
                                                + answer[i]
                                                + "</a></li>";
                                    }
                                }
                                var html = "<div class=\"option1\" >"
                                        + createMainQuestion(ques, "单选题", "<%=QuestionType.RADIO%>")
                                        + "<div class=\"option\"><ul>"
                                        + answerHtml
                                        + "</ul></div></div>"
                                paperDiv.append(html);
                            }
                            function createWord(paperDiv, ques) {
                                var userAnswer = ques.userAnswer != null ? ques.userAnswer : "";
                                var html = "<div class=\"answer1\" >"
                                        + createMainQuestion(ques, "简答题", "<%=QuestionType.WORD%>")
                                        + "<div class=\"answer\">"
                                        + "<textarea cols=\"90\" class=\"result\">" + userAnswer + "</textarea>"
                                        // + "<div class=\"refer\"><input type=\"button\" value=\"提交\" id=\"commentBtn\"></div>"
                                        + "</div></div>"
                                paperDiv.append(html);
                            }
                            function createCheckbox(paperDiv, ques) {
                                var answerHtml = "";
                                if (ques.answer != null && ques.answer.length > 0) {
                                    var answer = ques.answer;
                                    for (var i = 0; i < answer.length; i++) {
                                        var userCheck = "i1";
                                        if ((ques.userAnswer != null && ques.userAnswer.length > 0)) {
                                            for (var h = 0; h < ques.userAnswer.length; h++) {
                                                if (ques.userAnswer[h] == letters[i]) {
                                                    userCheck = "i1 active";
                                                    break;
                                                }
                                            }
                                        }
                                        answerHtml = answerHtml + "<li onclick=\"chooseAnswer(this,'<%=QuestionType.CHECKBOX%>')\" class=\"" + userCheck + "\">"
                                                + " <a href=\"javascript:void(0)\"  >"
                                                + "<span class=\"option1\"></span>"
                                                + "<p>" + letters[i] + "</p>"
                                                + answer[i]
                                                + "</a></li>";
                                    }
                                }
                                var html = "<div class=\"option1\" >"
                                        + createMainQuestion(ques, "多选题", "<%=QuestionType.CHECKBOX%>")
                                        + "<div class=\"option\"><ul>"
                                        + answerHtml
                                        + "</ul></div></div>"
                                paperDiv.append(html);
                            }
                            function createBlank(paperDiv, ques) {
                                var html = "<div class=\"filling1\" >"
                                        + createMainQuestion(ques, "填空题", "<%=QuestionType.BLANK%>")
                                        + "<div class=\"filling\">"
                                        + "<div id=\"blankAnswer_" + ques.pos + "\" class=\"gap\">" + ques.answer + "</div>"
                                        //+ "<div class=\"refer\"><input type=\"button\" value=\"提交\" id=\"commentBtn\"></div>"
                                        + "</div></div>"
                                paperDiv.append(html);
                                //把用户已做过的答案添加到空格区
                                if (ques.userAnswer != null) {
                                    var inputList = $("div[id='blankAnswer_" + ques.pos + "']").find("input[type='text']");
                                    inputList.each(function(x) {
                                        if (ques.userAnswer != null&&ques.userAnswer.length>0) {
                                            $(this).val(ques.userAnswer[x]);
                                        }
                                    });
                                }
                            }
                            function createQuestion(ques) {
                                var qType = ques.type;
                                var paperDiv = $("#paperDiv");
                                if (qType == "<%=QuestionType.RADIO%>") {
                                    createRadio(paperDiv, ques);
                                } else if (qType == "<%=QuestionType.WORD%>") {
                                    createWord(paperDiv, ques);
                                } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                    createCheckbox(paperDiv, ques);
                                } else if (qType == "<%=QuestionType.BLANK%>") {
                                    createBlank(paperDiv, ques);
                                }
                            }
                            function  loadQuestionJson(paperId, pos, userId) {
                                $.ajax({
                                    url: "/common/paper/paperAction_loadPaperQuestionJson.action",
                                    type: "POST",
                                    data: {"paperId": paperId, "pos": pos, "userId": userId},
                                    dataType: "json",
                                    async: false,
                                    success: function(json) {
                                        var questions = json.questions;
                                        for (var i = 0; i < questions.length; i++) {
                                            var question = questions[i];
                                            createQuestion(question);
                                        }
                                        //启动计时器
                                        var timer_rt = window.setInterval("GetRTime()", 1000);
                                    }
                                });
                            }

                            function getResultJson() {
                                var json = {};
                                var questions = [];
                                var questionList = $("#paperDiv").children();
                                json["paperId"] = "<s:property value="#request.paper.id"/>";
                                json["userId"] = "<paper:getStudyUser/>";
                                for (var i = 0; i < questionList.length; i++) {
                                    var qJson = {};
                                    var question = $(questionList[i]);
                                    var qType = question.find("input[id^='quesType']").val();
                                    var qId = question.find("input[id^='quesId']").val();
                                    var userAnswer;
                                    if (qType == "<%=QuestionType.RADIO%>") {
                                        userAnswer = $.trim(question.find(".option li[class='i1 active'] p").text());
                                    } else if (qType == "<%=QuestionType.WORD%>") {
                                        userAnswer = $.trim(question.find("textarea").val());
                                    } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                        var pList = question.find(".option li[class='i1 active'] p");
                                        userAnswer = []
                                        for (var h = 0; h < pList.length; h++) {
                                            var lePoint = $.trim($(pList[h]).text());
                                            userAnswer.push(lePoint);
                                        }
                                    } else if (qType == "<%=QuestionType.BLANK%>") {
                                        var blankList = question.find("input[type='text']");
                                        blankList.each(function(o) {
                                            userAnswer.push($.trim($(this).val()));
                                        });
                                    }
                                    qJson["questionId"] = qId;
                                    qJson["userAnswer"] = userAnswer;
                                    qJson["type"] = qType;
                                    questions.push(qJson);
                                }
                                json["questions"] = questions;
                                return json;
                            }

                            function saveResult(final) {
                                var json = getResultJson();
                                if (final != null && final == true) {
                                    json["finalAnswer"] = "<%=AnswerConstants.FINAL_ANSWER%>";
                                }
                                $.ajax({
                                    url: "/common/paper/paperAction_saveResult.action",
                                    type: "POST",
                                    data: {"resultJson": JSON.stringify(json)},
                                    async: false,
                                    success: function(result) {
                                        if (result == "success") {
                                            alert("交卷成功");
                                        } else {
                                            alert("交卷失败")
                                        }
                                    }
                                });
                            }

                            function endsWith(str, suffix) {
                                return str.indexOf(suffix, str.length - suffix.length) !== -1;
                            }


                            $(function() {
//                                window.onbeforeunload = function() {
//                                     
//                                }

                                loadQuestionJson("<s:property value="#request.paper.id"/>", "<s:property value="#request.pos"/>", "<paper:getStudyUser/>")
                            });
    </script>
</html>
