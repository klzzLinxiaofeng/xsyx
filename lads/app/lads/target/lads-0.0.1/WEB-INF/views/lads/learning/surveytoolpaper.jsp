<%-- 
    Document   : surveytoolpaper
    Created on : 2013-5-20, 11:26:58
    Author     : 罗志明
--%>

<%@page import="com.gzxtjy.lads.constants.surveyToolCons.QuestionType"%>
<%@page import="com.gzxtjy.lads.constants.surveyToolCons.AnswerConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lads" uri="/WEB-INF/lads" %>
<!DOCTYPE html>
<div class="questionnaire_main">
    <div class="article">
        <h1 class="article_title"><s:property value="#request.title"/></h1>
        <p>
            <s:property escape="false" value="#request.surveyDescription"></s:property>
        </p>
    </div>
    <div class="questions_area">
        <ul class="questions" id="surveyQuestionsUl_<s:property value="#request.id"/>">
        </ul>
        <input class="questionnaire_btn" onclick="saveSurveyUserAnswer('<s:property value="#request.id"/>')" type="button" value="提交答卷">
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
            for(var i=0;i<split.length;i++){
                var checked = "";
                if(userAnswer==letters[i]){
                    checked = "checked"
                }
                var inputHtml =  "<label class=\"checkbox\">"
                    +"<input "+checked+" value=\""+letters[i]+"\" name=\"surveyRadioAnswer_"+toolId+"_"+pos+"\" type=\"radio\">"
                    +"&nbsp;&nbsp;"+letters[i]+".&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+split[i]
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
                    +"<input "+checked+" value=\""+letters[i]+"\" name=\"surveyCheckboxAnswer_"+toolId+"_"+pos+"\" type=\"checkbox\">"
                    +"&nbsp;&nbsp;"+letters[i]+".&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+split[i]
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
        var userAnswer = json.userAnswer;
        userAnswer = (userAnswer!=null&&userAnswer!="")?userAnswer:"";
        var pos = json.pos;
        var questionId = json.questionId;
        var typeHtml = "<input type='hidden' value='word_"+questionId+"''/>";
        var html = "<li class=\"question\">"
            +"<h2>"+pos+".&nbsp;&nbsp;"+text+"<span class=\"question_red\">*</span></h2>"
            +"<div><textarea cols=\"90\" rows=\"4\" name=\"surveyWordAnswer_"+toolId+"_"+pos+"\" >"+userAnswer+"</textarea></div>"
            + typeHtml
            +"</li>"
        questionUl.append(html);
    }
    
    function saveSurveyUserAnswer(toolId){
        //预览模式不需提交用户数据
        if(!previewMode){
            var userId = "<lads:getEmbedUser sysType="${requestScope.sysType}"/>";
            if(userId!=null&&userId!=""){
                var data = {};
                data["toolId"] = toolId;
                data["userId"] = userId;
                data["userAnswers"] = JSON.stringify(getSurveyUserAnswerJson(toolId));
                $.ajax({
                    url:"/common/lads/ladsSurveyAction_saveUserAnswer.action",
                    type: "POST",
                    data:data,
                    dataType:"json",
                    async: true,
                    success:function(qJson){
                        alert("提交成功");
                    }
                });
            }else{
                alert("请先登录");
            }
    }else{
        alert("预览模式没有保存调查数据");
    }
}
    
function getSurveyUserAnswerJson(toolId){
    var questionUl = $("#surveyQuestionsUl_"+toolId);
    var answers = [];
    var splitFlag = "<%=AnswerConstants.ANSWER_SPLIT_FLAG%>";
    questionUl.children().each(function(o){
        var qType = $(this).find("input[type='hidden']").val().split("_")[0];
        var questionId = $(this).find("input[type='hidden']").val().split("_")[1];
        var pos = o+1
        var answer = {};
        var answerValue = "";
        if(qType=="<%=QuestionType.RADIO%>"){
            answerValue = $(this).find("input[type='radio'][checked]").val();
        }else if(qType=="<%=QuestionType.CHECKBOX%>"){
            $(this).find("input[type='checkbox'][checked]").each(function(){
                answerValue = answerValue + $(this).val() + splitFlag;
            })
            answerValue = answerValue.substring(0,answerValue.length-9);
        }else if(qType=="<%=QuestionType.WORD%>"){
            answerValue = $.trim($(this).find("textarea").val());
        }
        answer["type"] = qType;
        answer["answer"] = answerValue;
        answer["pos"] = pos;
        answer["questionId"] = questionId;
        answers.push(answer);
    });
    return answers;
}
    
function loadSurveyJson(toolId){
    //预览模式下不需要导入用户原有数据
    if(!previewMode){
        var userId = "<lads:getEmbedUser sysType="${requestScope.sysType}"/>";
    }
    $.ajax({
        url:"/common/lads/ladsSurveyAction_loadSurveyJson.action",
        type: "POST",
        data:{"toolId":toolId,"userId":userId},
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
