<%@page import="platform.education.paper.vo.SessionTempPaperVo"%>
<%@page import="platform.education.paper.constants.QuestionConstants"%>
<%@page import="platform.education.paper.constants.QuestionType"%>
<%@page import="platform.education.paper.constants.AnswerConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="paper" uri="http://www.jiaoxueyun.com/paper" %>
<!DOCTYPE html>
<%
    SessionTempPaperVo paperVo = (SessionTempPaperVo) request.getSession().getAttribute(QuestionConstants.SESSION_PAPER);
    String questionIds = "[]";
    if (paperVo != null) {
        questionIds = paperVo.getQuestionIds();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
        <title>
            <c:choose>
                <c:when test="${'true' eq requestScope.preivew}">
                    ${(sessionScope.paper_temp_paper_vo.title==null||'' eq sessionScope.paper_temp_paper_vo.title)?'预览':sessionScope.paper_temp_paper_vo.title}
                </c:when>
                <c:when test="${'true' eq requestScope.buildUp}">
                    ${requestScope.paper.title==null?'预览':requestScope.paper.title}
                </c:when>
                <c:otherwise>
                    ${requestScope.paper.title}
                </c:otherwise>
            </c:choose>
        </title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paper/learning/style.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.4.2.min.js"></script>
    </head>
    <body>
        <div class="question_check">
            <div class="top">
                <!--              <a href="javascript:void(0);" class="collect">收藏</a>
                              <a href="javascript:void(0);" class="error">报错</a>-->
            </div>
            <div class="left">
                <div id="paperDiv">

                    <!--                                <div class="question_title">题目预览</div>
                                                    <div class="l1">下列根式中，是最简二次根式的是（）</div>
                                                    <div class="option">
                                                        <a href="#"><b class="right"></b><p><input type="radio">1</p></a>
                                                        <a href="#"><p><input type="radio">2</p></a>
                                                        <a href="#"><p><input type="radio">3</p></a>
                                                        <a href="#"><p><input type="radio">4</p></a>
                                                    </div>	-->
                </div>
                <div class="l2">
                    <!--                    <a class="d1" href="javascript:void(0)">答题</a>-->
                    <!--                    <a class="d2" href="javascript:void(0)">查看解析</a>-->
                    <c:if test="${requestScope.pos>1&&requestScope.pos<=requestScope.questionSize}">
                        <a class="d3" onclick="locateQuestion('${requestScope.paper.id}', '${requestScope.pos-1}')" href="javascript:void(0)">上一题</a> 
                    </c:if>
                    <c:if test="${requestScope.pos<requestScope.questionSize}">
                        <a class="d4" onclick="locateQuestion('${requestScope.paper.id}', '${requestScope.pos+1}')" href="javascript:void(0)">下一题</a>  
                    </c:if>
                    <c:if test="${requestScope.pos==requestScope.questionSize}">
                        <a class="d4" href="${pageContext.request.contextPath}/common/paper/paperAction_confirmAnswer.action?paperId=${requestScope.paper.id}">交&nbsp;&nbsp;卷</a> 
                    </c:if> 
                </div>
            </div>
            <!--            <div class="right">
                            <div class="bd">
                                <p >
                                    <a href="#" target="_blank">罗志明 </a><span class="tail-info ml5"> 分享于：2011年12月14日</span>
                                </p>
                                <div class="value-box clearfix" >
                                    <div style="" class="value-msg" >
                                        <span class="doc-rate" > <span class="score">5.0</span> <span class="small-rating"><span style="width: 100%;" id="starScore"></span></span>
                                        </span>
                                        <div class="value-num">
                                            (<b id="docValueCount-2">115</b>人评星)
                                        </div>
                                    </div>
                                    <div class="value-msg"  style="display: none;">
                                        <span style="display: block" class="no-value-tip">暂无用户评价</span>
                                    </div>
                                    <div class="value-btn" style="">
                                        <a href="javaScript:;" hidefocus="true"  class="enable" alog-action="view.userEvaluation"><b class="ic ic-value"></b>我要评价</a>
                                    </div>
                                </div>
                            </div>
                            <div class="fixing-box" id="fixed-box">
                                <div class="mod page-doc-list relate-doc pt20">
                                    <div class="inner">
                                        <div class="hd clearfix">
                                            <h4>
                                                <a href="#" target="_blank">相关资源推荐</a>
                                            </h4>
                                        </div>
                                        <div class="bd">
                                            <ul class="tabContent">
                                                <li>
                                                    <p class="doc-title">
                                                        <a title="《2 走一步，再走一步》课件" href="javascript:void(0)" target="_blank" class="logSend">《2 走一步，再走一步》课件 </a>
                                                    </p>
                                                    <div>
                                                        <span style="display: inline-block; color: #888888; padding-right: 5px;" class="no-value-tip">暂无用户评价</span>
                                                        <div style="width: 41%" class="gd-g-u gd-u-1-4">
                                                            正确率：60%
                                                        </div>
                                                    </div>
                                                </li>
            
                                                <li>
                                                    <p class="doc-title">
                                                        <a title="《2 走一步，再走一步》课件" href="javascript:void(0)" target="_blank" class="logSend">《2 走一步，再走一步》课件 </a>
                                                    </p>
                                                    <div>
                                                        <span style="display: inline-block; color: #888888; padding-right: 5px;" class="no-value-tip">暂无用户评价</span>
                                                        <div style="width: 41%" class="gd-g-u gd-u-1-4">
                                                            正确率：60%
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <p class="doc-title">
                                                        <a title="《2 走一步，再走一步》课件" href="javascript:void(0)" target="_blank" class="logSend">《2 走一步，再走一步》课件 </a>
                                                    </p>
                                                    <div>
                                                        <span style="display: inline-block; color: #888888; padding-right: 5px;" class="no-value-tip">暂无用户评价</span>
                                                        <div style="width: 41%" class="gd-g-u gd-u-1-4">
                                                            正确率：60%
                                                        </div>
                                                    </div>
                                                </li>
            
                                                <li>
                                                    <p class="doc-title">
                                                        <a title="《2 走一步，再走一步》课件" href="javascript:void(0)" target="_blank" class="logSend">《2 走一步，再走一步》课件 </a>
                                                    </p>
                                                    <div>
                                                        <span class="small-rating"> <span style="width: 80.0%"></span></span>
                                                        <div style="width: 41%" class="gd-g-u gd-u-1-4">
                                                            正确率：60%
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <p class="doc-title">
                                                        <a title="《2 走一步，再走一步》课件" href="javascript:void(0)" target="_blank" class="logSend">《2 走一步，再走一步》课件 </a>
                                                    </p>
                                                    <div>
                                                        <span style="display: inline-block; color: #888888; padding-right: 5px;" class="no-value-tip">暂无用户评价</span>
                                                        <div style="width: 41%" class="gd-g-u gd-u-1-4">
                                                            正确率：60%
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>-->
            <div class="clear"></div>
        </div>
        <!--        <div class="comment">
                    <div class="tab">
                        <ul>
                            <li class="cur"><a href="#">评论（20）</a></li>
                                                        <li class="cur"><a href="#">您未登陆</a></li> 
                            <li><a href="#">评分（10）</a></li>
                        </ul>
                    </div>
                    <div class="comment-area">
                        <div class="comment-write">
                            <div class="jqte">
                                <textarea style="border: 1px solid grey" cols="115" rows="5" id="content1"></textarea>
                            </div>
                            <div class="comment-submit">
                                <span>可以输入<em id="title-counter">1-200</em>个字符
                                </span> <input type="button" onclick="comment();" id="commentBtn" value="评论">
                            </div>
                        </div>
        
        
                        <div class="comment-title">
                            所有评论(
                            1
                            )
                        </div>
                    </div>
                    <div class="comment-list">
                        <div class="comment-item clearfix">
                            <div class="comment-img">
                                <img onerror="this.src='/image/login/noPhoto.jpg'" alt="头像" src="/image/login/noPhoto.jpg">
                            </div>
                            <div class="comment-content">
                                <h4>
                                    <span class="name">林晓鸿</span>
                                    2天前
                                    <span class="floor">1楼 </span>
                                </h4>
                                <div style="word-break: break-all;">
                                    测试下
                                </div>
                            </div>
                        </div>
                        <div class="comment-item clearfix">
                            <div class="comment-img">
                                <img onerror="this.src='/image/login/noPhoto.jpg'" alt="头像" src="/image/login/noPhoto.jpg">
                            </div>
                            <div class="comment-content">
                                <h4>
                                    <span class="name">林晓鸿</span>
                                    2天前
                                    <span class="floor">2楼 </span>
                                </h4>
                                <div style="word-break: break-all;">
                                    测试下
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->

        <!--        <div class="gotop">
                    <a class="top" href="javascript:void();" onclick="goTop();"></a>
                    <a><div class="number_2"><p>15</p></div></a>
                </div>-->
        <footer> 
           <%-- <jsp:include page="/module/teacher/foot.jsp"></jsp:include>--%>
        </body>
    </html>
    <script type="text/javascript">
                                var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
                                var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                                //该变量用于表现预览模式
                                var previewMode = false;
                                //该变量用于表现组卷模式
                                var buildUpMode = false;
                                var questionIds = <%=questionIds%>;
                                /*回到顶部功能*/
                                function goToTop() {
                                    $('html, body').animate({scrollTop: $("body").offset().top}, "slow");
                                }

                                function createMainQuestion(ques, typeStr) {
                                    var html = "<div class=\"l1\">" + ques.pos + ".&nbsp;&nbsp;" + ques.content + "</div>";
                                    return html;
                                }

                                function createExpArea(json) {
                                    var html = "<div class=\"answer\">"
                                            + "<div>"
                                            + "<p class='p1'>来&nbsp;&nbsp;&nbsp;源</p>"
                                            + "<a href=\"#\">${requestScope.paper.title}</a><span>&nbsp;&nbsp;第${requestScope.pos}题</span>"
                                            + "</div>"
                                            + "<div>"
//                            + "<p>全站统计</p>"
//                            + "<span>本题共被作答699次  正确60%</span>"
                                            + "</div>"
                                            + "<div>"
                                            + "<p class='p1'>解&nbsp;&nbsp;&nbsp;析</p>"
                                            + "<div class=\'explain\' id=\"exp_" + json.questionId + "\"></div>"
                                            + "<div class=\'clear\'></div>"
                                            + "</div>"
                                            + "<div>"
//                            + "<p>知&nbsp;识&nbsp;点</p>"
//                            + "<span>一元二次方程</span>"
                                            + "</div>"
                                            + "</div>";
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
                                        var correctAnswer = (ques.correctAnswer != null && ques.correctAnswer.length == 1) ? ques.correctAnswer[0] : "";
                                        for (var i = 0; i < answer.length; i++) {
                                            var userCheck = userAnswer == letters[i] ? "checked" : "";
                                            var correctCheck = correctAnswer == letters[i] ? "<b class=\"right\" style=\"top:15px\"></b>" : "";
                                            answerHtml = answerHtml + "<a href=\"javascript:void(0)\" style=\"display:block;\">" + correctCheck + "<p style=\"width: 55px; float: left;\">"
                                                    + "<input class=\"radio\" name=\"radioAnswer_" + ques.pos + "\" " + userCheck + "  type=\"radio\">"
                                                    + letters[i] + "&nbsp;&nbsp;&nbsp;&nbsp;" + "</p><div style=\" float: left; width: 695px;\">"+answer[i] + "</div><div class=\"clearfix\"></div></a>"
                                        }
                                    }
                                    var html = createMainQuestion(ques, "单选题", "<%=QuestionType.RADIO%>")
                                            + "<div class=\"option\">"
                                            + answerHtml
                                            + "</div>"
                                            + createExpArea(ques);
                                    paperDiv.append(html);
                                    var exp = (ques.explanation == "" || ques.explanation == null) ? "无" : ques.explanation
                                    $("#exp_" + ques.questionId).html(exp)
                                }
                                function createTrueOrFalse(paperDiv, ques) {
                                    var answerHtml = "";
                                    if (ques.answer != null && ques.answer.length > 0) {
                                        var answer = ques.answer;
                                        var userAnswer = ques.userAnswer;
                                        var correctAnswer = (ques.correctAnswer != null && ques.correctAnswer.length == 1) ? ques.correctAnswer[0] : "";
                                        for (var i = 0; i < answer.length; i++) {
                                            var userCheck = userAnswer == letters[i] ? "checked" : "";
                                            var correctCheck = correctAnswer == letters[i] ? "<b class=\"right\"></b>" : "";
                                            answerHtml = answerHtml + "<a href=\"#\">" + correctCheck + "<p>"
                                                    + "<input name=\"trueOrFalseAnswer_" + ques.pos + "\" " + userCheck + " class=\"radio\" type=\"radio\">"
                                                    + letters[i] + "&nbsp;&nbsp;&nbsp;&nbsp;" + answer[i] + "</p></a>"
                                        }
                                    }
                                    var html = createMainQuestion(ques, "判断题", "<%=QuestionType.TRUEORFALSE%>")
                                            + "<div class=\"option\">"
                                            + answerHtml
                                            + "</div>"
                                            + createExpArea(ques);
                                    paperDiv.append(html);
                                    var exp = (ques.explanation == "" || ques.explanation == null) ? "无" : ques.explanation
                                    $("#exp_" + ques.questionId).html(exp)
                                }
                                function createWord(paperDiv, ques) {
                                    var userAnswer = ques.userAnswer != null ? ques.userAnswer : "";
                                    var html = createMainQuestion(ques, "简答题", "<%=QuestionType.WORD%>")
                                            + "<div class=\"option\">"
                                            + "<textarea cols=\"90\" rows=\"12\" class=\"result\">" + userAnswer + "</textarea>"
                                            // + "<div class=\"refer\"><input type=\"button\" value=\"提交\" id=\"commentBtn\"></div>"
                                            + "</div>"
                                            + createExpArea(ques);
                                    paperDiv.append(html);
                                    var exp = (ques.explanation == "" || ques.explanation == null) ? "无" : ques.explanation
                                    $("#exp_" + ques.questionId).html(exp)
                                }
                                function createCheckbox(paperDiv, ques) {
                                    var answerHtml = "";
                                    if (ques.answer != null && ques.answer.length > 0) {
                                        var answer = ques.answer;
                                        for (var i = 0; i < answer.length; i++) {
                                            var userCheck = "";
                                            var correctCheck = "";
                                            if (ques.correctAnswer != null && ques.correctAnswer.length > 0) {
                                                for (var h = 0; h < ques.correctAnswer.length; h++) {
                                                    if (ques.correctAnswer[h] == letters[i]) {
                                                        correctCheck = "<b class=\"right\"></b>";
                                                        break;
                                                    }
                                                }
                                            }
                                            if ((ques.userAnswer != null && ques.userAnswer.length > 0)) {
                                                for (var h = 0; h < ques.userAnswer.length; h++) {
                                                    if (ques.userAnswer[h] == letters[i]) {
                                                        userCheck = "checked";
                                                        break;
                                                    }
                                                }
                                            }
                                            answerHtml = answerHtml + "<a href=\"javascript:void(0)\">" + correctCheck + "<p>"
                                                    + "<input name=\"radioAnswer_" + ques.pos + "\" " + userCheck + " class=\"checkbox\" type=\"checkbox\">"
                                                    + letters[i] + "&nbsp;&nbsp;&nbsp;&nbsp;" + answer[i] + "</p></a>"
                                        }
                                    }
                                    var html = createMainQuestion(ques, "多选题", "<%=QuestionType.CHECKBOX%>")
                                            + "<div class=\"option\">"
                                            + answerHtml
                                            + "</div>"
                                            + createExpArea(ques);
                                    paperDiv.append(html);
                                    var exp = (ques.explanation == "" || ques.explanation == null) ? "无" : ques.explanation
                                    $("#exp_" + ques.questionId).html(exp)
                                }
                                function createBlank(paperDiv, ques) {
                                    var html = createMainQuestion(ques, "填空题", "<%=QuestionType.BLANK%>")
                                            + "<div id=\"blankAnswer_" + ques.pos + "\" class=\"option\">" + ques.answer + "</div>"
                                            + createExpArea(ques);
                                    paperDiv.append(html);
                                    var exp = (ques.explanation == "" || ques.explanation == null) ? "无" : ques.explanation
                                    $("#exp_" + ques.questionId).html(exp)
                                    //把用户已做过的答案添加到空格区
                                    if (ques.userAnswer != null) {
                                        var inputList = $("div[id='blankAnswer_" + ques.pos + "']").find("input[type='text']");
                                        inputList.each(function(x) {
                                            if (ques.userAnswer != null && ques.userAnswer.length > 0) {
                                                $(this).val(ques.userAnswer[x]);
                                            }
                                        });
                                    }
                                }

                                function createComplex(paperDiv, ques) {
                                    var html = createMainQuestion(ques, "复合题", "<%=QuestionType.COMPLEX%>");
                                    paperDiv.append(html);
                                    if (ques.questions != null && ques.questions.length > 0) {
                                        for (var i = 0; i < ques.questions.length; i++) {
                                            var sub = ques.questions[i];
                                            var qType = sub.type;
                                            if (qType == "<%=QuestionType.RADIO%>") {
                                                createRadio(paperDiv, sub);
                                            } else if (qType == "<%=QuestionType.WORD%>") {
                                                createWord(paperDiv, sub);
                                            } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                                createCheckbox(paperDiv, sub);
                                            } else if (qType == "<%=QuestionType.BLANK%>") {
                                                createBlank(paperDiv, sub);
                                            } else if (qType == "<%=QuestionType.TRUEORFALSE%>") {
                                                createTrueOrFalse(paperDiv, sub);
                                            }
                                        }
                                    }
                                }

                                function createQuestion(ques) {
                                    var qType = ques.type;
                                    var paperDiv = $("#paperDiv");
                                    var title = "";
                                    if (previewMode) {
                                        title = "${(sessionScope.paper_temp_paper_vo.title==null||'' eq sessionScope.paper_temp_paper_vo.title)?'预览':sessionScope.paper_temp_paper_vo.title}";
                                    } else {
                                        title = "${requestScope.paper.title}";
                                    }
                                    var groupTitle = (ques.groupTitle != null && ques.groupTitle != "") ? "&nbsp;-&nbsp;" + ques.groupTitle : "";
                                    paperDiv.append("<div class=\"question_title\">" + title + groupTitle + "</div>");
                                    if (buildUpMode) {
                                        var outHtml = "";
                                        var joinHtml = "";
                                        if (questionIds != "") {
                                            if (questionIds.indexOf(ques.questionId) != -1) {
                                                joinHtml = "style=\"display:none\"";
                                            } else {
                                                outHtml = "style=\"display:none\"";
                                            }
                                        } else {
                                            outHtml = "style=\"display:none\"";
                                        }
                                        $(".top").append("<a class=\"add\" onclick=\"buildUpQuestion('" + ques.questionId + "')\" href=\"javascript:void(0)\" " + joinHtml + " >加入组卷</a>"
                                                + "<a class=\"out\" onclick=\"removeQuestion('" + ques.questionId + "')\" href=\"javascript:void(0)\" " + outHtml + " >移出组卷</a>");
                                    }
                                    if (qType == "<%=QuestionType.RADIO%>") {
                                        createRadio(paperDiv, ques);
                                    } else if (qType == "<%=QuestionType.WORD%>") {
                                        createWord(paperDiv, ques);
                                    } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                        createCheckbox(paperDiv, ques);
                                    } else if (qType == "<%=QuestionType.BLANK%>") {
                                        createBlank(paperDiv, ques);
                                    } else if (qType == "<%=QuestionType.TRUEORFALSE%>") {
                                        createTrueOrFalse(paperDiv, ques);
                                    } else if (qType == "<%=QuestionType.COMPLEX%>") {
                                        createComplex(paperDiv, ques);
                                    }
                                }

                                function loadPaperQuestionJson(paperId, pos, userId) {
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/common/paper/paperAction_loadPaperQuestionListJson.action",
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
                                        }
                                    });
                                }

                                function loadQuestionJson(questionId, paperId) {
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/common/paper/paperAction_loadQuestionListJson.action",
                                        type: "POST",
                                        data: {"questionId": questionId, "paperId": paperId},
                                        dataType: "json",
                                        async: false,
                                        success: function(json) {
                                            var questions = json.questions;
                                            for (var i = 0; i < questions.length; i++) {
                                                var question = questions[i];
                                                createQuestion(question);
                                            }
                                        }
                                    });
                                }

                                function loadTempJson(pos) {
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/common/paper/paperAction_buildUpQuestion.action",
                                        type: "POST",
                                        data: {"pos": pos},
                                        dataType: "json",
                                        async: false,
                                        success: function(json) {
                                            var questions = json.questions;
                                            var questionSize = json.questionSize;
                                            pos = parseInt(pos);
                                            var html = "";
                                            if (pos > 1 && pos <= questionSize) {
                                                html = "<a class=\"d3\" href=\"${pageContext.request.contextPath}/common/paper/paperAction_studyPaper.action?preview=true&pos=" + (pos - 1) + "\">上一题</a>";
                                            }
                                            if (pos < questionSize) {
                                                html = html + "<a class=\"d4\" href=\"${pageContext.request.contextPath}/common/paper/paperAction_studyPaper.action?preview=true&pos=" + (pos + 1) + "\">下一题</a>"
                                            }
                                            $(".l2").html(html);
                                            for (var i = 0; i < questions.length; i++) {
                                                var question = questions[i];
                                                createQuestion(question);
                                            }
                                        }
                                    });
                                }

                                function buildUpQuestion(quesId) {
                                    var tempJson = {};
                                    if ($.inArray(quesId, questionIds) == -1) {
                                        questionIds.push(quesId);
                                    }
                                    tempJson["questionIds"] = questionIds;
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/common/paper/paperAction_saveTempPaper.action",
                                        type: "POST",
                                        data: {"tempJson": JSON.stringify(tempJson)},
                                        async: true,
                                        success: function(msg) {
                                            $(".top a[class='add']").hide();
                                            $(".top a[class='out']").show();
                                        }
                                    });
                                }

                                function removeQuestion(quesId) {
                                    var tempJson = {};
                                    if ($.inArray(quesId, questionIds) != -1) {
                                        questionIds = $.grep(questionIds, function(cur, i) {
                                            return cur != quesId;
                                        });
                                    }
                                    tempJson["questionIds"] = questionIds;
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/common/paper/paperAction_saveTempPaper.action",
                                        type: "POST",
                                        data: {"tempJson": JSON.stringify(tempJson)},
                                        async: true,
                                        success: function(msg) {
                                            $(".top a[class='add']").show();
                                            $(".top a[class='out']").hide();
                                        }
                                    });
                                }

                                function getResultJson() {
                                    var json = {};
                                    var questions = [];
                                    var questionList = $("#paperDiv").children();
                                    json["paperId"] = "${requestScope.paper.id}";
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
                                        } else if (qType == "<%=QuestionType.TRUEORFALSE%>") {
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
                                function getCurrentUrlParam(name) {
                                    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
                                    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
                                    if (r != null)
                                        return unescape(r[2]);
                                    return null; //返回参数值
                                }
                                
                                function locateQuestion(paperId, pos) {
                                    if (!previewMode) {
                                        saveResult();
                                    }
                                    var param = "";
                                    var preview = getCurrentUrlParam("preview");
                                    if(preview!=null){
                                        param = param+"&preview=true";
                                    }
                                    var buildUp = getCurrentUrlParam("buildUp");
                                    if(buildUp!=null){
                                         param = param+"&buildUp=true";
                                    }
                                    var info = getCurrentUrlParam("info");
                                    if(info!=null){
                                        param = param+"&info=true";
                                    }
                                    window.open("${pageContext.request.contextPath}/common/paper/paperAction_studyPaper.action?paperId=" + paperId + "&pos=" + pos+param, "_self");
                                }

                                function saveResult(final) {
                                    var json = getResultJson();
                                    if (final != null && final == true) {
                                        json["finalAnswer"] = "<%=AnswerConstants.FINAL_ANSWER%>";
                                    }
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/common/paper/paperAction_saveResult.action",
                                        type: "POST",
                                        data: {"resultJson": JSON.stringify(json)},
                                        async: false,
                                        success: function(result) {

                                        }
                                    });
                                }

                                function endsWith(str, suffix) {
                                    return str.indexOf(suffix, str.length - suffix.length) !== -1;
                                }


                                $(function() {
    <c:choose>
        <c:when test="${'true' eq requestScope.preview}">
            //预览模式
            previewMode = true;
            loadTempJson("${requestScope.pos}");
        </c:when>
        <c:when test="${'true' eq requestScope.info}">
            //查看模式
            previewMode = true;
            if ("${requestScope.failMsg}" == "paperNotExist") {
                alert("文件不存在或已被删除");
                window.opener = null;
                window.close();
            } else {
                loadPaperQuestionJson("${requestScope.paper.id}", "${requestScope.pos}")
            }
        </c:when>
        <c:when test="${'true' eq requestScope.buildUp}">
            //组卷模式
            buildUpMode = true;
            loadQuestionJson("${requestScope.questionId}");
        </c:when>
        <c:otherwise>
            //学习模式
            if ("${requestScope.failMsg}" == "paperNotExist") {
                alert("文件不存在或已被删除");
                window.opener = null;
                window.close();
            } else {
                loadPaperQuestionJson("${requestScope.paper.id}", "${requestScope.pos}", "<paper:getStudyUser/>")
            }
        </c:otherwise>
    </c:choose>
        });
</script>

