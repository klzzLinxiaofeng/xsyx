<%-- 
    Document   : surveytoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page import="com.gzxtjy.lads.constants.surveyToolCons.QuestionType"%>
<%@page import="com.gzxtjy.lads.constants.surveyToolCons.AnswerConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<table class="rt">
    <tr>
        <td width="100" align="right"><strong>标　　题：</strong></td>
        <td>
            <input onkeyup="changeTitle('<s:property value="#request.id"/>',this.value)" name="surveyTitle_<s:property value="#request.id"/>" id="surveyTitle_<s:property value="#request.id"/>" value="<s:property value="#request.title"/>" type="text"/>
        </td>
    </tr>
    <tr>
        <td width="100" align="right"><strong>分　　数：</strong></td>
        <td>
            <input onkeyup="checkScore('<s:property value="#request.id"/>',this)" name="surveyScore_<s:property value="#request.id"/>" id="surveyScore_<s:property value="#request.id"/>" value="<s:property value="#request.surveyScore"/>" type="text"/>
        </td>
    </tr>
    <tr>
        <td width="100" align="right"><strong>描　　述：</strong></td>
        <td>
            <textarea style="border:1px solid grey" name="surveyDescription_<s:property value="#request.id"/>" id="surveyDescription_<s:property value="#request.id"/>" cols="90" rows="1"><s:property value="#request.surveyDescription"/></textarea>
        </td>
    </tr>
</table>
<div class="questionnaire_editor">
    <div class="qe_toolbar">
        <div class="qe_toolbar_inner">
            <div class="qe_tb_group">
                <div class="qe_tb_group_items">
                    <div class="qe_tb_group_item">
                    	<div class="qe_tb_gi_selcet">
                        <div class="sigle_sel" onclick="createSurveyQuestion('<s:property value="#request.id"/>',{'type':'radio'})">
                        	单项选择题
                        </div>
                        </div>
                        <div class="qe_tb_gi_selcet">
                        <div class="mult_sel" onclick="createSurveyQuestion('<s:property value="#request.id"/>',{'type':'checkbox'})">
                        	多项选择题
                        </div>
                        </div>
                        <div class="qe_tb_gi_selcet">
                        <div class="ans" onclick="createSurveyQuestion('<s:property value="#request.id"/>',{'type':'word'})">
                        	简答题
                   		</div>
                   		</div>
                    </div>
                </div>
                <div class="qe_tb_group_title">新建题目</div>
            </div>
        </div>
    </div>
    <div class="qe_content">
        <div class="qe_question_list">
            <div class="qe_ql_title">
                <div class="qe_ql_title_inner">题目列表</div>
            </div>
            <div id="surveyQuestionButtonPanel_<s:property value="#request.id"/>" class="qe_ql_questions">

            </div>
            <div class="qe_ql_info">
                <div class="qe_ql_info_total">总题目数：<span id="surveyQuestionsCountSpan_<s:property value="#request.id"/>">0</span></div>
                <input style='width: 68px;' type="button" onclick="deleteSurveyQuestion('<s:property value="#request.id"/>')" value="删除题目" class="qe_btn">
                <input style='width: 68px;' type="button" onclick="upSurveyQuestion('<s:property value="#request.id"/>')" value="上移" class="qe_btn">
                <input style='width: 68px;' type="button" onclick="downSurveyQuestion('<s:property value="#request.id"/>')" value="下移" class="qe_btn">
            </div>
        </div>
        <div class="vertical_border"></div>
        <div id="surveyQuestionContentPanel_<s:property value="#request.id"/>" class="qe_question_content">
        </div>

    </div>

    <!--    <script type="text/javascript">
            var width = document.getElementById("qe_content").clientWidth - document.getElementById("qe_question_list").clientWidth -6;
            document.getElementById("qe_question_content").style.width = width + "px";
            window.onresize = function(){
                var width = document.getElementById("qe_content").clientWidth - document.getElementById("qe_question_list").clientWidth -6;
                document.getElementById("qe_question_content").style.width = width + "px";
            }
        </script>-->

    <script type="text/javascript">
        function createSurveyQuestion(id,json){
            var buttonPanel = $("#surveyQuestionButtonPanel_"+id);
            var contentPanel = $("#surveyQuestionContentPanel_"+id);
            var qType = json.type;
            if(qType=="<%=QuestionType.RADIO%>"){
                createSurveyRadio(id,buttonPanel,contentPanel,json);
            }else if(qType=="<%=QuestionType.WORD%>"){
                createSurveyWord(id,buttonPanel,contentPanel,json);
            }else if(qType=="<%=QuestionType.CHECKBOX%>"){
                createSurveyCheckbox(id,buttonPanel,contentPanel,json);
            }
            updateSurveyQuestionsCount(id);
        }
        function createSurveyWord(id,buttonPanel,contentPanel,json){
            var pos = buttonPanel.children().length+1;
            var text = (json.content==null||json.content=="")?"简答题":json.content;
            var questionId = json.questionId;
            questionId = (questionId!=null&&questionId!="")?"<input type='hidden' value='"+questionId+"'/>":"";
            var buttonText = setSurveyTrimButtonTitle(text);
            var buttonHtml = "<div onclick=\"chooseSurveyQuestion('"+id+"','"+pos+"')\" id=\"surveyButton_"+id+"_"+pos+"\" class=\"qe_ql_question\">"
                +"<div class=\"qe_ql_question_title\"><span class=\"surveyButtonPos\">"+pos+"</span>. <span class=\"surveyButtonTitle\">"+buttonText+"</span></div>"
                +"<div class=\"qe_ql_question_type\">简答题</div>"
                +questionId
                +"</div>";
            var contentDiv = $("<div id=\"surveyQuestionContentDiv_"+id+"_"+pos+"\"></div>");
            var contentHtml = "<div class=\"qe_question_section\">"
                +"<div class=\"qe_qs_title\">简答题</div>"
                +"<div class=\"qe_qs_content\">"
                +"<textarea onkeyup=\"changeSurveyButtonTitle(this)\" id=\"surveyQuestionContent_"+id+"_"+pos+"\" class=\"qe_qs_content_main\">"+text+"</textarea>"
                +"</div></div>";
            buttonPanel.append(buttonHtml);   
            contentPanel.append(contentDiv);
            contentDiv.append(contentHtml);
            chooseSurveyQuestion(id,pos)
        }
        function createSurveyRadio(id,buttonPanel,contentPanel,json){
            var pos = buttonPanel.children().length+1;
            var text = (json.content==null||json.content=="")?"单项选择题":json.content;
            var questionId = json.questionId;
            questionId = (questionId!=null&&questionId!="")?"<input type='hidden' value='"+questionId+"'/>":"";
            var buttonText = setSurveyTrimButtonTitle(text);
            var buttonHtml = "<div onclick=\"chooseSurveyQuestion('"+id+"','"+pos+"')\" id=\"surveyButton_"+id+"_"+pos+"\" class=\"qe_ql_question\">"
                +"<div class=\"qe_ql_question_title\"><span class=\"surveyButtonPos\">"+pos+"</span>. <span class=\"surveyButtonTitle\">"+buttonText+"</span></div>"
                +"<div class=\"qe_ql_question_type\">单项选择题</div>"
                +questionId
                +"</div>";
            var contentDiv = $("<div id=\"surveyQuestionContentDiv_"+id+"_"+pos+"\"></div>");
            var contentHtml = "<div class=\"qe_question_section\">"
                +"<div class=\"qe_qs_title\">单项选择题</div>"
                +"<div class=\"qe_qs_content\">"
                +"<textarea onkeyup=\"changeSurveyButtonTitle(this)\" id=\"surveyQuestionContent_"+id+"_"+pos+"\" class=\"qe_qs_content_main\">"+text+"</textarea>"
                +"</div></div>";
            //处理答案区
            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
            var answerList = "";
            if(json.answer!=null&&json.answer!=""){
                var jsonAnswer = json.answer;
                var split = jsonAnswer.split(splitFlag);
                for(var i=0;i<split.length;i++){
                    var odd = i%2==0?"_odd":"";
                    var inputHtml = "<div class=\"qe_qs_cm_list_item"+odd+"\">"
                        +"<div class=\"qe_qs_cm_list_item_inner\">"
                        +"<input onfocus=\"chooseSurveyAnswer(this)\" value=\""+split[i]+"\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                        +"</div>"+"</div>";
                    answerList = answerList + inputHtml;
                }
            }else{
                for(var i=0;i<4;i++){
                    var odd = i%2==0?"_odd":"";
                    var inputHtml = "<div class=\"qe_qs_cm_list_item"+odd+"\">"
                        +"<div class=\"qe_qs_cm_list_item_inner\">"
                        +"<input value=\"选项"+(i+1)+"\" onfocus=\"chooseSurveyAnswer(this)\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                        +"</div>"+"</div>";
                    answerList = answerList + inputHtml;
                }
            }
            var answerHtml = "<div class=\"qe_question_section\">"
                +"<div class=\"qe_qs_title\">答案</div>"
                +"<div class=\"qe_qs_content\">"
                +"<div class=\"qe_qs_answer_main\">"
                +"<div class=\"qe_qs_cm_title\">"+"</div>"
                +"<div class=\"qe_qs_cm_list\" id=\"surveyRadioAnswerPanel_"+id+"_"+pos+"\" >"
                + answerList
                +"</div>"
                +"</div>"
                +"<div class=\"qe_qs_answer_tool\">"
                +"<input style='width: 80px;' onclick=\"createSurveyAnswer(this)\" type=\"button\" value=\"添加选项\" class=\"qe_btn\"/><br>"
                +"<input style='width: 80px;' onclick=\"deleteSurveyAnswer(this)\" type=\"button\" value=\"删除选项\" class=\"qe_btn\"/><br>"
                +"<input style='width: 80px;' onclick=\"upSurveyAnswer(this)\" type=\"button\" value=\"上移\" class=\"qe_btn\"/><br>"
                +"<input style='width: 80px;' onclick=\"downSurveyAnswer(this)\" type=\"button\" value=\"下移\" class=\"qe_btn\"/>"
                +"</div>"
                +"<div style=\"clear: both\">"+"</div>"
                +"</div>"
                +"</div>"
            buttonPanel.append(buttonHtml);   
            contentPanel.append(contentDiv);
            contentDiv.append(contentHtml);
            contentDiv.append(answerHtml);
            chooseSurveyQuestion(id,pos);
        }
        
        function createSurveyCheckbox(id,buttonPanel,contentPanel,json){
            var pos = buttonPanel.children().length+1;
            var text = (json.content==null||json.content=="")?"多项选择题":json.content;
            var questionId = json.questionId;
            questionId = (questionId!=null&&questionId!="")?"<input type='hidden' value='"+questionId+"'/>":"";
            var buttonText = setSurveyTrimButtonTitle(text);
            var buttonHtml = "<div onclick=\"chooseSurveyQuestion('"+id+"','"+pos+"')\" id=\"surveyButton_"+id+"_"+pos+"\" class=\"qe_ql_question\">"
                +"<div class=\"qe_ql_question_title\"><span class=\"surveyButtonPos\">"+pos+"</span>. <span class=\"surveyButtonTitle\">"+buttonText+"</span></div>"
                +"<div class=\"qe_ql_question_type\">多项选择题</div>"
                +questionId
                +"</div>";
            var contentDiv = $("<div id=\"surveyQuestionContentDiv_"+id+"_"+pos+"\"></div>");
            var contentHtml = "<div class=\"qe_question_section\">"
                +"<div class=\"qe_qs_title\">多项选择题</div>"
                +"<div class=\"qe_qs_content\">"
                +"<textarea onkeyup=\"changeSurveyButtonTitle(this)\" id=\"surveyQuestionContent_"+id+"_"+pos+"\" class=\"qe_qs_content_main\">"+text+"</textarea>"
                +"</div></div>";
            //处理答案区
            var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
            var answerList = "";
            if(json.answer!=null&&json.answer!=""){
                var jsonAnswer = json.answer;
                var split = jsonAnswer.split(splitFlag);
                for(var i=0;i<split.length;i++){
                    var odd = i%2==0?"_odd":"";
                    var inputHtml = "<div class=\"qe_qs_cm_list_item"+odd+"\">"
                        +"<div class=\"qe_qs_cm_list_item_inner\">"
                        +"<input onfocus=\"chooseSurveyAnswer(this)\" value=\""+split[i]+"\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                        +"</div>"+"</div>";
                    answerList = answerList + inputHtml;
                }
            }else{
                for(var i=0;i<4;i++){
                    var odd = i%2==0?"_odd":"";
                    var inputHtml = "<div class=\"qe_qs_cm_list_item"+odd+"\">"
                        +"<div class=\"qe_qs_cm_list_item_inner\">"
                        +"<input value=\"选项"+(i+1)+"\" onfocus=\"chooseSurveyAnswer(this)\" class=\"qe_qs_cm_list_item_inner_text\"/>"
                        +"</div>"+"</div>";
                    answerList = answerList + inputHtml;
                }
            }
            var answerHtml = "<div class=\"qe_question_section\">"
                +"<div class=\"qe_qs_title\">答案</div>"
                +"<div class=\"qe_qs_content\">"
                +"<div class=\"qe_qs_answer_main\">"
                +"<div class=\"qe_qs_cm_title\">"+"</div>"
                +"<div class=\"qe_qs_cm_list\" id=\"surveyCheckboxAnswerPanel_"+id+"_"+pos+"\" >"
                + answerList
                +"</div>"
                +"</div>"
                +"<div class=\"qe_qs_answer_tool\">"
                +"<input style='width: 80px;' onclick=\"createSurveyAnswer(this)\" type=\"button\" value=\"添加选项\" class=\"qe_btn\"/><br>"
                +"<input style='width: 80px;' onclick=\"deleteSurveyAnswer(this)\" type=\"button\" value=\"删除选项\" class=\"qe_btn\"/><br>"
                +"<input style='width: 80px;' onclick=\"upSurveyAnswer(this)\" type=\"button\" value=\"上移\" class=\"qe_btn\"/><br>"
                +"<input style='width: 80px;' onclick=\"downSurveyAnswer(this)\" type=\"button\" value=\"下移\" class=\"qe_btn\"/>"
                +"</div>"
                +"<div style=\"clear: both\">"+"</div>"
                +"</div>"
                +"</div>"
            buttonPanel.append(buttonHtml);   
            contentPanel.append(contentDiv);
            contentDiv.append(contentHtml);
            contentDiv.append(answerHtml);
            chooseSurveyQuestion(id,pos);
        }
        
        function chooseSurveyQuestion(id,pos){
            var button = $("#surveyButton_"+id+"_"+pos);
            var buttonPanel = $("#surveyQuestionButtonPanel_"+id);
            var contentPanel = $("#surveyQuestionContentPanel_"+id);
            if(button.attr("class").lastIndexOf("cur")==-1){
                buttonPanel.children().each(function(){
                    if($(this).attr("class").lastIndexOf("cur")!=-1){
                        $(this).attr("class",$(this).attr("class").replace("cur",""));
                    }
                });
                button.attr("class",button.attr("class")+" cur");
                contentPanel.children().each(function(){
                    if($(this).attr("id")=="surveyQuestionContentDiv_"+id+"_"+pos){
                        $(this).show();
                    }else{
                        $(this).hide();
                    }
                });
            }
        }
        function chooseSurveyAnswer(obj){
            var answerList =  $(obj).parent().parent().parent();
            var chooseAnswer = $(obj).parent().parent();
            answerList.children().each(function(){
                if($(this).attr("class").indexOf("cur")!=-1){
                    $(this).attr("class",$(this).attr("class").replace("cur",""));
                } 
            });
            chooseAnswer.attr("class",chooseAnswer.attr("class")+" cur");
            chooseAnswer.focus();
        }
        
        function setSurveyTrimButtonTitle(value){
            value = value.length>12?value.substring(0,12)+"...":value;
            return value;
        }
        
        function changeSurveyButtonTitle(obj){
            var id = getSurveyButtonId(obj);
            var pos = getSurveyButtonPos(obj);
            var button = $("#surveyButton_"+id+"_"+pos);
            var value = $.trim($(obj).val());
            value = setSurveyTrimButtonTitle(value);
            button.find(".surveyButtonTitle").text(value)
        }
        function getSurveyButtonPos(obj){
            var id = $(obj).attr("id");
            var sp = id.split("_");
            return sp[2];
        }
        function getSurveyButtonId(obj){
            var id = $(obj).attr("id");
            var sp = id.split("_");
            return sp[1];
        }
        function deleteSurveyQuestion(id){
            var chooseButton = $("#surveyQuestionButtonPanel_"+id+" .cur");
            if(chooseButton.html()!=null){
                var pos = getSurveyButtonPos(chooseButton);
                chooseButton.remove();
                var content = $("#surveyQuestionContentDiv_"+id+"_"+pos);
                content.remove();
                orderSurveyQuestion(id);
            }
            updateSurveyQuestionsCount(id);
        }
        function orderSurveyQuestion(id){
            var buttonPanel = $("#surveyQuestionButtonPanel_"+id);
            var contentPanel = $("#surveyQuestionContentPanel_"+id);
            buttonPanel.children().each(function(i){
                var pos = i+1;
                $(this).find(".surveyButtonPos").text(pos);
                var html = $(this).html();
                $(this).parent().append("<div class=\"qe_ql_question\" onclick=\"chooseSurveyQuestion('"+id+"','"+pos+"')\" id=\"surveyButton_"+id+"_"+pos+"\">"+html+"</div>");
                $(this).remove();
            });
            contentPanel.children().each(function(n){
                var pos = n+1
                $(this).attr("id", $(this).attr("id").substring(0,$(this).attr("id").length-1)+pos);
                $(this).find("[id]").each(function(o){
                    $(this).attr("id", $(this).attr("id").substring(0,$(this).attr("id").length-1)+pos); 
                });
            });
        }
        function createSurveyAnswer(obj){
            var answerList = $(obj).parent().prev().find("[id]");
            var className = answerList.children().last().attr("class");
            className = className==null?"qe_qs_cm_list_item":className;
            var answerClass = className.indexOf("odd")!=-1?"qe_qs_cm_list_item":"qe_qs_cm_list_item_odd";
            var innerDiv = $("<div class=\"qe_qs_cm_list_item_inner\"></div>");
            var answerHtml = $("<div class=\""+answerClass+"\"></div>")
            var inputHtml = $("<input onfocus=\"chooseSurveyAnswer(this)\" class=\"qe_qs_cm_list_item_inner_text\"/>");
            innerDiv.append(inputHtml);
            answerHtml.append(innerDiv);
            answerList.append(answerHtml);
            chooseSurveyAnswer(inputHtml);
        }
        function deleteSurveyAnswer(obj){
            var answerList = $(obj).parent().prev().find("[id]");
            var choose = answerList.children("[class$='cur']");
            if(choose.html()!=null){
                choose.remove();
            }
        }
        function upSurveyAnswer(obj){
            var answerList = $(obj).parent().prev().find("[id]");
            var choose = answerList.children("[class$='cur']");
            if(choose.html()!=null){
                if( choose.prev().length > 0) {
                    var tmp = choose.clone();
                    var oo = choose.prev();
                    choose.remove();
                    oo.before(tmp);
                }
            }
        }
        function downSurveyAnswer(obj){
            var answerList = $(obj).parent().prev().find("[id]");
            var choose = answerList.children("[class$='cur']");
            if(choose.html()!=null){
                if( choose.next().length > 0) {
                    var tmp = choose.clone();
                    var oo = choose.next();
                    choose.remove();
                    oo.after(tmp);
                }
            }
        }
        function upSurveyQuestion(id){
            var chooseButton = $("#surveyQuestionButtonPanel_"+id+" .cur");
            if(chooseButton.html()!=null){
                if(chooseButton.prev().length > 0) {
                    var tmp = chooseButton.clone();
                    var oo = chooseButton.prev();
                    chooseButton.remove();
                    oo.before(tmp);
                    var chooseContent = $("#surveyQuestionContentDiv_"+id+"_"+getSurveyButtonPos(chooseButton));
                    var oc = chooseContent.prev();
                    oc.before(chooseContent);
                    orderSurveyQuestion(id);
                }
            }
        }
        function downSurveyQuestion(id){
            var chooseButton = $("#surveyQuestionButtonPanel_"+id+" .cur");
            if(chooseButton.html()!=null){
                if( chooseButton.next().length > 0) {
                    var tmp = chooseButton.clone();
                    var oo = chooseButton.next();
                    chooseButton.remove();
                    oo.after(tmp);
                    var chooseContent = $("#surveyQuestionContentDiv_"+id+"_"+getSurveyButtonPos(chooseButton));
                    var oc = chooseContent.next();
                    oc.after(chooseContent);
                    orderSurveyQuestion(id);
                }
            }
        }
        function updateSurveyQuestionsCount(id){
            var buttonPanel = $("#surveyQuestionButtonPanel_"+id);
            var count = buttonPanel.children().size();
            $("#surveyQuestionsCountSpan_"+id).text(count);
        }
        function getSurveyQuestionsJson(id){
            var buttonPanel = $("#surveyQuestionButtonPanel_"+id);
            var contentPanel = $("#surveyQuestionContentPanel_"+id);
            var survey = [];
            var questions = buttonPanel.children();
            for(var i=0;i<questions.length;i++){
                var qJson = {};
                var button = $(questions[i]);
                var pos = getSurveyButtonPos(button);
                var content = $.trim($("#surveyQuestionContent_"+id+"_"+pos).val());
                var type = $.trim(button.find(".qe_ql_question_type").text());
                var questionId = button.find("input[type='hidden']").val();
                if(type=="单项选择题"){
                    type = "<%=QuestionType.RADIO%>";
                    var answer="";
                    $("#surveyRadioAnswerPanel_"+id+"_"+pos).children().each(function(i){
                        var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                        var inputValue = $.trim($(this).find("input").val());
                        if(inputValue!=null&&inputValue!=""){
                            answer = answer +inputValue+splitFlag;
                        }
                    });
                    answer = answer.substring(0,answer.length-9);
                    qJson["answer"] = answer;
                }else if(type=="简答题"){
                    type = "<%=QuestionType.WORD%>";
                }else if(type=="多项选择题"){
                    type = "<%=QuestionType.CHECKBOX%>";
                    var answer="";
                    $("#surveyCheckboxAnswerPanel_"+id+"_"+pos).children().each(function(i){
                        var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
                        var inputValue = $.trim($(this).find("input").val());
                        if(inputValue!=null&&inputValue!=""){
                            answer = answer +inputValue+splitFlag;
                        }
                    });
                    answer = answer.substring(0,answer.length-9);
                    qJson["answer"] = answer;
                }
                qJson["questionId"] = questionId;
                qJson["content"] = content;
                qJson["type"] = type;
                qJson["pos"] = i+1 ; 
                survey.push(qJson);
            }
            return survey;
        }
        
        function loadSurveyJson(toolId){
            $.ajax({
                url:"/common/lads/ladsSurveyAction_loadSurveyJson.action",
                type: "POST",
                data:{"toolId":toolId},
                dataType:"json",
                async: true,
                success:function(qJson){
                    var questionList = qJson.surveyQuestions;
                    for(var i=0;i<questionList.length;i++){
                        var question = questionList[i];
                        createSurveyQuestion(toolId,question)
                    }
                }
            });
        }
        
        
        $(function(){
            loadSurveyJson("<s:property value="#request.id"/>");
        })
        
    </script>
</div>