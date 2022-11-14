<%-- 
    Document   : paperLib
    Created on : 2014-2-27, 16:02:47
    Author     : Administrator
--%>

<%@page import="com.gzxtjy.paper.vo.SessionTempPaperVo"%>
<%@page import="com.gzxtjy.paper.constants.QuestionConstants"%>
<%@page import="com.gzxtjy.paper.constants.PaperType"%>
<%@page import="com.gzxtjy.paper.constants.QuestionType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    SessionTempPaperVo paperVo = (SessionTempPaperVo) request.getSession().getAttribute(QuestionConstants.SESSION_PAPER);
    String questionIds = "[]";
    if (paperVo != null) {
        questionIds = paperVo.getQuestionIds();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>题库选题</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/css/common/paper/bag.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="/css/teacher/paper/style.css" media="screen"/>
        <script type="text/javascript" src="/js/common/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="/js/common/jquery.cookie.js"></script>
        <style>
            .rs_platformTabArea2Content .subnav .cur {
                background-color: #009720;
                border-radius: 5px;
                color: white;
            }
        </style>
    </head>
    <body>
        <!--**************rs_platformContainer****************-->
        <div class="new_main" style="width:1131px" >
            <div class="question_select" style="width:1131px;">
                <div class="nav">
                    <ul>
                        <li>
                            <a target="_self" href="/common/paper/paperAction_createPaper.action?paperId=<s:property value="#session.paper_temp_paper_vo.paperId"/>&paperType=<s:property value="#session.paper_temp_paper_vo.paperType"/>">常规</a>
                        </li>
                        <li class="on"><a href="javascript:void(0)">题库选题</a></li>
                        <li><a href="javascript:void(0)" onclick="previewPaper()">预览</a></li>
                    </ul>
                </div>
                <div class="tk_xuanti">
                    <div class="i1">
                        <a class="a1" href="#">题库</a>
                        <a class="a2" href="#">试卷库</a>
                        <a class="a3" href="#">作业库</a>
                        <p>公共题库</p>
                    </div>
                    <div class="i2">
                        <a class="b1" href="#">我的题库</a>
                        <a class="b2" href="#">我的试卷库</a>
                        <a class="b3" href="#">我的作业库</a>
                        <p>个人库</p>
                    </div>
                    <div class="i3">
                        <a class="c1" href="#">随机组题</a>
                        <a class="c2" href="#">随机选卷</a>
                        <p>智能推荐</p>
                    </div>


                </div>
            </div>
            <div monkey="selectArea" class="select-area mb10" id="select-area" style="width:1118px;" >
                <div class="nav_1">
                    <ul>
                        <li class="on"><a href="javascript:void(0)">平台题库</a></li>
                        <li><a href="/common/paper/paperAction_paperLib.action?paperType=<%=PaperType.HOMEWORD%>">作业库</a></li>
                        <li><a href="/common/paper/paperAction_paperLib.action?paperType=<%=PaperType.EXAM%>">试卷库</a></li>
                    </ul>
                    <a target="_self" 
                       href="/common/paper/paperAction_createPaper.action?paperId=<s:property value="#session.paper_temp_paper_vo.paperId"/>&paperType=<s:property value="#session.paper_temp_paper_vo.paperType"/>">
                        <div class="number"><p><s:property value="#request.quesLength"/></p></div>
                    </a>
                </div>
                <table>
                    <tbody>
                        <tr>
                            <td class="a" style="width:15%;">题目范围</td>
                            <td class="b" style="width:85%;">
                                <span><s:property value="#request.subject.name"/></span>
                                <span><s:property value="#request.publish.name"/></span>
                                <span><s:property value="#request.grade.name"/></span>
                                <span><s:property value="#request.volume.name"/></span>
                                <!--                                <span style="padding-left:15px;border-left:1px solid #E2E6E5;"><a class="alter" href="#">修改</a></span>-->
                            </td>
                        </tr>
                        <%--                        <tr >
                                                    <td class="a">题目难度</td>
                                                    <td class="b">
                                                        <span><input type="checkbox">简单</span>
                                                        <span><input type="checkbox">中等</span>
                                                        <span><input type="checkbox">困难</span>
                                                        <span><input type="checkbox">压轴</span>
                                                    </td>
                                                </tr>
                                                <tr >
                                                    <td class="a">题型</td>
                                                    <td class="b">
                                                        <span><input type="checkbox">全部</span>
                                                        <span><input type="checkbox">单选题</span>
                                                        <span><input type="checkbox">多选题</span>
                                                                                        <span><input type="checkbox">判断题</span>
                                                        <span><input type="checkbox">填空题</span>
                                                        <span><input type="checkbox">简答题</span>
                                                                                        <span><input type="checkbox">匹配题</span>
                                                                                        <span><input type="checkbox">排序题</span>
                                                                                        <span><input type="checkbox">拖动题</span>
                                                                                        <span><input type="checkbox">数值题</span>
                                                                                        <span><input type="checkbox">选择填空题</span>
                                                                                        <span><input type="checkbox">论述题</span>
                                                                                        <span><input type="checkbox">热区题</span>
                                                                                        <span><input type="checkbox">语音作答题</span>
                                                                                        <span><input type="checkbox">拍照作答题</span>
                                                                                        <span><input type="checkbox">摄像作答题</span>
                                                    </td>
                                                </tr>--%>
                    </tbody>
                </table>

            </div>

            <div class="rs_platformContainer_1" style="width:1116px" >
                <s:include value="/module/common/paper/catalog.jsp">
                </s:include>
                <div class="main" style=" float: left;width:758px;">
                    <ul id="tabUl" class="rs_platformTab" style="width:758px;">
                    </ul>
                    <div class="paper-list-area">
                        <ul id="paperUl">
                            <!--                          <li>
                                                          <div class="title">
                                                              <span class="type">[单选题]</span>
                                                              <span class="small-rating"><span style="width: 90.0000%"></span></span><span class="score">4.5</span>
                                                              <a class="num" href="#">( <b id="docValueCount-2">108</b> 人评价)</a>
                                                              <p class="add">
                                                                  <a class="a1" href="#">报错</a>
                                                                  <a class="a2" href="#">收藏</a>
                                                                  <a class="a3" href="#">预览</a>
                                                                  <a class="join" href="javascript:void();" style="display:none;">加入组卷</a>
                                                                  <a class="out" href="javascript:void();" >移出组卷</a>
                                                              </p>
                                                              <div class="top">下列根式是最简单的根式的是（）</div>
                                                          </div>
                                                          <div class="option">
                                                              <a href="#"><p><span>A.</span>1</p></a>
                                                              <a href="#"><p><span>B.</span>2</p></a>
                                                              <a href="#"><p><span>C.</span>3</p></a>
                                                              <a href="#"><p class="on"><span>D.</span>4</p></a>
                                                          </div>
                                                          <div class="analysis">
                                                              <a class="hide">收起解析</a>
                                                              <a class="show" style="display:none;">展开解析</a>
                                                              <p><span>kuku </span> 创建于  2013-11-12
                                                                  <img src="/image/common/resources/pic1.jpg" alt=""></p>
                                                          </div>
                                                          <div class="answer">
                                                              <div>
                                                                  <p>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源</p>
                                                                  <a href="#">2013-11-15一元一次方程家庭作业题</a><span>&nbsp;&nbsp;第三题</span>
                                                              </div>
                                                              <div>
                                                                  <p>全站统计</p>
                                                                  <span>本题共被作答699次  正确60%</span>
                                                              </div>
                                                              <div>
                                                                  <p>解 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;析</p>
                                                                  <span>无</span>
                                                              </div>
                                                              <div>
                                                                  <p>知&nbsp;识&nbsp;点</p>
                                                                  <span>一元二次方程</span>
                                                              </div>
                                                          </div>
                          
                                                      </li>
                                                      <li>
                                                          <div class="title">
                                                              <span class="type">[解答题]</span>
                                                              <span class="small-rating"><span style="width: 90.0000%"></span></span><span class="score">4.5</span>
                                                              <a class="num" href="#">( <b id="docValueCount-2">108</b> 人评价)</a>
                                                              <p class="add">
                                                                  <a class="a1" href="#">报错</a>
                                                                  <a class="a2" href="#">收藏</a>
                                                                  <a class="a3" href="#">预览</a>
                                                                  <a class="join" href="javascript:void();">加入组卷</a>
                                                                  <a class="out" href="javascript:void();" style="display:none;">移出组卷</a>
                                                              </p>
                                                              <div class="top">设函数f(x)=x+1,求x的值。</div>
                                                          </div>
                                                          <div class="option">
                                                              <div class="answer_1"><span >[答案]</span>x=1;</div>
                                                          </div>
                                                          <div class="analysis">
                                                              <a class="hide"style="display:none;">收起解析</a>
                                                              <a class="show" >展开解析</a>
                                                              <p><span>kuku </span> 创建于  2013-11-12
                                                                  <img src="/image/common/resources/pic1.jpg" alt=""></p>
                                                          </div>
                                                          <div class="answer" style="display:none;">
                                                              <div>
                                                                  <p>来&nbsp;&nbsp;&nbsp;源</p>
                                                                  <a href="#">2013-11-15一元一次方程家庭作业题</a><span>&nbsp;&nbsp;第三题</span>
                                                              </div>
                                                              <div>
                                                                  <p>全站统计</p>
                                                                  <span>本题共被作答699次  正确60%</span>
                                                              </div>
                                                              <div>
                                                                  <p>解 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;析</p>
                                                                  <span>无</span>
                                                              </div>
                                                              <div>
                                                                  <p>知&nbsp;识&nbsp;点</p>
                                                                  <span>一元二次方程</span>
                                                              </div>
                                                          </div>
                          
                                                      </li>
                                                      <li>
                                                          <div class="title">
                                                              <span class="type">[填空题]</span>
                                                              <span class="small-rating"><span style="width: 90.0000%"></span></span><span class="score">4.5</span>
                                                              <a class="num" href="#">( <b id="docValueCount-2">108</b> 人评价)</a>
                                                              <p class="add">
                                                                  <a class="a1" href="#">报错</a>
                                                                  <a class="a2" href="#">收藏</a>
                                                                  <a class="a3" href="#">预览</a>
                                                                  <a class="join" href="javascript:void();">加入组卷</a>
                                                                  <a class="out" href="javascript:void();" style="display:none;">移出组卷</a>
                                                              </p>
                                                              <div class="top">设6=x+1,所以x等于（）。</div>
                                                          </div>
                                                          <div class="option">
                                                              <div class="answer_1"><span >[答案]</span>5</div>
                                                          </div>
                                                          <div class="analysis">
                                                              <a class="hide"style="display:none;">收起解析</a>
                                                              <a class="show" >展开解析</a>
                                                              <p><span>kuku </span> 创建于  2013-11-12
                                                                  <img src="/image/common/resources/pic1.jpg" alt=""></p>
                                                          </div>
                                                          <div class="answer" style="display:none;">
                                                              <div>
                                                                  <p>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源</p>
                                                                  <a href="#">2013-11-15一元一次方程家庭作业题</a><span>&nbsp;&nbsp;第三题</span>
                                                              </div>
                                                              <div>
                                                                  <p>全站统计</p>
                                                                  <span>本题共被作答699次  正确60%</span>
                                                              </div>
                                                              <div>
                                                                  <p>解 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;析</p>
                                                                  <span>无</span>
                                                              </div>
                                                              <div>
                                                                  <p>知&nbsp;识&nbsp;点</p>
                                                                  <span>一元二次方程</span>
                                                              </div>
                                                          </div>
                          
                                                      </li>-->
                        </ul>
                    </div>
<!--                    <div class="number_1"><p><s:property value="#request.quesLength"/></p></div>-->
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="gotop">
        <a class="top" href="javascript:void();" onclick="goTop();"></a>
        <a target="_self"  href="/common/paper/paperAction_createPaper.action?paperId=<s:property value="#request.paperId"/>&paperType=<s:property value="#request.paperType"/>">
            <div class="number_2">
                <p><s:property value="#request.quesLength"/></p>
            </div>
        </a>
    </div>
    <footer>
        <jsp:include page="/module/teacher/foot.jsp"></jsp:include>
    </body>
</html>
<script type="text/javascript">
                            var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
                            var subjectCode = "<s:property value="#request.subject.code"/>";
                            var publishCode = "<s:property value="#request.publish.code"/>";
                            var gradeCode = "<s:property value="#request.grade.code"/>";
                            var volumeCode = "<s:property value="#request.volume.code"/>";
                            var questionIds = <%=questionIds%>;
                            /* 
                             回到顶部
                             */
                            function gotoTop(acceleration, time) {
                                acceleration = acceleration || 0.1;
                                time = time || 16;
                                var x1 = 0;
                                var y1 = 0;
                                var x2 = 0;
                                var y2 = 0;
                                var x3 = 0;
                                var y3 = 0;
                                if (document.documentElement) {
                                    x1 = document.documentElement.scrollLeft || 0;
                                    y1 = document.documentElement.scrollTop || 0;
                                }
                                if (document.body) {
                                    x2 = document.body.scrollLeft || 0;
                                    y2 = document.body.scrollTop || 0;
                                }
                                var x3 = window.scrollX || 0;
                                var y3 = window.scrollY || 0;
                                // 滚动条到页面顶部的水平距离
                                var x = Math.max(x1, Math.max(x2, x3));
                                // 滚动条到页面顶部的垂直距离
                                var y = Math.max(y1, Math.max(y2, y3));
                                // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
                                var speed = 1 + acceleration;
                                window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
                                // 如果距离不为零, 继续调用迭代本函数
                                if (x != 0 || y != 0) {
                                    var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
                                    window.setTimeout(invokeFunction, time);
                                }
                            }
                            function endsWith(str, suffix) {
                                return str.indexOf(suffix, str.length - suffix.length) !== -1;
                            }

                            function buildUpQuestion(quesId) {
                                var tempJson = {};
                                if ($.inArray(quesId, questionIds) == -1) {
                                    questionIds.push(quesId);
                                }
                                tempJson["questionIds"] = questionIds;
                                $.ajax({
                                    url: "/teacher/paperLib/paperAction_saveTempPaper.action",
                                    type: "POST",
                                    data: {"tempJson": JSON.stringify(tempJson)},
                                    async: true,
                                    success: function(msg) {
                                        $("#questionLi_" + quesId + " a[class='join']").hide();
                                        $("#questionLi_" + quesId + " a[class='out']").show();
                                        var num = parseInt($(".number p").text());
                                        $(".number p").each(function() {
                                            $(this).text(questionIds.length);
                                        });
                                        $(".number_2 p").text(questionIds.length);
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
                                    url: "/teacher/paperLib/paperAction_saveTempPaper.action",
                                    type: "POST",
                                    data: {"tempJson": JSON.stringify(tempJson)},
                                    async: true,
                                    success: function(msg) {
                                        $("#questionLi_" + quesId + " a[class='out']").hide();
                                        $("#questionLi_" + quesId + " a[class='join']").show();
                                        var num = parseInt($(".number p").text());
                                        $(".number p").each(function() {
                                            $(this).text(questionIds.length);
                                        });
                                        $(".number_2 p").text(questionIds.length);
                                    }
                                });
                            }

                            function searchQuestion(data) {
                                if (data == null) {
                                    data = {};
                                }
                                data["subjectCode"] = subjectCode;
                                data["publishCode"] = publishCode;
                                data["gradeCode"] = gradeCode;
                                data["volumeCode"] = volumeCode;
                                data["userId"] = "<s:property value="@com.gzxtjy.portal.session.constants.SessionConstants@SYS_ADMIN_ID"/>";
                                $("#paperUl").html("<div id=\"loading\" ><img src=\"/image/teacher/loading.gif\" alt=\"加载中\"/>文件加载中,请耐心等待</div>");
                                $.ajax({
                                    url: "/teacher/paperLib/paperAction_loadQuestionListJson.action",
                                    type: "POST",
                                    data: data,
                                    dataType: "json",
                                    async: true,
                                    success: function(json) {
                                        var questions = json.questions;
                                        if (questions.length <= 0) {
                                            $("#paperUl").html("本章节没有相关题目");
                                        } else {
                                            $("#paperUl").html("");
                                        }
                                        for (var i = 0; i < questions.length; i++) {
                                            var question = questions[i];
                                            createQuestion(question);
                                        }
                                    }
                                });
                            }

                            function createQuestion(ques) {
                                var qType = ques.type;
                                var ul = $("#paperUl");
                                if (qType == "<%=QuestionType.RADIO%>") {
                                    createRadio(ques, ul);
                                } else if (qType == "<%=QuestionType.WORD%>") {
                                    createWord(ques, ul);
                                } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                    createCheckbox(ques, ul);
                                } else if (qType == "<%=QuestionType.BLANK%>") {
                                    createBlank(ques, ul);
                                }
                            }

                            function createRadio(ques, ul) {
                                var typeName = "单选题";
                                var answerHtml = "";
                                if (ques.answer != null && ques.answer.length > 0) {
                                    var answerList = ques.answer;
                                    var correctAnswer = (ques.correctAnswer != null && ques.correctAnswer.length == 1) ? ques.correctAnswer[0] : "";
                                    for (var i = 0; i < answerList.length; i++) {
                                        var correctCheck = correctAnswer == letters[i] ? "class=\"on\"" : "";
                                        answerHtml = answerHtml + "<a href=\"javascript:void(0)\"><p " + correctCheck + "><span>" + letters[i] + ".</span>" + answerList[i] + "</p></a>";
                                    }
                                }
                                var liHtml = "<li id=\"questionLi_" + ques.questionId + "\">"
                                        + "<div class=\"title\">"
                                        + "<span class=\"type\">[" + typeName + "]</span>"
                                        + createQuesHeader(ques.questionId)
                                        + "<div class=\"top\">" + ques.content + "</div>"
                                        + "</div>"
                                        + "<div class=\"option\">"
                                        + answerHtml
                                        + "</div>"
                                        + createAnalysis(ques);
                                        + "</li>"
                                ul.append(liHtml);
                            }

                            function createCheckbox(ques, ul) {
                                var typeName = "多选题";
                                var answerHtml = "";
                                if (ques.answer != null && ques.answer.length > 0) {
                                    var answerList = ques.answer;
                                    var correctAnswer = (ques.correctAnswer != null && ques.correctAnswer.length == 1) ? ques.correctAnswer[0] : "";
                                    for (var i = 0; i < answerList.length; i++) {
                                        var correctCheck = "";
                                        if (ques.correctAnswer != null && ques.correctAnswer.length > 0) {
                                            for (var h = 0; h < ques.correctAnswer.length; h++) {
                                                if (ques.correctAnswer[h] == letters[i]) {
                                                    correctCheck = "class=\"on\"";
                                                    break;
                                                }
                                            }
                                        }
                                        answerHtml = answerHtml + "<a href=\"javascript:void(0)\"><p " + correctCheck + "><span>" + letters[i] + ".</span>" + answerList[i] + "</p></a>";
                                    }
                                }
                                var liHtml = "<li id=\"questionLi_" + ques.questionId + "\">"
                                        + "<div class=\"title\">"
                                        + "<span class=\"type\">[" + typeName + "]</span>"
                                        + createQuesHeader(ques.questionId)
                                        + "<div class=\"top\">" + ques.content + "</div>"
                                        + "</div>"
                                        + "<div class=\"option\">"
                                        + answerHtml
                                        + "</div>"
                                        + createAnalysis(ques);
                                        + "</li>"
                                ul.append(liHtml);
                            }

                            function createBlank(ques, ul) {
                                var typeName = "填空题";
                                var answerHtml = "<div class=\"answer_1\"><span >[答案]</span>" + ques.answer + "</div>";
                                var liHtml = "<li id=\"questionLi_" + ques.questionId + "\">"
                                        + "<div class=\"title\">"
                                        + "<span class=\"type\">[" + typeName + "]</span>"
                                        + createQuesHeader(ques.questionId)
                                        + "<div class=\"top\">" + ques.content + "</div>"
                                        + "</div>"
                                        + "<div class=\"option\">"
                                        + answerHtml
                                        + "</div>"
                                        + createAnalysis(ques);
                                        + "</li>"
                                ul.append(liHtml);
                            }

                            function createWord(ques, ul) {
                                var typeName = "简答题";
                                var answerHtml = "<div class=\"answer_1\"><span >[答案]</span>" + ques.answer + "</div>";
                                var liHtml = "<li id=\"questionLi_" + ques.questionId + "\">"
                                        + "<div class=\"title\">"
                                        + "<span class=\"type\">[" + typeName + "]</span>"
                                        + createQuesHeader(ques.questionId)
                                        + "<div class=\"top\">" + ques.content + "</div>"
                                        + "</div>"
                                        + "<div class=\"option\">"
                                        + answerHtml
                                        + "</div>"
                                        + createAnalysis(ques);
                                        + "</li>"
                                ul.append(liHtml);
                            }
                            
                            function createAnalysis(ques){
                                var html =  "<div class=\"analysis\">"
                                        + "<a class=\"hide\">收起解析</a>"
                                        + "<a class=\"show\" style=\"display:none;\">展开解析</a>"
                                        + "<p><span>参考库</span> 创建于  " + ques.createTime
                                        + "<img src=\"/image/common/resources/pic1.jpg\" alt=\"\"></p>"
                                        + "</div>"
                                        + "<div class=\"answer\">"
                                        + "<div>"
                                        + "<p>来&nbsp;&nbsp;&nbsp;源</p>"
                                        + "<a href=\"javascript:void(0)\">"+ques.source+"</a>"
                                        + "</div>"
                                        + "<div>"
//                                        + "<p>全站统计</p>"
//                                        + "<span>本题共被作答699次  正确60%</span>"
                                        + "</div>"
                                        + "<div>"
                                        + "<p>解 &nbsp;&nbsp;&nbsp;析</p>"
                                        + "<span>" + (ques.explanation != null ? ques.explanation : "") + "</span>"
                                        + "</div>"
                                        + "<div>"
//                                        + "<p>知&nbsp;识&nbsp;点</p>"
//                                        + "<span></span>"
                                        + "</div>"
                                        + "</div>"
                                return html;
                            }

                            function createQuesHeader(quesId, score) {
                                var outHtml = "";
                                var joinHtml = "";
                                score = (score == null || score == "") ? "没分数" : score;
                                if (questionIds != "") {
                                    if (questionIds.indexOf(quesId) != -1) {
                                        joinHtml = "style=\"display:none\"";
                                    } else {
                                        outHtml = "style=\"display:none\"";
                                    }
                                } else {
                                    outHtml = "style=\"display:none\"";
                                }
                                var html = "<span class=\"small-rating\"><span style=\"width: 90.0000%\"></span></span><span class=\"score\">" + score + "</span>"
//                                        + "<a class=\"num\" href=\"#\">( <b id=\"docValueCount-2\">108</b> 人评价)</a>"
                                        + "<p class=\"add\">"
//                                        + "<a class=\"a1\" href=\"#\">报错</a>"
//                                        + "<a class=\"a2\" href=\"#\">收藏</a>"
                                        + "<a class=\"a3\" target=\"_self\" href=\"/common/paper/paperAction_studyPaper.action?buildUp=true&questionId="+quesId+"\">预览</a>"
                                        + "<a class=\"join\" onclick=\"buildUpQuestion('" + quesId + "')\" href=\"javascript:void(0)\" " + joinHtml + " >加入组卷</a>"
                                        + "<a class=\"out\" onclick=\"removeQuestion('" + quesId + "')\" href=\"javascript:void(0)\" " + outHtml + " >移出组卷</a>"
                                        + "</p>";
                                return html;
                            }


</script>