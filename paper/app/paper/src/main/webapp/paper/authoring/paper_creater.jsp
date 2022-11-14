<%-- 
    Document   : paperCreater
    Created on : 2013-7-26, 14:33:03
    Author     : Administrator
--%>

<%@page import="platform.education.paper.vo.SessionTempPaperVo"%>
<%@page import="platform.education.paper.constants.QuestionConstants"%>
<%@page import="platform.education.paper.constants.Difficulity"%>
<%@page import="platform.education.paper.constants.PaperType"%>
<%@page import="platform.education.paper.constants.AnswerConstants"%>
<%@page import="platform.education.paper.constants.QuestionType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglib.jsp" %>
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
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>迅云练习创建平台2015</title>
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paper/authoring/style.css" media="screen"/>
<!--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/paper/authoring/blankEditor/default.css" media="screen"/>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery.cookie.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery.tools.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/paper/authoring/blankEditor/kindeditor-all-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/paper/authoring/blankEditor/zh_CN.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jmeditor/JMEditor.js"></script>
    </head>
    <body>
        <div class="question_select">
            <div class="nav">
                <ul>
                    <li class="on"><a href="#">常规</a></li>
                    <!--                    <li><a href="#">题目导出</a></li>-->
<!--                    <li><a target="_self" onclick="gotoQuestionsLib()" href="javascript:void(0)">题库选题</a></li>-->
                    <li><a href="javascript:void(0)" onclick="previewPaper()">预览</a></li>
                </ul>
                <div class="title">标&nbsp;&nbsp;&nbsp;&nbsp;题:<input style="width:502px" type="text" value="${sessionScope.paper_temp_paper_vo.title}" id="paperTitle" /></div>
            </div>
            <div class="chaozuo">
                <div class="a1">
                    <a class="b1" href="javascript:void(0)" onclick="submitData();" >保存</a>
                    <a class="b2" href="javascript:void(0)" onclick="createPaperSettingDialog();">设置</a>
                    <a class="b3" href="javascript:void(0)" onclick="previewPaper();" >预览</a>
                    <!--                    <a class="b4" href="#">发布</a>-->
                    <p>文件</p>
                </div>
                <div class="a2">
                    <a class="c1" href="#">粘贴</a>
                    <a class="c2" href="#">剪切</a>
                    <a class="c3" href="#">复制</a>
                    <p>剪贴板</p>
                </div> 
                <div class="a3">
                    <a href="javascript:void(0)" onclick="createQuestion({'type': '<%=QuestionType.TRUEORFALSE%>'})"><b class="d1"></b>判断题</a>
                    <a href="javascript:void(0)" onclick="createQuestion({'type': '<%=QuestionType.RADIO%>'})" ><b class="d6"></b>单选题</a>
                    <a href="javascript:void(0)" onclick="createQuestion({'type': '<%=QuestionType.CHECKBOX%>'})" ><b class="d11"></b>多选题</a>
                    <a href="javascript:void(0)" onclick="createQuestion({'type': '<%=QuestionType.BLANK%>'})"><b class="d2"></b>填空题</a>
                    <!--    			<a href="javascript:void(0)"><b class="d3"></b>排序题</a>
                                            <a href="javascript:void(0)"><b class="d4"></b>选择填空题</a>
                                            <a href="javascript:void(0)"><b class="d5"></b>语音作答题</a>-->

                    <a href="javascript:void(0)" onclick="createQuestion({'type': '<%=QuestionType.WORD%>'})"><b class="d7"></b>简答题</a>
                    <!--                        <a href="javascript:void(0)"><b class="d9"></b>论述题</a>
                                            <a href="javascript:void(0)"><b class="d10"></b>拍照作答题</a>-->

                    <!--    			<a href="javascript:void(0)"><b class="d12"></b>匹配题</a>
                                            <a href="javascript:void(0)"><b class="d13"></b>数值题</a>-->
                    <a href="javascript:void(0)" onclick="createQuestion({'type': '<%=QuestionType.COMPLEX%>'})"><b class="d15"></b>复合题</a>
                    <a href="javascript:void(0)" onclick="createGroup()"><b class="d16"></b>添加组</a>
                    <p class="new_question">新建题目</p>
                    <input type="hidden" id="paperId"  />
                    <input type="hidden" value="${requestScope.paperType}" id="paperType"  />
                </div>
                <div class="a4">
                    <a class="e1" onclick="inputImage()" href="javascript:void(0)">图片</a>
                    <a class="e2" onclick="inputMath()" href="javascript:void(0)">公式</a>
                    <a class="e3" onclick="inputXtjyPaperMedia()" href="javascript:void(0)">视频</a>
                    <a class="e4" onclick="inputXtjyPaperMedia()" href="javascript:void(0)">音频</a>
                    <p>多媒体</p>
                </div>
                <div class="a5">
<!--                    <a href="#">题库选题</a>
                    <p>题库</p>-->
                </div>
            </div>
        </div>
        <div class="qe_content">
            <div class="qe_question_list">
                <div class="qe_ql_title">
                    <div class="qe_ql_title_inner">题目列表</div>
                </div>
                <div id="questionButtonPanel_${requestScope.id}" class="qe_ql_questions">
                </div>
                <div class="qe_ql_info">
                    <%--                    <div class="qe_ql_info_total_1">总分：<span id="questionsCountSpan_${requestScope.id}">140</span></div>--%>
                    <div class="qe_ql_info_total">总题目数：<span id="questionsCountSpan_${requestScope.id}">0</span></div>
                    <input style='width: 68px;' type="button" onclick="deleteQuestion()" value="删除题目" class="qe_btn">
                    <input style='width: 68px;' type="button" onclick="upQuestion()" value="上移" class="qe_btn">
                    <input style='width: 68px;' type="button" onclick="downQuestion()" value="下移" class="qe_btn">
                </div>
            </div>
            <div class="vertical_border"></div>
            <div id="questionContentPanel_${requestScope.id}" class="qe_question_content">

            </div>

        </div>
        <div id="saveLoading" style="display: none"><img   src="${pageContext.request.contextPath}/images/loading.gif" title="加载中" alt="加载中"/></div>
        <div id="previewLoading" style="display: none"><img   src="${pageContext.request.contextPath}/images/loading.gif" title="加载中" alt="加载中"/></div>
        <div id="editLoading" style="display: none"><img   src="${pageContext.request.contextPath}/images/loading.gif" title="加载中" alt="加载中"/></div>
        <script type="text/javascript">
                        //overlay弹出层公用函数
                        var loadingOverlayObj;
                        //这变量是用来控制onbeforeunload方法与a标签在IE中的冲突bug,食屎吧ie
                        var notLeave = true;
                        var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
                        var chineseNo = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十"]
                        //计算题目内容 KindEditor 数量
                        var contentEditors = {};
                        var contentEditorNo = 0;
                        //计算填空题 KindEditor 数量
                        var blankEditors = {};
                        var blankEditorNo = 0;
                        //计算选择题答案区 KindEditor 数量
                        var chooseAnswerEditors = {};
                        var chooseAnswerEditorNo = 0;
                        //当前正在输入的编辑器
                        var focusEditor;
                        //计算题目解释区 Kindediter 数量
                        var explanationEditors = {};
                        var explanationEditorNo = 0;
                        //编辑器基本按钮数组
                        var baseEditorItems = ["source", "fullscreen", "undo", "redo", "cut", "copy", "paste", "plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "clearhtml", "quickformat", "|",
                            "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "removeformat", "|", "table", "hr"];
                        var chooseAnswerEditorItems = [];
                        //题干区编辑器基本配置参数
                        var baseEditorSetting = {items: baseEditorItems, filterMode: false, resizeType: 0,filePostName:"file", uploadJson: "${pageContext.request.contextPath}/common/paper/upload"};
                        //选择题答案区编辑器基本配置参数
                        var chooseAnswerEditorSetting = {items: chooseAnswerEditorItems, filterMode: false, resizeType: 0,filePostName:"file", uploadJson: "${pageContext.request.contextPath}/common/paper/upload"};
                        //当前选中的复合题
                        var chooseComplex;
                        //全局默认设置:教材目录,难易度,总分,知识点
                        var questionIds = <%=questionIds%>;
                        var subjectCode = "${requestScope.subject.code}";
                        var publishCode = "${requestScope.publish.code}";
                        var gradeCode = "${requestScope.grade.code}";
                        var volumeCode = "${requestScope.volume.code}";
                        var unitCode = "${requestScope.unitCode}";
                        var sectionCode = "${requestScope.sectionCode}";
                        var bookId;
                        var bookUnitId;
                        var bookSectionId;
                        var difficulity;
                        var totalScore;
                        var knowledge;
                        function endsWith(str, suffix) {
                            return str.indexOf(suffix, str.length - suffix.length) !== -1;
                        }
                        function createGroup(json) {
                            notLeave = false;
                            var buttonPanel = $("#questionButtonPanel_");
                            var contentPanel = $("#questionContentPanel_");
                            var groupId = (json != null && json.groupId != null && json.groupId != "") ? json.groupId : (new Date().getTime()) ^ Math.random();
                            if ($("#group_" + groupId).html() == null) {
                                var count = buttonPanel.children("div[id^='group']").size();
                                var groupTitle = (json != null && json.groupTitle != null && json.groupTitle != "") ? json.groupTitle : "第" + chineseNo[(count + 1)] + "大题";
                                var html = "<div onclick=\"chooseGroup('" + groupId + "')\" id=\"group_" + groupId + "\" class=\"ques_title_hide\">"
                                        + "<a onclick=\"toggleGroup('" + groupId + "')\" href=\"javascript:void(0);\"></a><span>" + groupTitle + "</span></div>"
                                var contentDiv = $("<div id=\"groupContentDiv_" + groupId + "\"></div>");
                                var contentHtml = "<div class=\"qe_question_section\">"
                                        + "<div class=\"qe_qs_title\">组名称</div>"
                                        + "<div class=\"qe_qs_content\">"
                                        + "<textarea onkeyup=\"changeGroupTitle(this)\" name=\"\" id=\"groupContent_" + groupId + "\" "
                                        + "style=\"width:100%;\" >" + groupTitle + "</textarea>"
                                        + "</div></div>";
                                buttonPanel.append(html);
                                contentPanel.append(contentDiv);
                                contentDiv.append(contentHtml);
                                chooseGroup(groupId);
                            }
                        }
                        function createQuestion(json) {
                            notLeave = false;
                            var buttonPanel = $("#questionButtonPanel_");
                            if (chooseComplex != null) {
                                $("div[id^='button_" + chooseComplex + "']").parent().append("<div style=\"padding-left:25px;\" class=\"small\"></div>");
                                buttonPanel = $("div[id^='button_" + chooseComplex + "']").next();
                            }
                            var contentPanel = $("#questionContentPanel_");
                            var qType = json.type;
                            var groupId = (json != null && json.groupId != null && json.groupId != "") ? json.groupId : null
                            if (groupId != null) {
                                createGroup(json);
                            }
                            if (qType == "<%=QuestionType.RADIO%>") {
                                createRadio(buttonPanel, contentPanel, json);
                            } else if (qType == "<%=QuestionType.WORD%>") {
                                createWord(buttonPanel, contentPanel, json);
                            } else if (qType == "<%=QuestionType.CHECKBOX%>") {
                                createCheckbox(buttonPanel, contentPanel, json);
                            } else if (qType == "<%=QuestionType.BLANK%>") {
                                createBlank(buttonPanel, contentPanel, json);
                            } else if (qType == "<%=QuestionType.TRUEORFALSE%>") {
                                createTrueOrFalse(buttonPanel, contentPanel, json);
                            } else if (qType == "<%=QuestionType.COMPLEX%>") {
                                if (chooseComplex == null) {
                                    createComplex(buttonPanel, contentPanel, json);
                                }
                            }
                            updateQuestionsCount();
                        }
                        function createComplex(buttonPanel, contentPanel, json) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var pos = buttonPanel.children("div:not([id^='group'])").length + 1;
                            var text = (json.content == null || json.content == "") ? "复合题" : json.content;
                            var questionId = json.questionId;
                            questionId = (questionId != null && questionId != "") ? "<input type='hidden' name='questionId' value='" + questionId + "'/>" : "";
                            var originalQuestionId = json.originalQuestionId;
                            originalQuestionId = (originalQuestionId != null && originalQuestionId != "") ? "<input type='hidden' name='originalQuestionId' value='" + originalQuestionId + "'/>" : "";
                            var buttonText = setTrimButtonTitle(text);
                            var buttonHtml = "<div class=\"complex\">"
                                    + "<div onclick=\"chooseQuestion('" + id + "','" + pos + "')\" id=\"button_" + id + "_" + pos + "\" class=\"qe_ql_question\" >"
                                    + "<div class=\"qe_ql_question_title\"><span class=\"buttonPos\">" + pos + "</span>. <b class=\"d15\"></b><span class=\"buttonTitle\">" + buttonText + "</span></div>"
                                    + "<div class=\"qe_ql_question_type\"><input type=\"hidden\" value=\"<%=QuestionType.COMPLEX%>\" /></div>"
                                    + questionId
                                    + originalQuestionId
                                    + "</div>"
                                    + "</div>"
                            var contentDiv = $("<div id=\"questionContentDiv_" + id + "_" + pos + "\"></div>");
                            contentEditorNo = contentEditorNo + 1;
                            var contentHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">复合题</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<textarea name=\"contentEditor_" + contentEditorNo + "\" id=\"questionContent_" + id + "_" + pos + "\" class=\"qe_qs_content_main\""
                                    + "style=\"width:89%;height:510px;visibility:hidden;\" ></textarea>"
                                    + "</div></div>";
                            buttonPanel.append(buttonHtml);
                            contentPanel.append(contentDiv);
                            contentDiv.append(contentHtml);
                            var editor = KindEditor.create('textarea[name="contentEditor_' + contentEditorNo + '"]', baseEditorSetting);
                            contentEditors["contentEditor_" + contentEditorNo] = editor;
                            editor.html(text);
                            //添加设置与解释区
                            createSetting(contentDiv, id, pos, json)
                            chooseQuestion(id, pos);
                            //添加复合题子题目
                            var subQuestionList = json.questions;
                            if (subQuestionList != null) {
                                for (var h = 0; h < subQuestionList.length; h++) {
                                    var question = subQuestionList[h];
                                    chooseComplex = id;
                                    createQuestion(question);
                                }
                            }
                        }

                        function createWord(buttonPanel, contentPanel, json) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var pos = buttonPanel.children("div:not([id^='group'])").length + 1;
                            var text = (json.content == null || json.content == "") ? "简答题" : json.content;
                            var questionId = json.questionId;
                            questionId = (questionId != null && questionId != "") ? "<input type='hidden' name='questionId' value='" + questionId + "'/>" : "";
                            var originalQuestionId = json.originalQuestionId;
                            originalQuestionId = (originalQuestionId != null && originalQuestionId != "") ? "<input type='hidden' name='originalQuestionId' value='" + originalQuestionId + "'/>" : "";
                            var buttonText = setTrimButtonTitle(text);
                            var buttonHtml = "<div onclick=\"chooseQuestion('" + id + "','" + pos + "')\" id=\"button_" + id + "_" + pos + "\" class=\"qe_ql_question\">"
                                    + "<div class=\"qe_ql_question_title\"><span class=\"buttonPos\">" + pos + "</span>. <b class=\"d7\"></b><span class=\"buttonTitle\">" + buttonText + "</span></div>"
                                    + "<div class=\"qe_ql_question_type\"><input type=\"hidden\" value=\"<%=QuestionType.WORD%>\" /></div>"
                                    + questionId
                                    + originalQuestionId
                                    + "</div>";
                            var contentDiv = $("<div id=\"questionContentDiv_" + id + "_" + pos + "\"></div>");
                            contentEditorNo = contentEditorNo + 1;
                            var contentHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">简答题</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<textarea name=\"contentEditor_" + contentEditorNo + "\" id=\"questionContent_" + id + "_" + pos + "\" class=\"qe_qs_content_main\""
                                    + "style=\"width:89%;height:510px;visibility:hidden;\" ></textarea>"
                                    + "</div></div>";
                            buttonPanel.append(buttonHtml);
                            contentPanel.append(contentDiv);
                            contentDiv.append(contentHtml);
                            var editor = KindEditor.create('textarea[name="contentEditor_' + contentEditorNo + '"]', baseEditorSetting);
                            contentEditors["contentEditor_" + contentEditorNo] = editor;
                            editor.html(text);
                            //添加设置与解释区
                            createSetting(contentDiv, id, pos, json)
                            chooseQuestion(id, pos)
                        }
                        function createRadio(buttonPanel, contentPanel, json) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var pos = buttonPanel.children("div:not([id^='group'])").length + 1;
                            //jsonAnswerArray数组是用来保存富文本答案数据
                            var jsonAnswerArray = [];
                            var text = (json.content == null || json.content == "") ? "单项选择题" : json.content;
                            var questionId = json.questionId;
                            questionId = (questionId != null && questionId != "") ? "<input type='hidden' name='questionId' value='" + questionId + "'/>" : "";
                            var originalQuestionId = json.originalQuestionId;
                            originalQuestionId = (originalQuestionId != null && originalQuestionId != "") ? "<input type='hidden' name='originalQuestionId' value='" + originalQuestionId + "'/>" : "";
                            var buttonText = setTrimButtonTitle(text);
                            var buttonHtml = "<div onclick=\"chooseQuestion('" + id + "','" + pos + "')\" id=\"button_" + id + "_" + pos + "\" class=\"qe_ql_question\">"
                                    + "<div class=\"qe_ql_question_title\"><span class=\"buttonPos\">" + pos + "</span>. <b class=\"d6\"></b><span class=\"buttonTitle\">" + buttonText + "</span></div>"
                                    + "<div class=\"qe_ql_question_type\"><input type=\"hidden\" value=\"<%=QuestionType.RADIO%>\" /></div>"
                                    + questionId
                                    + originalQuestionId
                                    + "</div>";
                            contentEditorNo = contentEditorNo + 1;
                            var contentDiv = $("<div id=\"questionContentDiv_" + id + "_" + pos + "\"></div>");
                            var contentHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">单项选择题</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<textarea name=\"contentEditor_" + contentEditorNo + "\" id=\"questionContent_" + id + "_" + pos + "\" "
                                    + "style=\"width:89%;visibility:hidden;\" ></textarea>"
                                    + "</div></div>";
                            //处理答案区
                            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                            var answerList = "";
                            if (json.answer != null && json.answer.length > 0) {
                                var jsonAnswer = json.answer;
                                var correctAnswer = (json.correctAnswer != null && json.correctAnswer.length == 1) ? json.correctAnswer[0] : "";
                                //var split = jsonAnswer.split(splitFlag);
                                for (var i = 0; i < jsonAnswer.length; i++) {
                                    var odd = i % 2 == 0 ? "_odd" : "";
                                    var correctCheck = correctAnswer == letters[i] ? "checked" : "";
                                    chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                                    var inputHtml = "<div onclick=\"chooseAnswer(this)\" class=\"qe_qs_cm_list_item" + odd + "\">"
                                            + "<dl class=\"qe_qs_cm_list_item_inner\">"
                                            + "<dt>"
                                            + "<input " + correctCheck + " type=\"radio\"/ class=\"qe_qs_cm_list_item_inner_radio\" name=\"radioCorrectAnswer_" + id + "_" + pos + "\"/>"
                                            + "</dt>"
                                            + "<dd>"
                                            + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:99.7%;height:10px;visibility:hidden;\"></textarea>"
//                                            + "<input value=\"" + jsonAnswer[i] + "\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                                            + "</dd>"
                                            + "</dl>" + "</div>";
                                    jsonAnswerArray["chooseAnswerEditor_" + chooseAnswerEditorNo] = jsonAnswer[i];
                                    answerList = answerList + inputHtml;
                                }
                            } else {
                                for (var i = 0; i < 4; i++) {
                                    var odd = i % 2 == 0 ? "_odd" : "";
                                    var correctCheck = "";
                                    if (i == 0) {
                                        correctCheck = "checked";
                                    }
                                    chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                                    var inputHtml = "<div onclick=\"chooseAnswer(this)\" class=\"qe_qs_cm_list_item" + odd + "\">"
                                            + "<dl class=\"qe_qs_cm_list_item_inner\">"
                                            + "<dt>"
                                            + "<input " + correctCheck + " type=\"radio\"/ class=\"qe_qs_cm_list_item_inner_radio\" name=\"radioCorrectAnswer_" + id + "_" + pos + "\"/>"
                                            + "</dt>"
                                            + "<dd style=\"position:relative\">"
                                            + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:88.7%;height:10px;visibility:hidden;\">选项" + (i + 1) + "</textarea>"
//                                            + "<input value=\"选项" + (i + 1) + "\"  class=\"qe_qs_cm_list_item_inner_text\"/>"
                                            + "</dd>"
                                            + "</dl>" + "</div>";
                                    answerList = answerList + inputHtml;
                                }
                            }
                            var answerHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">答案</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<div class=\"qe_qs_answer_main\">"
                                    + "<dl class=\"qe_qs_cm_title\">" + "<dt>正确答案</dt>" + "<dd></dd>"
                                    + "</dl>"
                                    + "<div class=\"qe_qs_cm_list\" id=\"radioAnswerPanel_" + id + "_" + pos + "\" >"
                                    + answerList
                                    + "</div>"
                                    + "</div>"
                                    + "<div class=\"qe_qs_answer_tool\">"
                                    + "<input style='width: 80px;' onclick=\"createAnswer(this,'<%=QuestionType.RADIO%>'," + pos + ")\" type=\"button\" value=\"添加选项\" class=\"qe_btn\"/><br/>"
                                    + "<input style='width: 80px;' onclick=\"deleteAnswer(this)\" type=\"button\" value=\"删除选项\" class=\"qe_btn\"/><br/>"
                                    + "<input style='width: 80px;' onclick=\"upAnswer(this)\" type=\"button\" value=\"上移\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"downAnswer(this)\" type=\"button\" value=\"下移\" class=\"qe_btn\"/>"
                                    + "</div>"
                                    + "<div style=\"clear: both\">" + "</div>"
                                    + "</div>"
                                    + "</div>"
                            buttonPanel.append(buttonHtml);
                            contentPanel.append(contentDiv);
                            contentDiv.append(contentHtml);
                            contentDiv.append(answerHtml);
                            contentDiv.find("textarea[name^='chooseAnswerEditor_']").each(function() {
                                var chooseAnswerEditor = KindEditor.create("textarea[name='" + $(this).attr("name") + "']", chooseAnswerEditorSetting);
                                var name = $(this).attr("name");
                                chooseAnswerEditors[name] = chooseAnswerEditor;
                                chooseAnswerEditor.html(jsonAnswerArray[name]);
                                $(this).prev().css("height", "190%");
                            });
                            var editor = KindEditor.create('textarea[name="contentEditor_' + contentEditorNo + '"]', baseEditorSetting);
                            contentEditors["contentEditor_" + contentEditorNo] = editor;
                            editor.html(text);
                            //添加设置与解释区
                            createSetting(contentDiv, id, pos, json)
                            chooseQuestion(id, pos);
                            //添加多媒体区域
                            if (json.contentMediaUrl != null && json.contentMediaUrl != "") {
                                appendMediaDiv(json.contentMediaUrl)
                            }
                        }

                        function createCheckbox(buttonPanel, contentPanel, json) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var pos = buttonPanel.children("div:not([id^='group'])").length + 1;
                            //jsonAnswerArray数组是用来保存富文本答案数据
                            var jsonAnswerArray = [];
                            var text = (json.content == null || json.content == "") ? "多项选择题" : json.content;
                            var questionId = json.questionId;
                            questionId = (questionId != null && questionId != "") ? "<input type='hidden' name='questionId' value='" + questionId + "'/>" : "";
                            var originalQuestionId = json.originalQuestionId;
                            originalQuestionId = (originalQuestionId != null && originalQuestionId != "") ? "<input type='hidden' name='originalQuestionId' value='" + originalQuestionId + "'/>" : "";
                            var buttonText = setTrimButtonTitle(text);
                            var buttonHtml = "<div onclick=\"chooseQuestion('" + id + "','" + pos + "')\" id=\"button_" + id + "_" + pos + "\" class=\"qe_ql_question\">"
                                    + "<div class=\"qe_ql_question_title\"><span class=\"buttonPos\">" + pos + "</span>. <b class=\"d11\"></b><span class=\"buttonTitle\">" + buttonText + "</span></div>"
                                    + "<div class=\"qe_ql_question_type\"><input type=\"hidden\" value=\"<%=QuestionType.CHECKBOX%>\" /></div>"
                                    + questionId
                                    + originalQuestionId
                                    + "</div>";
                            contentEditorNo = contentEditorNo + 1;
                            var contentDiv = $("<div id=\"questionContentDiv_" + id + "_" + pos + "\"></div>");
                            var contentHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">多项选择题</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<textarea name=\"contentEditor_" + contentEditorNo + "\" id=\"questionContent_" + id + "_" + pos + "\" "
                                    + "style=\"width:89%;visibility:hidden;\" ></textarea>"
                                    + "</div></div>";
                            //处理答案区
                            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                            var answerList = "";
                            if (json.answer != null && json.answer.length > 0) {
                                var jsonAnswer = json.answer;
                                //var split = jsonAnswer.split(splitFlag);
                                for (var i = 0; i < jsonAnswer.length; i++) {
                                    var odd = i % 2 == 0 ? "_odd" : "";
                                    var correctCheck = "";
                                    chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                                    if (json.correctAnswer != null && json.correctAnswer.length > 0) {
                                        for (var h = 0; h < json.correctAnswer.length; h++) {
                                            if (json.correctAnswer[h] == letters[i]) {
                                                correctCheck = "checked";
                                                break;
                                            }
                                        }
                                    }
                                    var inputHtml = "<div onclick=\"chooseAnswer(this)\" class=\"qe_qs_cm_list_item" + odd + "\">"
                                            + "<dl class=\"qe_qs_cm_list_item_inner\">"
                                            + "<dt>"
                                            + "<input " + correctCheck + " type=\"checkbox\"/ class=\"qe_qs_cm_list_item_inner_radio\" name=\"checkboxCorrectAnswer_" + id + "_" + pos + "\"/>"
                                            + "</dt>"
                                            + "<dd>"
                                            + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:88.7%;height:10px;visibility:hidden;\"></textarea>"
//                                            + "<input  value=\"" + jsonAnswer[i] + "\" class=\"qe_qs_cm_list_item_inner_text\"/>"

                                            + "</dd>"
                                            + "</dl>"
                                            + "</div>";
                                    jsonAnswerArray["chooseAnswerEditor_" + chooseAnswerEditorNo] = jsonAnswer[i];
                                    answerList = answerList + inputHtml;
                                }
                            } else {
                                for (var i = 0; i < 4; i++) {
                                    var odd = i % 2 == 0 ? "_odd" : "";
                                    var correctCheck = "";
                                    if (i == 0) {
                                        correctCheck = "checked";
                                    }
                                    chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                                    var inputHtml = "<div onclick=\"chooseAnswer(this)\"  class=\"qe_qs_cm_list_item" + odd + "\">"
                                            + "<dl class=\"qe_qs_cm_list_item_inner\">"
                                            + "<dt>"
                                            + "<input " + correctCheck + " type=\"checkbox\"/ class=\"qe_qs_cm_list_item_inner_radio\" name=\"checkboxCorrectAnswer_" + id + "_" + pos + "\"/>"
                                            + "</dt>"
                                            + "<dd  style=\"position:relative;\">"
                                            + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:88.7%;height:10px;visibility:hidden;\">选项" + (i + 1) + "</textarea>"
//                                            + "<input value=\"选项" + (i + 1) + "\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                                            + "</dd>"
                                            + "</dl>"
                                            + "</div>";
                                    answerList = answerList + inputHtml;
                                }
                            }
                            var answerHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">答案</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<div class=\"qe_qs_answer_main\">"
                                    + "<dl class=\"qe_qs_cm_title\">" + "<dt>正确答案</dt>" + "<dd></dd>"
                                    + "</dl>"
                                    + "<div class=\"qe_qs_cm_list\" id=\"checkboxAnswerPanel_" + id + "_" + pos + "\" >"
                                    + answerList
                                    + "</div>"
                                    + "</div>"
                                    + "<div class=\"qe_qs_answer_tool\">"
                                    + "<input style='width: 80px;' onclick=\"createAnswer(this,'<%=QuestionType.CHECKBOX%>'," + pos + ")\" type=\"button\" value=\"添加选项\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"deleteAnswer(this)\" type=\"button\" value=\"删除选项\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"upAnswer(this)\" type=\"button\" value=\"上移\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"downAnswer(this)\" type=\"button\" value=\"下移\" class=\"qe_btn\"/>"
                                    + "</div>"
                                    + "<div style=\"clear: both\">" + "</div>"
                                    + "</div>"
                                    + "</div>"
                            buttonPanel.append(buttonHtml);
                            contentPanel.append(contentDiv);
                            contentDiv.append(contentHtml);
                            contentDiv.append(answerHtml);
                            contentDiv.find("textarea[name^='chooseAnswerEditor_']").each(function() {
                                var chooseAnswerEditor = KindEditor.create("textarea[name='" + $(this).attr("name") + "']", chooseAnswerEditorSetting);
                                var name = $(this).attr("name");
                                chooseAnswerEditors[name] = chooseAnswerEditor;
                                chooseAnswerEditor.html(jsonAnswerArray[name]);
                                $(this).prev().css("height", "190%");
                            });
                            var editor = KindEditor.create('textarea[name="contentEditor_' + contentEditorNo + '"]', baseEditorSetting);
                            contentEditors["contentEditor_" + contentEditorNo] = editor;
                            editor.html(text);
                            //添加设置与解释区
                            createSetting(contentDiv, id, pos, json)
                            chooseQuestion(id, pos);
                            //添加多媒体区域
                            if (json.contentMediaUrl != null && json.contentMediaUrl != "") {
                                appendMediaDiv(json.contentMediaUrl)
                            }
                        }

                        function createTrueOrFalse(buttonPanel, contentPanel, json) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var pos = buttonPanel.children("div:not([id^='group'])").length + 1;
                            //jsonAnswerArray数组是用来保存富文本答案数据
                            var jsonAnswerArray = [];
                            var text = (json.content == null || json.content == "") ? "判断题" : json.content;
                            var questionId = json.questionId;
                            questionId = (questionId != null && questionId != "") ? "<input type='hidden' name='questionId' value='" + questionId + "'/>" : "";
                            var originalQuestionId = json.originalQuestionId;
                            originalQuestionId = (originalQuestionId != null && originalQuestionId != "") ? "<input type='hidden' name='originalQuestionId' value='" + originalQuestionId + "'/>" : "";
                            var buttonText = setTrimButtonTitle(text);
                            var buttonHtml = "<div onclick=\"chooseQuestion('" + id + "','" + pos + "')\" id=\"button_" + id + "_" + pos + "\" class=\"qe_ql_question\">"
                                    + "<div class=\"qe_ql_question_title\"><span class=\"buttonPos\">" + pos + "</span>.<b class=\"d1\"></b> <span class=\"buttonTitle\">" + buttonText + "</span></div>"
                                    + "<div class=\"qe_ql_question_type\"><input type=\"hidden\" value=\"<%=QuestionType.TRUEORFALSE%>\" /></div>"
                                    + questionId
                                    + originalQuestionId
                                    + "</div>";
                            contentEditorNo = contentEditorNo + 1;
                            var contentDiv = $("<div id=\"questionContentDiv_" + id + "_" + pos + "\"></div>");
                            var contentHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">判断题</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<textarea name=\"contentEditor_" + contentEditorNo + "\" id=\"questionContent_" + id + "_" + pos + "\" "
                                    + "style=\"width:89%;visibility:hidden;\" ></textarea>"
                                    + "</div></div>";
                            //处理答案区
                            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                            var answerList = "";
                            if (json.answer != null && json.answer.length > 0) {
                                var jsonAnswer = json.answer;
                                var correctAnswer = (json.correctAnswer != null && json.correctAnswer.length == 1) ? json.correctAnswer[0] : "";
                                //var split = jsonAnswer.split(splitFlag);
                                for (var i = 0; i < jsonAnswer.length; i++) {
                                    var odd = i % 2 == 0 ? "_odd" : "";
                                    var correctCheck = correctAnswer == letters[i] ? "checked" : "";
                                    chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                                    var inputHtml = "<div onclick=\"chooseAnswer(this)\" class=\"qe_qs_cm_list_item" + odd + "\">"
                                            + "<dl class=\"qe_qs_cm_list_item_inner\">"
                                            + "<dt>"
                                            + "<input " + correctCheck + " type=\"radio\"/ class=\"qe_qs_cm_list_item_inner_radio\" name=\"tureOrFalseCorrectAnswer_" + id + "_" + pos + "\"/>"
                                            + "</dt>"
                                            + "<dd>"
                                            + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:99.7%;height:10px;visibility:hidden;\"></textarea>"
//                                            + "<input value=\"" + jsonAnswer[i] + "\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                                            + "</dd>"
                                            + "</dl>" + "</div>";
                                    jsonAnswerArray["chooseAnswerEditor_" + chooseAnswerEditorNo] = jsonAnswer[i];
                                    answerList = answerList + inputHtml;
                                }
                            } else {
                                for (var i = 0; i < 2; i++) {
                                    var odd = i % 2 == 0 ? "_odd" : "";
                                    var str = i % 2 == 0 ? "正确" : "错误";
                                    var correctCheck = "";
                                    if (i == 0) {
                                        correctCheck = "checked";
                                    }
                                    chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                                    var inputHtml = "<div onclick=\"chooseAnswer(this)\" class=\"qe_qs_cm_list_item" + odd + "\">"
                                            + "<dl class=\"qe_qs_cm_list_item_inner\">"
                                            + "<dt>"
                                            + "<input " + correctCheck + " type=\"radio\"/ class=\"qe_qs_cm_list_item_inner_radio\" name=\"tureOrFalseCorrectAnswer_" + id + "_" + pos + "\"/>"
                                            + "</dt>"
                                            + "<dd  style=\"position:relative;\">"
                                            + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:88.7%;height:10px;visibility:hidden;\">" + str + "</textarea>"
//                                            + "<input value=\"选项" + (i + 1) + "\"  class=\"qe_qs_cm_list_item_inner_text\"/>"
                                            + "</dd>"
                                            + "</dl>" + "</div>";
                                    answerList = answerList + inputHtml;
                                }
                            }
                            var answerHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">答案</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<div class=\"qe_qs_answer_main\">"
                                    + "<dl class=\"qe_qs_cm_title\">" + "<dt>正确答案</dt>" + "<dd></dd>"
                                    + "</dl>"
                                    + "<div class=\"qe_qs_cm_list\" id=\"trueOrFalseAnswerPanel_" + id + "_" + pos + "\" >"
                                    + answerList
                                    + "</div>"
                                    + "</div>"
                                    + "<div class=\"qe_qs_answer_tool\">"
                                    + "<input style='width: 80px;' onclick=\"upAnswer(this)\" type=\"button\" value=\"上移\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"downAnswer(this)\" type=\"button\" value=\"下移\" class=\"qe_btn\"/>"
                                    + "</div>"
                                    + "<div style=\"clear: both\">" + "</div>"
                                    + "</div>"
                                    + "</div>"
                            buttonPanel.append(buttonHtml);
                            contentPanel.append(contentDiv);
                            contentDiv.append(contentHtml);
                            contentDiv.append(answerHtml);
                            contentDiv.find("textarea[name^='chooseAnswerEditor_']").each(function() {
                                var chooseAnswerEditor = KindEditor.create("textarea[name='" + $(this).attr("name") + "']", chooseAnswerEditorSetting);
                                var name = $(this).attr("name");
                                chooseAnswerEditors[name] = chooseAnswerEditor;
                                chooseAnswerEditor.html(jsonAnswerArray[name]);
                                $(this).prev().css("height", "190%");
                            });
                            var editor = KindEditor.create('textarea[name="contentEditor_' + contentEditorNo + '"]', baseEditorSetting);
                            contentEditors["contentEditor_" + contentEditorNo] = editor;
                            editor.html(text);
                            //添加设置与解释区
                            createSetting(contentDiv, id, pos, json)
                            chooseQuestion(id, pos);
                        }

                        function createBlank(buttonPanel, contentPanel, json) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var pos = buttonPanel.children("div:not([id^='group'])").length + 1;
                            var text = (json.content == null || json.content == "") ? "填空题" : json.content;
                            var questionId = json.questionId;
                            questionId = (questionId != null && questionId != "") ? "<input type='hidden' name='questionId' value='" + questionId + "'/>" : "";
                            var originalQuestionId = json.originalQuestionId;
                            originalQuestionId = (originalQuestionId != null && originalQuestionId != "") ? "<input type='hidden' name='originalQuestionId' value='" + originalQuestionId + "'/>" : "";
                            var buttonText = setTrimButtonTitle(text);
                            var buttonHtml = "<div onclick=\"chooseQuestion('" + id + "','" + pos + "')\" id=\"button_" + id + "_" + pos + "\" class=\"qe_ql_question\">"
                                    + "<div class=\"qe_ql_question_title\"><span class=\"buttonPos\">" + pos + "</span>. <b class=\"d2\"></b><span class=\"buttonTitle\">" + buttonText + "</span></div>"
                                    + "<div class=\"qe_ql_question_type\"><input type=\"hidden\" value=\"<%=QuestionType.BLANK%>\" /></div>"
                                    + questionId
                                    + originalQuestionId
                                    + "</div>";
                            contentEditorNo = contentEditorNo + 1;
                            var contentDiv = $("<div id=\"questionContentDiv_" + id + "_" + pos + "\"></div>");
                            var contentHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">填空题</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<textarea name=\"contentEditor_" + contentEditorNo + "\" id=\"questionContent_" + id + "_" + pos + "\" "
                                    + "style=\"width:89%;visibility:hidden;\" ></textarea>"
                                    + "</div></div>";
                            //处理答案区
                            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                            blankEditorNo = blankEditorNo + 1;
                            var answer = "";
                            if (json.answer != null && json.answer.length>0) {
                                answer = json.answer[0];
                            }
                            var answerList = "<textarea name=\"blankEditor_" + blankEditorNo + "\" style=\"width:88.7%;height:317px;visibility:hidden;\"></textarea>"
                            var answerHtml = "<div class=\"qe_question_section\">"
                                    + "<div class=\"qe_qs_title\">详细</div>"
                                    + "<div class=\"qe_qs_content\">"
                                    + "<div class=\"qe_qs_answer_main\">"
                                    + "<div class=\"qe_qs_cm_list\" id=\"blankAnswerPanel_" + id + "_" + pos + "\" >"
                                    + answerList
                                    + "</div>"
                                    + "</div>"
                                    + "<div class=\"qe_qs_answer_tool\" id=\"balnakAnswerToolPanel_" + id + "\" >"
                                    + "<input style='width: 80px;' onclick=\"createBlankAnswer(" + blankEditorNo + ")\" type=\"button\" value=\"插入空格\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"deleteBlankAnswer(" + id + "," + blankEditorNo + ")\" type=\"button\" value=\"删除空格\" class=\"qe_btn\"/><br>"
                                    + "<input style='width: 80px;' onclick=\"editBlankAnswer(" + id + "," + blankEditorNo + ")\" type=\"button\" value=\"编辑空格\"class=\"qe_btn\"/><br>"
                                    + "</div>"
                                    + "<div style=\"clear: both\">" + "</div>"
                                    + "</div>"
                                    + "</div>"
                            buttonPanel.append(buttonHtml);
                            contentPanel.append(contentDiv);
                            contentDiv.append(contentHtml);
                            var contentEditor = KindEditor.create('textarea[name="contentEditor_' + contentEditorNo + '"]', baseEditorSetting);
                            contentEditors["contentEditor_" + contentEditorNo] = contentEditor;
                            contentEditor.html(text);
                            contentDiv.append(answerHtml);
                            var answerEditor = KindEditor.create('textarea[name="blankEditor_' + blankEditorNo + '"]', baseEditorSetting);
                            blankEditors["blankEditor_" + blankEditorNo] = answerEditor;
                            answerEditor.html(answer);
                            //把正确答案添加到空格区
                            if (json.correctAnswer != null && json.correctAnswer.length > 0) {
                                var caSplit = json.correctAnswer;
                                var inputList = $("div[id^='blankAnswerPanel_" + id + "'] iframe").contents().find("input[type='text']");
                                inputList.each(function(x) {
                                    if (caSplit[x] != null) {
                                        $(this).val(caSplit[x]);
                                    }
                                });
                            }
                            //添加设置与解释区
                            createSetting(contentDiv, id, pos, json)
                            chooseQuestion(id, pos);
                        }
                        function settingPanelSwitch(id, pos, type, json) {
                            notLeave = false;
                            if (json == null) {
                                json = JSON.parse($("#questionJson_" + id).text());
                            }
                            var explanation = (json.explanation == null || json.explanation == "") ? "" : json.explanation;
                            var score = (json.score == null || json.score == "") ? "" : json.score;
                            var ddifficulity = (json.difficulity == null || json.difficulity == "") ? difficulity : json.difficulity;
                            var ssubjectCode = (json.subjectCode == null || json.subjectCode == "") ? subjectCode : json.subjectCode;
                            var ppublishCode = (json.publishCode == null || json.publishCode == "") ? publishCode : json.publishCode;
                            var ggradeCode = (json.gradeCode == null || json.gradeCode == "") ? gradeCode : json.gradeCode;
                            var vvolumeCode = (json.volumeCode == null || json.volumeCode == "") ? volumeCode : json.volumeCode;
                            var uunitCode = (json.unitCode == null || json.unitCode == "") ? unitCode : json.unitCode;
                            var ssectionCode = (json.sectionCode == null || json.sectionCode == "") ? sectionCode : json.sectionCode;
                            var sbookId = (json.bookId == null || json.bookId == "") ? bookId : json.bookId;
                            var sbookUnitId = (json.bookUnitId == null || json.bookUnitId == "") ? bookUnitId : json.bookUnitId;
                            var sbookSectionId = (json.bookSectionId == null || json.bookSectionId == "") ? bookSectionId : json.bookSectionId;
                            var kknowledge = (json.knowledge == null || json.knowledge == "") ? knowledge : json.knowledge;
                            var data = {"id": id, "pos": pos};
                            if (type == "properties") {
                                data["difficulity"] = ddifficulity;
                                data["subjectCode"] = ssubjectCode;
                                data["publishCode"] = ppublishCode;
                                data["gradeCode"] = ggradeCode;
                                data["volumeCode"] = vvolumeCode;
                                data["unitCode"] = uunitCode;
                                data["sectionCode"] = ssectionCode;
                                data["bookId"] = sbookId;
                                data["bookUnitId"] = sbookUnitId;
                                data["bookSectionId"] = sbookSectionId;
                                data["knowledgeId"] = kknowledge;
                            } else if (type == "explanation") {
                                explanationEditorNo = explanationEditorNo + 1;
                                data["explanation"] = explanation, data["explanationEditorNo"] = explanationEditorNo;
                            } else if (type == "score") {
                                data["score"] = score;
                            }
                            var ul = $("#questionSettingDiv_" + id + "_" + pos + " ul");
                            var natureDiv = $("#questionSettingDiv_" + id + "_" + pos + " .nature");
                            var typeDiv = natureDiv.find("div[name='" + type + "']");
                            var url = "${pageContext.request.contextPath}/paper/authoring/setting_" + type + ".jsp";
                            ul.find("li").each(function(i) {
                                if ($(this).attr("name") == type) {
                                    $(this).attr("class", "active");
                                } else {
                                    $(this).removeAttr("class");
                                }
                            });
                            natureDiv.find("div[name]").each(function(i) {
                                $(this).hide();
                            });
                            if (typeDiv.html() == null || typeDiv.html() == "") {
                                //没有加载过的
                                $.ajax({
                                    url: url,
                                    type: "POST",
                                    data: data,
                                    async: true,
                                    success: function(html) {
                                        natureDiv.append(html);
                                    }
                                });
                            } else {
                                //已经加载过的
                                typeDiv.show();
                                if (type == "explanation") {
                                    var focusExplanation = $("div[id^='questionContentDiv_" + id + "'] div[name='explanation'] textarea[name^='explanationEditor_']");
                                    var explanationEditor = focusExplanation.prev().find("iframe").contents().find("body");
                                    explanationEditor.unbind('click').click(function() {
                                        setFocusEditor(focusExplanation.attr("name"), "explanation");
                                    });
                                    //先点击一下内容区避免用户选不中内容区
                                    explanationEditor.click();
                                }
                            }
                        }
                        function createSetting(contentDiv, id, pos, json) {
                            var settingDiv = $("<div id=\"questionSettingDiv_" + id + "_" + pos + "\"></div>");
                            var settingHtml = "<div class=\"detail\">"
                                    + "<div class=\"detail_nav\">"
                                    + "<ul>"
                                    + "<li name=\"properties\" onclick=\"settingPanelSwitch('" + id + "','" + pos + "','properties')\" class=\"active\"><a href=\"javascript:void(0)\">题目属性</a></li>"
                                    + "<li name=\"explanation\" onclick=\"settingPanelSwitch('" + id + "','" + pos + "','explanation')\"><a href=\"javascript:void(0)\">题目解析</a></li>"
                                    + "<li name=\"score\" onclick=\"settingPanelSwitch('" + id + "','" + pos + "','score')\"><a href=\"javascript:void(0)\">分数设定</a></li>"
//                                    + "<li onclick=\"settingPanelSwitch('" + id + "','" + pos + "',this,"+json+")\"><a href=\"#\">答题反馈</a></li>"
                                    + "</ul>"
                                    + "</div>"
                                    + "<div class=\"nature\">"
                                    + "</div>"
                                    //用一个textarea保存json内容方便读取
                                    + "<textarea id=\"questionJson_" + id + "\" style=\"display:none\"></textarea>"
                                    + "</div>";
                            contentDiv.append(settingDiv);
                            settingDiv.append(settingHtml);
                            $("#questionJson_" + id).text(JSON.stringify(json));
                            settingPanelSwitch(id, pos, "properties", json);
                        }
                        function chooseGroup(id) {
                            var group = $("div[id^='group_" + id + "']");
                            var buttonPanel = $("#questionButtonPanel_");
                            var contentPanel = $("#questionContentPanel_");
                            if (group.attr("class").lastIndexOf("cur") == -1) {
                                buttonPanel.children().each(function() {
                                    if ($(this).attr("class").indexOf("qe_ql_question") != -1) {
                                        if ($(this).attr("class").lastIndexOf("cur") != -1) {
                                            $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        }
                                    } else if ($(this).attr("class").indexOf("complex") != -1) {
                                        //复合题
                                        $(this).find("div[class$='cur']").each(function() {
                                            $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        });
                                    } else if ($(this).attr("id").indexOf("group") != -1) {
                                        //组
                                        if ($(this).attr("class").lastIndexOf("cur") != -1) {
                                            $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        }
                                    }
                                });
                                group.attr("class", group.attr("class") + " cur");
                                contentPanel.children().each(function() {
                                    if ($(this).attr("id").indexOf("groupContentDiv_" + id) != -1) {
                                        $(this).show();
                                    } else {
                                        $(this).hide();
                                    }
                                });
                            }
                        }
                        function chooseQuestion(id, pos) {
                            var button = $("div[id^='button_" + id + "']");
                            var buttonPanel = $("#questionButtonPanel_");
                            var contentPanel = $("#questionContentPanel_");
                            focusEditor = null;
                            if (button.attr("class").lastIndexOf("cur") == -1) {
                                buttonPanel.children().each(function() {
                                    if ($(this).attr("class").indexOf("qe_ql_question") != -1) {
                                        if ($(this).attr("class").lastIndexOf("cur") != -1) {
                                            $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        }
                                    } else if ($(this).attr("class").indexOf("complex") != -1) {
                                        //复合题
                                        $(this).find("div[class$='cur']").each(function() {
                                            $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        });
                                    } else if ($(this).attr("id").indexOf("group") != -1) {
                                        //组
                                        if ($(this).attr("class").lastIndexOf("cur") != -1) {
                                            $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        }
                                    }
                                });
                                if (button.parent().attr("class") == "complex") {
                                    chooseComplex = id;
                                    $(".new_question").text("添加题目到复合题");
                                    $(".a3 .d15").parent().attr("class", "gray");
                                } else {
                                    chooseComplex = null;
                                    $(".new_question").text("新建题目");
                                    $(".a3 .d15").parent().attr("class", "");
                                }
                                button.attr("class", button.attr("class") + " cur");
                                contentPanel.children().each(function() {
                                    if ($(this).attr("id").indexOf("questionContentDiv_" + id) != -1) {
                                        $(this).show();
                                        var contentEditorName = $(this).find(".qe_qs_content textarea[name]").attr("name");
                                        var focusContent = $(this).find(".qe_qs_content").find("iframe").contents().find("body");
                                        focusContent.unbind('click').click(function() {
                                            setFocusEditor(contentEditorName, "content");
                                        });
                                        //先点击一下内容区避免用户选不中内容区
                                        focusContent.click();
                                        var explanationEditorName = $(this).find("div[id^='questionSettingDiv_'] div[name='explanation'] textarea[id^='explanationContent_']").attr("name");
                                        $(this).find("div[id^='questionSettingDiv_'] div[name='explanation']").find("iframe").contents().find("body").unbind('click').click(function() {
                                            setFocusEditor(explanationEditorName, "explanation");
                                        });
                                        //选择题答案区添加事件
                                        $(this).find("textarea[name^='chooseAnswerEditor_']").each(function() {
                                            var name = $(this).attr("name");
                                            var focusAnswer = $(this).prev().find("iframe").contents().find("body");
                                            var answerList = $(this).parent().parent().parent().parent();
                                            var chooseAnswer = $(this).parent().parent().parent();
                                            focusAnswer.unbind("click").click(function() {
                                                setFocusEditor(name, "chooseAnswer");
                                                answerList.children().each(function() {
                                                    if ($(this).attr("class").indexOf("cur") != -1) {
                                                        $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                                        return true;
                                                    }
                                                });
                                                chooseAnswer.attr("class", chooseAnswer.attr("class") + " cur");
                                                chooseAnswer.focus();
                                            });
                                        });
                                        //填空题答案区添加事件
                                        $(this).find("textarea[name^='blankEditor_']").each(function() {
                                            var name = $(this).attr("name");
                                            var focusAnswer = $(this).prev().find("iframe").contents().find("body");
                                            focusAnswer.unbind('click').click(function() {
                                                setFocusEditor(name, "blankAnswer");
                                            });
                                        })
                                        contentEditors[contentEditorName].focus();
                                    } else {
                                        $(this).hide();
                                    }
                                });
                            }
                        }
                        function chooseAnswer(obj) {
                            var answerList = $(obj).parent();
                            var chooseAnswer = $(obj);
                            answerList.children().each(function() {
                                if ($(this).attr("class").indexOf("cur") != -1) {
                                    $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                    return true;
                                }
                            });
                            chooseAnswer.attr("class", chooseAnswer.attr("class") + " cur");
                            chooseAnswer.focus();
                            var focusAnswer = chooseAnswer.find("iframe").contents().find("body");
                            var focusAnswerEditorName = chooseAnswer.find("textarea[name^='chooseAnswerEditor_']").attr("name");
                            focusAnswer.unbind('click').click(function() {
                                setFocusEditor(focusAnswerEditorName, "chooseAnswer");
                                answerList.children().each(function() {
                                    if ($(this).attr("class").indexOf("cur") != -1) {
                                        $(this).attr("class", $(this).attr("class").replace("cur", ""));
                                        return true;
                                    }
                                });
                                chooseAnswer.attr("class", chooseAnswer.attr("class") + " cur");
                                chooseAnswer.focus();
                            });
                            focusAnswer.click();
                        }
                        function toggleGroup(groupId) {
                            notLeave = false;
                            var group = $("#group_" + groupId);
                            if (group.attr("class").indexOf("hide") != -1) {
                                group.attr("class", group.attr("class").replace("hide", "show"));
                            } else if (group.attr("class").indexOf("show") != -1) {
                                group.attr("class", group.attr("class").replace("show", "hide"));
                            }
                            group.nextAll().each(function() {
                                if ($(this).attr("id").indexOf("group") != -1) {
                                    return false;
                                } else {
                                    $(this).toggle();
                                }
                            });
                        }
                        function setTrimButtonTitle(value) {
                            value = value.replace(/<[^>].*?>/g, "");
                            value = value.replace(/&[^>].*?;/g, "");
                            value = value.length > 12 ? value.substring(0, 10) + "..." : value;
                            return value;
                        }
                        function changeGroupTitle(obj) {
                            var id = getButtonId(obj);
                            var button = $("div[id^='group_" + id + "']");
                            var value = $.trim($(obj).val());
                            value = setTrimButtonTitle(value);
                            button.find("span").text(value)
                        }
                        function getButtonPos(obj) {
                            var id = $(obj).attr("id");
                            var pos = id.substring(id.length - 1);
                            return pos;
                        }
                        function getButtonId(obj) {
                            var id = $(obj).attr("id");
                            var sp = id.split("_");
                            return sp[1];
                        }
                        function deleteQuestion() {
                            var chooseButton = $("#questionButtonPanel_ .cur");
                            if (chooseButton.html() != null) {
                                if (chooseButton.attr("id").indexOf("group") != -1) {
                                    //删除组
                                    var id = getButtonId(chooseButton);
                                    chooseButton.remove();
                                    var content = $("#groupContentDiv_" + id);
                                    content.remove();
                                } else {
                                    //删除题目
                                    var pos = getButtonPos(chooseButton);
                                    var id = getButtonId(chooseButton);
                                    chooseButton.find("input[name='originalQuestionId']").each(function() {
                                        var tempJson = {};
                                        var quesId = $(this).val();
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
                                            async: true
                                        });
                                    });
                                    if (chooseButton.parent().attr("class").indexOf("complex") != -1) {
                                        //复合题删除子题
                                        chooseComplex = null;
                                        chooseButton.next().children().each(function() {
                                            var cid = getButtonId($(this));
                                            $("div[id^='questionContentDiv_" + cid + "']").remove();
                                        });
                                        chooseButton.parent().remove();
                                    } else {
                                        chooseButton.remove();
                                    }
                                    var content = $("div[id^='questionContentDiv_" + id + "']");
                                    content.remove();
                                    orderQuestion(id);
                                    focusEditor = null;
                                    updateQuestionsCount();
                                }
                            }
                        }
                        function orderQuestion() {
                            var buttonPanel = $("#questionButtonPanel_");
                            var contentPanel = $("#questionContentPanel_");
                            var prePosArray = new Array();
                            buttonPanel.children("div:not([id^='group'])").each(function(i) {
                                if ($(this).attr("class").indexOf("complex") != -1) {
                                    //复合题
                                    $(this).find(".small").children().each(function(x) {
                                        var xpos = x + 1;
                                        var xid = getButtonId($(this));
                                        $(this).children().first().find(".buttonPos").text(xpos);
                                        $(this).attr("id", "button_" + xid + "_" + xpos);
                                    });
                                }
                                prePosArray[i] = getButtonPos($(this));
                                var pos = i + 1;
                                var id = getButtonId($(this));
                                $(this).children().first().find(".buttonPos").text(pos);
                                //$(this).unbind('click').click(function(){chooseQuestion(id,pos);});
                                $(this).attr("id", "button_" + id + "_" + pos);
                            });
                            contentPanel.children("div:not([id^='group'])").each(function(n) {
                                var pos = n + 1;
                                $(this).attr("id", $(this).attr("id").substring(0, $(this).attr("id").length - 1) + pos);
                                $(this).find("div[id^='radioAnswerPanel']").each(function(o) {
                                    $(this).attr("id", $(this).attr("id").substring(0, $(this).attr("id").length - 1) + pos);
                                });
                                $(this).find("div[id^='trueOrFalseAnswerPanel']").each(function(o) {
                                    $(this).attr("id", $(this).attr("id").substring(0, $(this).attr("id").length - 1) + pos);
                                });
                                $(this).find("div[id^='checkboxAnswerPanel']").each(function(o) {
                                    $(this).attr("id", $(this).attr("id").substring(0, $(this).attr("id").length - 1) + pos);
                                });
                                $(this).find("div[id^='blankAnswerPanel']").each(function(o) {
                                    $(this).attr("id", $(this).attr("id").substring(0, $(this).attr("id").length - 1) + pos);
                                });
                            });
                        }
                        function createBlankAnswer(pos) {
                            var id = (new Date().getTime()) ^ Math.random();
                            var blankHtml = "<input id=\"blankAnswer_" + pos + "_" + id + "\" type=\"text\" value=\"\" score=\"\" />";
                            blankEditors["blankEditor_" + pos].insertHtml(blankHtml);
                        }
                        function deleteBlankAnswer(id, pos) {
                            var html = $.trim(blankEditors["blankEditor_" + pos].selectedHtml());
                            if (html != null && html != "" && html.indexOf("blankAnswer_" + pos) != -1 && html.indexOf("<input") == 0 && html.indexOf("/>") == (html.length - 2)) {
                                var blankId = $(html).attr("id");
                                $("div[id^='blankAnswerPanel_" + id + "'] iframe").contents().find("input[id='" + blankId + "']").remove();
                            }
                        }
                        function editBlankAnswer(id, pos) {
                            var html = $.trim(blankEditors["blankEditor_" + pos].selectedHtml());
                            if (html != null && html != "" && html.indexOf("blankAnswer_") != -1 && html.indexOf("<input") == 0 && html.indexOf("/>") == (html.length - 2)) {
                                var blankId = $(html).attr("id");
                                var blank = $("div[id^='blankAnswerPanel_" + id + "'] iframe").contents().find("input[id='" + blankId + "']");
                                createEditBlankAnswerDialog(id, pos, blank)
                            }
                        }

                        function createEditBlankAnswerDialog(id, pos, blank) {
                            var dialog = KindEditor.dialog({
                                width: 500,
                                title: '编辑空格',
                                body: '<div style="margin:10px;">'
                                        + '正确答案:<input id="blankCorrectAnswerText_' + id + '" value="' + $.trim(blank.val()) + '" type="text" ></input>'
                                        + '分数:<input id="blankScoreAnswerText_' + id + '" value="' + blank.attr("score") + '" type="text" ></input></div>',
                                closeBtn: {
                                    name: '关闭',
                                    click: function(e) {
                                        dialog.remove();
                                    }
                                },
                                yesBtn: {
                                    name: '确定',
                                    click: function(e) {
                                        blank.val($.trim($("#blankCorrectAnswerText_" + id).val()))
                                        blank.attr("score", $("#blankScoreAnswerText_" + id).val());
                                        dialog.remove();
                                    }
                                },
                                noBtn: {
                                    name: '取消',
                                    click: function(e) {
                                        dialog.remove();
                                    }
                                }
                            });
                            return dialog;
                        }

                        function createPaperSettingDialog() {
                            notLeave = false;
                            var dialog = KindEditor.dialog({
                                width: 1050,
                                title: '设置',
                                body: '<div id=\"defaultShowOnDiv\" style="margin:10px;">'
                                        + "<div class=\"default\" >"
                                        + "<div class=\"content\">"
                                        + " <div class=\"property\">"
                                        + " <div class=\"left\">"
                                        + " <div class=\"top\">"
                                        + "  <p>题目属性</p>"
                                        + "<span>设置题目默认属性</span>"
                                        + "</div>"
                                        + "<div class=\"bottom\">"
                                        + " <div id=\"defaultCatalogDiv\" class=\"b1\">"
                                        + " <span>教材目录：</span>"
                                        + " </div>"
                                        + " <div id=\"defaultKnowledgeDiv\" class=\"b1\">   "
                                        + " <span>知 &nbsp;识 &nbsp;点：</span>"
                                        + "  </div>"
                                        + " <div class=\"b1\">"
                                        + "  <span>难   &nbsp; &nbsp; &nbsp; 度：</span>"
                                        + "  <input name=\"difficulity_default\" checked=\"true\" type=\"radio\" value=\"<%=Difficulity.NONE%>\"  > 无  "
                                        + "   <input name=\"difficulity_default\" type=\"radio\" value=\"<%=Difficulity.EASY%>\"> 容易  "
                                        + "   <input name=\"difficulity_default\" type=\"radio\" value=\"<%=Difficulity.MIDDLE%>\"> 中等 "
                                        + "  <input name=\"difficulity_default\" type=\"radio\" value=\"<%=Difficulity.HARD%>\"> 困难"
                                        + "   </div>"
                                        + "  </div>"
                                        + " </div>"
                                        + " <div class=\"right\">"
                                        + "  <div class=\"top\">"
                                        + "  <p>得分</p>"
                                        + " <span>设置题目默认总分</span>"
                                        + "  </div>"
                                        + "  <div class=\"bottom\">"
                                        + "  <div class=\"b1\">"
                                        + " <span>总分：</span>"
                                        + "  <input id=\"totalScore_default\" onkeyup=\"checkScore('default',this)\"  type=\"text\"/>"
                                        + "  </div>"
                                        + "   <!--                                <div class=\"b1\">"
                                        + "   <span>填空题：</span><input type=\"text\">"
                                        + "   </div>-->"
                                        + " </div>"
                                        + "   </div>"
                                        + "  <div class=\"clear\"></div>"
                                        + " </div>"
                                        + "  <div class=\"use\">"
                                        + "  <div class=\"b2\">"
                                        + "   <input id=\"default_new_setting\" checked=\"true\" type=\"checkbox\"><span>应用到新建的题目</span>"
                                        + "  </div>"
                                        + "  <div class=\"b2\">"
                                        + "  <input id=\"default_current_setting\" checked=\"true\" type=\"checkbox\"><span>应用到当前题目</span>"
                                        + "  </div>"
                                        + "  </div>"
                                        + "  </div>"
                                        + "  </div>"
                                        + '</div>',
                                closeBtn: {
                                    name: '关闭',
                                    click: function(e) {
                                        dialog.remove();
                                    }
                                },
                                yesBtn: {
                                    name: '确定',
                                    click: function(e) {
                                        var flag = savePaperSetting();
                                        if (flag) {
                                            dialog.remove();
                                        }
                                    }
                                },
                                noBtn: {
                                    name: '取消',
                                    click: function(e) {
                                        dialog.remove();
                                    }
                                }
                            });
                            //默认教材目录搜索
                            $("#defaultCatalogDiv").load("/module/common/resources/video/select_1.jsp",
                                    {"temp": "default", "subjectCode": subjectCode, "gradeCode": gradeCode, "publishCode": publishCode, "volumeCode": volumeCode, "bookId": bookId, "bookUnitId": bookUnitId, "bookSectionId": bookSectionId})
                            $("#defaultKnowledgeDiv").load("/module/common/resources/video/knowledge_select2.jsp", {"temp": "default", "knowledgeId": knowledge});
                            //默认难度设置
                            if (difficulity != null && difficulity != "") {
                                $("input[name='difficulity_default']").each(function() {
                                    if (difficulity.toString() == $(this).val()) {
                                        $(this).attr("checked", true);
                                    }
                                });
                            }
                            //总分设置
                            if (!isNaN(totalScore))
                                $("#totalScore_default").val(totalScore);
                            return dialog;
                        }

                        function savePaperSetting() {
                            var buttonPanel = $("#questionButtonPanel_");
                            var id = "default";
                            subjectCode = $("#subjectCode_" + id + " option:selected").val();
                            publishCode = $("#publishCode_" + id).val();
                            gradeCode = $("#gradeCode_" + id + " option:selected").val();
                            volumeCode = $("#volumeCode_" + id).val();
                            unitCode = $("#unitCode_" + id + " option:selected").val();
                            sectionCode = $("#sectionCode_" + id + " option:selected").val();
                            bookId = $("#bookId_" + id + " option:selected").val();
                            bookUnitId = $("#bookUnitId_" + id + " option:selected").val();
                            bookSectionId = $("#bookSectionId_" + id + " option:selected").val();
                            //知识点先注释
                            //knowledge = getLinkId(id);
                            difficulity = $("input[name='difficulity_" + id + "']:checked").val();
                            totalScore = $("#totalScore_" + id).val();
                            if (totalScore <= 0) {
                                if (!confirm("总分为0,所有题目的分数将被清空?")) {
                                    return false;
                                }
                            }
                            if ($("#default_new_setting").is(":checked")) {

                            }
                            if ($("#default_current_setting").is(":checked")) {
                                var questions = buttonPanel.children();
                                for (var i = 0; i < questions.length; i++) {
                                    var button = $(questions[i]);
                                    var pos = getButtonPos(button);
                                    var type = $.trim(button.find(".qe_ql_question_type input[type='hidden']").val());
                                    var bid;
                                    if (type == "<%=QuestionType.COMPLEX%>") {
                                        bid = getButtonId(button.children().first());
                                    } else {
                                        bid = getButtonId(button);
                                    }
                                    //分数设置
                                    if (totalScore != "") {
                                        var url = "${pageContext.request.contextPath}/paper/authoring/setting_score.jsp";
                                        if (type == "<%=QuestionType.COMPLEX%>") {
                                            $("#questionScore_" + bid).val(Math.round(totalScore / questions.length));
                                            var averageScore = Math.round((totalScore / questions.length) / button.find(".small").children().length);
                                            button.find(".small").children().each(function() {
                                                var subId = getButtonId($(this));
                                                var snatureDiv = $("div[id^='questionSettingDiv_" + subId + "_" + "'] .nature");
                                                var stypeDiv = snatureDiv.find("div[name='score']");
                                                if (stypeDiv.html() == null || stypeDiv.html() == "") {
                                                    //没有加载过的
                                                    var data = {"id": bid};
                                                    data["score"] = averageScore;
                                                    $.ajax({
                                                        url: url,
                                                        type: "POST",
                                                        data: data,
                                                        async: false,
                                                        success: function(html) {
                                                            snatureDiv.append(html);
                                                            stypeDiv = snatureDiv.find("div[name='score']");
                                                            stypeDiv.hide();
                                                        }
                                                    });
                                                } else {
                                                    $("#questionScore_" + subId).val(averageScore);
                                                }
                                            });
                                        } else {
                                            var natureDiv = $("#questionSettingDiv_" + bid + "_" + pos + " .nature");
                                            var typeDiv = natureDiv.find("div[name='score']");
                                            if (typeDiv.html() == null || typeDiv.html() == "") {
                                                //没有加载过的
                                                var data = {"id": bid};
                                                data["score"] = Math.round(totalScore / questions.length);
                                                $.ajax({
                                                    url: url,
                                                    type: "POST",
                                                    data: data,
                                                    async: false,
                                                    success: function(html) {
                                                        natureDiv.append(html);
                                                        typeDiv = natureDiv.find("div[name='score']");
                                                        typeDiv.hide();
                                                    }
                                                });
                                            } else {
                                                $("#questionScore_" + bid).val(Math.round(totalScore / questions.length));
                                            }
                                        }
                                    }
                                    //教材目录设置
                                    $("div[id^='questionSettingDiv_" + bid + "'] [class='catalog']").load("/module/common/resources/video/select_1.jsp",
                                            {"temp": bid, "subjectCode": subjectCode, "gradeCode": gradeCode, "volumeCode": volumeCode, "publishCode": publishCode, "bookId": bookId, "bookUnitId": bookUnitId, "bookSectionId": bookSectionId})
                                    //知识点设置        
                                    $("div[id^='questionSettingDiv_" + bid + "'] [class='knowledge']").load("/module/common/resources/video/knowledge_select2.jsp",
                                            {"temp": bid, "knowledgeId": knowledge})
                                    //难度设置        
                                    $("#difficulity_" + bid).children().each(function() {
                                        if ($(this).val() == difficulity) {
                                            $(this).attr("selected", "true");
                                        }
                                    });
                                }
                            }
                            return true;
                        }

                        function setFocusEditor(name, type) {
                            if (type == "content") {
                                //内容区的编辑器绑定
                                focusEditor = contentEditors[name];
                            } else if (type == "explanation") {
                                //解释区的编辑器绑定
                                focusEditor = explanationEditors[name];
                            } else if (type == "blankAnswer") {
                                //填空题答案区的编辑器绑定
                                focusEditor = blankEditors[name];
                            } else if (type == "chooseAnswer") {
                                //选择题答案区的编辑器绑定
                                focusEditor = chooseAnswerEditors[name];
                            }
                        }
                        function inputImage() {
                            notLeave = false;
                            if (focusEditor == null) {
                                alert("请点击要插入图片的内容区域")
                            } else {
                                focusEditor.clickToolbar("image");
                            }
                        }
                        function inputMath() {
                            notLeave = false;
                            if (focusEditor == null) {
                                alert("请点击要插入公式的内容区域")
                            } else {
                                focusEditor.clickToolbar("jmeditor");
                            }
                        }
                        function inputXtjyPaperMedia() {
                            notLeave = false;
                            if (focusEditor == null) {
                                alert("请点击要插入音视频的内容区域")
                            } else {
                                focusEditor.clickToolbar("xtjyPaperMedia");
                            }
                        }
                        function appendMediaDiv(url) {
                            var find = false;
                            var focusName;
                            //在题干区域查找正在输入的编辑器
                            for (var ced in contentEditors) {
                                if (contentEditors[ced] == focusEditor) {
                                    focusName = ced;
                                    find = true;
                                    break;
                                }
                            }
                            //在题干区域查找不到正在输入的编辑器的话就在答案区域查找
                            if (!find) {
                                for (var caed in chooseAnswerEditors) {
                                    if (chooseAnswerEditors[caed] == focusEditor) {
                                        focusName = caed;
                                        find = true;
                                        break;
                                    }
                                }
                            }
                            var suffix = (url.substring(url.lastIndexOf(".") + 1)).toLowerCase();
                            var logo = suffix == "mp3" ? "add_audio" : "add_video";
                            var hidden = "<input type='hidden' value='" + url + "' />";
                            var html = "<div class='mediaDiv'><div onclick=\"playMedia('" + url + "')\" class=\"" + logo + "\" style=\"position:absolute;right:20px;top:20px;width:10%;height:120px;\">" + hidden + "</div>"
                                    + "<a onclick=\"removeMedia(this)\" href=\"javascript:void(0)\" class=\"close_video\" style=\"position:absolute;right:20px;top:20px;\"></a></div>";
                            $("textarea[name='" + focusName + "']").parent().append(html);
                        }
                        function removeMedia(obj) {
                            $(obj).parent().remove();
                        }
                        function playMedia(url) {
                            var width;
                            var mediaHtml;
                            var suffix = (url.substring(url.lastIndexOf(".") + 1)).toLowerCase();
                            if (suffix == "mp3") {
                                width = 322;
                                mediaHtml = '<audio autoplay="autoplay" width="500px" height="400px" controls="controls">'
                                        + '<source src="' + url + '" type="audio/ogg">'
                                        + '<source src="' + url + '" type="audio/mpeg">'
                                        + '你的浏览器不支持html5音频播放标签.'
                                        + '</audio>';
                            } else {
                                width = 521;
                                mediaHtml = '<video autoplay="autoplay" width="500px" height="400px" controls="controls">'
                                        + '<source src="' + url + '" type="video/ogg">'
                                        + '<source src="' + url + '" type="video/mp4">'
                                        + '你的浏览器不支持html5视频播放标签.'
                                        + '</video>';
                            }
                            var dialog = KindEditor.dialog({
                                width: width,
                                title: '音视频播放',
                                body: '<div style="margin:10px;">'
                                        + mediaHtml
                                        + '</div>',
                                closeBtn: {
                                    name: '关闭',
                                    click: function(e) {
                                        dialog.remove();
                                    }
                                }
                            });
                        }
                        function saveBlankAnswer(id, pos) {
                            var html = $.trim(blankEditors["blankEditor_" + pos].selectedHtml());
                            if (html != null && html != "" && html.indexOf("blankAnswer_" + pos) != -1 && html.indexOf("<input") == 0 && html.indexOf("/>") == (html.length - 2)) {
                                var blankId = $(html).attr("id");
                                var blank = $("div[id^='blankAnswerPanel_" + id + "'] iframe").contents().find("input[id='" + blankId + "']");
                                blank.val();
                            }
                        }
                        function createAnswer(obj, type, pos) {
                            var answerList = $(obj).parent().prev().find("[id]");
                            var id = getButtonId(answerList);
                            var className = answerList.children().last().attr("class");
                            className = className == null ? "qe_qs_cm_list_item" : className;
                            var answerClass = className.indexOf("odd") != -1 ? "qe_qs_cm_list_item" : "qe_qs_cm_list_item_odd";
                            var answerHtml = $("<div onclick=\"chooseAnswer(this)\"  class=\"" + answerClass + "\"></div>");
                            var innerDiv = $("<div class=\"qe_qs_cm_list_item_inner\"></div>");
                            var typeHtml;
                            if (type == "<%=QuestionType.CHECKBOX%>") {
                                typeHtml = "checkbox";
                            } else if (type == "<%=QuestionType.RADIO%>") {
                                typeHtml = "radio";
                            }
                            chooseAnswerEditorNo = chooseAnswerEditorNo + 1;
                            var inputHtml = $("<dt><input type=\"" + typeHtml + "\" class=\"qe_qs_cm_list_item_inner_radio\" name=\"" + typeHtml + "CorrectAnswer_" + id + "_" + pos + "\"/></dt><dd>"
                                    + "<textarea name=\"chooseAnswerEditor_" + chooseAnswerEditorNo + "\" style=\"width:99.7%;height:10px;visibility:hidden;\"></textarea></dd>");
                            innerDiv.append(inputHtml);
                            answerHtml.append(innerDiv);
                            answerList.append(answerHtml);
                            var chooseAnswerEditor = KindEditor.create("textarea[name='chooseAnswerEditor_" + chooseAnswerEditorNo + "']", chooseAnswerEditorSetting);
                            chooseAnswerEditors["chooseAnswerEditor_" + chooseAnswerEditorNo] = chooseAnswerEditor;
                            $("textarea[name='chooseAnswerEditor_" + chooseAnswerEditorNo + "']").prev().css("height", "190%");
                            chooseAnswer(answerHtml);
                        }
                        function deleteAnswer(obj) {
                            var answerList = $(obj).parent().prev().find("[id]");
                            var choose = answerList.children("[class$='cur']");
                            if (choose.html() != null) {
                                var editorName = choose.find("textarea[name^='chooseAnswerEditor_']").attr("name");
                                KindEditor.remove("textarea[name='" + editorName + "']")
                                chooseAnswerEditors[editorName] = null;
                                focusEditor = null;
                                choose.remove();
                            }
                        }
                        function upAnswer(obj) {
                            var answerList = $(obj).parent().prev().find(".qe_qs_cm_list");
                            var choose = answerList.children("[class$='cur']");
                            var editorName = choose.find("textarea[name^='chooseAnswerEditor_']").attr("name")
                            var editor = chooseAnswerEditors[editorName];
                            var preValue = editor.html();
                            if (choose.html() != null) {
                                if (choose.prev().length > 0) {
                                    KindEditor.remove("textarea[name='" + editorName + "']");
                                    var tmp = choose.clone();
                                    var oo = choose.prev();
                                    choose.remove();
                                    oo.before(tmp);
                                    editor = KindEditor.create("textarea[name='" + editorName + "']", chooseAnswerEditorSetting);
                                    chooseAnswerEditors[editorName] = editor;
                                    editor.html(preValue);
                                    $("textarea[name='" + editorName + "']").prev().css("height", "190%");
                                    chooseAnswer(tmp)
                                }
                            }
                        }
                        function downAnswer(obj) {
                            var answerList = $(obj).parent().prev().find(".qe_qs_cm_list");
                            var choose = answerList.children("[class$='cur']");
                            var editorName = choose.find("textarea[name^='chooseAnswerEditor_']").attr("name")
                            var editor = chooseAnswerEditors[editorName];
                            var preValue = editor.html();
                            if (choose.html() != null) {
                                if (choose.next().length > 0) {
                                    KindEditor.remove("textarea[name='" + editorName + "']");
                                    var tmp = choose.clone();
                                    var oo = choose.next();
                                    choose.remove();
                                    oo.after(tmp);
                                    editor = KindEditor.create("textarea[name='" + editorName + "']", chooseAnswerEditorSetting);
                                    chooseAnswerEditors[editorName] = editor;
                                    editor.html(preValue);
                                    $("textarea[name='" + editorName + "']").prev().css("height", "190%");
                                    chooseAnswer(tmp)
                                }
                            }
                        }
                        function upQuestion() {
                            var chooseButton = $("#questionButtonPanel_ .cur");
                            if (chooseButton.html() != null) {
                                if (chooseButton.parent().attr("class").indexOf("complex") != -1) {
                                    //复合题
                                    chooseButton = chooseButton.parent();
                                }
                                if (chooseButton.prev().length > 0) {
                                    var tmp = chooseButton.clone();
                                    var oo = chooseButton.prev();
                                    chooseButton.remove();
                                    oo.before(tmp);
                                    orderQuestion();
                                }
                            }
                        }
                        function downQuestion() {
                            var chooseButton = $("#questionButtonPanel_ .cur");
                            if (chooseButton.html() != null) {
                                if (chooseButton.parent().attr("class").indexOf("complex") != -1) {
                                    //复合题
                                    chooseButton = chooseButton.parent();
                                }
                                if (chooseButton.next().length > 0) {
                                    var tmp = chooseButton.clone();
                                    var oo = chooseButton.next();
                                    chooseButton.remove();
                                    oo.after(tmp);
                                    orderQuestion();
                                }
                            }
                        }
                        function updateQuestionsCount() {
                            var buttonPanel = $("#questionButtonPanel_");
                            var count = buttonPanel.children("div:not([id^='group_'])").size();
                            $("#questionsCountSpan_").text(count);
                        }
                        function getQuestionsJsonImpl(survey, questions, extraContent) {
                            var notInload = "<%=QuestionConstants.NOT_INLOAD_FLAG%>";
                            var qscore = 0;
                            for (var i = 0; i < questions.length; i++) {
                                var qJson = {};
                                var button = $(questions[i]);
                                var pos = getButtonPos(button);
                                var type = $.trim(button.find(".qe_ql_question_type input[type='hidden']").val());
                                var id;
                                if (type == "<%=QuestionType.COMPLEX%>") {
                                    id = getButtonId(button.children().first())
                                } else {
                                    id = getButtonId(button);
                                }
                                KindEditor.sync("textarea[id^='questionContent_" + id + "']");
                                var content = $.trim($("textarea[id^='questionContent_" + id + "']").val());
                                var explanation;
                                if ($("div[id^='questionSettingDiv_'] div[name='explanation']").html() != null) {
                                    KindEditor.sync("textarea[id^='explanationContent_" + id + "']");
                                    explanation = $.trim($("textarea[id^='explanationContent_" + id + "']").val());
                                } else {
                                    explanation = notInload;
                                }
                                var questionId = button.find("input[name='questionId']").val();
                                var originalQuestionId = button.find("input[name='originalQuestionId']").val();
                                if (type == "<%=QuestionType.RADIO%>") {
                                    var answer = [];
                                    var correctAnswer = [];
                                    var answerMedia = [];
                                    $("div[id^='radioAnswerPanel_" + id + "']").children().each(function(i) {
                                        var editorName = $(this).find("textarea[name^=chooseAnswerEditor_]").attr("name");
                                        var editor = chooseAnswerEditors[editorName];
                                        var inputValue = $.trim(editor.html());
                                        var radio = $(this).find("input[type='radio']:checked").val();
                                        var mediaDiv = $(this).find(".mediaDiv");
                                        if (mediaDiv.html() != null && mediaDiv.html() != "") {
                                            answerMedia.push(mediaDiv.find("input[type='hidden']").val());
                                        }
                                        if (inputValue != null) {
                                            answer.push(inputValue);
                                        }
                                        if (radio != null) {
                                            correctAnswer.push(letters[i]);
                                        }
                                    });
                                    qJson["answerMediaUrl"] = answerMedia;
                                    qJson["correctAnswer"] = correctAnswer;
                                    qJson["answer"] = answer;
                                } else if (type == "<%=QuestionType.CHECKBOX%>") {
                                    var answer = [];
                                    var correctAnswer = [];
                                    var answerMedia = [];
                                    $("div[id^='checkboxAnswerPanel_" + id + "']").children().each(function(i) {
                                        var editorName = $(this).find("textarea[name^=chooseAnswerEditor_]").attr("name");
                                        var editor = chooseAnswerEditors[editorName];
                                        var inputValue = $.trim(editor.html());
                                        var checkbox = $(this).find("input[type='checkbox']:checked").val();
                                        var mediaDiv = $(this).find(".mediaDiv");
                                        if (mediaDiv.html() != null && mediaDiv.html() != "") {
                                            answerMedia.push(mediaDiv.find("input[type='hidden']").val());
                                        }
                                        if (inputValue != null) {
                                            answer.push(inputValue);
                                        }
                                        if (checkbox != null) {
                                            correctAnswer.push(letters[i]);
                                        }
                                    });
                                    qJson["answerMediaUrl"] = answerMedia;
                                    qJson["correctAnswer"] = correctAnswer;
                                    qJson["answer"] = answer;
                                } else if (type == "<%=QuestionType.TRUEORFALSE%>") {
                                    var answer = [];
                                    var correctAnswer = [];
                                    var answerMedia = [];
                                    $("div[id^='trueOrFalseAnswerPanel_" + id + "']").children().each(function(i) {
                                        var editorName = $(this).find("textarea[name^=chooseAnswerEditor_]").attr("name");
                                        var editor = chooseAnswerEditors[editorName];
                                        var inputValue = $.trim(editor.html());
                                        var radio = $(this).find("input[type='radio']:checked").val();
                                        var mediaDiv = $(this).find(".mediaDiv");
                                        if (mediaDiv.html() != null && mediaDiv.html() != "") {
                                            answerMedia.push(mediaDiv.find("input[type='hidden']").val());
                                        }
                                        if (inputValue != null) {
                                            answer.push(inputValue);
                                        }
                                        if (radio != null) {
                                            correctAnswer.push(letters[i]);
                                        }
                                    });
                                    qJson["answerMediaUrl"] = answerMedia;
                                    qJson["correctAnswer"] = correctAnswer;
                                    qJson["answer"] = answer;
                                } else if (type == "<%=QuestionType.BLANK%>") {
                                    var textarea = $("div[id^='blankAnswerPanel_" + id + "']").find("textarea[name^='blankEditor']");
                                    var editor = blankEditors[textarea.attr("name")];
                                    var answer = [];
                                    answer.push(editor.html().replace(/value="[\s\S]*?"/gm, ""));
                                    var correctAnswer = [];
                                    var blankList = $("div[id^='blankAnswerPanel_" + id + "'] iframe").contents().find("input[type='text']");
                                    $.each(blankList, function(o) {
                                        correctAnswer.push($.trim($(this).val()));
                                    });
                                    qJson["correctAnswer"] = correctAnswer;
                                    qJson["answer"] = answer;
                                } else if (type == "<%=QuestionType.COMPLEX%>") {
                                    var subSurvey = [];
                                    subSurvey = getQuestionsJsonImpl(subSurvey, button.find(".small").children(), content);
                                    qJson["questions"] = subSurvey;
                                } else if (type == "<%=QuestionType.WORD%>") {

                                }
                                qJson["questionId"] = questionId;
                                qJson["originalQuestionId"] = originalQuestionId;
                                qJson["content"] = content;
                                if (extraContent != null) {
                                    qJson["extraContent"] = extraContent;
                                }
                                qJson["type"] = type;
                                qJson["pos"] = i + 1;
                                qJson["explanation"] = explanation;
                                qJson["score"] = $("#questionScore_" + id).val();
                                qscore = qscore + qJson["score"];
                                qJson["difficulity"] = parseInt($("#difficulity_" + id).val());
//                                qJson["subjectCode"] = $("#subjectCode_" + id + " option:selected").val();
//                                qJson["publishCode"] = $("#publishCode_" + id + " option:selected").val();
//                                qJson["gradeCode"] = $("#gradeCode_" + id + " option:selected").val();
//                                qJson["volumeCode"] = $("#volumeCode_" + id + " option:selected").val();
//                                qJson["unitCode"] = $("#unitCode_" + id + " option:selected").val();
//                                qJson["sectionCode"] = $("#sectionCode_" + id + " option:selected").val();
                                qJson["subjectCode"] = $("#subjectCode_" + id + " option:selected").val();
                                qJson["publishCode"] = $("#publishCode_" + id).val();
                                qJson["gradeCode"] = $("#gradeCode_" + id + " option:selected").val();
                                qJson["volumeCode"] = $("#volumeCode_" + id).val();
                                qJson["bookId"] = $("#bookId_" + id + " option:selected").val();
                                qJson["bookUnitId"] = $("#bookUnitId_" + id + " option:selected").val();
                                qJson["bookSectionId"] = $("#bookSectionId_" + id + " option:selected").val();
                                //知识点先注释
                                //qJson["knowledge"] = getLinkId(id);
                                var mediaDiv = $("textarea[id^='questionContent_" + id + "']").parent().find(".mediaDiv");
                                if (mediaDiv.html() != null && mediaDiv.html() != "") {
                                    qJson["contentMediaUrl"] = mediaDiv.find("input[type='hidden']").val();
                                }
                                var group = button.prevAll("div[id^='group_']:first");
                                if (group != null && group.html() != null) {
                                    qJson["groupId"] = group.attr("id").split("_")[1];
                                    qJson["groupTitle"] = $.trim($("#groupContent_" + qJson["groupId"]).val());
                                }
                                survey.push(qJson);
                            }
                            return survey;
                        }
                        function getQuestionsJson() {
                            var buttonPanel = $("#questionButtonPanel_");
                            var contentPanel = $("#questionContentPanel_");
                            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                            var survey = [];
                            var questions = buttonPanel.children("div:not([id^='group_'])");
                            survey = getQuestionsJsonImpl(survey, questions)
                            return survey;
                        }
                        function gotoQuestionsLib() {
                            var paperJson = saveTempJson();
                            //移除关闭页面事件
                            window.onbeforeunload = function() {
                            };
                            window.onunload = function() {
                            };
                            //模拟a标签点击来代替window.open,解决浏览器阻止弹出窗口
                            var a = document.createElement("a");
                            a.setAttribute("href", "${pageContext.request.contextPath}/common/paper/paperAction_questionLib.action?paperId=" + paperJson["paperId"] + "&quesLength=" + paperJson["questionsJson"].length + "&paperType=" + paperJson["paperType"]);
                            a.setAttribute("target", "_self");
                            a.setAttribute("id", "paperLibWin");
                            document.body.appendChild(a);
                            a.click();
                        }

                        function saveTempJson() {
                            var paperJson = {};
                            //<s:property value="#session.admin_session_Key!=null?@com.gzxtjy.portal.session.constants.SessionConstants@SYS_ADMIN_ID:#session.USERINFO.id"/>
                            paperJson["userId"] = "1";
                            paperJson["paperId"] = $("#paperId").val();
                            paperJson["paperTitle"] = $.trim($("#paperTitle").val());
                            paperJson["paperType"] = parseInt($("#paperType").val());
                            paperJson["questionsJson"] = getQuestionsJson();
                            paperJson["difficulity"] = difficulity != null ? difficulity.toString() : "";
                            paperJson["subjectCode"] = subjectCode;
                            paperJson["publishCode"] = publishCode;
                            paperJson["gradeCode"] = gradeCode;
                            paperJson["volumeCode"] = volumeCode;
                            paperJson["unitCode"] = unitCode;
                            paperJson["sectionCode"] = sectionCode;
                            $.ajax({
                                url: "${pageContext.request.contextPath}/common/paper/paperAction_saveTempPaper.action",
                                type: "POST",
                                data: {"tempJson": JSON.stringify(paperJson)},
                                async: false //同步
                            });
                            return paperJson;
                        }

                        function checkScore(id, obj) {
                            var partten = /^[0-9]*$/;
                            var value = $(obj).val();
                            if (!partten.test(value)) {
                                $(obj).val("");
                                $("#questionScore_" + id).text("");
                                if ($(obj).next().html() == null) {
                                    $(obj).parent().append("<font color='red'>请输入有效数字</font>");
                                }
                            } else {
                                if (value != "") {
                                    $("#questionScore_" + id).text(value + "分");
                                } else {
                                    $("#questionScore_" + id).text(value);
                                }
                                $(obj).next().remove();
                            }
                        }

                        function checkSubmit() {
                            var title = $.trim($("#paperTitle").val());
                            var actList = $("#questionButtonPanel_").children().size();
                            if (title == null || title == "") {
                                alert("请输入标题");
                                return false;
                            }
                            if (actList == null || actList <= 0) {
                                alert("请创建题目");
                                return false;
                            }
                            return true;
                        }

                        function submitData() {
                            notLeave = false;
                            if (checkSubmit()) {
                                openLoadingOverlay("save");
                            }
                        }

                        function savePaper() {
                            var paperJson = {};
                            var truePaperId = false;
                            paperJson["userId"] = "1";
                            paperJson["paperId"] = $("#paperId").val();
                            paperJson["paperTitle"] = $.trim($("#paperTitle").val());
                            if (paperJson["paperTitle"] == "") {
                                alert("请输入标题");
                                closeLoadingOverlay();
                                return false;
                            }
                            paperJson["paperType"] = parseInt($("#paperType").val());
                            paperJson["questions"] = getQuestionsJson();
                            if (paperJson["questions"].length <= 0) {
                                alert("请创建题目");
                                closeLoadingOverlay();
                                return false;
                            }
                            if(difficulity!=null){
                                paperJson["difficulity"] = parseInt(difficulity);
                            }
                            paperJson["score"] = isNaN(totalScore) ? null : totalScore;
                            paperJson["subjectCode"] = subjectCode;
                            paperJson["publishCode"] = publishCode;
                            paperJson["gradeCode"] = gradeCode;
                            paperJson["volumeCode"] = volumeCode;
                            paperJson["unitCode"] = unitCode;
                            paperJson["sectionCode"] = sectionCode;
                            paperJson["bookId"] = bookId;
                            paperJson["bookUnitId"] = bookUnitId;
                            paperJson["bookSectionId"] = bookSectionId;
                            paperJson["knowledge"] = knowledge;
                            $.ajax({
                                url: "${pageContext.request.contextPath}/common/paper/paperAction_savePaper.action",
                                type: "POST",
                                data: {"paperJson": JSON.stringify(paperJson)},
                                async: false, //同步
                                dataType: "json",
                                success: function(json) {
                                    if(json.errorMsg!=null&&json.errorMsg!=""){
                                        alert("保存失败,请与管理员联系")
                                        return;
                                    }
                                    updateCompare(json.questions)
                                    var paperId = json.paperId;
                                    $("#paperId").val(paperId);
                                    truePaperId = paperId;
                                    alert("保存成功");
                                    closeLoadingOverlay();
                                    //电子书包处理
                                    if ("${requestScope.schoolBag}" != "") {
                                        addToSchoolBag(paperJson["paperTitle"], "${requestScope.schoolBag}", paperId);
                                    }
                                    //移动微课堂处理
                                    if ("${requestScope.micoCourse}" != "") {  
                                        addToUserPaperLog(paperJson["paperTitle"], paperId);
                                    }
                                }
                            });
                            return truePaperId;
                        }

                        function openLoadingOverlay(fname, paperId) {
                            //初始化数据遮罩层
                            var top = (document.body.scrollTop + document.body.clientHeight / 2 - 600 / 2) + "px";
                            var left = (document.body.scrollLeft + document.body.clientWidth / 2 - 100 / 2);
                            if (fname == "preview") {
                                loadingOverlayObj = $("#previewLoading").overlay({
                                    api: true,
                                    closeOnClick: false,
                                    oneInstance: true,
                                    left: left,
                                    top: '30%',
                                    expose: {
                                        color: '#333',
                                        opacity: 0.7,
                                        zIndex: 10000
                                    },
                                    onLoad: function() {
                                        ajaxPreview();
                                    }
                                });
                            } else if (fname == "save") {
                                loadingOverlayObj = $("#saveLoading").overlay({
                                    api: true,
                                    closeOnClick: false,
                                    oneInstance: true,
                                    left: left,
                                    top: '30%',
                                    expose: {
                                        color: '#333',
                                        opacity: 0.7,
                                        zIndex: 10000
                                    },
                                    onLoad: function() {
                                        savePaper();
                                    }
                                });
                            } else if (fname == "edit") {
                                loadingOverlayObj = $("#editLoading").overlay({
                                    api: true,
                                    closeOnClick: false,
                                    oneInstance: true,
                                    left: left,
                                    top: '30%',
                                    expose: {
                                        color: '#333',
                                        opacity: 0.7,
                                        zIndex: 10000
                                    },
                                    onLoad: function() {
                                        loadPaperJson(paperId)
                                    }
                                });
                            }
                            loadingOverlayObj.load();
                            $("#" + fname + "Loading").show();
                        }

                        function closeLoadingOverlay() {
                            loadingOverlayObj.close();
                        }

                        function updateCompare(ques) {
                            $("#questionButtonPanel_").children("div:not([id^='group'])").each(function(i) {
                                if ($(this).attr("class") == "complex") {
                                    $(this).children().each(function(s) {
                                        if ($(this).attr("class") == "small") {
                                            $(this).children().each(function(x) {
                                                if ($(this).find("input[name='questionId']").html() == null) {
                                                    $(this).append("<input type='hidden' name='questionId' value='" + ques[i].questions[x].questionId + "'/>");
                                                }
                                            });
                                        } else {
                                            if ($(this).find("input[name='questionId']").html() == null) {
                                                $(this).append("<input type='hidden' name='questionId' value='" + ques[i].questionId + "'/>");
                                            }
                                        }
                                    })
                                } else {
                                    if ($(this).find("input[name='questionId']").html() == null) {
                                        $(this).append("<input type='hidden' name='questionId' value='" + ques[i].questionId + "'/>");
                                    }
                                }
                            });
                        }

                        function previewPaper() {
                            notLeave = false;
                            //先保存后预览
                            var paperJson = saveTempJson();
                            if (paperJson["questionsJson"].length <= 0) {
                                alert("请创建题目");
                                return false;
                            }
                            //模拟a标签点击来代替window.open,解决浏览器阻止弹出窗口
                            var a = document.createElement("a");
                            a.setAttribute("href", "${pageContext.request.contextPath}/common/paper/paperAction_studyPaper.action?preview=true&pos=1");
                            a.setAttribute("target", "_blank");
                            a.setAttribute("id", "previewWin");
                            document.body.appendChild(a);
                            a.click();
                        }

                        function loadPaperJson(paperId) {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/common/paper/paperAction_loadPaperJson.action",
                                type: "POST",
                                data: {"paperId": paperId},
                                dataType: "json",
                                async: false, //不异步
                                success: function(paperJson) {
                                    $("#paperId").val(paperJson.paperId);
                                    $("#paperTitle").val(paperJson.paperTitle);
                                    $("#paperType").val(paperJson.paperType);
                                    subjectCode = paperJson.subjectCode;
                                    publishCode = paperJson.publishCode;
                                    gradeCode = paperJson.gradeCode;
                                    volumeCode = paperJson.volumeCode;
                                    unitCode = paperJson.unitCode;
                                    sectionCode = paperJson.sectionCode;
                                    bookId = paperJson.bookId;
                                    bookUnitId = paperJson.bookUnitId;
                                    bookSectionId = paperJson.bookSectionId;
                                    difficulity = paperJson.difficulity;
                                    totalScore = paperJson.score;
                                    knowledge = paperJson.knowledge;
                                    var questionList = paperJson.questions;
                                    for (var i = 0; i < questionList.length; i++) {
                                        var question = questionList[i];
                                        createQuestion(question);
                                    }
                                    closeLoadingOverlay();
                                }
                            });
                        }

                        //加入电子书包方法
                        function addToSchoolBag(title, type, relateId) {
                            var url = "/teacher/portal/prepareLessonAction_addToPrepare.action";
                            var data = {
                                "relateId": relateId,
                                "type": type,
                                "originalType": "paper",
                                "lessonId": "${requestScope.lessonId}",
                                "title": title.replace(/<[^>]+>/g, "") //去除所有html标记
                            };
                            jQuery.ajax({
                                type: "POST",
                                url: url,
                                async: false,
                                data: data,
                                success: function() {
                                    location.href = document.referrer;
                                }
                            });
                        }

                        //加入移动微课堂方法
                        function addToUserPaperLog(title, relateId) {
                            var url = "${pageContext.request.contextPath}/common/paper/paperAction_addToVideoPaper.action";
                            var data = {
                                "paperId": relateId,
                                "bookUnitId": bookUnitId,
                                "bookSectionId": bookSectionId,
                                "title": title.replace(/<[^>]+>/g, "") //去除所有html标记
                            };
                            jQuery.ajax({
                                type: "POST",
                                url: url,
                                async: false,
                                data: data,
                                success: function() {
                                }
                            });
                        }
                        function appendTempQuestion() {
                            jQuery.ajax({
                                type: "POST",
                                url: "${pageContext.request.contextPath}/common/paper/paperAction_buildUpQuestion.action",
                                async: false,
                                dataType: "json",
                                success: function(paperJson) {
                                    difficulity = "${sessionScope.paper_temp_paper_vo.difficulity}";
                                    subjectCode = "${sessionScope.paper_temp_paper_vo.subjectCode}";
                                    publishCode = "${sessionScope.paper_temp_paper_vo.publishCode}";
                                    gradeCode = "${sessionScope.paper_temp_paper_vo.gradeCode}";
                                    volumeCode = "${sessionScope.paper_temp_paper_vo.volumeCode}";
                                    unitCode = "${sessionScope.paper_temp_paper_vo.unitCode}";
                                    sectionCode = "${sessionScope.paper_temp_paper_vo.sectionCode}";
                                    var questionList = paperJson.questions;
                                    for (var i = 0; i < questionList.length; i++) {
                                        var question = questionList[i];
                                        createQuestion(question);
                                    }
                                }
                            });
                        }

                        function browserCheck() {
                            var brow = $.browser;
                            var href = "${pageContext.request.contextPath}/paper/authoring/browser_check.jsp";;
                            if (!brow.msie) {
                                location.href = href;
                            }else{
                                if(parseInt(brow.version)<=8){
                                    location.href = href;
                                }
                            }
                        }

                        $(function() {
                            //判断浏览器类型
                            //browserCheck();

                            window.onbeforeunload = function() {
                                if (notLeave) {
                                    var str = "该操作可能会导致题目数据没有保存，您是否确认?";
                                    var evt = window.event || arguments[0];
                                    var userAgent = navigator.userAgent;
                                    if (userAgent.indexOf("MSIE") > 0) {
                                        var n = window.event.screenX - window.screenLeft;
                                        var b = n > document.documentElement.scrollWidth - 20;
                                        if (b && window.event.clientY < 0 || window.event.altKey) {
                                            window.event.returnValue = (str);
                                        } else {
                                            return (str);
                                        }
                                    } else if (userAgent.indexOf("Firefox") > 0) {
                                        return (str);
                                    }
                                } else {
                                    notLeave = true;
                                }
                            };

                            window.onunload = function() {
                                jQuery.ajax({
                                    type: "POST",
                                    url: "${pageContext.request.contextPath}/common/paper/paperAction_closeCreatePaper.action",
                                    cache: false,
                                    async: false
                                });
                            }

                            var paperId = "<%=request.getParameter("paperId")%>"
                            if (paperId != "" && paperId != "null" && paperId != "undefined") {
                                if ("${requestScope.failMsg}" == "paperNotExist") {
                                    alert("文件不存在或已被删除");
                                    //移除关闭页面事件
                                    window.onbeforeunload = function() {
                                    };
                                    window.onunload = function() {
                                    };
                                    window.opener = null;
                                    window.close();
                                } else {
                                    openLoadingOverlay("edit", paperId);
                                }
                            }
                            if (<%=(paperVo != null)%>) {
                                //读取临时保存的选题数据
                                appendTempQuestion();
                            }
                            //调整创建页面区域高度以适应屏幕
                            $(".qe_question_list").css("min-height", clinetHight = document.body.clientHeight - 138);
                            $(".qe_question_content").css("min-height", clinetHight = document.body.clientHeight - 138);
                        });
        </script>
    </div>
</body>
