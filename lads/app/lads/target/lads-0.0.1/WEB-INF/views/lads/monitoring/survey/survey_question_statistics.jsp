<%-- 
    Document   : survey_question_statistics
    Created on : 2013-5-23, 17:03:25
    Author     : Administrator
--%>

<%@page import="com.gzxtjy.lads.constants.surveyToolCons.QuestionType"%>
<%@page import="com.gzxtjy.lads.constants.surveyToolCons.AnswerConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String toolId = request.getParameter("toolId");
    request.setAttribute("id", toolId);
%>
<!DOCTYPE html>
<div class="questionnaire_main">
    <div class="questions_area">
        <ul class="questions" id="surveyQuestionsUl_<s:property value="#request.id"/>">
        </ul>
    </div>
</div>
<script type="text/javascript">
    function createSurveyQuestion(toolId,json){
        var questionUl = $("#surveyQuestionsUl_"+toolId);
        var qType = json.type;
        if(qType=="<%=QuestionType.RADIO%>"){
            createSurveyRadio(toolId,json);
        }else if(qType=="<%=QuestionType.WORD%>"){
            createSurveyWord(toolId,json);
        }else if(qType=="<%=QuestionType.CHECKBOX%>"){
            createSurveyCheckbox(toolId,json);
        }
    }
    
    function createSurveyRadio(toolId,json){
        var questionUl = $("#surveyQuestionsUl_"+toolId);
        var text = (json.content==null||json.content=="")?"没有输入内容..":json.content;
        //处理答案区
        var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
        var answerList = "";
        var letters = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
        var pos = json.pos;
        var questionId = json.questionId;
        var typeHtml = "<input type='hidden' value='radio_"+questionId+"'/>"
        if(json.answer!=null&&json.answer!=""){
            var jsonAnswer = json.answer;
            var split = jsonAnswer.split(splitFlag);
            var userAnswer = json.userAnswer;
            var answerPercents = json.answerPercents;
            for(var i=0;i<split.length;i++){
                var checked = "";
                if(userAnswer==letters[i]){
                    checked = "checked"
                }
                var inputHtml =  "<label class=\"checkbox\">"
                    +"&nbsp;&nbsp;"+letters[i]+".&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+split[i]
                    +"<img src=\"/module/common/lads/monitoring/finishedImage.jsp?percent="+answerPercents[i]+"\">"
                    +"</label>"
                answerList = answerList + inputHtml;
            }
        }else{
            answerList = "没有可选择的答案...";
        }
        var html = "<li class=\"question\">"
            +"<h2>"+pos+".&nbsp;&nbsp;"+text+"<span class=\"question_red\">*</span></h2>"
            +"<div>"
            +answerList
            +"</div>"
            + typeHtml
            +"</li>"
        questionUl.append(html);
    }
    
    function createSurveyCheckbox(toolId,json){
        var questionUl = $("#surveyQuestionsUl_"+toolId);
        var text = (json.content==null||json.content=="")?"没有输入内容..":json.content;
        //处理答案区
        var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
        var answerList = "";
        var letters = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
        var pos = json.pos;
        var questionId = json.questionId;
        var answerPercents = json.answerPercents;
        var typeHtml = "<input type='hidden' value='checkbox_"+questionId+"''/>";
        if(json.answer!=null&&json.answer!=""){
            var jsonAnswer = json.answer;
            var split = jsonAnswer.split(splitFlag);
            var userAnswer = json.userAnswer;
            if(userAnswer!=null&&userAnswer!=""){
                var usplit = userAnswer.split(splitFlag);
            }
            for(var i=0;i<split.length;i++){
                var checked = "";
                if(usplit!=null){
                    for(var o=0;o<usplit.length;o++){
                        if(usplit[o]==letters[i]){
                            checked = "checked"
                            break;
                        }
                    }
                }
                var inputHtml =  "<label class=\"radio\">"
                    +"&nbsp;&nbsp;"+letters[i]+".&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+split[i]
                    +"<img src=\"/module/common/lads/monitoring/finishedImage.jsp?percent="+answerPercents[i]+"\">"
                    +"</label>"
                answerList = answerList + inputHtml;
            }
        }else{
            answerList = "没有可选择的答案...";
        }
        var html = "<li class=\"question\">"
            +"<h2>"+pos+".&nbsp;&nbsp;"+text+"<span class=\"question_red\">*</span></h2>"
            +"<div>"
            +answerList
            +"</div>"
            + typeHtml
            +"</li>"
        questionUl.append(html);
    }
    
    function createSurveyWord(toolId,json){
        var questionUl = $("#surveyQuestionsUl_"+toolId);
        var text = (json.content==null||json.content=="")?"没有输入内容..":json.content;
        var userAnswer = "";
        var pos = json.pos;
        var questionId = json.questionId;
        var wordAnswers = json.wordAnswers;
        for(var h=0;h<wordAnswers.length;h++){
            userAnswer = userAnswer+"<label class=\"radio\">"+wordAnswers[h].realName+":&nbsp;&nbsp;&nbsp;&nbsp;"+wordAnswers[h].userAnswer+"</label>";
        }
        var typeHtml = "<input type='hidden' value='word_"+questionId+"''/>";
        var html = "<li class=\"question\">"
            +"<h2>"+pos+".&nbsp;&nbsp;"+text+"<span class=\"question_red\">*</span></h2>"
            +"<div>"
            + userAnswer
            +"</div>"
            + typeHtml
            +"</li>"
        questionUl.append(html);
    }
    
    function loadSurveyQuestionStatisticsJson(toolId){
        $.ajax({
            url:"/common/lads/ladsSurveyAction_loadSurveyQuestionStatisticsJson.action",
            type: "POST",
            data:{"toolId":toolId,"sysType":$("#sysType").val(),"ldId":$("#ldId").val()},
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
        loadSurveyQuestionStatisticsJson("<s:property value="#request.id"/>");
       
    })  
</script>