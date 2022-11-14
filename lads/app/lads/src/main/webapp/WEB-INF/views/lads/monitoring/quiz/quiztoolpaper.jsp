<%-- 
    Document   : quiztoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm"><b>标题：</b><s:property value="#request.title"/></li>
    <li class="rr"></li>
</ul>
<div class="content_big">
    <div class="content_big_box">
        <div class="content_top_l"></div>
        <div class="content_main">
            <div class="mgbotom main_cont_tab">
                <ul id="quiz_ul_<s:property value="#request.id"/>" >
                 <%--   <li>
                        <a target="_self" href="/teacher/quizLib/quizPublishedAction_reportQuizChartIndex.action?pqId=${pq.id}">成绩趋势图</a>
                    </li>--%>
                    <li class="tabcur">
                        <a href="javascript:void(0)" onclick="loadQuizTab(this,'<s:property value="#request.id"/>','/common/lads/ladsQuizAction_reportQuizQuestion.action?id=')">
                            题目详细统计</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="loadQuizTab(this,'<s:property value="#request.id"/>','/common/lads/ladsQuizAction_getUserResultList.action?sysType='+$('#sysType').val()+'&id=')">
                            个人成绩单</a>
                    </li>
                </ul>
            </div>
            <div id="quizContent_<s:property value="#request.id"/>" class="main_cont_table_box divtop">
                
            </div>
        </div>
        <div id="quizDetailDiv_<s:property value="#request.id"/>"></div>
        <div class="content_bottom_l"></div>
        <script type="text/javascript">
            //overlay弹出层公用函数
            var overlayObj;
            function openOverlay(elm, url, close_onclick, one_instance) {
                ///var msg = $('#head_result');
                if (close_onclick == undefined) {
                    close_onclick = true;
                }
                if (one_instance == undefined) {
                    one_instance = true;
                }
                var top = (document.body.scrollTop + document.body.clientHeight / 2 - 600 / 2) + "px";
                var left = (document.body.scrollLeft + document.body.clientWidth / 2 - 800 / 2);
                overlayObj = $(elm).overlay({
                    api : true,
                    closeOnClick : close_onclick,
                    oneInstance: one_instance,
                    left: left,
                    top: '10%',
                    expose: {
                        color: '#333',
                        opacity: 0.7,
                        zIndex:10000
                    }
                });
                $(elm).load(url,false,function(){
                    overlayObj.load()
                });
            }
            function quizDetail(id,qeId){
                openOverlay("#quizDetailDiv_"+id, "/common/lads/ladsQuizAction_reportQuizQuestionDetail.action?sysType="+$("#sysType").val()+"&id="+qeId+"&t="+ Math.random() + new Date().getMilliseconds(), "closeOverlay", false);
            }
            function loadQuizTab(obj,id,url){
                $("#quizContent_"+id).html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
                $("#quizContent_"+id).load(url+id);
                $("#quiz_ul_"+id).children().each(function(){
                    $(this).removeAttr("class");
                });
                $(obj).parent().attr("class", "tabcur");
            }
            $(function(){
                var obj = $("#quiz_ul_<s:property value="#request.id"/>").children().children().get(0);
                loadQuizTab(obj,'<s:property value="#request.id"/>','/common/lads/ladsQuizAction_reportQuizQuestion.action?id=');
            })
        </script>
    </div>
</div>