<%-- 
    Document   : surveytoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm"><b>标题：</b><s:property value="#request.title"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>分数：</b><s:property value="#request.surveyScore"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>描述：</b><s:property value="#request.surveyDescription"/>
    </li>
    <li class="rr"></li>
</ul>
<div class="content_big">
    <div class="content_big_box">
        <div class="content_top_l"></div>
        <div class="content_main">
            <div class="mgbotom main_cont_tab">
                <ul id="survey_ul_<s:property value="#request.id"/>" >
                    <li class="tabcur">
                        <a href="javascript:void(0)" onclick="loadSurveyQuestionStatistics('<s:property value="#request.id"/>')">
                            调查问卷详细统计</a>
                    </li>
<!--                    <li>
                        <a href="javascript:void(0)" onclick="">
                            个人选项统计</a>
                    </li>-->
                </ul>
            </div>
            <div id="surveyContent_<s:property value="#request.id"/>" class="main_cont_table_box divtop">

            </div>
        </div>
        <div class="content_bottom_l"></div>
        <script type="text/javascript">
            function loadSurveyQuestionStatistics(toolId){
                var surveyContentDiv = $("#surveyContent_"+toolId);
                surveyContentDiv.html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
                var url = "/module/common/lads/monitoring/survey/survey_question_statistics.jsp"
                surveyContentDiv.load(url,{"toolId":toolId});
            }
            
            $(function(){
                loadSurveyQuestionStatistics("<s:property value="#request.id"/>");
            })
        </script>
    </div>
</div>